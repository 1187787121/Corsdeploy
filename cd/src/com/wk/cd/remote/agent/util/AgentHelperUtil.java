package com.wk.cd.remote.agent.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Ps;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.Md5Util;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Agent的帮助类 Class Description:
 * 
 * @author 12049
 */
public class AgentHelperUtil {

	private static final Log logger = LogFactory.getLog();

	public static final String WINDOWS = "win";
	public static final String LINUX = "linux";
	public static final String AIX = "aix";

	public static final String UNAME_CMD = "uname -a";

	public static final String LINE_FEED = "\n";
	public static final String CONNECTOR = "&";

	/**
	 * 获取服务器IP地址
	 * 
	 * @return String
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getServerIP() throws SocketException,
			UnknownHostException {
		if (isWindowsOS()) {
			return InetAddress.getLocalHost().getHostAddress();
		} else {
			return getLinuxLocalIp();
		}
	}

	/**
	 * 判断操作系统是否是Windows
	 *
	 * @return boolean
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取操作系统（Windows, Linux, AIX）
	 */
	public static String getOperateSys() {
		if (isWindowsOS()) {
			return WINDOWS;
		} else {
			String rs = execShell(UNAME_CMD);
			if (Assert.notEmpty(rs) && rs.toLowerCase().indexOf("linux") > -1) {
				return LINUX;
			} else {
				return AIX;
			}
		}
	}

	/**
	 * 获取服务器名称
	 */
	public static String getServerHostName() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

	/**
	 * 获取Linux下的IP地址
	 *
	 * @return IP地址
	 * @throws SocketException
	 */
	private static String getLinuxLocalIp() throws SocketException {
		String ip = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress()
									.toString();
							if (!ipaddress.contains("::")
									&& !ipaddress.contains("0:0:")
									&& !ipaddress.contains("fe80")) {
								ip = ipaddress;
							}
						}
					}
				}
			}
		} catch (SocketException ex) {
			ip = "127.0.0.1";
			ex.printStackTrace();
		}
		return ip;
	}

	/**
	 * 对cmd,rs_flag,result,摘要进行加密，以便传到客户端进行密钥验证 Description:
	 * 
	 * @param osbean
	 * @return
	 */
	public static ShellBean getMD5Code(ShellBean osbean) {
		String md5Str = null;
		if (!Assert.isEmpty(osbean.getShell())) {
			md5Str += osbean.getShell() + CONNECTOR;
		}

		if (!Assert.isEmpty(osbean.getRs_flag())) {
			md5Str += osbean.getRs_flag() + CONNECTOR;
		}

		if (!Assert.isEmpty(osbean.getResult())) {
			md5Str += osbean.getResult() + CONNECTOR;
		}

		if (!Assert.isEmpty(md5Str)) {
			osbean.setDigest(Md5Util.GetMD5Code(md5Str.substring(0,
					md5Str.length() - 1)));
		}
		return osbean;
	}

	/**
	 * 获取当前命令的执行进程 Description:
	 * 
	 * @param cmd
	 * @return
	 */
	public static long getExeProcessId(String cmd) {
		String rs = "";
		if (!isWindowsOS()) {
			String cmd_str = "ps -ef | grep " + cmd
					+ " | grep -v grep | awk '{print $2}'";
			rs = execShell(cmd_str);
		}
		return Assert.isEmpty(rs) ? 0 : Long.parseLong(rs.trim());
	}

	/**
	 * 获取某个进程的所有子进程 Description:
	 * 
	 * @param pid
	 * @return
	 */
	public static List<String> getSonProcess(long pid) {
		List<String> pid_list = new ArrayList<String>();
		String rs = "";
		if (!isWindowsOS()) {
			String cmd = "ps -ef | grep " + pid
					+ " | grep -v grep | awk '{print $2}'";
			rs = execShell(cmd);
		}

		if (Assert.notEmpty(rs)) {
			String[] pids = rs.split(LINE_FEED);
			for (String pid_str : pids) {
				pid_list.add(pid_str);
			}
		}
		return pid_list;
	}

	/**
	 * 杀死某个进程 Description:
	 * 
	 * @param pid
	 */
	public static void killProcess(long pid) {
		String cmd = "";
		if (!isWindowsOS()) {
			cmd = "kill -9 " + pid;
		}
		execShell(cmd);
	}

	/**
	 * 开启进程去执行命令，执行完销毁进程，释放资源 Description:
	 * 
	 * @param cmd
	 * @return
	 */
	public static String execShell(String cmd) {
		String rs = null;
		Process proc = null;
		InputStream in = null;
		try {
			String[] head_cmds = getShell();
			String[] cmds = { head_cmds[0], head_cmds[1], cmd };
			proc = Runtime.getRuntime().exec(cmds);
			in = proc.getInputStream();
			rs = StringUtil.stream2str(in);
			proc.waitFor();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (proc != null) {
				proc.destroy();
			}
		}
		logger.debug("rs = [{}] , {}", rs, cmd);
		return !Assert.isEmpty(rs) ? rs.trim() : rs;
	}

	private static String[] getShell() {
		String[] cmds = new String[2];
		if (isWindowsOS()) {
			cmds[0] = "cmd.exe";
			cmds[1] = "/c";
		} else {
			cmds[0] = "sh";
			cmds[1] = "-c";
		}
		return cmds;
	}

	/**
	 * 获取所有执行进程CPU和内存百分比
	 * 
	 * @throws SigarException
	 */
	public static long[] getExecProcCPUAndMemPct() throws SigarException {
		long[] cpu_mem_arry = new long[2];

		Sigar sigar = new Sigar();
		
//		long pid = sigar.getPid();
//		ProcMem ppMem = sigar.getProcMem(pid);
//		
//		long total_cpu_pct = 0;
//		long total_mem_pct = 0;
//		try {
//			long[] pids = sigar.getProcList();
//			for (long pid_str : pids) {
//				if(pid_str != pid && sigar.getProcState(pid_str).getPpid() == pid){
//					ProcCpu pCpu = sigar.getProcCpu(pid_str);
//					total_cpu_pct += pCpu.getPercent();
//					ProcMem pMem = sigar.getProcMem(pid_str);
//					total_mem_pct += pMem.getResident();
//					logger.info(
//							"son pid = [{}], agent execute pid = [{}]",
//							pid_str, pid);
//				}
//			}
//			cpu_mem_arry[0] = total_cpu_pct;
//			cpu_mem_arry[1] = total_mem_pct * 100 / ppMem.getSize();
//			logger.debug(
//					"son execute process total_cpu_pct = [{}], agent cpu = [{}]",
//					cpu_mem_arry[0], sigar.getProcCpu(pid).getPercent());
//			logger.debug(
//					"son execute process total_mem_pct = [{}], agent mem = [{}]",
//					cpu_mem_arry[1], sigar.getProcMem(pid).getResident());
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
		
		if (isWindowsOS()) {
			long pid = sigar.getPid();
			ProcMem ppMem = sigar.getProcMem(pid);
			
			Ps ps = new Ps();
			long total_cpu_pct = 0;
			long total_mem_pct = 0;
			try {
				long[] pids = sigar.getProcList();
				for (long pid_str : pids) {
					if(pid_str != pid && sigar.getProcState(pid_str).getPpid() == pid){
						ProcCpu pCpu = sigar.getProcCpu(pid_str);
						total_cpu_pct += pCpu.getPercent();
						ProcMem pMem = sigar.getProcMem(pid_str);
						total_mem_pct += pMem.getResident();
					}
				}
				cpu_mem_arry[0] = total_cpu_pct;
				cpu_mem_arry[1] = total_mem_pct * 100 / ppMem.getSize();
			} catch (SigarException e) {
				e.printStackTrace();
			}
		} else {
//			String sys_str = execShell("uname -a");
//			if (Assert.notEmpty(sys_str)
//					&& sys_str.toLowerCase().startsWith("linux")) {
				long pid = sigar.getPid();
				ProcMem ppMem = sigar.getProcMem(pid);

				List<String> son_pid_list = getSonProcess(pid);
				long total_cpu_pct = 0;
				long total_mem_pct = 0;
				if (Assert.notEmpty(son_pid_list)) {
					for (String son_pid : son_pid_list) {
						if (Long.parseLong(son_pid) != pid) {
							ProcCpu pCpu = sigar.getProcCpu(son_pid);
							total_cpu_pct += pCpu.getPercent();
							ProcMem pMem = sigar.getProcMem(son_pid);
							total_mem_pct += pMem.getResident();
							logger.info(
									"son pid = [{}], agent execute pid = [{}]",
									son_pid, pid);
						}
					}
				}
				cpu_mem_arry[0] = total_cpu_pct;
				cpu_mem_arry[1] = total_mem_pct * 100 / ppMem.getSize();
				logger.debug(
						"son execute process total_cpu_pct = [{}], agent cpu = [{}]",
						cpu_mem_arry[0], sigar.getProcCpu(pid).getPercent());
				logger.debug(
						"son execute process total_mem_pct = [{}], agent mem = [{}]",
						cpu_mem_arry[1], sigar.getProcMem(pid).getResident());
			}
//		}
		return cpu_mem_arry;
	}
}
