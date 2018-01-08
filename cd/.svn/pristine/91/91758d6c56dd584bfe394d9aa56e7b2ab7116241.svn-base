/**
 * Title: AddDictAction.java
 * File Description: 新增数据字典Action
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
import com.wk.cd.system.dc.bean.AddDictInputBean;
import com.wk.cd.system.dc.bean.AddDictOutputBean;
import com.wk.cd.system.dc.dao.DcDictDaoService;
import com.wk.cd.system.dc.dao.DcDictEnuDaoService;
import com.wk.cd.system.dc.info.DcDictEnuInfo;
import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:新增数据字典Action
 * @author HT
 */
public class AddDictAction
		extends ActionBasic<AddDictInputBean, AddDictOutputBean> {
	@Inject
	private DcDictDaoService dictDaoService;
	@Inject
	private DcDictEnuDaoService enuDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 新增数据字典Action方法
	 * @param input
	 * @return
	 */
	@Override
	protected AddDictOutputBean doAction(AddDictInputBean input) {
		AddDictOutputBean output = new AddDictOutputBean();
		logger.debug("domain_name=[{}]", input.getDomain_name());

		// 必填项
		Assert.assertNotEmpty(input.getDomain_name(),
				AddDictInputBean.DOMAIN_NAMECN);
		Assert.assertNotEmpty(input.getDomain_cn_name(),
				AddDictInputBean.DOMAIN_CN_NAMECN);
		Assert.assertNotEmpty(input.getFld_type(), AddDictInputBean.FLD_TYPECN);
		Assert.assertNotEmpty(input.getFld_length(),
				AddDictInputBean.FLD_LENGTHCN);
		Assert.assertNotEmpty(input.getIs_enu(), AddDictInputBean.IS_ENUCN);
		if(input.getIs_enu()==YN_FLAG.YES){
			for(DcDictEnuInfo info:input.getEnu_list()){
				Assert.assertNotEmpty(info.getEnu_value(), DcDictEnuInfo.ENU_VALUECN);
				Assert.assertNotEmpty(info.getEnu_code(), DcDictEnuInfo.ENU_CODECN);
			}
		}
		// 检查表中是否已存在记录
		dictDaoService.checkNotExistDomainName(input.getDomain_name());
		dictDaoService.checkNotExistDomainCnName(input.getDomain_cn_name());

		// 构建dictInfo
		DcDictInfo dictInfo = new DcDictInfo();
		dictInfo.setDomain_name(input.getDomain_name());
		dictInfo.setDomain_cn_name(input.getDomain_cn_name());
		dictInfo.setFld_length(input.getFld_length());
		dictInfo.setFld_scale(input.getFld_scale());
		dictInfo.setFld_type(input.getFld_type());
		dictInfo.setEnu_yn_flag(input.getIs_enu());

		dictDaoService.insertInfo(dictInfo);

		// 若是枚举,构建List<DcDictEnuInfo>并保存
		if (input.getIs_enu() == YN_FLAG.YES) {
			List<DcDictEnuInfo> list = input.getEnu_list();
			if (list != null && list.size() > 0) {
				for (DcDictEnuInfo info : list) {
					info.setDomain_name(input.getDomain_name());
				}
				enuDaoService.insertListInfo(list);
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
	protected String getLogTxt(AddDictInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("domain_name="+input.getDomain_name());
		return lgsvc.getLogTxt("新增数据字典信息", lst_val);
	}

}
