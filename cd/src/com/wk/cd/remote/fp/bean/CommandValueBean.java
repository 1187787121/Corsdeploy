/**
 * Title: CommandValueBean.java
 * File Description: ����Bean
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 10/20/2015
 */

package com.wk.cd.remote.fp.bean;

/**
 * Class Description: ����Bean
 * @author lixl
 */
public class CommandValueBean {
	private String cmd;

	private String value;

	/**
	 * @return String ����
	 */
	public String getCmd(){
		return this.cmd;
	}

	/**
	 * @param cmd ���
	 */
	public void setCmd(String cmd){
		this.cmd = cmd;
	}

	/**
	 * @return String �����Ӧֵ
	 */
	public String getValue(){
		return this.value;
	}

	/**
	 * @param value �����Ӧֵ
	 */
	public void setValue(String value){
		this.value = value;
	}

}

