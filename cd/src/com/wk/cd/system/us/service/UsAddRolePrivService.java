/**
 * Title: UsAddRolePrivService.java
 * File Description: ���Ž�ɫȨ�����
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.system.us.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleRsPrivInfo;
import com.wk.cd.system.us.info.UsRoleSocPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:���Ž�ɫȨ�����
 * @author link
 */
public class UsAddRolePrivService {
	@Inject
	private UsRoleRsPrivDaoService daoRsService;
	@Inject
	private UsRoleSocPrivDaoService daoSocService;
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;
	@Inject
	private UsDeptRoleDaoService daoDeptService;
	@Inject
	private DpPublicService checkPrivService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: Ϊ���Ž�ɫ�����ԴȨ��
	 * @param infos
	 */
	public void addDprlRsPriv(String dprl_code, String[] rs_list) {
		String dept_id = new String();
		dept_id = daoDeptService.getDeptByDprl(dprl_code).getDept_id();
		logger.debug("dept_id=:[{}]", dept_id);
		UsRoleRsPrivInfo temp_info = new UsRoleRsPrivInfo();
		for (String rs_code : rs_list) {
			checkPrivService.checkDeptRsPrivState(dept_id, rs_code);
			daoRsService.checkDprlRsNotExist(dprl_code, rs_code);
			// if (daoRsService.countRsByDprlCode(dprl_code, rs_code) == 1) {
			// continue;
			// }
			temp_info.setDprl_code(dprl_code);
			temp_info.setRs_code(rs_code);
			daoRsService.insertInfo(temp_info);
		}
	}

	/**
	 * Description: Ϊ���Ž�ɫ�������ԴȨ��
	 * @param infos
	 */
	public void addDprlSocPriv(String dprl_code, String[] soc_list) {
		String dept_id = new String();
		dept_id = daoDeptService.getDeptByDprl(dprl_code).getDept_id();
		UsRoleSocPrivInfo temp_info = new UsRoleSocPrivInfo();
		for (String soc_name : soc_list) {
			checkPrivService.checkDeptSocPrivState(dept_id, soc_name);
			daoSocService.checkDprlSocNotExist(dprl_code, soc_name);
			// if (daoSocService.countSocByDprlCode(dprl_code, soc_name) == 1) {
			// continue;
			// }
			temp_info.setDprl_code(dprl_code);
			temp_info.setSoc_name(soc_name);
			daoSocService.insertInfo(temp_info);
		}
	}

	/**
	 * Description: Ϊ���Ž�ɫ���SQL����Ȩ��
	 * @param infos
	 */
	public void addDprlSqlPriv(String dprl_code,List<UsRoleSqlPrivInfo> infos) {
		List<String> dept_id = new ArrayList<String>();
		for (UsRoleSqlPrivInfo info : infos) {
			info.setDprl_code(dprl_code);
			dept_id.add(daoDeptService.getDeptByDprl(info.getDprl_code()).getDept_id());
			// checkPrivService.checkDeptSqlPrivState(dept_id,
			// info.getSoc_name(),
			// info.getTbl_name());
			// ����Ҫ����ĳ��Ȩ��ʱ��ȥ�����м��Ȩ���Ƿ����
			if (info.getIns_priv_flag() == PRIV_FLAG.YES) {
				checkPrivService.checkDeptTabInsPriv(dept_id,info.getSoc_name(), info.getTbl_name());
			}
			if (info.getDel_priv_flag() == PRIV_FLAG.YES) {
				checkPrivService.checkDeptTabDelPriv(dept_id,info.getSoc_name(), info.getTbl_name());
			}
			if (info.getUpd_priv_flag() == PRIV_FLAG.YES) {
				checkPrivService.checkDeptTabUpdPriv(dept_id,info.getSoc_name(), info.getTbl_name());
			}
			if (info.getSel_priv_flag() == PRIV_FLAG.YES) {
				checkPrivService.checkDeptTabSelPriv(dept_id,info.getSoc_name(), info.getTbl_name());
			}
			daoSqlService.checkDprlSqlNotExist(info);
			// if (daoSqlService.countSqlByDprlCode(info.getDprl_code(),
			// info.getSoc_name(), info.getTbl_name()) == 1) {
			// continue;
			// }
			
			
			daoSqlService.insertInfo(info);
		}
	}

	/**
	 * Description: Ϊ���Ž�ɫ���COL����Ȩ��
	 * @param infos
	 */
	public void addDprlColPriv(String dprl_code,List<UsRoleColPrivInfo> infos) {
		List<String> dept_id = new ArrayList<String>();
		for (UsRoleColPrivInfo info : infos) {
			info.setDprl_code(dprl_code);
			dept_id.add(daoDeptService.getDeptByDprl(info.getDprl_code())
					.getDept_id());
			// checkPrivService.checkDeptColPrivState(dept_id,
			// info.getSoc_name(),
			// info.getTbl_name(), info.getCol_name());
			if (info.getUpd_priv_flag() == PRIV_FLAG.YES) {
				checkPrivService.checkDeptColUpdPriv(dept_id,
						info.getSoc_name(), info.getTbl_name(),
						info.getCol_name());
			}
			// ��鲿�Ž�ɫCOL����Ȩ�ޱ��и�����¼�Ƿ����
			daoColService.checkDprlColNotExist(info);
			// if (daoColService.countColByDprlCode(info.getDprl_code(),
			// info.getSoc_name(), info.getTbl_name(), info.getCol_name()) == 1)
			// {
			// continue;
			// }
			
			daoColService.insertInfo(info);
		}
	}

}
