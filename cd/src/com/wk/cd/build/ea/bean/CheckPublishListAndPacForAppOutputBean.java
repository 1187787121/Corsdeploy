/**
 * Title: CheckPublishListAndPacOutputBean.java
 * File Description: У�鷢���ļ��嵥��Ͷ��������ӿ�(APP)
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��21��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: У�鷢���ļ��嵥��Ͷ��������ӿ�(APP)
 * @author Xul
 */
public class CheckPublishListAndPacForAppOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 7130274563719694825L;
	
	/**
	 * ״̬(0������1�쳣)
	 */
	private int status;
	
	public static final String STATUSCN = "״̬";
	
	/**
	 * ��Ϣ
	 */
	private String message;
	
	public static final String MESSAGECN = "��Ϣ";

	/**
	 * @return status ״̬
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status ״̬
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return message ��Ϣ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message ��Ϣ
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
