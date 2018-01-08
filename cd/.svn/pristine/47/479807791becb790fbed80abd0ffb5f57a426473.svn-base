package com.wk.cd.async.actor;

import sqlj.javac.ThisClassNode;

import com.wk.actor.Actor;
import com.wk.lang.Future;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Created by ½ªÖ¾¸Õ on 2016/5/25.
 */
public abstract class AsyncServiceActor<T> extends Actor<ServiceRequest<T>> {

    protected final Log logger;
    protected final String name;
    protected final Actor<ServiceRequest<T>> response_actor;
    private final String key;
    protected AsyncServiceActor<T> prev_actor;
    protected AsyncServiceActor<T> next_actor;

    public AsyncServiceActor(String name) {
        this.name = name;
        this.logger = LogFactory.getLog("async." + name);
        this.response_actor =  new Actor<ServiceRequest<T>>(name) {
            @Override
            protected void act(ServiceRequest<T> t) {
            	try {
            		T resp = t.getResponse();
            		if (t.hasException()) {
            			logger.error("doResponse pre_exception name=[{}]",this.name);
            			doException(t.getException());
            			logger.debug("futureSetException key=[{}]",key);
            			t.futureSetException(t.getException(), key);
            		} else {
            			logger.debug("doResponse name=[{}]",this.name);
            			doResponse(t);
            			logger.debug("futureSetResponse key=[{}]",key);
            			t.futureSetResponse(resp, key);
            		}
            	} catch(Throwable e) {
            		t.setException(e);
            		logger.error("doResponse name=[{}] ---- execption=[{}] ",this.name,e);
            		logger.debug("futureSetException key=[{}]",key);
            		t.futureSetException(t.getException(), key);
            	}
            	if (prev_actor != null ) {
        			prev_actor.sendResponse(t);
        		}
            }
        };
        this.key = getKey();
    }

    private String getKey() {
        return this.hashCode() + "";
    }

    void setPrevActor(AsyncServiceActor<T> actor) {
        this.prev_actor = actor;
    }

    void setNextActor(AsyncServiceActor<T> actor) {
        this.next_actor = actor;
    }

    public final Future<T> sendRequest(T t) {
        ServiceRequest<T> ts = new ServiceRequest<T>(t);
        logger.debug("newResponseFuture key=[{}]",key);
        Future<T> f = ts.newResponseFuture(key);
        send(ts);
        return f;
    }

    private void sendResponse(ServiceRequest<T> t) {
        response_actor.send(t);
    }

    @Override
    protected void act(ServiceRequest<T> t) {
        try {
        	logger.debug("doRequest name=[{}]",this.name);
            doRequest(t);
            if (next_actor != null) {
                next_actor.send(t);
            } else {
                sendResponse(t);
            }
        } catch(Throwable e) {
            t.setException(e);
            logger.debug("futureSetException key=[{}]",key);
            t.futureSetException(e, key);
            logger.error("doRequest name=[{}] ---- execption=[{}] ",this.name,e);
            sendResponse(t);
        }
    }

    protected void doRequest(ServiceRequest<T> t) {
        doRequest(t.getRequest());
    }

    protected final void doResponse(ServiceRequest<T> t) {
        T resp = t.getResponse();
        doResponse(resp);
    }

    protected abstract void doRequest(T t);

    protected abstract void doResponse(T t);

    protected void doException(Throwable e) {
    };

}
