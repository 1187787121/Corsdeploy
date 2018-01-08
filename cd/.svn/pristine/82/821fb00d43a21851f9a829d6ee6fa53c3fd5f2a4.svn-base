/**
 * Title: UsDeptRoleDaoService.java
 * File Description: 部门角色关联表
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
 * Class description:部门角色关联表
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
	 * 根据主键查询一条记录
	 * @param UsDeptRoleInfo info
	 * @return UsDeptRoleInfo
	 */
	public UsDeptRoleInfo getInfoByKey(UsDeptRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsDeptRoleInfo info
	 * @return UsDeptRoleInfo
	 */
	public UsDeptRoleInfo getInfoByKeyForUpdate(UsDeptRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsDeptRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsDeptRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsDeptRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsDeptRoleInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 删除部门角色
	 * @param dprl_code
	 */
	public void deleteDprl(String dprl_code) {
		dao.delete(dprl_code);
	}

	/**
	 * Description: 根据角色编码分页查询该角色所属的部门列表
	 * @param role_code 输入角色编码
	 * @return 部门角色关联表的实体列表
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
	 * Description: 根据角色编码分页查询到的部门总条数
	 * @param role_code
	 * @return
	 */
	public int countDeptByRole(String role_code) {
		return dao.countDeptByRole(role_code);
	}

	/**
	 * Description: 根据部门编码查询该部门所拥有的角色列表
	 * @param dept_id 部门编码
	 * @return 部门角色关联表的实体列表
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
	 * Description: 根据部门查询角色信息的总条数
	 * @param dept_id
	 * @return
	 */
	public int countRoleByDept(String dept_id) {
		return dao.countRoleByDept(dept_id);
	}

	/**
	 * Description: 根据部门角色编码查询该部门角色所属的部门
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
	 * Description: 检查部门角色编码是否存在，存在报错
	 * @param dprl_code
	 */
	public void checkExistDprlCode(String dprl_code) {
		if (dao.countByDprl(dprl_code) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", dao.getDeptByDprl(dprl_code).getBk_expl() + "[" + dprl_code + "]");
		}
	}

	/**
	 * Description: 查询部门角色编码是否存在，不存在报错
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
	 * Description: 查询部门角色总数
	 * @return
	 */
	public int countAllDprl() {
		return dao.countAllDprl();
	}

	/**
	 * Description: 更新部门角色说明信息
	 * @param dprl_code
	 */
	public void updateBkExplByDprl(String bk_expl, String dprl_code) {
		dao.updateBkExplByDprl(bk_expl, dprl_code);
	}
	
	/**
	 * Description: 查询所有部门角色对应的角色编码
	 * @param dprl_str 部门角色列表
	 * @return 角色编码
	 */
	public List<String> queryRoleCode(String dprl_str){
		return dao.queryRoleCode(dprl_str);
	}
	
	/**
	 * Description: 根据部门编码和角色编码查询部门角色编码
	 * @param dept_id 部门编码
	 * @param role_code 角色编码
	 * @return 部门角色编码
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
				addScene("INPUT", "部门"+ dept_name + "[" + dept_id + "]和角色" + role_cn_name + "[" + role_code + "]");
		}
		return dprl_list.get(0);
	}

	/** 
	 * Description: 根据部门角色编码查询角色类型
	 * @param dprl_code
	 * @return 
	 */
	public Integer getRoleTypeByDprl(String dprl_code) {
		return dao.getRoleTypeByDprl(dprl_code);
	}

	/**
	 * Description: 根据部门编号查询部门角色
	 * @param dept_id
	 * @return
	 */
	public List<UsDeptRoleInfo> queryRoleInfoByDept(String dept_id){
		return dao.queryRoleByDept(dept_id);
	}
	
}