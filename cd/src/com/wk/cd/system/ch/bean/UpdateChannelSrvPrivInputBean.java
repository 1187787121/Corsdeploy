/**
 * Title: UpdateChannelSrvPrivInputBean.java
 * File Description: 修改渠道服务权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月13日
 */
package com.wk.cd.system.ch.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.ch.info.ChChannelSrvPrivInfo;
import com.wk.cd.system.ch.info.ChChannelSrvgPrivInfo;

/**
 * Class Description: 修改渠道服务权限输入接口
 * @author HT
 */
public class UpdateChannelSrvPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6899407716131435165L;
	
	/**
	 * 渠道编码
	 */
	private String channel_code;
	
	public static final String CHANNEL_CODECN="渠道编码";
	
	/**
	 * 服务权限
	 */
	private List<ChChannelSrvPrivInfo> srv_priv;
	
	public static final String SRV_PRIVCN="服务权限";
	
	/**
	 * 服务组权限
	 */
	private List<ChChannelSrvgPrivInfo> srvg_priv;
	
	public static final String SRVG_PRIVCN="服务组权限";
	
	/**
	 * @return channel_code 渠道编码
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 * @param channel_code 渠道编码
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	
	/**
	 * @return srv_priv 服务权限
	 */
	public List<ChChannelSrvPrivInfo> getSrv_priv() {
		return this.srv_priv;
	}

	/**
	 * @param srv_priv 服务权限
	 */
	public void setSrv_priv(List<ChChannelSrvPrivInfo> srv_priv) {
		this.srv_priv = srv_priv;
	}

	/**
	 * @return srvg_priv 服务组权限
	 */
	public List<ChChannelSrvgPrivInfo> getSrvg_priv() {
		return this.srvg_priv;
	}

	/**
	 * @param srvg_priv 服务组权限
	 */
	public void setSrvg_priv(List<ChChannelSrvgPrivInfo> srvg_priv) {
		this.srvg_priv = srvg_priv;
	}
}
