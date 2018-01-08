/**
 * Title: WkWorkConfigPublicService.java
 * File Description:任务定义和配置相关的公共服务 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.work.wk.bean.DtSourceBean;
import com.wk.cd.work.wk.bean.RsResCodeBean;
import com.wk.cd.work.wk.bean.SvServiceBean;
import com.wk.cd.work.wk.bean.UpdateWkWorkBean;
import com.wk.cd.work.wk.dao.WkWorkDaoService;
import com.wk.cd.work.wk.dao.WkWorkRsDaoService;
import com.wk.cd.work.wk.dao.WkWorkSocDaoService;
import com.wk.cd.work.wk.dao.WkWorkSrvDaoService;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.info.WkWorkRsInfo;
import com.wk.cd.work.wk.info.WkWorkSocInfo;
import com.wk.cd.work.wk.info.WkWorkSrvInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:任务定义和配置相关的公共服务
 * @author tlw
 */
public class WorkConfigPublicService {
	@Inject
	private WkWorkDaoService wwDaos;
	@Inject
	private WkWorkRsDaoService wwRsDaos;
	@Inject
	private WkWorkSocDaoService wwSocDaos;
	@Inject
	private WkWorkSrvDaoService wwSrvDaos;
	@Inject
	private DtCheckSocExistService dtSrv;
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private RsPublicService rsSrv;

	/**
	 * 新增一条任务定义和配置
	 * @param info 新增任务信息
	 * @return 新增条数
	 */
	public int insertWorkDaoInfo(WkWorkInfo info) {
		return wwDaos.insertInfo(info);
	}

	/**
	 * 任务资源配置表新增一组记录
	 * @param infos 新增任务资源信息
	 * @return 新增条数
	 */
	public int insertWorkRsDaoInfo(List<WkWorkRsInfo> infos) {
		return wwRsDaos.insertListInfo(infos);
	}

	/**
	 * 任务数据源配置表新增一组记录
	 * @param infos 新增数据源信息
	 * @return 新增条数
	 */
	public int insertWorkSocDaoInfo(List<WkWorkSocInfo> infos) {
		return wwSocDaos.insertListInfo(infos);
	}

	/**
	 * 任务服务配置表新增一组记录
	 * @param infos 新增服务信息
	 * @return 新增条数
	 */
	public int insertWorkSrvDaoInfo(List<WkWorkSrvInfo> infos) {
		return wwSrvDaos.insertListInfo(infos);
	}

	/**
	 * 根据任务编码精确查询一条任务定义信息
	 * @param work_code 任务编码
	 * @return 任务定义信息
	 */
	public WkWorkInfo queryInfoByWorkCode(String work_code,
			List<Integer> role_type_list) {
		return wwDaos.getInfoByWorkCode(work_code, role_type_list);
	}

	/**
	 * 根据任务编码精确查询对应的资源信息
	 * @param work_code 任务编码
	 * @return 资源信息
	 */
	public List<WkWorkRsInfo> getWorkRsList(String work_code) {
		return wwRsDaos.getWorkRsList(work_code);
	}

	/**
	 * 根据任务编码精确查询对应的数据源信息
	 * @param work_code 任务编码
	 * @return 资源信息
	 */
	public List<WkWorkSocInfo> getWorkSocList(String work_code) {
		return wwSocDaos.getWorkSocList(work_code);
	}

	/**
	 * 根据任务编码精确查询对应的服务信息
	 * @param work_code 任务编码
	 * @return 资源信息
	 */
	public List<WkWorkSrvInfo> getWorkSrvList(String work_code) {
		return wwSrvDaos.getWorkSrvList(work_code);
	}

	/**
	 * 根据keyword的传值获取故障处理类任务分页信息
	 * @param keyword 关键字
	 * @param start_recd 起始记录数
	 * @param limit_recd 查询记录数
	 * @return
	 */
	public List<WkWorkInfo> pagePbmWork(FUN_TYPE work_fun_type, String keyword,
			int start_recd, int limit_recd) {
		return wwDaos.pagePbmWork(work_fun_type, keyword, start_recd,
				limit_recd);
	}

	/**
	 * 分页查询任务定义信息
	 * @param work_type_list 任务类型列表
	 * @param start_recd 分页查询记录起始位置
	 * @param limit_recd 分页查询显示记录数
	 * @return 分页查询信息
	 */
	public List<WkWorkInfo> pageAllWorkByWorkType(
			List<FUN_TYPE> work_type_list, List<Integer> role_type_list,
			int start_recd, int limit_recd) {
		return wwDaos.pageAllWorkByWorkType(work_type_list, role_type_list,
				start_recd, limit_recd);
	}

	/**
	 * 分页查询任务定义信息数量
	 * @param work_type_list 任务类型列表
	 * @return 分页查询信息
	 */
	public int countAllWorkByWorkType(List<FUN_TYPE> work_type_list,
			List<Integer> role_type_list) {
		return wwDaos.countAllWorkByWorkType(work_type_list, role_type_list);
	}

	/**
	 * 更新任务定义表
	 * @param work_code 任务编码
	 * @param update_work_bean 需要更新的任务信息
	 * @return 更新条数
	 */
	public int updateWorkByWrokCode(String work_code,
			UpdateWkWorkBean update_work_bean) {
		return wwDaos.updateWorkByWrokCode(work_code, update_work_bean);
	}

	/**
	 * 更新任务资源配置表
	 * @param work_code 任务编码
	 * @param List<RsResCodeBean> 需要更新任务资源配置信息
	 * @return 更新条数
	 */
	public int updateWorkRsByWrokCode(String work_code,
			List<RsResCodeBean> rs_list) {
		List<WkWorkRsInfo> infos = new ArrayList<WkWorkRsInfo>();
		if (!Assert.isEmpty(rs_list)) {
			for (RsResCodeBean rs : rs_list) {
				WkWorkRsInfo info = new WkWorkRsInfo();
				info.setWork_code(work_code);
				String rs_code = rs.getRs_code();
				rsSrv.checkRsExist(rs_code); // 检查资源是否存在
				info.setRs_code(rs_code);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwRsDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * 更新任务数据源配置表
	 * @param work_code 任务编码
	 * @param List<DtSourceBean> 需要更新数据源配置表信息
	 * @return 更新条数
	 */
	public int updateWorkSocByWrokCode(String work_code,
			List<DtSourceBean> soc_list) {
		List<WkWorkSocInfo> infos = new ArrayList<WkWorkSocInfo>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceBean dt : soc_list) {
				WkWorkSocInfo info = new WkWorkSocInfo();
				info.setWork_code(work_code);
				String soc_name = dt.getSoc_name();
				dtSrv.checkSocExist(soc_name); // 检查数据源存在性
				info.setSoc_name(soc_name);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwSocDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * 更新任务服务配置表
	 * @param work_code 任务编码
	 * @param List<SvServiceBean> 需要更新任务服务信息
	 * @return 更新条数
	 */
	public int updateWorkSrvByWrokCode(String work_code,
			List<SvServiceBean> srv_list) {
		List<WkWorkSrvInfo> infos = new ArrayList<WkWorkSrvInfo>();
		if (!Assert.isEmpty(srv_list)) {
			for (SvServiceBean srv : srv_list) {
				WkWorkSrvInfo info = new WkWorkSrvInfo();
				info.setWork_code(work_code);
				String srv_name = srv.getSrv_name();
				ssSrv.checkServiceExist(srv_name); // 检查服务是否存在
				info.setSrv_name(srv_name);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwSrvDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * 根据任务编码删除任务定义表信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkByWorkCode(String work_code, JaDate del_date,
			JaTime del_time, String user_id) {
		return wwDaos.deleteWorkByWorkCode(work_code, del_date, del_time,
				user_id);
	}

	/**
	 * 根据任务编码删除任务资源配置表信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkRsByWorkCode(String work_code) {
		return wwRsDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * 根据任务编码删除任务数据源配置表信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkSocByWorkCode(String work_code) {
		return wwSocDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * 根据任务编码删除任务服务配置表信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkSrvByWorkCode(String work_code) {
		return wwSrvDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * Description: 检查是否存在work_code信息，若不存在抛“不存在记录”异常。
	 * @param work_code 任务编码
	 */
	public void checkExist(String work_code) {
		if (wwDaos.countByWorkcode(work_code) < 1) {
			throw new RecordNotFoundException().addScene("Table", "WK_WORK")
					.addScene("FIELD", work_code);
		}
	}

	/**
	 * Description: 检查是否存在work_code信息，若存在抛“已存在记录”异常。
	 * @param work_code 任务编码
	 */
	public void checkNotExist(String work_code) {
		if (wwDaos.countByWorkcode(work_code) > 0) {
			throw new RecordAlreadyExistException()
					.addScene("Table", "WK_WORK").addScene("FIELD", work_code);
		}
	}
}
