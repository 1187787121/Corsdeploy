/**
 * Title: ViewServerInputBean.java
 * File Description: 服务器信息查看Action输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.IMPL_TYPE;

/**
 * Class Description: 服务器信息查看Action输入接口
 * @author yangl
 */
public class ViewServerInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : -239693455573535659L
	 */
	private static final long serialVersionUID = -239693455573535659L;

	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	public static final String CE_SERVER_NAMECN = "服务器名称";

	/**
	 * 服务器地址
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "服务器地址";

	/**
	 * 排序字段
	 */
	private String order_col_name;

	public static final String ORDER_COL_NAMECN = "排序字段";
	/**
	 * 排序类型
	 */
	private ORDER_TYPE order_type;

	public static final String ORDER_TYPECN = "排序类型";

	/**
	 * 待校验参数
	 */
	private String data;

	public static final String DATACN = "待校验参数";

	/**
	 * 环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";

	/**
	 * 阶段执行类型
	 */
	private IMPL_TYPE impl_type;

	public static final String MODULE_TYPECN = "阶段执行类型";

	/**
	 * 系统名
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "系统名";

	/**
	 * 业务系统名列表
	 */
	private String[] sys_name_list;

	public static final String SYS_NAME_LISTCN = "业务系统名列表";

	/**
	 * @return 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return 服务器地址
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param 服务器地址
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
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名称
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
	 * @return sys_name_list 业务系统名列表
	 */
	public String[] getSys_name_list() {
		return sys_name_list;
	}

	/**
	 * @param sys_name_list 业务系统名列表
	 */
	public void setSys_name_list(String[] sys_name_list) {
		this.sys_name_list = sys_name_list;
	}

}
