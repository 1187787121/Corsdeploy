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
	 * ģ����
	 */
	private String cn_name;
	public static final String TEMPLATE_CN_NAMECN = "ģ����";
	
	/**
	 * ģ��ID
	 */
	private String id;
	public static final String TEMPLATE_IDCN = "ģ��ID";
	
	/**
	 * ģ������
	 */
	private TEMPLATE_TYPE template_type;
	public static final String TEMPLATE_TYPECN = "ģ������";
	
	/**
	 * ����ϵͳ
	 */
	private int[] operating_system;
	public static final String OPERATING_SYSTEMCN = "����ϵͳ";
	
	/**
	 * ģ������
	 */
	private String bk_desc;
	public static final String TEMPLATE_BK_DESCCN = "ģ������";
	
	/**
	 * ģ������б�(ֻ��ȫ�ֲ���)
	 */
	private ParamInfo[] params;
	public static final String TEMPLATE_PARAMSCN = "ģ������б�";
	
	/**
	 * ���еĲ��� ������Ŵ�0��ʼ
	 */
	private ParamInfo[] all_params;
	
	public static final String ALL_PARAMSCN = "���еĲ���";
	
	/**
	 * Ͷ���������б�
	 */
	private List<PackageTypeInfo> package_types;
	
	public static final String PACKAGE_TYPESCN ="Ͷ���������б�";
		
	protected List<ModuleBasicInfo> modules = new ArrayList<ModuleBasicInfo>();
	public static final String TEMPLATE_MODULESCN = "ģ��������Ϣ";
	
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
	 * @return ģ����
	 */
	public String getCn_name() {
		return this.cn_name;
	}

	/**
	 * @param cn_name ģ����
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
	 * @return  ģ������
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return this.template_type;
	}

	/**
	 * @param template_type  ģ������
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 * @return  ����ϵͳ
	 */
	public int[] getOperating_system() {
		return this.operating_system;
	}

	/**
	 * @param operating_system ����ϵͳ
	 */
	public void setOperating_system(int[] operating_system) {
		this.operating_system = operating_system;
	}

	/** 
	 * @return  ģ������
	 */
	public String getBk_desc() {
		return bk_desc;
	}

	/** 
	 * @param bk_desc  ģ������
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
	}

	/** 
	 * @return  ģ�����
	 */
	public ParamInfo[] getParams() {
		return params;
	}

	/**
	 * @param params ģ�����
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
 
