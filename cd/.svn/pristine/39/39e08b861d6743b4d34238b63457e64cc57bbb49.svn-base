/**
 * Title: DbAsynService.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-30
 */
package com.wk.cd.async.da.service;

import com.wk.db.DBService;
import com.wk.lang.Inject;
import com.wk.threadpool.ThreadDispatch;

import java.util.List;

/**
 * @author lixl
 */
public abstract class DaoServiceInf {
	@Inject
	ThreadDispatch<Object> dbh;
	public abstract void daoRun(Object info);
}
