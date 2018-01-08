/**
 * Title: OrganizationDaoService.java
 * File Description: ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */
package com.wk.cd.dlk.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.db.DBIterator;
import com.wk.db.DBName;
import com.wk.lang.Inject;

/**
 * Class description:������
 * @author AutoGen
 */
public class OrganizationDaoService {
	@Inject 
	@DBName("dlkdb")
	private OrganizationDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param OrganizationInfo info
	 * @return OrganizationInfo
	 */
	public OrganizationInfo getInfoByKey(OrganizationInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param OrganizationInfo info
	 * @return OrganizationInfo
	 */
	public OrganizationInfo getInfoByKeyForUpdate(OrganizationInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param OrganizationInfo info
	 * @return int
	 */
	public int insertInfo(OrganizationInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<OrganizationInfo>
	 * @return int
	 */
	public int insertListInfo(List<OrganizationInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ���ݻ���������ѯ������Ϣ
	 * @param orgsyscoding
	 * @return 
	 */
	public OrganizationInfo getOrgByOrgsyscoding(String orgsyscoding) {
		OrganizationInfo info = dao.getOrgByOrgsyscoding(orgsyscoding);
		if(Assert.isEmpty(info)){
			throw new RecordNotFoundException().addScene("TABLE", OrganizationInfo.TABLECN).addScene(
					"FIELD", orgsyscoding);
		}
		return info;
	}

	/** 
	 * Description: ��ѯ���л����б�
	 * @return 
	 */
	public List<OrganizationInfo> listAllOrganization() {
		List<OrganizationInfo> result = new ArrayList<OrganizationInfo>();
		DBIterator<OrganizationInfo> dbIterator = dao.iteratorAllOrganization();
		try {
			while (dbIterator.hasNext()) {
				result.add(dbIterator.next());
			}
		} finally {
			dbIterator.close();
		}
		return result;
	}
}