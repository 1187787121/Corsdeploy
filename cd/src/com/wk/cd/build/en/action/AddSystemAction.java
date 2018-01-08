/**
 * Title: AddCeSystemAction.java
 * File Description: 新增应用系统
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.AddSystemInputBean;
import com.wk.cd.build.en.bean.AddSystemOutputBean;
import com.wk.cd.build.en.bean.SystemTemplateBean;
import com.wk.cd.build.en.dao.CeSystemCfgDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.dao.CeSystemTemplateDaoService;
import com.wk.cd.build.en.info.CeSystemCfgInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.build.en.info.CeSystemTemplateInfo;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增应用系统
 * 
 * @author chiss
 */
public class AddSystemAction extends ActionBasic<AddSystemInputBean, AddSystemOutputBean> {

	@Inject
	private SystemService systemService;
	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 新增应用系统操作
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected AddSystemOutputBean doAction(AddSystemInputBean input) {
		logger.info("-----------AddCeSystemAction begin----------");
		AddSystemOutputBean output = new AddSystemOutputBean();
		String sys_name = input.getSys_name();
		String sys_cn_name = input.getSys_cn_name();
		String sys_bk_desc = input.getSys_bk_desc();
		SYS_TYPE sys_type = input.getSys_type();
		List<SystemTemplateBean> sys_list = input.getSys_list();
		String[] file_list = input.getFile_list();
		// 非空校验
		Assert.assertNotEmpty(sys_name, AddSystemInputBean.SYS_NAMECN);
		Assert.assertNotEmpty(sys_cn_name, AddSystemInputBean.SYS_CN_NAMECN);
		Assert.assertNotEmpty(sys_bk_desc, AddSystemInputBean.SYS_BK_DESCCN);
		Assert.assertNotEmpty(sys_type.getValue() == 0 ? null : sys_type.getValue(), AddSystemInputBean.SYS_TYPECN);
		// 重复性校验
		systemService.checkSystemNameIsExist(sys_name);
		// 数据准备
		CeSystemInfo systemInfo = new CeSystemInfo();
		systemInfo.setSys_name(sys_name);
		systemInfo.setSys_cn_name(sys_cn_name);
		systemInfo.setSys_bk_desc(sys_bk_desc);
		systemInfo.setSys_type(sys_type);
		// 系统模板关联信息
		List<CeSystemTemplateInfo> temp_lis = new ArrayList<CeSystemTemplateInfo>();
		if (!Assert.isEmpty(sys_list)) {
			for (SystemTemplateBean sys : sys_list) {
				String[] template_name = sys.getTemplate_name();
				if (!Assert.isEmpty(template_name)) {
					for (int i = 0; i < template_name.length; i++) {
						CeSystemTemplateInfo sys_temp = new CeSystemTemplateInfo();
						sys_temp.setSys_name(sys_name);
						sys_temp.setTemplate_name(template_name[i]);
						sys_temp.setTemplate_type(sys.getTemplate_type());
						temp_lis.add(sys_temp);
					}
				}
			}
		}
		systemInfo.setCreate_bk_date(input.getDtbs_bk_date());
		systemInfo.setCreate_bk_time(input.getDtbs_bk_time());
		systemInfo.setCreate_user_id(input.getOrg_user_id());
		// 插入系统表
		ceSystemDaoService.insertInfo(systemInfo);
		// 插入系统模板关联表
		if (!Assert.isEmpty(temp_lis)) {
			ceSystemTemplateDaoService.insertListInfo(temp_lis);
		}
		// 插入应用系统配置文件表
		// 获取配置文件名列表
		List<String> cfg_list = new ArrayList<String>();
		if (!Assert.isEmpty(file_list)) {
			for (String cfg_bk_fname : file_list) {
				if (!cfg_list.contains(cfg_bk_fname)) {
					cfg_list.add(cfg_bk_fname);
				}
			}
		}
		if (!Assert.isEmpty(cfg_list)) {
			for (String cfg_name : cfg_list) {
				CeSystemCfgInfo cfginfo = new CeSystemCfgInfo();
				cfginfo.setSys_name(sys_name);
				cfginfo.setCfg_bk_fname(cfg_name);
				// 插入应用系统配置文件表
				ceSystemCfgDaoService.insertInfo(cfginfo);
			}
		}
		logger.info("-----------AddCeSystemAction end------------");
		return output;
	}

	/**
	 * Description: 新增应用系统日志写入
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddSystemInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("sys_name: " + input.getSys_name());
		return lgsvc.getLogTxt("新增应用系统表", log_lst);
	}

}
