/**
 * Title: AddWorkAction.java
 * File Description:新增任务定义 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.work.wk.bean.AddWorkInputBean;
import com.wk.cd.work.wk.bean.AddWorkOutputBean;
import com.wk.cd.work.wk.bean.DtSourceBean;
import com.wk.cd.work.wk.bean.RsResCodeBean;
import com.wk.cd.work.wk.bean.SvServiceBean;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.info.WkWorkRsInfo;
import com.wk.cd.work.wk.info.WkWorkSocInfo;
import com.wk.cd.work.wk.info.WkWorkSrvInfo;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description:新增任务定义
 * @author tlw
 */
public class AddWorkAction
		extends ActionBasic<AddWorkInputBean, AddWorkOutputBean> {
	@Inject
	private WorkConfigPublicService wcPubSrv;
	@Inject
	private DtCheckSocExistService dtSrv;
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private RsPublicService rsSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private GenNoService gnSrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 新增任务
	 * @param input 新增任务输入接口信息
	 * @return 新增任务返回信息：任务编码
	 */
	@Override
	protected AddWorkOutputBean doAction(AddWorkInputBean input) {
		AddWorkOutputBean output = new AddWorkOutputBean();
		logger.info("****************AddWorkAction Begin***************");
		String work_cn_name = input.getWork_cn_name();
		String work_bk_desc = input.getWork_bk_desc();
		IS_PUBLISH is_publish = input.getIs_publish();
		FUN_TYPE work_fun_type = input.getWork_fun_type();
		List<RsResCodeBean> rs_code_list = input.getRs_code_list();
		List<DtSourceBean> soc_list = input.getSoc_list();
		List<SvServiceBean> srv_list = input.getSrv_list();
		JaDate cur_date = input.getDtbs_bk_date();
		// 非空检查
		Assert.assertNotEmpty(work_cn_name, AddWorkInputBean.WORK_CN_NAMECN);
		Assert.assertNotEmpty(is_publish, AddWorkInputBean.IS_PUBLISHCN);
		Assert.assertNotEmpty(work_fun_type, AddWorkInputBean.WORK_FUN_TYPECN);
		// 生成任务编码
		String work_code = gnSrv.getWorkCode(cur_date);
		// 写任务定义表
		logger.info("****************Add WK_WORK***************");
		WkWorkInfo info = new WkWorkInfo();
		info.setWork_code(work_code);
		info.setWork_cn_name(work_cn_name);
		info.setWork_bk_desc(work_bk_desc);
		info.setIs_publish(is_publish);
		info.setWork_fun_type(work_fun_type);
		info.setCrt_bk_date(cur_date);
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setRcd_state(RCD_STATE.NORMAL);
		wcPubSrv.insertWorkDaoInfo(info);
		// 写任务资源配置表
		if (!Assert.isEmpty(rs_code_list)) {
			logger.info("****************Add WK_WORK_RS***************");
			List<WkWorkRsInfo> rs_infos = new ArrayList<WkWorkRsInfo>();
			for (RsResCodeBean rs : rs_code_list) {
				WkWorkRsInfo rs_info = new WkWorkRsInfo();
				rs_info.setWork_code(work_code);
				String rs_code = rs.getRs_code();
				rsSrv.checkRsExist(rs_code); // 检查资源是否存在

				rs_info.setRs_code(rs_code);
				rs_info.setBackup_fld("");
				rs_infos.add(rs_info);
			}
			wcPubSrv.insertWorkRsDaoInfo(rs_infos);
		}
		// 写数据源配置表
		if (!Assert.isEmpty(soc_list)) {
			logger.info("****************Add WK_WORK_SOC***************");
			List<WkWorkSocInfo> soc_lists = new ArrayList<WkWorkSocInfo>();
			for (DtSourceBean soc : soc_list) {
				WkWorkSocInfo soc_info = new WkWorkSocInfo();
				soc_info.setWork_code(work_code);
				String soc_name = soc.getSoc_name();
				dtSrv.checkSocExist(soc_name);// 检查数据源是否存在

				soc_info.setBackup_fld("");
				soc_info.setSoc_name(soc_name);
				soc_lists.add(soc_info);
			}
			wcPubSrv.insertWorkSocDaoInfo(soc_lists);
		}
		// 写服务配置表
		if (!Assert.isEmpty(srv_list)) {
			logger.info("****************Add WK_WORK_SRV***************");
			List<WkWorkSrvInfo> srv_lists = new ArrayList<WkWorkSrvInfo>();
			for (SvServiceBean srv : srv_list) {
				WkWorkSrvInfo srv_info = new WkWorkSrvInfo();
				srv_info.setWork_code(work_code);
				String srv_name = srv.getSrv_name();
				ssSrv.checkServiceExist(srv_name); // 检查服务是否存在

				srv_info.setBackup_fld("");
				srv_info.setSrv_name(srv_name);
				srv_lists.add(srv_info);
			}
			wcPubSrv.insertWorkSrvDaoInfo(srv_lists);
		}
		// 输出接口赋值
		output.setWork_code(work_code);
		return output;
	}

	/**
	 * 新增任务日志
	 * @param input 新增任务输入接口信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(AddWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWork_cn_name());
		lst_val.add("是否发布：" + input.getIs_publish().getCname());
		lst_val.add(input.getWork_fun_type().getCname());
		return lgsrv.getLogTxt("新增服务定义", lst_val);
	}
}
