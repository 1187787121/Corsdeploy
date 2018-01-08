/**
 * Title: AddInteProgStepAction.java
 * File Description: �����������ɷ�������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.AddInteProgStepInputBean;
import com.wk.cd.build.ea.bean.AddInteProgStepOutputBean;
import com.wk.cd.build.ea.bean.EnvProgStepBean;
import com.wk.cd.build.ea.bean.InteStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.exc.PackNameRepeatException;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �����������ɷ�������
 * 
 * @author chiss
 */
public class AddInteProgStepAction extends ActionBasic<AddInteProgStepInputBean, AddInteProgStepOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
    @Inject 
    private UuFilelistDaoService uuFileListDaoService;
    @Inject 
    private PgProgramDaoService pgPmDaoSrv;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �����������ɷ�������
	 * @param input
	 * @return
	 */
	@Override
	protected AddInteProgStepOutputBean doAction(AddInteProgStepInputBean input) {
		logger.info("-----------AddInteProgStepAction begin----------");
		AddInteProgStepOutputBean output = new AddInteProgStepOutputBean();
		String prog_id = input.getProg_id();
		// �ǿ�У��
		Assert.assertNotEmpty(prog_id, AddInteProgStepInputBean.PROG_IDCN);
		
		//����ҳ���ϵĵ���������ɾ�������������Ȱ����е�ɾ��
		List<PgInteStepInfo> inte_List = pgInteStepDaoService.getInteListByProgId(prog_id);
		if(!Assert.isEmpty(inte_List)) {
			for (PgInteStepInfo inte_step : inte_List) {
				//��ֹ�û��ظ�������ɾ�������
				if(!Assert.isEmpty(inte_step)){
					//ɾ������Դ������
					String uu_id = inte_step.getSoc_uuid();
				    if(!Assert.isEmpty(uu_id)){
				    	uuSocDaoService.deleteListByUU(uu_id);
				    }
					//ɾ���ļ��嵥��
					String list_uuid = inte_step.getStorage_list_uuid();
					if(!Assert.isEmpty(list_uuid)){
						uuFileListDaoService.deleteUuFileByListId(list_uuid);
					}
					//ɾ�����ɷ��������
					pgInteStepDaoService.deleteInfo(inte_step);
				}
			}
		}
		
		List<InteStepBean> step_list = input.getStep_list();
		if(!Assert.isEmpty(step_list)) {
			for (InteStepBean inteStepBean : step_list) {
				int step_id = inteStepBean.getStep_id();				
				EnvProgStepBean prog_step_bean = inteStepBean.getProg_step_bean();
				PgInteStepInfo stepInfo = new PgInteStepInfo();
				stepInfo.setProg_id(prog_id);
				stepInfo.setStep_id(step_id);
				stepInfo.setStep_expl(prog_step_bean.getStep_expl());
				STEP_TYPE stepType = prog_step_bean.getStep_type();
				stepInfo.setStep_type(stepType);
				String soc_id = UUID.randomUUID().toString().replace("-", "");
				stepInfo.setSoc_uuid(soc_id);
				// ����Դ��Ϣ
				UuSocInfo uuSocInfo = new UuSocInfo();
				uuSocInfo.setSoc_uuid(soc_id);
				uuSocInfo.setSoc_bk_seq(1);
				uuSocInfo.setExe_server_name(prog_step_bean.getServer_name());
				uuSocInfo.setExe_soc_name(prog_step_bean.getSoc_name());
				// ����Ϊ�汾ʱ��
				if (stepType == STEP_TYPE.VERSION) {
					uuSocInfo.setVer_server_name(prog_step_bean.getCode_server_name());
					uuSocInfo.setVer_soc_name(prog_step_bean.getCode_soc_name());
					uuSocInfo.setCode_bk_dir(prog_step_bean.getCode_bk_dir());
				}
				// ����Ϊ�ű�ʱ��
				if (stepType == STEP_TYPE.SCRIPT) {
					stepInfo.setStep_bk_script(prog_step_bean.getStep_bk_script());
				}
				// ����Ϊ����ʱ��
				if (stepType == STEP_TYPE.COMPILE) {
					stepInfo.setCompile_type(prog_step_bean.getCompile_type());
					stepInfo.setEnv_variable(prog_step_bean.getEnv_variable());
					stepInfo.setCompile_param(prog_step_bean.getCompile_param());
					stepInfo.setComplie_bk_path(prog_step_bean.getComplie_bk_path());
				}
				// ����Ϊ���ʱ��
				if (stepType == STEP_TYPE.STORAGE) {
					// Ŀ��汾����ԴUUID
					uuSocInfo.setVer_server_name(prog_step_bean.getTar_server_name());
					uuSocInfo.setVer_soc_name(prog_step_bean.getTar_soc_name());
					uuSocInfo.setCode_bk_dir(prog_step_bean.getTar_bk_dir());
					List<TargetPackageBean> tar_package_list = prog_step_bean.getTar_package_list();
					List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
					String fil_id = UUID.randomUUID().toString().replace("-", "");
					// �嵥UUID
					stepInfo.setStorage_list_uuid(fil_id);
					stepInfo.setStorage_bk_path(prog_step_bean.getTarget_root_path());
					if (!Assert.isEmpty(tar_package_list)) {
						List<String> pk_name_list = new ArrayList<String>();
						for (TargetPackageBean tarpack : tar_package_list) {
							String packageName = tarpack.getPackage_name();
							// �����ظ��׳��쳣
							if (pk_name_list.contains(packageName)) {
								throw new PackNameRepeatException().addScene("PACKNAME", packageName);
							}
							List<UuFilelistInfo> file_lis = new ArrayList<UuFilelistInfo>();
							file_lis = tarpack.getFile_list();
							if (!Assert.isEmpty(file_lis)) {
								for (UuFilelistInfo filelistInfo : file_lis) {
									UuFilelistInfo fileInfo = new UuFilelistInfo();
									fileInfo.setList_uuid(fil_id);
									fileInfo.setFile_work_seq(genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getRemote_ip(), input.getServer_port()));
									fileInfo.setFile_name(FileTool.getFileName(filelistInfo.getFile_name()));
									String file_path = FileTool.getFilePath(filelistInfo.getFile_name());
									file_path = file_path.startsWith("/") ? file_path.substring(1) : file_path;
									fileInfo.setFile_path(".".equals(file_path) ? "" : file_path);
									fileInfo.setFile_type(filelistInfo.getFile_type());
									fileInfo.setPackage_name(packageName);
									fileInfo.setTarget_relative_path(tarpack.getTarget_relative_path());
									file_list.add(fileInfo);
								}
							}
							pk_name_list.add(packageName);
						}
						// �����ļ��嵥�б�
						uuFilelistDaoService.insertListInfo(file_list);
					}
				}
				uuSocDaoService.insertInfo(uuSocInfo);
				pgInteStepDaoService.insertInfo(stepInfo);
			}
			//�޸ķ���״̬δ����
			PgProgramInfo programInfo = new PgProgramInfo();
			programInfo.setProg_id(prog_id);
			PgProgramInfo updateInfo = pgPmDaoSrv.getInfoByKeyForUpdate(programInfo);
			updateInfo.setIs_publish(IS_PUBLISH.NO);
			pgPmDaoSrv.updatePgPublishState(updateInfo);
		}
		logger.info("-----------AddInteProgStepAction begin----------");
		return output;
	}

	/**
	 * Description: �����������ɷ�������
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddInteProgStepInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("�������: " + input.getProg_id());
		return lgsvc.getLogTxt("�����������ɷ�������", log_lst);
	}

}