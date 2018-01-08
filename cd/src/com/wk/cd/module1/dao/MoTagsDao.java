/**
 * Title: MoTagsDao.java
 * File Description: 组件分类标签表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-11-16
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.MoTagsInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:组件分类标签表
 * @author AutoGen
 */
abstract class MoTagsDao
		extends EntityDao<MoTagsInfo> {

	@SqlParam(querySet = { "COMPONENT_TAG" }, distinct = true, condition = "1=1")
	abstract List<String> queryAllTags();

	@SqlParam(querySet = { "COMPONENT_TAG" }, condition = "COMPONENT_ID=:comp_id")
	abstract List<String> queryTagsById(String comp_id);

	@SqlParam(condition = "COMPONENT_ID=:comp_id")
	abstract int deleteInfoById(String comp_id);

	@SqlParam(querySet = { "COMPONENT_TAG" }, distinct = true, condition = "COMPONENT_TAG IN (${tags})")
	abstract List<String> queryIdsByTags(String tags);

}