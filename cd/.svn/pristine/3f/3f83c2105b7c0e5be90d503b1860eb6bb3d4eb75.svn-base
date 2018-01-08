/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.remote.sc.service;

import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.remote.sc.SCRSession;
import com.wk.cd.remote.sc.bean.SCBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.StringUtil;
import java.io.IOException;

/**
 * Created by 姜志刚 on 2016/11/25.
 */
public class SVNSession implements SCRSession {
	private static final Log logger = LogFactory.getLog();
	private final RSession remote_sess;
	private final SCBean bean;

	protected SVNSession(RSession remote_sess, SCBean bean) {
		this.remote_sess = remote_sess;
		this.bean = bean;
		execCmd("cd " + bean.getLocal_root());
	}

	public String co(String remote_path) {
		return co(remote_path, "");
	}

	public String co(String remote_path, String local_path) {
		String path = remote_path;
		logger.debug("svn remote_path [{}]local_path[{}]", path, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("svn co ")
				.append(join_with(this.bean.getSc_url(), remote_path, '/'))
				.append(" ")
				.append(join_with(this.bean.getLocal_root(), local_path, '/'))
				.append(" --username ").append(this.bean.getSc_user());
		System.out.println(sb.toString());
		logger.debug("svn checkout cmd[{}]", sb.toString());
		return execCmdWithAuth(sb.toString());
	}

	public String co_empty(String remote_path, String local_path) {
		String path = remote_path;
		logger.debug("svn remote_path [{}]local_path[{}]", path, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("svn co --depth=empty ")
				.append(join_with(this.bean.getSc_url(), remote_path, '/'))
				.append(" ")
				.append(join_with(this.bean.getLocal_root(), local_path, '/'))
				.append(" --username ").append(this.bean.getSc_user());
		System.out.println(sb.toString());
		logger.debug("svn checkout cmd[{}]", sb.toString());
		return execCmdWithAuth(sb.toString());
	}

	public String export(String remote_path, String local_path) {
		String path = remote_path;
		logger.debug("svn remote_path [{}]local_path[{}]", path, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("svn export ")
				.append(join_with(this.bean.getSc_url(), remote_path, '/'))
				.append(" ")
				.append(join_with(this.bean.getLocal_root(), local_path, '/'))
				.append(" --force")
				.append(" --username ").append(this.bean.getSc_user());
		System.out.println(sb.toString());	
		logger.debug("svn export cmd[{}]", sb.toString());
		return execCmdWithAuth(sb.toString());
	}

	public String ls(String remote_path) {
		String path = remote_path;
		logger.debug("svn remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("svn list ")
				.append(join_with(this.bean.getSc_url(), remote_path, '/'))
				.append(" --username ").append(this.bean.getSc_user());
		logger.debug("svn list cmd[{}]", sb.toString());
		return execCmdWithAuth(sb.toString());
	}

	public String tag(String chunk, String tag, String desc) {
		StringBuffer sb = new StringBuffer();
		sb.append("svn copy ")
				.append(join_with(this.bean.getSc_url(), chunk, '/'))
				.append(" ").append(this.bean.getSc_url()).append(tag)
				.append(" -m \"").append(desc).append("\"");
		return execCmdWithAuth(sb.toString());
	}

	public String merge(String branch, String desc, boolean commit) {
		StringBuffer sb = new StringBuffer();
		sb.append("svn merge ").append(
				join_with(this.bean.getSc_url(), branch, '/'));
		String msg = execCmd(sb.toString());
		if (commit) {
			sb = new StringBuffer();
			sb.append("svn ci -m \"").append(desc).append("\"");
			msg = execCmd(sb.toString());
		}
		return msg;
	}

	public String update() {
		return execCmdWithAuth("svn update --username "
				+ this.bean.getSc_user());
	}

	public String add(String file) {
		return add(new String[] { file });
	}

	public String add(String[] files) {
		return addOrRm("add", files);
	}

	public String rm(String file) {
		return rm(new String[] { file });
	}

	public String rm(String[] files) {
		return addOrRm("rm", files);
	}

	private String addOrRm(String op, String[] files) {
		StringBuffer sb = new StringBuffer();
		String msg = "";
		for (String f : files) {
			msg = execCmd("svn " + op + " " + f);
			sb.append(msg);
		}
		return sb.toString();
	}

	public String ci(String desc) {
		if (desc.charAt(0) == '"') {
			return execCmd("svn ci -m " + desc);
		}
		return execCmd("svn ci -m \"" + desc + "\"");
	}

	private String join_with(String part1, String part2, char c) {
		if (StringUtil.isEmpty(part2)) {
			return part1;
		}
		if (StringUtil.isEmpty(part1)) {
			return part2;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(part1);
		if (sb.charAt(sb.length() - 1) == c) {
			sb.deleteCharAt(sb.length() - 1);
		}
		if (part2.charAt(0) != c) {
			sb.append('/');
		}
		sb.append(part2);
		return sb.toString();
	}

	public String execCmd(String cmd) {
		ShExecRsBean reply = this.remote_sess.sendCmd(cmd);
		if (!(reply.getIs_succ())) {
			throw new ScriptExecErrorException().addScene("SCRIPT", cmd + ": "
					+ reply.getErr_msg());
		}
		return reply.getRs_msg();
	}

	private String execCmdWithAuth(String cmd) {
		try {
			this.remote_sess.send(cmd);
			String msg = this.remote_sess.getReplyUntilPrompt(new String[] {
					":", "$" });
			if (msg.trim().charAt(msg.length() - 1) == ':') {
				this.remote_sess.send(this.bean.getSc_passwd());
				this.remote_sess.getReplyUntilPrompt("?");
				this.remote_sess.send("yes\n");
				msg = this.remote_sess.getReplyUntilPrompt(new String[] { ":",
						"$" });
				if (msg.trim().charAt(msg.length() - 1) == ':') {
					this.remote_sess.send("yes\n");
					msg = this.remote_sess.getReplyUntilPrompt("$");
				}
			}
			logger.debug("命令执行结果信息：[{}]", msg);
			if (this.remote_sess.getExitStatus() != 0) {
				throw new ScriptExecErrorException().addScene("SCRIPT", cmd
						+ ": " + msg);
			}
			return msg;
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", "exec");
		}
	}

	public void close() {
		this.remote_sess.disconnect();
	}
}