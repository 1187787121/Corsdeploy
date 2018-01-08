/**
 * Title: UpdateDictAction.java
 * File Description:  修改数据字典信息Action
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014年11月17日
 */
package com.wk.cd.system.dc.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dc.bean.UpdateDictInputBean;
import com.wk.cd.system.dc.bean.UpdateDictOutputBean;
import com.wk.cd.system.dc.dao.DcDictDaoService;
import com.wk.cd.system.dc.dao.DcDictEnuDaoService;
import com.wk.cd.system.dc.info.DcDictEnuInfo;
import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:修改数据字典信息Action
 * @author HT
 */
public class UpdateDictAction
		extends ActionBasic<UpdateDictInputBean, UpdateDictOutputBean> {
	@Inject
	private DcDictDaoService dictDaoService;
	@Inject
	private DcDictEnuDaoService enuDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改数据字典信息Action方法
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateDictOutputBean doAction(UpdateDictInputBean input) {
		UpdateDictOutputBean output = new UpdateDictOutputBean();
		logger.debug("domain_name=[{}]", input.getDomain_name());
		//必填项
		Assert.assertNotEmpty(input.getDomain_name(),
				UpdateDictInputBean.DOMAIN_NAMECN);
		Assert.assertNotEmpty(input.getDomain_cn_name(),
				UpdateDictInputBean.DOMAIN_CN_NAMECN);
		Assert.assertNotEmpty(input.getFld_type(),
				UpdateDictInputBean.FLD_TYPECN);
		Assert.assertNotEmpty(input.getFld_length(),
				UpdateDictInputBean.FLD_LENGTHCN);
		Assert.assertNotEmpty(input.getIs_enu(), UpdateDictInputBean.IS_ENUCN);
		if(input.getIs_enu()==YN_FLAG.YES){
			for(DcDictEnuInfo info:input.getEnu_list()){
				Assert.assertNotEmpty(info.getEnu_value(), DcDictEnuInfo.ENU_VALUECN);
				Assert.assertNotEmpty(info.getEnu_code(), DcDictEnuInfo.ENU_CODECN);
			}
		}
		//检查DC_DICT表是否存在domain_name记录
		dictDaoService.checkExistDomainName(input.getDomain_name());
		

		DcDictInfo dictInfo = new DcDictInfo();
		dictInfo.setDomain_name(input.getDomain_name());
		dictInfo.setDomain_cn_name(input.getDomain_cn_name());
		dictInfo.setFld_type(input.getFld_type());
		dictInfo.setFld_length(input.getFld_length());
		dictInfo.setFld_scale(input.getFld_scale());
		dictInfo.setEnu_yn_flag(input.getIs_enu());
		dictDaoService.updateDict(dictInfo);

		if (enuDaoService.checkDomainName(input.getDomain_name())) {
			enuDaoService.deleteEnuList(input.getDomain_name());
		}
		// 若是枚举,构建List<DcDictEnuInfo>并保存
		if (input.getIs_enu() == YN_FLAG.YES) {
			List<DcDictEnuInfo> enu_list = input.getEnu_list();
			if (enu_list != null && enu_list.size() > 0) {
				for (DcDictEnuInfo enuInfo : enu_list) {
					enuInfo.setDomain_name(input.getDomain_name());
				}
				enuDaoService.insertListInfo(enu_list);
			}
		}
		output.setDomain_name(input.getDomain_name());
		return output;
	}

	/**
	 * Description: 写日志
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateDictInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("domain_name="+input.getDomain_name());
		return lgsvc.getLogTxt("修改数据字典信息", lst_val);
	}

}
