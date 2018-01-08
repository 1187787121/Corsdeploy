/**
 * Title: QueryPendWorkOutputBean.java
 * File Description: ��ѯ��������������ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-17
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.work.wk.info.WkDealStateInfo;

/**
 * Class Description: ��ѯ����������״̬����ӿ�
 * @author lixl
 */
public class QueryPendWorkOutputBean<T> {
	private WkDealStateInfo dw_state;
	private List<WkDealDetailBean> dw_dtl;
	private T obj;
	private String new_json_list;

	/**
	 * @return dw_state ����״̬��Ϣ
	 */
	public WkDealStateInfo getDw_state() {
		return this.dw_state;
	}

	/**
	 * @param dw_state ����״̬��Ϣ
	 */
	public void setDw_state(WkDealStateInfo dw_state) {
		this.dw_state = dw_state;
	}

	/**
	 * @return dw_dtl������ϸ�б�
	 */
	public List<WkDealDetailBean> getDw_dtl() {
		return this.dw_dtl;
	}

	/**
	 * @param dwDtl ������ϸ�б�
	 */
	public void setDw_dtl(List<WkDealDetailBean> dw_dtl) {
		this.dw_dtl = dw_dtl;
	}

	/**
	 * @return ����
	 */
	public T getObj() {
		return this.obj;
	}

	/**
	 * @param obj ����
	 */
	public void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * @return new_json_str ���ص�json�ַ��б�
	 */
	public String getNew_json_list() {
		return this.new_json_list;
	}

	/**
	 * @param new_json_str ���ص�json�ַ��б�
	 */
	public void setNew_json_list(String new_json_list) {
		this.new_json_list = new_json_list;
	}

}
