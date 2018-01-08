/**
 * Title: FireFly.java
 * File Description: 
 * @copyright: 2018
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2018年1月2日
 */
package com.wk.cd.module1.impl;

import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.Result;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * 
 * @author chiss
 */
public class FireFly extends MultiStepModule {

	private static final Log logger = LogFactory.getLog();

	private final ModuleSourceInfo dt_info;

	private FireFlyModuleSession sess;

	public FireFly(ModuleSourceInfo source_info, String[] cmds) {
		super(cmds);
		this.dt_info = source_info;
	}

	@Override
	public Result runModule(String remote_relative_dir) {
		long start_time = System.currentTimeMillis();
		if (sess == null) {
			try {
				sess = new FireFlyModuleSession(dt_info, step_count);
				sess.connect();
			} catch (Throwable t) {
				logger.error("连接数据源[{}]异常", dt_info.getDt_source_info().getSoc_name(), t);
				return new Result(t, start_time);
			}

			if (ctx != null) {
				ctx.bindSession(sess);
			}
		}

		try {
			ShExecRsBean reply = sess.sendCmds(cmds, remote_relative_dir);
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getRs_msg();
			if (!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)) {
				status = CMD_STATUS.STOP;
				Result result = new Result(status, msg, start_time);
				result.setError_msg(reply.getErr_msg());
				return result;
			}
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);

			Result result = new Result(status, msg, start_time);
			result.setError_msg(reply.getErr_msg());
			return result;
		} catch (Throwable t) {
			logger.error("执行[{}]异常", t);
			return new Result(t, start_time);
		} finally {
			if (this.sess != null) {
				this.sess.disconnect();
			}
			this.sess = null;
		}
	}

	@Override
	public void interactRun(String remote_relative_dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendInteractCmd(String cmd, boolean sensitive_flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public AsyncMsgBean getInteractMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMPL_TYPE getImplType() {
		return IMPL_TYPE.FIREFLY;
	}

	@Override
	public void sessionClose() {
		if (this.sess != null) {
			this.sess.forceDisconnect();
		}
	}

	@Override
	public Result stepinto(int step) {
		// TODO Auto-generated method stub
		return null;
	}

}
