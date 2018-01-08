/**
 * Title: WkWorkSrvDaoService.java
 * File Description: 任务服务配置表
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
 * Class description:任务服务配置表
 * @author AutoGen
 */
public class WkWorkSrvDaoService {
	@Inject
	private WkWorkSrvDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkWorkSrvInfo info
	 * @return WkWorkSrvInfo
	 */
	public WkWorkSrvInfo getInfoByKey(WkWorkSrvInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkWorkSrvInfo info
	 * @return WkWorkSrvInfo
	 */
	public WkWorkSrvInfo getInfoByKeyForUpdate(WkWorkSrvInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkWorkSrvInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkSrvInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkWorkSrvInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkSrvInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据任务编码查询服务信息
	 * @param work_code 任务编码
	 * @return 服务信息
	 */
	public List<WkWorkSrvInfo> getWorkSrvList(String work_code) {
		return dao.getWorkSrvList(work_code);
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
	 * 更新任务服务配置表
	 * @param work_code 任务编码
	 * @param srv_list 服务列表
	 * @return 更新条数
	 */
	public int updateWorkByWrokCode(String work_code, List<WkWorkSrvInfo> infos) {
		if (!Assert.isEmpty(dao.getWorkSrvList(work_code))) {
			dao.deleteWorkByWorkCode(work_code);
		}
		return dao.insert(infos);
	}
}