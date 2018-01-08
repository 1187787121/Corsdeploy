/**
 * Title: WkStruDetailInfo.java
 * File Description: ������������ϸ�ṹ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */

package com.wk.cd.work.wk.info;

import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:������������ϸ�ṹ��
 * @author AutoGen
 */
@Table("WK_STRU_DETAIL")
@PrimaryKey({"pend_work_seq","inte_class_name","inte_name"})
public class WkStruDetailInfo {
	/**
	 *������
	 */
	public static final String TABLECN = "������������ϸ�ṹ��";

	/**
	 *������������ˮ��
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "������������ˮ��";

	/**
	 *�ӿ�����
	 */
	private String inte_class_name;

	public static final String INTE_CLASS_NAMECN = "�ӿ�����";

	/**
	 *�ӿ�����
	 */
	private String inte_name;

	public static final String INTE_NAMECN = "�ӿ�����";

	/**
	 *�ӿ�����
	 */
	private String inte_type;

	public static final String INTE_TYPECN = "�ӿ�����";

	/**
	 *�ӿ�ֵ
	 */
	private String inte_value;

	public static final String INTE_VALUECN = "�ӿ�ֵ";

	/**
	 *��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 *@return pend_work_seq ������������ˮ��
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 *@param pend_work_seq ������������ˮ��
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 *@return inte_class_name �ӿ�����
	 */
	public String getInte_class_name() {
		return this.inte_class_name;
	}

	/**
	 *@param inte_class_name �ӿ�����
	 */
	public void setInte_class_name(String inte_class_name) {
		this.inte_class_name = inte_class_name;
	}

	/**
	 *@return inte_name �ӿ�����
	 */
	public String getInte_name() {
		return this.inte_name;
	}

	/**
	 *@param inte_name �ӿ�����
	 */
	public void setInte_name(String inte_name) {
		this.inte_name = inte_name;
	}

	/**
	 *@return inte_type �ӿ�����
	 */
	public String getInte_type() {
		return this.inte_type;
	}

	/**
	 *@param inte_type �ӿ�����
	 */
	public void setInte_type(String inte_type) {
		this.inte_type = inte_type;
	}

	/**
	 *@return inte_value �ӿ�ֵ
	 */
	public String getInte_value() {
		return this.inte_value;
	}

	/**
	 *@param inte_value �ӿ�ֵ
	 */
	public void setInte_value(String inte_value) {
		this.inte_value = inte_value;
	}

	/**
	 *@return rcd_state ��¼״̬
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state ��¼״̬
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

}
