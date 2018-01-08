/**
 * Title: ExecuteLogWriterService.java
 * File Description: 用于写执行日志的服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016年5月12日
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
 * Class Description: 用于写执行日志的服务
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
	 * Description: 执行开始日志
	 * @param desc 执行内容的描述 可以为空
	 */
	public void writerExecuteBegin(String desc) {
		List<String> list = new ArrayList<String>();
		list.add("*************************************************************");
		list.add("*************************************************************");
		list.add("****");
		list.add("****          " + desc + "开始执行*********");
		list.add("****          开始时间 【" + JaDateTime.now() + "】");
		list.add("****");
		list.add("*************************************************************");
		list.add("*************************************************************");
		writeToLog(list);

	}

	/**
	 * Description: 写入阶段（从1开始）
	 * @param current_phase 当前阶段号
	 * @param total_phase 总阶段号
	 * @param phase_desc 阶段描述
	 */
	public void writerPhase(int current_phase, int total_phase, String phase_desc) {
		current_phase++;
		String str = "【阶段" + current_phase + "/" + total_phase + "】" + phase_desc + "\r\n";
		writeToLog(str);
	}

	public void writerPhaseCmd(InstancePhase phase) {
		ModuleSourceInfo msi = phase.getModule_source_info();
		StringBuffer sb_source = new StringBuffer();
		sb_source
				.append(space + space + "执 行 节 点 ：" + msi.getDt_source_info().getSoc_ip() + "\r\n");
		sb_source.append(space + space + "实 现 类 型 ：" + phase.getImpl_type().getName() + "\r\n");
		sb_source.append(space + space + "执行数据源：" + msi.getDt_source_info().getSoc_name() + "\r\n");
		writeToLog(sb_source.toString());
		String[] cmds = phase.getScript().getCmds();
		StringBuffer sb_cmd = new StringBuffer();
		if (!Assert.isEmpty(cmds)) {
			sb_cmd.append(space + space + "执 行 命 令 ：\r\n");
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
	 * 写入交互式发送的命令
	 * Description: 
	 * @param cmd
	 */
	public void writerInteractCmd(String node_ip,String cmd){
		writerNodeName(node_ip);
		String msg = "交互发送命令:[" + cmd + "]\r\n";
		writeToLog(msg);
	}
	/**
	 * Description: 写入步骤（步骤号从1开始）
	 * @param current_step 当前步骤好
	 * @param total_step 总步骤号
	 * @param cmd 命令
	 */
	public void writerStep(int current_step, int total_step, String cmd) {
		current_step++;
		String str = space + "【步骤" + current_step + "/" + total_step + "】" + space + "【"
				+ cmd.trim() + "】 \r\n";
		writeToLog(str);
	}

	/**
	 * Description: 写入IP
	 * @param node_ip 执行的节点IP
	 */
	private void writerNodeName(String node_ip) {
		String msg = space + space + "[" + node_ip.trim() + "]" + space;
		writeToLog(msg);
	}

	/**
	 * Description: 发送命令成功日志
	 * @param node_ip
	 */
	public void writerSend(String node_ip) {
		writerNodeName(node_ip);
		String msg = "发送命令成功！\r\n";
		writeToLog(msg);
	}

	/**
	 * Description: 写入执行结果
	 * @param node_ip
	 * @param result 结果信息
	 */
	public void writerResult(String node_ip, Result result) {
		String msg = "无响应结果";
		if (!Assert.isEmpty(result)) {
			CMD_STATUS cs = result.getStatus();
			if (CMD_STATUS.SKIP.equals(cs)) {
				logger.debug("---------execute skip logger result in----------");
				msg = "执行跳过";
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
			msg = "响应结果:[" + msg.replace("\n", "\r\n") + "]\r\n";
		}else{
			msg = "响应结果:[" + msg + "]\r\n";
		}
		writeToLog(msg);
	}

	/**
	 * Description: 每步执行后输出日志
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
	 * Description: 执行结束输出
	 * @param desc 执行内容描述 可以为空
	 */
	public void writerEnd(String desc) {
		List<String> list = new ArrayList<String>();
		list.add("****");
		list.add("****          " + desc + "结束执行*********");
		list.add("****          结束时间 【" + JaDateTime.now() + "】");
		list.add("****");
		list.add("*************************************************************");
		list.add("*************************************************************");
		list.add("");
		list.add("");
		list.add("");
		writeToLog(list);
	}

	/**
	 * Description:追加执行的信息到文件
	 * @param fileName
	 * @param content
	 */
	private void writeToLog(List<String> content) {
		for (String str : content) {
			writeToLog(str + "\r\n");
		}
	}

	/**
	 * Description:追加执行的信息到文件
	 * @param fileName
	 * @param content
	 */
	private void writeToLog(String content) {
		FileWriter writer = null;
		try {
			// 打开一个写文件器，true表示以追加形式写文件
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
