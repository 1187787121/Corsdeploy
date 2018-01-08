/**
 * Title: DeptQuery.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.dp.dao;

import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: 
 * @author xuy
 */
@Query
public abstract class DeptQuery {
	@SqlParam(sql ="select (select count(*) from dp_dept dp2 where dp2.super_dept_id=dp.dept_id and dp2.rcd_state=1) LOWDPT_BK_NUM,dept_id,dept_cn_name,dept_type,dept_level,super_dept_id from dp_dept dp where dp.rcd_state=1 order by dept_level, dept_id")
	public abstract DBIterator<DeptExtendsBean> queryAllDeptInfo();
	@SqlParam(sql ="select (select count(*) from dp_dept dp2 where dp2.super_dept_id=dp.dept_id and dp2.rcd_state=1) LOWDPT_BK_NUM,dept_id,dept_cn_name,dept_type,dept_level,super_dept_id from dp_dept dp where (dp.super_dept_id = :super_dept_id1  or dp.dept_id = :dept_id)  and dp.rcd_state=1 order by dept_level")
	public abstract DBIterator<DeptExtendsBean> queryAllDeptInfo(String super_dept_id1,String dept_id);

	@SqlParam(sql = "select rs.* from RS_RES rs, DP_DEPT_RS_PRIV dp where dp.dept_id = :dept_id "+
					" and rs.RS_CODE = dp.RS_CODE and RCD_STATE = 1")
	public abstract DBIterator<RsResInfo> queryResByDeptId(String dept_id);

	@SqlParam(sql = "select sc.* from DT_SOURCE sc, DP_DEPT_SOC_PRIV dp where dp.dept_id = :dept_id "+
					" and sc.soc_name = dp.soc_name and RCD_STATE = 1")
	public abstract DBIterator<DtSourceInfo> querySocByDeptId(String dept_id);
}
