package com.wk.cd.remote.agent.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.ScriptUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.bean.EnvDirInfo;
import com.wk.cd.remote.agent.bean.ProcessInfo;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentCMDUtil;
import com.wk.cd.remote.agent.util.AgentHelperUtil;
import com.wk.cd.remote.agent.util.WriterFile;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Agent Python任务
 * 
 * @author 12049
 */
public class PythonAgent {

	private static final Log logger = LogFactory.getLog();
	private static final boolean isWindows = AgentHelperUtil.isWindowsOS(); // 检测运行环境是linux还是windows

	public ShellBean execPythonCmd(ShellBean isbBean, ShellBean osbean,
			Map<String, EnvDirInfo> envDirMap,  Map<String, String> filePathMap, Map<String, ShellBean> beanMap) {

		return pythonTask(isbBean, osbean, envDirMap, filePathMap, beanMap);
	}

	private ShellBean pythonTask(ShellBean isbean, ShellBean osbean,
			Map<String, EnvDirInfo> envDirMap,  Map<String, String> filePathMap, Map<String, ShellBean> beanMap) {
		String cmd_str = DESUtil.decrypt(isbean.getShell());
		String id = isbean.getId();
		boolean isStepSupport = isbean.isStepSupport();
		IMPL_TYPE imType = isbean.getImpl_type();
		int type = isbean.getType();                      // Agent执行类型 1.正常的命令 2.交互式

		//手动停止进程
		if (Assert.notEmpty(cmd_str) && cmd_str.startsWith(AgentCMDUtil.STOP_EXE_PROCESS_CMD)) {
			return killExecProcess(id, osbean, beanMap);
		}
		
		//生成执行脚本
		if (isStepSupport && !cmd_str.equals(AgentCMDUtil.INTERACT_GET_RS_CMD) && !cmd_str.startsWith("put ") && !cmd_str.equals("cd")) {
			String shellFileAbsPath = createShell(id, envDirMap, cmd_str,
					imType, isbean.getRemote_temp_path());
			if (Assert.notEmpty(shellFileAbsPath)) {
				if (imType == IMPL_TYPE.PYTHON2) {
					cmd_str = "python2 " + shellFileAbsPath;
				} else if (imType == IMPL_TYPE.PYTHON3) {
					cmd_str = "python3 " + shellFileAbsPath;
				}
				filePathMap.put(id, shellFileAbsPath);  //将脚本文件的全路径保存到map中，用于后面的删除文件
			}
		}

		osbean = exeScriptAndRecv(cmd_str, isStepSupport, type, id, imType, osbean, envDirMap, beanMap);

		
		/**
		 * 删除已经执行完的脚本
		 * 1 正常的执行脚本，执行完删除
		 * 2执行交互式脚本，在交互结束时删除
		 */
		if((type == 1 || (type == 2 && osbean.getExitStatus() == 0)) && isStepSupport){
			if(Assert.notEmpty(filePathMap)){
				for (Map.Entry<String, String> entry : filePathMap.entrySet()) {
					if (entry.getKey().equals(id)) {
						checkFileExist(filePathMap.get(id));
					}
				}
			}
		}
		
		return osbean;
	}

	/**
	 * Description: 手动杀死执行进程
	 * @param id
	 * @param osbean
	 * @param procMap
	 * @return
	 */
	private ShellBean killExecProcess(String id, ShellBean osbean, Map<String, ShellBean> beanMap) {
		logger.debug("agent begin kill process id = [{}]", id);
		if(beanMap.containsKey(id)){
			long pid = AgentHelperUtil.getExeProcessId(id);
			if(pid != 0){
				List<String> pid_list = AgentHelperUtil.getSonProcess(pid);
				if(Assert.notEmpty(pid_list)){
					for(String str : pid_list){
						long son_pid = Long.parseLong(str);
						logger.info("agent kill son pid = [{}] ppid = [{}]", son_pid, pid);
						if(son_pid != pid){
							AgentHelperUtil.killProcess(son_pid);
						}
					}
				}
				AgentHelperUtil.killProcess(pid);
			}
			logger.debug("exec prcoess destory pid = [{}]", pid);
			beanMap.remove(id);
		}
		osbean.setId(id);
		osbean.setShell(AgentCMDUtil.STOP_EXE_PROCESS_CMD);
		osbean.setRs_flag("ok");
		// 生成密钥 shell&rs_flag&result组成密钥
		osbean = AgentHelperUtil.getMD5Code(osbean);
		logger.debug("agent end kill process id = [{}]", id);
		return osbean;
	}
	
	/**
	 * 生成shell脚本
	 */
	public static String createShell(String id,
			Map<String, EnvDirInfo> envDirMap, String cmd_str,
			IMPL_TYPE impl_type, String remote_path) {
		File local_dir = null;
		// 遍历map，读取env和local_dir
		for (Map.Entry<String, EnvDirInfo> entry : envDirMap.entrySet()) {
			if (entry.getKey().equals(id)) {
				EnvDirInfo info = entry.getValue();
				local_dir = info.getFile_dir();
			}
		}

		if(!Assert.isEmpty(remote_path)){
			remote_path = remote_path.endsWith("/") ? remote_path : remote_path + "/";
		}
		
		String path = "";
		if (!Assert.isEmpty(local_dir)) {
			path = local_dir.getPath().endsWith("/") ? local_dir.getPath() + remote_path
					: local_dir.getPath() + "/" + remote_path;
		}

		WriterFile wf = new WriterFile();
		String fileName = id + ".py";
		String content = "#!/usr/bin/env python\n"
				+ "# -*- coding: UTF-8 -*-\n" + cmd_str;

		wf.SetFileName(fileName);
		wf.SetFilePath(path);
		wf.SetFileContent(content, "utf-8");
		if (wf.IsSuccess()) {
			logger.info("agent[{}] create python script success.", id);
			return path + fileName;
		} else {
			logger.info("agent[{}] create python script fail.", id);
			return null;
		}
	}

	/**
	 * Description: 执行shell命令，并返回执行结果
	 * 
	 * @param cmd_str
	 * @param isStepSupport
	 * @param type
	 * @param id
	 * @param osbean
	 * @return ShellBean
	 */

	private static ShellBean exeScriptAndRecv(String cmd_str,
			boolean isStepSupport, int type, String id, IMPL_TYPE impl_type,
			ShellBean osbean, Map<String, EnvDirInfo> envDirMap, Map<String, ShellBean> beanMap) {

		// 需要单独执行的环境变量命令，不需要添加到StringBuffer中
		boolean isAddRs = true;
		/**
		 * 对用分号连接的多条命令进行分割判断
		 */
		String[] cmds = null;
		if (isNeedSplit(cmd_str)) {
			cmds = cmd_str.split(";");
		} else {
			cmds = new String[] { cmd_str };
		}

		String[][] shell = new String[cmds.length][3];
		Map<String, String> env = new TreeMap<String, String>();
		for (int i = 0; i < cmds.length; i++) {
			shell[i][0] = getShell();
			if (isWindows) {
				shell[i][1] = "/c";
			} else {
				shell[i][1] = "-c";
			}
			shell[i][2] = changeCmdForExport(addPwdForCd(cmds[i]));

			if (Assert.isEmpty(shell[i][2])) {
				logger.warn("command is null");
				continue;
			}
		}
		String rs = "";
		String cmd = "";
		StringBuffer bf = new StringBuffer();
		try {
			String cur_ldir = "";
			File local_dir = null;

			for (int j = 0; j < shell.length; j++) {

				cmd = shell[j][2];
				if (Assert.isEmpty(cmd)) {
					continue;
				}
				logger.debug("execute script={}", cmd);
				if (Assert.isEmpty(local_dir)) {
					// 遍历map，读取env和local_dir
					for (Map.Entry<String, EnvDirInfo> entry : envDirMap
							.entrySet()) {
						if (entry.getKey().equals(id)) {
							EnvDirInfo envDirInfo = entry.getValue();
							env = envDirInfo.getEnv();
							local_dir = envDirInfo.getFile_dir();
						}
					}
				}

				ProcessInfo info = ScriptUtil.execScript1(shell[j], env,
						local_dir, false, isStepSupport, type, id, impl_type, beanMap);

				osbean.setPid(info.getPid());
				logger.debug("agent[{}] inputShell:[{}], outputResult:[{}]",
						id, cmd_str, info.getResult());

				isAddRs = true;
				/**
				 * 判断环境变量是否需要单独执行命令 需要单独执行的，将结果以"set/export a=xxx"的形式保存到envMap中
				 */
				if ((!isWindows && cmds[j].startsWith("export "))
						|| (isWindows && cmds[j].startsWith("set "))) {
					String[] v = cmds[j].split(" ", 2);
					String[] prop = v[1].split("=");
					if (prop.length > 1 && prop[1].startsWith("`")
							&& prop[1].endsWith("`")) {
						if (isWindows) {
							putEnv(env,
									"set " + prop[0] + "=" + info.getResult());
						} else {
							putEnv(env,
									"export " + prop[0] + "="
											+ info.getResult());
						}
						isAddRs = false;
					} else {
						putEnv(env, shell[j][2]);
					}
				}

				// 保存环境变量和dir
				if (cmd.startsWith("cd ") || cmd.startsWith("cd;")
						|| cmd.equals("cd") || cmd.equals("cd&cd")) {
					// 根据id保存环境变量和local_dir
					putEnvAndDir(envDirMap, env, changeDir(info.getResult()
							.replaceAll("\r|\n", "")), id);
				} else {
					// 根据id保存环境变量和local_dir
					putEnvAndDir(envDirMap, env, local_dir, id);
				}

				if (!Assert.isEmpty(info)) {
					if (isAddRs)
						bf.append(info.getResult());
					// 进程id和进程结束状态
					osbean.setExitStatus(info.getExitStatus());
				}
			}
			if (osbean.getExitStatus() != 0) {
				osbean.setRs_flag("fail");
			} else {
				osbean.setRs_flag("ok");
			}
			rs = bf.toString();
		} catch (Exception e) {
			logger.error("execute agent shell error", e);
			rs = bf.toString() + e.getMessage();
			osbean.setRs_flag("fail");
		}
		osbean.setResult(rs);
		// 生成密钥 shell&rs_flag&result组成密钥
		osbean = AgentHelperUtil.getMD5Code(osbean);

		return osbean;
	}

	// 判断命令是否需要分割
	private static boolean isNeedSplit(String cmd_str) {
		String[] cmds = cmd_str.split(";");
		for (String cmd : cmds) {
			if (cmd.startsWith("cd ") || cmd.startsWith("cd;")
					|| cmd.equals("cd") || cmd.startsWith("export ")
					|| cmd.startsWith("set ")) {
				return true;
			}
		}

		return false;
	}

	private static String getShell() {
		if (isWindows) {
			return "cmd.exe";
		}
		return "sh";
	}

	/**
	 * 判断是不是以cd开头，若是则添加pwd Description:
	 * 
	 * @param shell
	 * @return
	 */
	private static String addPwdForCd(String shell) {
		StringBuffer sb = new StringBuffer(shell);
		// 当windows运行bat文件是需要一个路径，只要执行一个cd就行，不需要下面的cd&cd
		if (isWindows && shell.equals("cd")) {
			return shell;
		}
		if (shell.startsWith("cd ") || shell.startsWith("cd;")
				|| shell.equals("cd")) {
			return isWindows ? sb.append("&cd").toString() : sb.append(";pwd")
					.toString();
		}
		return shell;
	}
	
	/**
	 * Description: 环境变量是命令行的话，需要执行命令行
	 * 
	 * @param shell
	 * @return
	 */
	private static String changeCmdForExport(String shell) {
		if (isWindows) {
			if (shell.startsWith("set ")) {
				String[] v = shell.split(" ", 2);
				String[] prop = v[1].split("=");
				if (prop[1].startsWith("`") && prop[1].endsWith("`")) {
					return prop[1].substring(1, prop[1].length() - 1);
				}
			}
		} else {
			if (shell.startsWith("export ")) {
				String[] v = shell.split(" ", 2);
				String[] prop = v[1].split("=");
				if (prop[1].startsWith("`") && prop[1].endsWith("`")) {
					return prop[1].substring(1, prop[1].length() - 1);
				}
			}
		}
		return shell;
	}

	/**
	 * Description: 保存环境变量
	 * 
	 * @param env
	 * @param shell
	 * @return
	 */
	private static void putEnv(Map<String, String> env, String shell) {
		if (isWindows) {
			if (shell.startsWith("set ")) {
				String[] v = shell.split(" ");
				String[] prop = v[1].split("=");
				env.put(prop[0], prop[1]);
			}
		} else {
			if (shell.startsWith("export ")) {
				String[] v = shell.split(" ", 2);
				String[] prop = v[1].split("=");
				env.put(prop[0], prop[1]);
			}
		}

	}

	/**
	 * Description: 保存环境变量和进程目录
	 * 
	 * @param envDirMap
	 * @param env
	 * @param local_dir
	 * @param id
	 */
	private static void putEnvAndDir(Map<String, EnvDirInfo> envDirMap,
			Map<String, String> env, File local_dir, String id) {
		EnvDirInfo info = new EnvDirInfo();
		info.setEnv(env);
		info.setFile_dir(local_dir);
		envDirMap.put(id, info);
	}

	private static File changeDir(String path) {
		File f = new File(path);
		logger.debug("change path", f.getPath());
		return f;

	}
	
	/**
	 * 检测脚本文件是否存在，脚本执行完应该删除
	 */
	public static void checkFileExist(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
	}

}
