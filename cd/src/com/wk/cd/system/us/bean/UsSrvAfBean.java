/**
 * Title: UsSrvAfBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��4��2��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.system.sv.info.SvSrvInfo;

/**
 * Class Description: �û�����Ȩ����չbean
 * @author HT
 */
public class UsSrvAfBean extends SvSrvInfo{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 2334320356687370700L;

	/**
	 *�����ֹ��־
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "�����ֹ��־";

	/**
	 * @return af_flag �����ֹ��־
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 * @param af_flag �����ֹ��־
	 */ 
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}
}
