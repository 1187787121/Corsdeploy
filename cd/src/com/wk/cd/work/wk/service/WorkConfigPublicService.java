/**
 * Title: WkWorkConfigPublicService.java
 * File Description:�������������صĹ������� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.work.wk.bean.DtSourceBean;
import com.wk.cd.work.wk.bean.RsResCodeBean;
import com.wk.cd.work.wk.bean.SvServiceBean;
import com.wk.cd.work.wk.bean.UpdateWkWorkBean;
import com.wk.cd.work.wk.dao.WkWorkDaoService;
import com.wk.cd.work.wk.dao.WkWorkRsDaoService;
import com.wk.cd.work.wk.dao.WkWorkSocDaoService;
import com.wk.cd.work.wk.dao.WkWorkSrvDaoService;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.info.WkWorkRsInfo;
import com.wk.cd.work.wk.info.WkWorkSocInfo;
import com.wk.cd.work.wk.info.WkWorkSrvInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:�������������صĹ�������
 * @author tlw
 */
public class WorkConfigPublicService {
	@Inject
	private WkWorkDaoService wwDaos;
	@Inject
	private WkWorkRsDaoService wwRsDaos;
	@Inject
	private WkWorkSocDaoService wwSocDaos;
	@Inject
	private WkWorkSrvDaoService wwSrvDaos;
	@Inject
	private DtCheckSocExistService dtSrv;
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private RsPublicService rsSrv;

	/**
	 * ����һ�������������
	 * @param info ����������Ϣ
	 * @return ��������
	 */
	public int insertWorkDaoInfo(WkWorkInfo info) {
		return wwDaos.insertInfo(info);
	}

	/**
	 * ������Դ���ñ�����һ���¼
	 * @param infos ����������Դ��Ϣ
	 * @return ��������
	 */
	public int insertWorkRsDaoInfo(List<WkWorkRsInfo> infos) {
		return wwRsDaos.insertListInfo(infos);
	}

	/**
	 * ��������Դ���ñ�����һ���¼
	 * @param infos ��������Դ��Ϣ
	 * @return ��������
	 */
	public int insertWorkSocDaoInfo(List<WkWorkSocInfo> infos) {
		return wwSocDaos.insertListInfo(infos);
	}

	/**
	 * ����������ñ�����һ���¼
	 * @param infos ����������Ϣ
	 * @return ��������
	 */
	public int insertWorkSrvDaoInfo(List<WkWorkSrvInfo> infos) {
		return wwSrvDaos.insertListInfo(infos);
	}

	/**
	 * ����������뾫ȷ��ѯһ����������Ϣ
	 * @param work_code �������
	 * @return ��������Ϣ
	 */
	public WkWorkInfo queryInfoByWorkCode(String work_code,
			List<Integer> role_type_list) {
		return wwDaos.getInfoByWorkCode(work_code, role_type_list);
	}

	/**
	 * ����������뾫ȷ��ѯ��Ӧ����Դ��Ϣ
	 * @param work_code �������
	 * @return ��Դ��Ϣ
	 */
	public List<WkWorkRsInfo> getWorkRsList(String work_code) {
		return wwRsDaos.getWorkRsList(work_code);
	}

	/**
	 * ����������뾫ȷ��ѯ��Ӧ������Դ��Ϣ
	 * @param work_code �������
	 * @return ��Դ��Ϣ
	 */
	public List<WkWorkSocInfo> getWorkSocList(String work_code) {
		return wwSocDaos.getWorkSocList(work_code);
	}

	/**
	 * ����������뾫ȷ��ѯ��Ӧ�ķ�����Ϣ
	 * @param work_code �������
	 * @return ��Դ��Ϣ
	 */
	public List<WkWorkSrvInfo> getWorkSrvList(String work_code) {
		return wwSrvDaos.getWorkSrvList(work_code);
	}

	/**
	 * ����keyword�Ĵ�ֵ��ȡ���ϴ����������ҳ��Ϣ
	 * @param keyword �ؼ���
	 * @param start_recd ��ʼ��¼��
	 * @param limit_recd ��ѯ��¼��
	 * @return
	 */
	public List<WkWorkInfo> pagePbmWork(FUN_TYPE work_fun_type, String keyword,
			int start_recd, int limit_recd) {
		return wwDaos.pagePbmWork(work_fun_type, keyword, start_recd,
				limit_recd);
	}

	/**
	 * ��ҳ��ѯ��������Ϣ
	 * @param work_type_list ���������б�
	 * @param start_recd ��ҳ��ѯ��¼��ʼλ��
	 * @param limit_recd ��ҳ��ѯ��ʾ��¼��
	 * @return ��ҳ��ѯ��Ϣ
	 */
	public List<WkWorkInfo> pageAllWorkByWorkType(
			List<FUN_TYPE> work_type_list, List<Integer> role_type_list,
			int start_recd, int limit_recd) {
		return wwDaos.pageAllWorkByWorkType(work_type_list, role_type_list,
				start_recd, limit_recd);
	}

	/**
	 * ��ҳ��ѯ��������Ϣ����
	 * @param work_type_list ���������б�
	 * @return ��ҳ��ѯ��Ϣ
	 */
	public int countAllWorkByWorkType(List<FUN_TYPE> work_type_list,
			List<Integer> role_type_list) {
		return wwDaos.countAllWorkByWorkType(work_type_list, role_type_list);
	}

	/**
	 * �����������
	 * @param work_code �������
	 * @param update_work_bean ��Ҫ���µ�������Ϣ
	 * @return ��������
	 */
	public int updateWorkByWrokCode(String work_code,
			UpdateWkWorkBean update_work_bean) {
		return wwDaos.updateWorkByWrokCode(work_code, update_work_bean);
	}

	/**
	 * ����������Դ���ñ�
	 * @param work_code �������
	 * @param List<RsResCodeBean> ��Ҫ����������Դ������Ϣ
	 * @return ��������
	 */
	public int updateWorkRsByWrokCode(String work_code,
			List<RsResCodeBean> rs_list) {
		List<WkWorkRsInfo> infos = new ArrayList<WkWorkRsInfo>();
		if (!Assert.isEmpty(rs_list)) {
			for (RsResCodeBean rs : rs_list) {
				WkWorkRsInfo info = new WkWorkRsInfo();
				info.setWork_code(work_code);
				String rs_code = rs.getRs_code();
				rsSrv.checkRsExist(rs_code); // �����Դ�Ƿ����
				info.setRs_code(rs_code);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwRsDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * ������������Դ���ñ�
	 * @param work_code �������
	 * @param List<DtSourceBean> ��Ҫ��������Դ���ñ���Ϣ
	 * @return ��������
	 */
	public int updateWorkSocByWrokCode(String work_code,
			List<DtSourceBean> soc_list) {
		List<WkWorkSocInfo> infos = new ArrayList<WkWorkSocInfo>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceBean dt : soc_list) {
				WkWorkSocInfo info = new WkWorkSocInfo();
				info.setWork_code(work_code);
				String soc_name = dt.getSoc_name();
				dtSrv.checkSocExist(soc_name); // �������Դ������
				info.setSoc_name(soc_name);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwSocDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * ��������������ñ�
	 * @param work_code �������
	 * @param List<SvServiceBean> ��Ҫ�������������Ϣ
	 * @return ��������
	 */
	public int updateWorkSrvByWrokCode(String work_code,
			List<SvServiceBean> srv_list) {
		List<WkWorkSrvInfo> infos = new ArrayList<WkWorkSrvInfo>();
		if (!Assert.isEmpty(srv_list)) {
			for (SvServiceBean srv : srv_list) {
				WkWorkSrvInfo info = new WkWorkSrvInfo();
				info.setWork_code(work_code);
				String srv_name = srv.getSrv_name();
				ssSrv.checkServiceExist(srv_name); // �������Ƿ����
				info.setSrv_name(srv_name);
				info.setBackup_fld("");
				infos.add(info);
			}
		}
		return wwSrvDaos.updateWorkByWrokCode(work_code, infos);
	}

	/**
	 * �����������ɾ�����������Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	public int deleteWorkByWorkCode(String work_code, JaDate del_date,
			JaTime del_time, String user_id) {
		return wwDaos.deleteWorkByWorkCode(work_code, del_date, del_time,
				user_id);
	}

	/**
	 * �����������ɾ��������Դ���ñ���Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	public int deleteWorkRsByWorkCode(String work_code) {
		return wwRsDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * �����������ɾ����������Դ���ñ���Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	public int deleteWorkSocByWorkCode(String work_code) {
		return wwSocDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * �����������ɾ������������ñ���Ϣ
	 * @param work_code �������
	 * @return ɾ������
	 */
	public int deleteWorkSrvByWorkCode(String work_code) {
		return wwSrvDaos.deleteWorkByWorkCode(work_code);
	}

	/**
	 * Description: ����Ƿ����work_code��Ϣ�����������ס������ڼ�¼���쳣��
	 * @param work_code �������
	 */
	public void checkExist(String work_code) {
		if (wwDaos.countByWorkcode(work_code) < 1) {
			throw new RecordNotFoundException().addScene("Table", "WK_WORK")
					.addScene("FIELD", work_code);
		}
	}

	/**
	 * Description: ����Ƿ����work_code��Ϣ���������ס��Ѵ��ڼ�¼���쳣��
	 * @param work_code �������
	 */
	public void checkNotExist(String work_code) {
		if (wwDaos.countByWorkcode(work_code) > 0) {
			throw new RecordAlreadyExistException()
					.addScene("Table", "WK_WORK").addScene("FIELD", work_code);
		}
	}
}
