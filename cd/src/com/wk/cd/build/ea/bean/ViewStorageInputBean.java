/**
 * Title: ViewStorageInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author chiss
 */
public class ViewStorageInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -9204336562514329624L;
	
	/**
	 * �����
	 */
	private String storage_id;
	
	public static final String STORAGE_IDCN = "�����";
	
	/**
	 * ʵ��Id
	 */
	private String inst_id;
	
	public static final String INST_IDCN = "ʵ��Id";

	/**
	 * �����ļ��б�
	 */
    private List<DownFileBean> down_list;
    
    public static final String DOWN_LISTCN = "�����ļ��б�";
    
    /**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
    
	/**
     * �����·��
     */
    private String storage_bk_path;
    
    public static final String STORAGE_BK_PATHCN = "�����·��";
    
	/**
	 * @return storage_id �����
	 */
	public String getStorage_id() {
		return storage_id;
	}

	/**
	 * @param storage_id �����
	 */
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}

	/**
	 * @return inst_id
	 */
	public String getInst_id() {
		return inst_id;
	}

	/**
	 * @param inst_id
	 */
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	/**
	 * @return down_list 
	 */
	public List<DownFileBean> getDown_list() {
		return this.down_list;
	}

	/**
	 * @param down_list
	 */
	public void setDown_list(List<DownFileBean> down_list) {
		this.down_list = down_list;
	}

	/**
	 * @return ce_server_name ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return storage_bk_path �����·��
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_bk_path �����·��
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}
	
}
