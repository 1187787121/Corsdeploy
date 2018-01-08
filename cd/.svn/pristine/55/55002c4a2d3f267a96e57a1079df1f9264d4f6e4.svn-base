/**
 * Title: UsExtUserDao.java
 * File Description: 用户扩展信息列表
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
 * Class Description: 用户扩展信息列表
 * @author link
 */
@Query
public abstract class UsExtUserDao {

	/**
	 * Description: 查询所属部门查询以及根据用户名和用户姓名模糊查询用户扩展信息列表
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
	 * Description: 根据用户名查询用户第一兼职部门名称信息
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.DEPT_CN_NAME FIRST_DEPT_CN_NAME FROM us_user a,dp_dept b "
			+ "WHERE a.USER_ID=:user_id AND a.FIRST_DEPT_ID=b.DEPT_ID")
	abstract String queryExtUserFirstDeptByUserId(String user_id);
	
	/**
	 * Description: 查询用户的部门名称
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.DEPT_CN_NAME FROM us_user a,dp_dept b " 
			+"WHERE a.USER_ID=:user_id AND a.BL_DEPT_ID=b.DEPT_ID")
	abstract String queryExtUserDeptNameByUserId(String user_id);
	
	/**
	 * Description: 查询用户部门角色信息列表
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT c.DPRL_CODE,c.DEPT_ID,c.BK_EXPL FROM us_user a,us_user_role b,us_dept_role c "
			+"WHERE a.USER_ID=:user_id AND a.USER_ID=b.USER_ID AND b.DPRL_CODE=c.DPRL_CODE")
	abstract List<UsDeptRoleInfo> queryExtUserDprlListByUserId(String user_id);

	/** 
	 * Description: 查询用户常用联系人
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql = "SELECT a.USER_ID,USER_CN_NAME,USER_TYPE,EMAIL_ADD,PHONE_NO,b.DEPT_CN_NAME "+
			" FROM us_user a,dp_dept b,us_user_contact c"
	+ " WHERE a.USER_ID=c.CONTACT_USER_ID and a.BL_DEPT_ID=b.DEPT_ID and c.USER_ID=:user_id"+
	  " order by USER_ID")
	abstract List<UsExtUserBean> queryUserContact(String user_id);
}
