/**
 * Title: UsExtDprlDaoService.java
 * File Description: 部门角色扩展信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-13
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.us.bean.UsExtDprlBean;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 部门角色扩展信息
 * @author link
 */
public class UsExtDprlDaoService {
	@Inject
	private UsExtDprlDao dao;

	/**
	 * Description: 根据部门角色查询部门编码、部门名称、角色编码、角色名称
	 * @param dprl_code
	 * @return
	 */
	public UsExtDprlBean queryExtDprlInfo(String dprl_code) {
		return dao.queryExtDprlInfo(dprl_code);
	}

	/**
	 * Description: 查询部门角色扩展信息列表(部门角色编码，部门角色名称，部门编码，部门名称)
	 * @return
	 */
	public List<UsExtDprlBean> pageExtDprlList(String dept_cn_name,String org_dept_id,
			int start_recd, int limited_recd) {
		return dao.pageExtDprlList(dept_cn_name, org_dept_id,start_recd, limited_recd);
	}

	/**
	 * Description: 根据部门查询部门角色扩展信息列表(部门角色编码，部门角色名称，部门编码，部门名称)
	 * @param dept_id
	 * @return
	 */
	public List<UsExtDprlBean> pageExtDprlListByDeptId(String dept_id,
			int start_recd, int limited_recd) {
		return dao.pageExtDprlListByDeptId(dept_id, start_recd, limited_recd);
	}

	/**
	 * Description: 查询部门角色扩展信息列表信息条数
	 * @param dept_cn_name
	 * @return
	 */
	public int countExtDprlList(String dept_cn_name,String dept_id_str) {
		return dao.countExtDprlList(dept_cn_name,dept_id_str);
	}

	/**
	 * Description: 根据部门编码查询部门角色编码、不买呢角色说明、部门编码、部门名称、角色编码、角色名称
	 * @param dept_id
	 * @param start_recd
	 * @param limited_recd
	 * @return
	 */
	public List<UsExtDprlBean> queryExtDprlListByDept(String dept_id) {
		return dao.queryExtDprlListByDept(dept_id);
	}

	/**
	 * Description: 根据部门角色编码查询资源权限信息列表
	 * @param dprl_code
	 * @return
	 */
	public List<RsPrivBean> queryRsPrivByDprl(String dprl_code) {
		List<RsPrivBean> rs_list=new ArrayList<RsPrivBean>();
		DBIterator<RsPrivBean> rs_iterator = dao.queryRsPrivByDprl(dprl_code);
		try {
			while (rs_iterator.hasNext()) {
				rs_list.add(rs_iterator.next());
			}
		} finally {
			rs_iterator.close();
		}
		return rs_list;
	}

	/** 
	 * Description: 根据部门角色编码查询数据源权限信息列表
	 * @param dprl_code
	 * @return 
	 */
	public List<SocPrivBean> querySocPrivByDprl(String dprl_code) {
		List<SocPrivBean> soc_list=new ArrayList<SocPrivBean>();
		DBIterator<SocPrivBean> soc_iterator = dao.querySocPrivByDprl(dprl_code);
		try {
			while (soc_iterator.hasNext()) {
				soc_list.add(soc_iterator.next());
			}
		} finally {
			soc_iterator.close();
		}
		return soc_list;
	}
}
