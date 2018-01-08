/**
 * Title: InstanceExeDaoService.java
 * File Description: 实例执行信息表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-3
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.*;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:实例执行信息表
 * @author AutoGen
 */
public class InstanceExeDaoService {
	@Inject private InstanceExeDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param InstanceExeInfo info
	 * @return InstanceExeInfo
	 */
	public InstanceExeInfo getInfoByKey(InstanceExeInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param InstanceExeInfo info
	 * @return InstanceExeInfo
	 */
	public InstanceExeInfo getInfoByKeyForUpdate(InstanceExeInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param InstanceExeInfo info
	 * @return int
	 */
	public int insertInfo(InstanceExeInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<InstanceExeInfo>
	 * @return int
	 */
	public int insertListInfo(List<InstanceExeInfo> infos) {
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
	 * Description: 根据实例ID删除多条记录
	 * @param instance_id
	 * @return
	 */
	public int deleteByInstanceId(String instance_id){
		return dao.deleteByInstanceId(instance_id);
		
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
	public InstanceExeInfo getExeInstByKey(String instance_id, int exe_bk_no) {
		return dao.getExeInstByKey(instance_id,exe_bk_no);
	}
	
	/** 
	 * Description: 根据实例ID获得多条记录
	 * @param inst_id 
	 */
	public List<InstanceExeInfo> getExeInstMsgByInstId(String inst_id) {
		return dao.getExeInstMsgByInstId(inst_id);
	}

	/** 
	 * Description: 插入执行开始时间
	 * @param start_bk_tm 执行开始时间
	 * @param instance_id 执行实例ID
	 * @param phase_id 执行编号
	 */
	public int updateExecuteStartTime(JaDateTime start_bk_tm, String instance_id, int inst_bk_no) {
		return dao.updateExecuteStartTime(start_bk_tm, instance_id, inst_bk_no);
	}

	/** 
	 * Description: 插入执行状态
	 * @param exe_status 执行状态
	 * @param instance_id 执行实例ID
	 * @param inst_bk_no 执行编号
	 */
	public int updateExecuteStatus(EXE_STATUS exe_status, String instance_id, int inst_bk_no) {
		return dao.updateExecuteStatus(exe_status, instance_id, inst_bk_no);
	}

	/** 
	 * Description: 插入执行结束时间
	 * @param exec_text 执行信息
	 * @param exe_result 执行结果
	 * @param end_bk_tm 执行结束时间
	 * @param time_used 耗时
	 * @param instance_id 执行实例ID
	 * @param inst_bk_no 执行编号
	 */
	public int updateExecuteEnd(String exec_text, EXE_RESULT exe_result, JaDateTime end_bk_tm, int time_used, String instance_id, int inst_bk_no) {
		return dao.updateExecuteEnd(exec_text, exe_result, end_bk_tm, time_used, instance_id, inst_bk_no);
	}

	/** 
	 * Description: 更新耗时
	 * @param time_used
	 * @param instance_id
	 * @param exe_bk_no
	 */
	public int updateCostTmBykey(int time_used, String instance_id, int inst_bk_no) {
		return dao.updateCostTmBykey(time_used,instance_id,inst_bk_no);
	}

	/** 
	 * Description: 获取模板最大阶段号
	 * @param instanceId
	 * @return 
	 */
	public int getMaxTplPhase(String instance_id) {
		return dao.getMaxTplPhase(instance_id);
	}

	/** 
	 * Description: 根据模板阶段号查询实例阶段号列表
	 * @param tpl_phase_id
	 * @return 
	 */
	public List<Integer> queryInstPhaseByTplPhase(String instance_id, int tpl_bk_no) {
		return dao.queryInstPhaseByTplPhase(instance_id, tpl_bk_no);
	}

	/** 
	 * Description: 根据实例ID删除集成任务执行表
	 * @param inst_id 
	 */
	public int deleteInfoByInst(String instance_id) {
		return dao.deleteInfoByInst(instance_id);
	}
}