/**
 * Title: CeSystemTemplateDao.java
 * File Description: 应用系统模板表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeSystemTemplateInfo;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:应用系统模板表
 * @author AutoGen
 */
abstract class CeSystemTemplateDao extends EntityDao<CeSystemTemplateInfo> {

	/** 
	 * Description: 根据模版类型查询模板名
	 * @param template_type
	 * @return 
	 */
	@SqlParam(sql="select ltrim(rtrim(TEMPLATE_NAME)) from ce_system_template where TEMPLATE_TYPE=:template_type")
	abstract List<String> queryTemplateNameByType(TEMPLATE_TYPE template_type);

	/** 
	 * Description: 通过系统名删除表
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract int deleteSystempBySysName(String sys_name);

	/** 
	 * Description: 通过系统名查看表
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract DBIterator<CeSystemTemplateInfo> getInfoBySysName(String sys_name);

	/** 
	 * Description: 得到系统模板的总数
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract int countAllSystemTemplate(String sys_name);

	/** 
	 * Description: 根据系统名和模板类型获取信息
	 * @param sys_name
	 * @param template_type
	 * @return 
	 */
	@SqlParam(querySet = {"LTRIM(RTRIM(TEMPLATE_NAME))"}, condition = "SYS_NAME =:sys_name AND TEMPLATE_TYPE =:template_type")
	abstract List<String> getInfosBySysAndType(String sys_name, TEMPLATE_TYPE template_type);
	
}