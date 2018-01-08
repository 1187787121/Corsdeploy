/**
 * Title: SeqCommonService.java
 * File Description: 序号表公共服务
 * @copyright: 2015
 * @company: CORSWORK
 * @author: CSWQZ
 * @version: 1.0
 * @date: 2015-10-10
 */
package com.wk.cd.common.cm.service;

import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.cm.info.CmSeqInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class Description: 序号表公共服务
 * @author CSWQZ
 */
public class SeqCommonService {
	@Inject
	private CmSeqDaoService cmDaos;
	/**
	 * Description: 取序号并更新序号值
	 * @param seq_name
	 * @param cur_date
	 * @param step
	 * @return
	 */
	public CmSeqInfo getSeqByKey(String seq_name, JaDate cur_date, long step){
		return cmDaos.getSeqByKey(seq_name, cur_date, step);
	}
}
