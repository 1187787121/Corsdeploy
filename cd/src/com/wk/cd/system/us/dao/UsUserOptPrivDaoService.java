/**
 * Title: UsUserOptPrivDaoService.java
 * File Description: �û�����Ȩ�����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:�û�����Ȩ�����ñ�
 * @author AutoGen
 */
public class UsUserOptPrivDaoService {
	@Inject private UsUserOptPrivDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param UsUserOptPrivInfo info
	 * @return UsUserOptPrivInfo
	 */
	public UsUserOptPrivInfo getInfoByKey(UsUserOptPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsUserOptPrivInfo info
	 * @return UsUserOptPrivInfo
	 */
	public UsUserOptPrivInfo getInfoByKeyForUpdate(UsUserOptPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsUserOptPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserOptPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsUserOptPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserOptPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: �����û�ID��ѯ�û�������ֹȨ����Ϣ�б�����������Դ�Ĳ���Ȩ����Ϣ��
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptForbidPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		DBIterator<UsUserOptPrivInfo> opt_iterator=dao.queryOptForbidPrivBy(user_id);
		try {
			while (opt_iterator.hasNext()) {
				opt_priv.add(opt_iterator.next());
			}
		} finally {
			opt_iterator.close();
		}
		return opt_priv;
	}

	/** 
	 * Description: �����û�ID��ѯ�û���������Ȩ����Ϣ�б�����ֹ����Դ�Ĳ���Ȩ����Ϣ��
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptAllowPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		DBIterator<UsUserOptPrivInfo> opt_iterator=dao.queryOptAllowPrivByUser(user_id);
		try {
			while (opt_iterator.hasNext()) {
				opt_priv.add(opt_iterator.next());
			}
		} finally {
			opt_iterator.close();
		}
		return opt_priv;
	}

	/** 
	 * Description: �����û�ɾ������Ȩ��
	 * @param user_id 
	 */
	public int deleteOptPrivByUser(String user_id) {
		return dao.deleteOptPrivByUser(user_id);
	}

}