/**
 * Title: SvRemoteSrvDao.java
 * File Description: 远程服务调用配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-16
 */
package com.wk.cd.system.sv.dao;

import com.wk.db.*;
import com.wk.cd.system.sv.info.*;

/**
 * Class description:远程服务调用配置表
 * @author AutoGen
 */
abstract class SvRemoteSrvDao extends EntityDao<SvRemoteSrvInfo> {
	/**
	 * 根据服务名称查询一组远程服务调用配置
	 * @param srv_name 服务名称
	 * @return 服务名称对应的一组远程服务调用信息
	 */
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract SvRemoteSrvInfo queryInfoBySrvName(String srv_name); 
	
	/**
	 * 根据服务名称查询远程服务配置对应的正常记录条数
	 * @param srv_name 服务名称
	 * @return 正常记录的条数
	 */
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countInfoBySrvName(String srv_name);
	
	/**
	 * 根据服务名称删除一组远程服务调用配置
	 * @param srv_name 服务名称
	 * @return 删除记录条数
	 */
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteInfoBySrvName(String srv_name);
}