/**
 * Title: ExeBuildExeScriptInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ִ�й����ű���������ӿ�
 * @author xuph
 */
public class ExeBuildExeScriptInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : -1134163628312966507L
	 */ 
	private static final long serialVersionUID = -1134163628312966507L;

	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 *�ű����
	 */
	private long script_bk_seq;

	public static final String SCRIPT_BK_SEQCN = "�ű����";
	
	/**
	 *�ű�����
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "�ű�����";

	/**
	 * @return work_id
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 * @return script_bk_seq
	 */
	public long getScript_bk_seq() {
		return script_bk_seq;
	}

	/**
	 * @param script_bk_seq
	 */
	public void setScript_bk_seq(long script_bk_seq) {
		this.script_bk_seq = script_bk_seq;
	}

	/**
	 * @return script_type
	 */
	public SCRIPT_TYPE getScript_type() {
		return script_type;
	}

	/**
	 * @param script_type
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}
	
}
