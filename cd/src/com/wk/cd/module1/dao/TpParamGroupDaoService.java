/**
 * Title: TpParamGroupDaoService.java
 * File Description: ʵ�����������ݱ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-5-30
 */
package com.wk.cd.module1.dao;

import java.util.List;

import com.wk.cd.module1.info.TpParamGroupInfo;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.dao.TpParamGroupDao;
import com.wk.lang.Inject;

/**
 * Class description:ʵ�����������ݱ�
 * @author AutoGen
 */
public class TpParamGroupDaoService {
	@Inject
	private TpParamGroupDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param TpParamGroupInfo info
	 * @return TpParamGroupInfo
	 */
	public TpParamGroupInfo getInfoByKey(TpParamGroupInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param TpParamGroupInfo info
	 * @return TpParamGroupInfo
	 */
	public TpParamGroupInfo getInfoByKeyForUpdate(TpParamGroupInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param TpParamGroupInfo info
	 * @return int
	 */
	public int insertInfo(TpParamGroupInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<TpParamGroupInfo>
	 * @return int
	 */
	public int insertListInfo(List<TpParamGroupInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��š����߰����Ʋ�ѯ������¼
	 * @param business_sys_name
	 * @param project_name
	 * @param propackage_name
	 * @return
	 */
	public List<TpParamGroupInfo> getListInfoByBsAndPj(
			String business_sys_name, String project_name,
			String propackage_name,String node_type) {
		return dao.getListInfoByBsAndPj(business_sys_name, project_name,business_sys_name,project_name,
				propackage_name,business_sys_name, project_name, node_type);
	}

	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��š�ģ������ɾ��������¼
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public int deleteListInfoByBsAndPj(String business_sys_name,
			String project_name, TEMPLATE_TYPE template_type) {
		return dao.deleteListInfoByBsAndPj(business_sys_name, project_name,
				template_type);
	}

	/**
	 * Description: ����ҵ��ϵͳ������Ŀ��š�ģ������ ��ѯ������¼
	 * @param business_sys_name
	 * @param project_name
	 * @param template_type
	 * @return
	 */
	public List<TpParamGroupInfo> queryInfoByBsAndPjAndType(
			String business_sys_name, String project_name,
			TEMPLATE_TYPE template_type) {
		return dao.queryInfoByBsAndPjAndType(business_sys_name, project_name,
				template_type);

	}
	
	/**
	 * Description: ����Ͷ��������ģ�����Ͳ�ѯ��Ӧ��Ͷ��������
	 * @return
	 */
	public List<TpParamGroupInfo> queryParamsByDppAndType(String business_sys_name, String project_name, String propackage_name, TEMPLATE_TYPE template_type){
		return dao.queryParamsByDppAndType(business_sys_name, project_name, propackage_name, template_type);
	}
}