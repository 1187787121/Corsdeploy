package com.wk.cd.module1.bean;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.module1.info.MoTemplateInfo;
import java.util.List;

public class PageTemplateListOutputBean extends PageQueryActionRootOutputBean {
	
	private static final long serialVersionUID = 1454754385597973106L;
	
	/**
	 * ģ���б�
	 */
	private List<MoTemplateInfo> tp_all_list;
	
	public static final String TP_ALL_LISTCN = "ģ���б�";

	public List<MoTemplateInfo> getTp_all_list() {
		return this.tp_all_list;
	}

	public void setTp_all_list(List<MoTemplateInfo> tp_all_list) {
		this.tp_all_list = tp_all_list;
	}
}