/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.common.util;

import com.wk.cd.remote.exc.ScriptExecErrorException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.bean.ProcessInfo;
import com.wk.cd.remote.agent.util.AgentHelperUtil;
import com.wk.cd.remote.agent.util.StreamInfo;
import com.wk.cd.remote.agent.util.StreamReader;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ScriptUtil {
	private static final String INTERACT_GET_RS_CMD = "$INTERACT_GET_RS_MSG$";     //交互式Agent获取数据命令
	private final static Map<String, StreamInfo> streamMap = new HashMap<String, StreamInfo>();
	private static final Log logger = LogFactory.getLog();

	public static String execScript(String[] cmd, boolean str_mod) {
		return execScript(cmd, null, null, str_mod);
	}

	public static String execScript(String[] cmd, Map<String, String> env,
			File local_dir, boolean str_mod) {
		Process proc = null;
		String cmd_str = "";
		String rs = new String();
		String ers = new String();
		String all_cmd = "";
		InputStream in = null;
		InputStream ein = null;
		String[] envp = null;
		if ((env != null) && (env.size() > 0)) {
			envp = new String[env.size()];
			int i = 0;
			StringBuffer bf = new StringBuffer();
			bf.append("\n");
			for (Map.Entry ent : env.entrySet()) {
				envp[i] = ((String) ent.getKey()) + "="
						+ ((String) ent.getValue());
				bf.append(envp[i] + "\n");
				++i;
			}
			logger.debug("env_param[{}]", bf.toString());
		}
		if (local_dir != null)
			logger.debug("local_dir=[{}]", local_dir.getPath());
		StringBuffer cms_b = new StringBuffer();
		for (int i = 0; i < cmd.length; ++i) {
			cms_b.append(cmd[i] + " ");
		}
		all_cmd = cms_b.toString().trim();
		logger.debug("cmd={}", all_cmd);
		try {
			logger.debug("begin running script");
			if (str_mod) {
				cmd_str = StringUtil.ary2str(cmd, " ");
				proc = Runtime.getRuntime().exec(cmd_str, envp, local_dir);
			} else {
				proc = Runtime.getRuntime().exec(cmd, envp, local_dir);
			}
			logger.debug("begin read InputStream");
			in = proc.getInputStream();
			ein = proc.getErrorStream();
			rs = getRetureString(in);
			ers = getRetureString(ein);
			logger.debug("end read InputStream");
			if (!(Assert.isEmpty(rs))) {
				logger.info(rs);
			}
			if (!(Assert.isEmpty(ers))) {
				logger.warn("script execute error! message=[{}]", ers);
				rs = ers;
			}
			close(in);
			close(ein);
			logger.debug("begin waitfor");
			proc.waitFor();
			logger.debug("end waitfor");
		} catch (IOException e) {
			String err = " cmd=[" + all_cmd + "]\n" + e.toString();
			logger.error(err, e);
			throw new ScriptExecErrorException().addScene("SCRIPT", err);
		} catch (InterruptedException e) {
			String err = " cmd=[" + all_cmd + "]\n" + e.toString();
			logger.error(err, e);
			throw new ScriptExecErrorException().addScene("SCRIPT", err);
		} finally {
			if (proc != null) {
				proc.destroy();
			}
			close(in);
		}
		return rs;
	}
	
	public static ProcessInfo execScript1(String[] cmd, Map<String, String> env, File local_dir, boolean str_mod, boolean isStepSupport, int type, String id, IMPL_TYPE impl_type, Map<String, ShellBean> beanMap){
		ProcessInfo processInfo = new ProcessInfo();
		boolean isExeStream2str = false;
		Process proc = null;
		String cmd_str = "";
		String rs = new String();
		String ers = new String();
		String all_cmd = "";
		InputStream in = null;
		InputStream ein = null;
		OutputStream os = null;
		String[] envp = null;
		
		if(env!=null && env.size()>0){
			envp = new String[env.size()];
			int i=0;
			StringBuffer bf = new StringBuffer();
			bf.append("\n");
			for(Map.Entry<String, String> ent : env.entrySet()){
				envp[i] = ent.getKey()+"="+ent.getValue();
				bf.append(envp[i] + "\n");
				i++;
			}
			logger.debug("env_param[{}]", bf.toString());
		}
		
		if(local_dir!=null) logger.debug("local_dir=[{}]", local_dir.getPath());

		//截取nohup与&中间的一段进行执行
		if(!Assert.isEmpty(cmd) && cmd[2].startsWith("nohup") && cmd[2].endsWith("&")){
			cmd[2] = cmd[2].substring(5, cmd[2].length()-1);
			isExeStream2str = true;
		}
		
		StringBuffer cms_b = new StringBuffer();
		for(int i=0; i<cmd.length; i++){
			cms_b.append(cmd[i]+" ");
		}
		all_cmd = cms_b.toString().trim();
		logger.debug("cmd={}", all_cmd);

		/**
		 * 交互式Agent操作		
		 *   1.INTERACT_GET_RS_CMD 读取数据流中的数据
		 *   2.put xxx  向数据流中写入数据
		 */
		if(type == 2){
			if(cmd[2].equals(INTERACT_GET_RS_CMD)){
				return getInputStreamData(cmd[2], type, id);
			}
			if(cmd[2].startsWith("put ")){
				return writeToOutputStream(cmd[2], id);
			}
		}
		
		/**
		 * 执行开始时间
		 */
		if(beanMap.containsKey(id)){
			beanMap.get(id).setBeginTime(System.currentTimeMillis());
		}
		
		try{
			logger.info("agent[{}] begin running script", id);
			if(str_mod) {
				cmd_str = StringUtil.ary2str(cmd, " ");
				proc = Runtime.getRuntime().exec(cmd_str, envp, local_dir);
			}else{
				if(isStepSupport && !AgentHelperUtil.isWindowsOS()){
					String[] chmod = {"sh", "-c", "chmod 777 " + cmd[2]};
					proc = Runtime.getRuntime().exec(chmod);
					proc.waitFor();
					if(proc != null){
						proc.destroy();
					}
				}
				proc = Runtime.getRuntime().exec(cmd, envp, local_dir);
			}
			
			/**
			 * 避免nohup导致请求超时
			 */
			if(!isExeStream2str){
				in = proc.getInputStream();
				ein = proc.getErrorStream();
				os = proc.getOutputStream();
				logger.info("agent[{}] begin read InputStream", id);
				
				if(type == 2 && !cmd[2].equals("cd;pwd") && !cmd[2].equals("cd")){
					
					StreamInfo info= new StreamInfo();
					info.setIs(in);
					info.setOs(os);
					StreamReader srin = new StreamReader(in, proc);
					srin.start();
					   
					StreamReader srerror = new StreamReader(ein, proc);
					srerror.start();
					info.setSrin(srin);
					streamMap.put(id, info);
					//根据执行文件的文件名去获取执行pid,交互式
					processInfo.setPid(AgentHelperUtil.getExeProcessId(cmd[2]));
				}else{
					rs = getRetureString(in);
					ers = getRetureString(ein);
				}
			}
			
			logger.info("agent[{}] end read InputStream", id);
			if(!Assert.isEmpty(rs)) {
				logger.info(rs);
			}
			if(!Assert.isEmpty(ers)) {
				logger.warn("script execute error! message=[{}]", ers);
				rs = ers;
			}
			
			/**
			 * 只返回一个pid的就不需要进行进程等待
			 * 退出状态不需要取
			 */
			if(!isExeStream2str){
				if(!(!cmd[2].equals("cd;pwd") && type == 2)){
					logger.debug("begin process waitfor");
					proc.waitFor();
					processInfo.setExitStatus(proc.exitValue());
					
					if(!Assert.isEmpty(ers)){
						processInfo.setExitStatus(1);         //若errorStream有值，那么进程状态为错误
					}
					logger.debug("end process waitfor");
				}
			}
			
		}catch (IOException e){
			String err = "this is cmd=["+all_cmd+"]\n"+ e.getMessage();
			logger.error(err+ "INEXCEPTION 666", e + "INEXCEPTION 666");
			throw new ScriptExecErrorException().addScene("SCRIPT",
					err);
		}catch (InterruptedException e){
			String err = " cmd=["+all_cmd+"]\n"+ e.getMessage();
			logger.error(err, e);
			throw new ScriptExecErrorException().addScene("SCRIPT",
					err);
		}finally{
			
			/**
			 * 只有交互式的脚本不需要关闭流，其他情况都需要关闭
			 */
			if(!(!cmd[2].equals("cd;pwd") && type == 2)){
				close(in);
				close(ein);
			}
			
			if(type == 1 && proc != null){
				proc.destroy();
			}
		}
		
		processInfo.setResult(rs);
		processInfo.setProc(proc);
		return processInfo;
	}	

	public static void execScriptAsyn(String[] cmd, boolean str_mod) {
		Process proc = null;
		try {
			logger.debug("begin runn script! ");
			if (str_mod) {
				String cmd_str = StringUtil.ary2str(cmd, " ");
				proc = Runtime.getRuntime().exec(cmd_str);
			} else {
				proc = Runtime.getRuntime().exec(cmd);
			}

			InputStream in = proc.getInputStream();
			close(in);

			Thread.sleep(2000L);
			logger.debug("end runn script! ");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT",
					StringUtil.ary2str(cmd, " "));
		} finally {
			if (proc != null)
				proc.destroy();
		}
	}
	
	/**
	 * 获取inputStream中的数据
	 */
	private static ProcessInfo getInputStreamData(String cmd, int type, String id){
		ProcessInfo processInfo = new ProcessInfo();
	
		logger.info("agent[{}] begin get inputStream Data", id);
		
		if(streamMap.containsKey(id)){
			StreamInfo info = streamMap.get(id);
			StreamReader reader = info.getSrin();
			String rs = reader.getOutput().toString();
			logger.debug("id={},rs = {}", id,rs);
			reader.setOutput(new StringBuffer());
			
			processInfo.setResult(rs);
			int exitStatus = reader.getExitStatus();
			if(exitStatus == 0){
				closeStream(id);
			}
			processInfo.setExitStatus(exitStatus);
			logger.debug("agent[{}] get inputstream data [{}] and read stream process exit status[{}]", id, rs, exitStatus);
		}
//		for (Map.Entry<String, StreamInfo> entry : streamMap.entrySet()) {
//			if (entry.getKey().equals(id)) {
//				StreamInfo info = entry.getValue();
//				StreamReader reader = info.getSrin();
//				String rs = reader.getOutput().toString();
//				logger.debug("id={},rs = {}", id,rs);
//				reader.setOutput(new StringBuffer());
//				
//				processInfo.setResult(rs);
//				int exitStatus = reader.getExitStatus();
//				if(exitStatus == 0){
//					closeStream(id);
//				}
//				processInfo.setExitStatus(exitStatus);
//				logger.debug("agent[{}] get inputstream data [{}] and read stream process exit status[{}]", id, rs, exitStatus);
//			}
//		}
		logger.info("agent[{}] end get inputStream Data", id);
		return processInfo;
	}
	
	/**
	 * 向outputStream中写入数据
	 */
	private static ProcessInfo writeToOutputStream(String cmd, String id){
		ProcessInfo processInfo = new ProcessInfo();
		
		logger.info("agent[{}] begin write to outputStream", id);
		
		if(streamMap.containsKey(id)){
			StreamInfo info = streamMap.get(id);
			OutputStream out = info.getOs();
			try {
				out.write(cmd.split(" ", 2)[1].getBytes());
				logger.debug("agent[{}] write outputstream data [{}] ", id, cmd.split(" ", 2)[1]);
				out.flush();
			} catch (IOException e) {
				logger.error("write to os error", e);
			}
		}
//		for (Map.Entry<String, StreamInfo> entry : streamMap
//				.entrySet()) {
//			if (entry.getKey().equals(id)) {
//				StreamInfo info = entry.getValue();
//				OutputStream out = info.getOs();
//				try {
//					out.write(cmd.split(" ", 2)[1].getBytes());
//					logger.debug("agent[{}] write outputstream data [{}] ", id, cmd.split(" ", 2)[1]);
//					out.flush();
//				} catch (IOException e) {
//					logger.error("write to os error", e);
//				}
//			}
//		}
		logger.info("agent[{}] end write to outputStream", id);
		return processInfo;
	}

	private static long getExeProcessId(String cmd){
		String pid = null;
		String[] cmds = {"sh", "-c", "ps -ef | grep " + cmd + " | grep -v grep | awk '{print $2}'"};
		String rs = "";
		Process proc = null;
		InputStream in = null;
		try {
			proc = Runtime.getRuntime().exec(cmds);
			in = proc.getInputStream();
			rs = getRetureString(in);
			
			proc.waitFor();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(!Assert.isEmpty(proc)){
				proc.destroy();
			}
			if(!Assert.isEmpty(in)){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		
		return Assert.isEmpty(rs) ? 0 : Long.parseLong(rs.trim());
	}
	
	/**
	 * 关闭数据流
	 */
	private static void closeStream(String id){
		logger.info("agent[{}] begin close to stream", id);
		
		if(streamMap.containsKey(id)){
			StreamInfo info = streamMap.get(id);
			OutputStream out = info.getOs();
			InputStream is = info.getIs();
			try {
				is.close();
				out.close();
			} catch (IOException e) {
				logger.error("close stream error", e);
			}
		}
//		for (Map.Entry<String, StreamInfo> entry : streamMap
//				.entrySet()) {
//			if (entry.getKey().equals(id)) {
//				StreamInfo info = entry.getValue();
//				OutputStream out = info.getOs();
//				InputStream is = info.getIs();
//				try {
//					is.close();
//					out.close();
//				} catch (IOException e) {
//					logger.error("close stream error", e);
//				}
//			}
//		}
		
		logger.info("agent[{}] end close stream", id);
	}
	
	private static String getRetureString(InputStream in) {
		return StringUtil.stream2str(in);
	}

	private static void close(InputStream in) {
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
	}
}