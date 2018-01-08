package com.wk.cd.remote.agent.service;


import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentCMDUtil;
import com.wk.lang.Inject;
import com.wk.util.GBKProperties;

/**
 * Agent JDBC任务
 * 
 * @author 12049
 */
public class SqlAgent {
	
	private static final String DB_CONNECT_SCHEMA= "agent.db.connect.schema";
	
	@Inject SqlExecDefaultModeService sqlExecDefaultMode;
	
	@Inject SqlExecTransferModeService sqlExecTransferMode;
	
	/**
	 * Description: JDBC连接数据库操作 
	 * @param sql
	 * @param isbean
	 * @param osbean
	 * @return ShellBean
	 */
	public ShellBean execSQLCmd(ShellBean isbean, ShellBean osbean){
		
		GBKProperties pro = CfgTool.getProperties("./agentdb.properties");
		boolean db_connect_schema = Boolean.parseBoolean(pro.getProperty(DB_CONNECT_SCHEMA));
		
		String sql = DESUtil.decrypt(isbean.getShell());
		if(sql.equals(AgentCMDUtil.STOP_EXE_PROCESS_CMD)){
			return osbean;
		}
		//读取配置文件连接数据库  db_connect_schema = true
		if(db_connect_schema){
			osbean = sqlExecDefaultMode.execSQLCmd(isbean, osbean);
		}else{
			osbean = sqlExecTransferMode.execSQLCmd(isbean, osbean);
		}
		
		return osbean;
	}
}
