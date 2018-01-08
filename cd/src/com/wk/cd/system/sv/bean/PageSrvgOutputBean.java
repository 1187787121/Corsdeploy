/**
 * Title: PageSrvgOutputBean.java
 * File Description: ��ҳ��ѯ�������б�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.sv.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.sv.info.SvSrvgInfo;

/**
 * Class Description: ��ҳ��ѯ�������б�����ӿ�
 * @author HT
 */
public class PageSrvgOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 2016154846599428169L;
	
	/**
	 * �������б�
	 */
	private List<SvSrvgInfo> srvg_list;
	
	public static final String SRVG_LISTCN="�������б�";

	/**
	 * @return srvg_list �������б�
	 */
	public List<SvSrvgInfo> getSrvg_list() {
		return this.srvg_list;
	}

	/**
	 * @param srvg_list �������б�
	 */
	public void setSrvg_list(List<SvSrvgInfo> srvg_list) {
		this.srvg_list = srvg_list;
	}
	
}
