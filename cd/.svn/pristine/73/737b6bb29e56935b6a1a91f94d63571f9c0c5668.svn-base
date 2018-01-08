/**
 * Title: DeleteSystemAction.java
 * File Description: 删除应用系统
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.DeleteSystemInputBean;
import com.wk.cd.build.en.bean.DeleteSystemOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeSystemCfgDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.dao.CeSystemTemplateDaoService;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.build.exc.SystemExistEnvException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除应用系统
 * @author chiss
 */
public class DeleteSystemAction extends ActionBasic<DeleteSystemInputBean, DeleteSystemOutputBean> {
	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject
	private SystemService systemService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 删除应用系统操作
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteSystemOutputBean doAction(DeleteSystemInputBean input) {
		logger.info("-----------DeleteSystemAction begin----------");
		DeleteSystemOutputBean output = new DeleteSystemOutputBean();
		String sys_name = input.getSys_name();
		// 非空校验
		Assert.assertNotEmpty(sys_name, DeleteSystemInputBean.SYS_NAMECN);
		// 校验是否存在
		systemService.checkSystemNameIsNotExist(sys_name);
		// 应用系统下存在关联环境不允许删除
		if(ceEnvironmentDaoService.countEnvironmentBySysName(sys_name) > 0) {
			throw new SystemExistEnvException().addScene("SYSTEM", sys_name);
		}
		// 删除 模板关联表
		ceSystemTemplateDaoService.deleteSystempBySysName(sys_name);
		// 删除应用系统配置文件表
		ceSystemCfgDaoService.deleteSystemCfgBySysName(sys_name);
		// 删除一条记录
		CeSystemInfo info = new CeSystemInfo();
		info.setSys_name(sys_name);
		ceSystemDaoService.deleteSystem(info);
		logger.info("-----------DeleteSystemAction begin----------");
		return output;
	}

	/**
	 * Description: 删除应用系统日志写入
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteSystemInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("sys_name：" + input.getSys_name());
		return lgsvc.getLogTxt("删除系统", lst_val);
	}

}
