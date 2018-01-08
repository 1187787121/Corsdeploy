/**
 * Title: Script.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年7月13日
 */
package com.wk.cd.module1.info;

/**
 * Class Description:
 * @author yangl
 */
public class Script {

	private String script_type;

	private String[] cmds;

	/**
	 * @return script_type
	 */
	public String getScript_type() {
		return script_type;
	}

	/**
	 * @param script_type
	 */
	public void setScript_type(String script_type) {
		this.script_type = script_type;
	}

	/**
	 * @return cmds
	 */
	public String[] getCmds() {
		return cmds;
	}

	/**
	 * @param cmds
	 */
	public void setCmds(String[] cmds) {
		this.cmds = cmds;
	}

	/**
	 * 构造函数
	 */
	public Script() {
		super();
	}

	/**
	 * 构造函数
	 * @param script_type
	 * @param cmds
	 */
	public Script(String script_type, String[] cmds) {
		super();
		this.script_type = script_type;
		this.cmds = cmds;
	}

}
