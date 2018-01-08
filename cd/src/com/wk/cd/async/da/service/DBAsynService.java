/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.async.da.service;

import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.db.DBDispatchDoBase;
import com.wk.db.DBTransaction;
import com.wk.threadpool.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Description:
 * Created by lixl on 16-8-31.
 */
public class DBAsynService extends AsynBaseService{
    private DaoServiceInf dsvc;
    private boolean is_disp ;

    DBAsynService(){
        super();
    }

    DBAsynService(DaoServiceInf dsvc, boolean is_disp){
        super();
        this.dsvc= dsvc;
        this.is_disp = is_disp;

    }

    @Override
    void dispatchService() throws InterruptedException{
        if(is_disp){
            doDispath();
        }else{
            doRun();
        }
    }

    private void doRun() throws InterruptedException{
        while (true){
            Object obj = getFromCache();
            dsvc.daoRun(obj);
        }
    }

    private void doDispath() throws InterruptedException{
        while(true){
            mutilDispath();
            sleep();
        }
    }

    private void mutilDispath() throws InterruptedException{
        List<Object> lst2 = new ArrayList<Object>();
        int count=0;
        while(count++ < CAP){
            Object obj = getFromCache();
            if(obj == END) {
                System.out.println("END");
                break;
            };
            lst2.add(obj);
        }
        System.out.println("size="+lst2.size()+"--ThreadName="+Thread.currentThread().getName());
        allRun(lst2);
    }

    private void allRun(List<Object> lst){
        ThreadDispatch<Object> _dbh = dsvc.dbh;
        _dbh.eachRow(lst.iterator(),"DBAsynService", 10,
                new DispatchKeyHandler<Object>() {
                    public Object getKey(Object obj, int currno,
                                         int threadNums) {
                        return currno%threadNums;
                    }
                },new DispatchIgnoreHandler<Object>() {
                    public boolean isIgnore(Object obj, int currNo,
                                            int threadNums) {
                        return false;
                    }
                }, new DispatchDoHandlerFactory<Object>() {
                    public DispatchDoHandler<Object> createDoHandler() {
                        return new DBDispatchDoBase<Object>() {
                            final DBTransaction dbt = DBTransaction.get();
                            int count = 0;
                            public void execute(Object obj) {
                                if(obj == null){
                                    dbt.commitAndResume();
                                    return;
                                }
                                //TODO insert
                                dsvc.daoRun(obj);
                                if(count++%100==0) dbt.commitAndResume();
                                //System.out.println(Thread.currentThread().getName());
                            }
                        };
                    }}
                );

    }

    private void sleep(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
        }
    }
}
