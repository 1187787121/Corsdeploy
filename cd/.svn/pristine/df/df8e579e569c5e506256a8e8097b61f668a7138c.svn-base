/**
 * Title: IViewBasic.java
 * File Description: 展示端交互查询基类
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 10/14/2015
 */

package com.wk.cd.service;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description:展示端交互查询基类
 * @author lixl
 */
public abstract class IViewActionBasic<IN extends ActionRootInputBean, OUT extends ActionRootOutputBean > 
	extends ActionBasic<IN, OUT> {

	@Override
	protected void chkInput(IN input){
	}

	@Override
	protected void genWorkSeq(IN input){
	}

	@Override
	protected void addLog(IN input) {
	}

	@Override
	protected void dataSourceLoad(IN input){
	}

	@Override
	protected void chkTerm(IN input){
	}

	@Override
	protected void chkState(IN input){
	}

	@Override
	protected void chkDeptPriv(IN input){
	}

	@Override
	protected void chkUserPriv(IN input){
	}

	@Override
	protected void setUpOutput(OUT output, IN input){
	}

	@Override
	protected OUT doAction(IN input){
		return null;
	}

	@Override
	protected String getLogTxt(IN input){
		return null;
	}

	@Override
	protected boolean isChk(IN input){
	    return false;
	}

	@Override
	protected boolean isAuth(IN input){
	    return false;
	}
	
	@Override
	protected boolean isLocAuth(IN input){
		return false;
	}
}

