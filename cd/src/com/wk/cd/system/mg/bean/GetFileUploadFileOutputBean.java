/**
 * Title: GetFileUploadFileOutputBean.java
 * File Description: 获取消息文件上传路径输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月7日
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 获取消息文件上传路径输出接口
 * @author HT
 */
public class GetFileUploadFileOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 4679559809278902474L;
	
	/**
	 * 文件上传路径
	 */
	private String fileupload_path;

	public static final String FILEUPLOAD_PATHCN = "文件上传路径";

	/** 
	 * @return fileupload_path 文件上传路径
	 */
	public String getFileupload_path() {
		return this.fileupload_path;
	}

	/**
	 * @param fileupload_path 文件上传路径
	 */
	public void setFileupload_path(String fileupload_path) {
		this.fileupload_path = fileupload_path;
	}
}
