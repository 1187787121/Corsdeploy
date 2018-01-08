/**
 * Title: QueryUserDeptPrivOutputBean.java
 * File Description: ��ѯ�û���������ӵ�е�Ȩ������ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-16
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.sv.info.SvSrvInfo;

/**
 * Class Description: ��ѯ�û���������ӵ�е�Ȩ������ӿ�
 * @author link
 */
public class QueryUserDeptPrivOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = -8246796502828211160L;
	private List<RsResInfo> rs_list;
	public static final String RS_LISTCN = "��Դ�б�";

	private List<DtSourceInfo> soc_list;
	public static final String SOC_LISTCN = "����Դ�б�";

	private List<SvSrvInfo> srv_list;
	public static final String SRV_LISTCN = "�����б�";

	private List<DpDeptSqlPrivInfo> sql_list;
	public static final String SQL_LISTCN = "SQL�б�";

	private List<DpDeptColPrivInfo> col_list;
	public static final String COL_LISTCN = "COL�б�";

	/**
	 * @return rs_list ��Դ�б�
	 */
	public List<RsResInfo> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list ��Դ�б�
	 */
	public void setRs_list(List<RsResInfo> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return soc_list ����Դ�б�
	 */
	public List<DtSourceInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list ����Դ�б�
	 */
	public void setSoc_list(List<DtSourceInfo> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return srv_list �����б�
	 */
	public List<SvSrvInfo> getSrv_list() {
		return this.srv_list;
	}

	/**
	 * @param srv_list �����б�
	 */
	public void setSrv_list(List<SvSrvInfo> srv_list) {
		this.srv_list = srv_list;
	}

	/**
	 * @return sql_list SQL�б�
	 */
	public List<DpDeptSqlPrivInfo> getSql_list() {
		return this.sql_list;
	}

	/**
	 * @param sql_list SQL�б�
	 */
	public void setSql_list(List<DpDeptSqlPrivInfo> sql_list) {
		this.sql_list = sql_list;
	}

	/**
	 * @return col_list COL�б�
	 */
	public List<DpDeptColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list COL�б�
	 */
	public void setCol_list(List<DpDeptColPrivInfo> col_list) {
		this.col_list = col_list;
	}

}
