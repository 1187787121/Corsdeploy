/**
 * Title: CfgTool.java
 * File Description: 配置文件工具类
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.wk.cd.exc.FileLoadErrorException;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.module1.exc.GetFileDocumentException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.GBKProperties;
import com.wk.util.JaDate;

/**
 * Class Description:配置文件工具类
 * @author lixl
 */
public class CfgTool {
	private static final Map<String, GBKProperties> proCache = new HashMap<String, GBKProperties>();
	public static final long FORK_WT = 2000L;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 加载项目默认配置文件
	 * @return
	 */
	public static GBKProperties getProperties() {
		return getProperties("./cms.properties");
	}
	
	/**
	 * 判断系统是否为测试模式
	 * @return boolean
	 */
	public static boolean isTestMode() {
		GBKProperties pro = getProperties();
		return pro.getBoolean("cms.test.mode", true);
	}

	/**
	 * 判断系统是否为集成模式
	 * @return boolean
	 */
	public static boolean isIntegrationMode() {
		GBKProperties pro = getProperties();
		return pro.getBoolean("cms.integration.mode", false);
	}

	/**
	 * 加载GBKProperties文件
	 * @param proName 配置文件名称
	 * @return
	 */
	public static GBKProperties getProperties(String proName) {
		InputStream in = null;
		try {
			GBKProperties pro = proCache.get(proName);
			if (pro == null) {
				in = CfgTool.class.getClassLoader().getResourceAsStream(proName);
				pro = new GBKProperties();
				pro.load(in);
				proCache.put(proName, pro);
			}
			return pro;
		} catch (IOException e) {
			throw new FileLoadErrorException().addScene("FILE", proName);

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new FileLoadErrorException().addScene("FILE", proName);
				}
			}
		}
	}

	public static void saveProperties(GBKProperties prop, String file_name) {
		File f = null;
		FileOutputStream fo = null;
		try {
			f = new File(file_name);
			fo = new FileOutputStream(f);
			prop.save(fo, null);
		} catch (IOException e) {
			logger.warn(e.getMessage());
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					logger.warn(e.getMessage());
				}
			}
		}
	}

	/**
	 * 获取项目根路径
	 * @return String
	 */
	public static String getProjectRootPath() {
		URL url = CfgTool.class.getClassLoader().getResource("cms.properties");
		File config_file = new File(url.getFile());
		return config_file.getParentFile().getParent();
	}

	public static Document getSystemParamDom(String xml_name) {
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new GetFileDocumentException();
		}
		InputStream is = CfgTool.class.getClassLoader().getResourceAsStream(xml_name);
		Document dom = null;
		try {
			dom = db.parse(is);
		} catch (SAXException e) {
			logger.warn(e.getMessage());
		} catch (IOException e) {
			logger.warn(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				logger.warn(e.getMessage());
			}
		}
		return dom;
	}

	/**
	 * 获取cms.properties配置
	 * @author lixl (2014-12-10)
	 * @param key
	 * @return String
	 */
	public static String getProjectPropterty(String key) {
		GBKProperties pro = getProperties();
		return pro.getProperty(key);
	}
	
	/**
	 * 清除配置文件缓存
	 * Description: 
	 * @param key
	 */
	public static void removeProCache(String key){
		proCache.remove(key);
	}
	
	/**
	 * Description: 获取前端路径
	 * @return
	 */
	public static String getWebRootPath() {
		String web_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		if (!web_path.endsWith("/")) {
			web_path = web_path + "/";
		}
		return web_path;
	}
	
	/**
	 * Description: 截取前端根路径
	 * @param path
	 */
	public static String replaceWebRootPath(String path){
		if(!Assert.isEmpty(path)){
			return path.replace(getWebRootPath(), "");
		}else{
			return null;
		}
	}

	public static void main(String[] args) {
		GBKProperties prop = new GBKProperties();
		prop.put("license",
				"29ABD3B7A72B294B0637A47D5848ACDC0FC100A4DA960DED0C80078CE27114755C8306A574F6E2AB7D4A0E4101D52CAAE515E09A47ED4B2168438FD24560BF00");
		prop.put("expire_end_date", "2016-10-10");
		prop.put("zh_name", "沃克软件");
		prop.put("name", "wk");
		prop.put("ip", "10.1.1.220");
		long beg = System.currentTimeMillis();
		saveProperties(prop, CfgTool.getProjectRootPath() + "/config/lic.properties");
		System.out.println((System.currentTimeMillis() - beg));

		GBKProperties pro = CfgTool.getProperties("./lic.properties");
		String zh_name = pro.getProperty("zh_name");
		String name = pro.getProperty("name");
		JaDate expire_end_date = JaDate.valueOf(pro.getProperty("expire_end_date"));
		String license = pro.getProperty("license");
		System.out.println((System.currentTimeMillis() - beg));
		String ip = pro.getProperty("ip");
	}
}
