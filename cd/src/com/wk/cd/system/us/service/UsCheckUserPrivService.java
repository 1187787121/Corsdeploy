/**
 * Title: CheckUserPrivService.java
 * File Description: �û�Ȩ�޼�����
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

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserPrivDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description:�û�Ȩ�޼�����
 * @author link
 */
public class UsCheckUserPrivService {

	@Inject
	private UsGetUserPrivService daoService;
	@Inject
	private UsUserPrivDaoService daoPrivService;
	@Inject
	private DpPublicService checkPrivService;
	@Inject
	private UsUserGetRoleDeptService getDeptService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private RsPublicService rssrv;
	@Inject
	private DtSocService dtsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �û���ԴȨ�޼��
	 * @param user_id
	 * @param rs_code
	 */
	public void checkUserRsPriv(String user_id, String rs_code,JaDateTime dt_datetime) {
		if (!Assert.isEmpty(user_id) && !Assert.isEmpty(rs_code)) {
			if (!daoService.hasRsPriv(user_id,rs_code,dt_datetime)) {
				logger.debug("�û�[{}]û����Դ[{}]Ȩ��", user_id, rs_code);
				UsUserInfo userInfo=new UsUserInfo();
				userInfo.setUser_id(user_id);
				throw new IllegalOperaterException().addScene("PARM1",
						"�û�" + usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
						.addScene("PARM2", "��Դ  " + rssrv.getResInfo(rs_code).getRs_bk_desc() + "[" + rs_code + "]");
			}
		}
		logger.plog("checkUserRsPriv");
	}

	/**
	 * Description: �û�����ԴȨ�޼��
	 * @param user_id
	 * @param soc_name
	 */
	public void checkUserSocPriv(String user_id, String soc_name,JaDateTime dt_datetime) {
		if (!Assert.isEmpty(user_id) && !Assert.isEmpty(soc_name)) {
			if (!daoService.hasSocPriv(user_id,soc_name,dt_datetime)) {
				UsUserInfo userInfo=new UsUserInfo();
				userInfo.setUser_id(user_id);
				logger.debug("�û�[{}]û������Դ[{}]Ȩ��", user_id, soc_name);
				throw new IllegalOperaterException().addScene("PARM1",
						"�û�" + usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
						.addScene("PARM2", "����Դ" + dtsrv.querySocDetailBySocName(soc_name).getSoc_bk_desc() + "[" + soc_name + "]");
			}
		}
	}

	/**
	 * Description: ����û���SQL����Ȩ��
	 * @param user_id
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 */
	public void checkUserSqlPriv(String user_id, String soc_name,
			String tbl_name) {
		Assert.assertNotEmpty(user_id, "�û���");
		Assert.assertNotEmpty(soc_name, "����Դ����");
		Assert.assertNotEmpty(tbl_name, "����");
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptSqlPrivState(dept_list, soc_name, tbl_name);

		Map<String, String> user_sql_map = new HashMap<String, String>();
		user_sql_map = usGetUserPrivService.getUserSqlPrivMap(user_id);

		List<String> test_sql_priv = new ArrayList<String>();
		test_sql_priv.add(soc_name);
		test_sql_priv.add(tbl_name);
		String temp_key = test_sql_priv.toString();
		logger.debug("key=[{}],value=[{}]", temp_key, user_sql_map
				.get(temp_key));
		if (user_sql_map.get(temp_key) == null) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("PARM1",
					"�û�" + usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]").addScene("PARM2", "SQLȨ��" + temp_key);
		}
	}

	/**
	 * Description: ����û���COL����Ȩ��
	 * @param user_id
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	public void checkUserColPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		Assert.assertNotEmpty(user_id, "�û���");
		Assert.assertNotEmpty(soc_name, "����Դ����");
		Assert.assertNotEmpty(tbl_name, "����");
		Assert.assertNotEmpty(col_name, "�ֶ���");
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptColPrivState(dept_list, soc_name, tbl_name,
				col_name);
		Map<String, String> user_col_map = new HashMap<String, String>();
		user_col_map = usGetUserPrivService.getUserColPrivMap(user_id);

		List<String> test_col_priv = new ArrayList<String>();
		test_col_priv.add(soc_name);
		test_col_priv.add(tbl_name);
		test_col_priv.add(col_name);
		String temp_key = test_col_priv.toString();
		// logger.debug("key=[{}],value=[{}]", temp_key,
		// user_col_map.get(temp_key));
		if (user_col_map.get(temp_key) == null) {
			throw new IllegalOperaterException().addScene("PARM1", "�û�")
					.addScene("PARM2", "COLȨ��");
		}
	}

	/**
	 * Description: ��ѯ�û���ĳ����Ĳ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	public int[] checkTabPriv(String user_id, String soc_name, String tbl_name) {
		int priv[] = { 0, 0, 0, 0 }; // ӵ��Ȩ��Ϊ1��û��Ȩ��Ϊ0
		List<UsUserPrivBean> tbl_role_sql_list = new ArrayList<UsUserPrivBean>();
		List<UsUserPrivBean> tbl_user_sql_list = new ArrayList<UsUserPrivBean>();
		tbl_role_sql_list = daoPrivService.queryTblPrivFromRole(soc_name,
				tbl_name, user_id);
		tbl_user_sql_list = daoPrivService.queryTblPrivFromUser(soc_name,
				tbl_name, user_id);

		if (tbl_user_sql_list.size() != 0) {
			for (UsUserPrivBean info : tbl_user_sql_list) {
				if (info.getAf_flag() == AF_FLAG.FORBID) {
					info.setIns_priv_flag(PRIV_FLAG.NO);
					info.setDel_priv_flag(PRIV_FLAG.NO);
					info.setUpd_priv_flag(PRIV_FLAG.NO);
					info.setSel_priv_flag(PRIV_FLAG.NO);
				}
				priv[0] = priv[0] + (info.getIns_priv_flag().getValue() % 2);
				priv[1] = priv[1] + (info.getDel_priv_flag().getValue() % 2);
				priv[2] = priv[2] + (info.getUpd_priv_flag().getValue() % 2);
				priv[3] = priv[3] + (info.getSel_priv_flag().getValue() % 2);
			}
			return priv;
		} else if (tbl_role_sql_list.size() != 0) {
			for (UsUserPrivBean info : tbl_role_sql_list) {
				priv[0] = priv[0] + (info.getIns_priv_flag().getValue() % 2);
				priv[1] = priv[1] + (info.getDel_priv_flag().getValue() % 2);
				priv[2] = priv[2] + (info.getUpd_priv_flag().getValue() % 2);
				priv[3] = priv[3] + (info.getSel_priv_flag().getValue() % 2);
			}
			return priv;
		} else {
			priv[0] = 1;
			priv[1] = 1;
			priv[2] = 1;
			priv[3] = 1;
			return priv;
		}
	}

	/**
	 * Description: ����û��Ա�Ĳ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	public void checkTabInsPriv(String user_id, String soc_name, String tbl_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptTabInsPriv(dept_list, soc_name, tbl_name);
		int[] temp_priv = new int[4];
		temp_priv = checkTabPriv(user_id, soc_name, tbl_name);
		if (temp_priv[0] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name);
		}
	}

	/**
	 * Description: ����û��Ա��ɾ��Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	public void checkTabDelPriv(String user_id, String soc_name, String tbl_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptTabDelPriv(dept_list, soc_name, tbl_name);
		int[] temp_priv = new int[4];
		temp_priv = checkTabPriv(user_id, soc_name, tbl_name);
		if (temp_priv[1] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name);
		}
	}

	/**
	 * Description: ����û��Ա�ĸ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	public void checkTabUpdPriv(String user_id, String soc_name, String tbl_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptTabUpdPriv(dept_list, soc_name, tbl_name);
		int[] temp_priv = new int[4];
		temp_priv = checkTabPriv(user_id, soc_name, tbl_name);
		if (temp_priv[2] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name);
		}
	}

	/**
	 * Description: ����û��Ա�Ĳ�ѯȨ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	public void checkTabSelPriv(String user_id, String soc_name, String tbl_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptTabSelPriv(dept_list, soc_name, tbl_name);
		int[] temp_priv = new int[4];
		temp_priv = checkTabPriv(user_id, soc_name, tbl_name);
		if (temp_priv[3] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name);
		}
	}

	/**
	 * Description: ����û��Ƿ��б�Ĳ�ѯȨ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 */
	public boolean isTabSelPriv(String user_id, String soc_name, String tbl_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.isDeptTabSelPriv(dept_list, soc_name, tbl_name);
		int[] temp_priv = new int[4];
		temp_priv = checkTabPriv(user_id, soc_name, tbl_name);
		if (temp_priv[3] == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Description: ��ѯ�û���ĳ�����ĳ���ֶεĲ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public int[] checkColPriv(String user_id, String soc_name, String tbl_name,
			String col_name) {
		int priv[] = { 0, 0, 0, 0 }; // ӵ��Ȩ��Ϊ1��û��Ȩ��Ϊ0
		List<UsUserPrivBean> col_role_sql_list = new ArrayList<UsUserPrivBean>();
		List<UsUserPrivBean> col_user_sql_list = new ArrayList<UsUserPrivBean>();
		col_role_sql_list = daoPrivService.queryColPrivFromRole(soc_name,
				tbl_name, col_name, user_id);
		col_user_sql_list = daoPrivService.queryColPrivFromUser(soc_name,
				tbl_name, col_name, user_id);

		if (col_user_sql_list.size() != 0) {
			for (UsUserPrivBean info : col_user_sql_list) {
				if (info.getAf_flag() == AF_FLAG.FORBID) {
					info.setIns_priv_flag(PRIV_FLAG.NO);
					info.setDel_priv_flag(PRIV_FLAG.NO);
					info.setUpd_priv_flag(PRIV_FLAG.NO);
					info.setSel_priv_flag(PRIV_FLAG.NO);
				}
				priv[0] = priv[0] + (info.getIns_priv_flag().getValue() % 2);
				priv[1] = priv[1] + (info.getDel_priv_flag().getValue() % 2);
				priv[2] = priv[2] + (info.getUpd_priv_flag().getValue() % 2);
				priv[3] = priv[3] + (info.getSel_priv_flag().getValue() % 2);
			}
			return priv;
		} else if (col_role_sql_list.size() != 0) {
			for (UsUserPrivBean info : col_role_sql_list) {
				priv[0] = priv[0] + (info.getIns_priv_flag().getValue() % 2);
				priv[1] = priv[1] + (info.getDel_priv_flag().getValue() % 2);
				priv[2] = priv[2] + (info.getUpd_priv_flag().getValue() % 2);
				priv[3] = priv[3] + (info.getSel_priv_flag().getValue() % 2);
			}
			return priv;
		} else {
			priv[0] = 1;
			priv[1] = 1;
			priv[2] = 1;
			priv[3] = 1;
			return priv;
		}
	}

	/**
	 * Description: ����û����ֶεĲ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public void checkColInsPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		int[] temp_priv = new int[4];
		temp_priv = checkColPriv(user_id, soc_name, tbl_name, col_name);
		if (temp_priv[0] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name).addScene("FIELD", col_name);
		}
	}

	/**
	 * Description: ����û����ֶε�ɾ��Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public void checkColDelPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		int[] temp_priv = new int[4];
		temp_priv = checkColPriv(user_id, soc_name, tbl_name, col_name);
		if (temp_priv[1] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name).addScene("FIELD", col_name);
		}
	}

	/**
	 * Description: ����û����ֶεĸ���Ȩ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public void checkColUpdPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		checkPrivService.checkDeptColUpdPriv(dept_list, soc_name, tbl_name,
				col_name);
		int[] temp_priv = new int[4];
		temp_priv = checkColPriv(user_id, soc_name, tbl_name, col_name);
		if (temp_priv[2] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name).addScene("FIELD", col_name);
		}
	}

	/**
	 * Description: ����û����ֶεĲ�ѯȨ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public void checkColSelPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		int[] temp_priv = new int[4];
		temp_priv = checkColPriv(user_id, soc_name, tbl_name, col_name);
		if (temp_priv[3] == 0) {
			UsUserInfo userInfo=new UsUserInfo();
			userInfo.setUser_id(user_id);
			throw new IllegalOperaterException().addScene("USER_ID", usUserDaoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
					.addScene("TBL_NAME", tbl_name).addScene("FIELD", col_name);
		}
	}

	/**
	 * Description: ����û����ֶ��Ƿ��в�ѯȨ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	public boolean isColSelPriv(String user_id, String soc_name,
			String tbl_name, String col_name) {
		List<String> dept_list = new ArrayList<String>();
		dept_list = getDeptService.queryDeptByUserId(user_id);
		if (checkPrivService.isDeptColSelPriv(dept_list, soc_name, tbl_name,
				col_name) == false) {
			return false;
		}
		int[] temp_priv = new int[4];
		temp_priv = checkColPriv(user_id, soc_name, tbl_name, col_name);
		if (temp_priv[3] == 0) {
			return false;
		}
		return true;
	}
}
