/**
 * Title: ListSubOptAction.java
 * File Description: 查询资源的下级操作列表
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年5月27日
 */
package com.wk.cd.system.rs.action;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.rs.bean.ListSubOptInputBean;
import com.wk.cd.system.rs.bean.ListSubOptOutputBean;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.lang.Inject;

/**
 * @author HT
 */
public class ListSubOptAction extends ActionBasic<ListSubOptInputBean, ListSubOptOutputBean>{
	@Inject 
	private RsPublicService rsOptService;
	/** 
	 * @param input
	 * @return 
	 */
	@Override
	protected ListSubOptOutputBean doAction(ListSubOptInputBean input) {
		ListSubOptOutputBean output=new ListSubOptOutputBean();
		String rs_code=input.getRs_code();
		Assert.assertNotEmpty(rs_code, ListSubOptInputBean.RS_CODECN);
		List<RsOptInfo> opt_list=rsOptService.getSubOptList(rs_code);
		output.setOpt_list(opt_list);
		return output;
	}

	/** 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ListSubOptInputBean input) {
		// TODO Auto-generated method stub
		return null;
	}

}
