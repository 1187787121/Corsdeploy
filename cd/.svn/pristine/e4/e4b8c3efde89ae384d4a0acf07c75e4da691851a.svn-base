package com.wk.cd.module1.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.wk.cd.module1.Module;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleBase;
import com.wk.cd.module1.impl.Shell;
import com.wk.cd.module1.impl.UriModule;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.system.dt.info.DtSourceInfo;

/**
 * 简单的通过URI创建的Module
 *
 * Created by 姜志刚 on 2016/11/3.
 */
public class UriModule extends ModuleBase {

    public static Result invoke(String uri_string) {
        return new UriModule(uri_string).run();
    }

    private final Module module;

    public UriModule(String uri_string) {
        URI uri = URI.create(uri_string);
        String proto = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        String user = uri.getUserInfo();
        PROTOCOL_TYPE type = PROTOCOL_TYPE.valueOf(PROTOCOL_TYPE.class, proto.toUpperCase());
        Map<String, String> params = queryStringToMap(uri.getQuery());

        if (PROTOCOL_TYPE.SSH == type || PROTOCOL_TYPE.TELNET == type) {
            String password = params.get("password");
            DtSourceInfo info = createDtInfo(type, host, port, user, password);
            ModuleSourceInfo msi = new ModuleSourceInfo(type,info);
            this.module = new Shell(msi, params.get("cmd"));
        } else {
            this.module = null;
        }
    }

    @Override
    public Result run() {
        return module.run();
    }

    private static DtSourceInfo createDtInfo(PROTOCOL_TYPE proto_type, String ip, int port, String user, String password) {
        DtSourceInfo info = new DtSourceInfo();
        info.setProtocol_type(PROTOCOL_TYPE.SSH);
        info.setSoc_name(proto_type + "_" + ip + "_" + user);
        info.setSoc_ip(ip);
        info.setSoc_port(port);
        info.setRemote_uname(user);
        String pass = "111111111111";
        info.setKey_remote_passwd(DESUtil.docryptAllowReverse(true, null, pass)
                .trim());
        info.setRemote_passwd(DESUtil.docryptAllowReverse(true, pass, password).trim());
        return info;
    }

    private static Map<String, String> queryStringToMap(String query) {
        Map<String, String> params = new HashMap<String, String>();
        String[] lines = query.split("&");
        for (String param : lines) {
            String[] ss = param.split("=");
            params.put(ss[0], ss[1]);
        }
        return params;
    }

	/** 
	 * Description:  
	 */
	@Override
	public void sessionClose() {
		
		
	}

	/** 
	 * Description:  
	 */
	@Override
	public void interactRun(String remote_relative_dir) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: 
	 * @param cmd 
	 */
	@Override
	public void sendInteractCmd(String cmd, boolean sensitive_flag) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public AsyncMsgBean getInteractMsg() {
		// TODO Auto-generated method stub
		return null;
	}


	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public Result runModule(String remote_relative_dir) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public IMPL_TYPE getImplType() {
		// TODO Auto-generated method stub
		return null;
	}
}
