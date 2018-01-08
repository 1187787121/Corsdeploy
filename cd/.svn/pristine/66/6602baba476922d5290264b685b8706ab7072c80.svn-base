/**
 * Title: UuFilelistDaoService.java
 * File Description: 文件清单表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.lang.Inject;

/**
 * Class description:文件清单表
 * @author AutoGen
 */
public class UuFilelistDaoService {
	@Inject private UuFilelistDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UuFilelistInfo info
	 * @return UuFilelistInfo
	 */
	public UuFilelistInfo getInfoByKey(UuFilelistInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UuFilelistInfo info
	 * @return UuFilelistInfo
	 */
	public UuFilelistInfo getInfoByKeyForUpdate(UuFilelistInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UuFilelistInfo info
	 * @return int
	 */
	public int insertInfo(UuFilelistInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UuFilelistInfo>
	 * @return int
	 */
	public int insertListInfo(List<UuFilelistInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 删除文件清单
	 * @param list_uuid 
	 */
	public int deleteUuFileByListId(String list_uuid) {
		return dao.deleteUuFileByListId(list_uuid);
	}

	/** 
	 * Description: 获得文件清单列表
	 * @param file_uuid
	 * @return 
	 */
	public List<UuFilelistInfo> getInfoByFileUuId(String list_uuid) {
		return dao.queryInfoByFileUuId(list_uuid);
	}
	
	/**
	 * Description: 根据UUID获取所有包名
	 * @param list_uuid
	 * @return
	 */
	public List<String> queryPacList(String list_uuid){
		return dao.queryPacList(list_uuid);
	}
	
	/**
	 * Description: 根据包名查询文件列表
	 * @param list_uuid
	 * @param package_name
	 * @return
	 */
	public List<UuFilelistInfo> queryFileByPac(String list_uuid, String package_name){
		return dao.queryFileByPac(list_uuid, package_name);
	}

}