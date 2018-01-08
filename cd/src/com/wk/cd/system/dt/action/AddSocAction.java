/**
 * Title: AddSocAction.java
 * File Description: 添加数据源功能
 *
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.bean.AddSocInputBean;
import com.wk.cd.system.dt.bean.AddSocOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;

/**
 * Class Description: 添加数据源功能
 *
 * @author link
 */
public class AddSocAction
        extends ActionBasic<AddSocInputBean, AddSocOutputBean> {

    @Inject
    private DtSourceDaoService daoService;
    @Inject
    private DtCheckSocExistService daoCheckService;
    @Inject
    private ActionLogPublicService lgsvc;

	/*
     * @Inject private DpPublicService dpService;
	 */

    /**
     * Description: 添加数据源功能
     *
     * @param input
     * @return
     */
    @Override
    protected AddSocOutputBean doAction(AddSocInputBean input) {
        AddSocOutputBean output = new AddSocOutputBean();
        DtSourceInfo info = new DtSourceInfo();
        // 必填项非空检查
        Assert.assertNotEmpty(input.getSoc_name(), AddSocInputBean.SOC_NAMECN);
        Assert.assertNotEmpty(input.getProtocol_type(), AddSocInputBean.PROTOCOL_TYPECN);
        Assert.assertNotEmpty(input.getRemote_passwd(), AddSocInputBean.REMOTE_PASSWDCN);
        Assert.assertNotEmpty(input.getRemote_uname(), AddSocInputBean.REMOTE_UNAMECN);
        Assert.assertNotEmpty(input.getSoc_ip(), AddSocInputBean.SOC_IPCN);
        PROTOCOL_TYPE protocol_type = input.getProtocol_type();
        // 根据协议类型校验数用户根路径非空
        if (PROTOCOL_TYPE.PLT_FTP.equals(protocol_type) || PROTOCOL_TYPE.SFTP.equals(protocol_type) || PROTOCOL_TYPE.TELNET.equals(protocol_type) || PROTOCOL_TYPE.SSH.equals(protocol_type)) {
            Assert.assertNotEmpty(input.getUser_root_path(), AddSocInputBean.USER_ROOT_PATHCN);
        }
        /*
         * Assert.assertNotEmpty(input.getKey_remote_passwd(),
		 * AddSocInputBean.KEY_REMOTE_PASSWDCN);
		 */
        if (input.getSoc_port() <= 0) {
            throw new DataErrorException().addScene("INPUT", AddSocInputBean.SOC_PORTCN + "[" + input.getSoc_port() + "]");
        }
        // Assert.assertNotEmpty(input.getCvt_type(),
        // AddSocInputBean.CVT_TYPECN);
        info.setSoc_name(input.getSoc_name());
        info.setSoc_ip(input.getSoc_ip());
        info.setSoc_port(input.getSoc_port());
        info.setProtocol_type(protocol_type);
        info.setRemote_uname(input.getRemote_uname());
        String encoding_type = input.getEncoding_type();
        if (!Assert.isEmpty(encoding_type)) {
            info.setEncoding_type(encoding_type);
        }
        String key;
        if (Assert.isEmpty(input.getKey_remote_passwd())) {
            key = DESUtil.getDef16Key();
        } else {
            key = input.getKey_remote_passwd();
        }
        String en_passwd = new String();
        String en_key = new String();
        // 对输入密码、密钥进行加密
        en_passwd = DESUtil.docryptAllowReverse(true, key, input.getRemote_passwd());
        en_key = DESUtil.docryptAllowReverse(true, null, key);
        info.setRemote_passwd(en_passwd);
        info.setKey_remote_passwd(en_key);
        info.setBk_timeout(input.getBk_timeout());
        info.setJdbc_drv(input.getJdbc_drv());
        info.setJdbc_url(input.getJdbc_url());
        info.setJdbc_schema(input.getJdbc_schema());
        if (!Assert.isEmpty(input.getCvt_type())) {
            info.setCvt_type(input.getCvt_type());
        }
        info.setFtp_local_script(input.getFtp_local_script());
        info.setCvt_local_script(input.getCvt_local_script());
        info.setSoc_domain(input.getSoc_domain());
        info.setSoc_bk_desc(input.getSoc_bk_desc());
        info.setRcd_state(RCD_STATE.NORMAL);
        info.setUser_root_path(input.getUser_root_path());
        if (!Assert.isEmpty(input.getEnvironment_variables())) {
            info.setEnvironment_variables(input.getEnvironment_variables());
        }
        daoCheckService.checkSocNotExist(input.getSoc_name());
        // 校验用户根路径是否存在
        daoCheckService.checkUserRootPathExist(info, input.getWork_seq());
        daoService.insertInfo(info);
        // dpService.insertDeptSoc(input.getOrg_dept_id(), input.getSoc_name());
        return output;
    }

    /**
     * Description: 添加数据源功能日志信息
     *
     * @param input
     * @return
     */
    @Override
    protected String getLogTxt(AddSocInputBean input) {
        List<String> lst_val = new ArrayList<String>();
        lst_val.add(input.getSoc_name());
        lst_val.add(String.valueOf(input.getSoc_port()));
        // lst_val.add(input.getProtocol_type().getCname());
        lst_val.add(input.getKey_remote_passwd());
        lst_val.add(String.valueOf(input.getBk_timeout()));
        return lgsvc.getLogTxt("添加数据源日志信息", lst_val);
    }
}
