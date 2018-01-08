/**
 * Title: DpDeptSqlPrivDaoService.java
 * File Description: ����SQL����Ȩ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:����SQL����Ȩ�ޱ�
 * @author AutoGen
 */
public class DpDeptSqlPrivDaoService {
	@Inject
	private DpDeptSqlPrivDao dao;

	/**
	 * Description: ����������ѯһ����¼
	 * @param DpDeptSqlPrivInfo info
	 * @return DpDeptSqlPrivInfo
	 */
	public DpDeptSqlPrivInfo getInfoByKey(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: ����������ѯһ����¼,�����쳣
	 * @param info
	 * @return
	 */
	public DpDeptSqlPrivInfo getInfoByKeyWithoutException(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.get(info);
		return info_dao;
	}

	/**
	 * 
	 * Description: ���ݲ��ű����ѯ������¼
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptSqlPrivInfo> queryInfosById(String dept_id) {
		List<DpDeptSqlPrivInfo> infos_dao = dao.queryDeptSqlPrivInfos(dept_id);
		return infos_dao;
	}

	/**
	 * Description: ����������ѯһ����¼���Լ�¼����
	 * @param DpDeptSqlPrivInfo info
	 * @return DpDeptSqlPrivInfo
	 */
	public DpDeptSqlPrivInfo getInfoByKeyForUpdate(DpDeptSqlPrivInfo info) {
		DpDeptSqlPrivInfo info_dao = dao.getForUpdate(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptSqlPrivInfo.TABLECN).addScene("FIELD",
					info.getDept_id());
		}
		return info_dao;
	}

	/**
	 * Description: ����һ����¼
	 * @param DpDeptSqlPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptSqlPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: ���������¼
	 * @param List<DpDeptSqlPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptSqlPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: �޸Ĳ���SQLȨ�޼�¼
	 * @param dpdeptsqlprivInfo
	 */
	public void updateInfo(DpDeptSqlPrivInfo dpdeptsqlprivInfo) {
		dao.update(dpdeptsqlprivInfo);
	}

	/**
	 * Description: ��ɾ��id�����б�
	 * @param id
	 */
	public void deleteDeptSqlPrivInfo(String id) {
		// ����ID���Ƿ������ݼ�¼
		if (dao.queryDeptSqlPrivInfos(id).isEmpty()) {
			return;
		}
		dao.deleteInfo(id);

	}
	/**
	 * 
	 * Description: ��������Դ����ɾ������SQL����Ȩ������
	 * @param soc_name
	 * @return
	 */
	public int deleteDeptSqlBySocName(String soc_name) {
		return dao.deleteDeptSqlBySocName(soc_name);

	}
	
	/**
	 * 
	 * Description: ���ݲ��ű����ѯsqlȨ��
	 * @param dept_id
	 * @return
	 */
	public List<DpDeptSqlPrivInfo> querySqlPrivByDeptId(String dept_id) {
		List<DpDeptSqlPrivInfo> sql_list = new ArrayList<DpDeptSqlPrivInfo>();
		DBIterator<DpDeptSqlPrivInfo> sql_iterator = dao.queryDeptSqlPrivByDeptId(dept_id);
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