/**
 * Title: UuParamDao.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-8
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:
 * @author AutoGen
 */
abstract class UuParamDao extends EntityDao<UuParamInfo> {

	
	@SqlParam(condition = "PARAM_UUID =:param_uuid",orderBy="PHASE_NO ASC")
	abstract List<UuParamInfo> getInfoByUuid(String param_uuid);
	
	@SqlParam(condition = "PARAM_UUID =:param_uuid")
	abstract int deleteById(String param_uuid);

	/** 
	 * Description: 获取投产参数值
	 * @param param_uuid
	 * @param param_name
	 * @return 
	 */
	@SqlParam(querySet = "PARAM_VALUE", condition = "PARAM_UUID =:param_uuid AND PARAM_TYPE = 2")
	abstract List<String> getValueByKey(String param_uuid);
}