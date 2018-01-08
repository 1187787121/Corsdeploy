/**
 * Title: PgInteStepDao.java
 * File Description: ���ɷ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:���ɷ��������
 * @author AutoGen
 */
abstract class PgInteStepDao extends EntityDao<PgInteStepInfo> {

	/** 
	 * Description: 
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id")
	abstract int deleteProgStepByProgId(String prog_id);

	/** 
	 * Description: ��ü��ɷ��������б�
	 * @param prog_id
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id")
	abstract List<PgInteStepInfo> getInteListByProgId(String prog_id);

	/** 
	 * Description: ͳ���м�������
	 * @param prog_id
	 * @param step_id
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id")
	abstract int countPgStepByProgId(String prog_id);

	/** 
	 * Description: ���ݲ������ͻ�ȡ��¼
	 * @param prog_id
	 * @param step_type
	 * @return 
	 */
	@SqlParam(condition="PROG_ID =:prog_id AND STEP_TYPE =:step_type")
	abstract List<PgInteStepInfo> getInfoByType(String prog_id, STEP_TYPE step_type);
}