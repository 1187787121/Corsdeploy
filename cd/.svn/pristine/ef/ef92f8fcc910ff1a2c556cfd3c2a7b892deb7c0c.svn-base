package com.wk.cd.module1.entity;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.common.util.Assert;
import com.wk.db.EnumValue;

public class Template implements XmlEntity{
	/**
	 * 模板名
	 */
	private String template_cn_name;
	
	public static final String TEMPLATE_CN_NAMECN = "模板名";
	
	/**
	 * 模板ID
	 */
	private String template_id;
	
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
	private String template_bk_desc;
	
	public static final String TEMPLATE_BK_DESCCN = "模板描述";
	
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
	
	
	@Override
	public void fromElement(Element element) {
		
		template_id = element.getAttribute(XmlTags.ID);
		template_cn_name = element.getAttribute(XmlTags.CNNAME);
		template_type = XmlAdapter.getAttribute(element, XmlTags.TEMPLATE_TYPE, TEMPLATE_TYPE.class, true);
		String[] system = XmlAdapter.getElementContent(element, XmlTags.OPERATING_SYSTEM, true).split("\\|");
		operating_system = new int[system.length];
		for (int i = 0; i < system.length; i++) {
			operating_system[i] = Integer.parseInt(system[i]);
		}
		template_bk_desc = element.getAttribute(XmlTags.BK_DESC);
		phase_list = XmlAdapter.getElementEntityList(element, XmlTags.PHASE, Phase.class, false);
		param_list = XmlAdapter.getElementEntityList(element, XmlTags.PHASE_PARAM, PhaseParam.class, false);
		pac_type_list = XmlAdapter.getElementEntityList(element, XmlTags.PKTYPE, PackageTypeInfo.class, false);
		
	}
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.TEMPLATE;
		}
		Element template_ele = document.createElement(tag);
		template_ele.setAttribute(XmlTags.ID, template_id);
		template_ele.setAttribute(XmlTags.CNNAME, template_cn_name);
		Element system_ele = document.createElement(XmlTags.OPERATING_SYSTEM);
		if(!Assert.isEmpty(operating_system)) {
			StringBuilder sb = new StringBuilder();
			for (int i : operating_system) {
				sb.append(i).append("|");
			}
			sb.deleteCharAt(sb.length() - 1);
			system_ele.setTextContent(sb.toString());
			template_ele.appendChild(system_ele);
		}
		
		XmlAdapter.setAttribute(template_ele, XmlTags.TEMPLATE_TYPE, template_type, true);
		template_ele.setAttribute(XmlTags.BK_DESC, template_bk_desc);
		template_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PHASE, phase_list));
		template_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PHASE_PARAM, param_list));
		template_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PKTYPE, pac_type_list));
		return template_ele;
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
	 * @return template_id
	 */
	public String getTemplate_id() {
		return template_id;
	}
	/**
	 * @param template_id
	 */
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	/**
	 * @return template_type
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return template_type;
	}
	/**
	 * @param template_type
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}
	/**
	 * @return operating_system
	 */
	public int[] getOperating_system() {
		return operating_system;
	}
	/**
	 * @param operating_system
	 */
	public void setOperating_system(int[] operating_system) {
		this.operating_system = operating_system;
	}
	/**
	 * @return template_bk_desc
	 */
	public String getTemplate_bk_desc() {
		return template_bk_desc;
	}
	/**
	 * @param template_bk_desc
	 */
	public void setTemplate_bk_desc(String template_bk_desc) {
		this.template_bk_desc = template_bk_desc;
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
	
	/**
	 * 构造函数
	 */
	public Template() {
		super();
	}
	/**
	 * 构造函数
	 * @param template_id
	 */
	public Template(String template_id) {
		super();
		this.template_id = template_id;
	}
	
	public Template copy(Template template) {
		Template new_tem = new Template();
		new_tem.setOperating_system(template.getOperating_system());
		new_tem.setPac_type_list(template.getPac_type_list());
		new_tem.setParam_list(template.getParam_list());
//		new_tem.setPhase_list(template.getPhase_list());
		new_tem.setTemplate_bk_desc(template.getTemplate_bk_desc());
		new_tem.setTemplate_cn_name(template.getTemplate_cn_name());
		new_tem.setTemplate_id(template.getTemplate_id());
		new_tem.setTemplate_type(template.getTemplate_type());
		return new_tem;
	}
	
}
