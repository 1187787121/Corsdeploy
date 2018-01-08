/**
 * Title: UsUserContactDao.java
 * File Description: 用户常用联系人关联表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-11-18
 */
package com.wk.cd.system.us.dao;

import com.wk.cd.system.us.info.UsUserContactInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:用户常用联系人关联表
 * @author AutoGen
 */
abstract class UsUserContactDao extends EntityDao<UsUserContactInfo> {

	/** 
	 * Description: 查询用户指定联系人数量，用于判断是否存在记录
	 * @param user_id
	 * @param contact_user_id
	 * @return 
	 */
	@SqlParam(condition="USER_ID=:user_id and CONTACT_USER_ID=:contact_user_id")
	abstract int countUserContact(String user_id, String contact_user_id);
}