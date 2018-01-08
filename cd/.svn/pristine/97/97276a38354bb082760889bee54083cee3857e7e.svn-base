package com.wk.cd.async.actor;

import com.wk.lang.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by ½ªÖ¾¸Õ on 2016/5/26.
 */
public class ServiceRequest<T> {

    private final Map<String, FutureImpl<T>> future_map = new HashMap<String, FutureImpl<T>>();
    private final T req_data;
    private T rsp_data;
    private Throwable exception;

    public ServiceRequest(T t) {
        this.req_data = t;
    }

    public T getRequest() {
        return req_data;
    }

    public T getResponse() {
        return rsp_data;
    }

    public Throwable getException() {
        return exception;
    }

    public void setResponse(T t) {
        this.rsp_data = t;
    }

    Future<T> newResponseFuture(String key) {
        final FutureImpl<T> future = new FutureImpl<T>();
        future_map.put(key, future);
        return future;
    }

    public boolean hasException() {
        return exception != null;
    }

    public void setException(Throwable t) {
        this.exception = t;
    }

    void futureSetResponse(T t, String key) {
        FutureImpl<T> f = future_map.get(key);
        if (f != null) {
            f.set(t);
        }
    }

    void futureSetException(Throwable e, String key) {
        FutureImpl<T> f = future_map.get(key);
        if (f != null) {
            f.setException(e);
        }
    }

}
