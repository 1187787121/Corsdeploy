/**
 * Title: PageMessageAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月7日
 */
package com.wk.cd.system.mg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.mg.bean.MgMsgRcBean;
import com.wk.cd.system.mg.bean.PageMessageInputBean;
import com.wk.cd.system.mg.bean.PageMessageOutputBean;
import com.wk.cd.system.mg.info.MgMsgInfo;
import com.wk.cd.system.mg.service.MgMsgService;
import com.wk.lang.Inject;

/**
 * Class Description: 分页查询消息信息
 * @author HT
 */
public class PageMessageAction
		extends ActionBasic<PageMessageInputBean, PageMessageOutputBean> {
	@Inject
	private MgMsgService msgService;

	/**
	 * Description: 根据消息类型和接收状态查询消息列表
	 * @param input
	 * @return
	 */
	@Override
	protected PageMessageOutputBean doAction(PageMessageInputBean input) {
		PageMessageOutputBean output = new PageMessageOutputBean();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		String user_id = input.getOrg_user_id();
		String msg_title = input.getMsg_title();
		MSG_TYPE msg_type = input.getMsg_type();
		RC_FLAG rc_flag = input.getRc_flag();
		Assert.assertNotEmpty(user_id, PageMessageInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(start_recd, PageMessageInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageMessageInputBean.LIMIT_RECDCN);

		List<MgMsgRcBean> msg_list = msgService.pageMsgRcBeansByRcAndType(
				user_id, rc_flag, msg_type, msg_title, start_recd, limit_recd);
		int count = msgService.countMessageByRcAndType(user_id, rc_flag,
				msg_type, msg_title);
		output.setAll_recd(count);
		output.setMsg_list(msg_list);
		return output;
	}

	/**
	 * 
	 * Description: 查询已发送消息信息
	 * @param input
	 * @return
	 */
	public PageMessageOutputBean pageSendMessage(PageMessageInputBean input) {
		PageMessageOutputBean output = new PageMessageOutputBean();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		String user_id = input.getOrg_user_id();
		Assert.assertNotEmpty(user_id, PageMessageInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(start_recd, PageMessageInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageMessageInputBean.LIMIT_RECDCN);

		List<MgMsgInfo> msg_list = msgService.pageMgMsgInfosByUser(user_id,
				start_recd, limit_recd);
		List<MgMsgRcBean> mgMsgList = new ArrayList<MgMsgRcBean>();
		for (MgMsgInfo mgMsgInfo : msg_list) {
			MgMsgRcBean mgMsgRcBean = new MgMsgRcBean();
			mgMsgRcBean.setWork_seq(mgMsgInfo.getWork_seq());
			mgMsgRcBean.setMsg_title(mgMsgInfo.getMsg_title());
			mgMsgRcBean.setMsg_type(mgMsgInfo.getMsg_type());
			mgMsgRcBean.setFile_path(mgMsgInfo.getFile_path());
			mgMsgRcBean.setFirst_bk_fname(mgMsgInfo.getFirst_bk_fname());
			mgMsgRcBean.setSecd_bk_fname(mgMsgInfo.getSecd_bk_fname());
			mgMsgRcBean.setThird_bk_fname(mgMsgInfo.getThird_bk_fname());
			mgMsgRcBean.setCrt_user_id(mgMsgInfo.getCrt_user_id());
			mgMsgRcBean.setCrt_bk_date(mgMsgInfo.getCrt_bk_date());
			mgMsgRcBean.setCrt_bk_time(mgMsgInfo.getCrt_bk_time());

			mgMsgList.add(mgMsgRcBean);
		}
		int count = msgService.countSendMessage(user_id);
		output.setAll_recd(count);
		output.setMsg_list(mgMsgList);
		return output;
	}

	/**
	 * 
	 * Description: 查询关注消息信息
	 * @param input
	 * @return
	 */
	public PageMessageOutputBean pageAttentMessage(PageMessageInputBean input) {
		PageMessageOutputBean output = new PageMessageOutputBean();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		String user_id = input.getOrg_user_id();
		MSG_TYPE msg_type=input.getMsg_type();
		Assert.assertNotEmpty(user_id, PageMessageInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(start_recd, PageMessageInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageMessageInputBean.LIMIT_RECDCN);

		List<MgMsgRcBean> msg_list = msgService.pageMsgRcBeansByAttent(user_id,
				YN_FLAG.YES, msg_type, start_recd, limit_recd);
		int count = msgService.countAttentMessage(user_id, YN_FLAG.YES,msg_type);
		output.setAll_recd(count);
		output.setMsg_list(msg_list);
		return output;
	}

	/**
	 * Description: 写日志
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageMessageInputBean input) {
		return null;
	}

}
