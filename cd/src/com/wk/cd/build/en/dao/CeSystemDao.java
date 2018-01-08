/**
 * Title: CeSystemDao.java
 * File Description: 应用系统表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:应用系统表
 * @author AutoGen
 */
abstract class CeSystemDao extends EntityDao<CeSystemInfo> {

	/**
	 * Description: 根据系统名查询模版数量
	 * @param sys_name
	 * @return
	 */
	@SqlParam(condition = "SYS_NAME =:sys_name")
	abstract int countBySystemName(String sys_name);
	
	/**
	 * Description: 分页查询所有应用系统
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	@SqlParam(sql="SELECT * FROM CE_SYSTEM WHERE (SYS_NAME LIKE '%${sys_name}%') ORDER BY CREATE_BK_DATE DESC, CREATE_BK_TIME DESC",dynamic=true)
	abstract List<CeSystemInfo> pageAllSystem(String sys_name,int start_recd,int limit_recd);
	/**
	 * 
	 * Description: 获得系统总数
	 * @param sys_name
	 * @return
	 */
	@SqlParam(sql="Select COUNT(1) from (select ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,count(DISTINCT es.ENV_NAME) rele_env_num,count(DISTINCT en.SERVER_NAME) mount_num from ce_system ser LEFT JOIN ce_environment es on ser.SYS_NAME=es.SYS_NAME LEFT JOIN ce_environment_server en on es.ENV_NAME=en.ENV_NAME where (ser.SYS_NAME LIKE '%${sys_name}%') GROUP BY ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE) aa",dynamic=true)
	abstract int countAllSystem(String sys_name);

	/** 
	 * Description: 分页查询所用应用系统并按照传入参数排序
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,count(DISTINCT es.ENV_NAME) rele_env_num,count(DISTINCT en.SERVER_NAME) mount_num,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME from ce_system ser LEFT JOIN ce_environment es on ser.SYS_NAME=es.SYS_NAME LEFT JOIN (SELECT SERVER_NAME, ENV_NAME FROM ce_environment_server GROUP BY SERVER_NAME, ENV_NAME) en ON es.ENV_NAME = en.ENV_NAME where ((ser.SYS_NAME LIKE '%${sys_name}%') OR (ser.SYS_CN_NAME LIKE '%${sys_cn_name}%')) GROUP BY ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME ORDER BY ser.CREATE_BK_DATE DESC,ser.CREATE_BK_TIME DESC",dynamic=true)
	abstract List<PageSystemListBean> pageSystemListDefault(String sys_name, String sys_cn_namem, int start_recd, int limit_recd );

	/** 
	 * Description: 分页查询所用应用系统并按照传入参数排序
	 * @param order_col_name
	 * @param order_type_str
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME,count(DISTINCT es.ENV_NAME) rele_env_num,count(DISTINCT en.SERVER_NAME) mount_num from ce_system ser LEFT JOIN ce_environment es on ser.SYS_NAME=es.SYS_NAME LEFT JOIN (SELECT SERVER_NAME, ENV_NAME FROM ce_environment_server GROUP BY SERVER_NAME, ENV_NAME) en ON es.ENV_NAME = en.ENV_NAME where ((ser.SYS_NAME LIKE '%${sys_name}%') OR (ser.SYS_CN_NAME LIKE '%${sys_cn_name}%')) GROUP BY ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME order by ${order_col_name} ${order_type_str}",dynamic=true)
	abstract List<PageSystemListBean> pageSystemList(String sys_name,String sys_cn_name,String order_col_name, String order_type_str,int start_recd, int limit_recd);

	/** 
	 * Description: 查询所用应用系统并按照传入参数排序
	 * @param sys_name
	 * @param sys_cn_name
	 * @return 
	 */
	@SqlParam(sql="select ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,count(DISTINCT es.ENV_NAME) rele_env_num,count(DISTINCT en.SERVER_NAME) mount_num,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME from ce_system ser LEFT JOIN ce_environment es on ser.SYS_NAME=es.SYS_NAME LEFT JOIN (SELECT SERVER_NAME, ENV_NAME FROM ce_environment_server GROUP BY SERVER_NAME, ENV_NAME) en ON es.ENV_NAME = en.ENV_NAME where ((ser.SYS_NAME LIKE '%${sys_name}%') OR (ser.SYS_CN_NAME LIKE '%{sys_cn_name}%')) GROUP BY ser.SYS_NAME,ser.SYS_CN_NAME,ser.SYS_TYPE,ser.CREATE_BK_DATE,ser.CREATE_BK_TIME ORDER BY ser.CREATE_BK_DATE DESC,ser.CREATE_BK_TIME DESC",dynamic=true)
	abstract List<PageSystemListBean> getSystemList(String sys_name,String sys_cn_name);

	/** 
	 * Description: 更新应用系统表的信息
	 * @param sys_name
	 * @param sys_cn_name
	 * @param sys_bk_desc
	 * @param sys_type
	 * @param dtbs_bk_date
	 * @param dtbs_bk_time
	 * @param org_user_id
	 * @return 
	 */
	@SqlParam(updateSet = {"SYS_CN_NAME","SYS_BK_DESC","SYS_TYPE","MODIFY_BK_DATE","MODIFY_BK_TIME","MODIFY_USER_ID"},condition = "PK")
	abstract int updateSystemMsgByKey(String sys_cn_name, String sys_bk_desc, SYS_TYPE sys_type, JaDate modify_bk_date, JaTime modify_bk_time,String modify_user_id,String sys_name);

	/** 
	 * Description: 
	 * @return 
	 */
	@SqlParam(condition = "1=1", orderBy = "SYS_NAME")
	abstract List<CeSystemInfo> getSystemInfo();
}