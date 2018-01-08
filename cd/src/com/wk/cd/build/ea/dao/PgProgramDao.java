/**
 * Title: PgProgramDao.java
 * File Description: 环境方案表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:环境方案表
 * @author AutoGen
 */
abstract class PgProgramDao extends EntityDao<PgProgramInfo> {

	/** 
	 * Description: 
	 * @param env_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */

	/** 
	 * Description: 通过PROD_ID统计数量
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(condition = "PROG_ID =:prog_id")
	abstract int countByProgId(String prog_id);

	/** 
	 * Description: 查询环境方案列表默认日期排序
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql="SELECT * from PG_PROGRAM where ENV_NAME ='${env_name}' ORDER BY CRT_BK_DATE DESC, CRT_BK_TIME DESC",dynamic=true)
	abstract List<PgProgramInfo> getEnvProgByEnvNameDefault(String env_name);

	/** 
	 * Description: 查询环境方案列表并按照传入参数排序
	 * @param env_name
	 * @param order_col_name
	 * @param order_type_str
	 * @return 
	 */
	@SqlParam(sql="SELECT * from PG_PROGRAM where ENV_NAME ='${env_name}' ORDER BY ${order_col_name} ${order_type_str}",dynamic=true)
	abstract List<PgProgramInfo> getEnvProgByEnvName(String env_name,String order_col_name, String order_type_str);

	/** 
	 * Description: 通过主键ID统计数量
	 * @param prog_id
	 * @param step_id
	 * @return 
	 */
	@SqlParam(condition = "PK")
	abstract int countByKeyId(String prog_id, int step_id);

	/** 
	 * Description: 根据方案类型查看方案列表
	 * @param prog_type
	 * @return 
	 */
	@SqlParam(condition = "PROG_TYPE =:prog_type AND ENV_NAME =:env_name")
	abstract List<PgProgramInfo> getInfoByProgType(PROG_TYPE prog_type,String env_name);

	/** 
	 * Description: 修改方案基本信息
	 * @param prog_name
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(updateSet = {"PROG_NAME"},condition = "PK")
	abstract int updatePgProgramByKey(String prog_name, String prog_id);

	/** 
	 * Description: 通过环境名查询计数
	 * @param env_name
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countByEnvName(String env_name);

	/** 
	 * Description: 
	 * @param is_publish
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(updateSet = {"IS_PUBLISH"},condition = "PK")
	abstract int updatePgPublishStateByKey(IS_PUBLISH is_publish, String prog_id);

}