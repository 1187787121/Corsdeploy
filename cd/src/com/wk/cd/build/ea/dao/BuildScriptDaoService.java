/**
 * Title: BuildScriptDaoService.java
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
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:�����ű���Ϣ��
 * @author AutoGen
 */
public class BuildScriptDaoService {
	@Inject private BuildScriptDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param BuildScriptInfo info
	 * @return BuildScriptInfo
	 */
	public BuildScriptInfo getInfoByKey(BuildScriptInfo info) {
		return dao.get(info);
	}
	
	/**
	 * ����������ѯһ����¼
	 * @param BuildScriptInfo info
	 * @return BuildScriptInfo
	 */
	public BuildScriptInfo getScriptByKey(String work_id,long script_bk_seq,SCRIPT_TYPE script_type) {
		return dao.getScriptByKey(work_id,script_type,script_bk_seq);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param BuildScriptInfo info
	 * @return BuildScriptInfo
	 */
	public BuildScriptInfo getInfoByKeyForUpdate(BuildScriptInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param BuildScriptInfo info
	 * @return int
	 */
	public int insertInfo(BuildScriptInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<BuildScriptInfo>
	 * @return int
	 */
	public int insertListInfo(List<BuildScriptInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: �޸Ĺ�����ʱ���״̬
	 * @param end_bk_tm
	 * @param exe_status
	 * @param exe_result
	 * @param work_id
	 * @param script_bk_seq
	 * @param script_type 
	 */
	public int updateScriptStatusInfoByKey(JaDateTime end_bk_tm,
			EXE_STATUS exe_status, EXE_RESULT exe_result, String work_id,
			long scirpt_bk_seq, SCRIPT_TYPE script_type) {
		return dao.updateScriptStatusInfoByKey(end_bk_tm,exe_status,exe_result,work_id,script_type,scirpt_bk_seq);
		
	}

	/** 
	 * Description: �޸ĺ�ʱ
	 * @param time_used
	 * @param work_id
	 * @param script_bk_seq
	 * @param script_type 
	 */
	public int updateScriptTimeByKey(int time_used, String work_id,
			long scirpt_bk_seq, SCRIPT_TYPE script_type) {
		return dao.updateScriptTimeByKey(time_used,work_id,script_type,scirpt_bk_seq);
		
	}

	/** 
	 * Description: ͨ�������źͽű�����
	 * @param work_id
	 * @param script_type
	 * @return 
	 */
	public List<BuildScriptInfo> getScriptByIdAndType(String work_id,
			SCRIPT_TYPE script_type) {
		return dao.getScriptByIdAndType(work_id,script_type);
	}
	
	/** 
	 * Description: ͨ�������Ż�ýű���Ϣ
	 * @param work_id
	 * @param script_type
	 * @return 
	 */
	public List<BuildScriptInfo> getScriptByWorkId(String work_id) {
		return dao.getScriptByWorkId(work_id);
	}
	
	

	/** 
	 * Description: 
	 * @param work_id
	 * @param script_type
	 * @return 
	 */
	public int countBuildScript(String work_id, SCRIPT_TYPE script_type) {
		return dao.countBuildScript(work_id,script_type);
	}

}