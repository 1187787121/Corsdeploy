/**
 * Title: UsUpdRolePrivService.java
 * File Description: ���Ž�ɫȨ�޸���
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
 * Class Description:���Ž�ɫȨ�޸���
 * @author link
 */
public class UsUpdRolePrivService {
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;

	/**
	 * Description: ���Ĳ��Ž�ɫSQL����Ȩ��
	 * @param info ����ӿ�
	 */
	public void updSqlByDprlCode(UsRoleSqlPrivInfo info) {
		daoSqlService.updateSqlPriv(info);
	}

	/**
	 * Description: ���Ĳ��Ž�ɫCOL����Ȩ��
	 * @param info ����ӿ�
	 */
	public void updColByDprlCode(UsRoleColPrivInfo info) {
		daoColService.updateColPriv(info);
	}
}
