/**
 * Title: QueryDprlInfoAction.java
 * File Description: ��ѯ���Ž�ɫ��ϸ��Ϣ
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryDprlInfoInputBean;
import com.wk.cd.system.us.bean.QueryDprlInfoOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsRoleDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsRoleInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯ���Ž�ɫ��ϸ��Ϣ
 * @author HT
 */
public class QueryDprlInfoAction extends ActionBasic<QueryDprlInfoInputBean, QueryDprlInfoOutputBean>{

	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private UsRoleDaoService usRoleDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ��ѯ���Ž�ɫ��ϸ��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryDprlInfoOutputBean doAction(QueryDprlInfoInputBean input) {
		logger.info("------QueryDprlInfoAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		QueryDprlInfoOutputBean output = new QueryDprlInfoOutputBean();
		
		String dprl_code=input.getDprl_code();
		Assert.assertNotEmpty(dprl_code,QueryDprlInfoInputBean.DPRL_CODECN);
		
		UsDeptRoleInfo dprl_info=new UsDeptRoleInfo();
		dprl_info.setDprl_code(dprl_code);
		dprl_info=usDeptRoleDaoService.getInfoByKey(dprl_info);
		
		DpDeptInfo dept_info=new DpDeptInfo();
		dept_info.setDept_id(dprl_info.getDept_id());
		dept_info=dpDeptDaoService.getInfoByKey(dept_info);
		
		UsRoleInfo role_info=new UsRoleInfo();
		role_info.setRole_code(dprl_info.getRole_code());
		role_info=usRoleDaoService.getInfoByKey(role_info);
		
		output.setDprl_code(dprl_code);
		output.setBk_expl(dprl_info.getBk_expl());
		output.setDept_id(dept_info.getDept_id());
		output.setDept_cn_name(dept_info.getDept_cn_name());
		output.setRole_code(dprl_info.getRole_code());
		output.setRole_cn_name(role_info.getRole_cn_name());
		
		logger.info("------QueryDprlInfoAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryDprlInfoInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("��ѯ���Ž�ɫ��ϸ��Ϣ", log_lst);
	}
}
