/**
 * Title: AddMessInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月9日
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.YN_FLAG;

/**
 * Class Description: 新增消息输入接口
 * @author HT
 */
public class AddMessInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 5921635251038004889L;

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
	 * 是否发送所有用户
	 */
	private YN_FLAG all_yn_flag;

	public static final String ALL_YN_FLAFCN = "是否发送所有用户";
	
	/**
	 * 发送部门列表
	 */
	private String[] dept_arr;

	public static final String DEPT_ARRCN = "发送部门列表";
	
	/**
	 * 发送用户列表
	 */
	private String[] user_arr;

	public static final String USER_ARRCN = "发送用户列表";
	
	/**
	 * @return msg_title 消息标题
	 */
	public String getMsg_title() {
		return this.msg_title;
	}

	/**
	 * @param msg_title 消息标题
	 */
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	/**
	 * @return msg_text 消息正文
	 */
	public String getMsg_text() {
		return this.msg_text;
	}

	/**
	 * @param msg_text 消息正文
	 */
	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	/**
	 * @return msg_type 消息类型
	 */
	public MSG_TYPE getMsg_type() {
		return this.msg_type;
	}

	/**
	 * @param msg_type 消息类型
	 */
	public void setMsg_type(MSG_TYPE msg_type) {
		this.msg_type = msg_type;
	}

	/**
	 * @return file_path 文件路径
	 */
	public String getFile_path() {
		return this.file_path;
	}

	/**
	 * @param file_path 文件路径
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @return first_bk_fname 文件名1
	 */
	public String getFirst_bk_fname() {
		return this.first_bk_fname;
	}

	/**
	 * @param first_bk_fname 文件名1
	 */
	public void setFirst_bk_fname(String first_bk_fname) {
		this.first_bk_fname = first_bk_fname;
	}

	/**
	 * @return secd_bk_fname 文件名2
	 */
	public String getSecd_bk_fname() {
		return this.secd_bk_fname;
	}

	/**
	 * @param secd_bk_fname 文件名2
	 */
	public void setSecd_bk_fname(String secd_bk_fname) {
		this.secd_bk_fname = secd_bk_fname;
	}

	/**
	 * @return third_bk_fname 文件名3
	 */
	public String getThird_bk_fname() {
		return this.third_bk_fname;
	}

	/**
	 * @param third_bk_fname 文件名3
	 */
	public void setThird_bk_fname(String third_bk_fname) {
		this.third_bk_fname = third_bk_fname;
	}

	

	/**
	 * @return all_yn_flag 是否发送所有用户
	 */
	public YN_FLAG getAll_yn_flag() {
		return this.all_yn_flag;
	}

	/**
	 * @param all_yn_flag 是否发送所有用户
	 */
	public void setAll_yn_flag(YN_FLAG all_yn_flag) {
		this.all_yn_flag = all_yn_flag;
	}

	/**
	 * @return dept_arr 发送部门列表
	 */
	public String[] getDept_arr() {
		return this.dept_arr;
	}

	/**
	 * @param dept_arr 发送部门列表
	 */
	public void setDept_arr(String[] dept_arr) {
		this.dept_arr = dept_arr;
	}

	/**
	 * @return user_arr 发送用户列表
	 */
	public String[] getUser_arr() {
		return this.user_arr;
	}

	/**
	 * @param user_arr 发送用户列表
	 */
	public void setUser_arr(String[] user_arr) {
		this.user_arr = user_arr;
	}
	
}
