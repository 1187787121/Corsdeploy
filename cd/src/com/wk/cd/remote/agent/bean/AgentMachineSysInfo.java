package com.wk.cd.remote.agent.bean;

/**
 * Agent ������Դ��Ϣ
 * Class Description: 
 * @author 12049
 */
public class AgentMachineSysInfo {

	/**
	 * CPUռ����
	 */
	private int cpu_rate;
	public static final String CPU_RATE = "CPUռ����";
	
	/**
	 * ����ռ����
	 */
	private int disk_rate;
	public static final String DISK_RATE = "����ռ����";
	
	/**
	 * IO��æ��
	 */
	private int io_rate;
	public static final String IO_RATE = "IO��æ��";
	
	/**
	 * ������������
	 */
	private String network_up;
	public static final String NETWORK_UP = "������������";
	
	/**
	 * ��������������
	 */
	private String network_down;
	public static final String NETWORK_DOWN = "������������";
	
	/**
	 * ��������ʹ�ñ���
	 */
	private int network_up_rate;
	public static final String NETWORK_UP_RATE = "��������ʹ�ñ���";
	
	/**
	 * ��������ʹ�ñ���
	 */
	private int network_down_rate;
	public static final String NETWORK_DOWN_RATE = "��������ʹ�ñ���";
	/**
	 * @return cpu_rate
	 */
	public int getCpu_rate() {
		return cpu_rate;
	}
	/**
	 * @return disk_rate
	 */
	public int getDisk_rate() {
		return disk_rate;
	}
	/**
	 * @param disk_rate
	 */
	public void setDisk_rate(int disk_rate) {
		this.disk_rate = disk_rate;
	}
	/**
	 * @return io_rate
	 */
	public int getIo_rate() {
		return io_rate;
	}
	/**
	 * @param io_rate
	 */
	public void setIo_rate(int io_rate) {
		this.io_rate = io_rate;
	}
	/**
	 * @return network_up
	 */
	public String getNetwork_up() {
		return network_up;
	}
	/**
	 * @param network_up
	 */
	public void setNetwork_up(String network_up) {
		this.network_up = network_up;
	}
	/**
	 * @return network_down
	 */
	public String getNetwork_down() {
		return network_down;
	}
	/**
	 * @param network_down
	 */
	public void setNetwork_down(String network_down) {
		this.network_down = network_down;
	}
	/**
	 * @return network_up_rate
	 */
	public int getNetwork_up_rate() {
		return network_up_rate;
	}
	/**
	 * @param network_up_rate
	 */
	public void setNetwork_up_rate(int network_up_rate) {
		this.network_up_rate = network_up_rate;
	}
	/**
	 * @return network_down_rate
	 */
	public int getNetwork_down_rate() {
		return network_down_rate;
	}
	/**
	 * @param network_down_rate
	 */
	public void setNetwork_down_rate(int network_down_rate) {
		this.network_down_rate = network_down_rate;
	}
	/**
	 * @param cpu_rate
	 */
	public void setCpu_rate(int cpu_rate) {
		this.cpu_rate = cpu_rate;
	}
}