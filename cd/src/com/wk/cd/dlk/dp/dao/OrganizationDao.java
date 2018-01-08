/**
 * Title: OrganizationDao.java
 * File Description: ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */
package com.wk.cd.dlk.dp.dao;

import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:������
 * @author AutoGen
 */
abstract class OrganizationDao extends EntityDao<OrganizationInfo> {

	/** 
	 * Description: ���ݻ���������ѯ������Ϣ
	 * @param orgsyscoding
	 * @return 
	 */
	@SqlParam(condition="ORGSYSCODING=:orgsyscoding")
	abstract OrganizationInfo getOrgByOrgsyscoding(String orgsyscoding);

	/** 
	 * Description: ��ѯ���л����б�
	 * @return 
	 */
	@SqlParam(querySet={"ORGID","ORGNAME"})
	abstract DBIterator<OrganizationInfo> iteratorAllOrganization();
}