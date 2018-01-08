/**
 * Title: AgentCommonTool.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年8月25日
 */
package com.wk.cd.remote.agent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.agent.service.AgentClient;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.lang.AppException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * 
 * @author hulc
 */
public class AgentCommonTool {

	private static final Log logger = LogFactory.getLog();
	
	private static String path = "common/";
	
	public static int getPort() {
		String port = CfgTool.getProjectPropterty("cms.agent.port");
		if (Assert.isEmpty(port)) {
			throw new ReadConfigFileException().addScene("FILE",
					"cms.properties").addScene("CONFIG", "cms.agent.port");
		}
		return Integer.parseInt(port);
	}

	// 检测远程目标机器环境
	public static String checkRemoteEnv(String ip) {
		int port = getPort();

		String id = UUID.randomUUID().toString().replaceAll("\\-", "");
		boolean isStepSupport = Boolean.parseBoolean(CfgTool
				.getProjectPropterty("cms.agent.isStepSupport"));
		AgentClient client = new AgentClient(id, ip, port, isStepSupport,
				IMPL_TYPE.SHELL, 1, 1, null, null, 0, 0);

		ShExecRsBean reply = client.exeShell(
				AgentCMDUtil.CHECK_AGENT_EXIST_CMD, "3000");

		String reply_msg = reply.getRs_msg();

		String[] reply_arry = reply_msg.split(",");
		if (!Assert.isEmpty(reply_arry)) {
			reply_msg = reply_arry[0];
		}
		
		String result = "";
		if(reply_msg.toLowerCase().contains("system: win")){
			result = "windows";
		}else if(reply_msg.toLowerCase().contains("system: linux")){
			result = "linux";
		}else{
			result = "aix";
		}
		return result;
	}

	// Agent获取远程机器的主目录路径
	public static String getRootPath(String ip) {
		int port = getPort();
		AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, path);

		String reply = checkRemoteEnv(ip);
		String str = "";
		if (reply.equals("windows")) {

			ShExecRsBean bean = session.sendCmd("echo %cd:~,3%");
			str = bean.getRs_msg();

		} else {
			ShExecRsBean bean = session.sendCmds(new String[]{"pwd"});
			str = bean.getRs_msg();
		}

		return !Assert.isEmpty(str) ? str.trim() : str;
	}

	// Agent获取远程机器的主目录路径(配置文件使用)
	public static String getRootPathForConfigList(String ip) {
		
		String reply = checkRemoteEnv(ip);
		String str = "";
		if (reply.equals("windows")) {
			str = "我的电脑";
		} else {
			int port = getPort();
			AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, path);
			
			ShExecRsBean bean = session.sendCmds(new String[]{"pwd"});
			str = bean.getRs_msg();
		}

		return !Assert.isEmpty(str) ? str.trim() : str;
	}

	// 若远程路径不存在，创建远程路径
	//不建议使用，推荐使用AgentFTPRCallservice
	public static void createRemotePath(String ip, int port, String path) {
		AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, path);
		String[] cmds = new String[4];
		cmds[0] = "path=" + path;
		cmds[1] = "if [ ! -d \"$path\" ];then";
		cmds[2] = "mkdir $path";
		cmds[3] = "fi";

		session.sendCmds(cmds);
	}

	// 检测远程目录是否存在
	//不建议使用，推荐使用AgentFTPRCallservice
	public static void checkRemoteDirPath(String ip, int port, String path) {
		AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, path);
		String[] cmds = new String[6];
		cmds[0] = "path=" + path;
		cmds[1] = "if [ ! -d \"$path\" ];then";
		cmds[2] = "echo false";
		cmds[3] = "else";
		cmds[4] = "echo true";
		cmds[5] = "fi";
		ShExecRsBean bean = session.sendCmds(cmds);
		if (bean.getRs_msg().trim().equals("false")) {
			throw new AppException("远程目录:" + path + " 不存在");
		}
	}

	// 检测远程文件是否存在
	//不建议使用，推荐使用AgentFTPRCallservice
	public static void checkRemoteFilePath(String ip, int port, String path) {
		AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, path);
		String[] cmds = new String[6];
		cmds[0] = "path=" + path;
		cmds[1] = "if [ ! -f \"$path\" ];then";
		cmds[2] = "echo false";
		cmds[3] = "else";
		cmds[4] = "echo true";
		cmds[5] = "fi";
		ShExecRsBean bean = session.sendCmds(cmds);
		if (bean.getRs_msg().trim().equals("false")) {
			throw new FileNotExistException().addScene("FILE", "远程文件：" + path);
		}
	}

	
	/**
	 * Description: 启动或停止Agent工作进程
	 * operation 1.start   2.stop
	 */
	public static boolean startOrStopWorkProc(String ip, int port, int operation){
		boolean success = false;
		try {
			// 与服务端建立连接
			Socket client = new Socket(ip, port);
			// 建立连接后就可以往服务端写数据了
			Writer writer = new OutputStreamWriter(client.getOutputStream());
			if(operation == 1){
				logger.debug("start work process...");
				writer.write(AgentCMDUtil.START_WORK_PROC_CMD);
			}else if(operation == 2){
				logger.debug("stop work process...");
				writer.write(AgentCMDUtil.STOP_WORK_PROC_CMD);
			}
			writer.write("eof\n");
			writer.flush();
			// 写完以后进行读操作
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String temp;
			int index;
			while ((temp = br.readLine()) != null) {
				if ((index = temp.indexOf("eof")) != -1) {
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			if(!Assert.isEmpty(sb.toString()) && (sb.toString().trim().equals(AgentCMDUtil.START_WORK_PROC_CMD) || sb.toString().trim().equals(AgentCMDUtil.STOP_WORK_PROC_CMD))){
				success = true;
			}
			logger.debug("I'm Client, Server say: " + sb);
			writer.close();
			br.close();
			client.close();
		} catch (UnknownHostException e) {
			logger.debug(e.getMessage(), e);
		} catch (IOException e) {
			logger.debug(e.getMessage(), e);
		}
		return success;
	}
}
