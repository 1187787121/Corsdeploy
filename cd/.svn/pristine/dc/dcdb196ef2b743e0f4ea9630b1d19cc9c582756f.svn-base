package com.wk.cd.async.actor;

/**
 * Created by ½ªÖ¾¸Õ on 2016/7/8.
 */
public abstract class BackendAsyncServiceActor<T> extends  AsyncServiceActor<T> {

    public BackendAsyncServiceActor(String name) {
        super(name);
    }

    @Override
    protected final void doRequest(ServiceRequest<T> t) {
        T resp = requestResponse(t.getRequest());
        t.setResponse(resp);
    }

    protected abstract T requestResponse(T request);

    protected final void doRequest(T s) {
    }

    protected final void doResponse(T s) {
    }

}
