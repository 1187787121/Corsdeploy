/**
 * Title: UuSocDao.java
 * File Description: 数据源关联表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:数据源关联表
 * @author AutoGen
 */
abstract class UuSocDao extends EntityDao<UuSocInfo> {

	/** 
	 * Description: 根据UUID查询多条记录
	 * @param soc_uuid
	 * @return 
	 */
	@SqlParam(condition = "SOC_UUID=:soc_uuid")
	abstract List<UuSocInfo> queryListInfoByUU(String soc_uuid);

	/** 
	 * Description: 根据UUID删除多条记录
	 * @param soc_uuid
	 * @return 
	 */
	@SqlParam(condition = "SOC_UUID=:soc_uuid")
	abstract int deleteListByUU(String soc_uuid);
	
}