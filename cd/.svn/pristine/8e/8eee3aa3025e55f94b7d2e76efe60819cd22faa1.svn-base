/**
 * Title: AsynThread.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-30
 */
package com.wk.cd.async.da.service;


import com.wk.cd.exc.CorsManagerSystemErrorException;

/**
 * @author lixl
 */
public class AsynThread implements Runnable {
	private String sys_id;
	private String dao_cls;
	private boolean is_disp;
	AsynBaseService svc;
	AsynThread(String sys_id, String dao_cls, boolean is_disp){
		this.sys_id = sys_id;
		this.dao_cls = dao_cls;
		this.is_disp = is_disp;
	}
	
	public void run() {
		System.out.println("------AsynThread ["+ sys_id + "] init start------");
		initSys();
		System.out.println("------AsynThread ["+ sys_id + "] init end------");
		dispatch();
	}
	
	private void initSys(){
		svc = DBAsynServiceFactory.initAsynSvc(sys_id, dao_cls, is_disp);
	}
	
	private void dispatch(){
		System.out.println("------AsynThread [" + sys_id + "] Running------");
		try{
			svc.dispatchService();
		}catch(InterruptedException e){
			e.printStackTrace();
			throw new CorsManagerSystemErrorException("INTERRUPTED_EXCEPTION");
		}
			
	}

}
