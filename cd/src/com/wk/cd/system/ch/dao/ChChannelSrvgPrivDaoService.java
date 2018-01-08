/**
 * Title: ChChannelSrvgPrivDaoService.java
 * File Description: 渠道服务组权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.ch.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.ch.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:渠道服务组权限配置表
 * @author AutoGen
 */
public class ChChannelSrvgPrivDaoService {
	@Inject private ChChannelSrvgPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param ChChannelSrvgPrivInfo info
	 * @return ChChannelSrvgPrivInfo
	 */
	public ChChannelSrvgPrivInfo getInfoByKey(ChChannelSrvgPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param ChChannelSrvgPrivInfo info
	 * @return ChChannelSrvgPrivInfo
	 */
	public ChChannelSrvgPrivInfo getInfoByKeyForUpdate(ChChannelSrvgPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param ChChannelSrvgPrivInfo info
	 * @return int
	 */
	public int insertInfo(ChChannelSrvgPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<ChChannelSrvgPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<ChChannelSrvgPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 删除渠道服务组权限
	 * @param channel_code 
	 */
	public void deleteSrvgPriv(String channel_code) {
		dao.deleteByChannelCode(channel_code);
	}

	/** 
	 * Description: 根据渠道编码查询服务组列表
	 * @param channel_code
	 * @return 
	 */
	public List<String> listSrvgByChannel(String channel_code) {
		return dao.listSrvgByChannel(channel_code);
	}

	/** 
	 * Description: 根据 渠道和服务组编码查询总数
	 * @param channel_code
	 * @param srvg_code
	 * @return 
	 */
	public int countByChannelAndSrvg(String channel_code, String srvg_code) {
		return dao.countByChannelAndSrvg(channel_code,srvg_code);
	}

	/** 
	 * Description: 查询渠道的所有服务组权限
	 * @param channel_code
	 * @return 
	 */
	public List<ChChannelSrvgPrivInfo> listAllSrvgPriv(String channel_code) {
		List<ChChannelSrvgPrivInfo> srvg_priv=new ArrayList<ChChannelSrvgPrivInfo>();
		DBIterator<ChChannelSrvgPrivInfo> iterator=dao.iteratorAllSrvgPriv(channel_code);
		try {
			while (iterator.hasNext()) {
				srvg_priv.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return srvg_priv;
	}
}