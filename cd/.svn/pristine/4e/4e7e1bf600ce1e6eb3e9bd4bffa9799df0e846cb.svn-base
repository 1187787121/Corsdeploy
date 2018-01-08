/**
 * Title: CloseStorageAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月22日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.CloseStorageInputBean;
import com.wk.cd.build.ea.bean.CloseStorageOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 入库关闭
 * @author xuph
 */
public class CloseStorageAction extends ActionBasic<CloseStorageInputBean, CloseStorageOutputBean>{
	@Inject private ActionLogPublicService lgsvc;
	@Inject private TargetPackPublicService targets;
	@Inject private EnvTagStorageDaoService envTagS;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected CloseStorageOutputBean doAction(CloseStorageInputBean input) {
		logger.info("--------------------CloseStorageAction Begin------------------");
		CloseStorageOutputBean output = new CloseStorageOutputBean();
		//非空校检
	    String storage_id = input.getStorage_id();
	    Assert.assertNotEmpty(storage_id, CloseStorageInputBean.STORAGE_IDCN);
	    //合法性校检
	    targets.checkTargetIsExist(storage_id);
	    //更新入库信息
	    envTagS.updateStatusInfoByKey(STORAGE_STATUS.CLOSE, storage_id);
	    logger.info("--------------------CloseStorageAction End--------------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(CloseStorageInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("入库编号：" + input.getStorage_id());
		return lgsvc.getLogTxt("关闭单个入库任务", lst_val);
	}

}
