/**
 * Title: EnvTagStorageDaoService.java
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
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:目标入库表
 * @author AutoGen
 */
public class EnvTagStorageDaoService {
	@Inject private EnvTagStorageDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param EnvTagStorageInfo info
	 * @return EnvTagStorageInfo
	 */
	public EnvTagStorageInfo getInfoByKey(EnvTagStorageInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param EnvTagStorageInfo info
	 * @return EnvTagStorageInfo
	 */
	public EnvTagStorageInfo getInfoByKeyForUpdate(EnvTagStorageInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param EnvTagStorageInfo info
	 * @return int
	 */
	public int insertInfo(EnvTagStorageInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<EnvTagStorageInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvTagStorageInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据环境名查询正在入库的入库编号
	 * @param env_name 
	 */
	public List<EnvTagStorageInfo> getIdByEnv(String env_name) {
		return dao.queryIdByEnv(env_name);
	}

	/** 
	 * Description: 根据环境名查询数量
	 * @param env_name
	 * @return 
	 */
	public int countByEnvName(String env_name) {
		return dao.countByEnvName(env_name);
	}

	/** 
	 * Description: 根据环境名查询入库的列表
	 * @param env_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> pageInfoByEnvName(String env_name, int start_recd, int limit_recd) {
		return dao.pageInfoByEnvName(env_name, start_recd, limit_recd);
	}

	/** 
	 * Description: 根据入库编号查询数量
	 * @return 
	 */
	public int countByStorageId(String storage_id) {
		return dao.countStorageIdByKey(storage_id);
	}
 
	/** 
	 * Description: 根据主键查询一条记录
	 * @param storage_id
	 * @return 
	 */
	public EnvTagStorageInfo getInfoByKey2(String storage_id) {
		EnvTagStorageInfo info = new EnvTagStorageInfo();
		info.setStorage_id(storage_id);
		return dao.get(info);
	}

	/** 
	 * Description: 更新入库信息
	 * @param tagst_update 
	 */
	public int updateStroageInfo(EnvTagStorageInfo tagst_update) {
	    return dao.update(tagst_update);
	}

	/** 
	 * Description: 通过入库编号修改版本编号
	 * @param tar_ver_num
	 * @param storage_id 
	 */
	public int updateStroageInfoByKey(String tar_ver_num, String storage_id) {
		return dao.updateStroageInfoByKey(tar_ver_num,storage_id);
		
	}

	/** 
	 * Description: 修改入库状态和结束时间
	 * @param now
	 * @param storaged
	 * @param storage_id 
	 */
	public int updateStrStatusInfoByKey(JaDateTime end_bk_tm,STORAGE_STATUS storage_state, String storage_id) {
		return dao.updateStrStatusInfoByKey(end_bk_tm,storage_state,storage_id);
		
	}

	/** 
	 * Description: 修改入库结果
	 * @param fail
	 * @param storage_id 
	 */
	public int updateStroageResultByKey(STORAGE_RESULT storage_result, String storage_id) {
		return dao.updateStroageResultByKey(storage_result,storage_id);
	}

	/** 
	 * Description: 修改入库用时
	 * @param time_used
	 * @param storage_id 
	 */
	public int updateStroageTimeByKey(int time_used, String storage_id) {
		return dao.updateStroageTimeByKey(time_used,storage_id);
	}

	/** 
	 * Description: 修改入库状态
	 * @param storaging
	 * @param storage_id 
	 */
	public int updateStatusInfoByKey(STORAGE_STATUS storage_status,
			String storage_id) {
	     return dao.updateStatusInfoByKey(storage_status,storage_id);
		
	}

	/** 
	 * Description: 根据环境名查询入库列表
	 * @param env_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> queryStorageInfoByEnvName(String env_name) {
		return dao.queryStorageInfoByEnvName(env_name);
	}

	/** 
	 * Description: 根据项目名查询入库列表
	 * @param project_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> queryStorageInfoByProName(String project_name) {
		return dao.queryStorageInfoByProName(project_name);
	}

	/** 
	 * Description: 获得环境下面入库中的任务数量
	 * @param env_name
	 * @param storaging
	 * @return 
	 */
	public int countStorageByEnvAndStatus(String env_name, STORAGE_STATUS storage_status) {
		return dao.countStorageByEnvAndStatus(env_name,storage_status);
	}

}