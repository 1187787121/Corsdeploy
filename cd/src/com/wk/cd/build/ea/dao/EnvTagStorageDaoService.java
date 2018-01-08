/**
 * Title: EnvTagStorageDaoService.java
 * File Description: Ŀ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:Ŀ������
 * @author AutoGen
 */
public class EnvTagStorageDaoService {
	@Inject private EnvTagStorageDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param EnvTagStorageInfo info
	 * @return EnvTagStorageInfo
	 */
	public EnvTagStorageInfo getInfoByKey(EnvTagStorageInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param EnvTagStorageInfo info
	 * @return EnvTagStorageInfo
	 */
	public EnvTagStorageInfo getInfoByKeyForUpdate(EnvTagStorageInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param EnvTagStorageInfo info
	 * @return int
	 */
	public int insertInfo(EnvTagStorageInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<EnvTagStorageInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvTagStorageInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ���ݻ�������ѯ�������������
	 * @param env_name 
	 */
	public List<EnvTagStorageInfo> getIdByEnv(String env_name) {
		return dao.queryIdByEnv(env_name);
	}

	/** 
	 * Description: ���ݻ�������ѯ����
	 * @param env_name
	 * @return 
	 */
	public int countByEnvName(String env_name) {
		return dao.countByEnvName(env_name);
	}

	/** 
	 * Description: ���ݻ�������ѯ�����б�
	 * @param env_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> pageInfoByEnvName(String env_name, int start_recd, int limit_recd) {
		return dao.pageInfoByEnvName(env_name, start_recd, limit_recd);
	}

	/** 
	 * Description: ��������Ų�ѯ����
	 * @return 
	 */
	public int countByStorageId(String storage_id) {
		return dao.countStorageIdByKey(storage_id);
	}
 
	/** 
	 * Description: ����������ѯһ����¼
	 * @param storage_id
	 * @return 
	 */
	public EnvTagStorageInfo getInfoByKey2(String storage_id) {
		EnvTagStorageInfo info = new EnvTagStorageInfo();
		info.setStorage_id(storage_id);
		return dao.get(info);
	}

	/** 
	 * Description: ���������Ϣ
	 * @param tagst_update 
	 */
	public int updateStroageInfo(EnvTagStorageInfo tagst_update) {
	    return dao.update(tagst_update);
	}

	/** 
	 * Description: ͨ��������޸İ汾���
	 * @param tar_ver_num
	 * @param storage_id 
	 */
	public int updateStroageInfoByKey(String tar_ver_num, String storage_id) {
		return dao.updateStroageInfoByKey(tar_ver_num,storage_id);
		
	}

	/** 
	 * Description: �޸����״̬�ͽ���ʱ��
	 * @param now
	 * @param storaged
	 * @param storage_id 
	 */
	public int updateStrStatusInfoByKey(JaDateTime end_bk_tm,STORAGE_STATUS storage_state, String storage_id) {
		return dao.updateStrStatusInfoByKey(end_bk_tm,storage_state,storage_id);
		
	}

	/** 
	 * Description: �޸������
	 * @param fail
	 * @param storage_id 
	 */
	public int updateStroageResultByKey(STORAGE_RESULT storage_result, String storage_id) {
		return dao.updateStroageResultByKey(storage_result,storage_id);
	}

	/** 
	 * Description: �޸������ʱ
	 * @param time_used
	 * @param storage_id 
	 */
	public int updateStroageTimeByKey(int time_used, String storage_id) {
		return dao.updateStroageTimeByKey(time_used,storage_id);
	}

	/** 
	 * Description: �޸����״̬
	 * @param storaging
	 * @param storage_id 
	 */
	public int updateStatusInfoByKey(STORAGE_STATUS storage_status,
			String storage_id) {
	     return dao.updateStatusInfoByKey(storage_status,storage_id);
		
	}

	/** 
	 * Description: ���ݻ�������ѯ����б�
	 * @param env_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> queryStorageInfoByEnvName(String env_name) {
		return dao.queryStorageInfoByEnvName(env_name);
	}

	/** 
	 * Description: ������Ŀ����ѯ����б�
	 * @param project_name
	 * @return 
	 */
	public List<EnvTagStorageInfo> queryStorageInfoByProName(String project_name) {
		return dao.queryStorageInfoByProName(project_name);
	}

	/** 
	 * Description: ��û�����������е���������
	 * @param env_name
	 * @param storaging
	 * @return 
	 */
	public int countStorageByEnvAndStatus(String env_name, STORAGE_STATUS storage_status) {
		return dao.countStorageByEnvAndStatus(env_name,storage_status);
	}

}