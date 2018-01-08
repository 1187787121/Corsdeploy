/**
 * Title: LgLogDownDao.java
 * File Description: 日期下载信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-29
 */
package com.wk.cd.system.lg.dao;

import com.wk.db.*;
import com.wk.util.JaDate;
import com.wk.cd.system.lg.info.*;

/**
 * Class description:日期下载信息表
 * @author AutoGen
 */
abstract class LgLogDownDao
		extends EntityDao<LgLogDownInfo> {

	/**
	 * 查询生成的日志文件信息
	 * @param user_id 用户名
	 * @param crt_bk_date 生成日期
	 * @return
	 */
	@SqlParam(condition = "USER_ID = ${user_id} and CRT_BK_DATE = ${crt_bk_date}", orderBy = "CRT_BK_DATE, CRT_BK_TIME desc")
	abstract DBIterator<LgLogDownInfo> getLogFileInfo(String user_id,
			JaDate crt_bk_date);
}