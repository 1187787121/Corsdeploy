/**
 * Title: PageTermOutputBean.java
 * File Description: �鿴�ն�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.tm.info.TmTermInfo;

/**
 * Class Description: �鿴�ն�����ӿ�
 * @author tlw
 */
public class PageTermOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = -9003583143851937210L;

	private List<TmTermInfo> term_list;

	public static final String TERM_LISTCN = "�ն���Ϣ�б�";

	/**
	 * @return term_list �ն���Ϣ�б�
	 */
	public List<TmTermInfo> getTerm_list() {
		return term_list;
	}

	/**
	 * @param term_list �ն���Ϣ�б�
	 */
	public void setTerm_list(List<TmTermInfo> term_list) {
		this.term_list = term_list;
	}

}
