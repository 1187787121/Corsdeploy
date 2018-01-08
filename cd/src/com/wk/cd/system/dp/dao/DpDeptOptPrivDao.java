/**
 * Title: DpDeptOptPrivDao.java
 * File Description: 部门操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.dp.dao;

import com.wk.db.*;
import com.wk.cd.system.dp.info.*;

/**
 * Class description:部门操作权限配置表
 * @author AutoGen
 */
abstract class DpDeptOptPrivDao extends EntityDao<DpDeptOptPrivInfo> {

	/** 
	 * Description: 根据部门编码查询部门操作权限信息列表
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptPrivByDept(String dept_id);

	/** 
	 * Description: 根据部门编码查询部门操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id and priv_flag=2")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptForbidPrivByDept(String dept_id);

	/** 
	 * Description: 根据部门编码查询部门操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id and priv_flag=1")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptAllowPrivByDept(String dept_id);

	/** 
	 * Description: 根据部门ID删除部门操作权限
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id")
	abstract int deleteDeptOptPrivByDpetId(String dept_id);
}