/**
 * Title: AddTermAction.java
 * File Description: �����ն�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TERM_TYPE;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.tm.bean.AddTermInputBean;
import com.wk.cd.system.tm.bean.AddTermOutputBean;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �����ն�
 * @author tlw
 */
public class AddTermAction
		extends ActionBasic<AddTermInputBean, AddTermOutputBean> {

	@Inject
	private TmTermDaoService tmsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * �����ն�
	 * @param input ������Ϣ
	 * @return
	 */
	@Override
	protected AddTermOutputBean doAction(AddTermInputBean input) {
		AddTermOutputBean output = new AddTermOutputBean();
		logger.info("***********�����ն�**************");
		String term_no = input.getTerm_no();
		TmTermInfo tm_info = new TmTermInfo();
		tm_info.setTerm_no(term_no);
		TERM_TYPE term_type = input.getTerm_type();
		String term_bk_desc = input.getTerm_bk_desc();
		Assert.assertNotEmpty(term_no, AddTermInputBean.TERM_NOCN);
		Assert.assertNotEmpty(term_type, AddTermInputBean.TERM_TYPECN);
		//����ն˺��Ƿ����
		if(!Assert.isEmpty(tmsrv.getInfoByKey(tm_info))){
			throw new RecordAlreadyExistException().addScene("TABLE",
					TmTermInfo.TABLECN).addScene("FIELD", tm_info.getTerm_no());
		}
		TmTermInfo info = new TmTermInfo();
		info.setTerm_no(term_no);
		info.setTerm_type(term_type);
		info.setTerm_bk_desc(term_bk_desc);
		info.setCrt_user_id(input.getOrg_user_id());
		info.setCrt_dept_id(input.getOrg_dept_id());
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		tmsrv.insertInfo(info);
		return output;
	}

	/**
	 * ������־
	 * @param input ������Ϣ
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(AddTermInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("�ն˺ţ�" + input.getTerm_no());
		lst_val.add("�ն����ͣ�" + input.getTerm_type());
		lst_val.add("�ն�˵����" + input.getTerm_bk_desc());
		return lgsrv.getLogTxt("�����ն�", lst_val);
	}

}
