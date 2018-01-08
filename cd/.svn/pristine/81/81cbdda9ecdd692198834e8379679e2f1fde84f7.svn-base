/**
 * Title: WkWorkDao.java
 * File Description: 任务定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:任务定义表
 * @author AutoGen
 */
abstract class WkWorkDao
		extends EntityDao<WkWorkInfo> {
	/**
	 * 根据任务编码查询详细信息
	 * @param work_code 任务编码
	 * @return 详细信息
	 */
	@SqlParam(condition = "WORK_CODE = :work_code and RCD_STATE = 1")
	abstract WkWorkInfo getInfoByWorkCode(String work_code);

	/**
	 * Description: 根据work_code获取任务数量
	 * @param work_code 任务编码
	 * @return
	 */
	@SqlParam(condition = "rcd_state=1 and work_code=:work_code")
	abstract int countByWorkcode(String work_code);

	/**
	 * Description: 根据keyword的传值获取故障处理类任务分页信息
	 * @param fun_type 任务类型
	 * @param keyword 关键字
	 * @param start_recd 起始记录数
	 * @param limit_recd 查询记录数
	 * @return
	 */
	@SqlParam(sql = "select work_code, work_cn_name, work_bk_desc, is_publish, work_fun_type, crt_bk_date, crt_bk_time, crt_user_id, " +
			"modify_bk_date, modify_bk_time, modify_user_id, del_bk_date, del_bk_time, del_user_id, rcd_state from WK_WORK where " +
			"rcd_state=1 and work_fun_type=${fun_type} and (work_cn_name like '%${keyword}%')", dynamic = true)
	abstract List<WkWorkInfo> pagePbmWork(FUN_TYPE fun_type, String keyword,
			int start_recd, int limit_recd);

	/**
	 * 查询所有任务信息，返回相应列表
	 * @param start_rcd 起始条数
	 * @param limit_rcd 查询数量
	 * @return 查询信息集合
	 */
	@SqlParam(sql = "select work_code, work_cn_name, work_bk_desc, is_publish, work_fun_type, crt_bk_date, crt_bk_time, crt_user_id, " +
			"modify_bk_date, modify_bk_time, modify_user_id, del_bk_date, del_bk_time, del_user_id, rcd_state from WK_WORK where " +
			"(WORK_FUN_TYPE in ${work_type_str}) and (IS_PUBLISH in ${is_publish_str}) and RCD_STATE = 1 order by WORK_FUN_TYPE, WORK_CODE", dynamic = true)
	abstract List<WkWorkInfo> pageAllWork(String work_type_str,
			String is_publish_str, int start_recd, int limit_recd);

	/**
	 * 查询所有任务数量
	 * @return 查询信息集合
	 */
	@SqlParam(sql = "select count(*) from WK_WORK where (WORK_FUN_TYPE in ${work_type_str}) and (IS_PUBLISH in ${is_publish_str}) and RCD_STATE = 1", dynamic = true)
	abstract int countAllWork(String work_type_str, String is_publish_str);

	/**
	 * 更新服务定义表
	 * @param work_code 任务编码
	 * @param work_cn_name 任务名称
	 * @param work_bk_desc 任务描述
	 * @param is_publish 是否发布
	 * @param work_fun_type 任务类型
	 * @return 更新条数
	 */
	@SqlParam(updateSet = { "WORK_CN_NAME", "WORK_BK_DESC", "IS_PUBLISH",
			"WORK_FUN_TYPE", "MODIFY_BK_DATE", "MODIFY_BK_TIME",
			"MODIFY_USER_ID" }, condition = "PK")
	abstract int updateWorkByWorkCode(String work_cn_name, String work_bk_desc,
			IS_PUBLISH is_publish, FUN_TYPE work_fun_type,
			JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String work_code);

	/**
	 * 根据任务编码删除对应任务信息
	 * @param work_code 任务编码
	 * @return 删除条数
	 */
	@SqlParam(updateSet = { "DEL_BK_DATE", "DEL_BK_TIME", "DEL_USER_ID",
			"RCD_STATE" }, condition = "PK")
	abstract int updateWorkByWorkCodeDel(JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, RCD_STATE rcd_state,
			String work_code);
}