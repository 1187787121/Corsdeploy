/**
 * Title: UsUserColPrivDaoService.java
 * File Description: 用户SQL字段操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:用户SQL字段操作权限表
 * @author AutoGen
 */
public class UsUserColPrivDaoService {
	@Inject
	private UsUserColPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	/**
	 * 根据主键查询一条记录
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public UsUserColPrivInfo getInfoByKey(UsUserColPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public UsUserColPrivInfo getInfoByKeyForUpdate(UsUserColPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 修改INfo
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public int updateInfo(UsUserColPrivInfo info) {
		return dao.update(info);
	}
	
	/**
	 * 插入一条记录
	 * @param UsUserColPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserColPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserColPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserColPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 检查用户的COL操作权限是否不存在
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkUserColPrivNotExist(String dprl_code, String soc_name,
			String tbl_name, String col_name) {
		if (dao.countByUserColPriv(dprl_code, soc_name, tbl_name, col_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserColPrivInfo.TABLECN).addScene(
					"FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "数据源" + soc_name + "表名" + tbl_name
							+ "字段名" + col_name);
		}
	}

	/**
	 * Description: 检查用户的COL操作权限是否存在
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkUserColPrivExist(String user_id, String soc_name,
			String tbl_name, String col_name) {
		UsUserColPrivInfo info = new UsUserColPrivInfo();
		info.setUser_id(user_id);
		info.setSoc_name(soc_name);
		info.setTbl_name(tbl_name);
		info.setCol_name(col_name);
		if (dao.countByUserColPriv(user_id, soc_name, tbl_name, col_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserColPrivInfo.TABLECN).addScene(
					"FIELD",
					"用户名" + user_id + "数据源" + soc_name + "表名" + tbl_name
							+ "字段名" + col_name);
		}
	}

	/**
	 * Description: 删除用户的COL权限记录
	 * @param user_id
	 * @param sql_list
	 */
	public void deleteUserColPriv(List<UsUserPrivBean> col_list) {
		UsUserColPrivInfo info = new UsUserColPrivInfo();
		for (UsUserPrivBean col_info : col_list) {
			checkUserColPrivExist(col_info.getUser_id(),
					col_info.getSoc_name(), col_info.getTbl_name(), col_info
							.getCol_name());
			info.setUser_id(col_info.getUser_id());
			info.setSoc_name(col_info.getSoc_name());
			info.setTbl_name(col_info.getTbl_name());
			info.setCol_name(col_info.getCol_name());
			dao.delete(info);
			// dao.updateUserColPrivState(RCD_STATE.ABNORMAL.getValue(),
			// col_info.getDprl_code(), col_info.getSoc_name(),
			// col_info.getTbl_name(), col_info.getCol_name());
		}
	}

	/**
	 * Description: 检测用户COL权限表中是否存在该权限
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	public int countByUserCol(String user_id, String soc_name, String tbl_name,
			String col_name) {
		int count1 = dao.countByUserCol(user_id, soc_name, tbl_name, col_name);
		return count1;
	}

	/**
	 * Description: 查询用户的临时COL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> queryUserTempColPriv(String user_id) {
		List<UsUserColPrivInfo> priv_list=new ArrayList<UsUserColPrivInfo>();
		DBIterator<UsUserColPrivInfo> iterator=dao.queryUserTempColPriv(user_id);
		try {
			while (iterator.hasNext()) {
				priv_list.add(iterator.next());
				
			}
		} finally {
			iterator.close();
		}
		
		return priv_list;
	}

	/**
	 * Description: 根据用户名查询COL操作权限列表
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> queryColPrivByUserId(String user_id) {
		return dao.queryColPrivByUserId(user_id);
	}

	/**
	 * Description: 删除用户的所有COL权限
	 * @param user_id
	 */
	public void deleteAllColByUserId(String user_id) {
		dao.deleteAllColByUserId(user_id);
	}

	/**
	 * Description: 用户添加COL权限
	 * @param rs_list
	 */
	public void addUserCol(List<UsUserColPrivInfo> col_list) {
		dao.insert(col_list);
	}

	/**
	 * Description: 删除用户数据源信息
	 * @param soc_name
	 */
	public int deleteUserCOLBySocName(String soc_name) {
		return dao.deleteUserCOLBySocName(soc_name);
	}

	/** 
	 * Description: 删除已经过期的用户col临时权限
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteColTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteColTempPriv(user_id,dt_datetime);
	}

	/** 
	 * Description:  删除用户临时权限
	 * @param user_id 
	 */
	public void deleteAllTempPriv(String user_id) {
		dao.deleteAllTempPriv(user_id);
	}
	
	/** 
	 * Description:  删除用户临时权限
	 * @param user_id 
	 */
	public void deleteTempPriv(String user_id,String soc_name,String tbl_name,String col_name) {
		dao.deleteTempPriv(user_id,soc_name,tbl_name,col_name);
	}

	/** 
	 * Description: 根据主键查询一条记录
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param col_name
	 * @param priv_type
	 * @return 
	 */
	public UsUserColPrivInfo getInfoByKey(String user_id, String soc_name,
			String table_name, String col_name, PRIV_TYPE priv_type) {
		return dao.getInfoByKey(user_id, soc_name,
			 table_name, col_name, priv_type);
	}
}