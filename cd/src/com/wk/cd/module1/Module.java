package com.wk.cd.module1;

import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.remote.bean.AsyncMsgBean;

/**
 * 运行模块
 *
 * 可单独运行，可以在流程中运行
 */
public interface Module {

	/**
	 * 获取Module定义信息
	 * @return
     */
	public abstract InstancePhase getPhaseInfo();

	/**
	 * Module是否支持按步骤运行
	 * @return
     */
	public abstract boolean isStepEnabled();

	/**
	 * 获取模块超时时间
	 * @return
     */
	public abstract int getTimeout();
	/**
	 * 是否最后一个步骤
	 * @param step 步骤
	 * @return
	 */
	public abstract boolean isLastStep(int step);
	/**
	 * 设置模块的超时时间
	 * @param timeout 超时时间，毫秒为单位
	 */
	public abstract void setTimeout(int timeout);
	/**
	 * 获取运行上下文
	 * @return
     */
	public abstract ProcessContext getCtx();
	/**
	 * 设置运行上下文
	 * @param ctx 上下文
	 */
	public abstract void setCtx(ProcessContext ctx);
	/**
	 * 运行模块
	 * @return 运行结果
	 */
	public abstract Result run();
	
	/**
	 * Description: 阶段执行 这个执行是将所有的命令写到远程执行文件中 再执行
	 * @return
	 */
	public abstract Result runModule(String remote_relative_dir);
	/**
	 * 运行模块步骤
	 * @param step 步骤
	 * @return 运行结果
	 */
	public abstract Result stepinto(int step);
	
	public abstract void interactRun(String remote_relative_dir);
	
	public abstract void sendInteractCmd(String cmd, boolean sensitive_flag);
	
	public abstract AsyncMsgBean getInteractMsg();
	
	public abstract IMPL_TYPE getImplType();
	
	/**
	 * Description:关闭session
	 */
	public abstract void sessionClose();
}
 
