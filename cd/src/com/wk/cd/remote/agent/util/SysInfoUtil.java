package com.wk.cd.remote.agent.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.DecimalFormat;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.agent.bean.AgentMonitorBasicInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class SysInfoUtil {
//	// �ڵ�cpu���еİٷֱȣ����ΪС��100������
//	private static final String NODE_CPU_CMD = "vmstat |tail -1|awk '{print $16}'";
//
//	// ��ȡCpu����(��λ����)
//	public static int getCpuCount() throws SigarException {
//		Sigar sigar = new Sigar();
//		try {
//			return sigar.getCpuInfoList().length;
//		} finally {
//			sigar.close();
//		}
//	}
//
//	// CPU����������λ��HZ����CPU�������Ϣ
//	public void getCpuTotal() {
//		Sigar sigar = new Sigar();
//		CpuInfo[] infos;
//		try {
//			infos = sigar.getCpuInfoList();
//			for (int i = 0; i < infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
//				CpuInfo info = infos[i];
//				System.out.println("mhz=" + info.getMhz());// CPU������MHz
//				System.out.println("vendor=" + info.getVendor());// ���CPU���������磺Intel
//				System.out.println("model=" + info.getModel());// ���CPU������磺Celeron
//				System.out.println("cache size=" + info.getCacheSize());// ����洢������
//			}
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// CPU���û�ʹ������ϵͳʹ��ʣ�������ܵ�ʣ�������ܵ�ʹ��ռ�����ȣ���λ��100%��
//	public void testCpuPerc() {
//		Sigar sigar = new Sigar();
//		// ��ʽһ����Ҫ�����һ��CPU�����
//		CpuPerc cpu;
//		try {
//			cpu = sigar.getCpuPerc();
//			printCpuPerc(cpu);
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//		// ��ʽ���������ǵ���CPU���Ƕ�CPU������
//		CpuPerc cpuList[] = null;
//		try {
//			cpuList = sigar.getCpuPercList();
//		} catch (SigarException e) {
//			e.printStackTrace();
//			return;
//		}
//		for (int i = 0; i < cpuList.length; i++) {
//			printCpuPerc(cpuList[i]);
//		}
//	}
//
//	private void printCpuPerc(CpuPerc cpu) {
//		System.out.println("User :" + CpuPerc.format(cpu.getUser()));// �û�ʹ����
//		System.out.println("Sys :" + CpuPerc.format(cpu.getSys()));// ϵͳʹ����
//		System.out.println("Wait :" + CpuPerc.format(cpu.getWait()));// ��ǰ�ȴ���
//		System.out.println("Nice :" + CpuPerc.format(cpu.getNice()));//
//		System.out.println("Idle :" + CpuPerc.format(cpu.getIdle()));// ��ǰ������
//		System.out.println("Total :" + CpuPerc.format(cpu.getCombined()));// �ܵ�ʹ����
//	}
//
//	// �ڴ���Դ��Ϣ
//	public static void getPhysicalMemory() {
//		// a)�����ڴ���Ϣ
//		Sigar sigar = new Sigar();
//		Mem mem;
//		try {
//			mem = sigar.getMem();
//			// �ڴ�����
//			System.out.println("Total = " + mem.getTotal() / 1024L + "K av");
//			// ��ǰ�ڴ�ʹ����
//			System.out.println("Used = " + mem.getUsed() / 1024L + "K used");
//			// ��ǰ�ڴ�ʣ����
//			System.out.println("Free = " + mem.getFree() / 1024L + "K free"); // b)ϵͳҳ���ļ���������Ϣ
//			Swap swap = sigar.getSwap();
//			// ����������
//			System.out.println("Total = " + swap.getTotal() / 1024L + "K av");
//			// ��ǰ������ʹ����
//			System.out.println("Used = " + swap.getUsed() / 1024L + "K used");
//			// ��ǰ������ʣ����
//			System.out.println("Free = " + swap.getFree() / 1024L + "K free");
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//	}
//
	// ����ϵͳ��Ϣ // a)ȡ����ǰ����ϵͳ�����ƣ�
	public String getPlatformName() {
		String hostname = "";
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (Exception exc) {
			Sigar sigar = new Sigar();
			try {
				hostname = sigar.getNetInfo().getHostName();
			} catch (SigarException e) {
				hostname = "localhost.unknown";
			} finally {
				sigar.close();
			}
		}
		return hostname;
	}
//
//	// b)ȡ��ǰ����ϵͳ����Ϣ
//	public static void testGetOSInfo() {
//		OperatingSystem OS = OperatingSystem.getInstance();
//		// ����ϵͳ�ں������磺 386��486��586��x86
//		System.out.println("OS.getArch() = " + OS.getArch());
//		System.out.println("OS.getCpuEndian() = " + OS.getCpuEndian());//
//		System.out.println("OS.getDataModel() = " + OS.getDataModel());//
//		// ϵͳ����
//		System.out.println("OS.getDescription() = " + OS.getDescription());
//		System.out.println("OS.getMachine() = " + OS.getMachine());//
//		// ����ϵͳ����
//		System.out.println("OS.getName() = " + OS.getName());
//		System.out.println("OS.getPatchLevel() = " + OS.getPatchLevel());//
//		// ����ϵͳ������
//		System.out.println("OS.getVendor() = " + OS.getVendor());
//		// ��������
//		System.out
//				.println("OS.getVendorCodeName() = " + OS.getVendorCodeName());
//		// ����ϵͳ����
//		System.out.println("OS.getVendorName() = " + OS.getVendorName());
//		// ����ϵͳ��������
//		System.out.println("OS.getVendorVersion() = " + OS.getVendorVersion());
//		// ����ϵͳ�İ汾��
//		System.out.println("OS.getVersion() = " + OS.getVersion());
//	}
//
//	// c)ȡ��ǰϵͳ���̱��е��û���Ϣ
//	public void testWho() {
//		try {
//			Sigar sigar = new Sigar();
//			org.hyperic.sigar.Who[] who = sigar.getWhoList();
//			if (who != null && who.length > 0) {
//				for (int i = 0; i < who.length; i++) {
//					System.out.println("\n~~~~~~~~~" + String.valueOf(i)
//							+ "~~~~~~~~~");
//					org.hyperic.sigar.Who _who = who[i];
//					System.out.println("getDevice() = " + _who.getDevice());
//					System.out.println("getHost() = " + _who.getHost());
//					System.out.println("getTime() = " + _who.getTime());
//					// ��ǰϵͳ���̱��е��û���
//					System.out.println("getUser() = " + _who.getUser());
//				}
//			}
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// 4.��Դ��Ϣ����Ҫ��Ӳ�̣� //
//	// a)ȡӲ�����еķ���������ϸ��Ϣ��ͨ��sigar.getFileSystemList()�����FileSystem�б�����Ȼ�������б�������
//	public static void testFileSystemInfo() throws Exception {
//		Sigar sigar = new Sigar();
//		FileSystem fslist[] = sigar.getFileSystemList();
//		// String dir = System.getProperty("user.home");// ��ǰ�û��ļ���·��
//		for (int i = 0; i < fslist.length; i++) {
//			System.out.println("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
//			FileSystem fs = fslist[i];
//			// �������̷�����
//			System.out.println("fs.getDevName() = " + fs.getDevName());
//			// �������̷�����
//			System.out.println("fs.getDirName() = " + fs.getDirName());
//			System.out.println("fs.getFlags() = " + fs.getFlags());//
//			// �ļ�ϵͳ���ͣ����� FAT32��NTFS
//			System.out.println("fs.getSysTypeName() = " + fs.getSysTypeName());
//			// �ļ�ϵͳ�����������籾��Ӳ�̡������������ļ�ϵͳ��
//			System.out.println("fs.getTypeName() = " + fs.getTypeName());
//			// �ļ�ϵͳ����
//			System.out.println("fs.getType() = " + fs.getType());
//			FileSystemUsage usage = null;
//			try {
//				usage = sigar.getFileSystemUsage(fs.getDirName());
//			} catch (SigarException e) {
//				if (fs.getType() == 2)
//					throw e;
//				continue;
//			}
//			switch (fs.getType()) {
//			case 0: // TYPE_UNKNOWN ��δ֪
//				break;
//			case 1: // TYPE_NONE
//				break;
//			case 2: // TYPE_LOCAL_DISK : ����Ӳ��
//				// �ļ�ϵͳ�ܴ�С
//				System.out.println(" Total = " + usage.getTotal() + "KB");
//				// �ļ�ϵͳʣ���С
//				System.out.println(" Free = " + usage.getFree() + "KB");
//				// �ļ�ϵͳ���ô�С
//				System.out.println(" Avail = " + usage.getAvail() + "KB");
//				// �ļ�ϵͳ�Ѿ�ʹ����
//				System.out.println(" Used = " + usage.getUsed() + "KB");
//				double usePercent = usage.getUsePercent() * 100D;
//				// �ļ�ϵͳ��Դ��������
//				System.out.println(" Usage = " + usePercent + "%");
//				break;
//			case 3:// TYPE_NETWORK ������
//				break;
//			case 4:// TYPE_RAM_DISK ������
//				break;
//			case 5:// TYPE_CDROM ������
//				break;
//			case 6:// TYPE_SWAP ��ҳ�潻��
//				break;
//			}
//			System.out.println(" DiskReads = " + usage.getDiskReads());
//			System.out.println(" DiskWrites = " + usage.getDiskWrites());
//		}
//		return;
//	}
//
//	// 5.������Ϣ // a)��ǰ��������ʽ����
//	public String getFQDN() {
//		Sigar sigar = null;
//		try {
//			return InetAddress.getLocalHost().getCanonicalHostName();
//		} catch (UnknownHostException e) {
//			try {
//				sigar = new Sigar();
//				return sigar.getFQDN();
//			} catch (SigarException ex) {
//				return null;
//			} finally {
//				sigar.close();
//			}
//		}
//	}
//
//	// b)ȡ����ǰ������IP��ַ
//	public String getDefaultIpAddress() {
//		String address = null;
//		try {
//			address = InetAddress.getLocalHost().getHostAddress();
//			// û�г����쳣��������ȡ����IPʱ�����ȡ���Ĳ�������ѭ�ص�ַʱ�ͷ���
//			// ������ͨ��Sigar���߰��еķ�������ȡ
//			if (!NetFlags.LOOPBACK_ADDRESS.equals(address)) {
//				return address;
//			}
//		} catch (UnknownHostException e) {
//			// hostname not in DNS or /etc/hosts
//		}
//		Sigar sigar = new Sigar();
//		try {
//			address = sigar.getNetInterfaceConfig().getAddress();
//		} catch (SigarException e) {
//			address = NetFlags.LOOPBACK_ADDRESS;
//		} finally {
//			sigar.close();
//		}
//		return address;
//	} // c)ȡ����ǰ������MAC��ַ
//
//	public String getMAC() {
//		Sigar sigar = null;
//		try {
//			sigar = new Sigar();
//			String[] ifaces = sigar.getNetInterfaceList();
//			String hwaddr = null;
//			for (int i = 0; i < ifaces.length; i++) {
//				NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
//				if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
//						|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//						|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
//					continue;
//				}
//				/*
//				 * ������ڶ������������������������Ĭ��ֻȡ��һ��������MAC��ַ�����Ҫ�������е����������������ĺ�����ģ�
//				 * ������޸ķ����ķ�������Ϊ�����Collection ��ͨ����forѭ����ȡ���Ķ��MAC��ַ��
//				 */
//				hwaddr = cfg.getHwaddr();
//				break;
//			}
//			return hwaddr != null ? hwaddr : null;
//		} catch (Exception e) {
//			return null;
//		} finally {
//			if (sigar != null)
//				sigar.close();
//		}
//	} // d)��ȡ������������Ϣ
//
//	public void testNetIfList() throws Exception {
//		Sigar sigar = new Sigar();
//		String ifNames[] = sigar.getNetInterfaceList();
//		for (int i = 0; i < ifNames.length; i++) {
//			String name = ifNames[i];
//			NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
//			print("\nname = " + name);// �����豸��
//			print("Address = " + ifconfig.getAddress());// IP��ַ
//			print("Netmask = " + ifconfig.getNetmask());// ��������
//			if ((ifconfig.getFlags() & 1L) <= 0L) {
//				print("!IFF_UP...skipping getNetInterfaceStat");
//				continue;
//			}
//			try {
//				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
//				print("RxPackets = " + ifstat.getRxPackets());// ���յ��ܰ�����
//				print("TxPackets = " + ifstat.getTxPackets());// ���͵��ܰ�����
//				print("RxBytes = " + ifstat.getRxBytes());// ���յ������ֽ���
//				print("TxBytes = " + ifstat.getTxBytes());// ���͵����ֽ���
//				print("RxErrors = " + ifstat.getRxErrors());// ���յ��Ĵ������
//				print("TxErrors = " + ifstat.getTxErrors());// �������ݰ�ʱ�Ĵ�����
//				print("RxDropped = " + ifstat.getRxDropped());// ����ʱ�����İ���
//				print("TxDropped = " + ifstat.getTxDropped());// ����ʱ�����İ���
//			} catch (SigarNotImplementedException e) {
//			} catch (SigarException e) {
//				print(e.getMessage());
//			}
//		}
//	}
//
//	void print(String msg) {
//		System.out.println(msg);
//	} // e)һЩ��������Ϣ
//
//	public void getEthernetInfo() {
//		Sigar sigar = null;
//		try {
//			sigar = new Sigar();
//			String[] ifaces = sigar.getNetInterfaceList();
//			for (int i = 0; i < ifaces.length; i++) {
//				NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
//				if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
//						|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//						|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
//					continue;
//				}
//				System.out.println("cfg.getAddress() = " + cfg.getAddress());// IP��ַ
//				System.out
//						.println("cfg.getBroadcast() = " + cfg.getBroadcast());// ���ع㲥��ַ
//				System.out.println("cfg.getHwaddr() = " + cfg.getHwaddr());// ����MAC��ַ
//				System.out.println("cfg.getNetmask() = " + cfg.getNetmask());// ��������
//				System.out.println("cfg.getDescription() = "
//						+ cfg.getDescription());// ����������Ϣ
//				System.out.println("cfg.getType() = " + cfg.getType());//
//				System.out.println("cfg.getDestination() = "
//						+ cfg.getDestination());
//				System.out.println("cfg.getFlags() = " + cfg.getFlags());//
//				System.out.println("cfg.getMetric() = " + cfg.getMetric());
//				System.out.println("cfg.getMtu() = " + cfg.getMtu());
//				System.out.println("cfg.getName() = " + cfg.getName());
//				System.out.println();
//			}
//		} catch (Exception e) {
//			System.out.println("Error while creating GUID" + e);
//		} finally {
//			if (sigar != null)
//				sigar.close();
//		}
//	}

	private static final Log logger = LogFactory.getLog();

	public AgentMonitorBasicInfo getBasicInfo() throws SigarException {
		AgentMonitorBasicInfo basicware = new AgentMonitorBasicInfo();
		// ��������
		basicware.setSrv_name(getPlatformName());

		OperatingSystem OS = OperatingSystem.getInstance();

		String desc = OS.getDescription();
		basicware.setSystem(OS.getName());
		basicware.setSystem_version(OS.getVersion());
		basicware.setOp_system(OS.getName());

		Sigar sigar = new Sigar();

		Mem mem = sigar.getMem();
		basicware.setMachine_mem((int) Math.floor(mem.getUsed() * 100
				/ mem.getTotal()));

		if (desc.toLowerCase().contains("win")) {

			// �����ǵ���CPU���Ƕ�CPU������
			CpuPerc[] cpuList = null;
			cpuList = sigar.getCpuPercList();

			double[] cpu_list = new double[cpuList.length];
			for (int i = 0; i < cpuList.length; i++) {
				cpu_list[i] = Double.parseDouble(CpuPerc.format(
						cpuList[i].getCombined()).replace("%", ""));
			}
			basicware.setMachine_cpu((int) Math.floor(getMax(cpu_list)));

			long pid = sigar.getPid();

			ProcState prs = sigar.getProcState(pid);

			ProcCpu pCpu = sigar.getProcCpu(pid);

			ProcMem pMem = sigar.getProcMem(pid);

			pMem.getSize();
			long ppid = prs.getPpid();

			ProcCpu ppCpu = sigar.getProcCpu(ppid);

			ProcMem ppMem = sigar.getProcMem(ppid);

			
			basicware.setAgent_cpu((int) (pCpu.getPercent() + ppCpu
					.getPercent()));

			double pMem_rate = pMem.getResident() * 100 / pMem.getSize();
			double ppMem_rate = ppMem.getResident() * 100 / ppMem.getSize();

			basicware.setAgent_mem((int) Math.floor(pMem_rate + ppMem_rate));
			
			int agent_operate_status = 2;

//			long[] pids = sigar.getProcList();
//			for (long pid_item : pids) {
//				ProcState prs1 = sigar.getProcState(pid_item);
//		        long ppid_item = prs1.getPpid();
//		        
//		        if(ppid_item == pid){
//		            agent_operate_status = 1;
//		        }
//		    }
//		
//			basicware.setAgent_operate_status(agent_operate_status);
			
			String rs = execShellWin("wmic process get parentprocessid | findstr " + pid);
			
			if(!Assert.isEmpty(rs)){
				String[] rs_array = rs.trim().split("\n");
				if(!Assert.isEmpty(rs_array) && rs_array.length > 1){
					agent_operate_status = 1;
				}
			}
			logger.debug("rs = {}, pid = {}, agent_operate_status = {}",rs, pid, agent_operate_status);
			basicware.setAgent_operate_status(agent_operate_status);
			
		} else{
			
			String sys_str = execShell("uname -a");
			if(!Assert.isEmpty(sys_str) && sys_str.toLowerCase().startsWith("linux")){

				// �����ǵ���CPU���Ƕ�CPU������
				CpuPerc[] cpuList = null;
				cpuList = sigar.getCpuPercList();

				double[] cpu_list = new double[cpuList.length];
				for (int i = 0; i < cpuList.length; i++) {
					cpu_list[i] = Double.parseDouble(CpuPerc.format(
							cpuList[i].getCombined()).replace("%", ""));
				}
				basicware.setMachine_cpu((int) Math.floor(getMax(cpu_list)));

				long pid = sigar.getPid();

				ProcState prs = sigar.getProcState(pid);

				ProcCpu pCpu = sigar.getProcCpu(pid);

				ProcMem pMem = sigar.getProcMem(pid);

				pMem.getSize();
				long ppid = prs.getPpid();

				ProcCpu ppCpu = sigar.getProcCpu(ppid);

				ProcMem ppMem = sigar.getProcMem(ppid);

				logger.info("pid = "+ pid + ", pCpu = " + pCpu.getPercent() + ", ppid = " + ppid + ", ppCpu = " + ppCpu.getPercent() );
				basicware.setAgent_cpu((int) (pCpu.getPercent() + ppCpu
						.getPercent()));

				double pMem_rate = pMem.getResident() * 100 / pMem.getSize();
				double ppMem_rate = ppMem.getResident() * 100 / ppMem.getSize();

				basicware.setAgent_mem((int) Math.floor(pMem_rate + ppMem_rate));
				
				int agent_operate_status = 2;
				
				String rs = execShell("ps axo ppid | grep " + pid + " | grep -v grep |awk '{print $1}'");
				
				if(!Assert.isEmpty(rs)){
					String[] rs_array = rs.trim().split("\n");
					if(!Assert.isEmpty(rs_array) && rs_array.length > 1){
						agent_operate_status = 1;
					}
				}
				basicware.setAgent_operate_status(agent_operate_status);
			}else{

				basicware.setMachine_cpu((int) getAgentCpuUsedRate());

				String rs = execShell("ps -eflo pcpu,pmem,args|grep service.Agent | grep -v grep | awk '{print $1 \" \" $2}'");
				double cpu_rate = 0;
				double mem_rate = 0;
				if (!Assert.isEmpty(rs)) {
					String[] strs = rs.split("\n");
					if (!Assert.isEmpty(strs)) {
						for (int i = 0; i < strs.length; i++) {
							String[] items = strs[i].split(" ");
							cpu_rate += Double.parseDouble(items[0]);
							mem_rate += Double.parseDouble(items[1]);
						}
					}

				}
				basicware.setAgent_cpu((int) cpu_rate);
				basicware.setAgent_mem((int) mem_rate);
				
				int agent_operate_status = 2;
				long pid = sigar.getPid();

				rs = execShell("ps -eflo ppid | grep " + pid + " | grep -v grep");
				
				if(!Assert.isEmpty(rs)){
					String[] rs_array = rs.trim().split("\n");
					if(!Assert.isEmpty(rs_array) && rs_array.length > 1){
						agent_operate_status = 1;
					}
				}
				basicware.setAgent_operate_status(agent_operate_status);
			
			}
		}
			
		return basicware;
	}

	private double getAgentCpuUsedRate() {
		/**
		 * ��ȡcpuռ����
		 */
		String rs = execShell("vmstat |tail -1|awk '{print $16}'");

		double used_rate = 100 - Double.parseDouble(rs);

		return used_rate;
	}

	private static String execShell(String cmd) {
		String rs = null;
		Process proc = null;
		InputStream in = null;
		try {
			String[] cmds = { "sh", "-c", cmd };
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

	private static String execShellWin(String cmd) {
		String rs = null;
		Process proc = null;
		InputStream in = null;
		try {
			String[] cmds = { "cmd.exe", "/c", cmd };
			proc = Runtime.getRuntime().exec(cmds);
			in = proc.getInputStream();
			rs = StringUtil.stream2str(in);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(proc != null){
				proc.destroy();
			}
		}
		return !Assert.isEmpty(rs) ? rs.trim() : rs;
	}
	
	/**
	 * ȡ�������е����ֵ
	 * 
	 * @param arr
	 * @return
	 */
	public double getMax(double[] arr) {
		double max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

//	public static void main(String[] args) {
//		Sigar sigar = new Sigar();
//		CpuInfo[] infos;
//		Mem mem;
//		try {
//			infos = sigar.getCpuInfoList();
//			System.out
//					.println(infos[0].getVendor() + " " + infos[0].getModel());
//
//			mem = sigar.getMem();
//
//			// �ڴ�����
//			System.out.println("Total = " + getDealMem(mem.getTotal()));
//
//			long total_disk = 0;
//			FileSystem fslist[] = sigar.getFileSystemList();
//			for (int i = 0; i < fslist.length; i++) {
//				FileSystem fs = fslist[i];
//				FileSystemUsage usage = null;
//				try {
//					usage = sigar.getFileSystemUsage(fs.getDirName());
//				} catch (SigarException e) {
//					if (fs.getType() == 2)
//						throw e;
//					continue;
//				}
//				switch (fs.getType()) {
//				case 2: // TYPE_LOCAL_DISK : ����Ӳ��
//					total_disk += usage.getTotal();
//					break;
//				}
//			}
//
//			System.out.println(getDealDisk(total_disk));
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			String[] netInterfaceList = sigar.getNetInterfaceList();
//			// һЩ��������Ϣ
//			for (int i = 0; i < netInterfaceList.length; i++) {
//				String netInterface = netInterfaceList[i];// ����ӿ�
//				NetInterfaceConfig netInterfaceConfig = sigar
//						.getNetInterfaceConfig(netInterface);
//				if (NetFlags.LOOPBACK_ADDRESS.equals(netInterfaceConfig
//						.getAddress())
//						|| (netInterfaceConfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//						|| NetFlags.NULL_HWADDR.equals(netInterfaceConfig
//								.getHwaddr())) {
//					continue;
//				}
//
//				System.out.println("netInterfaceConfig name��"
//						+ netInterfaceConfig.getName());
//				System.out.println("netInterfaceConfig hwaddr��"
//						+ netInterfaceConfig.getHwaddr());// ����MAC��ַ
//				System.out.println("netInterfaceConfig type:"
//						+ netInterfaceConfig.getType());
//				System.out.println("netInterfaceConfig description��"
//						+ netInterfaceConfig.getDescription());// ����������Ϣ
//				System.out.println("netInterfaceConfig address��"
//						+ netInterfaceConfig.getAddress());// IP��ַ
//				System.out.println("netInterfaceConfig destination��"
//						+ netInterfaceConfig.getDestination());
//				System.out.println("netInterfaceConfig broadcast��"
//						+ netInterfaceConfig.getBroadcast());// ���ع㲥��ַ
//				System.out.println("netInterfaceConfig netmask��"
//						+ netInterfaceConfig.getNetmask());// ��������
//				System.out.println("netInterfaceConfig flags��"
//						+ netInterfaceConfig.getFlags());
//				System.out.println("netInterfaceConfig mtu��"
//						+ netInterfaceConfig.getMtu());
//				System.out.println("netInterfaceConfig metric��"
//						+ netInterfaceConfig.getMetric());
//			}
//		} catch (SigarException e) {
//			e.printStackTrace();
//		}
//		sigar.close();
//
//	}

	/**
	 * �ڴ浥λ���� Description:
	 * 
	 * @param mem
	 * @return
	 */
	private static String getDealMem(double mem) {
		double mem_num;
		String mem_str;
		mem_num = mem / 1024 / 1024 / 1024;
		if (mem_num > 0) {
			mem_str = Math.ceil(mem / 1024 / 1024 / 1024) + "G";
		} else {
			mem_str = Math.ceil(mem / 1024 / 1024) + "MB";
		}
		return mem_str;
	}

	/**
	 * Ӳ�̵�λ���� Description:
	 * 
	 * @param disk
	 * @return
	 */
	private static String getDealDisk(double disk) {
		double disk_num;
		String disk_str;
		disk_num = disk / 1024 / 1024 / 1024;
		DecimalFormat df = new DecimalFormat("#.#");
		if (disk_num > 0) {
			disk_str = df.format(disk / 1024 / 1024 / 1024) + "T";
		} else {
			disk_str = df.format(disk / 1024 / 1024) + "G";
		}

		return disk_str;
	}

//	public AgentMonitorBasicInfo getSysConfigInfo() throws SigarException {
//		AgentMonitorBasicInfo basicware = new AgentMonitorBasicInfo();
//
//		// ��������
//		basicware.setSrv_name(getPlatformName());
//				
//		Sigar sigar = new Sigar();
//		CpuInfo[] infos;
//		Mem mem;
//
//		infos = sigar.getCpuInfoList();
//		basicware.setCpu(infos[0].getVendor() + " " + infos[0].getModel());
//		
//		mem = sigar.getMem();
//		// �ڴ�����
//		basicware.setMem(getDealMem(mem.getTotal()));
//		
//		long total_disk = 0;
//		FileSystem fslist[] = sigar.getFileSystemList();
//		for (int i = 0; i < fslist.length; i++) {
//			FileSystem fs = fslist[i];
//			FileSystemUsage usage = null;
//			try {
//				usage = sigar.getFileSystemUsage(fs.getDirName());
//			} catch (SigarException e) {
//				if (fs.getType() == 2)
//					throw e;
//				continue;
//			}
//			switch (fs.getType()) {
//			case 2: // TYPE_LOCAL_DISK : ����Ӳ��
//				total_disk += usage.getTotal();
//				break;
//			}
//		}
//
//		basicware.setDisk(getDealDisk(total_disk));
//		
//		Properties props = System.getProperties();
//
//		basicware.setJava_version("java version \""
//				+ props.getProperty("java.version") + "\","
//				+ props.getProperty("java.specification.name") + ","
//				+ props.getProperty("java.vm.name") + " (build "
//				+ props.getProperty("java.vm.version") + ")");
//
//		basicware.setOp_system(props.getProperty("os.name") + " " + props.getProperty("os.version"));
//		
//		
//		
//		String[] netInterfaceList = sigar.getNetInterfaceList();
//		// һЩ��������Ϣ
//		for (int i = 0; i < netInterfaceList.length; i++) {
//			String netInterface = netInterfaceList[i];// ����ӿ�
//			NetInterfaceConfig netInterfaceConfig = sigar
//					.getNetInterfaceConfig(netInterface);
//			if (NetFlags.LOOPBACK_ADDRESS.equals(netInterfaceConfig
//					.getAddress())
//					|| (netInterfaceConfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//					|| NetFlags.NULL_HWADDR.equals(netInterfaceConfig
//							.getHwaddr())) {
//				continue;
//			}
//
//			System.out.println("netInterfaceConfig name��"
//					+ netInterfaceConfig.getName());
//			System.out.println("netInterfaceConfig hwaddr��"
//					+ netInterfaceConfig.getHwaddr());// ����MAC��ַ
//			System.out.println("netInterfaceConfig type:"
//					+ netInterfaceConfig.getType());
//			System.out.println("netInterfaceConfig description��"
//					+ netInterfaceConfig.getDescription());// ����������Ϣ
//			System.out.println("netInterfaceConfig address��"
//					+ netInterfaceConfig.getAddress());// IP��ַ
//			System.out.println("netInterfaceConfig destination��"
//					+ netInterfaceConfig.getDestination());
//			System.out.println("netInterfaceConfig broadcast��"
//					+ netInterfaceConfig.getBroadcast());// ���ع㲥��ַ
//			System.out.println("netInterfaceConfig netmask��"
//					+ netInterfaceConfig.getNetmask());// ��������
//			System.out.println("netInterfaceConfig flags��"
//					+ netInterfaceConfig.getFlags());
//			System.out.println("netInterfaceConfig mtu��"
//					+ netInterfaceConfig.getMtu());
//			System.out.println("netInterfaceConfig metric��"
//					+ netInterfaceConfig.getMetric());
//		}
//
//		sigar.close();
//		
//		return basicware;
//
//	}
}