/**
 * Title: OrganizationService.java
 * File Description: �����Ʋ��ŷ�����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016��10��24��
 */
package com.wk.cd.dlk.dp.service;

import java.util.List;

import com.wk.cd.dlk.dp.dao.OrganizationDaoService;
import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.lang.Inject;


/**
 * Class Description: �����Ʋ��ŷ�����
 * @author HT
 */
public class OrganizationService {
	@Inject private OrganizationDaoService organizationDaoService;

	/** 
	 * Description: ���ݻ���������ѯ������Ϣ
	 * @param orgid
	 * @return 
	 */
	public OrganizationInfo getOrganizationInfoByKey(String orgsyscoding) {
		return organizationDaoService.getOrgByOrgsyscoding(orgsyscoding);
	}

	/** 
	 * Description: ��ѯ���л����б�
	 * @return 
	 */
	public List<OrganizationInfo> listAllOrganization() {
		return organizationDaoService.listAllOrganization();
	}
}
