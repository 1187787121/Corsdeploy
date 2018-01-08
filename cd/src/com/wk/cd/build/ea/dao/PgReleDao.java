/**
 * Title: PgReleDao.java
 * File Description: 发布方案扩展表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:发布方案扩展表
 * 
 * @author AutoGen
 */
abstract class PgReleDao extends EntityDao<PgReleInfo> {

	/**
	 * Description: 根据主键更新发布模板信息
	 * 
	 * @param pub_template_name
	 * @param prog_id
	 * @return
	 */
	@SqlParam(updateSet = { "PUB_TEMPLATE_NAME" }, condition = "PK")
	abstract int updatePubTemplateName(String pub_template_name, String prog_id);

	/**
	 * Description: 根据主键更新回退模板信息
	 * 
	 * @param rol_template_name
	 * @param prog_id
	 * @return
	 */
	@SqlParam(updateSet = { "ROL_TEMPLATE_NAME" }, condition = "PK")
	abstract int updateRolTemplateName(String rol_template_name, String prog_id);
	
	/**
	 * Description: 根据主键更新版本数据源uuid
	 * 
	 * @param ver_soc_uuid
	 * @param prog_id
	 * @return
	 */
	@SqlParam(updateSet = { "VER_SOC_UUID" }, condition = "PK")
	abstract int updateVerSoc(String ver_soc_uuid, String prog_id);

	@SqlParam(updateSet = { "PUBL_PARAM_UUID" }, condition = "PK")
	abstract int updatePubUuParam(String publ_param_uuid, String prog_id);

	@SqlParam(updateSet = { "ROLL_PARAM_UUID" }, condition = "PK")
	abstract int updateRolUuParam(String roll_param_uuid, String prog_id);

	/**
	 * Description: 修改发布方案扩展表
	 * @param pub_template_name
	 * @param rol_template_name
	 * @param prog_id
	 * @return
	 */
	@SqlParam(updateSet = { "PUB_TEMPLATE_NAME", "ROL_TEMPLATE_NAME" }, condition = "PK")
	abstract int UpdatePgReleByKey(String pub_template_name, String rol_template_name, String prog_id);

}