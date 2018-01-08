/**
 * Title: PgInteStepDaoService.java
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
import com.wk.lang.Inject;

/**
 * Class description:���ɷ��������
 * @author AutoGen
 */
public class PgInteStepDaoService {
	@Inject private PgInteStepDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKey(PgInteStepInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKeyForUpdate(PgInteStepInfo info) {
		return dao.getForUpdate(info);
	}
	
	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param PgInteStepInfo info
	 * @return PgInteStepInfo
	 */
	public PgInteStepInfo getInfoByKeyForUpdate2(String prog_id,int step_id) {
		PgInteStepInfo info = new PgInteStepInfo();
		info.setProg_id(prog_id);
		info.setStep_id(step_id);
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param PgInteStepInfo info
	 * @return int
	 */
	public int insertInfo(PgInteStepInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<PgInteStepInfo>
	 * @return int
	 */
	public int insertListInfo(List<PgInteStepInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ɾ�����ɷ��������
	 * @param prog_id 
	 */
	public int deleteProgStep(String prog_id) {
		
		return dao.deleteProgStepByProgId(prog_id);
	}

	/** 
	 * Description: ɾ�����ɷ��������ͨ��Info
	 * @param stepInfo 
	 */
	public int deleteInfo(PgInteStepInfo stepInfo) {
		return dao.delete(stepInfo);
	}

	/** 
	 * Description: ���·���������Ϣ
	 * @param inte_for_update 
	 */
	public int updatePgInteStep(PgInteStepInfo inte_for_update) {
		return dao.update(inte_for_update);
	}

	/** 
	 * Description: ��ü��ɷ��������б�
	 * @param prog_id
	 * @return 
	 */
	public List<PgInteStepInfo> getInteListByProgId(String prog_id) {
		return dao.getInteListByProgId(prog_id);
	}

	/** 
	 * Description: ͳ���м�������
	 * @param prog_id 
	 * @param step_id
	 * @return 
	 */
	public int countPgStepByKey(String prog_id) {
		return dao.countPgStepByProgId(prog_id);
	}

	/** 
	 * Description: 
	 * @param prog_id
	 * @param i
	 * @return 
	 */
	public PgInteStepInfo getInfoByKey2(String prog_id, int step_id) {
		PgInteStepInfo info = new PgInteStepInfo();
		info.setProg_id(prog_id);
		info.setStep_id(step_id);
		return dao.get(info);
	}
	
	/**
	 * Description: ���ݲ������ͻ�ȡ��¼
	 * @param prog_id
	 * @param step_type
	 * @return
	 */
	public List<PgInteStepInfo> getInfoByType(String prog_id, STEP_TYPE step_type){
		return dao.getInfoByType(prog_id, step_type);
	}

}