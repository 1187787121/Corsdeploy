/**
 * Title: XmlAdapter.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月18日
 */
package com.wk.cd.module1.xml1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.wk.cd.module1.exc.CheckElementNodeIsEmptyException;
import com.wk.cd.common.util.Assert;
import com.wk.db.EnumValue;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author yangl
 */
public class XmlAdapter {

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: Xml元素实体对象列表转换
	 * @param document
	 * @param entity_list
	 * @return
	 */
	public static DocumentFragment toDocumentFragment(Document document, String tag,
			List<? extends ElementEntity> entity_list) {
		DocumentFragment fragment = document.createDocumentFragment();
		if (!Assert.isEmpty(entity_list)) {
			for (ElementEntity entity : entity_list) {
				fragment.appendChild(entity.toElement(document, tag));
			}
		}
		return fragment;
	}

	public static Element getElement(Element element, String tag, boolean check_null) {
		NodeList node_list = element.getElementsByTagName(tag);
		if (node_list == null || node_list.getLength() == 0) {
			if (check_null) {
				throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG",
						tag);
			} else {
				return null;
			}
		}
		return (Element) node_list.item(0);
	}

	public static String getElementContent(Element element, String tag, boolean check_null) {
		NodeList node_list = element.getElementsByTagName(tag);
		if (node_list == null || node_list.getLength() == 0) {
			if (check_null) {
				throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG",
						tag);
			} else {
				return null;
			}
		}
		return ((Element) node_list.item(0)).getTextContent();
	}

	public static <E extends ElementEntity> List<E> getElementEntityList(Element element, String tag, Class<E> clas,
			boolean check_null) {
		NodeList node_list = element.getElementsByTagName(tag);
		if (node_list == null || node_list.getLength() == 0) {
			if (check_null) {
				throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG",
						tag);
			} else {
				return null;
			}
		}
		List<E> list = new ArrayList<E>();
		for (int i = 0; i < node_list.getLength(); i++) {
			E entity = null;
			try {
				entity = clas.newInstance();
				entity.fromElement((Element) node_list.item(i));
			} catch (InstantiationException e) {
				logger.debug(ExceptionUtils.getStackTrace(e));
			} catch (IllegalAccessException e) {
				logger.debug(ExceptionUtils.getStackTrace(e));
			}
			list.add(entity);
		}
		return list;
	}

	public static <E extends ElementEntity> E getElementEntity(Element element, String tag, Class<E> clas,
			boolean check_null) {
		Element ele = getElement(element, tag, check_null);
		E entity = null;
		if (!Assert.isEmpty(ele)) {
			try {
				entity = clas.newInstance();
				entity.fromElement(ele);
			} catch (InstantiationException e) {
				logger.debug(ExceptionUtils.getStackTrace(e));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.debug(ExceptionUtils.getStackTrace(e));
			}
		}
		return entity;
	}

	public static <E extends EnumValue<E>> void setAttribute(Element element, String tag, E enum_value,
			boolean check_null) {
		if (enum_value == null) {
			if (check_null) {
				throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG",
						tag);
			}
			return;
		}
		element.setAttribute(tag, enum_value.getCname());
	}

	public static <E extends EnumValue<E>> E getAttribute(Element element, String tag, Class<E> clas,
			boolean check_null) {
		String enum_cname = element.getAttribute(tag);
		if (Assert.isEmpty(enum_cname)) {
			if (check_null) {
				throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG",
						tag);
			}
			return null;
		}
		return EnumValue.valueOf(clas, enum_cname);
	}
	


}
