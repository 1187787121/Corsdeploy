package com.wk.cd.module1.impl;

import com.wk.cd.module1.MultiResult;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.impl.ModuleBase;
import com.wk.cd.module1.impl.StepEnabled;

/**
 * 支持按步运行的Module抽象类
 *
 * Created by 姜志刚 on 2016/11/24.
 */
public abstract class MultiStepModule extends ModuleBase implements StepEnabled {

    protected final String[] cmds;
    
    //当前阶段的总命令数 用于agent转脚本文件用
    protected final int step_count;
    

    protected MultiStepModule(String[] cmds) {
        this.cmds = cmds;
        this.step_count = cmds.length;
    }

    @Override
    public boolean isLastStep(int step) {
        return 	step == cmds.length-1;
    }

    public Result run() {
        MultiResult result = new MultiResult();
        for (int i = 0; i < cmds.length; i++) {
            result.addResult(stepinto(i));
        }
        return result;
    }

    public int getStepCount() {
        return cmds.length;
    }

    public abstract Result stepinto(int step);

}
