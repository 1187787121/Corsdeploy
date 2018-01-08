/**
 * Title: PageUnhandleWorkAction.java
 * File Description: ��ѯ������˻��ߴ���Ȩ������
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-26
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.PageUnhandleWorkInputBean;
import com.wk.cd.work.wk.bean.PageUnhandleWorkOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯ������˻��ߴ���Ȩ������
 * 
 * @author tlw
 */
public class PageUnhandleWorkAction extends
		ActionBasic<PageUnhandleWorkInputBean, PageUnhandleWorkOutputBean> {
	@Inject
	private WorkPublicService daosrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ��ѯ������˻��ߴ���Ȩ������
	 * 
	 * @param input
	 *            ������Ϣ
	 * @return
	 */
	@Override
	protected PageUnhandleWorkOutputBean doAction(
			PageUnhandleWorkInputBean input) {
		PageUnhandleWorkOutputBean output = new PageUnhandleWorkOutputBean();
		logger.info("*******PageUnhandleWorkAction Begin*********");
		String user_id = input.getOrg_user_id();
		List<WkDealStateInfo> infos = daosrv.pageUnhandleWork(user_id, input
				.getStart_recd(), input.getLimit_recd());
		output.setAll_recd(daosrv.countInfoNumByUser(user_id));
		output.setUnhandle_work_list(infos);
		return output;
	}

	/**
	 * Description: ��ѯ������˻��ߴ���Ȩ������
	 * 
	 * @param input
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(PageUnhandleWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("��ѯ���д���������", lst_val);
	}

}
