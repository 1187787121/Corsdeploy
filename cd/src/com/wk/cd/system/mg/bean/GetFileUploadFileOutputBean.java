/**
 * Title: GetFileUploadFileOutputBean.java
 * File Description: ��ȡ��Ϣ�ļ��ϴ�·������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��11��7��
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��ȡ��Ϣ�ļ��ϴ�·������ӿ�
 * @author HT
 */
public class GetFileUploadFileOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 4679559809278902474L;
	
	/**
	 * �ļ��ϴ�·��
	 */
	private String fileupload_path;

	public static final String FILEUPLOAD_PATHCN = "�ļ��ϴ�·��";

	/** 
	 * @return fileupload_path �ļ��ϴ�·��
	 */
	public String getFileupload_path() {
		return this.fileupload_path;
	}

	/**
	 * @param fileupload_path �ļ��ϴ�·��
	 */
	public void setFileupload_path(String fileupload_path) {
		this.fileupload_path = fileupload_path;
	}
}
