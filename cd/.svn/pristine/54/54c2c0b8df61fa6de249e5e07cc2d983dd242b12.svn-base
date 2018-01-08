/**
 * Title: SysEnuDao.java
 * File Description: 系统枚举dao 
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-27
 */
package com.wk.cd.system.dc.dao;

import com.wk.db.DBService;
import com.wk.db.SqlParam;


/**
 * Class description:系统枚举dao
 * @author AutoGen
 */
@DBService
abstract class SysEnuDao{

	/** 
	 * Description: 新增vframe系统枚举表数据
	 * @param tablename 表名
	 * @param fieldname 字段名
	 * @param code 代码
	 * @param value 值
	 * @param descript 
	 */
	@SqlParam(sql="insert sys_vframe_enumvalue values(:tablename,:fieldname,:code,:value,:descript)")
	abstract int insertSysEnu(String tablename, String fieldname,
			String code, int value, String descript);

	/** 
	 * Description: 删除vframe系统枚举表数据
	 * @param domain_name 域名称
	 */
	@SqlParam(sql="delete from sys_vframe_enumvalue where fieldname=:fieldname")
	abstract int deleteEnuList(String fieldname);
}