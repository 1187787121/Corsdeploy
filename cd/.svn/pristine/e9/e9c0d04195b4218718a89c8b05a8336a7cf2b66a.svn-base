/**
 * Title: ChChannelDao.java
 * File Description: 渠道定义表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.ch.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.system.ch.info.*;

/**
 * Class description:渠道定义表
 * @author AutoGen
 */
abstract class ChChannelDao extends EntityDao<ChChannelInfo> {

	/** 
	 * Description: 分页查询渠道信息列表
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(condition="RCD_STATE=1")
	abstract List<ChChannelInfo> pageChannel(int start_recd, int limit_recd);

	/** 
	 * Description: 查询渠道列表总数
	 * @return 
	 */
	@SqlParam(condition="RCD_STATE=1")
	abstract int countChannel();

	/** 
	 * Description: 查询渠道编码的总数
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countByChannelCode(String channel_code);

	/** 
	 * Description:  查询渠道中文名称的总数
	 * @param channel_cn_name
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CN_NAME=:channel_cn_name")
	abstract int countByChannelCnName(String channel_cn_name);

	/** 
	 * Description: 删除渠道编码（rcd_state=2）
	 *  * @param rcd_state 
	 * @param channel_code 
	 */
	@SqlParam(updateSet={"RCD_STATE"},condition="PK")
	abstract void updateRcdState(RCD_STATE rcd_state,String channel_code);

	/** 
	 * Description: 删除渠道编码
	 * @param channel_code 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract void deleteChannelCode(String channel_code);
}