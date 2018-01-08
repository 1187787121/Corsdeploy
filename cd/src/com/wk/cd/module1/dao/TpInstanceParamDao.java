/**
 * Title: TpInstanceParamDao.java
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
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:Ͷ��ģ��ʵ��������
 * @author AutoGen
 */
abstract class TpInstanceParamDao extends EntityDao<TpInstanceParamInfo> {
	/**
	 * Description: ����ϵͳ������Ŀ����ģ�����ͻ�ȡʵ�������б�
	 * @return
	 */
	@SqlParam(condition = "BUSINESS_SYS_NAME =:business_sys_name AND PROJECT_NAME =:project_name AND TEMPLATE_TYPE =:template_type")
	abstract List<TpInstanceParamInfo> queryInstanceParamBySysPro(String business_sys_name,
			String project_name,TEMPLATE_TYPE template_type);
	
	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��ţ��������ͣ�ģ������ɾ��������¼
	 * @param business_sys_name
	 * @param project_name
	 * @param param_type
	 * @return
	 */
	@SqlParam(condition = "BUSINESS_SYS_NAME =:business_sys_name AND PROJECT_NAME =:project_name AND INSTANCE_PARAM_TYPE=:param_type AND TEMPLATE_TYPE=:template_type")
	abstract int deleteInfoByPjBsAndType(String business_sys_name,String project_name,PARAM_TYPE param_type,TEMPLATE_TYPE template_type);
	
	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��Ż�ýڵ����
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	@SqlParam(sql="SELECT DISTINCT(NODE_TYPE) FROM tp_instance_param where BUSINESS_SYS_NAME =:business_sys_name and PROJECT_NAME =:project_name and INSTANCE_PARAM_TYPE = 2")
	abstract List<String> getNodeTypeByBsAndPj(String business_sys_name,String project_name);
}