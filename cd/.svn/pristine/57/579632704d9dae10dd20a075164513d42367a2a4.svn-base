/**
 * Title: DcDictDao.java
 * File Description: 数据字典信息表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dc.dao;

import java.util.List;

import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:数据字典信息表
 * @author AutoGen
 */
abstract class DcDictDao extends EntityDao<DcDictInfo> {

	
	/** 
	 * Description: 根据关键词到domain_name和domian_cn_name中查询结果集
	 * @param keyword 关键词	
	 * @param start_recd 起始记录数
	 * @param limit_recd 查询条数
	 * @return 
	 */
	@SqlParam(sql="select domain_name, domain_cn_name, fld_type, fld_length, fld_scale, enu_yn_flag from dc_dict where ((domain_name like '%${keyword1}%') or (domain_cn_name like '%${keyword2}%')) order by domain_name",dynamic=true)
    abstract List<DcDictInfo> pageDict(String keyword1,String keyword2,int start_recd, int limit_recd);

	/** 
	 * Description: 获取查询总条数
	 * @param keyword 关键词	
	 * @return 
	 */
	@SqlParam(sql="select count(*) from dc_dict where ((domain_name like '%${keyword1}%') or (domain_cn_name like '%${keyword2}%'))",dynamic=true)
	abstract int getCount(String keyword1, String keyword2);
	
	/** 
	 * Description:  根据domain_name查询数据字典详细信息
	 * @param domain_name 域名称
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract DcDictInfo getDictInfo(String domain_name);
	
	/** 
	 * Description: 根据domain_name查询数据字典记录数
	 * @param domain_name 域名称
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int countByDomainName(String domain_name);

	
	/** 
	 * Description: 根据domain_cn_name查询数据字典记录数
	 * @param domain_cn_name 域中文名称
	 * @return 
	 */
	@SqlParam(condition="domain_cn_name=:domain_cn_name")
	abstract int countByDomainCnName(String domain_cn_name);

}