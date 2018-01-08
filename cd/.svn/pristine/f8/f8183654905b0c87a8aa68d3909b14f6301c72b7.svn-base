/**
 * Title: DBAsynServiceFactory.java
 * File Description:  获取异步服务对象工厂
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-30
 */
package com.wk.cd.async.da.service;

import java.util.HashMap;
import java.util.Map;

import com.wk.cd.async.exc.AsyncSystemNotStartException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.BeanTool;


/**
 * @author lixl
 */
public class DBAsynServiceFactory {

	@SuppressWarnings("unchecked")
	private static final Map<String, AsynBaseService> map = new HashMap<String, AsynBaseService>();

	/**
	 * 此方法是获取异步调用对象的代理方法， 
	 * 　　根据系统标识获取异步服务，系统标识符必须在配置文件中配置 
	 *  cms.async.sysid.daocls=com.wk.cms.system.lg.dao.LgLogMfDaoService[log]
	 *  log为标识符
	 *  LgLogMfDaoService为实现DaoServiceInf接口的实现类
	 * @param sys_id　系统标识
	 * @return 返回服务对象
	 */
	@SuppressWarnings("unchecked")
	public static AsynBaseService getAsynSvcBySysId(String sys_id) throws AsyncSystemNotStartException{
		Assert.assertNotEmpty(sys_id, "sys_id");
		AsynBaseService asySvc = map.get(sys_id);
		if(asySvc == null){
			throw new AsyncSystemNotStartException().addScene("SYS_ID", sys_id);
		}
		return asySvc;
	}
	
	synchronized static AsynBaseService initAsynSvc(String sys_id, String dao_cls, boolean is_disp){
		DaoServiceInf dao = BeanTool.getBeanByClassName(dao_cls);
		DBAsynService asySvc = new DBAsynService(dao, is_disp);
		map.put(sys_id, asySvc);
		return asySvc;
	}
}
