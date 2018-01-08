/**
 * Title: WkWorkProcessDaoService.java
 * File Description: �����������̱�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-31
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:�����������̱�
 * @author AutoGen
 */
public class WkWorkProcessDaoService {
	@Inject private WkWorkProcessDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param WkWorkProcessInfo info
	 * @return WkWorkProcessInfo
	 */
	public WkWorkProcessInfo getInfoByKey(WkWorkProcessInfo info) {
		return dao.get(info);
	}
	
	/**
	 * Description: ������ˮ�Ų�ѯ������¼
	 * @param pend_work_seq
	 * @return
	 */
	public List<WkWorkProcessInfo> getInfoListByKey(String pend_work_seq){
		return dao.getInfoListByKey(pend_work_seq);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param WkWorkProcessInfo info
	 * @return WkWorkProcessInfo
	 */
	public WkWorkProcessInfo getInfoByKeyForUpdate(WkWorkProcessInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param WkWorkProcessInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkProcessInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<WkWorkProcessInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkProcessInfo> infos) {
		return dao.insert(infos);
	}

}