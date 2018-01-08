/**
 * Title: FileTransferMonitor.java
 * File Description: 文件传输监控类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 12/15/2014
 */

package com.wk.cd.remote.fp.service;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.jcraft.jsch.SftpProgressMonitor;
import com.wk.cd.remote.fp.bean.MBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:文件传输监控类
 * @author lixl
 */
public class FileTransferMonitor extends TimerTask implements SftpProgressMonitor{
    private static final Log logger = LogFactory.getLog();
    private long progressInterval = 5 * 1000; // 默认间隔时间为5秒
    private boolean isEnd = false; // 记录传输是否结束
    private long transfered; // 记录已传输的数据总大小
    private long lastsize; //上次已传输大小
    private long cursize;//当前已上传大小
    private long fileSize; // 记录文件总大小
    private String fname;//文件名称
    private String work_seq;//工作流水号
    private Timer timer; // 定时器对象
    private boolean isScheduled = false; // 记录是否已启动timer记时器
    
    FileTransferMonitor(long fileSize, String fname, String wor_seq) {
        this.fileSize = fileSize;
        this.fname = fname;
        this.work_seq = wor_seq;
    }
    
    @Override
    public void run() {
        setCurSize();
        if (!isEnd()) { // 判断传输是否已结束
            logger.info("Transfer monitor runing");
            long transfered1 = getTransfered();
            if (transfered1 != fileSize) { // 判断当前已传输数据大小是否等于文件总大小
                sendProgressMessage(transfered1);
            } else {
                setEnd(true); // 如果当前已传输数据大小等于文件总大小，说明已完成，设置end
            }
        } else {
            logger.info("Monitor Transfer file=[{}] over, size=[{}]", fname, fileSize);
            stop(); // 如果传输结束，停止timer记时器
        }
    }
    
    void stop() {
        logger.info("Try to end monitor");
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
            isScheduled = false;
        }
    }
    
    void start() {
        logger.info("Try to start monitor");
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(this, 1000L, progressInterval);
        isScheduled = true;
    }
    
    /**
     * 打印progress信息
     * @param transfered1
     */
    private void sendProgressMessage(long transfered1) {
        if (fileSize != 0) {
            logger.info("Monitor transfered size=[{}]", transfered1);
            double d = ((double)transfered1 * 100)/fileSize;
            DecimalFormat df = new DecimalFormat( "#.##"); 
            long s = getTransSpeed();
            double mb = 0.00;
            if(s > 1000) {
                mb = (double)s/1024;
                logger.info("file [{}] Already transfered =[{}%] transfer speed=[{}MB/s]", 
                             fname, df.format(d), df.format(mb));
            }else if(s>1000000){
                mb = (double)s/(1024*1024);
                logger.info("file [{}] Already transfered =[{}%] transfer speed=[{}GB/s]", 
                             fname, df.format(d), df.format(mb));
            }else{
                mb = s;
                logger.info("file [{}] Already transfered =[{}%] transfer speed=[{}KB/s]", 
                             fname, df.format(d), df.format(mb));
            }
        } else {
            logger.warn("Monitor transfer file size is zero");
        }
    }

    /**
     * 实现了SftpProgressMonitor接口的count方法
     */
    public boolean count(long count) {
        if (isEnd()) return false;
        if (!isScheduled) {
            start();
        }
        add(count);
        return true;
    }

    /**
     * 实现了SftpProgressMonitor接口的end方法
     */
    public void end() {
        logger.debug("Monitor detected transfer end");
        setEnd(true);
    }
    
    private synchronized void add(long count) {
        transfered = transfered + count;
    }
    
    public synchronized long getTransfered() {
        return this.transfered;
    }

    public synchronized void setTransfered(long transfered) {
        this.transfered = transfered;
    }

    public String getFname(){
        return this.fname;
    }

    public long getFileSize(){
        return this.fileSize;
    }
    
    private synchronized void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    
    private synchronized boolean isEnd() {
        return isEnd;
    }

    public void init(int op, String src, String dest, long max) {
        // Not used for putting InputStream
    }

    private synchronized void setCurSize(){
        this.lastsize = this.cursize;
        this.cursize = this.transfered;
        MBean bean = new MBean();
        bean.setFile_name(fname);
        bean.setTrans_size(this.cursize);
        bean.setFile_size(this.fileSize);
        bean.setTrans_speed(getTransSpeed());
        bean.setWork_seq(work_seq);
        FTPRCallService.updateMonitroFileSizeByFname(bean);
    }

    private synchronized long getTransSpeed(){
        return getIntevalSize()/progressInterval;
    }

    private synchronized long getIntevalSize(){
        return this.cursize- this.lastsize;
    }
}