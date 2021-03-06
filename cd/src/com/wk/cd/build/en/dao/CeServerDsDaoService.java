/**
 * Title: CeServerDsDaoService.java
 * File Description: 服务器数据源表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.cd.build.exc.ServerSocTypeIsNotExistException;
import com.wk.cd.common.util.Assert;
import com.wk.lang.Inject;

/**
 * Class description:服务器数据源表
 * @author AutoGen
 */
public class CeServerDsDaoService {
	@Inject private CeServerDsDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param CeServerDsInfo info
	 * @return CeServerDsInfo
	 */
	public CeServerDsInfo getInfoByKey(CeServerDsInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param CeServerDsInfo info
	 * @return CeServerDsInfo
	 */
	public CeServerDsInfo getInfoByKeyForUpdate(CeServerDsInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param CeServerDsInfo info
	 * @return int
	 */
	public int insertInfo(CeServerDsInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CeServerDsInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeServerDsInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description: 根据服务器名删除记录
	 * @param server_name 服务器名
	 * @return int
	 */
	public int deleteInfoByServerName(String server_name) {
		return dao.deleteInfoByServerName(server_name);
	}
	
	/**
	 * Description: 根据服务器名称获取FTP配置数据源
	 * @param server_name
	 * @return
	 */
	public String getFtpConfigSocByServerName(String server_name){
		return dao.getFtpConfigSocByServerName(server_name);
	}
	
	/**
	 * Description: 根据服务器名称获取SHELL配置数据源
	 * @param server_name
	 * @return
	 */
	public String getShellConfigSocByServerName(String server_name){
		return dao.getShellConfigSocByServerName(server_name);
	}

	/** 
	 * Description: 根据服务器名称查询数据源
	 * @param server_name
	 * @return 
	 */
	public List<String> querySocNameByServer(String server_name) {
		return dao.querySocNameByServer(server_name);
	}

	/** 
	 * Description: 得到对应服务器数据个数
	 * @param server_name
	 * @param soc_name
	 * @return 
	 */
	public int countServerDtsource(String server_name, String soc_name) {
		
		return dao.countServerDtsourceByKey(server_name,soc_name);
	}

	/** 
	 * Description: 根据服务器名查询服务器数据源表
	 * @param server_name
	 * @return 
	 */
	public List<CeServerDsInfo> querySourceByServer(String server_name) {
		return dao.querySourceByServer(server_name);
	}
}