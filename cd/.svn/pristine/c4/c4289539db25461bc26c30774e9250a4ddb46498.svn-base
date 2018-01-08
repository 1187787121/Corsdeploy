/**
 * Title: IgUserDao.java
 * File Description: 用户表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */
package com.wk.cd.dlk.us.dao;

import com.wk.cd.dlk.us.info.IgUserInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:用户表
 * @author AutoGen
 */
abstract class IgUserDao extends EntityDao<IgUserInfo> {

	/** 
	 * Description: 查询所有用户
	 * @return 
	 */
	@SqlParam(sql="select USERID ,USERNAME,USERMOBILE,USEREMAIL,org.ORGID from iguser us left join organization org  on us.ORGID=org.ORGSYSCODING where DELETEFLAG=0")
	abstract DBIterator<IgUserInfo> iteratorAllIgUser();
}