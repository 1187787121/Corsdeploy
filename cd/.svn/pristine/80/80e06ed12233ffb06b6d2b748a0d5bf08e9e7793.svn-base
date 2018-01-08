/**
 * Title: DcDictEnuDaoService.java
 * File Description: 数据字典枚举表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dc.dao;

import java.util.List;

import com.wk.cd.system.dc.info.DcDictEnuInfo;
import com.wk.lang.Inject;

/**
 * Class description:数据字典枚举表
 * @author AutoGen
 */
public class DcDictEnuDaoService {
	@Inject private DcDictEnuDao dao;
//	@Inject private SysEnuDao sysDao;

	/**
	 * 根据主键查询一条记录
	 * @param DcDictEnuInfo info
	 * @return DcDictEnuInfo
	 */
	public DcDictEnuInfo getInfoByKey(DcDictEnuInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param DcDictEnuInfo info
	 * @return DcDictEnuInfo
	 */
	public DcDictEnuInfo getInfoByKeyForUpdate(DcDictEnuInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param DcDictEnuInfo info
	 * @return int
	 */
	public int insertInfo(DcDictEnuInfo info) {
//		//在向dc_dict_enu表中插入记录的同时，向sys_vframe_enumvalue表中插入一条对应的记录
//		sysDao.insertSysEnu("",info.getDomain_name(),info.getEnu_code(),info.getEnu_value(),info.getEnu_bk_expl());
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<DcDictEnuInfo>
	 * @return int
	 */
	public int insertListInfo(List<DcDictEnuInfo> infos){
//		if (infos != null && infos.size() > 0) {
//			for (DcDictEnuInfo info : infos) {
//				//在向dc_dict_enu表中插入记录的同时，向sys_vframe_enumvalue表中插入一条对应的记录
//				sysDao.insertSysEnu("",info.getDomain_name(),info.getEnu_code(),info.getEnu_value(),info.getEnu_bk_expl());
//			}
//		}
		return dao.insert(infos);
	}


	
	/** 
	 * Description: 根据domain_name获取枚举列
	 * @param domain_name 域名称
	 * @return 
	 */
	public List<DcDictEnuInfo> getEnuListInfo(String domain_name) {
		return dao.getEnuListInfo(domain_name);
	}

	
	/** 
	 * Description: 根据domain_name删除复合条件的所有枚举信息 
	 * @param domain_name 域名称
	 * @return 
	 */
	public int deleteEnuList(String domain_name) {
//		sysDao.deleteEnuList(domain_name);
		return dao.deleteEnuList(domain_name);
	}

	
	/** 
	 * Description: 检查枚举表有无domain_name记录
	 * @param domain_name 域名称
	 * @return 
	 */
	public boolean checkDomainName(String domain_name) {
		if(dao.countEnuByDomain(domain_name)>0){
			return true;
		}
		return false;
	}

}