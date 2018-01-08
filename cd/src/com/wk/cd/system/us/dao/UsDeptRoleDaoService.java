/**
 * Title: UsDeptRoleDaoService.java
 * File Description: ���Ž�ɫ������
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
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.exc.DeptHasNotRoleException;
import com.wk.cd.system.us.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:���Ž�ɫ������
 * @author AutoGen
 */
public class UsDeptRoleDaoService {
	@Inject
	private UsDeptRoleDao dao;
	@Inject 
	private DpDeptDaoService dpDaoService;
	@Inject 
	private UsRoleDaoService usRoleDaoService;

	/**
	 * ����������ѯһ����¼
	 * @param UsDeptRoleInfo info
	 * @return UsDeptRoleInfo
	 */
	public UsDeptRoleInfo getInfoByKey(UsDeptRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsDeptRoleInfo info
	 * @return UsDeptRoleInfo
	 */
	public UsDeptRoleInfo getInfoByKeyForUpdate(UsDeptRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsDeptRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsDeptRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsDeptRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsDeptRoleInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ɾ�����Ž�ɫ
	 * @param dprl_code
	 */
	public void deleteDprl(String dprl_code) {
		dao.delete(dprl_code);
	}

	/**
	 * Description: ���ݽ�ɫ�����ҳ��ѯ�ý�ɫ�����Ĳ����б�
	 * @param role_code �����ɫ����
	 * @return ���Ž�ɫ�������ʵ���б�
	 */
	public List<String> pageDeptByRole(String role_code, int start_rcd,
			int limited_rcd) {
		List<String> dept_list = new ArrayList<String>();
		List<UsDeptRoleInfo> info_list = dao.pageDeptByRole(role_code,
				start_rcd, limited_rcd);
		for (UsDeptRoleInfo info : info_list) {
			dept_list.add(info.getDept_id());
		}
		return dept_list;
	}

	/**
	 * Description: ���ݽ�ɫ�����ҳ��ѯ���Ĳ���������
	 * @param role_code
	 * @return
	 */
	public int countDeptByRole(String role_code) {
		return dao.countDeptByRole(role_code);
	}

	/**
	 * Description: ���ݲ��ű����ѯ�ò�����ӵ�еĽ�ɫ�б�
	 * @param dept_id ���ű���
	 * @return ���Ž�ɫ�������ʵ���б�
	 */
	public List<String> queryRoleByDept(String dept_id) {
		List<String> dprl_list = new ArrayList<String>();
		List<UsDeptRoleInfo> info_list = dao.queryRoleByDept(dept_id);
		for (UsDeptRoleInfo info : info_list) {
			dprl_list.add(info.getDprl_code());
		}
		return dprl_list;
	}

	/**
	 * Description: ���ݲ��Ų�ѯ��ɫ��Ϣ��������
	 * @param dept_id
	 * @return
	 */
	public int countRoleByDept(String dept_id) {
		return dao.countRoleByDept(dept_id);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ�����ѯ�ò��Ž�ɫ�����Ĳ���
	 * @param dprl_code
	 * @return
	 */
	public UsDeptRoleInfo getDeptByDprl(String dprl_code) {
		UsDeptRoleInfo info = new UsDeptRoleInfo();
		info = dao.getDeptByDprl(dprl_code);
		if (!Assert.isEmpty(info)) {
			return info;
		} else {
			throw new RecordNotFoundException().addScene("TABLE",
					UsDeptRoleInfo.TABLECN).addScene("FIELD", info.getBk_expl() + "[" + dprl_code + "]");
		}
	}

	/**
	 * Description: ��鲿�Ž�ɫ�����Ƿ���ڣ����ڱ���
	 * @param dprl_code
	 */
	public void checkExistDprlCode(String dprl_code) {
		if (dao.countByDprl(dprl_code) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", dao.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code + "]");
		}
	}

	/**
	 * Description: ��ѯ���Ž�ɫ�����Ƿ���ڣ������ڱ���
	 * @param dprl_code
	 * @return
	 */
	public void checkDprlExist(String dprl_code) {
		if (dao.countByDprl(dprl_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsDeptRoleInfo.TABLECN).addScene("FIELD", "[" + dprl_code + "]");
		}
	}

	public List<UsDeptRoleInfo> queryDprl() {
		DBIterator<UsDeptRoleInfo> db = dao.queryDprl();
		List<UsDeptRoleInfo> lst = new ArrayList<UsDeptRoleInfo>();
		try{
			while(db.hasNext()) {
				UsDeptRoleInfo info = db.next();
				lst.add(info);
			}
		}finally{
			db.close();
		}
		return lst;
	}

	/**
	 * Description: ��ѯ���Ž�ɫ����
	 * @return
	 */
	public int countAllDprl() {
		return dao.countAllDprl();
	}

	/**
	 * Description: ���²��Ž�ɫ˵����Ϣ
	 * @param dprl_code
	 */
	public void updateBkExplByDprl(String bk_expl, String dprl_code) {
		dao.updateBkExplByDprl(bk_expl, dprl_code);
	}
	
	/**
	 * Description: ��ѯ���в��Ž�ɫ��Ӧ�Ľ�ɫ����
	 * @param dprl_str ���Ž�ɫ�б�
	 * @return ��ɫ����
	 */
	public List<String> queryRoleCode(String dprl_str){
		return dao.queryRoleCode(dprl_str);
	}
	
	/**
	 * Description: ���ݲ��ű���ͽ�ɫ�����ѯ���Ž�ɫ����
	 * @param dept_id ���ű���
	 * @param role_code ��ɫ����
	 * @return ���Ž�ɫ����
	 */
	public String queryDprlByDeptAndRole(String dept_id, String role_code){
		List<String> dprl_list = dao.queryDprlByDeptAndRole(dept_id, role_code);
		DpDeptInfo deptInfo = new DpDeptInfo();
		deptInfo.setDept_id(dept_id);
		String dept_name = dpDaoService.getInfoByKey(deptInfo).getDept_cn_name();
		UsRoleInfo usRoleInfo = new UsRoleInfo();
		usRoleInfo.setRole_code(role_code);
		String role_cn_name = usRoleDaoService.getInfoByKey(usRoleInfo).getRole_cn_name();
		if(Assert.isEmpty(dprl_list)){
			throw new DeptHasNotRoleException().addScene("PARM1", dept_name + "[" + dept_id + "]")
				.addScene("PARM2",role_cn_name + "[" + role_code + "]");
		}
		if(dprl_list.size() > 1){
			throw new DataErrorException().
				addScene("INPUT", "����"+ dept_name + "[" + dept_id + "]�ͽ�ɫ" + role_cn_name + "[" + role_code + "]");
		}
		return dprl_list.get(0);
	}

	/** 
	 * Description: ���ݲ��Ž�ɫ�����ѯ��ɫ����
	 * @param dprl_code
	 * @return 
	 */
	public Integer getRoleTypeByDprl(String dprl_code) {
		return dao.getRoleTypeByDprl(dprl_code);
	}

	/**
	 * Description: ���ݲ��ű�Ų�ѯ���Ž�ɫ
	 * @param dept_id
	 * @return
	 */
	public List<UsDeptRoleInfo> queryRoleInfoByDept(String dept_id){
		return dao.queryRoleByDept(dept_id);
	}
	
}