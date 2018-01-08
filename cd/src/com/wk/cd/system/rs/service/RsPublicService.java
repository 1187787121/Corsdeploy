/**
 * Title: RsPublicService.java
 * File Description: 资源公共服务
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.rs.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.OPEN_TYPE;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.rs.bean.RsOptPrivBean;
import com.wk.cd.system.rs.dao.RsOptDaoService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.cd.system.us.dao.UsUserPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 资源公共服务
 * @author xuy
 */
public class RsPublicService {
	@Inject
	private RsResDaoService resDaoService;
	@Inject
	private RsOptDaoService optDaoService;
	@Inject
	private UsUserPrivDaoService usUserPrivDaoService;
	@Inject
	private UsUserRoleDaoService usUserRoleDaoService;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 检查资源存在状态
	 * @param rs_code
	 */
	public void checkRsExist(String rs_code) {
		resDaoService.getInfoStateByCode(rs_code);
	}

	/**
	 * Description: 根据资源编码查询资源信息
	 * @param rs_code
	 * @return
	 */
	public RsResInfo getResInfo(String rs_code) {
		return resDaoService.getInfoByCode(rs_code);
	}

	/**
	 * Description: 根据资源编码dbiterator查询资源信息
	 * @param rs_iterator
	 */
	public DBIterator<RsResInfo> queryInfoByCodeString(DBIterator<String> rs_iterator) {
		return resDaoService.queryInfoByCodeString(rs_iterator);
	}

	/**
	 * Description: 根据资源编码字符串查询资源信息
	 * @param resInfo
	 */
	public List<RsResInfo> queryInfoByCodeString(List<String> rs_list) {
		return resDaoService.queryInfoByCodeString(rs_list);
	}

	/**
	 * 根据资源编码获取下级操作列表
	 * @param rs_code
	 * @return
	 */
	public List<RsOptInfo> getSubOptList(String rs_code) {
		return optDaoService.getSubOptList(rs_code);
	}

	/**
	 * 获取需要操作检查的资源配置信息
	 * @return
	 */
	public List<RsUrlBean> getRsConfig() {
		return optDaoService.getRsConfig();
	}

	/**
	 * 查询所有资源列表
	 * @return
	 */
	public List<RsOptInfo> listAllRsOpt() {
		return optDaoService.listAllRsOpt();
	}

	/**
	 * 查询所有资源权限信息列表
	 * @return
	 */
	public List<RsPrivBean> queryAllUnPublicRsPriv() {
		return resDaoService.queryAllUnPublicRsPriv();
	}

	/**
	 * Description: 查询用户对资源的操作权限
	 * @param rs_code
	 * @param dept_id
	 * @param dprl_lst
	 * @param user_id
	 * @return
	 */
	public List<RsOptPrivBean> getRsOptPriv(String rs_code, String user_id) {
		List<RsOptPrivBean> priv_list = new ArrayList<RsOptPrivBean>();

		RsResInfo rs_info = resDaoService.getInfoByCode(rs_code);
		OPEN_TYPE open_type = rs_info.getOpen_type();
		
		logger.debug("资源:[{}]开发类型：[{}]", rs_code, open_type.getCname());
		
		List<RsOptInfo> opt_list = optDaoService.getSubOptList(rs_code);

		List<String> dprl_list = usUserRoleDaoService.queryDprlByUserId(user_id);

		for (RsOptInfo opt_info : opt_list) {
			RsOptPrivBean priv_bean = new RsOptPrivBean();
			String opt_code = opt_info.getOpt_code();
			priv_bean.setOpt_code(opt_code);
			priv_bean.setOpt_name(opt_info.getOpt_name());
			priv_bean.setDis_express(opt_info.getDis_express());
			priv_bean.setOpt_bk_expl(opt_info.getOpt_bk_expl());

			boolean falg = usUserPrivDaoService.userHasOptPrivByOptCode(user_id, opt_code, open_type, dprl_list.size());
			if (falg) {// 用户对操作有权限
				priv_bean.setPriv_flag(PRIV_FLAG.YES);
			} else {
				priv_bean.setPriv_flag(PRIV_FLAG.NO);
			}
			priv_list.add(priv_bean);
		}

		return priv_list;
	}

	/**
	 * Description: 获取资源URL对照列表
	 * @return
	 */
	public List<RsUrlBean> getRsUrlList(String bl_rs_code) {
		return resDaoService.getRsUrlList(bl_rs_code);
	}

}
