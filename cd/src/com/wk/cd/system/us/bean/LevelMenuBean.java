/**
 * Title: LevelMenuBean.java
 * File Description: һ������Ŀ¼�ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��10��11��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

/**
 * Class Description: һ������Ŀ¼�ӿ�
 * @author Xul
 */
public class LevelMenuBean {
	
	/**
	 * Ŀ¼��
	 */
	private String menu_name;
	
	public static final String MENU_NAMECN = "Ŀ¼��";
	
	/**
	 * �����
	 */
	private int sort_key;

	public static final String SORT_KEYCN = "�����";
	
	/**
	 * ��Դͼ���ַ
	 */
	private String rsim_url;

	public static final String RSIM_URLCN = "��Դͼ���ַ";
	
	/**
	 * ��������Ŀ¼�б�
	 */
	private List<MenuBean> meun_bean_list;
	
	public static final String MEUN_BEAN_LISTCN = "��������Ŀ¼�б�";

	/**
	 * @return menu_name Ŀ¼��
	 */
	public String getMenu_name() {
		return menu_name;
	}

	/**
	 * @param menu_name Ŀ¼��
	 */
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	/**
	 * @return sort_key �����
	 */
	public int getSort_key() {
		return sort_key;
	}

	/**
	 * @param sort_key �����
	 */
	public void setSort_key(int sort_key) {
		this.sort_key = sort_key;
	}

	/**
	 * @return meun_bean_list ��������Ŀ¼�б�
	 */
	public List<MenuBean> getMeun_bean_list() {
		return meun_bean_list;
	}

	/**
	 * @param meun_bean_list ��������Ŀ¼�б�
	 */
	public void setMeun_bean_list(List<MenuBean> meun_bean_list) {
		this.meun_bean_list = meun_bean_list;
	}

	/**
	 * @return rsim_url ��Դͼ���ַ
	 */
	public String getRsim_url() {
		return rsim_url;
	}

	/**
	 * @param rsim_url ��Դͼ���ַ
	 */
	public void setRsim_url(String rsim_url) {
		this.rsim_url = rsim_url;
	}
}
