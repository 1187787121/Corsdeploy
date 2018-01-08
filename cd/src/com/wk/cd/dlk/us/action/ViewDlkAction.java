/**
 * Title: QueryLoginAction.java
 * File Description: ��ѯDlk�û���¼��Ϣ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016��10��25��
 */
package com.wk.cd.dlk.us.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.cd.dlk.dp.service.OrganizationService;
import com.wk.cd.dlk.us.bean.ViewDlkInput;
import com.wk.cd.dlk.us.bean.ViewDlkOutput;
import com.wk.cd.dlk.us.info.IgUserInfo;
import com.wk.cd.dlk.us.service.IgUserService;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯDlk�û���¼��Ϣ
 * @author HT
 */
public class ViewDlkAction extends IViewActionBasic<ViewDlkInput, ViewDlkOutput>{
	
	@Inject
	private IgUserService igUserService;
	@Inject
	private OrganizationService organizationService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ��ѯDlk�û���¼��Ϣ
	 * @param input
	 * @return 
	 */
	public ViewDlkOutput queryDlkLogin(ViewDlkInput input) {
		ViewDlkOutput output = new ViewDlkOutput();
		String userid = input.getUserid();
		Assert.assertNotEmpty(userid,ViewDlkInput.USERIDCN);
		
		IgUserInfo userInfo = igUserService.getIgUserInfoByKey(userid);
		OrganizationInfo orgInfo = organizationService.getOrganizationInfoByKey(userInfo.getOrgid());
		
		output.setUserid(userid);
		output.setOrgid(String.valueOf(orgInfo.getOrgid()));
		output.setOrgname(userInfo.getOrgname());
		output.setUsername(userInfo.getUsername());
		output.setTerm_no(input.getRemote_ip());
		return output;
	}
}
