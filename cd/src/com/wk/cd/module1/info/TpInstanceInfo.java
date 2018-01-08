/**
 * Title: TpInstanceInfo.java
 * File Description: 投产模版实例表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-31
 */

package com.wk.cd.module1.info;

import java.io.Serializable;

import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:投产模版实例表
 * @author AutoGen
 */
@Table("TP_INSTANCE")
@PrimaryKey({"business_sys_name","project_name","template_type"})
public class TpInstanceInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "投产模版实例表";

	/**
	 *业务系统
	 */
	private String business_sys_name;

	public static final String BUSINESS_SYS_NAMECN = "业务系统";

	/**
	 *项目编号
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "项目编号";

	/**
	 *模版类型
	 */
	private TEMPLATE_TYPE template_type;

	public static final String TEMPLATE_TYPECN = "模版类型";

	/**
	 *脚本文件
	 */
	private String script_file_path;

	public static final String SCRIPT_FILE_PATHCN = "脚本文件";

	/**
	 *模版名称
	 */
	private String template_name;

	public static final String TEMPLATE_NAMECN = "模版名称";

	/**
	 *清单存放目录
	 */
	private String prolist_bk_dir;

	public static final String PROLIST_BK_DIRCN = "清单存放目录";

	/**
	 *@return business_sys_name 业务系统
	 */
	public String getBusiness_sys_name() {
		return this.business_sys_name;
	}

	/**
	 *@param business_sys_name 业务系统
	 */
	public void setBusiness_sys_name(String business_sys_name) {
		this.business_sys_name = business_sys_name;
	}

	/**
	 *@return project_name 项目编号
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return template_type 模版类型
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return this.template_type;
	}

	/**
	 *@param template_type 模版类型
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 *@return script_file_path 脚本文件
	 */
	public String getScript_file_path() {
		return this.script_file_path;
	}

	/**
	 *@param script_file_path 脚本文件
	 */
	public void setScript_file_path(String script_file_path) {
		this.script_file_path = script_file_path;
	}

	/**
	 *@return template_name 模版名称
	 */
	public String getTemplate_name() {
		return this.template_name;
	}

	/**
	 *@param template_name 模版名称
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 *@return prolist_bk_dir 清单存放目录
	 */
	public String getProlist_bk_dir() {
		return this.prolist_bk_dir;
	}

	/**
	 *@param prolist_bk_dir 清单存放目录
	 */
	public void setProlist_bk_dir(String prolist_bk_dir) {
		this.prolist_bk_dir = prolist_bk_dir;
	}

}
