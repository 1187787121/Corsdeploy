/**
 * Title: CmSeqDao.java
 * File Description: ÐòºÅ±í
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-19
 */
package com.wk.cd.common.cm.dao;

import com.wk.db.*;
import com.wk.cd.common.cm.info.*;

import java.util.List;

/**
 * Class description:ÐòºÅ±í
 * @author AutoGen
 */
abstract class CmSeqDao extends EntityDao<CmSeqInfo> {

	@SqlParam(updateSet = {"LMOD_BK_DATE","CUR_BK_SEQ","LS_BK_SEQ"}, condition = "PK")
	abstract int updateLmodBkdateByKey(CmSeqInfo info);
	
	@SqlParam(updateSet = {"CUR_BK_SEQ"}, condition = "PK")
	abstract int updateCurSeqByKey(CmSeqInfo info);
	
	@SqlParam(updateSet = {"LS_BK_SEQ"}, condition = "PK")
	abstract int updateLsSeqByKey(CmSeqInfo info);
	
	@SqlParam(sql = "select * from cm_seq where SEQ_NAME = :seq_name")
	abstract CmSeqInfo getInfoByKeyNoCache(String seq_name);

	@SqlParam(updateSet = {"CUR_BK_SEQ", "LS_BK_SEQ"}, condition = "PK")
	abstract int updateSeqBeyName(long cur_bk_seq, long ls_bk_seq, String seq_name);

	@SqlParam(querySet = "SEQ_NAME", condition = "SEQ_NAME like ':{seq_name}%'", dynamic = true)
	abstract List<String> querySeqName(String seq_name);
}