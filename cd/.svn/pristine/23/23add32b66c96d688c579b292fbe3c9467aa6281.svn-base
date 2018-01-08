/**
 * Title: UsRoleSocPrivDaoService.java
 * File Description: 部门角色数据源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.us.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色数据源权限表
 * @author AutoGen
 */
public class UsRoleSocPrivDaoService {
	@Inject
	private UsRoleSocPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleSocPrivInfo info
	 * @return UsRoleSocPrivInfo
	 */
	public UsRoleSocPrivInfo getInfoByKey(UsRoleSocPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleSocPrivInfo info
	 * @return UsRoleSocPrivInfo
	 */
	public UsRoleSocPrivInfo getInfoByKeyForUpdate(UsRoleSocPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleSocPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleSocPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleSocPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleSocPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门角色编码查询拥有的数据源列表
	 * @param dprl_code
	 * @return
	 */
	public List<String> querySocByDprl(String dprl_code) {
		return dao.querySocByDprl(dprl_code);
	}

	/**
	 * Description: 检查部门角色对应的数据源记录是否已经存在
	 * @param dprl_code
	 * @param rs_code
	 */
	public void checkDprlSocNotExist(String dprl_code, String soc_name) {
		if (dao.countSocByDprlCode(dprl_code, soc_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleSocPrivInfo.TABLECN).addScene("FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "的数据源" + soc_name);
		}
	}

	public int countSocByDprlCode(String dprl_code, String soc_name) {
		return dao.countSocByDprlCode(dprl_code, soc_name);
	}

	/**
	 * Description: 检查部门角色对应的数据源记录是否已经存在
	 * @param dprl_code
	 * @param soc_name
	 */
	public void checkDprlSocExist(String dprl_code, String soc_name) {
		if (dao.countSocByDprlCode(dprl_code, soc_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleSocPrivInfo.TABLECN).addScene("FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "的数据源" + soc_name);
		}
	}

	/**
	 * Description: 为部门角色删除数据源权限
	 * @param dprl_code
	 * @param soc_list
	 */
	public void delSocByDprlCode(String dprl_code, List<String> soc_list) {
		UsRoleSocPrivInfo info = new UsRoleSocPrivInfo();
		for (String soc_name : soc_list) {
			checkDprlSocExist(dprl_code, soc_name);
			info.setDprl_code(dprl_code);
			info.setSoc_name(soc_name);
			dao.delete(info);
		}
	}

	/**
	 * Description: 根据部门角色编码查询数据源列表
	 * @param dprl_code
	 * @return
	 */
	public List<DtSourceInfo> querySocByDprlArr(String dprl_code_arr) {
		return dao.querySocByDprlArr(dprl_code_arr);
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有数据源权限
	 * @param dprl_code
	 */
	public void deleteAllSocByDprl(String dprl_code) {
		dao.deleteAllSocByDprl(dprl_code);
	}
	/**
	 * 
	 * Description: 根据数据源名称删除部门角色的所有数据源权限信息
	 * @param soc_name
	 * @return
	 */
	public int deleteRoleSocBySocName(String soc_name){
		return dao.deleteRoleSocBySocName(soc_name);
	}

	/** 
	 * Description: 通过部门角色查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<DtSourceInfo> iteratorSocByDprl(String dprl_code) {
		return dao.iteratorSocByDprl(dprl_code);
	}
	/** 
	 * Description: 通过部门角色数组查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<DtSourceInfo> iteratorSocByDprlArray(String dprl_code) {
		return dao.iteratorSocByDprlArray(dprl_code);
	}
	
	/**
	 * Description: 查询部门角色列表所拥有的数据源权限
	 * @param dprl_list
	 * @return
	 */
	public List<String> querySocPrivByDprls(List<String> dprl_list) {
		List<String> soc_list = new ArrayList<String>();
		Map<String, String> soc_map=new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			DBIterator<String> soc_iterator = dao.iteratorSocPrivByDprl(dprl_code);
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