/**
 * Title: UuSocDao.java
 * File Description: ����Դ������
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
 * Class description:����Դ������
 * @author AutoGen
 */
abstract class UuSocDao extends EntityDao<UuSocInfo> {

	/** 
	 * Description: ����UUID��ѯ������¼
	 * @param soc_uuid
	 * @return 
	 */
	@SqlParam(condition = "SOC_UUID=:soc_uuid")
	abstract List<UuSocInfo> queryListInfoByUU(String soc_uuid);

	/** 
	 * Description: ����UUIDɾ��������¼
	 * @param soc_uuid
	 * @return 
	 */
	@SqlParam(condition = "SOC_UUID=:soc_uuid")
	abstract int deleteListByUU(String soc_uuid);
	
}