/**
 * Title: InstanceExeDao.java
 * File Description: ʵ��ִ����Ϣ��
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-3
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.util.JaDateTime;
import com.wk.cd.build.ea.info.*;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;

/**
 * Class description:ʵ��ִ����Ϣ��
 * @author AutoGen
 */
abstract class InstanceExeDao extends EntityDao<InstanceExeInfo> {

	/**
	 * Description:�޸�ִ�п�ʼʱ���ִ��״̬
	 * @param start_bk_tm
	 * @param exe_status
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "START_BK_TM", "EXE_STATUS" }, condition = "PK")
	abstract int updateExeStarBkTmByKey(JaDateTime start_bk_tm,
			EXE_STATUS exe_status, String instance_id, int inst_bk_no);
	
	/**
	 * Description:�޸�ִ�н���ʱ���״̬
	 * @param end_bk_tm
	 * @param exe_status
	 * @param exe_result
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_STATUS", "EXE_RESULT" }, condition = "PK")
	abstract int updateExeEndBkTmByKey(JaDateTime end_bk_tm,
			EXE_STATUS exe_status, EXE_RESULT exe_result, String instance_id,
			int inst_bk_no);
	
	/**
	 * Description: ����ִ����Ϣ
	 * @param exec_text
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "EXEC_TEXT" }, condition = "PK")
	abstract int updateExeMsgByKey(String exec_text, String instance_id,
			int inst_bk_no);

	/**
	 * Description: ͨ��Key���һ����¼
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract InstanceExeInfo getExeInstByKey(String instance_id,
			int inst_bk_no);

	/** 
	 * Description: ��ù���ִ����Ϣ
	 * @param inst_id
	 * @return 
	 */
	@SqlParam(condition = "INSTANCE_ID =:instance_id",orderBy="INST_BK_NO ASC")
	abstract List<InstanceExeInfo> getExeInstMsgByInstId(String instance_id);

	/** 
	 * Description: ����ִ�п�ʼʱ��
	 * @param start_bk_tm
	 * @param instance_id
	 * @param inst_bk_no
	 * @return 
	 */
	@SqlParam(updateSet = {"START_BK_TM"}, condition = "PK")
	abstract int updateExecuteStartTime(JaDateTime start_bk_tm, String instance_id, int inst_bk_no);

	/** 
	 * Description: ����ִ��״̬
	 * @param exe_status
	 * @param instance_id
	 * @param inst_bk_no
	 * @return 
	 */
	@SqlParam(updateSet = {"EXE_STATUS"}, condition = "PK")
	abstract int updateExecuteStatus(EXE_STATUS exe_status, String instance_id, int inst_bk_no);

	/** 
	 * Description: ����ִ�н���ʱ��
	 * @param exec_text
	 * @param exe_result
	 * @param end_bk_tm
	 * @param time_used
	 * @param instance_id
	 * @param inst_bk_no
	 * @return 
	 */
	@SqlParam(updateSet = {"EXEC_TEXT", "EXE_RESULT", "END_BK_TM", "TIME_USED"}, condition = "PK")
	abstract int updateExecuteEnd(String exec_text, EXE_RESULT exe_result, JaDateTime end_bk_tm, int time_used, String instance_id, int inst_bk_no);
 
	/**
	 * Description:���º�ʱ
	 * @param time_used
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "TIME_USED" }, condition = "PK")
	abstract int updateCostTmBykey(int time_used, String instance_id,int inst_bk_no);

	/** 
	 * Description: ��ȡģ�����׶κ�
	 * @param instance_id
	 * @return 
	 */
	@SqlParam(sql = "select MAX(TPL_BK_NO) from INSTANCE_EXE where instance_id =:instance_id")
	abstract int getMaxTplPhase(String instance_id);

	/** 
	 * Description: ����ģ��׶κŲ�ѯʵ���׶κ��б�
	 * @param tpl_bk_no 
	 */
	@SqlParam(querySet = {"inst_bk_no"}, condition = "INSTANCE_ID =:INSTANCE_ID and tpl_bk_no =:tpl_bk_no", orderBy = "inst_bk_no asc")
	abstract List<Integer> queryInstPhaseByTplPhase(String instance_id, int tpl_bk_no);
	
	@SqlParam(condition="INSTANCE_ID =:instance_id")
	abstract int deleteByInstanceId(String instance_id);

	/** 
	 * Description: ����ʵ��IDɾ����������ִ�б�
	 * @param instance_id
	 * @return 
	 */
	@SqlParam(condition = "INSTANCE_ID =:instance_id")
	abstract int deleteInfoByInst(String instance_id);

}