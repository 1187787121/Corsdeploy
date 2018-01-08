/**
 * Title: UsUpdRolePrivService.java
 * File Description: 部门角色权限更改
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.system.us.service;

import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;

/**
 * Class Description:部门角色权限更改
 * @author link
 */
public class UsUpdRolePrivService {
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;

	/**
	 * Description: 更改部门角色SQL操作权限
	 * @param info 输入接口
	 */
	public void updSqlByDprlCode(UsRoleSqlPrivInfo info) {
		daoSqlService.updateSqlPriv(info);
	}

	/**
	 * Description: 更改部门角色COL操作权限
	 * @param info 输入接口
	 */
	public void updColByDprlCode(UsRoleColPrivInfo info) {
		daoColService.updateColPriv(info);
	}
}
