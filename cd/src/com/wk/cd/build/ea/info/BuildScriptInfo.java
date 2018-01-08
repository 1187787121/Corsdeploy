/**
 * Title: BuildScriptInfo.java
 * File Description: �����ű���Ϣ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDateTime;
/**
 * Class description:�����ű���Ϣ��
 * @author AutoGen
 */
@Table("BUILD_SCRIPT")
@PrimaryKey({"work_id","script_type","scirpt_bk_seq"})
public class BuildScriptInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�����ű���Ϣ��";

	/**
	 *������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";

	/**
	 *�ű�����
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "�ű�����";

	/**
	 *�ű����
	 */
	private long scirpt_bk_seq;

	public static final String SCIRPT_BK_SEQCN = "�ű����";

	/**
	 *�ű���ʽ
	 */
	private SCRIPT_METHOD script_method;

	public static final String SCRIPT_METHODCN = "�ű���ʽ";

	/**
	 *���ID
	 */
	private String module_id;

	public static final String MODULE_IDCN = "���ID";

	/**
	 *���������
	 */
	private String module_cn_name;

	public static final String MODULE_CN_NAMECN = "���������";

	/**
	 *����ԴUUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "����ԴUUID";

	/**
	 *���������UUID
	 */
	private String module_param_uuid;

	public static final String MODULE_PARAM_UUIDCN = "���������UUID";

	/**
	 *ʵ��ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "ʵ��ID";

	/**
	 *�ű�����
	 */
	private String script_text;

	public static final String SCRIPT_TEXTCN = "�ű�����";

	/**
	 *ִ��״̬
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "ִ��״̬";

	/**
	 *ִ�н��
	 */
	private EXE_RESULT exe_result;

	public static final String EXE_RESULTCN = "ִ�н��";

	/**
	 *ִ����־
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "ִ����־";

	/**
	 *ִ����
	 */
	private String exe_user_id;

	public static final String EXE_USER_IDCN = "ִ����";

	/**
	 *ִ�п�ʼʱ��
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "ִ�п�ʼʱ��";

	/**
	 *ִ�н���ʱ��
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "ִ�н���ʱ��";

	/**
	 *��ʱ
	 */
	private int time_used;

	public static final String TIME_USEDCN = "��ʱ";

	/**
	 *���ִ������
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPL_TYPECN = "���ִ������";

	/**
	 *@return work_id ������
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 *@param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 *@return script_type �ű�����
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 *@param script_type �ű�����
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}

	/**
	 *@return scirpt_bk_seq �ű����
	 */
	public long getScirpt_bk_seq() {
		return this.scirpt_bk_seq;
	}

	/**
	 *@param scirpt_bk_seq �ű����
	 */
	public void setScirpt_bk_seq(long scirpt_bk_seq) {
		this.scirpt_bk_seq = scirpt_bk_seq;
	}

	/**
	 *@return script_method �ű���ʽ
	 */
	public SCRIPT_METHOD getScript_method() {
		return this.script_method;
	}

	/**
	 *@param script_method �ű���ʽ
	 */
	public void setScript_method(SCRIPT_METHOD script_method) {
		this.script_method = script_method;
	}

	/**
	 *@return module_id ���ID
	 */
	public String getModule_id() {
		return this.module_id;
	}

	/**
	 *@param module_id ���ID
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	/**
	 *@return module_cn_name ���������
	 */
	public String getModule_cn_name() {
		return this.module_cn_name;
	}

	/**
	 *@param module_cn_name ���������
	 */
	public void setModule_cn_name(String module_cn_name) {
		this.module_cn_name = module_cn_name;
	}

	/**
	 *@return soc_uuid ����ԴUUID
	 */
	public String getSoc_uuid() {
		return this.soc_uuid;
	}

	/**
	 *@param soc_uuid ����ԴUUID
	 */
	public void setSoc_uuid(String soc_uuid) {
		this.soc_uuid = soc_uuid;
	}

	/**
	 *@return module_param_uuid ���������UUID
	 */
	public String getModule_param_uuid() {
		return this.module_param_uuid;
	}

	/**
	 *@param module_param_uuid ���������UUID
	 */
	public void setModule_param_uuid(String module_param_uuid) {
		this.module_param_uuid = module_param_uuid;
	}

	/**
	 *@return instance_id ʵ��ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id ʵ��ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 *@return script_text �ű�����
	 */
	public String getScript_text() {
		return this.script_text;
	}

	/**
	 *@param script_text �ű�����
	 */
	public void setScript_text(String script_text) {
		this.script_text = script_text;
	}

	/**
	 *@return exe_status ִ��״̬
	 */
	public EXE_STATUS getExe_status() {
		return this.exe_status;
	}

	/**
	 *@param exe_status ִ��״̬
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 *@return exe_result ִ�н��
	 */
	public EXE_RESULT getExe_result() {
		return this.exe_result;
	}

	/**
	 *@param exe_result ִ�н��
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 *@return exelog_bk_path ִ����־
	 */
	public String getExelog_bk_path() {
		return this.exelog_bk_path;
	}

	/**
	 *@param exelog_bk_path ִ����־
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}

	/**
	 *@return exe_user_id ִ����
	 */
	public String getExe_user_id() {
		return this.exe_user_id;
	}

	/**
	 *@param exe_user_id ִ����
	 */
	public void setExe_user_id(String exe_user_id) {
		this.exe_user_id = exe_user_id;
	}

	/**
	 *@return start_bk_tm ִ�п�ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return this.start_bk_tm;
	}

	/**
	 *@param start_bk_tm ִ�п�ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 *@return end_bk_tm ִ�н���ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return this.end_bk_tm;
	}

	/**
	 *@param end_bk_tm ִ�н���ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 *@return time_used ��ʱ
	 */
	public int getTime_used() {
		return this.time_used;
	}

	/**
	 *@param time_used ��ʱ
	 */
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}

	/**
	 *@return impl_type ���ִ������
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 *@param impl_type ���ִ������
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

}
