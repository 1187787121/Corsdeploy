/**
 * Title: QueryWorkDetailByWorkSeqInputBean.java
 * File Description:��������ˮ�Ų�ѯ������ϸ��Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:��������ˮ�Ų�ѯ������ϸ��Ϣ����ӿ�
 * @author tlw
 */
public class QueryPendWorkDetailByWorkSeqInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -557009262232407658L;

	/**
	 * ������������ˮ��
	 */
	private String wrk_seq;

	public static final String WRK_SEQCN = "������������ˮ��";

	/**
	 * @return wrk_seq ������������ˮ��
	 */
	public String getWrk_seq() {
		return wrk_seq;
	}

	/**
	 * @param wrk_seq ������������ˮ��
	 */
	public void setWrk_seq(String wrk_seq) {
		this.wrk_seq = wrk_seq;
	}

}
