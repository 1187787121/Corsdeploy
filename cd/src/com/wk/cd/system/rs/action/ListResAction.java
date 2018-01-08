/**
 * Title: ListResAction.java
 * File Description: ����������ܵ��ȫ����Ϣ��Ϣ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.system.rs.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.bean.ListResInputBean;
import com.wk.cd.system.rs.bean.ListResOutputBean;
import com.wk.cd.system.rs.bean.ResExtendsBean;
import com.wk.cd.system.rs.dao.ResQuery;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: ����������ܵ��ȫ����Ϣ��Ϣ
 * @author xuy
 */
public class ListResAction extends ActionBasic<ListResInputBean, ListResOutputBean> {
	@Inject private RsResDaoService resDaoService;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private ResQuery resQuery;
	/**
	 * 
	 * Description: ����������ܵ��ȫ����Ϣ��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected ListResOutputBean doAction(ListResInputBean input) {
		ListResOutputBean output=new ListResOutputBean();
		List<ResExtendsBean> res_infos=new ArrayList<ResExtendsBean>();
		//����Ϊ��ʱ����ѯȫ����Ϣ
		
		if(input.getRs_code()==null){
			DBIterator<ResExtendsBean> infos=resQuery.queryAllResInfo();
			try{
				while(infos.hasNext()){
					res_infos.add(infos.next());
				}
			}finally{
				infos.close();
			}
//			//���ؼ�¼����
//			int all_recd=resDaoService.countInfosByNullAllRecd();
//			output.setAll_recd(all_recd);
//			res_infos=resQuery.queryAllResInfo();
		}else{
			//������Դ����ʱ����ѯ��Ӧ��Ϣ
			ResExtendsBean info=new ResExtendsBean();
			info=resQuery.queryResInfoByCode(input.getRs_code(),input.getRs_code());
			res_infos.add(info);
		}
     		output.setRes_infos(res_infos);
			return output;
		
		
	}
	/**
	 * Description:����������־��Ϣ
	 * @param input ����ӿ�
	 * @return
	 */
	@Override
	protected String getLogTxt(ListResInputBean input) {
		List<String> res_val = new ArrayList<String>();
		//���벻Ϊ��ʱ
		if(input.getRs_code()!=null){
			res_val.add(input.getRs_code());
			return lgsvc.getLogTxt("��ѯ��Դ��Ϣ", res_val);
		}
		//����Ϊ��ʱ
			res_val.add("ȫ����Դ");
			return lgsvc.getLogTxt("��ѯ��Դ��Ϣ", res_val);
		
		
		
	}

}
