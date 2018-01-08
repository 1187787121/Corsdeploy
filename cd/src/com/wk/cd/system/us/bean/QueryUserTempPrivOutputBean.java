/**
 * Title: QueryUserPrivOutputBean.java
 * File Description: �û�����Ȩ�޲�ѯ����ӿ�
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
 * Class Description: �û�����Ȩ�޲�ѯ����ӿ�
 * @author link
 */
public class QueryUserTempPrivOutputBean extends ActionRootOutputBean {

	private static final long serialVersionUID = 6780436268588025257L;
	
	/**
	 * ģ����Դ�б�
	 */
	private List<RsPrivBean> modular_list;
	
	public static final String MODULAR_LISTCN = "ģ����Դ�б�";
	
	/**
	 * ������Դ�б�
	 */
	private List<RsPrivBean> rs_infos;
	
	public static final String RS_INFOSCN = "������Դ�б�";
	
	/**
	 * �û���������ԴȨ���б�
	 */
	private List<RsPrivBean> rs_list;
	
	public static final String RS_LISTCN = "�û���������ԴȨ���б�";

	/**
	 * �û�����������ԴȨ���б�
	 */
	private List<SocPrivBean> soc_list;
	
	public static final String SOC_LISTCN = "�û�����������ԴȨ���б�";
	
	/**
	 * �û���ʱ��ԴȨ���б�
	 */
	private List<TempRsBean> temp_rs_list;
	
	public static final String TEMP_RS_LISTCN = "�û���ʱ��ԴȨ���б�";
	
	/**
	 * �û���ʱ����ԴȨ���б�
	 */
	private List<UsUserSocPrivInfo> temp_soc_list;
	
	public static final String TEMP_SOC_LISTCN = "�û���ʱ����ԴȨ���б�";

	/**
	 * �û���ʱSQL����Ȩ���б�
	 */
	private List<UsUserSqlPrivInfo> temp_sql_list;
	
	public static final String TEMP_SQL_LISTCN = "�û���ʱSQL����Ȩ���б�";

	/**
	 * �û���ʱCOL����Ȩ���б�
	 */
	private List<UsUserColPrivInfo> temp_col_list;
	
	public static final String TEMP_COL_LISTCN = "�û���ʱCOL����Ȩ���б�";

	/**
	 * @return modular_list ģ����Դ�б�
	 */
	public List<RsPrivBean> getModular_list() {
		return modular_list;
	}

	/**
	 * @param modular_list ģ����Դ�б�
	 */
	public void setModular_list(List<RsPrivBean> modular_list) {
		this.modular_list = modular_list;
	}

	/**
	 * @return rs_infos ������Դ�б�
	 */
	public List<RsPrivBean> getRs_infos() {
		return this.rs_infos;
	}

	/**
	 * @param rs_infos ������Դ�б�
	 */
	public void setRs_infos(List<RsPrivBean> rs_infos) {
		this.rs_infos = rs_infos;
	}

	/**
	 * @return rs_list �û���������ԴȨ���б�
	 */
	public List<RsPrivBean> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list �û���������ԴȨ���б�
	 */
	public void setRs_list(List<RsPrivBean> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return soc_list �û�����������ԴȨ���б�
	 */
	public List<SocPrivBean> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list �û�����������ԴȨ���б�
	 */
	public void setSoc_list(List<SocPrivBean> soc_list) {
		this.soc_list = soc_list;
	}
	
	/**
	 * @return temp_rs_list �û���ʱ��ԴȨ���б�
	 */
	public List<TempRsBean> getTemp_rs_list() {
		return this.temp_rs_list;
	}

	/**
	 * @param temp_rs_list �û���ʱ��ԴȨ���б�
	 */
	public void setTemp_rs_list(List<TempRsBean> temp_rs_list) {
		this.temp_rs_list = temp_rs_list;
	}

	/**
	 * @return temp_soc_list �û���ʱ����ԴȨ���б�
	 */
	public List<UsUserSocPrivInfo> getTemp_soc_list() {
		return this.temp_soc_list;
	}

	/**
	 * @param temp_soc_list �û���ʱ����ԴȨ���б�
	 */
	public void setTemp_soc_list(List<UsUserSocPrivInfo> temp_soc_list) {
		this.temp_soc_list = temp_soc_list;
	}

	/**
	 * @return temp_sql_list �û���ʱSQL����Ȩ���б�
	 */
	public List<UsUserSqlPrivInfo> getTemp_sql_list() {
		return this.temp_sql_list;
	}

	/**
	 * @param temp_sql_list �û���ʱSQL����Ȩ���б�
	 */
	public void setTemp_sql_list(List<UsUserSqlPrivInfo> temp_sql_list) {
		this.temp_sql_list = temp_sql_list;
	}

	/**
	 * @return temp_col_list �û���ʱCOL����Ȩ���б�
	 */
	public List<UsUserColPrivInfo> getTemp_col_list() {
		return this.temp_col_list;
	}

	/**
	 * @param temp_col_list �û���ʱCOL����Ȩ���б�
	 */
	public void setTemp_col_list(List<UsUserColPrivInfo> temp_col_list) {
		this.temp_col_list = temp_col_list;
	}

}
