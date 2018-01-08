/**
 * Title: XmlReader.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017Äê08ÔÂ10ÈÕ
 */
package com.wk.cd.module1.xml1;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author "yangl"
 */
public class XmlReader {

	private static final Log logger = LogFactory.getLog();

	public static <E extends XmlEntity> E  read(E entity) {
		String xml_path = XmlPathUtil.getXmlPath(entity);
		Document document = DocumentFactory.getDocument(xml_path);
		Element element = document.getDocumentElement();
		entity.fromElement(element);
		return entity;
	}

	public static <E extends XmlEntity> E read(String xml_path, Class<E> clas) {
		Document document = DocumentFactory.getDocument(xml_path);
		Element element = document.getDocumentElement();
		E entity = null;
		try {
			entity = clas.newInstance();
			entity.fromElement(element);
		} catch (InstantiationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
		} catch (IllegalAccessException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
		}
		return entity;
	}

}
