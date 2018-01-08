/**
 * Title: QueryWorkDetailOutputBean.java
 * File Description: ��������鿴����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��3��14��
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;

/**
 * Class Description: ��������鿴����ӿ�
 * @author Xul
 */
public class QueryWorkDetailOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 9055884863418932724L;
	
	/**
	 * �ӿ���Ϣ
	 */
	private String new_json_list;

	public static final String NEW_JSON_LISTCN = "�ӿ���Ϣ";

	/**
	 * ��������ϸ�б�
	 */
	private List<WorkStateDetailBean> work_detail_list;
	
	public static final String WORK_DETAIL_LISTCN = "��������ϸ�б�";
	
	/**
	 * ��������˵��
	 */
	private String apply_bk_expl;
	
	public static final String APPLY_BK_EXPLCN = "��������˵��";
	
	/**
	 *����״̬
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECN = "����״̬";
	
	/**
	 *�������������
	 */
	private String pend_srv_name;

	public static final String PEND_SRV_NAMECN = "�������������";
	
	/**
	 * ����չʾ����
	 */
	private APROV_TYPE aprov_type;
	
	public static final String APROV_TYPECN = "����չʾ����";
	
	/**
	 *����������˵��
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "����������˵��";
	
	/**
	 *������Դ��ַ
	 */
	private String rs_url;

	public static final String RS_URLCN = "������Դ��ַ";
	
	/**
	 * @return new_json_list �ӿ���Ϣ
	 */
	public String getNew_json_list() {
		return new_json_list;
	}

	/**
	 * @param new_json_list �ӿ���Ϣ
	 */
	public void setNew_json_list(String new_json_list) {
		this.new_json_list = new_json_list;
	}

	/**
	 * @return work_detail_list ��������ϸ�б�
	 */
	public List<WorkStateDetailBean> getWork_detail_list() {
		return work_detail_list;
	}

	/**
	 * @param work_detail_list ��������ϸ�б�
	 */
	public void setWork_detail_list(List<WorkStateDetailBean> work_detail_list) {
		this.work_detail_list = work_detail_list;
	}

	/**
	 * @return apply_bk_expl ��������˵��
	 */
	public String getApply_bk_expl() {
		return apply_bk_expl;
	}

	/**
	 * @param apply_bk_expl ��������˵��
	 */
	public void setApply_bk_expl(String apply_bk_expl) {
		this.apply_bk_expl = apply_bk_expl;
	}

	/**
	 * @return workflow_state ����״̬
	 */
	public WORKFLOW_STATE getWorkflow_state() {
		return workflow_state;
	}

	/**
	 * @param workflow_state ����״̬
	 */
	public void setWorkflow_state(WORKFLOW_STATE workflow_state) {
		this.workflow_state = workflow_state;
	}

	/**
	 * @return pend_srv_name �������������
	 */
	public String getPend_srv_name() {
		return pend_srv_name;
	}

	/**
	 * @param pend_srv_name �������������
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 * @return aprov_type ����չʾ����
	 */
	public APROV_TYPE getAprov_type() {
		return aprov_type;
	}

	/**
	 * @param aprov_type ����չʾ����
	 */
	public void setAprov_type(APROV_TYPE aprov_type) {
		this.aprov_type = aprov_type;
	}

	/**
	 * @return pendwk_bk_expl ����������˵��
	 */
	public String getPendwk_bk_expl() {
		return pendwk_bk_expl;
	}

	/**
	 * @param pendwk_bk_expl ����������˵��
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl) {
		this.pendwk_bk_expl = pendwk_bk_expl;
	}

	/**
	 * @return rs_url ������Դ��ַ
	 */
	public String getRs_url() {
		return rs_url;
	}

	/**
	 * @param rs_url ������Դ��ַ
	 */
	public void setRs_url(String rs_url) {
		this.rs_url = rs_url;
	}
	
}
