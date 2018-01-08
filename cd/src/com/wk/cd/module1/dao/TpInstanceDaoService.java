/**
 * Title: TpInstanceDaoService.java
 * File Description: 投产模版实例表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-14
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.TpInstanceInfo;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.dao.TpInstanceDao;
import com.wk.lang.Inject;

/**
 * Class description:投产模版实例表
 * @author AutoGen
 */
public class TpInstanceDaoService {
	@Inject
	private TpInstanceDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param TpInstanceInfo info
	 * @return TpInstanceInfo
	 */
	public TpInstanceInfo getInfoByKey(TpInstanceInfo info) {
		return dao.get(info);
	}

	/**
	 * Description: 根据主键查询一条记录
	 * @param business_sys_name
	 * @param project_name
	 * @param template_type
	 * @return
	 */
	public TpInstanceInfo getInfoByKey2(String business_sys_name,
			String project_name, TEMPLATE_TYPE template_type) {
		return dao.get(business_sys_name, project_name, template_type);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param TpInstanceInfo info
	 * @return TpInstanceInfo
	 */
	public TpInstanceInfo getInfoByKeyForUpdate(TpInstanceInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param TpInstanceInfo info
	 * @return int
	 */
	public int insertInfo(TpInstanceInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<TpInstanceInfo>
	 * @return int
	 */
	public int insertListInfo(List<TpInstanceInfo> infos) {
		return dao.insert(infos);
	}

	public void deleteInfoByKey(TpInstanceInfo info) {
		dao.delete(info);
	}

	/**
	 * Description: 根据系统名和项目名查询所有的实例
	 * @param business_sys_name
	 * @param project_name
	 */
	public List<TpInstanceInfo> queryInfoByBsnAndProjn(
			String business_sys_name, String project_name) {
		return dao.queryInfoByBsnAndProjn(business_sys_name, project_name);
	}

}