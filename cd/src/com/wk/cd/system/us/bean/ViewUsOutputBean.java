/**
 * Title: ViewUsOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2015年11月12日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsDeptRoleInfo;

/**
 * Class Description: 查询执行用户输出接口
 * @author Xul
 */
public class ViewUsOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 6476045665605979118L;
	
	/**
	 * 用户名列表
	 */
	private List<UserAppBean> user_list;
	
	public static final String USER_LISTCN = "用户名列表";
	
	/**
	 * 所属部门用户名ID及中文名列表
	 */
	private List<UserAppBean> user_dept_list;
	
	public static final String USER_DEPT_LISTCN = "所属部门用户名ID及中文名列表";
	
	/**
	 *用户部门的所属用户列表
	 */
	private List<UserAppBean> user_app_list;
	
	public static final String USER_APP_LISTCN = "用户部门的所属用户列表";
	
	/**
	 * 部门角色列表
	 */
	private List<UsDeptRoleInfo> dept_role_list;
	
	public static final String DEPT_ROLE_LISTCN = "部门角色列表";
	
	/**
	 * 校验结果
	 */
	private boolean result;

	public static final String RESULTCN = "校验结果";
	
	/**
	 * @return 用户名列表
	 */
	public List<UserAppBean> getUser_list() {
		return user_list;
	}
	
	/**
	 * @param user_list 用户名列表
	 */
	public void setUser_list(List<UserAppBean> user_list) {
		this.user_list = user_list;
	}
	
	/**
	 * @return 用户部门的所属用户列表
	 */
	public List<UserAppBean> getUser_app_list() {
		return user_app_list;
	}
	
	/**
	 * @param user_app_list 用户部门的所属用户列表
	 */
	public void setUser_app_list(List<UserAppBean> user_app_list) {
		this.user_app_list = user_app_list;
	}

	/**
	 * @return user_dept_list 所属部门用户名ID及中文名列表
	 */
	public List<UserAppBean> getUser_dept_list() {
		return user_dept_list;
	}

	/**
	 * @param user_dept_list 所属部门用户名ID及中文名列表
	 */
	public void setUser_dept_list(List<UserAppBean> user_dept_list) {
		this.user_dept_list = user_dept_list;
	}

	/**
	 * @return dept_role_list 部门角色列表
	 */
	public List<UsDeptRoleInfo> getDept_role_list() {
		return dept_role_list;
	}

	/**
	 * @param dept_role_list 部门角色列表
	 */
	public void setDept_role_list(List<UsDeptRoleInfo> dept_role_list) {
		this.dept_role_list = dept_role_list;
	}

	/**
	 * @return result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
