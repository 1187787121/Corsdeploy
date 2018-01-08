/**
 * Title: UsUserRsPrivDaoService.java
 * File Description: �û���ԴȨ�ޱ�
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
 * Class description:�û���ԴȨ�ޱ�
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
	 * ����������ѯһ����¼
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public UsUserRsPrivInfo getInfoByKey(UsUserRsPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public UsUserRsPrivInfo getInfoByKeyForUpdate(UsUserRsPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * �޸�INfo
	 * @param UsUserRsPrivInfo info
	 * @return UsUserRsPrivInfo
	 */
	public int updateInfo(UsUserRsPrivInfo info) {
		return dao.update(info);
	}
	
	/**
	 * ����һ����¼
	 * @param UsUserRsPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserRsPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsUserRsPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserRsPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ɾ���û���������ԴȨ��
	 * @param user_id
	 */
	public void deleteAllRsByUserId(String user_id) {
		dao.deleteAllRsByUserId(user_id);
	}
	
	/**
	 * Description: ɾ���û�������������ԴȨ��
	 * @param user_id
	 */
	public void deleteAllTualRsByUserId(String user_id) {
		dao.deleteAllTualRsByUserId(user_id);
	}

	/**
	 * Description: �û������ԴȨ��
	 * @param rs_list
	 */
	public void addUserRs(List<UsUserRsPrivInfo> rs_list) {
		dao.insert(rs_list);
	}

	/**
	 * �������Listת��Ϊ�ַ���
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
	 * Description: �����û�ID�б����û���Դ�б�
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
	 * Description: ɾ���Ѿ����ڵ��û���Դ��ʱȨ��
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteRsTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteRsTempPriv(user_id,dt_datetime);
	}
}