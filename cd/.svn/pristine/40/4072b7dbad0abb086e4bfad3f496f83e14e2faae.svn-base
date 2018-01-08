/**
 * Title: DeleteConfigFileAction.java
 * File Description: 删除配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DeleteEnvConfigFileInputBean;
import com.wk.cd.build.ea.bean.DeleteEnvConfigFileOutputBean;
import com.wk.cd.build.ea.dao.EnvConfigfileModDaoService;
import com.wk.cd.build.ea.info.EnvConfigfileModInfo;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.EnvConfigFileDelException;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.OPT_STATUS;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.RemoteFileList;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除配置文件服务
 * @author Xul
 */
public class DeleteEnvConfigFileAction extends ActionBasic<DeleteEnvConfigFileInputBean, DeleteEnvConfigFileOutputBean>{
	
	@Inject private EnvConfigfileModDaoService confModSrv;
	@Inject private EnvironmentPublicService envPubSrv;
	@Inject private ServerCommonService serverComSrv;
	@Inject private CeServerDaoService serverSrv;
	@Inject private GenNoService genNoSrv;
	@Inject private CommonService comsrv;
	@Inject private RemoteFileList remoteFileList;
	@Inject private FTPRCallService ftprcallSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 删除配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteEnvConfigFileOutputBean doAction(DeleteEnvConfigFileInputBean input) {
		logger.info("-----------------DeleteEnvConfigFileAction Begin------------------");
		DeleteEnvConfigFileOutputBean output = new DeleteEnvConfigFileOutputBean();
		
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		String batch_no = input.getBatch_no();
		String relative_path = input.getRelative_path();
		YN_FLAG dir_yn_flag = input.getDir_yn_flag();
		Assert.assertNotEmpty(env_name, DeleteEnvConfigFileInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(ce_server_name, DeleteEnvConfigFileInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(relative_path, DeleteEnvConfigFileInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(dir_yn_flag, DeleteEnvConfigFileInputBean.DIR_YN_FLAGCN);
		
		//合法性校验
		envPubSrv.checkEnvNameIsExist(env_name);
		serverComSrv.checkServerIsExist(ce_server_name);

		String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
		
		//生成新版本号
		if(Assert.isEmpty(batch_no)){
			batch_no = genNoSrv.getBatchNo(input.getDtbs_bk_date());
			logger.debug("版本号为空，生成新版本号[{}]", batch_no);
			output.setBatch_no(batch_no);
		}
		
		EnvConfigfileModInfo info = new EnvConfigfileModInfo();
		// 获取文件名及文件目录
		String file_name = FileTool.getFileName(relative_path);
		String file_path = FileTool.getFilePath(relative_path);
		// 获取文件流水号
		String file_work_seq = genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getServer_name(), input.getServer_port());
		// 根据数据源名获取ftp或sftp连接实例
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(soc_name,input.getWork_seq());
		// 删除远程目录
		if(YN_FLAG.YES.equals(dir_yn_flag)){
			//根据数据源，查看远程文件列表
			List<FileListBean> file_list = remoteFileList.showRemoteFileList(relative_path, soc_name, input.getWork_seq(), false);
			if(!Assert.isEmpty(file_list)){
				for(FileListBean bean : file_list){
					if(bean.isDir()){
						throw new EnvConfigFileDelException().addScene("REASON", "不能包含子文件夹");
					}
				}
			}
			// 若目录不以"/"结尾则补全
			if(!relative_path.endsWith("/")){
				relative_path = relative_path + "/";
			}
			file_name = "";
			file_path = relative_path;
			ftprcallSrv.deleteDir(ftpBean, relative_path);
		// 删除远程文件
		}else{
			ftprcallSrv.deleteFile(ftpBean, relative_path);
		}
		// 新增环境配置文件变更表
		logger.info("新增环境配置文件变更表");
		info.setFile_work_seq(file_work_seq);
		info.setBatch_no(batch_no);
		info.setEnv_name(env_name);
		info.setServer_name(ce_server_name);
		// 根据服务器名称查询服务器地址
		CeServerInfo server_info = serverSrv.getInfoByServerName(ce_server_name);
		info.setServer_ip(Assert.isEmpty(server_info) ? null : server_info.getServer_ip());
		info.setFopt_type(FOPT_TYPE.DELETE);
		info.setFile_bk_fname(file_name);
		info.setFile_bk_path(file_path);
		info.setDir_yn_flag(dir_yn_flag);
		info.setOpt_status(OPT_STATUS.SUCCESS);
		info.setModify_user_id(input.getOrg_user_id());
		info.setModify_bk_date(input.getDtbs_bk_date());
		info.setModify_bk_time(input.getDtbs_bk_time());
		confModSrv.insertInfo(info);
		
		logger.info("-----------------DeleteEnvConfigFileAction End------------------");
		return output;
	}

	/** 
	 * Description: 删除配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteEnvConfigFileInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("服务器名：" + input.getCe_server_name());
		lst_val.add("相对路径：" + input.getRelative_path());
		return lgsvc.getLogTxt("删除配置文件服务", lst_val);
	}

}
