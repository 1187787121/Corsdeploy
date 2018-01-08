/**
 * Title: CeSystemTemplateDaoService.java
 * File Description: 应用系统模板表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.info.CeSystemTemplateInfo;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:应用系统模板表
 * @author AutoGen
 */
public class CeSystemTemplateDaoService {
	@Inject private CeSystemTemplateDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param CeSystemTemplateInfo info
	 * @return CeSystemTemplateInfo
	 */
	public CeSystemTemplateInfo getInfoByKey(CeSystemTemplateInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param CeSystemTemplateInfo info
	 * @return CeSystemTemplateInfo
	 */
	public CeSystemTemplateInfo getInfoByKeyForUpdate(CeSystemTemplateInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param CeSystemTemplateInfo info
	 * @return int
	 */
	public int insertInfo(CeSystemTemplateInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CeSystemTemplateInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeSystemTemplateInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description: 根据模版类型查询模板名
	 * @return 
	 */
	public List<String> queryTemplateNameByType(TEMPLATE_TYPE template_type) {
		return dao.queryTemplateNameByType(template_type);
	}

	/** 
	 * Description: 删除系统模板关联信息
	 * @param sys_name 
	 */
	public int deleteSystempBySysName(String sys_name) {
	return dao.deleteSystempBySysName(sys_name);
		
	}

	/** 
	 * Description: 获得系统模板关联信息列表
	 * @param sys_name
	 * @return 
	 */
	public List<CeSystemTemplateInfo> getInfoBySysName(String sys_name) {
		DBIterator<CeSystemTemplateInfo> iterator = dao.getInfoBySysName(sys_name);
		List<CeSystemTemplateInfo> tem_list = new ArrayList<CeSystemTemplateInfo>();
		try {
			while (iterator.hasNext()) {
				tem_list.add(iterator.next());
			}
		} finally{
			iterator.close();
		}
		return tem_list;
	}

	/** 
	 * Description: 获得系统模板的个数
	 * @param sys_name
	 * @return 
	 */
	public int countSystemTemplateBySysName(String sys_name) {
		return dao.countAllSystemTemplate(sys_name);
	}

	/** 
	 * Description: 根据系统名和模板类型获取信息
	 * @param sys_name
	 * @param product 
	 */
	public List<String> getInfosBySysAndType(String sys_name, TEMPLATE_TYPE template_type) {
		return dao.getInfosBySysAndType(sys_name, template_type);
	}
}