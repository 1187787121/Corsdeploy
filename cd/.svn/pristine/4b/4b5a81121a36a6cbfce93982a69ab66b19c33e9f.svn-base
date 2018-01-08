package com.wk.cd.module1;

import java.util.Map;

import com.wk.cd.enu.CMD_STATUS;

/**
 * 运行结果 包括：运行结果状态，成功返回信息，异常信息
 */
public class Result {

	protected CMD_STATUS status;

	protected String msg;
	// 如果是发送脚本执行 这里记录报错信息
	private String error_msg;

	/**
	 * 开始时间 单位 毫秒
	 */
	protected long start_time;

	/**
	 * 结束时间 单位 毫秒
	 */
	protected long end_time;

	/**
	 * 耗时 单位 毫秒
	 */
	protected int time_used;
	/**
	 * 当前执行对应的阶段号 从0开始，
	 */
	private int phase_no;

	/**
	 * 当前执行结果对应的步骤号 从0开始
	 */
	private int step_no;

	/**
	 * 交互命令没有返回结果 立刻返回交互标志位true
	 */
	private boolean interact_flag;
	/**
	 * 记录当前执行的数据源ip 用于故障维护脚本执行 中处理执行结果的时候使用
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
	 * 结果状态
	 * @return
	 */
	public CMD_STATUS getStatus() {
		return status;
	}

	/**
	 * 返回信息
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
	 * 异常
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
