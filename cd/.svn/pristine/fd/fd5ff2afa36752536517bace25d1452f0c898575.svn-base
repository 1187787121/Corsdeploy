/**
 * Title: RsOptDao.java
 * File Description: 资源操作信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-5-27
 */
package com.wk.cd.system.rs.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.rs.info.*;

/**
 * Class description:资源操作信息表
 * @author AutoGen
 */
abstract class RsOptDao extends EntityDao<RsOptInfo> {

	/** 
	 * 根据资源编码获取下级操作列表
	 * @param rs_code
	 * @return 
	 */
	@SqlParam(condition="BL_RS_CODE=:rs_code and RCD_STATE=1 order by OPT_CODE")
	abstract List<RsOptInfo> getSubOptList(String rs_code);

	/** 
	 * 查询所有操作列表
	 * @return 
	 */
	@SqlParam(condition="RCD_STATE=1 order by OPT_CODE")
	abstract DBIterator<RsOptInfo> iteratorAllRsOpt();
}