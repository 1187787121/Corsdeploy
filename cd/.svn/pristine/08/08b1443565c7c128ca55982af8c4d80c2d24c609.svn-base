/**
 * Title: UsUserAndDprlDao.java
 * File Description: 根据部门角色编码和用户类型分页查询用户信息的DAO层
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserAndDprlBean;
import com.wk.db.SqlParam;
import com.wk.db.Query;

/**
 * Class Description:根据部门角色编码和用户类型分页查询用户信息的DAO层
 * @author link
 */
@Query
public abstract class UsUserAndDprlDao {

	/**
	 * Description: 根据部门角色编码和用户类型分页查询用户信息
	 * @param user_type
	 * @param dprl_code
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	@SqlParam(sql = "SELECT USER_ID,USER_CN_NAME,EMAIL_ADD,PHONE_NO,BL_DEPT_ID FROM `us_user` "
			+ "WHERE (USER_TYPE=:user_type AND "
			+ "USER_ID IN (SELECT USER_ID FROM `us_user_role` WHERE DPRL_CODE=:dprl_code))")
	abstract List<UsUserAndDprlBean> pageUserByDprlAndType(int user_type,
			String dprl_code, int statr_rcd, int limited_rcd);

	/**
	 * Description: 根据部门角色编码和用户类型查询到的用户信息总条数
	 * @param user_type
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT count(*) FROM `us_user` WHERE (USER_TYPE=:user_type AND "
			+ "USER_ID IN (SELECT USER_ID FROM `us_user_role` WHERE DPRL_CODE=:dprl_code))")
	abstract int countUserByDprlAndType(int user_type, String dprl_code);
}
