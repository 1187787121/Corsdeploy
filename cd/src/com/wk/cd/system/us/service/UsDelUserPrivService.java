/**
 * Title: UsDelUserPrivService.java
 * File Description: ɾ���û�Ȩ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.lang.Inject;

/**
 * Class Description:ɾ���û�Ȩ��
 * @author link
 */
public class UsDelUserPrivService {
	@Inject
	private UsUserRsPrivDaoService daoRsService;
	@Inject
	private UsUserSocPrivDaoService daoSocService;
	@Inject
	private UsUserSqlPrivDaoService daoSqlService;
	@Inject
	private UsUserColPrivDaoService daoColService;

	/**
	 * Description: ɾ���û���������ԴȨ��
	 * @param user_id
	 */
	public void deleteAllRsByUserId(String user_id) {
		daoRsService.deleteAllRsByUserId(user_id);
	}

	/**
	 * Description: ɾ���û�������ԴȨ��
	 * @param user_id
	 * @param soc_list
	 */
	public void delUserSocPriv(String user_id, List<String> soc_list) {
		daoSocService.deleteUserSocPriv(user_id, soc_list);
	}

	/**
	 * Description: ɾ���û�����������ԴȨ��
	 * @param user_id
	 */
	public void deleteAllSocByUserId(String user_id) {
		daoSocService.deleteAllSocByUserId(user_id);
	}

	/**
	 * Description: ɾ���û���SQLȨ��
	 * @param sql_list
	 */
	public void delUserSqlPriv(List<UsUserPrivBean> sql_list) {
		daoSqlService.deleteUserSqlPriv(sql_list);
	}

	/**
	 * Description: ɾ���û�������SQLȨ��
	 * @param user_id
	 */
	public void deleteAllSqlByUserId(String user_id) {
		daoSqlService.deleteAllSqlByUserId(user_id);
	}

	/**
	 * Description: ɾ���û���COLȨ��
	 * @param col_list
	 */
	public void delUserColPriv(List<UsUserPrivBean> col_list) {
		daoColService.deleteUserColPriv(col_list);
	}

	/**
	 * Description: ɾ���û�������COLȨ��
	 * @param user_id
	 */
	public void deleteAllColByUserId(String user_id) {
		daoColService.deleteAllColByUserId(user_id);
	}
	
	/**
	 * Description: ɾ���û�����Դ��Ϣ
	 * @param soc_name
	 */
	public int deleteUserSocBySocName(String soc_name) {
		return daoSocService.deleteUserSocBySocName(soc_name);
	}
	
	/**
	 * Description: ɾ���û�����ԴSQL��Ϣ
	 * @param soc_name
	 */
	public int deleteUserSQLBySocName(String soc_name) {
		return daoSqlService.deleteUserSQLBySocName(soc_name);
	}
	
	/**
	 * Description: ɾ���û�����ԴCOL��Ϣ
	 * @param soc_name
	 */
	public int deleteUserCOLBySocName(String soc_name) {
		return daoColService.deleteUserCOLBySocName(soc_name);
	}
}
