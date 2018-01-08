/**
 * Title: WebLogic.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年5月8日
 */
package com.wk.cd.module1.impl;

import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.impl.MultiStepModule;
import com.wk.cd.module1.impl.WebLogicModuleSession;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author HT
 */
public class WebLogic
		extends MultiStepModule {

	private static final Log logger = LogFactory.getLog();

	private final ModuleSourceInfo dt_info;

	protected final String source;

	private WebLogicModuleSession sess;

	protected boolean connected;
	
	private final int step_count;

	/**
	 * 构造函数
	 * @param cmds
	 */
	public WebLogic(ModuleSourceInfo source_info, String[] cmds) {
		super(cmds);
		this.dt_info = source_info;
		this.source = StringUtil.ary2str(cmds, "\n");
		this.step_count = cmds.length;
	}

	public boolean isConnected() {
		return connected;
	}
	
	/**
	 * Description:
	 * @param step
	 * @return
	 */
	@Override
	public Result stepinto(int step) {
		long start_time = System.currentTimeMillis();
		DtSourceInfo dsi = dt_info.getDt_source_info();
		if (sess == null) {
			try {
				sess = new WebLogicModuleSession(dt_info,step_count);
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
		Result result = null;
		for(int i = 0;i<this.cmds.length;i++){
			result = stepinto(i);
		}
		return result;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public IMPL_TYPE getImplType() {
		return IMPL_TYPE.WEBLOGIC;
	}

}
