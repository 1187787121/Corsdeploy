/**
 * Title: CeServerDsDao.java
 * File Description: ����������Դ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:����������Դ��
 * @author AutoGen
 */
abstract class CeServerDsDao
		extends EntityDao<CeServerDsInfo> {

	/**
	 * Description: ���ݷ�������ɾ����¼
	 * @param server_name ��������
	 * @return int
	 */
	@SqlParam(condition = "SERVER_NAME=:server_name")
	abstract int deleteInfoByServerName(String server_name);

	/**
	 * Description:���ݷ��������Ʋ�ѯFTP��������Դ
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql = "SELECT LTRIM(RTRIM(csd.SOC_NAME)) FROM CE_SERVER_DS csd join dt_source ds on csd.soc_name = ds.soc_name WHERE SERVER_NAME=:server_name AND APPLY_TYPE like '%2%' and (PROTOCOL_TYPE=2 or PROTOCOL_TYPE=9)")
	abstract String getFtpConfigSocByServerName(String server_name);

	/**
	 * Description:���ݷ��������Ʋ�ѯSHELL��������Դ
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql = "SELECT LTRIM(RTRIM(csd.SOC_NAME)) FROM CE_SERVER_DS csd join dt_source ds on csd.soc_name = ds.soc_name WHERE SERVER_NAME=:server_name AND APPLY_TYPE like '%2%' and (PROTOCOL_TYPE=5 or PROTOCOL_TYPE=10)")
	abstract String getShellConfigSocByServerName(String server_name);
	
	/**
	 * Description:
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql = "select LTRIM(RTRIM(csd.soc_name)) from CE_SERVER_DS csd join dt_source ds on csd.soc_name = ds.soc_name where csd.server_name =:server_name")
	abstract List<String> querySocNameByServer(String server_name);

	/**
	 * Description: �õ���Ӧ���������ݸ���
	 * @param server_name
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract int countServerDtsourceByKey(String server_name, String soc_name);

	/** 
	 * Description: ���ݷ���������ѯ����������Դ��
	 * @param server_name
	 * @return 
	 */
	@SqlParam(sql = "select csd.* from CE_SERVER_DS csd join dt_source ds on csd.soc_name = ds.soc_name where csd.server_name =:server_name")
	abstract List<CeServerDsInfo> querySourceByServer(String server_name);
}