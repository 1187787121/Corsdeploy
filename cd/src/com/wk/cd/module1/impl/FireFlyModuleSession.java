/**
 * Title: FireFlyModuleSession.java
 * File Description: 
 * @copyright: 2018
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2018年1月2日
 */
package com.wk.cd.module1.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.service.CmdsExecuteUtil;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.remote.sc.bean.SCBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.remote.sh.service.JSchRCallService;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.TelnetRCallService;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.StringUtil;

/**
 * Class Description: 
 * @author chiss
 */
public class FireFlyModuleSession extends ModuleSessionBase{

	private static final Log logger = LogFactory.getLog();

	private static final int DEFAULT_TIMEOUT = 3600;
	
	@Inject
	private JSchRCallService ssh_svc;

	@Inject
	private TelnetRCallService telnet_svc;

	@Inject
	private DtSocService dtSocService;
	
	private RSession remote_sess;
	
	private SCBean scBean;
	/**
	 * 构造函数
	 * @param step_count
	 */
	public FireFlyModuleSession(ModuleSourceInfo module_source_info,int step_count) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
	}

	@Override
	protected void implConnect() {
		PROTOCOL_TYPE protocol_type = module_source_info.getProtocol_type();
		logger.debug("协议：[{}]",protocol_type.getCname());
		if(protocol_type == PROTOCOL_TYPE.FIREFLY) {
			scBean = getSCBeanFromSoc(module_source_info);
			protocol_type = scBean.getProtocol_type();
			if(PROTOCOL_TYPE.SSH == protocol_type) {
				RConnection conn = ssh_svc.getConnection(scBean);
				remote_sess = conn.openShellSession();
			} else if(PROTOCOL_TYPE.TELNET == protocol_type) {
				RConnection conn = telnet_svc.getConnection(scBean);
				remote_sess = conn.openShellSession();
			}else if (PROTOCOL_TYPE.AGENT == protocol_type) {
				remote_sess = new AgentRSession(scBean.getSoc_ip(),
						scBean.getSoc_port(), IMPL_TYPE.SHELL, step_count,
						AgentRSession.SYNCHRO_TYPE, true, "common/");
			} else {
				throw new RuntimeException("ProtoType[" + protocol_type + "] not firefly protocol type");
			}
		} else {
			throw new RuntimeException(protocol_type + " not supported");
		}
		
		
	}

	@Override
	protected void implDisconnect() {
		if (remote_sess != null) {
			remote_sess.disconnect();
		}
	}
	
	private SCBean getSCBeanFromSoc(ModuleSourceInfo module_source_info) {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		SCBean bean = new SCBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname());
		if (!Assert.isEmpty(dt_info.getKey_remote_passwd())
				&& !Assert.isEmpty(dt_info.getKey_remote_passwd())) {
			String passed_key = DESUtil.docryptAllowReverse(false, null,
					dt_info.getKey_remote_passwd().trim());
			String remote_passwd = DESUtil.docryptAllowReverse(false,
					passed_key, dt_info.getRemote_passwd());
			bean.setRemote_passwd(remote_passwd.trim());
		}

		bean.setSoc_ip(dt_info.getSoc_ip());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name());
		ServiceData data = module_source_info.getData();
		String[] ps = getData(data);
		bean.setSc_url(ps[0]);
		bean.setSc_user(ps[1]);
		bean.setSc_passwd(ps[2]);
		bean.setLocal_root(dt_info.getUser_root_path());
		int timeout = (int) dt_info.getBk_timeout();
		bean.setTimeout(timeout != 0 ? timeout : DEFAULT_TIMEOUT);

		return bean;
	}
	
	/**
	 * Description: 得到SVN参数组成的命令字符串
	 * @param soc_params扩展参数
	 * @param passed_key加密密码的密钥
	 * @return
	 */
	public static String[] getData(ServiceData data) {
		String url = data.getString("url");
		String user = data.getString("user");
		String passed_key = data.getString("passed_key");
		passed_key = DESUtil.docryptAllowReverse(false, null, passed_key.trim());
		String password = DESUtil.docryptAllowReverse(false, passed_key,data.getString("password"));
		String[] str = new String[] { url, user, password };
		return str;
	}
	
	/**
	 * Description: 通过文件来执行命令 获取执行信息
	 * @param old_cmds
	 * @param remote_relative_dir
	 * @return
	 */
	public ShExecRsBean sendCmds(String[] old_cmds, String remote_relative_dir) {
		String[] cmds = dealCmds(old_cmds);
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
		if (protocol_type == PROTOCOL_TYPE.AGENT) {
			if (remote_sess instanceof AgentRSession) {
				AgentRSession a = (AgentRSession) remote_sess;
				return a.sendCmds(cmds);
			}
		} else {
			DtSourceInfo ftp_info = dtSocService.changeDtSource(dt_info);
			CmdsExecuteUtil execute = new CmdsExecuteUtil(IMPL_TYPE.SHELL, ftp_info, remote_relative_dir);
			String[] remote_file = execute.uploadFile(cmds);
			StringBuilder sb = new StringBuilder();
			sb.append("chmod 777 ").append(remote_file[0]).append(';');
			sb.append("cd ").append(dt_info.getUser_root_path()).append(';');
			if ("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)) {
				sb.append(remote_file[0]).append(" 2>>").append(remote_file[1]).append(';');
			} else {
				sb.append(remote_file[0]).append(';');
			}
			logger.info("before cmd ts_soc_name = {}", dt_info.getSoc_name());
			ShExecRsBean result = remote_sess.sendCmd(sb.toString());
			logger.debug("send cmd is success[{}]", result.getIs_succ());
			logger.debug("send cmd msg [{}]", result.getRs_msg());
			logger.debug("send cmd error_msg [{}]", result.getErr_msg());
			String msg = result.getRs_msg();
			if (!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)) {
				execute.deleteFile(remote_file[0]);
				execute.deleteFile(remote_file[1]);
				return result;
			}
			if ("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)) {
				ShExecRsBean error_result = remote_sess.sendCmd("cat "
						+ remote_file[1]);
				String error_msg = error_result.getRs_msg();
				if (!Assert.isEmpty(error_msg)) {
					logger.debug("error msg[{}]", error_msg);
					result.setErr_msg(result.getErr_msg() + error_msg);
					result.setIs_succ(false);
				}
				execute.deleteFile(remote_file[1]);
			}
			execute.deleteFile(remote_file[0]);
			return result;
		}
		
		return null;
	}
	
	private String[] dealCmds(String[] old_cmds) {
		List<String> cmd_list = new ArrayList<String>();
		cmd_list.add("cd " + scBean.getLocal_root());
		String ecode = module_source_info.getData().getString("ecode");
		if (Assert.isEmpty(ecode)) {
			cmd_list.add("export LANG=zh_CN.gbk");
		} else {
			cmd_list.add("export LANG=" + ecode);
		}

		for (String cmd : old_cmds) {
			cmd_list.add(checkCmd(cmd));
		}
		String[] cmds = new String[old_cmds.length + 2];
		return cmd_list.toArray(cmds);
	}
	
	private String checkCmd(String cmd) {
		cmd = cmd.trim();
		int idx = cmd.indexOf(" ");
		String op = idx > 0 ? cmd.substring(0, idx) : cmd;
		String arg = idx > 0 ? cmd.substring(idx + 1) : null;
		logger.debug("op[{}],arg[{}]", op, arg);
		if("init".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			if(ss.length == 2){
				return init(ss[0], ss[1], null);
			} else if(ss.length == 3){
				return init(ss[0], ss[1], ss[2]);
			}
		} else if("create".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return create(ss[0]);
		} else if("delete".endsWith(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return delete(ss[0]);
		} else if("undelete".equals(op)){
			String[] ss = checkArg(cmd, arg, 1);
			return undelete(ss[0]);
		} else if("move".equals(op) || "mv".equals(op)){
			String[] ss = checkArg(cmd, arg, 1);
			return move(ss[0], ss.length > 1 ? ss[1] : "");
		} else if ("submit".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return submit(ss[0]);
		} else if ("bringover".equals(op)) {
			if(Assert.notEmpty(arg)){
				String[] ss = checkArg(cmd, arg, 1);
				return bringover(ss[0]);
			}else{
				return bringover("");
			}
		} else if ("download".equals(op)) {
			// download remote.xml loacl_path
			String[] ss = checkArg(cmd, arg, 1);
			return download(ss[0], ss.length > 1 ? ss[1] : "");
		} else if ("list".equals(op) || "list-nwf".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return list(ss[0]);
		}
		return cmd;
	}
	
	private String[] checkArg(String cmd, String argv, int count) {
		String argv0 = null;
		if (StringUtil.isEmpty(argv)) {
			throw new RuntimeException("[" + cmd + "] 缺少参数");
		}
		if (argv.charAt(0) == '\'') {
			int last = argv.lastIndexOf('\'');
			argv0 = argv.substring(1, last);
			argv = argv.substring(last + 1).trim();
		} else if (argv.charAt(0) == '\"') {
			int last = argv.lastIndexOf('\"');
			argv0 = argv.substring(1, last);
			argv = argv.substring(last + 1).trim();
		}
		String[] ss = argv.split(" ");
		if (argv0 != null) {
			String[] sss = new String[ss.length + 1];
			sss[0] = argv0;
			System.arraycopy(ss, 0, sss, 1, ss.length);
			ss = sss;
		}
		if (ss.length < count) {
			throw new RuntimeException("[" + cmd + "] 参数个数应为[" + count + "]实为["
					+ ss.length + "]");
		}
		return ss;
	}
	
	/**
	 * 该操作表示在指定的父分支初始化一个本地工作区。执行该操作后，系统将自动在指定的目录下
	 * 创建一个名为.fitrfly的子目录，此目录用于存储本地工作区 的相关信息
	 * Description: 
	 * @param proj
	 * @param parent_branch
	 * @param local_path
	 * @return
	 */
	private String init(String proj, String parent_branch, String local_path) {
		logger.debug("firefly project_name [{}] parent_branch [{}] local_path [{}]", proj, parent_branch, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		if(Assert.notEmpty(local_path)){
			sb.append("hff init ")
			.append("-h ").append(scBean.getSoc_ip())
			.append(" -proj ").append(proj)
			.append(" -p ").append(parent_branch)
			.append(" -d ").append(join_with(scBean.getLocal_root(), local_path, '/'))
			.append(" -u ").append(scBean.getSc_user())
			.append(" -pwd ").append(scBean.getSc_passwd());
		}else{
			sb.append("hff init ")
			.append("-h ").append(scBean.getSoc_ip())
			.append(" -proj ").append(proj)
			.append(" -p ").append(parent_branch)
			.append(" -u ").append(scBean.getSc_user())
			.append(" -pwd ").append(scBean.getSc_passwd());
		}
		logger.debug("firefly init cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 将本地工作去文件添加到资源控制中
	 * Description: firefly中的是相对路径，需要先cd到主目录下
	 * @param remote_path 远程版本路径，不包括url，可以是目录或者文件
	 * @return
	 */
	private String create(String remote_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff create ").append(path);
		logger.debug("firefly create cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 删除工作区文件或目录操作 
	 * Description: firefly中的是相对路径，需要先cd到主目录下
	 * @param remote_path远程版本路径，不包括url，可以是目录或者文件
	 * @return
	 */
	private String delete(String remote_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff delete ").append(path);
		logger.debug("firefly delete cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 将被上面delete 掉的文件恢复到原目录下
	 * Description: firefly 中的是相对路径，需要先cd到主目录下
	 * @param remote_path 远程版本路径，不包括url，可以是目录或者文件
	 * @return
	 */
	private String undelete(String remote_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff undelete ").append(path);
		logger.debug("firefly undelete cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 文件/目录改名或者移动
	 * Description: 
	 * @param remote_path
	 * @param local_path
	 * @return
	 */
	private String move(String remote_path, String local_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]local_path[{}]", path, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff move ")
				.append(remote_path).append(" ").append(local_path);
		logger.debug("firefly move cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 本地工作区文件提交
	 * Description: firefly中的是相对路径，需要先cd到主目录下
	 * @param remote_path 远程版本路径，不包括url，可以是目录或者文件
	 * @return
	 * hff submit -i -c ${comment} -crid ${crid} -r ${review} ${package_name}
	 * comment 提交描述 
	 * crid    提交编号 
	 * review  Review描述
	 */
	private String submit(String remote_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff submit -i -c coment -crid crid -r review ").append(path);
		logger.debug("firefly submit cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 该操作从父分支中把指定路径下的更改内容下载到本地工作去中，类似update
	 * Description: 
	 * @param remote_path
	 * @return
	 */
	private String bringover(String remote_path){
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff bringover ").append(remote_path);
		logger.debug("firefly bringover cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * download版本到本地
	 * 
	 * @param remote_path 远程版本路径，不包括url
	 * @param local_path 相对于用户根路径的相对路径
	 * @return
	 */
	private String download(String remote_path, String local_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]local_path[{}]", path, local_path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff download ")
				.append("-h ").append(scBean.getSoc_ip())
				.append(" -proj ").append(remote_path)
				.append(" -u ").append(scBean.getSc_user())
				.append(" -pwd ").append(scBean.getSc_passwd())
				.append(" -d ").append(join_with(scBean.getLocal_root(), local_path, '/'));
		logger.debug("firefly download cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	/**
	 * 查询目录下文件列表 
	 * Description: firefly中的是相对路径，需要先cd到主目录下
	 * @param remote_path 远程版本路径，不包括url
	 * @return
	 */
	private String list(String remote_path) {
		String path = remote_path;
		logger.debug("firefly remote_path [{}]", path);
		StringBuffer sb = new StringBuffer();
		sb.append("cd " + scBean.getSc_url()).append("\n");
		sb.append("hff list ").append(path);
		logger.debug("firefly list cmd[{}]", sb.toString());
		return sb.toString();
	}
	
	private String join_with(String part1, String part2, char c) {
		if (StringUtil.isEmpty(part2)) {
			return part1;
		}
		if (StringUtil.isEmpty(part1)) {
			return part2;
		}
		StringBuffer sb = new StringBuffer();
		sb.append('\'');
		sb.append(part1);
		if (sb.charAt(sb.length() - 1) == c) {
			sb.deleteCharAt(sb.length() - 1);
		}
		if (part2.charAt(0) != c) {
			sb.append('/');
		}
		sb.append(part2);
		sb.append('\'');
		return sb.toString();
	}
	
	
	//需要交互时直接写死让其通过
	private String execCmdWithAuth(String cmd) {
		try {
			this.remote_sess.send(cmd);
			String msg = this.remote_sess.getReplyUntilPrompt(new String[] {
					":", "$" });
			if (msg.trim().charAt(msg.length() - 1) == ':') {
				this.remote_sess.send(this.scBean.getSc_passwd());
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
}
