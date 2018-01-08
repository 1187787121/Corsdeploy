/**
 * Title: QueryUserTempPrivAction.java
 * File Description: �û���ʱ����Ȩ�޲�ѯ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-11
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dp.dao.DpDeptQueryDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.us.bean.QueryUserTempPrivInputBean;
import com.wk.cd.system.us.bean.QueryUserTempPrivOutputBean;
import com.wk.cd.system.us.bean.TempRsBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.lang.Inject;

/**
 * Class Description: �û���ʱ����Ȩ�޲�ѯ
 * @author link
 */
public class QueryUserTempPrivAction
		extends ActionBasic<QueryUserTempPrivInputBean, QueryUserTempPrivOutputBean> {

	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private UsUserRsPrivDaoService usUserRsPrivDaoService;
	@Inject
	private UsUserSocPrivDaoService usUserSocPrivDaoService;
	@Inject
	private UsUserSqlPrivDaoService usUserSqlPrivDaoService;
	@Inject
	private UsUserColPrivDaoService usUserColPrivDaoService;
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private DpDeptQueryDaoService dpDeptQueryDaoService;
	@Inject
	private UsUserDaoService checkUserService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: �û���ʱ����Ȩ�޲�ѯ
	 * @param input
	 * @return
	 */
	@Override
	protected QueryUserTempPrivOutputBean doAction(QueryUserTempPrivInputBean input) {
		QueryUserTempPrivOutputBean output = new QueryUserTempPrivOutputBean();
		
		String user_id=input.getUser_id();
		Assert.assertNotEmpty(input.getUser_id(),
				QueryUserTempPrivInputBean.USER_IDCN);
		checkUserService.checkExistUserIdExist(input.getUser_id());
		
		UsUserInfo info = new UsUserInfo();
		info.setUser_id(user_id);
		info = usUserDaoService.getInfoByKey(info);
		String super_dept_id = info.getBl_dept_id();
		
		List<RsPrivBean> modular_list=rsResDaoService.addModularRs(new ArrayList<RsPrivBean>());
		output.setModular_list(modular_list);
		
		// ��ѯ�ϼ�������ԴȨ��
		List<RsPrivBean> sup_rs_list = dpDeptQueryDaoService.queryRsPrivByDeptId(super_dept_id);
		output.setRs_infos(sup_rs_list);
		
		// ��ѯ�û���ԴȨ��
		List<RsPrivBean> rs_list = usGetUserPrivService.queryRealRsPrivByUserId(user_id);
		Map<String, RsPrivBean> usRsMap=new HashMap<String, RsPrivBean>();
		
		for(RsPrivBean us_rs_priv:rs_list){
			usRsMap.put(us_rs_priv.getRs_code(), us_rs_priv);
		}
		
		List<RsPrivBean> temp_rs_list=new ArrayList<RsPrivBean>();
		for(RsPrivBean dept_rs_priv:sup_rs_list){
			if(!usRsMap.containsKey(dept_rs_priv.getRs_code())){
				RsPrivBean bean=new RsPrivBean();
				bean.setRs_code(dept_rs_priv.getRs_code());
				bean.setRs_cn_name(dept_rs_priv.getRs_cn_name());
				bean.setSuper_rs_code(dept_rs_priv.getSuper_rs_code());
				bean.setRs_fun_type(dept_rs_priv.getRs_fun_type());
				bean.setOpen_type(dept_rs_priv.getOpen_type());
				bean.setRs_level(dept_rs_priv.getRs_level());
				temp_rs_list.add(bean);
			}
		}
		
		output.setRs_list(temp_rs_list);
		
		// ��ѯ�û�����ԴȨ��
		List<SocPrivBean> soc_list = usGetUserPrivService.getUserTualSocPriv(user_id);
		
		Map<String, SocPrivBean> usSocMap=new HashMap<String, SocPrivBean>();
		
		for(SocPrivBean us_soc_priv:soc_list){
			usSocMap.put(us_soc_priv.getSoc_name(), us_soc_priv);
		}

		// ��ѯ�ϼ���������ԴȨ��
		List<SocPrivBean> sup_soc_list = dpDeptQueryDaoService.querySocPrivByDeptId(super_dept_id);

		List<SocPrivBean> temp_soc_list=new ArrayList<SocPrivBean>();
		for(SocPrivBean dept_soc_priv:sup_soc_list){
			if(!usSocMap.containsKey(dept_soc_priv.getSoc_name())){
				temp_soc_list.add(dept_soc_priv);
			}
		}
		
		output.setSoc_list(temp_soc_list);
		
		//�û���ʱ��ԴȨ��
		List<TempRsBean> usrs_temp_priv=usGetUserPrivService.getTempUsUserRsPriv(user_id);
		output.setTemp_rs_list(usrs_temp_priv);
		
		//�û���ʱ����ԴȨ��
		List<UsUserSocPrivInfo> ussoc_temp_priv=usUserSocPrivDaoService.queryUserTempSocPriv(user_id);
		output.setTemp_soc_list(ussoc_temp_priv);
		
		//�û���ʱSQLȨ��
		List<UsUserSqlPrivInfo> ussql_temp_priv=usUserSqlPrivDaoService.queryUserTempSqlPriv(user_id);
		output.setTemp_sql_list(ussql_temp_priv);
		
		//�û���ʱCOlȨ��
		List<UsUserColPrivInfo> uscol_temp_priv=usUserColPrivDaoService.queryUserTempColPriv(user_id);
		output.setTemp_col_list(uscol_temp_priv);
		
		
		return output;
	}

	/**
	 * Description: �û�����Ȩ�޲�ѯ
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryUserTempPrivInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		return lgsvc.getLogTxt("�û���ʱȨ�޲�ѯ", lst_val);
	}
}
