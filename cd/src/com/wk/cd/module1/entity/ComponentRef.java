/**
 * Title: ComponentRef.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017��8��16��
 */
package com.wk.cd.module1.entity;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.Controller;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.common.util.Assert;

/**
 * Class Description:
 * @author yangl
 */
public class ComponentRef
		extends Component {
	
	private MoComponentDaoService moComponentDaoService = Controller.getInstance().getInjector().getBean(MoComponentDaoService.class);

	@Override
	public void fromElement(Element element) {
		id = element.getAttribute(XmlTags.ID);
		cn_name = element.getAttribute(XmlTags.CNNAME);
		List<String> db_id = moComponentDaoService.getIdByName(cn_name);
		if (!Assert.isEmpty(db_id)) {
			Component component = new Component(id);
			XmlReader.read(component);
			id = component.getId();
			impl_type = component.getImpl_type();
			component_source = component.getComponent_source();
			bk_desc = component.getBk_desc();
			component_purposes = component.getComponent_purposes();
			script_list = component.getScript_list();
			file_path = component.getFile_path();
			file_param = component.getFile_param();
			param_list = component.getParam_list();
			out_param_list = component.getOut_param_list();
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
		return component_ele;
	}

}
