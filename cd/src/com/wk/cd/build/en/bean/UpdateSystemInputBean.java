/**
 * Title: UpdateCeSystemAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.enu.SYS_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改应用系统输入接口
 * @author chiss
 */
public class UpdateSystemInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -4552851915280098737L;

	/**
	 * 应用系统名称
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN = "应用系统名称";
	
	/**
	 * 应用系统简称
	 */
	private String sys_cn_name;
	
	public static final String SYS_CN_NAMECN = "应用系统简称";
	
	/**
	 * 应用系统描述
	 */
	private String sys_bk_desc;
	
	public static final String SYS_BK_DESCCN = "应用系统描述";
	
	
	/**
	 * 应用系统类型
	 */
	private SYS_TYPE sys_type;
	
	public static final String SYS_TYPECN = "应用系统类型";
	
	
	/**
	 * 应用系统模板列表
	 */

	private List<SystemTemplateBean> sys_list;
	
	public static final String SYS_LISTCN = "应用系统模板列表";
	
	/**
	 * 应用系统文件名列表
	 */
	private String[] file_list;
	
	public static final String FILE_LISTCN = "应用系统文件名列表";

	/**
	 * @return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return sys_bk_desc 应用系统描述
	 */
	public String getSys_bk_desc() {
		return sys_bk_desc;
	}

	/**
	 * @param sys_bk_desc 应用系统描述
	 */
	public void setSys_bk_desc(String sys_bk_desc) {
		this.sys_bk_desc = sys_bk_desc;
	}

	/**
	 * @return sys_type 应用系统类型
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type 应用系统类型
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return sys_list 应用系统模板列表
	 */
	public List<SystemTemplateBean> getSys_list() {
		return sys_list;
	}

	/**
	 * @param sys_list 应用系统模板列表
	 */
	public void setSys_list(List<SystemTemplateBean> sys_list) {
		this.sys_list = sys_list;
	}

	/**
	 * @return file_list 应用系统文件名列表
	 */
	public String[] getFile_list() {
		return file_list;
	}

	/**
	 * @param file_list 应用系统文件名列表
	 */
	public void setFile_list(String[] file_list) {
		this.file_list = file_list;
	}

}
