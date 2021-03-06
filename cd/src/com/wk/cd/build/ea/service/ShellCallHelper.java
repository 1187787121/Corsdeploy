/**
 * Title: RemoteExcuteService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年1月9日
 */
package com.wk.cd.build.ea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.Controller;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.remote.sh.service.JSchRCallService;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.TelnetRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;

/**
 * Class Description:
 * @author yangl
 */
public class ShellCallHelper {
	
	private static DtSocService dtSocService = Controller.getInstance().getInjector().getBean(DtSocService.class);

	/**
	 * 默认超时时间，单位为秒
	 */
	public static final int DEFAULT_TIMEOUT = 600;

	/**
	 * Description: 根据数据源信息获取RBean
	 * @param dtInfo
	 * @return
	 */
	public static RBean getRBean(DtSourceInfo dtInfo) {
		RBean bean = new RBean();
		bean.setSoc_name(dtInfo.getSoc_name());
		bean.setSoc_ip(dtInfo.getSoc_ip().trim());
		bean.setSoc_port(dtInfo.getSoc_port());
		bean.setProtocol_type(dtInfo.getProtocol_type());
		bean.setRemote_uname(dtInfo.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null, dtInfo.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dtInfo.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd);
		int time_out = (int) dtInfo.getBk_timeout();
		bean.setTimeout(time_out == 0 ? DEFAULT_TIMEOUT : time_out);
		String was_params = getWasParamsInfo(dtInfo.getSoc_params(), passed_key);
		bean.setWas_params(was_params);
		return bean;
	}

	/**
	 * Description: 得到Was参数组成的命令字符串
	 * @param soc_params
	 * @return
	 */
	private static String getWasParamsInfo(String soc_params, String passed_key) {
		StringBuilder target_params = new StringBuilder("-conntype SOAP -host ");
		if (!Assert.isEmpty(soc_params)) {
			String[] wasParams = soc_params.split("\\|");
			String was_passwd = DESUtil.docryptAllowReverse(false, passed_key, wasParams[3]);
			target_params.append(wasParams[0] + " ");
			target_params.append("-port " + wasParams[1] + " ");
			target_params.append("-user " + wasParams[2] + " ");
			target_params.append("-password " + was_passwd + " ");
		}
		return target_params.toString();
	}
	
	/**
	 * Description: 得到Was参数组成的命令字符串
	 * @param soc_params
	 * @return
	 */
	private static AbstractRCallService getCallService(PROTOCOL_TYPE protocol_type) {
		if (protocol_type == PROTOCOL_TYPE.SSH) {
			return new JSchRCallService();
		} else if (protocol_type == PROTOCOL_TYPE.TELNET) {
			return new TelnetRCallService();
		}
		return null;
	}
	
	public static String execute(String cmd, String soc_name,String server_ip){
		DtSourceInfo dtInfo = dtSocService.getInfoByKey(soc_name, server_ip);
		if(dtInfo.getProtocol_type() == PROTOCOL_TYPE.AGENT) {
			RSession sess = new AgentRSession(dtInfo.getSoc_ip(),dtInfo.getSoc_port(), IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "common/");
			ShExecRsBean rs = sess.sendCmd(cmd);
			if (sess != null) {
				sess.disconnect();
			}
			return rs.getRs_msg();
		} else {
			RBean rbean = getRBean(dtInfo);
			AbstractRCallService callService = getCallService(dtInfo.getProtocol_type());
			RConnection conn = callService.getConnection(rbean);
			RSession sess = callService.openSession(conn);
			ShExecRsBean rs = sess.sendCmd(cmd);
			callService.close(conn, sess);		
			return rs.getRs_msg();
		}
	}
	
	public static List<String> findFiles(List<String> name_list, String soc_name,String server_ip){
		List<String> file_list = new ArrayList<String>();
		if (!Assert.isEmpty(name_list)) {
			StringBuilder cmd = new StringBuilder("find `pwd`");
			for (String name : name_list) {
				cmd.append(" -o -type f -name '");
				cmd.append(name);
				cmd.append("'");
			}
			cmd.delete(10, 13);
			String r = execute(cmd.toString(), soc_name,server_ip);
			String[] files = r.split("\n");
			if (!Assert.isEmpty(files)) {
				for (String file : files) {
					if (!Assert.isEmpty(file.trim())&&file.startsWith("/")) {
						file_list.add(file.trim());
					}
				}
			}
		}
		return file_list;
	}
	
	//获取文件大小（单位KB）
	public static int getFileSize(String soc_name, String file_path, String file_name,String server_ip){
		StringBuilder cmd = new StringBuilder("cd " + file_path+";");
		cmd.append("du -s " + file_name);
		String result = execute(cmd.toString(), soc_name, server_ip);
		
		//获取第一组数字
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(result);
		while (matcher.find()) {
			result = matcher.group(0);
			break;
		}
		if(!Assert.isEmpty(result)){
			return Integer.valueOf(result);
		}else{
			return 0;
		}
	}

	public static void main(String args[]) {
//		DtSourceInfo dtInfo = new DtSourceInfo();
//		dtInfo.setSoc_ip("10.1.1.230");
//		dtInfo.setProtocol_type(PROTOCOL_TYPE.TELNET);
//		dtInfo.setSoc_port(23);
//		dtInfo.setRemote_uname("core");
//		dtInfo.setRemote_passwd("5db2ef7fb6d3974ef3bec332a41aba27");
//		dtInfo.setKey_remote_passwd("a8cbfc6852835d24dcb9d17047cb04cf");
//		System.out.println(execute("find `pwd` -name 'start.sh' -o -name 'stop.sh'", dtInfo));
//		List<String> name_list = new ArrayList<String>();
//		name_list.add("123.txt");
//		name_list.add("satrt.sh");
//		name_list.add("startCms.sh");
//		name_list.add("stop.sh");
//		name_list.add("stopCms.sh");
//		name_list.add("test.txt");
//		name_list.add("web.xml");
//		long time = System.currentTimeMillis();
//		List<String> file_list = findFiles(name_list, "220ssh");
//		System.out.println(System.currentTimeMillis()-time);
//		int i = 0;
//		for (String file : file_list) {
//			System.out.println(++i+"---"+file);
//		}
		
		int result = getFileSize("227agent", "/home/sample", "2.txt", "10.1.1.227");
		System.out.println(result);
	}
	
}
