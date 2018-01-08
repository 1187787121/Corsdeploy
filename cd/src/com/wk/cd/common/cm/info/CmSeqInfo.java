/**
 * Title: CmSeqInfo.java
 * File Description: ��ű�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */
package com.wk.cd.common.cm.info;

import com.wk.cd.enu.SEQ_TYPE;
import com.wk.db.AppCache;
import com.wk.db.DBCacheType;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;

import java.io.Serializable;

@Table("CM_SEQ")
@PrimaryKey({ "seq_name" })
@AppCache(enabled = true, type = DBCacheType.READWRITE)
public class CmSeqInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "��ű�";

	/**
	 *�������
	 */
	private String seq_name;

	public static final String SEQ_NAMECN = "�������";

	/**
	 *��ǰ���
	 */
	private long cur_bk_seq;

	public static final String CUR_BK_SEQCN = "��ǰ���";

	/**
	 *��ų���
	 */
	private int seq_fld_length;

	public static final String SEQ_FLD_LENGTHCN = "��ų���";

	/**
	 *�������
	 */
	private SEQ_TYPE seq_type;

	public static final String SEQ_TYPECN = "�������";

	/**
	 *�ϴ��޸�����
	 */
	private JaDate lmod_bk_date;

	public static final String LMOD_BK_DATECN = "�ϴ��޸�����";

	/**
	 *�������
	 */
	private long ls_bk_seq;

	public static final String LS_BK_SEQCN = "�������";

	/**
	 *@return seq_name �������
	 */
	public String getSeq_name() {
		return this.seq_name;
	}

	/**
	 *@param seq_name �������
	 */
	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	/**
	 *@return cur_bk_seq ��ǰ���
	 */
	public long getCur_bk_seq() {
		return this.cur_bk_seq;
	}

	/**
	 *@param cur_bk_seq ��ǰ���
	 */
	public void setCur_bk_seq(long cur_bk_seq) {
		this.cur_bk_seq = cur_bk_seq;
	}

	/**
	 *@return seq_fld_length ��ų���
	 */
	public int getSeq_fld_length() {
		return this.seq_fld_length;
	}

	/**
	 *@param seq_fld_length ��ų���
	 */
	public void setSeq_fld_length(int seq_fld_length) {
		this.seq_fld_length = seq_fld_length;
	}

	/**
	 *@return seq_type �������
	 */
	public SEQ_TYPE getSeq_type() {
		return this.seq_type;
	}

	/**
	 *@param seq_type �������
	 */
	public void setSeq_type(SEQ_TYPE seq_type) {
		this.seq_type = seq_type;
	}

	/**
	 *@return lmod_bk_date �ϴ��޸�����
	 */
	public JaDate getLmod_bk_date() {
		return this.lmod_bk_date;
	}

	/**
	 *@param lmod_bk_date �ϴ��޸�����
	 */
	public void setLmod_bk_date(JaDate lmod_bk_date) {
		this.lmod_bk_date = lmod_bk_date;
	}

	/**
	 *@return ls_bk_seq �������
	 */
	public long getLs_bk_seq() {
		return this.ls_bk_seq;
	}

	/**
	 *@param ls_bk_seq �������
	 */
	public void setLs_bk_seq(long ls_bk_seq) {
		this.ls_bk_seq = ls_bk_seq;
	}
	
	@Override
	public CmSeqInfo clone(){
		try{
			return (CmSeqInfo) super.clone();
		}catch(CloneNotSupportedException e){
		}
		return null;
	}
}