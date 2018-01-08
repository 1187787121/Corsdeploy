/**
 * Title: UsRoleDao.java
 * File Description: 角色信表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
import com.wk.cd.system.us.info.*;

/**
 * Class description:角色信表
 * @author AutoGen
 */
abstract class UsRoleDao
		extends EntityDao<UsRoleInfo> {

	/**
	 * 分页查询角色列表
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	@SqlParam(condition = "1=1 and RCD_STATE=1")
	abstract List<UsRoleInfo> pageRole(int start_rcd, int limited_rcd);

	/**
	 * Description: 查询角色实体列表
	 * @return
	 */
	@SqlParam(condition = "1=1 and RCD_STATE=1")
	abstract List<UsRoleInfo> queryAllRole();

	/**
	 * Description: 分页查询的总条数
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	@SqlParam(condition = "1=1 and RCD_STATE=1")
	abstract int countRole();

	/**
	 * Description: 查询角色的详细信息
	 * @param role_code
	 * @return
	 */
	@SqlParam(condition = "ROLE_CODE=:role_code and RCD_STATE=1")
	abstract UsRoleInfo queryRole(String role_code);

	/**
	 * 根据主键修改角色信息，例如：用户中文名称、用户类型、用户说明。
	 * @param role_cn_name
	 * @param role_type
	 * @param role_bk_desc
	 * @param role_code
	 */
	@SqlParam(updateSet = { "ROLE_CN_NAME", "ROLE_TYPE", "ROLE_BK_DESC",
			"MODIFY_BK_DATE", "MODIFY_BK_TIME", "MODIFY_USER_ID" }, condition = "ROLE_CODE=:role_code")
	abstract void updateRoleByKey(String role_cn_name, int role_type,
			String role_bk_desc, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String role_code);

	/**
	 * 根据主键删除角色信息（更改角色的记录状态：正常-->删除）
	 * @param rcd_state
	 * @param role_code
	 */
	@SqlParam(updateSet = { "RCD_STATE", "DEL_BK_DATE", "DEL_BK_TIME",
			"DEL_USER_ID" }, condition = "ROLE_CODE=:role_code")
	abstract void updateRoleStateByKey(int rcd_state, JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, String role_code);

	/**
	 * Description: 查询角色信息表中是否存在相同的角色中为名称
	 * @param role_cn_name
	 * @return
	 */
	@SqlParam(condition = "role_cn_name=:role_cn_name ")
	abstract int countByRoleCnName(String role_cn_name);

	/**
	 * Description: 查询角色信息表中是否存在此role_code项
	 * @param role_code
	 * @return
	 */
	@SqlParam(condition = "role_code=:role_code")
	abstract int countByRoleCode(String role_code);

	/**
	 * Description: 根据角色名称模糊查询角色列表
	 * @param role_cn_name
	 * @param role_bk_desc
	 * @return
	 */
	@SqlParam(sql = "SELECT ROLE_CODE,ROLE_CN_NAME,ROLE_TYPE,ROLE_BK_DESC FROM us_role "
			+ "WHERE "
			+ "RCD_STATE=1 and ((ROLE_CN_NAME like '%${role_cn_name}%') OR (ROLE_BK_DESC LIKE '%${role_bk_desc}%'))", dynamic = true)
	abstract List<UsRoleInfo> pageRoleByRoleCnName(String role_cn_name,
			String role_bk_desc, int start_rcd, int limited_rcd);

	/**
	 * Description: 根据角色名称模糊查询角色信息条数
	 * @param role_cn_name
	 * @param role_bk_desc
	 * @return
	 */
	@SqlParam(sql = "SELECT count(*) FROM us_role WHERE "
			+ "RCD_STATE=1 and ((ROLE_CN_NAME like '%${role_cn_name}%') OR (ROLE_BK_DESC LIKE '%${role_bk_desc}%'))", dynamic = true)
	abstract int countRoleByRoleCnName(String role_cn_name, String role_bk_desc);

}