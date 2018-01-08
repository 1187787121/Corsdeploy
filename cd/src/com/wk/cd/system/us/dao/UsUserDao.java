/**
 * Title: UsUserDao.java
 * File Description: �û���
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.UserAppBean;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:�û���
 * 
 * @author AutoGen
 */
abstract class UsUserDao extends EntityDao<UsUserInfo> {

	/**
	 * Description: ����Ӧ�������Ƿ��Ѿ�����
	 * 
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract int countByUserId(String user_id);

	/**
	 * Description: �����û��������Ϣ
	 * 
	 * @param user_cn_name
	 *            �û�����
	 * @param email_add
	 *            Email��ַ
	 * @param phone_no
	 *            �绰����
	 * @param bl_dept_id
	 *            ��������
	 * @param user_type
	 *            �û�����
	 * @param first_dept_id
	 *            ��ְ����1
	 * @param secd_dept_id
	 *            ��ְ����2
	 * @param third_dept_id
	 *            ��ְ����3
	 * @param user_id
	 * @return �޸�����
	 */
	@SqlParam(updateSet = { "USER_CN_NAME", "EMAIL_ADD", "PHONE_NO",
			"BL_DEPT_ID", "USER_TYPE", "TELLER_NO", "FIRST_DEPT_ID",
			"SECD_DEPT_ID", "THIRD_DEPT_ID", "MODIFY_BK_DATE",
			"MODIFY_BK_TIME", "MODIFY_USER_ID" }, condition = ("USER_ID=:user_id"))
	abstract int updateUserByKey(String user_cn_name, String email_add,
			String phone_no, String bl_dept_id, int user_type,
			String teller_no, String first_dept_id, String secd_dept_id,
			String third_dept_id, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String user_id);

	/**
	 * Description: ͨ�������û��ļ�¼״̬��ɾ���û�
	 * 
	 * @param rcd_state
	 * @param user_id
	 */
	@SqlParam(updateSet = { "RCD_STATE" }, condition = ("USER_ID=:user_id"))
	abstract void updateUserStateByKey(int rcd_state, String user_id);

	/**
	 * Description: ���ݲ��Ų�ѯ�û�
	 * 
	 * @param bl_dept_id
	 * @return
	 */
	@SqlParam(querySet = { "USER_ID", "USER_CN_NAME", "EMAIL_ADD", "PHONE_NO",
			"BL_DEPT_ID" }, condition = "BL_DEPT_ID=:bl_dept_id and RCD_STATE=1")
	abstract List<UsUserInfo> pageUserByBlDeptId(String bl_dept_id,
			int statr_rcd, int limited_rcd);

	/**
	 * Description: ���ݲ��Ų�ѯ�û���Ϣ��������
	 * 
	 * @param bl_dept_id
	 * @return
	 */
	@SqlParam(condition = "BL_DEPT_ID=:bl_dept_id and RCD_STATE=1")
	abstract int countUserByBlDeptId(String bl_dept_id);

	/**
	 * Description: �����������źź��û����Ͳ�ѯ�û�
	 * 
	 * @param user_id
	 * @param user_type
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	@SqlParam(querySet = { "USER_ID", "USER_CN_NAME", "EMAIL_ADD", "PHONE_NO",
			"BL_DEPT_ID" }, condition = "BL_DEPT_ID=:bl_dept_id and USER_TYPE=:user_type and RCD_STATE=1")
	abstract List<UsUserInfo> pageUserByDeptAndType(String bl_dept_id,
			int user_type, int statr_rcd, int limited_rcd);

	/**
	 * Description: �����������ź��û����Ͳ�ѯ�����û�����
	 * 
	 * @param bl_dept_id
	 * @param user_type
	 * @return
	 */
	@SqlParam(condition = "BL_DEPT_ID=:bl_dept_id and USER_TYPE=:user_type and RCD_STATE=1")
	abstract int countUserByDeptAndType(String bl_dept_id, int user_type);

	/**
	 * Description: �����û�����ѯ�û�����ϸ��Ϣ
	 * 
	 * @param user_id
	 * @return
	 */
	@SqlParam(querySet = { "USER_ID", "USER_CN_NAME", "PWDEXP_BK_DATE",
			"EMAIL_ADD", "PHONE_NO", "BL_DEPT_ID", "USER_TYPE", "TELLER_NO",
			"FIRST_DEPT_ID", "SECD_DEPT_ID", "THIRD_DEPT_ID" }, condition = "USER_ID=:user_id")
	abstract UsUserInfo queryDetailByUserId(String user_id);

	/**
	 * Description: ��ѯ�û��ĵ�¼״̬�ͼ�¼״̬��Ϣ
	 * 
	 * @param user_id
	 * @return
	 */
	@SqlParam(querySet = {"USER_CN_NAME", "LOGIN_BK_NUM", "PWDEXP_BK_DATE", "RCD_STATE" }, condition = "PK")
	abstract UsUserInfo queryLoginAndState(String user_id);

	/**
	 * Description: ��ѯ�û���������뵽����
	 * 
	 * @param user_id
	 * @return
	 */
	@SqlParam(querySet = { "USER_PASSWD", "PWDEXP_BK_DATE" }, condition = "USER_ID=:user_id")
	abstract UsUserInfo queryPwdAndDate(String user_id);

	@SqlParam(condition = "USER_ID=:user_id and USER_PASSWD=:user_passwd ")
	abstract int countByPwd(String user_id, String user_passwd);

	/**
	 * Description: �û������޸�
	 * 
	 * @param user_passwd
	 * @param user_id
	 */
	@SqlParam(updateSet = { "USER_PASSWD", "PWDEXP_BK_DATE" }, condition = ("USER_ID=:user_id"))
	abstract void updateUserPasswdByKey(String user_passwd,
			JaDate pwdexp_bk_date, String user_id);

	/**
	 * Description: �����û��ĵ�¼״̬
	 * 
	 * @param login_bk_num
	 * @param user_id
	 */
	@SqlParam(updateSet = { "LOGIN_BK_NUM" }, condition = ("USER_ID=:user_id"))
	abstract void updateLoginNumByUserId(int login_bk_num, String user_id);
	
	/**
	 * Description: ���ݲ��Ų�ѯ�û�����Ϣ
	 * 
	 * @param bl_dept_id
	 * @return
	 */
	@SqlParam(querySet = { "USER_ID"}, condition = "BL_DEPT_ID=:bl_dept_id and RCD_STATE=1")
	abstract List<String> queryUserByBlDeptId(String bl_dept_id);

	/** 
	 * Description: ��ѯ�����û�
	 * @return 
	 */
	@SqlParam(querySet = { "USER_ID"}, condition = "RCD_STATE=1")
	abstract DBIterator<String> iteratorAllUser();

	/** 
	 * Description: ���ݲ��Ų�ѯ�û�����Ϣ
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(querySet = { "USER_ID"}, condition = "BL_DEPT_ID=:dept_id and RCD_STATE=1")
	abstract DBIterator<String> iteratorUserByDeptId(String dept_id);

	/** 
	 * Description: ��ѯ�����û���Ϣ
	 * @return 
	 */
	@SqlParam(sql="select * from us_user where rcd_state=1")
	abstract DBIterator<UsUserInfo> queryAllUserInfos();

	/** 
	 * Description: ��ѯ���������û�
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(querySet = { "USER_ID","USER_CN_NAME"},condition="BL_DEPT_ID=:dept_id and RCD_STATE=1")
	abstract List<UserAppBean> queryUsersByDeptId(String dept_id);

	/** 
	 * Description: �����û�����ѯ�û�������
	 * @param user_id �û�ID
	 * @return �û�������
	 */
	@SqlParam(querySet = {"USER_CN_NAME"},condition="user_id=:user_id")
	abstract String getUserCnNameByUserId(String user_id);


    /**
     * Description: �����û�����ѯ�û�����
     * @param user_id �û�ID
     * @return �û�����
     */
	@SqlParam(querySet = {"USER_PASSWD"}, condition = "USER_ID=:user_id")
	abstract String queryPasswdByUserId(String user_id);

}