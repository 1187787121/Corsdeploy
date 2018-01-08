package com.wk.cd.module1.impl;

import com.wk.cd.module1.ModuleSession;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;

/**
 * Module�Ự���Ӽ̳л���
 *
 * Created by ��־�� on 2016/11/25.
 */
public abstract class ModuleSessionBase implements ModuleSession {

    private boolean connected;
    private boolean real_close = true;
	protected ModuleSourceInfo module_source_info;
	
	protected final int step_count;
	
	public ModuleSessionBase (int step_count){
		this.step_count = step_count;
	}
	
	
    public boolean isConnected() {
        return connected;
    }

	@Override
	public String getKey() {
		Assert.assertNotEmpty(module_source_info.getProtocol_type(), ModuleSourceInfo.PROTOCOL_TYPECN);
		Assert.assertNotEmpty(module_source_info.getDt_source_info(), ModuleSourceInfo.DT_SOURCE_INFOCN);
		String type = module_source_info.getProtocol_type().getName();
		String soc_name = module_source_info.getDt_source_info().getSoc_name();
		return type + "_" + soc_name;
	}
    @Override
    public void setRealDisconnect(boolean real_close) {
        this.real_close = real_close;
    }

    @Override
    public void connect() {
        implConnect();
        connected = true;
    }

    @Override
    public void disconnect() {
        if (real_close) {
            realDisconnect();
        }
    }
    
    public void forceDisconnect(){
    	real_close = true;
    	 realDisconnect();
    }

    private void realDisconnect() {
        connected = false;
        implDisconnect();
    }


    protected abstract void implConnect();
    protected abstract void implDisconnect();

}