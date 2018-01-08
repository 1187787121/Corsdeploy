/**
 * Title: ListDeptByNameAction.java
 * File Description: 根据部门名称模糊分页查询部门信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.bean.PageDeptsByNameInputBean;
import com.wk.cd.system.dp.bean.PageDeptsByNameOutputBean;
import com.wk.cd.system.dp.dao.DeptQuery;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 根据部门名称模糊分页查询部门信息
 * @author xuy
 */
public class ListDeptsByNameAction extends ActionBasic<PageDeptsByNameInputBean, PageDeptsByNameOutputBean> {

	@Inject 
	private ActionLogPublicService lgsvc;
	@Inject 
	private DeptQuery deptQuery;
	@Inject
	private UsUserCombineDaoService combineDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	/**
	 * 
	 * Description: 根据部门名称模糊分页查询部门信息
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected PageDeptsByNameOutputBean doAction(PageDeptsByNameInputBean input) {
		PageDeptsByNameOutputBean output=new PageDeptsByNameOutputBean();
		//DBIterator<DeptExtendsBean> infos=deptQuery.queryAllDeptInfo();
		DBIterator<DeptExtendsBean> infos=null;
		List<String> dept_list = null;
		//查询当前用户角色
		List<Integer> user_role_type_list = combineDaoService.queryRoleTypeByUserId(input.getOrg_user_id());
		//如果是超级管理员或者信息管理员，则能增加所有部门
		if(!user_role_type_list.contains(1) && !user_role_type_list.contains(2)){
			String[] str =new String[]{input.getOrg_dept_id()};
			dept_list = dpDeptDaoService.queryDeptIdByKey(str);
			infos = dpDeptDaoService.queryAllDeptInfo(listToString(dept_list));
			
		}else{
			infos = deptQuery.queryAllDeptInfo();
		}
		List<DeptExtendsBean> dept_infos=new ArrayList<DeptExtendsBean>();
		try{
			while(infos.hasNext()){
				dept_infos.add(infos.next());
			}
		}finally{
			infos.close();
		}
		output.setDeptInfos(dept_infos);
		return output;
	}

	/**
	 * 将输入的列表转换为字符串，例如列表中为a b c，转换后则为('a','b','c')
	 * @param list 输入列表
	 * @return 字符串
	 */
	private String listToString(List<String> list) {
		String str = "";
		// 输入的部门角色列表信息为空报错
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "输入列表信息");
		}
		for (String s : list) {
			str += "'" + s + "',";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return str;
	}
	/**
	 * Description:新增部门日志信息
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected String getLogTxt(PageDeptsByNameInputBean input) {
		List<String> dept_val = new ArrayList<String>();
		dept_val.add(input.getDept_cn_name());//将被查询部门的名称记录日志
		return lgsvc.getLogTxt("查询部门信息", dept_val);
	}

}
