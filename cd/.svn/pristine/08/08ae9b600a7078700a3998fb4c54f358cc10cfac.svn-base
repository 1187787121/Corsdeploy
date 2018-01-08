package com.wk.cd.module1.entity;

import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.Controller;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.enu.COMPONENT_SOURCE;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.EnumValue;

public class Component
		implements XmlEntity {

	// IoDH方式实现延迟加载
	private static class HolderClass {
		private static MoComponentDaoService moComponentDaoService = Controller.getInstance().getInjector()
				.getBean(MoComponentDaoService.class);
	}

	protected String id;
	public static final String COMPONENT_IDCN = "组件ID";

	protected String cn_name;
	public static final String COMPONENT_CN_NAMECN = "组件中文名";

	protected String bk_desc;
	public static final String COMPONENT_BK_DESCCN = "组件描述";

	protected IMPL_TYPE impl_type;
	public static final String IMPL_TYPECN = "执行类别";

	protected int[] component_purposes;
	public static final String COMPONENT_PURPOSESCN = "组件用途列表";

	protected COMPONENT_SOURCE component_source;
	public static final String COMPONENT_SOURCECN = "组件来源";

	protected List<Script> script_list;
	public static final String SCRIPT_LISTCN = "脚本列表";

	protected String file_path;
	public static final String FILE_PATHCN = "文件路径";

	protected String file_param;
	public static final String FILE_PARAMCN = "文件参数";

	protected List<Param> param_list;
	public static final String PARAM_LISTCN = "参数列表";

	protected List<Param> out_param_list;
	public static final String OUT_PARAM_LISTCN = "输出参数列表";

	protected String[] tag_list;
	public static final String TAG_LISTCN = "分类标签列表";

	protected String check_comp_id;
	public static final String CHECK_COMP_IDCN = "校验组件ID";

	protected String check_comp_name;
	public static final String CHECK_COMP_NAMECN = "校验组件中文名";

	@Override
	public void fromElement(Element element) {
		id = element.getAttribute(XmlTags.ID);
		cn_name = element.getAttribute(XmlTags.CNNAME);
		impl_type = XmlAdapter.getAttribute(element, XmlTags.IMPL_TYPE, IMPL_TYPE.class, true);
		component_source = XmlAdapter.getAttribute(element, XmlTags.COMPONENT_SOURCE, COMPONENT_SOURCE.class, true);
		bk_desc = XmlAdapter.getElementContent(element, XmlTags.DESC, false);
		if(!Assert.isEmpty(XmlAdapter.getElementContent(element, XmlTags.PURPOSES, false))) {
			String[] purposes = XmlAdapter.getElementContent(element, XmlTags.PURPOSES, false).split("、");
			component_purposes = new int[purposes.length];
			for (int i = 0; i < purposes.length; i++) {
				component_purposes[i] = EnumValue.valueOf(COMPONENT_PURPOSE.class, purposes[i]).getValue();
			}
		}
		script_list = XmlAdapter.getElementEntityList(element, XmlTags.SCRIPT, Script.class, false);
		file_path = XmlAdapter.getElementContent(element, XmlTags.FILE, false);
		file_param = XmlAdapter.getElementContent(element, XmlTags.FILE_PARAM, false);
		param_list = XmlAdapter.getElementEntityList(element, XmlTags.PARAM, Param.class, false);
		out_param_list = XmlAdapter.getElementEntityList(element, XmlTags.OUT_PARAM, Param.class, false);
		tag_list = StringUtil.str2strary(XmlAdapter.getElementContent(element, XmlTags.TAGS, false));
		Element check_ele = XmlAdapter.getElement(element, XmlTags.CHECK_COMP, false);
		if (Assert.notEmpty(check_ele)) {
			check_comp_id = check_ele.getAttribute(XmlTags.ID);
			MoComponentInfo info = HolderClass.moComponentDaoService.getInfoByKey(check_comp_id);
			if (Assert.notEmpty(info)) {
				check_comp_name = info.getComponent_cn_name();
			} else {
				check_comp_id = null;
			}
		}
	}

	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.COMPONENT;
		}
		Element component_ele = document.createElement(tag);
		component_ele.setAttribute(XmlTags.ID, id);
		component_ele.setAttribute(XmlTags.CNNAME, cn_name);
		XmlAdapter.setAttribute(component_ele, XmlTags.IMPL_TYPE, impl_type, true);
		XmlAdapter.setAttribute(component_ele, XmlTags.COMPONENT_SOURCE, component_source, true);
		Element purposes_ele = document.createElement(XmlTags.PURPOSES);
		if(!Assert.isEmpty(component_purposes)) {
			StringBuilder sb = new StringBuilder();
			for (int i : component_purposes) {
				sb.append(EnumValue.valueOf(COMPONENT_PURPOSE.class, i).getCname()).append("、");
			}
			sb.deleteCharAt(sb.length() - 1);
			purposes_ele.setTextContent(sb.toString());
			component_ele.appendChild(purposes_ele);
		}
		Element desc_ele = document.createElement(XmlTags.DESC);
		desc_ele.setTextContent(bk_desc.trim());
		component_ele.appendChild(desc_ele);
		if (!Assert.isEmpty(file_path)) {
			Element file_ele = document.createElement(XmlTags.FILE);
			file_ele.setTextContent(file_path.trim());
			component_ele.appendChild(file_ele);
		}
		if (!Assert.isEmpty(file_param)) {
			Element file_param_ele = document.createElement(XmlTags.FILE_PARAM);
			file_param_ele.setTextContent(file_param.trim());
		}
		if (Assert.notEmpty(tag_list)) {
			Element tags_ele = document.createElement(XmlTags.TAGS);
			tags_ele.setTextContent(StringUtil.ary2str(tag_list));
			component_ele.appendChild(tags_ele);
		}
		if (Assert.notEmpty(check_comp_id)) {
			MoComponentInfo info = HolderClass.moComponentDaoService.getInfoByKey(check_comp_id);
			if (Assert.notEmpty(info)) {
				Element check_ele = document.createElement(XmlTags.CHECK_COMP);
				check_ele.setAttribute(XmlTags.ID, info.getComponent_id());
				check_ele.setAttribute(XmlTags.CNNAME, info.getComponent_cn_name());
				component_ele.appendChild(check_ele);
			}
		}
		component_ele.appendChild(XmlAdapter.toDocumentFragment(document, null, script_list));
		component_ele.appendChild(XmlAdapter.toDocumentFragment(document, null, param_list));
		component_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.OUT_PARAM, out_param_list));
		return component_ele;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return cn_name
	 */
	public String getCn_name() {
		return cn_name;
	}

	/**
	 * @return bk_desc
	 */
	public String getBk_desc() {
		return bk_desc;
	}

	/**
	 * @return impl_type
	 */
	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	/**
	 * @return component_purposes
	 */
	public int[] getComponent_purposes() {
		return component_purposes;
	}

	/**
	 * @return component_source
	 */
	public COMPONENT_SOURCE getComponent_source() {
		return component_source;
	}

	/**
	 * @return script_list
	 */
	public List<Script> getScript_list() {
		return script_list;
	}

	/**
	 * @return file_path
	 */
	public String getFile_path() {
		return file_path;
	}

	/**
	 * @return param_list
	 */
	public List<Param> getParam_list() {
		return param_list;
	}

	/**
	 * @return out_param_list
	 */
	public List<Param> getOut_param_list() {
		return out_param_list;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param cn_name
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	/**
	 * @param bk_desc
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
	}

	/**
	 * @param impl_type
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @param component_purposes
	 */
	public void setComponent_purposes(int[] component_purposes) {
		this.component_purposes = component_purposes;
	}

	/**
	 * @param component_source
	 */
	public void setComponent_source(COMPONENT_SOURCE component_source) {
		this.component_source = component_source;
	}

	/**
	 * @param script_list
	 */
	public void setScript_list(List<Script> script_list) {
		this.script_list = script_list;
	}

	/**
	 * @param file_path
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<Param> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @param out_param_list
	 */
	public void setOut_param_list(List<Param> out_param_list) {
		this.out_param_list = out_param_list;
	}

	/**
	 * @return file_param
	 */
	public String getFile_param() {
		return file_param;
	}

	/**
	 * @param file_param
	 */
	public void setFile_param(String file_param) {
		this.file_param = file_param;
	}

	public String[] getTag_list() {
		return tag_list;
	}

	public void setTag_list(String[] tag_list) {
		this.tag_list = tag_list;
	}

	public String getCheck_comp_id() {
		return check_comp_id;
	}

	public void setCheck_comp_id(String check_comp_id) {
		this.check_comp_id = check_comp_id;
	}

	public String getCheck_comp_name() {
		return check_comp_name;
	}

	public void setCheck_comp_name(String check_comp_name) {
		this.check_comp_name = check_comp_name;
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", cn_name=" + cn_name + ", bk_desc=" + bk_desc + ", impl_type=" + impl_type
				+ ", component_purposes=" + Arrays.toString(component_purposes) + ", component_source="
				+ component_source + ", script_list=" + script_list + ", file_path=" + file_path + ", file_param="
				+ file_param + ", param_list=" + param_list + ", out_param_list=" + out_param_list + ", tag_list="
				+ Arrays.toString(tag_list) + "]";
	}

	/**
	 * 构造函数
	 * @param id
	 */
	public Component(String id) {
		super();
		this.id = id;
	}

	/**
	 * 构造函数
	 */
	public Component() {
		super();
	}

}
