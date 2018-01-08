/**
 * Title: UsRoleEnvPrivDao.java
 * File Description: 部门角色应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */
package com.wk.cd.system.ep.dao;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.info.UsRoleEnvPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门角色应用环境权限表
 * @author AutoGen
 */
abstract class UsRoleEnvPrivDao
		extends EntityDao<UsRoleEnvPrivInfo> {

	/**
	 * Description: 根据部门角色查询环境权限
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(querySet = { "env_name" }, condition = "dprl_code=:dprl_code")
	abstract DBIterator<String> iteratorEnvPrivByDprl(String dprl_code);

	/**
	 * Description: 查询部门角色应用环境权限列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "select ce.env_name,ce.env_cn_name,cs.sys_name,cs.sys_cn_name　from us_role_env_priv ep,ce_environment ce,ce_system cs where ep.env_name=ce.env_name and ep.sys_name=cs.sys_name and dprl_code=:dprl_code")
	abstract DBIterator<EnvPrivBean> queryDprlEnvPriv(String dprl_code);

	/**
	 * Description: 删除部门角色应用环境权限
	 * @param dprl_code
	 */
	@SqlParam(condition = "dprl_code=:dprl_code")
	abstract void deleteDprlEnvPriv(String dprl_code);

	/**
	 * Description: 根据用户查询部门角色权限个数
	 * @param user_id
	 * @param env_name
	 * @return
	 */
	@SqlParam(sql = "select count(*) from us_user_role ur,us_role_env_priv ep where ur.dprl_code=ep.dprl_code and ur.user_id=:user_id and ep.env_name=:env_name")
	abstract int countDprlEnvPrivByUser(String user_id, String env_name);
}