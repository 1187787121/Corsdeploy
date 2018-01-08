/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.remote.agent.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.Md5Util;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.agent.bean.EnvDirInfo;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentCMDUtil;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.tcp.bean.TcpInputBean;
import com.wk.cd.remote.tcp.service.TcpCallRemoteService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.Base64;

/**
 * Class Description: Created by lixl on 16-8-11.
 */
public class AgentClient {

	private static final Log logger = LogFactory.getLog();

	private static final String LONG_TIMEOUT = "180000";
	private static final long OFFSIZE = 500 * 1024;               //ftp����ְ���С
	private static final String CONNECTOR = "&";                                      
	
	private final String id;
	private final String ip;
	private final int port;
	private boolean isStepSupport;                                //�Ƿ�֧�ֽű�ִ��   true:֧��
	private Map<String, EnvDirInfo> dirMap;                       //ftp����ʱ���洢����·����Զ��·��
	private IMPL_TYPE impl_type;                                  //ʵ�����ͣ�shell/ftp/jdbc/python
	private int stepCount;                                        //session���ܵ�������
	private int type;                                             //1.����������  2.����ʽ
	private String remote_temp_path;                              //Ŀ�������ʱ�ļ�Ŀ¼
	
	//JDBC��ز���
	private DtSourceInfo dt_source_info;
	private int start_num;
	private int offset;
	
	private boolean stop_flag;                                    //�ֶ�ֹͣ��־

	public AgentClient(String id, String ip, int port, boolean isStepSupport, IMPL_TYPE impl_type, int stepCount, int type, String remote_temp_path, DtSourceInfo dt_source_info, int start_num, int offset) {
		this.id = id;
		this.ip = ip;
		this.port = port;
		this.type = type;
		this.impl_type = impl_type;
		this.stepCount = stepCount;
		this.isStepSupport = isStepSupport;
		
		this.dt_source_info = dt_source_info;
		this.start_num = start_num;
		this.offset = offset;
		
		//Զ��Ŀ�������ʱ�ļ�ȫ·��
		String remote_path = CfgTool.getProjectPropterty("remote.upload.path");
		if(Assert.notEmpty(remote_path)){
			remote_path = remote_path.endsWith("/") ? remote_path : remote_path + "/";
		}
		if(remote_temp_path != null){
			this.remote_temp_path =  remote_path + remote_temp_path;
		}else{
			this.remote_temp_path = remote_path;
		}
				
		dirMap = new HashMap<String, EnvDirInfo>();
	}
	
	//remote_temp_pathԶ��Ŀ�������ʱ�ļ����·�������Ϲ����ռ䣩
	public ShExecRsBean exeShell(String cmd, String timeout) {
		/**
		 * ���û�������ֵ
		 */
		ShellBean s = new ShellBean();
		s.setId(id);
		s.setShell(DESUtil.encrypt(cmd));              // ��shell�������DES����
		s.setDigest(Md5Util.GetMD5Code(cmd));          //��Կ��������֤ 
		s.setImpl_type(impl_type);
		s.setStepSupport(isStepSupport);  
		s.setType(type);
		s.setStepCount(stepCount);
		s.setRemote_temp_path(remote_temp_path);
		/**
		 * �ж��Ƿ���ftp���仹��shell������
		 */
		if (impl_type == IMPL_TYPE.FTP) {
			if(cmd.equals(AgentCMDUtil.STOP_EXE_PROCESS_CMD)){
				stop_flag = true;
			}
			s = ftpTask(cmd, s);
		}else if(impl_type == IMPL_TYPE.SQL){
			s.setDt_source_info(dt_source_info);
			s.setStart_num(start_num);
			s.setOffset(offset);
			s = exeRmtShell(s, s, timeout);
		}else{
			s = exeRmtShell(s, s, timeout);
		}
		return toShExecRsBean(s);
	}
	
	/**
	 * ����TcpCallRemoteService��ִ��shell����
	 */
	private ShellBean exeRmtShell(ShellBean input, ShellBean result, String timeout) {
		Assert.assertNotEmpty(input, "shell");
		TcpInputBean tbean = new TcpInputBean();
		tbean.setRemote_address(ip + ":" + port);
		tbean.setRemote_name(ip);
		tbean.setTimeout(timeout);
		TcpCallRemoteService svc = new TcpCallRemoteService(tbean);

		ShellBean output = svc.callService(input, result);

//		logger.debug("---client[{}] inputShell:[{}], outputResult:[{}]---", id, DESUtil.decrypt(input.getShell()), output);
		return output;
	}
	
	/**	
	 * Description: ftp��ִ�в����ڴ˷�����
	 * 					1.���汾��·����Զ��·��
	 * 					2.�ϴ�������
	 * @param cmd
	 * @param s
	 */
	private ShellBean ftpTask(String cmd_text, ShellBean s){
		if(cmd_text.equals("cd")){
			return s;
		}
		ShellBean shellBean = new ShellBean();
		String[] cmd_args = cmd_text.split(" ");
		String cmd = cmd_args[0];
        logger.info("call sftp cmd =[{}]", cmd_text);
        if(cmd.equalsIgnoreCase("cd")){
        	savePath(cmd_args[0], cmd_args[1]);
        }else if(cmd.equalsIgnoreCase("lcd")){
        	savePath(cmd_args[0], cmd_args[1]);
        }else if(cmd.equalsIgnoreCase("get")){
        	try {
				if(cmd_args.length > 2){
					String local__path = FileTool.filePathCvt(cmd_args[1]);
					String remote_path = FileTool.filePathCvt(cmd_args[2]);
					s.setLocal_dir(FileTool.getFilePath(local__path));
					s.setRemote_dir(FileTool.getFilePath(remote_path));
					String remote_file_name = FileTool.getFileName(remote_path);
					String local_file_name = FileTool.getFileName(local__path);
					if(Assert.isEmpty(local_file_name)){
						s.setLocal_file_name(remote_file_name);
					}else{
						s.setLocal_file_name(local_file_name);
					}
					s.setRemote_file_name(remote_file_name);
					shellBean = downloadFile(s, false);
				}else{
					s.setRemote_file_name(cmd_args[1]);
					s.setLocal_file_name(cmd_args[1]);
					shellBean = downloadFile(s, true);
				}
			} catch (IOException e) {
				shellBean.setResult(e.getMessage());
				logger.error("client download file error", e);
			}
        }else if(cmd.equalsIgnoreCase("put")){
        	try {
				if(cmd_args.length > 2){
					String local_path = FileTool.filePathCvt(cmd_args[1]);
					String remote_path = FileTool.filePathCvt(cmd_args[2]);
					s.setLocal_dir(FileTool.getFilePath(local_path));
					s.setRemote_dir(FileTool.getFilePath(remote_path));
					String remote_file_name = FileTool.getFileName(remote_path);
					String local_file_name = FileTool.getFileName(local_path);
					if(Assert.isEmpty(remote_file_name)){
						s.setRemote_file_name(local_file_name);
					}else{
						s.setRemote_file_name(remote_file_name);
					}
					s.setLocal_file_name(local_file_name);
					shellBean = uploadFile(s, false);
				}else{
					s.setRemote_file_name(cmd_args[1]);
					s.setLocal_file_name(cmd_args[1]);
					shellBean = uploadFile(s, true);
				}
			} catch (IOException e) {
				e.printStackTrace();
				shellBean.setResult(e.getMessage());
				logger.error("client upload file error", e);
			}
        }
		return shellBean;
	}
	
	/**
	 * Description: ftp �ļ��ϴ�
	 * @param fileName   �ļ���
	 * @param s
	 * @throws IOException 
	 */
	private ShellBean uploadFile(ShellBean s, boolean flag) throws IOException{
		logger.debug("client[{}] begin upload file[{}]", id, s.getLocal_file_name());
		ShellBean bean = null;
		if(flag)
			s = getLocalAndRemoteDir(s);                                   //������·����Զ��·�����浽ShellBean��
    	
		File file = new File(s.getLocal_dir() + s.getLocal_file_name()); 
		if (!file.exists()) {
            throw new FileNotExistException().addScene("FILE", file);
        }
		
		long file_total_size = file.length();                          //�ļ��ܴ�С
		int exe_num = (int)(file_total_size / OFFSIZE);                //ִ�д���
		long remain_pac_size = file_total_size % OFFSIZE;              //ʣ��ְ���С
		long slice_size = 0;                                           //�ְ���С  
		int total_num = remain_pac_size > 0 ? exe_num + 1 : exe_num;   //������ִ�д���
		for(int cur_num = 1; cur_num <= total_num; cur_num++){
				if(stop_flag){
					break;
				}
				file_total_size = file.length();                       //���»�ȡ�ļ��Ĵ�С���Է��ļ����ϴ�ʱ�����Ӱ�
				exe_num = (int)(file_total_size / OFFSIZE);            //ִ�д���
				remain_pac_size = file_total_size % OFFSIZE;           //ʣ��ְ���С
				
				if(remain_pac_size > 0){
					total_num = exe_num + 1; 
					if(cur_num < total_num){
						slice_size = OFFSIZE;
					}else{
						slice_size = remain_pac_size;
					}
				}else if(remain_pac_size == 0){
					total_num = exe_num;
					slice_size = exe_num == 0 ? 0 : OFFSIZE;
				}
				
				byte[] file_content = FileTool.getFileToByte(file, OFFSIZE * (cur_num - 1), (int)slice_size);     //��ȡ�ļ�����byte����
				s.setSlice_size(slice_size);   //�ְ���С
				if(total_num == 0 && slice_size == 0){
					s.setFile_content("");
				}else{
					s.setFile_content(new String(Base64.encode(file_content)));
				}
				s.setFile_total_size(file_total_size);
				s.setTotal_num(total_num);
				s.setCur_num(cur_num);
				
				bean = exeRmtShell(s, s, LONG_TIMEOUT);
				s.setRemote_dir(bean.getRemote_dir());         //Զ��·�����������"./"��ͷ�ģ�ֻҪ����һ�ξ��У�����ÿ�ζ�Ҫ��������ҪҪ�Ѵ�����·�����������ش���
		}
		logger.debug("client[{}] end upload file[{}]", id, s.getLocal_file_name());
		return bean;
	}
	
	/**
	 * Description: ftp �ļ�����
	 * @param fileName  �ļ���
	 * @param s
	 * @throws IOException 
	 */
	private ShellBean downloadFile(ShellBean s, boolean flag) throws IOException{
		logger.debug("client[{}] begin download file[{}]", id, s.getRemote_file_name());
		ShellBean bean = null;
		if(flag)
			s = getLocalAndRemoteDir(s);
		/**
		 * ��һ���������أ���������ļ��Ĵ�С,�����ܵ��������Ϊ1
		 */
		int total_num = 1;
		for(int download_cur_num = 1; download_cur_num <= total_num; download_cur_num++){
			if(stop_flag){
				break;
			}
			s.setCur_num(download_cur_num);
			bean = exeRmtShell(s, s, LONG_TIMEOUT);
			s.setRemote_dir(bean.getRemote_dir());
			
			total_num = bean.getTotal_num();     //ÿ������֮��Ҫ���»�ȡִ�е��ܴ���
			int cur_num = bean.getCur_num();
			if(total_num == 0 && bean.getSlice_size() == 0){      //���ļ�
				byte[] content_bytes = new byte[0];
				FileTool.writeFileFromBytes(content_bytes, OFFSIZE * (cur_num - 1), bean.getLocal_dir() , bean.getLocal_file_name());
			}else{
				byte[] content_bytes = Base64.decode(bean.getFile_content());
				FileTool.writeFileFromBytes(content_bytes, OFFSIZE * (cur_num - 1), bean.getLocal_dir() , bean.getLocal_file_name());
			}
			
		}
		logger.debug("client[{}] end download file[{}]", id, s.getRemote_file_name());
		return bean;
	}
	/**
	 * Description: ���汾��·����Զ��·����map��,�ж��Ƿ��ԡ�/����β
	 * @param cmd
	 */
	private void savePath(String cmd, String path){
		path = FileTool.filePathCvt(path);
		boolean isStartCd = cmd.equalsIgnoreCase("cd");
		boolean isStartLcd = cmd.equalsIgnoreCase("lcd");
		if(isStartCd || isStartLcd){
			path = path.endsWith("/") ? path : path + "/";
			if(!Assert.isEmpty(dirMap)){
				for (Map.Entry<String, EnvDirInfo> entry : dirMap.entrySet()) {

					if(entry.getKey().equals(id)){
						EnvDirInfo info = entry.getValue();
						if(isStartCd){
							info.setRemote_dir(path);
						}else if(isStartLcd){
							info.setLocal_dir(path);
						}
					}else{
						EnvDirInfo info = new EnvDirInfo();
						if(isStartCd){
							info.setRemote_dir(path);
						}else if(isStartLcd){
							info.setLocal_dir(path);
						}
						dirMap.put(id, info);
					}
				}
			} else {
				EnvDirInfo info = new EnvDirInfo();
				if(isStartCd){
					info.setRemote_dir(path);
				}else if(isStartLcd){
					info.setLocal_dir(path);
				}
				dirMap.put(id, info);
			}
		}
	}
	
	/**
	 * Description: �Է��ؽ������У���ת��
	 * @param shellBean
	 * @return
	 */
	private ShExecRsBean toShExecRsBean(ShellBean shellBean){
		ShExecRsBean bean = new ShExecRsBean();
		if(Assert.notEmpty(shellBean)){
			bean.setIs_succ(shellBean.getExitStatus() == 0);
			/**
			 * �ж���Կ�Ƿ�һ��,��һ��ֱ��return shell&rs_flag&result�����Կ
			 */
			String id = shellBean.getId();
			String shell = shellBean.getShell();
			String rs_flag = shellBean.getRs_flag();
			String result = shellBean.getResult();
			String digest = shellBean.getDigest();
			String pid = String.valueOf(shellBean.getPid());
			
			String md5Str = connectStr(shell, rs_flag, result);
			/**
			 * ���ؽ����Ϊ�ղ���У��ͨ��
			 */
			if (Assert.notEmpty(md5Str) && Md5Util.GetMD5Code(md5Str.substring(0, md5Str.length() - 1)).equals(digest)) {
				if(shellBean.getImpl_type() == IMPL_TYPE.SQL){
					if (!Assert.isEmpty(rs_flag) && rs_flag.equals("fail")) {
						bean.setErr_msg(Assert.isEmpty(result) ? result : result.trim());
					}
					bean.setRs_msg(Assert.isEmpty(result) ? result : result.trim());
					bean.setPid(pid);
					bean.setSessionId(id);
					bean.setExitStatus(shellBean.getExitStatus());
				}else{
					
					if(Assert.notEmpty(shell) && shell.equals(AgentCMDUtil.AGENT_MANAGER_CMD)){
						if(Assert.notEmpty(shellBean.getNodeMsgBean())){
							bean.setNodeMsgBean(shellBean.getNodeMsgBean());
						}
					}else{
					
						if (Assert.notEmpty(rs_flag) && rs_flag.equals("fail")) {
							bean.setErr_msg(Assert.isEmpty(result) ? result : result.trim());
						}
						
						bean.setRs_msg(Assert.isEmpty(result) ? result : result.trim());
						bean.setPid(pid);
						bean.setSessionId(id);
						bean.setExitStatus(shellBean.getExitStatus());
					}
				}
			} 
		}
		return bean;
	}

	/**
	 * Description: ����shell,rs_flag,result
	 * @return md5Str
	 */
	private String connectStr(String shell, String rs_flag, String result){
		String md5Str = null;
		if (!Assert.isEmpty(shell)) {
			md5Str += shell + CONNECTOR;
		}

		if (!Assert.isEmpty(rs_flag)) {
			md5Str += rs_flag + CONNECTOR;
		}

		if (!Assert.isEmpty(result)) {
			md5Str += result + CONNECTOR;
		}
		
		return md5Str;
	}
	
	/**
	 * ��ȡ������map�еı���·����Զ��·��
	 * Description: 
	 * @param s
	 * @return
	 */
	private ShellBean getLocalAndRemoteDir(ShellBean s){
		for (Map.Entry<String, EnvDirInfo> entry : dirMap.entrySet()) {
			if(entry.getKey().equals(id)){
				EnvDirInfo info = entry.getValue();
				s.setLocal_dir(info.getLocal_dir());
				s.setRemote_dir(info.getRemote_dir());
			}
		}
		return s;
	}

	public String getId() {
		return id;
	}

	public boolean isStepSupport() {
		return isStepSupport;
	}

	public void setStepSupport(boolean isStepSupport) {
		this.isStepSupport = isStepSupport;
	}

	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemote_temp_path() {
		return remote_temp_path;
	}

	public void setRemote_temp_path(String remote_temp_path) {
		this.remote_temp_path = remote_temp_path;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
}