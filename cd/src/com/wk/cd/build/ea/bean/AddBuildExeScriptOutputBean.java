/**
 * Title: AddBuildExeScriptOutputBean.java
 * File Description: 参数配置输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 参数配置输出接口
 * @author xuph
 */
public class AddBuildExeScriptOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : 5968149308634440628L
	 */ 
	private static final long serialVersionUID = 5968149308634440628L;

	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *脚本类型
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "脚本类型";

	/**
	 *脚本序号
	 */
	private long script_bk_seq;

	public static final String SCRIPT_BK_SEQCN = "脚本序号";
	
	/**
	 *@return work_id 任务编号
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 *@param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 *@return script_type 脚本类型
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 *@param script_type 脚本类型
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}

	/**
	 *@return script_bk_seq 脚本序号
	 */
	public long getScript_bk_seq() {
		return this.script_bk_seq;
	}

	/**
	 *@param script_bk_seq 脚本序号
	 */
	public void setScript_bk_seq(long script_bk_seq) {
		this.script_bk_seq = script_bk_seq;
	}

}
