/**
 * Title: DeleteComponentAction.java
 * File Description: ɾ���������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017��11��22��
 */
package com.wk.cd.module1.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.DeleteComponentInputBean;
import com.wk.cd.module1.bean.DeleteComponentOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.dao.MoTagsDaoService;
import com.wk.cd.module1.entity.Component;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;

/**
 * Class Description: ɾ���������
 * @author xuph
 */
public class DeleteComponentAction
		extends ActionBasic<DeleteComponentInputBean, DeleteComponentOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private MoComponentDaoService moComponentDaoService;
//	@Inject
//	private MoTagsDaoService moTagsDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ɾ���������
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteComponentOutputBean doAction(DeleteComponentInputBean input) {
		logger.info("------------------DeleteModuleAction begin ---------------");
		DeleteComponentOutputBean output = new DeleteComponentOutputBean();
		// ��ȡ����
		String id = input.getId();
		// �ǿ�У��
		Assert.assertNotEmpty(id, DeleteComponentInputBean.COMP_IDCN);
		// ɾ�����Xml�ļ�
		String save_dir = CfgTool.getWebRootPath() + XmlPathUtil.getComponentFileRelativeDir(id);
		FileUtil.deleteFolder(new File(save_dir));
		Component component = new Component(id);
		FileUtil.deleteFile(XmlPathUtil.getXmlPath(component));
		// ���������Ϣ��
		logger.debug("ɾ�������Ϣ����¼��COMPID=[" + component.getId() + "]");
		moComponentDaoService.deleteInfoByKey(id);
//		moTagsDaoService.deleteTagsById(component.getId());
		logger.info("------------------DeleteModuleAction end -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteComponentInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getId());
		return lgsvc.getLogTxt("ɾ�����", log_lst);
	}
}