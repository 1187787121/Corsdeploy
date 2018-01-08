/**
 * Title: UsGetUserInfoService.java
 * File Description: 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author link
 */
public class UsGetUserInfoService {

	@Inject
	private UsUserCombineDaoService daoGetUserInfoService;
	@Inject
	private UsDeptRoleDaoService daoDeptRoleSrv;
	@Inject
	private UsUserRoleDaoService daoUserRoleSrv;
	@Inject
	private UsUserDaoService usUserDaoService;
	

	/**
	 * Description: �����û�����ѯ�û��Ľ�ɫ����
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryRoleTypeByUserId(String user_id) {
		List<Integer> user_role_typelist = new ArrayList<Integer>();
		user_role_typelist = daoGetUserInfoService
				.queryRoleTypeByUserId(user_id);
		return user_role_typelist;

	}
	
	/**
	 * Description: ���ݲ��ű���ͽ�ɫ�����ѯ���Ž�ɫ����
	 * @param dept_id ���ű���
	 * @param role_code ��ɫ����
	 * @return ���Ž�ɫ����
	 */
	public String queryDprlByDeptAndRole(String dept_id, String role_code){
		return daoDeptRoleSrv.queryDprlByDeptAndRole(dept_id, role_code);
	}
	
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯȨ��ֵ��С���û���Ϣ
	 * @param dprl_code ���Ž�ɫ����
	 * @param user_ids ������������Ȩ���û���Ϣ
	 * @return
	 */
	public UsUserRoleInfo queryUserRoleByDprlAndMinWeght(String dprl_code, List<String> deal_user_ids){
		String user_ids = listToString(deal_user_ids);
		return daoUserRoleSrv.queryUserRoleByDprlAndMinWeght(dprl_code, user_ids);
	}
	
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ���Ž�ɫ��Ϣ
	 * @param dprl_code ���Ž�ɫ����
	 * @param List<String> deal_user_ids ���������˵���Ա��Ϣ
	 * @return
	 */
	public List<UsUserRoleInfo> queryUserByDprlCode(String dprl_code, List<String> deal_user_ids){
		String user_ids = listToString(deal_user_ids);
		return daoUserRoleSrv.queryUserRoleByDprlCode(dprl_code, user_ids);
	}
	
	/**
	 * ��������б�ת��Ϊ�ַ����������б���Ϊa b c��ת������Ϊ('a','b','c')
	 * @param list �����б�
	 * @return �ַ���
	 */
	private String listToString(List<String> list) {
		String str = "";
		// ����Ĳ��Ž�ɫ�б���ϢΪ�ձ���
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "�����б���Ϣ");
		}
		for (String s : list) {
			str += "'" + s + "',";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return str;
	}
	
	/**
	 * Description: �����û�����ѯ�û���Ϣ
	 * @param user_id
	 * @return
	 */
	public UsUserInfo getUserInfoByUserId(String user_id) {
		return usUserDaoService.getInfoByKey(user_id);
	}
	
	/** 
	 * Description: �����û�����ѯ�û�������
	 * @param user_id �û�ID
	 * @return �û�������
	 */
	public String getUserCnNameByUserId(String user_id) {
		return usUserDaoService.getUserCnNameByUserId(user_id);
	}
}
