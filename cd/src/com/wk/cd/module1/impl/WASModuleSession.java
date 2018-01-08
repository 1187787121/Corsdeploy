/**
 * Title: WASModuleSession.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017年2月22日
 */
package com.wk.cd.module1.impl;

import java.io.IOException;
import java.util.Arrays;

import com.wk.Controller;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleSessionBase;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.WasSSHRCallService;
import com.wk.cd.remote.sh.service.WasTelnetRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;

/**
 * Class Description:
 * @author zhangj
 */
public class WASModuleSession
		extends ModuleSessionBase {
	private static final Log logger = LogFactory.getLog();
	@Inject
	WasTelnetRCallService was_tel_srv;
	@Inject
	WasSSHRCallService was_ssh_srv;

	private RConnection conn;
	private RSession sess;
	// 当采用agent作为执行数据源的时候 wasparam需要存在这个里面，并且在发送命令的时候拼接上 注意只是在agent的时候这样弄
	private String was_param;
	// 采用agent作为执行数据源的时候的环境参数
	private String env_var;

	public WASModuleSession(ModuleSourceInfo module_source_info, int step_count) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
	}

	/**
	 * Description:
	 */
	@Override
	protected void implConnect() {

		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		try {
			PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
			if (protocol_type == PROTOCOL_TYPE.SSH) {
				RBean bean = getRBeanFromSoc(dt_info);
				logger.debug("[{}]connect begin", dt_info.getSoc_name());
				conn = was_ssh_srv.getConnection(bean);
				sess = was_ssh_srv.openSession(conn);
			} else if (protocol_type == PROTOCOL_TYPE.TELNET) {
				RBean bean = getRBeanFromSoc(dt_info);
				logger.debug("[{}]connect begin", dt_info.getSoc_name());
				conn = was_tel_srv.getConnection(bean);
				sess = was_tel_srv.openSession(conn);
			} else if (protocol_type == PROTOCOL_TYPE.AGENT) {
				this.was_param = getWasParam();
				if (!Assert.isEmpty(this.env_var)) {
					sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),
							IMPL_TYPE.SHELL, step_count + 1, AgentRSession.SYNCHRO_TYPE, true ,"was/");
					sess.sendCmd(this.env_var);
				} else {
					sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),
							IMPL_TYPE.SHELL, step_count, AgentRSession.SYNCHRO_TYPE, true, "was/");
				}

			} else {
				throw new RuntimeException(protocol_type + " not supported");
			}
			String vars = dt_info.getEnvironment_variables();
			logger.debug("en_parma[{}]", vars);
			if (!com.wk.util.StringUtil.isEmpty(vars) && protocol_type != PROTOCOL_TYPE.AGENT) {
				executeEnvironmentVariables(sess, dt_info, dt_info.getProtocol_type());
			}
			logger.info("{}'s session {} connected", dt_info.getSoc_name(), sess);
		} catch (RuntimeException t) {
			t.printStackTrace();
			logger.error("连接数据源[{}]异常", dt_info.getSoc_name(), t);
			throw t;
		}

	}

	/**
	 * Description:
	 */
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
		PROTOCOL_TYPE protocol_type = this.module_source_info.getDt_source_info()
				.getProtocol_type();
		if (protocol_type == PROTOCOL_TYPE.AGENT) {
			return sess.sendCmd(getWasCmd(cmd));
		} else {
			return sess.sendCmd(cmd);
		}

	}

	private RBean getRBeanFromSoc(DtSourceInfo dt_info) {
		RBean bean = new RBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null,
				dt_info.getKey_remote_passwd().trim());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
				dt_info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dt_info.getSoc_ip());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name());
		bean.setWas_params(getWasParam());
		int timeout = (int) dt_info.getBk_timeout();
		if (timeout != 0) {
			bean.setTimeout(timeout);
		} else {
			bean.setTimeout(10000);
		}
		return bean;
	}

	private String getWasParam() {
		ServiceData data = this.module_source_info.getData();
		int port = data.getInt("port");
		String user = data.getString("user");
		String password = data.getString("password");
		String key_remote_passwd = data.getString("key_remote_passwd");
		String passed_key = DESUtil.docryptAllowReverse(false, null, key_remote_passwd.trim());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, password);

		String url = data.getString("url");
		this.env_var = data.getString("env");

		StringBuffer target_params = new StringBuffer("-conntype SOAP -host ");
		target_params.append(url + " ");
		target_params.append("-port " + port + " ");
		target_params.append("-user " + user + " ");
		target_params.append("-password " + remote_passwd + " ");
		String was_param = target_params.toString();
		logger.debug("wasparam[{}]", was_param);
		return was_param;

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
		logger.debug("截取前的环境参数[{}]", vars);
		String vars1 = StringUtil.rpEnter(vars).trim();
		String[] var_array = vars1.split(";");
		logger.debug("截取后的环境参数[{}]", Arrays.toString(var_array));
		return var_array;
	}

	/**
	 * Description: 判断是否要拼接 要则为true
	 * @return
	 */
	private String getWasCmd(String cmd) {
		String[] list = { "AdminApp.", "AdminConfig.", "AdminNodeManagement.", "AdminControl.",
				"AdminTask." };
		boolean flag = false;
		for (String str : list) {
			if (cmd.startsWith(str)) {
				flag = true;
			}
		}
		String was_cmd;
		if (flag) {
			was_cmd = "wsadmin.sh -lang jython -c \"" + cmd + "\" " + this.was_param;
		} else {
			was_cmd = cmd;
		}
		logger.debug("was_ssh 处理后的命令[{}]", was_cmd);
		return was_cmd;

	}

}
