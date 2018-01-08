/**
 * Title: UserAppBean.java
 * File Description: �û����ŵ������û�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2015��11��18��
 */
package com.wk.cd.system.us.bean;

/**
 * Class Description: �û����ŵ������û�
 * @author Xul
 */
public class UserAppBean {
	
	/**
	 *�û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���";
	
	/**
	 *�û�����
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "�û�����";
	
	/**
	 * �Ƿ񹫹��ʺ�(0���ǣ�1��)
	 */
	private int is_public_account;
	
	public static final String IS_PUBLIC_ACCOUNTCN = "�Ƿ񹫹��ʺ�(0���ǣ�1��)";
	
	/**
	 * @return �û���
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * @param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @return �û�����
	 */
	public String getUser_cn_name() {
		return user_cn_name;
	}
	
	/**
	 * @param user_cn_name �û�����
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return is_public_account �Ƿ񹫹��ʺ�(0���ǣ�1��)
	 */
	public int getIs_public_account() {
		return is_public_account;
	}

	/**
	 * @param is_public_account �Ƿ񹫹��ʺ�(0���ǣ�1��)
	 */
	public void setIs_public_account(int is_public_account) {
		this.is_public_account = is_public_account;
	}
	
}
