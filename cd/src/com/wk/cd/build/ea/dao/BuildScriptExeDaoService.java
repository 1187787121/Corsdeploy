/**
 * Title: BuildScriptExeDaoService.java
 * File Description: �����ű�ִ�б�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.BuildScriptExeInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:�����ű�ִ�б�
 * @author AutoGen
 */
public class BuildScriptExeDaoService {
	@Inject private BuildScriptExeDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param BuildScriptExeInfo info
	 * @return BuildScriptExeInfo
	 */
	public BuildScriptExeInfo getInfoByKey(BuildScriptExeInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param BuildScriptExeInfo info
	 * @return BuildScriptExeInfo
	 */
	public BuildScriptExeInfo getInfoByKeyForUpdate(BuildScriptExeInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param BuildScriptExeInfo info
	 * @return int
	 */
	public int insertInfo(BuildScriptExeInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<BuildScriptExeInfo>
	 * @return int
	 */
	public int insertListInfo(List<BuildScriptExeInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ���¿�ʼʱ���ִ��״̬
	 * @param now
	 * @param running
	 * @param inst_id
	 * @param j 
	 */
	public int updateExeStarBkTmByKey(JaDateTime start_bk_tm, EXE_STATUS exe_status,String instance_id, int exe_bk_no) {
		return dao.updateExeStarBkTmByKey(start_bk_tm,exe_status,instance_id,exe_bk_no);
	}
	
	/** 
	 * Description: ���½���ʱ���ִ��״̬
	 * @param now
	 * @param running
	 * @param inst_id
	 * @param j 
	 */
	public int updateExeEndBkTmByKey(JaDateTime end_bk_tm, EXE_STATUS exe_status,EXE_RESULT exe_result,String instance_id, int exe_bk_no) {
		return dao.updateExeEndBkTmByKey(end_bk_tm,exe_status,exe_result,instance_id,exe_bk_no);
	}

	/** 
	 * Description: ����ִ����Ϣ
	 * @param exec_text
	 * @param instance_id
	 * @param exe_bk_no
	 */
	public int updateExeMsgByKey(String exec_text, String instance_id, int exe_bk_no) {
		return dao.updateExeMsgByKey(exec_text,instance_id,exe_bk_no);
		
	}

	/** 
	 * Description: ͨ���������һ����¼
	 * @param instance_id
	 * @param exe_bk_no
	 * @return 
	 */
	public BuildScriptExeInfo getExeScriptByKey(String instance_id, int exe_bk_no) {
		return dao.getExeScriptByKey(instance_id,exe_bk_no);
	}

	/** 
	 * Description: ���º�ʱ
	 * @param time_used
	 * @param instance_id
	 * @param exe_bk_no
	 */
	public int updateCostTmBykey(int time_used, String instance_id, int exe_bk_no) {
		return dao.updateCostTmBykey(time_used,instance_id,exe_bk_no);
	}

	/** 
	 * Description: ��ù���ִ����Ϣ
	 * @param inst_id 
	 */
	public List<BuildScriptExeInfo> getExeScriptMsgByInstId(String inst_id) {
		return dao.getExeScriptMsgByInstId(inst_id);
		
	}

}