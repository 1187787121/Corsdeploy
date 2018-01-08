/**
 * Title: DpDeptRsPrivDaoService.java
 * File Description: 部门资源权限表
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
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.info.DpDeptRsPrivInfo;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门资源权限表
 * @author AutoGen
 */
public class DpDeptRsPrivDaoService {
	@Inject
	private DpDeptRsPrivDao dao;
	@Inject
	private RsResDaoService rsDaoService;
	@Inject
	private DpDeptDao dpDao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param DpDeptRsPrivInfo info
	 * @return DpDeptRsPrivInfo
	 */
	public DpDeptRsPrivInfo getInfoByKey(DpDeptRsPrivInfo info) {
		DpDeptRsPrivInfo info_dao = dao.get(info);
		if (Assert.isEmpty(info)) {
			String rs_cn_name = rsDaoService.getInfoByCode(info_dao.getRs_code()).getRs_cn_name();
			DpDeptInfo bean = new DpDeptInfo();
			bean.setDept_id(info.getDept_id());
			String dept_name = dpDao.get(bean).getDept_cn_name();
			throw new IllegalOperaterException().addScene("PARM1",
					"部门" + dept_name + "[" + info.getDept_id()+ "]").addScene("PARM2",
					"资源" + rs_cn_name + "[" + info.getRs_code() + "]");
		}
		return info_dao;
	}

	/**
	 * 
	 * Description: 根据主键查询一条记录,不抛异常
	 * @param info
	 * @return
	 */
	public int getInfoByKeyWithoutException(DpDeptRsPrivInfo info) {
		DpDeptRsPrivInfo info_dao = dao.get(info);
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
	public DBIterator<String> iteratorInfosById(String dept_id) {
		return dao.iteratorDeptRsPrivInfos(dept_id);
	}

	/**
	 * Description: 根据部门编码列表查询多个部门的资源权限
	 * @param dept_id
	 * @return
	 */
	public DBIterator<String> iteratorInfosByIds(List<String> dept_ids) {
		String dept_ids_str = getStringByList(dept_ids);
		return dao.iteratorDeptRsByDeptIds(dept_ids_str);
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param DpDeptRsPrivInfo info
	 * @return DpDeptRsPrivInfo
	 */
	public DpDeptRsPrivInfo getInfoByKeyForUpdate(DpDeptRsPrivInfo info) {
		DpDeptRsPrivInfo info_dao = dao.getForUpdate(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptRsPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * Description: 插入一条记录
	 * @param DpDeptRsPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptRsPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<DpDeptRsPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptRsPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门编码删除一条记录
	 * @param dept_id
	 * @return int
	 */
	public int deleteDeptRsPrivInfo(String dept_id) {
		return dao.deleteByDeptId(dept_id);
	}

	/**
	 * Description: 根据主键删除一条记录
	 * @param rs_info
	 * @return
	 */
	public int deleteInfo(DpDeptRsPrivInfo rs_info) {
		return dao.delete(rs_info);
	}

	/**
	 * 
	 * Description: 更新部门资源数据列表
	 * @param dept_rs_priv_infos
	 */
	public void updateDeptRsPrivs(List<DpDeptRsPrivInfo> dept_rs_priv_infos) {
		dao.update(dept_rs_priv_infos);
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
	 * Description: 查询部门角色列表所拥有的资源权限
	 * @param dprl_list
	 * @return
	 */
	public List<String> queryRsPrivByDepts(List<String> depts_list) {
		List<String> rs_list = new ArrayList<String>();
		Map<String, String> rs_map=new HashMap<String, String>();
		for (String dept_id : depts_list) {
			DBIterator<String> rs_iterator = dao.iteratorDeptRsPrivInfos(dept_id);
			try {
				while (rs_iterator.hasNext()) {
					String rs_code=rs_iterator.next();
					if(!rs_map.containsKey(rs_code)){
						rs_map.put(rs_code, rs_code);
					}
				}
			} finally {
				rs_iterator.close();
			}
		}
		rs_list.addAll(rs_map.values());
		return rs_list;
	}
	
	/**
	 * 
	 * Description: 根据主键查询一条记录,不抛异常
	 * @param info
	 * @return
	 */
	public boolean checkRsPrivIsExist(DpDeptRsPrivInfo info) {
		DpDeptRsPrivInfo info_dao = dao.get(info);
		if (Assert.isEmpty(info_dao)) {
			return false;
		} else {
			return true;
		}
	}
}