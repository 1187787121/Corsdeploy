package com.wk.cd.module1.impl;

import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 远程Shell Module实现
 */
public class SQL
		extends MultiStepModule {

	protected static final Log logger = LogFactory.getLog();

	private final ModuleSourceInfo dt_info;

	protected final String source;

	protected SQLSession sess;

	protected boolean connected;
	
	public SQL(ModuleSourceInfo dt_info, String source) {
		this(dt_info, source.replaceAll("\r", "").split("\n"));
	}

	public SQL(ModuleSourceInfo dt_info, String[] cmds) {
		super(cmds);
		this.dt_info = dt_info;
		this.source = StringUtil.ary2str(cmds, "\n");
	}

	public boolean isConnected() {
		return connected;
	}

	@Override
	public Result stepinto(int step) {
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (sess == null) {
			try {
				sess = new SQLSession(dt_info,step_count);
				sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
				return new Result(t, start_time);
			}

			if (ctx != null) {
				ctx.bindSession(sess);
			}
			connected = true;
		}

		try {
			String cmd = cmds[step].trim();
			String msg = sess.execute(cmd);
			logger.debug("命令执行结果信息：{}", msg);
			if (step >= cmds.length - 1) {
				this.sess.disconnect();
				connected = false;
				this.sess = null;
			}
			return new Result(CMD_STATUS.SUCCEED, msg, start_time);
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			if (this.sess != null) {
				this.sess.disconnect();
				connected = false;
			}
			return new Result(t, start_time);
		}
	}

	/** 
	 * Description:  
	 */
	@Override
	public void sessionClose() {
		this.sess.disconnect();
		
	}

	/** 
	 * Description:  
	 */
	@Override
	public void interactRun(String remote_relative_dir) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: 
	 * @param cmd 
	 */
	@Override
	public void sendInteractCmd(String cmd, boolean sensitive_flag) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public AsyncMsgBean getInteractMsg() {
		// TODO Auto-generated method stub
		return null;
	}


	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public Result runModule(String remote_relative_dir) {
		long start = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		Result result_step = new Result();
		for(int i = 0;i<this.cmds.length;i++){
			 result_step = stepinto(i);
			sb.append(result_step.getMsg()+"\r\n");
			if(result_step.getStatus() != CMD_STATUS.SUCCEED){
				break;
			}
		}
		Result result = new Result(result_step.getStatus(),sb.toString(),start);
		return result;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public IMPL_TYPE getImplType() {
		return IMPL_TYPE.SQL;
	}
}
