/**
 * Title: PageEnvProgOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class QueryEnvProgOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 3551073437160403906L;
	
	/**
	 * ���������б�
	 */
	private List<PgProgramInfo> program_list;
	
	public static final String PROGRAM_LISTCN = "���������б�";

	/**
	 * @return program_list ���������б�
	 */
	public List<PgProgramInfo> getProgram_list() {
		return program_list;
	}

	/**
	 * @param program_list ���������б�
	 */
	public void setProgram_list(List<PgProgramInfo> program_list) {
		this.program_list = program_list;
	}
	
	

}
