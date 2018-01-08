/**
 * Title: SyncIgUserTask.java
 * File Description: 同步用户表任务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年10月26日
 */
package com.wk.cd.dlk.us.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.BeanTool;
import com.wk.cd.dlk.dp.info.OrganizationInfo;
import com.wk.cd.dlk.dp.service.OrganizationService;
import com.wk.cd.dlk.us.dao.IgUserDaoService;
import com.wk.cd.dlk.us.info.IgUserInfo;
import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.USER_TYPE;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.db.DBTask;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.threadpool.Task;

/**
 * Class Description: 同步用户表任务
 * @author HT
 */
public class SyncIgUserTask
		extends DBTask {
	@Inject
	private OrganizationService organizationService;
	@Inject
	private IgUserDaoService igUserDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private CmSeqDaoService cmsvc;
	private static final Log logger = LogFactory.getLog();

	@Override
	protected synchronized void execute() {
		logger.info("--------SyncIgUserTask begin--------");
		int data_count = 1;
		int commit_count = 100;
		int user_all_count;
		int user_error_count = 0;
		int dept_all_count;
		int dept_error_count = 0;

		logger.debug("---------------------sync table dlk.iguser to cms.us_user start--------");

		List<IgUserInfo> userList = igUserDaoService.listAllIgUser();
		logger.debug("--------query all iguser success--------");
		user_all_count = userList.size();

		List<OrganizationInfo> orgList = organizationService.listAllOrganization();

		logger.debug("--------query all organization success--------");
		dept_all_count = orgList.size();

		dpDeptDaoService.truncateTable();
		logger.debug("--------truncate talbe dp_dept success--------");

		List<DpDeptInfo> dpCommitList = new ArrayList<DpDeptInfo>();
		data_count = 1;
		for (int i = 0; i < orgList.size(); i++) {
			OrganizationInfo orgInfo = orgList.get(i);
			DpDeptInfo deptInfo = new DpDeptInfo();
			deptInfo.setDept_id(String.valueOf(orgInfo.getOrgid()));
			deptInfo.setDept_cn_name(orgInfo.getOrgname());
			deptInfo.setDept_full_cname(orgInfo.getOrgname());
			deptInfo.setDept_type(DEPT_TYPE.TECH);
			deptInfo.setDept_level(1);
			deptInfo.setRcd_state(RCD_STATE.NORMAL);
			dpCommitList.add(deptInfo);
			// 每隔n条（有效<20）数据提交一次
			if (++data_count > commit_count) {
				commitDeptList(dpCommitList,dept_error_count);
				dpCommitList = new ArrayList<DpDeptInfo>();
				data_count = 1;
			}

		}
		if (dpCommitList.size() > 0) {
			commitDeptList(dpCommitList,dept_error_count);
			data_count = 1;
		}
		logger.debug("--------insert talbe dp_dept end--------");

		usUserDaoService.truncateTable();
		logger.debug("--------truncate talbe us_user success--------");

		List<UsUserInfo> usCommitList = new ArrayList<UsUserInfo>();

		for (int i = 0; i < userList.size(); i++) {
			IgUserInfo igUserInfo = userList.get(i);
			if (igUserInfo.getUserid().length() > 20) {
				logger.error("ERROR!!! user " + igUserInfo.getUserid() + " length is too long leng=[" + igUserInfo.getUserid().length() + "]--------");
				user_error_count++;
				continue;
			}
			UsUserInfo userInfo = new UsUserInfo();
			userInfo.setUser_id(igUserInfo.getUserid());
			userInfo.setUser_cn_name(igUserInfo.getUsername());
			userInfo.setBl_dept_id(igUserInfo.getOrgid());
			userInfo.setLogin_bk_num(1);
			userInfo.setEmail_add(igUserInfo.getUseremail());
			userInfo.setPhone_no(igUserInfo.getUsermobile());
			userInfo.setUser_type(USER_TYPE.INNER);
			userInfo.setRcd_state(RCD_STATE.NORMAL);
			usCommitList.add(userInfo);
			// 每隔n条（有效<20）数据提交一次
			if (++data_count > commit_count) {
				commitUserList(usCommitList,user_error_count);
				usCommitList = new ArrayList<UsUserInfo>();
				data_count = 1;
			}

		}
		if (usCommitList.size() > 0) {
			commitUserList(usCommitList,user_error_count);
			data_count = 1;
		}
		logger.debug("--------insert talbe dp_dept end--------");
		logger.debug("	dept list count =[{}] success=[{}] error=[{}]", dept_all_count, dept_all_count - dept_error_count, dept_error_count);
		logger.debug("	user list count =[{}] success=[{}] error=[{}]", user_all_count, user_all_count - user_error_count, user_error_count);
		logger.info("--------SyncIgUserTask end--------");
	}

	/**
	 * Description:
	 * @param dpCommitList
	 */
	protected void commitDeptList(List<DpDeptInfo> dpCommitList,int dept_error_count) {
		try {
			dpDeptDaoService.insertListInfo(dpCommitList);
		} catch (RuntimeException e) {
			cmsvc.getSession().rollbackAndResume();
			logger.error(e.toString(), e);
			// 整体保存出错，继续单条保存dpCommitList的数据，
			for (DpDeptInfo dept : dpCommitList) {
				try {
					logger.debug("-->insert deptdata=[" + dept.getDept_full_cname() + "]");
					dpDeptDaoService.insertInfo(dept);
				} catch (RuntimeException e2) {
					dept_error_count++;
					logger.error("ERROR!!! dept" + dept.getDept_full_cname() + " commit error--------");
					logger.error(e2.toString(), e2);
				} finally {
					cmsvc.getSession().commitAndResume();
					logger.debug("-->insert deptdata=[" + dept.getDept_full_cname() + "] end");
				}
			}
		} finally {
			cmsvc.getSession().commitAndResume();
			logger.debug("---commit deptlist end");
		}
	}

	protected void commitUserList(List<UsUserInfo> userList,int user_error_count) {
		try {
			usUserDaoService.insertListInfo(userList);
		} catch (RuntimeException e) {
			cmsvc.getSession().rollbackAndResume();
			logger.error(e.toString(), e);
			// 整体保存出错，继续单条保存commitList的数据，
			for (UsUserInfo user : userList) {
				try {
					logger.debug("-->insert userdata=[" + user.getUser_id() + "]");
					usUserDaoService.insertInfo(user);
				} catch (RuntimeException e2) {
					user_error_count++;
					logger.error("ERROR!!! user " + user.getUser_id() + " commit error--------");
					logger.error(e2.toString(), e2);
				} finally {
					cmsvc.getSession().commitAndResume();
					logger.debug("-->insert userdata=[" + user.getUser_id() + "] end");
				}
			}
		} finally {
			cmsvc.getSession().commitAndResume();
			logger.debug("---commit userlist end");
		}
	}

	public static void main(String[] args) {
		Task t1 = BeanTool.getBeanByClzz(SyncIgUserTask.class);
		t1.run();
	}
}
