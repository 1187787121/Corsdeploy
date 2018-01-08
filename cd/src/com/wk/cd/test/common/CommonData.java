/**
 * Title: CommonData.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: playg
 * @version: 1.0
 * @date: 2017年11月22日
 */
package com.wk.cd.test.common;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description:
 * @author playg
 */
public class CommonData {
	private static Injector inject=Controller.getInstance().getInjector();
	private static CommonService cmsvc = inject.getBean(CommonService.class);
	
	/**
	 * 公共赋值
	 * @param input
	 */
	public static void commonInput(ActionRootInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("cm_RegistLicenseAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增项目信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
