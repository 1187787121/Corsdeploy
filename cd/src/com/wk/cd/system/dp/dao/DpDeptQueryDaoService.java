/**
 * Title: DpDeptQueryDaoService.java
 * File Description: 部门联合查询类
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月27日
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 部门联合查询类
 * 
 * @author HT
 */
public class DpDeptQueryDaoService {
	
	@Inject
	private DpDeptQueryDao dpDeptQueryDao;

	/**
	 * Description: 根据部门ID查询资源权限信息列表
	 * 
	 * @param dept_id
	 * @return
	 */
	public List<RsPrivBean> queryRsPrivByDeptId(String dept_id) {
		List<RsPrivBean> rs_list=new ArrayList<RsPrivBean>();
		DBIterator<RsPrivBean> rs_iterator = dpDeptQueryDao.queryRsPrivByDeptId(dept_id);
		try {
			while (rs_iterator.hasNext()) {
				RsPrivBean bean =rs_iterator.next();
				rs_list.add(bean);
			}
		} finally {
			rs_iterator.close();
		}
		return rs_list;
	}

	/** 
	 * Description: 根据部门ID查询数据源权限信息列表
	 * @param dept_id
	 * @return 
	 */
	public List<SocPrivBean> querySocPrivByDeptId(String dept_id) {
		List<SocPrivBean> soc_list=new ArrayList<SocPrivBean>();
		DBIterator<SocPrivBean> soc_iterator = dpDeptQueryDao.querySocPrivByDeptId(dept_id);
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
