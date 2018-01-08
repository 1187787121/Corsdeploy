package com.wk.cd.remote.agent.service;


import java.util.UUID;

import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.Result;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 检测服务器上Agent是否存在
 * Class Description: 
 * @author 12049
 */
public class CheckAgentExistService{

	private static final Log logger = LogFactory.getLog();
	
	private static final String CHECK_AGENT_EXIST_CMD = "$HELLO_AGENT$";        //校验服务器上是否存在Agent的命令

	private static final String SHORT_TIMEOUT = "2000";
	
	public static Result checkAgent(String ip, int port){
		
		long start_time = System.currentTimeMillis();
		try {
			String id = UUID.randomUUID().toString().replaceAll("\\-", "");
			boolean isStepSupport = Boolean.parseBoolean(CfgTool.getProjectPropterty("cms.agent.isStepSupport"));
			AgentClient client = new AgentClient(id, ip, port, isStepSupport, IMPL_TYPE.SHELL, 1, 1, null, null, 0, 0);
			
			ShExecRsBean reply = client.exeShell(CHECK_AGENT_EXIST_CMD, SHORT_TIMEOUT);
			CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
			String msg = reply.getIs_succ() ? reply.getRs_msg() : reply.getErr_msg();
			logger.debug("命令执行结果信息：{}", msg);
			logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);
			return new Result(status, msg, start_time);
		} catch (RuntimeException t) {
            return new Result(t,start_time);
        }
	}

	public static boolean checkAgentExist(String ip,int port){
		Result result = checkAgent(ip, port);
		boolean flag = CMD_STATUS.SUCCEED.equals(result.getStatus());
		return flag;
	}
}
