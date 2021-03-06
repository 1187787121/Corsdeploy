/**
 * Title: WkWorkSocDao.java
 * File Description: 任务数据源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.work.wk.info.*;

/**
 * Class description:任务数据源配置表
 * @author AutoGen
 */
abstract class WkWorkSocDao
		extends EntityDao<WkWorkSocInfo> {
	/**
	 * Description: 根据work_code获取任务数据源信息列表
	 * @param work_code 任务编码
	 * @return
	 */
	@SqlParam(condition = "WORK_CODE = :work_code")
	abstract List<WkWorkSocInfo> getWorkSocList(String work_code);

	/**
	 * 根据任务编码删除对应任务信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	@SqlParam(condition = "WORK_CODE = :work_code")
	abstract int deleteWorkByWorkCode(String work_code);
}