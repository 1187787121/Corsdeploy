/**
 * Title: ExecuteLogWriterService.java
 * File Description: ����дִ����־�ķ���
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016��5��12��
 */
package com.wk.cd.module1.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: ����дִ����־�ķ���
 * @author "Zhangj"
 */
public class LogWriterService {
	private final String space = "  ";

	private final String path;

	private static final Log logger = LogFactory.getLog();

	public LogWriterService(String path) {
		this.path = path;
	}

	/**
	 * Description: ִ�п�ʼ��־
	 * @param desc ִ�����ݵ����� ����Ϊ��
	 */
	public void writerExecuteBegin(String desc) {
		List<String> list = new ArrayList<String>();
		list.add("*************************************************************");
		list.add("*************************************************************");
		list.add("****");
		list.add("****          " + desc + "��ʼִ��*********");
		list.add("****          ��ʼʱ�� ��" + JaDateTime.now() + "��");
		list.add("****");
		list.add("*************************************************************");
		list.add("*************************************************************");
		writeToLog(list);

	}

	/**
	 * Description: д��׶Σ���1��ʼ��
	 * @param current_phase ��ǰ�׶κ�
	 * @param total_phase �ܽ׶κ�
	 * @param phase_desc �׶�����
	 */
	public void writerPhase(int current_phase, int total_phase, String phase_desc) {
		current_phase++;
		String str = "���׶�" + current_phase + "/" + total_phase + "��" + phase_desc + "\r\n";
		writeToLog(str);
	}

	public void writerPhaseCmd(InstancePhase phase) {
		ModuleSourceInfo msi = phase.getModule_source_info();
		StringBuffer sb_source = new StringBuffer();
		sb_source
				.append(space + space + "ִ �� �� �� ��" + msi.getDt_source_info().getSoc_ip() + "\r\n");
		sb_source.append(space + space + "ʵ �� �� �� ��" + phase.getImpl_type().getName() + "\r\n");
		sb_source.append(space + space + "ִ������Դ��" + msi.getDt_source_info().getSoc_name() + "\r\n");
		writeToLog(sb_source.toString());
		String[] cmds = phase.getScript().getCmds();
		StringBuffer sb_cmd = new StringBuffer();
		if (!Assert.isEmpty(cmds)) {
			sb_cmd.append(space + space + "ִ �� �� �� ��\r\n");
			int index = 1;
			for (String cmd : cmds) {
				sb_cmd.append(space + space + space + space + space + space + space + space + index
						+ ":" + cmd + "\r\n");
				index ++;
			}
			writeToLog(sb_cmd.toString());
		}

	}

	/**
	 * д�뽻��ʽ���͵�����
	 * Description: 
	 * @param cmd
	 */
	public void writerInteractCmd(String node_ip,String cmd){
		writerNodeName(node_ip);
		String msg = "������������:[" + cmd + "]\r\n";
		writeToLog(msg);
	}
	/**
	 * Description: д�벽�裨����Ŵ�1��ʼ��
	 * @param current_step ��ǰ�����
	 * @param total_step �ܲ����
	 * @param cmd ����
	 */
	public void writerStep(int current_step, int total_step, String cmd) {
		current_step++;
		String str = space + "������" + current_step + "/" + total_step + "��" + space + "��"
				+ cmd.trim() + "�� \r\n";
		writeToLog(str);
	}

	/**
	 * Description: д��IP
	 * @param node_ip ִ�еĽڵ�IP
	 */
	private void writerNodeName(String node_ip) {
		String msg = space + space + "[" + node_ip.trim() + "]" + space;
		writeToLog(msg);
	}

	/**
	 * Description: ��������ɹ���־
	 * @param node_ip
	 */
	public void writerSend(String node_ip) {
		writerNodeName(node_ip);
		String msg = "��������ɹ���\r\n";
		writeToLog(msg);
	}

	/**
	 * Description: д��ִ�н��
	 * @param node_ip
	 * @param result �����Ϣ
	 */
	public void writerResult(String node_ip, Result result) {
		String msg = "����Ӧ���";
		if (!Assert.isEmpty(result)) {
			CMD_STATUS cs = result.getStatus();
			if (CMD_STATUS.SKIP.equals(cs)) {
				logger.debug("---------execute skip logger result in----------");
				msg = "ִ������";
			} else  {
				logger.debug("----------------execute success with logger msg--------------");
				msg = result.getMsg()+"\r\n";
				if(!Assert.isEmpty(result.getError_msg())){
					msg = msg+"ERROR_MESSAGE: \r\n"+result.getError_msg();
				}
			} 
		}
		writerNodeName(node_ip);
		if(!Assert.isEmpty(msg)){
			msg = "��Ӧ���:[" + msg.replace("\n", "\r\n") + "]\r\n";
		}else{
			msg = "��Ӧ���:[" + msg + "]\r\n";
		}
		writeToLog(msg);
	}

	/**
	 * Description: ÿ��ִ�к������־
	 * @param ip
	 * @param result
	 * @param start_time
	 * @param end_time
	 */
	public void writerStepEnd(String ip, Result result) {
		writerNodeName(ip);
		CMD_STATUS cmd_status = result.getStatus();
		String msg = "[" + cmd_status.getCname() + "]" + space
				+ JaDateTime.valueOf(result.getStart_time()) + space
				+ JaDateTime.valueOf(result.getEnd_time()) + space + result.getTime_used()
				+ "(ms)\r\n";
		writeToLog(msg);

	}

	/**
	 * Description: ִ�н������
	 * @param desc ִ���������� ����Ϊ��
	 */
	public void writerEnd(String desc) {
		List<String> list = new ArrayList<String>();
		list.add("****");
		list.add("****          " + desc + "����ִ��*********");
		list.add("****          ����ʱ�� ��" + JaDateTime.now() + "��");
		list.add("****");
		list.add("*************************************************************");
		list.add("*************************************************************");
		list.add("");
		list.add("");
		list.add("");
		writeToLog(list);
	}

	/**
	 * Description:׷��ִ�е���Ϣ���ļ�
	 * @param fileName
	 * @param content
	 */
	private void writeToLog(List<String> content) {
		for (String str : content) {
			writeToLog(str + "\r\n");
		}
	}

	/**
	 * Description:׷��ִ�е���Ϣ���ļ�
	 * @param fileName
	 * @param content
	 */
	private void writeToLog(String content) {
		FileWriter writer = null;
		try {
			// ��һ��д�ļ�����true��ʾ��׷����ʽд�ļ�
			writer = new FileWriter(path, true);
			writer.write(content);
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
