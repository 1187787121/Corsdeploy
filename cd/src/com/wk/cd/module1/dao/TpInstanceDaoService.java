/**
 * Title: TpInstanceDaoService.java
 * File Description: Ͷ��ģ��ʵ����
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
 * Class description:Ͷ��ģ��ʵ����
 * @author AutoGen
 */
public class TpInstanceDaoService {
	@Inject
	private TpInstanceDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param TpInstanceInfo info
	 * @return TpInstanceInfo
	 */
	public TpInstanceInfo getInfoByKey(TpInstanceInfo info) {
		return dao.get(info);
	}

	/**
	 * Description: ����������ѯһ����¼
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
	 * ����������ѯһ����¼���Լ�¼����
	 * @param TpInstanceInfo info
	 * @return TpInstanceInfo
	 */
	public TpInstanceInfo getInfoByKeyForUpdate(TpInstanceInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param TpInstanceInfo info
	 * @return int
	 */
	public int insertInfo(TpInstanceInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
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
	 * Description: ����ϵͳ������Ŀ����ѯ���е�ʵ��
	 * @param business_sys_name
	 * @param project_name
	 */
	public List<TpInstanceInfo> queryInfoByBsnAndProjn(
			String business_sys_name, String project_name) {
		return dao.queryInfoByBsnAndProjn(business_sys_name, project_name);
	}

}