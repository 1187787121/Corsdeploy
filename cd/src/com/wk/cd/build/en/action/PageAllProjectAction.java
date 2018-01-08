/**
 * Title: PageAllProjectAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.bean.PageAllProjectInputBean;
import com.wk.cd.build.en.bean.PageAllProjectOutputBean;
import com.wk.cd.build.en.bean.PageEnvironmentInputBean;
import com.wk.cd.build.en.bean.PageSystemListInputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询项目信息
 * @author xuph
 */
public class PageAllProjectAction extends ActionBasic<PageAllProjectInputBean, PageAllProjectOutputBean>{
	@Inject private CeProjectDaoService ceProjectDaoService;
	@Inject private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject private ProjectPublicService projectPublicService;
	@Inject private SystemService systemService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected PageAllProjectOutputBean doAction(PageAllProjectInputBean input) {
		logger.info("-----------------PageAllProjectAction Begin------------------");
		PageAllProjectOutputBean output = new PageAllProjectOutputBean();
		int limit_recd = input.getLimit_recd();
		int start_recd = input.getStart_recd();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		String sys_name = input.getSys_name();
		// 非空校验
		Assert.assertNotEmpty(start_recd, PageEnvironmentInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageEnvironmentInputBean.LIMIT_RECDCN);
		Assert.assertNotEmpty(order_col_name,PageSystemListInputBean.ORDER_COL_NAMECN);
		Assert.assertNotEmpty(order_type,PageSystemListInputBean.ORDER_TYPECN);
		if(!Assert.isEmpty(sys_name)){
			sys_name = sys_name.trim();
		}
		// 分页查询项目信息
		List<CeProjectBean> proj_list = ceProjectDaoService.pageAllProject(sys_name,sys_name,order_col_name,order_type,start_recd,limit_recd);
		output.setProj_list(proj_list);
		int all_recd = ceProjectDaoService.countProjectNameBySysName(sys_name,sys_name);
		output.setAll_recd(all_recd);
		logger.info("-----------------PageAllProjectAction End--------------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageAllProjectInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("sys_name" + input.getSys_name());
		return lgsvc.getLogTxt("分页查询项目", lst_val);
	}

}
