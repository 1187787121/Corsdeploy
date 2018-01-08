/**
 * Title: SvSrvAuthDao.java
 * File Description: 服务授权定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:服务授权定义表
 * @author AutoGen
 */
abstract class SvSrvAuthDao
		extends EntityDao<SvSrvAuthInfo> {
	// 根据服务名称查询授权配置记录
	@SqlParam(condition = "SRV_NAME = :srv_name", orderBy = "auth_seq")
	abstract List<SvSrvAuthInfo> getListInfoByName(String srv_name);

	// 根据部门编码和服务名称删除服务授权定义表一组记录
	@SqlParam(condition = "AUTH_DEPT_ID = :dept_id and SRV_NAME = :srv_name")
	abstract int deleteInfo(String dept_id, String srv_name);
	
	// 根据服务名称删除服务授权定义表一组记录
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int deleteSvSrvAuth(String srv_name);

	// 按照部门编码和服务名称查询记录条数
	@SqlParam(sql = "select count(*) from sv_srv_auth where (AUTH_DEPT_ID in ${dept_str::1=0}) and (SRV_NAME = '${srv_name::1=0}')", dynamic = true)
	abstract int countInfos(String dept_str, String srv_name);
	
	// 按照服务名称查询记录条数
	@SqlParam(condition = "SRV_NAME = :srv_name")
	abstract int countSvSrvAuth(String srv_name);

	// 按照部门角色列表查询授权服务
	@SqlParam(sql = "select srv_name, auth_seq, auth_type, auth_dprl_code, auth_aprov_category from SV_SRV_AUTH WHERE (AUTH_DPRL_CODE in ${auth_dprl_code_str})", dynamic = true)
	abstract List<SvSrvAuthInfo> querySrvAuthByDprlCode(
			String auth_dprl_code_str);

	// 检查传入的服务名、序号对应的授权部门角色是否存在
	@SqlParam(sql = "select count(*) from SV_SRV_AUTH where SRV_NAME = ${srv_name} and AUTH_SEQ = ${auth_seq} and AUTH_DPRL_CODE in ${dprl_code_str} and AUTH_APROV_CATEGORY = ${role}", dynamic = true)
	abstract int countSrvByNameAndSeq(String srv_name, int auth_seq,
			String dprl_code_str, APROV_CATEGORY role);

	// 查询远程授权部门角色的相关信息
	@SqlParam(condition = "auth_dept_id = :auth_dept_id and srv_name = :srv_name", orderBy = "auth_seq")
	abstract List<AuthDprlCodeBean> queryAuthDprlBySrvName(String auth_dept_id, String srv_name);
	
	// 查找指定的服务名称和大于指定授权序号的最小的授权序号
	@SqlParam(sql = "select min(auth_seq) from sv_srv_auth where auth_dept_id = ${dept_id} and srv_name = ${srv_name} and auth_seq > ${auth_seq} and auth_type=${type}")
	abstract int queryMinAuthSeq(String dept_id, String srv_name, int auth_seq,AUTH_TYPE type);

	/** 根据服务和授权类型查询总数
	 * @param org_srv_name
	 * @param org_srv_name2
	 * @return 
	 */
	@SqlParam(condition="auth_dept_id=:dept_id and  srv_name=:org_srv_name and auth_type=:type")
	abstract int countBySrvAndType(String dept_id, String org_srv_name, AUTH_TYPE type);
}