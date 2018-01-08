/**
 * Title: UsUserPrivDaoService.java
 * File Description: ��ѯ�û�ӵ�е�Ȩ��
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
 * Class Description: ��ѯ�û�ӵ�е�Ȩ��
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
	 * Description: �����û�����ѯ�û�ӵ�е���ԴȨ��
	 * @param String �����������
	 * @param user_id �����û���
	 * @return ��Դ�����б�
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
	 * Description: ��ȡ�û���ԴȨ�ޣ����У�����+��ʱ��δ����
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
				// /�û��������Դ�Ѿ���ӵ�������У������������
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
	 * Description: ��ȡ�û���ԴȨ�ޣ����ã�
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
				// /�û��������Դ�Ѿ���ӵ�������У������������
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
					PRIV_TYPE.PERPETUAL);// �û�����Ȩ��
			PRIV_TYPE type = PRIV_TYPE.TEMPORARY;
			if (rsInfo.getRs_level() == 0 || rsInfo.getRs_level() == 2) {
				type = PRIV_TYPE.TEMPPAGE;
			}
			UsUserRsPrivInfo tempinfo = rsPrivDao.get(user_id, rs_code, type);// �û���ʱȨ��Ȩ��

			if (!Assert.isEmpty(turalinfo)
					&& turalinfo.getAf_flag() == AF_FLAG.ALLOW) {// �û���Ȩ��
				return true;
			} else if (!Assert.isEmpty(tempinfo)
					&& tempinfo.getAf_flag() == AF_FLAG.ALLOW
					&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
					&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
				return true;
			} else if (dao.countDprlRsPriv(user_id, rs_code) != 0
					&& Assert.isEmpty(turalinfo)) {// ���Ž�ɫ��Ȩ��
				return true;
			}
		}
		logger.plog("hasRsPriv end get");
		return false;
	}

	/**
	 * Description: �����û�����ѯ�û�ӵ�е���ʱ��ԴȨ��
	 * @param String �����������
	 * @param user_id �����û���
	 * @return ��Դ�����б�
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
	 * Description: �����û�����ѯ�û�ӵ�е�����ԴȨ��
	 * @param String �����������
	 * @param user_id �����û���
	 * @return ����Դ�����б�
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
	 * Description: �����û�����ѯ�û�ӵ�е���ʱ����ԴȨ��
	 * @param String �����������
	 * @param user_id �����û���
	 * @return ����Դ�����б�
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
		// �û���������Ȩ�޼�¼
		if (!Assert.isEmpty(prepinfo) && prepinfo.getAf_flag() == AF_FLAG.ALLOW) {
			logger.debug("$$$$�û���������Ȩ�޼�¼$$$$");
			return true;
		}
		UsUserSocPrivInfo tempinfo = socPrivDao.get(user_id, soc_name,
				PRIV_TYPE.TEMPORARY);
		// �û�������ʱȨ����Ч��¼
		if (!Assert.isEmpty(tempinfo)
				&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
				&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
			logger.debug("<<<<<�û�������ʱȨ����Ч��¼>>>>");
			return true;
		}
		// �û����������ý�ֹ��¼�������Ž�ɫ��Ȩ��
		int count = dao.countDprlSocPriv(user_id, soc_name);
		if (count != 0
				&& (Assert.isEmpty(prepinfo) || (!Assert.isEmpty(prepinfo) && prepinfo
						.getAf_flag() != AF_FLAG.FORBID))) {
			logger.debug("<<<<<�û����������ý�ֹ��¼�������Ž�ɫ��Ȩ��>>>>");
			return true;
		}
		return false;
	}

	/**
	 * Description: �ж��û����ڱ��Ƿ��ж�Ӧ���͵Ĳ���Ȩ��
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
		// �û���������Ȩ�޼�¼
		if (!Assert.isEmpty(prepinfo) && prepinfo.getAf_flag() == AF_FLAG.ALLOW) {
			logger.debug("�û�[{}]����[{}]����Ȩ�޼�¼",user_id,table_name);
			logger.debug("�����ͣ�[{}],��������[{}]",sql_type,sql_type.getCname());
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
				logger.debug("�û�[{}]����[{}]�����ò�ѯȨ�޼�¼",user_id,table_name);
				return true;
			}
		}
		UsUserSqlPrivInfo tempinfo = sqlPrivService.getInfoByKey(user_id,
				soc_name, table_name, PRIV_TYPE.TEMPORARY);
		// �û�������ʱȨ����Ч��¼
		if (!Assert.isEmpty(tempinfo)
				&& dt_datetime.isBefor(tempinfo.getTempend_bk_datetime())
				&& dt_datetime.isAfter(tempinfo.getTempstart_bk_datetime())) {
			logger.debug("�û�[{}]����[{}]��ʱȨ����Ч��¼",user_id,table_name);
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
		// �û����������ý�ֹ��¼�������Ž�ɫ��Ȩ��
		UsRoleSqlPrivInfo roleinfo = dao.getRoleSqlPrivInfo(user_id, soc_name,
				table_name);
		if (!Assert.isEmpty(roleinfo)
				&& (Assert.isEmpty(prepinfo) || (!Assert.isEmpty(prepinfo) && prepinfo
						.getAf_flag() != AF_FLAG.FORBID))) {
			logger.debug("�û�[{}]����[{}]���Ž�ɫ��Ȩ��",user_id,table_name);
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
	 * Description: �ж��û��������Ƿ��ж�Ӧ���͵Ĳ���Ȩ��
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
		logger.debug("�û�����Ȩ��ʵ��[{}]",prepinfo);
		// �û���������Ȩ�޼�¼
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

		logger.debug("��ʱȨ��ʵ��[{}]",tempinfo);
		// �û�������ʱȨ����Ч��¼
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

		
		// �û����������ý�ֹ��¼�������Ž�ɫ��Ȩ��
		UsRoleColPrivInfo roleinfo = dao.getRoleColPrivInfo(user_id, soc_name,
				table_name, col_name);
		logger.debug("�û����������ý�ֹ��¼ʵ��[{}]",roleinfo);
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
	 * Description: �����û�����ѯ�û�ӵ�е�SQLȨ��
	 * @param String �����������
	 * @param user_id �����û���
	 * @return SQLȨ���б�
	 */
	public List<UsUserPrivBean> getSqlPriv(String user_id) {
		List<UsUserPrivBean> sql_info_list = new ArrayList<UsUserPrivBean>();
		sql_info_list = dao.getSqlPriv(user_id, user_id);
		return sql_info_list;
	}

	/**
	 * Description: �����û�����ѯ�û�ӵ�е�COLȨ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> getColPriv(String user_id) {
		List<UsUserPrivBean> col_info_list = new ArrayList<UsUserPrivBean>();
		col_info_list = dao.getColPriv(user_id, user_id);
		return col_info_list;
	}

	/**
	 * Description: ��ⲿ�Ž�ɫSQLȨ�ޱ����Ƿ���ڸ�Ȩ�ޣ��Ѿ�ȥ���û�SQLȨ�ޱ���ɾ���Ĳ��֣�
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
	 * Description: ��ⲿ�Ž�ɫCOLȨ�ޱ����Ƿ���ڸ�Ȩ�ޣ��Ѿ�ȥ���û�COLȨ�ޱ���ɾ���Ĳ��֣�
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
	 * Description: �Ӳ��Ž�ɫ��ϢSQL����Ȩ�ޱ��в�ѯĳ���û���������Դ�µ�ĳ�ű��Ȩ��
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
	 * Description: ���û�SQL����Ȩ�ޱ��в�ѯĳ���û���������Դ�µ�ĳ�ű�Ĳ���Ȩ��
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
	 * Description: �Ӳ��Ž�ɫ��ϢCOL����Ȩ�ޱ��в�ѯĳ���û���������Դ�µ�ĳ�ű��ĳ���ֶε�Ȩ��
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
	 * Description: ���û�COL����Ȩ�ޱ��в�ѯĳ���û���������Դ�µ�ĳ�ű��ĳ���ֶεĲ���Ȩ��
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
	 * Description: ��ѯ�û�����ֹ��SQL����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfSqlPriv(String user_id) {
		return dao.queryUserAfSqlPriv(user_id);
	}

	/**
	 * Description: ��ѯ�û�����ֹ��COL����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfColPriv(String user_id) {
		return dao.queryUserAfColPriv(user_id);
	}

	/**
	 * Description: �����û�ID�б����û���Դ�б�
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> iteratorRsInfos(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.iteratorRsInfosByIds(user_ids_str);
	}

	/**
	 * Description: �����û�ID�б����û�����Դ�б�
	 * @param dept_ids
	 * @return
	 */

	public List<String> querySocInfo(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.querySocByIds(user_ids_str);
	}

	/**
	 * Description: �����û�ID�б����û������б�
	 * @param dept_ids
	 * @return
	 */
	public DBIterator<String> querySrvInfo(List<String> user_ids) {
		String user_ids_str = getStringByList(user_ids);
		return dao.iteratorSvInfosByIds(user_ids_str);
	}

	/**
	 * �������Listת��Ϊ�ַ���
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
	 * Description: ��ȡ�û�sqlȨ��
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
	 * Description: �����û�����ȡ�û�COLȨ������
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
	 * Description: ��ѯ�û��Ĳ���Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserOptPrivInfo> queryOptPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv = new ArrayList<UsUserOptPrivInfo>();
		List<String> dprl_list = usUserRoleDaoService
				.queryDprlByUserId(user_id);

		// ȡ���Ž�ɫ��Ȩ��
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

		// ȡ�û�Ȩ��
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
	 * Description: �����û���ѯ���Ž�ɫ��ԴȨ��
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
	 * Description: �����û���ѯ���Ž�ɫ����Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<UsUserOptPrivInfo> queryDprlOptPrivByUser(String user_id) {
		List<UsUserOptPrivInfo> opt_priv = new ArrayList<UsUserOptPrivInfo>();
		List<String> dprl_list = usUserRoleDaoService
				.queryDprlByUserId(user_id);

		// ȡ���Ž�ɫ��Ȩ��
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
	 * Description: �����û���ѯ���Ž�ɫ����ԴȨ��
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
	 * Description: �û��Բ��� �Ƿ��в���Ȩ��
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
		if (open_type == OPEN_TYPE.ALLOW) {// ��������
			int dprl_forbid_size = dao
					.countDprlOptforbidSize(user_id, opt_code);
			if (size == dprl_forbid_size) {// ���Ž�ɫ��ֹ
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.YES) {
					return true;
				} else {
					return false;
				}
			} else {// ���Ž�ɫ����
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.NO) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			int dprl_allow_size = dao.countDprlOptAllowSize(user_id, opt_code);
			if (dprl_allow_size > 0) {// ���Ž�ɫ����
				if (!Assert.isEmpty(us_priv)
						&& us_priv.getPriv_flag() == PRIV_FLAG.NO) {
					return false;
				} else {
					return true;
				}
			} else {// ���Ž�ɫ��ֹ
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
	 * Description:��ѯ���и��û�����ʱ��ԴȨ��
	 * @param user_id
	 * @return
	 */
	public List<TempRsBean> getTempUsUserRsPriv(String user_id) {
		return rsPrivDao.getTempUsUserRsPriv(user_id);
	}
}
