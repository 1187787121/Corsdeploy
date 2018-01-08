/**
 * Title: WkDetailRegisterInfo.java
 * File Description: 待处理任务明细登记表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-15
 */

package com.wk.cd.work.wk.info;

import java.io.Serializable;
import java.util.Arrays;

import com.wk.cd.enu.APROV_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:待处理任务明细登记表
 * @author AutoGen
 */
@Table("WK_DETAIL_REGISTER")
@PrimaryKey({"pend_work_seq"})
public class WkDetailRegisterInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "待处理任务明细登记表";

	/**
	 *待处理任务流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理任务流水号";

	/**
	 *接口明细信息
	 */
	private byte[] inte_detail;

	public static final String INTE_DETAILCN = "接口明细信息";

	/**
	 *审批展示类型
	 */
	private APROV_TYPE aprov_type;

	public static final String APROV_TYPECN = "审批展示类型";

	/**
	 *定制页面资源编码
	 */
	private String custom_rs_code;

	public static final String CUSTOM_RS_CODECN = "定制页面资源编码";

	/**
	 *申请页面代码
	 */
	private byte[] apply_html;

	public static final String APPLY_HTMLCN = "申请页面代码";

	/**
	 *@return pend_work_seq 待处理任务流水号
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 *@param pend_work_seq 待处理任务流水号
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 *@return inte_detail 接口明细信息
	 */
	public byte[] getInte_detail() {
		return this.inte_detail;
	}

	/**
	 *@param inte_detail 接口明细信息
	 */
	public void setInte_detail(byte[] inte_detail) {
		this.inte_detail = inte_detail;
	}

	/**
	 *@return aprov_type 审批展示类型
	 */
	public APROV_TYPE getAprov_type() {
		return this.aprov_type;
	}

	/**
	 *@param aprov_type 审批展示类型
	 */
	public void setAprov_type(APROV_TYPE aprov_type) {
		this.aprov_type = aprov_type;
	}

	/**
	 *@return custom_rs_code 定制页面资源编码
	 */
	public String getCustom_rs_code() {
		return this.custom_rs_code;
	}

	/**
	 *@param custom_rs_code 定制页面资源编码
	 */
	public void setCustom_rs_code(String custom_rs_code) {
		this.custom_rs_code = custom_rs_code;
	}

	/**
	 *@return apply_html 申请页面代码
	 */
	public byte[] getApply_html() {
		return this.apply_html;
	}

	/**
	 *@param apply_html 申请页面代码
	 */
	public void setApply_html(byte[] apply_html) {
		this.apply_html = apply_html;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "WkDetailRegisterInfo [pend_work_seq=" + pend_work_seq + ", inte_detail=" + Arrays.toString(inte_detail) + ", aprov_type=" + aprov_type + ", custom_rs_code="
				+ custom_rs_code + ", apply_html=" + Arrays.toString(apply_html) + "]";
	}

	
	
}
