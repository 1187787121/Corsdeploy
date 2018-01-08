/**
 * Title: TargetPackageBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuFilelistInfo;

/**
 * Class Description: 
 * @author xuph
 */
public class TargetPackageBean {
	/**
	 * 目标包名
	 */
	private String package_name; 
	
	public static final String PACKAGE_NAMECN = "目标包名";
	
	/**
	 * 打包根路径
	 */
	private String storage_bk_path;
	
	public static final String STORAGE_BK_PATHCN = "打包根路径";
	
	/**
	 * 目标包相对路径
	 */
	private String target_relative_path;
	
	public static final String TARGET_RELATIVE_PATHCN = "目标包相对路径";
	
	/**
	 * 文件清单
	 */
	private List<UuFilelistInfo> file_list;
	
	public static final String FILE_LISTCN = "文件清单";
	
	/**
	 * 下载路径
	 */
	private String download_path;
	
	public static final String DOWNLOAD_PATHCN = "下载路径";

	/**
	 * @return package_name 目标包名
	 */
	public String getPackage_name() {
		return package_name;
	}

	/**
	 * @param package_name 目标包名
	 */
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	/**
	 * @return file_list 文件清单
	 */
	public List<UuFilelistInfo> getFile_list() {
		return file_list;
	}

	/**
	 * @param file_list 文件清单
	 */
	public void setFile_list(List<UuFilelistInfo> file_list) {
		this.file_list = file_list;
	}

	/**
	 * @return storage_path 打包根路径
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_path 打包根路径
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/**
	 * @return target_relative_path 目标包相对路径
	 */
	public String getTarget_relative_path() {
		return target_relative_path;
	}

	/**
	 * @param target_relative_path 目标包相对路径
	 */
	public void setTarget_relative_path(String target_relative_path) {
		this.target_relative_path = target_relative_path;
	}

	/**
	 * @return download_path 下载路径
	 */
	public String getDownload_path() {
		return download_path;
	}

	/**
	 * @param download_path 下载路径
	 */
	public void setDownload_path(String download_path) {
		this.download_path = download_path;
	}
}
