/**
 * Title: WkWorkSocDaoService.java
 * File Description: 任务数据源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:任务数据源配置表
 * @author AutoGen
 */
public class WkWorkSocDaoService {
	@Inject
	private WkWorkSocDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkWorkSocInfo info
	 * @return WkWorkSocInfo
	 */
	public WkWorkSocInfo getInfoByKey(WkWorkSocInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkWorkSocInfo info
	 * @return WkWorkSocInfo
	 */
	public WkWorkSocInfo getInfoByKeyForUpdate(WkWorkSocInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkWorkSocInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkSocInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkWorkSocInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkSocInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据任务编码查询数据源信息
	 * @param work_code 任务编码
	 * @return 数据源信息
	 */
	public List<WkWorkSocInfo> getWorkSocList(String work_code) {
		return dao.getWorkSocList(work_code);
	}

	/**
	 * 根据任务编码删除对应任务信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkByWorkCode(String work_code) {
		return dao.deleteWorkByWorkCode(work_code);
	}

	/**
	 * 更新任务数据源配置表
	 * @param work_code 任务编码
	 * @param soc_list 数据源列表
	 * @return 更新条数
	 */
	public int updateWorkByWrokCode(String work_code, List<WkWorkSocInfo> infos) {
		if (!Assert.isEmpty(dao.getWorkSocList(work_code))) {
			dao.deleteWorkByWorkCode(work_code);
		}
		return dao.insert(infos);
	}
}