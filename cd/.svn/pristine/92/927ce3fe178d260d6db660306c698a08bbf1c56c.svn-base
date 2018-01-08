/**
 * Title: PackageTypeInfo.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017年3月22日
 */
package com.wk.cd.module1.info;

import java.io.Serializable;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.module1.xml1.ElementEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.info.PackageTypeInfo;

/**
 * Class Description:
 * @author zhangj
 */
public class PackageTypeInfo
		implements Serializable, ElementEntity {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6311731530400511725L;

	private String type_name;

	private String type_cn_name;

	private YN_FLAG gen_flag;

	private List<String> package_list;

	/**
	 * Description:
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		type_name = element.getAttribute(XmlTags.NAME);
		type_cn_name = element.getAttribute(XmlTags.CNNAME);
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
			tag = XmlTags.PKTYPE;
		}
		Element pkg_ele = document.createElement(tag);
		pkg_ele.setAttribute(XmlTags.NAME, type_name);
		pkg_ele.setAttribute(XmlTags.CNNAME, type_cn_name);
		return pkg_ele;
	}

	/**
	 * 构造函数
	 */
	public PackageTypeInfo() {
		super();
	}

	/**
	 * 构造函数
	 * @param type_name
	 * @param type_cn_name
	 */
	public PackageTypeInfo(String type_name, String type_cn_name) {
		super();
		this.type_name = type_name;
		this.type_cn_name = type_cn_name;
	}

	/**
	 * 构造函数
	 * @param type_name
	 * @param type_cn_name
	 * @param gen_flag
	 */
	public PackageTypeInfo(String type_name, String type_cn_name, YN_FLAG gen_flag) {
		super();
		this.type_name = type_name;
		this.type_cn_name = type_cn_name;
		this.gen_flag = gen_flag;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_cn_name() {
		return type_cn_name;
	}

	public void setType_cn_name(String type_cn_name) {
		this.type_cn_name = type_cn_name;
	}

	/**
	 * @return gen_flag
	 */
	public YN_FLAG getGen_flag() {
		return gen_flag;
	}

	/**
	 * @param gen_flag
	 */
	public void setGen_flag(YN_FLAG gen_flag) {
		this.gen_flag = gen_flag;
	}

	/**
	 * @return package_list
	 */
	public List<String> getPackage_list() {
		return package_list;
	}

	/**
	 * @param package_list
	 */
	public void setPackage_list(List<String> package_list) {
		this.package_list = package_list;
	}

	/**
	 * Description:
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PackageTypeInfo) {
			PackageTypeInfo o = (PackageTypeInfo) obj;
			return (type_name.equals(o.type_name) && type_cn_name.equals(o.type_cn_name));
		}
		return false;
	}

	/**
	 * Description:
	 * @return
	 */
	@Override
	public String toString() {
		return "PackageTypeInfo [type_name=" + type_name + ", type_cn_name=" + type_cn_name + ", gen_flag=" + gen_flag
				+ ", package_list=" + package_list + "]";
	}

}
