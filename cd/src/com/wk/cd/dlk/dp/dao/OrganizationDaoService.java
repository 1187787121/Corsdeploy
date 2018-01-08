/**
 * Title: OrganizationDaoService.java
 * File Description: 机构表
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
 * Class description:机构表
 * @author AutoGen
 */
public class OrganizationDaoService {
	@Inject 
	@DBName("dlkdb")
	private OrganizationDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param OrganizationInfo info
	 * @return OrganizationInfo
	 */
	public OrganizationInfo getInfoByKey(OrganizationInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param OrganizationInfo info
	 * @return OrganizationInfo
	 */
	public OrganizationInfo getInfoByKeyForUpdate(OrganizationInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param OrganizationInfo info
	 * @return int
	 */
	public int insertInfo(OrganizationInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<OrganizationInfo>
	 * @return int
	 */
	public int insertListInfo(List<OrganizationInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 根据机构层次码查询机构信息
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
	 * Description: 查询所有机构列表
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