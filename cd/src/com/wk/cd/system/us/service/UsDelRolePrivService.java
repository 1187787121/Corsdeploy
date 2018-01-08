/**
 * Title: UsDelRolePrivService.java
 * File Description: ɾ�����Ž�ɫȨ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;

/**
 * Class Description: ɾ�����Ž�ɫȨ��
 * @author link
 */
public class UsDelRolePrivService {
	@Inject
	private UsRoleRsPrivDaoService daoRsService;
	@Inject
	private UsRoleSocPrivDaoService daoSocService;
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;

	/**
	 * Description: Ϊ���Ž�ɫɾ����ԴȨ��
	 * @param dprl_code
	 * @param rs_list
	 */
	public void delRsByDprlCode(String dprl_code, List<String> rs_list) {
		daoRsService.delRsByDprlCode(dprl_code, rs_list);
	}

	/**
	 * Description: Ϊ���Ž�ɫɾ������ԴȨ��
	 * @param dprl_code
	 * @param soc_list
	 */
	public void delSocByDprlCode(String dprl_code, List<String> soc_list) {
		daoSocService.delSocByDprlCode(dprl_code, soc_list);
	}

	/**
	 * Description: Ϊ���Ž�ɫɾ��SQLȨ��
	 * @param sql_list
	 */
	public void delSqlByDprlCode(List<UsRoleSqlPrivInfo> sql_list) {
		daoSqlService.delSqlByDprlCode(sql_list);
	}

	/**
	 * Description: Ϊ���Ž�ɫɾ��COLȨ��
	 * @param col_list
	 */
	public void delColByDprlCode(List<UsRoleColPrivInfo> col_list) {
		daoColService.delColByDprlCode(col_list);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ��������ԴȨ��
	 * @param dprl_code
	 */
	public void deleteAllRsByDprl(String dprl_code) {
		daoRsService.deleteAllRsByDprl(dprl_code);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ����������ԴȨ��
	 * @param dprl_code
	 */
	public void deleteAllSocByDprl(String dprl_code) {
		daoSocService.deleteAllSocByDprl(dprl_code);
	}
	/**
	 * Description: ��������Դ����ɾ�����Ž�ɫ����������ԴȨ����Ϣ
	 * @param soc_name
	 */
	public void deleteRoleSocBySocName(String soc_name) {
		daoSocService.deleteRoleSocBySocName(soc_name);
	}
	/**
	 * Description: ��������Դ����ɾ�����Ž�ɫ��������Ӧ����Դ��SQL��Ȩ����Ϣ
	 * @param soc_name
	 */
	public void deleteRoleSQLBySocName(String soc_name) {
		daoSqlService.deleteRoleSQLBySocName(soc_name);
	}
	
	/**
	 * Description: ��������Դ����ɾ�����Ž�ɫ��������Ӧ����Դ��SQL��Ȩ����Ϣ
	 * @param soc_name
	 */
	public void deleteRoleCOLBySocName(String soc_name) {
		daoColService.deleteRoleCOLBySocName(soc_name);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ������SQLȨ��
	 * @param dprl_code
	 */
	public void deleteAllSqlByDprl(String dprl_code) {
		daoSqlService.deleteAllSqlByDprl(dprl_code);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ������COLȨ��
	 * @param dprl_code
	 */
	public void deleteAllColByDprl(String dprl_code) {
		daoColService.deleteAllColByDprl(dprl_code);
	}

}
