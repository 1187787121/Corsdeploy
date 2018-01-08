/**
 * Title: TmTermDao.java
 * File Description: �ն����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-5
 */
package com.wk.cd.system.tm.dao;

import java.util.List;

import com.wk.cd.enu.TERM_TYPE;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.db.*;

/**
 * Class description:�ն����ñ�
 * @author AutoGen
 */
abstract class TmTermDao
		extends EntityDao<TmTermInfo> {

	/**
	 * ����������Ϣ��ѯ�ն���Ϣ
	 * @param term_no �ն˺�
	 * @param term_type �ն�����
	 * @param term_bk_desc �ն�����
	 * @return �ն���Ϣ
	 */
	@SqlParam(condition = "(term_no like '%${term_no}%') and (term_type like '%${term_type}%') and (term_bk_desc like '%${term_bk_desc}%')", dynamic = true)
	abstract List<TmTermInfo> pageInfo(String term_no, TERM_TYPE term_type,
			String term_bk_desc, int start_recd, int limit_recd);

	/**
	 * ����������Ϣ��ѯ�ն���Ϣ
	 * @param term_no �ն˺�
	 * @param term_type �ն�����
	 * @param term_bk_desc �ն�����
	 * @return �ն���Ϣ
	 */
	@SqlParam(sql = "SELECT count(*) FROM tm_term WHERE (term_no like '%${term_no}%') and (term_type like '%${term_type}%') and (term_bk_desc like '%${term_bk_desc}%')", dynamic = true)
	abstract int countInfo(String term_no, TERM_TYPE term_type,
			String term_bk_desc);

	@SqlParam(condition = "1=1")
	abstract DBIterator<TmTermInfo> iteratorTmAllTerm();

	/** 
	 * Description: ��ѯ�ն˺�����
	 * @param term_no
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countByTermNo(String term_no);
}