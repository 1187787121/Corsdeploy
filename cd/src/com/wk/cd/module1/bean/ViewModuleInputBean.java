/**
 * Title: ViewCompInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月19日
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.ORDER_TYPE;

/**
 * Class Description:
 * @author xuph
 */
public class ViewModuleInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : -3181209154554978088L
	 */
	private static final long serialVersionUID = -3181209154554978088L;

	/**
	 * 查询关键字
	 */
	private String key_word;

	public static final String KEY_WORDCN = "查询关键字";

	/**
	 * 组件ID
	 */
	private String id;

	public static final String ID_CN = "ID";

	/**
	 * 组件ID列表
	 */
	private String[] id_list;

	public static final String ID_LISTCN = "ID列表";

	/**
	 * 组件类型
	 */
	private MODULE_TYPE type;

	public static final String MODULE_TYPECN = "组件类型";
	
//	/**
//	 * 组件用途
//	 */
//	private MODULE_PURPOSE module_purpose;
//	
//	public static final String MODULE_PURPOSE = "组件用途";
	
	/**
	 * 模板名称
	 */
	private String template_cn_name;
	
	public static final String CN_NAMECN = "模板名称";
	
	/**
	 * 节点IP
	 */
	private String soc_ip;
	
	public static final String SERVER_IPCN = "节点IP";
	
	/**
	 * 执行类别
	 */
	private IMPL_TYPE impl_type;
	
	public static final String IMPL_TYPECN = "执行类别";
	
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
	 * 组件组信息列表
	 */
	private List<ModuleBasicInfo> groups;

	public static final String GROUPSCN = "组件组信息列表";
	
	/**
	 * 参数列表
	 */
	private ParamInfo[] params;

	public static final String PARAMSCN = "参数列表";

	/**
	 * @return key_word 查询关键字
	 */
	public String getKey_word() {
		return this.key_word;
	}

	/**
	 * @param key_word 查询关键字
	 */
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	/**
	 * @return id 组件或组件组ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id 组件或组件组ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return comp_id_list 组件ID列表
	 */
	public String[] getId_list() {
		return this.id_list;
	}

	/**
	 * @param id_list 组件ID列表
	 */
	public void setId_list(String[] id_list) {
		this.id_list = id_list;
	}

	/**
	 * @return module_type 组件类型
	 */
	public MODULE_TYPE getType() {
		return this.type;
	}

	/**
	 * @param type 组件类型
	 */
	public void setType(MODULE_TYPE type) {
		this.type = type;
	}
	
	
	/**
	 * @return template_cn_name
	 */
	public String getTemplate_cn_name() {
		return template_cn_name;
	}

	/**
	 * @param template_cn_name
	 */
	public void setTemplate_cn_name(String template_cn_name) {
		this.template_cn_name = template_cn_name;
	}

	/**
	 * @return soc_ip
	 */
	public String getSoc_ip() {
		return soc_ip;
	}

	/**
	 * @param soc_ip
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
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

	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return groups
	 */
	public List<ModuleBasicInfo> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 */
	public void setGroups(List<ModuleBasicInfo> groups) {
		this.groups = groups;
	}

	/**
	 * @return params 参数列表
	 */
	public ParamInfo[] getParams() {
		return params;
	}

	/**
	 * @param params 参数列表
	 */
	public void setParams(ParamInfo[] params) {
		this.params = params;
	}

//	public MODULE_PURPOSE getModule_purpose() {
//		return module_purpose;
//	}
//
//	public void setModule_purpose(MODULE_PURPOSE module_purpose) {
//		this.module_purpose = module_purpose;
//	}
}
