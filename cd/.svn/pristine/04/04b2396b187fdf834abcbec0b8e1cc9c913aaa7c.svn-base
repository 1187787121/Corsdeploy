/**
 * Title: DpDeptColPrivDaoService.java
 * File Description: 部门SQL字段操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门SQL字段操作权限表
 * @author AutoGen
 */
public class DpDeptColPrivDaoService {
	@Inject
	private DpDeptColPrivDao dao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param DpDeptColPrivInfo info
	 * @return DpDeptColPrivInfo
	 */
	public DpDeptColPrivInfo getInfoByKey(DpDeptColPrivInfo info) {
		DpDeptColPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据主键查询一条记录,不抛异常
	 * @param info
	 * @return
	 */
	public DpDeptColPrivInfo getInfoByKeyWithoutException(DpDeptColPrivInfo info) {
		DpDeptColPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据部门编码查询多条记录
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptColPrivInfo> queryInfosById(String dept_id) {
		List<DpDeptColPrivInfo> infos_dao = dao.queryDeptColPrivInfos(dept_id);
		return infos_dao;
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param DpDeptColPrivInfo info
	 * @return DpDeptColPrivInfo
	 */
	public DpDeptColPrivInfo getInfoByKeyForUpdate(DpDeptColPrivInfo info) {
		DpDeptColPrivInfo info_dao = dao.getForUpdate(info);
		if (info_dao == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptColPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * Description: 插入一条记录
	 * @param DpDeptColPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptColPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<DpDeptColPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptColPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 
	 * Description: 修改部门SQL字段权限记录
	 * @param dpdeptcolprivInfo
	 */
	public void updateInfo(DpDeptColPrivInfo dpdeptcolprivInfo) {
		dao.update(dpdeptcolprivInfo);
	}

	/**
	 * Description: 删除id下所有表
	 * @param id
	 */
	public void deleteDeptColPrivInfo(String id) {
		// 检查此ID下是否有数据记录
		if (dao.queryDeptColPrivInfos(id).isEmpty()) {
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
	public int deleteDeptColBySocName(String soc_name) {
		return dao.deleteDeptColBySocName(soc_name);
		
	}
	
	
	/**
	 * 
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptColPrivInfo> queryColPrivByDeptId(String dept_id) {
		List<DpDeptColPrivInfo> col_list = new ArrayList<DpDeptColPrivInfo>();
		DBIterator<DpDeptColPrivInfo> col_iterator = dao.queryDeptColPrivByDeptId(dept_id);
		try {
			while (col_iterator.hasNext()) {
				col_list.add(col_iterator.next());
			}
		} finally {
			col_iterator.close();
		}
		return col_list;
	}
	
}