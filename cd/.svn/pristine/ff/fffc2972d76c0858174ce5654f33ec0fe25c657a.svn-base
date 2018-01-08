/**
 * Title: AbstractCallService.java
 * File Description: Shell远程调用服务抽象类
 * @copyright 2015 
 * @company CORSWORK
 * @author jiangzg
 * @version 1.0
 * @date 11/9/2015
 */

package com.wk.cd.remote.sh.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ReplyTimeOutException;
import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:远程Shell调用服务抽象类
 * @author jiangzg
 */
public abstract class AbstractRCallService {

	protected static final Log logger = LogFactory.getLog();
	// protected static final String PS1 = "PS1=\"[\\u \\w ]\\$ \"";
	protected static final String PS1 = "PS1=\"CV\\$ \";";
	protected static final String LANG = "export LANG=en_US;";
	private static final String REPLY_TIMEOUT_KEY = "cv.remote.reply_timeout";
	private static final int GET_PROMPT_SLEEP_TIME = 10;
	// 当前执行再获取信息的过程当中发现 如果session关闭了 则在返回结果信息当中 加上这段表示 注意加两次 用于判断当前的执行是被关闭了
	public static final String CLOSE_MSG = "session closed";

	public static final int SESSION_CLOSE = 1;
	public static final int SESSION_NOT_CLOSE = 2;

	/**
	 * 执行SHELL脚本
	 * @author jiangzg (2015-11-6)
	 * @param bean
	 * @param scr_path 脚本
	 */
	public String executeScript(RBean bean, String scr_path) {
		RConnection session = null;
		try {
			session = getConnection(bean);
			return execSh(session, scr_path).getRs_msg();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", scr_path);
		} finally {
			if (session != null) {
				session.disconnect();
			}
			logger.info("connect end.");
		}
	}

	/**
	 * 在连接下执行脚本
	 * @param conn
	 * @param scr_path
	 * @return ShExecRsBean
	 */
	public ShExecRsBean execSh(RConnection conn, String scr_path) {
		RSession sess = null;
		ShExecRsBean output = new ShExecRsBean();
		try {
			logger.info(">>> [{}]", scr_path);
			String msg = conn.exec(scr_path);
			logger.info("<<< \n{}", msg);
		} finally {
			if (sess != null) {
				sess.disconnect();
			}
		}
		return output;
	}

	/**
	 * 打开SSH连接
	 * @param bean
	 * @return Connection
	 */
	public RConnection getConnection(RBean bean) {
		Assert.assertNotEmpty(bean.getSoc_ip(), "SOC_IP");
		Assert.assertNotEmpty(bean.getSoc_port(), "SOC_PORT");
		Assert.assertNotEmpty(bean.getRemote_uname(), "REMOTE_UNAME");
		Assert.assertNotEmpty(bean.getRemote_passwd(), "REMOTE_PASSWD");
		logger.info("begin connect");
		RConnection conn = connect(bean);
		logger.info("connect successfully!");
		return conn;
	}

	abstract protected RConnection connect(RBean bean);

	/**
	 * 打开Session
	 * @param conn
	 * @return Session
	 */
	public RSession openSession(RConnection conn) {
		logger.debug("begin openSession");
		RSession sess = conn.openShellSession();
		logger.debug("openSession successfully!");
		return sess;
	}

	/**
	 * 关闭Session
	 */
	public void close(RConnection conn, RSession sess) {
		try {
			if (sess != null) {
				sess.disconnect();
			}

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected static abstract class RSessionBase
			implements RSession {

		protected final RBean bean;
		protected String prompt;
		protected InputStream in;
		protected OutputStream out;
		// 交互式执行的过程当中 返回的信息
		ConcurrentLinkedQueue<Character> interact_msg = new ConcurrentLinkedQueue<Character>();
		// 交互式执行是否结束的标志
		boolean end_flag = false;
		// 判断连接是否被关闭的标志， 关闭连接的时候设置为true
		// 现在用来判断异步执行连接是否被中途关闭
		int closed_flag = 0;

		String CURSOR = "CV$";

		protected RSessionBase(RBean bean, InputStream in, OutputStream out) {
			this.prompt = "CV$";
			this.bean = bean;
			this.in = in;
			this.out = out;
		}

		public ShExecRsBean sendCmd(String cmd) {
			return sendCmd(cmd, true);
		}

		public ShExecRsBean sendCmd(String cmd, boolean isReply) {
			ShExecRsBean output = new ShExecRsBean();
			try {
				logger.info(">>> [{}]", cmd);
				// System.out.println("sendcmd >>>> " +cmd);
				send(cmd);
				if (isReply) {
					String msg = "";
					int ret = 0;
					output.setIs_succ(true);
					try {
						msg = getReply();
						msg = processMsg(msg, cmd);
						if (Assert.isEmpty(msg) || !msg.endsWith(CLOSE_MSG)) {
							ret = getExitStatus();
						} else {
							ret = 0;
						}

					} catch (Exception e) {
						// 获取返回出错时忽略增加容错性
						logger.warn("getReply Error", e);
						msg = "warning!!! [ reply error ]";
						ret = 0;
					}
					output.setRs_msg(msg);

					if (ret != 0) {
						output.setIs_succ(false);
						output.setRs_msg(msg);
						logger.error("<<< sendCmd_ERROR: {} ! \n{}", ret, msg);
						throw new ScriptExecErrorException().addScene("SCRIPT", msg);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.toString(), e);
				throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
			}
			return output;
		}

		protected String getReplyUntilClose()
			throws IOException {
			StringBuffer sb = new StringBuffer();
			while (true) {
				sb.append(getAvailableReply());
				if (isClosed()) {
					break;
				}
			}
			return sb.toString();
		}

		protected String getAvailableReply()
			throws IOException {
			StringBuffer sb = new StringBuffer();

			byte[] tmp = new byte[1024];
			int c = 1;
			if (in.available() == 0) {
				sleep(1000);
			}
			while (in.available() > 0) {
				logger.plog("<<<read reply count=" + c++);
				int i = in.read(tmp, 0, 1024);
				sb.append(new String(tmp, 0, i));
			}

			return sb.toString();
		}

		private void sleep(int time) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				logger.error("<<< sleep_ERROR: ", e);
			}

		}

		public String getReplyUntilPrompt(String pattern)
			throws IOException {
			String msg = getReplyUntilPrompt(new String[] { pattern });
			return msg;
		}

		private boolean checkNotEnoughLength(String msg, String[] patterns) {
			for (String p : patterns) {
				if (msg.length() >= p.length()) {
					return false;
				}
			}
			return true;
		}

		private int getMaxPatternLength(String[] patterns) {
			int max_length = 0;
			for (String pattern : patterns) {
				if (pattern.trim().length() > max_length) {
					max_length = pattern.trim().length();
				}
			}
			return max_length;
		}

		private String complateLastPrompt(String full_prompt, String last_prompt,
				int max_pattern_length) {
			full_prompt = full_prompt.trim();
			String end_prompt = last_prompt;
			if (last_prompt.length() < max_pattern_length) {
				end_prompt = full_prompt;
			}
			return end_prompt;
		}

		public String getReplyUntilPrompt(String[] patterns)
			throws IOException {
			for (String p : patterns) {
				Assert.assertNotEmpty(p, "pattern");
			}

			StringBuffer sb = new StringBuffer();
			int timeout = bean.getTimeout();
			int cfg_timeout = CfgTool.getProperties().getInt(REPLY_TIMEOUT_KEY);
			if (cfg_timeout > 0) {
				timeout = cfg_timeout;
			}
			long st_tm = System.currentTimeMillis();
			int maxPatternLength = getMaxPatternLength(patterns);
			int c = 1;
			byte[] tmp;
			while (true) {
				if ((int) ((System.currentTimeMillis() - st_tm) / 1000) > timeout) {
					logger.warn("reply time out=[{}]", timeout);
					throw new ReplyTimeOutException();
				}
				tmp = new byte[1024];
				int i = 0;
				if (closed_flag != SESSION_CLOSE) {
					logger.debug("not closed contiue read");
					i = in.read(tmp, 0, 1024);
				} else {
					logger.debug("was closed end read");
					// 获取信息关闭 在信息结尾加上关闭的标志
					// 这样保留了之前的所有信息 同时也可以判断关闭
					// 这里之所以要加两边colse_msg 为有个方法 skip 就在这个类里面 会把最有一样结果处理掉 至于为什么这么 是在没看明白 所以没办法就加了两边
					sb.append(CLOSE_MSG+"\n"+CLOSE_MSG);
					return sb.toString();
				}

				// 未读到任何字节 且无可读字节
				if (i <= 0 && in.available() == 0) {
					sleep(GET_PROMPT_SLEEP_TIME);
					continue;
				}
				String data = new String(tmp, 0, i);
				sb.append(data);
				logger.plog("<<<read reply count=" + c++);
				String os = complateLastPrompt(sb.toString(), data.trim(), maxPatternLength);
				// 在正常时间范围里面的处理
				if (checkNotEnoughLength(os, patterns)) {
					continue;
				}
				for (String pattern : patterns) {
					pattern = pattern.trim();
					char lastChar = pattern.charAt(pattern.length() - 1);
					char ch = os.charAt(os.length() - 1);
					if (ch == lastChar) {
						if (isPattern(os, pattern)) {
							String msg = sb.toString().trim();
							logger.plog("pattern ok \n{}", msg);
							if (!Assert.isEmpty(msg)) {
								msg = StringUtil.usFmWs(msg);
								// System.out.println("------------------------");
								// System.out.println(msg);
								// System.out.println("------------------------");
								return msg;
							}
						}
					}
				}
			}
		}

		protected boolean isPattern(String src, String pattern) {
			if (Assert.isEmpty(src))
				return false;
			boolean ispattern = true;
			byte[] buff = src.getBytes();
			byte[] pbuff = pattern.getBytes();
			int len = pattern.length();
			int offset = buff.length - pbuff.length;
			byte[] sb_buff = new byte[pbuff.length];
			for (int i = 0; i < len; i++) {
				sb_buff[i] = buff[offset + i];
				if (sb_buff[i] != pbuff[i])
					ispattern = false;
			}
			logger.plog(StringUtil.byteArr2HexStr(sb_buff));
			return ispattern;
		}

		protected String getReplyUntilPrompt()
			throws IOException {
			String pattern = prompt + "";
			String m = getReplyUntilPrompt(pattern);
			String msg = skip(m);
			return msg;
		}

		protected String readUntilPrompt()
			throws IOException {
			String pattern = prompt + " ";
			return skip(readUntil(pattern));
		}

		protected String readUntil(String pattern)
			throws IOException {
			return getReplyUntilPrompt(pattern);
		}

		abstract String getReply()
			throws IOException;

		abstract boolean isClosed();

		static final String ECHO_EXIT_CMD = "echo $?";

		public int getExitStatus()
			throws IOException {
			String ret = "";
			send(ECHO_EXIT_CMD);
			ret = getReplyUntilPrompt().trim();
			logger.info("exit cmd reply : {}", ret);
			ret = getStatusFromReply(ret);
			logger.info("exit status : {}", ret);
			return !StringUtil.isAllDigit(ret) || ret.startsWith("0") || ret.equals("") ? 0 : 1;

		}

		private static String getStatusFromReply(String s) {
			int idx = s.lastIndexOf(ECHO_EXIT_CMD);
			if (idx >= 0) {
				s = s.substring(idx + ECHO_EXIT_CMD.length());
			}
			s = s.replaceAll("\n", "");
			s = s.replaceAll("\r", "");
			return s.trim();
		}

		/**
		 * 跳过首位行
		 * @param rs
		 * @return String
		 */
		private String skip(String rs) {
			String[] ar = rs.split("\n");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ar.length - 1; i++) {
				sb.append(ar[i]);
				// 最后一行不加换行
				if (i != ar.length - 2) {
					sb.append("\n");
				}
			}
			String msg = sb.toString();
			logger.debug("跳过处理后：[{}]", trunkMsg(msg));

			return msg;
		}

		/**
		 * Description: 处理执行返回结果信息 处理掉空行和返回命令
		 * @param msg
		 * @param cmd
		 * @return
		 * @throws IOException
		 */
		private String processMsg(String msg, String cmd)
			throws IOException {
			logger.debug("processMsg cmd[{}]", cmd);
			logger.debug("processMsg begin[{}]", trunkMsg(msg));
			StringReader sr = new StringReader(msg);
			BufferedReader br = new BufferedReader(sr);
			StringBuffer sb = new StringBuffer();

			while (true) {
				String str = br.readLine();
				if (str != null) {
					if (Assert.isEmpty(str.trim()) || str.trim().equals(cmd)) {
						continue;
					} else {
						sb.append(str + "\n");
					}
				} else {
					break;
				}
			}
			// 将最后的\n删除
			String msg_return = sb.toString();
			if (msg_return.contains("\n")) {
				msg_return = msg_return.substring(0, msg_return.length() - 1);
			}
			return msg_return;
		}
	}

	private static String trunkMsg(String msg) {
		int total = msg.length();
		if (total > 500) {
			String left = msg.substring(0, 250);
			String right = msg.substring(total - 250);
			return left + "...[skip" + total + " chars]..." + right;
		}
		return msg;
	}

}
