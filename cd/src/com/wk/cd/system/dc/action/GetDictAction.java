/**
 * Title: GetDictAction.java
 * File Description: 获取数据字典详细信息Action
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
import com.wk.cd.system.dc.bean.GetDictInputBean;
import com.wk.cd.system.dc.bean.GetDictOutputBean;
import com.wk.cd.system.dc.dao.DcDictDaoService;
import com.wk.cd.system.dc.dao.DcDictEnuDaoService;
import com.wk.cd.system.dc.info.DcDictEnuInfo;
import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 获取数据字典详细信息Action
 * @author HT
 */
public class GetDictAction
		extends ActionBasic<GetDictInputBean, GetDictOutputBean> {
	@Inject
	private DcDictDaoService dictDaoService;
	@Inject
	private DcDictEnuDaoService enuDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 获取数据字典详细信息Action方法
	 * @param input
	 * @return
	 */
	@Override
	protected GetDictOutputBean doAction(GetDictInputBean input) {
		GetDictOutputBean output = new GetDictOutputBean();
		logger.debug("domain_name=[{}]", input.getDomain_name());
		
		// 必填项
		Assert.assertNotEmpty(input.getDomain_name(),
				GetDictInputBean.DOMAIN_NAMECN);
		
		//检查DC_DICT表是否存在domain_name记录
		dictDaoService.checkExistDomainName(input.getDomain_name());
		
		DcDictInfo dictInfo = dictDaoService
				.getDictInfo(input.getDomain_name());
		if (dictInfo != null) {
			output.setDomain_name(dictInfo.getDomain_name());
			output.setDomain_cn_name(dictInfo.getDomain_cn_name());
			output.setFld_type(dictInfo.getFld_type());
			output.setFld_length(dictInfo.getFld_length());
			output.setFld_scale(dictInfo.getFld_scale());
			output.setIs_enu(dictInfo.getEnu_yn_flag());
			
			//判断是否枚举项，若是，则加载枚举列表
			if (dictInfo.getEnu_yn_flag() == YN_FLAG.YES) {
				List<DcDictEnuInfo> enuInfo = enuDaoService
						.getEnuListInfo(input.getDomain_name());
				if (enuInfo != null && enuInfo.size() > 0) {
					output.setEnu_list(enuInfo);
				}
			}
		}
		return output;
	}

	/**
	 * Description: 写日志
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(GetDictInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("domain_name="+input.getDomain_name());
		return lgsvc.getLogTxt("获取数据字典信息", lst_val);
	}

}
