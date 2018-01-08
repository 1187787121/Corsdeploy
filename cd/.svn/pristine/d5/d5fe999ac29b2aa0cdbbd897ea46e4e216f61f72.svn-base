/**
 * Title: SvSrvDao.java
 * File Description: 服务配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.system.ap.bean.ApproveServiceBean;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:服务配置表
 * @author AutoGen
 */
abstract class SvSrvDao
		extends EntityDao<SvSrvInfo> {

	// 按照主键查询一条记录
//	@SqlParam(condition = "SRV_NAME = :srv_name and RCD_STATE = 1")
	@SqlParam(sql="select * from SV_SRV where SRV_NAME = :srv_name and RCD_STATE = 1")
	abstract SvSrvInfo getInfoByName(String srv_name);

	// 修改记录状态
	@SqlParam(updateSet = { "RCD_STATE" }, condition = "PK")
	abstract int updateRcdStateInfo(RCD_STATE rcd_state, String srv_name);

	// 按照服务名称查询记录条数
	@SqlParam(condition = "SRV_NAME = :srv_name and RCD_STATE = 1")
	abstract int countInfo(String srv_name);

	// 更新服务配置表信息
	@SqlParam(updateSet = { "SRV_BK_DESC", "SRV_FUN_TYPE", "SRV_CLASS_NAME",
			"SRV_METHOD_NAME", "CHECK_FLAG", "AUTH_FLAG", "SOC_FLAG" }, 
			condition = "PK")
	abstract int updateInfo(SvSrvInfo info);

	// 分页查询一组服务信息
	@SqlParam(sql = "select srv_name, srv_bk_desc, srv_fun_type, srv_class_name, srv_method_name, check_flag, auth_flag, soc_flag, sallow_flag, " +
			"log_level, rcd_state from SV_SRV where (SRV_NAME in ${user_srv_str}) and (SRV_FUN_TYPE in ${srv_type_str}) and RCD_STATE = 1 " +
			"order by SRV_FUN_TYPE, SRV_NAME", dynamic = true)
	abstract List<SvSrvInfo> pageAllSvSrv(String user_srv_str,
			String srv_type_str, int start_recd, int limit_recd);

	// 分页查询时返回满足分页条件的总条数
	@SqlParam(sql = "select count(*) from SV_SRV where (SRV_NAME in ${user_srv_str}) and (SRV_FUN_TYPE in ${srv_type_str}) and RCD_STATE = 1 ", dynamic = true)
	abstract int countAllSvSrv(String user_srv_str, String srv_type_str);

	// 根据传入的服务名称字符串（格式为“('srv1','srv2','srv3')”）查询详细信息
	@SqlParam(sql = "select * from SV_SRV where SRV_NAME in ${srv_name_str::1=0} and RCD_STATE = 1", dynamic = true)
	abstract DBIterator<SvSrvInfo> iteratorSrvBySrvNames(String srv_name_str);

	// 无条件查询所有的记录状态为正常的服务
	@SqlParam(condition = "RCD_STATE = 1")
	abstract DBIterator<SvSrvInfo> iteratorAllSrv();

	/** 
	 * 查询所有本地授权服务数组
	 * @return 
	 */
	@SqlParam(sql="select b.SRV_NAME from (select DISTINCT SRV_NAME from sv_srv_auth where AUTH_TYPE=1)a ,sv_srv b where a.SRV_NAME=b.SRV_NAME and b.AUTH_FLAG=1")
	abstract DBIterator<String> queryLocalAuthSrv();
	
	/**
	 * Description: 根据服务类型分页查询服务
	 * @param srv_fun_type1 服务类型
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return
	 */
	@SqlParam(sql="select srv_name, srv_bk_desc, srv_fun_type, srv_class_name, srv_method_name, check_flag, auth_flag, soc_flag, sallow_flag,log_level, rcd_state from sv_srv where (srv_fun_type =${srv_fun_type}) and RCD_STATE = 1 order by SRV_FUN_TYPE, SRV_NAME",dynamic=true)
	abstract List<SvSrvInfo> pageSrvByFunType(FUN_TYPE srv_fun_type,int start_recd, int limit_recd);

	/**
	 * Description: 根据用户名、服务类型分页查询用户拥有的服务权限总条数
	 * @param srv_fun_type
	 * @return
	 */
	@SqlParam(sql="select count(*) from sv_srv where (srv_fun_type =${srv_fun_type}) and RCD_STATE = 1",dynamic=true)
	abstract int countSrvByFunType(FUN_TYPE srv_fun_type);

	/** 
	 * Description: 查询服务组包含服务列表
	 * @param sup_srvg_code
	 * @return 
	 */
	@SqlParam(condition="SUP_SRVG_CODE=:sup_srvg_code and RCD_STATE=1")
	abstract DBIterator<SvSrvInfo> listSubSrvBySrvgCode(String sup_srvg_code);
	
	/**
	 * Description: 查询所有可复核和可授权的服务
	 * @return
	 */
	@SqlParam(condition="(check_flag = 1 or auth_flag = 1) and rcd_state = 1")
	abstract DBIterator<ApproveServiceBean> queryCanApproveSrv();

	@SqlParam(updateSet = "SRV_CLASS_NAME", condition = "PK")
	abstract int updateClassNameByKey(String class_name, String srv_name);

	/** 
	 * Description: 
	 * @param key
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM SV_SRV WHERE (SRV_BK_DESC LIKE '%${key}%') AND (CHECK_FLAG = 1 OR AUTH_FLAG = 1) AND RCD_STATE = 1",dynamic=true)
	abstract DBIterator<ApproveServiceBean> queryCanApproveSrvByKey(String key);
}