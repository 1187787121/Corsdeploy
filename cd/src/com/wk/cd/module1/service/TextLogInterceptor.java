/**
 * Title: TextLogInterceptor.java
 * File Description: 日志拦截器，在执行的执行 在process里面add这个实现类 可以实现打日志的方法
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.module1.service;

import java.io.File;
import java.util.List;

import com.wk.cd.module1.ModuleInterceptor;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.module1.service.LogWriterService;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:执行日志写入的拦截器
 * @author "Zhangj"
 */
public class TextLogInterceptor
		implements ModuleInterceptor {
	private final LogWriterService writer;

	private final int total_phase;

	private final String desc;

	private static final Log logger = LogFactory.getLog();


	/**
	 * 构造函数
	 * @param file_path 日志写入路径
	 * @param total_phase 总阶段数
	 * @param desc 执行描述 可以为null
	 */
	public TextLogInterceptor(String file_path, int total_phase, String desc) {
		String dir = FileTool.getFilePath(file_path);
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		this.writer = new LogWriterService(file_path);
		this.total_phase = total_phase;
		this.desc = desc;
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void beforeRun(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		writer.writerPhase(ctx.getCurrentStage(), total_phase, mi.getPhase_name());
		InstancePhase phase = ctx.getCurrentModuleInfo();
		
		
		writer.writerPhaseCmd(phase);
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void afterRun(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		Result result = ctx.getLastResult();
		logger.debug("TextLogInterceptor result = {},{}", result, result.getError_msg());
		writer.writerResult(ip, result);

		writer.writerStepEnd(ip, result);
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void beforeStepinto(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		writer.writerStep(ctx.getCurrentStep(), ctx.getCurrentCmdCount(),
				ctx.getCurrentCmd());
		writer.writerSend(mi.getModule_source_info().getDt_source_info().getSoc_ip());
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void afterStepinto(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		Result result = ctx.getLastResult();
		writer.writerResult(ip, result);
		writer.writerStepEnd(ip, result);
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void beforeStart(ProcessContext ctx) {
		writer.writerExecuteBegin(desc);
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void afterEnd(ProcessContext ctx) {
		logger.debug("-------LogInterceptor AfterEnd-------");
		writer.writerEnd(desc);
	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void skip(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
//		writer.writerPhase(ctx.getCurrentStage(), total_phase, mi.getCn_name());
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		List<Result> list = ctx.getResults();
		Result result = null;
		if (!Assert.isEmpty(list)) {
			result = list.get(0);
		}
		writer.writerResult(ip, result);

	}

	/**
	 * Description:
	 * @param ctx
	 */
	@Override
	public void skipStep(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
//		writer.writerStep(ctx.getCurrentStep(), ctx.getCurrentCmdCount(),
//				ctx.getCurrentCmd());
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		writer.writerResult(ip, ctx.getLastResult());
		writer.writerStepEnd(ip, ctx.getLastResult());
	}

	/**
	 * 交互式发送命令
	 * Description: 
	 * @param ctx
	 */
	@Override
	public void sendInteractCmd(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		String cmd = ctx.getInteractCmd();
		writer.writerInteractCmd(ip, cmd);
	}

	/**
	 * 交互式获取执行返回信息
	 * Description: 
	 * @param ctx
	 */
	@Override
	public void getInteractMsg(ProcessContext ctx) {
		InstancePhase mi = ctx.getCurrentModuleInfo();
		String ip = mi.getModule_source_info().getDt_source_info().getSoc_ip();
		Result result = ctx.getLastResult();
		writer.writerResult(ip, result);
	}

}
