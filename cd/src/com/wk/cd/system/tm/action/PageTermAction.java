/**
 * Title: PageTermAction.java
 * File Description: 查看终端
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.tm.bean.PageTermInputBean;
import com.wk.cd.system.tm.bean.PageTermOutputBean;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.lang.Inject;

/**
 * Class Description: 查看终端
 * @author tlw
 */
public class PageTermAction
		extends ActionBasic<PageTermInputBean, PageTermOutputBean> {
	@Inject
	private TmTermDaoService tmsrv;
	@Inject
	private ActionLogPublicService lgsrv;

	/**
	 * 查看终端
	 * @param input 输入信息
	 * @return 终端信息
	 */
	@Override
	protected PageTermOutputBean doAction(PageTermInputBean input) {
		PageTermOutputBean output = new PageTermOutputBean();
		output.setAll_recd(tmsrv.countInfo(input.getTerm_no(),
				input.getTerm_type(), input.getTerm_bk_desc()));
		output.setTerm_list(tmsrv.pageInfo(input.getTerm_no(),
				input.getTerm_type(), input.getTerm_bk_desc(),
				input.getStart_recd(), input.getLimit_recd()));
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageTermInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("终端号：" + input.getTerm_no());
		if (!Assert.isEmpty(input.getTerm_type())) {
			lst_val.add("终端类型：" + input.getTerm_type().getCname());
		}
		lst_val.add("终端说明：" + input.getTerm_bk_desc());
		return lgsrv.getLogTxt("查询终端信息", lst_val);
	}

}
