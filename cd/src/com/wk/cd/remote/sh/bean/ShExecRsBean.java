/**
 * Title: ShExecRsBean.java
 * File Description: �ű�ִ�нӿ�
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
 * Class Description:�ű�ִ�нӿ�
 * @author lixl
 */
public class ShExecRsBean {
	/**
	 * �ɹ���־ 
	 * true �ɹ� 
	 * false ʧ�� 
	 */
	private boolean is_succ;
	public static final String IS_SUCCCN = "�ɹ���־";

	/**
	 * ִ�н��
	 */
	private String rs_msg;
	public static final String RS_MSGCN = "ִ�н��";

	/**
	 * �쳣��Ϣ
	 */
	private String err_msg;
	public static final String ERR_MSGCN = "�쳣��Ϣ";
	
	/**
	 * �Ự���
	 */
	private String sessionId;
	public static final String SESSION_ID = "�Ự���";
	
	/**
	 * ���̺�
	 */
	private String pid;
	public static final String PID = "���̺�";
	
	/**
	 * �رձ�־������ǹر���Ϊtrue
	 */
	private boolean close_flag;
	public static final String CLOSE_FLAGCN = "�رձ�־";
	
	/**
	 * ����ִ��״̬
	 * 0:���������˳�
	 */
	private int exitStatus;
	public static final String EXITSTATUS = "����ִ��״̬";
	
	/**
	 * JDBCִ�����ݿ�������ص�����
	 */
	private ServiceData serviceData;
	public static final String SERVICEDATA = "JDBCִ�����ݿ�������ص�����";
	
	/**
	 * Զ��ִ�нű���ʱ�ļ� ִ�н���֮����Ҫɾ��
	 */
	private String remote_temp_shell;
	public static final String REMOTE_TEMP_SHELLCN = "";
	/**
	 * Զ�̽ű�ִ�д�����Ϣ�����ʱ�ļ�  ִ�н���֮����Ҫɾ��
	 */
	private String remote_temp_errorlog;
	public static final String REMOTE_TEMP_ERRORLOGCN = "";
	
	 /**
     * ��ؽڵ���Ϣ
     */
    private AgentNodeMsgBean nodeMsgBean;

	/**
	 * @return boolean 
	 *  		true �ɹ�
	 * 			false ʧ��
	 */
	public boolean getIs_succ(){
		return this.is_succ;
	}

	/**
	 * @param is_succ �ɹ���־ 
	 *  	  true �ɹ�
	 *  	  false ʧ��
	 */
	public void setIs_succ(boolean is_succ){
		this.is_succ = is_succ;
	}

	/**
	 * @return String ִ�н��
	 */
	public String getRs_msg(){
		return this.rs_msg;
	}

	/**
	 * @param rs_msg ִ�н��
	 */
	public void setRs_msg(String rs_msg){
		this.rs_msg = rs_msg;
	}

	/**
	 * @return String �쳣��Ϣ
	 */
	public String getErr_msg(){
		return this.err_msg;
	}

	/**
	 * @param err_msg �쳣��Ϣ
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
