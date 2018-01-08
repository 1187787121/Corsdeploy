/**
 * Title: WkWorkProcessInfo.java
 * File Description: 任务审批流程表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-31
 */

package com.wk.cd.work.wk.info;

import java.io.Serializable;

import com.wk.cd.enu.PEND_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:任务审批流程表
 * @author AutoGen
 */
@Table("WK_WORK_PROCESS")
@PrimaryKey({"pend_work_seq","deal_seq"})
public class WkWorkProcessInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "任务审批流程表";

	/**
	 *待处理任务流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理任务流水号";

	/**
	 *处理序号
	 */
	private int deal_seq;

	public static final String DEAL_SEQCN = "处理序号";

	/**
	 *审批人
	 */
	private String pend_user_id;

	public static final String PEND_USER_IDCN = "审批人";

	/**
	 *审批人中文名
	 */
	private String pend_user_cn_name;

	public static final String PEND_USER_CN_NAMECN = "审批人中文名";

	/**
	 *审批方式
	 */
	private PEND_TYPE pend_type;

	public static final String PEND_TYPECN = "审批方式";

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
	 *@return deal_seq 处理序号
	 */
	public int getDeal_seq() {
		return this.deal_seq;
	}

	/**
	 *@param deal_seq 处理序号
	 */
	public void setDeal_seq(int deal_seq) {
		this.deal_seq = deal_seq;
	}

	/**
	 *@return pend_user_id 审批人
	 */
	public String getPend_user_id() {
		return this.pend_user_id;
	}

	/**
	 *@param pend_user_id 审批人
	 */
	public void setPend_user_id(String pend_user_id) {
		this.pend_user_id = pend_user_id;
	}

	/**
	 *@return pend_user_cn_name 审批人中文名
	 */
	public String getPend_user_cn_name() {
		return this.pend_user_cn_name;
	}

	/**
	 *@param pend_user_cn_name 审批人中文名
	 */
	public void setPend_user_cn_name(String pend_user_cn_name) {
		this.pend_user_cn_name = pend_user_cn_name;
	}

	/**
	 *@return pend_type 审批方式
	 */
	public PEND_TYPE getPend_type() {
		return this.pend_type;
	}

	/**
	 *@param pend_type 审批方式
	 */
	public void setPend_type(PEND_TYPE pend_type) {
		this.pend_type = pend_type;
	}

}
