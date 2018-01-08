/**
 * Title: UsRoleDaoService.java
 * File Description: ��ɫ�ű�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.system.us.info.UsRoleInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:��ɫ�ű�
 * @author AutoGen
 */
public class UsRoleDaoService {
	@Inject
	private UsRoleDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param UsRoleInfo info
	 * @return UsRoleInfo
	 */
	public UsRoleInfo getInfoByKey(UsRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsRoleInfo info
	 * @return UsRoleInfo
	 */
	public UsRoleInfo getInfoByKeyForUpdate(UsRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ����ɫ��Ϣ�����Ƿ��Ѿ����ڸý�ɫ��������
	 * @param role_cn_name ��ɫ��������
	 */
	public void checkNotExistRoleCnName(String role_cn_name) {
		if (dao.countByRoleCnName(role_cn_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_cn_name);
		}
	}

	/**
	 * Description: ���Ҫɾ���Ľ�ɫ�Ľ�ɫ�����Ƿ�����Լ��ý�ɫ��״̬�Ƿ�������
	 * @param role_code
	 */
	public void checkExistRoleCode(String role_code) {
		UsRoleInfo info = new UsRoleInfo();
		info.setRole_code(role_code);
		if (dao.countByRoleCode(role_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_code);
		} else if (getInfoByKey(info).getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", getInfoByKey(info).getRole_cn_name()+ "[" + role_code + "]");
		}

	}

	/**
	 * �ӽ�ɫ��Ϣ���в�ѯ�����н�ɫ�����б����ʽ����
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleInfo> pageRole(int start_rcd, int limited_rcd) {
		return dao.pageRole(start_rcd, limited_rcd);
	}

	public int countRole() {
		return dao.countRole();
	}

	/**
	 * ���������޸Ľ�ɫ��Ϣ�����磺�û��������ơ��û����͡��û�˵����
	 * @param role_cn_name
	 * @param role_type
	 * @param role_bk_desc
	 * @param role_code
	 */
	public void updateRoleByKey(String role_cn_name, int role_type,
			String role_bk_desc, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String role_code) {
		dao.updateRoleByKey(role_cn_name, role_type, role_bk_desc,
				modify_bk_date, modify_bk_time, modify_user_id, role_code);
	}

	/**
	 * ��������ɾ����ɫ��Ϣ�����Ľ�ɫ�ļ�¼״̬������-->ɾ����
	 * @param rcd_state
	 * @param role_code
	 */
	public void deleteRoleByKey(int rcd_state, JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, String role_code) {
		dao.updateRoleStateByKey(rcd_state, del_bk_date, del_bk_time,
				del_user_id, role_code);
	}

	/**
	 * Description: ģ����ѯ��ɫ���ơ�˵��ģ����ѯ��ɫ��Ϣ
	 * @param role_cn_name
	 * @param role_bk_desc
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleInfo> pageRoleByRoleCnName(String role_cn_name,
			int start_rcd, int limited_rcd) {
		List<UsRoleInfo> role_list = new ArrayList<UsRoleInfo>();
		String role_bk_desc = role_cn_name;
		role_list = dao.pageRoleByRoleCnName(role_cn_name, role_bk_desc,
				start_rcd, limited_rcd);
		return role_list;
	}

	/**
	 * Description: ���ݽ�ɫ����ģ����ѯ��ɫ��Ϣ����
	 * @param role_cn_name
	 * @return
	 */
	public int countRoleByRoleCnName(String role_cn_name) {
		return dao.countRoleByRoleCnName(role_cn_name, role_cn_name);
	}

	/**
	 * Description: ��ѯ��ɫ����ϸ��Ϣ
	 * @param role_code
	 * @return
	 */
	public UsRoleInfo queryRole(String role_code) {
		return dao.queryRole(role_code);
	}

	/**
	 * Description: ��ѯ��ɫʵ���б�
	 * @return
	 */
	public List<UsRoleInfo> queryAllRole() {
		return dao.queryAllRole();
	}

	/**
	 * Description: ��ѯ��ɫ�Ƿ����
	 * @return
	 */
	public void checkRoleExist(String role_code){
		if(dao.queryRole(role_code) == null){
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_code);
		}
	}
}