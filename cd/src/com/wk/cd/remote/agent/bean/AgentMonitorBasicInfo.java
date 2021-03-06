package com.wk.cd.remote.agent.bean;

/**
 * Agent管理获取机器基本信息
 * Class Description: 
 * @author 12049
 */
public class AgentMonitorBasicInfo {
	private String ip;   
	
	private String system;                          //系统
	
	private String system_version;                  //系统版本号
	
	private String cpu;                             //CPU版本信息
	
	private String mem;                             //内存 
	
	private String disk;                            //硬盘
	
	private String network;                         //网络
	
	private String machine_cfg;                     //机器配置
	
	private String srv_name;                        //服务器名
	
	private String op_system;                       //操作系统
	
	private String java_version;                    //java版本
	
	private int machine_cpu;                     //机器cpu%
	
	private int machine_mem;                     //机器内存%
	
	private int agent_cpu;                       //agent自身cpu%
	
	private int agent_mem;                       //agent自身mem%
	
	private int agent_operate_status;            //agent运行状态   0.停止   1.运行   2.闲置
	
	/**
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * @return system_version
	 */
	public String getSystem_version() {
		return system_version;
	}

	/**
	 * @param system_version
	 */
	public void setSystem_version(String system_version) {
		this.system_version = system_version;
	}

	/**
	 * @return cpu
	 */
	public String getCpu() {
		return cpu;
	}

	/**
	 * @param cpu
	 */
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	/**
	 * @return mem
	 */
	public String getMem() {
		return mem;
	}

	/**
	 * @param mem
	 */
	public void setMem(String mem) {
		this.mem = mem;
	}

	/**
	 * @return disk
	 */
	public String getDisk() {
		return disk;
	}

	/**
	 * @param disk
	 */
	public void setDisk(String disk) {
		this.disk = disk;
	}
	
	/**
	 * @return network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return machine_cfg
	 */
	public String getMachine_cfg() {
		return machine_cfg;
	}

	/**
	 * @param machine_cfg
	 */
	public void setMachine_cfg(String machine_cfg) {
		this.machine_cfg = machine_cfg;
	}

	/**
	 * @return srv_name
	 */
	public String getSrv_name() {
		return srv_name;
	}

	/**
	 * @param srv_name
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}
	
	/**
	 * @return op_system
	 */
	public String getOp_system() {
		return op_system;
	}

	/**
	 * @param op_system
	 */
	public void setOp_system(String op_system) {
		this.op_system = op_system;
	}

	/**
	 * @return java_version
	 */
	public String getJava_version() {
		return java_version;
	}

	/**
	 * @param java_version
	 */
	public void setJava_version(String java_version) {
		this.java_version = java_version;
	}

	public int getMachine_cpu() {
		return machine_cpu;
	}

	public void setMachine_cpu(int machine_cpu) {
		this.machine_cpu = machine_cpu;
	}

	public int getMachine_mem() {
		return machine_mem;
	}

	public void setMachine_mem(int machine_mem) {
		this.machine_mem = machine_mem;
	}

	public int getAgent_cpu() {
		return agent_cpu;
	}

	public void setAgent_cpu(int agent_cpu) {
		this.agent_cpu = agent_cpu;
	}

	public int getAgent_mem() {
		return agent_mem;
	}

	public void setAgent_mem(int agent_mem) {
		this.agent_mem = agent_mem;
	}

	public int getAgent_operate_status() {
		return agent_operate_status;
	}

	public void setAgent_operate_status(int agent_operate_status) {
		this.agent_operate_status = agent_operate_status;
	}
}
