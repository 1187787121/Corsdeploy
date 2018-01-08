package com.wk.cd.remote.fp.bean;

public class FileListBean {

	/**
	 * 文件名
	 */
	private String file;
	
	public static final String FILECN = "文件名";
	
	/**
	 * 文件类型
	 */
	private String type;
	
	public static final String TYPECN = "文件类型";
	
	/**
	 * 是否目录
	 */
	private boolean isDir;
	
	public static final String ISDIRCN = "是否目录";
	/**
	 * 配置文件修改标志
	 */
	
	private boolean modified_flag;
	
	public static final String MODIFIED_FLAGCN="修改标志";
	
	/**
	 * 文件可修改标志
	 */
	private boolean edit_flag;
	


	public static final String EDIT_FLAGCN="文件可编辑标志";
	
	
	public FileListBean() {
	}

	public FileListBean(String file, boolean isDir) {
		this.file = file;
		this.isDir = isDir;
	}

	public FileListBean(String file, String type, boolean isDir) {
		this.file = file;
		this.type = type;
		this.isDir = isDir;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDir() {
		return this.isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public boolean isModified_flag() {
		return this.modified_flag;
	}

	public void setModified_flag(boolean modified_flag) {
		this.modified_flag = modified_flag;
	}

	public boolean isEdit_flag() {
		return this.edit_flag;
	}

	public void setEdit_flag(boolean edit_flag) {
		this.edit_flag = edit_flag;
	}

	public String toString() {
		return "FileListBean [file=" + this.file + ", type=" + this.type
				+ ", isDir=" + this.isDir + ", modified_flag="
				+ this.modified_flag + ", edit_flag=" + this.edit_flag + "]";
	}
}
