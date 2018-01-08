/**
 * Title: PgReleDaoService.java
 * File Description: 发布方案扩展表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.lang.Inject;

/**
 * Class description:发布方案扩展表
 * @author AutoGen
 */
public class PgReleDaoService {
	@Inject private PgReleDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param PgReleInfo info
	 * @return PgReleInfo
	 */
	public PgReleInfo getInfoByKey(String prog_id) {
		return dao.get(prog_id);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param PgReleInfo info
	 * @return PgReleInfo
	 */
	public PgReleInfo getInfoByKeyForUpdate(PgReleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param PgReleInfo info
	 * @return int
	 */
	public int insertInfo(PgReleInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<PgReleInfo>
	 * @return int
	 */
	public int insertListInfo(List<PgReleInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 删除发布方案扩展表表
	 * @param prog_id 
	 */
	public int deleteProgRele(String prog_id) {
		return dao.delete(prog_id);
	}
	
	/** 
	 * Description: 更新发布方案扩展表表
	 * @param PgReleInfo 
	 */
	public int UpdateInfoByKey(PgReleInfo info) {
		return dao.update(info);
	}
	/**
	 * Description: 根据主键修改发布模板字段
	 * @param pub_template_name
	 * @param prog_id
	 * @return
	 */
	public int updatePubTemplateName(String pub_template_name, String prog_id){
		return dao.updatePubTemplateName(pub_template_name, prog_id);
	}
	/**
	 * Description: 根据主键修改发布模板字段
	 * @param rol_template_name
	 * @param prog_id
	 * @return
	 */
	public int updateRolTemplateName(String rol_template_name, String prog_id){
		return dao.updateRolTemplateName(rol_template_name, prog_id);
	}
	
	public int updateVerSoc(String ver_soc_uuid,String prog_id){
		return dao.updateVerSoc(ver_soc_uuid, prog_id);
	}
	/**
	 * Description: 根据主键修改发布参数UUID
	 * @param publ_param_uuid
	 * @param prog_id
	 * @return
	 */
	public int updatePubUuParam(String publ_param_uuid, String prog_id){
		return dao.updatePubUuParam(publ_param_uuid, prog_id);
	}
	/**
	 * Description: 根据主键修改回退参数UUID
	 * @param roll_param_uuid
	 * @param prog_id
	 * @return
	 */
	public int updateRolUuParam(String roll_param_uuid, String prog_id){
		return dao.updateRolUuParam(roll_param_uuid, prog_id);
	}

	/** 
	 * Description: 修改发布方案扩展表
	 * @param pub_template_name
	 * @param rol_template_name
	 * @param prog_id 
	 */
	public int UpdatePgReleByKey(String pub_template_name, String rol_template_name, String prog_id) {
		return dao.UpdatePgReleByKey(pub_template_name,rol_template_name,prog_id);
	}
}