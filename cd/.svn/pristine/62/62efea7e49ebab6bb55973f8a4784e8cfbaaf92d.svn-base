/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.system.dt.service;

import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.exc.WasParamsIpIsExistException;
import com.wk.cd.exc.BsNodeNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DtCheckSocExistService {

	@Inject
	DtSourceDaoService daoService;

	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	@Inject
	private CommonService comsrv;

	@Inject
	private FTPRCallService ftpsrv;

	public Boolean assertSocExist(String soc_name) {
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		logger.info("检测数据源[{}]是否存在", soc_name);
		if (this.daoService.countBySocName(soc_name) == 0)
			return Boolean.valueOf(false);
		if (this.daoService.getInfoByKey(info).getRcd_state() == RCD_STATE.ABNORMAL) {
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public void checkSocExist(String soc_name) {
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		logger.info("检测数据源[{}]是否存在", soc_name);
		if (this.daoService.countBySocName(soc_name) == 0)
			throw new RecordNotFoundException().addScene("TABLE", "数据源信息表")
					.addScene("FIELD", soc_name);
		if (this.daoService.getInfoByKey(info).getRcd_state() == RCD_STATE.ABNORMAL)
			throw new RecordStateAbnormalException()
					.addScene("TABLE", "数据源信息表").addScene("FIELD", soc_name);
	}

	public void checkSocIPExist(String soc_ip) {
		logger.info("检测数据源IP[{}]是否存在", soc_ip);
		if (this.daoService.countBySocIp(soc_ip) == 0)
			throw new BsNodeNotExistException().addScene("IP", soc_ip);
	}

	public void checkSocNotExist(String soc_name) {
		logger.info("检测数据源[{}]是否不存在", soc_name);
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		info = this.daoService.getInfoByKey(info);
		if (this.daoService.countBySocName(soc_name) > 0)
			throw new RecordAlreadyExistException().addScene("TABLE", "数据源信息表")
					.addScene("FIELD", soc_name);
	}

	protected String getLogTxt(String soc_name) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(soc_name);
		return lgsvc.getLogTxt("检查数据源是否存在", lst_val);
	}

	public void checkWasIp(String wasip) {
		if (("".equals(wasip)) || (wasip.length() < 7) || (wasip.length() > 15)) {
			throw new WasParamsIpIsExistException();
		}
		String rexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(wasip);
		boolean ipAddress = mat.find();
		if (!(ipAddress))
			throw new WasParamsIpIsExistException();
	}

	public void checkUserRootPathExist(DtSourceInfo info, String work_seq) {
		logger.info("******checkUserRootPathExist Begin******");
		if ((!(Assert.isEmpty(info)))
				&& (!(Assert.isEmpty(info.getUser_root_path())))) {
			String root_path = info.getUser_root_path();
			PROTOCOL_TYPE type = info.getProtocol_type();
			if (PROTOCOL_TYPE.SFTP.equals(type)) {
				FTPBean bean = getFtpBean(info, work_seq);
				this.comsrv.checkRemoteFileExistBySFTP(bean, root_path, false);
			} else if (PROTOCOL_TYPE.PLT_FTP.equals(type)) {
				FTPBean bean = getFtpBean(info, work_seq);
				this.ftpsrv.checkRemoteDirExist(bean, root_path);
			} else if (!(PROTOCOL_TYPE.TELNET.equals(type))) {
				PROTOCOL_TYPE.SSH.equals(type);
			}
		}

		logger.info("******checkUserRootPathExist End******");
	}

	private FTPBean getFtpBean(DtSourceInfo info, String work_seq) {
		FTPBean bean = new FTPBean();
		bean.setProtocol_type(info.getProtocol_type());
		bean.setRemote_uname(info.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null,
				info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
				info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(info.getSoc_ip());
		bean.setSoc_port(info.getSoc_port());
		bean.setSoc_name(info.getSoc_name());
		bean.setWork_seq(work_seq);
		return bean;
	}
}