/**
 * Title: XmlPathUtil.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月27日
 */
package com.wk.cd.module1.xml;

import java.io.File;

import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author zhangj
 */
public class XmlPathUtil {
	private static final Log logger = LogFactory.getLog();
	/**
	 * Description: 根据导入文件的相对路径 获取导入文件所在的全路径。
	 * @param relative_path
	 * @return
	 */
	public static String getImportFileFullPath(String relative_path) {
		String web_path = CfgTool.getWebRootPath();
		String file_path = web_path + relative_path;
		return file_path;
	}

	/**
	 * Description:获得组件文件路径
	 * @param id
	 * @param comp_type
	 * @return
	 */
	public static String getPathByCompId(String id, MODULE_TYPE comp_type) {
		String web_path = CfgTool.getWebRootPath();
		String file_path = web_path + getRelativeDirByCompType(comp_type) + "/" + id + ".xml";
		logger.debug("file_path[{}]",file_path);
		return file_path;
	}

	/**
	 * Description:获得组件文件相对路径
	 * @param id
	 * @param comp_type
	 * @return
	 */
	public static String getRelativePathByCompId(String id, MODULE_TYPE comp_type) {
		String file_path = getRelativeDirByCompType(comp_type);
		file_path = file_path + "/" + id + ".xml";
		return file_path;
	}
	
	/**
	 * Description: 获得采集组件路径
	 * @param id
	 * @param comp_type
	 * @return
	 */
	public static  String getctPathByCompId(String id, MODULE_TYPE comp_type) {
		String web_path = CfgTool.getWebRootPath();
		String file_path = getRelativeDirByCompType(comp_type);
		file_path = web_path + file_path + "/" + id + ".xml";
		return file_path;
	}
	/**
	 * Description: 根据组件类型获取其相对存放目录
	 * @param comp_type
	 * @return
	 */
	public static String getRelativeDirByCompType(MODULE_TYPE comp_type) {
		String web_path = CfgTool.getWebRootPath();
		String file_dir = "comp/";
		if (MODULE_TYPE.COMPONENT.equals(comp_type)) {
			file_dir = file_dir + "component";
		} else if (MODULE_TYPE.GROUP.equals(comp_type)) {
			file_dir = file_dir + "group";
		} else if (MODULE_TYPE.TEMPLATE.equals(comp_type)) {
			file_dir = file_dir + "template";
		} else if (MODULE_TYPE.INSTANCE.equals(comp_type)) {
			file_dir = file_dir + "instance";
		} else if(MODULE_TYPE.PROGRAM.equals(comp_type)){
			file_dir = file_dir + "program";
		} else if(MODULE_TYPE.COLLECT.equals(comp_type)){
			file_dir = file_dir + "collect";
		}else {
			file_dir = file_dir + comp_type.getName();
		}
		File dir_path = new File(web_path + file_dir + "/temp/");
		if (!dir_path.exists()) {
			dir_path.mkdirs();
		}
		return file_dir;
	}
	
	/**
	 * Description: 根据组件类型获取其相对存放目录
	 * @param comp_type
	 * @return
	 */
	public static String getCtRelativeDirByType(MODULE_TYPE comp_type){
		String web_path = CfgTool.getWebRootPath();
		String file_dir = "comp/";
		if (MODULE_TYPE.COMPONENT.equals(comp_type)) {
			file_dir = file_dir + "component";
		}
		File dir_path = new File(web_path + file_dir + "/collect/");
		if (!dir_path.exists()) {
			dir_path.mkdirs();
		}
		return file_dir;
	}

	/**
	 * Description:测试用的临时目录
	 * @return
	 */

	public static String getTestPacakageDirectory() {
		String file_path = "";
		String web_root_dir = CfgTool.getWebRootPath();
		file_path = web_root_dir + getTestRelativeDir();
		File file = new File(file_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file_path;
	}

	/**
	 * Description: 获取测试的相对路径
	 * @param id
	 * @return
	 */
	public static String getTestRelativeDir() {
		return "module/testExecute/";
	}

}
