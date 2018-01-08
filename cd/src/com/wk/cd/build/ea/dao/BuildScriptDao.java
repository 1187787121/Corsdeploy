/**
 * Title: BuildScriptDao.java
 * File Description: �����ű���Ϣ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.BuildScriptInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:�����ű���Ϣ��
 * @author AutoGen
 */
abstract class BuildScriptDao
		extends EntityDao<BuildScriptInfo> {

	/**
	 * Description: ����������ѯһ����¼
	 * @param work_id
	 * @param script_bk_seq
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract BuildScriptInfo getScriptByKey(String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: �޸Ĺ�����ʱ���״̬
	 * @param end_bk_tm
	 * @param exe_status
	 * @param exe_result
	 * @param work_id
	 * @param script_type
	 * @param scirpt_bk_seq
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_STATUS", "EXE_RESULT" }, condition = "PK")
	abstract int updateScriptStatusInfoByKey(JaDateTime end_bk_tm,
			EXE_STATUS exe_status, EXE_RESULT exe_result, String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: �޸ĺ�ʱ
	 * @param time_used
	 * @param work_id
	 * @param script_type
	 * @param scirpt_bk_seq
	 * @return
	 */
	@SqlParam(updateSet = { "TIME_USED" }, condition = "PK")
	abstract int updateScriptTimeByKey(int time_used, String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: ͨ�������źͽű�����
	 * @param work_id
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND SCRIPT_TYPE =:script_type")
	abstract List<BuildScriptInfo> getScriptByIdAndType(String work_id,
			SCRIPT_TYPE script_type);

	/**
	 * Description: ͨ�������Ż�ýű���Ϣ
	 * @param work_id
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id")
	abstract List<BuildScriptInfo> getScriptByWorkId(String work_id);
	/** 
	 * Description: 
	 * @param work_id
	 * @param script_type
	 * @return 
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND SCRIPT_TYPE =:script_type")
	abstract int countBuildScript(String work_id, SCRIPT_TYPE script_type);

}