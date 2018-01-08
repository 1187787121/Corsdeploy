/**
 * Title: UsUserOptPrivDao.java
 * File Description: 用户操作权限配置表
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
 * Class description:用户操作权限配置表
 * @author AutoGen
 */
abstract class UsUserOptPrivDao extends EntityDao<UsUserOptPrivInfo> {

	/** 
	 * Description: 根据用户ID查询用户操作禁止权限信息列表（即开放型资源的操作权限信息）
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_flag=2")
	abstract DBIterator<UsUserOptPrivInfo> queryOptForbidPrivBy(String user_id);

	/** 
	 * Description: 根据用户ID查询用户操作允许权限信息列表（即禁止型资源的操作权限信息）
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_flag=1")
	abstract DBIterator<UsUserOptPrivInfo> queryOptAllowPrivByUser(String user_id);

	/** 
	 * Description: 根据用户删除操作权限
	 * @param user_id
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id")
	abstract int deleteOptPrivByUser(String user_id);
}