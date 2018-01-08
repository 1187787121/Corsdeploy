/**
 * Title: DpDeptOptPrivDao.java
 * File Description: ���Ų���Ȩ�����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.dp.dao;

import com.wk.db.*;
import com.wk.cd.system.dp.info.*;

/**
 * Class description:���Ų���Ȩ�����ñ�
 * @author AutoGen
 */
abstract class DpDeptOptPrivDao extends EntityDao<DpDeptOptPrivInfo> {

	/** 
	 * Description: ���ݲ��ű����ѯ���Ų���Ȩ����Ϣ�б�
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptPrivByDept(String dept_id);

	/** 
	 * Description: ���ݲ��ű����ѯ���Ų�����ֹȨ����Ϣ�б�����������Դ�Ĳ���Ȩ����Ϣ��
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id and priv_flag=2")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptForbidPrivByDept(String dept_id);

	/** 
	 * Description: ���ݲ��ű����ѯ���Ų�������Ȩ����Ϣ�б�����ֹ����Դ�Ĳ���Ȩ����Ϣ��
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id and priv_flag=1")
	abstract DBIterator<DpDeptOptPrivInfo> queryOptAllowPrivByDept(String dept_id);

	/** 
	 * Description: ���ݲ���IDɾ�����Ų���Ȩ��
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition="dept_id=:dept_id")
	abstract int deleteDeptOptPrivByDpetId(String dept_id);
}