/**
 * Title: DpDeptQueryDao.java
 * File Description:  部门联合查询类
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015-8-27
 */
package com.wk.cd.system.dp.dao;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: 部门联合查询类
 * @author HT
 */
@Query
public abstract class DpDeptQueryDao {
	/**
	 * 
	 * Description: 根据部门ID查询资源权限信息列表
	 * @param dept_id
	 * @return
	 */
	@SqlParam(sql = "select rs.* from RS_RES rs, DP_DEPT_RS_PRIV dp where dp.dept_id = :dept_id and rs.RS_CODE = dp.RS_CODE and rs.PUBLIC_YN_FLAG=2 and RCD_STATE = 1")
	public abstract DBIterator<RsPrivBean> queryRsPrivByDeptId(String dept_id);

	/**
	 * 
	 * Description: 根据部门ID查询数据源权限信息列表
	 * @param dept_id
	 * @return
	 */
	@SqlParam(sql = "select sc.* from DT_SOURCE sc, DP_DEPT_SOC_PRIV dp where dp.dept_id = :dept_id and sc.soc_name = dp.soc_name and RCD_STATE = 1")
	public abstract DBIterator<SocPrivBean> querySocPrivByDeptId(String dept_id);
}
