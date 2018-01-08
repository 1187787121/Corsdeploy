/**
 * Title: AddChannelInputBean.java
 * File Description: ����������Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.ch.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.CHANNEL_TYPE;

/**
 * Class Description: ����������Ϣ����ӿ�
 * @author HT
 */
public class AddChannelInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 8626479711573740244L;

	/**
	 *������������
	 */
	private String channel_cn_name;

	public static final String CHANNEL_CN_NAMECN = "������������";

	/**
	 *��������
	 */
	private CHANNEL_TYPE channel_type;

	public static final String CHANNEL_TYPECN = "��������";

	/**
	 *��������
	 */
	private String channel_bk_desc;

	public static final String CHANNEL_BK_DESCCN = "��������";

	/**
	 *@return channel_cn_name ������������
	 */
	public String getChannel_cn_name() {
		return this.channel_cn_name;
	}

	/**
	 *@param channel_cn_name ������������
	 */
	public void setChannel_cn_name(String channel_cn_name) {
		this.channel_cn_name = channel_cn_name;
	}

	/**
	 *@return channel_type ��������
	 */
	public CHANNEL_TYPE getChannel_type() {
		return this.channel_type;
	}

	/**
	 *@param channel_type ��������
	 */
	public void setChannel_type(CHANNEL_TYPE channel_type) {
		this.channel_type = channel_type;
	}

	/**
	 *@return channel_bk_desc ��������
	 */
	public String getChannel_bk_desc() {
		return this.channel_bk_desc;
	}

	/**
	 *@param channel_bk_desc ��������
	 */
	public void setChannel_bk_desc(String channel_bk_desc) {
		this.channel_bk_desc = channel_bk_desc;
	}
}
