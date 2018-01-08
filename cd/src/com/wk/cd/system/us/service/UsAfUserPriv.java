/**
 * Title: UsAfUserPriv.java
 * File Description:  ��ѯ�û�����ֹ��Ȩ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.dao.UsUserPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.lang.Inject;

/**
 * Class Description: ��ѯ�û�����ֹ��Ȩ��
 * @author link
 */
public class UsAfUserPriv {
	@Inject
	private UsUserRsPrivDaoService daoAfRsService;
	@Inject
	private UsUserSocPrivDaoService daoAfSocService;
	@Inject
	private UsUserPrivDaoService daoAfSqlColService;

	/**
	 * Description: ��ѯ�û�����ֹ������ԴȨ��
	 * @param user_id
	 * @return
	 */
	public List<String> queryUserAfSocPriv(String user_id) {
		return daoAfSocService.queryUserAfSocPriv(user_id);
	}

	/**
	 * Description: ��ѯ�û�����ֹ��SQL����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfSqlPriv(String user_id) {
		return daoAfSqlColService.queryUserAfSqlPriv(user_id);
	}

	/**
	 * Description: ��ѯ�û�����ֹ��COL����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfColPriv(String user_id) {
		return daoAfSqlColService.queryUserAfColPriv(user_id);
	}

}
