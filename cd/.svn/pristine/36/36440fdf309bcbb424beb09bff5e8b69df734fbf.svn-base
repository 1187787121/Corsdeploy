/**
 * Title: Program.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月18日
 */
package com.wk.cd.module1.entity;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;

/**
 * Class Description:
 * @author yangl
 */
public class Program
		implements XmlEntity {

	/**
	 * 方案编号
	 */
	private String prog_id;

	public static final String PROG_IDCN = "方案编号";

	/**
	 * 方案中文名
	 */
	private String prog_name;

	public static final String PROG_NAMECN = "方案中文名";
	
	/**
	 * 发布模板
	 */
	private String pub_template_name;
	
	public static final String PUB_TEMPLATE_NAMECN = "发布模板";
	
	/**
	 * 回退模版
	 */
	private String rol_template_name;
	
	public static final String ROL_TEMPLATE_NAMECN = "回退模版";
	
	/**
	 *版本服务器名
	 */
	private String ver_server_name;

	public static final String VER_SERVER_NAMECN = "版本服务器名";

	/**
	 *版本数据源名
	 */
	private String ver_soc_name;

	public static final String VER_SOC_NAMECN = "版本数据源名";

	/**
	 *源码版本目录
	 */
	private String code_bk_dir;

	public static final String CODE_BK_DIRCN = "源码版本目录";

	/**
	 * 方案描述
	 */
//	private String prog_bk_desc;
//
//	public static final String PROG_BK_DESCCN = "方案描述";
	
	

	/**
	 * 方案类型
	 */
	private PROG_TYPE prog_type;

	public static final String PROG_TYPECN = "方案类型";

	/**
	 * 方案阶段列表
	 */
	private List<Phase> phase_list;

	public static final String PHASE_LISTCN = "方案阶段列表";

	/**
	 * 方案参数列表
	 */
	private List<PhaseParam> param_list;

	public static final String PARAM_LISTCN = "方案参数列表";

	/**
	 * 投产包类型列表
	 */
	private List<PackageTypeInfo> pac_type_list;

	public static final String PAC_TYPE_LISTCN = "投产包类型列表";

	/**
	 * Description:
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		prog_id = element.getAttribute(XmlTags.ID);
		prog_name = element.getAttribute(XmlTags.CNNAME);
		pub_template_name = element.getAttribute(XmlTags.PUB_TEMPLATE_NAME);
		rol_template_name = element.getAttribute(XmlTags.ROL_TEMPLATE_NAME);
		ver_server_name = element.getAttribute(XmlTags.VER_SERVER_NAME);
		ver_soc_name = element.getAttribute(XmlTags.VER_SOC_NAME);
		code_bk_dir = element.getAttribute(XmlTags.CODE_BK_DIR);
		
		prog_type = XmlAdapter.getAttribute(element, XmlTags.PROGRAM_TYPE, PROG_TYPE.class, true);
//		prog_bk_desc = XmlAdapter.getElementContent(element, XmlTags.DESC, false);
		phase_list = XmlAdapter.getElementEntityList(element, XmlTags.PHASE, Phase.class, false);
		param_list = XmlAdapter.getElementEntityList(element, XmlTags.PHASE_PARAM, PhaseParam.class, false);
		pac_type_list = XmlAdapter.getElementEntityList(element, XmlTags.PKTYPE, PackageTypeInfo.class, false);
	}

	/**
	 * Description:
	 * @param document
	 * @param tag
	 * @return
	 */
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.PROGRAM;
		}
		Element program_ele = document.createElement(tag);
		program_ele.setAttribute(XmlTags.ID, prog_id);
		program_ele.setAttribute(XmlTags.CNNAME, prog_name);
		program_ele.setAttribute(XmlTags.PUB_TEMPLATE_NAME, pub_template_name);
		program_ele.setAttribute(XmlTags.ROL_TEMPLATE_NAME, rol_template_name);
		program_ele.setAttribute(XmlTags.VER_SERVER_NAME, ver_server_name);
		program_ele.setAttribute(XmlTags.VER_SOC_NAME, ver_soc_name);
		program_ele.setAttribute(XmlTags.CODE_BK_DIR, code_bk_dir);
		XmlAdapter.setAttribute(program_ele, XmlTags.PROGRAM_TYPE, prog_type, true);
		
//		Element desc_ele = document.createElement(XmlTags.DESC);
//		desc_ele.setTextContent(prog_bk_desc.trim());
//		program_ele.appendChild(desc_ele);
		program_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PHASE, phase_list));
		program_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PHASE_PARAM, param_list));
		program_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PKTYPE, pac_type_list));
		return program_ele;
	}
	
	/**
	 * 构造函数
	 * @param prog_id
	 */
	public Program(String prog_id) {
		super();
		this.prog_id = prog_id;
	}

	/**
	 * 构造函数
	 */
	public Program() {
		super();
	}

	/**
	 * @return prog_id
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return prog_name
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	/**
	 * @return pub_template_name
	 */
	public String getPub_template_name() {
		return pub_template_name;
	}

	/**
	 * @param pub_template_name
	 */
	public void setPub_template_name(String pub_template_name) {
		this.pub_template_name = pub_template_name;
	}

	/**
	 * @return rol_template_name
	 */
	public String getRol_template_name() {
		return rol_template_name;
	}

	/**
	 * @param rol_template_name
	 */
	public void setRol_template_name(String rol_template_name) {
		this.rol_template_name = rol_template_name;
	}

	/**
	 * @return ver_server_name
	 */
	public String getVer_server_name() {
		return ver_server_name;
	}

	/**
	 * @param ver_server_name
	 */
	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	/**
	 * @return ver_soc_name
	 */
	public String getVer_soc_name() {
		return ver_soc_name;
	}

	/**
	 * @param ver_soc_name
	 */
	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	/**
	 * @return code_bk_dir
	 */
	public String getCode_bk_dir() {
		return code_bk_dir;
	}

	/**
	 * @param code_bk_dir
	 */
	public void setCode_bk_dir(String code_bk_dir) {
		this.code_bk_dir = code_bk_dir;
	}

	/**
	 * @return prog_type
	 */
	public PROG_TYPE getProg_type() {
		return prog_type;
	}

	/**
	 * @param prog_type
	 */
	public void setProg_type(PROG_TYPE prog_type) {
		this.prog_type = prog_type;
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
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "Program [prog_id=" + prog_id + ", prog_name=" + prog_name + ", pub_template_name=" + pub_template_name + ", rol_template_name="
				+ rol_template_name + ", ver_server_name=" + ver_server_name + ", ver_soc_name=" + ver_soc_name + ", code_bk_dir=" + code_bk_dir
				+ ", prog_type=" + prog_type + ", phase_list=" + phase_list + ", param_list=" + param_list + ", pac_type_list=" + pac_type_list + "]";
	}
	
}
