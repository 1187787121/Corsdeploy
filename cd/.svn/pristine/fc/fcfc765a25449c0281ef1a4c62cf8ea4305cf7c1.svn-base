/**
 * Title: PageCeEnvironmentListAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.en.bean.CeEnvironmentBean;
import com.wk.cd.build.en.bean.PageEnvironmentInputBean;
import com.wk.cd.build.en.bean.PageEnvironmentOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询应用环境列表
 * @author xuph
 */
public class PageEnvironmentAction
		extends ActionBasic<PageEnvironmentInputBean, PageEnvironmentOutputBean> {
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private SystemService systemService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private EnvPrivService envPrivService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 分页查询应用环境操作
	 * @param input
	 * @return
	 */
	@Override
	protected PageEnvironmentOutputBean doAction(PageEnvironmentInputBean input) {
		logger.info("----------------------PageCeEnvironmentAction Begin--------------");
		PageEnvironmentOutputBean output = new PageEnvironmentOutputBean();
		String user_id = input.getOrg_user_id();
		int limit_recd = input.getLimit_recd();
		int start_recd = input.getStart_recd();
		String sys_name = input.getSys_name();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		// 非空校验
		Assert.assertNotEmpty(user_id, PageEnvironmentInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(start_recd, PageEnvironmentInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageEnvironmentInputBean.LIMIT_RECDCN);

		if (!Assert.isEmpty(sys_name)) {
			sys_name = sys_name.trim().toUpperCase();
		}
		// 分页查询应用环境信息
		List<CeEnvironmentInfo> envir_list = ceEnvironmentDaoService.pageEnvironmentBySysName(sys_name, sys_name, order_col_name, order_type, start_recd, limit_recd);
		CeEnvironmentBean[] envir_bean = new CeEnvironmentBean[envir_list.size()];
		logger.info("应用环境信息列表长度：env_size=[{}]", envir_list.size());
		if (!Assert.isEmpty(envir_list)) {
			int i = 0;
			for (CeEnvironmentInfo envir : envir_list) {
				CeEnvironmentBean bean = new CeEnvironmentBean();
				if (pgProgramDaoService.countByEnvName(envir.getEnv_name()) > 0) {
					bean.setPg_mark(true);
				}
				List<ELE_TYPE> ele_type = new ArrayList<ELE_TYPE>();
				ele_type = environmentPublicService.generateEleTypeList(envir.getEle_type());
				bean.setEnv_name(envir.getEnv_name());
				bean.setEnv_cn_name(envir.getEnv_cn_name());
				bean.setDt_range(envir.getDt_range());
				bean.setEnv_type(envir.getEnv_type());
				bean.setSys_cn_name(envir.getSys_name());
				bean.setEle_type(ele_type.toArray(new ELE_TYPE[ele_type.size()]));
				bean.setCreate_bk_date(envir.getCreate_bk_date());
				bean.setCreate_bk_time(envir.getCreate_bk_time());
				if (envPrivService.hasUserEnvPriv(user_id,envir.getEnv_name())) {
					bean.setAf_flag(AF_FLAG.ALLOW);
				}else{
					bean.setAf_flag(AF_FLAG.FORBID);
				}
				logger.info("环境名称：env_name=[{}]", envir.getEnv_name());
				logger.info("构成要素：ele_type=[{}]", ele_type);
				envir_bean[i] = bean;
				i++;
			}
		}
		output.setEnvir_list(envir_bean);
		int all_recd = ceEnvironmentDaoService.countEnvironmentBySysCnName(sys_name, sys_name);
		output.setAll_recd(all_recd);
		logger.info("----------------------PageCeEnvironmentAction End----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageEnvironmentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("sys_name=" + input.getSys_name());
		return lgsvc.getLogTxt("分页查询应用环境信息", lst_val);
	}

}
