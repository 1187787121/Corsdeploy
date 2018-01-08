/**
 * Title: WkWorkDaoService.java
 * File Description: 任务定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.work.exc.CanNotUpdateToUnpublishedException;
import com.wk.cd.work.wk.bean.UpdateWkWorkBean;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:任务定义表
 * @author AutoGen
 */
public class WkWorkDaoService {
	@Inject
	private WkWorkDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkWorkInfo info
	 * @return WkWorkInfo
	 */
	public WkWorkInfo getInfoByKey(WkWorkInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkWorkInfo info
	 * @return WkWorkInfo
	 */
	public WkWorkInfo getInfoByKeyForUpdate(WkWorkInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkWorkInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkWorkInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据任务编码查询一条记录
	 * @param work_code 任务编码
	 * @return 任务编码信息
	 */
	public WkWorkInfo getInfoByWorkCode(String work_code,
			List<Integer> role_type_list) {
		WkWorkInfo info = dao.getInfoByWorkCode(work_code);
		// 查询结果为空报错
		if (info == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkWorkInfo.TABLECN).addScene("FIELD", work_code);
		}
		// 检查任务是否未发布
		if (!(role_type_list.contains(1) || role_type_list.contains(2))
				&& info.getIs_publish() == IS_PUBLISH.NO) {
			throw new CanNotUpdateToUnpublishedException();
		}
		return info;
	}

	/**
	 * 查询任务编码在任务定义表中条数
	 * @param work_code
	 * @return
	 */
	public int countByWorkcode(String work_code) {
		return dao.countByWorkcode(work_code);
	}

	/**
	 * Description: 根据keyword的传值获取故障处理类 任务分页信息
	 * @param keyword 关键字
	 * @param start_recd 起始记录数
	 * @param limit_recd 查询记录数
	 * @return
	 */
	public List<WkWorkInfo> pagePbmWork(FUN_TYPE work_fun_type, String keyword,
			int start_recd, int limit_recd) {
		return dao.pagePbmWork(work_fun_type, keyword, start_recd, limit_recd);
	}

	public int updateWorkByWrokCode(String work_code,
			UpdateWkWorkBean update_work_bean) {
		// 已发布修改为未发布报错
		IS_PUBLISH new_is_publish = update_work_bean.getIs_publish();
		if (dao.getInfoByWorkCode(work_code).getIs_publish() == IS_PUBLISH.YES
				&& new_is_publish == IS_PUBLISH.NO) {
			throw new CanNotUpdateToUnpublishedException();
		}
		return dao.updateWorkByWorkCode(update_work_bean.getWork_cn_name(),
				update_work_bean.getWork_bk_desc(), update_work_bean
						.getIs_publish(), update_work_bean.getWork_fun_type(),
				update_work_bean.getModify_bk_date(), update_work_bean
						.getModify_bk_time(), update_work_bean
						.getModify_user_id(), work_code);
	}

	/**
	 * 分页查询任务定义信息，角色类型为超级管理员和系统管理员的可以查询未发布任务，其他用户不能查询未发布任务
	 * @param work_type_list 任务类型列表
	 * @param start_recd 分页查询记录起始位置
	 * @param limit_recd 分页查询显示记录数
	 * @return 分页查询信息
	 */
	public List<WkWorkInfo> pageAllWorkByWorkType(
			List<FUN_TYPE> work_type_list, List<Integer> role_type_list,
			int start_recd, int limit_recd) {
		String work_type_str = "";
		String is_publish_str = "";
		// 输入的角色类型列表不能为空
		if (Assert.isEmpty(role_type_list)) {
			throw new DataErrorException().addScene("INPUT", "部门角色");
		}
		// 任务类型List转换为字符串
		if (!Assert.isEmpty(work_type_list)) {
			for (FUN_TYPE type : work_type_list) {
				work_type_str += type.getValue() + ",";
			}
			work_type_str = "("
					+ work_type_str.substring(0, work_type_str.length() - 1)
					+ ")";
		}
		// 获取查询是否发布类型
//		if (role_type_list.contains(1) || role_type_list.contains(2)) {
//			is_publish_str = "('" + IS_PUBLISH.YES + "','" + IS_PUBLISH.NO
//					+ "')";
//		} else {
//			is_publish_str = "('" + IS_PUBLISH.YES + "')";
//		}
		is_publish_str = "('" + IS_PUBLISH.YES + "')";
		return dao.pageAllWork(work_type_str, is_publish_str, start_recd,
				limit_recd);
	}

	/**
	 * 分页查询任务数量
	 * @param work_type_list 任务类型列表
	 * @return 分页查询信息
	 */
	public int countAllWorkByWorkType(List<FUN_TYPE> work_type_list,
			List<Integer> role_type_list) {
		String work_type_str = "";
		String is_publish_str = "";
		// 输入的角色类型列表不能为空
		if (Assert.isEmpty(role_type_list)) {
			throw new DataErrorException().addScene("INPUT", "部门角色");
		}
		// 任务类型List转换为字符串
		if (!Assert.isEmpty(work_type_list)) {
			for (FUN_TYPE type : work_type_list) {
				work_type_str += type.getValue() + ",";
			}
			work_type_str = "("
					+ work_type_str.substring(0, work_type_str.length() - 1)
					+ ")";
		}
		// 获取查询是否发布类型
		if (role_type_list.contains(1) || role_type_list.contains(2)) {
			is_publish_str = "('" + IS_PUBLISH.YES + "','" + IS_PUBLISH.NO
					+ "')";
		} else {
			is_publish_str = "('" + IS_PUBLISH.YES + "')";
		}
		return dao.countAllWork(work_type_str, is_publish_str);
	}

	/**
	 * 根据任务编码删除对应任务信息(将记录状态修改为2)
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	public int deleteWorkByWorkCode(String work_code, JaDate del_date,
			JaTime del_time, String user_id) {
		return dao.updateWorkByWorkCodeDel(del_date, del_time, user_id,
				RCD_STATE.ABNORMAL, work_code);
	}
}