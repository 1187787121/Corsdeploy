/**
 * Title: QueryChannelInputBean.java
 * File Description: ��ѯ������Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.ch.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ������Ϣ����ӿ�
 * @author HT
 */
public class QueryChannelInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -925818470302063948L;
	
	/**
	 *��������
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "��������";
	
	/**
	 *@return channel_code ��������
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 *@param channel_code ��������
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

}