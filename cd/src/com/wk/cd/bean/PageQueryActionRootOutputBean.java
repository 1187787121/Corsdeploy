/**
 * Title: PageQueryActionRootOutputBean.java
 * File Description: ��ҳʽ��ѯ����ӿڻ���
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 1/6/2015
 */

package com.wk.cd.bean;

/**
 * Class Description:��ҳʽ��ѯ����ӿڻ���
 * @author lixl
 */
public class PageQueryActionRootOutputBean extends ActionRootOutputBean {
	private static final long serialVersionUID = 9091813608894044296L;

	/**
	 * ��¼������
	 */
	private int all_recd;

	public static final String ALL_RECDCN = "��¼������";

	/**
	 * @return int ��¼������
	 */
	public int getAll_recd(){
		return this.all_recd;
	}

	/**
	 * @param all_recd ��¼������
	 */
	public void setAll_recd(int all_recd){
		this.all_recd = all_recd;
	}

}

