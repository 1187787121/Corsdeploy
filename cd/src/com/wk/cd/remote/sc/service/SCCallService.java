package com.wk.cd.remote.sc.service;

import com.wk.cd.remote.sc.SCRSession;
import com.wk.cd.remote.sc.bean.SCBean;

/**
 * Created by ½ªÖ¾¸Õ on 2016/11/12.
 */
public interface SCCallService {

	SCRSession connect(SCBean bean,int step_count);

}
