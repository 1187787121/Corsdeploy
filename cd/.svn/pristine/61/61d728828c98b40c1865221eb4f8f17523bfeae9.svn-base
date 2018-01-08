/**
 * Title: QueryRsPrivOutputBean.java
 * File Description: 查询用户资源权限输出接口
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 1/23/2015
 */

package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description:查询用户资源权限输出接口
 * @author lixl
 */
public class QueryRsPrivOutputBean extends ActionRootOutputBean{
	private static final long serialVersionUID = -6230682114882296739L;
	/**
	 * 导航资源权限（包括三级）
	 */
	private List<LevelMenuBean> level_menu_list;
	
	/**
	 * 导航默认打开索引
	 */
	private int defaultmenu;
	
	/**
	 * 用户工作区域权限
	 */
	private List<ItemBean> item;
	
	/**
	 * 操作资源配置
	 */
	private List<RsUrlBean> rs_config;
	
	/**
	 * 资源编码和URL对照列表
	 */
	private List<RsUrlBean> rsurl;

	/**
	 * 待处理任务数
	 */
	private int pwork_bk_num;
	
	/**
	 * 待处理终端申请数
	 */
	private int pterm_bk_num;

	/**
	 * @return level_menu_list 导航资源权限（包括三级）
	 */
	public List<LevelMenuBean> getLevel_menu_list() {
		return level_menu_list;
	}

	/**
	 * @param level_menu_list 导航资源权限（包括三级）
	 */
	public void setLevel_menu_list(List<LevelMenuBean> level_menu_list) {
		this.level_menu_list = level_menu_list;
	}

	/**
	 * @return defaultmenu导航默认打开索引
	 */
	public int getDefaultmenu() {
		return this.defaultmenu;
	}

	/**
	 * @param defaultmenu导航默认打开索引
	 */
	public void setDefaultmenu(int defaultmenu) {
		this.defaultmenu = defaultmenu;
	}

	/**
	 * @return item用户工作区域权限
	 */
	public List<ItemBean> getItem() {
		return this.item;
	}

	/**
	 * @param item用户工作区域权限
	 */
	public void setItem(List<ItemBean> item) {
		this.item = item;
	}

	/**
	 * @return int 待处理任务数
	 */
	public int getPwork_bk_num(){
		return this.pwork_bk_num;
	}

	/**
	 * @param pwork_bk_num 待处理任务数
	 */
	public void setPwork_bk_num(int pwork_bk_num){
		this.pwork_bk_num = pwork_bk_num;
	}

	/**
	 * @return rs_config 操作资源配置
	 */
	public List<RsUrlBean> getRs_config() {
		return this.rs_config;
	}

	/**
	 * @param rs_config 操作资源配置
	 */
	public void setRs_config(List<RsUrlBean> rs_config) {
		this.rs_config = rs_config;
	}
	
	/**
	 * @return rsurl
	 */
	public List<RsUrlBean> getRsurl() {
		return this.rsurl;
	}

	/**
	 * @param rsurl
	 */
	public void setRsurl(List<RsUrlBean> rsurl) {
		this.rsurl = rsurl;
	}

	/**
	 * @return pterm_bk_num  待处理终端申请数
	 */
	public int getPterm_bk_num() {
		return this.pterm_bk_num;
	}

	/**
	 * @param pterm_bk_num 待处理终端申请数
	 */
	public void setPterm_bk_num(int pterm_bk_num) {
		this.pterm_bk_num = pterm_bk_num;
	}
}

