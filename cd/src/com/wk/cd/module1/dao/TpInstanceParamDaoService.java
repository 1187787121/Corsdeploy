/**
 * Title: TpInstanceParamDaoService.java
 * File Description: Ͷ��ģ��ʵ��������
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
 * Class description:Ͷ��ģ��ʵ��������
 * @author AutoGen
 */
public class TpInstanceParamDaoService {
	@Inject private TpInstanceParamDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param business_sys_name
	 * @param project_name
	 * @param instance_param_name
	 * @return TpInstanceParamInfo
	 */
	public TpInstanceParamInfo getInfoByKey(String business_sys_name, String project_name, String instance_param_name) {
		return dao.get(business_sys_name, project_name, instance_param_name);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param business_sys_name
	 * @param project_name
	 * @param instance_param_name
	 * @return TpInstanceParamInfo
	 */
	public TpInstanceParamInfo getInfoByKeyForUpdate(String business_sys_name, String project_name, String instance_param_name) {
		return dao.getForUpdate(business_sys_name, project_name, instance_param_name);
	}

	/**
	 * ����һ����¼
	 * @param TpInstanceParamInfo info
	 * @return int
	 */
	public int insertInfo(TpInstanceParamInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<TpInstanceParamInfo>
	 * @return int
	 */
	public int insertListInfo(List<TpInstanceParamInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: ����ϵͳ������Ŀ����ģ�����ͻ�ȡʵ�������б�
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public List<TpInstanceParamInfo> queryInstanceParamBySysPro(String business_sys_name,String project_name,TEMPLATE_TYPE template_type){
		return dao.queryInstanceParamBySysPro(business_sys_name, project_name, template_type);
	}

	
	/**
	 * Description: ɾ��һ����¼ 
	 * @param info
	 * @return
	 */
	public int deleteInfo(TpInstanceParamInfo info){
		return dao.delete(info);
	}
	
	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��ţ��������ͣ�ģ������ɾ��������¼
	 * @param business_sys_name
	 * @param project_name
	 * @param param_type
	 * @return
	 */
	public int deleteInfoByPjBsAndType(String business_sys_name,String project_name,PARAM_TYPE param_type,TEMPLATE_TYPE template_type){
		return dao.deleteInfoByPjBsAndType(business_sys_name,project_name,param_type,template_type);
	}
	
	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��Ż�ýڵ����
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public List<String> getNodeTypeByBsAndPj(String business_sys_name,String project_name){
		return dao.getNodeTypeByBsAndPj(business_sys_name, project_name);
	}
}