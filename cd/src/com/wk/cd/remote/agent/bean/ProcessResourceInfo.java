package com.wk.cd.remote.agent.bean;

/**
 * ������Դ��Ϣ
 * Class Description: 
 * @author 12049
 */
public class ProcessResourceInfo {

	private String  user_id;              //�û�id                                    
    private String  pid;                  //����id   
    private String  ppid;                 //������id   
    private String  cpu_rate;             //cpuռ����  
    private String  start_time;           //����ʱ��  
    private String  time_used;            //���к�ʱ���룩   
    private String  progerss_status;      //����״̬   
    private String  cmd;                  //ִ������
	/**
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return ppid
	 */
	public String getPpid() {
		return ppid;
	}
	/**
	 * @param ppid
	 */
	public void setPpid(String ppid) {
		this.ppid = ppid;
	}
	/**
	 * @return cpu_rate
	 */
	public String getCpu_rate() {
		return cpu_rate;
	}
	/**
	 * @param cpu_rate
	 */
	public void setCpu_rate(String cpu_rate) {
		this.cpu_rate = cpu_rate;
	}
	/**
	 * @return start_time
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return time_used
	 */
	public String getTime_used() {
		return time_used;
	}
	/**
	 * @param time_used
	 */
	public void setTime_used(String time_used) {
		this.time_used = time_used;
	}
	/**
	 * @return progerss_status
	 */
	public String getProgerss_status() {
		return progerss_status;
	}
	/**
	 * @param progerss_status
	 */
	public void setProgerss_status(String progerss_status) {
		this.progerss_status = progerss_status;
	}
	/**
	 * @return cmd
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * @param cmd
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
