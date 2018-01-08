/**
 * Title: ViewStorageOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.util.JaDateTime;

/**
 * Class Description: 
 * @author chiss
 */
public class ViewStorageOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : -8685251169673999467L
	 */ 
	private static final long serialVersionUID = -8685251169673999467L;
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 * �������
	 */
	private String storage_bk_desc;
	public static final String STORAGE_BK_DESCCN = "�������";
	
	/**
	 * ��Ŀ���
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "��Ŀ���";
	
	/**
	 * ��Ŀ���
	 */
	private String project_short_name;
	
	public static final String PROJECT_SHORT_NAMECN = "��Ŀ���";
	
	/**
	 * ��������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "��������";
	
	/**
	 * ����Դ��
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ��";
	
	/**
	 * Ŀ��汾��������
	 */
	private String tag_server_name;
	
	public static final String TAG_SERVER_NAMECN = "Ŀ��汾��������";
	
	/**
	 * Ŀ��汾����Դ��
	 */
	private String tag_soc_name;
	public static final String TAG_SOC_NAMECN = "Ŀ��汾����Դ��";
	
	/**
	 * Ŀ��汾Ŀ¼
	 */
	private String tag_bk_dir;
	public static final String TAG_BK_DIRCN = "Ŀ��汾Ŀ¼";
	/**
	 * Ŀ����б�
	 */
	private List<TargetPackageBean> tar_package_list;
	
	public static final String TAR_PACKAGE_LISTCN = "Ŀ����б�";
	/**
	 * ���״̬
	 */
	private STORAGE_STATUS storage_status;
	
	public static final String STORAGE_STATUSCN = "���״̬";
	
	/**
	 * �����
	 */
	private STORAGE_RESULT storage_result;
	
	public static final String STORAGE_RESULTCN = "�����";
	
	/**
	 * ��⿪ʼʱ��
	 */
	private JaDateTime start_bk_tm;
	
	public static final String START_BK_TMCN = "��⿪ʼʱ��";
	
	/**
	 * ������ʱ��
	 */
	private JaDateTime end_bk_tm;
	
	public static final String END_BK_TMCN = "������ʱ��";
	
	/**
	 * �����ļ��б�
	 */
	private List<DownFileBean> down_file_list;
	
	public static final String DOWN_FILE_LISTCN = "�����ļ��б�";
	
	/**
	 * �ܲ���
	 */
	private int total_phase;

	public static final String TOTAL_PHASECN = "�ܲ���";
	/**
	 * ��ǰ����
	 */
	private int current_step;
	
	public static final String CURRENT_STEPCN = "��ǰ����";
	
	/**
	 * ��ǰ�׶�
	 */
	private int current_phase;
	
	public static final String CURRENT_PHASECN = "��ǰ�׶�";
	
	/**
	 * ʵ��Id
	 */
	private String inst_id;
	
	public static final String INST_IDCN = "ʵ��Id";
	
	/**
     * �����·��
     */
    private String storage_bk_path;
    
    public static final String STORAGE_BK_PATHCN = "�����·��";
    
    /**
     * �汾����Ŀ��汾��
     */
    private String inte_ver_num;

    public static final String INTE_VER_NUMCN = "�汾����Ŀ��汾��";
    
    /**
     * �ļ��嵥·��
     */
    private String file_xlsx_path;
    
    public static final String FILE_XLSX_PATHCN = "�ļ��嵥·��";
	
	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return storage_bk_desc �������
	 */
	public String getStorage_bk_desc() {
		return storage_bk_desc;
	}

	/**
	 * @param storage_bk_desc �������
	 */
	public void setStorage_bk_desc(String storage_bk_desc) {
		this.storage_bk_desc = storage_bk_desc;
	}

	/**
	 * @return project_name ��Ŀ���
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return project_short_name ��Ŀ���
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name ��Ŀ���
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 * @return ce_server_name ��������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ��������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return soc_name ����Դ��
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ��
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return tag_server_name Ŀ��汾��������
	 */
	public String getTag_server_name() {
		return tag_server_name;
	}

	/**
	 * @param tag_server_name Ŀ��汾��������
	 */
	public void setTag_server_name(String tag_server_name) {
		this.tag_server_name = tag_server_name;
	}

	/**
	 * @return tag_soc_name Ŀ��汾����Դ��
	 */
	public String getTag_soc_name() {
		return tag_soc_name;
	}

	/**
	 * @param tag_soc_name Ŀ��汾����Դ��
	 */
	public void setTag_soc_name(String tag_soc_name) {
		this.tag_soc_name = tag_soc_name;
	}

	/**
	 * @return tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public String getTag_bk_dir() {
		return tag_bk_dir;
	}

	/**
	 * @param tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public void setTag_bk_dir(String tag_bk_dir) {
		this.tag_bk_dir = tag_bk_dir;
	}

	/**
	 * @return tar_package_list Ŀ����б�
	 */
	public List<TargetPackageBean> getTar_package_list() {
		return tar_package_list;
	}

	/**
	 * @param tar_package_list Ŀ����б�
	 */
	public void setTar_package_list(List<TargetPackageBean> tar_package_list) {
		this.tar_package_list = tar_package_list;
	}

	/**
	 * @return storage_status ���״̬
	 */
	public STORAGE_STATUS getStorage_status() {
		return storage_status;
	}

	/**
	 * @param storage_status ���״̬
	 */
	public void setStorage_status(STORAGE_STATUS storage_status) {
		this.storage_status = storage_status;
	}

	/**
	 * @return storage_result �����
	 */
	public STORAGE_RESULT getStorage_result() {
		return storage_result;
	}

	/**
	 * @param storage_result �����
	 */
	public void setStorage_result(STORAGE_RESULT storage_result) {
		this.storage_result = storage_result;
	}

	/**
	 * @return start_bk_tm ��⿪ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return start_bk_tm;
	}

	/**
	 * @param start_bk_tm ��⿪ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 * @return end_bk_tm ������ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return end_bk_tm;
	}

	/**
	 * @param end_bk_tm ������ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 * @return down_file_list
	 */
	public List<DownFileBean> getDown_file_list() {
		return down_file_list;
	}

	/**
	 * @param down_file_list
	 */
	public void setDown_file_list(List<DownFileBean> down_file_list) {
		this.down_file_list = down_file_list;
	}
	
	/**
	 * ����״̬
	 */
	private CMD_STATUS cmd_state;
	
	public static final String CMD_STATECN = "����״̬";

	/**
	 * @return total_phase
	 */
	public int getTotal_phase() {
		return total_phase;
	}

	/**
	 * @param total_step
	 */
	public void setTotal_phase(int total_phase) {
		this.total_phase = total_phase;
	}

	/**
	 * @return current_step
	 */
	public int getCurrent_step() {
		return current_step;
	}

	/**
	 * @param current_step
	 */
	public void setCurrent_step(int current_step) {
		this.current_step = current_step;
	}

	/**
	 * @return cmd_state
	 */
	public CMD_STATUS getCmd_state() {
		return cmd_state;
	}

	/**
	 * @param cmd_state
	 */
	public void setCmd_state(CMD_STATUS cmd_state) {
		this.cmd_state = cmd_state;
	}

	/**
	 * @return current_phase
	 */
	public int getCurrent_phase() {
		return current_phase;
	}

	/**
	 * @param current_phase
	 */
	public void setCurrent_phase(int current_phase) {
		this.current_phase = current_phase;
	}

	/**
	 * @return inst_id ʵ��ID
	 */
	public String getInst_id() {
		return inst_id;
	}

	/**
	 * @param inst_id ʵ��ID
	 */
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	/**
	 * @return storage_bk_path �����·��
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_bk_path �����·��
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/**
	 * @return inte_ver_num �汾����Ŀ��汾��
	 */
	public String getInte_ver_num() {
		return inte_ver_num;
	}

	/**
	 * @param inte_ver_num �汾����Ŀ��汾��
	 */
	public void setInte_ver_num(String inte_ver_num) {
		this.inte_ver_num = inte_ver_num;
	}

	/**
	 * @return file_xlsx_path �ļ��嵥·��
	 */
	public String getFile_xlsx_path() {
		return file_xlsx_path;
	}

	/**
	 * @param file_xlsx_path �ļ��嵥·��
	 */
	public void setFile_xlsx_path(String file_xlsx_path) {
		this.file_xlsx_path = file_xlsx_path;
	}	
	
}
