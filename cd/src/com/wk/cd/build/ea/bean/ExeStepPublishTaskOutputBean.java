/**
 * Title: ExeStepPublishTaskOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class ExeStepPublishTaskOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : -3757110336154023969L
	 */ 
	private static final long serialVersionUID = -3757110336154023969L;
	
	/**
	 * �¸��׶εĽ׶κ�
	 */
	private int next_phase ;
	
	public static final String NEXT_PHASECN = "�¸��׶εĽ׶κ�";

	/**
	 * @return next_phase �¸��׶εĽ׶κ�
	 */
	public int getNext_phase() {
		return this.next_phase;
	}

	/**
	 * @param next_phase �¸��׶εĽ׶κ�
	 */
	public void setNext_phase(int next_phase) {
		this.next_phase = next_phase;
	}
	
	
	

}
