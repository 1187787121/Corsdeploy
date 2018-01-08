/**
 * Title: PageDictAction.java
 * File Description: 分页查询数据字典列Action
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014年11月17日
 */
package com.wk.cd.system.dc.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dc.bean.PageDictInputBean;
import com.wk.cd.system.dc.bean.PageDictOutputBean;
import com.wk.cd.system.dc.dao.DcDictDaoService;
import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;

/**
 * Class Description:分页查询数据字典列Action
 * @author HT
 */
public class PageDictAction
		extends ActionBasic<PageDictInputBean, PageDictOutputBean> {
	@Inject
	private DcDictDaoService dictDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: 分页查询数据字典列Action方法
	 * @param input
	 * @return
	 */
	@Override
	protected PageDictOutputBean doAction(PageDictInputBean input) {
		PageDictOutputBean output = new PageDictOutputBean();

		// 必填项
		Assert.assertNotEmpty(input.getStart_recd(),
				PageDictInputBean.START_RECDCN);
		Assert.assertNotEmpty(input.getLimit_recd(),
				PageDictInputBean.LIMIT_RECDCN);

		List<DcDictInfo> dictList = dictDaoService.pageDict(input.getKeyword(),
				input.getStart_recd(), input.getLimit_recd());

		int pagecount = dictDaoService.getCount(input.getKeyword());

		output.setAll_recd(pagecount);
		output.setDict_list(dictList);
		return output;
	}

	/**
	 * Description: 写日志
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageDictInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("查询关键字：" + input.getKeyword());
		lst_val.add("起始条数：" + input.getStart_recd());
		lst_val.add("查询条数：" + input.getLimit_recd());
		return lgsvc.getLogTxt("获取数据字典分页信息", lst_val);
	}

}
