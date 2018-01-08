/**
 * Title: DcDictEnuDaoService.java
 * File Description: �����ֵ�ö�ٱ�
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
 * Class description:�����ֵ�ö�ٱ�
 * @author AutoGen
 */
public class DcDictEnuDaoService {
	@Inject private DcDictEnuDao dao;
//	@Inject private SysEnuDao sysDao;

	/**
	 * ����������ѯһ����¼
	 * @param DcDictEnuInfo info
	 * @return DcDictEnuInfo
	 */
	public DcDictEnuInfo getInfoByKey(DcDictEnuInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param DcDictEnuInfo info
	 * @return DcDictEnuInfo
	 */
	public DcDictEnuInfo getInfoByKeyForUpdate(DcDictEnuInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param DcDictEnuInfo info
	 * @return int
	 */
	public int insertInfo(DcDictEnuInfo info) {
//		//����dc_dict_enu���в����¼��ͬʱ����sys_vframe_enumvalue���в���һ����Ӧ�ļ�¼
//		sysDao.insertSysEnu("",info.getDomain_name(),info.getEnu_code(),info.getEnu_value(),info.getEnu_bk_expl());
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<DcDictEnuInfo>
	 * @return int
	 */
	public int insertListInfo(List<DcDictEnuInfo> infos){
//		if (infos != null && infos.size() > 0) {
//			for (DcDictEnuInfo info : infos) {
//				//����dc_dict_enu���в����¼��ͬʱ����sys_vframe_enumvalue���в���һ����Ӧ�ļ�¼
//				sysDao.insertSysEnu("",info.getDomain_name(),info.getEnu_code(),info.getEnu_value(),info.getEnu_bk_expl());
//			}
//		}
		return dao.insert(infos);
	}


	
	/** 
	 * Description: ����domain_name��ȡö����
	 * @param domain_name ������
	 * @return 
	 */
	public List<DcDictEnuInfo> getEnuListInfo(String domain_name) {
		return dao.getEnuListInfo(domain_name);
	}

	
	/** 
	 * Description: ����domain_nameɾ����������������ö����Ϣ 
	 * @param domain_name ������
	 * @return 
	 */
	public int deleteEnuList(String domain_name) {
//		sysDao.deleteEnuList(domain_name);
		return dao.deleteEnuList(domain_name);
	}

	
	/** 
	 * Description: ���ö�ٱ�����domain_name��¼
	 * @param domain_name ������
	 * @return 
	 */
	public boolean checkDomainName(String domain_name) {
		if(dao.countEnuByDomain(domain_name)>0){
			return true;
		}
		return false;
	}

}