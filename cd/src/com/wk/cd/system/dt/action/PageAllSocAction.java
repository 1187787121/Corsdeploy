/**
 * Title: PageAllSocAction.java
 * File Description: ʵ�ַ�ҳ��ѯ����Դ��Ϣ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.bean.PageAllSocInputBean;
import com.wk.cd.system.dt.bean.PageAllSocOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;

/**
 * Class Description:ʵ�ַ�ҳ��ѯ����Դ��Ϣ
 * @author link
 */
public class PageAllSocAction
		extends ActionBasic<PageAllSocInputBean, PageAllSocOutputBean> {

	@Inject
	private DtSourceDaoService daoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: ʵ�ַ�ҳ��ѯ����Դ��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected PageAllSocOutputBean doAction(PageAllSocInputBean input) {
		PageAllSocOutputBean output = new PageAllSocOutputBean();
		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();
		output.setAll_recd(daoService.countAllSocName(input.getSoc_name()));
		soc_list = daoService.pageAllSocName(input.getSoc_name(),
				input.getStart_recd(), input.getLimit_recd());
		output.setDt_source_list(soc_list);
		return output;
	}

	/**
	 * Description: ʵ�ַ�ҳ��ѯ����Դ��Ϣ��־��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageAllSocInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("��ҳ��ѯ����Դ��Ϣ", lst_val);
	}

}
