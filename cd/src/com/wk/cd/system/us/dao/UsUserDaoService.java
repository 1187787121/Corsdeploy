/**
 * Title: UsUserDaoService.java
 * File Description: �û���
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.exc.UserIdOrPasswdErrorException;
import com.wk.cd.system.exc.UserNotLoginException;
import com.wk.cd.system.exc.UserStateAbnormalException;
import com.wk.cd.system.us.bean.UserAppBean;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class description:�û���
 * 
 * @author AutoGen
 */
public class UsUserDaoService {
	@Inject
	private UsUserDao dao;

	/**
	 * ����������ѯһ����¼
	 * 
	 * @param UsUserInfo
	 *            info
	 * @return UsUserInfo
	 */
	public UsUserInfo getInfoByKey(UsUserInfo info) {
		return dao.get(info);
	}

	public UsUserInfo getInfoByKey(String user_id){
		return dao.get(user_id);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * 
	 * @param UsUserInfo
	 *            info
	 * @return UsUserInfo
	 */
	public UsUserInfo getInfoByKeyForUpdate(UsUserInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * 
	 * @param String
	 *            user_id
	 * @return UsUserInfo
	 */
	public UsUserInfo getInfoByKeyForUpdate(String user_id) {
		return dao.getForUpdate(user_id);
	}

	/**
	 * ����һ����¼
	 * 
	 * @param UsUserInfo
	 *            info
	 * @return int
	 */
	public int insertInfo(UsUserInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * 
	 * @param List
	 *            <UsUserInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description:��ձ�
	 */
	public void truncateTable() {
		dao.truncateTable();
	}
	
	/**
	 * Description: ����¼�Ƿ񲻴���
	 * 
	 * @param user_id
	 */
	public void checkNotExistUserIdExist(String user_id) {
		if (dao.countByUserId(user_id) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("FIELD", user_id);
		}
	}

	/**
	 * Description: ����¼�Ƿ����
	 * 
	 * @param user_id
	 */
	public void checkExistUserIdExist(String user_id) {
		UsUserInfo info = new UsUserInfo();
		info.setUser_id(user_id);
		info = getInfoByKey(info);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("FIELD", user_id);
		} else if (info.getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new UserStateAbnormalException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("FIELD", info.getUser_cn_name() + "[" + user_id + "]");
		}
	}

	/**
	 * Description: �����û���Ϣ
	 * 
	 * @param info
	 */
	public void updateUserByKey(UsUserInfo info) {
		// dao.update(info);
		dao.updateUserByKey(info.getUser_cn_name(), info.getEmail_add(), info
				.getPhone_no(), info.getBl_dept_id(), info.getUser_type()
				.getValue(), info.getTeller_no(), info.getFirst_dept_id(), info
				.getSecd_dept_id(), info.getThird_dept_id(), info
				.getModify_bk_date(), info.getModify_bk_time(), info
				.getModify_user_id(), info.getUser_id());
	}

	/**
	 * Description: ͨ�������û���¼״̬ɾ��һ���û���Ϣ
	 * 
	 * @param rcd_state
	 * @param user_id
	 */
	public void deleteUserByKey(int rcd_state, String user_id) {
		checkExistUserIdExist(user_id);
		dao.updateUserStateByKey(rcd_state, user_id);
	}

	/**
	 * Description: ����ɾ���û�
	 * 
	 * @param user_id
	 */
	public void deleteUser(String user_id) {
		checkExistUserIdExist(user_id);
		UsUserInfo info = new UsUserInfo();
		info.setUser_id(user_id);
		dao.delete(info);
	}

	/**
	 * Description: ���ݲ��ű����ҳ��ѯ�û�
	 * 
	 * @param bl_dept_id
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsUserInfo> pageUserByBlDeptId(String bl_dept_id,
			int statr_rcd, int limited_rcd) {
		List<UsUserInfo> user_list = new ArrayList<UsUserInfo>();
		user_list = dao.pageUserByBlDeptId(bl_dept_id, statr_rcd, limited_rcd);
		return user_list;
	}

	/**
	 * Description: ���ݲ��Ų�ѯ�û���Ϣ��������
	 * 
	 * @param bl_dept_id
	 * @return
	 */
	public int countUserByBlDeptId(String bl_dept_id) {
		return dao.countUserByBlDeptId(bl_dept_id);
	}

	/**
	 * Description: �����������źź��û����Ͳ�ѯ�û�
	 * 
	 * @param bl_dept_id
	 * @param user_type
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsUserInfo> pageUserByDeptAndType(String bl_dept_id,
			int user_type, int statr_rcd, int limited_rcd) {
		List<UsUserInfo> user_list = new ArrayList<UsUserInfo>();
		user_list = dao.pageUserByDeptAndType(bl_dept_id, user_type, statr_rcd,
				limited_rcd);
		return user_list;
	}

	/**
	 * Description: �����������ź��û����Ͳ�ѯ�����û�����
	 * 
	 * @param bl_dept_id
	 * @param user_type
	 * @return
	 */
	public int countUserByDeptAndType(String bl_dept_id, int user_type) {
		return dao.countUserByDeptAndType(bl_dept_id, user_type);
	}

	/**
	 * Description: �����û�����ѯ�û�����ϸ��Ϣ
	 * 
	 * @param user_id
	 * @return
	 */
	public UsUserInfo queryDetailByUserId(String user_id) {
		checkExistUserIdExist(user_id);
		return dao.queryDetailByUserId(user_id);
	}

	/**
	 * Description: ��ѯ�û��ļ�¼״̬
	 * 
	 * @param user_id
	 * @return
	 */
	public void checkUserState(String user_id) {
		checkExistUserIdExist(user_id);
		UsUserInfo info = new UsUserInfo();
		info = dao.queryLoginAndState(user_id);
		if (info.getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new UserStateAbnormalException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("FIELD", user_id);
		}
	}

	/**
	 * Description: ��ѯ�û��ĵ�¼״̬
	 * 
	 * @param user_id
	 */
	public void checkUserLoginNum(String user_id) {
		UsUserInfo info = new UsUserInfo();
		info = dao.queryLoginAndState(user_id);
		if(info == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("FIELD", user_id);
		} else if (info.getLogin_bk_num() <= 0) {
			throw new UserNotLoginException().addScene("USER_ID", info.getUser_cn_name() + "[" + user_id + "]");
		}else if (info.getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new UserStateAbnormalException().addScene("TABLE",
					UsUserInfo.TABLECN).addScene("USER_ID", info.getUser_cn_name() + "[" + user_id + "]");
		}
	}

	/**
	 * Description: ����û������Ƿ��ǳ�ʼ���룬�Ƿ���true
	 * 
	 * @param user_id
	 * @return
	 */
	public boolean isDefinePwd(String user_id) {
		String pwd = DESUtil.docrypt(null, CfgTool
				.getProjectPropterty("cms.reset.pwd"));
		int c = dao.countByPwd(user_id, pwd);
		if (c >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Description: ����û��������Ƿ���ȷ
	 * 
	 * @param user_id
	 * @param user_passwd
	 */
	public void checkUserPwd(String user_id, String user_passwd) {
		if (dao.countByPwd(user_id, user_passwd) == 0) {
			throw new UserIdOrPasswdErrorException();
		}
	}

	/**
	 * Description: �û������޸�
	 * 
	 * @param user_passwd
	 * @param user_id
	 */
	public void updateUserPasswdByKey(String user_passwd, JaDate pwdexp_date,
			String user_id) {
		dao.updateUserPasswdByKey(user_passwd, pwdexp_date, user_id);
	}

	/**
	 * Description: �����û��ĵ�¼״̬
	 * 
	 * @param login_bk_num
	 * @param user_id
	 */
	public void updateLoginNumByUserId(int login_bk_num, String user_id) {
		checkExistUserIdExist(user_id);
		dao.updateLoginNumByUserId(login_bk_num, user_id);
	}

	/**
	 * Description: ��ȡ�û��ĵ�ǰ��¼״ֵ̬
	 * 
	 * @param user_id
	 * @return
	 */
	public int getUserLoginBkNum(String user_id) {
		// checkExistUserIdExist(user_id);
		UsUserInfo info = dao.queryLoginAndState(user_id);
		int login_bk_num = info.getLogin_bk_num();
		return login_bk_num;
	}

	/**
	 * Description: �����û�����ѯ�����Ĳ����Լ���ְ����
	 * 
	 * @param user_id
	 * @return
	 */
	public List<String> queryDeptByUserId(String user_id) {
		List<String> dept_list = new ArrayList<String>();
		UsUserInfo info = new UsUserInfo();
		info = dao.queryDetailByUserId(user_id);
		dept_list.add(info.getBl_dept_id());
		if (!Assert.isEmpty(info.getFirst_dept_id())) {
			dept_list.add(info.getFirst_dept_id());
		} else if (!Assert.isEmpty(info.getSecd_dept_id())) {
			dept_list.add(info.getSecd_dept_id());
		} else if (!Assert.isEmpty(info.getThird_dept_id())) {
			dept_list.add(info.getThird_dept_id());
		}
		return dept_list;
	}
	
	/**
	 * Description: ���ݲ��Ų�ѯ�û�����Ϣ
	 * 
	 * @param bl_dept_id
	 * @return
	 */
	public List<String> queryUserByBlDeptId(String bl_dept_id) {
		return dao.queryUserByBlDeptId(bl_dept_id);
	}

	/** 
	 * Description: ��ѯ�����û�
	 * @return 
	 */
	public DBIterator<String> iteratorAllUser() {
		return dao.iteratorAllUser();
	}

	/**
	 * Description: ���ݲ��Ų�ѯ�û�����Ϣ
	 * 
	 * @param dept_id
	 * @return
	 */
	public DBIterator<String> iteratorUserByDeptId(String dept_id) {
		return dao.iteratorUserByDeptId(dept_id);
	}

	/** 
	 * Description: ��ѯ�����û���Ϣ
	 * @return 
	 */
	public DBIterator<UsUserInfo> queryAllUserInfos() {
		return dao.queryAllUserInfos();
	}

	/** 
	 * Description: ��ѯ���������û�
	 * @param dept_id
	 * @return 
	 */
	public List<UserAppBean> queryUsersByDeptId(String dept_id) {
		return dao.queryUsersByDeptId(dept_id);
	}
	
	/**
	 * Description: ����Ӧ�������Ƿ��Ѿ�����
	 * @param user_id
	 * @return
	 */
	public int countByUserId(String user_id) {
		return dao.countByUserId(user_id);
	}

	/** 
	 * Description: �����û�����ѯ�û�������
	 * @param user_id �û�ID
	 * @return �û�������
	 */
	public String getUserCnNameByUserId(String user_id) {
		return dao.getUserCnNameByUserId(user_id);
	}

	public String getPasswdByUserId(String user_id){
        return dao.queryPasswdByUserId(user_id);
    }
}