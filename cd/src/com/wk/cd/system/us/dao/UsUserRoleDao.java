/**
 * Title: UsUserRoleDao.java
 * File Description: 用户角色关联表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.us.info.*;

/**
 * Class description:用户角色关联表
 * 
 * @author AutoGen
 */
abstract class UsUserRoleDao extends EntityDao<UsUserRoleInfo> {

	/**
	 * Description: 根据user_id查询该用户所拥有的角色编码
	 * 
	 * @param user_id
	 *            输入变量
	 * @return List<UsUserRoleInfo>
	 */
	@SqlParam(querySet = { "dprl_code" }, condition = "USER_ID=:user_id")
	abstract List<String> queryDprlByUserId(String user_id);

	/**
	 * Description: 在用户角色关联表中查询记录项是否存在，存在返回1，不存在返回0
	 * 
	 * @param dprl_code
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and USER_ID=:user_id")
	abstract int countByDprlAndUserID(String dprl_code, String user_id);

	/**
	 * 删除用户的所有部门角色
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract void deleteUserDprlByUserId(String user_id);

	/**
	 * Description: 根据部门角色编码查询用户信息的总条数
	 * 
	 * @param dprl_code
	 *            部门角色编码
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract int countUserByDprlCode(String dprl_code);

	/**
	 * 根据部门角色代码查询所属用户列表
	 * 
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(querySet = { "USER_ID" }, condition = "DPRL_CODE=:dprl_code")
	abstract List<String> listUserByDprlCode(String dprl_code);

	/**
	 * Description: 根据部门角色和用户权重查询记录记录条数
	 * 
	 * @param dprl_code
	 *            部门角色编码
	 * @param user_bk_weight
	 *            用户权重
	 * @return
	 */
	@SqlParam(condition = "USER_ID <> :user_id and DPRL_CODE=:dprl_code and USER_BK_WEIGHT = :user_bk_weight")
	abstract int countByDprlAndWeght(String user_id, String dprl_code, int user_bk_weight);

	/**
	 * Description: 根据部门角色编码查询权重值最小的用户信息
	 * 
	 * @param dprl_code1
	 *            部门角色编码
	 * @param dprl_code2
	 *            部门角色编码
	 * @param user_id
	 *            生成任务用户名
	 * @return
	 */
	@SqlParam(sql = "select * from US_USER_ROLE WHERE (DPRL_CODE= '${dprl_code1}') and USER_BK_WEIGHT = " +
			"(select min(USER_BK_WEIGHT) from US_USER_ROLE WHERE (DPRL_CODE = '${dprl_code2}') and (user_id not in ${user_ids::1==1}))", dynamic = true)
	abstract UsUserRoleInfo queryUserRoleByDprlAndMinWeght(String dprl_code1,
			String dprl_code2, String user_ids);

	/**
	 * Description: 根据部门角色编码查询部门角色信息
	 * 
	 * @param dprl_code
	 *            部门角色编码
	 * @param user_id
	 *            用户名
	 * @return
	 */
//	@SqlParam(condition = "DPRL_CODE = :dprl_code and USER_ID <> :user_id and USER_ID <> :crt_user_id", orderBy = "USER_BK_WEIGHT")
	@SqlParam(sql = "select user_id, dprl_code, user_bk_weight, backup_fld from us_user_role where DPRL_CODE = '${dprl_code}' and " +
			"USER_ID not in ${user_ids::1=1} order by USER_BK_WEIGHT", dynamic = true)
	abstract List<UsUserRoleInfo> queryUserRoleByDprlCode(String dprl_code, String user_ids);
	
	/**
	 * Description: 根据部门角色编码查询所有已使用的用户权重
	 * @param derl_code 部门角色编码
	 * @return 用户权重信息
	 */
	@SqlParam(condition = "DPRL_CODE = :dprl_code", orderBy = "user_bk_weight desc")
	abstract List<UsUserRoleInfo> queryUserRoleByDprlCode(String derl_code);

	/** 
	 * Description: 查找用户下的所属部门角色描述
	 * @param user_id
	 * @param bl_dept_id
	 * @return 
	 */
	@SqlParam(sql="select dr.bk_expl from us_dept_role dr, us_user_role ur where dr.dprl_code=ur.dprl_code and ur.user_id=:user_id and dr.dept_id=:bl_dept_id")
	abstract List<String> queryBlDrExplByUserId(String user_id, String bl_dept_id);
}