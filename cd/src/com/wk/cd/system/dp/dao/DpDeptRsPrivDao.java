/**
 * Title: DpDeptRsPrivDao.java
 * File Description: ������ԴȨ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import com.wk.cd.system.dp.info.DpDeptRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:������ԴȨ�ޱ�
 * @author AutoGen
 */
abstract class DpDeptRsPrivDao
		extends EntityDao<DpDeptRsPrivInfo> {
	/**
	 * Description: ���ݲ��Ŵ���ɾ����ԴȨ����Ϣ
	 * @param deptId
	 * @return
	 */
	@SqlParam(condition="DEPT_ID =:dept_id")
	abstract int deleteByDeptId(String dept_id);
	/**
	 * Description: ���ݲ��Ŵ����ѯ������ԴȨ����Ϣ
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from DP_DEPT_RS_PRIV where DEPT_ID =:dept_id")
	abstract DBIterator<String> iteratorDeptRsPrivInfos(String dept_id);

	/**
	 * ���ݶ�����ű����ѯ������Դ����
	 * @param dept_ids_str
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from DP_DEPT_RS_PRIV where DEPT_ID in ${dept_ids_str::1 = 0}", dynamic = true)
	abstract DBIterator<String> iteratorDeptRsByDeptIds(String dept_ids_str);
}