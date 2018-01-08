/**
 * Title: CeSystemTemplateDaoService.java
 * File Description: Ӧ��ϵͳģ���
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
 * Class description:Ӧ��ϵͳģ���
 * @author AutoGen
 */
public class CeSystemTemplateDaoService {
	@Inject private CeSystemTemplateDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeSystemTemplateInfo info
	 * @return CeSystemTemplateInfo
	 */
	public CeSystemTemplateInfo getInfoByKey(CeSystemTemplateInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeSystemTemplateInfo info
	 * @return CeSystemTemplateInfo
	 */
	public CeSystemTemplateInfo getInfoByKeyForUpdate(CeSystemTemplateInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeSystemTemplateInfo info
	 * @return int
	 */
	public int insertInfo(CeSystemTemplateInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeSystemTemplateInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeSystemTemplateInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description: ����ģ�����Ͳ�ѯģ����
	 * @return 
	 */
	public List<String> queryTemplateNameByType(TEMPLATE_TYPE template_type) {
		return dao.queryTemplateNameByType(template_type);
	}

	/** 
	 * Description: ɾ��ϵͳģ�������Ϣ
	 * @param sys_name 
	 */
	public int deleteSystempBySysName(String sys_name) {
	return dao.deleteSystempBySysName(sys_name);
		
	}

	/** 
	 * Description: ���ϵͳģ�������Ϣ�б�
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
	 * Description: ���ϵͳģ��ĸ���
	 * @param sys_name
	 * @return 
	 */
	public int countSystemTemplateBySysName(String sys_name) {
		return dao.countAllSystemTemplate(sys_name);
	}

	/** 
	 * Description: ����ϵͳ����ģ�����ͻ�ȡ��Ϣ
	 * @param sys_name
	 * @param product 
	 */
	public List<String> getInfosBySysAndType(String sys_name, TEMPLATE_TYPE template_type) {
		return dao.getInfosBySysAndType(sys_name, template_type);
	}
}