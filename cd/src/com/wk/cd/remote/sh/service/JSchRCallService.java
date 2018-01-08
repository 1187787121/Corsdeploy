/**
 * Title: JSchRCallService.java
 * File Description: ssh远程调用服务JSch实现
 * @copyright 2015 
 * @company CORSWORK
 * @author jiangzg
 * @version 1.0
 * @date 11/9/2015
 */

package com.wk.cd.remote.sh.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Logger;
import com.jcraft.jsch.Session;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:jschSSH远程调用服务
 * @author jiangzg
 */
public class JSchRCallService
		extends AbstractRCallService {

	static final Log logger = LogFactory.getLog();

	/**
	 * shell执行命令获取响应信息的等待间隔时间，缺省为2000秒
	 */
	protected static final int SHELL_REPLY_WAIT_TIME = CfgTool.getProperties()
			.getInt("cv.shell_wait_time", 2000);

	static {
		JSch.setLogger(new JschLogger());
	}

	private static class JschLogger
			implements com.jcraft.jsch.Logger {

		@Override
		public boolean isEnabled(int level) {
			return true;
		}

		@Override
		public void log(int level, String message) {
			String head = "[JSCH --> {}]";
			if (logger.isErrorEnabled() && level == Logger.ERROR) {
				logger.error(head, message);
			}
			if (logger.isInfoEnabled() && level == Logger.INFO) {
				logger.info(head, message);
			}
			if (logger.isDebugEnabled() && level == Logger.DEBUG) {
				logger.debug(head, message);
			}
			if (logger.isWarnEnabled() && level == Logger.WARN) {
				logger.warn(head, message);
			}
			if (logger.isFatalEnabled() && level == Logger.FATAL) {
				logger.fatal(head, message);
			}
		}
	}

	protected final JSch jsch = new JSch();

	@Override
	public RConnection connect(RBean bean) {
		Session conn;
		try {
			jsch.getSession("");
			conn = jsch.getSession(bean.getRemote_uname(), bean.getSoc_ip(), bean.getSoc_port());
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("PreferredAuthentications", "password");
			conn.setConfig(config);
			conn.setPassword(bean.getRemote_passwd());
			int timeout = bean.getTimeout() < 90 ? 90 : bean.getTimeout();
			conn.setTimeout(timeout * 1000);// 设置超时时间
			conn.connect();
			return new JSchConn(conn, bean);
		} catch (JSchException e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	public static class JSchConn
			implements RConnection {

		public final Session conn;
		public final RBean bean;

		protected JSchConn(Session conn, RBean bean) {
			this.conn = conn;
			this.bean = bean;
		}

		@Override
		public String exec(String cmd) {
			RSession sess = null;
			try {
				sess = new JSchSession(conn.openChannel("exec"), bean, true);
				return ((JSchSession) sess).execCmd(cmd);
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
				return new JSchSession(sess, bean, false);
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
			} catch (JSchException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
			}
		}

		public void setTimeout(int timeout) {
			try {
				conn.setTimeout(timeout * 1000);
			} catch (JSchException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "setTimeout");
			}
		}

		@Override
		public void disconnect() {
			conn.disconnect();
		}

	}

	public static class JSchSession
			extends RSessionBase {

		protected final Channel sess;
		protected final OutputStream writer;

		public JSchSession(Channel sess, RBean bean, boolean is_exec)
			throws IOException, JSchException {
			super(bean, sess.getInputStream(), sess.getOutputStream());
			this.sess = sess;
			if (is_exec) {
				((ChannelExec) sess).setPtyType("cvshell");
				sess.setInputStream(null);
				writer = null;
			} else {
				((ChannelShell) sess).setPtyType("cvshell");
				PipedInputStream pis = new PipedInputStream();
				PipedOutputStream pos = new PipedOutputStream(pis);
				PipedOutputStream out = new PipedOutputStream();
				PipedInputStream in = new PipedInputStream(out);
				sess.setInputStream(pis);
				sess.setOutputStream(out);
				this.writer = pos;
				this.in = in;
				sess.connect();
				// 如果有中文需要处理编码方式，比较麻烦
				// setEnv设置的环境变量会被用户的profile重新改变，需要重新执行
				// ((ChannelShell)sess).setEnv("LANG", "en_US");
				sendCmd(PS1 + LANG, false);
				getReplyUntilPrompt("CV$");
				sendCmd("stty -echo", false);
				getReplyUntilPrompt("CV$");

				// getReplyUntilPrompt("$");
			}
		}

		public String execCmd(String cmd)
			throws JSchException, IOException {
			((ChannelExec) sess).setCommand("export LANG=en_US && " + cmd);
			sess.setInputStream(null);
			sess.connect();

			String msg = getReplyUntilClose();
			int ret = sess.getExitStatus();
			if (ret != 0) {
				logger.error("error: {} ! \n{}", ret, msg);
				throw new ScriptExecErrorException().addScene("SCRIPT", msg);
			}
			return msg;
		}

		@Override
		public void send(String cmd)
			throws IOException {
			logger.info("send: {}", cmd);
			writer.write((cmd + "\n").getBytes());
		}

		@Override
		public String getReply()
			throws IOException {
			String msg =  getReplyUntilPrompt();
			return msg;
		}

		@Override
		public boolean isClosed() {
			return sess.isClosed();
		}

		// @Override
		// public int getExitStatus() {
		// int ret = super.getExitStatus();
		// return ret == 0 ? sess.getExitStatus() : ret;
		// }

		@Override
		public void disconnect() {
			try {
				closed_flag = AbstractRCallService.SESSION_CLOSE;
				if (writer != null) {
					writer.close();
				}
				if (in != null) {
					in.close();
				}
				sess.disconnect();
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "disconnect");
			}
		}

		/**
		 * Description:
		 * @throws IOException
		 */
		@Override
		public void asyncRunStage(String[] cmds, boolean is_read, boolean sensitive_flag)
			throws IOException {
			StreamReader sr = new StreamReader();
			if (is_read) {
				sr.start();
			}
			StringBuffer sb = new StringBuffer();
			for(String cmd : cmds){
				sb.append(cmd+"\n");
			}
			if(sensitive_flag){
				logger.debug("asyncRun cmds[{}]","******");
			}else{
				logger.debug("asyncRun cmds[{}]",sb);
			}
			writer.write((sb.toString()).getBytes());
		}

		private class StreamReader
				extends Thread {
			InputStreamReader isr;
			BufferedReader br;
			@Override
			public void run() {
				try {
					isr = new InputStreamReader(in);
					br = new BufferedReader(isr);
					
					char ch = (char) br.read();
					while (ch != -1) {
						
						if(closed_flag == AbstractRCallService.SESSION_CLOSE){
							// 表示关闭了不再获取信息
							break;
						}
						interact_msg.add(ch);
						ch = (char) br.read();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();

				}
			}
		}


		/**
		 * Description:
		 * @return
		 */
		@Override
		public AsyncMsgBean asyncRunMsg() {
			StringBuffer msg = new StringBuffer();
			while(!interact_msg.isEmpty()){
				msg.append(interact_msg.poll());
			}
			AsyncMsgBean bean = new AsyncMsgBean();
			String exe_msg = msg.toString();
			exe_msg = StringUtil.usFmWs(exe_msg);
			if(exe_msg != null){
				bean.setMsg(exe_msg);
				if(exe_msg.contains(CURSOR)){
					this.end_flag = true;
				}
				if(closed_flag == AbstractRCallService.SESSION_CLOSE){
					this.end_flag = true;
				}
			}
			bean.setEnd_flag(this.end_flag);
			return bean;
		}

	

	}

	public static void main(String[] args)
		throws Exception {
		RBean bean = new RBean();
		bean.setRemote_uname("sample");
		bean.setRemote_passwd("sample");
		bean.setSoc_ip("10.1.1.220");
		bean.setSoc_port(22);
		bean.setTimeout(-1);
		JSchRCallService js = new JSchRCallService();
		RConnection conn = js.getConnection(bean);
		/*
		 * js.execSh(conn, bean.getTimeout(), "uname -a"); js.execSh(conn,
		 * bean.getTimeout(), "cd log"); js.execSh(conn, bean.getTimeout(),
		 * "pwd"); js.execSh(conn, bean.getTimeout(), "ls -l");
		 * conn.disconnect();
		 */
		conn.disconnect();
		conn = js.getConnection(bean);
		JSchSession sess = (JSchSession) js.openSession(conn);
		ShExecRsBean rs = sess.sendCmd(
				"tail -f /home/sample/corslares/logs/corscl.log");
		String result = rs.getRs_msg();
        System.out.println(result);
        sess.sendCmd("ls -l");
		sess.sendCmd("cd log");
		sess.sendCmd("pwd");
		js.close(conn, sess);
	}

}
