/**
 * Title: DpDeptDao.java
 * File Description: 部门表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.List;

import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:部门表
 * @author AutoGen
 */
abstract class DpDeptDao
		extends EntityDao<DpDeptInfo> {

	/**
	 * Description: 根据上级部门编码查询多部门信息
	 * @param dept_id
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	@SqlParam(condition = "SUPER_DEPT_ID = :dept_id and RCD_STATE=1")
	abstract List<DpDeptInfo> pageSubInfosByKey(String dept_id, int start_rcd,
			int limit_rcd);

	/**
	 * Description: 根据上级部门编码查询多部门信息之总条数
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition = "SUPER_DEPT_ID = :dept_id and RCD_STATE=1")
	abstract int countSubInfosByKeyAllRecd(String dept_id);

	/**
	 * Description: 根据上级部门编码查询下级部门ID
	 * @param dept_id
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	@SqlParam(sql = "select DEPT_ID from dp_dept where (SUPER_DEPT_ID in ${dept_id_str::1=0}) and (RCD_STATE=1)", dynamic=true)
	abstract List<String> querySubDeptIdByKey(String dept_id_str);

	/**
	 * Description: 根据上级部门编码查询下级部门ID
	 * @param dept_id
	 * @return
	 */
	@SqlParam(sql = "select dept_id from dp_dept dp,us_user us where us.user_id = ${user_id} and " +
			"( dp.super_dept_id = us.bl_dept_id or (us.first_dept_id != '' and dp.super_dept_id = us.first_dept_id)) and dp.RCD_STATE=1")
	abstract List<String> querySubDeptId(String user_id);
	
	/**
	 * Description: 根据部门代码软删除部门信息
	 * @param dept_id
	 * @param rcdState
	 * @return
	 */
	@SqlParam(updateSet = { "RCD_STATE", "DEL_BK_DATE", "DEL_BK_TIME",
			"DEL_USER_ID" }, condition = "PK")
	abstract int updateDeleteDept(RCD_STATE rcdState, JaDate del_bk_date,
			JaTime del_bk_time, String del_user_id, String dept_id);

	/**
	 * 
	 * Description: 根据部门名称模糊分页查询部门信息
	 * @param dept_name
	 * @param start_rcd
	 * @param limit_rcd
	 * @return
	 */
	@SqlParam(sql = "select * from DP_DEPT where rcd_state=1 and (dept_cn_name like '%${dept_name}%')", dynamic = true)
	abstract List<DpDeptInfo> pageDeptsByName(String dept_name, int start_rcd,
			int limit_rcd);

	/**
	 * 
	 * Description: 根据部门名称模糊分页查询部门信息之总条数
	 * @param dept_name
	 * @return
	 */
	@SqlParam(condition = "(dept_cn_name like '%${dept_name}%') and RCD_STATE=1", dynamic = true)
	abstract int countDeptsByNameAllRecd(String dept_name);

	/**
	 * Description: 根据部门类型列出部门信息
	 * @param deptType
	 * @param startRcd
	 * @param limitRcd
	 * @return
	 */
	@SqlParam(condition = "DEPT_TYPE=:dept_type and RCD_STATE=1 ")
	abstract List<DpDeptInfo> pageDeptsByType(DEPT_TYPE dept_type,
			int start_rcd, int limit_rcd);

	/**
	 * Description: 根据部门类型列出部门信息之总条数
	 * @param deptType
	 * @return
	 */
	@SqlParam(condition = "DEPT_TYPE=:dept_type and RCD_STATE=1 ")
	abstract int countDeptsByTypeAllRecd(DEPT_TYPE dept_type);

	/**
	 * Description: 根据部门级别列出部门信息
	 * @param deptType
	 * @param startRcd
	 * @param limitRcd
	 * @return
	 */
	@SqlParam(condition = "DEPT_LEVEL=:dept_level and RCD_STATE=1")
	abstract List<DpDeptInfo> pageDeptsByLevel(int dept_level, int start_rcd,
			int limit_rcd);

	/**
	 * Description: 根据部门级别列出部门信息之总条数
	 * @param deptType
	 * @return
	 */
	@SqlParam(condition = "DEPT_LEVEL=:dept_level and RCD_STATE=1")
	abstract int countDeptsByLevelAllRecd(int dept_level);

	/**
	 * 
	 * Description: 修改部门名称信息
	 * @param dept_id
	 * @param dept_name
	 */
	@SqlParam(updateSet = { "DEPT_CN_NAME","DEPT_FULL_CNAME","BRANCH_NO", "MODIFY_BK_DATE", "MODIFY_BK_TIME",
			"MODIFY_USER_ID" }, condition = "PK")
	abstract void updateDeptNameAndBranchNoByKey(String dept_name,String dept_full_cname,String branch_no, JaDate modify_bk_date,
			JaTime modify_bk_time, String modify_user_id, String dept_id);

	/**
	 * 
	 * Description: 修改部门级别信息
	 * @param dept_type
	 * @param dept_level
	 * @param super_dept_id
	 * @param dept_id
	 */
	@SqlParam(updateSet = { "DEPT_LEVEL", "SUPER_DEPT_ID", "BRANCH_NO",
			"MODIFY_BK_DATE", "MODIFY_BK_TIME", "MODIFY_USER_ID" }, condition = "PK")
	abstract void updateDeptLevelByKey(int dept_level, String super_dept_id,
			String branch_no, JaDate modify_bk_date, JaTime modify_bk_time,
			String modify_user_id, String dept_id);

	/**
	 * Description: 查询所有该部门简称的部门总数
	 * @param dept_cn_name 部门简称
	 * @return
	 */
	@SqlParam(condition = "DEPT_ID <> :dept_id and DEPT_CN_NAME = :dept_cn_cname and rcd_state=1")
	abstract int countDeptByDeptCnNames(String dept_id, String dept_cn_name);
	
	/**
	 * Description: 查询所有该部门全称的部门总数
	 * @param dept_full_name 部门全称
	 * @return
	 */
	@SqlParam(condition = "DEPT_ID <> :dept_id and DEPT_FULL_CNAME = :dept_full_cname and rcd_state=1")
	abstract int countDeptByDeptFullNames(String dept_id, String dept_full_cname);

	/** 
	 * Description: 根据用户名查询所属部门信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select dp.* from us_user us,dp_dept dp where us.bl_dept_id=dp.dept_id and us.user_id=:user_id and us.rcd_state=1 and dp.rcd_state=1")
	abstract DpDeptInfo getDeptInfoByUserId(String user_id);

	@SqlParam(querySet={"dept_cn_name"},condition="PK")
	abstract String getDeptCnameByKey(String dept_id);
	@SqlParam(sql ="select (select count(*) from dp_dept dp2 where dp2.super_dept_id=dp.dept_id and dp2.rcd_state=1) LOWDPT_BK_NUM,dept_id,dept_cn_name,dept_type,dept_level,super_dept_id from dp_dept dp where (dp.dept_id in ${dept_id::1=0})  and dp.rcd_state=1 order by dept_level, dept_id", dynamic = true)
	abstract DBIterator<DeptExtendsBean> queryAllDeptInfo(String dept_id);

	/** 
	 * Description: 查询单个部门的下级部门ID列表
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(condition = "DEPT_ID = :dept_id")
	abstract DpDeptInfo getDeptInfo(String dept_id);
	
	/** 
	 * Description: 查询单个部门的下级部门ID列表
	 * @param dept_id
	 * @return 
	 */
	@SqlParam(querySet={"DEPT_ID"},condition="SUPER_DEPT_ID=:dept_id and RCD_STATE=1")
	abstract DBIterator<String> iteratorSubDeptByDeptId(String dept_id);

	/** 
	 * Description: 
	 * @return 
	 */
	@SqlParam(querySet={"DEPT_ID","DEPT_CN_NAME"}, condition="RCD_STATE=1")
	abstract DBIterator<DeptExtendsBean> iteratorDeptIdAndCnName();
}