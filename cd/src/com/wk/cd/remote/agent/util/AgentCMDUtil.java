package com.wk.cd.remote.agent.util;

/**
 * Agent�Զ���������
 * Class Description: 
 * @author 12049
 */
public class AgentCMDUtil {

	public static final String CHECK_AGENT_EXIST_CMD = "$HELLO_AGENT$";                             //У����������Ƿ����Agent������
	
	public static final String INTERACT_GET_RS_CMD = "$INTERACT_GET_RS_MSG$";                       //����ʽAgent��ȡ��������
	
	public static final String STOP_EXE_PROCESS_CMD = "$STOP_EXE_PROCESS$";                           //�ر�ִ�н���
	
//	public static final String AGENT_GET_SYS_BASIC_INFO_CMD = "$AGENT_GET_SYSTEM_BASIC_INFO$";        // ��ȡϵͳ������Ϣ
//	
//	public static final String AGENT_MONITOR_CMD = "$AGENT_MONITOR$";                                 //��ȡ�����Ϣ
	
	public static final String AGENT_MANAGER_CMD = "$AGENT_MANAGER$";                                //��ȡAgent�����������Ϣ
	
	public static final String STOP_WORK_PROC_CMD= "$STOP_WORK_PROCESS$";                          //ֹͣ��������

	public static final String START_WORK_PROC_CMD = "$START_WORK_PROCESS$";                       //������������
}