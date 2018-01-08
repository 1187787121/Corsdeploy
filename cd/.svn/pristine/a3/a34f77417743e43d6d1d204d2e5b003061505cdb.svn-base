/**
 * Title: AddUserTermAction.java
 * File Description: 新增用户接入终端
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月14日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.enu.TERM_TYPE;
import com.wk.cd.enu.USE_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.cd.system.us.bean.AddUserTermInputBean;
import com.wk.cd.system.us.bean.AddUserTermOutputBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserTermInfo;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增用户接入终端
 * @author HT
 */
public class AddUserTermAction extends ActionBasic<AddUserTermInputBean, AddUserTermOutputBean>{

	@Inject
	private TmTermDaoService tmTermDaoService;
	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	@Inject
	private UsUserDaoService userDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private UsCheckUserService usCheckUserService;
	@Inject
	private DpPublicService dpsvc;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 新增用户接入终端
	 * @param input
	 * @return 
	 */
	@Override
	protected AddUserTermOutputBean doAction(AddUserTermInputBean input) {
		logger.info("------AddUserTermAction begin------");
		logger.debug("------user_id=[{}]",input.getUser_id());
		
		AddUserTermOutputBean output = new AddUserTermOutputBean();
		String user_id=input.getUser_id();
		String term_no=input.getTerm_no();
		String channel_code=input.getChannel_code();
		
		Assert.assertNotEmpty(user_id, AddUserTermInputBean.USER_IDCN);
		Assert.assertNotEmpty(term_no, AddUserTermInputBean.TERM_NOCN);
		Assert.assertNotEmpty(channel_code, AddUserTermInputBean.CHANNEL_CODECN);
		
		if(!Assert.isEmpty(channel_code)){
			chChannelDaoService.checkChannelCodeExist(channel_code);
		}
		
		//检查终端号是否在终端表中满足规范
		ChChannelInfo chInfo =new ChChannelInfo();
		chInfo.setChannel_code(channel_code);
		chInfo=chChannelDaoService.getInfoByKey(chInfo);

		//所有绑定终端都必须满足终端规则管理，可以是全包含也可以IP段管理
		tmTermDaoService.checkTermTypeN(term_no);

		usUserTermDaoService.checkUserTermNoExist(user_id,term_no);
		
		UsUserInfo userInfo=new UsUserInfo();
		userInfo=userDaoService.getInfoByKey(user_id);
		
		UsUserTermInfo info=new UsUserTermInfo();
		info.setUser_id(user_id);
		info.setTerm_no(term_no);
		info.setChannel_code(channel_code);
	
		if(usCheckUserService.isUserManager(user_id)){//是否管理员
			if(userInfo.getLogin_bk_num()<0){//首次登陆-1
				info.setUse_state(USE_STATE.USED);
				usUserDaoService.updateLoginNumByUserId(0, user_id);
				output.setIs_used(true);
			}else{
				info.setUse_state(USE_STATE.UNUSE);
				output.setIs_used(false);
			}
			
		}else{
			info.setUse_state(USE_STATE.UNUSE);
			output.setIs_used(false);
		}
		info.setDept_id(userInfo.getBl_dept_id());
		info.setUser_cn_name(userInfo.getUser_cn_name());
		
		DpDeptInfo deptInfo=new DpDeptInfo();
		deptInfo.setDept_id(userInfo.getBl_dept_id());
		deptInfo=dpDeptDaoService.getInfoByKey(deptInfo);
		info.setDept_cn_name(deptInfo.getDept_cn_name());
		
		usUserTermDaoService.insertInfo(info);
		
		logger.info("------AddUserTermAction end------");
		return output;
	}
	
	@Override
	protected void chkInput(AddUserTermInputBean input) {
		Assert.assertNotEmpty(input.getOrg_user_id(), AddUserTermInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), AddUserTermInputBean.ORG_SRV_NAMECN);
		Assert.assertNotEmpty(input.getOrg_rs_code(), AddUserTermInputBean.ORG_RS_CODECN);
		Assert.assertNotEmpty(input.getSubmit_type(), AddUserTermInputBean.SUBMIT_TYPECN);
	}

	@Override
	protected void chkTerm(AddUserTermInputBean input) {
	}

	@Override
	protected void chkSQLPriv(AddUserTermInputBean input) {
	}

	@Override
	protected void chkDeptPriv(AddUserTermInputBean input) {
	}

	@Override
	protected void chkState(AddUserTermInputBean input){
	}

	@Override
	protected boolean isChk(AddUserTermInputBean input){
	    return false;
	}

	@Override
	protected boolean isAuth(AddUserTermInputBean input){
	    return false;
	}
	
	@Override
	protected boolean isLocAuth(AddUserTermInputBean input){
		return false;

	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddUserTermInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		log_lst.add(input.getTerm_no());
		log_lst.add(input.getChannel_code());
		return lgsvc.getLogTxt("新增用户接入终端", log_lst);
	}

}
