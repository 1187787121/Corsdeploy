/**
 * Title: UsExtUserDao.java
 * File Description: �û���չ��Ϣ�б�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-20
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.UsExtUserBean;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: �û���չ��Ϣ�б�
 * @author link
 */
@Query
public abstract class UsExtUserDao {

	/**
	 * Description: ��ѯ�������Ų�ѯ�Լ������û������û�����ģ����ѯ�û���չ��Ϣ�б�
	 * @param bl_dept_id
	 * @param user_cn_name
	 * @param user_id
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	@SqlParam(sql = "SELECT USER_ID,USER_CN_NAME,USER_TYPE,EMAIL_ADD,PHONE_NO,b.DEPT_CN_NAME "+
					" FROM us_user a,dp_dept b "
			+ "WHERE (a.BL_DEPT_ID=b.DEPT_ID)  AND (a.BL_DEPT_ID IN ${dept_id}) AND (USER_CN_NAME like '%${user_cn_name}%')"+
			  " order by USER_ID", dynamic = true)
	abstract List<UsExtUserBean> pageUserByBlDeptId(String dept_id, String user_cn_name,int statr_rcd, int limited_rcd);

	@SqlParam(sql = "SELECT count(*) FROM us_user a,dp_dept b "
			+ "WHERE a.BL_DEPT_ID=b.DEPT_ID AND (a.BL_DEPT_ID IN ${dept_id}) AND ((USER_CN_NAME like '%${user_cn_name}%'))", dynamic = true)
	abstract int countUserByBlDeptId(String dept_id, String user_cn_name);

	/**
	 * Description: �����û�����ѯ�û���һ��ְ����������Ϣ
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.DEPT_CN_NAME FIRST_DEPT_CN_NAME FROM us_user a,dp_dept b "
			+ "WHERE a.USER_ID=:user_id AND a.FIRST_DEPT_ID=b.DEPT_ID")
	abstract String queryExtUserFirstDeptByUserId(String user_id);
	
	/**
	 * Description: ��ѯ�û��Ĳ�������
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.DEPT_CN_NAME FROM us_user a,dp_dept b " 
			+"WHERE a.USER_ID=:user_id AND a.BL_DEPT_ID=b.DEPT_ID")
	abstract String queryExtUserDeptNameByUserId(String user_id);
	
	/**
	 * Description: ��ѯ�û����Ž�ɫ��Ϣ�б�
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT c.DPRL_CODE,c.DEPT_ID,c.BK_EXPL FROM us_user a,us_user_role b,us_dept_role c "
			+"WHERE a.USER_ID=:user_id AND a.USER_ID=b.USER_ID AND b.DPRL_CODE=c.DPRL_CODE")
	abstract List<UsDeptRoleInfo> queryExtUserDprlListByUserId(String user_id);

	/** 
	 * Description: ��ѯ�û�������ϵ��
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql = "SELECT a.USER_ID,USER_CN_NAME,USER_TYPE,EMAIL_ADD,PHONE_NO,b.DEPT_CN_NAME "+
			" FROM us_user a,dp_dept b,us_user_contact c"
	+ " WHERE a.USER_ID=c.CONTACT_USER_ID and a.BL_DEPT_ID=b.DEPT_ID and c.USER_ID=:user_id"+
	  " order by USER_ID")
	abstract List<UsExtUserBean> queryUserContact(String user_id);
}
