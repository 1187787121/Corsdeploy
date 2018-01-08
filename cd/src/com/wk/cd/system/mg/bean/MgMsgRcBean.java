/**
 * Title: MsMsgRcBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年3月31日
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 消息信息接收类
 * @author HT
 */
public class MgMsgRcBean {
	/**
	 *消息流水号
	 */
	private String work_seq;

	public static final String WORK_SEQCN = "消息流水号";

	/**
	 *消息标题
	 */
	private String msg_title;

	public static final String MSG_TITLECN = "消息标题";

	/**
	 *消息正文
	 */
	private String msg_text;

	public static final String MSG_TEXTCN = "消息正文";

	/**
	 *消息类型
	 */
	private MSG_TYPE msg_type;

	public static final String MSG_TYPECN = "消息类型";

	/**
	 *文件路径
	 */
	private String file_path;

	public static final String FILE_PATHCN = "文件路径";

	/**
	 *文件名1
	 */
	private String first_bk_fname;

	public static final String FIRST_BK_FNAMECN = "文件名1";

	/**
	 *文件名2
	 */
	private String secd_bk_fname;

	public static final String SECD_BK_FNAMECN = "文件名2";

	/**
	 *文件名3
	 */
	private String third_bk_fname;

	public static final String THIRD_BK_FNAMECN = "文件名3";

	/**
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

	/**
	 *创建用户姓名
	 */
	private String crt_user_name;

	public static final String CRT_USER_NAMECN = "创建用户姓名";
	
	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";

	
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
	 *关注标志
	 */
	private YN_FLAG attent_yn_flag;

	public static final String ATTENT_YN_FLAGCN = "关注标志";


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
	 *@return msg_title 消息标题
	 */
	public String getMsg_title() {
		return this.msg_title;
	}

	/**
	 *@param msg_title 消息标题
	 */
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	/**
	 *@return msg_text 消息正文
	 */
	public String getMsg_text() {
		return this.msg_text;
	}

	/**
	 *@param msg_text 消息正文
	 */
	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	/**
	 *@return msg_type 消息类型
	 */
	public MSG_TYPE getMsg_type() {
		return this.msg_type;
	}

	/**
	 *@param msg_type 消息类型
	 */
	public void setMsg_type(MSG_TYPE msg_type) {
		this.msg_type = msg_type;
	}

	/**
	 *@return file_path 文件路径
	 */
	public String getFile_path() {
		return this.file_path;
	}

	/**
	 *@param file_path 文件路径
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 *@return first_bk_fname 文件名1
	 */
	public String getFirst_bk_fname() {
		return this.first_bk_fname;
	}

	/**
	 *@param first_bk_fname 文件名1
	 */
	public void setFirst_bk_fname(String first_bk_fname) {
		this.first_bk_fname = first_bk_fname;
	}

	/**
	 *@return secd_bk_fname 文件名2
	 */
	public String getSecd_bk_fname() {
		return this.secd_bk_fname;
	}

	/**
	 *@param secd_bk_fname 文件名2
	 */
	public void setSecd_bk_fname(String secd_bk_fname) {
		this.secd_bk_fname = secd_bk_fname;
	}

	/**
	 *@return third_bk_fname 文件名3
	 */
	public String getThird_bk_fname() {
		return this.third_bk_fname;
	}

	/**
	 *@param third_bk_fname 文件名3
	 */
	public void setThird_bk_fname(String third_bk_fname) {
		this.third_bk_fname = third_bk_fname;
	}

	/**
	 *@return crt_user_id 创建用户
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建用户
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}
	

	/**
	 * @return crt_user_name 创建用户姓名
	 */
	public String getCrt_user_name() {
		return this.crt_user_name;
	}

	/**
	 * @param crt_user_name 创建用户姓名
	 */
	public void setCrt_user_name(String crt_user_name) {
		this.crt_user_name = crt_user_name;
	}

	/**
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
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

}
