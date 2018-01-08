package com.wk.cd.module1;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.Result;

/**
 * 运行结果集
 * 用在多步运行的返回结果
 * Created by 姜志刚 on 2016/11/2.
 */
public class MultiResult extends Result {

    public MultiResult() {
    }

    protected final List<Result> result_list = new ArrayList<Result>();

    public void addResult(Result result) {
        result_list.add(result);
        status = result.status;
        msg = result.msg;
    }

    public List<Result> getResults() {
        return result_list;
    }

    public String toString() {
        return result_list.toString();
    }

}
