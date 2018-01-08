/**
 * Title: SvRemoteSrvDaoService.java
 * File Description: 远程服务调用配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-16
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.sv.info.*;
import com.wk.lang.Inject;

/**
 * Class description:远程服务调用配置表
 * @author AutoGen
 */
public class SvRemoteSrvDaoService {
	@Inject
	private SvRemoteSrvDao dao;
	@Inject
	private SvSrvDao info_dao;

	/**
	 * 根据主键查询一条记录
	 * @param SvRemoteSrvInfo info
	 * @return SvRemoteSrvInfo
	 */
	public SvRemoteSrvInfo getInfoByKey(SvRemoteSrvInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvRemoteSrvInfo info
	 * @return SvRemoteSrvInfo
	 */
	public SvRemoteSrvInfo getInfoByKeyForUpdate(SvRemoteSrvInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 根据服务名称查询服务对应的一组记录(找不到记录报错)
	 * @param srv_name 服务名称
	 * @return 一组状态正常的远程配置记录
	 */
	public SvRemoteSrvInfo queryInfoBySrvName(String srv_name) {
		SvRemoteSrvInfo infos = dao.queryInfoBySrvName(srv_name);
		SvSrvInfo info = info_dao.getInfoByName(srv_name);
		if (Assert.isEmpty(infos)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvRemoteSrvInfo.TABLECN).addScene("FIELD", info.getSrv_bk_desc()+"("+srv_name+")");
		}
		return infos;
	}

	/**
	 * 根据服务名称查询服务对应的一组记录(不报错)
	 * @param srv_name 服务名称
	 * @return 一组状态正常的远程配置记录
	 */
	public SvRemoteSrvInfo queryInfoByNameNoError(String srv_name) {
		return dao.queryInfoBySrvName(srv_name);
	}

	/**
	 * 根据服务名称查询服务对应的一组状态正常的记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countInfo(String srv_name) {
		return dao.countInfoBySrvName(srv_name);
	}

	/**
	 * 根据服务名称删除服务对应的一组记录
	 * @param srv_name 服务名称
	 * @return 删除条数
	 */
	public int deleteInfo(String srv_name) {
		return dao.deleteInfoBySrvName(srv_name);
	}

	/**
	 * 插入一条记录
	 * @param SvRemoteSrvInfo info
	 * @return int
	 */
	public int insertInfo(SvRemoteSrvInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvRemoteSrvInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvRemoteSrvInfo> infos) {
		return dao.insert(infos);
	}

}