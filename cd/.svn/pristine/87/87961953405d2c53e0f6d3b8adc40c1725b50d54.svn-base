/**
 * Title: UsUserSocPrivDaoService.java
 * File Description: 用户数据源权限表
 * @copyright: 2014
 * @company: COSOCWORK
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
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:用户数据源权限表
 * @author AutoGen
 */
public class UsUserSocPrivDaoService {
	@Inject
	private UsUserSocPrivDao dao;
	@Inject
	private UsUserDaoService usUserDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserSocPrivInfo info
	 * @return UsUserSocPrivInfo
	 */
	public UsUserSocPrivInfo getInfoByKey(UsUserSocPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserSocPrivInfo info
	 * @return UsUserSocPrivInfo
	 */
	public UsUserSocPrivInfo getInfoByKeyForUpdate(UsUserSocPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 修改INfo
	 * @param UsUserSocPrivInfo info
	 * @return UsUserSocPrivInfo
	 */
	public int updateInfo(UsUserSocPrivInfo info) {
		return dao.update(info);
	}
	
	/**
	 * 插入一条记录
	 * @param UsUserSocPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserSocPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserSocPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserSocPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 检查用户的数据源权限是否不存在
	 * @param user_id
	 * @param soc_name
	 */
	public void checkUserSocPrivNotExist(String user_id, String soc_name) {
		if (dao.countByUserSocPriv(user_id, soc_name) > 0) {
			UsUserInfo info = new UsUserInfo();
			info.setUser_id(user_id);
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserSocPrivInfo.TABLECN).addScene("FIELD",
					"用户" + usUserDaoService.getInfoByKey(info).getUser_cn_name() + "的数据源权限" + soc_name);
		}
	}

	/**
	 * Description: 检查用户的数据源权限是否存在
	 * @param user_id
	 * @param soc_name
	 */
	public void checkUserSocPrivExist(String user_id, String soc_name) {
		if (dao.countByUserSocPriv(user_id, soc_name) == 0) {
			UsUserInfo info = new UsUserInfo();
			info.setUser_id(user_id);
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserSocPrivInfo.TABLECN).addScene("FIELD",
					"用户" + usUserDaoService.getInfoByKey(info).getUser_cn_name() + "的数据源权限" + soc_name);
		}
	}

	/**
	 * Description: 删除用户的数据源权限
	 * @param user_id
	 * @param soc_list
	 */
	public void deleteUserSocPriv(String user_id, List<String> soc_list) {
		UsUserSocPrivInfo info = new UsUserSocPrivInfo();
		for (String soc_name : soc_list) {
			checkUserSocPrivExist(user_id, soc_name);
			info.setUser_id(user_id);
			info.setSoc_name(soc_name);
			dao.delete(info);
		}
	}

	/**
	 * Description: 查询用户被禁止的数据源权限
	 * @param user_id
	 * @return
	 */
	public List<String> queryUserAfSocPriv(String user_id) {
		return dao.queryUserAfSocPriv(user_id);
	}

	/**
	 * Description: 查询用户的临时数据源权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserSocPrivInfo> queryUserTempSocPriv(String user_id) {
		List<UsUserSocPrivInfo> priv_list=new ArrayList<UsUserSocPrivInfo>();
		DBIterator<UsUserSocPrivInfo> iterator=dao.queryUserTempSocPriv(user_id);
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
	 * Description: 删除用户的所有数据源权限
	 * @param user_id
	 */
	public void deleteAllSocByUserId(String user_id) {
		dao.deleteAllSocByUserId(user_id);
	}
	
	/**
	 * Description: 删除用户数据源信息
	 * @param soc_name
	 */
	public int deleteUserSocBySocName(String soc_name) {
		return dao.deleteUserSocBySocName(soc_name);
	}

	/**
	 * Description: 用户添加数据源权限
	 * @param rs_list
	 */
	public void addUserSoc(List<UsUserSocPrivInfo> soc_list) {
		dao.insert(soc_list);
	}

	/**
	 * 
	 * Description: 根据用户ID列表获得用户数据源列表
	 * @param dept_ids
	 * @return
	 */

	public List<String> querySocInfo(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.querySocByIds(user_ids_str);
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
	 * Description: 根据用户ID列表获得用户数据源列表
	 * @param dept_ids
	 * @return
	 */
	public List<String> querySocPrivByUserId(List<String> user_ids) {
		List<String> soc_list=new ArrayList<String>();
		Map<String, String> soc_map=new HashMap<String, String>();
		for (String user_id : user_ids) {
			DBIterator<String> soc_iterator = dao.iteratorUserSocPriv(user_id);
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

	/** 
	 * Description: 删除已经过期的用户数据源临时权限
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteSocTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteSocTempPriv(user_id,dt_datetime);
	}

	/** 
	 * Description: 检查用户临时权限是否已经存在相应记录
	 * @param user_id
	 * @param soc_name
	 * @return 
	 */
	public boolean existTempPriv(String user_id, String soc_name) {
		if(dao.countTempPriv(user_id,soc_name)==0){
			return false;
		}else{
			return true;
		}
	}

	/** 
	 * Description: 删除用户临时权限
	 * @param user_id
	 * @param soc_name 
	 */
	public void deleteTempPriv(String user_id, String soc_name) {
		dao.deleteTempPriv(user_id,soc_name);
	}

}