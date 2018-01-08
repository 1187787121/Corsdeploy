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
	
	private final Map<String, EnvDirInfo> envDirMap = new HashMap<String, EnvDirInfo>();             //�洢��������
	private final Map<String, String> filePathMap = new HashMap<String, String>();                   //����ű��ļ�·��
	private final Map<String, ShellBean> beanMap = new HashMap<String, ShellBean>();                //bean�ļ��ϣ����ڶ�ʱ�����������Ƿ�ʱ
	/**
	 * Description: ÿִ��һ�������ִ�и÷���
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
		
		//�ж���Կ�Ƿ�һ��,��һ��ֱ��return. shell & rs_flag & result�����Կ
		if (!Md5Util.GetMD5Code(cmd_str).equals(md5_str)) {
			logger.debug("Not through the key check.");
			return osbean;
		}
		
		//��Դ����
		if(controlResource(osbean, id)){
			return osbean;
		}

		//����ʵ�ַ�֧
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
	 * ��������Դ����
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
		
		//����ڴ����cpu����ˣ���ֱ�ӷ��ص�ǰ��Ҫִ�е����񣬲�����
		if(current_cpu > max_cpu){
			osbean.setId(id);
			osbean.setRs_flag("fail");
			osbean.setResult("��ǰCPU"+ current_cpu +"%ռ���ѳ����������" + max_cpu + "%");
			AgentHelperUtil.getMD5Code(osbean);
			return true;
		}else if(current_mem > max_mem){
			osbean.setId(id);
			osbean.setRs_flag("fail");
			osbean.setResult("��ǰ�ڴ�"+current_mem+"%ռ���ѳ����������" + max_mem + "%");
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
