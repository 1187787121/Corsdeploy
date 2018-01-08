/**
 * Title: LgLogDownDaoService.java
 * File Description: 日期下载信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-29
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class description:日期下载信息表
 * @author AutoGen
 */
public class LgLogDownDaoService {
	@Inject
	private LgLogDownDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param LgLogDownInfo info
	 * @return LgLogDownInfo
	 */
	public LgLogDownInfo getInfoByKey(LgLogDownInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param LgLogDownInfo info
	 * @return LgLogDownInfo
	 */
	public LgLogDownInfo getInfoByKeyForUpdate(LgLogDownInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param LgLogDownInfo info
	 * @return int
	 */
	public int insertInfo(LgLogDownInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<LgLogDownInfo>
	 * @return int
	 */
	public int insertListInfo(List<LgLogDownInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 查询生成的日志文件信息
	 * @param user_id 用户名
	 * @param crt_bk_date 生成日期
	 * @return 日志文件信息
	 */
	public DBIterator<LgLogDownInfo> getLogFileInfo(String user_id,
			JaDate crt_bk_date) {
		return dao.getLogFileInfo(user_id, crt_bk_date);
	}
}