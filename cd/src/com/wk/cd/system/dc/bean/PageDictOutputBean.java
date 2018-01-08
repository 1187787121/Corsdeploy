/**
 * Title: PageDictOutputBean.java
 * File Description: ��ҳ��ѯ�����ֵ��б�����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014��11��17��
 */
package com.wk.cd.system.dc.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.dc.info.DcDictInfo;

/**
 * Class Description: ��ҳ��ѯ�����ֵ��б�����ӿ���
 * @author HT
 */
public class PageDictOutputBean extends PageQueryActionRootOutputBean{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = -8239477877829102789L;

	/**
	 * �����ֵ��б�
	 */
	private List<DcDictInfo> dict_list;
	
	public final String DICT_LISTCN="�����ֵ��б�";
	
	/**
	 * @return dict_list �����ֵ��б�
	 */
	public List<DcDictInfo> getDict_list() {
		return this.dict_list;
	}

	/**
	 * @param dict_list �����ֵ��б�
	 */
	public void setDict_list(List<DcDictInfo> dict_list) {
		this.dict_list = dict_list;
	}

}
