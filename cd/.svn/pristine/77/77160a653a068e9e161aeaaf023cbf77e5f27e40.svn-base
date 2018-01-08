/**
 * Title: UuFilelistDao.java
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
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:文件清单表
 * @author AutoGen
 */
abstract class UuFilelistDao extends EntityDao<UuFilelistInfo> {

	/** 
	 * Description: 删除文件清单
	 * @param list_uuid
	 * @return 
	 */
	@SqlParam(condition = "LIST_UUID =:list_uuid")
	abstract int deleteUuFileByListId(String list_uuid);

	/** 
	 * Description: 获得文件清单列表
	 * @param file_uuid
	 * @return 
	 */
	@SqlParam(condition = "LIST_UUID =:list_uuid")
	abstract List<UuFilelistInfo> queryInfoByFileUuId(String list_uuid);

	/** 
	 * Description: 根据UUID获取所有包名
	 * @param list_uuid
	 * @return 
	 */
	@SqlParam(sql = "select DISTINCT(PACKAGE_NAME) from uu_filelist where LIST_UUID =:list_uuid")
	abstract List<String> queryPacList(String list_uuid);

	/** 
	 * Description: 根据包名查询文件列表
	 * @param list_uuid
	 * @param package_name
	 * @return 
	 */
	@SqlParam(condition = "LIST_UUID =:list_uuid AND PACKAGE_NAME =:package_name")
	abstract List<UuFilelistInfo> queryFileByPac(String list_uuid, String package_name);

}