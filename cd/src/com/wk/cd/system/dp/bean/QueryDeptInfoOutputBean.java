/**
 * Title: QueryDeptInfoOutputBean.java
 * File Description: 查询部门详细信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.DEPT_TYPE;

/**
 * Class Description: 查询部门详细信息输出接口
 * @author HT
 */
public class QueryDeptInfoOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -3444582666715939647L;
	
	/**
	 * 部门编码
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "部门编码";
	
	/**
	 * 部门简称
	 */
	private String dept_cn_name ;
	
	public static final String DEPT_CN_NAMECN = "部门简称";
	
	/**
	 * 部门全称
	 */
	private String dept_full_cname ;
	
	public static final String DEPT_FULL_CNAMECN = "部门全称";
	
	/**
	 * 部门类型
	 */
	private DEPT_TYPE dept_type ;
	
	public static final String DEPT_TYPECN = "部门类型";
	
	/**
	 * 部门级别
	 */
	private int dept_level ;
	
	public static final String DEPT_LEVELCN = "部门级别";
	
	/**
	 * 上级部门编码
	 */
	private String super_dept_id ;
	
	public static final String SUPER_DEPT_IDCN = "上级部门编码";
	
	/**
	 * 上级部门名称
	 */
	private String super_dept_cn_name ;
	
	public static final String SUPER_DEPT_CN_NAMECN = "上级部门名称";
	
	/**
	 * 机构号
	 */
	private String branch_no;
	
	public static final String BRANCH_NOCN = "机构号";
	
	/**
	 * @return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}
	
	/**
	 * @param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @return dept_cn_name 部门简称
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}
	
	/**
	 * @param dept_cn_name 部门简称
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
	 * @return dept_type 部门类型
	 */
	public DEPT_TYPE getDept_type() {
		return this.dept_type;
	}
	
	/**
	 * @param dept_type 部门类型
	 */
	public void setDept_type(DEPT_TYPE dept_type) {
		this.dept_type = dept_type;
	}
	
	/**
	 * @return dept_level 部门级别
	 */
	public int getDept_level() {
		return this.dept_level;
	}
	
	/**
	 * @param dept_level 部门级别
	 */
	public void setDept_level(int dept_level) {
		this.dept_level = dept_level;
	}
	
	/**
	 * @return super_dept_id 上级部门编码
	 */
	public String getSuper_dept_id() {
		return this.super_dept_id;
	}
	
	/**
	 * @param super_dept_id 上级部门编码
	 */
	public void setSuper_dept_id(String super_dept_id) {
		this.super_dept_id = super_dept_id;
	}
	
	/**
	 * @return super_dept_cn_name 上级部门名称
	 */
	public String getSuper_dept_cn_name() {
		return this.super_dept_cn_name;
	}
	
	/**
	 * @param super_dept_cn_name 上级部门名称
	 */
	public void setSuper_dept_cn_name(String super_dept_cn_name) {
		this.super_dept_cn_name = super_dept_cn_name;
	}
	
	/**
	 * @return branch_no 机构号
	 */
	public String getBranch_no() {
		return this.branch_no;
	}
	
	/**
	 * @param branch_no 机构号
	 */
	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}
}
