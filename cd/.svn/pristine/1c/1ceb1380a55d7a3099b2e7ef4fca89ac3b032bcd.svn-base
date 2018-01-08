/**
 * Title: UsUserPrivDao.java
 * File Description: 用户实际拥有的权限查询
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-11-27
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.us.bean.UsRsAfBean;
import com.wk.cd.system.us.bean.UsSocAfBean;
import com.wk.cd.system.us.bean.UsSrvAfBean;
import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.Query;
import com.wk.db.SafeInsert;
import com.wk.db.SafeUpdate;
import com.wk.db.SqlParam;
import com.wk.sdo.ServiceData;
import com.wk.util.JaDateTime;

/**
 * Class Description: 用户实际拥有的权限查询
 * @author link
 */
@Query
abstract class UsUserPrivDao {

	/**
	 * Description: 根据用户名查询用户拥有的资源权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "(SELECT b.* FROM rs_res b,us_user_rs_priv a WHERE USER_ID=:user_id1 AND AF_FLAG=1 "
			+ "AND b.RS_CODE=a.RS_CODE AND RCD_STATE=1) "
			+ "UNION "
			+ "(SELECT b.* FROM rs_res b,us_role_rs_priv c WHERE "
			+ "(DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id )) "
			+ "AND c.RS_CODE=b.RS_CODE AND RCD_STATE = 1 AND "
			+ "(c.RS_CODE NOT IN (SELECT RS_CODE FROM us_user_rs_priv WHERE AF_FLAG=2  and USER_ID=:user_id2))) "
			+ "ORDER BY RS_LEVEL, SORT_KEY")
	abstract DBIterator<RsResInfo> iteratorRsPriv(String user_id1,
			String user_id,String user_id2);
	
	/**
	 * Description: 根据用户名查询用户拥有的临时资源权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_user_rs_priv a, rs_res b WHERE a.USER_ID=:user_id AND a.AF_FLAG=1 AND a.PRIV_TYPE <> 1 AND b.RS_CODE = a.RS_CODE AND b.RCD_STATE=1 ORDER BY b.RS_FUN_TYPE, a.RS_CODE")
	abstract DBIterator<RsResInfo> iteratorRsTempPriv(String user_id);
	
	

	/**
	 * Description: 根据用户名查询用户拥有的数据源权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "(SELECT b.* FROM dt_source b,us_user_soc_priv a WHERE USER_ID=:user_id1 AND AF_FLAG=1 "
			+ "AND a.SOC_NAME=b.SOC_NAME) "
			+ "UNION "
			+ "(SELECT b.* FROM dt_source b,us_role_soc_priv c WHERE "
			+ "(DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id )) "
			+ "AND c.SOC_NAME=b.SOC_NAME AND "
			+ "(c.SOC_NAME NOT IN (SELECT SOC_NAME FROM us_user_soc_priv WHERE AF_FLAG=2 and USER_ID=:user_id2))) "
			+ "ORDER BY SOC_NAME ")
	abstract DBIterator<DtSourceInfo> getSocPriv(String user_id1, String user_id,String user_id2);
	
	/**
	 * Description: 根据用户名查询用户拥有的临时数据源权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_user_soc_priv a, dt_source b WHERE a.USER_ID=:user_id AND a.AF_FLAG=1 AND a.PRIV_TYPE = 2 AND a.SOC_NAME=b.SOC_NAME AND b.RCD_STATE=1 ORDER BY a.SOC_NAME ")
		abstract DBIterator<DtSourceInfo> getSocTempPriv(String user_id);

	/**
	 * Description: 根据用户名查询用户拥有的临时服务权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_user_srv_priv a, sv_srv b WHERE a.USER_ID=:user_id AND a.AF_FLAG=1 AND a.PRIV_TYPE = 2 AND a.SRV_NAME=b.SRV_NAME AND b.RCD_STATE=1 ORDER BY b.SRV_FUN_TYPE, b.SRV_NAME ")
		abstract DBIterator<SvSrvInfo> iteratorSrvTempPriv(String user_id);

	/**
	 * Description: 根据用户名查询用户的SQL字段权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "(SELECT DPRL_CODE,SOC_NAME,TBL_NAME,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_user_sql_priv "
			+ "WHERE(AF_FLAG=1 AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id1)))) "
			+ "UNION "
			+ "(SELECT a.DPRL_CODE,a.SOC_NAME,a.TBL_NAME,a.INS_PRIV_FLAG,a.DEL_PRIV_FLAG,a.UPD_PRIV_FLAG,a.SEL_PRIV_FLAG "
			+ "FROM us_role_sql_priv a LEFT JOIN "
			+ "us_user_sql_priv b USING(DPRL_CODE,SOC_NAME,TBL_NAME) "
			+ "WHERE b.DPRL_CODE IS NULL AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id))) ")
	abstract List<UsUserPrivBean> getSqlPriv(String user_id1, String user_id);

	/**
	 * Description: 根据用户名查询用户的COL权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "(SELECT DPRL_CODE,SOC_NAME,TBL_NAME,COL_NAME,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_user_col_priv "
			+ "WHERE AF_FLAG=1 AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id1))) "
			+ "UNION "
			+ "(SELECT a.DPRL_CODE,a.SOC_NAME,a.TBL_NAME,a.COL_NAME,a.INS_PRIV_FLAG,a.DEL_PRIV_FLAG,a.UPD_PRIV_FLAG,a.DEL_PRIV_FLAG "
			+ "FROM us_role_col_priv a LEFT JOIN "
			+ "us_user_col_priv b USING(DPRL_CODE,SOC_NAME,TBL_NAME,COL_NAME) "
			+ "WHERE b.DPRL_CODE IS NULL AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id))) ")
	abstract List<UsUserPrivBean> getColPriv(String user_id1, String user_id);

	/**
	 * Description: 检测部门角色SQL权限表中是否存在该权限（已经去掉用户SQL权限表中删除的部分）
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "(SELECT COUNT(*) FROM us_role_sql_priv a LEFT JOIN "
			+ "us_user_sql_priv b USING(DPRL_CODE,SOC_NAME,TBL_NAME) "
			+ "WHERE b.DPRL_CODE IS NULL AND DPRL_CODE=:dprl_code AND SOC_NAME=:soc_name AND TBL_NAME=:tbl_name "
			+ "AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id))) ")
	abstract int countByRoleSqlPriv(String dprl_code, String soc_name,
			String tbl_name, String user_id);

	/**
	 * Description: 检测部门角色COL权限表中是否存在该权限（已经去掉用户COL权限表中删除的部分）
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT COUNT(*) FROM us_role_col_priv a LEFT JOIN "
			+ "us_user_col_priv b USING(DPRL_CODE,SOC_NAME,TBL_NAME,COL_NAME) "
			+ "WHERE b.DPRL_CODE IS NULL AND DPRL_CODE=:dprl_code AND SOC_NAME=:soc_name "
			+ "AND TBL_NAME=:tbl_name AND COL_NAME=:col_name "
			+ "AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id))")
	abstract int countByRoleColPriv(String dprl_code, String soc_name,
			String tbl_name, String col_name, String user_id);

	/**
	 * Description: 从部门角色信息SQL操作权限表中查询某个用户对于数据源下的某张表的权限
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT DPRL_CODE,SOC_NAME,TBL_NAME,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_role_sql_priv "
			+ "WHERE SOC_NAME=:soc_name AND TBL_NAME=:tbl_name AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id)) ")
	abstract List<UsUserPrivBean> queryTblPrivFromRole(String soc_name,
			String tbl_name, String user_id);

	/**
	 * Description: 从用户SQL操作权限表中查询某个用户对于数据源下的某张表的操作权限
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT DPRL_CODE,SOC_NAME,TBL_NAME,AF_FLAG,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_user_sql_priv "
			+ "WHERE SOC_NAME=:soc_name AND TBL_NAME=:tbl_name AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id)) ")
	abstract List<UsUserPrivBean> queryTblPrivFromUser(String soc_name,
			String tbl_name, String user_id);

	/**
	 * Description: 从部门角色信息COL操作权限表中查询某个用户对于数据源下的某张表的某个字段的权限
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT DPRL_CODE,SOC_NAME,TBL_NAME,COL_NAME,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_role_col_priv "
			+ "WHERE SOC_NAME=:soc_name AND TBL_NAME=:tbl_name AND COL_NAME=:col_name AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id)) ")
	abstract List<UsUserPrivBean> queryColPrivFromRole(String soc_name,
			String tbl_name, String col_name, String user_id);

	/**
	 * Description: 从用户COL操作权限表中查询某个用户对于数据源下的某张表的某个字段的操作权限
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT DPRL_CODE,SOC_NAME,TBL_NAME,COL_NAME,AF_FLAG,INS_PRIV_FLAG,DEL_PRIV_FLAG,UPD_PRIV_FLAG,SEL_PRIV_FLAG FROM us_user_col_priv "
			+ "WHERE SOC_NAME=:soc_name AND TBL_NAME=:tbl_name AND COL_NAME=:col_name AND (DPRL_CODE IN (SELECT DPRL_CODE FROM us_user_role WHERE USER_ID=:user_id)) ")
	abstract List<UsUserPrivBean> queryColPrivFromUser(String soc_name,
			String tbl_name, String col_name, String user_id);

	/**
	 * Description: 查询用户被禁止的SQL权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT user_id, srv_name, rs_code, super_rs_code, rs_fun_type, rs_cn_name, rs_bk_desc, rs_level, rs_url, soc_name, tbl_name, " +
			"col_name, af_flag, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag FROM us_user_sql_priv WHERE AF_FLAG=2 AND USER_ID=:user_id ")
	abstract List<UsUserPrivBean> queryUserAfSqlPriv(String user_id);

	/**
	 * Description: 查询用户被禁止的COL权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT user_id, srv_name, rs_code, super_rs_code, rs_fun_type, rs_cn_name, rs_bk_desc, rs_level, rs_url, soc_name, tbl_name, " +
			"col_name, af_flag, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag FROM us_user_col_priv WHERE AF_FLAG=2 AND USER_ID=:user_id ")
	abstract List<UsUserPrivBean> queryUserAfColPriv(String user_id);

	/** 
	 * Description: 查询用户资源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select up.af_flag,rs.* from us_user_rs_priv up,rs_res rs where up.rs_code=rs.rs_code and user_id=:user_id and priv_type=1 and rs.public_yn_flag=2 and rcd_state=1 order by rs_level")
	abstract DBIterator<UsRsAfBean> queryIteratorUsRsAf(String user_id);
	
	/** 
	 * Description: 根据一级资源查询用户资源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select up.af_flag,rs.* from us_user_rs_priv up,rs_res rs where up.rs_code=rs.rs_code and user_id=:user_id and bl_rs_code=:bl_rs_code and priv_type=1 and rcd_state=1 order by rs_level")
	abstract DBIterator<UsRsAfBean> queryIteratorUsRsAfByBlRsCode(String user_id,String bl_rs_code);
	
	/**
	 * Description: 根据用户名及一级资源编码查询用户拥有的临时资源权限
	 * @param user_id1
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT a.af_flag,b.* FROM us_user_rs_priv a, rs_res b WHERE a.USER_ID=:user_id and b.bl_rs_code=:bl_rs_code AND a.AF_FLAG=1 AND a.PRIV_TYPE <> 1 AND b.RS_CODE = a.RS_CODE AND (:dt_datetime BETWEEN a.tempstart_bk_datetime and a.tempend_bk_datetime) AND b.RCD_STATE=1")
	abstract DBIterator<UsRsAfBean> iteratorUserRsTempPrivByBlRsCode(String user_id,String bl_rs_code,JaDateTime dt_datetime);

	/** 
	 * Description: 查询部门角色资源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select * from rs_res r where exists("+
				  " select distinct(rl.rs_code) from us_user_role us,us_role_rs_priv rl "+
				  " where us.DPRL_CODE=rl.DPRL_CODE and us.USER_ID=:user_id and "+
				  " r.RS_CODE=rl.RS_CODE and r.PUBLIC_YN_FLAG=2 and RCD_STATE=1"+
				  " ) order by r.rs_level")
	abstract DBIterator<RsResInfo> iteratorDprlRsPriv(String user_id);
	
	/** 
	 * Description: 查询一级资源的部门角色资源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select * from rs_res r where exists("+
				  " select distinct(rl.rs_code) from us_user_role us,us_role_rs_priv rl "+
				  " where us.DPRL_CODE=rl.DPRL_CODE and us.USER_ID=:user_id and "+
				  " r.RS_CODE=rl.RS_CODE and RCD_STATE=1"+
				  " ) and bl_rs_code=:bl_rs_code order by r.rs_level")
	abstract DBIterator<RsResInfo> iteratorDprlRsPrivByBlRsCode(String user_id,String bl_rs_code);

	@SafeInsert
	@SafeUpdate
	@SqlParam(sql = "select count(*)  from us_user_role us,us_role_rs_priv rl where us.DPRL_CODE=rl.DPRL_CODE and us.USER_ID=:user_id  and rl.rs_code =:rs_code ")
	abstract int countDprlRsPriv(String user_id, String rs_code);

	/** 
	 * Description: 查询用户数据源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select up.af_flag,soc.* from us_user_soc_priv up,dt_source soc where up.soc_name=soc.soc_name and user_id=:user_id and priv_type=1 and rcd_state=1")
	abstract DBIterator<UsSocAfBean> queryIteratorUsSocAf(String user_id);

	/** 
	 * Description: 查询部门角色数据源权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select distinct soc.* from us_user_role us,us_role_soc_priv sl,dt_source soc where us.DPRL_CODE=sl.DPRL_CODE and sl.soc_name=soc.soc_name and us.USER_ID=:user_id and RCD_STATE=1")
	abstract DBIterator<DtSourceInfo> iteratorDprlSocPriv(String user_id);

	@SafeInsert 
	@SafeUpdate
	@SqlParam(sql="select count(*)  from us_user_role us,us_role_soc_priv sl where us.DPRL_CODE=sl.DPRL_CODE   and us.USER_ID=:user_id and sl.soc_name=:soc_name")
	abstract int countDprlSocPriv(String user_id, String soc_name);

	/** 
	 * Description: 查询用户服务权限信息
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select up.af_flag,sv.* from us_user_srv_priv up,sv_srv sv where up.srv_name=sv.srv_name and user_id=:user_id and priv_type=1 and rcd_state=1")
	abstract DBIterator<UsSrvAfBean> queryIteratorUsSrvAf(String user_id);

	/**
	 * 根据多个用户编码查询资源编码
	 * @param user_ids_str
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from US_USER_RS_PRIV where USER_ID in ${user_ids_str::1 = 0}", dynamic = true)
	abstract DBIterator<String> iteratorRsInfosByIds(String user_ids_str);
	/**
	 * 根据多个用户编码查询数据源编码
	 * @param user_ids_str
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from US_USER_SOC_PRIV where USER_ID in ${user_ids_str::1 = 0}", dynamic = true)
	abstract List<String> querySocByIds(String user_ids_str);
	/**
	 * 根据多个用户编码查询服务编码
	 * @param user_ids_str
	 * @return
	 */
	@SqlParam(sql = "select SRV_NAME from US_USER_SRV_PRIV where USER_ID in ${user_ids_str::1 = 0}", dynamic = true)
	abstract DBIterator<String> iteratorSvInfosByIds(String user_ids_str);

	/** 
	 * Description: 获取用户sql权限
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select * from us_user_sql_priv where user_id=:user_id and priv_type=1")
	abstract DBIterator<UsUserSqlPrivInfo> getUserSqlPriv(String user_id);

	/** 
	 * Description: 根据用户名获取用户COL权限名称
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select * from us_user_col_priv where user_id=:user_id and priv_type=1")
	abstract DBIterator<UsUserColPrivInfo> getUserColPriv(String user_id);

	/** 
	 * Description: 根据用户取部门角色的操作权限
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select opt_code,priv_flag,count(*) count from us_user_role us,us_role_opt_priv priv where us.DPRL_CODE=priv.DPRL_CODE and us.USER_ID=:user_id group by opt_code,priv_flag")
	abstract DBIterator<ServiceData> iteratorDprlOptPrivByUser(String user_id);

	/** 
	 * Description: 查询用户所有的操作权限
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select * from us_user_opt_priv where user_id=:user_id")
	abstract DBIterator<UsUserOptPrivInfo> iteratorOptPrivByUser(String user_id);
	
	/** 
	 * Description: 查询用户的部门角色中禁止操作权限的个数
	 * @param user_id
	 * @param opt_code
	 * @return 
	 */
	@SqlParam(sql="select count(*) from us_role_opt_priv priv,us_user_role ur where ur.dprl_code=priv.dprl_code and ur.user_id=:user_id and priv.opt_code=:opt_code and priv.priv_flag=2")
	abstract int countDprlOptforbidSize(String user_id, String opt_code);

	/** 
	 * Description: 查询用户的部门角色中允许操作权限的个数
	 * @param user_id
	 * @param opt_code
	 * @return 
	 */
	@SqlParam(sql="select count(*) from us_role_opt_priv priv,us_user_role ur where ur.dprl_code=priv.dprl_code and ur.user_id=:user_id and priv.opt_code=:opt_code and priv.priv_flag=1")
	abstract int countDprlOptAllowSize(String user_id, String opt_code);

	/** 
	 * Description: 查询部门角色SQL权限信息
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @return 部门角色SQL权限信息
	 */
	@SqlParam(sql="SELECT * FROM us_role_sql_priv WHERE DPRL_CODE=(SELECT DPRL_CODE FROM us_user_role WHERE user_id=:user_id) AND SOC_NAME=:soc_name AND TBL_NAME=:table_name")
	abstract UsRoleSqlPrivInfo getRoleSqlPrivInfo(String user_id, String soc_name,
			String table_name);

	/** 
	 * Description: 查询部门角色COL权限信息
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param col_name
	 * @return 部门角色COL权限信息
	 */
	@SqlParam(sql="SELECT * FROM us_role_col_priv WHERE DPRL_CODE=(SELECT DPRL_CODE FROM us_user_role WHERE user_id=:user_id) AND SOC_NAME=:soc_name AND TBL_NAME=:table_name AND COL_NAME=:col_name")
	abstract UsRoleColPrivInfo getRoleColPrivInfo(String user_id, String soc_name,
			String table_name, String col_name);

}
