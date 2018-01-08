/**
 * Title: QueryAllUserInfosAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月10日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.us.bean.QueryAllUserInfosInputBean;
import com.wk.cd.system.us.bean.QueryAllUserInfosOutputBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 查询所有用户信息
 * @author HT
 */
public class QueryAllUserInfosAction extends ActionBasic<QueryAllUserInfosInputBean, QueryAllUserInfosOutputBean>{
	@Inject
	private UsUserDaoService usUserDaoService;

	/** 
	 * Description: 查询所有用户信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryAllUserInfosOutputBean doAction(
			QueryAllUserInfosInputBean input) {
		QueryAllUserInfosOutputBean output=new QueryAllUserInfosOutputBean();
		
		DBIterator<UsUserInfo> infos=usUserDaoService.queryAllUserInfos();
		List<UsUserInfo> user_list=new ArrayList<UsUserInfo>();
		try{
			while(infos.hasNext()){
				user_list.add(infos.next());
			}
		}finally{
			infos.close();
		}
		output.setUser_list(user_list);
		
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryAllUserInfosInputBean input) {
		// TODO Auto-generated method stub
		return null;
	}

}
