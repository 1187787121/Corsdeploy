/**
 * Title: Script.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年7月13日
 */
package com.wk.cd.module1.entity;

import java.util.Arrays;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.module1.xml.XmlTags;
import com.wk.cd.module1.xml1.ElementEntity;
import com.wk.cd.common.util.Assert;

/**
 * Class Description:
 * @author yangl
 */
public class Script
		implements ElementEntity {

	private String script_type;
	public static final String SCRIPT_TYPECN = "脚本类型";

	private String[] cmds;
	public static final String CMDSCN = "脚本命令列表";

	/**
	 * Description:
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		script_type = element.getAttribute(XmlTags.TYPE);
		cmds = element.getTextContent().split("\n");
	}

	/**
	 * Description:
	 * @param document
	 * @return
	 */
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.SCRIPT;
		}
		Element script_ele = document.createElement(tag);
		script_ele.setAttribute(XmlTags.TYPE, script_type);
		StringBuffer sb = new StringBuffer();
		if (!Assert.isEmpty(cmds)) {
			for (String cmd : cmds) {
				sb.append(cmd);
				sb.append("\n");
			}
		}
		script_ele.setTextContent(sb.toString());
		return script_ele;
	}

	/**
	 * @return script_type
	 */
	public String getScript_type() {
		return script_type;
	}

	/**
	 * @param script_type
	 */
	public void setScript_type(String script_type) {
		this.script_type = script_type;
	}

	/**
	 * @return cmds
	 */
	public String[] getCmds() {
		return cmds;
	}

	/**
	 * @param cmds
	 */
	public void setCmds(String[] cmds) {
		this.cmds = cmds;
	}

	/**
	 * 构造函数
	 */
	public Script() {
		super();
	}

	/**
	 * 构造函数
	 * @param script_type
	 * @param cmds
	 */
	public Script(String script_type, String[] cmds) {
		super();
		this.script_type = script_type;
		this.cmds = cmds;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "Script [script_type=" + script_type + ", cmds=" + Arrays.toString(cmds) + "]";
	}

}
