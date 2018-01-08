/**
 * Title: UsUserRoleDaoService.java
 * File Description: 用户角色关联表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class description:用户角色关联表
 * @author AutoGen
 */
public class UsUserRoleDaoService {
	@Inject
	private UsUserRoleDao dao;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject 
	private UsUserDaoService usUserDaoService;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserRoleInfo info
	 * @return UsUserRoleInfo
	 */
	public UsUserRoleInfo getInfoByKey(UsUserRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserRoleInfo info
	 * @return UsUserRoleInfo
	 */
	public UsUserRoleInfo getInfoByKeyForUpdate(UsUserRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsUserRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserRoleInfo> infos) {
		return dao.insert(infos);
	}

	public List<String> queryDprlByUserId(String user_id) {
		return dao.queryDprlByUserId(user_id);
	}
	
	/** 
	 * Description: 查找用户下的所属部门角色描述
	 * @param user_id
	 * @param bl_dept_id
	 * @return 
	 */
	public List<String> queryBlDrExplByUserId(String user_id, String bl_dept_id) {
		return dao.queryBlDrExplByUserId(user_id,bl_dept_id);
	}

	/**
	 * Description: 检查该记录项是否不存在
	 * 
	 * @param dprl_code
	 * @param user_id
	 */
	public void checkDprlAndUserNotExist(UsUserRoleInfo info) {
		if (dao.countByDprlAndUserID(info.getDprl_code(), info.getUser_id()) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsUserRoleInfo.TABLECN).addScene(
					"FIELD",
					"部门角色"
							+ usDeptRoleDaoService.getDeptByDprl(
									info.getDprl_code()).getBk_expl()
							+ "的用户"
							+ usUserDaoService.getInfoByKey(info.getUser_id())
									.getUser_cn_name());
		}
	}

	/**
	 * Description: 检查该记录项是否存在,以及记录状态是否正常
	 * 
	 * @param dprl_code
	 * @param user_id
	 */
	public void checkDprlAndUserExist(UsUserRoleInfo info) {
		if (dao.countByDprlAndUserID(info.getDprl_code(), info.getUser_id()) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsUserRoleInfo.TABLECN).addScene(
					"FIELD",
					"部门角色"
							+ usDeptRoleDaoService.getDeptByDprl(
									info.getDprl_code()).getBk_expl() + "的用户"
							+ info.getUser_id());
		}
	}

	/**
	 * Description: 物理删除用户角色关联表中的一项
	 * @param user_id
	 * @param dprl_code
	 */
	public void deleteUserDprl(String user_id, String dprl_code) {
		UsUserRoleInfo info = new UsUserRoleInfo();
		info.setUser_id(user_id);
		info.setDprl_code(dprl_code);
		checkDprlAndUserExist(info);
		dao.delete(info);
	}

	/**
	 * 删除用户的所有部门角色 Description:
	 * @param user_id
	 */
	public void deleteUserDprlByUserId(String user_id) {
		dao.deleteUserDprlByUserId(user_id);
	}

	/**
	 * Description: 根据部门角色编码查询用户信息的总条数
	 * 
	 * @param dprl_code
	 * @return
	 */
	public int countUserByDprlCode(String dprl_code) {
		return dao.countUserByDprlCode(dprl_code);
	}
	
	/**
	 * 根据部门角色代码查询所属用户列表
	 * @param dprl_code
	 * @return
	 */
	public List<String> listUserByDprlCode(String dprl_code){
		return dao.listUserByDprlCode(dprl_code);
	}

	/** 
	 * 检查部门角色下是否存在用户
	 * @param dprl_code
	 * @param auth_user_id
	 * @return 
	 */
	public boolean checkDprlHasUser(String dprl_code, String auth_user_id) {
		if (dao.countByDprlAndUserID(dprl_code, auth_user_id) > 0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Description: 判断非本用户的部门角色和用户权重在用户部门角色表中是否已经存在，
	 * 如果存在则报错，保证部门角色和用户权限的表中的唯一性
	 * @param user_id 用户名
	 * @param dprl_code 部门角色
	 * @param user_bk_weight 用户权重
	 */
	public void checkDprlWeghtExsit(String user_id, String dprl_code, int user_bk_weight) {
		if (dao.countByDprlAndWeght(user_id, dprl_code, user_bk_weight) > 0) {
			UsDeptRoleInfo info = new UsDeptRoleInfo();
			info.setDprl_code(dprl_code);
			info = usDeptRoleDaoService.getInfoByKey(info);
			throw new RecordAlreadyExistException().addScene("TABLE",
					"用户部门角色表us_user_role").addScene(
					"FIELD",
					"部门角色[" + info.getBk_expl() + "]和用户权重[" + user_bk_weight
							+ "]");
		}
	}
	
	/**
	 * Description: 根据部门角色编码查询权重值最小的用户信息
	 * @param dprl_code 部门角色编码
	 * @param user_ids 不能做复核授权的用户信息
	 * @return
	 */
	public UsUserRoleInfo queryUserRoleByDprlAndMinWeght(String dprl_code, String user_ids){
		return dao.queryUserRoleByDprlAndMinWeght(dprl_code, dprl_code, user_ids);
	}
	
	/**
	 * Description: 根据部门角色编码查询部门角色信息
	 * @param dprl_code 部门角色编码
	 * @param user_ids 用户名
	 * @return
	 */
	public List<UsUserRoleInfo> queryUserRoleByDprlCode(String dprl_code, String user_ids){
		return dao.queryUserRoleByDprlCode(dprl_code, user_ids);
	}
	/**
	 * Description: 根据部门角色编码查询所有已使用的用户权重
	 * @param derl_code 部门角色编码
	 * @return 
	 */
	public List<UsUserRoleInfo> queryUserRoleByDprlCode(String dprl_code){
		return dao.queryUserRoleByDprlCode(dprl_code); 
	}
}