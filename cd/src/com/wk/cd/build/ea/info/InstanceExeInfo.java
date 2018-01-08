/**
 * Title: InstanceExeInfo.java
 * File Description: ʵ��ִ����Ϣ��
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-3
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.build.ea.bean.BuildMonPhaseBean;
import com.wk.cd.build.ea.bean.PubMonStepBean;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:ʵ��ִ����Ϣ��
 * @author AutoGen
 */
@Table("INSTANCE_EXE")
@PrimaryKey({"instance_id","inst_bk_no"})
public class InstanceExeInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "ʵ��ִ����Ϣ��";

	/**
	 *ִ��ʵ��ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "ִ��ʵ��ID";

	/**
	 *ִ�б��
	 */
	private int inst_bk_no;

	public static final String INST_BK_NOCN = "ִ�б��";

	/**
	 *ģ������׶κ�
	 */
	private int tpl_bk_no;

	public static final String TPL_BK_NOCN = "ģ������׶κ�";

	/**
	 *�׶�����
	 */
	private String step_bk_desc;

	public static final String STEP_BK_DESCCN = "�׶�����";

	/**
	 *��������
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "��������";

	/**
	 *����Դ��
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ��";

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
	 *ִ����Ϣ
	 */
	private String exec_text;

	public static final String EXEC_TEXTCN = "ִ����Ϣ";

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
	 *@return instance_id ִ��ʵ��ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id ִ��ʵ��ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 *@return inst_bk_no ִ�б��
	 */
	public int getInst_bk_no() {
		return this.inst_bk_no;
	}

	/**
	 *@param inst_bk_no ִ�б��
	 */
	public void setInst_bk_no(int inst_bk_no) {
		this.inst_bk_no = inst_bk_no;
	}

	/**
	 *@return tpl_bk_no ģ������׶κ�
	 */
	public int getTpl_bk_no() {
		return this.tpl_bk_no;
	}

	/**
	 *@param tpl_bk_no ģ������׶κ�
	 */
	public void setTpl_bk_no(int tpl_bk_no) {
		this.tpl_bk_no = tpl_bk_no;
	}

	/**
	 *@return step_bk_desc �׶�����
	 */
	public String getStep_bk_desc() {
		return this.step_bk_desc;
	}

	/**
	 *@param step_bk_desc �׶�����
	 */
	public void setStep_bk_desc(String step_bk_desc) {
		this.step_bk_desc = step_bk_desc;
	}

	/**
	 *@return server_name ��������
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name ��������
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 *@return soc_name ����Դ��
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 *@param soc_name ����Դ��
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
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
	 *@return exec_text ִ����Ϣ
	 */
	public String getExec_text() {
		return this.exec_text;
	}

	/**
	 *@param exec_text ִ����Ϣ
	 */
	public void setExec_text(String exec_text) {
		this.exec_text = exec_text;
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
	
	public static PubMonStepBean copyToMonBean(InstanceExeInfo info){
		PubMonStepBean bean = new PubMonStepBean();
		bean.setPhase_id(info.getInst_bk_no());
		bean.setPhase_bk_desc(info.getStep_bk_desc());
		bean.setExe_status(info.getExe_status());
		bean.setTask_exe_result(info.getExe_result());
		return bean;	
	}
	
	public static BuildMonPhaseBean copyToBuildMonBean(InstanceExeInfo info){
		BuildMonPhaseBean bean = new BuildMonPhaseBean();
		bean.setPhase_id(info.getInst_bk_no());
		bean.setPhase_name(info.getStep_bk_desc());
		bean.setExe_status(info.getExe_status());
		bean.setTask_exe_result(info.getExe_result());
		return bean;
	}

}