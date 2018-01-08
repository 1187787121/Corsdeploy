package com.wk.cd.module1;

import com.wk.cd.module1.ModuleInterceptor;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.remote.bean.AsyncMsgBean;

/**
 * ���̽ӿ� ��˳��ִ�У��ɰ��׶Ρ�����ִ�С�������
 */
public interface Process {

	public abstract ProcessContext getCtx();

	//�׶�ִ�� �ڲ�ʵ����һ��һ��ȥִ��
	public Result run(int stage);
	// �׶�ִ�� �ڲ���дΪshell�ļ�ִ��
	// ʹ����ʱ�ļ�ִ�� ��ʱ�ļ�����ʱ������·��
	public Result runStage(String remote_relative_dir,int stage,boolean skip_flag);
	
	public Result stepInto(int stage, int step);

	public Result skip(int stage);

	public Result skipStep(int stage, int step);

	public void addInterceptor(ModuleInterceptor interceptor);

	public void removeInterceptor(ModuleInterceptor interceptor);
	//�������ʱ��رշ���
	public Result close(int stage);
	// ��������ִ�� ���ս׶�ִ��
	public Result interactRun(String remote_relative_dir,int stage);
	// ��������ִ���з��ͽ�������
	public void sendInteractCmd(int stage,String cmd, boolean sensitive_flag);
	// ��������ִ�л�ȡ�м�ִ�н��
	public AsyncMsgBean getInteractMsg(int stage);
}
