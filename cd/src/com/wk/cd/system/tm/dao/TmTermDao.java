/**
 * Title: TmTermDao.java
 * File Description: 终端配置表
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
 * Class description:终端配置表
 * @author AutoGen
 */
abstract class TmTermDao
		extends EntityDao<TmTermInfo> {

	/**
	 * 根据输入信息查询终端信息
	 * @param term_no 终端号
	 * @param term_type 终端类型
	 * @param term_bk_desc 终端描述
	 * @return 终端信息
	 */
	@SqlParam(condition = "(term_no like '%${term_no}%') and (term_type like '%${term_type}%') and (term_bk_desc like '%${term_bk_desc}%')", dynamic = true)
	abstract List<TmTermInfo> pageInfo(String term_no, TERM_TYPE term_type,
			String term_bk_desc, int start_recd, int limit_recd);

	/**
	 * 根据输入信息查询终端信息
	 * @param term_no 终端号
	 * @param term_type 终端类型
	 * @param term_bk_desc 终端描述
	 * @return 终端信息
	 */
	@SqlParam(sql = "SELECT count(*) FROM tm_term WHERE (term_no like '%${term_no}%') and (term_type like '%${term_type}%') and (term_bk_desc like '%${term_bk_desc}%')", dynamic = true)
	abstract int countInfo(String term_no, TERM_TYPE term_type,
			String term_bk_desc);

	@SqlParam(condition = "1=1")
	abstract DBIterator<TmTermInfo> iteratorTmAllTerm();

	/** 
	 * Description: 查询终端号总数
	 * @param term_no
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countByTermNo(String term_no);
}