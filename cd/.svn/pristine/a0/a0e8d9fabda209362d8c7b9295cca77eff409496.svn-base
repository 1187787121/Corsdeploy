/**
 * Title: LogPublicService.java
 * File Description: 日志服务类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/8/2014
 */

package com.wk.cd.system.lg.service;


import java.util.List;

import com.wk.cd.system.lg.dao.*;
import com.wk.cd.system.lg.info.*;
import com.wk.lang.Inject;

/**
 * Class Description: 日志服务类
 * @author lixl
 */
public class ActionLogPublicService {
    @Inject private LgLogMfDaoService logDaos;
    
    /**
     * 获取日志详细信息
     * @param head_val
     * @param lst_val
     * @return 日志信息字符串
     */
    public String getLogTxt(String head_val, List<String> lst_val){
    	StringBuffer sb = new StringBuffer();
    	sb.append(head_val).append(":");
    	if(lst_val != null){
            for(String val : lst_val){
                sb.append("[");
                sb.append(val);
                sb.append("]");
            }
        }
    	return sb.toString();
    }

    /**
     * 新增一条日志信息
     * @author lixl (2014-11-14)
     * @param info 
     */
    public void addActionLog(LgLogMfInfo info){
        logDaos.insertInfoForNewTransaction(info);
    }

    /**
     * 更新日志具体内容
     * @author lixl (2014-11-14)
     * @param work_seq 
     * @param log_txt 
     */
    public void updateLogTxt(LgLogMfInfo info){
        logDaos.updateLogTxtByKey(info);
    }

    /**
     * 更新日志状态
     * @author lixl (2014-11-14)
     * @param info 
     */
    public void updateLogState(LgLogMfInfo info){
        logDaos.updateLogStateByKey(info);
    }
}

