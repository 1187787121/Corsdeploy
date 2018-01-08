/**
 * Title: DpDeptSocPrivDao.java
 * File Description: ��������ԴȨ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.List;

import com.wk.cd.system.dp.info.DpDeptSocPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:��������ԴȨ�ޱ�
 * @author AutoGen
 */
abstract class DpDeptSocPrivDao
		extends EntityDao<DpDeptSocPrivInfo> {

	/**
	 * Description: ���ݲ��Ŵ����ѯ��������ԴȨ����Ϣ
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from DP_DEPT_SOC_PRIV where DEPT_ID = :dept_id")
	abstract List<String> queryDeptSocPrivInfos(String dept_id);

	/**
	 * Description: ���ݲ��ű���ɾ�����м�¼
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition = "DEPT_ID = :dept_id")
	abstract int deleteInfo(String dept_id);

	/**
	 * Description: ��������Դ����ɾ�����м�¼
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "SOC_NAME = :soc_name")
	abstract int deleteInfoBySocName(String soc_name);
	/**
	 * ���ݶ�����ű����ѯ��������Դ����
	 * @param dept_ids_str
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from DP_DEPT_SOC_PRIV where DEPT_ID in ${dept_ids_str::1 = 0}", dynamic = true)
	abstract List<String> queryDeptRsByDeptIds(String dept_ids_str);

	/**
	 * Description: ���ݲ��Ŵ����ѯ������ԴȨ����Ϣ
	 * @param deptId
	 * @return
	 */
	@SqlParam(querySet={"SOC_NAME"}, condition = "DEPT_ID =:dept_id")
	abstract DBIterator<String> iteratorDeptSocPrivInfos(String dept_id);
}