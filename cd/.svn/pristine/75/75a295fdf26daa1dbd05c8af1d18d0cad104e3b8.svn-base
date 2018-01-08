/**
 * Title: DcDictEnuDao.java
 * File Description: 数据字典枚举表
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
 * Class description:数据字典枚举表
 * @author AutoGen
 */
/**
 * Class Description: 
 * @author HT
 */
abstract class DcDictEnuDao extends EntityDao<DcDictEnuInfo> {

	
	/** 
	 * Description: 根据domain_name获取枚举列
	 * @param domain_name 域名称
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract List<DcDictEnuInfo> getEnuListInfo(String domain_name);

	/** 
	 * Description: 根据domain_name删除复合条件的所有枚举信息
	 * @param domain_name 域名称
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int deleteEnuList(String domain_name);

	/** 
	 * Description: 根据domain__name查询枚举记录数
	 * @param domain_name 域名称
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int countEnuByDomain(String domain_name);

}