package com.wk.cd.module1;

import com.wk.cd.module1.ModuleInterceptor;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.remote.bean.AsyncMsgBean;

/**
 * 流程接口 按顺序执行，可按阶段、步骤执行、可跳过
 */
public interface Process {

	public abstract ProcessContext getCtx();

	//阶段执行 内部实则是一步一步去执行
	public Result run(int stage);
	// 阶段执行 内部是写为shell文件执行
	// 使用临时文件执行 临时文件的暂时存放相对路径
	public Result runStage(String remote_relative_dir,int stage,boolean skip_flag);
	
	public Result stepInto(int stage, int step);

	public Result skip(int stage);

	public Result skipStep(int stage, int step);

	public void addInterceptor(ModuleInterceptor interceptor);

	public void removeInterceptor(ModuleInterceptor interceptor);
	//命令挂起时候关闭服务
	public Result close(int stage);
	// 交互命令执行 按照阶段执行
	public Result interactRun(String remote_relative_dir,int stage);
	// 交互命令执行中发送交互命令
	public void sendInteractCmd(int stage,String cmd, boolean sensitive_flag);
	// 交互命令执行获取中间执行结果
	public AsyncMsgBean getInteractMsg(int stage);
}
