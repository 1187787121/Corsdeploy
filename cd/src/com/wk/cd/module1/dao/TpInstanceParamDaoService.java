/**
 * Title: TpInstanceParamDaoService.java
 * File Description: 投产模版实例参数表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-2-25
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.TpInstanceParamInfo;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.dao.TpInstanceParamDao;
import com.wk.lang.Inject;

/**
 * Class description:投产模版实例参数表
 * @author AutoGen
 */
public class TpInstanceParamDaoService {
	@Inject private TpInstanceParamDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param business_sys_name
	 * @param project_name
	 * @param instance_param_name
	 * @return TpInstanceParamInfo
	 */
	public TpInstanceParamInfo getInfoByKey(String business_sys_name, String project_name, String instance_param_name) {
		return dao.get(business_sys_name, project_name, instance_param_name);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param business_sys_name
	 * @param project_name
	 * @param instance_param_name
	 * @return TpInstanceParamInfo
	 */
	public TpInstanceParamInfo getInfoByKeyForUpdate(String business_sys_name, String project_name, String instance_param_name) {
		return dao.getForUpdate(business_sys_name, project_name, instance_param_name);
	}

	/**
	 * 插入一条记录
	 * @param TpInstanceParamInfo info
	 * @return int
	 */
	public int insertInfo(TpInstanceParamInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<TpInstanceParamInfo>
	 * @return int
	 */
	public int insertListInfo(List<TpInstanceParamInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: 根据系统名、项目名、模板类型获取实例参数列表
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public List<TpInstanceParamInfo> queryInstanceParamBySysPro(String business_sys_name,String project_name,TEMPLATE_TYPE template_type){
		return dao.queryInstanceParamBySysPro(business_sys_name, project_name, template_type);
	}

	
	/**
	 * Description: 删除一条记录 
	 * @param info
	 * @return
	 */
	public int deleteInfo(TpInstanceParamInfo info){
		return dao.delete(info);
	}
	
	/**
	 * Description: 根据业务系统名，项目编号，参数类型，模板类型删除多条记录
	 * @param business_sys_name
	 * @param project_name
	 * @param param_type
	 * @return
	 */
	public int deleteInfoByPjBsAndType(String business_sys_name,String project_name,PARAM_TYPE param_type,TEMPLATE_TYPE template_type){
		return dao.deleteInfoByPjBsAndType(business_sys_name,project_name,param_type,template_type);
	}
	
	/**
	 * Description: 根据业务系统名和项目编号获得节点类别
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public List<String> getNodeTypeByBsAndPj(String business_sys_name,String project_name){
		return dao.getNodeTypeByBsAndPj(business_sys_name, project_name);
	}
}