/**
 * Title: UsUserEnvPrivDao.java
 * File Description: 用户应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */
package com.wk.cd.system.ep.dao;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:用户应用环境权限表
 * @author AutoGen
 */
abstract class UsUserEnvPrivDao
		extends EntityDao<UsUserEnvPrivInfo> {

	/**
	 * Description: 根据用户ID列表获得用户应用环境列表
	 * @param user_id
	 * @return
	 */
	@SqlParam(querySet = { "env_name" }, condition = "user_id=:user_id and af_flag = 1 and priv_type=1")
	abstract DBIterator<String> iteratorUserEnvPriv(String user_id);

	/**
	 * Description: 查询用户永久应用环境权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "select ep.af_flag,ep.env_name,ep.sys_name from us_user_env_priv ep where user_id=:user_id and priv_type=1")
	abstract DBIterator<EnvPrivBean> queryIteratorUsEnvAf(String user_id);

	/**
	 * Description:根据用户查询部门角色应用环境权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "select distinct ep.env_name ,ep.sys_name,cs.sys_cn_name,ce.env_cn_name from us_user_role ur,us_role_env_priv ep,ce_environment ce,ce_system cs where ur.dprl_code=ep.dprl_code and ep.env_name=ce.env_name and ep.sys_name=cs.sys_name and ur.user_id=:user_id")
	abstract DBIterator<EnvPrivBean> iteratorDprlEnvPrivByUser(String user_id);

	/**
	 * Description: 删除用户应用环境权限
	 * @param user_id
	 */
	@SqlParam(condition = "user_id=:user_id")
	abstract void deleteUserEnvPriv(String user_id);
}