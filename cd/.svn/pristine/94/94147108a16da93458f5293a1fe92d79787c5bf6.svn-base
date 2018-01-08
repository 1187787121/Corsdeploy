/**
 * Title: QueryDprlPrivAction.java
 * File Description: 查询部门角色权限信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
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
import com.wk.cd.system.us.bean.QueryDprlPrivInputBean;
import com.wk.cd.system.us.bean.QueryDprlPrivOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsExtDprlDaoService;
import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleOptPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询部门角色权限信息
 * @author HT
 */
public class QueryDprlPrivAction extends ActionBasic<QueryDprlPrivInputBean, QueryDprlPrivOutputBean>{

	@Inject
	private UsExtDprlDaoService usExtDprlDaoService;
	@Inject
	private RsOptDaoService rsOptDaoService;
	@Inject
	private UsRoleOptPrivDaoService usRoleOptPrivDaoService;
	@Inject
	private UsRoleSqlPrivDaoService usRoleSqlPrivDaoService;
	@Inject
	private UsRoleColPrivDaoService usRoleColPrivDaoService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
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
	 * Description: 查询部门角色权限信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryDprlPrivOutputBean doAction(QueryDprlPrivInputBean input) {
		logger.info("------QueryDprlPrivAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		QueryDprlPrivOutputBean output = new QueryDprlPrivOutputBean();
		// 验证部门角色编码非空
		String dprl_code = input.getDprl_code();
		Assert.assertNotEmpty(dprl_code,QueryDprlPrivInputBean.DPRL_CODECN);

		// 查询部门角色资源权限
		List<RsPrivBean> rs_list = usExtDprlDaoService.queryRsPrivByDprl(dprl_code);
		output.setRs_list(rs_list);

		// 查询所有操作信息列表
		List<RsOptInfo> opt_list = rsOptDaoService.listAllRsOpt();
		output.setOpt_list(opt_list);

		// 查询部门角色操作权限列表
		List<UsRoleOptPrivInfo> opt_priv = usRoleOptPrivDaoService.queryOptPrivByDprl(dprl_code);
		output.setOpt_priv(opt_priv);

		// 查询部门角色数据源权限
		List<SocPrivBean> soc_list = usExtDprlDaoService.querySocPrivByDprl(dprl_code);
		output.setSoc_list(soc_list);

		// 查询部门SQL权限
		List<UsRoleSqlPrivInfo> sql_list = usRoleSqlPrivDaoService.querySqlPrivByDprl(dprl_code);
		output.setSql_list(sql_list);

		// 查询部门COL权限
		List<UsRoleColPrivInfo> col_list = usRoleColPrivDaoService.queryColPrivByDprl(dprl_code);
		output.setCol_list(col_list);

		UsDeptRoleInfo info = new UsDeptRoleInfo();
		info.setDprl_code(dprl_code);
		info = usDeptRoleDaoService.getInfoByKey(info);
		String super_dept_id = info.getDept_id();
		// 查询上级部门资源权限
		List<RsPrivBean> sup_rs_list = dpDeptQueryDaoService.queryRsPrivByDeptId(super_dept_id);
		output.setSup_rs_list(rsResDaoService.addModularRs(sup_rs_list));

		// 查询呢上级部门操作权限
		List<DpDeptOptPrivInfo> sup_opt_priv = dpDeptOptPrivDaoService.queryOptPrivByDept(super_dept_id);
		output.setSup_opt_priv(sup_opt_priv);

		// 查询上级部门数据源权限
		List<SocPrivBean> sup_soc_list = dpDeptQueryDaoService.querySocPrivByDeptId(super_dept_id);
		output.setSup_soc_list(sup_soc_list);

		logger.info("------QueryDprlPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryDprlPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("查询部门角色权限信息", log_lst);
	}
}
