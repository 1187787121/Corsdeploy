/**
 * Title: PageStorageTaskOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 
 * @author chiss
 */
public class PageStorageTaskOutputBean extends PageQueryActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 998805837899952818L;
	
	/**
	 * ��������б�
	 */
	private List<EnvTagStorageBean> stor_list;
	
	public static final String stor_listcn = "��������б�";

	/**
	 * @return stor_list ��������б�
	 */
	public List<EnvTagStorageBean> getStor_list() {
		return stor_list;
	}

	/**
	 * @param stor_list ��������б�
	 */
	public void setStor_list(List<EnvTagStorageBean> stor_list) {
		this.stor_list = stor_list;
	}
}
