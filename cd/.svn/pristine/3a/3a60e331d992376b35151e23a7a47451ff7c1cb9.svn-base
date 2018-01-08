/**
 * Title: DpPublicService.java
 * File Description: 部门模块公共服务
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.system.dp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.bean.DeptExtendsBean;
import com.wk.cd.system.dp.dao.DeptQuery;
import com.wk.cd.system.dp.dao.DpDeptColPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.dao.DpDeptOptPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptRsPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptSocPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptSqlPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.dp.info.DpDeptRsPrivInfo;
import com.wk.cd.system.dp.info.DpDeptSocPrivInfo;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.dao.UsRoleOptPrivDaoService;
import com.wk.cd.system.us.dao.UsUserOptPrivDaoService;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 部门模块公共服务
 * @author xuy
 */
public class DpPublicService {
	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private DpDeptRsPrivDaoService deptRsPrivDaoService;
	@Inject
	private DpDeptSocPrivDaoService deptSocPrivDaoService;
	@Inject
	private DpDeptSqlPrivDaoService deptSqlPrivDaoService;
	@Inject
	private DpDeptColPrivDaoService deptColPrivDaoService;
	@Inject
	private DpDeptOptPrivDaoService dpDeptOptPrivDaoService;
	@Inject
	private UsRoleOptPrivDaoService usRoleOptPrivDaoService;
	@Inject
	private UsUserOptPrivDaoService usUserOptPrivDaoService;
	@Inject
	private DeptQuery deptQuery;
	@Inject
	private RsResDaoService rsResDaoService;
	
	/**
	 * 
	 * Description: 根据用户名查询所属部门信息
	 * @param user_id
	 * @return
	 */
	public DpDeptInfo getDeptInfoByUserId(String user_id) {
		return deptDaoService.getDeptInfoByUserId(user_id);
	}

	public String getDeptCnameBeyKey(String dept_id){
		return deptDaoService.getDeptCnameByKey(dept_id);
	}

	public DpDeptInfo getInfoByKey(String dept_id){
		return deptDaoService.getInfoByKey(dept_id);
	}

	/**
	 * 
	 * Description: 检查部门名称是否重复，部门简称或者部门全称有重复都报错
	 * @param dept_cn_name
	 */
	public void checkDeptNameRepeat(String dept_id, String dept_cn_name, String dept_full_cname) {
		if (deptDaoService.countDeptByDeptCnNames(dept_id, dept_cn_name)>0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					DpDeptInfo.TABLECN).addScene("FIELD", "部门简称为" + dept_cn_name);
		}
		if(deptDaoService.countDeptByDeptFullNames(dept_id, dept_full_cname)>0){
			throw new RecordAlreadyExistException().addScene("TABLE",
					DpDeptInfo.TABLECN).addScene("FIELD", "部门全称为" + dept_full_cname);
		}
	}

	/**
	 * 
	 * Description: 检查部门状态
	 * @param dept_id
	 */
	public void checkDeptState(String dept_id) {
		DpDeptInfo deptInfo = new DpDeptInfo();
		deptInfo.setDept_id(dept_id);
		deptDaoService.getInfoByKey(deptInfo);
	}

	/**
	 * 
	 * Description: 检查部门资源权限表记录状态
	 * @param dept_id
	 * @param rs_code
	 */
	public void checkDeptRsPrivState(String dept_id, String rs_code) {
		RsResInfo rsInfo=new RsResInfo();
		rsInfo.setRs_code(rs_code);;
		rsInfo=rsResDaoService.getInfoByKey(rsInfo);
		if(!Assert.isEmpty(rsInfo)&&rsInfo.getPublic_yn_flag()==YN_FLAG.NO){
			DpDeptRsPrivInfo deptRsPrivInfo = new DpDeptRsPrivInfo();
			deptRsPrivInfo.setDept_id(dept_id);
			deptRsPrivInfo.setRs_code(rs_code);
			deptRsPrivDaoService.getInfoByKey(deptRsPrivInfo);
		}
	}

	/**
	 * 
	 * Description: 检查多部门资源权限表记录状态
	 * @param dept_id
	 * @param rs_code
	 */
	public void checkDeptRsPrivState(List<String> dept_ids, String rs_code) {
		int count = 0;
		for (String dept_id : dept_ids) {
			DpDeptRsPrivInfo deptRsPrivInfo = new DpDeptRsPrivInfo();
			deptRsPrivInfo.setDept_id(dept_id);
			deptRsPrivInfo.setRs_code(rs_code);
			int i = deptRsPrivDaoService
					.getInfoByKeyWithoutException(deptRsPrivInfo);
			if (i != 0) {
				count++;
			}
		}
		if (count == 0) {
			throw new IllegalOperaterException().addScene("PARM1",
					"用户所属部门").addScene("PARM2",
					"资源" + rsResDaoService.getInfoByCode(rs_code).getRs_cn_name()+
					"["+rs_code+"]");
		}
	}

	/**
	 * 
	 * Description: 检查部门数据源权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 */
	public void checkDeptSocPrivState(String dept_id, String soc_name) {
		DpDeptSocPrivInfo deptSocPrivInfo = new DpDeptSocPrivInfo();
		deptSocPrivInfo.setDept_id(dept_id);
		deptSocPrivInfo.setSoc_name(soc_name);
		deptSocPrivDaoService.getInfoByKey(deptSocPrivInfo);
	}

	/**
	 * 
	 * Description: 检查多部门数据源权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 */
	public void checkDeptSocPrivState(List<String> dept_ids, String soc_name) {
		int count = 0;
		for (String dept_id : dept_ids) {
			DpDeptSocPrivInfo deptSocPrivInfo = new DpDeptSocPrivInfo();
			deptSocPrivInfo.setDept_id(dept_id);
			deptSocPrivInfo.setSoc_name(soc_name);
			int i = deptSocPrivDaoService
					.getInfoByKeyWithoutException(deptSocPrivInfo);
			if (i != 0) {
				count++;
			}
		}
		if (count == 0) {
			throw new IllegalOperaterException().addScene("PARM1",
					"机构" + dept_ids.toString()).addScene("PARM2",
					"数据源" + soc_name);
		}
	}

	/**
	 * 
	 * Description: 检查部门SQL权限表记录
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptSqlPrivState(String dept_id, String soc_name,
			String tbl_name) {
		DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
		deptSqlPrivInfo.setDept_id(dept_id);
		deptSqlPrivInfo.setSoc_name(soc_name);
		deptSqlPrivInfo.setTbl_name(tbl_name);
		DpDeptSqlPrivInfo info = deptSqlPrivDaoService
				.getInfoByKey(deptSqlPrivInfo);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptSqlPrivInfo.TABLECN).addScene("FIELD", dept_id);
		}
	}

	/**
	 * 
	 * Description: 检查部门SQL权限表记录
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 */
	public int checkDeptSqlPriv(String dept_id, String soc_name, String tbl_name) {
		DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
		deptSqlPrivInfo.setDept_id(dept_id);
		deptSqlPrivInfo.setSoc_name(soc_name);
		deptSqlPrivInfo.setTbl_name(tbl_name);
		DpDeptSqlPrivInfo info = deptSqlPrivDaoService
				.getInfoByKey(deptSqlPrivInfo);
		if (Assert.isEmpty(info)) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptSqlPrivState(List<String> dept_ids, String soc_name,
			String tbl_name) {
		if (dept_ids.size() != 0) {
			int count = 0;
			for (String dept_id : dept_ids) {
				DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
				deptSqlPrivInfo.setDept_id(dept_id);
				deptSqlPrivInfo.setSoc_name(soc_name);
				deptSqlPrivInfo.setTbl_name(tbl_name);
				deptSqlPrivInfo = deptSqlPrivDaoService
						.getInfoByKeyWithoutException(deptSqlPrivInfo);
				if (Assert.isEmpty(deptSqlPrivInfo)) {
					count++;
					continue;
				}
			}
			if (count == dept_ids.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_ids.toString()).addScene(
						"PARM2",
						DpDeptSqlPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptSqlPrivInfo.TBL_NAMECN + tbl_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表Ins状态
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptTabInsPriv(List<String> dept_list, String soc_name,
			String tbl_name) {
		if (dept_list.size() != 0) {
			int count = 0;
			for (String dept_id : dept_list) {
				DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
				deptSqlPrivInfo.setDept_id(dept_id);
				deptSqlPrivInfo.setSoc_name(soc_name);
				deptSqlPrivInfo.setTbl_name(tbl_name);
				deptSqlPrivInfo = deptSqlPrivDaoService
						.getInfoByKeyWithoutException(deptSqlPrivInfo);
				if (Assert.isEmpty(deptSqlPrivInfo)) {
					continue;
				}
				if (deptSqlPrivInfo.getIns_priv_flag() == PRIV_FLAG.NO) {
					count++;
				}
			}
			if (count == dept_list.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_list.toString()).addScene(
						"PARM2",
						DpDeptSqlPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptSqlPrivInfo.TBL_NAMECN + tbl_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表Del状态
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptTabDelPriv(List<String> dept_list, String soc_name,
			String tbl_name) {
		if (dept_list.size() != 0) {
			int count = 0;
			for (String dept_id : dept_list) {
				DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
				deptSqlPrivInfo.setDept_id(dept_id);
				deptSqlPrivInfo.setSoc_name(soc_name);
				deptSqlPrivInfo.setTbl_name(tbl_name);
				deptSqlPrivInfo = deptSqlPrivDaoService
						.getInfoByKeyWithoutException(deptSqlPrivInfo);
				if (Assert.isEmpty(deptSqlPrivInfo)) {
					continue;
				}
				if (deptSqlPrivInfo.getDel_priv_flag() == PRIV_FLAG.NO) {
					count++;
				}
			}
			if (count == dept_list.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_list.toString()).addScene(
						"PARM2",
						DpDeptSqlPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptSqlPrivInfo.TBL_NAMECN + tbl_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表Upd状态
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptTabUpdPriv(List<String> dept_list, String soc_name,
			String tbl_name) {
		if (dept_list.size() != 0) {
			int count = 0;
			for (String dept_id : dept_list) {
				DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
				deptSqlPrivInfo.setDept_id(dept_id);
				deptSqlPrivInfo.setSoc_name(soc_name);
				deptSqlPrivInfo.setTbl_name(tbl_name);
				deptSqlPrivInfo = deptSqlPrivDaoService
						.getInfoByKeyWithoutException(deptSqlPrivInfo);
				if (Assert.isEmpty(deptSqlPrivInfo)) {
					continue;
				}
				if (deptSqlPrivInfo.getUpd_priv_flag() == PRIV_FLAG.NO) {
					count++;
				}
			}
			if (count == dept_list.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_list.toString()).addScene(
						"PARM2",
						DpDeptSqlPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptSqlPrivInfo.TBL_NAMECN + tbl_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表Sel状态
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptTabSelPriv(List<String> dept_list, String soc_name,
			String tbl_name) {
		if (dept_list.size() != 0) {
			int count = 0;
			for (String dept_id : dept_list) {
				DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
				deptSqlPrivInfo.setDept_id(dept_id);
				deptSqlPrivInfo.setSoc_name(soc_name);
				deptSqlPrivInfo.setTbl_name(tbl_name);
				deptSqlPrivInfo = deptSqlPrivDaoService
						.getInfoByKeyWithoutException(deptSqlPrivInfo);
				if (Assert.isEmpty(deptSqlPrivInfo)) {
					continue;
				}
				if (deptSqlPrivInfo.getSel_priv_flag() == PRIV_FLAG.NO) {
					count++;
				}
			}
			if (count == dept_list.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_list.toString()).addScene(
						"PARM2",
						DpDeptSqlPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptSqlPrivInfo.TBL_NAMECN + tbl_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL权限表Sel状态是否
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public boolean isDeptTabSelPriv(List<String> dept_list, String soc_name,
			String tbl_name) {
		if (dept_list.size() == 0) {
			return false;
		}
		int count = 0;
		for (String dept_id : dept_list) {
			DpDeptSqlPrivInfo deptSqlPrivInfo = new DpDeptSqlPrivInfo();
			deptSqlPrivInfo.setDept_id(dept_id);
			deptSqlPrivInfo.setSoc_name(soc_name);
			deptSqlPrivInfo.setTbl_name(tbl_name);
			deptSqlPrivInfo = deptSqlPrivDaoService
					.getInfoByKeyWithoutException(deptSqlPrivInfo);
			if (Assert.isEmpty(deptSqlPrivInfo)) {
				continue;
			}
			if (deptSqlPrivInfo.getSel_priv_flag() == PRIV_FLAG.NO) {
				count++;
			}
		}
		if (count == dept_list.size()) {
			return false;
			// throw new IllegalOperaterException().addScene("PARM1",
			// "部门SQL权限表Sel权限").addScene("PARM2", dept_list.toString());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * Description: 检查部门SQL字段权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkDeptColPrivState(String dept_id, String soc_name,
			String tbl_name, String col_name) {
		DpDeptColPrivInfo deptColPrivInfo = new DpDeptColPrivInfo();
		deptColPrivInfo.setDept_id(dept_id);
		deptColPrivInfo.setSoc_name(soc_name);
		deptColPrivInfo.setTbl_name(tbl_name);
		deptColPrivInfo.setCol_name(col_name);
		DpDeptColPrivInfo info = deptColPrivDaoService
				.getInfoByKey(deptColPrivInfo);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					DpDeptColPrivInfo.TABLECN).addScene("FIELD", dept_id);
		}
	}

	/**
	 * 
	 * Description: 检查部门SQL字段权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public int checkDeptColPriv(String dept_id, String soc_name,
			String tbl_name, String col_name) {
		DpDeptColPrivInfo deptColPrivInfo = new DpDeptColPrivInfo();
		deptColPrivInfo.setDept_id(dept_id);
		deptColPrivInfo.setSoc_name(soc_name);
		deptColPrivInfo.setTbl_name(tbl_name);
		deptColPrivInfo.setCol_name(col_name);
		DpDeptColPrivInfo info = deptColPrivDaoService
				.getInfoByKey(deptColPrivInfo);
		if (Assert.isEmpty(info)) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL字段权限表记录状态
	 * @param dept_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkDeptColPrivState(List<String> dept_ids, String soc_name,
			String tbl_name, String col_name) {
		if (dept_ids.size() != 0) {
			int count = 0;
			for (String dept_id : dept_ids) {
				DpDeptColPrivInfo deptColPrivInfo = new DpDeptColPrivInfo();
				deptColPrivInfo.setDept_id(dept_id);
				deptColPrivInfo.setSoc_name(soc_name);
				deptColPrivInfo.setTbl_name(tbl_name);
				deptColPrivInfo.setCol_name(col_name);
				deptColPrivInfo = deptColPrivDaoService
						.getInfoByKeyWithoutException(deptColPrivInfo);
				if (Assert.isEmpty(deptColPrivInfo)) {
					count++;
					continue;
				}
			}
			if (count == dept_ids.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_ids.toString()).addScene(
						"PARM2",
						DpDeptColPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptColPrivInfo.TBL_NAMECN + tbl_name
								+ DpDeptColPrivInfo.COL_NAMECN + col_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL字段权限表Upd状态
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkDeptColUpdPriv(List<String> dept_list, String soc_name,
			String tbl_name, String col_name) {
		if (dept_list.size() != 0) {
			int count = 0;
			int abnormal = 0;
			for (String dept_id : dept_list) {
				DpDeptColPrivInfo deptColPrivInfo = new DpDeptColPrivInfo();
				deptColPrivInfo.setDept_id(dept_id);
				deptColPrivInfo.setSoc_name(soc_name);
				deptColPrivInfo.setTbl_name(tbl_name);
				deptColPrivInfo.setCol_name(col_name);
				deptColPrivInfo = deptColPrivDaoService
						.getInfoByKeyWithoutException(deptColPrivInfo);
				if (Assert.isEmpty(deptColPrivInfo)) {
					continue;
				}
				if (deptColPrivInfo.getUpd_priv_flag() == PRIV_FLAG.NO) {
					count++;
				}
			}
			if (count == dept_list.size() || abnormal == dept_list.size()) {
				throw new IllegalOperaterException().addScene("PARM1",
						dept_list.toString()).addScene(
						"PARM2",
						DpDeptColPrivInfo.SOC_NAMECN + soc_name
								+ DpDeptColPrivInfo.TBL_NAMECN + tbl_name
								+ DpDeptColPrivInfo.COL_NAMECN + col_name);
			}
		}
	}

	/**
	 * 
	 * Description: 检查多部门SQL字段权限表Sel状态是否
	 * @param dept_list
	 * @param soc_name
	 * @param tbl_name
	 */
	public boolean isDeptColSelPriv(List<String> dept_list, String soc_name,
			String tbl_name, String col_name) {
		if (dept_list.size() == 0) {
			return false;
		}
		int count = 0;
		int abnormal = 0;
		for (String dept_id : dept_list) {
			DpDeptColPrivInfo deptColPrivInfo = new DpDeptColPrivInfo();
			deptColPrivInfo.setDept_id(dept_id);
			deptColPrivInfo.setSoc_name(soc_name);
			deptColPrivInfo.setTbl_name(tbl_name);
			deptColPrivInfo.setCol_name(col_name);
			deptColPrivInfo = deptColPrivDaoService
					.getInfoByKeyWithoutException(deptColPrivInfo);
			if (deptColPrivInfo.getSel_priv_flag() == PRIV_FLAG.NO) {
				count++;
			}
		}
		if (count == dept_list.size() || abnormal == dept_list.size()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * Description: 根据部门ID获得部门资源列表
	 * @param dept_id
	 */
	public DBIterator<String> queryRsInfo(String dept_id) {
		return deptRsPrivDaoService.iteratorInfosById(dept_id);
	}

	/**
	 * 
	 * Description: 根据部门ID列表获得部门资源列表
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> iteratorRsInfos(List<String> dept_ids) {
		return deptRsPrivDaoService.iteratorInfosByIds(dept_ids);
	}

	/**
	 * 
	 * Description: 根据部门ID获得部门数据源列表
	 * @param dept_id
	 */
	public List<String> querySocInfo(String dept_id) {
		return deptSocPrivDaoService.queryInfosById(dept_id);
	}

	/**
	 * 
	 * Description: 根据部门ID列表获得部门数据源列表
	 * @param dept_ids
	 * @return
	 */

	public List<String> querySocInfo(List<String> dept_ids) {
		return deptSocPrivDaoService.queryInfosByIds(dept_ids);
	}

	/**
	 * 
	 * Description: 根据部门ID获得部门SQL权限列表
	 * @param dept_id
	 */
	public List<DpDeptSqlPrivInfo> querySqlInfo(String dept_id) {
		List<DpDeptSqlPrivInfo> sql_infos = deptSqlPrivDaoService
				.queryInfosById(dept_id);
		return sql_infos;
	}

	/**
	 * 
	 * Description: 根据部门ID列表获得部门SQL权限列表
	 * @param dept_ids
	 * @return
	 */
	public List<DpDeptSqlPrivInfo> querySqlInfo(List<String> dept_ids) {
		List<DpDeptSqlPrivInfo> sql_infos = new ArrayList<DpDeptSqlPrivInfo>();
		for (String dept_id : dept_ids) {
			sql_infos.addAll(deptSqlPrivDaoService.queryInfosById(dept_id));
		}
		return sql_infos;
	}

	/**
	 * 
	 * Description: 根据部门ID获得部门SQL字段权限列表
	 * @param dept_id
	 */
	public List<DpDeptColPrivInfo> queryColInfo(String dept_id) {
		List<DpDeptColPrivInfo> col_infos = deptColPrivDaoService
				.queryInfosById(dept_id);
		return col_infos;
	}

	/**
	 * 
	 * Description: 根据部门ID列表获得部门SQL字段权限列表
	 * @param dept_ids
	 * @return
	 */
	public List<DpDeptColPrivInfo> queryColInfo(List<String> dept_ids) {
		List<DpDeptColPrivInfo> col_infos = new ArrayList<DpDeptColPrivInfo>();
		for (String dept_id : dept_ids) {
			col_infos.addAll(deptColPrivDaoService.queryInfosById(dept_id));
		}
		return col_infos;
	}

	/**
	 * 
	 * Description: 增加部门数据源（新增数据源用）///////////////////////
	 * @param dept_id
	 * @param soc_name
	 * @return
	 */
	public int insertDeptSoc(String dept_id, String soc_name) {
		DpDeptSocPrivInfo info = new DpDeptSocPrivInfo();
		info.setDept_id(dept_id);
		info.setSoc_name(soc_name);
		return deptSocPrivDaoService.insertInfo(info);
	}

	public List<DeptExtendsBean> queryLowDeptNum() {
		DBIterator<DeptExtendsBean> infos=deptQuery.queryAllDeptInfo();
		List<DeptExtendsBean> dept_infos=new ArrayList<DeptExtendsBean>();
		try{
			while(infos.hasNext()){
				dept_infos.add(infos.next());
			}
		}finally{
			infos.close();
		}
		
		return dept_infos;
	}

	/**
	 * 根据传入的部门编码数组编码查询该部门数组中所有的下级部门（包括数组中的部门）
	 * @param dept_ids 部门编码数组
	 * @return 所有的下级部门
	 */
	public List<String> queryDeptIdByKey(String[] dept_ids) {
		return deptDaoService.queryDeptIdByKey(dept_ids);
	}
	
	/**
	 * 
	 * Description: 根据数据源名称删除部门数据源数据
	 * @param soc_name
	 * @return
	 */
	public int deleteDeptSocBySocName(String soc_name){
		return deptSocPrivDaoService.deleteInfoBySocName(soc_name);
	}
	
	/**
	 * 
	 * Description: 根据数据源名称删除部门SQL表数据源数据
	 * @param soc_name
	 * @return
	 */
	public int deleteDeptSQLBySocName(String soc_name){
		return deptSqlPrivDaoService.deleteDeptSqlBySocName(soc_name);
	}
	
	/**
	 * 
	 * Description: 根据数据源名称删除部门SQL表数据源数据
	 * @param soc_name
	 * @return
	 */
	public int deleteDeptCOLBySocName(String soc_name){
		return deptColPrivDaoService.deleteDeptColBySocName(soc_name);
	}
	
	/**
	 * Description: 查询单个部门的下级部门ID列表
	 * @param dept_id
	 * @return
	 */
	public List<String> querySubDeptIdsByDeptId(String dept_id){
		return deptDaoService.querySubDeptIdsByDeptId(dept_id);
	}
	
	/** 
	 * Description: 查询多个部门的允许资源操作
	 * @param sub_dept_ids
	 * @return 
	 */
	public List<DpDeptOptPrivInfo> queryOptAllowPrivByDepts(
			List<String> sub_dept_ids) {
		List<DpDeptOptPrivInfo> opt_priv=new ArrayList<DpDeptOptPrivInfo>();
		Map<String, DpDeptOptPrivInfo> opt_map=new HashMap<String, DpDeptOptPrivInfo>();
		for(String dept_id:sub_dept_ids){
			List<DpDeptOptPrivInfo> opt_lst=dpDeptOptPrivDaoService.queryOptAllowPrivByDept(dept_id);
			for (DpDeptOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}
	
	/** 
	 * Description: 查询多个部门的禁止资源操作
	 * @param sub_dept_ids
	 * @return 
	 */
	public List<DpDeptOptPrivInfo> queryOptForbidPrivByDepts(
			List<String> sub_dept_ids) {
		List<DpDeptOptPrivInfo> opt_priv=new ArrayList<DpDeptOptPrivInfo>();
		Map<String, DpDeptOptPrivInfo> opt_map=new HashMap<String, DpDeptOptPrivInfo>();
		for(String dept_id:sub_dept_ids){
			List<DpDeptOptPrivInfo> opt_lst=dpDeptOptPrivDaoService.queryOptForbidPrivByDept(dept_id);
			for (DpDeptOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}
	
	/** 
	 * Description: 查询多个部门角色的禁止资源操作
	 * @param dprl_list
	 * @return 
	 */
	public List<UsRoleOptPrivInfo> queryOptForbidPrivByDprls(
			List<String> dprl_list) {
		List<UsRoleOptPrivInfo> opt_priv=new ArrayList<UsRoleOptPrivInfo>();
		Map<String, UsRoleOptPrivInfo> opt_map=new HashMap<String, UsRoleOptPrivInfo>();
		for(String dprl_code:dprl_list){
			List<UsRoleOptPrivInfo> opt_lst=usRoleOptPrivDaoService.queryOptForbidPrivByDprl(dprl_code);
			for (UsRoleOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}
	
	/** 
	 * Description: 查询多个部门角色的允许资源操作
	 * @param dprl_list
	 * @return 
	 */
	public List<UsRoleOptPrivInfo> queryOptAllowPrivByDprls(
			List<String> dprl_list) {
		List<UsRoleOptPrivInfo> opt_priv=new ArrayList<UsRoleOptPrivInfo>();
		Map<String, UsRoleOptPrivInfo> opt_map=new HashMap<String, UsRoleOptPrivInfo>();
		for(String dprl_code:dprl_list){
			List<UsRoleOptPrivInfo> opt_lst=usRoleOptPrivDaoService.queryOptAllowPrivByDprl(dprl_code);
			for (UsRoleOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}
	
	/** 
	 * Description: 查询多个用户的禁止资源操作
	 * @param user_list
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptForbidPrivByUsers(
			List<String> user_list) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		Map<String, UsUserOptPrivInfo> opt_map=new HashMap<String, UsUserOptPrivInfo>();
		for(String user_id:user_list){
			List<UsUserOptPrivInfo> opt_lst=usUserOptPrivDaoService.queryOptForbidPrivByUser(user_id);
			for (UsUserOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}

	/** 
	 * Description: 查询多个用户的允许资源操作
	 * @param user_list
	 * @return 
	 */
	public List<UsUserOptPrivInfo> queryOptAllowPrivByUsers(
			List<String> user_list) {
		List<UsUserOptPrivInfo> opt_priv=new ArrayList<UsUserOptPrivInfo>();
		Map<String, UsUserOptPrivInfo> opt_map=new HashMap<String, UsUserOptPrivInfo>();
		for(String user_id:user_list){
			List<UsUserOptPrivInfo> opt_lst=usUserOptPrivDaoService.queryOptAllowPrivByUser(user_id);
			for (UsUserOptPrivInfo temp : opt_lst) {
				if(!opt_map.containsKey(temp.getOpt_code())){
					opt_map.put(temp.getOpt_code(), temp);
				}
			}
		}
		opt_priv.addAll(opt_map.values());
		return opt_priv;
	}

}
