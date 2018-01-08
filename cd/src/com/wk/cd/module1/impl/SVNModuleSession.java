package com.wk.cd.module1.impl;

import com.wk.Controller;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleSessionBase;
import com.wk.cd.remote.sc.SCRSession;
import com.wk.cd.remote.sc.bean.SCBean;
import com.wk.cd.remote.sc.service.SVNCallService;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.StringUtil;

/**
 * SVN Module会话连接 封装实现了SVN的常用命令：checkout(co), add, rm, update(up), commit(ci)
 * Created by 姜志刚 on 2016/11/25.
 */
public class SVNModuleSession
		extends ModuleSessionBase {

	private static final Log logger = LogFactory.getLog();

	private static final int DEFAULT_TIMEOUT = 3600;

	private RConnection conn;
	private SCRSession sess;

	@Inject
	private SVNCallService svn_svc;

	public SVNModuleSession(ModuleSourceInfo module_source_info, int step_count) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
	}

	public String execCmd(String cmd) {
		cmd = cmd.trim();
		int idx = cmd.indexOf(" ");
		String op = idx > 0 ? cmd.substring(0, idx) : cmd;
		String arg = idx > 0 ? cmd.substring(idx + 1) : null;
		logger.debug("op[{}],arg[{}]", op, arg);
		if ("co".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			logger.debug("ss length:[{}]", ss.length);
			return sess.co(ss[0], ss.length > 1 ? ss[1] : "");
		} else if ("add".equals(op)) {
			String s = checkArg(cmd, arg, 1)[0];
			return sess.add(arg.split(","));
		} else if ("rm".equals(op)) {
			String s = checkArg(cmd, arg, 1)[0];
			return sess.rm(arg.split(","));
		} else if ("commit".equals(op) || "ci".equals(op)) {
			String s = checkArg(cmd, arg, 1)[0];
			return sess.ci(arg);
		} else if ("update".equals(op) || "up".equals(op)) {
			return sess.update();
		} else if ("export".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return sess.export(ss[0], ss.length > 1 ? ss[1] : "");
		} else if ("list".equals(op) || "ls".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			return sess.ls(ss[0]);
		} else if ("co_empty".equals(op) || "commit_empty".equals(op)) {
			String[] ss = checkArg(cmd, arg, 1);
			logger.debug("ss length:[{}]", ss.length);
			return sess.co_empty(ss[0], ss.length > 1 ? ss[1] : "");
		}
		return sess.execCmd(cmd);
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
			throw new RuntimeException("[" + cmd + "] 参数个数应为[" + count + "]实为[" + ss.length + "]");
		}
		return ss;
	}

	@Override
	protected void implConnect() {
		PROTOCOL_TYPE proto_type = module_source_info.getProtocol_type();
		logger.debug("协议：[{}]",proto_type.getCname());
		String ecode = module_source_info.getData().getString("ecode");
		logger.debug("编码：[{}]",ecode);
		SCBean bean = getSCBeanFromSoc(module_source_info);
		if (proto_type == PROTOCOL_TYPE.SVN) {
			logger.debug("#####export LANG####:[{}]",ecode);
			sess = svn_svc.connect(bean, step_count);
			if (Assert.isEmpty(ecode)) {
				sess.execCmd("export LANG=zh_CN.gbk");
			} else {
				sess.execCmd("export LANG=" + ecode);
			}
		} else {
			throw new RuntimeException(bean.getProtocol_type() + " not supported");
		}
		logger.info("{}'s session {} connected", module_source_info.getDt_source_info().getSoc_name(), sess);

	}

	@Override
	protected void implDisconnect() {
		if (sess != null) {
			sess.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
	}

	private SCBean getSCBeanFromSoc(ModuleSourceInfo module_source_info) {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		SCBean bean = new SCBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname());
		if (!Assert.isEmpty(dt_info.getKey_remote_passwd()) && !Assert.isEmpty(dt_info.getKey_remote_passwd())) {
			String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd().trim());
			String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
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
	 * @param soc_params 扩展参数
	 * @param passed_key 加密密码的密钥
	 * @return
	 */
	public static String[] getData(ServiceData data) {
		String url = data.getString("url");
		String user = data.getString("user");
		String passed_key = data.getString("passed_key");
		passed_key = DESUtil.docryptAllowReverse(false, null, passed_key.trim());
		String password = DESUtil.docryptAllowReverse(false, passed_key, data.getString("password"));
		String[] str = new String[] { url, user, password };
		return str;
	}

}
