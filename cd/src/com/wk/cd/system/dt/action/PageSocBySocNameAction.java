/**
 * Title: PageSocBySocNameAction.java
 * File Description: �����û���ģ����ѯ����Դ��Ϣ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.bean.PageSocBySocNameInputBean;
import com.wk.cd.system.dt.bean.PageSocBySocNameOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;

/**
 * Class Description:�����û���ģ����ѯ����Դ��Ϣ
 * @author link
 */
public class PageSocBySocNameAction
		extends
		ActionBasic<PageSocBySocNameInputBean, PageSocBySocNameOutputBean> {
	@Inject
	private DtSourceDaoService daoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:�����û���ģ����ѯ����Դ��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected PageSocBySocNameOutputBean doAction(
			PageSocBySocNameInputBean input) {
		PageSocBySocNameOutputBean output = new PageSocBySocNameOutputBean();
		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();
		Assert.assertNotEmpty(input.getSoc_name(),
				PageSocBySocNameInputBean.SOC_NAME);
		output.setAll_recd(daoService.countByLikeSocName(input.getSoc_name()));
		soc_list = daoService.pageBySocName(input.getSoc_name(),
				input.getStart_recd(), input.getLimit_recd());
		output.setDt_source_list(soc_list);
		return output;
	}

	/**
	 * Description:�����û���ģ����ѯ����Դ��Ϣ��־��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageSocBySocNameInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("��������Դ����ģ����ѯ����Դ��Ϣ", lst_val);
	}

}
