/**
 * Title: PageExtDprlListByDeptIdAction.java
 * File Description: ���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryExtDprlListByDeptIdInputBean;
import com.wk.cd.system.us.bean.QueryExtDprlListByDeptIdOutputBean;
import com.wk.cd.system.us.bean.UsExtDprlBean;
import com.wk.cd.system.us.dao.UsExtDprlDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description: ���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ
 * @author link
 */
public class QueryExtDprlListByDeptIdAction
		extends
		ActionBasic<QueryExtDprlListByDeptIdInputBean, QueryExtDprlListByDeptIdOutputBean> {

	@Inject
	private UsExtDprlDaoService daoService;
	@Inject
	private UsUserRoleDaoService usRoleDaoSrv;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected QueryExtDprlListByDeptIdOutputBean doAction(
			QueryExtDprlListByDeptIdInputBean input) {
		QueryExtDprlListByDeptIdOutputBean output = new QueryExtDprlListByDeptIdOutputBean();
		String[] dept_arr = input.getDept_arr();
		Assert.assertNotEmpty(dept_arr, "��������");
		List<UsExtDprlBean> dprl_bean_list = new ArrayList<UsExtDprlBean>();
		List<UsUserRoleInfo> us_roles = new ArrayList<UsUserRoleInfo>();
		for (String dept_id : dept_arr) {
			if (dept_id != null) {
				List<UsExtDprlBean> temp_dprl_bean_list = daoService
						.queryExtDprlListByDept(dept_id);
				dprl_bean_list.addAll(temp_dprl_bean_list);
			}
		}
		if(!Assert.isEmpty(dprl_bean_list)){
			for(UsExtDprlBean u : dprl_bean_list){
				us_roles.addAll(usRoleDaoSrv.queryUserRoleByDprlCode(u.getDprl_code()));
			}
		}
		output.setDprl_bean_list(dprl_bean_list);
		output.setUs_roles(us_roles);
		return output;
	}

	/**
	 * Description:���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ��־��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryExtDprlListByDeptIdInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		String[] dept_arr = input.getDept_arr();
		Assert.assertNotEmpty(dept_arr, "��������");
		lst_val.add(dept_arr.toString());
		return lgsvc.getLogTxt("���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ��־��Ϣ", lst_val);
	}

}
