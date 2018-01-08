/**
 * Title: DelayTempSqlInputBean.java
 * File Description: 临时sql权限申请延期输入接口 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年3月22日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: 临时sql权限申请延期输入接口
 * @author HT
 */
public class DelayTempSqlInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 1443246640995862710L;
	
	/**
	 * 数据源名
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "数据源名";
	
	/**
	 * 表名
	 */
	private String tbl_name;
	
	public static final String TBL_NAMECN = "表名";
	
	/**
	 *临时权限结束时间
	 */
	private JaDateTime tempend_bk_datetime;

	public static final String TEMPEND_BK_DATETIMECN = "临时权限结束时间";

	/**
	 * @return soc_name 数据源名
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name 数据源名
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return tbl_name 表名
	 */
	public String getTbl_name() {
		return tbl_name;
	}

	/**
	 * @param tbl_name 表名
	 */
	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}

	/**
	 * @return tempend_bk_datetime 临时权限结束时间
	 */
	public JaDateTime getTempend_bk_datetime() {
		return tempend_bk_datetime;
	}

	/**
	 * @param tempend_bk_datetime 临时权限结束时间
	 */
	public void setTempend_bk_datetime(JaDateTime tempend_bk_datetime) {
		this.tempend_bk_datetime = tempend_bk_datetime;
	}
}
