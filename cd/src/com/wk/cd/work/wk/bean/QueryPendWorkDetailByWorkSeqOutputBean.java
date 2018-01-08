/**
 * Title: QueryPendWorkDetailByWorkSeqOutputBean.java
 * File Description:������������ϸ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.work.wk.info.WkDealDetailInfo;

/**
 * Class Description:������������ϸ����ӿ�
 * @author tlw
 */
public class QueryPendWorkDetailByWorkSeqOutputBean
		extends ActionRootOutputBean {
	
	private static final long serialVersionUID = -4275222079812414059L;

	/**
	 * ��������ϸ�б�
	 */
	private List<WkDealDetailInfo> detail_list;
	
	public static final String DETAIL_LISTCN = "��������ϸ�б�";

	/**
	 * @return detail_list ��������ϸ�б�
	 */
	public List<WkDealDetailInfo> getDetail_list() {
		return detail_list;
	}

	/**
	 * @param detail_list ��������ϸ�б�
	 */
	public void setDetail_list(List<WkDealDetailInfo> detail_list) {
		this.detail_list = detail_list;
	}


}
