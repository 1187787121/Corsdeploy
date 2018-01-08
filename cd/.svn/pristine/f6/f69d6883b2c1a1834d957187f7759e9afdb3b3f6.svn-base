/**
 * Title: EnvTagStorageDao.java
 * File Description: 目标入库表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:目标入库表
 * @author AutoGen
 */
abstract class EnvTagStorageDao
		extends EntityDao<EnvTagStorageInfo> {

	/**
	 * Description: 根据环境名查询正在入库的入库编号
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND STORAGE_STATUS = 2")
	abstract List<EnvTagStorageInfo> queryIdByEnv(String env_name);

	/**
	 * Description:
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countByEnvName(String env_name);

	/**
	 * Description: 根据环境名查询入库的编号
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name", orderBy="crt_bk_date desc,crt_bk_time desc")
	abstract List<EnvTagStorageInfo> pageInfoByEnvName(String env_name,
			int start_recd, int limit_recd);

	/**
	 * Description: 根据入库编号查询数量
	 * @param storage_id
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract int countStorageIdByKey(String storage_id);

	/**
	 * Description: 通过入库编号修改版本编号
	 * @param tar_ver_num
	 * @param storage_id
	 * @return
	 */
	@SqlParam(updateSet = { "TAR_VER_NUM" }, condition = "PK")
	abstract int updateStroageInfoByKey(String tar_ver_num, String storage_id);

	/**
	 * Description: 修改入库状态和结束时间
	 * @param end_bk_tm
	 * @param storage_state
	 * @param storage_id
	 * @return
	 */
	@SqlParam(updateSet = { "END_BK_TM", "STORAGE_STATUS" }, condition = "PK")
	abstract int updateStrStatusInfoByKey(JaDateTime end_bk_tm,
			STORAGE_STATUS storage_state, String storage_id);

	/**
	 * Description: 修改入库结果
	 * @param storage_result
	 * @param storage_id
	 * @return
	 */
	@SqlParam(updateSet = { "STORAGE_RESULT" }, condition = "PK")
	abstract int updateStroageResultByKey(STORAGE_RESULT storage_result,
			String storage_id);

	/** 
	 * Description: 修改入库用时
	 * @param time_used
	 * @param storage_id
	 * @return 
	 */
	@SqlParam(updateSet = { "TIME_USED" }, condition = "PK")
	abstract int updateStroageTimeByKey(int time_used, String storage_id);

	/** 
	 * Description:  修改入库状态
	 * @param storage_status
	 * @param storage_id
	 * @return 
	 */
	@SqlParam(updateSet = { "STORAGE_STATUS" }, condition = "PK")
	abstract int updateStatusInfoByKey(STORAGE_STATUS storage_status,
			String storage_id);

	/** 
	 * Description: 根据环境名查询入库列表
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM ENV_TAG_STORAGE WHERE ENV_NAME ='${env_name}' ORDER BY CRT_BK_DATE DESC, CRT_BK_TIME DESC",dynamic=true)
	abstract List<EnvTagStorageInfo> queryStorageInfoByEnvName(String env_name);

	/** 
	 * Description: 
	 * @param project_name
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM ENV_TAG_STORAGE WHERE PROJECT_NAME ='${project_name}' ORDER BY CRT_BK_DATE DESC, CRT_BK_TIME DESC",dynamic=true)
	abstract List<EnvTagStorageInfo> queryStorageInfoByProName(String project_name);

	/** 
	 * Description: 获得环境下面入库中的任务数量
	 * @param env_name
	 * @param storage_status
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND STORAGE_STATUS =:storage_status")
	abstract int countStorageByEnvAndStatus(String env_name, STORAGE_STATUS storage_status);
}