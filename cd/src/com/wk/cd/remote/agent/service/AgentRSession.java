package com.wk.cd.remote.agent.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.util.AgentCMDUtil;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 自定义的Agent会话
 * Class Description:
 * @author 12049
 *         通过AgentRSession连接AgentServer，并通过UUID生成唯一的ID标识保存对应的数据
 */
public class AgentRSession implements RSession {

	public static final int ASYN_TYPE = 2;              //异步
	public static final int SYNCHRO_TYPE = 1;           //同步
	private static final String LINE_SPLIT = "\n";
	private static final Log logger = LogFactory.getLog();
	private static final String SHORT_TIMEOUT = "3000";
	private static final String LONG_TIMEOUT = "86400000";
	
	private final boolean isStepSupport;             //是否支持脚本执行      true:支持
	private final IMPL_TYPE impl_type;               
	private final AgentClient client;                
	private final String id;                         
	private final int stepCount;                     //shell脚本总的步骤数
	private int cur_step = 1;                        //当前传入的步骤数
	private StringBuffer sb;                         
	private int type;                                //1.正常的命令  2.交互式
	private String remote_temp_path;                 //目标机器临时脚本路径
	
	private boolean stop_flag;                  //暂停标志
	
	private Map<String, String> pidMap = new HashMap<String, String>();
	
	//类似FTP无需生成临时脚本的, type 是否为交互式  flag是否需要获取主目录路径 remote_temp_path可以为null
	public AgentRSession(String ip, int port, IMPL_TYPE impl_type, int stepCount, int type, boolean flag, String remote_temp_path){
		this(ip, port, impl_type, stepCount, type, flag, remote_temp_path, null, 0, 0);
	}
	
	public AgentRSession(String ip, int port, IMPL_TYPE impl_type, int stepCount, int type, boolean flag, String remote_temp_path, DtSourceInfo dt_source_info, int start_num, int offset) {
		this.type = type;
		this.impl_type = impl_type;
		this.stepCount = stepCount;
		this.id = UUID.randomUUID().toString().replaceAll("\\-", ""); // 通过UUID随机获取ID
		this.isStepSupport = Boolean.parseBoolean(CfgTool.getProjectPropterty("cms.agent.isStepSupport"));
		
		sb = new StringBuffer();
		client = new AgentClient(id, ip, port, isStepSupport, impl_type, stepCount, type, remote_temp_path, dt_source_info, start_num, offset);
		
		if(impl_type != IMPL_TYPE.FTP && impl_type != IMPL_TYPE.SQL){
			//通过flag判断是否需要发送cd命令,类似监控之类的就无需发送，会耗时
			if(flag){
				//切换到主目录
				String remote_env = AgentCommonTool.checkRemoteEnv(ip);
				if(remote_env.equals("windows")){
					client.exeShell("cd", SHORT_TIMEOUT); 
				}else{
					client.exeShell("set +H", SHORT_TIMEOUT);
					client.exeShell("cd", LONG_TIMEOUT); 
				}
			}
		}
		logger.info("client[{}] connect agent success.", id);
	}

	/**
	 * Description: 发送单条命令
	 * @param cmd
	 * @return
	 */
	@Override
	public ShExecRsBean sendCmd(String cmd) {
		logger.info("client[{}] execute cmd:[{}]", id, cmd);
		ShExecRsBean bean = new ShExecRsBean();
		/**
		 * 如果是shell或python命令，需要将多条命令一起写入脚本
		 */
		if(isStepSupport && impl_type != IMPL_TYPE.FTP){
			if(cur_step < stepCount){
				cur_step++;
				sb.append(cmd).append(LINE_SPLIT);
				bean.setIs_succ(true);
			}else if(cur_step == stepCount){
				sb.append(cmd);
				bean = client.exeShell(sb.toString(), LONG_TIMEOUT);
			}
		}else{
			bean = client.exeShell(cmd, LONG_TIMEOUT);
		}
		dealRsMsgByStop(bean);
		return bean;
	}

	/**
	 * 发送多条命令
	 * Description: 
	 * @param cmds
	 * @return
	 */
	public ShExecRsBean sendCmds(String[] cmds) {
		logger.info("---client[{}] execute cmds:[{}]---", id, StringUtil.ary2str(cmds, LINE_SPLIT));
		ShExecRsBean bean = new ShExecRsBean();
		if(Assert.notEmpty(cmds)){
			if(isStepSupport && impl_type != IMPL_TYPE.FTP){
				bean = client.exeShell(StringUtil.ary2str(cmds, LINE_SPLIT), LONG_TIMEOUT);
			}else{
				for (int i = 0; i < cmds.length; i++){
					bean = client.exeShell(cmds[i], LONG_TIMEOUT);
				} 
			}
		}
		
		dealRsMsgByStop(bean);
		return bean;
	}
	
	/** 
	 * Description: 交互式Agent发送命令
	 * @param cmd
	 * @param is_read true: first send cmd   false: os write cmd 
	 * @throws IOException 
	 */
	@Override
	public void asyncRunStage(String[] cmds, boolean is_read, boolean sensitive_flag) throws IOException {
		if(Assert.notEmpty(cmds)){
			String cmd = StringUtil.ary2str(cmds, LINE_SPLIT);
			logger.info("---client[{}] execute cmds:[{}]---", id, cmd);
			if(is_read){
				ShExecRsBean bean = client.exeShell(cmd, LONG_TIMEOUT);
				pidMap.put(client.getId(), bean.getPid());
			}else{
				if(cmd.trim().equalsIgnoreCase("ctrl+c") || cmd.equalsIgnoreCase("ctrl c")){
					//另起一个进程去杀掉交互式的pid
					AgentRSession session = new AgentRSession(client.getIp(), client.getPort(), IMPL_TYPE.SHELL, 1, 1, true, remote_temp_path);
					session.sendCmd("kill -9 " + pidMap.get(client.getId()));
				}else{
					client.exeShell("put " + cmd + "\n", LONG_TIMEOUT);
				}
				
			}
		}
	}

	/** 
	 * Description: Agent交互式向服务器端请求数据流中的数据
	 * @return 
	 */
	@Override
	public AsyncMsgBean asyncRunMsg() {
		logger.info("---client[{}] interactive get data---", id);
		AsyncMsgBean interactMsgBean = new AsyncMsgBean();
		ShExecRsBean bean = client.exeShell(AgentCMDUtil.INTERACT_GET_RS_CMD, LONG_TIMEOUT);
		
		String exe_msg = StringUtil.usFmWs(bean.getRs_msg());
		interactMsgBean.setMsg(exe_msg);
		interactMsgBean.setEnd_flag(bean.getExitStatus() == 0);
		
		//检验进程是否被杀死（发送ctrl+c）
		AgentRSession session = new AgentRSession(client.getIp(), client.getPort(), IMPL_TYPE.SHELL, 1, 1, true, remote_temp_path);
		bean = session.sendCmd("ps -ef | grep " + pidMap.get(client.getId()) + " | grep -v grep");
		if(Assert.notEmpty(bean) && Assert.isEmpty(bean.getRs_msg())){
			interactMsgBean.setEnd_flag(true);
		}
		return interactMsgBean;
	}
	
	/**
	 * 手动停止某个执行进程
	 * Description: 
	 * @param id
	 * @return
	 */
	@Override
	public void disconnect() {
		logger.info("---client[{}] disconnect agent begin---", id);
		client.exeShell(AgentCMDUtil.STOP_EXE_PROCESS_CMD, LONG_TIMEOUT);
		stop_flag = true;
		logger.info("---client[{}] disconnect agent end---", id);
	}

	/**
	 * 处理Agent执行停止信息
	 * Description: 
	 * @param bean
	 */
	private void dealRsMsgByStop(ShExecRsBean bean){
		if(stop_flag){
			bean.setRs_msg(AbstractRCallService.CLOSE_MSG);
			bean.setErr_msg("");
		}
	}
	
	@Override
	public void send(String cmd) throws IOException {
		
	}

	@Override
	public String getReplyUntilPrompt(String pattern) throws IOException {
		return null;
	}

	@Override
	public String getReplyUntilPrompt(String[] patterns) throws IOException {
		return null;
	}

	@Override
	public int getExitStatus() throws IOException {
		return 0;
	}
}
