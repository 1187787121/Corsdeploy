/**
 * Title: WkDetailRegisterDaoService.java
 * File Description: ������������ϸ�ǼǱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-23
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:������������ϸ�ǼǱ�
 * @author AutoGen
 */
public class WkDetailRegisterDaoService {
	@Inject
	private WkDetailRegisterDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param WkDetailRegisterInfo info
	 * @return WkDetailRegisterInfo
	 */
	public WkDetailRegisterInfo getInfoByKey(WkDetailRegisterInfo info) {
		WkDetailRegisterInfo info1 = dao.get(info);
		if (Assert.isEmpty(info1)) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDetailRegisterInfo.TABLECN).addScene("FIELD",
					info.getPend_work_seq());
		}
		return info1;
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param WkDetailRegisterInfo info
	 * @return WkDetailRegisterInfo
	 */
	public WkDetailRegisterInfo getInfoByKeyForUpdate(WkDetailRegisterInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param WkDetailRegisterInfo info
	 * @return int
	 */
	public int insertInfo(WkDetailRegisterInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<WkDetailRegisterInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkDetailRegisterInfo> infos) {
		return dao.insert(infos);
	}

}