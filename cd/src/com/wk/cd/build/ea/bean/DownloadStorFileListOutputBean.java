/**
 * Title: DownloadStorFileListOutputBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年2月15日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 下载入库Excel清单输出接口
 * @author xuph
 */
public class DownloadStorFileListOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : 249384319976843305L
	 */ 
	private static final long serialVersionUID = 249384319976843305L;

	/**
	 * 入库清单文件路径
	 */
	private String file_path;
	
	public static final String FILE_PATHCN = "入库清单文件路径";

	/**
	 * @return file_path 入库清单文件路径
	 */
	public String getFile_path() {
		return file_path;
	}

	/**
	 * @param file_path 入库清单文件路径
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
}
