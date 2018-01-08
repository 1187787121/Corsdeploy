/**
 * Title: DirTransferMonitor.java
 * File Description: 目录传输监控类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 12/16/2014
 */

package com.wk.cd.remote.fp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.wk.cd.remote.fp.bean.MBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:目录传输监控类
 * @author lixl
 */
public class DirTransferMonitor extends TimerTask {
    private String work_seq;
    private List<String> all_file;
    private List<String> trans_file = new ArrayList<String>();
    private int all_num;
    private int trans_num = 0;
    private Timer timer; // 定时器对象
    private long progressInterval = 5 * 1000; // 默认间隔时间为5秒
    private boolean isEnd = false; // 记录传输是否结束
    private boolean isScheduled = false; // 记录是否已启动timer记时器
    private static final Log logger = LogFactory.getLog();

    DirTransferMonitor(String work_seq, int all_num, List<String> all_file){
        this.work_seq = work_seq;
        this.all_num = all_num;
        this.all_file = all_file;
    }


    @Override
    public void run() {
        if (!isEnd()) { // 判断传输是否已结束
            logger.info("Dir monitor runing");
            if (this.all_num != this.trans_num) { // 判断当前已传输文件个数
                sendProgressMessage(this.trans_num);
            } else {
                setEnd(true);
            }
        } else {
            logger.info("Dir transfer over, num=[{}]", this.trans_num);
            stop();
        }
    }

    void stop() {
        logger.info("Dir monitor end");
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
            isScheduled = false;
        }
    }

    void start() {
        logger.info("Dir monitor start");
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(this, 1000L, progressInterval);
        isScheduled = true;
    }

    private void sendProgressMessage(int tran_num){
        logger.info("Already transfered =[{}/{}]", tran_num, this.all_num);
    }

    public boolean count(String trans_file1){
        if(isEnd()) return false;
        this.trans_num++;
        this.trans_file.add(trans_file1);
        setCurCount();
        return true;
    }

    public void end(){
        this.setEnd(true);
    }

    public void init(){
        if(!isScheduled) {
            start();
        }
    }

    private synchronized void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    private synchronized boolean isEnd() {
        return isEnd;
    }

    private synchronized void setCurCount(){
        MBean bean = new MBean();
        bean.setAll_num(all_num);
        bean.setTrans_num(trans_num);
        bean.setAll_file(all_file);
        bean.setWork_seq(work_seq);
        FTPRCallService.updateMonitorFileNumBySeq(bean);
    }
}

