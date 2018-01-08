/**
 * Title: DeptExtendsBean.java
 * File Description: 资源信息表扩展类
 * @copyright: 2015
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.system.rs.info.RsResInfo;

/**
 * Class Description: 资源信息表扩展类
 * @author xuy
 */
public class ResExtendsBean extends RsResInfo{
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 3456852691111516804L;

	private String lowrs_bk_num;
	
	public static final String LOWRS_BK_NUMCN = "下级资源数量";

	/**
	 * @return lowrs_bk_num 下级资源数量
	 */
	public String getLowrs_bk_num() {
		return lowrs_bk_num;
	}

	/**
	 * @param lowrsBkNum 下级资源数量
	 */
	public void setLowrs_bk_num(String lowrsBkNum) {
		this.lowrs_bk_num = lowrsBkNum;
	}
	

}
