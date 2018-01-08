package com.wk.cd.async.actor;

import com.wk.lang.Future;

/**
 * Created by ½ªÖ¾¸Õ on 2016/7/11.
 */
public final class Pipeline<T> {

    private final AsyncServiceActor<T>[] mids;
    private final BackendAsyncServiceActor<T> end;
    private final AsyncServiceActor<T> root;

    public Pipeline(AsyncServiceActor<T>[] mids, BackendAsyncServiceActor<T> end) {
        this.mids = mids;
        this.end = end;
        if (mids == null || mids.length == 0) {
            this.root= end;
        } else {
            this.root = mids[0];
            for (int i=0; i<mids.length-1; i++) {
                mids[i].setNextActor(mids[i+1]);
                mids[i+1].setPrevActor(mids[i]);
            }
            mids[mids.length-1].setNextActor(end);
            end.setPrevActor(mids[mids.length-1]);
        }
    }

    public Future<T> execute(T request) {
        return root.sendRequest(request);
    }
}
