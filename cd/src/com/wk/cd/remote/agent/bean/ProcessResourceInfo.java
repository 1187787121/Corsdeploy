package com.wk.cd.remote.agent.bean;

/**
 * 进程资源信息
 * Class Description: 
 * @author 12049
 */
public class ProcessResourceInfo {

	private String  user_id;              //用户id                                    
    private String  pid;                  //进程id   
    private String  ppid;                 //父进程id   
    private String  cpu_rate;             //cpu占用率  
    private String  start_time;           //启动时间  
    private String  time_used;            //运行耗时（秒）   
    private String  progerss_status;      //进程状态   
    private String  cmd;                  //执行命令
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
