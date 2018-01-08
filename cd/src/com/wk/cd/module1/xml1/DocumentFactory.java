/**
 * Title: DocumentUtils.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月10日
 */
package com.wk.cd.module1.xml1;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.wk.cd.module1.exc.GetFileDocumentException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author yangl
 */
public class DocumentFactory {

	private static final DocumentFactory DOCUMENTFACTORY = new DocumentFactory();
	private static final Log logger = LogFactory.getLog();

	private DocumentBuilder db;

	/**
	 * Description: 构造一个新的Document对象
	 * @return
	 */
	public static Document newDocument() {
		return DOCUMENTFACTORY.db.newDocument();
	}

	/**
	 * Description: 从现在的Xml文件构造Document对象
	 * @param file_path Xml文件路径
	 * @return
	 */
	public static Document getDocument(String file_path) {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file_path);
		} catch (FileNotFoundException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new FileNotExistException().addScene("PATH", file_path);
		} catch (SAXException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new GetFileDocumentException().addScene("PATH", file_path);
		} catch (IOException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new GetFileDocumentException().addScene("PATH", file_path);
		} catch (ParserConfigurationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new GetFileDocumentException().addScene("PATH", file_path);
		}
	}

	// 私有构造函数中实现文档构建相关对象的初始化
	private DocumentFactory() {
		try {
			this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
		}
	}
}
