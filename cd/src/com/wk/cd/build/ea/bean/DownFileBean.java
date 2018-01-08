/**
 * Title: DownFileBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

/**
 * Class Description: 
 * @author xuph
 */
public class DownFileBean {
	/**
	 * 文件名
	 */
	private String file_name;
	
	public static final String FILE_NAMECN = "文件名";
	/**
	 * 文件路径
	 */
	private String file_path;
	
	public static final String FILE_PATHCN = "文件路径";
	/**
	 * 文件类型（1为日志，2为目标包）
	 */
	private int file_type; 
	
	public static final String FILE_TYPECN = "文件类型";
	
	/**
	 * 文件大小（单位KB）
	 */
	private int file_size;
	
	public static final String FILE_SIZECN = "文件大小";

	/**
	 * @return file_name
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * @param file_name
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * @return file_path
	 */
	public String getFile_path() {
		return file_path;
	}

	/**
	 * @param file_path
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @return file_type
	 */
	public int getFile_type() {
		return file_type;
	}

	/**
	 * @param file_type
	 */
	public void setFile_type(int file_type) {
		this.file_type = file_type;
	}

	/**
	 * @return file_size 文件大小
	 */
	public int getFile_size() {
		return file_size;
	}

	/**
	 * @param file_size 文件大小
	 */
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
}
