/**
 * Title: CeSystemTemplateInfo.java
 * File Description: 应用系统模板表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:应用系统模板表
 * @author AutoGen
 */
@Table("CE_SYSTEM_TEMPLATE")
@PrimaryKey({"sys_name","template_type","template_name"})
public class CeSystemTemplateInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "应用系统模板表";

	/**
	 *应用系统名称
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 *模板类型
	 */
	private TEMPLATE_TYPE template_type;

	public static final String TEMPLATE_TYPECN = "模板类型";

	/**
	 *模板名称
	 */
	private String template_name;

	public static final String TEMPLATE_NAMECN = "模板名称";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return template_type 模板类型
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return this.template_type;
	}

	/**
	 *@param template_type 模板类型
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 *@return template_name 模板名称
	 */
	public String getTemplate_name() {
		return this.template_name;
	}

	/**
	 *@param template_name 模板名称
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
