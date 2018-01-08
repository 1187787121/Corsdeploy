/**
 * Title: WkListDetailInfo.java
 * File Description: ������������ϸ�����
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-18
 */

package com.wk.cd.work.wk.info;

import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:������������ϸ�����
 * @author AutoGen
 */
@Table("WK_LIST_DETAIL")
@PrimaryKey({"pend_work_seq","inte_class_name","lst_inte_name","inte_name","inte_bk_num"})
public class WkListDetailInfo {
	/**
	 *������
	 */
	public static final String TABLECN = "������������ϸ�����";

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
	 *��������
	 */
	private String lst_inte_name;

	public static final String LST_INTE_NAMECN = "��������";

	/**
	 *�ӿ�����
	 */
	private String inte_name;

	public static final String INTE_NAMECN = "�ӿ�����";

	/**
	 *�����±�
	 */
	private int inte_bk_num;

	public static final String INTE_BK_NUMCN = "�����±�";

	/**
	 *����Ԫ������
	 */
	private String lstelm_class_name;

	public static final String LSTELM_CLASS_NAMECN = "����Ԫ������";

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
	 *@return lst_inte_name ��������
	 */
	public String getLst_inte_name() {
		return this.lst_inte_name;
	}

	/**
	 *@param lst_inte_name ��������
	 */
	public void setLst_inte_name(String lst_inte_name) {
		this.lst_inte_name = lst_inte_name;
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
	 *@return inte_bk_num �����±�
	 */
	public int getInte_bk_num() {
		return this.inte_bk_num;
	}

	/**
	 *@param inte_bk_num �����±�
	 */
	public void setInte_bk_num(int inte_bk_num) {
		this.inte_bk_num = inte_bk_num;
	}

	/**
	 *@return lstelm_class_name ����Ԫ������
	 */
	public String getLstelm_class_name() {
		return this.lstelm_class_name;
	}

	/**
	 *@param lstelm_class_name ����Ԫ������
	 */
	public void setLstelm_class_name(String lstelm_class_name) {
		this.lstelm_class_name = lstelm_class_name;
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
