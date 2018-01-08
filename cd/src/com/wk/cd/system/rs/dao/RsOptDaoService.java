/**
 * Title: RsOptDaoService.java
 * File Description: 资源操作信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-5-27
 */
package com.wk.cd.system.rs.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.rs.info.*;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:资源操作信息表
 * @author AutoGen
 */
public class RsOptDaoService {
	@Inject private RsOptDao dao;
	@Inject private ResQuery query;

	/**
	 * 根据主键查询一条记录
	 * @param RsOptInfo info
	 * @return RsOptInfo
	 */
	public RsOptInfo getInfoByKey(RsOptInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param RsOptInfo info
	 * @return RsOptInfo
	 */
	public RsOptInfo getInfoByKeyForUpdate(RsOptInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param RsOptInfo info
	 * @return int
	 */
	public int insertInfo(RsOptInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<RsOptInfo>
	 * @return int
	 */
	public int insertListInfo(List<RsOptInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * 根据资源编码获取下级操作列表
	 * @param rs_code
	 * @return 
	 */
	public List<RsOptInfo> getSubOptList(String rs_code) {
		return dao.getSubOptList(rs_code);
	}

	/** 
	 * 获取需要操作检查的资源配置信息
	 * @return 
	 */
	public List<RsUrlBean> getRsConfig() {
		return query.getRsConfig();
	}

	/** 
	 * 查询所有操作列表
	 * @return 
	 */
	public List<RsOptInfo> listAllRsOpt() {
		List<RsOptInfo> opt_list=new ArrayList<RsOptInfo>();
		DBIterator<RsOptInfo> optIterator=dao.iteratorAllRsOpt();
		try {
			while (optIterator.hasNext()) {
				RsOptInfo info=optIterator.next();
				opt_list.add(info);
			}
		}finally{
			optIterator.close();
		}
		return opt_list;
	}
}