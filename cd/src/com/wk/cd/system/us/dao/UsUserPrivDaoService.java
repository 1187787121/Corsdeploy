/**
 * Title: UsUserPrivDaoService.java
 * File Description: 查询用户拥有的权限
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-11-27
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.OPEN_TYPE;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.enu.SQL_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.TempRsBean;
import com.wk.cd.system.us.bean.UsRsAfBean;
import com.wk.cd.system.us.bean.UsSocAfBean;
import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.BeanCopier;
import com.wk.util.JaDateTime;

/**
 * Class Description: 查询用户拥有的权限
 * @author link
 */
public class UsUserPrivDaoService {

	@Inject
	private UsUserPrivDao dao;
	@Inject
	private UsUserRsPrivDao rsPrivDao;
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private UsUserSocPrivDao socPrivDao;
	@Inject
	private UsUserColPrivDaoService colPrivDaoService;
	@Inject
	private BeanCopier<UsRsAfBean, RsResInfo> rscpy;
	@Inject
	private BeanCopier<UsSocAfBean, DtSourceInfo> soccpy;
	@Inject
	private UsUserRoleDaoService usUserRoleDaoService;
	@Inject
	private UsUserOptPrivDaoService usUserOptPrivDaoService;
	@Inject
	private UsUserSqlPrivDaoService sqlPrivService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 根据用户名查询用户拥有的资源权限
	 * @param String 输入变量类型
	 * @param user_id 输入用户名
	 * @return 资源编码列表
	 */
	public List<RsResInfo> getRspriv(String user_id, String bl_rs_code,
			JaDateTime dt_datetime) {
		List<RsResInfo> rs_list = getRsPrivNoSort(user_id, bl_rs_code,
				dt_datetime);
		Collections.sort(rs_list, new Comparator<RsResInfo>() {
			public int compare(RsResInfo arg0, RsResInfo arg1) {
				return (arg0.getRs_level() == arg1.getRs_level()) ? arg0
						.getSort_key() - arg1.getSort_key() : arg0
						.getRs_level() - arg1.getRs_level();
			}
		});
		return rs_list;
	}

	/**
	 * Description: 获取用户资源权限（所有：永久+临时）未排序
	 * @param user_id
	 * @return
	 */
	public List<RsResInfo> getRsPrivNoSort(String user_id, String bl_rs_code,
			JaDateTime dt_datetime) {
		List<RsResInfo> rs_list = new ArrayList<RsResInfo>();

		DBIterator<UsRsAfBean> urIterator = dao.queryIteratorUsRsAfByBlRsCode(
				user_id, bl_rs_code);
		Map<String, UsRsAfBean> urForbidMap = new HashMap<String, UsRsAfBean>();
		Map<String, RsResInfo> rsMap = new HashMap<String, RsResInfo>();
		try {
			while (urIterator.hasNext()) {
				UsRsAfBean usRsAfBean = urIterator.next();
				if (usRsAfBean.getAf_flag() == AF_FLAG.ALLOW) {
					RsResInfo rsInfo = new RsResInfo();
					rscpy.copy(usRsAfBean, rsInfo);
					rsMap.put(rsInfo.getRs_code(), rsInfo);
				} else {
					urForbidMap.put(usRsAfBean.getRs_code(), usRsAfBean);
				}
			}
		} finally {
			urIterator.close();
		}

		DBIterator<UsRsAfBean> urTempIterator = dao
				.iteratorUserRsTempPrivByBlRsCode(user_id, bl_rs_code,
						dt_datetime);

		try {
			while (urTempIterator.hasNext()) {
				UsRsAfBean usRsAfBean = urTempIterator.next();
				if (usRsAfBean.getAf_flag() == AF_FLAG.ALLOW) {
					RsResInfo rsInfo = new RsResInfo();
					rscpy.copy(usRsAfBean, rsInfo);
					rsMap.put(rsInfo.getRs_code(), rsInfo);
				}
			}
		} finally {
			urTempIterator.close();
		}

		DBIterator<RsResInfo> rsIterator = dao.iteratorDprlRsPrivByBlRsCode(
				user_id, bl_rs_code);
		try {
			while (rsIterator.hasNext()) {
				RsResInfo rsInfo = rsIterator.next();
				UsRsAfBean usRsAfBean = urForbidMap.get(rsInfo.getRs_code());
				// /用户允许的资源已经添加到结果集中，不允许的跳过
				if (!Assert.isEmpty(usRsAfBean)) {
					continue;
				}
				rsMap.put(rsInfo.getRs_code(), rsInfo);
			}
		} finally {
			rsIterator.close();
		}

		DBIterator<RsResInfo> pbIterator = rsResDaoService
				.iteratorPublicRs(bl_rs_code);
		try {
			while (pbIterator.hasNext()) {
				RsResInfo rsInfo = pbIterator.next();
				rsMap.put(rsInfo.getRs_code(), rsInfo);
			}
		} finally {
			rsIterator.close();
		}
		rs_list.addAll(rsMap.values());
		return rs_list;
	}

	/**
	 * Description: 获取用户资源权限（永久）
	 * @param user_id
	 * @return
	 */
	public List<RsResInfo> getPerpRsPrivByUser(String user_id) {
		List<RsResInfo> rs_list = new ArrayList<RsResInfo>();

		DBIterator<UsRsAfBean> urIterator = dao.queryIteratorUsRsAf(user_id);
		Map<String, UsRsAfBean> urMap = new HashMap<String, UsRsAfBean>();
		try {
			while (urIterator.hasNext()) {
				UsRsAfBean usRsAfBean = urIterator.next();
				if (usRsAfBean.getAf_flag() == AF_FLAG.ALLOW) {
					RsResInfo rsInfo = new RsResInfo();
					rscpy.copy(usRsAfBean, rsInfo);
					rs_list.add(rsInfo);
				}
				urMap.put(usRsAfBean.getRs_code(), usRsAfBean);
			}
		} finally {
			urIterator.close();
		}

		DBIterator<RsResInfo> rsIterator = dao.iteratorDprlRsPriv(user_id);
		try {
			while (rsIterator.hasNext()) {
				RsResInfo rsInfo = rsIterator.next();
				UsRsAfBean usRsAfBean = urMap.get(rsInfo.getRs_code());
				// /用户允许的资源已经添加到结果集中，不允许的跳过
				if (!Assert.isEmpty(usRsAfBean)) {
					continue;
				}
				rs_list.add(rsInfo);
			}
		} finally {
			rsIterator.close();
		}
		return rs_list;
	}

	public boolean hasRsPriv(String user_id, String rs_code,
			JaDateTime dt_datetime) {
		logger.plog("hasRsPriv begin get");
		RsResInfo rsInfo = new RsResInfo();
		rsInfo.setRs_code(rs_code);
		rsInfo = rsResDaoService.getInfoByKey(rsInfo);
		if (!Assert.isEmpty(rsInfo)
				&& rsInfo.getPublic_yn_flag() == YN_FLAG.YES) {
			return true;
		} else {
			UsUserRsPrivInfo turalinfo = rsPrivDao.get(user_id, rs_code,
					PRIV_TYPE.PERPETUAL);// 用户永久权限
			PRIV_TYPE type = PRIV_TYPE.TEMPORARY;
			if (rsInfo.getRs_level() == 0 || rsInfo.getRs_level() == 2) {
				type = PRIV_TYPE.TEMPPAGE;
			}
			UsUserRsPrivInfo tempinfo = rsPrivDao.get(user_id, rs_code, type);// 用户临时权限权限

			if (!Assert.isEmpty(turalinfo)
					&& turalinfo.getAf_flag() == AF_FLAG.ALLOW) {// 用户有权限
				return true;
			} else if (!Assert.isEmpty(tempinfo)
					&& tempinfo.getAf_flag() == AF_FLAG.ALLOW
					&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
					&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
				return true;
			} else if (dao.countDprlRsPriv(user_id, rs_code) != 0
					&& Assert.isEmpty(turalinfo)) {// 部门角色有权限
				return true;
			}
		}
		logger.plog("hasRsPriv end get");
		return false;
	}

	/**
	 * Description: 根据用户名查询用户拥有的临时资源权限
	 * @param String 输入变量类型
	 * @param user_id 输入用户名
	 * @return 资源编码列表
	 */
	public List<RsResInfo> getTempRspriv(String user_id) {
		List<RsResInfo> rs_list = new ArrayList<RsResInfo>();
		DBIterator<RsResInfo> rs_it = dao.iteratorRsTempPriv(user_id);
		try {
			while (rs_it.hasNext()) {
				rs_list.add(rs_it.next());
			}
		} finally {
			rs_it.close();
		}
		return rs_list;
	}

	/**
	 * Description: 根据用户名查询用户拥有的数据源权限
	 * @param String 输入变量类型
	 * @param user_id 输入用户名
	 * @return 数据源名称列表
	 */
	public List<DtSourceInfo> getSocPriv(String user_id) {

		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();

		DBIterator<UsSocAfBean> usocIterator = dao
				.queryIteratorUsSocAf(user_id);
		Map<String, UsSocAfBean> usocMap = new HashMap<String, UsSocAfBean>();
		try {
			while (usocIterator.hasNext()) {
				UsSocAfBean usSocAfBean = usocIterator.next();
				if (usSocAfBean.getAf_flag() == AF_FLAG.ALLOW) {
					DtSourceInfo socInfo = new DtSourceInfo();
					soccpy.copy(usSocAfBean, socInfo);
					soc_list.add(socInfo);
				}
				usocMap.put(usSocAfBean.getSoc_name(), usSocAfBean);
			}
		} finally {
			usocIterator.close();
		}

		DBIterator<DtSourceInfo> socIterator = dao.iteratorDprlSocPriv(user_id);
		try {
			while (socIterator.hasNext()) {
				DtSourceInfo socInfo = socIterator.next();
				UsSocAfBean usSocAfBean = usocMap.get(socInfo.getSoc_name());
				if (!Assert.isEmpty(usSocAfBean)) {
					if (usSocAfBean.getAf_flag() == AF_FLAG.FORBID) {
						continue;
					}
				}
				soc_list.add(socInfo);
			}
		} finally {
			socIterator.close();
		}
		return soc_list;
	}

	/**
	 * Description: 根据用户名查询用户拥有的临时数据源权限
	 * @param String 输入变量类型
	 * @param user_id 输入用户名
	 * @return 数据源名称列表
	 */
	public List<DtSourceInfo> getTempSocPriv(String user_id) {
		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();
		DBIterator<DtSourceInfo> soc_it = dao.getSocTempPriv(user_id);
		try {
			while (soc_it.hasNext()) {
				soc_list.add(soc_it.next());
			}
		} finally {
			soc_it.close();
		}
		return soc_list;
	}

	public boolean hasSocPriv(String user_id, String soc_name,
			JaDateTime dt_datetime) {
		UsUserSocPrivInfo prepinfo = socPrivDao.get(user_id, soc_name,
				PRIV_TYPE.PERPETUAL);
		// 用户存在永久权限记录
		if (!Assert.isEmpty(prepinfo) && prepinfo.getAf_flag() == AF_FLAG.ALLOW) {
			logger.debug("$$$$用户存在永久权限记录$$$$");
			return true;
		}
		UsUserSocPrivInfo tempinfo = socPrivDao.get(user_id, soc_name,
				PRIV_TYPE.TEMPORARY);
		// 用户存在临时权限有效记录
		if (!Assert.isEmpty(tempinfo)
				&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
				&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
			logger.debug("<<<<<用户存在临时权限有效记录>>>>");
			return true;
		}
		// 用户不存在永久禁止记录，但部门角色有权限
		int count = dao.countDprlSocPriv(user_id, soc_name);
		if (count != 0
				&& (Assert.isEmpty(prepinfo) || (!Assert.isEmpty(prepinfo) && prepinfo
						.getAf_flag() != AF_FLAG.FORBID))) {
			logger.debug("<<<<<用户不存在永久禁止记录，但部门角色有权限>>>>");
			return true;
		}
		return false;
	}

	/**
	 * Description: 判断用户对于表是否有对应类型的操作权限
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param sql_type
	 * @param dt_datetime
	 * @return
	 */
	public boolean hasTablePriv(String user_id, String soc_name,
			String table_name, SQL_TYPE sql_type, JaDateTime dt_datetime) {
		UsUserSqlPrivInfo prepinfo = sqlPrivService.getInfoByKey(user_id,
				soc_name, table_name, PRIV_TYPE.PERPETUAL);
		// 用户存在永久权限记录
		if (!Assert.isEmpty(prepinfo) && prepinfo.getAf_flag() == AF_FLAG.ALLOW) {
			logger.debug("用户[{}]存在[{}]永久权限记录",user_id,table_name);
			logger.debug("表类型：[{}],类型名：[{}]",sql_type,sql_type.getCname());
			if (sql_type.equals(SQL_TYPE.INSERT)
					&& prepinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.DELETE)
					&& prepinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.UPDATE)
					&& prepinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.SELECT)
					&& prepinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
				logger.debug("用户[{}]存在[{}]的永久查询权限记录",user_id,table_name);
				return true;
			}
		}
		UsUserSqlPrivInfo tempinfo = sqlPrivService.getInfoByKey(user_id,
				soc_name, table_name, PRIV_TYPE.TEMPORARY);
		// 用户存在临时权限有效记录
		if (!Assert.isEmpty(tempinfo)
				&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
				&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
			logger.debug("用户[{}]存在[{}]临时权限有效记录",user_id,table_name);
			if (sql_type.equals(SQL_TYPE.INSERT)
					&& tempinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.DELETE)
					&& tempinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.UPDATE)
					&& tempinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.SELECT)
					&& tempinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			}
		}
		// 用户不存在永久禁止记录，但部门角色有权限
		UsRoleSqlPrivInfo roleinfo = dao.getRoleSqlPrivInfo(user_id, soc_name,
				table_name);
		if (!Assert.isEmpty(roleinfo)
				&& (Assert.isEmpty(prepinfo) || (!Assert.isEmpty(prepinfo) && prepinfo
						.getAf_flag() != AF_FLAG.FORBID))) {
			logger.debug("用户[{}]存在[{}]部门角色有权限",user_id,table_name);
			if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Description: 判断用户对于列是否有对应类型的操作权限
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param col_name
	 * @param sql_type
	 * @param dt_datetime
	 * @return
	 */
	public boolean hasColPriv(String user_id, String soc_name,
			String table_name, String col_name, SQL_TYPE sql_type,
			JaDateTime dt_datetime) {
		logger.debug("------------hasColPriv begin--------------");
		UsUserColPrivInfo prepinfo = colPrivDaoService.getInfoByKey(user_id,
				soc_name, table_name, col_name, PRIV_TYPE.PERPETUAL);
		logger.debug("用户永久权限实例[{}]",prepinfo);
		// 用户存在永久权限记录
		if (!Assert.isEmpty(prepinfo) && prepinfo.getAf_flag() == AF_FLAG.ALLOW) {
			if (sql_type.equals(SQL_TYPE.INSERT)
					&& prepinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.DELETE)
					&& prepinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.UPDATE)
					&& prepinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.SELECT)
					&& prepinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			}
		}
		UsUserColPrivInfo tempinfo = colPrivDaoService.getInfoByKey(user_id,
				soc_name, table_name, col_name, PRIV_TYPE.TEMPORARY);

		logger.debug("临时权限实例[{}]",tempinfo);
		// 用户存在临时权限有效记录
		if(!Assert.isEmpty(tempinfo)){
			JaDateTime end = tempinfo.getTempend_bk_datetime();
			JaDateTime start = tempinfo.getTempstart_bk_datetime();
			if (!Assert.isEmpty(end)
					&& !Assert.isEmpty(start) && dt_datetime.isBefor(end)
					&& dt_datetime.isAfter(start)) {
				if (sql_type.equals(SQL_TYPE.INSERT)
						&& prepinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
					return true;
				} else if (sql_type.equals(SQL_TYPE.INSERT)
						&& prepinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
					return true;
				} else if (sql_type.equals(SQL_TYPE.INSERT)
						&& prepinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
					return true;
				} else if (sql_type.equals(SQL_TYPE.INSERT)
						&& prepinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
					return true;
				}
			}
		}

		
		// 用户不存在永久禁止记录，但部门角色有权限
		UsRoleColPrivInfo roleinfo = dao.getRoleColPrivInfo(user_id, soc_name,
				table_name, col_name);
		logger.debug("用户不存在永久禁止记录实例[{}]",roleinfo);
		if (!Assert.isEmpty(roleinfo)
				&& (Assert.isEmpty(prepinfo) || (!Assert.isEmpty(prepinfo) && prepinfo
						.getAf_flag() != AF_FLAG.FORBID))) {
			if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getIns_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getDel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getUpd_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			} else if (sql_type.equals(SQL_TYPE.INSERT)
					&& roleinfo.getSel_priv_flag().equals(PRIV_FLAG.YES)) {
				return true;
			}
		}
		logger.debug("------------hasColPriv end--------------");
		return false;
	}
	
	public UsRoleColPrivInfo getRoleColPrivInfo (String user_id, String soc_name,
			String table_name, String col_name){
		return dao.getRoleColPrivInfo(user_id, soc_name,
				table_name, col_name);
	}

	/**
	 * Description: 根据用户名查询用户拥有的SQL权限
	 * @param String 输入变量类型
	 * @param user_id 输入用户名
	 * @return SQL权限列表
	 */
	public List<UsUserPrivBean> getSqlPriv(String user_id) {
		List<UsUserPrivBean> sql_info_list = new ArrayList<UsUserPrivBean>();
		sql_info_list = dao.getSqlPriv(user_id, user_id);
		return sql_info_list;
	}

	/**
	 * Description: 根据用户名查询用户拥有的COL权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> getColPriv(String user_id) {
		List<UsUserPrivBean> col_info_list = new ArrayList<UsUserPrivBean>();
		col_info_list = dao.getColPriv(user_id, user_id);
		return col_info_list;
	}

	/**
	 * Description: 检测部门角色SQL权限表中是否存在该权限（已经去掉用户SQL权限表中删除的部分）
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	public int countByRoleSqlPriv(String dprl_code, String soc_name,
			String tbl_name, String user_id) {
		int count2 = dao.countByRoleSqlPriv(dprl_code, soc_name, tbl_name,
				user_id);
		return count2;
	}

	/**
	 * Description: 检测部门角色COL权限表中是否存在该权限（已经去掉用户COL权限表中删除的部分）
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	public int countByRoleColPriv(String dprl_code, String soc_name,
			String tbl_name, String col_name, String user_id) {
		int count2 = dao.countByRoleColPriv(dprl_code, soc_name, tbl_name,
				col_name, user_id);
		return count2;
	}

	/**
	 * Description: 从部门角色信息SQL操作权限表中查询某个用户对于数据源下的某张表的权限
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryTblPrivFromRole(String soc_name,
			String tbl_name, String user_id) {
		List<UsUserPrivBean> tbl_role_sql_list = new ArrayList<UsUserPrivBean>();
		tbl_role_sql_list = dao.queryTblPrivFromRole(soc_name, tbl_name,
				user_id);
		return tbl_role_sql_list;
	}

	/**
	 * Description: 从用户SQL操作权限表中查询某个用户对于数据源下的某张表的操作权限
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryTblPrivFromUser(String soc_name,
			String tbl_name, String user_id) {
		List<UsUserPrivBean> tbl_user_sql_list = new ArrayList<UsUserPrivBean>();
		tbl_user_sql_list = dao.queryTblPrivFromUser(soc_name, tbl_name,
				user_id);
		return tbl_user_sql_list;
	}

	/**
	 * Description: 从部门角色信息COL操作权限表中查询某个用户对于数据源下的某张表的某个字段的权限
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryColPrivFromRole(String soc_name,
			String tbl_name, String col_name, String user_id) {
		List<UsUserPrivBean> col_role_sql_list = new ArrayList<UsUserPrivBean>();
		col_role_sql_list = dao.queryColPrivFromRole(soc_name, tbl_name,
				col_name, user_id);
		return col_role_sql_list;
	}

	/**
	 * Description: 从用户COL操作权限表中查询某个用户对于数据源下的某张表的某个字段的操作权限
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryColPrivFromUser(String soc_name,
			String tbl_name, String col_name, String user_id) {
		List<UsUserPrivBean> col_user_sql_list = new ArrayList<UsUserPrivBean>();
		col_user_sql_list = dao.queryColPrivFromUser(soc_name, tbl_name,
				col_name, user_id);
		return col_user_sql_list;
	}

	/**
	 * Description: 查询用户被禁止的SQL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfSqlPriv(String user_id) {
		return dao.queryUserAfSqlPriv(user_id);
	}

	/**
	 * Description: 查询用户被禁止的COL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfColPriv(String user_id) {
		return dao.queryUserAfColPriv(user_id);
	}

	/**
	 * Description: 根据用户ID列表获得用户资源列表
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> iteratorRsInfos(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.iteratorRsInfosByIds(user_ids_str);
	}

	/**
	 * Description: 根据用户ID列表获得用户数据源列表
	 * @param dept_ids
	 * @return
	 */

	public List<String> querySocInfo(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.querySocByIds(user_ids_str);
	}

	/**
	 * Description: 根据用户ID列表获得用户服务列表
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> querySrvInfo(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.iteratorSvInfosByIds(user_ids_str);
	}

	/**
	 * 将输入的List转换为字符串
	 * @param list
	 * @return
	 */
	public String getStringByList(List<String> list) {
		String str = "";
		if (!list.isEmpty()) {
			for (String s : list) {
				str = str + s + "','";
			}
			if (!str.isEmpty()) {
				str = "('" + str.substring(0, str.length() - 2) + ")";
			} else {
				str = "('')";
			}
		}
		return str;
	}

	/**
	 * Description: 获取用户sql权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserSqlPrivInfo> getUserSqlPriv(String user_id) {
		List<UsUserSqlPrivInfo> sql_list = new ArrayList<UsUserSqlPrivInfo>();
		DBIterator<UsUserSqlPrivInfo> sqlIterator = dao.getUserSqlPriv(user_id);
		try {
			while (sqlIterator.hasNext()) {
				sql_list.add(sqlIterator.next());
			}
		} finally {
			sqlIterator.close();
		}
		return sql_list;
	}

	/**
	 * Description: 根据用户名获取用户COL权限名称
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> getUserColPriv(String user_id) {
		List<UsUserColPrivInfo> col_list = new ArrayList<UsUserColPrivInfo>();
		DBIterator<UsUserColPrivInfo> colIterator = dao.getUserColPriv(user_id);
		try {
			while (colIterator.hasNext()) {
				col_list.add(colIterator.next());
			}
		} finally {
			colIterator.close();
		}
		return col_list;
	}

	/**
	 * Description: 查询用户的操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserOptPrivInfo> queryOptPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv = new ArrayList<UsUserOptPrivInfo>();
		List<String> dprl_list = usUserRoleDaoService
				.queryDprlByUserId(user_id);

		// 取部门角色的权限
		DBIterator<ServiceData> drIterator = dao
				.iteratorDprlOptPrivByUser(user_id);
		Map<String, UsUserOptPrivInfo> dr_map = new HashMap<String, UsUserOptPrivInfo>();
		try {
			while (drIterator.hasNext()) {
				UsUserOptPrivInfo privInfo = new UsUserOptPrivInfo();

				ServiceData data = drIterator.next();
				String opt_code = data.getString("opt_code");
				int count = data.getInt("count");
				int priv_flag = data.getInt("priv_flag");

				privInfo.setOpt_code(opt_code);
				privInfo.setUser_id(user_id);
				if (priv_flag == 1) {
					privInfo.setPriv_flag(PRIV_FLAG.YES);
					dr_map.put(opt_code, privInfo);
				} else if (priv_flag == 2 && count == dprl_list.size()) {
					privInfo.setPriv_flag(PRIV_FLAG.NO);
					dr_map.put(opt_code, privInfo);
				}
			}
		} finally {
			drIterator.close();
		}

		// 取用户权限
		DBIterator<UsUserOptPrivInfo> usIterator = dao
				.iteratorOptPrivByUser(user_id);
		try {
			while (usIterator.hasNext()) {
				UsUserOptPrivInfo us_priv = usIterator.next();
				String opt_code = us_priv.getOpt_code();
				if (dr_map.containsKey(opt_code)) {
					if (us_priv.getPriv_flag() != dr_map.get(opt_code)
							.getPriv_flag()) {
						dr_map.remove(opt_code);
					}
				} else {
					dr_map.put(opt_code, us_priv);
				}
			}
		} finally {
			usIterator.close();
		}
		opt_priv.addAll(dr_map.values());
		return opt_priv;
	}

	/**
	 * Description: 根据用户查询部门角色资源权限
	 * @param user_id
	 * @return
	 */
	public List<RsPrivBean> queryDprlRsPrivByUserId(String user_id) {
		List<RsPrivBean> priv_list = new ArrayList<RsPrivBean>();
		DBIterator<RsResInfo> rsIterator = dao.iteratorDprlRsPriv(user_id);
		try {
			while (rsIterator.hasNext()) {
				RsResInfo rsInfo = rsIterator.next();
				RsPrivBean priv_bean = new RsPrivBean();
				priv_bean.setRs_code(rsInfo.getRs_code());
				priv_bean.setRs_cn_name(rsInfo.getRs_cn_name());
				priv_bean.setSuper_rs_code(rsInfo.getSuper_rs_code());
				priv_bean.setOpen_type(rsInfo.getOpen_type());
				priv_list.add(priv_bean);
			}
		} finally {
			rsIterator.close();
		}
		return priv_list;
	}

	/**
	 * Description: 根据用户查询部门角色操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserOptPrivInfo> queryDprlOptPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv = new ArrayList<UsUserOptPrivInfo>();
		List<String> dprl_list = usUserRoleDaoService
				.queryDprlByUserId(user_id);

		// 取部门角色的权限
		DBIterator<ServiceData> drIterator = dao
				.iteratorDprlOptPrivByUser(user_id);
		try {
			while (drIterator.hasNext()) {
				UsUserOptPrivInfo privInfo = new UsUserOptPrivInfo();

				ServiceData data = drIterator.next();
				String opt_code = data.getString("opt_code");
				int count = data.getInt("count");
				int priv_flag = data.getInt("priv_flag");

				privInfo.setOpt_code(opt_code);
				privInfo.setUser_id(user_id);
				if (priv_flag == 1) {
					privInfo.setPriv_flag(PRIV_FLAG.YES);
					opt_priv.add(privInfo);
				} else if (priv_flag == 2 && count == dprl_list.size()) {
					privInfo.setPriv_flag(PRIV_FLAG.NO);
					opt_priv.add(privInfo);
				}
			}
		} finally {
			drIterator.close();
		}
		return opt_priv;
	}

	/**
	 * Description: 根据用户查询部门角色数据源权限
	 * @param user_id
	 * @return
	 */
	public List<SocPrivBean> getDprlSocPrivByUser(String user_id) {
		List<SocPrivBean> priv_list = new ArrayList<SocPrivBean>();
		DBIterator<DtSourceInfo> socIterator = dao.iteratorDprlSocPriv(user_id);
		try {
			while (socIterator.hasNext()) {
				DtSourceInfo socInfo = socIterator.next();
				SocPrivBean priv_bean = new SocPrivBean();
				priv_bean.setSoc_name(socInfo.getSoc_name());
				priv_list.add(priv_bean);
			}
		} finally {
			socIterator.close();
		}
		return priv_list;
	}

	/**
	 * Description: 用户对操作 是否有操作权限
	 * @param user_id
	 * @param opt_code
	 * @param open_type
	 * @param size
	 * @return
	 */
	public boolean userHasOptPrivByOptCode(String user_id, String opt_code,
			OPEN_TYPE open_type, int size) {
		UsUserOptPrivInfo us_priv = new UsUserOptPrivInfo();
		us_priv.setUser_id(user_id);
		us_priv.setOpt_code(opt_code);
		us_priv = usUserOptPrivDaoService.getInfoByKey(us_priv);
		if (open_type == OPEN_TYPE.ALLOW) {// 开放类型
			int dprl_forbid_size = dao
					.countDprlOptforbidSize(user_id, opt_code);
			if (size == dprl_forbid_size) {// 部门角色禁止
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.YES) {
					return true;
				} else {
					return false;
				}
			} else {// 部门角色允许
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.NO) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			int dprl_allow_size = dao.countDprlOptAllowSize(user_id, opt_code);
			if (dprl_allow_size > 0) {// 部门角色允许
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.NO) {
					return false;
				} else {
					return true;
				}
			} else {// 部门角色禁止
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.YES) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Description:查询所有该用户的临时资源权限
	 * @param user_id
	 * @return
	 */
	public List<TempRsBean> getTempUsUserRsPriv(String user_id) {
		return rsPrivDao.getTempUsUserRsPriv(user_id);
	}
}
