/**
 * Title: BlockSeqService.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-21
 */
package com.wk.cd.common.cm.service;

import java.util.HashMap;
import java.util.Map;

import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * 获取段流水号服务
 * @author lixl
 */
class BlockSeqService {
	private static final long STEPNUM;
	private static final Log logger = LogFactory.getLog();
	static{
		String num = CfgTool.getProjectPropterty("cms.workseq.step.num");
		STEPNUM = Assert.isEmpty(num) ? 1 : Long.valueOf(num);
	}

	
	private static final Map<String, BlockSeqObjService> oc = 
		new HashMap<String, BlockSeqObjService>();
	@Inject private CmSeqDaoService csDaos;
	
	synchronized long getNextSeqForBlock(String seq_name, JaDate cur_date){
		logger.plog("getNextSeqForBlock begin");
		BlockSeqObjService obj = oc.get(seq_name);
		if(obj == null){
			obj = new BlockSeqObjService(STEPNUM, csDaos);
			oc.put(seq_name, obj);
		}
		logger.debug("Object addr=[{}]",obj);
		return obj.getNextSeqForBlock(seq_name, cur_date);
	} 
} 