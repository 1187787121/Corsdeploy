/**
 * Title: SeqCommonService.java
 * File Description: ��ű�������
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
 * Class Description: ��ű�������
 * @author CSWQZ
 */
public class SeqCommonService {
	@Inject
	private CmSeqDaoService cmDaos;
	/**
	 * Description: ȡ��Ų��������ֵ
	 * @param seq_name
	 * @param cur_date
	 * @param step
	 * @return
	 */
	public CmSeqInfo getSeqByKey(String seq_name, JaDate cur_date, long step){
		return cmDaos.getSeqByKey(seq_name, cur_date, step);
	}
}
