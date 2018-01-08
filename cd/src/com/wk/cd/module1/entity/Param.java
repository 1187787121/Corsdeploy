package com.wk.cd.module1.entity;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.module1.xml1.ElementEntity;
import com.wk.cd.module1.xml1.XmlTags;

public class Param
		implements ElementEntity {

	protected String param_name;
	protected String param_cn_name;
	protected String param_bk_desc;
	protected String param_value;
	protected boolean sensitive_flag;

	/**
	 * Description:
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		param_name = element.getAttribute(XmlTags.NAME);
		param_cn_name = element.getAttribute(XmlTags.CNNAME);
		param_bk_desc = element.getAttribute(XmlTags.DESC);
		param_value = element.getAttribute(XmlTags.DEFAULT);
		sensitive_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.SENSITIVE));
		if (sensitive_flag) {
			param_value = DESUtil.decrypt(param_value);
		}
	}

	/**
	 * Description:
	 * @param document
	 * @return
	 */
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.PARAM;
		}
		Element param_ele = document.createElement(tag);
		param_ele.setAttribute(XmlTags.NAME, param_name);
		param_ele.setAttribute(XmlTags.CNNAME, param_cn_name);
		param_ele.setAttribute(XmlTags.DESC, param_bk_desc);
		String value = param_value;
		if (sensitive_flag) {
			value = DESUtil.encrypt(param_value);
		}
		param_ele.setAttribute(XmlTags.DEFAULT, value);
		param_ele.setAttribute(XmlTags.SENSITIVE, Boolean.toString(sensitive_flag));
		return param_ele;
	}

	/**
	 * @return param_name
	 */
	public String getParam_name() {
		return param_name;
	}

	/**
	 * @return param_cn_name
	 */
	public String getParam_cn_name() {
		return param_cn_name;
	}

	/**
	 * @return param_bk_desc
	 */
	public String getParam_bk_desc() {
		return param_bk_desc;
	}

	/**
	 * @return param_value
	 */
	public String getParam_value() {
		return param_value;
	}

	/**
	 * @param param_name
	 */
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	/**
	 * @param param_cn_name
	 */
	public void setParam_cn_name(String param_cn_name) {
		this.param_cn_name = param_cn_name;
	}

	/**
	 * @param param_bk_desc
	 */
	public void setParam_bk_desc(String param_bk_desc) {
		this.param_bk_desc = param_bk_desc;
	}

	/**
	 * @param param_value
	 */
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "Param [param_name=" + param_name + ", param_cn_name=" + param_cn_name + ", param_bk_desc="
				+ param_bk_desc + ", param_value=" + param_value + "]";
	}
	
	public boolean getSensitive_flag() {
		return sensitive_flag;
	}

	public void setSensitive_flag(boolean sensitive_flag) {
		this.sensitive_flag = sensitive_flag;
	}

    /**
     * 构造函数
     */
    public Param(Param param) {
        super();
        this.param_name = param.param_name;
        this.param_cn_name = param.param_cn_name;
        this.param_bk_desc = param.param_bk_desc;
        this.param_value = param.param_value;
        this.sensitive_flag  = param.sensitive_flag;
    }

    /**
     * 构造函数
     * @param param_name
     * @param param_cn_name
     * @param param_bk_desc
     * @param param_value
     * @param sensitive_flag
     */
	public Param(String param_name, String param_cn_name, String param_bk_desc, String param_value, boolean sensitive_flag) {
		super();
		this.param_name = param_name;
		this.param_cn_name = param_cn_name;
		this.param_bk_desc = param_bk_desc;
		this.param_value = param_value;
		this.sensitive_flag = sensitive_flag;
	}

	/**
	 * 构造函数
	 */
	public Param() {
		super();
	}

	public static List<Param> copy(List<Param> list) {
        List<Param> newList = new ArrayList<Param>();
        if (Assert.notEmpty(list)) {
            for (Param param : list) {
                if (param != null) {
                    newList.add(new Param(param));
                }
            }
        }
        return newList;
    }

}
