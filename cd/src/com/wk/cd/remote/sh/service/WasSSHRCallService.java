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
 * @author "Zhangj"
 */
public class WasSSHRCallService extends JSchRCallService {
	
	@Override
	public RConnection connect(RBean bean) {
		logger.debug("was_ssh connect begin-------");
		try {
			Session conn = this.jsch.getSession(bean.getRemote_uname(),
					bean.getSoc_ip(), bean.getSoc_port());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			conn.setConfig(config);
			conn.setPassword(bean.getRemote_passwd());
			conn.setTimeout(bean.getTimeout());
			conn.connect();
			return new WasSSHConn(conn, bean);
		} catch (JSchException e) {
			logger.error(e.toString(), e);
			System.out.println(ExceptionUtils.getStackTrace(e));
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	private static class WasSSHConn extends JSchRCallService.JSchConn {
		
		/**
		 * 构造函数
		 * @param conn
		 * @param bean
		 */
		protected WasSSHConn(Session conn, RBean bean) {
			super(conn, bean);
		}

		@Override
		public String exec(String cmd) {
			RSession sess = null;
			try {
				logger.debug("was_ssh执行的命令[{}]", cmd);
				sess = new WasSSHRCallService.WasSSHSession(conn.openChannel("exec"), bean, true);
				return ((WasSSHSession) sess).execCmd(cmd);
			} catch (JSchException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			} finally {
				if (sess != null)
					sess.disconnect();
			}
		}

		@Override
		public RSession openShellSession() {
			Channel sess = null;
			try {
				sess = this.conn.openChannel("shell");
				return new WasSSHSession(sess, bean, false);
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

	private static class WasSSHSession extends JSchRCallService.JSchSession {
		String was_params;

		/**
		 * 构造函数
		 * @param sess
		 * @param bean
		 * @param is_exec
		 * @throws IOException
		 * @throws JSchException
		 */
		public WasSSHSession(Channel sess, RBean bean, boolean is_exec)
				throws IOException, JSchException {
			super(sess, bean, is_exec);
			this.was_params = bean.getWas_params();
		}

		public String execCmd(String cmd) throws JSchException, IOException {
			String was_cmd = getWasCmd(cmd);
			return super.execCmd(was_cmd);
		}

		@Override
		public void send(String cmd) throws IOException {
			String was_cmd = getWasCmd(cmd);
			super.send(was_cmd);
		}

		/**
		 * Description: 判断是否要拼接 要则为true 
		 * @return
		 */
		private String getWasCmd(String cmd) {
			String[] list = { "AdminApp.", "AdminConfig.",
					"AdminNodeManagement.", "AdminControl.", "AdminTask." };
			boolean flag = false;
			for(String str : list){
				if(cmd.startsWith(str)){
					flag = true;
				}
			}
			String was_cmd;
			if (flag){
				was_cmd = "wsadmin.sh -lang jython -c \"" + cmd + "\" "
						+ this.was_params;
			} else {
				was_cmd = cmd;
			}
			logger.debug("was_ssh 处理后的命令[{}]", was_cmd);
			return was_cmd;
		}
	}
}