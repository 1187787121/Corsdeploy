package com.wk.cd.module1;

import com.wk.cd.module1.ProcessContext;

/**
 * Module������
 *
 * ��Moduleִ�еĸ����ڵ�ǰ����빦��ʵ�֡�
 */
public interface ModuleInterceptor {

	/**
	 * ������������֮ǰִ��
	 * @param ctx
	 */
	public abstract void beforeStart(ProcessContext ctx);
	/**
	 * ���������������֮��ִ��
	 * @param ctx
	 */
	public abstract void afterEnd(ProcessContext ctx);
	/**
	 * �׶�����֮ǰִ��
	 * @param ctx
     */
	public abstract void beforeRun(ProcessContext ctx);
	/**
	 * �׶�����֮��ִ��
	 * @param ctx
	 */
	public abstract void afterRun(ProcessContext ctx);
	/**
	 * ��������֮ǰִ�У�����֧�ְ�������ģʽ����Ч
	 * @param ctx
	 */
	public abstract void beforeStepinto(ProcessContext ctx);
	/**
	 * ��������֮��ִ�У�����֧�ְ�������ģʽ����Ч
	 * @param ctx
	 */
	public abstract void afterStepinto(ProcessContext ctx);
	/**
	 * �����׶�ʱִ��
	 * @param ctx
	 */
	public abstract void skip(ProcessContext ctx);
	/**
	 * ��������ʱִ��
	 * @param ctx
	 */
	public abstract void skipStep(ProcessContext ctx);
	
	/**
	 * ����ʽ��������
	 * Description: 
	 * @param ctx
	 */
	public abstract void sendInteractCmd(ProcessContext ctx);
	
	/**
	 * ��������ִ�л�ȡ�м�ִ�н��
	 * Description: 
	 * @param ctx
	 */
	public abstract void getInteractMsg(ProcessContext ctx);
	
}
 
