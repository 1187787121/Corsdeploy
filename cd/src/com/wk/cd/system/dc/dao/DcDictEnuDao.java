/**
 * Title: DcDictEnuDao.java
 * File Description: �����ֵ�ö�ٱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dc.dao;

import java.util.List;

import com.wk.cd.system.dc.info.DcDictEnuInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:�����ֵ�ö�ٱ�
 * @author AutoGen
 */
/**
 * Class Description: 
 * @author HT
 */
abstract class DcDictEnuDao extends EntityDao<DcDictEnuInfo> {

	
	/** 
	 * Description: ����domain_name��ȡö����
	 * @param domain_name ������
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract List<DcDictEnuInfo> getEnuListInfo(String domain_name);

	/** 
	 * Description: ����domain_nameɾ����������������ö����Ϣ
	 * @param domain_name ������
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int deleteEnuList(String domain_name);

	/** 
	 * Description: ����domain__name��ѯö�ټ�¼��
	 * @param domain_name ������
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int countEnuByDomain(String domain_name);

}