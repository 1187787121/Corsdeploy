/**
 * Title: UuSocDaoService.java
 * File Description: 数据源关联表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.lang.Inject;

/**
 * Class description:数据源关联表
 * @author AutoGen
 */
public class UuSocDaoService {
	@Inject private UuSocDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UuSocInfo info
	 * @return UuSocInfo
	 */
	public UuSocInfo getInfoByKey(UuSocInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UuSocInfo info
	 * @return UuSocInfo
	 */
	public UuSocInfo getInfoByKeyForUpdate(String ver_soc_uuid, long i) {
		return dao.getForUpdate(ver_soc_uuid,i);
	}

	/**
	 * 插入一条记录
	 * @param UuSocInfo info
	 * @return int
	 */
	public int insertInfo(UuSocInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UuSocInfo>
	 * @return int
	 */
	public int insertListInfo(List<UuSocInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: 根据UUID查询多条记录
	 * @param soc_uuid
	 * @return
	 */
	public List<UuSocInfo> queryListInfoByUU(String soc_uuid){
		return dao.queryListInfoByUU(soc_uuid);
	}
	
	/**
	 * Description: 根据UUID删除多条记录
	 * @param soc_uuid
	 * @return
	 */
	public int deleteListByUU(String soc_uuid){
		return dao.deleteListByUU(soc_uuid);
	}

	/** 
	 * Description: 更新uu_soc
	 * @param uu_soc 
	 */
	public int updateSoctInfo(UuSocInfo uu_soc) {
		return dao.update(uu_soc);
	}
		
}