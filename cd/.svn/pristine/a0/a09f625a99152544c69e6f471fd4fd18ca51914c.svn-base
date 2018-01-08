/**
 * Title: BuildStepDao.java
 * File Description: 构建阶段表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import com.wk.cd.build.ea.info.BuildStepInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:构建阶段表
 * @author AutoGen
 */
abstract class BuildStepDao extends EntityDao<BuildStepInfo> {

	/** 
	 * Description: 根据任务编号和模板名查询多条记录
	 * @param work_id
	 * @param template_name
	 * @return 
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND TEMPLATE_NAME =:template_name", orderBy = "PHASE_ID ASC")
	abstract DBIterator<BuildStepInfo> queryListInfoByIdAndTp(String work_id, String template_name);

	/** 
	 * Description: 根据任务编号和模板名删除多条记录
	 * @param work_id
	 * @param template_name
	 * @return 
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND TEMPLATE_NAME =:template_name")
	abstract int deleteListByIdAndTp(String work_id, String template_name);
}