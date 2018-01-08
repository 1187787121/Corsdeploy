/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.license;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.BeanTool;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.license.CheckService;
import com.wk.cd.license.CheckTask;
import com.wk.db.DBTask;
import com.wk.lang.Inject;
import com.wk.threadpool.Task;
import com.wk.util.JaDate;

/**
 * Class Description:
 * Created by lixl on 16-10-14.
 */
public class CheckTask extends DBTask{
    @Inject
    private LgLogMfDaoService mfsvc;
    @Inject
    private CommonService cmsvc;
    @Inject
    private CheckService cksvc;
    private static long pre_count = 0L;
    private static long pre_time = 0L;
    private static boolean is_destroy = false;
    private static boolean is_destory_seq = false;
    private static int exe_count = 1;
    /*
    static {
        Task t1 = BeanTool.getBeanByClzz(CheckTask.class);
        POOL.executeAt(t1, new Date(System.currentTimeMillis()), 1000, 3600);
    }
    */

    @Override
    protected synchronized void execute(){
        long last_time = CheckService.lasttime;
        if(exe_count++%20==0){
            System.out.println(""+last_time+"|"+pre_time);
            exe_count = 1;
        }
        if(!is_destory_seq){
            cksvc.destorySeq();
            is_destory_seq = true;
        }
        JaDate today = cmsvc.getCurrentDateTime().jaDateValue();
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(0);
        if(last_time > pre_time){
            pre_time = last_time;
            pre_count = mfsvc.countLog(today, today, 0, "", lst,0);
        }else{
            int lg_ct = mfsvc.countLog(today, today, 0, "", lst,0);
            if(lg_ct > pre_count+500){
                System.out.println(lg_ct+"|"+pre_count);
                if(!is_destroy){
                    System.out.println("task desctory really");
                    cksvc.destroy();
                    is_destroy = true;
                }
                pre_count = lg_ct;
                pre_time = last_time;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Task t1 = BeanTool.getBeanByClzz(CheckTask.class);
        Thread.sleep(3600*1000);
    }
}
