/**
 * Title: WkWorkSrvDao.java
 * File Description: ����������ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.work.wk.info.*;

/**
 * Class description:����������ñ�
 * @author AutoGen
 */
abstract class WkWorkSrvDao
		extends EntityDao<WkWorkSrvInfo> {
	/**
	 * Description: ����work_code��ȡ���������Ϣ�б�
	 * @param work_code �������
	 * @return
	 */
	@SqlParam(condition = "WORK_CODE = :work_code")
	abstract List<WkWorkSrvInfo> getWorkSrvList(String work_code);

	/**
	 * �����������ɾ����Ӧ������Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	@SqlParam(condition = "WORK_CODE = :work_code")
	abstract int deleteWorkByWorkCode(String work_code);
}