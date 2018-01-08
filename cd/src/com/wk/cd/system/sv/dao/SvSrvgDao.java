/**
 * Title: SvSrvgDao.java
 * File Description: 服务组定义表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:服务组定义表
 * @author AutoGen
 */
abstract class SvSrvgDao extends EntityDao<SvSrvgInfo> {

	/** 
	 * Description: 分页查询服务组信息
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(condition="1=1")
	abstract List<SvSrvgInfo> pageSrvg(int start_recd, int limit_recd);

	/** 
	 * Description: 查询服务组信息总数
	 * @return 
	 */
	@SqlParam(condition="1=1")
	abstract int countSrvg();

	/** 
	 * Description: 查询服务组编码的总数
	 * @param srvg_code
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countByCode(String srvg_code);

	/** 
	 * Description: 查询服务组中文名称的总数
	 * @param srvg_cn_name
	 * @return 
	 */
	@SqlParam(condition="SRVG_CN_NAME=:srvg_cn_name")
	abstract int countBySrvgCnName(String srvg_cn_name);

	/** 
	 * Description: 查询渠道编码的总数
	 * @param srvg_code
	 * @return 
	 */
	@SqlParam(condition="PK")
	abstract int countBySrvgCode(String srvg_code);

	/** 
	 * Description: 删除服务组信息
	 * @param srvg_code 
	 */
	@SqlParam(condition="SRVG_CODE=:srvg_code")
	abstract void deleteSrvg(String srvg_code);

	/** 
	 * Description: 查询所有服务组信息
	 * @return 
	 */
	@SqlParam(condition="1=1")
	abstract DBIterator<SvSrvgInfo> iteratorAllSrvg();
}