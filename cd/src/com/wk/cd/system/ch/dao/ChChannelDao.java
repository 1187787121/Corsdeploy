/**
 * Title: ChChannelDao.java
 * File Description: ���������
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
 * Class description:���������
 * @author AutoGen
 */
abstract class ChChannelDao extends EntityDao<ChChannelInfo> {

	/** 
	 * Description: ��ҳ��ѯ������Ϣ�б�
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(condition="RCD_STATE=1")
	abstract List<ChChannelInfo> pageChannel(int start_recd, int limit_recd);

	/** 
	 * Description: ��ѯ�����б�����
	 * @return 
	 */
	@SqlParam(condition="RCD_STATE=1")
	abstract int countChannel();

	/** 
	 * Description: ��ѯ�������������
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countByChannelCode(String channel_code);

	/** 
	 * Description:  ��ѯ�����������Ƶ�����
	 * @param channel_cn_name
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CN_NAME=:channel_cn_name")
	abstract int countByChannelCnName(String channel_cn_name);

	/** 
	 * Description: ɾ���������루rcd_state=2��
	 *  * @param rcd_state 
	 * @param channel_code 
	 */
	@SqlParam(updateSet={"RCD_STATE"},condition="PK")
	abstract void updateRcdState(RCD_STATE rcd_state,String channel_code);

	/** 
	 * Description: ɾ����������
	 * @param channel_code 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract void deleteChannelCode(String channel_code);
}