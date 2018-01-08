/**
 * Title: QueryUserPrivOutputBean.java
 * File Description: ��ѯ�û�Ȩ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��8��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;

/**
 * Class Description:  ��ѯ�û�Ȩ����Ϣ����ӿ�
 * @author HT
 */
public class QueryUserPrivOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -2328993640018929474L;

	
	/**
	 * ��ԴȨ���б�
	 */
	private List<RsPrivBean> rs_list;

	public static final String RS_LISTCN = "��ԴȨ���б�";
	
	/**
	 * ���Ž�ɫ��ԴȨ���б�
	 */
	private List<RsPrivBean> dr_rs_list;
	
	public static final String DR_RS_LISTCN = "���Ž�ɫ��ԴȨ���б�";

	/**
	 * �ϼ���ԴȨ���б�
	 */
	private List<RsPrivBean> sup_rs_list;

	public static final String SUP_RS_LISTCN = "�ϼ���ԴȨ���б�";
	
	/**
	 * ���еĲ�����Ϣ�б�
	 */
	private List<RsOptInfo> opt_list;
	
	public static final String OPT_LISTCN = "���еĲ�����Ϣ�б�";
	
	/**
	 * �û�����Ȩ���б�
	 */
	private List<UsUserOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "�û�����Ȩ���б�";
	
	/**
	 * ���Ž�ɫ����Ȩ���б�
	 */
	private List<UsUserOptPrivInfo> dr_opt_priv;
	
	public static final String DR_OPT_PRIVCN = "���Ž�ɫ����Ȩ���б�";
	
	/**
	 * �ϼ����Ų���Ȩ���б�
	 */
	private List<DpDeptOptPrivInfo> sup_opt_priv;
	
	public static final String SUP_OPT_PRIVCN = "�ϼ����Ų���Ȩ���б�";

	/**
	 * ����ԴȨ���б�
	 */
	private List<SocPrivBean> soc_list;

	public static final String SOC_LISTCN = "����ԴȨ���б�";
	
	/**
	 * ���Ž�ɫ����ԴȨ���б�
	 */
	private List<SocPrivBean> dr_soc_list;

	public static final String DR_SOC_LISTCN = "���Ž�ɫ����ԴȨ���б�";

	/**
	 * �ϼ�����ԴȨ���б�
	 */
	private List<SocPrivBean> sup_soc_list;

	public static final String SUP_SOC_LISTCN = "�ϼ�����ԴȨ���б�";

	/**
	 * SQLȨ���б�
	 */
	private List<UsUserSqlPrivInfo> sql_list;

	public static final String SQL_LISTCN = "SQLȨ����Ϣ�б�";

	/**
	 * COLȨ���б�
	 */
	private List<UsUserColPrivInfo> col_list;

	public static final String COL_LISTCN = "COLȨ���б�";


	/**
	 * @return rs_list ��ԴȨ���б�
	 */
	public List<RsPrivBean> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list ��ԴȨ���б�
	 */
	public void setRs_list(List<RsPrivBean> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return sup_rs_list �ϼ���ԴȨ���б�
	 */
	public List<RsPrivBean> getSup_rs_list() {
		return this.sup_rs_list;
	}

	/**
	 * @param sup_rs_list �ϼ���ԴȨ���б�
	 */
	public void setSup_rs_list(List<RsPrivBean> sup_rs_list) {
		this.sup_rs_list = sup_rs_list;
	}

	/**
	 * @return soc_list ����ԴȨ���б�
	 */
	public List<SocPrivBean> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list ����ԴȨ���б�
	 */
	public void setSoc_list(List<SocPrivBean> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return sup_soc_list �ϼ�����ԴȨ���б�
	 */
	public List<SocPrivBean> getSup_soc_list() {
		return this.sup_soc_list;
	}

	/**
	 * @param sup_soc_list �ϼ�����ԴȨ���б�
	 */
	public void setSup_soc_list(List<SocPrivBean> sup_soc_list) {
		this.sup_soc_list = sup_soc_list;
	}

	/**
	 * @return sql_list SQLȨ���б�
	 */
	public List<UsUserSqlPrivInfo> getSql_list() {
		return this.sql_list;
	}

	/**
	 * @param sql_list SQLȨ���б�
	 */
	public void setSql_list(List<UsUserSqlPrivInfo> sql_list) {
		this.sql_list = sql_list;
	}

	/**
	 * @return col_list COLȨ���б�
	 */
	public List<UsUserColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list COLȨ���б�
	 */
	public void setCol_list(List<UsUserColPrivInfo> col_list) {
		this.col_list = col_list;
	}

	/**
	 * @return opt_list ���еĲ�����Ϣ�б�
	 */
	public List<RsOptInfo> getOpt_list() {
		return this.opt_list;
	}

	/**
	 * @param opt_list ���еĲ�����Ϣ�б�
	 */
	public void setOpt_list(List<RsOptInfo> opt_list) {
		this.opt_list = opt_list;
	}

	/**
	 * @return opt_priv �û�����Ȩ���б�
	 */
	public List<UsUserOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv �û�����Ȩ���б�
	 */
	public void setOpt_priv(List<UsUserOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}

	/**
	 * @return sup_opt_priv �ϼ����Ų���Ȩ���б�
	 */
	public List<DpDeptOptPrivInfo> getSup_opt_priv() {
		return this.sup_opt_priv;
	}

	/**
	 * @param sup_opt_priv �ϼ����Ų���Ȩ���б�
	 */
	public void setSup_opt_priv(List<DpDeptOptPrivInfo> sup_opt_priv) {
		this.sup_opt_priv = sup_opt_priv;
	}

	/**
	 * @return dr_rs_list ���Ž�ɫ��ԴȨ���б�
	 */
	public List<RsPrivBean> getDr_rs_list() {
		return this.dr_rs_list;
	}

	/**
	 * @param dr_rs_list ���Ž�ɫ��ԴȨ���б�
	 */
	public void setDr_rs_list(List<RsPrivBean> dr_rs_list) {
		this.dr_rs_list = dr_rs_list;
	}

	/**
	 * @return dr_opt_priv ���Ž�ɫ����Ȩ���б�
	 */
	public List<UsUserOptPrivInfo> getDr_opt_priv() {
		return this.dr_opt_priv;
	}

	/**
	 * @param dr_opt_priv ���Ž�ɫ����Ȩ���б�
	 */
	public void setDr_opt_priv(List<UsUserOptPrivInfo> dr_opt_priv) {
		this.dr_opt_priv = dr_opt_priv;
	}

	/**
	 * @return dr_soc_list ���Ž�ɫ����ԴȨ���б�
	 */
	public List<SocPrivBean> getDr_soc_list() {
		return this.dr_soc_list;
	}

	/**
	 * @param dr_soc_list ���Ž�ɫ����ԴȨ���б�
	 */
	public void setDr_soc_list(List<SocPrivBean> dr_soc_list) {
		this.dr_soc_list = dr_soc_list;
	}
}