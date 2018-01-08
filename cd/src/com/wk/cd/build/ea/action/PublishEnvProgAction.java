/**
 * Title: PublishEnvProgAction.java
 * File Description: ����������������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.PublishEnvProgInputBean;
import com.wk.cd.build.ea.bean.PublishEnvProgOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.exc.EnvProgConnotPublishException;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ����������������
 * @author chiss
 */
public class PublishEnvProgAction extends ActionBasic<PublishEnvProgInputBean, PublishEnvProgOutputBean>{
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject 
	private EnvProgPublicService envProgPublicService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: ����������������
	 * @param input
	 * @return 
	 */
	@Override
	protected PublishEnvProgOutputBean doAction(PublishEnvProgInputBean input) {
		logger.info("-----------PublishEnvProgAction end----------");
		PublishEnvProgOutputBean output = new PublishEnvProgOutputBean();
		String prog_id = input.getProg_id();
		//�ǿ�У��
		Assert.assertNotEmpty(prog_id, PublishEnvProgInputBean.PROG_IDCN);
		//�Ϸ���У��
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		PgProgramInfo programInfo = new PgProgramInfo();
		programInfo.setProg_id(prog_id);
		PgProgramInfo updateInfo = pgProgramDaoService.getInfoByKeyForUpdate(programInfo);
		//У�鷽���Ƿ��в��裬û�����׳��쳣
		if(PROG_TYPE.INTEGRATION.equals(updateInfo.getProg_type()) && pgInteStepDaoService.countPgStepByKey(prog_id) <= 0){
			throw new EnvProgConnotPublishException().addScene("PROGID", updateInfo.getProg_name()).addScene("REASON", "�������޲���");
//		}else if(PROG_TYPE.PUBLISH.equals(updateInfo.getProg_type()) && pgReleStepDaoService.countGenById(prog_id, YN_FLAG.YES)<=0){
		}else if(PROG_TYPE.PUBLISH.equals(updateInfo.getProg_type())){
			Program program = new Program(prog_id);
			program = XmlReader.read(program);
			if(Assert.isEmpty(program.getPhase_list())) {
				throw new EnvProgConnotPublishException().addScene("PROGID", updateInfo.getProg_name()).addScene("REASON", "�������޿�����ʵ���Ľ׶�");
			}
		}
		updateInfo.setIs_publish(IS_PUBLISH.YES);
		pgProgramDaoService.updatePgPublishState(updateInfo);
		logger.info("-----------PublishEnvProgAction end----------");
		return output;
	}

	/** 
	 * Description: ����������������
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PublishEnvProgInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("�������: "+input.getProg_id());
		return lgsvc.getLogTxt("����������������", log_lst);
	}
	
}
