package com.wk.cd.remote.sh.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ScriptExecErrorException;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Class Description:
 * @author HT
 */
public class WebLogicSSHRCallService extends JSchRCallService {
	public RConnection connect(RBean bean) {
		logger.debug("weblogic_ssh connect begin-------");
		try {
			Session conn = this.jsch.getSession(bean.getRemote_uname(),
					bean.getSoc_ip(), bean.getSoc_port());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			conn.setConfig(config);
			conn.setPassword(bean.getRemote_passwd());
			conn.setTimeout(bean.getTimeout());
			conn.connect();
			return new WebLogicSSHConn(conn, bean);
		} catch (JSchException e) {
			logger.error(e.toString(), e);
			System.out.println(ExceptionUtils.getStackTrace(e));
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	private static class WebLogicSSHConn extends JSchRCallService.JSchConn {
		
		/**
		 * 构造函数
		 * @param conn
		 * @param bean
		 */
		protected WebLogicSSHConn(Session conn, RBean bean) {
			super(conn, bean);
		}

		@Override
		public String exec(String cmd) {
			RSession sess = null;
			try {
				logger.debug("weblogic_ssh执行的命令[{}]",
						cmd);
				sess = new WebLogicSSHRCallService.WebLogicSSHSession(conn.openChannel("exec"), bean, true);
				return ((WebLogicSSHSession) sess).execCmd(cmd);
			} catch (JSchException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			} finally {
				if (sess != null) {
					sess.disconnect();
				}
			}
		}

		@Override
		public RSession openShellSession() {
			Channel sess = null;
			try {
				sess = conn.openChannel("shell");
				return new WebLogicSSHSession(sess, bean, false);
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT",
						"connect");
			} catch (JSchException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT",
						"connect");
			}
		}
	}

	private static class WebLogicSSHSession extends
			JSchRCallService.JSchSession {
		
		String weblogic_params;
		private static final String WEBLOGIC_STR = "weblogic.Deployer";

		/**
		 * 构造函数
		 * @param sess
		 * @param bean
		 * @param is_exec
		 * @throws IOException
		 * @throws JSchException
		 */
		public WebLogicSSHSession(Channel sess, RBean bean, boolean is_exec)
				throws IOException, JSchException {
			super(sess, bean, is_exec);
			this.weblogic_params = bean.getWas_params();
		}

		public String execCmd(String cmd) throws JSchException, IOException {
			String was_cmd = getWebLogicCmd(cmd);
			return super.execCmd(was_cmd);
		}
		
		@Override
		public void send(String cmd) throws IOException {
			String was_cmd = getWebLogicCmd(cmd);
			super.send(was_cmd);
		}

		/**
		 * Description: 判断是否要拼接 要则为true
		 * @return
		 */
		private String getWebLogicCmd(String cmd) {
			int length = cmd.indexOf(WEBLOGIC_STR);
			String weblogic_cmd;
			if (length > 0) {
				StringBuffer sb = new StringBuffer(cmd);
				sb.insert(length + WEBLOGIC_STR.length(), " "
						+ weblogic_params);
				weblogic_cmd = sb.toString();
			} else {
				weblogic_cmd = cmd;
			}
			logger.debug("weblogic_ssh 处理后的命令[{}]", weblogic_cmd);
			return weblogic_cmd;
		}
	}
}