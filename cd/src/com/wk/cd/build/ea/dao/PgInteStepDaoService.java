/**
 * Title: PgInteStepDaoService.java
 * File Description: 集成方案步骤表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.lang.Inject;

/**
 * Class description:集成方案步骤表
 * @author AutoGen
 */
public class PgInteStepDaoService {
	@Inject private PgInteStepDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKey(PgInteStepInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKeyForUpdate(PgInteStepInfo info) {
		return dao.getForUpdate(info);
	}
	
	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKeyForUpdate2(String prog_id,int step_id) {
		PgInteStepInfo info = new PgInteStepInfo();
		info.setProg_id(prog_id);
		info.setStep_id(step_id);
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param PgInteStepInfo info
	 * @return int
	 */
	public int insertInfo(PgInteStepInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<PgInteStepInfo>
	 * @return int
	 */
	public int insertListInfo(List<PgInteStepInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 删除集成方案步骤表
	 * @param prog_id 
	 */
	public int deleteProgStep(String prog_id) {
		
		return dao.deleteProgStepByProgId(prog_id);
	}

	/** 
	 * Description: 删除集成方案步骤表通过Info
	 * @param stepInfo 
	 */
	public int deleteInfo(PgInteStepInfo stepInfo) {
		return dao.delete(stepInfo);
	}

	/** 
	 * Description: 更新方案步骤信息
	 * @param inte_for_update 
	 */
	public int updatePgInteStep(PgInteStepInfo inte_for_update) {
		return dao.update(inte_for_update);
	}

	/** 
	 * Description: 获得集成方案步骤列表
	 * @param prog_id
	 * @return 
	 */
	public List<PgInteStepInfo> getInteListByProgId(String prog_id) {
		return dao.getInteListByProgId(prog_id);
	}

	/** 
	 * Description: 统计有几个步骤
	 * @param prog_id 
	 * @param step_id
	 * @return 
	 */
	public int countPgStepByKey(String prog_id) {
		return dao.countPgStepByProgId(prog_id);
	}

	/** 
	 * Description: 
	 * @param prog_id
	 * @param i
	 * @return 
	 */
	public PgInteStepInfo getInfoByKey2(String prog_id, int step_id) {
		PgInteStepInfo info = new PgInteStepInfo();
		info.setProg_id(prog_id);
		info.setStep_id(step_id);
		return dao.get(info);
	}
	
	/**
	 * Description: 根据步骤类型获取记录
	 * @param prog_id
	 * @param step_type
	 * @return
	 */
	public List<PgInteStepInfo> getInfoByType(String prog_id, STEP_TYPE step_type){
		return dao.getInfoByType(prog_id, step_type);
	}

}