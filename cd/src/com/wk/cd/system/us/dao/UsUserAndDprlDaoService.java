/**
 * Title: UsUserAndDprlDaoService.java
 * File Description: ���ݲ��Ž�ɫ������û����ͷ�ҳ��ѯ�û���Ϣ��DaoService��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.us.bean.UsUserAndDprlBean;
import com.wk.lang.Inject;

/**
 * Class Description:���ݲ��Ž�ɫ������û����ͷ�ҳ��ѯ�û���Ϣ��DaoService��
 * @author link
 */
public class UsUserAndDprlDaoService {

	@Inject
	UsUserAndDprlDao dao;

	/**
	 * Description: ���ݲ��Ž�ɫ������û����ͷ�ҳ��ѯ�û���Ϣ
	 * @param dprl_code
	 * @param user_type
	 * @param statr_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsUserAndDprlBean> pageUserByDprlAndType(String dprl_code,
			int user_type, int statr_rcd, int limited_rcd) {
		List<UsUserAndDprlBean> info_list = new ArrayList<UsUserAndDprlBean>();
		info_list = dao.pageUserByDprlAndType(user_type, dprl_code, statr_rcd,
				limited_rcd);
		return info_list;
	}

	/**
	 * Description: ���ݲ��Ž�ɫ������û����Ͳ�ѯ�����û���Ϣ������
	 * @param user_type
	 * @param dprl_code
	 * @return
	 */
	public int countUserByDprlAndType(String dprl_code, int user_type) {
		return dao.countUserByDprlAndType(user_type, dprl_code);
	}
}
