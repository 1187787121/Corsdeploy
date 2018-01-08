/**
 * Title: DpDeptDaoService.java
 * File Description: 部门表
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
import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:部门表
 * @author AutoGen
 */
public class DpDeptDaoService {
	@Inject
	private DpDeptDao dao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param DpDeptInfo info
	 * @return DpDeptInfo
	 */
	public DpDeptInfo getInfoByKey(DpDeptInfo info) {
		DpDeptInfo info_dao = dao.get(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException()
					.addScene("TABLE", DpDeptInfo.TABLECN)
					.addScene("FIELD", info.getDept_id());
		}
		if (info_dao.getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException()
					.addScene("TABLE", DpDeptInfo.TABLECN)
					.addScene("FIELD", info_dao.getDept_cn_name() + "["
							+ info.getDept_id() + "]");
		}
		return info_dao;
	}

	public DpDeptInfo getInfoByKey(String dept_id) {
		DpDeptInfo info = dao.get(dept_id);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException()
					.addScene("TABLE", DpDeptInfo.TABLECN)
					.addScene("FIELD", info.getDept_cn_name() + "["
							+ info.getDept_id() + "]");
		}
		if (info.getRcd_state() == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException()
					.addScene("TABLE", DpDeptInfo.TABLECN)
					.addScene("FIELD", info.getDept_cn_name() + "["
							+ info.getDept_id() + "]");
		}
		return info;
	}

	public String getDeptCnameByKey(String dept_id) {
		return dao.getDeptCnameByKey(dept_id);
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param DpDeptInfo info
	 * @return DpDeptInfo
	 */
	public DpDeptInfo getInfoByKeyForUpdate(DpDeptInfo info) {
		DpDeptInfo info_dao = dao.getForUpdate(info);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException()
					.addScene("TABLE", DpDeptInfo.TABLECN)
					.addScene("FIELD", info_dao.getDept_cn_name() + "["
							+ info.getDept_id() + "]");
		}
		return info_dao;
	}

	/**
	 * Description: 插入一条记录
	 * @param DpDeptInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<DpDeptInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description:  清空部门表
	 */
	public void truncateTable() {
		dao.truncateTable();
	}

	/**
	 * Description: 根据上级部门编码分页查询多个部门
	 * @param id
	 * @return
	 */
	public List<DpDeptInfo> pageSubInfosByKey(String dept_id, int start_rcd,
			int limit_rcd) {
		return dao.pageSubInfosByKey(dept_id, start_rcd, limit_rcd);
	}

	/**
	 * Description: 根据上级部门编码分页查询多个部门之总条数
	 * @param id
	 * @return
	 */
	public int countSubInfosByKeyAllRecd(String dept_id) {
		return dao.countSubInfosByKeyAllRecd(dept_id);
	}

	/**
	 * Description: 删除一条记录
	 * @param dept_id
	 * @return int
	 */
	public int deleteDeptInfo(JaDate del_bk_date, JaTime del_bk_time,
			String del_user_id, String dept_id) {
		return dao.updateDeleteDept(RCD_STATE.ABNORMAL, del_bk_date,
				del_bk_time, del_user_id, dept_id);
	}

	/**
	 * Description: 根据部门名称模糊分页查询部门信息
	 * @param dept_name
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	public List<DpDeptInfo> pageInfosByName(String dept_name, int start_rcd,
			int limit_rcd) {
		return dao.pageDeptsByName(dept_name, start_rcd, limit_rcd);
	}

	/**
	 * Description: 根据部门名称模糊分页查询部门信息之总条数
	 * @param dept_name
	 * @return
	 */
	public int countInfosByNameAllRecd(String dept_name) {
		return dao.countDeptsByNameAllRecd(dept_name);
	}

	/**
	 * Description: 根据部门类型分页查询部门信息
	 * @param dept_type
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	public List<DpDeptInfo> pageInfosByType(DEPT_TYPE dept_type, int start_rcd,
			int limit_rcd) {
		return dao.pageDeptsByType(dept_type, start_rcd, limit_rcd);
	}

	/**
	 * Description: 根据部门类型分页查询部门信息之总条数
	 * @param dept_type
	 * @return
	 */
	public int countInfosByTypeAllRecd(DEPT_TYPE dept_type) {
		return dao.countDeptsByTypeAllRecd(dept_type);
	}

	/**
	 * Description: 根据部门级别分页查询部门信息
	 * @param dept_type
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	public List<DpDeptInfo> pageInfosByLevel(int dept_level, int start_rcd,
			int limit_rcd) {
		return dao.pageDeptsByLevel(dept_level, start_rcd, limit_rcd);
	}

	/**
	 * Description: 根据部门级别分页查询部门信息之总条数
	 * @param dept_type
	 * @return
	 */
	public int countInfosByLevelAllRecd(int dept_level) {
		return dao.countDeptsByLevelAllRecd(dept_level);
	}

	/**
	 * Description: 修改部门名称信息
	 * @param dept_id
	 * @param dept_cn_name
	 */
	public void updateDeptNameAndBranchNo(String dept_cn_name,
			String dept_full_cname, String branch_no, JaDate modify_bk_date,
			JaTime modify_bk_time, String modify_user_id, String dept_id) {
		dao.updateDeptNameAndBranchNoByKey(dept_cn_name, dept_full_cname,
				branch_no, modify_bk_date, modify_bk_time, modify_user_id,
				dept_id);
	}

	/**
	 * Description: 修改部门级别信息
	 * @param dept_type
	 * @param dept_level
	 * @param super_dept_id
	 * @param dept_id
	 */
	public void updateDeptLevel(int dept_level, String super_dept_id,
			String branch_no, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String dept_id) {
		dao.updateDeptLevelByKey(dept_level, super_dept_id, branch_no,
				modify_bk_date, modify_bk_time, modify_user_id, dept_id);
	}

	/**
	 * Description: 查询所有该部门简称的部门总数
	 * @param dept_cn_name 部门简称
	 * @return
	 */
	public int countDeptByDeptCnNames(String dept_id, String dept_cn_name) {
		return dao.countDeptByDeptCnNames(dept_id, dept_cn_name);
	}

	/**
	 * Description: 查询所有该部门全称的部门总数
	 * @param dept_full_name 部门全称
	 * @return
	 */
	public int countDeptByDeptFullNames(String dept_id,
			String dept_full_cname) {
		return dao.countDeptByDeptFullNames(dept_id, dept_full_cname);
	}

	/**
	 * 根据传入的部门编码数组编码查询该部门数组中所有的下级部门（包括数组中的部门）
	 * @param dept_ids 部门编码数组
	 * @return 所有的下级部门
	 */
	public List<String> queryDeptIdByKey(String[] dept_ids) {
		List<String> dept_id_list = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		Assert.isEmpty(dept_ids);
		if (dept_ids.length <= 0) {
			throw new DataErrorException().addScene("INPUT",
					DpDeptInfo.DEPT_IDCN);
		}
		for (String s : dept_ids) {
			dept_id_list.add(s);
		}
		return getDeptList(dept_id_list, list);
	}

	/**
	 * 查询输入部门列表的所有下级部门
	 * @param dept_id_list 部门列表
	 * @return 所有下级部门编码
	 */
	private List<String> getDeptList(List<String> dept_id_list,
			List<String> list) {
		list.addAll(dept_id_list);
		String dept_id_str = listToString(dept_id_list);
		List<String> dept_id_list_temp = dao.querySubDeptIdByKey(dept_id_str);
		if (!Assert.isEmpty(dept_id_list_temp)) {
			getDeptList(dept_id_list_temp, list);
		}
		return list;
	}

	/**
	 * 将输入的列表转换为字符串，例如列表中为a b c，转换后则为('a','b','c')
	 * @param list 输入列表
	 * @return 字符串
	 */
	private String listToString(List<String> list) {
		String str = "";
		// 输入的部门角色列表信息为空报错
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "输入信息");
		}
		for (String s : list) {
			str = str + s + "','";
		}
		str = "('" + str.substring(0, str.length() - 2) + ")";
		return str;
	}

	/**
	 * Description: 根据用户名查询所属部门信息
	 * @param user_id
	 * @return
	 */
	public DpDeptInfo getDeptInfoByUserId(String user_id) {
		return dao.getDeptInfoByUserId(user_id);
	}

	/**
	 * Description: 根据上级部门编码查询所有的部门
	 * @param super_dept_id 上级部门编码
	 * @return 下级部门编码集合
	 */
	public List<String> querySubDeptIdByKey(String user_id) {
		return dao.querySubDeptId(user_id);
	}

	public DBIterator<DeptExtendsBean> queryAllDeptInfo(String dept_id) {
		return dao.queryAllDeptInfo(dept_id);
	}

	// 根据部门编码查询部门信息
	public DpDeptInfo getDeptInfo(String dept_id) {
		return dao.getDeptInfo(dept_id);
	}

	/**
	 * Description: 查询单个部门的下级部门ID列表
	 * @param dept_id
	 * @return
	 */
	public List<String> querySubDeptIdsByDeptId(String dept_id) {
		List<String> dept_list = new ArrayList<String>();
		DBIterator<String> deptIterator = dao.iteratorSubDeptByDeptId(dept_id);
		try {
			while (deptIterator.hasNext()) {
				dept_list.add(deptIterator.next());
			}
		} finally {
			deptIterator.close();
		}
		return dept_list;
	}

	/**
	 * Description: 查询所有部门编号以及简称
	 * @return
	 */
	public List<DeptExtendsBean> queryAllDeptIdAndCnName() {
		List<DeptExtendsBean> dept_list = new ArrayList<DeptExtendsBean>();
		DBIterator<DeptExtendsBean> deptIterator = dao
				.iteratorDeptIdAndCnName();
		try {
			while (deptIterator.hasNext()) {
				dept_list.add(deptIterator.next());
			}
		} finally {
			deptIterator.close();
		}
		return dept_list;
	}
}