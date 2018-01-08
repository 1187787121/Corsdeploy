package com.wk.cd.system.dt.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dt.bean.SocParamsBean;
import com.wk.cd.system.dt.bean.SvnParamsBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.exc.CheckProtocolTypeException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.PLTFTP;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

import java.util.ArrayList;
import java.util.List;

public class DtSocService {

	@Inject
	private DtSourceDaoService daoService;

	@Inject
	private DtCheckSocExistService daoCheckService;
	
	@Inject
	private FTPRCallService ftpRSrv;

	public DtSourceInfo querySocDetailBySocName(String soc_name) {
		return this.daoService.querySocDetailBySocName(soc_name);
	}

	public List<DtSourceInfo> querySocInfoList(List<String> soc_name_list) {
		return this.daoService.querySocInfoList(soc_name_list);
	}

	public List<DtSourceInfo> queryAllSoc() {
		return this.daoService.queryAllSoc();
	}

	public DBIterator<DtSourceInfo> iteratorAllSoc() {
		return this.daoService.iteratorAllSoc();
	}

	public List<SocPrivBean> queryAllSocPriv() {
		return this.daoService.queryAllSocPriv();
	}

	public DtSourceInfo getInfoByKey(String soc_name) {
		this.daoCheckService.checkSocExist(soc_name);
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		return this.daoService.getInfoByKey(info);
	}

	public List<String> getSocNameByProtypeAndIp(String soc_ip,
			PROTOCOL_TYPE protocol_type) {
		List<DtSourceInfo> source_info_list = daoService.querySocByIpAndProType(
				protocol_type, soc_ip);
		List<String> source_name_list = new ArrayList<String>();
		if (!(Assert.isEmpty(source_info_list))) {
			for (DtSourceInfo info : source_info_list) {
				String soc_name = info.getSoc_name();
				source_name_list.add(soc_name);
			}
		}
		return source_name_list;
	}

	public List<DtSourceInfo> getSocNameByIp(String soc_ip) {
		return this.daoService.querySocByIp(soc_ip);
	}

	public SocParamsBean getWasParams(String socParams) {
		SocParamsBean wasParamsBean = new SocParamsBean();
		if (!(Assert.isEmpty(socParams))) {
			String[] wasParams = socParams.split("\\|");
			wasParamsBean.setIp(wasParams[0]);
			wasParamsBean.setPort(Integer.parseInt(wasParams[1]));
			wasParamsBean.setUname(wasParams[2]);

			wasParamsBean.setPasswd(wasParams[3]);
		}
		return wasParamsBean;
	}

	public SvnParamsBean getSvnParams(String socParams) {
		SvnParamsBean svnParamsBean = new SvnParamsBean();
		if (!(Assert.isEmpty(socParams))) {
			String[] svnParams = socParams.split("\\|");
			svnParamsBean.setSvn_url(svnParams[0]);
			svnParamsBean.setSvn_uname(svnParams[1]);
			svnParamsBean.setSvn_passwd(svnParams[2]);
		}
		return svnParamsBean;
	}

	public SocParamsBean getWasParamsPartInfo(String socParams) {
		SocParamsBean wasParamsBean = new SocParamsBean();
		if (!(Assert.isEmpty(socParams))) {
			String[] wasParams = socParams.split("\\|");
			wasParamsBean.setIp(wasParams[0]);
			wasParamsBean.setPort(Integer.parseInt(wasParams[1]));
			wasParamsBean.setUname(wasParams[2]);
		}
		return wasParamsBean;
	}

	public SvnParamsBean getSvnParamsPartInfo(String socParams) {
		SvnParamsBean svnParamsBean = new SvnParamsBean();
		if (!(Assert.isEmpty(socParams))) {
			String[] svnParams = socParams.split("\\|");
			svnParamsBean.setSvn_url(svnParams[0]);
			svnParamsBean.setSvn_uname(svnParams[1]);
		}
		return svnParamsBean;
	}

	public String getWasParamsInfo(String soc_params, String passed_key) {
		StringBuffer target_params = new StringBuffer("-conntype SOAP -host ");
		if (!(Assert.isEmpty(soc_params))) {
			String[] wasParams = soc_params.split("\\|");
			String was_passwd = DESUtil.docryptAllowReverse(false, passed_key,
					wasParams[3]);
			target_params.append(wasParams[0] + " ");
			target_params.append("-port " + wasParams[1] + " ");
			target_params.append("-user " + wasParams[2] + " ");
			target_params.append("-password " + was_passwd + " ");
		}
		return target_params.toString();
	}

	public PROTOCOL_TYPE getProtocolTypeByName(String soc_name) {
		return this.daoService.getProtocolTypeByName(soc_name);
	}

	public String getUserRootPath(String soc_name) {
		return this.daoService.getUserRootPath(soc_name);
	}
	/**
	 * 协议类型位SSH时改成SFTP,TELENT时校验是否有FTP权限，有就改成PLT_FTP权限的DtSourceInfo
	 */
	public DtSourceInfo changeDtSource(DtSourceInfo info){
		DtSourceInfo dt_info = null;
		if(!Assert.isEmpty(info)) {
			dt_info = DtSourceInfo.copy(info);
			
			PROTOCOL_TYPE type = dt_info.getProtocol_type();
			if(type==PROTOCOL_TYPE.TELNET) {
				dt_info.setProtocol_type(PROTOCOL_TYPE.PLT_FTP);
				dt_info.setSoc_port(21);
				FTPBean bean = getFTPBean(dt_info);
				PLTFTP ftp = ftpRSrv.getPFTPClient(bean);
				if(ftp.connect()) {
					return dt_info;
				}else {
					throw new CheckProtocolTypeException().addScene("TYPE",dt_info.getSoc_name()+"对应的"+type.getCname());
				}
			} else if(type==PROTOCOL_TYPE.SSH) {
				dt_info.setProtocol_type(PROTOCOL_TYPE.SFTP);
				return dt_info;
			} 
		}
		return dt_info;
	}
	/**
	 * Description: FTPBean
	 * @param dt_info
	 * @return
	 */
	public FTPBean getFTPBean(DtSourceInfo dt_info) {
		FTPBean bean = new FTPBean();
		bean.setRemote_uname(dt_info.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dt_info.getSoc_ip());
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name());
		String encoding_type = dt_info.getEncoding_type();
		bean.setFtp_encoding(Assert.isEmpty(encoding_type) ? "GBK" : encoding_type);
		bean.setWork_seq("no_seq");
		return bean;
	}
}