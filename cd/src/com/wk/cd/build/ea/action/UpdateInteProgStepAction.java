/**
 * Title: UpdateInteProgStepAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EnvProgStepBean;
import com.wk.cd.build.ea.bean.InteStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.UpdateInteProgStepInputBean;
import com.wk.cd.build.ea.bean.UpdateInteProgStepOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸ĵ������ɷ�������
 * @author xuph
 */
public class UpdateInteProgStepAction extends ActionBasic<UpdateInteProgStepInputBean, UpdateInteProgStepOutputBean>{
    @Inject EnvProgPublicService envProgPublicService;
    @Inject PgInteStepDaoService pgInteStepDaoService;
    @Inject PgProgramDaoService pgPmDaoSrv;
    @Inject UuFilelistDaoService uuFileListDaoService;
    @Inject GenNoService genNoService;
    @Inject UuSocDaoService uuSocDaoService;
	@Inject ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateInteProgStepOutputBean doAction(UpdateInteProgStepInputBean input) {
		logger.info("--------------------UpdateInteProgStepAction Begin---------------");
		UpdateInteProgStepOutputBean output = new UpdateInteProgStepOutputBean();
		String prog_id = input.getProg_id();
		List<InteStepBean> step_list = input.getStep_list();
		//�ǿ�У��
		Assert.assertNotEmpty(prog_id, UpdateInteProgStepInputBean.PROG_IDCN);
		//�Ϸ���У��
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		if(!Assert.isEmpty(step_list)) {
			for (InteStepBean inteStepBean : step_list) {
				int step_id = inteStepBean.getStep_id();
				EnvProgStepBean prog_step_bean = inteStepBean.getProg_step_bean();
				PgInteStepInfo upinfo = new PgInteStepInfo();
				upinfo.setStep_id(step_id);
				upinfo.setProg_id(prog_id);
				PgInteStepInfo inte_info = pgInteStepDaoService.getInfoByKey(upinfo);
				PgInteStepInfo inte_for_update = pgInteStepDaoService.getInfoByKeyForUpdate(upinfo);
				if(!Assert.isEmpty(inte_info)&&!Assert.isEmpty(inte_for_update)){
					STEP_TYPE step_type = prog_step_bean.getStep_type();
					String soc_id = inte_info.getSoc_uuid();
					//ɾ�����ɷ��������
					uuSocDaoService.deleteListByUU(soc_id);
					UuSocInfo uuSocInfo = new UuSocInfo();
					uuSocInfo.setSoc_bk_seq(1);
					uuSocInfo.setSoc_uuid(soc_id);
					uuSocInfo.setExe_server_name(prog_step_bean.getServer_name());
					uuSocInfo.setExe_soc_name(prog_step_bean.getSoc_name());
					if(step_type==STEP_TYPE.VERSION){
						
						uuSocInfo.setVer_server_name(prog_step_bean.getCode_server_name());
						uuSocInfo.setVer_soc_name(prog_step_bean.getCode_soc_name());
						uuSocInfo.setCode_bk_dir(prog_step_bean.getCode_bk_dir());
						inte_for_update.setStep_type(step_type);
					}
					if(step_type==STEP_TYPE.SCRIPT){
						 
						inte_for_update.setStep_type(step_type);
						inte_for_update.setStep_bk_script(prog_step_bean.getStep_bk_script());
					}
					if(step_type==STEP_TYPE.COMPILE){
						 
						inte_for_update.setStep_type(step_type);
						inte_for_update.setCompile_type(prog_step_bean.getCompile_type());
						inte_for_update.setCompile_param(prog_step_bean.getCompile_param());
						inte_for_update.setEnv_variable(prog_step_bean.getEnv_variable());
						inte_for_update.setComplie_bk_path(prog_step_bean.getComplie_bk_path());
					}
					if(step_type==STEP_TYPE.STORAGE){
						
						uuSocInfo.setVer_soc_name(prog_step_bean.getTar_soc_name());
						uuSocInfo.setCode_bk_dir(prog_step_bean.getTar_bk_dir());
						uuSocInfo.setVer_server_name(prog_step_bean.getTar_server_name());
						// ɾ���ļ��嵥
						String list_uuid = inte_info.getStorage_list_uuid();
						uuFileListDaoService.deleteUuFileByListId(list_uuid);
						List<TargetPackageBean> tar_package_list = prog_step_bean.getTar_package_list();
						if(!Assert.isEmpty(tar_package_list)){
							List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
							for (TargetPackageBean tarbean : tar_package_list) {
								String package_name =tarbean.getPackage_name();
								List<UuFilelistInfo> filelis = tarbean.getFile_list();
								if(!Assert.isEmpty(filelis)){
									for (UuFilelistInfo lis : filelis) {
										String work_seq = genNoService.getWorkSeq(input.getDtbs_bk_date(), input.getRemote_ip(), input.getServer_port());
										UuFilelistInfo filis = new UuFilelistInfo();
										filis.setList_uuid(list_uuid);
										filis.setFile_work_seq(work_seq);
										filis.setFile_name(FileTool.getFileName(lis.getFile_name()));
										filis.setFile_type(lis.getFile_type());
										String file_path = FileTool.getFilePath(lis.getFile_name());
										file_path = file_path.startsWith("/") ? file_path.substring(1) : file_path;
										filis.setFile_path(".".equals(file_path) ? "" : file_path);
										filis.setPackage_name(package_name);
										filis.setTarget_relative_path(tarbean.getTarget_relative_path());
										file_list.add(filis);
									}
								}
							}
							uuFileListDaoService.insertListInfo(file_list);
						}
					}
					//���뼯�ɷ�������
					uuSocDaoService.insertInfo(uuSocInfo);
					//���²���˵��
					inte_for_update.setStep_expl(prog_step_bean.getStep_expl());
					inte_for_update.setStorage_bk_path(prog_step_bean.getTarget_root_path());
					//���·���������Ϣ
					pgInteStepDaoService.updatePgInteStep(inte_for_update);
					//�޸ķ���״̬δ����
					PgProgramInfo programInfo = new PgProgramInfo();
					programInfo.setProg_id(prog_id);
					PgProgramInfo updateInfo = pgPmDaoSrv.getInfoByKeyForUpdate(programInfo);
					updateInfo.setIs_publish(IS_PUBLISH.NO);
					pgPmDaoSrv.updatePgPublishState(updateInfo);
				}
			}
		}
		logger.info("--------------------UpdateInteProgStepAction End------------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateInteProgStepInputBean input) {
		List<String>log_list = new ArrayList<String>();
		log_list.add("prog_id��"+input.getProg_id());
	    return lgsvc.getLogTxt("�޸ĵ������ɷ�������", log_list);
	}

}
