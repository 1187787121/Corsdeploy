/**
 * Title: AsynMain.java
 * File Description: 异步系统启动类
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 6/1/2015
 */

package com.wk.cd.async.da.service;
import com.wk.cd.common.util.CfgTool;

/**
 * Class Description: 异步系统启动类
 * @author lixl
 */
public class AsynStartup {
	private static final String NAME = "AsyncGrp";
	private static boolean start = false;
	static final ThreadGroup tgrp = new ThreadGroup(NAME);

	public static void startAsynSystem() {
		if (isStart() && !start) {
			AsynMainController main = new AsynMainController();
			Thread tm = new Thread(tgrp, main);
			System.out.println("------AsyncSystem start-------");
			tm.setName("AsynMainThread");
			tm.start();
			start = true;
		}
	}

	private static boolean isStart() {
		return "true".equalsIgnoreCase(CfgTool
				.getProjectPropterty("cms.async.on"));
	}
}

