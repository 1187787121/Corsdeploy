/**
 * Title: QueryDeptPrivOutputBean.java
 * File Description: 查询部门权限信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.cd.system.rs.info.RsOptInfo;

/**
 * Class Description: 查询部门权限信息输出接口
 * 
 * @author HT
 */
public class QueryDeptPrivOutputBean extends ActionRootOutputBean {

	private static final long serialVersionUID = -6403681747240307701L;

	/**
	 * 资源权限列表
	 */
	private List<RsPrivBean> rs_list;

	public static final String RS_LISTCN = "资源权限列表";

	/**
	 * 上级资源权限列表
	 */
	private List<RsPrivBean> sup_rs_list;

	public static final String SUP_RS_LISTCN = "上级资源权限列表";
	
	/**
	 * 所有的操作信息列表
	 */
	private List<RsOptInfo> opt_list;
	
	public static final String OPT_LISTCN = "所有的操作信息列表";
	
	/**
	 * 部门操作权限列表
	 */
	private List<DpDeptOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "部门操作权限列表";
	
	/**
	 * 上级部门操作权限列表
	 */
	private List<DpDeptOptPrivInfo> sup_opt_priv;
	
	public static final String SUP_OPT_PRIVCN = "上级部门操作权限列表";

	/**
	 * 数据源权限列表
	 */
	private List<SocPrivBean> soc_list;

	public static final String SOC_LISTCN = "数据源权限列表";

	/**
	 * 上级数据源权限列表
	 */
	private List<SocPrivBean> sup_soc_list;

	public static final String SUP_SOC_LISTCN = "上级数据源权限列表";

	/**
	 * SQL权限列表
	 */
	private List<DpDeptSqlPrivInfo> sql_list;

	public static final String SQL_LISTCN = "SQL权限信息列表";

	/**
	 * COL权限列表
	 */
	private List<DpDeptColPrivInfo> col_list;

	public static final String COL_LISTCN = "COL权限列表";


	/**
	 * @return rs_list 资源权限列表
	 */
	public List<RsPrivBean> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list 资源权限列表
	 */
	public void setRs_list(List<RsPrivBean> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return sup_rs_list 上级资源权限列表
	 */
	public List<RsPrivBean> getSup_rs_list() {
		return this.sup_rs_list;
	}

	/**
	 * @param sup_rs_list 上级资源权限列表
	 */
	public void setSup_rs_list(List<RsPrivBean> sup_rs_list) {
		this.sup_rs_list = sup_rs_list;
	}

	/**
	 * @return soc_list 数据源权限列表
	 */
	public List<SocPrivBean> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 数据源权限列表
	 */
	public void setSoc_list(List<SocPrivBean> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return sup_soc_list 上级数据源权限列表
	 */
	public List<SocPrivBean> getSup_soc_list() {
		return this.sup_soc_list;
	}

	/**
	 * @param sup_soc_list 上级数据源权限列表
	 */
	public void setSup_soc_list(List<SocPrivBean> sup_soc_list) {
		this.sup_soc_list = sup_soc_list;
	}

	/**
	 * @return sql_list SQL权限列表
	 */
	public List<DpDeptSqlPrivInfo> getSql_list() {
		return this.sql_list;
	}

	/**
	 * @param sql_list SQL权限列表
	 */
	public void setSql_list(List<DpDeptSqlPrivInfo> sql_list) {
		this.sql_list = sql_list;
	}

	/**
	 * @return col_list COL权限列表
	 */
	public List<DpDeptColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list COL权限列表
	 */
	public void setCol_list(List<DpDeptColPrivInfo> col_list) {
		this.col_list = col_list;
	}

	/**
	 * @return opt_list 所有的操作信息列表
	 */
	public List<RsOptInfo> getOpt_list() {
		return this.opt_list;
	}

	/**
	 * @param opt_list 所有的操作信息列表
	 */
	public void setOpt_list(List<RsOptInfo> opt_list) {
		this.opt_list = opt_list;
	}

	/**
	 * @return opt_priv 部门操作权限列表
	 */
	public List<DpDeptOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv 部门操作权限列表
	 */
	public void setOpt_priv(List<DpDeptOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}

	/**
	 * @return sup_opt_priv 上级部门操作权限列表
	 */
	public List<DpDeptOptPrivInfo> getSup_opt_priv() {
		return this.sup_opt_priv;
	}

	/**
	 * @param sup_opt_priv 上级部门操作权限列表
	 */
	public void setSup_opt_priv(List<DpDeptOptPrivInfo> sup_opt_priv) {
		this.sup_opt_priv = sup_opt_priv;
	}
}
