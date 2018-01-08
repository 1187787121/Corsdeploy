/**
 * Title: QuerySrvByNameOutputBean.java
 * File Description: ���շ�������ѯ������Ϣ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.SALLOW_FLAG;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.cd.system.sv.info.SvSrvSocInfo;

/**
 * Class Description: ���շ�������ѯ������Ϣ����ӿ�
 * @author tlw
 */
public class QuerySrvByNameOutputBean
		extends ActionRootOutputBean {
	private static final long serialVersionUID = 2368165847747714242L;

	/**
	 * ��������
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "��������";

	/**
	 * ��������
	 */
	private String srv_bk_desc;

	public static final String SRV_BK_DESCCN = "��������";

	/**
	 * ��������
	 */
	private FUN_TYPE srv_fun_type;

	public static final String SRV_FUN_TYPECN = "��������";

	/**
	 * ��������
	 */
	private String srv_class_name;

	public static final String SRV_CLASS_NAMECN = "��������";

	/**
	 * ���񷽷���
	 */
	private String srv_method_name;

	public static final String SRV_METHOD_NAMECN = "���񷽷���";

	/**
	 * ���������־
	 */
	private SALLOW_FLAG sallow_flag;

	public static final String SALLOW_FLAGCN = "���������־";

	/**
	 * �Ƿ�������Դ
	 */
	private SOC_FLAG soc_flag;

	public static final String SOC_FLAGCN = "�Ƿ�������Դ";

	/**
	 * ����Դ������Ϣ
	 */
	private List<SvSrvSocInfo> srv_soc_list;

	public static final String SRV_SOC_LISTCN = "����Դ������Ϣ";

	/**
	 * Ŀ����������
	 */
	private String remote_srv_name;

	public static final String REMOTE_SRV_NAMECN = "Ŀ����������";

	/**
	 * Ŀ��������������
	 */
	private String srv_type;

	public static final String SRV_TYPECN = "Ŀ��������������";

	/**
	 * ����·��
	 */
	private String srv_root_path;

	public static final String SRV_ROOT_PATHCN = "����·��";

	/**
	 * @return srv_name ��������
	 */
	public String getSrv_name() {
		return srv_name;
	}

	/**
	 * @param srv_name ��������
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 * @return srv_bk_desc ��������
	 */
	public String getSrv_bk_desc() {
		return srv_bk_desc;
	}

	/**
	 * @param srv_bk_desc ��������
	 */
	public void setSrv_bk_desc(String srv_bk_desc) {
		this.srv_bk_desc = srv_bk_desc;
	}

	/**
	 * @return srv_fun_type ��������
	 */
	public FUN_TYPE getSrv_fun_type() {
		return srv_fun_type;
	}

	/**
	 * @param srv_fun_type ��������
	 */
	public void setSrv_fun_type(FUN_TYPE srv_fun_type) {
		this.srv_fun_type = srv_fun_type;
	}

	/**
	 * @return srv_class_name ��������
	 */
	public String getSrv_class_name() {
		return srv_class_name;
	}

	/**
	 * @param srv_class_name ��������
	 */
	public void setSrv_class_name(String srv_class_name) {
		this.srv_class_name = srv_class_name;
	}

	/**
	 * @return srv_method_name ���񷽷���
	 */
	public String getSrv_method_name() {
		return srv_method_name;
	}

	/**
	 * @param srv_method_name ���񷽷���
	 */
	public void setSrv_method_name(String srv_method_name) {
		this.srv_method_name = srv_method_name;
	}

	/**
	 * @return sallow_flag ���������־
	 */
	public SALLOW_FLAG getSallow_flag() {
		return sallow_flag;
	}

	/**
	 * @param sallow_flag ���������־
	 */
	public void setSallow_flag(SALLOW_FLAG sallow_flag) {
		this.sallow_flag = sallow_flag;
	}

	/**
	 * @return soc_flag �Ƿ�������Դ
	 */
	public SOC_FLAG getSoc_flag() {
		return soc_flag;
	}

	/**
	 * @param soc_flag �Ƿ�������Դ
	 */
	public void setSoc_flag(SOC_FLAG soc_flag) {
		this.soc_flag = soc_flag;
	}

	/**
	 * @return srv_soc_list ����Դ������Ϣ
	 */
	public List<SvSrvSocInfo> getSrv_soc_list() {
		return srv_soc_list;
	}

	/**
	 * @param srv_soc_list ����Դ������Ϣ
	 */
	public void setSrv_soc_list(List<SvSrvSocInfo> srv_soc_list) {
		this.srv_soc_list = srv_soc_list;
	}

	/**
	 * @return remote_srv_name Ŀ����������
	 */
	public String getRemote_srv_name() {
		return remote_srv_name;
	}

	/**
	 * @param remote_srv_name Ŀ����������
	 */
	public void setRemote_srv_name(String remote_srv_name) {
		this.remote_srv_name = remote_srv_name;
	}

	/**
	 * @return srv_type Ŀ��������������
	 */
	public String getSrv_type() {
		return srv_type;
	}

	/**
	 * @param srv_type Ŀ��������������
	 */
	public void setSrv_type(String srv_type) {
		this.srv_type = srv_type;
	}

	/**
	 * @return srv_root_path Ŀ����������
	 */
	public String getSrv_root_path() {
		return srv_root_path;
	}

	/**
	 * @param srv_root_path Ŀ����������
	 */
	public void setSrv_root_path(String srv_root_path) {
		this.srv_root_path = srv_root_path;
	}

}
