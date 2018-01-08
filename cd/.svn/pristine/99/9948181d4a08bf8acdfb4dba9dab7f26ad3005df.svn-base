/**
 * Title: OrganizationService.java
 * File Description: 递蓝科部门服务类
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年10月24日
 */
package com.wk.cd.dlk.dp.service;

import java.util.List;

import com.wk.cd.dlk.dp.dao.OrganizationDaoService;
import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.lang.Inject;


/**
 * Class Description: 递蓝科部门服务类
 * @author HT
 */
public class OrganizationService {
	@Inject private OrganizationDaoService organizationDaoService;

	/** 
	 * Description: 根据机构层次码查询机构信息
	 * @param orgid
	 * @return 
	 */
	public OrganizationInfo getOrganizationInfoByKey(String orgsyscoding) {
		return organizationDaoService.getOrgByOrgsyscoding(orgsyscoding);
	}

	/** 
	 * Description: 查询所有机构列表
	 * @return 
	 */
	public List<OrganizationInfo> listAllOrganization() {
		return organizationDaoService.listAllOrganization();
	}
}
