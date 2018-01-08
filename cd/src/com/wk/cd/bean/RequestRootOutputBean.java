/**
 * Title: RequestActionRootOutputBean.java
 * File Description: 服务请求基类输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月12日
 */
package com.wk.cd.bean;

import java.io.Serializable;

import com.wk.cd.exc.CannotCloneException;

/**
 * Class Description: 服务请求基类输出接口
 * @author HT
 */
public class RequestRootOutputBean implements Cloneable, Serializable{

	private static final long serialVersionUID = -7045992115376912574L;
	
	/**
     * 深度拷贝 构造函数
     * @param bean 
     */
	public RequestRootOutputBean(RequestRootOutputBean bean){
		this.request_state = bean.getRequest_state();
		this.message_code = bean.getMessage_code();
		this.message_text = bean.getMessage_text();
	}
		
	/**
	 * 构造函数
	 */
	public RequestRootOutputBean(){
	}

	
	/**
	 * 成功标志
	 */
	private boolean request_state;
	
	public static final String REQUEST_STATECN = "成功标志";
	  
	/**
	 * 异常代码
	 */
	private String message_code;
	
	public static final String MESSAGE_CODECN = "异常代码";
	
	/**
	 * 异常文本
	 */
	private String message_text;
	
	public static final String MESSAGE_TEXTCN = "异常文本";

	/**
	 * @return request_state 成功标志
	 */
	public boolean getRequest_state() {
		return this.request_state;
	}

	/**
	 * @param request_state 成功标志
	 */
	public void setRequest_state(boolean request_state) {
		this.request_state = request_state;
	}

	/**
	 * @return message_code 异常代码
	 */
	public String getMessage_code() {
		return this.message_code;
	}

	/**
	 * @param message_code 异常代码
	 */
	public void setMessage_code(String message_code) {
		this.message_code = message_code;
	}

	/**
	 * @return message_text 异常文本
	 */
	public String getMessage_text() {
		return this.message_text;
	}

	/**
	 * @param message_text 异常文本
	 */
	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}
	
	@Override
	public RequestRootOutputBean clone() {
		try
		{
			return (RequestRootOutputBean) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new CannotCloneException().addScene("CLASS",this.getClass().getName());
		}
	}
}
