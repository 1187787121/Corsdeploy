package com.wk.cd.module1.impl;

import com.wk.cd.module1.Module;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.impl.StepEnabled;

/**
 * Module¼Ì³Ð»ùÀà
 *
 * Created by ½ªÖ¾¸Õ on 2016/11/3.
 */
public abstract class ModuleBase implements Module {

    protected InstancePhase info;

    protected int timeout;

    protected ProcessContext ctx;

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPhaseInfo(InstancePhase info) {
        this.info = info;
    }

    @Override
    public InstancePhase getPhaseInfo() {
        return info;
    }

    @Override
    public boolean isStepEnabled() {
        return this instanceof StepEnabled;
    }

    @Override
    public boolean isLastStep(int step) {
        throw new RuntimeException("stepinto disabled");
    }

    @Override
    public Result stepinto(int step) {
        throw new RuntimeException("stepinto disabled");
    }

    @Override
    public ProcessContext getCtx() {
        return ctx;
    }

    @Override
    public void setCtx(ProcessContext ctx) {
        this.ctx = ctx;
    }
}
