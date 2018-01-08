/**
 * Title: UsUserOptPrivDaoService.java
 * File Description: 用户操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:用户操作权限配置表
 * @author AutoGen
 */
public class UsUserOptPrivDaoService {
	@Inject private UsUserOptPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserOptPrivInfo info
	 * @return UsUserOptPrivInfo
	 */
	public UsUserOptPrivInfo getInfoByKey(UsUserOptPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserOptPrivInfo info
	 * @return UsUserOptPrivInfo
	 */
	public UsUserOptPrivInfo getInfoByKeyForUpdate(UsUserOptPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserOptPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserOptPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserOptPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserOptPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据用户ID查询用户操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptForbidPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		DBIterator<UsUserOptPrivInfo> opt_iterator=dao.queryOptForbidPrivBy(user_id);
		try {
			while (opt_iterator.hasNext()) {
				opt_priv.add(opt_iterator.next());
			}
		} finally {
			opt_iterator.close();
		}
		return opt_priv;
	}

	/** 
	 * Description: 根据用户ID查询用户操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptAllowPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		DBIterator<UsUserOptPrivInfo> opt_iterator=dao.queryOptAllowPrivByUser(user_id);
		try {
			while (opt_iterator.hasNext()) {
				opt_priv.add(opt_iterator.next());
			}
		} finally {
			opt_iterator.close();
		}
		return opt_priv;
	}

	/** 
	 * Description: 根据用户删除操作权限
	 * @param user_id 
	 */
	public int deleteOptPrivByUser(String user_id) {
		return dao.deleteOptPrivByUser(user_id);
	}

}