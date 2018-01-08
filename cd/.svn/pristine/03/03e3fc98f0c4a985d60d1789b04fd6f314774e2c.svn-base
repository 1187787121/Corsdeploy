/**
 * Title: RequestActionBasic.java
 * File Description: 服务请求基类
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月12日
 */
package com.wk.cd.service;

import com.wk.cd.bean.RequestRootInputBean;
import com.wk.cd.bean.RequestRootOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.license.LicenseCheckService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.ch.service.ChChannelService;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.wk.lang.Inject;
import com.wk.lang.RuntimeBussinessException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.startup.License;
import com.wk.util.GBKProperties;

import java.io.File;

/**
 * Class Description: 服务请求基类
 * @author HT
 */
public abstract class RequestBasic<RIN extends RequestRootInputBean,ROUT extends RequestRootOutputBean> {
	@Inject
	private CommonService cmsrv;
	@Inject
	private ChChannelService chsvc;
	@Inject
	LicenseCheckService cksvc;
	
	private static final Log logger = LogFactory.getLog();
	
	@SuppressWarnings("unchecked")
	public ROUT run(RIN input) {
		logger.info("------RequestAction begin------");
		Assert.assertNotEmpty(input.getOrg_channel_code(),input.ORG_CHANNEL_CODECN);
		ROUT output = null;

		cksvc.checkLicense();
		
		ChChannelInfo chInfo=chsvc.getInfoByKey(input.getOrg_channel_code());
		if(Assert.isEmpty(chInfo)){
			throw new RecordNotFoundException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD",input.getOrg_channel_code());
		}else if(chInfo.getChannel_type()==CHANNEL_TYPE.PCWEB){
			logger.info("------WebChannelRequest channel_code=[{}]------",input.getOrg_channel_code());
			output = runActionBasic(input);
			output.setRequest_state(true);
			logger.debug("------WebChannelRequest end------");
		}else{
			logger.info("------OtherChannelRequest channel_code=[{}]------",input.getOrg_channel_code());
			try {
				output = runActionBasic(input);
				output.setRequest_state(true);
				logger.debug("------RequestAction success------");
			} catch (RuntimeBussinessException e) {
				logger.debug("------RequestAction RuntimeException------");
				cmsrv.getSession().rollbackAndResume();
				RequestRootOutputBean request_excpt = new RequestRootOutputBean();
				request_excpt.setRequest_state(false);
				request_excpt.setMessage_code(e.getErrorCode());
				request_excpt.setMessage_text(e.getMessage());
				output = (ROUT)request_excpt;
			}catch (Exception e){
				logger.debug("------RequestAction Exception------");
				logger.warn("Exectpion=[{}]\nTrace=[\n{}]",e.toString(),ExceptionUtils.getStackTrace(e));

				cmsrv.getSession().rollbackAndResume();
				RequestRootOutputBean request_excpt = new RequestRootOutputBean();
				request_excpt.setRequest_state(false);
				request_excpt.setMessage_code("APP_CORS_MANAGER_SYSTEM_ERROR");
				request_excpt.setMessage_text("系统错误");
				output = (ROUT)request_excpt;
			}
		}
		logger.info("------RequestAction end------");
		return output;
	}

	// 实现服务主程序实现
	protected abstract ROUT runActionBasic(RIN input);
}
