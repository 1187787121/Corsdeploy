/**
 * Title: ViewServerInputBean.java
 * File Description: ��������Ϣ�鿴Action����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.IMPL_TYPE;

/**
 * Class Description: ��������Ϣ�鿴Action����ӿ�
 * @author yangl
 */
public class ViewServerInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : -239693455573535659L
	 */
	private static final long serialVersionUID = -239693455573535659L;

	/**
	 * ����������
	 */
	private String ce_server_name;
	public static final String CE_SERVER_NAMECN = "����������";

	/**
	 * ��������ַ
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "��������ַ";

	/**
	 * �����ֶ�
	 */
	private String order_col_name;

	public static final String ORDER_COL_NAMECN = "�����ֶ�";
	/**
	 * ��������
	 */
	private ORDER_TYPE order_type;

	public static final String ORDER_TYPECN = "��������";

	/**
	 * ��У�����
	 */
	private String data;

	public static final String DATACN = "��У�����";

	/**
	 * ��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 * �׶�ִ������
	 */
	private IMPL_TYPE impl_type;

	public static final String MODULE_TYPECN = "�׶�ִ������";

	/**
	 * ϵͳ��
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "ϵͳ��";

	/**
	 * ҵ��ϵͳ���б�
	 */
	private String[] sys_name_list;

	public static final String SYS_NAME_LISTCN = "ҵ��ϵͳ���б�";

	/**
	 * @return ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return ��������ַ
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param ��������ַ
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return order_col_name
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @param order_col_name
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @return order_type
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}

	/**
	 * @param order_type
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}

	/**
	 * @return data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return impl_type
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 * @param impl_type
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return sys_name
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return sys_name_list ҵ��ϵͳ���б�
	 */
	public String[] getSys_name_list() {
		return sys_name_list;
	}

	/**
	 * @param sys_name_list ҵ��ϵͳ���б�
	 */
	public void setSys_name_list(String[] sys_name_list) {
		this.sys_name_list = sys_name_list;
	}

}
