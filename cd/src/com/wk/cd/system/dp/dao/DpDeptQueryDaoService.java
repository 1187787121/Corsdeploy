/**
 * Title: DpDeptQueryDaoService.java
 * File Description: �������ϲ�ѯ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��27��
 */
package com.wk.cd.system.dp.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: �������ϲ�ѯ��
 * 
 * @author HT
 */
public class DpDeptQueryDaoService {
	
	@Inject
	private DpDeptQueryDao dpDeptQueryDao;

	/**
	 * Description: ���ݲ���ID��ѯ��ԴȨ����Ϣ�б�
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
	 * Description: ���ݲ���ID��ѯ����ԴȨ����Ϣ�б�
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
