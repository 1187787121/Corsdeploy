/**
 * Title: UsUserOptPrivDao.java
 * File Description: �û�����Ȩ�����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */
package com.wk.cd.system.us.dao;

import com.wk.db.*;
import com.wk.cd.system.us.info.*;

/**
 * Class description:�û�����Ȩ�����ñ�
 * @author AutoGen
 */
abstract class UsUserOptPrivDao extends EntityDao<UsUserOptPrivInfo> {

	/** 
	 * Description: �����û�ID��ѯ�û�������ֹȨ����Ϣ�б�����������Դ�Ĳ���Ȩ����Ϣ��
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_flag=2")
	abstract DBIterator<UsUserOptPrivInfo> queryOptForbidPrivBy(String user_id);

	/** 
	 * Description: �����û�ID��ѯ�û���������Ȩ����Ϣ�б�����ֹ����Դ�Ĳ���Ȩ����Ϣ��
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_flag=1")
	abstract DBIterator<UsUserOptPrivInfo> queryOptAllowPrivByUser(String user_id);

	/** 
	 * Description: �����û�ɾ������Ȩ��
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id")
	abstract int deleteOptPrivByUser(String user_id);
}