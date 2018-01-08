/**
 * Title: SvSrvSocDaoService.java
 * File Description: 服务数据源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.sv.info.*;
import com.wk.lang.Inject;

/**
 * Class description:服务数据源配置表
 * @author AutoGen
 */
public class SvSrvSocDaoService {
	@Inject private SvSrvSocDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param SvSrvSocInfo info
	 * @return SvSrvSocInfo
	 */
	public SvSrvSocInfo getInfoByKey(SvSrvSocInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvSrvSocInfo info
	 * @return SvSrvSocInfo
	 */
	public SvSrvSocInfo getInfoByKeyForUpdate(SvSrvSocInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param SvSrvSocInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvSocInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvSrvSocInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvSocInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据服务名称查询数据源定义表多条记录
	 * @param String srv_name
	 * @return List<SvSrvSocInfo>
	 */
	public List<SvSrvSocInfo> getListInfoByName(String srv_name) {
		List<SvSrvSocInfo> srv_soc_lst = dao.getListInfoByName(srv_name);
		if (Assert.isEmpty(srv_soc_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvSocInfo.TABLECN).addScene("FIELD", srv_name);
		}
		return srv_soc_lst;
	}
	
	/**
	 * 根据服务名称删除一组服务数据源信息
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int deleteInfo(String srv_name){
		return dao.deleteInfo(srv_name);
	}
	
	/**
	 * 根据服务名称查询记录条数
	 * @param srv_name 服务名称
	 * @return int 记录条数
	 */
	public int countInfo(String srv_name){
		return dao.countInfo(srv_name);
	}
}