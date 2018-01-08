/**
 * Title: SvSrvSocDao.java
 * File Description: 服务数据源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.sv.info.*;

/**
 * Class description:服务数据源配置表
 * @author AutoGen
 */
abstract class SvSrvSocDao extends EntityDao<SvSrvSocInfo> {
	//根据服务名称获取一组服务数据源信息
	@SqlParam(condition = "SRV_NAME = :srv_name", orderBy = "soc_seq")
	abstract List<SvSrvSocInfo> getListInfoByName(String srv_name);
	//根据服务名称删除一组服务数据源信息
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteInfo(String srv_name);
	//按照服务名称查询记录条数
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countInfo(String srv_name);
}