/**
 * Title: WkDealStateDaoService.java
 * File Description: ������״̬��
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.QUERY_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.work.wk.bean.HistoryWorkBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:������״̬��
 * 
 * @author AutoGen
 */
public class WkDealStateDaoService {
	@Inject
	private WkDealStateDao dao;
	@Inject
	private WorkQuery wkqry;
	private static final String TD = WkDealStateInfo.TABLECN;

	/**
	 * ����������ѯһ����¼
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return ������״̬����Ϣ
	 */
	public WkDealStateInfo getInfoByKey(WkDealStateInfo info) {
		String pend_work_seq = info.getPend_work_seq();
		WkDealStateInfo o = dao.get(pend_work_seq);
		if (Assert.isEmpty(o)) {
			throw new RecordNotFoundException().addScene("TABLE", TD).addScene(
					"FIELD", pend_work_seq);
		}
		return o;
	}

	/**
	 * ���ݴ�������ˮ�Ų�ѯһ����¼
	 * 
	 * @param pend_wk_seq
	 *            ��������ˮ��
	 * @return ������״̬����Ϣ
	 */
	public WkDealStateInfo getInfoByKey(String pend_wk_seq) {
		return dao.get(pend_wk_seq);
	}

	/**
	 * ���ݴ�������ˮ�Ų�ѯ״̬�����ļ�¼���Ҳ�����¼����
	 * 
	 * @param pend_work_seq
	 *            ��������ˮ��
	 * @return
	 */
	public WkDealStateInfo queryInfoByWorkSeq(String pend_work_seq) {
		WkDealStateInfo info = dao.queryInfoByWorkSeq(pend_work_seq);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDealStateInfo.TABLECN).addScene("FIELD", pend_work_seq);
		}
		return info;
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return ������״̬����Ϣ
	 */
	public WkDealStateInfo getInfoByKeyForUpdate(String pend_work_seq) {
		WkDealStateInfo info = dao.getForUpdate(pend_work_seq);
		if (info == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDealStateInfo.TABLECN).addScene("FIELD", pend_work_seq);
		}
		return info;
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return ������״̬����Ϣ
	 */
	public WkDealStateInfo getInfoByKeyForUpdate(WkDealStateInfo info) {
		return dao.getForUpdate(info);
	}
	
	/**
	 * ����һ����¼
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return int
	 */
	public int insertInfo(WkDealStateInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * 
	 * @param List
	 *            <WkDealStateInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkDealStateInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ���ݴ������û���ѯ��������Ϣ
	 * 
	 * @param user_id
	 *            �������û�
	 * @param start_recd
	 *            ��ʼ��¼����
	 * @param limit_recd
	 *            ��ѯ����
	 * @return
	 */
	public List<WkDealStateInfo> pageUncheckWork(String user_id,
			int start_recd, int limit_recd) {
		Assert.assertNotEmpty(user_id, "�û�" + user_id);
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.RECHECK,
				start_recd, limit_recd);
	}

	/**
	 * ���ݴ������û���ѯ��������Ϣ����
	 * 
	 * @param user_id
	 *            �������û�
	 * @return
	 */
	public int countUncheckWork(String user_id) {
		Assert.assertNotEmpty(user_id, "�û�");
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.RECHECK);
	}

	/**
	 * ���ݴ���Ȩ�û���ѯ����Ȩ��Ϣ
	 * 
	 * @param user_id
	 *            ����Ȩ�û�
	 * @param start_recd
	 *            ��ʼ��¼����
	 * @param limit_recd
	 *            ��ѯ����
	 * @return
	 */
	public List<WkDealStateInfo> pageUnauthWork(String user_id, int start_recd,
			int limit_recd) {
		Assert.assertNotEmpty(user_id, "�û�" + user_id);
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.APPROVAL,
				start_recd, limit_recd);
	}

	/**
	 * ���ݴ���Ȩ�û���ѯ����Ȩ��Ϣ����
	 * 
	 * @param user_id
	 *            ����Ȩ�û�
	 * @return
	 */
	public int countUnauthWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.APPROVAL);
	}

	/**
	 * Description: �����û���Ϣ��ѯ��ִ������
	 * 
	 * @param user_id
	 *            �û�����
	 * @param start_recd
	 *            ��ʼ����
	 * @param limit_recd
	 *            ��ѯ����
	 * @return ������Ϣ
	 */
	public List<WkDealStateInfo> pageUnexecuteWork(String user_id,
			int start_recd, int limit_recd) {
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.EXECUTORY,
				start_recd, limit_recd);
	}

	/**
	 * Description: �����û���Ϣ��ѯ��ִ����������
	 * 
	 * @param user_id
	 *            �û�����
	 * @param start_recd
	 *            ��ʼ����
	 * @param limit_recd
	 *            ��ѯ����
	 * @return ������Ϣ
	 */
	public int countUnexecuteWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.EXECUTORY);
	}

	/**
	 * Description: �����û���Ϣ��ѯ���д���������(�������Լ��ύ)
	 * 
	 * @param user_id
	 *            �û�����
	 * @param start_recd
	 *            ��ʼ����
	 * @param limit_recd
	 *            ��ѯ����
	 * @return ������Ϣ
	 */
	public List<WkDealStateInfo> pageUnhandleWork(String user_id,
			int start_recd, int limit_recd) {
		return dao.pageUnhandleWork(user_id, null, start_recd, limit_recd);
	}
	
	/**
	 * Description: �����û���Ϣ��ѯ���д�������������(�������Լ��ύ)
	 * 
	 * @param user_id
	 *            �û�����
	 * @return ������Ϣ
	 */
	public int countUnhandleWork(String user_id) {
		return dao.countUnhandleNum(user_id, null);
	}

	/**
	 * Description: ��ҳ��ѯ����״̬
	 * 
	 * @param crt_user_id
	 *            �û�id
	 * @param query_type
	 *            ��ѯ����
	 * @param start_recd
	 *            ��ʼ��¼��
	 * @param limit_recd
	 *            �ܼ�¼��
	 * @return ����״̬�б�
	 */
	public List<WkDealStateInfo> pageInfoListByWorkStateAndUser(
			String crt_user_id, QUERY_TYPE query_type, int start_recd,
			int limit_recd) {
		// ��ѯ����Ϊ�ύ�����ˡ���Ȩ��ִ�С��ر�
		if (query_type == QUERY_TYPE.SUBMITWORK) {
			return dao.pageInfoListByWorkStateAndUser(crt_user_id, start_recd,
					limit_recd);
		} else if (query_type == QUERY_TYPE.CHECKWORK) {
			return wkqry.pageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.RECHECK, start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.AUTHWORK) {
			return wkqry.pageInfoListCheckAndAuth(crt_user_id, DEAL_TYPE.AUTH,
					start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.EXCWORK) {
			return dao.pageInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.COMPLETE, start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.CLOSEWORK) {
			return dao.pageInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.CLOSE, start_recd, limit_recd);
		} else {
			throw new DataErrorException().addScene("INPUT", "��������");
		}
	}

	/**
	 * Description: ��ҳ��ѯ����״̬�ܼ�¼
	 * 
	 * @param crt_user_id
	 *            �û�id
	 * @param query_type
	 *            ��ѯ����
	 * @return �ܼ�¼��
	 */
	public int countInfoListByWorkStateAndUser(String crt_user_id,
			QUERY_TYPE query_type) {
		// ��ѯ����Ϊ�ύ�����ˡ���Ȩ��ִ�С��ر�
		if (query_type == QUERY_TYPE.SUBMITWORK) {
			return dao.countpageInfoListByWorkStateAndUser(crt_user_id);
		} else if (query_type == QUERY_TYPE.CHECKWORK) {
			return wkqry.countpageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.RECHECK);
		} else if (query_type == QUERY_TYPE.AUTHWORK) {
			return wkqry.countpageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.AUTH);
		} else if (query_type == QUERY_TYPE.EXCWORK) {
			return dao.countInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.COMPLETE);
		} else if (query_type == QUERY_TYPE.CLOSEWORK) {
			return dao.countInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.CLOSE);
		} else {
			throw new DataErrorException().addScene("INPUT", "��������");
		}
	}
	
	/**
	 * ������������һ����ˮ
	 * 
	 * @param pend_work_seq
	 *            ��������������ˮ
	 * @param pend_bk_seq
	 *            ���������
	 * @param pend_user_id
	 *            �������û�
	 * @param workflow_state
	 *            ����״̬
	 * @return ��������
	 */
	public int updateWorkStateByWorkSeq(String pend_work_seq,
			int pend_deal_seq, String pend_user_id,String pend_user_cn_name,
			WORKFLOW_STATE workflow_state) {
		return dao.updateWorkStateByWorkSeq(pend_deal_seq, pend_user_id,pend_user_cn_name,
				workflow_state, pend_work_seq);
	}

	/**
	 * ���ݴ�����������ƺ�����״̬��ѯ��Ӧ�Ĵ�������ˮ��
	 * 
	 * @param pend_srv_name
	 *            �������������
	 * @param workflow_state
	 *            ����״̬
	 * @return ��ˮ���б�
	 */
	public List<String> queryPendWorkSeqByWorkState(String pend_srv_name,
			List<WORKFLOW_STATE> workflow_state_list) {
		String str = "";
		if (Assert.isEmpty(workflow_state_list)) {
			throw new DataErrorException().addScene("INPUT", "������Ϣ");
		}
		for (WORKFLOW_STATE s : workflow_state_list) {
			str = str + s + ",";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return dao.queryPendWorkSeqByWorkState(pend_srv_name, str);
	}

	/**
	 * Description: ��ѯ�����Ѿܾ�������
	 * @param user_id �û���
	 * @param start_recd ��ѯ��ʼ����
	 * @param limit_recd ��ѯ����
	 * @return
	 */
	public List<WkDealStateInfo> pageRefuseWork(String user_id, int start_recd,
			int limit_recd) {
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.CHECK_REFUSE,
				WORKFLOW_STATE.APP_REFUSE, start_recd, limit_recd);
	}

	/**
	 * Description: ��ѯ�����Ѿܾ�����������
	 * @param user_id �û���
	 * @return
	 */
	public int countRefuseWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.CHECK_REFUSE,
				WORKFLOW_STATE.APP_REFUSE);
	}
	
	/**
	 * Description: ��ѯ��������������б�
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineWorkList(String crt_user_id) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryMineWorkList(crt_user_id);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: ��ѯ�Ҵ����˻���Ȩ���������
	 * @param crt_user_id
	 * @return
	 */
	public int countMineExecutoryWork(String crt_user_id){
		return dao.countMineExecutoryWork(crt_user_id);
	}
	
	/**
	 * Description: ��ѯ�Ҵ����˻���Ȩ�������б�
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineExecutoryWork(String crt_user_id){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryMineExecutoryWork(crt_user_id);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: ��ѯ�Ҵ�����ĸ��˻���Ȩ�����б�
	 * @param crt_user_id
	 * @param query_type
	 * @return
	 */
	public List<WkDealStateInfo> queryUncheckOrUnauthWorkList(String crt_user_id,DEAL_TYPE deal_type){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryUncheckOrUnauthWorkList(crt_user_id,deal_type);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: ��ѯ�Ҵ����˻���Ȩ�������б�
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public List<WkDealStateInfo> queryExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: ��ѯ�Ҵ����˻���Ȩ���������
	 * @param crt_user_id
	 * @param workflow_state
	 * @return
	 */
	public int countExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		return dao.countExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
	}
	
	/**
	 * Description: ��ҳ��ѯ�ҵ���ʷ�����б�
	 * @return
	 */
	public List<HistoryWorkBean> pageMineHistoryWork(String crt_user_id, int start_recd , int limit_recd){
		return dao.pageMineHistoryWork(crt_user_id,start_recd,limit_recd);
	}
	
	/**
	 * Description: ��ѯ�ҵ���ʷ�������
	 * @param crt_user_id
	 * @return
	 */
	public int countMineHistoryWork(String crt_user_id){
		return dao.countMineHistoryWork(crt_user_id);
	}
	
	/**
	 * Description: ��ҳ��ѯ���˻���Ȩ����ʷ�����б�
	 * @param crt_user_id
	 * @param deal_type
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	public List<HistoryWorkBean> pageMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type , int start_recd , int limit_recd){
		return dao.pageMineUncheckOrUnauth(crt_user_id,deal_type,start_recd,limit_recd);
	}
	
	/**
	 * Description: ��ѯ���˻���Ȩ����ʷ�������
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public int countMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type){
		return dao.countMineUncheckOrUnauth(crt_user_id,deal_type);
	}

	/** 
	 * Description: ���ݴ���������ѯ������������
	 * @param pend_srv_name 
	 */
	public List<WkDealStateInfo> queryPendWorkListByPendSrv(String pend_srv_name) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryPendWorkListByPendSrv(pend_srv_name);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/** 
	 * Description: ���ݴ���������ѯ������������
	 * @param pend_srv_name 
	 */
	public List<WkDealStateInfo> queryWorkListByPendSrv(String pend_srv_name) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryWorkListByPendSrv(pend_srv_name);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
}
