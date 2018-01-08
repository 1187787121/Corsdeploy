/**
 * Title: TarPacDownloadBean.java
 * File Description: 目标包下载接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月29日
 */
package com.wk.cd.build.ea.bean;

import java.io.Serializable;

/**
 * Class Description: 目标包下载接口
 * @author Xul
 */
public class TarPacDownloadBean implements Serializable{

	private static final long serialVersionUID = -7922655049123498294L;
	
	/**
	 * 远程文件全路径
	 */
	private String romote_full_path;
	
	public static final String ROMOTE_FULL_PATHCN = "远程文件全路径";
	
	/**
	 * 本地文件全路径
	 */
	private String local_full_path;
	
	public static final String LOCAL_FULL_PATHCN = "本地文件全路径";
	
	/**
	 * 数据源名
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "数据源名";

	/**
	 * @return romote_full_path 远程文件全路径
	 */
	public String getRomote_full_path() {
		return romote_full_path;
	}

	/**
	 * @param romote_full_path 远程文件全路径
	 */
	public void setRomote_full_path(String romote_full_path) {
		this.romote_full_path = romote_full_path;
	}

	/**
	 * @return local_full_path 本地文件全路径
	 */
	public String getLocal_full_path() {
		return local_full_path;
	}

	/**
	 * @param local_full_path 本地文件全路径
	 */
	public void setLocal_full_path(String local_full_path) {
		this.local_full_path = local_full_path;
	}

	/**
	 * @return soc_name 数据源名
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name 数据源名
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}
	
}
