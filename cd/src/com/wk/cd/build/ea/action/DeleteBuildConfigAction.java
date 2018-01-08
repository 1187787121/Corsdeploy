/**
 * Title: DeleteBuildConfigAction.java
 * File Description: 删除构建配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DeleteBuildConfigInputBean;
import com.wk.cd.build.ea.bean.DeleteBuildConfigOutputBean;
import com.wk.cd.build.ea.dao.BuildConfigfileDaoService;
import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.EnvConfigFileDelException;
import com.wk.cd.enu.CFG_TYPE;
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
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;


/**
 * Class Description: 删除构建配置文件服务
 * @author chiss
 */
public class DeleteBuildConfigAction extends ActionBasic<DeleteBuildConfigInputBean, DeleteBuildConfigOutputBean>{
	@Inject private BuildConfigfileDaoService buildConfigfileDaoService;
	@Inject private EnvironmentPublicService environmentPublicService;
	@Inject private ServerCommonService serverCommonService;
	@Inject private DtCheckSocExistService dtCheckSocExistService;
	@Inject private GenNoService genNoSrv;
	@Inject private CommonService comsrv;
	@Inject private RemoteFileList remoteFileList;
	@Inject private FTPRCallService ftprcallSrv;
	@Inject private CeServerDaoService ceServerDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 删除构建配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteBuildConfigOutputBean doAction(DeleteBuildConfigInputBean input) {
		logger.info("-----------------DeleteBuildConfigAction Begin------------------");
		DeleteBuildConfigOutputBean output = new DeleteBuildConfigOutputBean();
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		String work_id = input.getWork_id();
		String relative_path = input.getRelative_path();
		YN_FLAG dir_yn_flag = input.getDir_yn_flag();
		// 非空校验
		Assert.assertNotEmpty(env_name, DeleteBuildConfigInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(ce_server_name, DeleteBuildConfigInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(work_id, DeleteBuildConfigInputBean.WORK_IDCN);
		Assert.assertNotEmpty(relative_path, DeleteBuildConfigInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(dir_yn_flag, DeleteBuildConfigInputBean.DIR_YN_FLAGCN);
		
		// 合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		serverCommonService.checkServerIsExist(ce_server_name);
		
		String soc_name = serverCommonService.getFtpConfigSocByServerName(ce_server_name);
		
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
		// 新增构建任务配置文件变更表
		BuildConfigfileInfo info = new BuildConfigfileInfo();
		info.setFile_work_seq(file_work_seq);
		info.setWork_id(work_id);
		info.setCfg_type(CFG_TYPE.NORMAL);
		info.setServer_name(ce_server_name);
		//根据服务器名称查询服务器地址
		CeServerInfo server_info = ceServerDaoService.getInfoByServerName(ce_server_name);
		info.setServer_ip(Assert.isEmpty(server_info) ? null : server_info.getServer_ip());
		info.setFopt_type(FOPT_TYPE.DELETE);
		info.setFile_bk_fname(file_name);
		info.setFile_bk_path(file_path);
		info.setDir_yn_flag(dir_yn_flag);
		info.setOpt_status(OPT_STATUS.SUCCESS);
		info.setModify_user_id(input.getOrg_user_id());
		info.setModify_bk_date(input.getDtbs_bk_date());
		info.setModify_bk_time(input.getDtbs_bk_time());
		buildConfigfileDaoService.insertInfo(info);
		// 从构建任务配置文件变更表删除修改过的同类文件
		buildConfigfileDaoService.deleteInfoByWorkAndIp(work_id,server_info.getServer_ip(),FOPT_TYPE.MODIFY,file_name);
		logger.info("-----------------DeleteBuildConfigAction End------------------");
		return output;
	}

	/** 
	 * Description: 删除构建配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteBuildConfigInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("数据源名：" + input.getSoc_name());
		lst_val.add("相对路径：" + input.getRelative_path());
		return lgsvc.getLogTxt("删除配置文件服务", lst_val);
	}

}
