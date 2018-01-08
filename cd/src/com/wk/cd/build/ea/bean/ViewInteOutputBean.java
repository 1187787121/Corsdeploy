/**
 * Title: ViewInteOutputBean.java
 * File Description: ��������鿴��������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��������鿴��������ӿ�
 * @author Xul
 */
public class ViewInteOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 8768264623441972034L;
	
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
	 * �������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";
	
	/**
	 *��������
	 */
	private String prog_name;

	public static final String PROG_NAMECN = "��������";
	
	/**
	 * Դ��汾���汾��
	 */
	private String vercode_ver_num;

	public static final String VERCODE_VER_NUMCN = "����Դ��汾��";
	
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
	 * ����ִ����Ϣ�б�
	 */
	private List<InteMonStepBean> inte_step_list;
	
	public static final String INTE_STEP_LISTCN = "����ִ����Ϣ�б�";
	
	/**
	 * �����ļ��б�
	 */
	private List<DownFileBean> down_file_list;
	
	public static final String DOWN_FILE_LISTCN = "�����ļ��б�";
	
	/**
	 * �ܲ�����
	 */
	private int all_steps;
	
	public static final String ALL_STEPSCN = "�ܲ�����";
	
	/**
	 * ��ǰ������
	 */
	private int exe_step;
	
	public static final String EXE_STEPCN = "��ǰ������";
	
	/**
	 *ִ����־
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "ִ����־";
	
	/**
	 * ��ȫ·���б�
	 */
	private List<String> full_path_list;
	
	public static final String FULL_PATH_LISTCN = "��ȫ·���б�";
	
	/**
	 *ִ�ж���
	 */
	private EXE_METHOD exe_method;

	public static final String EXE_METHODCN = "ִ�ж���";
	
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
	 * �汾���б�
	 */
	private String[] version_list;
	
	public static final String VERSION_LISTCN = "�汾���б�";
	
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
	 * @return prog_name ��������
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name ��������
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	/**
	 * @return prog_id �������
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return vercode_ver_num Դ��汾���汾��
	 */
	public String getVercode_ver_num() {
		return vercode_ver_num;
	}

	/**
	 * @param vercode_ver_num Դ��汾���汾��
	 */
	public void setVercode_ver_num(String vercode_ver_num) {
		this.vercode_ver_num = vercode_ver_num;
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
	 * @return inte_step_list ����ִ����Ϣ�б�
	 */
	public List<InteMonStepBean> getInte_step_list() {
		return inte_step_list;
	}

	/**
	 * @param inte_step_list ����ִ����Ϣ�б�
	 */
	public void setInte_step_list(List<InteMonStepBean> inte_step_list) {
		this.inte_step_list = inte_step_list;
	}

	/**
	 * @return down_file_list �����ļ��б�
	 */
	public List<DownFileBean> getDown_file_list() {
		return down_file_list;
	}

	/**
	 * @param down_file_list �����ļ��б�
	 */
	public void setDown_file_list(List<DownFileBean> down_file_list) {
		this.down_file_list = down_file_list;
	}

	/**
	 * @return all_steps �ܲ�����
	 */
	public int getAll_steps() {
		return all_steps;
	}

	/**
	 * @param all_steps �ܲ�����
	 */
	public void setAll_steps(int all_steps) {
		this.all_steps = all_steps;
	}

	/**
	 * @return exe_step ��ǰ������
	 */
	public int getExe_step() {
		return exe_step;
	}

	/**
	 * @param exe_step ��ǰ������
	 */
	public void setExe_step(int exe_step) {
		this.exe_step = exe_step;
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
	 * @return full_path_list ��ȫ·���б�
	 */
	public List<String> getFull_path_list() {
		return full_path_list;
	}

	/**
	 * @param full_path_list ��ȫ·���б�
	 */
	public void setFull_path_list(List<String> full_path_list) {
		this.full_path_list = full_path_list;
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
	 * @return version_list �汾���б�
	 */
	public String[] getVersion_list() {
		return version_list;
	}

	/**
	 * @param version_list �汾���б�
	 */
	public void setVersion_list(String[] version_list) {
		this.version_list = version_list;
	}
}
