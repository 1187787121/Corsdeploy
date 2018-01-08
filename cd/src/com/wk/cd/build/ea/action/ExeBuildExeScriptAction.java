/**
 * Title: ExeBuildExeScriptAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeBuildExeScriptInputBean;
import com.wk.cd.build.ea.bean.ExeBuildExeScriptOutputBean;
import com.wk.cd.build.ea.dao.BuildScriptDaoService;
import com.wk.cd.build.ea.service.ExeScriptPublicService;
import com.wk.cd.build.ea.service.ExeScriptService;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ִ�й����ű�����
 * @author xuph
 */
public class ExeBuildExeScriptAction extends ActionBasic<ExeBuildExeScriptInputBean, ExeBuildExeScriptOutputBean>{
	@Inject private ExeScriptPublicService exeSrptSrv;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private BuildScriptDaoService buildScriptDaoSrv;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: ִ�й����ű��������
	 * @param input
	 * @return 
	 */
	@Override
	protected ExeBuildExeScriptOutputBean doAction(ExeBuildExeScriptInputBean input) {
		logger.info("-----------------ExeBuildExeScriptAction Begin-----------------");
		ExeBuildExeScriptOutputBean output = new ExeBuildExeScriptOutputBean();
		String work_id = input.getWork_id();
		long script_bk_seq = input.getScript_bk_seq();
		SCRIPT_TYPE script_type = input.getScript_type();
		Assert.assertNotEmpty(work_id, ExeBuildExeScriptInputBean.WORK_IDCN);
		Assert.assertNotEmpty(script_bk_seq, ExeBuildExeScriptInputBean.SCRIPT_BK_SEQCN);
		Assert.assertNotEmpty(script_type.getValue()==0?null:script_type, ExeBuildExeScriptInputBean.SCRIPT_TYPECN);
		//�Ϸ���У��
		exeSrptSrv.checkExeScriptIsExist(work_id,script_bk_seq,script_type);
		// ִ��
		exeSrptSrv.exeScript(work_id,script_bk_seq,script_type);
		logger.info("-----------------ExeBuildExeScriptAction End-----------------");
		return output;
	}

	/** 
	 * Description: ��־д��
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExeBuildExeScriptInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("�����ţ�" + input.getWork_id());
		lst_val.add("�ű���ţ�"+input.getScript_bk_seq());
		lst_val.add("�ű����ͣ�"+ input.getScript_type().getCname());
		return lgsvc.getLogTxt("һ��ִ�м�������", lst_val);
	}

}