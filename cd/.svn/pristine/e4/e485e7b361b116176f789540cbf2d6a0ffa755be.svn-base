/**
 * Title: UsUserSqlPrivDaoService.java
 * File Description: 用户SQL操作权限表
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
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:用户SQL操作权限表
 * @author AutoGen
 */
public class UsUserSqlPrivDaoService {
	@Inject
	private UsUserSqlPrivDao dao;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserSqlPrivInfo info
	 * @return UsUserSqlPrivInfo
	 */
	public UsUserSqlPrivInfo getInfoByKey(UsUserSqlPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁	
	 * @param UsUserSqlPrivInfo info
	 * @return UsUserSqlPrivInfo
	 */
	public UsUserSqlPrivInfo getInfoByKeyForUpdate(UsUserSqlPrivInfo info) {
		return dao.getForUpdate(info);
	}
	
	/**
	 * 修改INfo
	 * @param UsUserSqlPrivInfo info
	 * @return UsUserSqlPrivInfo
	 */
	public int updateInfo(UsUserSqlPrivInfo info) {
		return dao.update(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserSqlPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserSqlPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserSqlPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserSqlPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 检查用户的SQL操作权限是否不存在
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkUserSqlPrivNotExist(String dprl_code, String soc_name,
			String tbl_name) {
		if (dao.countByUserSqlPriv(dprl_code, soc_name, tbl_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserSqlPrivInfo.TABLECN).addScene("FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code+"]" + "数据源" + soc_name + "表名" + tbl_name);
		}
	}

	/**
	 * Description: 检查用户的SQL操作权限是否存在
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkUserSqlPrivExist(String user_id, String soc_name,
			String tbl_name) {
		UsUserSqlPrivInfo info = new UsUserSqlPrivInfo();
		info.setUser_id(user_id);
		info.setSoc_name(soc_name);
		info.setTbl_name(tbl_name);
		if (dao.countByUserSqlPriv(user_id, soc_name, tbl_name) == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserSqlPrivInfo.TABLECN).addScene("FIELD",
					"用户" + usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]" + "数据源" + soc_name + "表名" + tbl_name);
		}
	}

	/**
	 * Description: 删除用户的SQL权限记录
	 * @param user_id
	 * @param sql_list
	 */
	public void deleteUserSqlPriv(List<UsUserPrivBean> sql_list) {
		UsUserSqlPrivInfo info = new UsUserSqlPrivInfo();
		for (UsUserPrivBean sql_info : sql_list) {
			checkUserSqlPrivExist(sql_info.getUser_id(),
					sql_info.getSoc_name(), sql_info.getTbl_name());
			info.setUser_id(sql_info.getUser_id());
			info.setSoc_name(sql_info.getSoc_name());
			info.setTbl_name(sql_info.getTbl_name());
			dao.delete(info);
		}
	}

	/**
	 * Description: 检测用户SQL权限表中是否存在该权限
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	public int countByUserSql(String user_id, String soc_name, String tbl_name) {
		int count1 = dao.countByUserSql(user_id, soc_name, tbl_name);
		return count1;
	}

	/**
	 * Description: 查询过户的临时SQL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserSqlPrivInfo> queryUserTempSqlPriv(String user_id) {
		List<UsUserSqlPrivInfo> priv_list = new ArrayList<UsUserSqlPrivInfo>();
		
		DBIterator<UsUserSqlPrivInfo> iterator=dao.queryUserTempSqlPriv(user_id);
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
	 * Description: 根据用户名查询SQL操作权限列表
	 * @param user_id
	 * @return
	 */
	public List<UsUserSqlPrivInfo> querySqlPrivByUserId(String user_id) {
		return dao.querySqlPrivByUserId(user_id);
	}

	/**
	 * Description: 删除用户的所有SQL权限
	 * @param user_id
	 */
	public void deleteAllSqlByUserId(String user_id) {
		dao.deleteAllSqlByUserId(user_id);
	}

	/**
	 * Description: 用户添加SQL权限
	 * @param rs_list
	 */
	public void addUserSql(List<UsUserSqlPrivInfo> sql_list) {
		dao.insert(sql_list);
	}

	/**
	 * Description: 删除用户数据源SQL信息
	 * @param soc_name
	 */
	public int deleteUserSQLBySocName(String soc_name) {
		return dao.deleteUserSQLBySocName(soc_name);
	}

	/** 
	 * Description: 删除已经过期的用户sql临时权限
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteSqlTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteSqlTempPriv(user_id,dt_datetime);
	}

	/** 
	 * Description: 删除用户临时权限
	 * @param user_id 
	 */
	public void deleteAllTempPriv(String user_id) {
		dao.deleteAllTempPriv(user_id);
	}
	
	/** 
	 * Description: 删除用户临时权限
	 * @param user_id 
	 */
	public void deleteTempPriv(String user_id,String soc_name,String tbl_name) {
		dao.deleteTempPriv(user_id,soc_name,tbl_name);
	}
	
	/**
	 * 根据主键查询一条记录
	 * @param UsUserSqlPrivInfo info
	 * @return UsUserSqlPrivInfo
	 */
	public UsUserSqlPrivInfo getInfoByKey(String user_id,String soc_name,String table_name,PRIV_TYPE priv_type) {
		UsUserSqlPrivInfo info = new UsUserSqlPrivInfo();
		info.setUser_id(user_id);
		info.setSoc_name(soc_name);
		info.setTbl_name(table_name);
		info.setPriv_type(priv_type);
		return dao.get(info);
	}
}