package com.wk.cd.remote.agent.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.remote.sh.bean.ShExecRsBean;



/**
 * Agent��FTP����
 * ���Ƽ�ʹ�ô��࣬�Ƽ�ʹ��AgentFTPRCallService������Ը�   ôô�գ�
 * Class Description: 
 * @author hulc
 */
public class AgentFTPService {

	/**
	 * ���ص����ļ�
	 * Description: 
	 * @param ip
	 * @param port
	 * @param local_path  ����·��
	 * @param remote_path  Զ��·��
	 * @param file_name   �ļ���
	 * @return
	 * ���Ƽ�ʹ�ã��Ƽ�ʹ��AgentFTPRCallService
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
	 * ���ض���ļ�
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
	 * �ϴ������ļ�
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
	 * �ϴ�����ļ�
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
