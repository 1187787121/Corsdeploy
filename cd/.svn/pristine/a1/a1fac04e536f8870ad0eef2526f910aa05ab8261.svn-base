package com.wk.cd.module1;

import com.wk.cd.module1.ProcessContext;

/**
 * Module拦截器
 *
 * 在Module执行的各环节的前后插入功能实现。
 */
public interface ModuleInterceptor {

	/**
	 * 整个流程运行之前执行
	 * @param ctx
	 */
	public abstract void beforeStart(ProcessContext ctx);
	/**
	 * 整个流程运行完毕之后执行
	 * @param ctx
	 */
	public abstract void afterEnd(ProcessContext ctx);
	/**
	 * 阶段运行之前执行
	 * @param ctx
     */
	public abstract void beforeRun(ProcessContext ctx);
	/**
	 * 阶段运行之后执行
	 * @param ctx
	 */
	public abstract void afterRun(ProcessContext ctx);
	/**
	 * 步骤运行之前执行，仅在支持按步运行模式下有效
	 * @param ctx
	 */
	public abstract void beforeStepinto(ProcessContext ctx);
	/**
	 * 步骤运行之后执行，仅在支持按步运行模式下有效
	 * @param ctx
	 */
	public abstract void afterStepinto(ProcessContext ctx);
	/**
	 * 跳过阶段时执行
	 * @param ctx
	 */
	public abstract void skip(ProcessContext ctx);
	/**
	 * 跳过步骤时执行
	 * @param ctx
	 */
	public abstract void skipStep(ProcessContext ctx);
	
	/**
	 * 交互式发送命令
	 * Description: 
	 * @param ctx
	 */
	public abstract void sendInteractCmd(ProcessContext ctx);
	
	/**
	 * 交互命令执行获取中间执行结果
	 * Description: 
	 * @param ctx
	 */
	public abstract void getInteractMsg(ProcessContext ctx);
	
}
 
