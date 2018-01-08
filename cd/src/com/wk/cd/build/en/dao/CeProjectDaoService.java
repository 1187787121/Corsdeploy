/**
 * Title: CeProjectDaoService.java
 * File Description: 项目表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.lang.Inject;
import com.wk.util.StringUtil;

/**
 * Class description:项目表
 * @author AutoGen
 */
public class CeProjectDaoService {
	@Inject private CeProjectDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param CeProjectInfo info
	 * @return CeProjectInfo
	 */
	public CeProjectInfo getInfoByKey(CeProjectInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param CeProjectInfo info
	 * @return CeProjectInfo
	 */
	public CeProjectInfo getInfoByKeyForUpdate(CeProjectInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param CeProjectInfo info
	 * @return int
	 */
	public int insertInfo(CeProjectInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CeProjectInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeProjectInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 
	 * @param project_name
	 * @return 
	 */
	public int countProjectName(String project_name) {
		return dao.countProjectName(project_name);
	}

	/** 
	 * Description: 删除项目信息
	 * @param project_name 
	 */
	public int deleteProjectInfo(String project_name) {
		return dao.delete(project_name);
	}

	/** 
	 * Description: 更新项目信息
	 * @param proj_update 
	 */
	public int updateProjectInfo(CeProjectInfo proj_update) {
		return dao.update(proj_update);
	}

	/** 
	 * Description: 分页查询项目信息
	 * @param sys_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<CeProjectInfo> pageProjectBySysName(String sys_name,String sys_cn_name,
			int start_recd, int limit_recd) {
		List<CeProjectInfo> proj_list = dao.pageProjectBySysName(sys_name,sys_cn_name, start_recd, limit_recd);
		return proj_list;
	}

	/** 
	 * Description: 得到项目总数
	 * @param sys_name
	 * @return 
	 */
	public int countProjectNameBySysName(String sys_name,String sys_cn_name) {
		 
		return dao.countProjectNameBySysName(sys_name,sys_cn_name);
	}

	/** 
	 * Description: 
	 * @param project_name
	 * @return 
	 */
	public CeProjectInfo getInfoByProjectName(String project_name) {
		CeProjectInfo info = new CeProjectInfo();
		info.setProject_name(project_name);
		return getInfoByKey(info);
	}

	/** 
	 * Description: 
	 * @param order_col_name
	 * @param order_type
	 * @param sys_name
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<CeProjectBean> pageAllProject(String sys_name, String sys_cn_name, String order_col_name,
			ORDER_TYPE order_type, int start_recd,
			int limit_recd) {
		String order_type_str = order_type.getName();
		if("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)){
			return dao.pageAllProjectDefault(sys_name, sys_cn_name, start_recd, limit_recd);
		}
		if("create_bk_date".equals(order_col_name)) {
			return dao.pageAllProject(sys_name, sys_cn_name, order_col_name+" "+order_type_str+",create_bk_time",order_type_str, start_recd, limit_recd);
		}
		return dao.pageAllProject(sys_name, sys_cn_name, order_col_name,order_type_str, start_recd, limit_recd);
	}

	/** 
	 * Description: 根据系统名查找项目表
	 * @param sys_name
	 * @return 
	 */
	public List<CeProjectInfo> getInfoBySysName(String sys_name) {
		return dao.getInfoBySysName(sys_name);
	}

}