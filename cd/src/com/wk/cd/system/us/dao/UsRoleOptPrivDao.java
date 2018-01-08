/**
 * Title: UsRoleOptPrivDao.java
 * File Description: 部门角色操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.us.dao;

import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门角色操作权限配置表
 * @author AutoGen
 */
abstract class UsRoleOptPrivDao extends EntityDao<UsRoleOptPrivInfo> {

	/** 
	 * Description: 根据部门角色编码查询部门角色操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="dprl_code=:dept_id and priv_flag=2")
	abstract DBIterator<UsRoleOptPrivInfo> queryOptForbidPrivByDprl(String dprl_code);

	/** 
	 * Description: 根据部门角色编码查询部门角色操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="dprl_code=:dept_id and priv_flag=1")
	abstract DBIterator<UsRoleOptPrivInfo> queryOptAllowPrivByDprl(String dprl_code);

	/** 
	 * Description: 根据部门角色编码查询部门角色操作权限信息列表
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="dprl_code=:dprl_code")
	abstract DBIterator<UsRoleOptPrivInfo> queryOptPrivByDprl(String dprl_code);

	/** 
	 * Description: 根据部门角色编码删除操作权限
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="dprl_code=:dprl_code")
	abstract int deleteOptPrivByDprl(String dprl_code);
}