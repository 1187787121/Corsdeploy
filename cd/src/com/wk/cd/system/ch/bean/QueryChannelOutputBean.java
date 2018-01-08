/**
 * Title: QueryChannelOutputBean.java
 * File Description: ��ѯ������Ϣ����ӿ� 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.ch.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.CHANNEL_TYPE;

/**
 * Class Description:��ѯ������Ϣ����ӿ� 
 * @author HT
 */
public class QueryChannelOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 1278298254195787030L;
	
	/**
	 *��������
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "��������";

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
