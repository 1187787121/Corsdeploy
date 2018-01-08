/**
 * Title: MgMsgUserInfo.java
 * File Description: 消息用户接收表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */

package com.wk.cd.system.mg.info;

import com.wk.util.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:消息用户接收表
 * @author AutoGen
 */
@Table("MG_MSG_USER")
@PrimaryKey({"work_seq","rc_user_id"})
public class MgMsgUserInfo {
	/**
	 *表名称
	 */
	public static final String TABLECN = "消息用户接收表";

	/**
	 *消息流水号
	 */
	private String work_seq;

	public static final String WORK_SEQCN = "消息流水号";

	/**
	 *接收用户
	 */
	private String rc_user_id;

	public static final String RC_USER_IDCN = "接收用户";
	
	/**
	 *关注标志
	 */
	private YN_FLAG attent_yn_flag;

	public static final String ATTENT_YN_FLAGCN = "关注标志";

	/**
	 *接收状态
	 */
	private RC_FLAG rc_flag;

	public static final String RC_FLAGCN = "接收状态";

	/**
	 *接收日期
	 */
	private JaDate rc_bk_date;

	public static final String RC_BK_DATECN = "接收日期";

	/**
	 *接收时间
	 */
	private JaTime rc_bk_time;

	public static final String RC_BK_TIMECN = "接收时间";
	
	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

	/**
	 *@return work_seq 消息流水号
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 *@param work_seq 消息流水号
	 */
	public void setWork_seq(String work_seq) {
		this.work_seq = work_seq;
	}

	/**
	 *@return rc_user_id 接收用户
	 */
	public String getRc_user_id() {
		return this.rc_user_id;
	}

	/**
	 *@param rc_user_id 接收用户
	 */
	public void setRc_user_id(String rc_user_id) {
		this.rc_user_id = rc_user_id;
	}
	

	/**
	 * @return attent_yn_flag 关注标志
	 */
	public YN_FLAG getAttent_yn_flag() {
		return this.attent_yn_flag;
	}

	/**
	 * @param attent_yn_flag 关注标志
	 */
	public void setAttent_yn_flag(YN_FLAG attent_yn_flag) {
		this.attent_yn_flag = attent_yn_flag;
	}

	/**
	 *@return rc_flag 接收状态
	 */
	public RC_FLAG getRc_flag() {
		return this.rc_flag;
	}

	/**
	 *@param rc_flag 接收状态
	 */
	public void setRc_flag(RC_FLAG rc_flag) {
		this.rc_flag = rc_flag;
	}

	/**
	 *@return rc_bk_date 接收日期
	 */
	public JaDate getRc_bk_date() {
		return this.rc_bk_date;
	}

	/**
	 *@param rc_bk_date 接收日期
	 */
	public void setRc_bk_date(JaDate rc_bk_date) {
		this.rc_bk_date = rc_bk_date;
	}

	/**
	 *@return rc_bk_time 接收时间
	 */
	public JaTime getRc_bk_time() {
		return this.rc_bk_time;
	}

	/**
	 *@param rc_bk_time 接收时间
	 */
	public void setRc_bk_time(JaTime rc_bk_time) {
		this.rc_bk_time = rc_bk_time;
	}

	/**
	 *@return rcd_state 记录状态
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state 记录状态
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}
}
