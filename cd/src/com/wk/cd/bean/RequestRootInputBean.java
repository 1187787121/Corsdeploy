/**
 * Title: RequestActionRootInputBean.java
 * File Description: 服务请求基类输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月12日
 */
package com.wk.cd.bean;

import java.io.Serializable;

import com.wk.cd.exc.CannotCloneException;

/**
 * Class Description: 服务请求基类输入接口
 * @author HT
 */
public class RequestRootInputBean implements Cloneable, Serializable{

	private static final long serialVersionUID = 7294035073917058732L;
	
	/**
     * 深度拷贝 构造函数
     * @param bean 
     */
    public RequestRootInputBean(RequestRootInputBean bean){
    	this.org_channel_code = bean.getOrg_channel_code();
    }

    /**
     * 构造函数
     */
    public RequestRootInputBean(){
    }
    
    /**
     * 发起渠道
     */
    private transient String org_channel_code;
    
    public static final String ORG_CHANNEL_CODECN = "发起渠道";
    
    /**
	 * @return org_channel_code 发起渠道
	 */
	public String getOrg_channel_code() {
		return this.org_channel_code;
	}

	/**
	 * @param orgChannelCode 发起渠道
	 */
	public void setOrg_channel_code(String orgChannelCode) {
		this.org_channel_code = orgChannelCode;
	}
	
	@Override
	public RequestRootInputBean clone() {
		try
		{
			return (RequestRootInputBean) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new CannotCloneException().addScene("CLASS",this.getClass().getName());
		}
	}
}
