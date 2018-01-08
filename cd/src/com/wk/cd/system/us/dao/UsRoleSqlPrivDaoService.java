/**
 * Title: UsRoleSqlPrivDaoService.java
 * File Description: ���Ž�ɫSQL����Ȩ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:���Ž�ɫSQL����Ȩ�ޱ�
 * @author AutoGen
 */
public class UsRoleSqlPrivDaoService {
	@Inject
	private UsRoleSqlPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	/**
	 * ����������ѯһ����¼
	 * @param UsRoleSqlPrivInfo info
	 * @return UsRoleSqlPrivInfo
	 */
	public UsRoleSqlPrivInfo getInfoByKey(UsRoleSqlPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsRoleSqlPrivInfo info
	 * @return UsRoleSqlPrivInfo
	 */
	public UsRoleSqlPrivInfo getInfoByKeyForUpdate(UsRoleSqlPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsRoleSqlPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleSqlPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsRoleSqlPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleSqlPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯSQL����Ȩ���б�
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprl(String dprl_code) {
		return dao.querySqlByDprl(dprl_code);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ���������ѯSQL����Ȩ���б�
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprlArr(String dprl_code) {
		return dao.querySqlByDprlArr(dprl_code);
	}

	/**
	 * Description: ��鲿�Ž�ɫ��Ӧ��SQL��¼�Ƿ��Ѿ�����
	 * @param info
	 */
	public void checkDprlSqlNotExist(UsRoleSqlPrivInfo info) {
		if (dao.countSqlByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name()) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleSqlPrivInfo.TABLECN).addScene(
					"FIELD",
					"���Ž�ɫ" + usDeptRoleDaoService.getDeptByDprl(info.getDprl_code()).getBk_expl() + "[" + info.getDprl_code() +"]" + "����Դ" + info.getSoc_name()
							+ "����" + info.getTbl_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleSqlPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleSqlPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleSqlPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleSqlPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	public int countSqlByDprlCode(String dprl_code, String soc_name,
			String tbl_name) {
		return dao.countSqlByDprlCode(dprl_code, soc_name, tbl_name);
	}

	/**
	 * Description: ��鲿�Ž�ɫ��Ӧ��SQL��¼�Ƿ��Ѿ�����
	 * @param info
	 */
	private void checkDprlSqlExist(UsRoleSqlPrivInfo info) {
		if (dao.countSqlByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name()) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleSqlPrivInfo.TABLECN).addScene(
					"FIELD",
					"���Ž�ɫ" + usDeptRoleDaoService.getDeptByDprl(info.getDprl_code()).getBk_expl() + "[" + info.getDprl_code() +"]" + "����Դ" + info.getSoc_name()
							+ "����" + info.getTbl_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleSqlPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleSqlPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleSqlPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleSqlPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	/**
	 * Description: Ϊ���Ž�ɫɾ��sql����Ȩ��
	 * @param sql_list
	 */
	public void delSqlByDprlCode(List<UsRoleSqlPrivInfo> sql_list) {
		for (UsRoleSqlPrivInfo info : sql_list) {
			checkDprlSqlExist(info);
			dao.delete(info);
		}
	}

	/**
	 * Description: ���Ĳ��Ž�ɫSQL����Ȩ��
	 * @param info ����ӿ�
	 */
	public void updateSqlPriv(UsRoleSqlPrivInfo info) {
		checkDprlSqlExist(info);
		dao.updateSqlPriv(info.getIns_priv_flag().getValue(), info
				.getDel_priv_flag().getValue(), info.getUpd_priv_flag()
				.getValue(), info.getSel_priv_flag().getValue(), info
				.getDprl_code(), info.getSoc_name(), info.getTbl_name());
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ������SQLȨ��
	 * @param dprl_code
	 */
	public void deleteAllSqlByDprl(String dprl_code) {
		dao.deleteAllSqlByDprl(dprl_code);
	}

	/** 
	 * Description: ͨ�����Ž�ɫ��ѯsqlȨ����Ϣ
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprl(String dprl_code) {
		return dao.queryIteratorSqlByDprl(dprl_code);
	}
	/** 
	 * Description: ͨ�����Ž�ɫ�����ѯsqlȨ����Ϣ
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprlArray(String dprl_code) {
		return dao.queryIteratorSqlByDprlArray(dprl_code);
	}
	
	/**
	 * 
	 * Description: ��������Դ����ɾ�����Ž�ɫ����������ԴSQL��Ȩ����Ϣ
	 * @param soc_name
	 * @return
	 */
	public int deleteRoleSQLBySocName(String soc_name){
		return dao.deleteRoleSQLBySocName(soc_name);
	}

	/** 
	 * Description: ���ݲ��Ž�ɫ�����ѯsqlȨ��
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleSqlPrivInfo> querySqlPrivByDprl(String dprl_code) {
		List<UsRoleSqlPrivInfo> sql_list = new ArrayList<UsRoleSqlPrivInfo>();
		DBIterator<UsRoleSqlPrivInfo> sql_iterator = dao.querySqlPrivByDprl(dprl_code);
		try {
			while (sql_iterator.hasNext()) {
				sql_list.add(sql_iterator.next());
			}
		} finally {
			sql_iterator.close();
		}
		return sql_list;
	}

}