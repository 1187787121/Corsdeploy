/**
 * Title: PageSrvByFunTypeOutputBean.java
 * File Description:��ҳ�б��ѯ����������Ϣ 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.sv.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.sv.info.SvSrvInfo;

/**
 * Class Description:��ҳ�б��ѯ����������Ϣ
 * @author tlw
 */
public class PageSrvByFunTypeOutputBean
		extends PageQueryActionRootOutputBean {
	private static final long serialVersionUID = -7190389830817794456L;

	/**
	 * ��ҳ��ѯ�����б�
	 */
	private List<SvSrvInfo> list_info;

	public static final String LIST_INFOCN = "��ҳ��ѯ�����б�";

	/**
	 * @return list_info ��ҳ��ѯ�����б�
	 */
	public List<SvSrvInfo> getList_info() {
		return list_info;
	}

	/**
	 * @param list_info ��ҳ��ѯ�����б�
	 */
	public void setList_info(List<SvSrvInfo> list_info) {
		this.list_info = list_info;
	}

}
