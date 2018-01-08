/**
 * Title: UpdatePublishProgDetailAction.java
 * File Description: �޸ķ�����������չ��Ϣ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��12��14��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.AddPublishProgDetailInputBean;
import com.wk.cd.build.ea.bean.PhasePublishBean;
import com.wk.cd.build.ea.bean.UpdatePublishProgDetailInputBean;
import com.wk.cd.build.ea.bean.UpdatePublishProgDetailOutputBean;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:�޸ķ�����������չ��Ϣ
 * @author Zhangj
 */
public class UpdatePublishProgDetailAction
		extends ActionBasic<UpdatePublishProgDetailInputBean, UpdatePublishProgDetailOutputBean> {
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	private static final String SUFFIX_ROLL = "rollback";

	/**
	 * Description: �޸ķ�����������չ��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected UpdatePublishProgDetailOutputBean doAction(UpdatePublishProgDetailInputBean input) {
		logger.info("-----------UpdatePublishProgDetailAction begin----------");
		// ��ȡ�������ݲ��ǿ�У��---------------------------
		checkNull(input);
		UpdatePublishProgDetailOutputBean output = new UpdatePublishProgDetailOutputBean();
//		PhasePublishBean[] rol_phase_list = input.getRol_phase_list();
//		String rol_template_name = input.getRol_template_name();
//		String pub_template_name = input.getPub_template_name();
		
		Program pub_program = input.getPub_program();
		Program rol_program = input.getRol_program();
		
		XmlWriter.write(pub_program);
		
		String prog_id = pub_program.getProg_id();
		if(!Assert.isEmpty(pub_program.getRol_template_name())) {
			rol_program.setProg_id(prog_id+SUFFIX_ROLL);
			XmlWriter.write(rol_program);
		}
		
		logger.debug("���淢��������PROG_ID[{}]", prog_id);
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		pgProgramDaoService.updatePgProgramByKey(pub_program.getProg_name(), prog_id);
		// �޸ķ���״̬Ϊδ����
		pgProgramDaoService.updatePgPublishStateByKey(IS_PUBLISH.NO,prog_id);
		
		PgReleInfo info = pgReleDaoService.getInfoByKey(prog_id);
		if(!Assert.isEmpty(info)) {
			String ver_soc_uuid = info.getVer_soc_uuid();
			UuSocInfo uu_soc = uuSocDaoService.getInfoByKeyForUpdate(ver_soc_uuid,1);
			uu_soc.setVer_server_name(pub_program.getVer_server_name());
			uu_soc.setVer_soc_name(pub_program.getVer_soc_name());
			uu_soc.setExe_server_name(pub_program.getVer_server_name());
			uu_soc.setExe_soc_name(pub_program.getVer_soc_name());
			uu_soc.setCode_bk_dir(pub_program.getCode_bk_dir());
			//����uu_soc
			uuSocDaoService.updateSoctInfo(uu_soc);
		}
		
		
//		// ɾ�������׶α��� �׶�����Դ������
//		envProgPublicService.deletePubByProgId(prog_id);
//		// ɾ������������
//		envProgPublicService.deleteUuParamById(prog_id);
//		// ɾ���汾������Դ
//		envProgPublicService.deletePubVerSocByProgId(prog_id);
//		// ���뷢���Ĳ����Ͳ����
//		String pub_param_id = UUID.randomUUID().toString().replace("-", "");
//		envProgPublicService.insertUuParam(input.getPub_param_list(), pub_param_id);
//		pgReleDaoService.updatePubUuParam(pub_param_id, prog_id);
//		envProgPublicService.insertReleStep(input.getPub_phase_list(), input.getPub_template_name(), prog_id);
//		// �޸ķ���������չ����ģ�����ֶ�
//		pgReleDaoService.updatePubTemplateName(pub_template_name, prog_id);
//		if (!Assert.isEmpty(rol_phase_list) && !Assert.isEmpty(rol_template_name)) {
//			String rol_param_id = UUID.randomUUID().toString().replace("-", "");
//			envProgPublicService.insertUuParam(input.getRol_param_list(), rol_param_id);
//			pgReleDaoService.updateRolUuParam(rol_param_id, prog_id);
//			envProgPublicService.insertReleStep(rol_phase_list, input.getRol_template_name(), prog_id);
//			pgReleDaoService.updateRolTemplateName(rol_template_name, prog_id);
//		}
//		String ver_soc_uuid = UUID.randomUUID().toString().replace("-", "");
//		UuSocInfo usi = new UuSocInfo();
//		usi.setSoc_bk_seq(1);
//		usi.setVer_server_name(input.getVer_server_name());
//		usi.setVer_soc_name(input.getVer_soc_name());
//		usi.setExe_server_name(input.getVer_server_name());
//		usi.setExe_soc_name(input.getVer_soc_name());
//		usi.setCode_bk_dir(input.getCode_bk_dir());
//		usi.setSoc_uuid(ver_soc_uuid);
//		uuSocDaoService.insertInfo(usi);
//		pgReleDaoService.updateVerSoc(ver_soc_uuid, prog_id);
		logger.info("-----------UpdatePublishProgDetailAction end----------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdatePublishProgDetailInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("����ģ��: " + input.getPub_template_name());
		log_lst.add("����ģ��: " + input.getRol_template_name());
		return lgsvc.getLogTxt("�޸Ļ�����������", log_lst);
	}

	/**
	 * Description: �ǿ�У��
	 * @param input
	 */
	private void checkNull(UpdatePublishProgDetailInputBean input) {
		Program program = input.getPub_program();
		Assert.assertNotEmpty(program, AddPublishProgDetailInputBean.PUB_PROGRAMCN);
		Assert.assertNotEmpty(program.getProg_name(), Program.PROG_NAMECN);
		Assert.assertNotEmpty(program.getPub_template_name(), Program.PUB_TEMPLATE_NAMECN);
		Assert.assertNotEmpty(program.getVer_server_name(), Program.VER_SERVER_NAMECN);
		Assert.assertNotEmpty(program.getVer_soc_name(), Program.VER_SOC_NAMECN);
		Assert.assertNotEmpty(program.getCode_bk_dir(), Program.CODE_BK_DIRCN);
		Assert.assertNotEmpty(program.getProg_type(), Program.PROG_TYPECN);
		Assert.assertNotEmpty(program.getPhase_list(), Program.PHASE_LISTCN);
		
//		Assert.assertNotEmpty(input.getPub_phase_list(), UpdatePublishProgDetailInputBean.PUB_PHASE_LISTCN);
//		Assert.assertNotEmpty(input.getPub_param_list(), UpdatePublishProgDetailInputBean.PUB_PARAM_LISTCN);
//		Assert.assertNotEmpty(input.getPub_template_name(), UpdatePublishProgDetailInputBean.PUB_TEMPLATE_NAMECN);
//		Assert.assertNotEmpty(input.getEnv_name(), UpdatePublishProgDetailInputBean.ENV_NAMECN);
//		Assert.assertNotEmpty(input.getProg_name(), UpdatePublishProgDetailInputBean.PROG_NAMECN);
//		Assert.assertNotEmpty(input.getProg_id(), UpdatePublishProgDetailInputBean.PROG_IDCN);
//		Assert.assertNotEmpty(input.getVer_server_name(), UpdatePublishProgDetailInputBean.VER_SERVER_NAMECN);
//		Assert.assertNotEmpty(input.getVer_soc_name(), UpdatePublishProgDetailInputBean.VER_SOC_NAMECN);
//		Assert.assertNotEmpty(input.getCode_bk_dir(), UpdatePublishProgDetailInputBean.CODE_BK_DIRCN);
	}

}