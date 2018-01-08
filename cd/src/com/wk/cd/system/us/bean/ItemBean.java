/**
 * Title: ItemBean.java
 * File Description: 
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 1/23/2015
 */

package com.wk.cd.system.us.bean;

/**
 * Class Description:
 * @author lixl
 */
public class ItemBean {
	/**
	 * 中文名称
	 */
	private String name;

	/**
	 * 图标地址
	 */
	private int icoindex;

	/**
	 * 地址
	 */
	private String page;

	/**
	 * 宽
	 */
	private String pwidth;

	/**
	 * 高
	 */
	private String gheight;

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return icoindex
	 */
	public int getIcoindex() {
		return this.icoindex;
	}

	/**
	 * @param icoindex
	 */
	public void setIcoindex(int icoindex) {
		this.icoindex = icoindex;
	}

	/**
	 * @return page
	 */
	public String getPage() {
		return this.page;
	}

	/**
	 * @param page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return String pwidth 宽
	 */
	public String getPwidth(){
		return this.pwidth;
	}

	/**
	 * @param pwidth 宽
	 */
	public void setPwidth(String pwidth){
		this.pwidth = pwidth;
	}

	/**
	 * @return String gheight 高
	 */
	public String getGheight(){
		return this.gheight;
	}

	/**
	 * @param gheight 高
	 */
	public void setGheight(String gheight){
		this.gheight = gheight;
	}
}

