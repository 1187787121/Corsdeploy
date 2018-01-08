/**
 * Title: CeProjectDaoService.java
 * File Description: ��Ŀ��
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
 * Class description:��Ŀ��
 * @author AutoGen
 */
public class CeProjectDaoService {
	@Inject private CeProjectDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeProjectInfo info
	 * @return CeProjectInfo
	 */
	public CeProjectInfo getInfoByKey(CeProjectInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeProjectInfo info
	 * @return CeProjectInfo
	 */
	public CeProjectInfo getInfoByKeyForUpdate(CeProjectInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeProjectInfo info
	 * @return int
	 */
	public int insertInfo(CeProjectInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
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
	 * Description: ɾ����Ŀ��Ϣ
	 * @param project_name 
	 */
	public int deleteProjectInfo(String project_name) {
		return dao.delete(project_name);
	}

	/** 
	 * Description: ������Ŀ��Ϣ
	 * @param proj_update 
	 */
	public int updateProjectInfo(CeProjectInfo proj_update) {
		return dao.update(proj_update);
	}

	/** 
	 * Description: ��ҳ��ѯ��Ŀ��Ϣ
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
	 * Description: �õ���Ŀ����
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
	 * Description: ����ϵͳ��������Ŀ��
	 * @param sys_name
	 * @return 
	 */
	public List<CeProjectInfo> getInfoBySysName(String sys_name) {
		return dao.getInfoBySysName(sys_name);
	}

}