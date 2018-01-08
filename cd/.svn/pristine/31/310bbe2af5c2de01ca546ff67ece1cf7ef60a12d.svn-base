/**
 * Title: UsUserCombineDao.java
 * File Description: 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserCombineBean;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: 用户综合信息表DAO层
 * @author link
 */
@Query
public abstract class UsUserCombineDao {

	/**
	 * Description: 根据用户名返回用户的综合信息实体列表
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT a.USER_ID,a.DPRL_CODE,b.DEPT_ID,b.ROLE_CODE,c.ROLE_CN_NAME,c.ROLE_TYPE "
			+ "FROM (us_user_role a INNER JOIN us_dept_role b ON a.DPRL_CODE=b.DPRL_CODE) "
			+ "INNER JOIN us_role c ON b.ROLE_CODE=c.ROLE_CODE "
			+ "WHERE a.USER_ID=:user_id ")
	abstract List<UsUserCombineBean> queryUserCombineInfo(String user_id);

}
