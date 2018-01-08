/**
 * Title: UpadateListBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016年6月17日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class UpdateListBean {
	/**
	 * 文件名
	 */
	private String file_name ;
	
	/**
	 * 文件别名
	 */
	private String alias_file_name;
	
	/**
	 * 是否为压缩文件，true为压缩文件 默认为true
	 */
	private boolean is_tar;
	
	/**
	 * 是否需要校验压缩文件中文件列表，true需要校验 默认为true
	 */
	private boolean is_check;
	
	
	/**
	 * 压缩文件 内目录列表（如果压缩文件中不能列出详细的文件，则需要将其目录列出来）
	 */
	private List<FileDetailBean> file_detail_bean;
	
	/**
	 * 
	 * 构造函数
	 * @param file_name
	 * @param alias_file_name
	 * @param is_tar
	 * @param is_check
	 */
	public UpdateListBean(String file_name, String alias_file_name,
			boolean is_tar, boolean is_check) {
		super();
		this.file_name = file_name;
		this.alias_file_name = alias_file_name;
		this.is_tar = is_tar;
		this.is_check = is_check;
	}

	/**
	 * @return 文件名
	 */
	public String getFile_name() {
		return this.file_name;
	}

	/**
	 * Description: 设置文件名 
	 * @param file_name
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * Description: 获取别名 
	 * @return
	 */
	public String getAlias_file_name() {
		return this.alias_file_name;
	}

	/**
	 * Description: 设置别名 
	 * @param alias_file_name
	 */
	public void setAlias_file_name(String alias_file_name) {
		this.alias_file_name = alias_file_name;
	}

	/**
	 * Description: 获取是否为tar包 
	 * @return
	 */
	public boolean isIs_tar() {
		return this.is_tar;
	}

	/**
	 * Description: 设置是否为压缩包 
	 * @param is_tar
	 */
	public void setIs_tar(boolean is_tar) {
		this.is_tar = is_tar;
	}

	/**
	 * Description: 获取是否需要校验压缩包内文件列表 
	 * @return
	 */
	public boolean isIs_check() {
		return this.is_check;
	}

	/**
	 * Description: 设置是否需要校验压缩包内文件列表 
	 * @param is_check
	 */
	public void setIs_check(boolean is_check) {
		this.is_check = is_check;
	}



	/**
	 * Description: 获取压缩文件 内目录列表
	 * @return
	 */
	public List<FileDetailBean> getFile_detail_bean() {
		return this.file_detail_bean;
	}

	/**
	 * Description: 设置 压缩文件 内目录列表
	 * @param file_dir_detail
	 */
	public void setFile_detail_bean(List<FileDetailBean> file_detail_bean) {
		this.file_detail_bean = file_detail_bean;
	}

	@Override
	public String toString() {
		return "UpdateListBean [file_name=" + file_name + ", alias_file_name="
				+ alias_file_name + ", is_tar=" + is_tar + ", is_check="
				+ is_check + "]";
	}
	
	
	
	
	
	

}
