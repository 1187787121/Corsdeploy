/**
 * Title: ActionRootOutputBean.java
 * File Description:  ��������ӿڻ���
 * @copyright 2014
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/7/2014
 */

package com.wk.cd.bean;

import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.SVDEAL_TYPE;
import com.wk.sdo.ServiceData;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:��������ӿڻ���
 * @author lixl
 */
public class ActionRootOutputBean extends RequestRootOutputBean{
	private static final long serialVersionUID = 9066144690361105669L;

	/**
     * ��ȿ��� ���캯��
     * @param bean 
     */
	public ActionRootOutputBean(ActionRootOutputBean bean){
		super(bean);
		this.dtbs_bk_date = bean.getDtbs_bk_date();
		this.dtbs_bk_time = bean.getDtbs_bk_time();
		this.work_seq = bean.getWork_seq();
	}
		
	/**
	 * ���캯��
	 */
	public ActionRootOutputBean(){
	}

   /**
    * ϵͳ��ˮ��
    */
    private String work_seq;
    
    public static final String WORK_SEQCN = "ϵͳ��ˮ��";
    
    /**
     * ��������ˮ��
     */
    private String pend_work_seq;
    
    public static final String PEND_WORK_SEQCN = "��������ˮ��";

    /**
     * ���ݿ�����
     */
    private JaDate dtbs_bk_date;
    
    public static final String DTBS_BK_DATECN = "���ݿ�����";

    /**
    * ���ݿ�ʱ��
    */
    private JaTime dtbs_bk_time;
    
    public static final String DTBS_BK_TIMECN = "���ݿ�ʱ��";
	
	/**
	 * �����������
	 */
	private String pend_srv_name;
	
	public static final String PEND_SRV_NAMECN="�����������";
	
	/**
	 * ��Ȩ���
	 */
	private Integer auth_seq;
	
	public static final String AUTH_SEQCN = "��Ȩ���";
	
	/**
	 * ��Ȩ���Ž�ɫ���
	 */
	private String auth_dprl_code;
	
	public static final String AUTH_DPRL_CODECN = "��Ȩ���Ž�ɫ���";
	
	/**
	 * ��Ȩ���Ž�ɫ����
	 */
	private String authdprl_bk_expl;
	
	public static final String AUTHDPRL_BK_EXPLCN = "��Ȩ���Ž�ɫ����";
	
	/**
	 * ����������
	 */
	private SVDEAL_TYPE svdeal_type;
	
	public static final String svdeal_typecn = "����������";
	
	/**
	 *����������˵��
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "����������˵��";
	
	/**
	 *����չʾ����
	 */
	private APROV_TYPE aprov_type;

	public static final String APROV_TYPECN = "����չʾ����";
	
	/**
	 * ��������
	 */
	private ServiceData req_data;
	
	public static final String REQ_DATACN = "��������";
	
	/**
	 * @return work_seq ϵͳ��ˮ��
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 * @param workSeq ϵͳ��ˮ��
	 */
	public void setWork_seq(String workSeq) {
		this.work_seq = workSeq;
	}
	
	/**
	 * ��������ˮ��
	 * @return
	 */
	public String getPend_work_seq(){
		return this.pend_work_seq;
	}
	
	/**
	 * ��������ˮ��
	 * @param pend_work_seq 
	 */
	public void setPend_work_seq(String pend_work_seq){
		this.pend_work_seq = pend_work_seq;
	}
	
	/**
	 * ��Ȩ�ؼ���
	 */
	private String auth_key;
	
	public static final String AUTH_KEYCN = "��Ȩ�ؼ���";

	/**
	 * @return dtbs_bk_date ���ݿ�����
	 */
	public JaDate getDtbs_bk_date() {
		return this.dtbs_bk_date;
	}

	/**
	 * @param dtbsBkDate ���ݿ�����
	 */
	public void setDtbs_bk_date(JaDate dtbsBkDate) {
		this.dtbs_bk_date = dtbsBkDate;
	}

	/**
	 * @return dtbs_bk_time ���ݿ�ʱ��
	 */
	public JaTime getDtbs_bk_time() {
		return this.dtbs_bk_time;
	}

	/**
	 * @param dtbsBkTime ���ݿ�ʱ��
	 */
	public void setDtbs_bk_time(JaTime dtbsBkTime) {
		this.dtbs_bk_time = dtbsBkTime;
	}

	/**
	 * @return auth_seq ��Ȩ���
	 */
	public Integer getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 * @param auth_seq ��Ȩ���
	 */
	public void setAuth_seq(Integer auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 * @return auth_dprl_code ��Ȩ���Ž�ɫ���
	 */
	public String getAuth_dprl_code() {
		return this.auth_dprl_code;
	}

	/**
	 * @param auth_dprl_code ��Ȩ���Ž�ɫ���
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
	}

	/**
	 * @return authdprl_bk_expl ��Ȩ���Ž�ɫ����
	 */
	public String getAuthdprl_bk_expl() {
		return this.authdprl_bk_expl;
	}

	/**
	 * @param authdprl_bk_expl ��Ȩ���Ž�ɫ����
	 */
	public void setAuthdprl_bk_expl(String authdprl_bk_expl) {
		this.authdprl_bk_expl = authdprl_bk_expl;
	}
	

	/**
	 * @return pend_srv_name �����������
	 */
	public String getPend_srv_name() {
		return this.pend_srv_name;
	}

	/**
	 * @param pend_srv_name �����������
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 * @return svdeal_type ����������
	 */
	public SVDEAL_TYPE getSvdeal_type() {
		return svdeal_type;
	}

	/**
	 * @param svdeal_type ����������
	 */
	public void setSvdeal_type(SVDEAL_TYPE svdeal_type) {
		this.svdeal_type = svdeal_type;
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
	 * @return auth_key ��Ȩ�ؼ���
	 */
	public String getAuth_key() {
		return auth_key;
	}

	/**
	 * @param auth_key ��Ȩ�ؼ���
	 */
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	
	/**
	 * @return req_data ��������
	 */
	public ServiceData getReq_data() {
		return req_data;
	}

	/**
	 * @param req_data ��������
	 */
	public void setReq_data(ServiceData req_data) {
		this.req_data = req_data;
	}

	@Override
	public ActionRootOutputBean clone() {
		return (ActionRootOutputBean) super.clone();
	}
}
