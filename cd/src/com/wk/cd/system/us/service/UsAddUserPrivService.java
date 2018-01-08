/**
 * Title: UsAddUserPrivService.java
 * File Description: ����û�Ȩ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.lang.Inject;

/**
 * Class Description:����û�Ȩ��
 * @author link
 */
public class UsAddUserPrivService {

	@Inject
	private UsUserRsPrivDaoService daoRsService;
	@Inject
	private UsUserSocPrivDaoService daoSocService;
	@Inject
	private UsUserSqlPrivDaoService daoSqlService;
	@Inject
	private UsUserColPrivDaoService daoColService;
	
	/**
	 * Description: �û������ԴȨ��
	 * @param rs_list
	 */
	public void addUserRs(List<UsUserRsPrivInfo> rs_list) {
		daoRsService.addUserRs(rs_list);
	}

	/**
	 * Description: �û��������ԴȨ��
	 * @param rs_list
	 */
	public void addUserSoc(List<UsUserSocPrivInfo> soc_list) {
		daoSocService.addUserSoc(soc_list);
	}

	/**
	 * Description: �û����SQLȨ��
	 * @param rs_list
	 */
	public void addUserSql(List<UsUserSqlPrivInfo> sql_list) {
		daoSqlService.addUserSql(sql_list);
	}

	/**
	 * Description: �û����COLȨ��
	 * @param rs_list
	 */
	public void addUserCol(List<UsUserColPrivInfo> col_list) {
		daoColService.addUserCol(col_list);
	}

}
