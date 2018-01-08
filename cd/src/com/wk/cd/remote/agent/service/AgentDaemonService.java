package com.wk.cd.remote.agent.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.remote.agent.util.AgentCMDUtil;
import com.wk.cd.remote.agent.util.StreamWatch;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.StringUtil;

/**
 * Class Description: 守护进程
 * 
 * @author 12049
 */
public class AgentDaemonService {

	private static final Log logger = LogFactory.getLog();

	private static final String PROP_VM_ARGS = "vmargs";

	private static final String PROP_MAIN_CLASS = "main_class";

	private static final String PROP_VFRAME_HOME = "vframe.home";
	
	private static boolean isCreateSocket = false;
	
	private static ProcessWatcher watcher;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		watcher = new ProcessWatcher();
		watcher.start();
		watcher.join();
	}
	
	/**
	 * Description: 创建守护进程 在守护进程中通过java -cp main.class 启动工作进程
	 */
	static class ProcessWatcher extends Thread {

		private static final int TRY_RESTART_MAX_TIMES = 3;

		private static final ProcessBuilder pb = getProcessBuilder();

		private static ProcessBuilder getProcessBuilder() {

			final String sep = File.separator;

			String java_home = System.getenv("JAVA_HOME");	
			if (StringUtil.isEmpty(java_home)) { // 如果JAVA_HOME环境变量没有设，取缺省路径
				java_home = System.getProperty("java.home");
			}

			final String vmargs = System.getProperty(PROP_VM_ARGS);
			final String classpath = System.getProperty("java.class.path");
			// 工作进程启动类，缺省为com.wk.cd.remote.agent.service.AgentServerStartup
			final String main_class_name = System.getProperty(PROP_MAIN_CLASS,
					AgentServerStartup.class.getName());

			final List<String> args = new ArrayList<String>();
			// java命令
			StringBuffer sb = new StringBuffer();
			sb.append(java_home).append(sep).append("bin").append(sep)
					.append("java");
			args.add(sb.toString());
			// vmargs
			if (!StringUtil.isEmpty(vmargs)) {
				final String[] ss = vmargs.split(" ");
				for (String s : ss) {
					args.add(s);
				}
			}
			// classpath
			args.add("-cp");
			args.add(classpath);

			// vframe.home
			final String vframe_home = System.getProperty(PROP_VFRAME_HOME);
			if (StringUtil.isEmpty(vframe_home)) {
				throw new IllegalArgumentException("VFRAME_HOME not found");
			} else {
				sb = new StringBuffer();
				sb.append("-D").append(PROP_VFRAME_HOME).append("=")
						.append(vframe_home);
				args.add(sb.toString());
			}
			// encoding
			args.add("-Dfile.encoding=GBK");
			args.add("-Dmax_buffer_capacity=1024000");
			// codegen dir
			sb = new StringBuffer();
			sb.append("-Dcodegen.dir=").append(vframe_home)
					.append(File.separator).append("temp");
			args.add(sb.toString());
			// main class
			args.add(main_class_name);

			args.add(CfgTool.getProjectPropterty("cms.agent.port"));
			final ProcessBuilder builder = new ProcessBuilder(args)
					.redirectErrorStream(true);

			logger.info("Daemon start up Agent Server");

			sb = new StringBuffer();
			for (String s : args) {
				sb.append(s).append(" ");
			}
			logger.info("Instance start shell : ");
			logger.info("--------------------------------------------");
			logger.info(sb.toString());
			logger.info("--------------------------------------------");

			return builder;
		}

		private Process process;
		private int retry_times = 0;
		private boolean shutdown = false;

		public boolean isShutdown() {
			return shutdown;
		}

		public void shutdown() {
			shutdown = true;
			if(!Assert.isEmpty(process)){
				process.destroy();
			}
			logger.info("Agent Server Stopped.");
		}

		@Override
		public void run() {
			
			while (true) {
				try {
					process = startInstance();
					//工作进程的起动与停止
					if(!isCreateSocket){
						isCreateSocket = true;
						new Thread(new Runnable() {
							@Override
							public void run() {
								socketServer();
							}
						}).start();;
					}
					
					final int exit_value = process.waitFor();
					// 重启进程
					if (exit_value != 0
							&& (retry_times < TRY_RESTART_MAX_TIMES)
							&& !shutdown) {
						logger.debug("Process exit abnormally : " + exit_value);
						logger.debug("Process restart [" + (++retry_times)
								+ "] times.");
					} else if (shutdown) {
						return;
					} else if (exit_value == 0) {
						shutdown = true;
						logger.debug("Normally stopped, watcher exit.");
						return;
					} else if (retry_times == TRY_RESTART_MAX_TIMES) {
						shutdown = true;
						logger.debug("Try restart fail, watcher exit.");
						return;
					} else {
						shutdown = true;
						logger.debug("Exit[" + exit_value + "], " + "restart ["
								+ retry_times + "] times, Watcher exit.");
						return;
					}
				} catch (InterruptedException e) {
					logger.debug("Interrupted.");
					return;
				} catch (Exception e) {
					logger.debug("Process error");
					e.printStackTrace();
					return;
				}
			}
		}

		private Process startInstance() {
			try {
				Process process = pb.start();
				// 在进程中开启线程，读取进程中的相关数据流
				StreamWatch errorWatch = new StreamWatch(
						process.getErrorStream(), "ERROR");

				StreamWatch outputWatch = new StreamWatch(
						process.getInputStream(), "OUTPUT");
				errorWatch.start();
				outputWatch.start();
				return process;
			} catch (IOException e) {
				throw new IllegalStateException("启动工作进程失败", e);
			}
		}
	}

	public static void socketServer() {
		try {
			 //定义一个ServerSocket监听在端口38888上  
			ServerSocket server = new ServerSocket(Integer.parseInt(CfgTool.getProjectPropterty("cms.agent.daemon.port")));  
			
			logger.debug("Deamon socket start success");
			 while (true) {  
			    //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
			    Socket socket = server.accept();  
			    logger.debug("Deamon socket connect success");
			    //每接收到一个Socket就建立一个新的线程来处理它  
			    new Thread(new Task(socket)).start();  
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * 用来处理Socket请求的
	 */
	static class Task implements Runnable {

		private Socket socket;
		
		public Task(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				handleSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 跟客户端Socket进行通信
		 * 
		 * @throws Exception
		 */
		private void handleSocket() throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String temp;
			int index;
			while ((temp = br.readLine()) != null) {
				if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			if(!Assert.isEmpty(sb)){
				if(sb.toString().equals(AgentCMDUtil.STOP_WORK_PROC_CMD)){
					watcher.shutdown();
					logger.debug("销毁process");
				}else if(sb.toString().equals(AgentCMDUtil.START_WORK_PROC_CMD)){
					logger.debug("启动process");
					watcher = new ProcessWatcher();
					watcher.start();
				}
			}
			logger.debug("I'm Client, Server say: " + sb);
			// 读完后写一句
			Writer writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write(sb.toString());
			writer.write("eof\n");
			writer.flush();
			writer.close();
			br.close();
			socket.close();
		}
	}
}
