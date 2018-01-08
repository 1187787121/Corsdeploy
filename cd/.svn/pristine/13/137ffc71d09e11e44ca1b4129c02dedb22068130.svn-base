/**
 * Title: PgProgramDaoService.java
 * File Description: 环境方案表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.lang.Inject;
import com.wk.util.StringUtil;

/**
 * Class description:环境方案表
 * @author AutoGen
 */
public class PgProgramDaoService {
	@Inject private PgProgramDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param PgProgramInfo info
	 * @return PgProgramInfo
	 */
	public PgProgramInfo getInfoByKey(String prog_id) {
		return dao.get(prog_id);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param PgProgramInfo info
	 * @return PgProgramInfo
	 */
	public PgProgramInfo getInfoByKeyForUpdate(PgProgramInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param PgProgramInfo info
	 * @return int
	 */
	public int insertInfo(PgProgramInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<PgProgramInfo>
	 * @return int
	 */
	public int insertListInfo(List<PgProgramInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 分页查询环境方案表
	 * @param env_name
	 * @param start_recd
	 * @param limit_recd 
	 * @return 
	 */
	public List<PgProgramInfo> getEnvProgByEnvName(String env_name,String order_col_name, ORDER_TYPE order_type) {
		String order_type_str = order_type.getName();
		if("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)){
			return dao.getEnvProgByEnvNameDefault(env_name);
		}
		if("crt_bk_date".equals(order_col_name)) {
			return dao.getEnvProgByEnvName(env_name,order_col_name+" "+order_type_str+",crt_bk_time",order_type_str);
		}
		return dao.getEnvProgByEnvName(env_name,order_col_name,order_type_str);
	}

	/** 
	 * Description: 删除单个环境方案
	 * @param info 
	 * @return 
	 */
	public int deleteProg(String prog_id) {
	 
		return dao.delete(prog_id);
	}

	/** 
	 * Description: 根据方案编号查询系统的数量
	 * @param prog_id
	 * @return 
	 */
	public int countByProgId(String prog_id) {
		return dao.countByProgId(prog_id);
	}

	/** 
	 * Description: 更新发布信息
	 * @param updateInfo 
	 * @return 
	 */
	public int updateInfoByKey(PgProgramInfo updateInfo) {
		return dao.update(updateInfo);
	}
	
	/** 
	 * Description: 更新发布信息
	 * @param updateInfo 
	 * @return 
	 */
	public int updatePgPublishState(PgProgramInfo updateInfo) {
		return dao.update(updateInfo);
	}

	/** 
	 * Description: 
	 * @param prog_id
	 * @param step_id
	 * @return 
	 */
	public int countByKeyId(String prog_id, int step_id) {
		return dao.countByKeyId(prog_id,step_id);
	}

	/** 
	 * Description: 根据方案类型查看方案列表
	 * @param prog_type
	 * @return 
	 */
	public List<PgProgramInfo> getInfoByProgType(PROG_TYPE prog_type,String env_name) {
		return dao.getInfoByProgType(prog_type,env_name);
	}

	/** 
	 * Description: 修改方案类型信息
	 * @param prog_name
	 * @param prog_id 
	 */
	public int updatePgProgramByKey(String prog_name, String prog_id) {
		return dao.updatePgProgramByKey(prog_name,prog_id);
	}

	/** 
	 * Description: 通过环境名获得环境方案关联列表
	 * @param env_name
	 * @return 
	 */
	public List<PgProgramInfo> getEvnPgList(String env_name) {
		return dao.getEnvProgByEnvNameDefault(env_name);
	}

	/** 
	 * Description: 通过环境名查询计数
	 * @param env_name 
	 */
	public int countByEnvName(String env_name) {
	    return dao.countByEnvName(env_name);
		
	}

	/** 
	 * Description: 修改方案发布状态
	 * @param no
	 * @param prog_id 
	 */
	public int updatePgPublishStateByKey(IS_PUBLISH is_publish, String prog_id) {
		return dao.updatePgPublishStateByKey(is_publish,prog_id);
		
	}
}