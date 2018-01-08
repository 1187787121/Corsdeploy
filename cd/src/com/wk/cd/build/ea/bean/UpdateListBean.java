/**
 * Title: UpadateListBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016��6��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class UpdateListBean {
	/**
	 * �ļ���
	 */
	private String file_name ;
	
	/**
	 * �ļ�����
	 */
	private String alias_file_name;
	
	/**
	 * �Ƿ�Ϊѹ���ļ���trueΪѹ���ļ� Ĭ��Ϊtrue
	 */
	private boolean is_tar;
	
	/**
	 * �Ƿ���ҪУ��ѹ���ļ����ļ��б�true��ҪУ�� Ĭ��Ϊtrue
	 */
	private boolean is_check;
	
	
	/**
	 * ѹ���ļ� ��Ŀ¼�б����ѹ���ļ��в����г���ϸ���ļ�������Ҫ����Ŀ¼�г�����
	 */
	private List<FileDetailBean> file_detail_bean;
	
	/**
	 * 
	 * ���캯��
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
	 * @return �ļ���
	 */
	public String getFile_name() {
		return this.file_name;
	}

	/**
	 * Description: �����ļ��� 
	 * @param file_name
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	/**
	 * Description: ��ȡ���� 
	 * @return
	 */
	public String getAlias_file_name() {
		return this.alias_file_name;
	}

	/**
	 * Description: ���ñ��� 
	 * @param alias_file_name
	 */
	public void setAlias_file_name(String alias_file_name) {
		this.alias_file_name = alias_file_name;
	}

	/**
	 * Description: ��ȡ�Ƿ�Ϊtar�� 
	 * @return
	 */
	public boolean isIs_tar() {
		return this.is_tar;
	}

	/**
	 * Description: �����Ƿ�Ϊѹ���� 
	 * @param is_tar
	 */
	public void setIs_tar(boolean is_tar) {
		this.is_tar = is_tar;
	}

	/**
	 * Description: ��ȡ�Ƿ���ҪУ��ѹ�������ļ��б� 
	 * @return
	 */
	public boolean isIs_check() {
		return this.is_check;
	}

	/**
	 * Description: �����Ƿ���ҪУ��ѹ�������ļ��б� 
	 * @param is_check
	 */
	public void setIs_check(boolean is_check) {
		this.is_check = is_check;
	}



	/**
	 * Description: ��ȡѹ���ļ� ��Ŀ¼�б�
	 * @return
	 */
	public List<FileDetailBean> getFile_detail_bean() {
		return this.file_detail_bean;
	}

	/**
	 * Description: ���� ѹ���ļ� ��Ŀ¼�б�
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
