/**
 * Title: CeEnvironmentBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.AF_FLAG;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author xuph
 */
public class CeEnvironmentBean {
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	/**
	 * 环境简称
	 */
	private String env_cn_name;
	
	public static final String ENV_CN_NAMECN = "环境简称";
	
	/**
	 * 环境类型
	 */
	private ENV_TYPE env_type;
	
	public static final String ENV_TYPECN = "环境类型";
	
	/**
	 * 数据范围
	 */
	private DT_RANGE dt_range;
	
	public static final String DT_RANGECN = "数据范围";
	
	/**
	 * 构成要素
	 */
	private ELE_TYPE[] ele_type;
	
	public static final String ELE_TYPECN = "构成要素";
	
	/**
	 *应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";

	/**
	 * 是否含有方案
	 */
	private boolean pg_mark;
	
	public static final String PG_MARKCN = "是否含有方案";

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
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";
	
	/**
	 * @return env_name 环境名
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 * @param env_name 环境名
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return env_cn_name
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 * @param env_cn_name
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return env_type
	 */
	public ENV_TYPE getEnv_type() {
		return this.env_type;
	}

	/**
	 * @param env_type
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 * @return dt_range
	 */
	public DT_RANGE getDt_range() {
		return this.dt_range;
	}

	/**
	 * @param dt_range
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

	/**
	 * @return ele_type
	 */
	public ELE_TYPE[] getEle_type() {
		return this.ele_type;
	}

	/**
	 * @param ele_type
	 */
	public void setEle_type(ELE_TYPE[] ele_type) {
		this.ele_type = ele_type;
	}

	/**
	 * @return sys_cn_name
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 * @param sys_cn_name
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return pg_mark
	 */
	public boolean isPg_mark() {
		return this.pg_mark;
	}

	/**
	 * @param pg_mark
	 */
	public void setPg_mark(boolean pg_mark) {
		this.pg_mark = pg_mark;
	}

	/**
	 * @return create_bk_time 创建时间
	 */
	public JaTime getCreate_bk_time() {
		return create_bk_time;
	}

	/**
	 * @param create_bk_time 创建时间
	 */
	public void setCreate_bk_time(JaTime create_bk_time) {
		this.create_bk_time = create_bk_time;
	}

	/**
	 * @return create_bk_date 创建日期
	 */
	public JaDate getCreate_bk_date() {
		return create_bk_date;
	}

	/**
	 * @param create_bk_date 创建日期
	 */
	public void setCreate_bk_date(JaDate create_bk_date) {
		this.create_bk_date = create_bk_date;
	}

	/**
	 * @return af_flag
	 */
	public AF_FLAG getAf_flag() {
		return af_flag;
	}

	/**
	 * @param af_flag
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}
}
