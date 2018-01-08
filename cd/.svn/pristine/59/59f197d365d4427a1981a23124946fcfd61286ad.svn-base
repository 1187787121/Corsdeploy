/**
 * Title: CeEnvironmentDao.java
 * File Description: 环境表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:环境表
 * @author AutoGen
 */
abstract class CeEnvironmentDao
		extends EntityDao<CeEnvironmentInfo> {

	/**
	 * Description: 得到对应应用环境名的个数
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countEnvNameNum(String env_name);

	/**
	 * Description: 分页查询应用环境信息(默认时间倒序)
	 * @param sys_name
	 * @return
	 */
	@SqlParam(sql = "SELECT envir.* from CE_ENVIRONMENT envir LEFT JOIN CE_SYSTEM sys on sys.SYS_NAME=envir.SYS_NAME where ((sys.SYS_NAME LIKE '%${sys_name}%') OR (sys.SYS_CN_NAME LIKE '%${sys_cn_name}%')) ORDER BY envir.CREATE_BK_DATE ${order_type1}, envir.CREATE_BK_TIME ${order_type2}", dynamic = true)
	abstract List<CeEnvironmentInfo> pageEnvironmentDefault(String sys_name, String sys_cn_name, String order_type1, String order_type2, int start_recd, int limit_recd);

	/**
	 * Description: 分页查询应用环境信息
	 * @param sys_name
	 * @return
	 */
	@SqlParam(sql = "SELECT envir.* from CE_ENVIRONMENT envir LEFT JOIN CE_SYSTEM sys on sys.SYS_NAME=envir.SYS_NAME where ((upper(sys.SYS_NAME) LIKE '%${sys_name}%') OR (upper(sys.SYS_CN_NAME) LIKE '%${sys_cn_name}%')) ORDER BY envir.${order_col_name} ${order_type}", dynamic = true)
	abstract List<CeEnvironmentInfo> pageEnvironmentBySysName(String sys_name, String sys_cn_name, String order_col_name, String order_type, int start_recd, int limit_recd);

	/**
	 * Description: 查询应用环境个数
	 * @param sys_name
	 * @return
	 */
	@SqlParam(sql = "SELECT COUNT(1) from CE_ENVIRONMENT envir LEFT JOIN CE_SYSTEM sys on sys.SYS_NAME=envir.SYS_NAME where ((upper(sys.SYS_NAME) LIKE '%${sys_name}%') OR (upper(sys.SYS_CN_NAME) LIKE '%${sys_cn_name}%'))", dynamic = true)
	abstract int countEnvironmentBySysCnName(String sys_name, String sys_cn_name);

	/**
	 * Description: 查询应用环境个数
	 * @param sys_name
	 * @return
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract int countEnvironmentBySysName(String sys_name);

	/**
	 * Description:
	 * @param sys_name
	 * @return
	 */
	@SqlParam(sql = "SELECT * from CE_ENVIRONMENT ceen,(SELECT MAX(cee.CREATE_BK_TIME) MAXTIME from CE_ENVIRONMENT cee,(SELECT MAX(ce.CREATE_BK_DATE)as maxdate from CE_ENVIRONMENT ce WHERE ce.SYS_NAME=:sys_name) ceee where ceee.maxdate=cee.CREATE_BK_DATE) ceeen where ceeen.MAXTIME = ceen.CREATE_BK_TIME")
	abstract CeEnvironmentInfo getEnviNameBySysName(String sys_name);

	/**
	 * Description:
	 * @param sys_name
	 * @return
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract List<CeEnvironmentInfo> getEnvirListBySysName(String sys_name);

	/**
	 * Description: 获得系统环境关联
	 * @return
	 */
	@SqlParam(sql = "SELECT * FROM CE_ENVIRONMENT WHERE (SYS_NAME LIKE '%${sys_name}%') ORDER BY CREATE_BK_DATE DESC, CREATE_BK_TIME DESC", dynamic = true)
	abstract List<CeEnvironmentInfo> getEnvirList(String sys_name);

	/**
	 * Description: 根据应用系统名查询环境列表
	 * @param sys_name
	 * @return
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract List<CeEnvironmentInfo> queryEnvInfosBySys(String sys_name);

	/**
	 * Description: 得到环境中文名
	 * @param env_name
	 * @return
	 */
	@SqlParam(sql = "SELECT ENV_CN_NAME FROM CE_ENVIRONMENT WHERE ENV_NAME='${env_name}'", dynamic = true)
	abstract String getInfoByEnvCnName(String env_name);

	/**
	 * Description: 修改应用环境信息
	 * @param env_cn_name
	 * @param env_bk_desc
	 * @param ele_type
	 * @param dt_range
	 * @param env_type
	 * @param sys_name
	 * @param dtbs_bk_date
	 * @param dtbs_bk_time
	 * @param org_user_id
	 * @param env_name
	 * @return
	 */
	@SqlParam(updateSet = { "ENV_CN_NAME", "ENV_BK_DESC", "ELE_TYPE", "DT_RANGE", "ENV_TYPE", "SYS_NAME", "MODIFY_BK_DATE", "MODIFY_BK_TIME", "MODIFY_USER_ID" }, condition = "PK")
	abstract int updateEnvirMsgByKey(String env_cn_name, String env_bk_desc, String ele_type, DT_RANGE dt_range, ENV_TYPE env_type, String sys_name, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String env_name);

	/**
	 * Description: 查询所有应用环境权限
	 * @return
	 */
	@SqlParam(sql="select ce.env_name,ce.env_cn_name,cs.sys_name,cs.sys_cn_name　from  ce_environment ce,ce_system cs where ce.sys_name=cs.sys_name")
	abstract DBIterator<EnvPrivBean> queryAllEnvPriv();

	/** 
	 * Description: 获取环境的所有配置文件
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql="select CFG_BK_FNAME from CE_ENVIRONMENT ce right join CE_SYSTEM_CFG csf on ce.SYS_NAME=csf.SYS_NAME where ce.ENV_NAME=:env_name")
	abstract List<String> queryAllConfigFile(String env_name);

	/** 
	 * Description: 获得所有环境信息
	 * @return 
	 */
	@SqlParam(sql = "select * from CE_ENVIRONMENT order by ENV_NAME")
	abstract DBIterator<CeEnvironmentInfo> queryAllEnvMsg();
}