/**
 * Title: UsUserTermBean.java
 * File Description: �û������ն˽ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��15��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.system.us.info.UsUserTermInfo;

/**
 * Class Description: �û������ն˽ӿ�
 * @author HT
 */
public class UsUserTermBean extends UsUserTermInfo{

	private static final long serialVersionUID = -1029229450097795493L;
	
	/**
	 *����������
	 */
	private String channel_cn_name;

	public static final String CHANNEL_CN_NAMECN = "����������";

	/**
	 * @return channel_cn_name ����������
	 */
	public String getChannel_cn_name() {
		return this.channel_cn_name;
	}

	/**
	 * @param channel_cn_name ����������
	 */
	public void setChannel_cn_name(String channel_cn_name) {
		this.channel_cn_name = channel_cn_name;
	}
}
