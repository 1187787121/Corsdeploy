/**
 * Title: DpDeptOptPrivDaoService.java
 * File Description: 部门操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.dp.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门操作权限配置表
 * @author AutoGen
 */
public class DpDeptOptPrivDaoService {
	@Inject private DpDeptOptPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param DpDeptOptPrivInfo info
	 * @return DpDeptOptPrivInfo
	 */
	public DpDeptOptPrivInfo getInfoByKey(DpDeptOptPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param DpDeptOptPrivInfo info
	 * @return DpDeptOptPrivInfo
	 */
	public DpDeptOptPrivInfo getInfoByKeyForUpdate(DpDeptOptPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param DpDeptOptPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptOptPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<DpDeptOptPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptOptPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据部门编码查询部门操作权限信息列表
	 * @param dept_id
	 * @return 
	 */
	public List<DpDeptOptPrivInfo> queryOptPrivByDept(String dept_id) {
		List<DpDeptOptPrivInfo> opt_priv=new ArrayList<DpDeptOptPrivInfo>();
		DBIterator<DpDeptOptPrivInfo> opt_iterator=dao.queryOptPrivByDept(dept_id);
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
	 * Description: 根据部门编码查询部门操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param dept_id
	 * @return 
	 */
	public List<DpDeptOptPrivInfo> queryOptForbidPrivByDept(String dept_id) {
		List<DpDeptOptPrivInfo> opt_priv=new ArrayList<DpDeptOptPrivInfo>();
		DBIterator<DpDeptOptPrivInfo> opt_iterator=dao.queryOptForbidPrivByDept(dept_id);
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
	 * Description: 根据部门编码查询部门操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param dept_id
	 * @return 
	 */
	public List<DpDeptOptPrivInfo> queryOptAllowPrivByDept(String dept_id) {
		List<DpDeptOptPrivInfo> opt_priv=new ArrayList<DpDeptOptPrivInfo>();
		DBIterator<DpDeptOptPrivInfo> opt_iterator=dao.queryOptAllowPrivByDept(dept_id);
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
	 * Description: 根据部门ID删除部门操作权限
	 * @param dept_id 
	 */
	public int deleteDeptOptPrivByDpetId(String dept_id) {
		return dao.deleteDeptOptPrivByDpetId(dept_id);
	}
}