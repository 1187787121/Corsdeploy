/**
 * Title: UsUserRsPrivDaoService.java
 * File Description: 用户资源权限表
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

import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:用户资源权限表
 * @author AutoGen
 */
public class UsUserRsPrivDaoService {
	@Inject
	private UsUserRsPrivDao dao;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private RsResDaoService rsResDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public UsUserRsPrivInfo getInfoByKey(UsUserRsPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public UsUserRsPrivInfo getInfoByKeyForUpdate(UsUserRsPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 修改INfo
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public int updateInfo(UsUserRsPrivInfo info) {
		return dao.update(info);
	}
	
	/**
	 * 插入一条记录
	 * @param UsUserRsPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserRsPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserRsPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserRsPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 删除用户的所有资源权限
	 * @param user_id
	 */
	public void deleteAllRsByUserId(String user_id) {
		dao.deleteAllRsByUserId(user_id);
	}
	
	/**
	 * Description: 删除用户的所有永久资源权限
	 * @param user_id
	 */
	public void deleteAllTualRsByUserId(String user_id) {
		dao.deleteAllTualRsByUserId(user_id);
	}

	/**
	 * Description: 用户添加资源权限
	 * @param rs_list
	 */
	public void addUserRs(List<UsUserRsPrivInfo> rs_list) {
		dao.insert(rs_list);
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
	 * Description: 根据用户ID列表获得用户资源列表
	 * @param dept_ids
	 * @return
	 */
	public List<String> queryRsPrivByUserId(List<String> user_ids) {
		List<String> rs_list=new ArrayList<String>();
		Map<String, String> rs_map=new HashMap<String, String>();
		for (String user_id : user_ids) {
			DBIterator<String> rs_iterator = dao.iteratorUserRsPriv(user_id);
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
	 * Description: 删除已经过期的用户资源临时权限
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteRsTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteRsTempPriv(user_id,dt_datetime);
	}
}