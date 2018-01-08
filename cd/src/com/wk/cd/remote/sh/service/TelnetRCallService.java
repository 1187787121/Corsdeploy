/**
 * Title: JSchRCallService.java
 * File Description: Telnet远程调用服务
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
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.LoginTimeoutException;
import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:Telnet远程调用服务
 * @author jiangzg
 */
public class TelnetRCallService
		extends AbstractRCallService {

	static final Log logger = LogFactory.getLog();
	
	private static final String LOGIN_FLAG = "Last login: ";

	@Override
	public RConnection connect(RBean bean) {
		try {
			TelnetClient client = new TelnetClient("cvtelnet");
			client.connect(bean.getSoc_ip(), bean.getSoc_port());
			return new TelnetConn(client, bean);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	protected  static class TelnetConn
			extends RSessionBase implements RConnection, RSession {

		private final TelnetClient conn;
		private final PrintStream out;

		protected TelnetConn(TelnetClient conn, RBean bean) throws IOException {
			super(bean, conn.getInputStream(), conn.getOutputStream());
			this.conn = conn;
			setTimeout(bean.getTimeout()*1000);//超时时间设置
			this.out = new PrintStream(conn.getOutputStream());
			login(bean.getRemote_uname(), bean.getRemote_passwd());
		}

		@Override
		public RSession openShellSession() {
			return this;
		}

		public void setTimeout(int timeout) {
			try {
				conn.setDefaultTimeout(timeout);
				conn.setConnectTimeout(timeout);
				conn.setSoTimeout(timeout);
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT",
						"setTimeout");
			}
		}

		public String getReply() {
			logger.debug("getReply begin");
			try {
//				return readUntilPrompt();
				return getReplyUntilPrompt();
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "getReply");
			}
		}

		@Override
		public String exec(String cmd) {
			try {
				send(cmd);
				return getReply();
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			}
		}

		@Override
		public boolean isClosed() {
			return !conn.isConnected();
		}

		@Override
		public void disconnect() {
			try {
				closed_flag = AbstractRCallService.SESSION_CLOSE;
				if (conn.isConnected()) {
					conn.disconnect();
				}
			} catch (IOException e) {
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "disconnect");
			}
		}

		/**
		 * 登录
		 * @param user
		 * @param password
		 */
		private void login(String user, String password) throws IOException {
			//readUntil("login: ");
			getReplyUntilPrompt("login:");
		        logger.plog("read Until login:");
			send(user);
//			readUntil("Password: ");
			getReplyUntilPrompt("Password:");
		        logger.plog("read Until password:");
			send(password);
			//checkLogin();

			send(PS1);
			getReplyUntilPrompt("CV$");
		        logger.plog("PS1 send over");
		        
			send(LANG);
//			readUntil("["+user+" ~ ]$ ");
			getReplyUntilPrompt("CV$");
		        logger.plog("LANG read1 over");
		     send("stty -echo");
		     getReplyUntilPrompt("CV$");
			//readUntil(LANG);
			//readUntil(LANG);
			//readUntilPrompt();
		}
	
		@Override
		public void send(String value) throws IOException {
//			System.out.println("send >>> "+value);
			out.println(value);
			out.flush();
		}
		
		/**
		 * Description: 用于telnet校验是否登录成功如果不成功就抛异常
		 * @throws IOException
		 */
		private void checkLogin() throws IOException{
			StringBuffer sb = new StringBuffer();
			final String END_FLG = "login: ";
			String os = "";
			while (true) {
				byte[] tmp = new byte[1024];
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					sb.append(new String(tmp, 0, i));
				}
				if(sb.toString().contains(END_FLG)){
					break;
				}
		    }
			os = sb.toString();
			logger.plog(os);
			if (!os.contains(LOGIN_FLAG)|| Assert.isEmpty(os)) {
				logger.error("login Error");
				throw new LoginTimeoutException();
			}
		}



	public static void main(String[] args)
		throws Exception {
		RBean bean = new RBean();
		bean.setRemote_uname("front");
		bean.setRemote_passwd("front");
		bean.setSoc_ip("10.1.1.228");
		bean.setSoc_port(23);
		bean.setTimeout(100000);
		AbstractRCallService ts = new TelnetRCallService();
		RConnection conn = ts.getConnection(bean);
		RSession sess = ts.openSession(conn);
		String cmd="uname -a";
		ShExecRsBean rs = sess.sendCmd(cmd);
		String r = rs.getRs_msg();
		
		ts.close(conn, sess);
		System.out.println(r);
//		System.out.println("out:\n"+r);
//		ts.execSh(conn, "uname -a");
//		ts.execSh(conn, "echo $TERM");
//		ts.execSh(conn, "cd log");
//		ts.execSh(conn, "pwd");
//		ts.execSh(conn, "ls -l");
//		conn.disconnect();
//
//		conn = ts.getConnection(bean);
//		RSession sess = ts.openSession(conn);
////		sess.sendCmd("cd log");
//		sess.sendCmd("pwd");
//		ts.close(conn, sess);
	}

	/** 
	 * Description: 
	 * @param cmd
	 * @param is_read
	 * @throws IOException 
	 */
	@Override
	public void asyncRunStage(String[] cmds, boolean is_read, boolean sensitive_flag)
		throws IOException {
		if(is_read){
			new StreamReader().start();
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
		out.println(sb.toString());
		out.flush();
		
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
	
	class StreamReader extends Thread {
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



	}

}
