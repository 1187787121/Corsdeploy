/**
 * Title: PageDictOutputBean.java
 * File Description: 分页查询数据字典列表输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014年11月17日
 */
package com.wk.cd.system.dc.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.dc.info.DcDictInfo;

/**
 * Class Description: 分页查询数据字典列表输出接口类
 * @author HT
 */
public class PageDictOutputBean extends PageQueryActionRootOutputBean{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = -8239477877829102789L;

	/**
	 * 数据字典列表
	 */
	private List<DcDictInfo> dict_list;
	
	public final String DICT_LISTCN="数据字典列表";
	
	/**
	 * @return dict_list 数据字典列表
	 */
	public List<DcDictInfo> getDict_list() {
		return this.dict_list;
	}

	/**
	 * @param dict_list 数据字典列表
	 */
	public void setDict_list(List<DcDictInfo> dict_list) {
		this.dict_list = dict_list;
	}

}
