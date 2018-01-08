/**
 * Title: WebLogicModuleSession.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017��5��8��
 */
package com.wk.cd.module1.impl;

import java.io.IOException;
import java.util.Arrays;

import com.wk.Controller;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleSessionBase;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.WebLogicSSHRCallService;
import com.wk.cd.remote.sh.service.WebLogicTelnetRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;

/**
 * Class Description:
 * @author HT
 */
public class WebLogicModuleSession
		extends ModuleSessionBase {
	private static final Log logger = LogFactory.getLog();
	@Inject
	WebLogicTelnetRCallService web_tel_srv;
	@Inject
	WebLogicSSHRCallService web_ssh_srv;

	private RConnection conn;
	private RSession sess;
	

	public WebLogicModuleSession(ModuleSourceInfo module_source_info,int step_count) {
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
				conn = web_ssh_srv.getConnection(bean);
				sess = web_ssh_srv.openSession(conn);
			} else if (protocol_type == PROTOCOL_TYPE.TELNET) {
				RBean bean = getRBeanFromSoc(dt_info);
				logger.debug("[{}]connect begin", dt_info.getSoc_name());
				conn = web_tel_srv.getConnection(bean);
				sess = web_tel_srv.openSession(conn);
			} else if(protocol_type == PROTOCOL_TYPE.AGENT){
				sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),IMPL_TYPE.SHELL,step_count, AgentRSession.SYNCHRO_TYPE, true, "weblogic/");
			} else {
				throw new RuntimeException( protocol_type + " not supported");
			}
			String vars = dt_info.getEnvironment_variables();
			logger.debug("en_parma[{}]", vars);
			if (!com.wk.util.StringUtil.isEmpty(vars)) {
				executeEnvironmentVariables(sess, dt_info, dt_info.getProtocol_type());
			}
			logger.info("{}'s session {} connected", dt_info.getSoc_name(), sess);
		} catch (RuntimeException t) {
			logger.error("��������Դ[{}]�쳣", dt_info.getSoc_name(), t);
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
		return sess.sendCmd(cmd);
	}

	private RBean getRBeanFromSoc(DtSourceInfo dt_info) {
		RBean bean = new RBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd().trim());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dt_info.getSoc_ip());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name());
		bean.setWas_params(getWebLogicParam(passed_key));
		int timeout = (int) dt_info.getBk_timeout();
		if (timeout != 0) {
			bean.setTimeout(timeout);
		} else {
			bean.setTimeout(10000);
		}
		return bean;
	}

	private String getWebLogicParam(String passed_key) {
		ServiceData data = this.module_source_info.getData();
		int port = data.getInt("port");
		String user = data.getString("user");
		String password = data.getString("password");

		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, password);
		
		remote_passwd=remote_passwd.trim();

		String url = data.getString("url");

		StringBuffer target_params = new StringBuffer("-adminurl ");
		target_params.append(url + " ");
		target_params.append("-username " + user + " ");
		target_params.append("-password " + remote_passwd);
		String was_param = target_params.toString();
		logger.debug("wasparam[{}]", was_param);
		return was_param;

	}

	/**
	 * Description: ִ�л�������
	 * @param sess
	 * @param dt
	 * @param pro
	 * @throws IOException
	 */
	private void executeEnvironmentVariables(RSession sess, DtSourceInfo dt, PROTOCOL_TYPE pro) {
		if (pro.equals(PROTOCOL_TYPE.SSH) || pro.equals(PROTOCOL_TYPE.TELNET)) {
			logger.debug("��ʼִ�л�������");
			String vars = dt.getEnvironment_variables();

			if (!Assert.isEmpty(vars)) {
				String[] var_list = varsSplit(vars);
				for (String var : var_list) {
					logger.debug("��ǰִ�еĻ�������[{}]", var);
					sess.sendCmd(var);
				}
			}
		}
	}

	/**
	 * Description: �Ѵ���Ĳ��� ���ա������ָ� �ٰ��ж�ȡ ����list
	 * @param vars
	 * @return
	 * @throws IOException
	 */
	private String[] varsSplit(String vars) {
		logger.debug("��ȡǰ�Ļ�������[{}]", vars);
		String vars1 = StringUtil.rpEnter(vars).trim();
		String[] var_array = vars1.split(";");
		logger.debug("��ȡ��Ļ�������[{}]", Arrays.toString(var_array));
		return var_array;
	}
}