/**
 * Title: QueryFirstNavigagePrivOutputBean.java
 * File Description: ��ȡ�û�һ������Ȩ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��11��23��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.rs.info.RsResInfo;

/**
 * Class Description: ��ȡ�û�һ������Ȩ������ӿ�
 * @author HT
 */
public class QueryFirstNavigagePrivOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -357953927630448566L;

	/**
	 *һ�������б�
	 */
	private List<RsResInfo> nav_list;

	public static final String NAV_LISTCN = "һ�������б�";

	/**
	 * @return nav_list
	 */
	public List<RsResInfo> getNav_list() {
		return this.nav_list;
	}

	/**
	 * @param nav_list
	 */
	public void setNav_list(List<RsResInfo> nav_list) {
		this.nav_list = nav_list;
	}
}
