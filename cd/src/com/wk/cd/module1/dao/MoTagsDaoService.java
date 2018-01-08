/**
 * Title: MoTagsDaoService.java
 * File Description: 组件分类标签表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-11-16
 */
package com.wk.cd.module1.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.info.MoTagsInfo;
import com.wk.cd.common.util.Assert;
import com.wk.lang.Inject;

/**
 * Class description:组件分类标签表
 * @author AutoGen
 */
public class MoTagsDaoService {
	@Inject
	private MoTagsDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param component_id
	 * @param component_tag
	 * @return CO_TAGSInfo
	 */
	public MoTagsInfo getInfoByKey(String component_id, String component_tag) {
		return dao.get(component_id, component_tag);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param component_id
	 * @param component_tag
	 * @return CO_TAGSInfo
	 */
	public MoTagsInfo getInfoByKeyForUpdate(String component_id, String component_tag) {
		return dao.getForUpdate(component_id, component_tag);
	}

	/**
	 * 插入一条记录
	 * @param MoTagsInfo info
	 * @return int
	 */
	public int insertInfo(MoTagsInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CO_TAGSInfo>
	 * @return int
	 */
	public int insertListInfo(List<MoTagsInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 查询所有的组件标签
	 * @return
	 */
	public List<String> queryAllTags() {
		return dao.queryAllTags();
	}

	/**
	 * Description: 根据组件ID查询分类标签
	 * @return
	 */
	public List<String> queryTagsById(String comp_id) {
		return dao.queryTagsById(comp_id);
	}

	/**
	 * Description: 根据组件ID删除分类标签
	 * @return
	 */
	public int deleteTagsById(String comp_id) {
		return dao.deleteInfoById(comp_id);
	}

	/**
	 * Description:
	 * @param comp_id
	 * @param tag_list
	 */
	public int insertTags(String comp_id, String[] tag_list) {
		if (Assert.notEmpty(tag_list)) {
			List<MoTagsInfo> infos = new ArrayList<MoTagsInfo>();
			for (String tag : tag_list) {
				if (Assert.notEmpty(tag)) {
					infos.add(new MoTagsInfo(comp_id, tag));
				}
			}
			return dao.insert(infos);
		}
		return 0;
	}

	/**
	 * Description:
	 * @param comp_id
	 * @param tag_list
	 */
	public void updateTags(String comp_id, String[] tag_list) {
		dao.deleteInfoById(comp_id);
		insertTags(comp_id, tag_list);
	}

	/**
	 * Description:
	 * @param tag_list
	 * @return
	 */
	public List<String> queryIdsByTags(String[] tag_list) {
		if (Assert.notEmpty(tag_list)) {
			StringBuilder sb = new StringBuilder();
			for (String tag : tag_list) {
				if (Assert.notEmpty(tag)) {
					sb.append('\'').append(tag).append("\',");
				}
			}
			if (Assert.notEmpty(sb)) {
				sb.deleteCharAt(sb.length() - 1);
				return dao.queryIdsByTags(sb.toString());
			}
		}
		return new ArrayList<String>();
	}

}