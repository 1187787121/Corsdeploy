/**
 * Title: ExcuteStorageAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月23日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExcuteStorageInputBean;
import com.wk.cd.build.ea.bean.ExcuteStorageOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 入库执行
 * @author xuph
 */
public class ExcuteStorageAction extends ActionBasic<ExcuteStorageInputBean, ExcuteStorageOutputBean>{
	@Inject private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject private ExcuteStoragePubService excuteStorageSrv;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private CmSeqDaoService cmsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 入库执行操作
	 * @param input
	 * @return 
	 */
	@Override
	protected ExcuteStorageOutputBean doAction(ExcuteStorageInputBean input) {
		logger.info("--------------ExcuteStorageAction Begin----------------");
		ExcuteStorageOutputBean output= new ExcuteStorageOutputBean();
		String inst_id = input.getInst_id();
		String storage_id = input.getStorage_id();
		logger.info("入库编号：storage_id:[{}],实例编号:[{}]",storage_id,inst_id);
		Assert.assertNotEmpty(inst_id, ExcuteStorageInputBean.INST_IDCN);
		Assert.assertNotEmpty(storage_id, ExcuteStorageInputBean.STORAGE_IDCN);
		//更新入库状态为入库中
		envTagStorageDaoService.updateStatusInfoByKey(STORAGE_STATUS.STORAGING, storage_id);
		//将执行前的sql提交
		cmsvc.getSession().commitAndResume();
		//入库执行
		excuteStorageSrv.excuteStorage(inst_id, storage_id);
		logger.info("--------------ExcuteStorageAction End----------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExcuteStorageInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("入库编号: " + input.getStorage_id());
		log_lst.add("实例编号: " + input.getInst_id());
		return lgsvc.getLogTxt("入库执行", log_lst);
	}

}
