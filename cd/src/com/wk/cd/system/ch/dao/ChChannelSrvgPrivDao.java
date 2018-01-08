/**
 * Title: ChChannelSrvgPrivDao.java
 * File Description: 渠道服务组权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.ch.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.ch.info.*;

/**
 * Class description:渠道服务组权限配置表
 * @author AutoGen
 */
abstract class ChChannelSrvgPrivDao extends EntityDao<ChChannelSrvgPrivInfo> {

	/** 
	 * Description: 删除渠道服务组权限
	 * @param channel_code 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract void deleteByChannelCode(String channel_code);

	/** 
	 * Description: 根据渠道编码查询服务组列表
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(querySet={"SRVG_CODE"},condition="CHANNEL_CODE=:channel_code")
	abstract List<String> listSrvgByChannel(String channel_code);

	/** 
	 * Description: 根据 渠道和服务组编码查询总数
	 * @param channel_code
	 * @param srvg_code
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code and SRVG_CODE=:srvg_code")
	abstract int countByChannelAndSrvg(String channel_code, String srvg_code);

	/** 
	 * Description: 查询渠道的所有服务组权限
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract DBIterator<ChChannelSrvgPrivInfo> iteratorAllSrvgPriv(String channel_code);
}