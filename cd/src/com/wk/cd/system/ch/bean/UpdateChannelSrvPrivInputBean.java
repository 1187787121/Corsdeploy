/**
 * Title: UpdateChannelSrvPrivInputBean.java
 * File Description: �޸���������Ȩ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��13��
 */
package com.wk.cd.system.ch.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.ch.info.ChChannelSrvPrivInfo;
import com.wk.cd.system.ch.info.ChChannelSrvgPrivInfo;

/**
 * Class Description: �޸���������Ȩ������ӿ�
 * @author HT
 */
public class UpdateChannelSrvPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6899407716131435165L;
	
	/**
	 * ��������
	 */
	private String channel_code;
	
	public static final String CHANNEL_CODECN="��������";
	
	/**
	 * ����Ȩ��
	 */
	private List<ChChannelSrvPrivInfo> srv_priv;
	
	public static final String SRV_PRIVCN="����Ȩ��";
	
	/**
	 * ������Ȩ��
	 */
	private List<ChChannelSrvgPrivInfo> srvg_priv;
	
	public static final String SRVG_PRIVCN="������Ȩ��";
	
	/**
	 * @return channel_code ��������
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 * @param channel_code ��������
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	
	/**
	 * @return srv_priv ����Ȩ��
	 */
	public List<ChChannelSrvPrivInfo> getSrv_priv() {
		return this.srv_priv;
	}

	/**
	 * @param srv_priv ����Ȩ��
	 */
	public void setSrv_priv(List<ChChannelSrvPrivInfo> srv_priv) {
		this.srv_priv = srv_priv;
	}

	/**
	 * @return srvg_priv ������Ȩ��
	 */
	public List<ChChannelSrvgPrivInfo> getSrvg_priv() {
		return this.srvg_priv;
	}

	/**
	 * @param srvg_priv ������Ȩ��
	 */
	public void setSrvg_priv(List<ChChannelSrvgPrivInfo> srvg_priv) {
		this.srvg_priv = srvg_priv;
	}
}
