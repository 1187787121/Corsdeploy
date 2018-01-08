/**
 * Title: UsUserPrivService.java
 * File Description: 根据用户名查询用户所拥有的资源、数据源、服务、SQL、COL权限
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-11-27
 */
package com.wk.cd.system.us.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.enu.SQL_TYPE;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.TempRsBean;
import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description:根据用户名查询用户所拥有的资源、数据源、服务、SQL、COL权限
 * @author link
 */
public class UsGetUserPrivService {
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private UsUserPrivDaoService daoService;
	@Inject
	private UsUserSqlPrivDaoService userSqlDaoService;
	@Inject
	private UsRoleSqlPrivDaoService dprlSqlDaoService;
	@Inject
	private UsUserGetRoleDeptService getDeptService;
	@Inject
	private UsUserColPrivDaoService userColDaoService;
	@Inject
	private UsRoleColPrivDaoService dprlColDaoService;
	
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 根据用户名获取用户资源编码
	 * @param user_id 输入变量
	 * @return 用户资源编码列表
	 */
	public List<RsResInfo> getUserRsPriv(String user_id, String bl_rs_code,JaDateTime dt_datetime) {
		return daoService.getRspriv(user_id, bl_rs_code, dt_datetime);
	}
	
	/** 
	 * Description: 获取系统所有下级资源权限
	 * @param user_id
	 * @param bl_rs_code
	 * @return 
	 */
	public List<RsResInfo> getAllSubRsPriv(String user_id, String bl_rs_code) {
		return rsResDaoService.getRsListByBlRsCode(bl_rs_code);
	}

	/**
	 * 判断用户是否具有资源权限
	 * @param user_id 
	 * @return boolean
	 */
	public  boolean hasRsPriv(String user_id, String rs_code,JaDateTime dt_datetime){
		return daoService.hasRsPriv(user_id, rs_code,dt_datetime);
	}
	
	/**
	 * Description: 根据用户名获取用户数据源名称(永久)
	 * @param user_id 输入变量
	 * @return 用户数据源名称列表
	 */
	public List<SocPrivBean> getUserSocPriv(String user_id) {
		List<SocPrivBean> soc_priv=new ArrayList<SocPrivBean>();
		List<DtSourceInfo> soc_list=daoService.getSocPriv(user_id);
		for(DtSourceInfo soc_info:soc_list){
			SocPrivBean priv_bean=new SocPrivBean();
			priv_bean.setSoc_name(soc_info.getSoc_name());
			soc_priv.add(priv_bean);
		}
		return soc_priv;
	}
	
	/**
	 * Description: 根据用户名获取用户数据源名称(永久+临时)
	 * @param user_id 输入变量
	 * @return 用户数据源名称列表
	 */
	public List<SocPrivBean> getUserTualSocPriv(String user_id) {
		List<SocPrivBean> soc_priv=new ArrayList<SocPrivBean>();
		List<DtSourceInfo> soc_list=daoService.getSocPriv(user_id);
		for(DtSourceInfo soc_info:soc_list){
			SocPrivBean priv_bean=new SocPrivBean();
			priv_bean.setSoc_name(soc_info.getSoc_name());
			soc_priv.add(priv_bean);
		}
		List<DtSourceInfo> temp_soc_list=daoService.getTempSocPriv(user_id);
		for(DtSourceInfo soc_info:temp_soc_list){
			SocPrivBean priv_bean=new SocPrivBean();
			priv_bean.setSoc_name(soc_info.getSoc_name());
			soc_priv.add(priv_bean);
		}
		return soc_priv;
	}
	
	public boolean hasSocPriv(String user_id, String soc_name,JaDateTime dt_datetime){
		return daoService.hasSocPriv(user_id,soc_name,dt_datetime);
	}
	
	public boolean hasTablePriv(String user_id,String soc_name,String table_name,SQL_TYPE sql_type,JaDateTime dt_datetime){
		return daoService.hasTablePriv(user_id, soc_name, table_name, sql_type, dt_datetime);
	}
	
	public boolean hasColPriv(String user_id,String soc_name,String table_name,String col_name,SQL_TYPE sql_type,JaDateTime dt_datetime){
	
		return daoService.hasColPriv(user_id,soc_name,table_name, col_name, sql_type, dt_datetime);
	}
	
	/**
	 * Description: 根据用户名获取用户临时数据源名称
	 * @param user_id 输入变量
	 * @return 用户数据源名称列表
	 */
	public List<DtSourceInfo> getUserTempSocPriv(String user_id) {
		return daoService.getTempSocPriv(user_id);
	}
	
	/**
	 * 
	 * Description: 根据用户名获取用户SQL权限名称
	 * @param user_id
	 * @return
	 */
	public List<UsUserSqlPrivInfo> getUserSqlPriv(String user_id) {
		return daoService.getUserSqlPriv(user_id);
	}

	/**
	 * Description: 根据用户名获取用户SQL权限名称(备份)
	 * @param user_id 输入变量
	 * @return 权限列表
	 */
	public List<UsUserSqlPrivInfo> getUserSqlPriv_bak(String user_id) {
		Map<String, String> user_sql_map = new HashMap<String, String>();
		user_sql_map = getUserSqlPrivMap(user_id);
		List<UsUserSqlPrivInfo> user_sql_list = new ArrayList<UsUserSqlPrivInfo>();

		for (Entry<String, String> entry : user_sql_map.entrySet()) {
			UsUserSqlPrivInfo info = new UsUserSqlPrivInfo();
			String key = entry.getKey();
			String value = entry.getValue();
			String[] key_string = key.split(", ");
			String[] value_string = value.split(", ");
			info.setUser_id(user_id);
			info.setSoc_name(key_string[0].substring(1));
			info.setTbl_name(key_string[1].substring(0,
					key_string[1].length() - 1));
			if (value_string[0].substring(1).contains("1")) {
				info.setIns_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setIns_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[1].contains("1")) {
				info.setDel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setDel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[2].contains("1")) {
				info.setUpd_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setUpd_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[3].contains("1")) {
				info.setSel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setSel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[4].contains("1")) {
				info.setAf_flag(AF_FLAG.ALLOW);
			} else {
				info.setAf_flag(AF_FLAG.FORBID);
			}
			if (value_string[5].contains("1")) {
				info.setPriv_type(PRIV_TYPE.PERPETUAL);
			} else {
				info.setPriv_type(PRIV_TYPE.TEMPORARY);
			}
			user_sql_list.add(info);
		}
		return user_sql_list;
	}

	/**
	 * Description: 根据用户名获取用户SQL权限,返回MAP
	 * @param user_id
	 * @return
	 */
	public Map<String, String> getUserSqlPrivMap(String user_id) {

		List<String> dprl_list = new ArrayList<String>();
		dprl_list = getDeptService.queryDprlByUserId(user_id);

		List<UsUserSqlPrivInfo> user_sql_list = new ArrayList<UsUserSqlPrivInfo>();
		user_sql_list = userSqlDaoService.querySqlPrivByUserId(user_id);
		// 将用户的SQL操作权限表转化为MAP
		Map<String, String> user_sql_map = new HashMap<String, String>();
		for (UsUserSqlPrivInfo user_info : user_sql_list) {
			List<String> user_key = new ArrayList<String>();
			List<String> user_value = new ArrayList<String>();
			user_key.add(user_info.getSoc_name());
			user_key.add(user_info.getTbl_name());
			user_value.add(user_info.getIns_priv_flag().toString());
			user_value.add(user_info.getDel_priv_flag().toString());
			user_value.add(user_info.getUpd_priv_flag().toString());
			user_value.add(user_info.getSel_priv_flag().toString());
			user_value.add(user_info.getAf_flag().toString());
			user_value.add(user_info.getPriv_type().toString());
			// logger.debug("user_key=[{}],user_value=[{}]",
			// user_key.toString(),
			// user_value.toString());
			user_sql_map.put(user_key.toString(), user_value.toString());
		}

		// 获取用户所拥有的部门角色所拥有的SQL操作权限
		Map<String, String> dprl_sql_map = new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			List<UsRoleSqlPrivInfo> dprl_sql_list = new ArrayList<UsRoleSqlPrivInfo>();
			dprl_sql_list = dprlSqlDaoService.querySqlByDprl(dprl_code);
			for (UsRoleSqlPrivInfo dprl_info : dprl_sql_list) {
				List<String> dprl_key = new ArrayList<String>();
				List<String> dprl_value = new ArrayList<String>();
				dprl_key.add(dprl_info.getSoc_name());
				dprl_key.add(dprl_info.getTbl_name());
				dprl_value.add(dprl_info.getIns_priv_flag().toString());
				dprl_value.add(dprl_info.getDel_priv_flag().toString());
				dprl_value.add(dprl_info.getUpd_priv_flag().toString());
				dprl_value.add(dprl_info.getSel_priv_flag().toString());
				dprl_value.add(AF_FLAG.ALLOW.toString());
				dprl_value.add(PRIV_TYPE.PERPETUAL.toString());
				if (user_sql_map.containsKey(dprl_key.toString())) {
					continue;
				} else if (dprl_sql_map.containsKey(dprl_key.toString())) {// 数据合并
					String edit_key = dprl_key.toString();
					String edit_value = dprl_sql_map.get(dprl_key.toString());

					String[] edit_value_char = edit_value.split(", ");
					edit_value_char[0] = edit_value_char[0].substring(1);
					edit_value_char[5] = edit_value_char[5].substring(0, 1);
					int count = 0;
					List<String> temp_value = new ArrayList<String>();
					for (String temp : dprl_value) {
						if (temp.contains("1")) {
							edit_value_char[count] = temp.toString();
						}
						temp_value.add(edit_value_char[count].toString());
						count++;
					}
					edit_value = temp_value.toString();
					dprl_sql_map.put(edit_key, edit_value);
					// logger.debug("更改后：dprl_key=[{}],dprl_value=[{}]",
					// dprl_key.toString(), dprl_value.toString());
				} else { // 添加
					dprl_sql_map
							.put(dprl_key.toString(), dprl_value.toString());
					// logger.debug("dprl_key=[{}],dprl_value=[{}]",
					// dprl_key.toString(), dprl_value.toString());
				}
			}
		}
		user_sql_map.putAll(dprl_sql_map);
		return user_sql_map;
	}

	/**
	 * Description: 根据用户名获取用户SQL临时权限名称
	 * @param user_id 输入变量
	 * @return 权限列表
	 */
	public List<UsUserSqlPrivInfo> getUserTempSqlPriv(String user_id) {
		Map<String, String> user_sql_map = new HashMap<String, String>();
		user_sql_map = getUserSqlTempPrivMap(user_id);
		List<UsUserSqlPrivInfo> user_sql_list = new ArrayList<UsUserSqlPrivInfo>();

		for (Entry<String, String> entry : user_sql_map.entrySet()) {
			UsUserSqlPrivInfo info = new UsUserSqlPrivInfo();
			String key = entry.getKey();
			String value = entry.getValue();
			String[] key_string = key.split(", ");
			String[] value_string = value.split(", ");
			info.setUser_id(user_id);
			info.setSoc_name(key_string[0].substring(1));
			info.setTbl_name(key_string[1].substring(0,
					key_string[1].length() - 1));
			if (value_string[0].substring(1).contains("1")) {
				info.setIns_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setIns_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[1].contains("1")) {
				info.setDel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setDel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[2].contains("1")) {
				info.setUpd_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setUpd_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[3].contains("1")) {
				info.setSel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setSel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[4].contains("1")) {
				info.setAf_flag(AF_FLAG.ALLOW);
			} else {
				info.setAf_flag(AF_FLAG.FORBID);
			}
			if (value_string[5].contains("1")) {
				info.setPriv_type(PRIV_TYPE.PERPETUAL);
			} else {
				info.setPriv_type(PRIV_TYPE.TEMPORARY);
			}
			user_sql_list.add(info);
		}
		return user_sql_list;
	}

	/**
	 * Description: 根据用户名获取用户临时SQL权限,返回MAP
	 * @param user_id
	 * @return
	 */
	private Map<String, String> getUserSqlTempPrivMap(String user_id) {
		List<UsUserSqlPrivInfo> user_sql_list = new ArrayList<UsUserSqlPrivInfo>();
		user_sql_list = userSqlDaoService.querySqlPrivByUserId(user_id);
		// 将用户的SQL操作权限表转化为MAP
		Map<String, String> user_sql_map = new HashMap<String, String>();
		for (UsUserSqlPrivInfo user_info : user_sql_list) {
			List<String> user_key = new ArrayList<String>();
			List<String> user_value = new ArrayList<String>();
			user_key.add(user_info.getSoc_name());
			user_key.add(user_info.getTbl_name());
			user_value.add(user_info.getIns_priv_flag().toString());
			user_value.add(user_info.getDel_priv_flag().toString());
			user_value.add(user_info.getUpd_priv_flag().toString());
			user_value.add(user_info.getSel_priv_flag().toString());
			user_value.add(user_info.getAf_flag().toString());
			user_value.add(user_info.getPriv_type().toString());
			user_sql_map.put(user_key.toString(), user_value.toString());
		}
		return user_sql_map;
	}
	
	/**
	 * 
	 * Description:  根据用户名获取用户COL权限名称
	 * @param user_id
	 * @return
	 */
	public List<UsUserColPrivInfo> getUserColPriv(String user_id) {
		return daoService.getUserColPriv(user_id);
	}

	/**
	 * Description: 根据用户名获取用户COL权限名称(备份)
	 * @param user_id 输入变量
	 * @return 权限列表
	 */
	public List<UsUserColPrivInfo> getUserColPriv_bak(String user_id) {
		Map<String, String> user_col_map = new HashMap<String, String>();
		user_col_map = getUserColPrivMap(user_id);

		List<UsUserColPrivInfo> user_col_list = new ArrayList<UsUserColPrivInfo>();
		for (Entry<String, String> entry : user_col_map.entrySet()) {
			UsUserColPrivInfo info = new UsUserColPrivInfo();
			String key = entry.getKey();
			String value = entry.getValue();
			String[] key_string = key.split(", ");
			String[] value_string = value.split(", ");
			info.setUser_id(user_id);
			info.setSoc_name(key_string[0].substring(1));
			info.setTbl_name(key_string[1]);
			info.setCol_name(key_string[2].substring(0,
					key_string[2].length() - 1));

			if (value_string[0].substring(1).contains("1")) {
				info.setIns_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setIns_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[1].contains("1")) {
				info.setDel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setDel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[2].contains("1")) {
				info.setUpd_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setUpd_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[3].contains("1")) {
				info.setSel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setSel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[4].contains("1")) {
				info.setAf_flag(AF_FLAG.ALLOW);
			} else {
				info.setAf_flag(AF_FLAG.FORBID);
			}
			if (value_string[5].contains("1")) {
				info.setPriv_type(PRIV_TYPE.PERPETUAL);
			} else {
				info.setPriv_type(PRIV_TYPE.TEMPORARY);
			}
			user_col_list.add(info);
		}
		return user_col_list;
	}

	/**
	 * Description: 根据用户名获取用户COL权限名称,返回MAP
	 * @param user_id
	 * @return
	 */
	public Map<String, String> getUserColPrivMap(String user_id) {

		List<String> dprl_list = new ArrayList<String>();
		dprl_list = getDeptService.queryDprlByUserId(user_id);

		List<UsUserColPrivInfo> user_col_list = new ArrayList<UsUserColPrivInfo>();
		user_col_list = userColDaoService.queryColPrivByUserId(user_id);
		// 将用户的COL操作权限表转化为MAP
		Map<String, String> user_col_map = new HashMap<String, String>();
		for (UsUserColPrivInfo user_info : user_col_list) {
			List<String> user_key = new ArrayList<String>();
			List<String> user_value = new ArrayList<String>();
			user_key.add(user_info.getSoc_name());
			user_key.add(user_info.getTbl_name());
			user_key.add(user_info.getCol_name());

			user_value.add(user_info.getIns_priv_flag().toString());
			user_value.add(user_info.getDel_priv_flag().toString());
			user_value.add(user_info.getUpd_priv_flag().toString());
			user_value.add(user_info.getSel_priv_flag().toString());
			user_value.add(user_info.getAf_flag().toString());
			user_value.add(user_info.getPriv_type().toString());
			// logger.debug("user_key=[{}],user_value=[{}]",
			// user_key.toString(),
			// user_value.toString());
			user_col_map.put(user_key.toString(), user_value.toString());
		}

		// 获取用户所拥有的部门角色所拥有的COL操作权限
		Map<String, String> dprl_col_map = new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			List<UsRoleColPrivInfo> dprl_col_list = new ArrayList<UsRoleColPrivInfo>();
			dprl_col_list = dprlColDaoService.queryColByDprl(dprl_code);
			for (UsRoleColPrivInfo dprl_info : dprl_col_list) {
				List<String> dprl_key = new ArrayList<String>();
				List<String> dprl_value = new ArrayList<String>();
				dprl_key.add(dprl_info.getSoc_name());
				dprl_key.add(dprl_info.getTbl_name());
				dprl_key.add(dprl_info.getCol_name());
				dprl_value.add(dprl_info.getIns_priv_flag().toString());
				dprl_value.add(dprl_info.getDel_priv_flag().toString());
				dprl_value.add(dprl_info.getUpd_priv_flag().toString());
				dprl_value.add(dprl_info.getSel_priv_flag().toString());
				dprl_value.add(AF_FLAG.ALLOW.toString());
				dprl_value.add(PRIV_TYPE.PERPETUAL.toString());
				if (user_col_map.containsKey(dprl_key.toString())) {
					continue;
				} else if (dprl_col_map.containsKey(dprl_key.toString())) {// 数据合并
					String edit_key = dprl_key.toString();
					String edit_value = dprl_col_map.get(dprl_key.toString());

					String[] edit_value_char = edit_value.split(", ");
					edit_value_char[0] = edit_value_char[0].substring(1);
					edit_value_char[5] = edit_value_char[5].substring(0, 1);
					int count = 0;
					List<String> temp_value = new ArrayList<String>();
					for (String temp : dprl_value) {
						if (temp.contains("1")) {
							edit_value_char[count] = temp.toString();
						}
						temp_value.add(edit_value_char[count].toString());
						count++;
					}
					edit_value = temp_value.toString();
					dprl_col_map.put(edit_key, edit_value);
					// logger.debug("更改后：dprl_key=[{}],dprl_value=[{}]",
					// dprl_key.toString(), dprl_value.toString());
				} else { // 添加
					dprl_col_map
							.put(dprl_key.toString(), dprl_value.toString());
					// logger.debug("dprl_key=[{}],dprl_value=[{}]",
					// dprl_key.toString(), dprl_value.toString());
				}
			}
		}
		user_col_map.putAll(dprl_col_map);
		return user_col_map;
	}

	/**
	 * Description: 根据用户名获取用户COL临时权限名称
	 * @param user_id 输入变量
	 * @return 权限列表
	 */
	public List<UsUserColPrivInfo> getUserTempColPriv(String user_id) {
		Map<String, String> user_col_map = new HashMap<String, String>();
		user_col_map = getUserColTempPrivMap(user_id);

		List<UsUserColPrivInfo> user_col_list = new ArrayList<UsUserColPrivInfo>();
		for (Entry<String, String> entry : user_col_map.entrySet()) {
			UsUserColPrivInfo info = new UsUserColPrivInfo();
			String key = entry.getKey();
			String value = entry.getValue();
			String[] key_string = key.split(", ");
			String[] value_string = value.split(", ");
			info.setUser_id(user_id);
			info.setSoc_name(key_string[0].substring(1));
			info.setTbl_name(key_string[1]);
			info.setCol_name(key_string[2].substring(0,
					key_string[2].length() - 1));

			if (value_string[0].substring(1).contains("1")) {
				info.setIns_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setIns_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[1].contains("1")) {
				info.setDel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setDel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[2].contains("1")) {
				info.setUpd_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setUpd_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[3].contains("1")) {
				info.setSel_priv_flag(PRIV_FLAG.YES);
			} else {
				info.setSel_priv_flag(PRIV_FLAG.NO);
			}
			if (value_string[4].contains("1")) {
				info.setAf_flag(AF_FLAG.ALLOW);
			} else {
				info.setAf_flag(AF_FLAG.FORBID);
			}
			if (value_string[5].contains("1")) {
				info.setPriv_type(PRIV_TYPE.PERPETUAL);
			} else {
				info.setPriv_type(PRIV_TYPE.TEMPORARY);
			}
			user_col_list.add(info);
		}
		return user_col_list;
	}

	/**
	 * Description: 根据用户名获取用户COL权限名称,返回MAP
	 * @param user_id
	 * @return
	 */
	private Map<String, String> getUserColTempPrivMap(String user_id) {
		List<UsUserColPrivInfo> user_col_list = new ArrayList<UsUserColPrivInfo>();
		user_col_list = userColDaoService.queryColPrivByUserId(user_id);
		// 将用户的COL操作权限表转化为MAP
		Map<String, String> user_col_map = new HashMap<String, String>();
		for (UsUserColPrivInfo user_info : user_col_list) {
			List<String> user_key = new ArrayList<String>();
			List<String> user_value = new ArrayList<String>();
			user_key.add(user_info.getSoc_name());
			user_key.add(user_info.getTbl_name());
			user_key.add(user_info.getCol_name());

			user_value.add(user_info.getIns_priv_flag().toString());
			user_value.add(user_info.getDel_priv_flag().toString());
			user_value.add(user_info.getUpd_priv_flag().toString());
			user_value.add(user_info.getSel_priv_flag().toString());
			user_value.add(user_info.getAf_flag().toString());
			user_value.add(user_info.getPriv_type().toString());
			user_col_map.put(user_key.toString(), user_value.toString());
		}
		return user_col_map;
	}
	
	/**
	 * 
	 * Description: 根据用户ID列表获得用户资源列表（待删除）
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> iteratorRsInfos(List<String> user_ids) {
		return daoService.iteratorRsInfos(user_ids);
	}
	/**
	 * 
	 * Description: 根据用户ID列表获得用户数据源列表（待删除）
	 * @param dept_ids
	 * @return
	 */

	public List<String> querySocInfo(List<String> user_ids) {
		return daoService.querySocInfo(user_ids);
	}
	/**
	 * 
	 * Description: 根据用户ID列表获得用户服务列表（待删除）
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> querySrvInfo(List<String> user_ids) {
		return daoService.querySrvInfo(user_ids);
	}
	/** 
	 * Description: 查询用户资源权限（永久）
	 * @param user_id
	 * @return 
	 */
	public List<RsPrivBean> queryRsPrivByUserId(String user_id) {
		List<RsResInfo> rs_list=daoService.getPerpRsPrivByUser(user_id);
		List<RsPrivBean> rs_priv=new ArrayList<RsPrivBean>();
		for(RsResInfo rs_info : rs_list){
			RsPrivBean priv=new RsPrivBean();
			priv.setRs_code(rs_info.getRs_code());
			priv.setRs_cn_name(rs_info.getRs_cn_name());
			priv.setSuper_rs_code(rs_info.getSuper_rs_code());
			priv.setOpen_type(rs_info.getOpen_type());
			rs_priv.add(priv);
		}
		return rs_priv;
	}
	
	/** 
	 * Description: 查询用户资源权限（永久+临时）
	 * @param user_id
	 * @return 
	 */
	public List<RsPrivBean> queryRealRsPrivByUserId(String user_id) {
		List<RsResInfo> rs_list=daoService.getPerpRsPrivByUser(user_id);
		List<RsPrivBean> rs_priv=new ArrayList<RsPrivBean>();
		for(RsResInfo rs_info : rs_list){
			RsPrivBean priv=new RsPrivBean();
			priv.setRs_code(rs_info.getRs_code());
			priv.setRs_cn_name(rs_info.getRs_cn_name());
			priv.setSuper_rs_code(rs_info.getSuper_rs_code());
			priv.setOpen_type(rs_info.getOpen_type());
			rs_priv.add(priv);
		}
		List<RsResInfo> temp_priv=daoService.getTempRspriv(user_id);
		for(RsResInfo rs_info : temp_priv){
			RsPrivBean priv=new RsPrivBean();
			priv.setRs_code(rs_info.getRs_code());
			priv.setRs_cn_name(rs_info.getRs_cn_name());
			priv.setSuper_rs_code(rs_info.getSuper_rs_code());
			priv.setOpen_type(rs_info.getOpen_type());
			rs_priv.add(priv);
		}
		return rs_priv;
	}


	/** 
	 * Description: 查询用户的操作权限
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptPrivByUser(String user_id) {
		return daoService.queryOptPrivByUser(user_id);
	}

	/** 
	 * Description: 根据用户查询部门角色资源权限
	 * @param user_id
	 * @return 
	 */
	public List<RsPrivBean> queryDprlRsPrivByUserId(String user_id) {
		return daoService.queryDprlRsPrivByUserId(user_id);
	}

	/** 
	 * Description: 根据用户查询部门角色操作权限
	 * @param user_id
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryDprlOptPrivByUser(String user_id) {
		return daoService.queryDprlOptPrivByUser(user_id);
	}

	/** 
	 * Description: 根据用户查询部门角色数据源权限
	 * @param user_id
	 * @return 
	 */
	public List<SocPrivBean> getDprlSocPrivByUser(String user_id) {
		return daoService.getDprlSocPrivByUser(user_id);
	}

	/** 
	 * Description: 获取用户一级导航权限
	 * @param user_id
	 * @return 
	 */
	public List<RsResInfo> getFirstNavigatePriv(String user_id,JaDateTime dt_datetime) {
		List<RsResInfo> result_list=new ArrayList<RsResInfo>();
		List<RsResInfo> nav_list=rsResDaoService.getFirstNavigate();
		
		for(RsResInfo rsResInfo:nav_list){
			if(daoService.hasRsPriv(user_id,rsResInfo.getRs_code(),dt_datetime)){
				result_list.add(rsResInfo);
			}
		}
		return result_list;
	}
	
	/** 
	 * Description: 获取所有一级导航权限
	 * @param 
	 * @return 
	 */
	public List<RsResInfo> getAllFirstNavigatePriv() {
		return rsResDaoService.getFirstNavigate();
	}

	/** 
	 * Description: 查询所有该用户的临时资源权限
	 * @param user_id
	 * @return 
	 */
	public List<TempRsBean> getTempUsUserRsPriv(String user_id) {
		return daoService.getTempUsUserRsPriv(user_id);
	}

}
