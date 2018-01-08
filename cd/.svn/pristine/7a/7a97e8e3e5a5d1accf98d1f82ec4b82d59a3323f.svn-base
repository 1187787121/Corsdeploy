/**
 * Title: ViewLicenseAction.java
 * File Description: 读取License信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年9月29日
 */
package com.wk.cd.common.action;

import com.wk.cd.common.bean.ViewLicenseInputBean;
import com.wk.cd.common.bean.ViewLicenseOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.GBKProperties;
import com.wk.util.JaDate;

/**
 * Class Description: 读取License信息
 * @author Xul
 */
public class ViewLicenseAction extends IViewActionBasic<ViewLicenseInputBean, ViewLicenseOutputBean> {
	
	@Inject private ActionLogPublicService lgsvc;
	@Inject private CommonService cmsvc;
	private static final Log logger = LogFactory.getLog();
	
	//定义最大提醒到期天数
	private static final int EXPIRE_DAYS = 15;
	
	/** 
	 * Description: 读取License信息
	 */
	public ViewLicenseOutputBean loadLicense(ViewLicenseInputBean input) {
		logger.info("***************loadLicense Begin**************");
		ViewLicenseOutputBean output = new ViewLicenseOutputBean();
		
		//清除license.properties配置文件缓存
		CfgTool.removeProCache("./license.properties");
		//读取license.properties配置文件
		GBKProperties pro = CfgTool.getProperties("./license.properties");
		logger.plog("get properties");
		String zh_name = pro.getProperty("zh_name");
		String name = pro.getProperty("name");
		JaDate expire_end_date = JaDate.valueOf(pro.getProperty("expire_end_date"));
		String license = pro.getProperty("license");
		String ip = pro.getProperty("ip");
		String version_msg = pro.getProperty("version_msg");
		String app_state = pro.getProperty("app_state");
		//读取注册标志，若为空，则返回1（未注册）
		String regist_flag =pro.getProperty("regist_flag", "1");
		//返回读取结果
		output.setZh_name(zh_name);
		output.setVersion_msg(version_msg);
		output.setApp_state(Boolean.valueOf(app_state));
		output.setName(name);
		output.setExpire_end_date(expire_end_date);
		output.setLicense(license);
		output.setIp(ip);
		output.setRegist_flag(Integer.valueOf(regist_flag));

		//计算剩余到期天数
		int expire_days = JaDate.getIntervalDays(expire_end_date, cmsvc.getCurrentDateTime().jaDateValue());
		//当达到最大提醒到期天数，则返回剩余天数
		if(expire_days <= EXPIRE_DAYS){
			output.setExpire_days(expire_days);
		}else{
			output.setExpire_days(-9999);
		}
		
		logger.info("***************loadLicense End**************");
		return output;
	}
}
