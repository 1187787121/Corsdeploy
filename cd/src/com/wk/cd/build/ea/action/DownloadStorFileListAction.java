/**
 * Title: DownloadStorFileListAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年2月15日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.DownloadStorFileListInputBean;
import com.wk.cd.build.ea.bean.DownloadStorFileListOutputBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.GenerateExcelListService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 下载入库Excel文件清单
 * @author xuph
 */
public class DownloadStorFileListAction
		extends ActionBasic<DownloadStorFileListInputBean, DownloadStorFileListOutputBean> {
	@Inject
	private GenerateExcelListService generateExcelSrv;
	@Inject
	private TargetPackPublicService targetPackSrv;
	@Inject
	private EnvTaskPublicService envTaskPublicSrv;
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 主操作
	 * @param input
	 * @return
	 */
	@Override
	protected DownloadStorFileListOutputBean doAction(DownloadStorFileListInputBean input) {
		logger.info("------------------- DownloadStorFileListAction Begin-----------------------");
		DownloadStorFileListOutputBean output = new DownloadStorFileListOutputBean();
		// 生成临时文件ID
		String env_name = input.getEnv_name();
		List<TargetPackageBean> tar_package_list = input.getTar_package_list();
		Assert.assertNotEmpty(env_name, DownloadStorFileListInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(tar_package_list, DownloadStorFileListInputBean.TAR_PACKAGE_LISTCN);
		String file_id = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		String file_temp = targetPackSrv.getStorageExcelTempDir();
		String file_name = env_name + "_" + file_id + envTaskPublicSrv.generateListName("", "");
		String path = file_temp + file_name;
		// 生成本地入库清单文件
		generateExcelSrv.generateExcelList(tar_package_list, path);
		// 返回入库文件清单路径
		output.setFile_path("/temp/" + file_name);
		logger.info("------------------- DownloadStorFileListAction End  -----------------------");
		return output;
	}

	/**
	 * Description: 日志写入
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DownloadStorFileListInputBean input) {
		List<String> log_list = new ArrayList<String>();
		log_list.add("入库环境 " + input.getEnv_name());
		return lgsvc.getLogTxt("下载入库Excel文件清单", log_list);
	}

}
