/**
 * Title: DeleteFileEntity.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��9��18��
 */
package com.wk.cd.module1.entity;

import com.wk.cd.system.dt.info.DtSourceInfo;

/**
 * Class Description: ִ�й��̵��е���ʱ�ļ�����Ҫ��ʱɾ��
 * @author zhangj
 */
public class DeleteFileEntity {
	
	
	
	/**
	 * ���캯��
	 * @param file_name
	 * @param dtSourceInfo
	 * @param upload_start_time
	 */
	public DeleteFileEntity(String file_name, DtSourceInfo dtSourceInfo, long upload_start_time) {
		super();
		this.file_name = file_name;
		this.dtSourceInfo = dtSourceInfo;
		this.upload_start_time = upload_start_time;
	}

	// ��ʱ�ļ���ʱʱ��
	public static final int TIME_OUT = 30*60*1000;
	
	private String file_name;
	
	private DtSourceInfo dtSourceInfo;
	
	private long upload_start_time;

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
	 * @return dtSourceInfo
	 */
	public DtSourceInfo getDtSourceInfo() {
		return dtSourceInfo;
	}

	/**
	 * @param dtSourceInfo
	 */
	public void setDtSourceInfo(DtSourceInfo dtSourceInfo) {
		this.dtSourceInfo = dtSourceInfo;
	}

	/**
	 * @return upload_start_time
	 */
	public long getUpload_start_time() {
		return upload_start_time;
	}

	/**
	 * @param upload_start_time
	 */
	public void setUpload_start_time(long upload_start_time) {
		this.upload_start_time = upload_start_time;
	}
	
	

}
