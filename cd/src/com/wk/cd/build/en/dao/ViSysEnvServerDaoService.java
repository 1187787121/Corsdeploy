/**
 * Title: ViSysEnvServerDaoService.java
 * File Description: VIEW
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.ViSysEnvServerInfo;
import com.wk.lang.Inject;

/**
 * Class description:VIEW
 * @author AutoGen
 */
public class ViSysEnvServerDaoService {
	@Inject private ViSysEnvServerDao dao;

	/**
	 * 根据主键查询一条记录
	 * @return ViSysEnvServerInfo
	 */
	public ViSysEnvServerInfo getInfoByKey(ViSysEnvServerInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @return ViSysEnvServerInfo
	 */
	public ViSysEnvServerInfo getInfoByKeyForUpdate(ViSysEnvServerInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param ViSysEnvServerInfo info
	 * @return int
	 */
	public int insertInfo(ViSysEnvServerInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<ViSysEnvServerInfo>
	 * @return int
	 */
	public int insertListInfo(List<ViSysEnvServerInfo> infos) {
		return dao.insert(infos);
	}

	
	
}