/**
 * Title: QueryAllChChannelOutputBean.java
 * File Description: ��ѯ����������Ϣ�б�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��14��
 */
package com.wk.cd.system.ch.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.ch.info.ChChannelInfo;

/**
 * Class Description: ��ѯ����������Ϣ�б�����ӿ�
 * @author HT
 */
public class QueryAllChChannelOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 2817185583114497169L;
	
	/**
	 * �����б�
	 */
	private List<ChChannelInfo> channel_list;
	
	public static final String CHANNEL_LISTCN="�����б�";

	/**
	 * @return channel_list �����б�
	 */
	public List<ChChannelInfo> getChannel_list() {
		return this.channel_list;
	}

	/**
	 * @param channel_list �����б�
	 */
	public void setChannel_list(List<ChChannelInfo> channel_list) {
		this.channel_list = channel_list;
	}
}
