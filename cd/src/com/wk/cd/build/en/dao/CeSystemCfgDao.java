/**
 * Title: CeSystemCfgDao.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-6
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeSystemCfgInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:
 * @author AutoGen
 */
abstract class CeSystemCfgDao extends EntityDao<CeSystemCfgInfo> {

	/** 
	 * Description: ͨ��Ӧ��ϵͳ��ɾ��Ӧ��ϵͳ�����ļ���
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract int deleteSystemCfgBySysName(String sys_name);

	/** 
	 * Description: ͨ��ϵͳ����ѯӦ��ϵͳ�����ļ����е��ļ���
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(sql = "SELECT DISTINCT LTRIM(RTRIM(CFG_BK_FNAME)) FROM CE_SYSTEM_CFG WHERE SYS_NAME=:sys_name")
	abstract List<String> queryCfgNameBySysName(String sys_name);

	/** 
	 * Description: ͨ����������ѯ�����ļ��б�
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql = "SELECT DISTINCT LTRIM(RTRIM(cfg.CFG_BK_FNAME)) FROM CE_SYSTEM_CFG cfg LEFT JOIN ce_environment es on es.SYS_NAME=cfg.SYS_NAME WHERE es.ENV_NAME=:env_name")
	abstract List<String> queryCfgNameByEnvName(String env_name);

}