/**
 * Title: ProgramCombine.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017��3��27��
 */
package com.wk.cd.module1.info;

import java.util.List;

/**
 * Class Description:
 * @author yangl
 */
public class PackageCombine {

	/**
	 * ������ɽ׶κ��б�
	 */
	private List<Integer> gen_phase_list;
	
	/**
	 * ��Ͽ�ѡ�׶κ��б�
	 */
	private List<Integer> selectable_phase_list;

	/**
	 * ��ϲ������б�
	 */
	private List<String> param_name_list;
	
	/**
	 * @return gen_phase_list
	 */
	public List<Integer> getGen_phase_list() {
		return gen_phase_list;
	}

	/**
	 * @param gen_phase_list
	 */
	public void setGen_phase_list(List<Integer> gen_phase_list) {
		this.gen_phase_list = gen_phase_list;
	}

	/**
	 * @return param_name_list
	 */
	public List<String> getParam_name_list() {
		return param_name_list;
	}

	/**
	 * @param param_name_list
	 */
	public void setParam_name_list(List<String> param_name_list) {
		this.param_name_list = param_name_list;
	}

	/**
	 * @return selectable_phase_list
	 */
	public List<Integer> getSelectable_phase_list() {
		return selectable_phase_list;
	}

	/**
	 * @param selectable_phase_list
	 */
	public void setSelectable_phase_list(List<Integer> selectable_phase_list) {
		this.selectable_phase_list = selectable_phase_list;
	}

}
