/**
 * Title: DpDeptEnvPrivDao.java
 * File Description: 部门应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */
package com.wk.cd.system.ep.dao;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.info.DpDeptEnvPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门应用环境权限表
 * @author AutoGen
 */
abstract class DpDeptEnvPrivDao
		extends EntityDao<DpDeptEnvPrivInfo> {

	/**
	 * Description: 查询部门角色列表所拥有的应用环境权限
	 * @param dept_id
	 * @return
	 */
	@SqlParam(querySet = { "env_name" }, condition = "dept_id=:dept_id")
	abstract DBIterator<String> iteratorDeptEnvPriv(String dept_id);

	/**
	 * Description: 删除部门应用环境权限
	 * @param dept_id
	 */
	@SqlParam(condition = "dept_id=:dept_id")
	abstract void deleteDeptEnvPriv(String dept_id);

	/**
	 * Description: 查询部门应用环境权限列表
	 * @param dept_id
	 * @return
	 */
	@SqlParam(sql="select ce.env_name,ce.env_cn_name,cs.sys_name,cs.sys_cn_name　from  dp_dept_env_priv ep,ce_environment ce,ce_system cs where ep.env_name=ce.env_name and ep.sys_name=cs.sys_name and dept_id=:dept_id")
	abstract DBIterator<EnvPrivBean> queryDeptEnvPriv(String dept_id);
}