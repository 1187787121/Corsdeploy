/**
 * Title: PageQueryWorkAction.java
 * File Description:分页查询任务 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.wk.bean.PageQueryWorkInputBean;
import com.wk.cd.work.wk.bean.PageQueryWorkOutputBean;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:分页查询任务
 * @author tlw
 */
public class PageQueryWorkAction
		extends ActionBasic<PageQueryWorkInputBean, PageQueryWorkOutputBean> {
	@Inject
	private WorkConfigPublicService wcPubSrv;
	@Inject
	private UsGetUserInfoService usSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 分页查询任务定义表，输入接口为空，默认查询所有的任务，不为空则查询对应的列表信息
	 * @param input 输入信息
	 * @return 任务定义信息列表
	 */
	@Override
	protected PageQueryWorkOutputBean doAction(PageQueryWorkInputBean input) {
		PageQueryWorkOutputBean output = new PageQueryWorkOutputBean();
		logger.info("*********PageQueryWorkAction Begin**********");
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		// 非空检查
		Assert.assertNotEmpty(start_recd, PageQueryWorkInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageQueryWorkInputBean.LIMIT_RECDCN);
		// 获取角色类型
		List<Integer> role_type_list = usSrv.queryRoleTypeByUserId(input
				.getOrg_user_id());
		int[] arr = input.getWork_type_list();
		List<FUN_TYPE> work_type_list = new ArrayList<FUN_TYPE>();
		if (!Assert.isEmpty(arr)) {
			for (int i : arr) {
				work_type_list.add(FUN_TYPE.valueOf(FUN_TYPE.class, i));
			}
		}
		// 角色类型为超级管理员和系统管理员的可以查询未发布任务，其他用户不能查询未发布任务
		List<WkWorkInfo> work_list = wcPubSrv.pageAllWorkByWorkType(
				work_type_list, role_type_list, start_recd, limit_recd);
		output.setWork_list(work_list);
		output.setAll_recd(wcPubSrv.countAllWorkByWorkType(work_type_list,
				role_type_list));
		return output;
	}

	/**
	 * 新增日志
	 * @param input 分页查询输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageQueryWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("起始条数" + input.getStart_recd());
		lst_val.add("查询条数" + input.getLimit_recd());
		return lgsrv.getLogTxt("分页查询任务定义", lst_val);
	}

}
