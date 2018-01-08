/**
 * Title: UsRoleRsPrivDaoService.java
 * File Description: ���Ž�ɫ��ԴȨ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.info.UsRoleRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:���Ž�ɫ��ԴȨ�ޱ�
 * @author AutoGen
 */
public class UsRoleRsPrivDaoService {
	@Inject
	private UsRoleRsPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private RsResDaoService rsResDaoService;

	/**
	 * ����������ѯһ����¼
	 * @param UsRoleRsPrivInfo info
	 * @return UsRoleRsPrivInfo
	 */
	public UsRoleRsPrivInfo getInfoByKey(UsRoleRsPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsRoleRsPrivInfo info
	 * @return UsRoleRsPrivInfo
	 */
	public UsRoleRsPrivInfo getInfoByKeyForUpdate(UsRoleRsPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsRoleRsPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleRsPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsRoleRsPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleRsPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯӵ�е���Դ�б�
	 * @param dprl_code
	 * @return
	 */
	public List<String> queryRsByDprl(String dprl_code) {
		return dao.queryRsByDprl(dprl_code);
	}

	/**
	 * Description: ��鲿�Ž�ɫ��Ӧ����Դ��¼�Ƿ��Ѿ�����
	 * @param dprl_code
	 * @param rs_code
	 */
	public void checkDprlRsNotExist(String dprl_code, String rs_code) {
		if (dao.countRsByDprlCode(dprl_code, rs_code) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleRsPrivInfo.TABLECN).addScene("FIELD", usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]")
					.addScene("FIELD", rsResDaoService.getInfoByCode(rs_code).getRs_cn_name() + "[" + rs_code + "]");
		}
	}

	public int countRsByDprlCode(String dprl_code, String rs_code) {
		return dao.countRsByDprlCode(dprl_code, rs_code);
	}

	/**
	 * Description: ��鲿�Ž�ɫ��Ӧ����Դ��¼�Ƿ��Ѿ�����
	 * @param dprl_code
	 * @param rs_code
	 */
	public void checkDprlRsExist(String dprl_code, String rs_code) {
		if (dao.countRsByDprlCode(dprl_code, rs_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleRsPrivInfo.TABLECN).addScene("FIELD",
					"���Ž�ɫ" + usDeptRoleDaoService.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code +"]" + "����Դ" + rsResDaoService.getInfoByCode(rs_code).getRs_cn_name() + "[" + rs_code + "]");
		}
	}

	/**
	 * Description: Ϊ���Ž�ɫɾ����ԴȨ��
	 * @param dprl_code
	 * @param rs_list
	 */
	public void delRsByDprlCode(String dprl_code, List<String> rs_list) {
		UsRoleRsPrivInfo info = new UsRoleRsPrivInfo();
		for (String rs_code : rs_list) {
			checkDprlRsExist(dprl_code, rs_code);
			info.setDprl_code(dprl_code);
			info.setRs_code(rs_code);
			dao.delete(info);
		}
	}

	/**
	 * Description: ��ѯ���Ž�ɫ�б���ӵ�е���ԴȨ��
	 * @param dprl_code_arr
	 * @return
	 */
	public List<RsResInfo> queryRsByDprlArr(String dprl_code_arr) {
		DBIterator<RsResInfo> rs_it = dao.iteratorRsByDprlArr(dprl_code_arr);
		List<RsResInfo> rs_list = new ArrayList<RsResInfo>();
		try {
			while (rs_it.hasNext()) {
				rs_list.add(rs_it.next());
			}
		} finally {
			rs_it.close();
		}
		return rs_list;
	}

	/**
	 * Description: ���ݲ��Ž�ɫ����ɾ�����Ž�ɫ��������ԴȨ��
	 * @param dprl_code
	 */
	public void deleteAllRsByDprl(String dprl_code) {
		dao.deleteAllRsByDprl(dprl_code);
	}

	/** 
	 * Description: ���ݲ��Ž�ɫ�����ѯ��Դ��Ϣ
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprl(String dprl_code) {
		return dao.iteratorRsByDprl(dprl_code);
	}
	
	/** 
	 * Description: ���ݲ��Ž�ɫ���������ѯ��Դ��Ϣ
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprlArray(String dprl_code) {
		return dao.iteratorRsByDprlArray(dprl_code);
	}
	
	/**
	 * Description: ��ѯ���Ž�ɫ�б���ӵ�е���ԴȨ��
	 * @param dprl_list
	 * @return
	 */
	public List<String> queryRsPrivByDprls(List<String> dprl_list) {
		List<String> rs_list = new ArrayList<String>();
		Map<String, String> rs_map=new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			DBIterator<String> rs_iterator = dao.iteratorRsPrivByDprl(dprl_code);
			try {
				while (rs_iterator.hasNext()) {
					String rs_code=rs_iterator.next();
					if(!rs_map.containsKey(rs_code)){
						rs_map.put(rs_code, rs_code);
					}
				}
			} finally {
				rs_iterator.close();
			}
		}
		rs_list.addAll(rs_map.values());
		return rs_list;
	}
}