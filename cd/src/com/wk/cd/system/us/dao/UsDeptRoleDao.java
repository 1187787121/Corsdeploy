/**
 * Title: UsDeptRoleDao.java
 * File Description: 部门角色关联表
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
 * Class description:部门角色关联表
 * @author AutoGen
 */
abstract class UsDeptRoleDao
		extends EntityDao<UsDeptRoleInfo> {

	/**
	 * Description: 根据角色编码查询该角色所属的部门
	 * @param role_code 输入角色编码
	 * @return 部门角色关联表的实体列表
	 */
	@SqlParam(condition = "role_code =:role_code")
	abstract List<UsDeptRoleInfo> pageDeptByRole(String role_code,
			int start_rcd, int limited_rcd);

	/**
	 * Description: 根据角色编码分页查询到的部门总条数
	 * @param role_code
	 * @return
	 */
	@SqlParam(condition = "role_code =:role_code")
	abstract int countDeptByRole(String role_code);

	/**
	 * Description: 根据部门编码查询该部门所拥有的角色
	 * @param dept_id 输入部门编码
	 * @return 部门角色关联表的实体列表
	 */
	@SqlParam(condition = "dept_id =:dept_id")
	abstract List<UsDeptRoleInfo> queryRoleByDept(String dept_id);

	/**
	 * Description: 根据部门查询角色信息的总条数
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition = "dept_id =:dept_id")
	abstract int countRoleByDept(String dept_id);

	/**
	 * Description: 根据部门角色编码查询该部门角色所属的部门
	 * @param dprl_code 输入部门角色编码
	 * @return 部门角色关联表的实体列表
	 */
	@SqlParam(condition = "PK")
	abstract UsDeptRoleInfo getDeptByDprl(String dprl_code);

	/**
	 * Description: 根据主键查询记录是否存在，存在返回 1， 不存在返回 0
	 * @param dprl_code
	 */
	@SqlParam(condition = "PK")
	abstract int countByDprl(String dprl_code);

	/**
	 * Description: 查询全部部门角色列表
	 * @return
	 */
	@SqlParam(querySet = { "DEPT_ID", "DPRL_CODE", "BK_EXPL" }, condition = "1=1")
	abstract DBIterator<UsDeptRoleInfo> queryDprl();

	/**
	 * Description:查询部门角色总数
	 * @return
	 */
	@SqlParam(condition = "1=1")
	abstract int countAllDprl();

	/**
	 * Description: 更新部门角色说明信息
	 * @param dprl_code
	 */
	@SqlParam(updateSet = { "bk_expl" }, condition = "PK")
	abstract void updateBkExplByDprl(String bk_expl, String dprl_code);

	/**
	 * Description: 查询所有部门角色对应的角色编码
	 * @param dprl_str 部门角色列表
	 * @return 角色编码
	 */
	@SqlParam(sql = "select role_code from us_dept_role where dprl_code in ${dprl_str::1=0}", dynamic = true)
	abstract List<String> queryRoleCode(String dprl_str);
	
	/**
	 * Description: 根据部门编码和角色编码查询部门角色编码
	 * @param dept_id 部门编码
	 * @param role_code 角色编码
	 * @return 部门角色编码
	 */
	@SqlParam(querySet = { "DPRL_CODE" }, condition = "DEPT_ID = :dept_id and ROLE_CODE = :role_code")
	abstract List<String> queryDprlByDeptAndRole(String dept_id, String role_code);

	/** 
	 * Description: 根据部门角色编码查询角色类型
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select role_type from us_dept_role dr,us_role rl where dr.role_code=rl.role_code and dr.dprl_code=:dprl_code")
	abstract Integer getRoleTypeByDprl(String dprl_code);
}