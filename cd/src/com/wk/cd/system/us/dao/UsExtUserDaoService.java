/**
 * Title: UsExtUserDaoService.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-20
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.UsExtUserBean;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author link
 */
public class UsExtUserDaoService {

	@Inject
	private UsExtUserDao dao;

	public List<UsExtUserBean> pageUserByBlDeptId(String dept_id,String user_cn_name,
			int statr_rcd, int limited_rcd) {
		return dao.pageUserByBlDeptId(dept_id, user_cn_name, statr_rcd,
				limited_rcd);
	}

	public int countUserByBlDeptId(String dept_id, String user_id) {
		return dao.countUserByBlDeptId(dept_id, user_id);
	}

	/**
	 * Description: �����û�����ѯ�û���һ��ְ����������Ϣ
	 * @param user_id
	 * @return
	 */
	public String queryExtUserFirstDeptByUserId(String user_id) {
		return dao.queryExtUserFirstDeptByUserId(user_id);
	}
	
	/**
	 * Description: ��ѯ�û��Ĳ�������
	 * @param user_id
	 * @return
	 */
	public String queryExtUserDeptNameByUserId(String user_id){
		return dao.queryExtUserDeptNameByUserId(user_id);
	}

	/**
	 * Description: ��ѯ�û����Ž�ɫ��Ϣ�б�
	 * @param user_id
	 * @return
	 */
	public List<UsDeptRoleInfo> queryExtUserDprlListByUserId(String user_id) {
		return dao.queryExtUserDprlListByUserId(user_id);
	}

	/** 
	 * Description: ��ѯ�û�������ϵ��
	 * @param user_id
	 * @return 
	 */
	public List<UsExtUserBean> queryUserContact(String user_id) {
		return dao.queryUserContact(user_id);
	}

}
