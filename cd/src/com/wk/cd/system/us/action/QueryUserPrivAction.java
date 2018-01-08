/**
 * Title: QueryUserPrivAction.java
 * File Description: 查询用户权限信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月8日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dp.dao.DpDeptOptPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptQueryDaoService;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.dao.RsOptDaoService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.us.bean.QueryUserPrivInputBean;
import com.wk.cd.system.us.bean.QueryUserPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询用户权限信息
 * @author HT
 */
public class QueryUserPrivAction extends ActionBasic<QueryUserPrivInputBean, QueryUserPrivOutputBean>{

	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private RsOptDaoService rsOptDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private DpDeptQueryDaoService dpDeptQueryDaoService;
	@Inject
	private DpDeptOptPrivDaoService dpDeptOptPrivDaoService;
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询用户权限信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryUserPrivOutputBean doAction(QueryUserPrivInputBean input) {
		logger.info("------QueryUserPrivAction begin------");
		logger.debug("------user_id=[{}]", input.getUser_id());

		QueryUserPrivOutputBean output = new QueryUserPrivOutputBean();
		
		String user_id = input.getUser_id();
		Assert.assertNotEmpty(user_id,QueryUserPrivInputBean.USER_IDCN);
		
		// 查询用户资源权限
		List<RsPrivBean> rs_list = usGetUserPrivService.queryRsPrivByUserId(user_id);
		output.setRs_list(rs_list);
		
		//查询部门角色资源权限
		List<RsPrivBean> dr_rs_list = usGetUserPrivService.queryDprlRsPrivByUserId(user_id);
		output.setDr_rs_list(dr_rs_list);
		
		// 查询所有操作信息列表
		List<RsOptInfo> opt_list = rsOptDaoService.listAllRsOpt();
		output.setOpt_list(opt_list);

		// 查询用户操作权限列表
		List<UsUserOptPrivInfo> opt_priv = usGetUserPrivService.queryOptPrivByUser(user_id);
		output.setOpt_priv(opt_priv);
		
		// 查询部门角色操作权限列表
		List<UsUserOptPrivInfo> dr_opt_priv = usGetUserPrivService.queryDprlOptPrivByUser(user_id);
		output.setDr_opt_priv(dr_opt_priv);

		// 查询用户数据源权限
		List<SocPrivBean> soc_list = usGetUserPrivService.getUserSocPriv(user_id);
		output.setSoc_list(soc_list);
		
		// 查询部门角色数据源权限
		List<SocPrivBean> dr_soc_list = usGetUserPrivService.getDprlSocPrivByUser(user_id);
		output.setDr_soc_list(dr_soc_list);

		// 查询用户SQL权限
		List<UsUserSqlPrivInfo> sql_list = usGetUserPrivService.getUserSqlPriv(user_id);
		output.setSql_list(sql_list);

		// 查询用户COL权限
		List<UsUserColPrivInfo> col_list = usGetUserPrivService.getUserColPriv(user_id);
		output.setCol_list(col_list);

		UsUserInfo info = new UsUserInfo();
		info.setUser_id(user_id);
		info = usUserDaoService.getInfoByKey(info);
		String super_dept_id = info.getBl_dept_id();
		// 查询上级部门资源权限
		List<RsPrivBean> sup_rs_list = dpDeptQueryDaoService.queryRsPrivByDeptId(super_dept_id);
		output.setSup_rs_list(rsResDaoService.addModularRs(sup_rs_list));

		// 查询呢上级部门操作权限
		List<DpDeptOptPrivInfo> sup_opt_priv = dpDeptOptPrivDaoService.queryOptPrivByDept(super_dept_id);
		output.setSup_opt_priv(sup_opt_priv);

		// 查询上级部门数据源权限
		List<SocPrivBean> sup_soc_list = dpDeptQueryDaoService.querySocPrivByDeptId(super_dept_id);
		output.setSup_soc_list(sup_soc_list);
		
		logger.info("------QueryUserPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryUserPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("查询用户权限信息", log_lst);
	}
}
