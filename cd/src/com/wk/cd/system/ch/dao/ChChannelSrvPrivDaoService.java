/**
 * Title: ChChannelSrvPrivDaoService.java
 * File Description: 渠道服务权限配置表
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
 * Class description:渠道服务权限配置表
 * @author AutoGen
 */
public class ChChannelSrvPrivDaoService {
	@Inject private ChChannelSrvPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param ChChannelSrvPrivInfo info
	 * @return ChChannelSrvPrivInfo
	 */
	public ChChannelSrvPrivInfo getInfoByKey(ChChannelSrvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param ChChannelSrvPrivInfo info
	 * @return ChChannelSrvPrivInfo
	 */
	public ChChannelSrvPrivInfo getInfoByKeyForUpdate(ChChannelSrvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param ChChannelSrvPrivInfo info
	 * @return int
	 */
	public int insertInfo(ChChannelSrvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<ChChannelSrvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<ChChannelSrvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 删除渠道服务权限
	 * @param channel_code 
	 */
	public void deleteSrvPriv(String channel_code) {
		dao.deleteSrvByChannelCode(channel_code);
	}

	/** 
	 * Description: 查询渠道的所有服务权限
	 * @param channel_code
	 * @return 
	 */
	public List<ChChannelSrvPrivInfo> listAllSrvPriv(String channel_code) {
		List<ChChannelSrvPrivInfo> srv_priv=new ArrayList<ChChannelSrvPrivInfo>();
		DBIterator<ChChannelSrvPrivInfo> iterator=dao.iteratorAllSrvPriv(channel_code);
		try {
			while (iterator.hasNext()) {
				srv_priv.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return srv_priv;
	}

}