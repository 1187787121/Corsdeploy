/**
 * Title: WorkPublicService.java
 * File Description:任务模块公共服务类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/14/2014
 */

package com.wk.cd.work.wk.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.BeanTool;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.PEND_TYPE;
import com.wk.cd.enu.QUERY_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.RequestDataRegistErrorException;
import com.wk.cd.exc.ResponseDataAcquiredErrorException;
import com.wk.cd.remote.hp.bean.AppBean;
import com.wk.cd.remote.hp.service.AppRequestService;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.exc.ApproveDeptRoleLackForSrvException;
import com.wk.cd.work.exc.SrvNotSupportRemoteAuthException;
import com.wk.cd.work.exc.WorkStateAbnormalException;
import com.wk.cd.work.wk.bean.HistoryWorkBean;
import com.wk.cd.work.wk.bean.QueryPendWorkOutputBean;
import com.wk.cd.work.wk.bean.UpdateWorkStateToCompleteBean;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkDealStateDaoService;
import com.wk.cd.work.wk.dao.WkDetailRegisterDaoService;
import com.wk.cd.work.wk.dao.WkWorkProcessDaoService;
import com.wk.cd.work.wk.info.WkDealDetailInfo;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.info.WkDetailRegisterInfo;
import com.wk.cd.work.wk.info.WkWorkProcessInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:任务模块公共服务类
 * 
 * @author lixl
 */
public class WorkPublicService {
	@Inject
	private WkDealStateDaoService stdaos;
	@Inject
	private WkDealDetailDaoService dwdaos;
	@Inject
	private WkDetailRegisterDaoService regdaos;
	@Inject
	private WkDealDetailDaoService wkdetaidaos;
	@Inject
	private UsGetUserInfoService usGetSrv;
	@Inject
	private WkWorkProcessDaoService wkprocessdaos;
	@Inject
	private SvSrvService svsvc;
	@Inject
	private ApPublicService apsrv;
	@Inject
	private AppRequestService msgSvc;

	private static final Log logger = LogFactory.getLog();

	/**
	 * 登记待处理任务
	 * 
	 * @param <T>
	 *            登记目标类型
	 * @param input
	 *            登记对象(一般是Action接口）
	 */
	public <T extends ActionRootInputBean> void addPendWork(T input) {
		String temp = "";
		int len;
		Assert.assertNotEmpty(input, "input");
		// 发起部门
		String dept_id = input.getOrg_dept_id();
		WkDealStateInfo info = new WkDealStateInfo();
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_dept_id(input.getOrg_dept_id());
		info.setCrt_user_cn_name(input.getOrguser_cn_name());
		info.setCrt_dept_cn_name(input.getOrgdept_cn_name());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setPbl_code(input.getPbl_code());
		info.setPend_rs_code(input.getOrg_rs_code());
		// 当前提交服务信息做为待处理任务信息
		info.setPend_srv_name(input.getOrg_srv_name());
		info.setPend_work_code(input.getOrg_work_code());
		info.setPend_work_seq(input.getPend_work_seq());
		info.setPendwk_bk_expl(input.getPendwk_bk_expl());
		info.setSubmit_work_seq(input.getWork_seq());
		info.setApply_bk_expl(input.getApply_bk_expl());
		// 设置待处理数据源列表
		if (input.getOrglst_soc_name() != null) {
			len = input.getOrglst_soc_name().length;
			if (len == 1) {
				temp = input.getOrglst_soc_name()[0];
			}
			for (int i = 0; len > 2 && i < len; i++) {
				temp = temp + ":" + input.getOrglst_soc_name()[i];
			}
		}
		info.setPend_ary_socname(temp);
		info.setProxy_user_id(input.getProxy_user_id());
		info.setRcd_state(RCD_STATE.NORMAL);
		info.setBackup_fld("");// 暂时不用
		// 新增任务审批流程表
		addProcess(info, dept_id, input.getOrg_srv_name());

		// 获取复核授权用户信息
		info = addCheckOrAuthUser(info, dept_id, input.getOrg_srv_name());

		// 增加任务状态记录
		stdaos.insertInfo(info);

		// 登记接口明细
		registPendWorkDetail(input.getPend_work_seq(), input,input.getOrg_srv_name() , input.getApply_html()); // 将对象序列化后登记到表中
	}

	/**
	 * 查询待处理任务
	 * 
	 * @param T
	 * @param pend_work_seq
	 *            待处理流水号
	 * @return QueryPendWorkOutputBean<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> QueryPendWorkOutputBean<T> queryPendWork(String pend_work_seq) {
		QueryPendWorkOutputBean<T> output = new QueryPendWorkOutputBean<T>();
		T obj;

		Assert.assertNotEmpty(pend_work_seq, "pend_work_seq");

		WkDealStateInfo dwState = new WkDealStateInfo();
		List<WkDealDetailBean> dwDtl;

		// 设置待处理任务状态信息
		dwState.setPend_work_seq(pend_work_seq);
		dwState = stdaos.getInfoByKey(dwState);
		output.setDw_state(dwState);

		// 设置待处理任务处理明细信息
		dwDtl = dwdaos.queryDealDetailByWorkSeq(pend_work_seq);
		output.setDw_dtl(dwDtl);

		// 反序列化存储的对象
		WkDetailRegisterInfo info = new WkDetailRegisterInfo();
		info.setPend_work_seq(pend_work_seq);
		info = regdaos.getInfoByKey(info);
		ByteArrayInputStream bi = new ByteArrayInputStream(info
				.getInte_detail());
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(bi);
			obj = (T) in.readObject();

			String json_str = obj.toString();
			logger.debug("Json反序列化信息:[{}]",json_str);
			// BeanTool.jsonStr2BeanFld(json_str, false);
			output.setNew_json_list(json_str);
			output.setObj(obj);
		} catch (IOException e) {
			logger.info("Register pend_work_seq is [" + pend_work_seq + "]");
			logger.error(e.toString(), e);
			throw new ResponseDataAcquiredErrorException().addScene("SEQ",
					pend_work_seq);
		} catch (ClassNotFoundException e) {
			logger.info("Register pend_work_seq is [" + pend_work_seq + "]");
			logger.error(e.toString(), e);
			throw new ResponseDataAcquiredErrorException().addScene("SEQ",
					pend_work_seq);
		} catch (IllegalArgumentException e) {
			logger.info("Register pend_work_seq is [" + pend_work_seq + "]");
			logger.error(e.toString(), e);
			throw new ResponseDataAcquiredErrorException().addScene("SEQ",
					pend_work_seq);
		} finally {
			try {
				bi.close();
				if (in != null && !Assert.isEmpty(in)) {
					in.close();
				}
			} catch (IOException e) {
				logger
						.info("Register pend_work_seq is [" + pend_work_seq
								+ "]");
				logger.error(e.toString(), e);
				throw new ResponseDataAcquiredErrorException().addScene("SEQ",
						pend_work_seq);
			}
		}
		return output;
	}

	/**
	 * 将输入接口序列化后进行登记
	 * 
	 * @param pend_work_seq
	 *            待处理任务流水
	 * @param obj
	 *            需要序列化登记的对象
	 */
	private <T extends ActionRootInputBean> void registPendWorkDetail(
			String pend_work_seq , T obj , String org_srv_name , String apply_html) {
		try {
			// 判断登记的输入信息是否包含了密码字段，包含密码报错
			String pwds = CfgTool
					.getProjectPropterty("cms.transient.fld.check");
			String[] pwd = pwds.split("\\,");
			for (String p : pwd) {
				try {
					if (!BeanTool.fldHasKeyWord(obj.getClass(), p, "transient")) {
						logger.info("输入信息类中密码字段没有添加transient关键字");
						throw new RequestDataRegistErrorException().addScene(
								"SEQ", pend_work_seq);
					}
				} catch (CorsManagerSystemErrorException e) {
					continue;
				}
			}
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bo);
			out.writeObject(obj);
			WkDetailRegisterInfo info = new WkDetailRegisterInfo();
			info.setPend_work_seq(pend_work_seq);
			info.setInte_detail(bo.toByteArray());
			//查询服务表
			SvSrvInfo svInfo = new SvSrvInfo();
			svInfo.setSrv_name(org_srv_name);
			SvSrvInfo sv_Info = svsvc.queryNoDelSvSrvByName(svInfo);
			info.setAprov_type(sv_Info.getAprov_type());
			
			//若为定制页面
			if(APROV_TYPE.CUSTOM_PAGE.equals(sv_Info.getAprov_type())){
				info.setCustom_rs_code(sv_Info.getCustom_rs_code());
			}
			//若为静态页面
			if(APROV_TYPE.STATIC_PAGE.equals(sv_Info.getAprov_type())){
				if(!Assert.isEmpty(apply_html)){
					info.setApply_html(apply_html.getBytes());
				}
			}
			
			regdaos.insertInfo(info);
			out.close();
		} catch (IOException e) {
			logger.info("Register pend_work_seq is [" + pend_work_seq + "]");
			logger.error(e.toString(), e);
			throw new RequestDataRegistErrorException().addScene("SEQ",
					pend_work_seq);
		}
	}

	private WkDealStateInfo addCheckOrAuthUser(WkDealStateInfo info,
			String dept_id, String srv_name) {
		String dprl_code = "";
		List<ChkDprlCodeBean> check_dprl_list = apsrv.queryCheckBySrvName(
				dept_id, srv_name);
		if (!Assert.isEmpty(check_dprl_list)) {
			// 获取复核序号最小的记录
			int min_seq = -1, item = 0, check_len = check_dprl_list.size();
			for (int i = 0; i < check_len; i++) {
				if (min_seq == -1) {
					min_seq = check_dprl_list.get(i).getCheck_seq();
					item = i;
				}
				if (i > 1) {
					int check_seq = check_dprl_list.get(i).getCheck_seq();
					if (check_seq < min_seq) {
						min_seq = check_seq;
						item = i;
					}
				}
			}
			ChkDprlCodeBean bean = check_dprl_list.get(item);

			// 查询部门角色编码
			if (bean.getChk_aprov_category() == APROV_CATEGORY.ROLE) {
				dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id, bean
						.getChk_dprl_code());
			} else {
				dprl_code = bean.getChk_dprl_code();
			}

			// 查询部门角色对应权重值最小的用户
			List<String> user_ids = new ArrayList<String>();
			user_ids.add(info.getCrt_user_id());
			UsUserRoleInfo user_role = usGetSrv
					.queryUserRoleByDprlAndMinWeght(dprl_code, user_ids);
			info.setPend_user_cn_name(usGetSrv.getUserInfoByUserId(user_role.getUser_id()).getUser_cn_name());
			info.setPend_user_id(user_role.getUser_id());
			info.setPend_deal_seq(min_seq);
			info.setWorkflow_state(WORKFLOW_STATE.RECHECK);
		} else {
			List<AuthDprlCodeBean> auth_dprl_list = apsrv.queryAuthBySrvName(
					dept_id, srv_name);
			if (!Assert.isEmpty(auth_dprl_list)) {
				AuthDprlCodeBean bean = new AuthDprlCodeBean();
				// 获取远程授权序号最小的记录
				int min_seq = -1, item = -1, auth_len = auth_dprl_list.size();
				for (int i = 0; i < auth_len; i++) {
					if (auth_dprl_list.get(i).getAuth_type() == AUTH_TYPE.REMOTE) {
						if (min_seq == -1) {
							min_seq = auth_dprl_list.get(i).getAuth_seq();
							item = i;
						}
						if (i > 0) {
							int auth_seq = auth_dprl_list.get(i).getAuth_seq();
							if (auth_seq < min_seq) {
								min_seq = auth_seq;
								item = i;
							}
						}
					}
				}
				if (item > -1) {
					bean = auth_dprl_list.get(item);
					// 查询部门角色编码
					if (bean.getAuth_aprov_category() == APROV_CATEGORY.ROLE) {
						dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id,
								bean.getAuth_dprl_code());
					} else {
						dprl_code = bean.getAuth_dprl_code();
					}
					// 查询部门角色对应权重值最小的用户
					List<String> user_ids = new ArrayList<String>();
					user_ids.add(info.getCrt_user_id());
					UsUserRoleInfo user_role = usGetSrv
							.queryUserRoleByDprlAndMinWeght(dprl_code, user_ids);
					info.setPend_user_cn_name(usGetSrv.getUserInfoByUserId(user_role.getUser_id()).getUser_cn_name());
					info.setPend_user_id(user_role.getUser_id());
					info.setPend_deal_seq(min_seq);
					info.setWorkflow_state(WORKFLOW_STATE.APPROVAL);
				} else {
					throw new SrvNotSupportRemoteAuthException().addScene(
							"OPT", srv_name);
				}
			}
		}
		sendMessage(info.getPend_user_id());
		return info;
	}

	/**
	 * 根据待处理流水号查询状态正常的记录,找不到报错
	 * 
	 * @param pend_work_seq
	 *            待处理流水号
	 * @return
	 */
	public WkDealStateInfo queryWorkStateByWorkSeq(String pend_work_seq) {
		return stdaos.queryInfoByWorkSeq(pend_work_seq);
	}

	/**
	 * 根据到处理任务流水号查询待处理任务明细，找不到记录报错
	 * 
	 * @param pend_work_seq
	 *            待处理任务流水号
	 * @return
	 */
	public List<WkDealDetailInfo> queryWorkDetailListByWorkSeq(
			String pend_work_seq) {
		return dwdaos.queryWorkDetailListByWorkSeq(pend_work_seq);
	}

	/**
	 * Description: 根据用户信息查询所有待处理任务(不包括自己提交)
	 * 
	 * @param user_id
	 *            用户名称
	 * @param start_recd
	 *            起始条数
	 * @param limit_recd
	 *            查询条数
	 * @return 任务信息
	 */
	public List<WkDealStateInfo> pageUnhandleWork(String user_id,
			int start_recd, int limit_recd) {
		return stdaos.pageUnhandleWork(user_id, start_recd, limit_recd);
	}

	/**
	 * 用户查询个人待处理任务数量
	 * 
	 * @param crt_user_id
	 *            用户名
	 * @return 任务状态列表
	 */
	public int countInfoNumByUser(String crt_user_id) {
		return stdaos.countUnhandleWork(crt_user_id);
	}


	/**
	 * 查询待复核任务
	 * 
	 * @param start_recd
	 *            起始记录
	 * @param limit_recd
	 *            查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageUncheckWork(String user_id,
			int start_recd, int limit_recd) {
		return stdaos.pageUncheckWork(user_id, start_recd, limit_recd);
	}

	/**
	 * 查询待复核任务数量
	 * 
	 * @param user_id
	 *            待处理用户
	 * @return
	 */
	public int countUncheckWork(String user_id) {
		return stdaos.countUncheckWork(user_id);
	}

	/**
	 * 查询待授权任务
	 * 
	 * @param start_recd
	 *            起始记录
	 * @param limit_recd
	 *            查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageUnauthWork(String user_id, int start_recd,
			int limit_recd) {
		return stdaos.pageUnauthWork(user_id, start_recd, limit_recd);
	}
	
	/**
	 * 查询待授权任务数量
	 * 
	 * @param user_id
	 *            待授权用户
	 * @return
	 */
	public int countUnauthWork(String user_id) {
		return stdaos.countUnauthWork(user_id);
	}
	
	/**
	 * Description: 查询所有已拒绝的任务
	 * @param user_id 用户名
	 * @param start_recd 查询起始条数
	 * @param limit_recd 查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageRefuseWork(String user_id, int start_recd,
			int limit_recd) {
		return stdaos.pageRefuseWork(user_id, start_recd, limit_recd);
	}
	
	/**
	 * Description: 查询所有已拒绝的任务数量
	 * @param user_id 用户名
	 * @return
	 */
	public int countRefuseWork(String user_id) {
		return stdaos.countRefuseWork(user_id);
	}

	/**
	 * 根据主键更新一条流水
	 * 
	 * @param pend_work_seq
	 *            主键：待处理流水
	 * @param pend_bk_seq
	 *            待处理序号
	 * @param pend_user_id
	 *            待处理用户名
	 * @param workflow_state
	 *            任务状态
	 * @return 更新条数
	 */
	public int updateWorkStateByWorkSeq(String pend_work_seq,
			int pend_deal_seq, String pend_user_id,String pend_user_cn_name,
			WORKFLOW_STATE workflow_state) {
		return stdaos.updateWorkStateByWorkSeq(pend_work_seq, pend_deal_seq,
				pend_user_id,pend_user_cn_name, workflow_state);
	}

	/**
	 * 更新任务状态为完成，并且登记任务明细信息
	 * 
	 * @param bean
	 *            输入信息
	 */
	public void updateWorkStateToComplete(UpdateWorkStateToCompleteBean bean) {
		String pend_work_seq = bean.getPend_work_seq();
		String deal_user_id = bean.getDeal_user_id();
		JaDate deal_bk_date = bean.getDeal_bk_date();
		JaTime deal_bk_time = bean.getDeal_bk_time();
		// 待处理流水、用户、日期、时间都不能为空
		Assert.assertNotEmpty(pend_work_seq,
				UpdateWorkStateToCompleteBean.PEND_WORK_SEQCN);
		Assert.assertNotEmpty(deal_user_id,
				UpdateWorkStateToCompleteBean.DEAL_USER_IDCN);
		Assert.assertNotEmpty(deal_bk_date,
				UpdateWorkStateToCompleteBean.DEAL_BK_DATECN);
		Assert.assertNotEmpty(deal_bk_time,
				UpdateWorkStateToCompleteBean.DEAL_BK_TIMECN);
		// 修改任务状态表
		stdaos.updateWorkStateByWorkSeq(pend_work_seq, 0, "","",
				WORKFLOW_STATE.COMPLETE);
		// 登记任务明细表
		// 获取任务明细表中当前待处理流水的记录条数
		int count = wkdetaidaos.countWorkDetailByWorkSeq(pend_work_seq);
		WkDealDetailInfo info = new WkDealDetailInfo();
		info.setPend_work_seq(pend_work_seq);
		info.setDeal_seq(count + 1);
		info.setDeal_type(DEAL_TYPE.EXE);
		info.setDeal_res(DEAL_RES.NOTHING);
		info.setDeal_user_id(deal_user_id);
		info.setDeal_bk_date(deal_bk_date);
		info.setDeal_bk_time(deal_bk_time);
		info.setDeal_bk_desc(bean.getDeal_bk_desc());
		dwdaos.insertInfo(info);
	}

	/**
	 * 任务处理明细中插入一条记录
	 * 
	 * @param info
	 *            需要查询记录信息
	 * @return 插入条数
	 */
	public int insertInfo(WkDealDetailInfo info) {
		return dwdaos.insertInfo(info);
	}

	/**
	 * 查询一个待处理任务是否是待执行状态，是待执行状态返回TRUE，否则返回FALSE
	 * 
	 * @param pend_work_seq
	 *            待处理任务流水号
	 * @return
	 */
	public Boolean queryPendWorkIsExecute(String pend_work_seq) {
		WkDealStateInfo info = stdaos.queryInfoByWorkSeq(pend_work_seq);
		if (info.getWorkflow_state() == WORKFLOW_STATE.EXECUTORY) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个待处理任务的当前处理状态是否是待执行状态，不是待执行状态报错
	 * 
	 * @param pend_work_seq
	 *            待处理任务流水号
	 * @return 当前任务状态
	 */
	public void checkPendWorkIsExecute(String pend_work_seq) {
		WkDealStateInfo info = stdaos.queryInfoByWorkSeq(pend_work_seq);
		if (info.getWorkflow_state() != WORKFLOW_STATE.EXECUTORY) {
			throw new WorkStateAbnormalException().addScene("PARM1", "待执行");
		}
	}

	/**
	 * 根据待处理服务名称和任务状态查询对应的待处理流水号
	 * 
	 * @param pend_srv_name
	 *            待处理服务名称
	 * @param workflow_state
	 *            任务状态
	 * @return 流水号列表
	 */
	public List<String> queryPendWorkSeqByWorkState(String pend_srv_name,
			List<WORKFLOW_STATE> workflow_state_list) {
		return stdaos.queryPendWorkSeqByWorkState(pend_srv_name,
				workflow_state_list);
	}

	/**
	 * Description: 分页查询任务状态
	 * 
	 * @param crt_user_id
	 *            用户id
	 * @param query_type
	 *            查询类型
	 * @param start_recd
	 *            起始记录
	 * @param limit_recd
	 *            总记录
	 * @return 任务状态列表
	 */
	public List<WkDealStateInfo> pageInfoListByWorkStateAndUser(
			String crt_user_id, QUERY_TYPE query_type, int start_recd,
			int limit_recd) {
		return stdaos.pageInfoListByWorkStateAndUser(crt_user_id, query_type,
				start_recd, limit_recd);
	}

	/**
	 * Description: 分页查询任务状态总记录
	 * 
	 * @param crt_user_id
	 *            用户id
	 * @param query_type
	 *            查询类型
	 * @return 总记录数
	 */
	public int countInfoListByWorkStateAndUser(String crt_user_id,
			QUERY_TYPE query_type) {
		return stdaos.countInfoListByWorkStateAndUser(crt_user_id, query_type);
	}
	
	/**
	 * Description: 查询由我申请的已处理任务列表
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineWorkList(String crt_user_id) {
		return stdaos.queryMineWorkList(crt_user_id);
	}
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineExecutoryWork(String crt_user_id){
		return stdaos.queryMineExecutoryWork(crt_user_id);
	}
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @return
	 */
	public int countMineExecutoryWork(String crt_user_id){
		return stdaos.countMineExecutoryWork(crt_user_id);
	}
	
	/**
	 * Description: 查询我处理过的复核或授权任务列表
	 * @param crt_user_id
	 * @param query_type
	 * @return
	 */
	public List<WkDealStateInfo> queryUncheckOrUnauthWorkList(String crt_user_id,DEAL_TYPE deal_type){
		return stdaos.queryUncheckOrUnauthWorkList(crt_user_id, deal_type);
	}
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public List<WkDealStateInfo> queryExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		return stdaos.queryExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
	}
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @param workflow_state
	 * @return
	 */
	public int countExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		return stdaos.countExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
	}
	
	/**
	 * Description: 分页查询我的历史任务列表
	 * @return
	 */
	public List<HistoryWorkBean> pageMineHistoryWork(String crt_user_id, int start_recd , int limit_recd){
		return stdaos.pageMineHistoryWork(crt_user_id,start_recd,limit_recd);
	}
	
	/**
	 * Description: 查询我的历史任务个数
	 * @param crt_user_id
	 * @return
	 */
	public int countMineHistoryWork(String crt_user_id){
		return stdaos.countMineHistoryWork(crt_user_id);
	}
	
	/**
	 * Description: 分页查询复核或授权的历史任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	public List<HistoryWorkBean> pageMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type , int start_recd , int limit_recd){
		return stdaos.pageMineUncheckOrUnauth(crt_user_id,deal_type,start_recd,limit_recd);
	}
	
	/**
	 * Description: 查询复核或授权的历史任务个数
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public int countMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type){
		return stdaos.countMineUncheckOrUnauth(crt_user_id,deal_type);
	}
	
	/**
	 * Description:新增任务审批流程
	 */
	private void addProcess(WkDealStateInfo info,String dept_id, String srv_name){
		//非空校验
		Assert.assertNotEmpty(info.getPend_work_seq(), "pend_work_seq");
		//查看服务授权记录
		List<AuthDprlCodeBean> auth_list = apsrv.queryAuthBySrvName(dept_id, srv_name);
		//查看服务复核记录
		List<ChkDprlCodeBean> check_list = apsrv.queryCheckBySrvName(dept_id, srv_name);
		//查询是否需要本地授权
		boolean isLocal = apsrv.queryLocalAuthFlag(dept_id, srv_name);
		List<WkWorkProcessInfo> process_info_list = new ArrayList<WkWorkProcessInfo>();
		
		//所有已经做过复核和授权的用户信息，如果做过复核或者授权则不能做授权
		List<String> deal_user_ids = new ArrayList<String>();
		//部门角色编码
		String dprl_code = "";
		//创建序号，处理人Map
		Map<Integer,String> deal_user_map = new HashMap<Integer, String>();
		//创建序号，处理类型Map
		Map<Integer,DEAL_TYPE> deal_type_map = new HashMap<Integer, DEAL_TYPE>();
		//创建序号，审批类别Map
		Map<Integer,APROV_CATEGORY> aprov_category_map = new HashMap<Integer, APROV_CATEGORY>();
		//查看服务授权记录，过滤本地授权
		for(int i = 0; i< auth_list.size() ; i++){
			int n = auth_list.get(i).getAuth_seq();
			if(auth_list.get(i).getAuth_type() == AUTH_TYPE.LOCAL){
				continue;
			}
			if(isLocal){
				n--;
			}
			deal_user_map.put(n, auth_list.get(i).getAuth_dprl_code());
			deal_type_map.put(n, DEAL_TYPE.AUTH);
			aprov_category_map.put(n, auth_list.get(i).getAuth_aprov_category());
		}
		//查看服务复核记录，若存在本地授权，复核序号减1
		for(int i = 0; i < check_list.size() ; i++){
			int n = check_list.get(i).getCheck_seq();
			if(isLocal){
				n--;
			}
			deal_user_map.put(n, check_list.get(i).getChk_dprl_code());
			deal_type_map.put(n, DEAL_TYPE.RECHECK);
			aprov_category_map.put(n, check_list.get(i).getChk_aprov_category());
		}
		
		//生成申请流程信息(审批序号为1)
		WkWorkProcessInfo apply_info = new WkWorkProcessInfo();
		apply_info.setDeal_seq(1);
		apply_info.setPend_type(PEND_TYPE.APPLY);
		apply_info.setPend_user_cn_name(info.getCrt_user_cn_name());
		apply_info.setPend_user_id(info.getCrt_user_id());
		apply_info.setPend_work_seq(info.getPend_work_seq());
		process_info_list.add(apply_info);
		
		//生成复核/授权流程信息
		for(int i = 1 ; i <= deal_user_map.size() ; i++){
			WkWorkProcessInfo check_auth_info = new WkWorkProcessInfo();
			//审批序号需加上申请流程，即加1
			check_auth_info.setDeal_seq(i+1);
			check_auth_info.setPend_work_seq(info.getPend_work_seq());
			if(DEAL_TYPE.AUTH.equals(deal_type_map.get(i))){
				check_auth_info.setPend_type(PEND_TYPE.AUTH);
			}else{
				check_auth_info.setPend_type(PEND_TYPE.RECHECK);
			}
			//获取部门角色编码
			if(APROV_CATEGORY.ROLE.equals(aprov_category_map.get(i))){
				dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id, deal_user_map.get(i));
			}else{
				dprl_code = deal_user_map.get(i);
			}
			// 按照权重排序后的用户和部门角色信息
			deal_user_ids.add(info.getCrt_user_id());
			List<UsUserRoleInfo> user_role_list = usGetSrv
					.queryUserByDprlCode(dprl_code, deal_user_ids);
			if (Assert.isEmpty(user_role_list)) {
				throw new ApproveDeptRoleLackForSrvException().addScene(
						"OPT", srv_name);
			}
			//添加到已复核授权列表，做过复核或者授权则不能做授权
			deal_user_ids.add(user_role_list.get(0).getUser_id());
			
			check_auth_info.setPend_user_id(user_role_list.get(0).getUser_id());
			check_auth_info.setPend_user_cn_name(usGetSrv.getUserInfoByUserId(user_role_list.get(0).getUser_id()).getUser_cn_name());
			process_info_list.add(check_auth_info);
		}
		
		//生成执行流程信息
		WkWorkProcessInfo exe_info = new WkWorkProcessInfo();
		exe_info.setDeal_seq(deal_user_map.size()+2);
		exe_info.setPend_type(PEND_TYPE.EXE);
		exe_info.setPend_user_cn_name(info.getCrt_user_cn_name());
		exe_info.setPend_user_id(info.getCrt_user_id());
		exe_info.setPend_work_seq(info.getPend_work_seq());
		process_info_list.add(exe_info);
		
		//新增任务审批流程表
		wkprocessdaos.insertListInfo(process_info_list);
		sendMessage(exe_info.getPend_user_id());
	}

	private void sendMessage(String userid){
		AppBean bean = new AppBean();
		bean.setUserId(userid);
		try {
			logger.info("App send Messsage");
			msgSvc.sendTaskMessage(bean);
		} catch (Exception e) {
			logger.warn("sendMessage error {}",e);
		}
	}
}
