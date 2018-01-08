/**
 * Title: RsResDao.java
 * File Description: 资源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.rs.dao;

import java.util.List;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:资源配置表
 * @author AutoGen
 */
abstract class RsResDao
		extends EntityDao<RsResInfo> {

	/**
	 * Description: 查询所有资源信息
	 * @return List<DpDeptInfo>
	 */
	@SqlParam(condition = "RCD_STATE=1")
	abstract List<RsResInfo> pageResInfosByNull(int start_rcd, int limit_rcd);

	/**
	 * Description: 查询所有资源信息之总条数
	 * @return int
	 */
	@SqlParam(condition = "RCD_STATE=1")
	abstract int countResInfosByNullAllRecd();

	/**
	 * 
	 * Description: 根据资源编码查询资源信息
	 * @param rs_code
	 * @return
	 */
	@SqlParam(condition = "RS_CODE= :rs_code and RCD_STATE=1")
	abstract RsResInfo getInfo(String rs_code);

	/**
	 * 
	 * Description: 根据资源编码查询资源状态信息
	 * @param rs_code
	 * @return
	 */
	@SqlParam(sql = "select RCD_STATE from RS_RES where RS_CODE=:rs_code")
	abstract RsResInfo getInfoStateByCode(String rs_code);

	// @SqlParam(querySet = {"RCD_STATE"}, condition = "PK")
	// abstract RsResInfo getInfoStateByCode(String rs_code);

	/**
	 * 
	 * Description: 根据资源名称模糊查询部门信息
	 * @param rs_cn_name
	 * @return
	 */
	@SqlParam(sql = "select rs_code, super_rs_code, rs_fun_type, rs_cn_name, rs_bk_desc, rs_url, rsim_url, rs_level, title_able, sort_key, " +
			"rcd_state from RS_RES where rcd_state=1 and (rs_cn_name like '%${rs_cn_name}%')", dynamic = true)
	abstract List<RsResInfo> queryInfosByName(String rs_cn_name);

	/**
	 * Description: 根据资源名称模糊查询部门信息之总条数
	 * @param rs_cn_name
	 * @return
	 */
	@SqlParam(condition = "(rs_cn_name like '%${rs_cn_name}%') and RCD_STATE=1", dynamic = true)
	abstract int countInfosByNameAllRecd(String rs_cn_name);

	/**
	 * Description: 根据部门代码查询部门资源权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from DP_DEPT_RS_PRIV where DEPT_ID =:dept_id and RCD_STATE = 1")
	abstract List<String> queryDeptRsPrivInfos(String dept_id);

	/**
	 * Description: 根据资源编码字符串查询资源信息
	 * @param codes
	 * @return
	 */
	@SqlParam(sql = "select * from RS_RES where RS_CODE in ${codes::1=0} and RCD_STATE = 1", dynamic = true)
	abstract DBIterator<RsResInfo> iteratorInfoByCodeString(String codes);

	/**
	 * Description: 根据资源编码字符串查询资源信息
	 * @param codes
	 * @return
	 */
	@SqlParam(sql = "select rs_code, super_rs_code, rs_fun_type, rs_cn_name, rs_bk_desc, rs_url, rsim_url, rs_level, title_able, sort_key, " +
			"rcd_state from RS_RES where RS_CODE in ${codes::1=0} and RCD_STATE = 1", dynamic = true)
	abstract List<RsResInfo> queryInfoByCodeString(String codes);

	/**
	 * Description: 无条件查询所有的资源
	 * @return
	 */
	@SqlParam(condition = "RCD_STATE = 1")
	abstract DBIterator<RsResInfo> iteratorAllRes();
	
	/**
	 * Description: 查询所有资源权限信息列表
	 * @return
	 */
	@SqlParam(condition = "PUBLIC_YN_FLAG=2 and RCD_STATE = 1")
	abstract DBIterator<RsPrivBean> iteratorAllUnPublicRsPriv();

	/** 
	 * Description: 获取一级导航
	 * @return 
	 */
	@SqlParam(condition="SUPER_RS_CODE is null and RS_LEVEL=-1 and RCD_STATE=1")
	abstract List<RsResInfo> getFirstNavigate();
	
	/** 
	 * Description: 获取一级导航下所有资源
	 * @return 
	 */
	@SqlParam(condition="BL_RS_CODE=:bl_rs_code and RCD_STATE=1")
	abstract List<RsResInfo> getRsListByBlRsCode(String bl_rs_code);

	/** 
	 * Description: 获取资源URL对照列表
	 * @param bl_rs_code
	 * @return 
	 */
	@SqlParam(querySet={"RS_CODE","RS_URL"},condition="BL_RS_CODE=:bl_rs_code and RCD_STATE=1")
	abstract List<RsUrlBean> getRsUrlList(String bl_rs_code);

	/** 
	 * Description: 查询资源是否存在
	 * @param rs_code
	 * @return 
	 */
	@SqlParam(condition="RS_CODE=:rs_code and RCD_STATE=1")
	abstract int countByRsCode(String rs_code);

	/** 
	 * Description: 查询bl_rs_code下所有公共资源
	 * @param bl_rs_code
	 * @return 
	 */
	@SqlParam(condition="BL_RS_CODE=:bl_rs_code and PUBLIC_YN_FLAG=1 and RCD_STATE=1")
	abstract DBIterator<RsResInfo> iteratorPublicRs(String bl_rs_code);

	/** 
	 * Description: 查询所有公开的模块描述的资源
	 * @return 
	 */
	@SqlParam(condition="RS_LEVEL=-2 and PUBLIC_YN_FLAG=1 and RCD_STATE=1")
	abstract List<RsResInfo> getModular();
}