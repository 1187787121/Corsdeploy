/**
 * Title: ShExecRsBean.java
 * File Description: 脚本执行接口
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 9/23/2015
 */

package com.wk.cd.remote.sh.bean;

import com.wk.cd.remote.agent.bean.AgentNodeMsgBean;
import com.wk.sdo.ServiceData;

/**
 * Class Description:脚本执行接口
 * @author lixl
 */
public class ShExecRsBean {
	/**
	 * 成功标志 
	 * true 成功 
	 * false 失败 
	 */
	private boolean is_succ;
	public static final String IS_SUCCCN = "成功标志";

	/**
	 * 执行结果
	 */
	private String rs_msg;
	public static final String RS_MSGCN = "执行结果";

	/**
	 * 异常信息
	 */
	private String err_msg;
	public static final String ERR_MSGCN = "异常信息";
	
	/**
	 * 会话编号
	 */
	private String sessionId;
	public static final String SESSION_ID = "会话编号";
	
	/**
	 * 进程号
	 */
	private String pid;
	public static final String PID = "进程号";
	
	/**
	 * 关闭标志，如果是关闭则为true
	 */
	private boolean close_flag;
	public static final String CLOSE_FLAGCN = "关闭标志";
	
	/**
	 * 进程执行状态
	 * 0:进程正常退出
	 */
	private int exitStatus;
	public static final String EXITSTATUS = "进程执行状态";
	
	/**
	 * JDBC执行数据库操作返回的数据
	 */
	private ServiceData serviceData;
	public static final String SERVICEDATA = "JDBC执行数据库操作返回的数据";
	
	/**
	 * 远程执行脚本临时文件 执行结束之后需要删除
	 */
	private String remote_temp_shell;
	public static final String REMOTE_TEMP_SHELLCN = "";
	/**
	 * 远程脚本执行错误信息存放临时文件  执行结束之后需要删除
	 */
	private String remote_temp_errorlog;
	public static final String REMOTE_TEMP_ERRORLOGCN = "";
	
	 /**
     * 监控节点信息
     */
    private AgentNodeMsgBean nodeMsgBean;

	/**
	 * @return boolean 
	 *  		true 成功
	 * 			false 失败
	 */
	public boolean getIs_succ(){
		return this.is_succ;
	}

	/**
	 * @param is_succ 成功标志 
	 *  	  true 成功
	 *  	  false 失败
	 */
	public void setIs_succ(boolean is_succ){
		this.is_succ = is_succ;
	}

	/**
	 * @return String 执行结果
	 */
	public String getRs_msg(){
		return this.rs_msg;
	}

	/**
	 * @param rs_msg 执行结果
	 */
	public void setRs_msg(String rs_msg){
		this.rs_msg = rs_msg;
	}

	/**
	 * @return String 异常信息
	 */
	public String getErr_msg(){
		return this.err_msg;
	}

	/**
	 * @param err_msg 异常信息
	 */
	public void setErr_msg(String err_msg){
		this.err_msg = err_msg;
	}

	/**
	 * @return sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	 * @return close_flag
	 */
	public boolean isClose_flag() {
		return close_flag;
	}

	/**
	 * @param close_flag
	 */
	public void setClose_flag(boolean close_flag) {
		this.close_flag = close_flag;
	}

	/**
	 * @return exitStatus
	 */
	public int getExitStatus() {
		return exitStatus;
	}

	/**
	 * @param exitStatus
	 */
	public void setExitStatus(int exitStatus) {
		this.exitStatus = exitStatus;
	}

	/**
	 * @return serviceData
	 */
	public ServiceData getServiceData() {
		return serviceData;
	}

	/**
	 * @param serviceData
	 */
	public void setServiceData(ServiceData serviceData) {
		this.serviceData = serviceData;
	}

	/**
	 * @return nodeMsgBean
	 */
	public AgentNodeMsgBean getNodeMsgBean() {
		return nodeMsgBean;
	}

	/**
	 * @param nodeMsgBean
	 */
	public void setNodeMsgBean(AgentNodeMsgBean nodeMsgBean) {
		this.nodeMsgBean = nodeMsgBean;
	}
	
	

	/**
	 * @return remote_temp_shell
	 */
	public String getRemote_temp_shell() {
		return remote_temp_shell;
	}

	/**
	 * @param remote_temp_shell
	 */
	public void setRemote_temp_shell(String remote_temp_shell) {
		this.remote_temp_shell = remote_temp_shell;
	}

	/**
	 * @return remote_temp_errorlog
	 */
	public String getRemote_temp_errorlog() {
		return remote_temp_errorlog;
	}

	/**
	 * @param remote_temp_errorlog
	 */
	public void setRemote_temp_errorlog(String remote_temp_errorlog) {
		this.remote_temp_errorlog = remote_temp_errorlog;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "ShExecRsBean [is_succ=" + is_succ + ", rs_msg=" + rs_msg
				+ ", err_msg=" + err_msg + ", pid=" + pid + "]";
	}
	
	
}
