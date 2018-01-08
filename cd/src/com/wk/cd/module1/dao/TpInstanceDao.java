/**
 * Title: TpInstanceDao.java
 * File Description: 投产模版实例表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-22
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.TpInstanceInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:投产模版实例表
 * @author AutoGen
 */
abstract class TpInstanceDao extends EntityDao<TpInstanceInfo> {
	/**
	 * Description: 根据系统名和项目名查询所有的实例
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	@SqlParam(condition = "BUSINESS_SYS_NAME =:business_sys_name and PROJECT_NAME =:project_name")
	abstract List<TpInstanceInfo> queryInfoByBsnAndProjn(
			String business_sys_name,String project_name);

}