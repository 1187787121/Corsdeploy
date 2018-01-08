/**
 * Title: DeleteMessAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��4��9��
 */
package com.wk.cd.system.mg.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.mg.bean.DeleteMessInputBean;
import com.wk.cd.system.mg.bean.DeleteMessOutputBean;
import com.wk.cd.system.mg.service.MgMsgService;
import com.wk.lang.Inject;

/**
 * Class Description: ɾ����Ϣ��Ϣ
 * @author HT
 */
public class DeleteMessAction extends ActionBasic<DeleteMessInputBean, DeleteMessOutputBean>{
	@Inject
	private MgMsgService mgMsgService;
	
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteMessOutputBean doAction(DeleteMessInputBean input) {
		return null;
	}
	
	/**
	 * 
	 * Description: ɾ��������Ϣ
	 * @param input
	 * @return
	 */
	public DeleteMessOutputBean deleteRcMess(DeleteMessInputBean input) {
		DeleteMessOutputBean output=new DeleteMessOutputBean();
		String work_seq=input.getWorkseq();
		String user_id=input.getOrg_user_id();
		Assert.assertNotEmpty(work_seq, DeleteMessInputBean.WORKSEQCN);
		Assert.assertNotEmpty(user_id, DeleteMessInputBean.ORG_USER_IDCN);
		
		mgMsgService.deleteRcMess(work_seq,user_id);
		
		return output;
	}
	
	/**
	 * 
	 * Description: ɾ��������Ϣ
	 * @param input
	 * @return
	 */
	public DeleteMessOutputBean deleteSendMess(DeleteMessInputBean input) {
		DeleteMessOutputBean output=new DeleteMessOutputBean();
		String work_seq=input.getWorkseq();
		Assert.assertNotEmpty(work_seq, DeleteMessInputBean.WORKSEQCN);
		
		mgMsgService.deleteSendMess(work_seq);
		
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteMessInputBean input) {
		return null;
	}

}
