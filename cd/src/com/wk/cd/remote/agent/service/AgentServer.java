/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.remote.agent.service;

import java.util.HashMap;
import java.util.Map;

import org.hyperic.sigar.SigarException;

import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.Md5Util;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.bean.EnvDirInfo;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentHelperUtil;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
/**
 * Class Description: Created by lixl on 16-8-9.
 */
public class AgentServer extends AgentBaseActor {
	
	private static final Log logger = LogFactory.getLog();
	
	@Inject ShellAgent shellAgent;
	@Inject PythonAgent pythonAgent;
	@Inject FtpAgent ftpAgent;
	@Inject SqlAgent sqlAgent;
	
	private final Map<String, EnvDirInfo> envDirMap = new HashMap<String, EnvDirInfo>();             //存储环境变量
	private final Map<String, String> filePathMap = new HashMap<String, String>();                   //保存脚本文件路径
	private final Map<String, ShellBean> beanMap = new HashMap<String, ShellBean>();                //bean的集合，用于定时任务检查任务是否超时
	/**
	 * Description: 每执行一次命令都会执行该方法
	 * @param input
	 *            ShellBean
	 * @param output
	 *            ShellBean
	 * @return
	 */
	@Override
	protected Object runService(Object input, Object output) {
		ShellBean isbean = (ShellBean) input;
		ShellBean osbean = (ShellBean) output;
		String cmd_str = DESUtil.decrypt(isbean.getShell());
		String id = isbean.getId();
		String md5_str = isbean.getDigest();
		IMPL_TYPE imType = isbean.getImpl_type();

		logger.info("agent[{}] execute cmd:[{}]", id, cmd_str);
		
		//判断密钥是否一致,不一致直接return. shell & rs_flag & result组成密钥
		if (!Md5Util.GetMD5Code(cmd_str).equals(md5_str)) {
			logger.debug("Not through the key check.");
			return osbean;
		}
		
		//资源控制
		if(controlResource(osbean, id)){
			return osbean;
		}

		//各种实现分支
		if(imType == IMPL_TYPE.FTP){
			
			return ftpAgent.execFTPCmd(isbean, osbean);
			
		}else if(imType == IMPL_TYPE.SQL){
			
			return sqlAgent.execSQLCmd(isbean, osbean);
			
		}else if(imType == IMPL_TYPE.PYTHON2 || imType == IMPL_TYPE.PYTHON3){
			beanMap.put(id, isbean);
			return pythonAgent.execPythonCmd(isbean, osbean, envDirMap, filePathMap, beanMap);
			
		}else{
			beanMap.put(id, isbean);
			return shellAgent.execShellCmd(isbean, osbean, envDirMap, filePathMap, beanMap);
			
		}
	}
	
	/**
	 * 服务器资源控制
	 * Description: 
	 * @param osbean
	 * @param id
	 * @return
	 */
	private boolean controlResource(ShellBean osbean, String id) {
//		long max_cpu = Long.parseLong(CfgTool.getProjectPropterty("cms.agent.max.cpu"));
		long max_cpu = CfgTool.getProperties().getInt("cms.agent.max.cpu", 30);
//		long max_mem = Long.parseLong(CfgTool.getProjectPropterty("cms.agent.max.mem"));
		long max_mem = CfgTool.getProperties().getInt("cms.agent.max.mem", 30);
		long current_cpu = 0;
		long current_mem = 0;
		try {
			long[] array = AgentHelperUtil.getExecProcCPUAndMemPct();
			current_cpu = array[0];
			current_mem = array[1];
		} catch (SigarException e) {
			e.printStackTrace();
		}
		
		//如果内存或者cpu溢出了，就直接返回当前需要执行的任务，并报错
		if(current_cpu > max_cpu){
			osbean.setId(id);
			osbean.setRs_flag("fail");
			osbean.setResult("当前CPU"+ current_cpu +"%占用已超出最大限制" + max_cpu + "%");
			AgentHelperUtil.getMD5Code(osbean);
			return true;
		}else if(current_mem > max_mem){
			osbean.setId(id);
			osbean.setRs_flag("fail");
			osbean.setResult("当前内存"+current_mem+"%占用已超出最大限制" + max_mem + "%");
			AgentHelperUtil.getMD5Code(osbean);
			return true;
		}
		return false;
	}

	@Override
	protected ShellBean newInput() {
		return new ShellBean();
	}

	@Override
	protected ShellBean newOutput() {
		return new ShellBean();
	}
}
