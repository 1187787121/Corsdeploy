/**
 * Title: UpdateEnvProgBasicAction.java
 * File Description: �޸Ļ�������������Ϣ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.AddEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.UpdateEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.UpdateEnvProgBasicOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸Ļ�������������Ϣ
 * @author yangl
 */
public class UpdateEnvProgBasicAction extends ActionBasic<UpdateEnvProgBasicInputBean, UpdateEnvProgBasicOutputBean> {

	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private TargetPackPublicService targetPackService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �޸Ļ�������������Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateEnvProgBasicOutputBean doAction(UpdateEnvProgBasicInputBean input) {
		logger.info("-----------UpdateEnvProgBasicAction begin----------");
		UpdateEnvProgBasicOutputBean output = new UpdateEnvProgBasicOutputBean();
		String prog_id = input.getProg_id();
		PROG_TYPE prog_type = input.getProg_type();
		String prog_name = input.getProg_name();
		// �ǿ�У��
		Assert.assertNotEmpty(prog_id, UpdateEnvProgBasicInputBean.PROG_IDCN);
		Assert.assertNotEmpty(prog_type, UpdateEnvProgBasicInputBean.PROG_TYPECN);
		Assert.assertNotEmpty(prog_name, UpdateEnvProgBasicInputBean.PROG_NAMECN);
		// ����У��
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		// ����������
		PgProgramInfo programInfo = pgProgramDaoService.getInfoByKey(prog_id);
		// �޸Ļ���������Ϣ
		pgProgramDaoService.updatePgProgramByKey(prog_name, prog_id);
		// ����������չ��
		if (prog_type == PROG_TYPE.PUBLISH) {
			// ��ȡ����������չ����Ϣ
			String pub_template_name = input.getPub_template_name();
			String rol_template_name = input.getRol_template_name();
			Assert.assertNotEmpty(prog_type, AddEnvProgBasicInputBean.PROG_TYPECN);
			Assert.assertNotEmpty(pub_template_name, AddEnvProgBasicInputBean.PUB_TEMPLATE_NAMECN);
			// �޸ķ����������ű���Ϣ
			pgReleDaoService.UpdatePgReleByKey(pub_template_name, rol_template_name, prog_id);
		}
		output.setProg_id(programInfo.getProg_id());
		if (prog_type == PROG_TYPE.INTEGRATION) {
			output.setNext_step_id(pgInteStepDaoService.countPgStepByKey(prog_id) + 1);
		}
		logger.info("-----------UpdateEnvProgBasicAction end----------");
		return output;
	}

	/**
	 * Description: ��־д��
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateEnvProgBasicInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("��������: " + input.getProg_name());
		return lgsvc.getLogTxt("�޸Ļ�������", log_lst);
	}

}
