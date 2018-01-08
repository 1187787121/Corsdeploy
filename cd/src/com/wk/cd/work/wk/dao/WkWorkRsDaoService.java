/**
 * Title: WkWorkRsDaoService.java
 * File Description: ������Դ���ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:������Դ���ñ�
 * @author AutoGen
 */
public class WkWorkRsDaoService {
	@Inject
	private WkWorkRsDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param WkWorkRsInfo info
	 * @return WkWorkRsInfo
	 */
	public WkWorkRsInfo getInfoByKey(WkWorkRsInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param WkWorkRsInfo info
	 * @return WkWorkRsInfo
	 */
	public WkWorkRsInfo getInfoByKeyForUpdate(WkWorkRsInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param WkWorkRsInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkRsInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<WkWorkRsInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkRsInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ������������ѯ��Դ������Ϣ
	 * @param work_code �������
	 * @return ��Դ������Ϣ
	 */
	public List<WkWorkRsInfo> getWorkRsList(String work_code) {
		return dao.getWorkRsList(work_code);
	}

	/**
	 * �����������ɾ����Ӧ������Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	public int deleteWorkByWorkCode(String work_code) {
		return dao.deleteWorkByWorkCode(work_code);
	}

	/**
	 * ����������Դ���ñ�
	 * @param work_code �������
	 * @param rs_list ��Դ�б�
	 * @return ��������
	 */
	public int updateWorkByWrokCode(String work_code, List<WkWorkRsInfo> infos) {
		if (!Assert.isEmpty(dao.getWorkRsList(work_code))) {
			dao.deleteWorkByWorkCode(work_code);
		}
		return dao.insert(infos);
	}
}