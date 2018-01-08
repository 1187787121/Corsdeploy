/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.remote.tcp.service;

import java.io.IOException;
import java.util.Map;

import com.wk.actor.FutureActor;
import com.wk.cd.common.util.Assert;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.tcp.bean.TcpInputBean;
import com.wk.lang.FutureImpl;
import com.wk.lang.NetworkException;
import com.wk.lang.SystemException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.net.ChannelBufferMsg;
import com.wk.net.ClientCommManager;
import com.wk.net.CommManagers;
import com.wk.net.NullResponseActor;
import com.wk.net.Response;
import com.wk.nio.ChannelBuffer;
import com.wk.outsystem.MsgConverter;
import com.wk.outsystem.MsgConverters;
import com.wk.sdo.Field;
import com.wk.sdo.ServiceData;
import com.wk.service.ServiceResult;
import com.wk.util.ConverterUtil;
import com.wk.util.GBKProperties;
import com.wk.util.Message;

/**
 * Class Description:
 * Created by lixl on 16-7-22.
 */
public class TcpCallRemoteService {
    private final ClientCommManager<ChannelBufferMsg, NullResponseActor<ChannelBufferMsg>> comm ;
    private TcpInputBean inputBean;
    private MsgConverter req_cvt = null;
    private MsgConverter resp_cvt = null;

    private static final Log logger = LogFactory.getLog();

    private final GBKProperties props = new GBKProperties();
    private final static String MSG_CLAZZ = "com.wk.net.ChannelBufferMsg";
    private final static String TYPE="client";
    private final static String CONN_MODE="SYNC_SHORT_CONNECTION";
    private final static String PACK_TYPE = "com.wk.net.impl.LengthProtocol(adjust=-4, includelen=true, max=40960000)";
    //private final static String PACK_TYPE = "vf";
    private final static String TIMEOUT = "180000";
    private final static String JSON = "json";

    @SuppressWarnings("unchecked")
    public TcpCallRemoteService (TcpInputBean inputBean){
        //logger = LogFactory.getLog("CallRemoteService");
        this.inputBean = inputBean;
        initProperties(inputBean);
        initMsgConveter(inputBean);
        comm = CommManagers.getClientCommManager(inputBean.getRemote_name(), ChannelBufferMsg.class, NullResponseActor.class, props);
    }

    public <O, I> O callService(I input, O output){
        Assert.assertNotEmpty(input,"input");
        Assert.assertNotEmpty(output, "output");
        
        ServiceResult<ServiceData> result = service(input);
        return convertResult(result, output, output.getClass());
    }

    /**
     * 配置相关参数
     */
    private void initProperties(TcpInputBean inputBean){
        Assert.assertNotEmpty(inputBean.getRemote_name(), "remote_name");
//        Assert.assertNotEmpty(inputBean.getService_name(), "service_name");
        Assert.assertNotEmpty(inputBean.getRemote_address(), "remote_address");
        String type = Assert.isEmpty(inputBean.getComm_type())?TYPE:inputBean.getComm_type();
        String mode = Assert.isEmpty(inputBean.getConnection_mode())?CONN_MODE:inputBean.getConnection_mode();
        String pktype = Assert.isEmpty(inputBean.getPacket_type())?PACK_TYPE:inputBean.getPacket_type();
        String rmt_addr = inputBean.getRemote_address();
        String timeout = Assert.isEmpty(inputBean.getTimeout())?TIMEOUT:inputBean.getTimeout();
        String req_cvt_type = Assert.isEmpty(inputBean.getRequest_type())?JSON:inputBean.getRequest_type();
        String resp_cvt_type = Assert.isEmpty(inputBean.getResponse_type())?JSON:inputBean.getResponse_type();
        props.put("type", type);
        props.put("connection_mode", mode);
        props.put("packet_type", pktype);
        props.put("remote_address", rmt_addr);
        props.put("timeout", timeout);
        props.put("timeout_check_interval", "500");
        props.put("msg_class", MSG_CLAZZ);
        props.put("request.type", req_cvt_type);
        props.put("response.type", resp_cvt_type);
    }

    /**
     * 注册消息转换器
     * Description: 
     * @param inputBean
     */
    private void initMsgConveter (TcpInputBean inputBean){
        String req_cvt_type = Assert.isEmpty(inputBean.getRequest_type())?JSON:inputBean.getRequest_type();
        if (req_cvt_type == null) {
            throw new SystemException("SYS_OUT_SYSTEM_NO_REQUEST_TYPE").
                    addScene("Name", inputBean.getRemote_name());
        }

        try {
            req_cvt = MsgConverters.getMsgConverter(req_cvt_type, props);
        } catch (SystemException e) {
            throw e.addScene("OutSystemName", inputBean.getRemote_name());
        }

        String resp_cvt_type = Assert.isEmpty(inputBean.getResponse_type())?JSON:inputBean.getResponse_type();
        if (resp_cvt_type == null) {
            throw new SystemException("SYS_OUT_SYSTEM_NO_RESPONSE_TYPE").
                    addScene("Name", inputBean.getRemote_name());
        }

        try {
            resp_cvt = MsgConverters.getMsgConverter(resp_cvt_type, props);
        } catch (SystemException e) {
            throw e.addScene("OutSystemName", inputBean.getRemote_name());
        }
    }

    /**
     * 将ServiceData转化成具体类对象（如ShellBean）
     * Description: 
     * @param result
     * @param output
     * @param resultRawType
     * @return
     */
    @SuppressWarnings("unchecked")
    private  <O> O convertResult(ServiceResult<ServiceData> result, O output, Class<?> resultRawType) {
//    	logger.info("result data=[{}]", result.getResult());
        if (result.isSuccess() || result.isFail()) {
            return (O)ConverterUtil.serviceData2Bean(result.getResult(), resultRawType);
        }else if(result.isExcept()){
            logger.warn("result exception : ", result.getException());
            throw result.getException();
        }
        return null;
    }


    private <I> ServiceResult<ServiceData> service(I input) {
        ServiceResult<ServiceData> result = new ServiceResult<ServiceData>();
        byte[] buff = req_cvt.toBytes(inputBean.getRemote_name(), input);

        try {
//            logger.debug("send buffer = [\n{}]", StringUtil.byteArr2HexStr(buff));
            byte[] resp_buff = sendAndRecv(inputBean.getRemote_name(), buff);
          
            if (resp_buff == null || resp_buff.length == 0) {
                //result.except(new SystemException("SYS_REMOTE_SYSTEM_NO_RESPONSE_DATA"));
            } else {
//                logger.debug("received buffer = [\n{}]", StringUtil.byteArr2HexStr(resp_buff));
                ServiceData sd = resp_cvt.fromBytes(resp_buff, ServiceData.class);
//                logger.debug("received data = \n{}", sd);
                setResultFromServiceData(result, sd);
            }
            
//            logger.debug("result" + result);
            return result;
        } catch (NetworkException e) {
            result.except(e);
            return result;
        }
    }

    private byte[] sendAndRecv(String remote_name, byte[] buff) {
        ChannelBuffer buffer = ChannelBuffer.wrap(buff);
        buffer.writerIndex(buff.length);

        FutureImpl<Response<ChannelBufferMsg>> future = new FutureImpl<Response<ChannelBufferMsg>>();
        logger.debug("timeout = {}", comm.getReceiveTimeout());
        comm.send(new ChannelBufferMsg(buffer), new FutureActor<Response<ChannelBufferMsg>>(future), Integer.parseInt(inputBean.getTimeout()));
//        Response<ChannelBufferMsg> resp = future.get();
//        if (resp.isException()) {
//        	throw new SystemException("SYS_TRANSPORT_TCP_COMM_RESPONSE_ERROR",resp.getException());
//        } else if (resp.isTimeout()) {
//        	throw new SystemException("SYS_TRANSPORT_TCP_COMM_RESPONSE_TIMEOUT");
//        }
        
        ChannelBufferMsg rsp_msg = future.get().getResponseMsg();
        if (rsp_msg == null) {
            return null;
//            throw new SystemException("SYS_TRANSPORT_TCP_COMM_RESPONSE_IS_NULL").addScene("ServiceName", remote_name);
        }else {
            buffer = rsp_msg.toChannelBuffer();
            byte[] bytes = new byte[buffer.readableBytes()];
            buffer.getBytes(bytes);

            return bytes;
        }
    }

    private void setResultFromServiceData(ServiceResult<ServiceData> result, ServiceData data) {
        ServiceData header = data.getServiceData("sys_header");
        Field appdata = data.getField("appdata");
        if (appdata == null && header == null) {
            result.setResult(data);
            return;
        }

        if (appdata != null && appdata.isServiceData()) {
            result.setResult(appdata.getServiceData());
        } else {
            ServiceData sd = new ServiceData();
            if (appdata != null) {
                sd.putField("value", appdata);
            } else {
                //TODO: MayBe should throw exception as no appdata
            }
            result.setResult(sd);
        }

        String status = header.getString("status");
        if (status.equals("ok")) {
            return;
        }

        String scode = header.getString("scode");
        String msg = header.getString("message");
        ServiceData sd = result.getResult();
        result.setResult(null);

        Message message = Message.getMessage(scode, msg);
        for (Map.Entry<String, Field> entry : sd.entrySet()) {
            message.addScene(entry.getKey(), entry.getValue().getValue());
        }

        if (status.equals("fail")) {
            result.fail(message);
        } else if (status.equals("except")) {
            result.except(new SystemException(message));
        } else if (status.equals("confirm")) {
            result.confirm(header.getString("confirmid"), message);
        } else if (status.equals("auth")) {
            result.auth(message);
        } else {
            throw new SystemException("SYS_UNKNOWN_STATUS").
                    addScene("Status", status);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException{
        TcpInputBean input = new TcpInputBean();
        input.setService_name("app/xxx.do");
        input.setRemote_address("127.0.0.1:9001");
        input.setRemote_name("core001");
        input.setConnection_mode("SYNC_LONG_CONNECTION");
        //input.setPacket_type("length");
        TcpCallRemoteService svc = new TcpCallRemoteService(input);
        ShellBean sh = new ShellBean();
        sh.setShell("ls");
        String rs = new String();
        rs = svc.callService(sh, rs);
        System.in.read();
        System.exit(0);
    }

}
