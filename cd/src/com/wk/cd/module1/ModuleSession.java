package com.wk.cd.module1;

/**
 * Module�ĻỰ����
 * Created by ��־�� on 2016/11/25.
 */
public interface ModuleSession {
    String getKey();
    boolean isConnected();
    void setRealDisconnect(boolean real_close);
    void connect();
    void disconnect();
}
