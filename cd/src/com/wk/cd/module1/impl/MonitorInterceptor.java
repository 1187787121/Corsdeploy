package com.wk.cd.module1.impl;

import com.wk.cd.module1.ModuleInterceptor;
import com.wk.cd.module1.ProcessContext;

public class MonitorInterceptor implements ModuleInterceptor {
 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#beforeRun(com.wk.cd.module1.ProcessContext)
	 */
	public void beforeRun(ProcessContext ctx) {
	 
	}
	 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#afterRun(com.wk.cd.module1.ProcessContext)
	 */
	public void afterRun(ProcessContext ctx) {
	 
	}
	 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#beforeStepinto(com.wk.cd.module1.ProcessContext)
	 */
	public void beforeStepinto(ProcessContext ctx) {
	 
	}
	 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#afterStepinto(com.wk.cd.module1.ProcessContext)
	 */
	public void afterStepinto(ProcessContext ctx) {
	 
	}
	 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#beforeStart(com.wk.cd.module1.ProcessContext)
	 */
	public void beforeStart(ProcessContext ctx) {
	 
	}
	 
	/**
	 * @see com.wk.cd.module1.ModuleInterceptor#afterEnd(com.wk.cd.module1.ProcessContext)
	 */
	public void afterEnd(ProcessContext ctx) {
	 
	}

	/** 
	 * Description: 
	 * @param ctx 
	 */
	@Override
	public void skip(ProcessContext ctx) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: 
	 * @param ctx 
	 */
	@Override
	public void skipStep(ProcessContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendInteractCmd(ProcessContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getInteractMsg(ProcessContext ctx) {
		// TODO Auto-generated method stub
		
	}
	 
}
 
