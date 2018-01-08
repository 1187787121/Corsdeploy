/**
 * Title: LgLogLabelDaoService.java
 * File Description: 日志标记级别表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-23
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:日志标记级别表
 * @author AutoGen
 */
public class LgLogLabelDaoService {
	@Inject
	private LgLogLabelDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param LgLogLabelInfo info
	 * @return LgLogLabelInfo
	 */
	public LgLogLabelInfo getInfoByKey(LgLogLabelInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param LgLogLabelInfo info
	 * @return LgLogLabelInfo
	 */
	public LgLogLabelInfo getInfoByKeyForUpdate(LgLogLabelInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param LgLogLabelInfo info
	 * @return int
	 */
	public int insertInfo(LgLogLabelInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<LgLogLabelInfo>
	 * @return int
	 */
	public int insertListInfo(List<LgLogLabelInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 更新一条记录
	 * @param info
	 * @return
	 */
	public int updateInfo(LgLogLabelInfo info) {
		return dao.update(info);
	}

	/**
	 * 删除一条记录
	 * @param info 删除一条记录
	 * @return
	 */
	public int deleteInfo(LgLogLabelInfo info) {
		return dao.delete(info);
	}

	/**
	 * 查询标记流水号
	 * @param user_id 用户名
	 * @param log_label 日期标记级别
	 * @return 日志流水号
	 */
	public DBIterator<String> getLogWorkSeqByLabel(String user_id, int log_label) {
		return dao.getLogWorkSeqByLabel(user_id, log_label);
	}

}