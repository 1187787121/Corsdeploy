package com.wk.cd.module1.impl;

//import org.tmatesoft.sqljet.core.internal.lang.SqlParser.bool_return;

import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.impl.FTPSession;
import com.wk.cd.module1.impl.MultiStepModule;
import com.wk.cd.remote.bean.AsyncMsgBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * FTP模块实现
 */
public class FTP extends MultiStepModule {

    private static final Log logger = LogFactory.getLog();

    private final ModuleSourceInfo dt_info;

    private ProcessContext ctx;
    private FTPSession sess;

    public FTP(ModuleSourceInfo dt_info, String[] cmds) {
        super(cmds);
        this.dt_info = dt_info;
    }

    public FTPSession getSession() {
        return sess;
    }

    @Override
    public Result stepinto(int step) {
        long start_time = System.currentTimeMillis();
        DtSourceInfo dsi = dt_info.getDt_source_info();
        if (sess == null) {
            try {
                sess = new FTPSession(dt_info, step_count);
                sess.connect();
            } catch (Throwable t) {
                logger.error("连接数据源[{}]异常", dsi.getSoc_name(), t);
                return new Result(t, start_time);
            }

            if (ctx != null) {
                ctx.bindSession(sess);
            }
        }

        String cmd = cmds[step];
        logger.debug("ftp send cmd [{}]", cmd);
        ShExecRsBean reply = sess.sendCmd(cmd);
        CMD_STATUS status = reply.getIs_succ() ? CMD_STATUS.SUCCEED : CMD_STATUS.ERROR;
        String msg = reply.getIs_succ() ? reply.getRs_msg() : reply
                .getErr_msg();
        logger.debug("命令执行结果信息：{}", msg);
        logger.debug("命令输出：{}\n{}", reply.getIs_succ(), msg);

        return new Result(status, msg, start_time);
    }

    /**
     * Description:
     */
    @Override
    public void sessionClose() {
        if (this.sess != null) {
            this.sess.disconnect();
        }


    }

    /**
     * Description:
     */
    @Override
    public void interactRun(String remote_relative_dir) {
        // TODO Auto-generated method stub

    }

    /**
     * Description:
     *
     * @param cmd
     */
    @Override
    public void sendInteractCmd(String cmd, boolean sensitive_flag) {
        // TODO Auto-generated method stub

    }

    /**
     * Description:
     *
     * @return
     */
    @Override
    public AsyncMsgBean getInteractMsg() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * Description:
     *
     * @return
     */
    @Override
    public Result runModule(String remote_relative_dir) {
        long start_time = System.currentTimeMillis();
        StringBuffer msg = new StringBuffer();
        StringBuffer error_msg = new StringBuffer();
        CMD_STATUS status = null;
        for (int i = 0; i < this.cmds.length; i++) {

            Result result = stepinto(i);
            status = result.getStatus();
            if (status != CMD_STATUS.SUCCEED) {
                logger.debug("ftp execute error out");
                msg.append(result.getMsg() + "\n");
                error_msg.append(result.getError_msg());
                break;
            }
            if (!Assert.isEmpty(result.getMsg())) {
                msg.append(result.getMsg() + "\n");
            }

        }
        Result result = new Result(status, msg.toString(), start_time);
        result.setError_msg(error_msg.toString());

        return result;
    }

    /**
     * Description:
     *
     * @return
     */
    @Override
    public IMPL_TYPE getImplType() {
        return IMPL_TYPE.FTP;
    }
}
 
