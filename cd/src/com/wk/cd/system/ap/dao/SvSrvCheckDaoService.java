/**
 * Title: SvSrvCheckDaoService.java
 * File Description: ���񸴺˶����
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.lang.Inject;

/**
 * Class description:���񸴺˶����
 * @author AutoGen
 */
public class SvSrvCheckDaoService {
	@Inject
	private SvSrvCheckDao dao;
	@Inject
	private SvSrvCheckDaoService daosrv;
	@Inject
	private DpPublicService dpsrv;
	
	/**
	 * ����������ѯһ����¼
	 * @param SvSrvCheckInfo info
	 * @return SvSrvCheckInfo
	 */
	public SvSrvCheckInfo getInfoByKey(SvSrvCheckInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param SvSrvCheckInfo info
	 * @return SvSrvCheckInfo
	 */
	public SvSrvCheckInfo getInfoByKeyForUpdate(SvSrvCheckInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param SvSrvCheckInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvCheckInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<SvSrvCheckInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvCheckInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ���ݷ������Ʋ�ѯ���˱������¼
	 * @param String srv_name
	 * @return List<SvSrvCheckInfo>
	 */
	public List<SvSrvCheckInfo> getListInfoByName(String srv_name) {
		List<SvSrvCheckInfo> srv_chk_lst = dao.getListInfoByName(srv_name);
		if (Assert.isEmpty(srv_chk_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvCheckInfo.TABLECN).addScene("FIELD", srv_name);
		}
		return srv_chk_lst;
	}

	/**
	 * ���ݲ��ű���ͷ�������ɾ��һ����񸴺���Ϣ
	 * @param dept_id ���ű���
	 * @param srv_name ��������
	 * @return int ɾ������
	 */
	public int deleteInfo(String dept_id, String srv_name) {
		return dao.deleteInfo(dept_id, srv_name);
	}
	
	/**
	 * ���ݷ�������ɾ��һ����񸴺���Ϣ
	 * @param srv_name ��������
	 * @return int ɾ������
	 */
	public int deleteSvSrvCheck(String srv_name) {
		return dao.deleteSvSrvCheck(srv_name);
	}

	/**
	 * ���ݲ��ű���ͷ������Ʋ�ѯ��¼����
	 * @param dept_str ���ű���
	 * @param srv_name ��������
	 * @return int ��¼����
	 */
	public int countInfos(String dept_str, String srv_name) {
		return dao.countInfos(dept_str, srv_name);
	}
	
	/**
	 * ���ݷ������Ʋ�ѯ��¼����
	 * @param srv_name ��������
	 * @return int ��¼����
	 */
	public int countSvSrvCheck(String srv_name) {
		return dao.countSvSrvCheck(srv_name);
	}

	/**
	 * ���ݲ��Ž�ɫ�����ѯ��ɫ���˷���
	 * @param check_dprl_code_list ���˲��Ž�ɫ�б�
	 * @return �����б�
	 */
	public List<SvSrvCheckInfo> querySrvCheckByDprlCode(
			List<String> check_dprl_code_list) {
		String check_dprl_code_str = "";
		int list_size = 0;
		if (!Assert.isEmpty(check_dprl_code_list)) {
			list_size = check_dprl_code_list.size();
			if (list_size > 0) {
				for (int i = 0; i < list_size; i++) {
					check_dprl_code_str += check_dprl_code_list.get(i) + "','";
				}
				check_dprl_code_str = "('"
						+ check_dprl_code_str.substring(0, check_dprl_code_str
								.length() - 2) + ")";
			}
		}
		return dao.querySrvCheckByDprlCode(check_dprl_code_str);
	}

	/**
	 * Description: ���ݻ�������ͷ������Ʋ�ѯ�����˲��Ž�ɫ������Ϣ
	 * @param dept_id ���ű���
	 * @param srv_name ��������
	 * @return
	 */
	public List<ChkDprlCodeBean> queryCheckBySrvName (String dept_id, String srv_name){
		List<ChkDprlCodeBean> check_list =  dao.queryCheckDprlBySrvName(dept_id, srv_name);
		if(Assert.isEmpty(check_list)){
			DpDeptInfo dept_info = dpsrv.getInfoByKey(dept_id);
			if(dept_info.getDept_level() >= 1 && !Assert.isEmpty(dept_info.getSuper_dept_id())){
				check_list =  daosrv.queryCheckBySrvName(dept_info.getSuper_dept_id(), srv_name);
				if(!Assert.isEmpty(check_list)){
					for(ChkDprlCodeBean c : check_list){
						c.setSuper_flag(SUPER_FLAG.YES);
					}
				}
			}
		}
		return check_list;
	}
	
	/**
	 * ����ָ���ķ������ƺʹ���ָ��������ŵ���С�ĸ������
	 * @param srv_name ��������
	 * @param check_seq �������
	 * @return ��С�������
	 */
	public int queryMinCheckSeq(String dept_id, String srv_name, int check_seq){
		return dao.queryMinCheckSeq(dept_id, srv_name, check_seq);
	}
}
