package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;

public class UploadScriptFileInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : -5212545072043367486L
	 */ 
	private static final long serialVersionUID = -5212545072043367486L;

	/**
	 * 脚本文件路径
	 */
	private String script_file_path;
	
	public static final String SCRIPT_FILE_PATHCN = "脚本文件路径";

	/**
	 * @return script_file_path
	 */
	public String getScript_file_path() {
		return script_file_path;
	}

	/**
	 * @param script_file_path
	 */
	public void setScript_file_path(String script_file_path) {
		this.script_file_path = script_file_path;
	}
	
}
