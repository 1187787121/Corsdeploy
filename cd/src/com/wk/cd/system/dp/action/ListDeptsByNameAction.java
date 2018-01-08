/**
 * Title: ListDeptByNameAction.java
 * File Description: ���ݲ�������ģ����ҳ��ѯ������Ϣ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.bean.PageDeptsByNameInputBean;
import com.wk.cd.system.dp.bean.PageDeptsByNameOutputBean;
import com.wk.cd.system.dp.dao.DeptQuery;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: ���ݲ�������ģ����ҳ��ѯ������Ϣ
 * @author xuy
 */
public class ListDeptsByNameAction extends ActionBasic<PageDeptsByNameInputBean, PageDeptsByNameOutputBean> {

	@Inject 
	private ActionLogPublicService lgsvc;
	@Inject 
	private DeptQuery deptQuery;
	@Inject
	private UsUserCombineDaoService combineDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	/**
	 * 
	 * Description: ���ݲ�������ģ����ҳ��ѯ������Ϣ
	 * @param input ����ӿ�
	 * @return
	 */
	@Override
	protected PageDeptsByNameOutputBean doAction(PageDeptsByNameInputBean input) {
		PageDeptsByNameOutputBean output=new PageDeptsByNameOutputBean();
		//DBIterator<DeptExtendsBean> infos=deptQuery.queryAllDeptInfo();
		DBIterator<DeptExtendsBean> infos=null;
		List<String> dept_list = null;
		//��ѯ��ǰ�û���ɫ
		List<Integer> user_role_type_list = combineDaoService.queryRoleTypeByUserId(input.getOrg_user_id());
		//����ǳ�������Ա������Ϣ����Ա�������������в���
		if(!user_role_type_list.contains(1) && !user_role_type_list.contains(2)){
			String[] str =new String[]{input.getOrg_dept_id()};
			dept_list = dpDeptDaoService.queryDeptIdByKey(str);
			infos = dpDeptDaoService.queryAllDeptInfo(listToString(dept_list));
			
		}else{
			infos = deptQuery.queryAllDeptInfo();
		}
		List<DeptExtendsBean> dept_infos=new ArrayList<DeptExtendsBean>();
		try{
			while(infos.hasNext()){
				dept_infos.add(infos.next());
			}
		}finally{
			infos.close();
		}
		output.setDeptInfos(dept_infos);
		return output;
	}

	/**
	 * ��������б�ת��Ϊ�ַ����������б���Ϊa b c��ת������Ϊ('a','b','c')
	 * @param list �����б�
	 * @return �ַ���
	 */
	private String listToString(List<String> list) {
		String str = "";
		// ����Ĳ��Ž�ɫ�б���ϢΪ�ձ���
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "�����б���Ϣ");
		}
		for (String s : list) {
			str += "'" + s + "',";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return str;
	}
	/**
	 * Description:����������־��Ϣ
	 * @param input ����ӿ�
	 * @return
	 */
	@Override
	protected String getLogTxt(PageDeptsByNameInputBean input) {
		List<String> dept_val = new ArrayList<String>();
		dept_val.add(input.getDept_cn_name());//������ѯ���ŵ����Ƽ�¼��־
		return lgsvc.getLogTxt("��ѯ������Ϣ", dept_val);
	}

}
