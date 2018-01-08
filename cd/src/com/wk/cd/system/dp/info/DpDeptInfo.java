/**
 * Title: DpDeptInfo.java
 * File Description: 部门表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.dp.info;

import java.io.Serializable;

import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.*;
/**
 * Class description:部门表
 * @author AutoGen
 */
@Table("DP_DEPT")
@PrimaryKey({"dept_id"})

public class DpDeptInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "部门表";

	/**
	 *部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";

	/**
	 *部门名称
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "部门名称";
	
	/**
	 * 部门全称
	 */
	private String dept_full_cname;
	
	public static final String DEPT_FULL_CNAMECN="部门全称";

	/**
	 *部门类型
	 */
	private DEPT_TYPE dept_type;

	public static final String DEPT_TYPECN = "部门类型";

	/**
	 *部门级别
	 */
	private int dept_level;

	public static final String DEPT_LEVELCN = "部门级别";

	/**
	 *上级部门编码
	 */
	private String super_dept_id;

	public static final String SUPER_DEPT_IDCN = "上级部门编码";

	/**
	 *
	 */
	private String branch_no;

	public static final String BRANCH_NOCN = "";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

	/**
	 *修改日期
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 *修改时间
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "修改时间";

	/**
	 *修改用户
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "修改用户";

	/**
	 *删除日期
	 */
	private JaDate del_bk_date;

	public static final String DEL_BK_DATECN = "删除日期";

	/**
	 *删除时间
	 */
	private JaTime del_bk_time;

	public static final String DEL_BK_TIMECN = "删除时间";

	/**
	 *删除用户
	 */
	private String del_user_id;

	public static final String DEL_USER_IDCN = "删除用户";

	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

	/**
	 *@return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 *@param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 *@return dept_cn_name 部门名称
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 *@param dept_cn_name 部门名称
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}
	
	

	/**
	 * @return dept_full_cname 部门全称
	 */
	public String getDept_full_cname() {
		return this.dept_full_cname;
	}

	/**
	 * @param dept_full_cname 部门全称
	 */
	public void setDept_full_cname(String dept_full_cname) {
		this.dept_full_cname = dept_full_cname;
	}

	/**
	 *@return dept_type 部门类型
	 */
	public DEPT_TYPE getDept_type() {
		return this.dept_type;
	}

	/**
	 *@param dept_type 部门类型
	 */
	public void setDept_type(DEPT_TYPE dept_type) {
		this.dept_type = dept_type;
	}

	/**
	 *@return dept_level 部门级别
	 */
	public int getDept_level() {
		return this.dept_level;
	}

	/**
	 *@param dept_level 部门级别
	 */
	public void setDept_level(int dept_level) {
		this.dept_level = dept_level;
	}

	/**
	 *@return super_dept_id 上级部门编码
	 */
	public String getSuper_dept_id() {
		return this.super_dept_id;
	}

	/**
	 *@param super_dept_id 上级部门编码
	 */
	public void setSuper_dept_id(String super_dept_id) {
		this.super_dept_id = super_dept_id;
	}

	/**
	 *@return branch_no 
	 */
	public String getBranch_no() {
		return this.branch_no;
	}

	/**
	 *@param branch_no 
	 */
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}

	/**
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
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
	 *@return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return modify_user_id 修改用户
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id 修改用户
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 *@return del_bk_date 删除日期
	 */
	public JaDate getDel_bk_date() {
		return this.del_bk_date;
	}

	/**
	 *@param del_bk_date 删除日期
	 */
	public void setDel_bk_date(JaDate del_bk_date) {
		this.del_bk_date = del_bk_date;
	}

	/**
	 *@return del_bk_time 删除时间
	 */
	public JaTime getDel_bk_time() {
		return this.del_bk_time;
	}

	/**
	 *@param del_bk_time 删除时间
	 */
	public void setDel_bk_time(JaTime del_bk_time) {
		this.del_bk_time = del_bk_time;
	}

	/**
	 *@return del_user_id 删除用户
	 */
	public String getDel_user_id() {
		return this.del_user_id;
	}

	/**
	 *@param del_user_id 删除用户
	 */
	public void setDel_user_id(String del_user_id) {
		this.del_user_id = del_user_id;
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
