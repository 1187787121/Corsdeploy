/**
 * Title: QueryLogByWorkSeqInputBean.java
 * File Description: ��ȷ��ѯ��־��Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ȷ��ѯ��־��Ϣ����ӿ�
 * @author tlw
 */
public class QueryLogByWorkSeqInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 6539284236422187793L;

	/**
	 * ������ˮ��
	 */
	private String query_work_seq;

	public static final String QUERY_WORK_SEQCN = "������ˮ��";

	/**
	 * @return query_work_seq ������ˮ��
	 */
	public String getQuery_work_seq() {
		return query_work_seq;
	}

	/**
	 * @param query_work_seq ������ˮ��
	 */
	public void setQuery_work_seq(String query_work_seq) {
		this.query_work_seq = query_work_seq;
	}

}
