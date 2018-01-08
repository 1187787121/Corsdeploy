/**
 * Title: WkWorkRsDaoService.java
 * File Description: 任务资源配置表
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
 * Class description:任务资源配置表
 * @author AutoGen
 */
public class WkWorkRsDaoService {
	@Inject
	private WkWorkRsDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkWorkRsInfo info
	 * @return WkWorkRsInfo
	 */
	public WkWorkRsInfo getInfoByKey(WkWorkRsInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkWorkRsInfo info
	 * @return WkWorkRsInfo
	 */
	public WkWorkRsInfo getInfoByKeyForUpdate(WkWorkRsInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkWorkRsInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkRsInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkWorkRsInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkRsInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据任务编码查询资源编码信息
	 * @param work_code 任务编码
	 * @return 资源编码信息
	 */
	public List<WkWorkRsInfo> getWorkRsList(String work_code) {
		return dao.getWorkRsList(work_code);
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
	 * 更新任务资源配置表
	 * @param work_code 任务编码
	 * @param rs_list 资源列表
	 * @return 更新条数
	 */
	public int updateWorkByWrokCode(String work_code, List<WkWorkRsInfo> infos) {
		if (!Assert.isEmpty(dao.getWorkRsList(work_code))) {
			dao.deleteWorkByWorkCode(work_code);
		}
		return dao.insert(infos);
	}
}