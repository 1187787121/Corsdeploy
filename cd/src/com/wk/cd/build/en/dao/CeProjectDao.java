/**
 * Title: CeProjectDao.java
 * File Description: 项目表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:项目表
 * @author AutoGen
 */
abstract class CeProjectDao extends EntityDao<CeProjectInfo> {

	/** 
	 * Description: 
	 * @param project_name
	 * @return 
	 */
	@SqlParam(condition = "PROJECT_NAME =:project_name")
	abstract int countProjectName(String project_name);

	/** 
	 * Description: 分页查询项目信息
	 * @param sys_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="SELECT pj.* FROM CE_PROJECT pj LEFT JOIN CE_SYSTEM sys on sys.SYS_NAME=pj.SYS_NAME WHERE ((sys.SYS_NAME LIKE '%${sys_name}%') OR (sys.SYS_CN_NAME LIKE '%${sys_cn_name}%')) ORDER BY CREATE_BK_DATE DESC, CREATE_BK_TIME DESC",dynamic=true)
	abstract List<CeProjectInfo> pageProjectBySysName(String sys_name,String sys_cn_name,
			int start_recd, int limit_recd);

	/** 
	 * Description: 得到项目总数
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(sql="SELECT COUNT(1) FROM (select pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME,count(DISTINCT es.ENV_NAME) rel_env_num from ce_project pj LEFT JOIN ce_system sys on sys.SYS_NAME=pj.SYS_NAME LEFT JOIN ce_environment es on pj.SYS_NAME=es.SYS_NAME where ((sys.SYS_NAME LIKE '%${sys_name}%') OR (sys.SYS_CN_NAME LIKE '%${sys_cn_name}%')) GROUP BY pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME) bb",dynamic=true)
	abstract int countProjectNameBySysName(String sys_name,String sys_cn_name);

	/** 
	 * Description: 分页查询所用项目并按照传入参数排序
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME,count(DISTINCT es.ENV_NAME) rel_env_num,pj.CREATE_BK_DATE,pj.CREATE_BK_TIME from ce_project pj LEFT JOIN ce_system sys on sys.SYS_NAME=pj.SYS_NAME LEFT JOIN ce_environment es on pj.SYS_NAME=es.SYS_NAME where ((sys.SYS_NAME LIKE '%${sys_name}%') OR (sys.SYS_CN_NAME LIKE '%${sys_cn_name}%')) GROUP BY pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME,pj.CREATE_BK_DATE,pj.CREATE_BK_TIME ORDER BY CREATE_BK_DATE DESC,CREATE_BK_TIME DESC",dynamic=true)
	abstract List<CeProjectBean> pageAllProjectDefault(String sys_name, String sys_cn_name, int start_recd,int limit_recd);

	/** 
	 * Description: 分页查询所用项目并按照传入参数排序
	 * @param order_col_name
	 * @param order_type_str
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME,pj.CREATE_BK_DATE,pj.CREATE_BK_TIME,count(DISTINCT es.ENV_NAME) rel_env_num from ce_project pj LEFT JOIN ce_system sys on sys.SYS_NAME=pj.SYS_NAME LEFT JOIN ce_environment es on pj.SYS_NAME=es.SYS_NAME where ((sys.SYS_NAME LIKE '%${sys_name}%') OR (sys.SYS_CN_NAME LIKE '%${sys_cn_name}%')) GROUP BY pj.PROJECT_NAME,pj.PROJECT_SHORT_NAME,sys.SYS_CN_NAME,sys.SYS_NAME,pj.CREATE_BK_DATE,pj.CREATE_BK_TIME ORDER BY ${order_col_name} ${order_type_str}",dynamic=true)
	abstract List<CeProjectBean> pageAllProject(String sys_name, String sys_cn_name, String order_col_name,String order_type_str, int start_recd, int limit_recd);

	/** 
	 * Description: 根据系统名查找项目表
	 * @param sys_name
	 * @return 
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract List<CeProjectInfo> getInfoBySysName(String sys_name);

}