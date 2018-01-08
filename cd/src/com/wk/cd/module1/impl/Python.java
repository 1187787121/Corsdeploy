package com.wk.cd.module1.impl;

import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.impl.MultiStepModule;
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
public class Python extends MultiStepModule {

	protected static final Log logger = LogFactory.getLog();

	private final ModuleSourceInfo dt_info;

	protected final String source;

	protected PythonSession sess;

	protected boolean connected;
	
	private final IMPL_TYPE impl_type;
	

	public Python(ModuleSourceInfo dt_info, String source, IMPL_TYPE impl_type) {
	
		this(dt_info, source.replaceAll("\r", "").split("\n"),impl_type);
		
	}

	public Python(ModuleSourceInfo dt_info, String[] cmds, IMPL_TYPE impl_type) {
		super(cmds);
		this.dt_info = dt_info;
		this.source = StringUtil.ary2str(cmds, "\n");
		this.impl_type = impl_type;
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public Result stepinto(int step) {
//		long start_time = System.currentTimeMillis();
//		Throwable exc = new Throwable("PYTHON不支持按步执行");
//		return new Result(exc,start_time);
		
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (sess == null) {
			try {
				sess = new PythonSession(dt_info,step_count,impl_type,AgentRSession.SYNCHRO_TYPE, null);
				sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t,start_time);
			}

			if (ctx != null) {
				ctx.bindSession(sess);
			}
			connected = true;
		}

		try {
			String cmd = cmds[step];
			ShExecRsBean reply = sess.sendCmd(cmd);
			
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getIs_succ() ? reply.getRs_msg() : reply
					.getErr_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				status = CMD_STATUS.STOP;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);

			if (step >= cmds.length-1) {
				this.sess.disconnect();
				connected = false;
				this.sess = null;
			}
			return new Result(status, msg,start_time);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			if (this.sess != null) {
				this.sess.disconnect();
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
		if (sess == null) {
			try {
				sess = new PythonSession(dt_info,step_count,this.impl_type,AgentRSession.ASYN_TYPE, remote_relative_dir);
				sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
			}

			if (ctx != null) {
				ctx.bindSession(sess);
			}
			connected = true;
		}

		try {
			sess.interactRun(cmds,true, remote_relative_dir, false);
		} catch (Throwable t) {
			t.printStackTrace();
			logger.error("执行[{}]异常", t);
			if (this.sess != null) {
				this.sess.disconnect();
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
			sess.interactRun(new String[]{cmd}, false, null, sensitive_flag);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			if (this.sess != null) {
				this.sess.disconnect();
				connected = false;
			}
		}
		
	}
	
	@Override
	public AsyncMsgBean getInteractMsg(){
		return sess.getInteractMsg();
	}
	
	/** 
	 * Description:  
	 */
	@Override
	public void sessionClose() {
		if(this.sess != null){
			this.sess.forceDisconnect();
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
		if (sess == null) {
			try {
				sess = new PythonSession(dt_info,step_count,this.impl_type,AgentRSession.SYNCHRO_TYPE, remote_relative_dir);
				sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t,start_time);
			}

			if (ctx != null) {
				ctx.bindSession(sess);
			}
			connected = true;
		}
		
		try {
		
			ShExecRsBean reply = sess.sendCmds(cmds, remote_relative_dir);
			
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getIs_succ() ? reply.getRs_msg() : reply
					.getErr_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				status = CMD_STATUS.STOP;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);
			return new Result(status, msg,start_time);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			return new Result(t,start_time);
		}finally{
			if(this.sess != null){
				this.sess.disconnect();
			}
			connected = false;
			this.sess = null;
		}
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public IMPL_TYPE getImplType() {
		// TODO Auto-generated method stub
		return this.impl_type;
	}

	

	

}
 
