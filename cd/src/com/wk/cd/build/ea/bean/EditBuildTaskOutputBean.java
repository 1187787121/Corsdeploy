/**
 * Title: EditBuildTaskOutputBean.java
 * File Description: �༭����������������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: �༭����������������ӿ�
 * @author wangj
 */
public class EditBuildTaskOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -2655044007071716011L;

	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * ִ����־
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "ִ����־";
	
	/**
	 * ��ǰ����������
	 */
	private int build_step_id;
	
	public static final String BUILD_STEP_IDCN = "��ǰ����������";
	
	/**
	 * @return work_id ������
	 */
	public String getWork_id() {
		return work_id;
	}
	/**
	 * @param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	/**
	 * @return exelog_bk_path ִ����־
	 */
	public String getExelog_bk_path() {
		return exelog_bk_path;
	}
	/**
	 * @param exelog_bk_path ִ����־
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}
	/**
	 * @return build_step_id ��ǰ����������
	 */
	public int getBuild_step_id() {
		return build_step_id;
	}
	/**
	 * @param build_step_id ��ǰ����������
	 */
	public void setBuild_step_id(int build_step_id) {
		this.build_step_id = build_step_id;
	}
	
}
