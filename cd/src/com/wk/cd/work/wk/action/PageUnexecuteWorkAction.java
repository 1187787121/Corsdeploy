/**
 * Title: PageUnexecuteWorkAction.java
 * File Description: ��ѯ��ִ������
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-6
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.PageUnhandleWorkInputBean;
import com.wk.cd.work.wk.bean.PageUnhandleWorkOutputBean;
import com.wk.cd.work.wk.dao.WkDealStateDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯ��ִ������
 * @author tlw
 */
public class PageUnexecuteWorkAction
		extends
		ActionBasic<PageUnhandleWorkInputBean, PageUnhandleWorkOutputBean> {
	@Inject
	private WkDealStateDaoService daosrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ��ѯ��ִ������
	 * @param input
	 * @return
	 */
	@Override
	protected PageUnhandleWorkOutputBean doAction(
			PageUnhandleWorkInputBean input) {
		PageUnhandleWorkOutputBean output = new PageUnhandleWorkOutputBean();
		logger.info("********PageUnhandleWorkOutputBean begin*********");
		String user_id = input.getOrg_user_id();
		output.setAll_recd(daosrv.countUnexecuteWork(user_id));
		output.setUnhandle_work_list(daosrv.pageUnexecuteWork(user_id, input
				.getStart_recd(), input.getLimit_recd()));
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageUnhandleWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("�����û�" + input.getOrg_user_id());
		return lgsrv.getLogTxt("��ѯ���ύ����", lst_val);
	}

}
