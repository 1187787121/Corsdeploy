/**
 * Title: OrganizationDao.java
 * File Description: 机构表
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
 * Class description:机构表
 * @author AutoGen
 */
abstract class OrganizationDao extends EntityDao<OrganizationInfo> {

	/** 
	 * Description: 根据机构层次码查询机构信息
	 * @param orgsyscoding
	 * @return 
	 */
	@SqlParam(condition="ORGSYSCODING=:orgsyscoding")
	abstract OrganizationInfo getOrgByOrgsyscoding(String orgsyscoding);

	/** 
	 * Description: 查询所有机构列表
	 * @return 
	 */
	@SqlParam(querySet={"ORGID","ORGNAME"})
	abstract DBIterator<OrganizationInfo> iteratorAllOrganization();
}