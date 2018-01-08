/**
 * Title: SvSrvCheckDao.java
 * File Description: 服务复核定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:服务复核定义表
 * @author AutoGen
 */
abstract class SvSrvCheckDao
		extends EntityDao<SvSrvCheckInfo> {
	// 根据服务名称查询一组服务复核信息
	@SqlParam(condition = "SRV_NAME = :srv_name", orderBy = "check_seq")
	abstract List<SvSrvCheckInfo> getListInfoByName(String srv_name);

	// 根据部门编码和服务名称删除一组服务复核信息
	@SqlParam(condition = "CHECK_DEPT_ID = :dept_id and SRV_NAME = :srv_name")
	abstract int deleteInfo(String dept_id, String srv_name);
	
	// 根据服务名称删除一组服务复核信息
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteSvSrvCheck(String srv_name);

	// 按照部门编码和服务名称查询记录条数
	@SqlParam(sql = "select count(*) from sv_srv_check where (CHECK_DEPT_ID in ${dept_str::1=0}) and (SRV_NAME = '${srv_name::1=0}')", dynamic = true)
//	@SqlParam(condition = "CHECK_DEPT_ID in :dept_str and SRV_NAME = :srv_name")
	abstract int countInfos(String dept_str, String srv_name);
	
	// 按照服务名称查询记录条数
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countSvSrvCheck(String srv_name);

	// 按照部门角色列表查询复核服务
	@SqlParam(sql = "select srv_name, check_seq, chk_dprl_code, chk_aprov_category from sv_srv_check where (CHK_DPRL_CODE in ${check_dprl_code_str})", dynamic = true)
	abstract List<SvSrvCheckInfo> querySrvCheckByDprlCode(
			String check_dprl_code_str);

	//查询复核部门角色的相关信息
	@SqlParam(condition = "check_dept_id = :check_dept_id and srv_name = :srv_name", orderBy = "check_seq")
	abstract List<ChkDprlCodeBean> queryCheckDprlBySrvName(String check_dept_id, String srv_name);
	
	// 查找指定的服务名称和大于指定复核序号的最小的复核序号
	@SqlParam(sql = "select min(check_seq) from sv_srv_check where check_dept_id = ${dept_id} and srv_name = ${srv_name} and check_seq > ${check_seq}")
	abstract int queryMinCheckSeq(String dept_id, String srv_name, int check_seq);
}