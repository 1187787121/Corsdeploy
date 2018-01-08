/**
 * Title: QueryUserPrivOutputBean.java
 * File Description: 用户工作权限查询输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-11
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;

/**
 * Class Description: 用户工作权限查询输出接口
 * @author link
 */
public class QueryUserTempPrivOutputBean extends ActionRootOutputBean {

	private static final long serialVersionUID = 6780436268588025257L;
	
	/**
	 * 模块资源列表
	 */
	private List<RsPrivBean> modular_list;
	
	public static final String MODULAR_LISTCN = "模块资源列表";
	
	/**
	 * 部门资源列表
	 */
	private List<RsPrivBean> rs_infos;
	
	public static final String RS_INFOSCN = "部门资源列表";
	
	/**
	 * 用户可申请资源权限列表
	 */
	private List<RsPrivBean> rs_list;
	
	public static final String RS_LISTCN = "用户可申请资源权限列表";

	/**
	 * 用户可申请数据源权限列表
	 */
	private List<SocPrivBean> soc_list;
	
	public static final String SOC_LISTCN = "用户可申请数据源权限列表";
	
	/**
	 * 用户临时资源权限列表
	 */
	private List<TempRsBean> temp_rs_list;
	
	public static final String TEMP_RS_LISTCN = "用户临时资源权限列表";
	
	/**
	 * 用户临时数据源权限列表
	 */
	private List<UsUserSocPrivInfo> temp_soc_list;
	
	public static final String TEMP_SOC_LISTCN = "用户临时数据源权限列表";

	/**
	 * 用户临时SQL操作权限列表
	 */
	private List<UsUserSqlPrivInfo> temp_sql_list;
	
	public static final String TEMP_SQL_LISTCN = "用户临时SQL操作权限列表";

	/**
	 * 用户临时COL操作权限列表
	 */
	private List<UsUserColPrivInfo> temp_col_list;
	
	public static final String TEMP_COL_LISTCN = "用户临时COL操作权限列表";

	/**
	 * @return modular_list 模块资源列表
	 */
	public List<RsPrivBean> getModular_list() {
		return modular_list;
	}

	/**
	 * @param modular_list 模块资源列表
	 */
	public void setModular_list(List<RsPrivBean> modular_list) {
		this.modular_list = modular_list;
	}

	/**
	 * @return rs_infos 部门资源列表
	 */
	public List<RsPrivBean> getRs_infos() {
		return this.rs_infos;
	}

	/**
	 * @param rs_infos 部门资源列表
	 */
	public void setRs_infos(List<RsPrivBean> rs_infos) {
		this.rs_infos = rs_infos;
	}

	/**
	 * @return rs_list 用户可申请资源权限列表
	 */
	public List<RsPrivBean> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list 用户可申请资源权限列表
	 */
	public void setRs_list(List<RsPrivBean> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return soc_list 用户可申请数据源权限列表
	 */
	public List<SocPrivBean> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 用户可申请数据源权限列表
	 */
	public void setSoc_list(List<SocPrivBean> soc_list) {
		this.soc_list = soc_list;
	}
	
	/**
	 * @return temp_rs_list 用户临时资源权限列表
	 */
	public List<TempRsBean> getTemp_rs_list() {
		return this.temp_rs_list;
	}

	/**
	 * @param temp_rs_list 用户临时资源权限列表
	 */
	public void setTemp_rs_list(List<TempRsBean> temp_rs_list) {
		this.temp_rs_list = temp_rs_list;
	}

	/**
	 * @return temp_soc_list 用户临时数据源权限列表
	 */
	public List<UsUserSocPrivInfo> getTemp_soc_list() {
		return this.temp_soc_list;
	}

	/**
	 * @param temp_soc_list 用户临时数据源权限列表
	 */
	public void setTemp_soc_list(List<UsUserSocPrivInfo> temp_soc_list) {
		this.temp_soc_list = temp_soc_list;
	}

	/**
	 * @return temp_sql_list 用户临时SQL操作权限列表
	 */
	public List<UsUserSqlPrivInfo> getTemp_sql_list() {
		return this.temp_sql_list;
	}

	/**
	 * @param temp_sql_list 用户临时SQL操作权限列表
	 */
	public void setTemp_sql_list(List<UsUserSqlPrivInfo> temp_sql_list) {
		this.temp_sql_list = temp_sql_list;
	}

	/**
	 * @return temp_col_list 用户临时COL操作权限列表
	 */
	public List<UsUserColPrivInfo> getTemp_col_list() {
		return this.temp_col_list;
	}

	/**
	 * @param temp_col_list 用户临时COL操作权限列表
	 */
	public void setTemp_col_list(List<UsUserColPrivInfo> temp_col_list) {
		this.temp_col_list = temp_col_list;
	}

}
