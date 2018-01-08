/**
 * Title: ChChannelSrvPrivDao.java
 * File Description: 渠道服务权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.ch.dao;

import com.wk.db.*;
import com.wk.cd.system.ch.info.*;

/**
 * Class description:渠道服务权限配置表
 * @author AutoGen
 */
abstract class ChChannelSrvPrivDao extends EntityDao<ChChannelSrvPrivInfo> {

	/** 
	 * Description: 删除渠道服务权限
	 * @param channel_code 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract void deleteSrvByChannelCode(String channel_code);

	/** 
	 * Description: 查询渠道的所有服务权限
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract DBIterator<ChChannelSrvPrivInfo> iteratorAllSrvPriv(String channel_code);
}