/**
 * Title: WkDetailRegisterDaoService.java
 * File Description: 待处理任务明细登记表
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
 * Class description:待处理任务明细登记表
 * @author AutoGen
 */
public class WkDetailRegisterDaoService {
	@Inject
	private WkDetailRegisterDao dao;

	/**
	 * 根据主键查询一条记录
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
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkDetailRegisterInfo info
	 * @return WkDetailRegisterInfo
	 */
	public WkDetailRegisterInfo getInfoByKeyForUpdate(WkDetailRegisterInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkDetailRegisterInfo info
	 * @return int
	 */
	public int insertInfo(WkDetailRegisterInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkDetailRegisterInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkDetailRegisterInfo> infos) {
		return dao.insert(infos);
	}

}