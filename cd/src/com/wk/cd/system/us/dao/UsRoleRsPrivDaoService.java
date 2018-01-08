/**
 * Title: UsRoleRsPrivDaoService.java
 * File Description: 部门角色资源权限表
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
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.info.UsRoleRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色资源权限表
 * @author AutoGen
 */
public class UsRoleRsPrivDaoService {
	@Inject
	private UsRoleRsPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private RsResDaoService rsResDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleRsPrivInfo info
	 * @return UsRoleRsPrivInfo
	 */
	public UsRoleRsPrivInfo getInfoByKey(UsRoleRsPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleRsPrivInfo info
	 * @return UsRoleRsPrivInfo
	 */
	public UsRoleRsPrivInfo getInfoByKeyForUpdate(UsRoleRsPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleRsPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleRsPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleRsPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleRsPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门角色编码查询拥有的资源列表
	 * @param dprl_code
	 * @return
	 */
	public List<String> queryRsByDprl(String dprl_code) {
		return dao.queryRsByDprl(dprl_code);
	}

	/**
	 * Description: 检查部门角色对应的资源记录是否已经存在
	 * @param dprl_code
	 * @param rs_code
	 */
	public void checkDprlRsNotExist(String dprl_code, String rs_code) {
		if (dao.countRsByDprlCode(dprl_code, rs_code) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleRsPrivInfo.TABLECN).addScene("FIELD", usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]")
					.addScene("FIELD", rsResDaoService.getInfoByCode(rs_code).getRs_cn_name() + "[" + rs_code + "]");
		}
	}

	public int countRsByDprlCode(String dprl_code, String rs_code) {
		return dao.countRsByDprlCode(dprl_code, rs_code);
	}

	/**
	 * Description: 检查部门角色对应的资源记录是否已经存在
	 * @param dprl_code
	 * @param rs_code
	 */
	public void checkDprlRsExist(String dprl_code, String rs_code) {
		if (dao.countRsByDprlCode(dprl_code, rs_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleRsPrivInfo.TABLECN).addScene("FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "的资源" + rsResDaoService.getInfoByCode(rs_code).getRs_cn_name() + "[" + rs_code + "]");
		}
	}

	/**
	 * Description: 为部门角色删除资源权限
	 * @param dprl_code
	 * @param rs_list
	 */
	public void delRsByDprlCode(String dprl_code, List<String> rs_list) {
		UsRoleRsPrivInfo info = new UsRoleRsPrivInfo();
		for (String rs_code : rs_list) {
			checkDprlRsExist(dprl_code, rs_code);
			info.setDprl_code(dprl_code);
			info.setRs_code(rs_code);
			dao.delete(info);
		}
	}

	/**
	 * Description: 查询部门角色列表所拥有的资源权限
	 * @param dprl_code_arr
	 * @return
	 */
	public List<RsResInfo> queryRsByDprlArr(String dprl_code_arr) {
		DBIterator<RsResInfo> rs_it = dao.iteratorRsByDprlArr(dprl_code_arr);
		List<RsResInfo> rs_list = new ArrayList<RsResInfo>();
		try {
			while (rs_it.hasNext()) {
				rs_list.add(rs_it.next());
			}
		} finally {
			rs_it.close();
		}
		return rs_list;
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有资源权限
	 * @param dprl_code
	 */
	public void deleteAllRsByDprl(String dprl_code) {
		dao.deleteAllRsByDprl(dprl_code);
	}

	/** 
	 * Description: 根据部门角色编码查询资源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprl(String dprl_code) {
		return dao.iteratorRsByDprl(dprl_code);
	}
	
	/** 
	 * Description: 根据部门角色编码数组查询资源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprlArray(String dprl_code) {
		return dao.iteratorRsByDprlArray(dprl_code);
	}
	
	/**
	 * Description: 查询部门角色列表所拥有的资源权限
	 * @param dprl_list
	 * @return
	 */
	public List<String> queryRsPrivByDprls(List<String> dprl_list) {
		List<String> rs_list = new ArrayList<String>();
		Map<String, String> rs_map=new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			DBIterator<String> rs_iterator = dao.iteratorRsPrivByDprl(dprl_code);
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
}