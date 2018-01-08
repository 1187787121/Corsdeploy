/**
 * Title: ViewBuildTaskOutputBean.java
 * File Description: �鿴�����ļ��Ķ��б�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: �鿴�����ļ��Ķ��б�����ӿ�
 * @author chiss
 */
public class ViewBuildTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -1826609292259639223L;
	
	/**
	 * ���޸��ļ��б�
	 */
	private List<EnvModFileBean> pend_list;
	
	public static final String PEND_MODIFY_LISTCN = "�޸��ļ��б�";
	
	/**
	 * �޸��ļ��б�
	 */
	private List<EnvModFileBean> modify_list;
	
	public static final String MODIFY_LISTCN = "�޸��ļ��б�";
	
	/**
	 * ɾ���ļ��б�
	 */
	private List<EnvModFileBean> delete_list;
	
	public static final String DELETE_LISTCN = "ɾ���ļ��б�";
	
	/**
	 * �ļ��б�
	 */
	private List<FileListBean> file_list_bean;
	
	public static final String FILE_LIST_BEANCN = "�ļ��б�";
	
	/**
	 * ����״̬
	 */
	private TASK_STATUS task_status;
	
	public static final String TASK_STATUSCN = "����״̬";
	
	/**
	 * ִ�н��
	 */
	private EXE_RESULT exe_result;
	
	public static final String EXE_RESULTCN = "ִ�н��";
	
	/**
	 * ִ�ж���
	 */
	private EXE_METHOD exe_method;
	
	public static final String EXE_METHODCN = "ִ�ж���";
	
	/**
	 * ִ�п�ʼʱ��
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "ִ�п�ʼʱ��";

	/**
	 * ִ�н���ʱ��
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "ִ�н���ʱ��";
	
	/**
	 * ִ�н׶��б�
	 */
	private List<BuildMonPhaseBean> build_phase_list;
	
	public static final String BUILD_PHASE_LISTCN = "ִ�н׶��б�";
	
	/**
	 * ִ����־
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "ִ����־";
	
	/**
	 * �ܽ׶���
	 */
	private int all_phases;
	
	public static final String ALL_PHASESCN = "�ܽ׶���";
	
	/**
	 * ��ǰ�׶���
	 */
	private int exe_phase;
	
	public static final String EXE_PHASECN = "��ǰ�׶���";
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";

	/**
	 * ��������
	 */
	private String task_bk_desc;
	
	public static final String TASK_BK_DESCCN = "��������";
	
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
	 * ��ǰ����������
	 */
	private int build_step_id;
	
	public static final String BUILD_STEP_IDCN = "��ǰ����������";
	
	/**
	 * �׶��б�
	 */
//	private List<PhasePublishBean> phases;
//	
//	public static final String PHASESCN = "�׶��б�";
	private List<Phase> phases;
	
	public static final String PHASESCN = "�׶��б�";

	/**
	 * ģ������б�
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "ģ������б�";
	
	/**
	 * ����ִ����Ϣ
	 */
	private List<InstanceExeInfo>exemsg_list;
	
	public static final String EXEMSG_LISTCN = "����ִ����Ϣ";
	
	/**
	 * �ű�����
	 */
	private String[] script_text;
	
	public static final String SCRIPT_TEXTCN = "�ű�����";
	
	/**
	 * ģ����
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "ģ����";
	
	/**
	 * ���������ļ��б�
	 */
	private List<String> cfg_name_list;
	
	public static final String CFG_NAME_LISTCN = "���������ļ��б�";
	
	/**
	 * �ű���Ϣ
	 */
	private List<BuildScriptBean> script_list;
	
	public static final String SCRIPT_LISTCN = "�ű���Ϣ";
	
	/**
	 * �嵥�б�
	 */
	private List<TargetPackageBean> list_list;
	
	public static final String LIST_LISTCN = "�嵥�б�";
	
	/**
	 * Ͷ�����б�
	 */
	private List<TargetPackageBean> pac_list;
	
	public static final String PAC_LISTCN = "�嵥�б�";
	
	/**
	 * ��־��Ϣ
	 */
	private TargetPackageBean log_bean;
	
	public static final String LOG_BEANCN = "��־��Ϣ";
	
	/**
	 * ����Դ����
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ����";
	
	/**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
	
	/**
	 * �汾��·��
	 */
	private String ver_root_path;
	
	public static final String VER_ROOT_PATHCN = "�汾��·��";
	
	/**
	 * Ͷ���������仯��־��true�䡢false���䣩
	 */
	private boolean change_flag;
	
	public static final String CHANGE_FLAGCN = "Ͷ���������仯��־";
	
	/**
	 * @return pend_modify_list ���޸��ļ��б�
	 */
	public List<EnvModFileBean> getPend_list() {
		return pend_list;
	}

	/**
	 * @param pend_list ���޸��ļ��б�
	 */
	public void setPend_list(List<EnvModFileBean> pend_list) {
		this.pend_list = pend_list;
	}

	/**
	 * @return modify_list �޸��ļ��б�
	 */
	public List<EnvModFileBean> getModify_list() {
		return modify_list;
	}

	/**
	 * @param modify_list �޸��ļ��б�
	 */
	public void setModify_list(List<EnvModFileBean> modify_list) {
		this.modify_list = modify_list;
	}

	/**
	 * @return delete_list ɾ���ļ��б�
	 */
	public List<EnvModFileBean> getDelete_list() {
		return delete_list;
	}

	/**
	 * @param delete_list ɾ���ļ��б�
	 */
	public void setDelete_list(List<EnvModFileBean> delete_list) {
		this.delete_list = delete_list;
	}
	
	/**
	 * @return task_status ����״̬
	 */
	public TASK_STATUS getTask_status() {
		return task_status;
	}

	/**
	 * @param task_status ����״̬
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
	}

	/**
	 * @return exe_result ִ�н��
	 */
	public EXE_RESULT getExe_result() {
		return exe_result;
	}

	/**
	 * @param exe_result ִ�н��
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}
	
	/**
	 * @return exe_method ִ�ж���
	 */
	public EXE_METHOD getExe_method() {
		return exe_method;
	}

	/**
	 * @param exe_method ִ�ж���
	 */
	public void setExe_method(EXE_METHOD exe_method) {
		this.exe_method = exe_method;
	}

	/**
	 * @return start_bk_tm ִ�п�ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return start_bk_tm;
	}

	/**
	 * @param start_bk_tm ִ�п�ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 * @return end_bk_tm ִ�н���ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return end_bk_tm;
	}

	/**
	 * @param end_bk_tm ִ�н���ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}
	
	/**
	 * @return exelog_bk_path ִ����־
	 */
	public String getExelog_bk_path() {
		return exelog_bk_path;
	}

	/**
	 * @param exelog_bk_path ִ����־
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}

	/**
	 * @return build_phase_list ִ�н׶��б�
	 */
	public List<BuildMonPhaseBean> getBuild_phase_list() {
		return build_phase_list;
	}

	/**
	 * @param build_phase_list ִ�н׶��б�
	 */
	public void setBuild_phase_list(List<BuildMonPhaseBean> build_phase_list) {
		this.build_phase_list = build_phase_list;
	}

	/**
	 * @return all_phases �ܽ׶���
	 */
	public int getAll_phases() {
		return all_phases;
	}

	/**
	 * @param all_phases �ܽ׶���
	 */
	public void setAll_phases(int all_phases) {
		this.all_phases = all_phases;
	}

	/**
	 * @return exe_phase ��ǰ�׶���
	 */
	public int getExe_phase() {
		return exe_phase;
	}

	/**
	 * @param exe_phase ��ǰ�׶���
	 */
	public void setExe_phase(int exe_phase) {
		this.exe_phase = exe_phase;
	}

	/**
	 * @return file_list_bean �ļ��б�
	 */
	public List<FileListBean> getFile_list_bean() {
		return file_list_bean;
	}

	/**
	 * @param file_list_bean �ļ��б�
	 */
	public void setFile_list_bean(List<FileListBean> file_list_bean) {
		this.file_list_bean = file_list_bean;
	}
	
	/**
	 * @return phases �׶��б�
	 */
	public List<Phase> getPhases() {
		return phases;
	}

	/**
	 * @param phases �׶��б�
	 */
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	/**
	 * @return param_list ģ������б�
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list ģ������б�
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return env_name
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return task_bk_desc ��������
	 */
	public String getTask_bk_desc() {
		return task_bk_desc;
	}

	/**
	 * @param task_bk_desc ��������
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
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
	 * @return build_step_id ��ǰ����������
	 */
	public int getBuild_step_id() {
		return build_step_id;
	}

	/**
	 * @param build_step_id ��ǰ����������
	 */
	public void setBuild_step_id(int build_step_id) {
		this.build_step_id = build_step_id;
	}

	/**
	 * @return exemsg_list ����ִ����Ϣ
	 */
	public List<InstanceExeInfo> getExemsg_list() {
		return exemsg_list;
	}

	/**
	 * @param exemsg_list ����ִ����Ϣ
	 */
	public void setExemsg_list(List<InstanceExeInfo> exemsg_list) {
		this.exemsg_list = exemsg_list;
	}

	/**
	 * @return script_text �ű�����
	 */
	public String[] getScript_text() {
		return script_text;
	}

	/**
	 * @param script_text �ű�����
	 */
	public void setScript_text(String[] script_text) {
		this.script_text = script_text;
	}

	/**
	 * @return template_name ģ����
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name ģ����
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 * @return cfg_name_list ���������ļ��б�
	 */
	public List<String> getCfg_name_list() {
		return cfg_name_list;
	}

	/**
	 * @param cfg_name_list ���������ļ��б�
	 */
	public void setCfg_name_list(List<String> cfg_name_list) {
		this.cfg_name_list = cfg_name_list;
	}

	/**
	 * @return script_list
	 */
	public List<BuildScriptBean> getScript_list() {
		return script_list;
	}

	/**
	 * @param script_list
	 */
	public void setScript_list(List<BuildScriptBean> script_list) {
		this.script_list = script_list;
	}

	/**
	 * @return list_list �嵥�б�
	 */
	public List<TargetPackageBean> getList_list() {
		return list_list;
	}

	/**
	 * @param list_list �嵥�б�
	 */
	public void setList_list(List<TargetPackageBean> list_list) {
		this.list_list = list_list;
	}

	/**
	 * @return pac_list Ͷ�����б�
	 */
	public List<TargetPackageBean> getPac_list() {
		return pac_list;
	}

	/**
	 * @param pac_list Ͷ�����б�
	 */
	public void setPac_list(List<TargetPackageBean> pac_list) {
		this.pac_list = pac_list;
	}

	/**
	 * @return log_bean ��־��Ϣ
	 */
	public TargetPackageBean getLog_bean() {
		return log_bean;
	}

	/**
	 * @param log_bean ��־��Ϣ
	 */
	public void setLog_bean(TargetPackageBean log_bean) {
		this.log_bean = log_bean;
	}

	/**
	 * @return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return ce_server_name ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return ver_root_path �汾��·��
	 */
	public String getVer_root_path() {
		return ver_root_path;
	}

	/**
	 * @param ver_root_path �汾��·��
	 */
	public void setVer_root_path(String ver_root_path) {
		this.ver_root_path = ver_root_path;
	}

	/**
	 * @return change_flag Ͷ���������仯��־
	 */
	public boolean isChange_flag() {
		return change_flag;
	}

	/**
	 * @param change_flag Ͷ���������仯��־
	 */
	public void setChange_flag(boolean change_flag) {
		this.change_flag = change_flag;
	}
}