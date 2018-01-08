/**
 * Title: ListResAction.java
 * File Description: 浏览单个功能点或全部信息信息
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
 * Class Description: 浏览单个功能点或全部信息信息
 * @author xuy
 */
public class ListResAction extends ActionBasic<ListResInputBean, ListResOutputBean> {
	@Inject private RsResDaoService resDaoService;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private ResQuery resQuery;
	/**
	 * 
	 * Description: 浏览单个功能点或全部信息信息
	 * @param input
	 * @return
	 */
	@Override
	protected ListResOutputBean doAction(ListResInputBean input) {
		ListResOutputBean output=new ListResOutputBean();
		List<ResExtendsBean> res_infos=new ArrayList<ResExtendsBean>();
		//输入为空时，查询全部信息
		
		if(input.getRs_code()==null){
			DBIterator<ResExtendsBean> infos=resQuery.queryAllResInfo();
			try{
				while(infos.hasNext()){
					res_infos.add(infos.next());
				}
			}finally{
				infos.close();
			}
//			//返回记录总数
//			int all_recd=resDaoService.countInfosByNullAllRecd();
//			output.setAll_recd(all_recd);
//			res_infos=resQuery.queryAllResInfo();
		}else{
			//输入资源编码时，查询相应信息
			ResExtendsBean info=new ResExtendsBean();
			info=resQuery.queryResInfoByCode(input.getRs_code(),input.getRs_code());
			res_infos.add(info);
		}
     		output.setRes_infos(res_infos);
			return output;
		
		
	}
	/**
	 * Description:新增部门日志信息
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected String getLogTxt(ListResInputBean input) {
		List<String> res_val = new ArrayList<String>();
		//输入不为空时
		if(input.getRs_code()!=null){
			res_val.add(input.getRs_code());
			return lgsvc.getLogTxt("查询资源信息", res_val);
		}
		//输入为空时
			res_val.add("全部资源");
			return lgsvc.getLogTxt("查询资源信息", res_val);
		
		
		
	}

}
