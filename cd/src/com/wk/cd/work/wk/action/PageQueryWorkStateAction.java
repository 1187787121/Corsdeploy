/**
 * Title: PageWorkStateByUserAction.java
 * File Description:��ѯ�Լ����������˺���Ȩ������״̬
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lijie
 * @version: 1.0
 * @date: 2015-03-30
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.QUERY_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.PageQueryWorkStateInputBean;
import com.wk.cd.work.wk.bean.PageQueryWorkStateOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:��ѯ�Լ����������˺���Ȩ������״̬
 * @author lijie
 */
public class PageQueryWorkStateAction
		extends
		ActionBasic<PageQueryWorkStateInputBean, PageQueryWorkStateOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ��ҳ��ѯ�û�����״̬
	 * @param input ������Ϣ
	 * @return
	 */
	@Override
	protected PageQueryWorkStateOutputBean doAction(
			PageQueryWorkStateInputBean input) {
		PageQueryWorkStateOutputBean output = new PageQueryWorkStateOutputBean();
		String crt_user_id = input.getOrg_user_id();
		QUERY_TYPE query_type = input.getQuery_type();
		logger.info("�û���[{}]", crt_user_id);
		logger.info("�û���ѯ�������ͣ�[{}]", query_type);
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		// �ǿռ��
		Assert.assertNotEmpty(crt_user_id,
				PageQueryWorkStateInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(query_type,
				PageQueryWorkStateInputBean.QUERY_TYPECN);
		Assert.assertNotEmpty(start_recd,
				PageQueryWorkStateInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,
				PageQueryWorkStateInputBean.LIMIT_RECDCN);
		List<WkDealStateInfo> list = wkPubSrv.pageInfoListByWorkStateAndUser(crt_user_id,query_type,start_recd, limit_recd);
		output.setWork_state_list(list);
		output.setAll_recd(wkPubSrv.countInfoListByWorkStateAndUser(crt_user_id,query_type));
		return output;
	}

	/**
	 * ��־�Ǽ�
	 * @param input ������Ϣ
	 * @return
	 */
	@Override
	protected String getLogTxt(PageQueryWorkStateInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		lst_val.add(input.getQuery_type().getCname());
		return lgsrv.getLogTxt("�û����˲�ѯ����״̬", lst_val);
	}
}
