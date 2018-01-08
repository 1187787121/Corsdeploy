/**
 * Title: UsUserColPrivDaoService.java
 * File Description: �û�SQL�ֶβ���Ȩ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:�û�SQL�ֶβ���Ȩ�ޱ�
 * @author AutoGen
 */
public class UsUserColPrivDaoService {
	@Inject
	private UsUserColPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	/**
	 * ����������ѯһ����¼
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public UsUserColPrivInfo getInfoByKey(UsUserColPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public UsUserColPrivInfo getInfoByKeyForUpdate(UsUserColPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * �޸�INfo
	 * @param UsUserColPrivInfo info
	 * @return UsUserColPrivInfo
	 */
	public int updateInfo(UsUserColPrivInfo info) {
		return dao.update(info);
	}
	
	/**
	 * ����һ����¼
	 * @param UsUserColPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserColPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsUserColPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserColPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ����û���COL����Ȩ���Ƿ񲻴���
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkUserColPrivNotExist(String dprl_code, String soc_name,
			String tbl_name, String col_name) {
		if (dao.countByUserColPriv(dprl_code, soc_name, tbl_name, col_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserColPrivInfo.TABLECN).addScene(
					"FIELD",
					"���Ž�ɫ" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "����Դ" + soc_name + "����" + tbl_name
							+ "�ֶ���" + col_name);
		}
	}

	/**
	 * Description: ����û���COL����Ȩ���Ƿ����
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkUserColPrivExist(String user_id, String soc_name,
			String tbl_name, String col_name) {
		UsUserColPrivInfo info = new UsUserColPrivInfo();
		info.setUser_id(user_id);
		info.setSoc_name(soc_name);
		info.setTbl_name(tbl_name);
		info.setCol_name(col_name);
		if (dao.countByUserColPriv(user_id, soc_name, tbl_name, col_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserColPrivInfo.TABLECN).addScene(
					"FIELD",
					"�û���" + user_id + "����Դ" + soc_name + "����" + tbl_name
							+ "�ֶ���" + col_name);
		}
	}

	/**
	 * Description: ɾ���û���COLȨ�޼�¼
	 * @param user_id
	 * @param sql_list
	 */
	public void deleteUserColPriv(List<UsUserPrivBean> col_list) {
		UsUserColPrivInfo info = new UsUserColPrivInfo();
		for (UsUserPrivBean col_info : col_list) {
			checkUserColPrivExist(col_info.getUser_id(),
					col_info.getSoc_name(), col_info.getTbl_name(), col_info
							.getCol_name());
			info.setUser_id(col_info.getUser_id());
			info.setSoc_name(col_info.getSoc_name());
			info.setTbl_name(col_info.getTbl_name());
			info.setCol_name(col_info.getCol_name());
			dao.delete(info);
			// dao.updateUserColPrivState(RCD_STATE.ABNORMAL.getValue(),
			// col_info.getDprl_code(), col_info.getSoc_name(),
			// col_info.getTbl_name(), col_info.getCol_name());
		}
	}

	/**
	 * Description: ����û�COLȨ�ޱ����Ƿ���ڸ�Ȩ��
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	public int countByUserCol(String user_id, String soc_name, String tbl_name,
			String col_name) {
		int count1 = dao.countByUserCol(user_id, soc_name, tbl_name, col_name);
		return count1;
	}

	/**
	 * Description: ��ѯ�û�����ʱCOL����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> queryUserTempColPriv(String user_id) {
		List<UsUserColPrivInfo> priv_list=new ArrayList<UsUserColPrivInfo>();
		DBIterator<UsUserColPrivInfo> iterator=dao.queryUserTempColPriv(user_id);
		try {
			while (iterator.hasNext()) {
				priv_list.add(iterator.next());
				
			}
		} finally {
			iterator.close();
		}
		
		return priv_list;
	}

	/**
	 * Description: �����û�����ѯCOL����Ȩ���б�
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> queryColPrivByUserId(String user_id) {
		return dao.queryColPrivByUserId(user_id);
	}

	/**
	 * Description: ɾ���û�������COLȨ��
	 * @param user_id
	 */
	public void deleteAllColByUserId(String user_id) {
		dao.deleteAllColByUserId(user_id);
	}

	/**
	 * Description: �û����COLȨ��
	 * @param rs_list
	 */
	public void addUserCol(List<UsUserColPrivInfo> col_list) {
		dao.insert(col_list);
	}

	/**
	 * Description: ɾ���û�����Դ��Ϣ
	 * @param soc_name
	 */
	public int deleteUserCOLBySocName(String soc_name) {
		return dao.deleteUserCOLBySocName(soc_name);
	}

	/** 
	 * Description: ɾ���Ѿ����ڵ��û�col��ʱȨ��
	 * @param user_id
	 * @param dt_datetime 
	 */
	public int deleteColTempPriv(String user_id, JaDateTime dt_datetime) {
		return dao.deleteColTempPriv(user_id,dt_datetime);
	}

	/** 
	 * Description:  ɾ���û���ʱȨ��
	 * @param user_id 
	 */
	public void deleteAllTempPriv(String user_id) {
		dao.deleteAllTempPriv(user_id);
	}
	
	/** 
	 * Description:  ɾ���û���ʱȨ��
	 * @param user_id 
	 */
	public void deleteTempPriv(String user_id,String soc_name,String tbl_name,String col_name) {
		dao.deleteTempPriv(user_id,soc_name,tbl_name,col_name);
	}

	/** 
	 * Description: ����������ѯһ����¼
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param col_name
	 * @param priv_type
	 * @return 
	 */
	public UsUserColPrivInfo getInfoByKey(String user_id, String soc_name,
			String table_name, String col_name, PRIV_TYPE priv_type) {
		return dao.getInfoByKey(user_id, soc_name,
			 table_name, col_name, priv_type);
	}
}