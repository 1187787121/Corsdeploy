/**
 * Title: PageQueryWorkAction.java
 * File Description:��ҳ��ѯ���� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.wk.bean.PageQueryWorkInputBean;
import com.wk.cd.work.wk.bean.PageQueryWorkOutputBean;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:��ҳ��ѯ����
 * @author tlw
 */
public class PageQueryWorkAction
		extends ActionBasic<PageQueryWorkInputBean, PageQueryWorkOutputBean> {
	@Inject
	private WorkConfigPublicService wcPubSrv;
	@Inject
	private UsGetUserInfoService usSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ��ҳ��ѯ�����������ӿ�Ϊ�գ�Ĭ�ϲ�ѯ���е����񣬲�Ϊ�����ѯ��Ӧ���б���Ϣ
	 * @param input ������Ϣ
	 * @return ��������Ϣ�б�
	 */
	@Override
	protected PageQueryWorkOutputBean doAction(PageQueryWorkInputBean input) {
		PageQueryWorkOutputBean output = new PageQueryWorkOutputBean();
		logger.info("*********PageQueryWorkAction Begin**********");
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		// �ǿռ��
		Assert.assertNotEmpty(start_recd, PageQueryWorkInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageQueryWorkInputBean.LIMIT_RECDCN);
		// ��ȡ��ɫ����
		List<Integer> role_type_list = usSrv.queryRoleTypeByUserId(input
				.getOrg_user_id());
		int[] arr = input.getWork_type_list();
		List<FUN_TYPE> work_type_list = new ArrayList<FUN_TYPE>();
		if (!Assert.isEmpty(arr)) {
			for (int i : arr) {
				work_type_list.add(FUN_TYPE.valueOf(FUN_TYPE.class, i));
			}
		}
		// ��ɫ����Ϊ��������Ա��ϵͳ����Ա�Ŀ��Բ�ѯδ�������������û����ܲ�ѯδ��������
		List<WkWorkInfo> work_list = wcPubSrv.pageAllWorkByWorkType(
				work_type_list, role_type_list, start_recd, limit_recd);
		output.setWork_list(work_list);
		output.setAll_recd(wcPubSrv.countAllWorkByWorkType(work_type_list,
				role_type_list));
		return output;
	}

	/**
	 * ������־
	 * @param input ��ҳ��ѯ������Ϣ
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(PageQueryWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("��ʼ����" + input.getStart_recd());
		lst_val.add("��ѯ����" + input.getLimit_recd());
		return lgsrv.getLogTxt("��ҳ��ѯ������", lst_val);
	}

}
