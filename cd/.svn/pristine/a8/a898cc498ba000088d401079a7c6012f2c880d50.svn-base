package com.wk.cd.remote.agent.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Formatter;
import java.util.StringTokenizer;

import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class SysInfoAcquirerService {

	private static final Log log = LogFactory.getLog();
	
	private static final int CPUTIME = 5000;
	private static final int PERCENT = 100;
	private static final int FAULTLENGTH = 10;
	/**
	 * 此类中Thread.sleep()里的线程睡眠时间
	 */
	private static final int SLEEP_TIME = 1000 * 2;
	/**
	 * 获取Windows环境下网口的上下行速率
	 * 
	 * @return
	 */
	public String[] getNetworkThroughputForWindows() {
		Process pro1 = null;
		Process pro2 = null;
		Runtime r = Runtime.getRuntime();
		BufferedReader input = null;
		String rxPercent = "";
		String txPercent = "";
		try {
			String command = "netstat -e";
			pro1 = r.exec(command);
			input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
			String result1[] = readInLine(input, "windows");
			Thread.sleep(SLEEP_TIME);
			pro2 = r.exec(command);
			input = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
			String result2[] = readInLine(input, "windows");
			rxPercent = formatNumber((Long.parseLong(result2[0]) - Long.parseLong(result1[0]))
					/ (float) (1024 * (SLEEP_TIME / 1000))) + "KB/s"; // 上行速率(KB/s)
			txPercent = formatNumber((Long.parseLong(result2[1]) - Long.parseLong(result1[1]))
					/ (float) (1024 * (SLEEP_TIME / 1000))) + "KB/s"; // 下行速率(KB/s)
			input.close();
			pro1.destroy();
			pro2.destroy();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		System.out.println("下行速率 = " + rxPercent);
		System.out.println("上行速率 = " + txPercent);
		String[] net_work = {rxPercent, txPercent};
		return net_work;
	}


	/**
	 * 获取windows环境下JVM的cpu占用率
	 * 
	 * @return
	 */
	public String getCPURateForWindows() {
		try {
			String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe  process "
					+ "  get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// 取进程信息
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				long cpuRate = PERCENT * (busytime) / (busytime + idletime);
				if (cpuRate > 100) {
					cpuRate = 100;
				} else if (cpuRate < 0) {
					cpuRate = 0;
				}
				return String.valueOf(PERCENT * (busytime) / (busytime + idletime));

			} else {
				return "0.0";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "0.0";
		}
	}
	
	/**
	 * 
	 * 读取CPU信息
	 * 
	 * @param proc
	 * @return
	 * 
	 */
	private long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < FAULTLENGTH) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			// Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = this.substring(line, capidx, cmdidx - 1).trim();
				String cmd = this.substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("javaw.exe") >= 0) {
					continue;
				}
				// log.info("line="+line);
				if (caption.equals("System Idle Process") || caption.equals("System")) {
					idletime += Long.valueOf(this.substring(line, kmtidx, rocidx - 1).trim()).longValue();
					idletime += Long.valueOf(this.substring(line, umtidx, wocidx - 1).trim()).longValue();
					continue;
				}

				kneltime += Long.valueOf(this.substring(line, kmtidx, rocidx - 1).trim()).longValue();
				usertime += Long.valueOf(this.substring(line, umtidx, wocidx - 1).trim()).longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
	 * 
	 * @param src
	 *            要截取的字符串
	 * @param start_idx
	 *            开始坐标（包括该坐标)
	 * @param end_idx
	 *            截止坐标（包括该坐标）
	 * @return
	 */
	private String substring(String src, int start_idx, int end_idx) {
		byte[] b = src.getBytes();
		String tgt = "";
		for (int i = start_idx; i <= end_idx; i++) {
			tgt += (char) b[i];
		}
		return tgt;
	}
	
	/**
	 * 格式化浮点数(float 和 double)，保留两位小数
	 * 
	 * @param obj
	 * @return
	 */
	private static String formatNumber(Object obj) {
		String result = "";
		if (obj.getClass().getSimpleName().equals("Float")) {
			result = new Formatter().format("%.2f", obj).toString();
		} else if (obj.getClass().getSimpleName().equals("Double")) {
			result = new Formatter().format("%.2f", obj).toString();
		}
		return result;
	}

	/**
	 * 获取网口上下行速率
	 * 
	 * @param input
	 * @return
	 */
	public static String[] readInLine(BufferedReader input, String osType) {
		String rxResult = "";
		String txResult = "";
		StringTokenizer tokenStat = null;
		try {
			if (osType.equals("linux")) { // 获取linux环境下的网口上下行速率
				String result[] = input.readLine().split(" ");
				int j = 0, k = 0;
				for (int i = 0; i < result.length; i++) {
					if (result[i].indexOf("RX") != -1) {
						j++;
						if (j == 2) {
							rxResult = result[i + 1].split(":")[1];
						}
					}
					if (result[i].indexOf("TX") != -1) {
						k++;
						if (k == 2) {
							txResult = result[i + 1].split(":")[1];
							break;
						}
					}
				}

			} else { // 获取windows环境下的网口上下行速率
				input.readLine();
				input.readLine();
				input.readLine();
				input.readLine();
				tokenStat = new StringTokenizer(input.readLine());
				tokenStat.nextToken();
				rxResult = tokenStat.nextToken();
				txResult = tokenStat.nextToken();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		String arr[] = { rxResult, txResult };
		return arr;
	}
}
