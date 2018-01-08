package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.info.ParamInfo;


public class TemplateInfo {
 
	/**
	 * 模板名
	 */
	private String cn_name;
	public static final String TEMPLATE_CN_NAMECN = "模板名";
	
	/**
	 * 模板ID
	 */
	private String id;
	public static final String TEMPLATE_IDCN = "模板ID";
	
	/**
	 * 模板类型
	 */
	private TEMPLATE_TYPE template_type;
	public static final String TEMPLATE_TYPECN = "模板类型";
	
	/**
	 * 操作系统
	 */
	private int[] operating_system;
	public static final String OPERATING_SYSTEMCN = "操作系统";
	
	/**
	 * 模板描述
	 */
	private String bk_desc;
	public static final String TEMPLATE_BK_DESCCN = "模板描述";
	
	/**
	 * 模板参数列表(只有全局参数)
	 */
	private ParamInfo[] params;
	public static final String TEMPLATE_PARAMSCN = "模板参数列表";
	
	/**
	 * 所有的参数 参数序号从0开始
	 */
	private ParamInfo[] all_params;
	
	public static final String ALL_PARAMSCN = "所有的参数";
	
	/**
	 * 投产包类型列表
	 */
	private List<PackageTypeInfo> package_types;
	
	public static final String PACKAGE_TYPESCN ="投产包类型列表";
		
	protected List<ModuleBasicInfo> modules = new ArrayList<ModuleBasicInfo>();
	public static final String TEMPLATE_MODULESCN = "模版配置信息";
	
	protected final List<ModuleInfo> sub_modules = new ArrayList<ModuleInfo>();

	public void addSub_moduleInfo(ModuleInfo info) {
		sub_modules.add(info);
	}
	
	public void addSub_moduleInfos(List<ModuleInfo> infos){
		sub_modules.addAll(infos);
	}

	public Iterator<ModuleInfo> submoduleIterator() {
		return sub_modules.iterator();
	}

	public List<ModuleInfo> getSub_ModuleInfos() {
		return sub_modules;
	}
	
	public List<ModuleBasicInfo> getModules(){
		return this.modules;
	}
	
	
	
	public List<PackageTypeInfo> getPackage_types() {
		return package_types;
	}

	public void setPackage_types(List<PackageTypeInfo> package_types) {
		this.package_types = package_types;
	}

	public void addModule(ModuleBasicInfo module) {
		this.modules.add(module);
	}
	
	public void addModules(List<ModuleBasicInfo> modules) {
		this.modules.addAll(modules);
	}
	
	public void setModules(List<ModuleBasicInfo> modules) {
		this.modules.clear();
		this.addModules(modules);
	}
	
	/**
	 * @return 模板名
	 */
	public String getCn_name() {
		return this.cn_name;
	}

	/**
	 * @param cn_name 模板名
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * @return  模板类型
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return this.template_type;
	}

	/**
	 * @param template_type  模板类型
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 * @return  操作系统
	 */
	public int[] getOperating_system() {
		return this.operating_system;
	}

	/**
	 * @param operating_system 操作系统
	 */
	public void setOperating_system(int[] operating_system) {
		this.operating_system = operating_system;
	}

	/** 
	 * @return  模板描述
	 */
	public String getBk_desc() {
		return bk_desc;
	}

	/** 
	 * @param bk_desc  模板描述
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
	}

	/** 
	 * @return  模板参数
	 */
	public ParamInfo[] getParams() {
		return params;
	}

	/**
	 * @param params 模板参数
	 */
	public void setParams(ParamInfo[] params) {
		this.params = params;
	}

	public ParamInfo[] getAll_params() {
		return all_params;
	}

	public void setAll_params(ParamInfo[] all_parmas) {
		this.all_params = all_parmas;
	}
	


}
 
