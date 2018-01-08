/**
 * Title: DcDictDao.java
 * File Description: �����ֵ���Ϣ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dc.dao;

import java.util.List;

import com.wk.cd.system.dc.info.DcDictInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:�����ֵ���Ϣ��
 * @author AutoGen
 */
abstract class DcDictDao extends EntityDao<DcDictInfo> {

	
	/** 
	 * Description: ���ݹؼ��ʵ�domain_name��domian_cn_name�в�ѯ�����
	 * @param keyword �ؼ���	
	 * @param start_recd ��ʼ��¼��
	 * @param limit_recd ��ѯ����
	 * @return 
	 */
	@SqlParam(sql="select domain_name, domain_cn_name, fld_type, fld_length, fld_scale, enu_yn_flag from dc_dict where ((domain_name like '%${keyword1}%') or (domain_cn_name like '%${keyword2}%')) order by domain_name",dynamic=true)
    abstract List<DcDictInfo> pageDict(String keyword1,String keyword2,int start_recd, int limit_recd);

	/** 
	 * Description: ��ȡ��ѯ������
	 * @param keyword �ؼ���	
	 * @return 
	 */
	@SqlParam(sql="select count(*) from dc_dict where ((domain_name like '%${keyword1}%') or (domain_cn_name like '%${keyword2}%'))",dynamic=true)
	abstract int getCount(String keyword1, String keyword2);
	
	/** 
	 * Description:  ����domain_name��ѯ�����ֵ���ϸ��Ϣ
	 * @param domain_name ������
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract DcDictInfo getDictInfo(String domain_name);
	
	/** 
	 * Description: ����domain_name��ѯ�����ֵ��¼��
	 * @param domain_name ������
	 * @return 
	 */
	@SqlParam(condition="domain_name=:domain_name")
	abstract int countByDomainName(String domain_name);

	
	/** 
	 * Description: ����domain_cn_name��ѯ�����ֵ��¼��
	 * @param domain_cn_name ����������
	 * @return 
	 */
	@SqlParam(condition="domain_cn_name=:domain_cn_name")
	abstract int countByDomainCnName(String domain_cn_name);

}