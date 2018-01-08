/**
 * Title: CeSystemCfgDaoService.java
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
import com.wk.lang.Inject;

/**
 * Class description:
 * @author AutoGen
 */
public class CeSystemCfgDaoService {
	@Inject private CeSystemCfgDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param CeSystemCfgInfo info
	 * @return CeSystemCfgInfo
	 */
	public CeSystemCfgInfo getInfoByKey(CeSystemCfgInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param CeSystemCfgInfo info
	 * @return CeSystemCfgInfo
	 */
	public CeSystemCfgInfo getInfoByKeyForUpdate(CeSystemCfgInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param CeSystemCfgInfo info
	 * @return int
	 */
	public int insertInfo(CeSystemCfgInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CeSystemCfgInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeSystemCfgInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 通过应用系统名删除应用系统配置文件表
	 * @param sys_name 
	 */
	public int deleteSystemCfgBySysName(String sys_name) {
		return dao.deleteSystemCfgBySysName(sys_name);
	}

	/** 
	 * Description: 通过系统名查询应用系统配置文件表中的文件名
	 * @param sys_name
	 * @return 
	 */
	public List<String> queryCfgNameBySysName(String sys_name) {
		return dao.queryCfgNameBySysName(sys_name);
	}

	/** 
	 * Description: 通过环境名查询配置文件列表
	 * @param env_name
	 * @return 
	 */
	public List<String> queryCfgNameByEnvName(String env_name) {
		return dao.queryCfgNameByEnvName(env_name);
	}

}