/**
 * Title: InteractMsgBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��8��11��
 */
package com.wk.cd.remote.bean;

/**
 * Class Description: ����ִ�н����Ϣ
 * @author zhangj
 */
public class AsyncMsgBean {
	
	private String msg;
	
	private boolean end_flag;

	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return end_flag
	 */
	public boolean isEnd_flag() {
		return end_flag;
	}

	/**
	 * @param end_flag
	 */
	public void setEnd_flag(boolean end_flag) {
		this.end_flag = end_flag;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "AsyncMsgBean [msg=" + msg + ", end_flag=" + end_flag + "]";
	}
	
	

}
