/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.remote.agent.service;

import java.io.IOException;

import com.wk.Controller;
import com.wk.SystemConfig;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.net.ChannelBufferMsg;
import com.wk.net.CommManagers;
import com.wk.util.GBKProperties;

/**
 * Class Description:
 * Created by lixl on 16-8-9.
 * agent服务器启动类
 */
public final class AgentServerStartup {
    private static final Log logger = LogFactory.getLog();

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length < 1) {
            System.out.println("              Agent服务器              ");
            System.out.println("--------------------------------------");
            System.out.println("Usage: <listen port>");
            System.out.println();
            System.out.println("    通讯名称为Agentserver，");
            System.out.println("    采用同步长连接，4位长度包括长度域");
            System.out.println();
            return;
        }
        /*
        ServiceData header = new ServiceData();
        ServiceData cxt = new ServiceData();
        cxt.putString("status", "fail");
        cxt.putString("scode", "AA_AAA");
        cxt.putString("message", "");
        cxt.putString("shell", "ls");
        cxt.putString("result", "ssss");
        header.putServiceData("sys_header", cxt);

        JsonMsgConverter jv = new JsonMsgConverter(JSONCaseType.LOWERCASE);
        byte[] bs = jv.toBytes("sss", header);
        ServiceData dt1 = jv.fromBytes(bs, ServiceData.class);
        System.out.println(dt1);
        */

        new AgentServerStartup().init(args[0]);

        System.in.read();
        System.out.println(">>> EchoServer stopped.");
        System.exit(0);

    }
 
    /*
     * 配置一些参数，并启动agent
     */
    private void init(String port){
    	
    	logger.debug("Agent Server Started");
        //启动vframe
    	Controller.getInstance();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
        }
        GBKProperties props = new GBKProperties();
        props.put("comm.agentserver.request.type","json");
        props.put("comm.agentserver.response.type","json");
        props.put("comm.agentserver.type","server");
        props.put("comm.agentserver.connection_mode","SYNC_SHORT_CONNECTION");
        props.put("comm.agentserver.packet_type","com.wk.net.impl.LengthProtocol(max_length=40960000, adjust=-4, includelen=true, max=40960000)");
        //props.put("comm.agentserver.packet_type","vf");
        props.put("comm.agentserver.bind_address",port);
        props.put("comm.agentserver.msg_class", "com.wk.net.ChannelBufferMsg");
        props.put("comm.agentserver.request_actor", AgentServer.class.getName());
        SystemConfig.getInstance().putAll(props);
        CommManagers.getServerCommManager("agentserver", ChannelBufferMsg.class, AgentServer.class);
        
//        new ShellAgent().agentStart(props);
        
    }
}
