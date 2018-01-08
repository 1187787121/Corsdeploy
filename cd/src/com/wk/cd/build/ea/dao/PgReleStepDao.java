/**
 * Title: PgReleStepDao.java
 * File Description: 发布方案阶段表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgReleStepInfo;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:发布方案阶段表
 * @author AutoGen
 */
abstract class PgReleStepDao extends EntityDao<PgReleStepInfo> {

	/** 
	 * Description: 
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id")
	abstract int deleteProgReleStepByProId(String prog_id);

	/** 
	 * Description: 查询发布方案阶段表的个数
	 * @param template_name
	 * @return 
	 */
	@SqlParam(condition="TEMPLATE_NAME =:template_name")
	abstract int countByTemplateName(String template_name);
	
	
	@SqlParam(condition="PROG_ID =:prog_id and GEN_YN_FLAG =:gen_yn_flag")
	abstract int countGenById(String prog_id,YN_FLAG gen_yn_flag);

	/** 
	 * Description: 查询发布方案阶段表
	 * @param template_name
	 * @return 
	 */
	@SqlParam(condition="TEMPLATE_NAME =:template_name")
	abstract List<PgReleStepInfo> getInfoByTemplateName(String template_name);
	
	@SqlParam(condition="TEMPLATE_NAME =:template_name and PROG_ID =:prog_id")
	abstract List<PgReleStepInfo> getInfoByTpNameAndPgId(String template_name,String prog_id);

	/** 
	 * Description: 获取发布方案阶段列表
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id")
	abstract List<PgReleStepInfo> getReleListByProgId(String prog_id);
}