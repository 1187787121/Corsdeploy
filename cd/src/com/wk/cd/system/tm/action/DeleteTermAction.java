/**
 * Title: DeleteTermAction.java
 * File Description: …æ≥˝÷’∂À
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
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.tm.bean.DeleteTermInputBean;
import com.wk.cd.system.tm.bean.DeleteTermOutputBean;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: …æ≥˝÷’∂À
 * @author tlw
 */
public class DeleteTermAction
		extends ActionBasic<DeleteTermInputBean, DeleteTermOutputBean> {

	@Inject
	private TmTermDaoService tmsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * …æ≥˝÷’∂À
	 * @param input  ‰»Î–≈œ¢
	 * @return
	 */
	@Override
	protected DeleteTermOutputBean doAction(DeleteTermInputBean input) {
		DeleteTermOutputBean output = new DeleteTermOutputBean();
		String term_no = input.getTerm_no();
		Assert.assertNotEmpty(term_no, DeleteTermInputBean.TERM_NOCN);
		logger.info("…æ≥˝÷’∂À£∫[{}]" + term_no);
		TmTermInfo info = new TmTermInfo();
		info.setTerm_no(term_no);
		tmsrv.deleteInfo(info);
		return output;
	}

	/**
	 * …˙≥…»’÷æ–≈œ¢
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteTermInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("÷’∂À∫≈£∫" + input.getTerm_no());
		return lgsrv.getLogTxt("…æ≥˝÷’∂À", lst_val);
	}

}
