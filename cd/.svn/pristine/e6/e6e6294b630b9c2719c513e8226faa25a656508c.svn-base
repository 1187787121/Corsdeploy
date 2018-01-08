/**
 * Title: UsRoleDaoService.java
 * File Description: 角色信表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.system.us.info.UsRoleInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:角色信表
 * @author AutoGen
 */
public class UsRoleDaoService {
	@Inject
	private UsRoleDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleInfo info
	 * @return UsRoleInfo
	 */
	public UsRoleInfo getInfoByKey(UsRoleInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleInfo info
	 * @return UsRoleInfo
	 */
	public UsRoleInfo getInfoByKeyForUpdate(UsRoleInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 检测角色信息表中是否已经存在该角色中文名称
	 * @param role_cn_name 角色中文名称
	 */
	public void checkNotExistRoleCnName(String role_cn_name) {
		if (dao.countByRoleCnName(role_cn_name) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_cn_name);
		}
	}

	/**
	 * Description: 检查要删除的角色的角色编码是否存在以及该角色的状态是否是正常
	 * @param role_code
	 */
	public void checkExistRoleCode(String role_code) {
		UsRoleInfo info = new UsRoleInfo();
		info.setRole_code(role_code);
		if (dao.countByRoleCode(role_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_code);
		} else if (getInfoByKey(info).getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", getInfoByKey(info).getRole_cn_name()+ "[" + role_code + "]");
		}

	}

	/**
	 * 从角色信息表中查询出所有角色，以列表的形式返回
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleInfo> pageRole(int start_rcd, int limited_rcd) {
		return dao.pageRole(start_rcd, limited_rcd);
	}

	public int countRole() {
		return dao.countRole();
	}

	/**
	 * 根据主键修改角色信息，例如：用户中文名称、用户类型、用户说明。
	 * @param role_cn_name
	 * @param role_type
	 * @param role_bk_desc
	 * @param role_code
	 */
	public void updateRoleByKey(String role_cn_name, int role_type,
			String role_bk_desc, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String role_code) {
		dao.updateRoleByKey(role_cn_name, role_type, role_bk_desc,
				modify_bk_date, modify_bk_time, modify_user_id, role_code);
	}

	/**
	 * 根据主键删除角色信息（更改角色的记录状态：正常-->删除）
	 * @param rcd_state
	 * @param role_code
	 */
	public void deleteRoleByKey(int rcd_state, JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, String role_code) {
		dao.updateRoleStateByKey(rcd_state, del_bk_date, del_bk_time,
				del_user_id, role_code);
	}

	/**
	 * Description: 模糊查询角色名称、说明模糊查询角色信息
	 * @param role_cn_name
	 * @param role_bk_desc
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleInfo> pageRoleByRoleCnName(String role_cn_name,
			int start_rcd, int limited_rcd) {
		List<UsRoleInfo> role_list = new ArrayList<UsRoleInfo>();
		String role_bk_desc = role_cn_name;
		role_list = dao.pageRoleByRoleCnName(role_cn_name, role_bk_desc,
				start_rcd, limited_rcd);
		return role_list;
	}

	/**
	 * Description: 根据角色名称模糊查询角色信息条数
	 * @param role_cn_name
	 * @return
	 */
	public int countRoleByRoleCnName(String role_cn_name) {
		return dao.countRoleByRoleCnName(role_cn_name, role_cn_name);
	}

	/**
	 * Description: 查询角色的详细信息
	 * @param role_code
	 * @return
	 */
	public UsRoleInfo queryRole(String role_code) {
		return dao.queryRole(role_code);
	}

	/**
	 * Description: 查询角色实体列表
	 * @return
	 */
	public List<UsRoleInfo> queryAllRole() {
		return dao.queryAllRole();
	}

	/**
	 * Description: 查询角色是否存在
	 * @return
	 */
	public void checkRoleExist(String role_code){
		if(dao.queryRole(role_code) == null){
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleInfo.TABLECN).addScene("FIELD", role_code);
		}
	}
}