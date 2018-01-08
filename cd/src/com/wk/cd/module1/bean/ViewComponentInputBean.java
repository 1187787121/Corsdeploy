/**
 * Title: DeleteTpComponentInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月18日
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;

/**
 * Class Description:
 * @author yangl
 */
public class ViewComponentInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5134527483756364361L;
	
	/**
	 * 组件中文名
	 */
	private String component_cn_name;
	
	public static final String COMPONENT_CN_NAMECN = "组件中文名";

	/**
	 * 组件ID
	 */
	private String id;

	public static final String IDCN = "组件(组)ID";
	
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
	 * 组件ID列表
	 */
	private String[] id_list;

	public static final String ID_LISTCN = "ID列表";

	/**
	 * 查询关键字
	 */
	private String key_word;

	public static final String KEY_WORDCN = "查询关键字";
	
	/**
	 * 组件类型
	 */
	private MODULE_TYPE type;
	public static final String TYPECN = "组件类型";
	
	/**
	 * 组件用途
	 */
	private COMPONENT_PURPOSE component_purpose;
	public static final String COMPONENT_PURPOSECN = "组件用途列表";
	
	/**
	 * 文件路径
	 */
	private String file_path;
	public static final String FILE_PATHCN = "文件路径";
	
	/**
	 * 待校验参数
	 */
	private String data;

	public static final String DATACN = "待校验参数";
	
	/**
	 * 阶段列表
	 */
	private List<Phase> phase_list;
	
	public static final String PHASE_LISTCN = "方案阶段列表";

	/**
	 * 模板参数列表
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "方案参数列表";

	/**
	 * 投产包类型列表
	 */
	private List<PackageTypeInfo> pac_type_list;
	
	public static final String PAC_TYPE_LISTCN = "投产包类型列表";
	
	/**
	 * @return component_cn_name
	 */
	public String getComponent_cn_name() {
		return component_cn_name;
	}

	/**
	 * @param component_cn_name
	 */
	public void setComponent_cn_name(String component_cn_name) {
		this.component_cn_name = component_cn_name;
	}

	/**
	 * @return comp_id 组件ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id 组件ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return order_col_name
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @return order_type
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}

	/**
	 * @return key_word
	 */
	public String getKey_word() {
		return key_word;
	}

	/**
	 * @param order_col_name
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @param order_type
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}

	/**
	 * @param key_word
	 */
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}

	/**
	 * @return id_list
	 */
	public String[] getId_list() {
		return id_list;
	}

	/**
	 * @param id_list
	 */
	public void setId_list(String[] id_list) {
		this.id_list = id_list;
	}

	/**
	 * @return type
	 */
	public MODULE_TYPE getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(MODULE_TYPE type) {
		this.type = type;
	}

	/**
	 * @return component_purpose
	 */
	public COMPONENT_PURPOSE getComponent_purpose() {
		return component_purpose;
	}

	/**
	 * @param component_purpose
	 */
	public void setComponent_purpose(COMPONENT_PURPOSE component_purpose) {
		this.component_purpose = component_purpose;
	}

	/**
	 * @return file_path
	 */
	public String getFile_path() {
		return file_path;
	}

	/**
	 * @param file_path
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
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
	 * @return phase_list
	 */
	public List<Phase> getPhase_list() {
		return phase_list;
	}

	/**
	 * @param phase_list
	 */
	public void setPhase_list(List<Phase> phase_list) {
		this.phase_list = phase_list;
	}

	/**
	 * @return param_list
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return pac_type_list
	 */
	public List<PackageTypeInfo> getPac_type_list() {
		return pac_type_list;
	}

	/**
	 * @param pac_type_list
	 */
	public void setPac_type_list(List<PackageTypeInfo> pac_type_list) {
		this.pac_type_list = pac_type_list;
	}
}
