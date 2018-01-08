/**
 * Title: UsUserRoleDaoService.java
 * File Description: �û���ɫ������
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class description:�û���ɫ������
 * @author AutoGen
 */
public class UsUserRoleDaoService {
	@Inject
	private UsUserRoleDao dao;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject 
	private UsUserDaoService usUserDaoService;

	/**
	 * ����������ѯһ����¼
	 * @param UsUserRoleInfo info
	 * @return UsUserRoleInfo
	 */
	public UsUserRoleInfo getInfoByKey(UsUserRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsUserRoleInfo info
	 * @return UsUserRoleInfo
	 */
	public UsUserRoleInfo getInfoByKeyForUpdate(UsUserRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsUserRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsUserRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsUserRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserRoleInfo> infos) {
		return dao.insert(infos);
	}

	public List<String> queryDprlByUserId(String user_id) {
		return dao.queryDprlByUserId(user_id);
	}
	
	/** 
	 * Description: �����û��µ��������Ž�ɫ����
	 * @param user_id
	 * @param bl_dept_id
	 * @return 
	 */
	public List<String> queryBlDrExplByUserId(String user_id, String bl_dept_id) {
		return dao.queryBlDrExplByUserId(user_id,bl_dept_id);
	}

	/**
	 * Description: ���ü�¼���Ƿ񲻴���
	 * 
	 * @param dprl_code
	 * @param user_id
	 */
	public void checkDprlAndUserNotExist(UsUserRoleInfo info) {
		if (dao.countByDprlAndUserID(info.getDprl_code(), info.getUser_id()) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserRoleInfo.TABLECN).addScene(
					"FIELD",
					"���Ž�ɫ"
							+ usDeptRoleDaoService.getDeptByDprl(
									info.getDprl_code()).getBk_expl()
							+ "���û�"
							+ usUserDaoService.getInfoByKey(info.getUser_id())
									.getUser_cn_name());
		}
	}

	/**
	 * Description: ���ü�¼���Ƿ����,�Լ���¼״̬�Ƿ�����
	 * 
	 * @param dprl_code
	 * @param user_id
	 */
	public void checkDprlAndUserExist(UsUserRoleInfo info) {
		if (dao.countByDprlAndUserID(info.getDprl_code(), info.getUser_id()) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserRoleInfo.TABLECN).addScene(
					"FIELD",
					"���Ž�ɫ"
							+ usDeptRoleDaoService.getDeptByDprl(
									info.getDprl_code()).getBk_expl() + "���û�"
							+ info.getUser_id());
		}
	}

	/**
	 * Description: ����ɾ���û���ɫ�������е�һ��
	 * @param user_id
	 * @param dprl_code
	 */
	public void deleteUserDprl(String user_id, String dprl_code) {
		UsUserRoleInfo info = new UsUserRoleInfo();
		info.setUser_id(user_id);
		info.setDprl_code(dprl_code);
		checkDprlAndUserExist(info);
		dao.delete(info);
	}

	/**
	 * ɾ���û������в��Ž�ɫ Description:
	 * @param user_id
	 */
	public void deleteUserDprlByUserId(String user_id) {
		dao.deleteUserDprlByUserId(user_id);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ�û���Ϣ��������
	 * 
	 * @param dprl_code
	 * @return
	 */
	public int countUserByDprlCode(String dprl_code) {
		return dao.countUserByDprlCode(dprl_code);
	}
	
	/**
	 * ���ݲ��Ž�ɫ�����ѯ�����û��б�
	 * @param dprl_code
	 * @return
	 */
	public List<String> listUserByDprlCode(String dprl_code){
		return dao.listUserByDprlCode(dprl_code);
	}

	/** 
	 * ��鲿�Ž�ɫ���Ƿ�����û�
	 * @param dprl_code
	 * @param auth_user_id
	 * @return 
	 */
	public boolean checkDprlHasUser(String dprl_code, String auth_user_id) {
		if (dao.countByDprlAndUserID(dprl_code, auth_user_id) > 0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Description: �жϷǱ��û��Ĳ��Ž�ɫ���û�Ȩ�����û����Ž�ɫ�����Ƿ��Ѿ����ڣ�
	 * ��������򱨴���֤���Ž�ɫ���û�Ȩ�޵ı��е�Ψһ��
	 * @param user_id �û���
	 * @param dprl_code ���Ž�ɫ
	 * @param user_bk_weight �û�Ȩ��
	 */
	public void checkDprlWeghtExsit(String user_id, String dprl_code, int user_bk_weight) {
		if (dao.countByDprlAndWeght(user_id, dprl_code, user_bk_weight) > 0) {
			UsDeptRoleInfo info = new UsDeptRoleInfo();
			info.setDprl_code(dprl_code);
			info = usDeptRoleDaoService.getInfoByKey(info);
			throw new RecordAlreadyExistException().addScene("TABLE",
					"�û����Ž�ɫ��us_user_role").addScene(
					"FIELD",
					"���Ž�ɫ[" + info.getBk_expl() + "]���û�Ȩ��[" + user_bk_weight
							+ "]");
		}
	}
	
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯȨ��ֵ��С���û���Ϣ
	 * @param dprl_code ���Ž�ɫ����
	 * @param user_ids ������������Ȩ���û���Ϣ
	 * @return
	 */
	public UsUserRoleInfo queryUserRoleByDprlAndMinWeght(String dprl_code, String user_ids){
		return dao.queryUserRoleByDprlAndMinWeght(dprl_code, dprl_code, user_ids);
	}
	
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ���Ž�ɫ��Ϣ
	 * @param dprl_code ���Ž�ɫ����
	 * @param user_ids �û���
	 * @return
	 */
	public List<UsUserRoleInfo> queryUserRoleByDprlCode(String dprl_code, String user_ids){
		return dao.queryUserRoleByDprlCode(dprl_code, user_ids);
	}
	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ������ʹ�õ��û�Ȩ��
	 * @param derl_code ���Ž�ɫ����
	 * @return 
	 */
	public List<UsUserRoleInfo> queryUserRoleByDprlCode(String dprl_code){
		return dao.queryUserRoleByDprlCode(dprl_code); 
	}
}