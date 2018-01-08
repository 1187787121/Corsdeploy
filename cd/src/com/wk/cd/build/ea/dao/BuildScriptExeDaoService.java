/**
 * Title: BuildScriptExeDaoService.java
 * File Description: 构建脚本执行表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.BuildScriptExeInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:构建脚本执行表
 * @author AutoGen
 */
public class BuildScriptExeDaoService {
	@Inject private BuildScriptExeDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param BuildScriptExeInfo info
	 * @return BuildScriptExeInfo
	 */
	public BuildScriptExeInfo getInfoByKey(BuildScriptExeInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param BuildScriptExeInfo info
	 * @return BuildScriptExeInfo
	 */
	public BuildScriptExeInfo getInfoByKeyForUpdate(BuildScriptExeInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param BuildScriptExeInfo info
	 * @return int
	 */
	public int insertInfo(BuildScriptExeInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<BuildScriptExeInfo>
	 * @return int
	 */
	public int insertListInfo(List<BuildScriptExeInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 更新开始时间和执行状态
	 * @param now
	 * @param running
	 * @param inst_id
	 * @param j 
	 */
	public int updateExeStarBkTmByKey(JaDateTime start_bk_tm, EXE_STATUS exe_status,String instance_id, int exe_bk_no) {
		return dao.updateExeStarBkTmByKey(start_bk_tm,exe_status,instance_id,exe_bk_no);
	}
	
	/** 
	 * Description: 更新结束时间和执行状态
	 * @param now
	 * @param running
	 * @param inst_id
	 * @param j 
	 */
	public int updateExeEndBkTmByKey(JaDateTime end_bk_tm, EXE_STATUS exe_status,EXE_RESULT exe_result,String instance_id, int exe_bk_no) {
		return dao.updateExeEndBkTmByKey(end_bk_tm,exe_status,exe_result,instance_id,exe_bk_no);
	}

	/** 
	 * Description: 更新执行信息
	 * @param exec_text
	 * @param instance_id
	 * @param exe_bk_no
	 */
	public int updateExeMsgByKey(String exec_text, String instance_id, int exe_bk_no) {
		return dao.updateExeMsgByKey(exec_text,instance_id,exe_bk_no);
		
	}

	/** 
	 * Description: 通过主键获得一条记录
	 * @param instance_id
	 * @param exe_bk_no
	 * @return 
	 */
	public BuildScriptExeInfo getExeScriptByKey(String instance_id, int exe_bk_no) {
		return dao.getExeScriptByKey(instance_id,exe_bk_no);
	}

	/** 
	 * Description: 更新耗时
	 * @param time_used
	 * @param instance_id
	 * @param exe_bk_no
	 */
	public int updateCostTmBykey(int time_used, String instance_id, int exe_bk_no) {
		return dao.updateCostTmBykey(time_used,instance_id,exe_bk_no);
	}

	/** 
	 * Description: 获得构建执行信息
	 * @param inst_id 
	 */
	public List<BuildScriptExeInfo> getExeScriptMsgByInstId(String inst_id) {
		return dao.getExeScriptMsgByInstId(inst_id);
		
	}

}