package com.wk.cd.module1.info;

import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.enu.TEMPLATE_FORMATE;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

import java.io.Serializable;

@Table("MO_TEMPLATE")
@PrimaryKey({"template_name"})
public class MoTemplateInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  /**
	 *表名称
	 */
	public static final String TABLECN = "投产模版表";

	/**
	 *模版名称
	 */
	private String template_name;

	public static final String TEMPLATE_NAMECN = "模版名称";

	/**
	 *模板格式
	 */
	private TEMPLATE_FORMATE template_formate;

	public static final String TEMPLATE_FORMATECN = "模板格式";

	/**
	 *模版中文名
	 */
	private String template_cn_name;

	public static final String TEMPLATE_CN_NAMECN = "模版中文名";

	/**
	 *模板类名
	 */
	private String tp_class_name;

	public static final String TP_CLASS_NAMECN = "模板类名";

	/**
	 *模版路径
	 */
	private String script_file_path;

	public static final String SCRIPT_FILE_PATHCN = "模版路径";

	/**
	 *引用ID
	 */
	private String ref_module_id;

	public static final String REF_MODULE_IDCN = "引用ID";

	/**
	 *模版类型
	 */
	private TEMPLATE_TYPE template_type;

	public static final String TEMPLATE_TYPECN = "模版类型";

	/**
	 *操作系统
	 */
	private String operating_system;

	public static final String OPERATING_SYSTEMCN = "操作系统";

	/**
	 *发布状态
	 */
	private PUBLISH_STATE publish_state;

	public static final String PUBLISH_STATECN = "发布状态";
	
	/**
	 *模版描述
	 */
	private String template_bk_desc;

	public static final String TEMPLATE_BK_DESCCN = "模版描述";

	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";

	/**
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

	/**
	 *修改日期
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 *修改时间
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "修改时间";

	/**
	 *修改用户
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "修改用户";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return template_formate 模板格式
	 */
	public TEMPLATE_FORMATE getTemplate_formate() {
		return this.template_formate;
	}

	/**
	 *@param template_formate 模板格式
	 */
	public void setTemplate_formate(TEMPLATE_FORMATE template_formate) {
		this.template_formate = template_formate;
	}

	/**
	 *@return template_cn_name 模版中文名
	 */
	public String getTemplate_cn_name() {
		return this.template_cn_name;
	}

	/**
	 *@param template_cn_name 模版中文名
	 */
	public void setTemplate_cn_name(String template_cn_name) {
		this.template_cn_name = template_cn_name;
	}

	/**
	 *@return tp_class_name 模板类名
	 */
	public String getTp_class_name() {
		return this.tp_class_name;
	}

	/**
	 *@param tp_class_name 模板类名
	 */
	public void setTp_class_name(String tp_class_name) {
		this.tp_class_name = tp_class_name;
	}

	/**
	 *@return script_file_path 模版路径
	 */
	public String getScript_file_path() {
		return this.script_file_path;
	}

	/**
	 *@param script_file_path 模版路径
	 */
	public void setScript_file_path(String script_file_path) {
		this.script_file_path = script_file_path;
	}

	/**
	 *@return ref_module_id 引用ID
	 */
	public String getRef_module_id() {
		return this.ref_module_id;
	}

	/**
	 *@param ref_module_id 引用ID
	 */
	public void setRef_module_id(String ref_module_id) {
		this.ref_module_id = ref_module_id;
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
	 *@return operating_system 操作系统
	 */
	public String getOperating_system() {
		return this.operating_system;
	}

	/**
	 *@param operating_system 操作系统
	 */
	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}

	/**
	 *@return publish_state 发布状态
	 */
	public PUBLISH_STATE getPublish_state() {
		return this.publish_state;
	}

	/**
	 *@param publish_state 发布状态
	 */
	public void setPublish_state(PUBLISH_STATE publish_state) {
		this.publish_state = publish_state;
	}
	
	/**
	 *@return template_bk_desc 模版描述
	 */
	public String getTemplate_bk_desc() {
		return this.template_bk_desc;
	}

	/**
	 *@param template_bk_desc 模版描述
	 */
	public void setTemplate_bk_desc(String template_bk_desc) {
		this.template_bk_desc = template_bk_desc;
	}

	/**
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return crt_user_id 创建用户
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建用户
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return modify_user_id 修改用户
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id 修改用户
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
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