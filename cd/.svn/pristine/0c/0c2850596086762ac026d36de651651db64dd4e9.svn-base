/**
 * Title: AsynMain.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-5-30
 */
package com.wk.cd.async.da.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;

/**
 * @author lixl
 */
public final class AsynMainController implements Runnable {
	private static final Map<String, MonitorItem> mthd = new HashMap<String, MonitorItem>();
	private static final ThreadGroup tgrp = AsynStartup.tgrp;
	public void run() {
		String pro = CfgTool.getProjectPropterty("cms.async.sysid.daocls");
		List<MonitorItem> lst = getSysCfg(pro);
		for (MonitorItem item : lst) {
			startThread(item.sys_id, item.dao_cls, item.is_disp);
		}

		sleep(1000);

		monitorAsynSystem();
	}

	private void startThread(String sys_id, String dao_cls, boolean is_disp) {
		AsynThread inst = new AsynThread(sys_id, dao_cls, is_disp);
		Thread t = new Thread(tgrp, inst);
		t.setName("AsynThread-"+sys_id);
		t.start();
		MonitorItem m = new MonitorItem(sys_id, dao_cls, t, is_disp);
		mthd.put(sys_id, m);
	}

	private void monitorAsynSystem() {
		long time = Long.valueOf(CfgTool
				.getProjectPropterty("cms.async.monitor.interval"));
		System.out.println("------AsyncSystem start monitor------");
		while (true) {
			sleep(time);
			for (Map.Entry<String, MonitorItem> entry : mthd.entrySet()) {
				MonitorItem m = entry.getValue();
				if (!m.t.isAlive()) {
					startThread(m.sys_id, m.dao_cls, m.is_disp);
				}
			}
		}
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private List<MonitorItem> getSysCfg(String pro) {
		List<MonitorItem> lst = new ArrayList<MonitorItem>();
		if(Assert.isEmpty(pro)) {
			return lst;
		}
		String [] litm = pro.split("\\,");
		if(Assert.isEmpty(litm)) {
			return lst;
		}

		for(String citm : litm) {
			MonitorItem item = new MonitorItem();
			String [] cell = citm.split("\\[");
			String[] cell2 = cell[1].replace("]", "").split("\\[");
			item.dao_cls = cell[0];
			item.sys_id = cell2[0];
			if(cell.length>=3){
				String[] cell3 = cell[2].replace("]", "").split("\\[");
				if(cell3[0].equalsIgnoreCase("disp")) item.is_disp = true;
			}
			lst.add(item);
		}
		return lst;
	}

	private class MonitorItem {
		Thread t;
		String sys_id;
		String dao_cls;
		boolean is_disp = false;

		MonitorItem(){
		}

		MonitorItem(String sys_id, String dao_cls, Thread t, boolean is_disp) {
			this.t = t;
			this.sys_id = sys_id;
			this.dao_cls = dao_cls;
			this.is_disp = is_disp;
		}
	}

	public static void main(String[] args){
		AsynMainController c = new AsynMainController();
		List<MonitorItem> lst = c.getSysCfg("com.wk.cms.system.lg.dao.LgLogMfDaoService[log]");
		for(MonitorItem item: lst){
			System.out.println("sys_id="+item.sys_id+"-"+"dao_cls="+item.dao_cls+"-"+"is_disp="+item.is_disp);
		}

	}
}
