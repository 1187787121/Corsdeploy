/**
 * Title: LgLogDownDaoService.java
 * File Description: ����������Ϣ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-29
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class description:����������Ϣ��
 * @author AutoGen
 */
public class LgLogDownDaoService {
	@Inject
	private LgLogDownDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param LgLogDownInfo info
	 * @return LgLogDownInfo
	 */
	public LgLogDownInfo getInfoByKey(LgLogDownInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param LgLogDownInfo info
	 * @return LgLogDownInfo
	 */
	public LgLogDownInfo getInfoByKeyForUpdate(LgLogDownInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param LgLogDownInfo info
	 * @return int
	 */
	public int insertInfo(LgLogDownInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<LgLogDownInfo>
	 * @return int
	 */
	public int insertListInfo(List<LgLogDownInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ��ѯ���ɵ���־�ļ���Ϣ
	 * @param user_id �û���
	 * @param crt_bk_date ��������
	 * @return ��־�ļ���Ϣ
	 */
	public DBIterator<LgLogDownInfo> getLogFileInfo(String user_id,
			JaDate crt_bk_date) {
		return dao.getLogFileInfo(user_id, crt_bk_date);
	}
}