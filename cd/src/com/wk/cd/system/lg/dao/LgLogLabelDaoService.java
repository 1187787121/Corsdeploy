/**
 * Title: LgLogLabelDaoService.java
 * File Description: ��־��Ǽ����
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-23
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:��־��Ǽ����
 * @author AutoGen
 */
public class LgLogLabelDaoService {
	@Inject
	private LgLogLabelDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param LgLogLabelInfo info
	 * @return LgLogLabelInfo
	 */
	public LgLogLabelInfo getInfoByKey(LgLogLabelInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param LgLogLabelInfo info
	 * @return LgLogLabelInfo
	 */
	public LgLogLabelInfo getInfoByKeyForUpdate(LgLogLabelInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param LgLogLabelInfo info
	 * @return int
	 */
	public int insertInfo(LgLogLabelInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<LgLogLabelInfo>
	 * @return int
	 */
	public int insertListInfo(List<LgLogLabelInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ����һ����¼
	 * @param info
	 * @return
	 */
	public int updateInfo(LgLogLabelInfo info) {
		return dao.update(info);
	}

	/**
	 * ɾ��һ����¼
	 * @param info ɾ��һ����¼
	 * @return
	 */
	public int deleteInfo(LgLogLabelInfo info) {
		return dao.delete(info);
	}

	/**
	 * ��ѯ�����ˮ��
	 * @param user_id �û���
	 * @param log_label ���ڱ�Ǽ���
	 * @return ��־��ˮ��
	 */
	public DBIterator<String> getLogWorkSeqByLabel(String user_id, int log_label) {
		return dao.getLogWorkSeqByLabel(user_id, log_label);
	}

}