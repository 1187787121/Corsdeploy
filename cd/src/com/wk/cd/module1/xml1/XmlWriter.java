/**
 * Title: XmlWriter.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年08月10日
 */
package com.wk.cd.module1.xml1;

import java.io.File;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.w3c.dom.Document;

import com.wk.cd.module1.exc.WriterXmlException;
import com.wk.cd.module1.xml.XmlTags;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author "yangl"
 */
public class XmlWriter {

	private Transformer tf;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: Xml写入服务
	 * @param entity
	 */
	public static void write(XmlEntity entity) {
		Document document = DocumentFactory.newDocument();
		document.appendChild(entity.toElement(document, null));
		String xml_path = XmlPathUtil.getXmlPath(entity);
		write(document, xml_path);
	}

	private static void write(Document document, String file_path) {
		try {
			HolderClass.XMLWRITER.tf.transform(new DOMSource(document), new StreamResult(new File(file_path)));
		} catch (TransformerConfigurationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new WriterXmlException().addScene("PATH", file_path);
		} catch (TransformerException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new WriterXmlException().addScene("PATH", file_path);
		}
	}

	// IoDH方式实现DocumentFactory对象的单例模式
	private static class HolderClass {
		private static final XmlWriter XMLWRITER = new XmlWriter();
	}

	// 私有构造函数中实现文档构建相关对象的初始化
	private XmlWriter() {
		try {
			// 创建Transformer对象
			this.tf = TransformerFactory.newInstance().newTransformer();
			// 设置换行
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.ENCODING, "GBK");
			// 设置 script标签中的特殊字符不进行转义，原样输出，因为实际脚本中有很多 > < 类似的特殊字符
			tf.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, XmlTags.SCRIPT);
		} catch (TransformerConfigurationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
		}
	}
}
