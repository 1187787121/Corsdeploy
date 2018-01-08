/**
 * Title: AddMessAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月9日
 */
package com.wk.cd.system.mg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.MessSendUserCanNotNullException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.mg.bean.AddMessInputBean;
import com.wk.cd.system.mg.bean.AddMessOutputBean;
import com.wk.cd.system.mg.info.MgMsgInfo;
import com.wk.cd.system.mg.info.MgMsgUserInfo;
import com.wk.cd.system.mg.service.MgMsgService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 新增消息
 * @author HT
 */
public class AddMessAction
		extends ActionBasic<AddMessInputBean, AddMessOutputBean> {
	@Inject
	private GenNoService genNoService;
	@Inject
	private MgMsgService mgMsgService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	/**
	 * Description: 新增消息
	 * @param input
	 * @return
	 */
	@Override
	protected AddMessOutputBean doAction(AddMessInputBean input) {
		AddMessOutputBean output = new AddMessOutputBean();
		Assert.assertNotEmpty(input.getMsg_title(),
				AddMessInputBean.MSG_TITLECN);
		Assert.assertNotEmpty(input.getMsg_text(), AddMessInputBean.MSG_TEXTCN);
		Assert.assertNotEmpty(input.getMsg_type(), AddMessInputBean.MSG_TYPECN);

		YN_FLAG send_all_flag = input.getAll_yn_flag();
		Assert.assertNotEmpty(send_all_flag, AddMessInputBean.ALL_YN_FLAFCN);

		String work_seq = genNoService.getWorkSeq(input.getDtbs_bk_date(), 
				input.getServer_name(), input.getServer_port());
		String file_path = input.getFile_path();
		
		MgMsgInfo mgMsgInfo = new MgMsgInfo();
		mgMsgInfo.setWork_seq(work_seq);
		mgMsgInfo.setMsg_title(input.getMsg_title());
		mgMsgInfo.setMsg_text(input.getMsg_text());
		mgMsgInfo.setMsg_type(input.getMsg_type());
		mgMsgInfo.setFile_path(file_path);
		mgMsgInfo.setFirst_bk_fname(input.getFirst_bk_fname());
		mgMsgInfo.setSecd_bk_fname(input.getSecd_bk_fname());
		mgMsgInfo.setThird_bk_fname(input.getThird_bk_fname());
		mgMsgInfo.setCrt_user_id(input.getOrg_user_id());
		mgMsgInfo.setCrt_bk_date(input.getDtbs_bk_date());
		mgMsgInfo.setCrt_bk_time(input.getDtbs_bk_time());
		mgMsgInfo.setDel_yn_flag(YN_FLAG.NO);
		mgMsgInfo.setRcd_state(RCD_STATE.NORMAL);
		mgMsgService.insertMgMsgInfo(mgMsgInfo);

		List<MgMsgUserInfo> mgMsgUserInfos = new ArrayList<MgMsgUserInfo>();
		if (send_all_flag == YN_FLAG.YES) {
			DBIterator<String> user_iters = usUserDaoService.iteratorAllUser();
			try {
				while (user_iters.hasNext()) {
					String user_id = user_iters.next();
					MgMsgUserInfo mgMsgUserInfo = new MgMsgUserInfo();
					mgMsgUserInfo.setWork_seq(work_seq);
					mgMsgUserInfo.setRc_user_id(user_id);
					mgMsgUserInfo.setRc_flag(RC_FLAG.NO);
					mgMsgUserInfo.setAttent_yn_flag(YN_FLAG.NO);
					mgMsgUserInfo.setRc_bk_date(input.getDtbs_bk_date());
					mgMsgUserInfo.setRc_bk_time(input.getDtbs_bk_time());
					mgMsgUserInfo.setRcd_state(RCD_STATE.NORMAL);

					mgMsgUserInfos.add(mgMsgUserInfo);
				}
			} finally {
				user_iters.close();
			}
		} else {
			String[] dept_arr = input.getDept_arr();
			String[] user_arr = input.getUser_arr();

			if (Assert.isEmpty(dept_arr) && Assert.isEmpty(user_arr)) {
				throw new MessSendUserCanNotNullException();
			}
			if (!Assert.isEmpty(dept_arr)) {
				for (int i = 0; i < dept_arr.length; i++) {
					String dept_id = dept_arr[i];
					DBIterator<String> user_iterators = usUserDaoService
							.iteratorUserByDeptId(dept_id);
					try {
						while (user_iterators.hasNext()) {
							String user_id = user_iterators.next();
							MgMsgUserInfo mgMsgUserInfo = new MgMsgUserInfo();
							mgMsgUserInfo.setWork_seq(work_seq);
							mgMsgUserInfo.setRc_user_id(user_id);
							mgMsgUserInfo.setRc_flag(RC_FLAG.NO);
							mgMsgUserInfo.setAttent_yn_flag(YN_FLAG.NO);
							mgMsgUserInfo
									.setRc_bk_date(input.getDtbs_bk_date());
							mgMsgUserInfo
									.setRc_bk_time(input.getDtbs_bk_time());
							mgMsgUserInfo.setRcd_state(RCD_STATE.NORMAL);

							mgMsgUserInfos.add(mgMsgUserInfo);
						}
					} finally {
						user_iterators.close();
					}
				}
			}

			if (!Assert.isEmpty(user_arr)) {
				for (int i = 0; i < user_arr.length; i++) {
					String user_id = user_arr[i];
					MgMsgUserInfo mgMsgUserInfo = new MgMsgUserInfo();
					mgMsgUserInfo.setWork_seq(work_seq);
					mgMsgUserInfo.setRc_user_id(user_id);
					mgMsgUserInfo.setRc_flag(RC_FLAG.NO);
					mgMsgUserInfo.setAttent_yn_flag(YN_FLAG.NO);
					mgMsgUserInfo.setRc_bk_date(input.getDtbs_bk_date());
					mgMsgUserInfo.setRc_bk_time(input.getDtbs_bk_time());
					mgMsgUserInfo.setRcd_state(RCD_STATE.NORMAL);

					mgMsgUserInfos.add(mgMsgUserInfo);
				}
			}
		}
		mgMsgService.insertMgMsgInfoList(mgMsgUserInfos);

		return output;
	}

	/**
	 * Description:写日志
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddMessInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getMsg_title());
		return lgsvc.getLogTxt("发送消息", log_lst);
	}

}
