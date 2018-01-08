/**
 * Title: PgReleStepDaoService.java
 * File Description: ���������׶α�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.PgReleStepInfo;
import com.wk.cd.enu.YN_FLAG;
import com.wk.lang.Inject;

/**
 * Class description:���������׶α�
 * @author AutoGen
 */
public class PgReleStepDaoService {
	@Inject private PgReleStepDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param PgReleStepInfo info
	 * @return PgReleStepInfo
	 */
	public PgReleStepInfo getInfoByKey(PgReleStepInfo info) {
		return dao.get(info);
	}
	
	public PgReleStepInfo getInfoByPrimary(int index,String prog_id,String template_name){
		PgReleStepInfo rele_step_info = new PgReleStepInfo();
		rele_step_info.setPhase_id(index);
		rele_step_info.setProg_id(prog_id);
		rele_step_info.setTemplate_name(template_name);
		return getInfoByKey(rele_step_info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param PgReleStepInfo info
	 * @return PgReleStepInfo
	 */
	public PgReleStepInfo getInfoByKeyForUpdate(PgReleStepInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param PgReleStepInfo info
	 * @return int
	 */
	public int insertInfo(PgReleStepInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<PgReleStepInfo>
	 * @return int
	 */
	public int insertListInfo(List<PgReleStepInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ɾ�����������׶α�
	 * @param prog_id 
	 */
	public int deleteProgReleStep(String prog_id) {
		return dao.deleteProgReleStepByProId(prog_id);
		
	}
	public int deleteProgRele(PgReleStepInfo info){
		return dao.delete(info);
	}


	/** 
	 * Description: ��ѯ���������׶α�ĸ���
	 * @param template_name
	 * @return 
	 */
	public int countByTemplateName(String template_name) {
		return dao.countByTemplateName(template_name);
	}
	
	public int countGenById (String prog_id,YN_FLAG gen_yn_flag){
		return dao.countGenById(prog_id, gen_yn_flag);
	}

	/** 
	 * Description: ͨ��ģ������ý׶α���Ϣ
	 * @param template_name
	 * @return 
	 */
	public List<PgReleStepInfo> getInfoByTemplateName(String template_name) {
		return dao.getInfoByTemplateName(template_name);
	}
	
	public List<PgReleStepInfo> getInfoByTpNameAndPgId(String template_name,String prog_id){
		return dao.getInfoByTpNameAndPgId(template_name, prog_id);
	}

	/** 
	 * Description: ��ȡ���������׶��б�
	 * @param prog_id
	 * @return 
	 */
	public List<PgReleStepInfo> getReleListByProgId(String prog_id) {
		return dao.getReleListByProgId(prog_id);
	}

}