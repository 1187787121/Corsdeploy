/**
 * Title: DpDeptSocPrivDaoService.java
 * File Description: 部门数据源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.info.DpDeptSocPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门数据源权限表
 * @author AutoGen
 */
public class DpDeptSocPrivDaoService {
	@Inject
	private DpDeptSocPrivDao dao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param DpDeptSocPrivInfo info
	 * @return DpDeptSocPrivInfo
	 */
	public DpDeptSocPrivInfo getInfoByKey(DpDeptSocPrivInfo info) {
		DpDeptSocPrivInfo info_dao = dao.get(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptSocPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据主键查询一条记录,不抛异常
	 * @param info
	 * @return
	 */
	public int getInfoByKeyWithoutException(DpDeptSocPrivInfo info) {
		DpDeptSocPrivInfo info_dao = dao.get(info);
		if (Assert.isEmpty(info_dao)) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Description: 根据部门编码查询多条记录
	 * @param dept_id
	 * @return
	 */
	public List<String> queryInfosById(String dept_id) {
		return dao.queryDeptSocPrivInfos(dept_id);
	}

	/**
	 * Description: 根据部门编码列表查询多条记录
	 * @param dept_id
	 * @return
	 */
	public List<String> queryInfosByIds(List<String> dept_ids) {
		String dept_ids_str = getStringByList(dept_ids);
		return dao.queryDeptRsByDeptIds(dept_ids_str);
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param DpDeptSocPrivInfo info
	 * @return DpDeptSocPrivInfo
	 */
	public DpDeptSocPrivInfo getInfoByKeyForUpdate(DpDeptSocPrivInfo info) {
		DpDeptSocPrivInfo info_dao = dao.getForUpdate(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptSocPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * Description: 插入一条记录
	 * @param DpDeptSocPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptSocPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<DpDeptSocPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptSocPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门编码删除一条记录
	 * @param dept_id
	 * @return int
	 */
	public int deleteDeptSocPrivInfo(String dept_id) {
		// 检查此ID下是否有数据记录
		if (dao.queryDeptSocPrivInfos(dept_id).size() == 0) {
			return 0;
		}
		return dao.deleteInfo(dept_id);
	}

	/**
	 * Description: 根据主键删除一条记录
	 * @param rs_info
	 * @return
	 */
	public int deleteInfo(DpDeptSocPrivInfo soc_info) {
		return dao.delete(soc_info);
	}

	/**
	 * 
	 * Description: 更新部门数据源数据列表
	 * @param dept_soc_priv_infos
	 */
	public void updateDeptSocPrivs(List<DpDeptSocPrivInfo> dept_soc_priv_infos) {
		dao.update(dept_soc_priv_infos);
	}

	/**
	 * 将输入的List转换为字符串
	 * @param list
	 * @return
	 */
	public String getStringByList(List<String> list) {
		String str = "";
		if (!list.isEmpty()) {
			for (String s : list) {
				str = str + s + "','";
			}
			if (!str.isEmpty()) {
				str = "('" + str.substring(0, str.length() - 2) + ")";
			} else {
				str = "('')";
			}
		}
		return str;
	}
	/**
	 * 
	 * Description: 根据数据源名称删除部门数据源数据
	 * @param soc_name
	 * @return
	 */
	public int deleteInfoBySocName(String soc_name){
		return dao.deleteInfoBySocName(soc_name);
	}
	
	
	/**
	 * Description: 查询部门角色列表所拥有的数据源权限
	 * @param dprl_list
	 * @return
	 */
	public List<String> querySocPrivByDepts(List<String> depts_list) {
		List<String> soc_list = new ArrayList<String>();
		Map<String, String> soc_map=new HashMap<String, String>();
		for (String dept_id : depts_list) {
			DBIterator<String> soc_iterator = dao.iteratorDeptSocPrivInfos(dept_id);
			try {
				while (soc_iterator.hasNext()) {
					String soc_name=soc_iterator.next();
					if(!soc_map.containsKey(soc_name)){
						soc_map.put(soc_name, soc_name);
					}
				}
			} finally {
				soc_iterator.close();
			}
		}
		soc_list.addAll(soc_map.values());
		return soc_list;
	}
}