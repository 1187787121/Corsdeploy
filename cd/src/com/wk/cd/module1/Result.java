package com.wk.cd.module1;

import java.util.Map;

import com.wk.cd.enu.CMD_STATUS;

/**
 * ���н�� ���������н��״̬���ɹ�������Ϣ���쳣��Ϣ
 */
public class Result {

	protected CMD_STATUS status;

	protected String msg;
	// ����Ƿ��ͽű�ִ�� �����¼������Ϣ
	private String error_msg;

	/**
	 * ��ʼʱ�� ��λ ����
	 */
	protected long start_time;

	/**
	 * ����ʱ�� ��λ ����
	 */
	protected long end_time;

	/**
	 * ��ʱ ��λ ����
	 */
	protected int time_used;
	/**
	 * ��ǰִ�ж�Ӧ�Ľ׶κ� ��0��ʼ��
	 */
	private int phase_no;

	/**
	 * ��ǰִ�н����Ӧ�Ĳ���� ��0��ʼ
	 */
	private int step_no;

	/**
	 * ��������û�з��ؽ�� ���̷��ؽ�����־λtrue
	 */
	private boolean interact_flag;
	/**
	 * ��¼��ǰִ�е�����Դip ���ڹ���ά���ű�ִ�� �д���ִ�н����ʱ��ʹ��
	 */
	private String soc_ip;
	protected Throwable t;

	public Result() {
	}

	public Result(Throwable t, long start_time) {
		setThrowable(t);
		long end_time1 = System.currentTimeMillis();
		this.end_time = end_time1;
		this.start_time = start_time;
		this.time_used = (int) (end_time1 - start_time);
	}

	public Result(CMD_STATUS status, String msg, String error_msg, long start_time) {
		this.status = status;
		this.msg = msg;
		this.error_msg = error_msg;
		long end_time1 = System.currentTimeMillis();
		this.end_time = end_time1;
		this.start_time = start_time;
		this.time_used = (int) (end_time1 - start_time);

	}

	public Result(CMD_STATUS status, String msg, long start_time) {
		this.status = status;
		if (status != CMD_STATUS.SUCCEED && status != CMD_STATUS.SKIP) {
			this.error_msg = msg;
		}
		this.msg = msg;
		long end_time1 = System.currentTimeMillis();
		this.end_time = end_time1;
		this.start_time = start_time;
		this.time_used = (int) (end_time1 - start_time);

	}

	public Map<String, String> getOutput() {
		return null;
	}

	/**
	 * ���״̬
	 * @return
	 */
	public CMD_STATUS getStatus() {
		return status;
	}

	/**
	 * ������Ϣ
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getStart_time() {
		return start_time;
	}

	public long getEnd_time() {
		return end_time;
	}

	public int getTime_used() {
		return time_used;
	}

	public int getPhase_no() {
		return phase_no;
	}

	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}

	public int getStep_no() {
		return step_no;
	}

	public void setStep_no(int step_no) {
		this.step_no = step_no;
	}

	public void setThrowable(Throwable t) {
		this.status = CMD_STATUS.ERROR;
		this.t = t;
		this.msg = t.getMessage();
		this.error_msg = t.getMessage();
	}

	/**
	 * @return interact_flag
	 */
	public boolean isInteract_flag() {
		return interact_flag;
	}

	/**
	 * @param interact_flag
	 */
	public void setInteract_flag(boolean interact_flag) {
		this.interact_flag = interact_flag;
	}

	/**
	 * �쳣
	 * @return
	 */
	public Throwable getThrowable() {
		return t;
	}

	/**
	 * @return soc_ip
	 */
	public String getSoc_ip() {
		return soc_ip;
	}

	/**
	 * @param soc_ip
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}

	public void setStatus(CMD_STATUS status) {
		this.status = status;
	}

	/**
	 * @return error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * @param error_msg
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (status == CMD_STATUS.ERROR) {
			sb.append("Result: {status: ").append(status).append(", msg: \"").append(msg).append("\"}")
					.append(", ex: \"").append(t).append("\"}");
		} else {
			sb.append("Result: {status: ").append(status).append(", msg: \"").append(msg).append("\"}");
		}
		return sb.toString();
	}

}
