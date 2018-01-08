/**
 * Title: EnvConfigfileModDaoService.java
 * File Description: 环境配置文件变更表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.info.EnvConfigfileModInfo;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:环境配置文件变更表
 * @author AutoGen
 */
public class EnvConfigfileModDaoService {
	@Inject private EnvConfigfileModDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param EnvConfigfileModInfo info
	 * @return EnvConfigfileModInfo
	 */
	public EnvConfigfileModInfo getInfoByKey(EnvConfigfileModInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param EnvConfigfileModInfo info
	 * @return EnvConfigfileModInfo
	 */
	public EnvConfigfileModInfo getInfoByKeyForUpdate(EnvConfigfileModInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param EnvConfigfileModInfo info
	 * @return int
	 */
	public int insertInfo(EnvConfigfileModInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<EnvConfigfileModInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvConfigfileModInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据批次号及操作类型查询修改信息列表(去重)
	 * @param batch_no
	 * @param modify 
	 */
	public List<EnvConfigfileModInfo> getInfoByBatchAndFopt(String server_name, String batch_no, FOPT_TYPE fopt_type) {
		List<EnvConfigfileModInfo> list = new ArrayList<EnvConfigfileModInfo>();
        DBIterator<EnvConfigfileModInfo> iterator = dao.getInfoByBatchAndFopt(server_name, batch_no, fopt_type);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}

}