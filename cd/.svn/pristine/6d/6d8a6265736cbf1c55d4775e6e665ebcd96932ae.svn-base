/**
 * Title: RegistLicenseAction.java
 * File Description: 注册license信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年10月12日
 */
package com.wk.cd.common.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.bean.RegistLicenseInputBean;
import com.wk.cd.common.bean.RegistLicenseOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.InvalidLicenseException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.GBKProperties;
import com.wk.util.JaDate;

/**
 * Class Description: 注册license信息
 * @author Xul
 */
public class RegistLicenseAction extends ActionBasic<RegistLicenseInputBean, RegistLicenseOutputBean>{

	private static final String FILE_PATH = CfgTool.getProjectRootPath()+"/config/license.properties";
	@Inject private ActionLogPublicService lgsvc;
	@Inject private CommonService cmsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 注册license信息
	 * @param input
	 * @return 
	 */
	@Override
	protected RegistLicenseOutputBean doAction(RegistLicenseInputBean input) {
		logger.info("***************RegistLicenseAction Begin**************");
		RegistLicenseOutputBean output = new RegistLicenseOutputBean();
		
		//非空校验
		String name = input.getName();
		String zh_name = input.getZh_name();
		JaDate expire_end_date = input.getExpire_end_date();
		String license = input.getLicense();
		String ip = input.getIp();
		Assert.assertNotEmpty(name, RegistLicenseInputBean.NAMECN);
		Assert.assertNotEmpty(zh_name, RegistLicenseInputBean.ZH_NAMECN);
		Assert.assertNotEmpty(expire_end_date, RegistLicenseInputBean.EXPIRE_END_DATECN);
		Assert.assertNotEmpty(license, RegistLicenseInputBean.LICENSECN);
		Assert.assertNotEmpty(ip, RegistLicenseInputBean.IPCN);
		
		//获取配置文件全路径
		logger.debug("license.properties配置文件全路径为[{}]", FILE_PATH);
		File config = new File(FILE_PATH);
		if(!config.exists()){
			throw new FileNotExistException().addScene("FILE", "license.properties");
		}

		GBKProperties prop = new GBKProperties();
		prop.put("license", license);
		prop.put("expire_end_date", expire_end_date.toString());
		prop.put("zh_name", zh_name);
		prop.put("name", name);
		prop.put("ip", ip);
		prop.put("regist_flag", "2");
		if(!cmsvc.checkLicense(prop, license)){
			throw new InvalidLicenseException();
		}

		save(prop);
		
		logger.info("***************RegistLicenseAction End**************");
		return output;
	}

	/**
	 * Description: 注册license信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(RegistLicenseInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("注册license信息", lst_val);
	}

	private void save(GBKProperties prop){
		final String tf = CfgTool.getProjectRootPath() + "/config/license.properties";
		CfgTool.saveProperties(prop, tf);
	}
}
