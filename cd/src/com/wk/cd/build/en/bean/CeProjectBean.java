/**
 * Title: CeProjectBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:
 * @author xuph
 */
public class CeProjectBean {
	/**
	 * 项目编号
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "项目编号";

	/**
	 * 项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";

	/**
	 * 项目名
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN = "项目名";
	/**
	 * 应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";

	/**
	 * 环境简称
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "环境简称";
	
	/**
	 * 关联环境个数
	 */
	private long rel_env_num;
	
	public static final String REL_ENV_NUMCN = "关联环境个数";

	/**
	 * 项目描述
	 */
	private String project_bk_desc;

	public static final String PROJECT_BK_DESC = "项目描述";
	
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
	 * @return project_name 项目编号
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 * @param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return env_cn_name 环境简称
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 * @param env_cn_name 环境简称
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 * @param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return project_short_name 项目简称
	 */
	public String getProject_short_name() {
		return this.project_short_name;
	}

	/**
	 * @param project_short_name 项目简称
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 * @return project_bk_desc 项目描述
	 */
	public String getProject_bk_desc() {
		return this.project_bk_desc;
	}

	/**
	 * @param project_bk_desc 项目描述
	 */
	public void setProject_bk_desc(String project_bk_desc) {
		this.project_bk_desc = project_bk_desc;
	}

	/**
	 * @return rel_env_num
	 */
	public long getRel_env_num() {
		return this.rel_env_num;
	}

	/**
	 * @param rel_env_num
	 */
	public void setRel_env_num(long rel_env_num) {
		this.rel_env_num = rel_env_num;
	}
	

	/**
	 * @return sys_name 项目名
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name 项目名
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
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
