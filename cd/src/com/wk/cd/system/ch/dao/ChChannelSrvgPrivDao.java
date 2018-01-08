/**
 * Title: ChChannelSrvgPrivDao.java
 * File Description: ����������Ȩ�����ñ�
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
 * Class description:����������Ȩ�����ñ�
 * @author AutoGen
 */
abstract class ChChannelSrvgPrivDao extends EntityDao<ChChannelSrvgPrivInfo> {

	/** 
	 * Description: ɾ������������Ȩ��
	 * @param channel_code 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract void deleteByChannelCode(String channel_code);

	/** 
	 * Description: �������������ѯ�������б�
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(querySet={"SRVG_CODE"},condition="CHANNEL_CODE=:channel_code")
	abstract List<String> listSrvgByChannel(String channel_code);

	/** 
	 * Description: ���� �����ͷ���������ѯ����
	 * @param channel_code
	 * @param srvg_code
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code and SRVG_CODE=:srvg_code")
	abstract int countByChannelAndSrvg(String channel_code, String srvg_code);

	/** 
	 * Description: ��ѯ���������з�����Ȩ��
	 * @param channel_code
	 * @return 
	 */
	@SqlParam(condition="CHANNEL_CODE=:channel_code")
	abstract DBIterator<ChChannelSrvgPrivInfo> iteratorAllSrvgPriv(String channel_code);
}