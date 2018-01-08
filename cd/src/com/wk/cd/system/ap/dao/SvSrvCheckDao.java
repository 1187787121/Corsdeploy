/**
 * Title: SvSrvCheckDao.java
 * File Description: ���񸴺˶����
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:���񸴺˶����
 * @author AutoGen
 */
abstract class SvSrvCheckDao
		extends EntityDao<SvSrvCheckInfo> {
	// ���ݷ������Ʋ�ѯһ����񸴺���Ϣ
	@SqlParam(condition = "SRV_NAME = :srv_name", orderBy = "check_seq")
	abstract List<SvSrvCheckInfo> getListInfoByName(String srv_name);

	// ���ݲ��ű���ͷ�������ɾ��һ����񸴺���Ϣ
	@SqlParam(condition = "CHECK_DEPT_ID = :dept_id and SRV_NAME = :srv_name")
	abstract int deleteInfo(String dept_id, String srv_name);
	
	// ���ݷ�������ɾ��һ����񸴺���Ϣ
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteSvSrvCheck(String srv_name);

	// ���ղ��ű���ͷ������Ʋ�ѯ��¼����
	@SqlParam(sql = "select count(*) from sv_srv_check where (CHECK_DEPT_ID in ${dept_str::1=0}) and (SRV_NAME = '${srv_name::1=0}')", dynamic = true)
//	@SqlParam(condition = "CHECK_DEPT_ID in :dept_str and SRV_NAME = :srv_name")
	abstract int countInfos(String dept_str, String srv_name);
	
	// ���շ������Ʋ�ѯ��¼����
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countSvSrvCheck(String srv_name);

	// ���ղ��Ž�ɫ�б��ѯ���˷���
	@SqlParam(sql = "select srv_name, check_seq, chk_dprl_code, chk_aprov_category from sv_srv_check where (CHK_DPRL_CODE in ${check_dprl_code_str})", dynamic = true)
	abstract List<SvSrvCheckInfo> querySrvCheckByDprlCode(
			String check_dprl_code_str);

	//��ѯ���˲��Ž�ɫ�������Ϣ
	@SqlParam(condition = "check_dept_id = :check_dept_id and srv_name = :srv_name", orderBy = "check_seq")
	abstract List<ChkDprlCodeBean> queryCheckDprlBySrvName(String check_dept_id, String srv_name);
	
	// ����ָ���ķ������ƺʹ���ָ��������ŵ���С�ĸ������
	@SqlParam(sql = "select min(check_seq) from sv_srv_check where check_dept_id = ${dept_id} and srv_name = ${srv_name} and check_seq > ${check_seq}")
	abstract int queryMinCheckSeq(String dept_id, String srv_name, int check_seq);
}