/**
 * Title: DeleteComponentAction.java
 * File Description: 删除组件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年11月22日
 */
package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.DeleteComponentGroupInputBean;
import com.wk.cd.module1.bean.DeleteComponentGroupOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;

/**
 * Class Description: 删除组件服务
 * @author xuph
 */
public class DeleteComponentGroupAction
		extends ActionBasic<DeleteComponentGroupInputBean, DeleteComponentGroupOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 删除组件操作
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteComponentGroupOutputBean doAction(DeleteComponentGroupInputBean input) {
		logger.info("------------------DeleteComponentGroupAction begin ---------------");
		DeleteComponentGroupOutputBean output = new DeleteComponentGroupOutputBean();
		// 获取参数
		String id = input.getId();
		// 非空校检
		Assert.assertNotEmpty(id, DeleteComponentGroupInputBean.COMP_IDCN);
		// 删除组件Xml文件
		ComponentGroup component = new ComponentGroup(id);
		FileUtil.deleteFile(XmlPathUtil.getXmlPath(component));
		// 保存组件信息表
		logger.debug("删除组件信息表记录：COMPID=[" + component.getId() + "]");
		moComponentDaoService.deleteInfoByKey(id);
		logger.info("------------------DeleteComponentGroupAction end -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteComponentGroupInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getId());
		return lgsvc.getLogTxt("删除组件", log_lst);
	}
}
