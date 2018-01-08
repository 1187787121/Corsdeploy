/**
 * Title: PageServerBean.java
 * File Description: 服务器分页查询搜索Bean
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 服务器分页查询搜索Bean
 * @author yangl
 */
public class PageServerBean {

	/**
	 * 服务器名称
	 */
	private String server_name;
	public static final String SERVER_NAME_CN = "服务器名称";

	/**
	 * 服务器简称
	 */
	private String server_cn_name;
	public static final String SERVER_CN_NAMECN = "服务器简称";

	/**
	 * 服务器地址
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "服务器地址";

	/**
	 * 应用系统个数
	 */
	private long system_num;
	public static final String SYSTEM_NUM_CN = "应用系统个数";
	
	/**
	 * 环境个数
	 */
	private long environment_num;
	public static final String ENVIRONMENT_NUM_CN = "环境个数";
	
	/**
	 *创建日期
	 */
	private JaDate create_bk_date;

	public static final String CREATE_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime create_bk_time;

	public static final String CREATE_BK_TIMECN = "创建时间";
	
	/**
	 * @return 服务器名称
	 */
	public String getServer_name() {
		return server_name;
	}

	/**
	 * @param 服务器名称
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 * @return 服务器简称
	 */
	public String getServer_cn_name() {
		return server_cn_name;
	}

	/**
	 * @param 服务器简称
	 */
	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
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
	 * @return 应用系统个数
	 */
	public long getSystem_num() {
		return system_num;
	}

	/**
	 * @param 应用系统个数
	 */
	public void setSystem_num(long system_num) {
		this.system_num = system_num;
	}

	/**
	 * @return 应用系统个数
	 */
	public long getEnvironment_num() {
		return environment_num;
	}

	/**
	 * @param 应用系统个数
	 */
	public void setEnvironment_num(long environment_num) {
		this.environment_num = environment_num;
	}
	
	/**
	 *@return create_bk_date 创建日期
	 */
	public JaDate getCreate_bk_date() {
		return this.create_bk_date;
	}

	/**
	 *@param create_bk_date 创建日期
	 */
	public void setCreate_bk_date(JaDate create_bk_date) {
		this.create_bk_date = create_bk_date;
	}

	/**
	 *@return create_bk_time 创建时间
	 */
	public JaTime getCreate_bk_time() {
		return this.create_bk_time;
	}

	/**
	 *@param create_bk_time 创建时间
	 */
	public void setCreate_bk_time(JaTime create_bk_time) {
		this.create_bk_time = create_bk_time;
	}

}
