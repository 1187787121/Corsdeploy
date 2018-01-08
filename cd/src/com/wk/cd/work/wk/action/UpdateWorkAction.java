/**
 * Title: UpdateWorkAction.java
 * File Description:������������Ϣ 
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
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.UpdateWkWorkBean;
import com.wk.cd.work.wk.bean.UpdateWorkInputBean;
import com.wk.cd.work.wk.bean.UpdateWorkOutputBean;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:������������Ϣ
 * @author tlw
 */
public class UpdateWorkAction
		extends ActionBasic<UpdateWorkInputBean, UpdateWorkOutputBean> {
	@Inject
	private WorkConfigPublicService wcPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ��������������
	 * @param input ������������Ϣ����ӿ�
	 * @return
	 */
	@Override
	protected UpdateWorkOutputBean doAction(UpdateWorkInputBean input) {
		UpdateWorkOutputBean output = new UpdateWorkOutputBean();
		String work_code = input.getWork_code();
		String work_cn_name = input.getWork_cn_name();
		FUN_TYPE work_fun_type = input.getWork_fun_type();
		IS_PUBLISH is_publish = input.getIs_publish();
		logger.info("�������� work_code = [{}]", work_code);
		//�ǿռ��
		Assert.assertNotEmpty(work_code, UpdateWorkInputBean.WORK_CODECN);
		Assert.assertNotEmpty(work_cn_name, UpdateWorkInputBean.WORK_CN_NAMECN);
		Assert.assertNotEmpty(work_fun_type, UpdateWorkInputBean.WORK_FUN_TYPECN);
		Assert.assertNotEmpty(is_publish, UpdateWorkInputBean.IS_PUBLISHCN);
		//�����������
		logger.info("****************UPDATE WK_WORK***************");
		UpdateWkWorkBean update_work_bean = new UpdateWkWorkBean();
		update_work_bean.setWork_cn_name(work_cn_name);
		update_work_bean.setWork_bk_desc(input.getWork_bk_desc());
		update_work_bean.setWork_fun_type(work_fun_type);
		update_work_bean.setIs_publish(is_publish);
		update_work_bean.setModify_bk_date(input.getDtbs_bk_date());
		update_work_bean.setModify_bk_time(input.getDtbs_bk_time());
		update_work_bean.setModify_user_id(input.getOrg_user_id());
		wcPubSrv.updateWorkByWrokCode(work_code, update_work_bean);
		//����������Դ���ñ�
		logger.info("****************UPDATE WK_WORK_RS***************");
		wcPubSrv.updateWorkRsByWrokCode(work_code, input.getRs_code_list());
		//������������Դ���ñ�
		logger.info("****************UPDATE WK_WORK_SOC***************");
		wcPubSrv.updateWorkSocByWrokCode(work_code, input.getSoc_list());
		//��������������ñ�
		logger.info("****************UPDATE WK_WORK_SRV***************");
		wcPubSrv.updateWorkSrvByWrokCode(work_code, input.getSrv_list());
		return output;
	}

	/**
	 * ��־��Ϣ
	 * @param input ������������Ϣ����ӿ�
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(UpdateWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWork_code());
		lst_val.add(input.getWork_cn_name());
		lst_val.add(input.getWork_fun_type().getCname());
		lst_val.add("�Ƿ񷢲���" + input.getIs_publish().getCname());
		return lgsrv.getLogTxt("��������", lst_val);
	}

}
