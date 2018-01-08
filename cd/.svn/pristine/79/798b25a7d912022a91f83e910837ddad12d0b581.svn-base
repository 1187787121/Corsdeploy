/**
 * Title: TestSvn.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017Äê10ÔÂ12ÈÕ
 */
package com.wk.cd.test;

import java.io.File;

import org.tmatesoft.svn.core.SVNException;

import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.SVNUtil;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.sdo.ServiceData;
import com.wk.test.TestCase;

/**
 * Class Description:
 * @author xuph
 */
public class TestSvn
		extends TestCase {
	@Inject
	DtSocService dss;

	@SuppressWarnings("unused")
	private int kkkk() {
		DtSourceInfo shell_soc = getLocalSoc();
		String[] cmds = getCmd();
		// ModuleSourceInfo msi = new ModuleSourceInfo(shell_soc, svn_soc);
		ModuleSourceInfo msi = new ModuleSourceInfo(PROTOCOL_TYPE.SVN, shell_soc);
		msi.setData(getSvnSoc());
		SVN svn = new SVN(msi, cmds);
		Result result = svn.run();
		System.out.println(result.getMsg());
		System.out.println(result.getStatus().getCname());
		return 2;

	}

	private ServiceData getSvnSoc() {
		ServiceData data = new ServiceData();
		String pass_key = "111111111111";
		final String url = "svn://10.1.1.240/";
		final String sc_user = "jiangzg";
		final String sc_pass = "jiangzg";
		String p = DESUtil.docryptAllowReverse(true, pass_key, sc_pass).trim();
		data.putString("url", url);
		data.putString("user", sc_user);
		data.putString("password", p);
		data.putString("passed_key", DESUtil.docryptAllowReverse(true, null, pass_key).trim());
		return data;
	}

	private DtSourceInfo getLocalSoc() {
		DtSourceInfo info = new DtSourceInfo();
		final int port = 22;
		final String user = "monitor";
		final String pass = "monitor";
		final int timeout = 240;
		final String local_root = "/home/monitor";
		try {
			info.setProtocol_type(PROTOCOL_TYPE.SSH);
			info.setSoc_name("228ssh");
			info.setSoc_ip("10.1.1.228");
			info.setSoc_port(port);
			info.setRemote_uname(user);
			info.setUser_root_path(local_root);
			info.setBk_timeout(timeout);
			String pass_key = "111111111111";
			info.setKey_remote_passwd(DESUtil.docryptAllowReverse(true, null, pass_key).trim());
			info.setRemote_passwd(DESUtil.docryptAllowReverse(true, pass_key, pass).trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	private String[] getCmd() {
		String[] cmds = new String[2];
		cmds[0] = "pwd";
		cmds[1] = "co /CorsManager/bank_kk/cd_xp_test xuph_dir/";
		return cmds;
	}

	@SuppressWarnings("unused")
	private void testExport() {
		DtSourceInfo version_soc = dss.getInfoByKey("240svn");
		System.out.println(version_soc.toString());
		try {
			SVNUtil.export(version_soc, "bank_kk/cd_1.0.1_alpha/app", "F:\\svn_test");
		} catch (SVNException e) {
			e.printStackTrace();
		}
	}

/*	public void testCo(){
		DtSourceInfo version_soc = dss.getInfoByKey("240svn");
		System.out.println(version_soc.toString());
		try {
			SVNUtil.checkout(version_soc, "bank_kk/cd_1.0.1_alpha/app", "F:\\svn_test");
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	 
	private void testAddEntry(){
		DtSourceInfo version_soc = dss.getInfoByKey("240svn");
		System.out.println(version_soc.toString());
		try {
			SVNUtil.addEntry(version_soc, "F:\\svn_test");
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 
	public void testCommit(){
		DtSourceInfo version_soc = dss.getInfoByKey("240svn");
		System.out.println(version_soc.toString());
		try {
			SVNUtil.commit(version_soc, "F:\\svn_test", false, "svn_test");
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
