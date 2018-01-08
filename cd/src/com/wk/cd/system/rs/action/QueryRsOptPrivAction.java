/**

 * Title: QueryRsOptPrivAction.java
 * File Description: ��ѯ�û���Դ����Ȩ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��5��15��
 */
package com.wk.cd.system.rs.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.bean.QueryRsOptPrivInputBean;
import com.wk.cd.system.rs.bean.QueryRsOptPrivOutputBean;
import com.wk.cd.system.rs.bean.RsOptPrivBean;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * @author HT
 */
public class QueryRsOptPrivAction extends ActionBasic<QueryRsOptPrivInputBean, QueryRsOptPrivOutputBean>{
	
	@Inject private RsPublicService rsOptService;
	
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();
	/** 
	 * @param input ��ѯ�û���Դ����Ȩ��
	 * @return 
	 */
	@Override
	protected QueryRsOptPrivOutputBean doAction(QueryRsOptPrivInputBean input) {
		logger.info("------QueryRsOptPrivAction begin------");
		logger.debug("-----login user_id=[{}]", input.getOrg_user_id());
		logger.debug("��Դ����:[{}]",input.getOrg_rs_code());
		QueryRsOptPrivOutputBean output=new QueryRsOptPrivOutputBean();
		String rs_code=input.getOrg_rs_code();
		String user_id=input.getOrg_user_id();
		Assert.assertNotEmpty(rs_code, QueryRsOptPrivInputBean.ORG_RS_CODECN);
		Assert.assertNotEmpty(user_id, QueryRsOptPrivInputBean.ORG_USER_IDCN);
		List<RsOptPrivBean> opt_list=rsOptService.getRsOptPriv(rs_code, user_id);
		
		output.setOpt_list(opt_list);
		
		logger.info("------QueryRsOptPrivAction end------");
		return output;
	}

	/** 
	 * @param input д��־��Ϣ
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryRsOptPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getOrg_rs_code());
		return lgsvc.getLogTxt("��ѯ�û���Դ����Ȩ��", log_lst);
	}

}
