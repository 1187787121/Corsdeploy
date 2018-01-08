/**
 * Title: TpParamGroupDao.java
 * File Description: 实例参数组数据表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-30
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.TpParamGroupInfo;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:实例参数组数据表
 * @author AutoGen
 */
abstract class TpParamGroupDao
		extends EntityDao<TpParamGroupInfo> {

	/**
	 * Description: 根据业务系统名、项目编号、上线包名称查询多条记录
	 * @param business_sys_name
	 * @param project_name
	 * @param propackage_name
	 * @return
	 */
	@SqlParam(sql = "select t1.* from TP_PARAM_GROUP t1 WHERE t1.BUSINESS_SYS_NAME=:business_sys_name and t1.PROJECT_NAME=:project_name and t1.FIELD_TYPE<3 and t1.PARAM_BK_SEQ = (SELECT t2.PARAM_BK_SEQ from TP_PARAM_GROUP t2 where t2.BUSINESS_SYS_NAME=:business_sys_name2 and t2.PROJECT_NAME=:project_name2 and t2.FIELD_TYPE=2 and t2.FIELD_VALUE=:propackage_name and t2.INSTANCE_PARAM_NAME=(select t3.INSTANCE_PARAM_NAME from tp_instance_param t3 where t3.BUSINESS_SYS_NAME =:business_sys_name3 and t3.PROJECT_NAME =:project_name3 and t3.NODE_TYPE =:node_type))")
	abstract List<TpParamGroupInfo> getListInfoByBsAndPj(
			String business_sys_name, String project_name,String business_sys_name2, String project_name2,String propackage_name, String business_sys_name3, String project_name3, String node_type);

	/**
	 * Description: 根据业务系统名、项目编号、模板类型删除多条记录
	 * @param business_sys_name
	 * @param project_name
	 * @param template_type
	 * @return
	 */
	@SqlParam(condition = "BUSINESS_SYS_NAME=:business_sys_name and PROJECT_NAME=:project_name and TEMPLATE_TYPE=:template_type")
	abstract int deleteListInfoByBsAndPj(String business_sys_name,
			String project_name, TEMPLATE_TYPE template_type);

	/**
	 * Description: 根据系统名、项目编号、模版类型查询多条记录 
	 * @param business_sys_name
	 * @param project_name
	 * @param template_type
	 * @return
	 */
	@SqlParam(condition = "BUSINESS_SYS_NAME =:business_sys_name and PROJECT_NAME =:project_name and TEMPLATE_TYPE =:template_type")
	abstract List<TpParamGroupInfo> queryInfoByBsAndPjAndType(
			String business_sys_name, String project_name,
			TEMPLATE_TYPE template_type);

	/** 
	 * Description: 根据投产包名和模板类型查询对应的投产包参数
	 * @param business_sys_name
	 * @param project_name
	 * @param propackage_name
	 * @param template_type
	 * @return 
	 */
	@SqlParam(sql ="SELECT t1.* FROM TP_PARAM_GROUP t1 LEFT JOIN TP_PARAM_GROUP t2 on t1.BUSINESS_SYS_NAME = t2.BUSINESS_SYS_NAME and t1.PROJECT_NAME = t2.PROJECT_NAME and t1.TEMPLATE_TYPE = t2.TEMPLATE_TYPE and t1.INSTANCE_PARAM_NAME = t2.INSTANCE_PARAM_NAME and t1.PARAM_BK_SEQ = t2.PARAM_BK_SEQ where t2.BUSINESS_SYS_NAME =:business_sys_name and t2.PROJECT_NAME =:project_name and t2.FIELD_VALUE =:propackage_name and t2.TEMPLATE_TYPE =:template_type and t2.FIELD_TYPE = 2")
	abstract List<TpParamGroupInfo> queryParamsByDppAndType(
			String business_sys_name, String project_name,
			String propackage_name, TEMPLATE_TYPE template_type);
}