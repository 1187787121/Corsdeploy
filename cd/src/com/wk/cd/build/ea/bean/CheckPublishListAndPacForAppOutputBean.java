/**
 * Title: CheckPublishListAndPacOutputBean.java
 * File Description: 校验发布文件清单和投产包输出接口(APP)
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月21日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 校验发布文件清单和投产包输出接口(APP)
 * @author Xul
 */
public class CheckPublishListAndPacForAppOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 7130274563719694825L;
	
	/**
	 * 状态(0正常，1异常)
	 */
	private int status;
	
	public static final String STATUSCN = "状态";
	
	/**
	 * 信息
	 */
	private String message;
	
	public static final String MESSAGECN = "信息";

	/**
	 * @return status 状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return message 信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message 信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
