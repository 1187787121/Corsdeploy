/**
 * Title: XmlPathUtil.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月27日
 */
package com.wk.cd.module1.xml1;

import java.io.File;

import com.wk.cd.module1.entity.Component;
import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.enu.MODULE_TYPE;
//import com.wk.cd.business.entity.Program;
import com.wk.cd.common.util.CfgTool;

/**
 * Class Description:
 * @author zhangj
 */
public class XmlPathUtil {
	/**
	 * Description:根据类对象获得组件文件相对路径
	 * @param entity
	 * @return
	 */
	public static String getXmlPath(XmlEntity entity) {
		return CfgTool.getWebRootPath() + getXmlRelativePath(entity);
	}

	/**
	 * Description:根据类对象获得组件文件相对路径
	 * @param entity
	 * @return
	 */
	public static String getXmlRelativePath(XmlEntity entity) {
		String name = null;
		MODULE_TYPE module_type = null;
		if (entity instanceof Component) {
			name = ((Component) entity).getId();
			module_type = MODULE_TYPE.COMPONENT;
		} else if (entity instanceof ComponentGroup) {
			name = ((ComponentGroup) entity).getId();
			module_type = MODULE_TYPE.GROUP;
		} else if(entity instanceof Instance) {
			name = ((Instance) entity).getInstance_id();
			module_type = MODULE_TYPE.INSTANCE;
		} else if (entity instanceof Template) {
			name = ((Template) entity).getTemplate_id();
			module_type = MODULE_TYPE.TEMPLATE;
		} else if (entity instanceof Program) {
			name = ((Program) entity).getProg_id();
			module_type = MODULE_TYPE.PROGRAM;
		}
		String file_path = getRelativeDirByModuleType(module_type) + "/" + getFileName(name);
		File dir = new File(CfgTool.getWebRootPath() + getRelativeDirByModuleType(module_type));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return file_path;
	}

	/**
	 * Description: 根据组件类型获取其相对上传目录
	 * @param module_type
	 * @return
	 */
	public static String getRelativeUploadDirByModuleType(MODULE_TYPE module_type) {
		String web_path = CfgTool.getWebRootPath();
		String upload_path = getRelativeDirByModuleType(module_type) + "/temp";
		String file_dir = web_path + upload_path;
		File dir = new File(file_dir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return upload_path;
	}

	/**
	 * Description: 根据组件类型获取其相对上传目录
	 * @param module_type
	 * @return
	 */
	public static String getUploadDirByModuleType(MODULE_TYPE module_type) {
		String web_path = CfgTool.getWebRootPath();
		String upload_path = web_path + getRelativeUploadDirByModuleType(module_type);
		return upload_path;
	}

	/**
	 * Description: 获取组件文件存放目录
	 * @param id
	 * @return
	 */
	public static String getComponentFileRelativeDir(String id) {
		String file_dir = getRelativeDirByModuleType(MODULE_TYPE.COMPONENT) + "/" + id;
		return file_dir;
	}

	// /**
	// * Description: 根据组件类型获取其存放目录
	// * @param module_type
	// * @return
	// */
	// private static String getDirByModuleType(MODULE_TYPE module_type) {
	// String web_path = CfgTool.getWebRootPath();
	// String file_dir = web_path + getRelativeDirByModuleType(module_type);
	// return file_dir;
	// }

	/**
	 * Description: 根据组件类型获取其相对存放目录
	 * @param module_type
	 * @return
	 */
	private static String getRelativeDirByModuleType(MODULE_TYPE module_type) {
		String file_dir = "module/" + module_type.getName();
		return file_dir;
	}

	private static String getFileName(String name) {
		return name + ".xml";
	}

	/**
	 * Description: 获取导出相对路径
	 * @param id
	 * @param comp_type
	 * @return
	 */
	public static String getRelativePathByCompId(String id, MODULE_TYPE comp_type) {
		String file_path = getRelativeDirByModuleType(comp_type);
		file_path = file_path + "/" + getFileName(id);
		return file_path;
	}

}
