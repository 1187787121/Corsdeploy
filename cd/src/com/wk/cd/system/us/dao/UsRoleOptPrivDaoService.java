/**
 * Title: UsRoleOptPrivDaoService.java
 * File Description: 部门角色操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色操作权限配置表
 * @author AutoGen
 */
public class UsRoleOptPrivDaoService {
	@Inject private UsRoleOptPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleOptPrivInfo info
	 * @return UsRoleOptPrivInfo
	 */
	public UsRoleOptPrivInfo getInfoByKey(UsRoleOptPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleOptPrivInfo info
	 * @return UsRoleOptPrivInfo
	 */
	public UsRoleOptPrivInfo getInfoByKeyForUpdate(UsRoleOptPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleOptPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleOptPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleOptPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleOptPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据部门角色编码查询部门角色操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleOptPrivInfo> queryOptForbidPrivByDprl(String dprl_code) {
		List<UsRoleOptPrivInfo> opt_priv=new ArrayList<UsRoleOptPrivInfo>();
		DBIterator<UsRoleOptPrivInfo> opt_iterator=dao.queryOptForbidPrivByDprl(dprl_code);
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
	 * Description: 根据部门角色编码查询部门角色操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleOptPrivInfo> queryOptAllowPrivByDprl(String dprl_code) {
		List<UsRoleOptPrivInfo> opt_priv=new ArrayList<UsRoleOptPrivInfo>();
		DBIterator<UsRoleOptPrivInfo> opt_iterator=dao.queryOptAllowPrivByDprl(dprl_code);
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
	 * Description: 根据部门角色编码查询部门角色操作权限信息列表
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleOptPrivInfo> queryOptPrivByDprl(String dprl_code) {
		List<UsRoleOptPrivInfo> opt_priv=new ArrayList<UsRoleOptPrivInfo>();
		DBIterator<UsRoleOptPrivInfo> opt_iterator=dao.queryOptPrivByDprl(dprl_code);
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
	 * Description: 根据部门角色编码删除操作权限
	 * @param dprl_code 
	 */
	public int deleteOptPrivByDprl(String dprl_code) {
		return dao.deleteOptPrivByDprl(dprl_code);
	}
}