/**
 * Title: QueryMsgInfoByWorkSeq.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月9日
 */
package com.wk.cd.system.mg.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.mg.bean.MgMsgRcBean;
import com.wk.cd.system.mg.bean.QueryMsgInfoInputBean;
import com.wk.cd.system.mg.bean.QueryMsgInfoOutputBean;
import com.wk.cd.system.mg.info.MgMsgInfo;
import com.wk.cd.system.mg.service.MgMsgService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;

/**
 * Class Description: 根据流水号查询消息详细信息
 * @author HT
 */
public class QueryMsgInfoAction extends ActionBasic<QueryMsgInfoInputBean, QueryMsgInfoOutputBean>{
	@Inject
	private MgMsgService mgMsgService;
	@Inject
	private UsUserDaoService usUserDaoService;
	/** 
	 * Description: 查看消息详细信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryMsgInfoOutputBean doAction(QueryMsgInfoInputBean input) {
		QueryMsgInfoOutputBean output=new QueryMsgInfoOutputBean();
		String work_seq=input.getWorkseq();
		String user_id=input.getOrg_user_id();
		Assert.assertNotEmpty(work_seq, QueryMsgInfoInputBean.WORKSEQCN);
		Assert.assertNotEmpty(user_id, QueryMsgInfoInputBean.ORG_USER_IDCN);
		
		MgMsgInfo msg_info=mgMsgService.getMgMsgInfoById(work_seq);
		
		UsUserInfo user_info=new UsUserInfo();
		user_info.setUser_id(msg_info.getCrt_user_id());
		
		Assert.assertNotEmpty(msg_info.getCrt_user_id(),MgMsgInfo.CRT_USER_IDCN);
		user_info=usUserDaoService.getInfoByKey(user_info);
		
		MgMsgRcBean rcBean=new MgMsgRcBean();
		rcBean.setWork_seq(msg_info.getWork_seq());
		rcBean.setMsg_title(msg_info.getMsg_title());
		rcBean.setMsg_text(msg_info.getMsg_text());
		rcBean.setMsg_type(msg_info.getMsg_type());
		rcBean.setFile_path(msg_info.getFile_path());
		rcBean.setFirst_bk_fname(msg_info.getFirst_bk_fname());
		rcBean.setSecd_bk_fname(msg_info.getSecd_bk_fname());
		rcBean.setThird_bk_fname(msg_info.getThird_bk_fname());
		rcBean.setCrt_user_id(msg_info.getCrt_user_id());
		rcBean.setCrt_user_name(user_info.getUser_cn_name());
		rcBean.setCrt_bk_date(msg_info.getCrt_bk_date());
		rcBean.setCrt_bk_time(msg_info.getCrt_bk_time());
		
		mgMsgService.updateRcFlag(work_seq,user_id);
		
		output.setMsg_info(rcBean);
		return output;
	}
	
	/**
	 * 
	 * Description: 关注消息
	 * @param input
	 * @return
	 */
	public QueryMsgInfoOutputBean updateAttentFlag(QueryMsgInfoInputBean input) {
		QueryMsgInfoOutputBean output=new QueryMsgInfoOutputBean();
		String work_seq=input.getWorkseq();
		String user_id=input.getOrg_user_id();
		YN_FLAG attent_flag=input.getAttent_yn_flag();
		Assert.assertNotEmpty(work_seq, QueryMsgInfoInputBean.WORKSEQCN);
		Assert.assertNotEmpty(user_id, QueryMsgInfoInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(attent_flag, QueryMsgInfoInputBean.ATTENT_YN_FLAGCN);
		mgMsgService.updateAttentFlag(attent_flag, work_seq, user_id);
		
		return output;
	}
	/** 
	 * Description: 写日志
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryMsgInfoInputBean input) {
		return null;
	}

}
