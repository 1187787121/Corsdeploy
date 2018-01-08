/**
 * Title: PageSrvgOutputBean.java
 * File Description: 分页查询服务组列表输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.sv.info.SvSrvgInfo;

/**
 * Class Description: 分页查询服务组列表输出接口
 * @author HT
 */
public class PageSrvgOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 2016154846599428169L;
	
	/**
	 * 服务组列表
	 */
	private List<SvSrvgInfo> srvg_list;
	
	public static final String SRVG_LISTCN="服务组列表";

	/**
	 * @return srvg_list 服务组列表
	 */
	public List<SvSrvgInfo> getSrvg_list() {
		return this.srvg_list;
	}

	/**
	 * @param srvg_list 服务组列表
	 */
	public void setSrvg_list(List<SvSrvgInfo> srvg_list) {
		this.srvg_list = srvg_list;
	}
	
}
