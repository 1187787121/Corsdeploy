/**
 * Title: QueryDeptPrivAction.java
 * File Description: 查询部门权限信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.QueryDeptPrivInputBean;
import com.wk.cd.system.dp.bean.QueryDeptPrivOutputBean;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dp.dao.DpDeptColPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.dao.DpDeptOptPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptQueryDaoService;
import com.wk.cd.system.dp.dao.DpDeptSqlPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.dao.RsOptDaoService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询部门权限信息
 * 
 * @author HT
 */
public class QueryDeptPrivAction extends
		ActionBasic<QueryDeptPrivInputBean, QueryDeptPrivOutputBean> {
	@Inject
	private RsPublicService rsPublicService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private SvSrvService svSrvService;
	@Inject
	private RsOptDaoService rsOptDaoService;
	@Inject
	private DpDeptQueryDaoService dpDeptQueryDaoService;
	@Inject
	private DpDeptOptPrivDaoService dpDeptOptPrivDaoService;
	@Inject
	private DpDeptSqlPrivDaoService dpDeptSqlPrivDaoService;
	@Inject
	private DpDeptColPrivDaoService dpDeptColPrivDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 查询部门权限信息
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected QueryDeptPrivOutputBean doAction(QueryDeptPrivInputBean input) {
		logger.info("------QueryDeptPrivAction begin------");
		logger.debug("------dept_id=[{}]", input.getDept_id());

		QueryDeptPrivOutputBean output = new QueryDeptPrivOutputBean();
		// 验证部门ID非空
		String dept_id = input.getDept_id();
		Assert.assertNotEmpty(dept_id,QueryDeptPrivInputBean.DEPT_IDCN);
		
		// 查询部门资源权限
		List<RsPrivBean> rs_list = dpDeptQueryDaoService.queryRsPrivByDeptId(dept_id);
		output.setRs_list(rs_list);
		
		//查询所有操作信息列表
		List<RsOptInfo> opt_list=rsOptDaoService.listAllRsOpt();
		output.setOpt_list(opt_list);
		
		//查询部门操作权限列表
		List<DpDeptOptPrivInfo> opt_priv=dpDeptOptPrivDaoService.queryOptPrivByDept(dept_id);
		output.setOpt_priv(opt_priv);
		
		// 查询部门数据源权限
		List<SocPrivBean> soc_list = dpDeptQueryDaoService.querySocPrivByDeptId(dept_id);
		output.setSoc_list(soc_list);

		// 查询部门SQL权限
		List<DpDeptSqlPrivInfo> sql_list = dpDeptSqlPrivDaoService.querySqlPrivByDeptId(dept_id);
		output.setSql_list(sql_list);

		// 查询部门COL权限
		List<DpDeptColPrivInfo> col_list = dpDeptColPrivDaoService.queryColPrivByDeptId(dept_id);
		output.setCol_list(col_list);

		DpDeptInfo info = new DpDeptInfo();
		info.setDept_id(dept_id);
		DpDeptInfo dept = dpDeptDaoService.getInfoByKey(info);
		if (dept.getDept_level() != 1) {// 有上级
			String super_dept_id = dept.getSuper_dept_id();
			// 查询上级部门资源权限
			List<RsPrivBean> sup_rs_list = dpDeptQueryDaoService.queryRsPrivByDeptId(super_dept_id);
			
			output.setSup_rs_list(rsResDaoService.addModularRs(sup_rs_list));
			
			//查询呢上级部门操作权限
			List<DpDeptOptPrivInfo> sup_opt_priv=dpDeptOptPrivDaoService.queryOptPrivByDept(super_dept_id);
			output.setSup_opt_priv(sup_opt_priv);
			
			// 查询上级部门数据源权限
			List<SocPrivBean> sup_soc_list = dpDeptQueryDaoService.querySocPrivByDeptId(super_dept_id);
			output.setSup_soc_list(sup_soc_list);

		} else {// 无上级 查询所有权限
			// 查询所有资源
			List<RsPrivBean> sup_rs_list = rsPublicService.queryAllUnPublicRsPriv();
			output.setSup_rs_list(rsResDaoService.addModularRs(sup_rs_list));

			// 查询所有数据源
			List<SocPrivBean> sup_soc_list = dtSocService.queryAllSocPriv();
			output.setSup_soc_list(sup_soc_list);
		}

		logger.info("------QueryDeptPrivAction end------");
		return output;
	}

	/**
	 * Description: 写日志信息
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryDeptPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());// 将被查询部门编码记录日志
		return lgsvc.getLogTxt("查询部门权限信息", log_lst);
	}

}
