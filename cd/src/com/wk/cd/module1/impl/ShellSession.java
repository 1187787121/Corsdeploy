package com.wk.cd.module1.impl;

import java.io.IOException;

import com.wk.Controller;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.service.CmdsExecuteUtil;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleSessionBase;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.remote.sh.service.JSchRCallService;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.TelnetRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 远程Shell会话连接 Created by 姜志刚 on 2016/11/25.
 */
public class ShellSession
		extends ModuleSessionBase {

	private static final Log logger = LogFactory.getLog();

	protected static final int DEFAULT_TIMEOUT = 3600;

	@Inject
	private JSchRCallService ssh_svc;

	@Inject
	private TelnetRCallService telnet_svc;

	@Inject
	private DtSocService dtSocService;

	private RConnection conn;
	private RSession sess;
	//Agnet 执行的时候传入的执行类型 用来区分 异步执行还是同步执行
	private final int EXECUTE_TYPE;
	private String remote_relative_dir;

	public ShellSession(ModuleSourceInfo module_source_info, int step_count,int execute_type, String remote_relative_dir) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
		this.EXECUTE_TYPE = execute_type;
		this.remote_relative_dir = remote_relative_dir;
	}

	@Override
	protected void implConnect() {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		try {
			PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
			
			if (protocol_type == PROTOCOL_TYPE.SSH) {
				RBean bean = getRBeanFromSoc(dt_info);
				conn = ssh_svc.getConnection(bean);
				sess = ssh_svc.openSession(conn);
			} else if (protocol_type == PROTOCOL_TYPE.TELNET) {
				RBean bean = getRBeanFromSoc(dt_info);
				conn = telnet_svc.getConnection(bean);
				sess = telnet_svc.openSession(conn);
			} else if (protocol_type == PROTOCOL_TYPE.AGENT) {
				sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),
						IMPL_TYPE.SHELL, step_count, this.EXECUTE_TYPE, true, remote_relative_dir);
			} else {
				throw new RuntimeException(protocol_type + " not supported");
			}
			String vars = dt_info.getEnvironment_variables();
			
			if (!com.wk.util.StringUtil.isEmpty(vars)) {
				executeEnvironmentVariables(sess, dt_info, dt_info.getProtocol_type());
			}
			logger.info("{}'s session {} connected", dt_info.getSoc_name(), sess);
		} catch (RuntimeException t) {
			t.printStackTrace();
			logger.error("连接数据源[{}]异常", dt_info.getSoc_name(), t);
			throw t;
		}
	}

	@Override
	protected void implDisconnect() {
		if (sess != null) {
			sess.disconnect();
		}
		if (conn != null) {
			conn.disconnect();
		}
	}

	public ShExecRsBean sendCmd(String cmd) {
		return sess.sendCmd(cmd);
	}

	public ShExecRsBean sendCmds(String[] cmds, String remote_relative_dir) {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
		if (protocol_type == PROTOCOL_TYPE.AGENT) {
			if (sess instanceof AgentRSession) {
				AgentRSession a = (AgentRSession) sess;
				return a.sendCmds(cmds);
			}
		} else {
			DtSourceInfo ftp_info = dtSocService.changeDtSource(dt_info);
			CmdsExecuteUtil execute = new CmdsExecuteUtil(IMPL_TYPE.SHELL, ftp_info, remote_relative_dir);
			String[] remote_file = execute.uploadFile(cmds);
			StringBuilder sb = new StringBuilder();
			sb.append("chmod 777 ").append(remote_file[0]).append(';');
			sb.append("cd ").append(dt_info.getUser_root_path()).append(';');
			if("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)){
				sb.append(remote_file[0]).append(" 2>>").append(remote_file[1]).append(';');
			}else{
				sb.append(remote_file[0]).append(';');
			}
			
//			sess.sendCmd("chmod 777 " + remote_file[0]);
//			sess.sendCmd("cd "+dt_info.getUser_root_path());
//			String cmd = remote_file[0] + " 2>>" + remote_file[1];
			ShExecRsBean result = sess.sendCmd(sb.toString());
			logger.debug("send cmd is success[{}]",result.getIs_succ());
			logger.debug("send cmd msg [{}]",result.getRs_msg());
			logger.debug("send cmd error_msg [{}]",result.getErr_msg());
			String msg = result.getRs_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				execute.deleteFile(remote_file[0]);
				execute.deleteFile(remote_file[1]);
				return result;
			}
			if("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)){
				ShExecRsBean error_result = sess.sendCmd("cat "+remote_file[1]);
				String error_msg = error_result.getRs_msg();
				if(!Assert.isEmpty(error_msg)){
					logger.debug("error msg[{}]",error_msg);
					result.setErr_msg(result.getErr_msg()+error_msg);
					result.setIs_succ(false);
				}
				execute.deleteFile(remote_file[1]);
			}
			
			execute.deleteFile(remote_file[0]);
			
			return result;
		}
		return null;
	}

	/**
	 * Description:交互执行
	 * @throws IOException
	 */
	public void interactRun(String[] cmds, boolean is_read, String remote_relative_dir, boolean sensitive_flag)
		throws IOException {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();

		if (protocol_type == PROTOCOL_TYPE.AGENT) {
			sess.asyncRunStage(cmds, is_read, sensitive_flag);
		} else {
			if (is_read) {
				DtSourceInfo ftp_info = dtSocService.changeDtSource(dt_info);
				CmdsExecuteUtil execute = new CmdsExecuteUtil(IMPL_TYPE.SHELL, ftp_info, remote_relative_dir);
				String[] remote_file = execute.uploadFile(cmds);
				sess.sendCmd("chmod 777 " + remote_file[0]);
//				String cmd = remote_file[0] + " 2 >> " + remote_file[1];
				String cmd = "sh " + remote_file[0] ;
				sess.asyncRunStage(new String[] { cmd }, is_read, sensitive_flag);
			} else {
				if(!Assert.isEmpty(cmds) && !Assert.isEmpty(cmds[0]) && (cmds[0].equalsIgnoreCase("ctrl+c") || cmds[0].equalsIgnoreCase("ctrl c"))){
					sess.disconnect();
				}else{
					sess.asyncRunStage(cmds, false, sensitive_flag);
				}
			}

		}

	}

	public AsyncMsgBean getInteractMsg() {
		return sess.asyncRunMsg();
	}

	private RBean getRBeanFromSoc(DtSourceInfo dt_info) {
		RBean bean = new RBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname().trim());
		String passed_key = DESUtil.docryptAllowReverse(false, null,
				dt_info.getKey_remote_passwd().trim());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
				dt_info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dt_info.getSoc_ip().trim());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name().trim());
		int timeout = (int) dt_info.getBk_timeout();
		if (timeout != 0) {
			bean.setTimeout(timeout);
		} else {
			bean.setTimeout(DEFAULT_TIMEOUT);
		}
		return bean;
	}

	/**
	 * Description: 执行环境参数
	 * @param sess
	 * @param dt
	 * @param pro
	 * @throws IOException
	 */
	private void executeEnvironmentVariables(RSession sess, DtSourceInfo dt, PROTOCOL_TYPE pro) {
		if (pro.equals(PROTOCOL_TYPE.SSH) || pro.equals(PROTOCOL_TYPE.TELNET)) {
			logger.debug("开始执行环境参数");
			String vars = dt.getEnvironment_variables();
			if (!Assert.isEmpty(vars)) {
				String[] var_list = varsSplit(vars);
				for (String var : var_list) {
					logger.debug("当前执行的环境参数[{}]", var);
					sess.sendCmd(var);
				}
			}
		}
	}

	/**
	 * Description: 把传入的参数 按照“；”分隔 再按行读取 返回list
	 * @param vars
	 * @return
	 * @throws IOException
	 */
	private String[] varsSplit(String vars) {
		String vars1 = StringUtil.rpEnter(vars).trim();
		String[] var_array = vars1.split(";");
		return var_array;
	}

}
