/**
 * Title: UpdateWorkInputBean.java
 * File Description:����������Ϣ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;

/**
 * Class Description:����������Ϣ����ӿ�
 * @author tlw
 */
public class UpdateWorkInputBean
		extends ActionRootInputBean {
	
	private static final long serialVersionUID = -247586119526725815L;

	/**
	 * �������
	 */
	private String work_code;
	
	public static final String WORK_CODECN = "�������";
	
	/**
	 * ��������
	 */
	private String work_cn_name;
	
	public static final String WORK_CN_NAMECN = "��������";
	
	/**
	 * ��������
	 */
	private String work_bk_desc;
	
	public static final String WORK_BK_DESCCN = "��������";
	
	/**
	 * ��������
	 */
	private FUN_TYPE work_fun_type;
	
	public static final String WORK_FUN_TYPECN = "��������";
	
	/**
	 * �Ƿ񷢲�
	 */
	private IS_PUBLISH is_publish;
	
	public static final String IS_PUBLISHCN = "�Ƿ񷢲�";
	
	/**
	 * ��Դ�����б�
	 */
	private List<RsResCodeBean> rs_code_list;
	
	public static final String RS_CODE_LISTCN = "��Դ�����б�";
	
	/**
	 * ����Դ�б�
	 */
	private List<DtSourceBean> soc_list;
	
	public static final String SOC_LISTCN = "����Դ�б�";
	
	/**
	 * �����б�
	 */
	private List<SvServiceBean> srv_list;
	
	public static final String SRV_LISTCN = "�����б�";

	/**
	 * @return work_code �������
	 */
	public String getWork_code() {
		return work_code;
	}

	/**
	 * @param work_code �������
	 */
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}

	/**
	 * @return work_cn_name ��������
	 */
	public String getWork_cn_name() {
		return work_cn_name;
	}

	/**
	 * @param work_cn_name ��������
	 */
	public void setWork_cn_name(String work_cn_name) {
		this.work_cn_name = work_cn_name;
	}

	/**
	 * @return work_bk_desc ��������
	 */
	public String getWork_bk_desc() {
		return work_bk_desc;
	}

	/**
	 * @param work_bk_desc ��������
	 */ 
	public void setWork_bk_desc(String work_bk_desc) {
		this.work_bk_desc = work_bk_desc;
	}

	/**
	 * @return work_fun_type ��������
	 */
	public FUN_TYPE getWork_fun_type() {
		return work_fun_type;
	}

	/**
	 * @param work_fun_type ��������
	 */
	public void setWork_fun_type(FUN_TYPE work_fun_type) {
		this.work_fun_type = work_fun_type;
	}

	/**
	 * @return is_publish �Ƿ񷢲�
	 */
	public IS_PUBLISH getIs_publish() {
		return is_publish;
	}

	/**
	 * @param is_publish �Ƿ񷢲�
	 */
	public void setIs_publish(IS_PUBLISH is_publish) {
		this.is_publish = is_publish;
	}

	/**
	 * @return rs_code_list ��Դ�б�
	 */
	public List<RsResCodeBean> getRs_code_list() {
		return rs_code_list;
	}

	/**
	 * @param rs_code_list ��Դ�б�
	 */
	public void setRs_code_list(List<RsResCodeBean> rs_code_list) {
		this.rs_code_list = rs_code_list;
	}

	/**
	 * @return soc_list ����Դ�б�
	 */
	public List<DtSourceBean> getSoc_list() {
		return soc_list;
	}

	/**
	 * @param soc_list ����Դ�б�
	 */
	public void setSoc_list(List<DtSourceBean> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return srv_list �����б�
	 */
	public List<SvServiceBean> getSrv_list() {
		return srv_list;
	}

	/**
	 * @param srv_list �����б�
	 */
	public void setSrv_list(List<SvServiceBean> srv_list) {
		this.srv_list = srv_list;
	}
}
