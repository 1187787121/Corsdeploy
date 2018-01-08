/**
 * Title: UsExtDprlDao.java
 * File Description: 部门角色扩展信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-13
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.us.bean.UsExtDprlBean;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: 部门角色扩展信息
 * @author link
 */
@Query
public abstract class UsExtDprlDao {

	/**
	 * Description: 根据部门角色查询部门编码、部门名称、角色编码、角色名称
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT a.DEPT_CN_NAME,b.dprl_code,b.DEPT_ID,b.ROLE_CODE,b.BK_EXPL,c.ROLE_CN_NAME "
			+ "FROM dp_dept a,us_dept_role b,us_role c "
			+ "WHERE a.DEPT_ID=b.DEPT_ID AND b.ROLE_CODE=c.ROLE_CODE AND b.DPRL_CODE=:dprl_code ")
	abstract UsExtDprlBean queryExtDprlInfo(String dprl_code);

	/**
	 * Description: 查询部门角色扩展信息列表(部门角色编码，部门角色名称，部门编码，部门名称)
	 * @return
	 */
	@SqlParam(sql = "SELECT a.DPRL_CODE,a.BK_EXPL,a.DEPT_ID,b.DEPT_CN_NAME FROM "
			+ "us_dept_role a,dp_dept b WHERE a.DEPT_ID=b.DEPT_ID and (b.DEPT_CN_NAME like '%${dept_cn_name}%') and (a.DEPT_ID like '%${org_dept_id}%') order by a.DPRL_CODE", dynamic = true)
	abstract List<UsExtDprlBean> pageExtDprlList(String dept_cn_name,String org_dept_id,
			int start_recd, int limited_recd);

	/**
	 * Description: 查询部门角色扩展信息列表信息条数
	 * @return
	 */
	@SqlParam(sql = "SELECT count(*) FROM us_dept_role a,dp_dept b "
			+ "WHERE a.DEPT_ID=b.DEPT_ID and (b.DEPT_CN_NAME like '%${dept_cn_name}%') and (a.DEPT_ID like '%${org_dept_id}%')", dynamic = true)
	abstract int countExtDprlList(String dept_cn_name,String dept_id_str);

	/**
	 * Description: 根据部门查询部门角色扩展信息列表(部门角色编码，部门角色名称，部门编码，部门名称)
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT a.DPRL_CODE,a.BK_EXPL,a.DEPT_ID,b.DEPT_CN_NAME FROM "
			+ "us_dept_role a,dp_dept b WHERE a.DEPT_ID=b.DEPT_ID and (b.DEPT_CN_NAME like '%${dept_cn_name}%') ", dynamic = true)
	abstract List<UsExtDprlBean> pageExtDprlListByDeptId(String dept_cn_name,
			int start_recd, int limited_recd);

	@SqlParam(sql = "SELECT b.DPRL_CODE,b.BK_EXPL,a.ROLE_CN_NAME,b.ROLE_CODE,b.DEPT_ID,c.DEPT_CN_NAME "
			+ "FROM us_role a,us_dept_role b,dp_dept c WHERE b.DEPT_ID=:dept_id AND a.ROLE_CODE=b.ROLE_CODE AND b.DEPT_ID=c.DEPT_ID")
	abstract List<UsExtDprlBean> queryExtDprlListByDept(String dept_id);

	/**
	 * 
	 * Description: 根据部门角色编码查询资源权限信息列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "select rs.* from RS_RES rs, US_ROLE_RS_PRIV dr where dr.dprl_code = :dprl_code and rs.RS_CODE = dr.RS_CODE and rs.PUBLIC_YN_FLAG=2 and RCD_STATE = 1")
	abstract DBIterator<RsPrivBean> queryRsPrivByDprl(String dprl_code);

	/** 
	 * Description: 根据部门角色编码查询数据源权限信息列表
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql = "select sc.* from DT_SOURCE sc, US_ROLE_SOC_PRIV dr where dr.dprl_code = :dprl_code and sc.soc_name = dr.soc_name and RCD_STATE = 1")
	abstract DBIterator<SocPrivBean> querySocPrivByDprl(String dprl_code);
}
