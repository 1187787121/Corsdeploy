package com.wk.cd.remote.agent.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.sh.bean.ShExecRsBean;



/**
 * Agent的FTP服务
 * 不推荐使用此类，推荐使用AgentFTPRCallService（后果自负   么么哒）
 * Class Description: 
 * @author hulc
 */
public class AgentFTPService {

	/**
	 * 下载单个文件
	 * Description: 
	 * @param ip
	 * @param port
	 * @param local_path  本地路径
	 * @param remote_path  远程路径
	 * @param file_name   文件名
	 * @return
	 * 不推荐使用，推荐使用AgentFTPRCallService
	 */
	public static ShExecRsBean download(String ip, int port, String local_path, String remote_path, String file_name){
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		sess.sendCmd("cd " + remote_path);
		sess.sendCmd("lcd " + local_path);
		ShExecRsBean bean = sess.sendCmd("get " + file_name);
		return bean;
	}
	
	public static ShExecRsBean download(String ip, int port, String local_path, String remote_path){
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		ShExecRsBean bean = sess.sendCmd("get " + local_path + " " + remote_path);
		return bean;
	}
	/**
	 * 下载多个文件
	 * Description: 
	 * @param ip
	 * @param port
	 * @param local_path
	 * @param remote_path
	 * @param file_name
	 * @return
	 */
	public static ShExecRsBean download(String ip, int port, String local_path, String remote_path, String[] file_names){
		ShExecRsBean bean = null;
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		sess.sendCmd("cd " + remote_path);
		sess.sendCmd("lcd " + local_path);
		if(!Assert.isEmpty(file_names)){
			for(String file_name : file_names){
				bean = sess.sendCmd("get " + file_name);
			}
		}
		return bean;
	}
	
	/**
	 * 上传单个文件
	 * Description: 
	 * @param ip
	 * @param port
	 * @param local_path
	 * @param remote_path
	 * @param file_name
	 * @return
	 */

	public static ShExecRsBean upload(String ip, int port, String local_path, String remote_path, String file_name){
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		sess.sendCmd("cd " + remote_path);
		sess.sendCmd("lcd " + local_path);
		ShExecRsBean bean = sess.sendCmd("put " + file_name);
		return bean;
	}
	
	public static ShExecRsBean upload(String ip, int port, String local_path, String remote_path){
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		ShExecRsBean bean = sess.sendCmd("put " + local_path + " " + remote_path);
		return bean;
	}
	/**
	 * 上传多个文件
	 * Description: 
	 * @param ip
	 * @param port
	 * @param local_path
	 * @param remote_path
	 * @param file_names
	 * @return
	 */
	public static ShExecRsBean upload(String ip, int port, String local_path, String remote_path, String[] file_names){
		ShExecRsBean bean = null;
		AgentRSession sess = new AgentRSession(ip, port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		sess.sendCmd("cd " + remote_path);
		sess.sendCmd("lcd " + local_path);
		if(!Assert.isEmpty(file_names)){
			for(String file_name : file_names){
				bean = sess.sendCmd("put " + file_name);
			}
		}
		return bean;
	}
	
}
