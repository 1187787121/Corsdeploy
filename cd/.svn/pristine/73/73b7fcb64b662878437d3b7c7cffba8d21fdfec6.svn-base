/**
 * Title: CheckPublishListAndPacForAppAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年3月23日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.CheckPublishListAndPacForAppInputBean;
import com.wk.cd.build.ea.bean.CheckPublishListAndPacForAppOutputBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.service.FileListService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 校验发布文件清单和投产包(APP)
 * @author Xul
 */
public class CheckPublishListAndPacForAppAction extends ActionBasic<CheckPublishListAndPacForAppInputBean, CheckPublishListAndPacForAppOutputBean>{

	@Inject private FileListService fileListService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 校验发布文件清单和投产包(APP)
	 * @param input
	 * @return 
	 */
	@Override
	protected CheckPublishListAndPacForAppOutputBean doAction(CheckPublishListAndPacForAppInputBean input) {
		logger.debug("-----------CheckPublishListAndPacAction Start------------");
		CheckPublishListAndPacForAppOutputBean output = new CheckPublishListAndPacForAppOutputBean();
		
		try {
			// 非空校验
			List<TargetPackageBean> list_list = input.getList_list();
			List<TargetPackageBean> pac_list = input.getPac_list();
			Assert.assertNotEmpty(list_list, CheckPublishListAndPacForAppInputBean.LIST_LISTCN);
			Assert.assertNotEmpty(pac_list, CheckPublishListAndPacForAppInputBean.PAC_LISTCN);

			// 获取本地存放路径
			String web_root_path = CfgTool.getProjectPropterty("web.root.path");
			if (Assert.isEmpty(web_root_path)) {
				throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
			}

			// 获取清单路径
			String list_path = web_root_path + list_list.get(0).getDownload_path();
			logger.debug("清单存放路径：" + list_path);

			// 获取投产包路径
			List<String> dpp_path_list = new ArrayList<String>();
			for (TargetPackageBean bean : pac_list) {
				String pac_path = web_root_path + bean.getDownload_path();
				logger.debug("投产包存放路径：" + list_path);
				dpp_path_list.add(pac_path);
			}

			fileListService.checkFileList(list_path, dpp_path_list);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			output.setStatus(1);
			output.setMessage(e.getMessage());
		}
		
		logger.debug("-----------CheckPublishListAndPacAction End------------");
		return output;
	}

	/** 
	 * Description: 校验发布文件清单和投产包(APP)
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(CheckPublishListAndPacForAppInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		return lgsvc.getLogTxt("校验发布文件清单和投产包(APP)", log_lst);
	}

}
