/**
 * Title: ReadConfigFileAction.java
 * File Description: 读取配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ReadEnvConfigFileInputBean;
import com.wk.cd.build.ea.bean.ReadEnvConfigFileOutputBean;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileStringService;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.AgentFTPRCallService;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 读取配置文件服务
 * @author Xul
 */
public class ReadEnvConfigFileAction extends ActionBasic<ReadEnvConfigFileInputBean, ReadEnvConfigFileOutputBean>{
	
	@Inject private DtCheckSocExistService dtSocSrv;
	@Inject private FTPRCallService ftprcallSrv;
	@Inject private ServerCommonService serverCommonService;
	@Inject private CommonService comsrv;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private FileStringService fileStrsrv;
	@Inject private CeServerDaoService ceServerDaoService;
	@Inject private AgentFTPRCallService agentFTPRCallService;
	private static final Log logger = LogFactory.getLog();
	
	//定义下载配置文件本地存放路径
	private static final String LOCAL_PATH = "temp";
	
	/** 
	 * Description: 读取配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected ReadEnvConfigFileOutputBean doAction(
			ReadEnvConfigFileInputBean input) {
		logger.info("-----------------ReadEnvConfigFileAction Begin------------------");
		ReadEnvConfigFileOutputBean output = new ReadEnvConfigFileOutputBean();
		
		//非空校验
		String ce_server_name = input.getCe_server_name();
		String relative_path = input.getRelative_path();
		String encoding = input.getEncoding();
		Assert.assertNotEmpty(ce_server_name, ReadEnvConfigFileInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(relative_path, ReadEnvConfigFileInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(encoding, ReadEnvConfigFileInputBean.ENCODINGCN);
		
		//合法性校验
		serverCommonService.checkServerIsExist(ce_server_name);
		String soc_name = null;
		CeServerInfo server_info = ceServerDaoService.getInfoByServerName(ce_server_name);
		String server_ip = server_info.getServer_ip();
		if (server_info.getAgent_config_yn_flag() == YN_FLAG.YES) {
			soc_name = server_ip.substring(server_ip.lastIndexOf(".") + 1) + "agent";
		} else {
			soc_name = serverCommonService.getFtpConfigSocByServerName(ce_server_name);
		}
//		String soc_name = serverCommonService.getFtpConfigSocByServerName(ce_server_name);
		
		// 获取本地存放路径
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		// 为避免重复，本地文件名设为时间戳
//		String local_file_name = Long.toString(input.getDtbs_bk_time().longValue());
		// 若文件夹不存在，则创建
		File dir = new File(web_root_path + LOCAL_PATH);
		if(!dir.exists()){
			dir.mkdir();
		}
//		String local_full_path = web_root_path + LOCAL_PATH + "/"+ local_file_name;
		String local_full_path = web_root_path + LOCAL_PATH + "/" + relative_path.substring(relative_path.lastIndexOf("/")+1);
		
		// 根据数据源名获取ftp或sftp连接实例
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(soc_name,input.getWork_seq(),server_ip);
		// 下载文件
		logger.debug("下载文件from[{}],to[{}]", relative_path, local_full_path);
		if (server_info.getAgent_config_yn_flag() == YN_FLAG.YES) {
			agentFTPRCallService.downloadFile(ftpBean, relative_path, local_full_path);
		} else {
			ftprcallSrv.downloadFile(ftpBean, relative_path, local_full_path);
		}
		// 读取文件，并返回字符串
		
		List<String> list = fileStrsrv.readFileToString(local_full_path, encoding);
		String config_string = list.get(0);
		String system = list.get(1);
		output.setConfig_string(config_string);
		output.setSystem(system);
		// 删除下载的文件
		File file = new File(local_full_path);
		if(file.exists()){
			file.delete();
		}
		
		logger.info("-----------------ReadEnvConfigFileAction End------------------");
		return output;
	}

	/** 
	 * Description: 读取配置文件服务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ReadEnvConfigFileInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("数据源名：" + input.getServer_name());
		lst_val.add("相对路径：" + input.getRelative_path());
		lst_val.add("字符集：" + input.getEncoding());
		return lgsvc.getLogTxt("读取配置文件服务", lst_val);
	}

}
