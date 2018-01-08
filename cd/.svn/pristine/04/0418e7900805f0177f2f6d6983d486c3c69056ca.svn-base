/**
 * Title: DeleteDictAction.java
 * File Description: 删除数据字典Action
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
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dc.bean.DeleteDictInputBean;
import com.wk.cd.system.dc.bean.DeleteDictOutputBean;
import com.wk.cd.system.dc.dao.DcDictDaoService;
import com.wk.cd.system.dc.dao.DcDictEnuDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:删除数据字典Action
 * @author HT
 */
public class DeleteDictAction
		extends ActionBasic<DeleteDictInputBean, DeleteDictOutputBean> {
	@Inject
	DcDictDaoService dictDaoService;
	@Inject
	DcDictEnuDaoService enuDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private final static Log logger = LogFactory.getLog();


	/** 
	 * Description: 删除数据字典Action方法
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteDictOutputBean doAction(DeleteDictInputBean input) {
		DeleteDictOutputBean output = new DeleteDictOutputBean();
		logger.debug("domain_name=[{}]", input.getDomain_name());
		
		// 必填项
		Assert.assertNotEmpty(input.getDomain_name(), DeleteDictInputBean.DOMAIN_NAMECN);

		//检查DC_DICT表是否存在domain_name记录
		dictDaoService.checkExistDomainName(input.getDomain_name());

		dictDaoService.deleteDict(input.getDomain_name());
		enuDaoService.deleteEnuList(input.getDomain_name());
		output.setDomain_name(input.getDomain_name());
		return output;
	}

	/** 
	 * Description: 写日志
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteDictInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("domain_name="+input.getDomain_name());
		return lgsvc.getLogTxt("删除数据字典信息", lst_val);
	}

}
