/**
 * Title: IgUserDaoService.java
 * File Description: 用户表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */
package com.wk.cd.dlk.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.dlk.us.info.IgUserInfo;
import com.wk.db.DBIterator;
import com.wk.db.DBName;
import com.wk.lang.Inject;

/**
 * Class description:用户表
 * @author AutoGen
 */
public class IgUserDaoService {
	@Inject 
	@DBName("dlkdb")
	private IgUserDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param IgUserInfo info
	 * @return IgUserInfo
	 */
	public IgUserInfo getInfoByKey(IgUserInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param IgUserInfo info
	 * @return IgUserInfo
	 */
	public IgUserInfo getInfoByKeyForUpdate(IgUserInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param IgUserInfo info
	 * @return int
	 */
	public int insertInfo(IgUserInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<IgUserInfo>
	 * @return int
	 */
	public int insertListInfo(List<IgUserInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 查询所有用户数据列表
	 * @return 
	 */
	public List<IgUserInfo> listAllIgUser() {
		List<IgUserInfo> result = new ArrayList<IgUserInfo>();
		DBIterator<IgUserInfo> dbIterator = dao.iteratorAllIgUser();
		try {
			while (dbIterator.hasNext()) {
				result.add(dbIterator.next());
			}
		} finally {
			dbIterator.close();
		}
		return result;
	}
}