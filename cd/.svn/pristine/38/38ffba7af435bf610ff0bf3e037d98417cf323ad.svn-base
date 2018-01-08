/**
 * Title: ViewDeptAction.java
 * File Description: 部门查看类
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年7月26日
 */
package com.wk.cd.system.dp.action;

import java.util.List;

import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.bean.ViewDeptInputBean;
import com.wk.cd.system.dp.bean.ViewDeptOutputBean;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.lang.Inject;

/**
 * Class Description: 部门查看类
 * @author yangl
 */
public class ViewDeptAction
		extends IViewActionBasic<ViewDeptInputBean, ViewDeptOutputBean> {

	@Inject
	private DpDeptDaoService dpDeptDaoService;
	
	public ViewDeptOutputBean queryDeptList(ViewDeptInputBean input){
		ViewDeptOutputBean output = new ViewDeptOutputBean();
		List<DeptExtendsBean> dept_list = dpDeptDaoService.queryAllDeptIdAndCnName();
		output.setDept_list(dept_list);
		return output;
	}
	
}
