package com.wk.cd.module1.impl;

import java.util.List;

import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.impl.MultiStepModule;
import com.wk.cd.module1.impl.ShellSession;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 远程Shell Module实现
 */
public class Shell extends MultiStepModule {

	protected static final Log logger = LogFactory.getLog();

	 private final ModuleSourceInfo dt_info;

	protected final String source;

	protected ShellSession shell_sess;

	protected boolean connected;
	
	public Shell(ModuleSourceInfo dt_info, String source) {
		this(dt_info, source.replaceAll("\r", "").split("\n"));
	}

	public Shell(ModuleSourceInfo dt_info, String[] cmds) {
		super(cmds);
		this.dt_info = dt_info;
		this.source = StringUtil.ary2str(cmds, "\n");
	}

	public boolean isConnected() {
		return connected;
	}

	//此方法不用
	@Override
	public Result stepinto(int step) {
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (shell_sess == null) {
			try {
				shell_sess = new ShellSession(dt_info,step_count,AgentRSession.SYNCHRO_TYPE, null);
				shell_sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t,start_time);
			}

			if (ctx != null) {
				ctx.bindSession(shell_sess);
			}
			connected = true;
		}

		try {
			String cmd = cmds[step];
			ShExecRsBean reply = shell_sess.sendCmd(cmd);
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getIs_succ() ? reply.getRs_msg() : reply
					.getErr_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				status = CMD_STATUS.STOP;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);

			if (step >= cmds.length-1) {
				this.shell_sess.disconnect();
				connected = false;
				this.shell_sess = null;
			}
			return new Result(status, msg, start_time);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			if (this.shell_sess != null) {
				this.shell_sess.disconnect();
				connected = false;
			}
			return new Result(t,start_time);
		}
	}
	
	/**
	 * Description:交互式按阶段调用 第一次发送命令调用的方法
	 * 这个方法首先建立session连接 然后把一个阶段的命令全部发过去
	 */
	@Override
	public void interactRun(String remote_relative_dir){
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (shell_sess == null) {
			try {
				shell_sess = new ShellSession(dt_info,step_count,AgentRSession.ASYN_TYPE, remote_relative_dir);
				shell_sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
			}

			if (ctx != null) {
				ctx.bindSession(shell_sess);
			}
			connected = true;
		}
		
		try {
			shell_sess.interactRun(cmds,true,remote_relative_dir, false);
		} catch (Throwable t) {
			t.printStackTrace();
			logger.error("执行[{}]异常", t);
			if (this.shell_sess != null) {
				this.shell_sess.disconnect();
				connected = false;
			}
		}
	}
	
	/** 
	 * Description: 
	 * @param cmd 
	 */
	@Override
	public void sendInteractCmd(String cmd, boolean sensitive_flag) {
		try {
			shell_sess.interactRun(new String[]{cmd}, false, null, sensitive_flag);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			if (this.shell_sess != null) {
				this.shell_sess.disconnect();
				connected = false;
			}
		}
		
	}
	
	@Override
	public AsyncMsgBean getInteractMsg(){
		return shell_sess.getInteractMsg();
	}
	
	/** 
	 * Description:  
	 */
	@Override
	public void sessionClose() {
		if(this.shell_sess != null){
			this.shell_sess.forceDisconnect();
		}
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public Result runModule(String remote_relative_dir) {
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (shell_sess == null) {
			try {
				shell_sess = new ShellSession(dt_info,step_count,AgentRSession.SYNCHRO_TYPE, remote_relative_dir);
				shell_sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t,start_time);
			}

			if (ctx != null) {
				ctx.bindSession(shell_sess);
			}
			connected = true;
		}
		try {
			
			ShExecRsBean reply = shell_sess.sendCmds(cmds, remote_relative_dir);
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getRs_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				status = CMD_STATUS.STOP;
				Result result = new Result(status, msg,start_time);
				result.setError_msg(reply.getErr_msg());
				return result;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);
			
			Result result = new Result(status, msg,start_time);
			result.setError_msg(reply.getErr_msg());
			return result;
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			return new Result(t,start_time);
		}finally{
			if(this.shell_sess != null){
				this.shell_sess.disconnect();
			}
			connected = false;
			this.shell_sess = null;
		}
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public IMPL_TYPE getImplType() {
		// TODO Auto-generated method stub
		return IMPL_TYPE.SHELL;
	}
}
 
