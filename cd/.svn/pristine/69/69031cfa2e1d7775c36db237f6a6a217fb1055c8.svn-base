/**
 * Title: DpDeptSqlPrivDaoService.java
 * File Description: 部门SQL操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门SQL操作权限表
 * @author AutoGen
 */
public class DpDeptSqlPrivDaoService {
	@Inject
	private DpDeptSqlPrivDao dao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param DpDeptSqlPrivInfo info
	 * @return DpDeptSqlPrivInfo
	 */
	public DpDeptSqlPrivInfo getInfoByKey(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据主键查询一条记录,不抛异常
	 * @param info
	 * @return
	 */
	public DpDeptSqlPrivInfo getInfoByKeyWithoutException(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据部门编码查询多条记录
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptSqlPrivInfo> queryInfosById(String dept_id) {
		List<DpDeptSqlPrivInfo> infos_dao = dao.queryDeptSqlPrivInfos(dept_id);
		return infos_dao;
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param DpDeptSqlPrivInfo info
	 * @return DpDeptSqlPrivInfo
	 */
	public DpDeptSqlPrivInfo getInfoByKeyForUpdate(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.getForUpdate(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptSqlPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * Description: 插入一条记录
	 * @param DpDeptSqlPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptSqlPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<DpDeptSqlPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptSqlPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 修改部门SQL权限记录
	 * @param dpdeptsqlprivInfo
	 */
	public void updateInfo(DpDeptSqlPrivInfo dpdeptsqlprivInfo) {
		dao.update(dpdeptsqlprivInfo);
	}

	/**
	 * Description: 软删除id下所有表
	 * @param id
	 */
	public void deleteDeptSqlPrivInfo(String id) {
		// 检查此ID下是否有数据记录
		if (dao.queryDeptSqlPrivInfos(id).isEmpty()) {
			return;
		}
		dao.deleteInfo(id);

	}
	/**
	 * 
	 * Description: 根据数据源名称删除部门SQL操作权限数据
	 * @param soc_name
	 * @return
	 */
	public int deleteDeptSqlBySocName(String soc_name) {
		return dao.deleteDeptSqlBySocName(soc_name);

	}
	
	/**
	 * 
	 * Description: 根据部门编码查询sql权限
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptSqlPrivInfo> querySqlPrivByDeptId(String dept_id) {
		List<DpDeptSqlPrivInfo> sql_list = new ArrayList<DpDeptSqlPrivInfo>();
		DBIterator<DpDeptSqlPrivInfo> sql_iterator = dao.queryDeptSqlPrivByDeptId(dept_id);
		try {
			while (sql_iterator.hasNext()) {
				sql_list.add(sql_iterator.next());
			}
		} finally {
			sql_iterator.close();
		}
		return sql_list;
	}
}