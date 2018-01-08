/**
 * Title: SvSrvService.java
 * File Description: 服务模块公用服务类
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.service;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.SALLOW_FLAG;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.cd.enu.TERM_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.ap.bean.ApproveServiceBean;
import com.wk.cd.system.sv.dao.SvRemoteSrvDaoService;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.dao.SvSrvSocDaoService;
import com.wk.cd.system.sv.info.SvRemoteSrvInfo;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.info.SvSrvSocInfo;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.cd.system.tm.service.TmTermService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 服务模块公用服务类
 * @author tlw
 */
public class SvSrvService {
	@Inject
	private SvSrvDaoService ssDaos;
	@Inject
	private SvSrvSocDaoService ssSocDaos;
	@Inject
	private TmTermService tmsrv;
	@Inject
	private SvRemoteSrvDaoService srmDaos;
	@Inject
	private SvSrvDaoService daoService;

	/**
	 * 根据主键查询服务配置表一条状态为正常的记录
	 * @param SvSrvInfo info 服务定义表信息（输入服务名称）
	 * @return SvSrvInfo 服务定义表信息
	 */
	public SvSrvInfo queryNoDelSvSrvByName(SvSrvInfo info) {
		return ssDaos.getInfoByName(info);
	}

	/**
	 * 根据服务名称查询服务数据源定义表的记录
	 * @param String srv_name 服务名称
	 * @return List<SvSrvSocInfo> 服务数据源定义表信息
	 */
	public List<SvSrvSocInfo> queryNoDelSvSrvSocByName(String srv_name) {
		return ssSocDaos.getListInfoByName(srv_name);
	}

	/**
	 * 根据服务名称查询服务对应的一组远程配置记录(找不到记录报错)
	 * @param srv_name 服务名称
	 * @return 一组状态正常的远程配置记录
	 */
	public SvRemoteSrvInfo queryNoDelRemoteSrvInfoByName(String srv_name) {
		return srmDaos.queryInfoBySrvName(srv_name);
	}

	/**
	 * 根据服务名称查询服务对应的一组远程配置记录(找不到不报错)
	 * @param srv_name 服务名称
	 * @return 一组状态正常的远程配置记录
	 */
	public SvRemoteSrvInfo queryNoDelRemoteSrvInfoByNameNoError(String srv_name) {
		return srmDaos.queryInfoByNameNoError(srv_name);
	}

	/**
	 * 分页查询服务定义表中所有状态为正常的记录
	 * @param user_srv_list 用户服务列表
	 * @param srv_type_list 服务类型列表
	 * @param offset 起始条数
	 * @param limit 查询条数
	 * @return List<SvSrvInfo>
	 */
	public List<SvSrvInfo> pageAllSvSrv(List<String> user_srv_list,
			List<FUN_TYPE> srv_type_list, int offset, int limit) {
		return ssDaos.pageAllSvSrv(user_srv_list, srv_type_list, offset, limit);
	}

	/**
	 * 分页查询服务定义表中所有状态为正常的记录
	 * @param user_srv_list 用户服务列表
	 * @param srv_fun_type 服务类型
	 * @return 总条数
	 */
	public int countAllSvSrv(List<String> user_srv_list,
			List<FUN_TYPE> srv_type_list) {
		return ssDaos.countAllSvSrv(user_srv_list, srv_type_list);
	}

	/**
	 * 服务定义表插入一条记录
	 * @param SvSrvInfo info 插入服务定义表信息
	 * @return int 插入条数
	 */
	public int insertSvSrv(SvSrvInfo info) {
		return ssDaos.insertInfo(info);
	}

	/**
	 * 服务数据源定义表插入一组信息
	 * @param List<SvSrvSocInfo> infos 服务数据源定义表插入信息
	 * @return int 插入条数
	 */
	public int insertSvSrvSoc(List<SvSrvSocInfo> infos) {
		return ssSocDaos.insertListInfo(infos);
	}

	/**
	 * 服务远程配置表插入一组信息
	 * @param List<SvRemoteSrvInfo> infos 远程调用服务配置表插入信息
	 * @return int 插入条数
	 */
	public int insertSvRemoteSrv(List<SvRemoteSrvInfo> infos) {
		return srmDaos.insertListInfo(infos);
	}

	/**
	 * 根据服务名称删除一组服务信息（修改记录状态为2）
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int lgDeleteSvSrv(String srv_name) {
		return ssDaos.lgDeleteInfo(srv_name);
	}

	/**
	 * 按照服务名称删除服务授权配置表一组信息
	 * @param srv_name 服务名称
	 * @return 删除条数
	 */
	public int deleteSvSrvSoc(String srv_name) {
		return ssSocDaos.deleteInfo(srv_name);
	}

	/**
	 * 按照服务名称删除远程服务调用配置表一组信息
	 * @param srv_name 服务名称
	 * @return 删除条数
	 */
	public int deleteSvRemoteSrv(String srv_name) {
		return srmDaos.deleteInfo(srv_name);
	}

	/**
	 * 按照服务名称查询服务定义表中对应记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countSvSrv(String srv_name) {
		return ssDaos.countInfo(srv_name);
	}

	/**
	 * 按照服务名称查询服务数据源定义表中对应记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countSvSrvSoc(String srv_name) {
		return ssSocDaos.countInfo(srv_name);
	}

	/**
	 * 按照服务名称查询远程服务调用配置表中对应记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countSvRemoteSrv(String srv_name) {
		return srmDaos.countInfo(srv_name);
	}

	/**
	 * 检查服务是否存在
	 * @param srv_name 服务名称
	 */
	public void checkServiceExist(String srv_name) {
		ssDaos.checkServiceExist(srv_name);
	}

	/**
	 * 按照服务名称更新服务配置表相关信息
	 * @param SvSrvInfo info
	 * @return 记录条数
	 */
	public int updateSvSrvByName(SvSrvInfo info) {
		if (ssDaos.countInfo(info.getSrv_name()) < 1) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvInfo.TABLECN).addScene("FIELD", info.getSrv_name());
		}
		return ssDaos.updateInfo(info);
	}

	/**
	 * Description: 按照服务名称和是否定义数据源标志更新服务数据源表相关信息
	 * @param srv_name 服务名称
	 * @param soc_flag 复核标志
	 * @param infos 更新复核信息列表
	 * @return 记录条数
	 */
	public int updateSvSrvSocByName(String srv_name, SOC_FLAG soc_flag,
			List<SvSrvSocInfo> infos) {
		int update_item = ssSocDaos.countInfo(srv_name);
		// 存在记录则删除记录
		if (update_item > 0) {
			ssSocDaos.deleteInfo(srv_name);
		}
		// 需要复核写入新的记录
		if (soc_flag == SOC_FLAG.YES) {
			ssSocDaos.insertListInfo(infos);
		}
		return update_item;
	}

	/**
	 * Description: 按照服务名称更新远程服务调用配置表相关信息
	 * @param srv_name 服务名称
	 * @param soc_flag 复核标志
	 * @param infos 更新复核信息列表
	 * @return 记录条数
	 */
	public int updateSvRemoteSrvByName(String srv_name, SvRemoteSrvInfo infos) {
		int update_item = srmDaos.countInfo(srv_name);
		// 存在记录则删除记录
		if (update_item > 0) {
			srmDaos.deleteInfo(srv_name);
		}
		// 需要复核写入新的记录
		if (!Assert.isEmpty(infos)) {
			srmDaos.insertInfo(infos);
		}
		return update_item;
	}

	/**
	 * 检查服务允许执行标志是否正确
	 * @param srv_name 服务名称
	 * @param sallow_flag 服务允许标志
	 */
	public void checkSallowFlag(String srv_name, SALLOW_FLAG sallow_flag) {
		ssDaos.checkSallowFlag(srv_name, sallow_flag);
	}

	/**
	 * 根据传入的服务名称信息dbiterator查询服务信息
	 * @param srv_name_iterator 传入的服务名称信息
	 * @return
	 */
	public DBIterator<SvSrvInfo> iteratorSrvBySrvNames(
			DBIterator<String> srv_name_iterator) {
		return ssDaos.iteratorSrvBySrvNames(srv_name_iterator);
	}

	/**
	 * 无条件查询所有的服务
	 * @return 服务信息
	 */
	public DBIterator<SvSrvInfo> iteratorAllSrv() {
		return ssDaos.iteratorAllSrv();
	}

	/**
	 * Description: 根据服务类型分页查询用户拥有的服务
	 * @param srv_fun_type1 服务类型
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return
	 */
	public List<SvSrvInfo> pageSrvByFunType(FUN_TYPE srv_fun_type, int start_recd, int limit_recd) {
		return daoService.pageSrvByFunType(srv_fun_type,start_recd, limit_recd);
	}

	/**
	 * Description: 根据服务类型分页查询用户拥有的服务总条数
	 * @param srv_fun_type 服务类型
	 * @return
	 */
	public int countSrvByFunType(FUN_TYPE srv_fun_type) {
		return daoService.countSrvByFunType(srv_fun_type);
	}
	
	/**
	 * Description: 查询所有可复核和可授权的服务
	 * @return
	 */
	public List<ApproveServiceBean> queryCanApproveSrv(){
		return daoService.queryCanApproveSrv();
	}

	/** 
	 * Description: 
	 * @param key 
	 */
	public List<ApproveServiceBean> querySrvNameListByKey(String key) {
		return daoService.querySrvNameList(key);
	}
}
