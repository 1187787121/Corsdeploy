/**
 * Title: CeServerDao.java
 * File Description: 服务器表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-1
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.PageServerBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:服务器表
 * @author AutoGen
 */
abstract class CeServerDao
		extends EntityDao<CeServerInfo> {

	/**
	 * Description: 根据名称和地址分页查询服务器
	 * @param server_name 服务器名称
	 * @param server_ip 服务器地址
	 * @param start_recd 起始记录
	 * @param limit_recd 查询条数
	 * @return
	 */
	@SqlParam(sql = "select ser.SERVER_NAME,ser.SERVER_CN_NAME,ser.SERVER_IP,ser.EXIST_AGENT_YN_FLAG,ser.AGENT_CONFIG_YN_FLAG,count(es.ENV_NAME) ENVIRONMENT_NUM,count(DISTINCT en.SYS_NAME) SYSTEM_NUM,SER.CREATE_BK_DATE,SER.CREATE_BK_TIME from ce_server ser LEFT JOIN (SELECT SERVER_NAME,ENV_NAME FROM ce_environment_server GROUP BY SERVER_NAME,ENV_NAME) es on ser.SERVER_NAME=es.SERVER_NAME LEFT JOIN ce_environment en on es.ENV_NAME=en.ENV_NAME where (ser.SERVER_NAME LIKE '%${server_name}%') and (ser.SERVER_IP LIKE '%${server_ip}%') GROUP BY ser.SERVER_NAME,ser.SERVER_CN_NAME,ser.SERVER_IP,ser.EXIST_AGENT_YN_FLAG,ser.AGENT_CONFIG_YN_FLAG,SER.CREATE_BK_DATE,SER.CREATE_BK_TIME ORDER BY ${order_col_name} ${order_type}", dynamic = true)
	abstract List<PageServerBean> pageServerByNameAndIP(String server_name,
			String server_ip, String order_col_name, String order_type,
			int start_recd, int limit_recd);

	/**
	 * Description: 根据名称和地址分页统计服务器数量
	 * @param server_name 服务器名称
	 * @param server_ip 服务器地址
	 * @return
	 */
	@SqlParam(sql = "select count(1) from ce_server where (SERVER_NAME LIKE '%${server_name}%') and (SERVER_IP LIKE '%${server_ip}%')", dynamic = true)
	abstract int countServerByNameAndIP(String server_name, String server_ip);

	/**
	 * Description: 查询所有服务器名称
	 * @param
	 * @return
	 */
	@SqlParam(querySet = { "SERVER_NAME", "SERVER_CN_NAME",
	"SERVER_IP" }, condition = "1=1")
	abstract DBIterator<ServerBean> queryAllServerName();

	/**
	 * 根据服务器名查询一条记录
	 * @param String server_name
	 * @return CeServerInfo
	 */
	@SqlParam(condition = "PK")
	abstract CeServerInfo getInfoByServerName(String server_name);

	/**
	 * Description: 根据IP模糊查询服务器信息
	 * @param server_ip
	 * @return
	 */
	@SqlParam(sql = "select * from ce_server where (SERVER_IP LIKE '%${server_ip}%')", dynamic = true)
	abstract DBIterator<CeServerInfo> queryInfoByLikeIP(String server_ip);
	
	/**
	 * Description: 根据地址统计服务器数量
	 * @param server_ip 服务器地址
	 * @return
	 */
	@SqlParam(condition = "server_ip=:server_ip")
	abstract int countServerByIP(String server_ip);

	/** 
	 * Description: 根据简称统计服务器数量
	 * @param server_cn_name
	 * @return 
	 */
	@SqlParam(condition = "SERVER_CN_NAME=:SERVER_CN_NAME")
	abstract int countByServerCnName(String server_cn_name);

	/** 
	 * Description: 查询所有记录
	 * @return 
	 */
	@SqlParam(orderBy = "SERVER_IP")
	abstract DBIterator<CeServerInfo> queryAllInfo();

	/** 
	 * Description: 查询服务器列表
	 * @param soc_ip
	 * @return 
	 */
	@SqlParam(condition = "SERVER_IP=:soc_ip")
	abstract List<CeServerInfo> getServerInfo(String soc_ip);

	/** 
	 * Description: 修改agent状态
	 * @param exist_agent_yn_flag
	 * @param agent_config_yn_flag
	 * @param server_name
	 * @return 
	 */
	@SqlParam(updateSet = { "EXIST_AGENT_YN_FLAG", "AGENT_CONFIG_YN_FLAG" }, condition = "PK")
	abstract int updateAgentStatusByKey(YN_FLAG exist_agent_yn_flag, YN_FLAG agent_config_yn_flag, String server_name);

}