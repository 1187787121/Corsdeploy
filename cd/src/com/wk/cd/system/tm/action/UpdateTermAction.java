/**
 * Title: UpdateTermAction.java
 * File Description: 更新终端配置信息
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
import com.wk.cd.system.tm.bean.UpdateTermInputBean;
import com.wk.cd.system.tm.bean.UpdateTermOutputBean;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 更新终端配置信息
 * @author tlw
 */
public class UpdateTermAction
		extends ActionBasic<UpdateTermInputBean, UpdateTermOutputBean> {

	@Inject
	private TmTermDaoService tmsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 更新终端配置信息
	 * @param input 输入信息
	 * @return
	 */
	@Override
	protected UpdateTermOutputBean doAction(UpdateTermInputBean input) {
		UpdateTermOutputBean output = new UpdateTermOutputBean();
		logger.info("************修改终端***************");
		TmTermInfo info = new TmTermInfo();
		String term_no = input.getTerm_no();
		Assert.assertNotEmpty(term_no, UpdateTermInputBean.TERM_NOCN);
		logger.info("修改的终端号是：[{}]" + term_no);
		info.setTerm_no(term_no);
		TmTermInfo info1 = tmsrv.getTermInfoForUpdate(info);
		info1.setTerm_type(input.getTerm_type());
		info1.setTerm_bk_desc(input.getTerm_bk_desc());
		tmsrv.updateInfo(info1);
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(UpdateTermInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("终端号：" + input.getTerm_no());
		lst_val.add("终端类型：" + input.getTerm_type().getCname());
		lst_val.add("终端说明：" + input.getTerm_bk_desc());
		return lgsrv.getLogTxt("修改终端", lst_val);
	}

}
