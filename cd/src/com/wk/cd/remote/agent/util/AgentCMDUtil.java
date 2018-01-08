package com.wk.cd.remote.agent.util;

/**
 * Agent自定义命令类
 * Class Description: 
 * @author 12049
 */
public class AgentCMDUtil {

	public static final String CHECK_AGENT_EXIST_CMD = "$HELLO_AGENT$";                             //校验服务器上是否存在Agent的命令
	
	public static final String INTERACT_GET_RS_CMD = "$INTERACT_GET_RS_MSG$";                       //交互式Agent获取数据命令
	
	public static final String STOP_EXE_PROCESS_CMD = "$STOP_EXE_PROCESS$";                           //关闭执行进程
	
//	public static final String AGENT_GET_SYS_BASIC_INFO_CMD = "$AGENT_GET_SYSTEM_BASIC_INFO$";        // 获取系统基本信息
//	
//	public static final String AGENT_MONITOR_CMD = "$AGENT_MONITOR$";                                 //获取监控信息
	
	public static final String AGENT_MANAGER_CMD = "$AGENT_MANAGER$";                                //获取Agent监控自身的信息
	
	public static final String STOP_WORK_PROC_CMD= "$STOP_WORK_PROCESS$";                          //停止工作进程

	public static final String START_WORK_PROC_CMD = "$START_WORK_PROCESS$";                       //启动工作进程
}
