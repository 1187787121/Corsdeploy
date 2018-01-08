/**
 * Title: UpdateSocAction.java
 * File Description: 实现更新数据源信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.WasParamsIsExistEmptyException;
import com.wk.cd.exc.WasParamsPassIsEmptyException;
import com.wk.cd.exc.WasParamsUserNameIsEmptyException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.bean.AddSocInputBean;
import com.wk.cd.system.dt.bean.SocParamsBean;
import com.wk.cd.system.dt.bean.UpdateSocInputBean;
import com.wk.cd.system.dt.bean.UpdateSocOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:实现更新数据源信息
 * @author link
 */
public class UpdateSocAction
		extends ActionBasic<UpdateSocInputBean, UpdateSocOutputBean> {

	@Inject
	private DtSourceDaoService daoService;
	@Inject
	private DtCheckSocExistService daoCheckService;
	@Inject
	private DtSocService getSocService;
	@Inject
	private Log logger = LogFactory.getLog();
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: 实现更新数据源信息
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateSocOutputBean doAction(UpdateSocInputBean input) {
		UpdateSocOutputBean output = new UpdateSocOutputBean();
		String soc_name = input.getSoc_name();
		// 必填项非空检查
		Assert.assertNotEmpty(soc_name, UpdateSocInputBean.SOC_NAMECN);
		Assert.assertNotEmpty(input.getSoc_port(), UpdateSocInputBean.SOC_PORTCN);
		Assert.assertNotEmpty(input.getProtocol_type(), UpdateSocInputBean.PROTOCOL_TYPECN);
		/*
		 * Assert.assertNotEmpty(input.getKey_remote_passwd(),
		 * AddSocInputBean.KEY_REMOTE_PASSWDCN);
		 */
		Assert.assertNotEmpty(input.getBk_timeout(), UpdateSocInputBean.BK_TIMEOUTCN);
		Assert.assertNotEmpty(input.getCvt_type(), UpdateSocInputBean.CVT_TYPECN);
		PROTOCOL_TYPE protocol_type = input.getProtocol_type();
		// 根据协议类型校验数用户根路径非空
		if (PROTOCOL_TYPE.PLT_FTP.equals(protocol_type) || PROTOCOL_TYPE.SFTP.equals(protocol_type) || PROTOCOL_TYPE.TELNET.equals(protocol_type) || PROTOCOL_TYPE.SSH.equals(protocol_type)) {
			Assert.assertNotEmpty(input.getUser_root_path(), UpdateSocInputBean.USER_ROOT_PATHCN);
		}
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		info = daoService.getInfoByKeyForUpdate(info);
		info.setSoc_ip(input.getSoc_ip());
		info.setSoc_port(input.getSoc_port());
		info.setProtocol_type(protocol_type);
		info.setRemote_uname(input.getRemote_uname());

		info.setEnvironment_variables(input.getEnvironment_variables());
		String encoding_type = input.getEncoding_type();
		info.setEncoding_type(encoding_type);
		String key = "";
		String en_key = "";
		String en_passwd = "";
		if (!Assert.isEmpty(input.getKey_remote_passwd())) {
			// 改密钥必须改密码
			key = input.getKey_remote_passwd();
			en_key = DESUtil.docryptAllowReverse(true, null, key);
			Assert.assertNotEmpty(input.getRemote_passwd(), UpdateSocInputBean.REMOTE_PASSWDCN);
			en_passwd = DESUtil.docryptAllowReverse(true, key, input.getRemote_passwd());

		} else if (!Assert.isEmpty(input.getRemote_passwd())) {
			// 只改密码
			en_key = info.getKey_remote_passwd();
			key = DESUtil.docryptAllowReverse(false, null, en_key);
			en_passwd = DESUtil.docryptAllowReverse(true, key, input.getRemote_passwd());
		}
		info.setRemote_passwd(en_passwd);
		info.setKey_remote_passwd(en_key);
		info.setUser_root_path(input.getUser_root_path());
		info.setBk_timeout(input.getBk_timeout());
		info.setJdbc_drv(input.getJdbc_drv());
		info.setJdbc_url(input.getJdbc_url());
		info.setJdbc_schema(input.getJdbc_schema());
		info.setCvt_type(input.getCvt_type());
		info.setFtp_local_script(input.getFtp_local_script());
		info.setCvt_local_script(input.getCvt_local_script());
		info.setSoc_domain(input.getSoc_domain());
		info.setSoc_bk_desc(input.getSoc_bk_desc());
		logger.info("将要更改信息的数据源是：[{}]", soc_name);
		// 校验用户根路径是否存在
		daoCheckService.checkUserRootPathExist(info, input.getWork_seq());
		daoService.updateBySocName(info);
		return output;
	}

	/**
	 * Description: 实现更新数据源信息日志信息
	 * @param input
	 * @return
	 */

	@Override
	protected String getLogTxt(UpdateSocInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSoc_name());
		lst_val.add(String.valueOf(input.getSoc_port()));
		lst_val.add(input.getProtocol_type().getCname());
		lst_val.add(input.getKey_remote_passwd());
		lst_val.add(String.valueOf(input.getBk_timeout()));
		return lgsvc.getLogTxt("更改数据源日志信息", lst_val);
	}

}
