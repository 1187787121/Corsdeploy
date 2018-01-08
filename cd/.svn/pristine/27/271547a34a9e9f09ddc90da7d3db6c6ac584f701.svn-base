/**
 * Title: UuParamDaoService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-8
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.lang.Inject;

/**
 * Class description:
 * @author AutoGen
 */
public class UuParamDaoService {
	@Inject private UuParamDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UuParamInfo info
	 * @return UuParamInfo
	 */
	public UuParamInfo getInfoByKey(UuParamInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UuParamInfo info
	 * @return UuParamInfo
	 */
	public UuParamInfo getInfoByKeyForUpdate(UuParamInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UuParamInfo info
	 * @return int
	 */
	public int insertInfo(UuParamInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UuParamInfo>
	 * @return int
	 */
	public int insertListInfo(List<UuParamInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: 
	 * @param param_uuid
	 * @return
	 */
	public int deleteById(String param_uuid){
		return dao.deleteById(param_uuid);
	}

	public List<UuParamInfo> getInfoByUuid(String param_uuid){
		return dao.getInfoByUuid(param_uuid);
	}
	
	/**
	 * Description: 获取投产参数值
	 * @param param_uuid
	 * @param param_name
	 * @return
	 */
	public List<String> getValueByKey(String param_uuid){
		return dao.getValueByKey(param_uuid);
	}
}