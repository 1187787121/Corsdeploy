package com.wk.cd.system.dt.dao;

import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

import java.util.List;

abstract class DtSourceDao extends EntityDao<DtSourceInfo> {
	@SqlParam(condition = "soc_name=:soc_name")
	abstract int countBySocName(String paramString);

	@SqlParam(querySet = { "SOC_NAME", "SOC_IP", "SOC_PORT", "PROTOCOL_TYPE",
			"REMOTE_UNAME", "BK_TIMEOUT", "JDBC_DRV", "JDBC_URL",
			"JDBC_SCHEMA", "CVT_TYPE", "FTP_LOCAL_SCRIPT", "CVT_LOCAL_SCRIPT",
			"SOC_DOMAIN", "SOC_BK_DESC" }, condition = "SOC_NAME=:soc_name")
	abstract DtSourceInfo querySocDetailBySocName(String paramString);

	@SqlParam(updateSet = { "SOC_IP", "SOC_PORT", "PROTOCOL_TYPE",
			"REMOTE_UNAME", "REMOTE_PASSWD", "KEY_REMOTE_PASSWD", "BK_TIMEOUT",
			"JDBC_DRV", "JDBC_URL", "JDBC_SCHEMA", "CVT_TYPE",
			"FTP_LOCAL_SCRIPT", "CVT_LOCAL_SCRIPT", "SOC_DOMAIN", "SOC_BK_DESC" }, condition = "SOC_NAME=:soc_name")
	abstract void updateBySocName(DtSourceInfo paramDtSourceInfo);

	@SqlParam(sql = "SELECT SOC_NAME,SOC_IP,SOC_PORT,PROTOCOL_TYPE,REMOTE_UNAME,BK_TIMEOUT,CVT_TYPE FROM dt_source WHERE RCD_STATE=1 and (SOC_NAME like '%${soc_name}%') order by SOC_NAME", dynamic = true)
	abstract List<DtSourceInfo> pageAllSocName(String paramString,
			int paramInt1, int paramInt2);

	@SqlParam(sql = "SELECT count(*) FROM dt_source WHERE RCD_STATE=1 and (SOC_NAME like '%${soc_name}%')", dynamic = true)
	abstract int countAllSocName(String paramString);

	@SqlParam(condition = "1=1 and RCD_STATE=1")
	abstract int countAllSocName();

	@SqlParam(sql = "SELECT SOC_NAME,SOC_PORT,PROTOCOL_TYPE,KEY_REMOTE_PASSWD,BK_TIMEOUT,CVT_TYPE FROM dt_source WHERE RCD_STATE=1 AND (SOC_NAME like '%${soc_name}%') order by SOC_NAME", dynamic = true)
	abstract List<DtSourceInfo> pageBySocName(String paramString,
			int paramInt1, int paramInt2);

	@SqlParam(sql = "SELECT count(*) FROM dt_source WHERE RCD_STATE=1 AND (SOC_NAME like '%${soc_name}%')", dynamic = true)
	abstract int countByLikeSocName(String paramString);

	@SqlParam(sql = "SELECT soc_name, soc_ip, soc_port, protocol_type, remote_uname, remote_passwd, key_remote_passwd, bk_timeout, jdbc_drv, jdbc_url, jdbc_schema, cvt_type, ftp_local_script, cvt_local_script, soc_domain, soc_bk_desc, backup_fld, rcd_state FROM dt_source WHERE soc_name IN ${soc_name_list::1=0} ", dynamic = true)
	abstract List<DtSourceInfo> querySocInfoList(String paramString);

	@SqlParam(condition = "RCD_STATE = 1")
	abstract List<DtSourceInfo> queryAllSoc();

	@SqlParam(condition = "RCD_STATE = 1")
	abstract DBIterator<DtSourceInfo> iteratorAllSoc();

	@SqlParam(condition = "RCD_STATE = 1")
	abstract DBIterator<SocPrivBean> iteratorAllSocPriv();

	@SqlParam(sql = "SELECT ltrim(rtrim(soc_name)) FROM dt_source where RCD_STATE = 1")
	abstract List<String> queryAllSocName();

	@SqlParam(condition = "SOC_IP=:ip")
	abstract List<DtSourceInfo> querySocByIp(String paramString);

	@SqlParam(condition = "PROTOCOL_TYPE =:protocol_type")
	abstract List<DtSourceInfo> querySocByProType(
			PROTOCOL_TYPE paramPROTOCOL_TYPE);

	@SqlParam(condition = "PROTOCOL_TYPE =:protocol_type and SOC_IP =:ip")
	abstract List<DtSourceInfo> querySocByIpAndProType(
			PROTOCOL_TYPE paramPROTOCOL_TYPE, String paramString);

	@SqlParam(condition = "soc_ip=:soc_ip")
	abstract int countBySocIp(String paramString);

	@SqlParam(querySet = { "PROTOCOL_TYPE" }, condition = "soc_name=:soc_name")
	abstract int getProtocolTypeByName(String paramString);

	@SqlParam(querySet = { "USER_ROOT_PATH" }, condition = "SOC_NAME=:soc_name")
	abstract String getUserRootPath(String paramString);
	
	@SqlParam(sql = "select DISTINCT ltrim(rtrim(soc_ip))  from dt_source")
	abstract List<String> queryDistinctIp();
}