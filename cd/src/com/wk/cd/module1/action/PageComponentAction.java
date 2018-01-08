/**
 * Title: PageComponentAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年11月16日
 */
package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.CompBeanForList;
import com.wk.cd.module1.bean.PageComponentInputBean;
import com.wk.cd.module1.bean.PageComponentOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.dao.MoTagsDaoService;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description:
 * @author yangl
 */
public class PageComponentAction
		extends ActionBasic<PageComponentInputBean, PageComponentOutputBean> {

	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private MoTagsDaoService moTagsDaoService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected PageComponentOutputBean doAction(PageComponentInputBean input) {
		logger.info("----------------------PageAllOrderOutputBean begin----------------------");
		PageComponentOutputBean output = new PageComponentOutputBean();
		String cn_name = input.getComponent_cn_name();
//		int[] impl_types = input.getImpl_types();
//		int component_purpose = input.getComponent_purpose();
//		String[] tag_list = input.getTag_list();
//		JaDate start_modify_date = input.getStart_modify_date();
//		JaDate end_modify_date = input.getEnd_modify_date();
//		String crt_user_id = input.getCrt_user_id();
//		int publish_state = input.getPublish_state();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		List<MoComponentInfo> infos =  moComponentDaoService.pageComponentList(cn_name,order_col_name,order_type,start_recd,limit_recd);
		int all_recd = moComponentDaoService.countComponentByCnName(cn_name);
		
		output.setAll_recd(all_recd);
		output.setComp_list(infos);
//		if (Assert.isEmpty(tag_list)) {
//			List<MoComponentInfo> infos = moComponentDaoService.pageComponent(component_purpose, impl_types,
//					crt_user_id, publish_state, start_modify_date, end_modify_date, cn_name, order_type, start_recd,
//					limit_recd);S
//			output.setAll_recd(moComponentDaoService.countComponent(component_purpose, impl_types, crt_user_id,
//					publish_state, start_modify_date, end_modify_date, cn_name));
//			output.setComp_list(CompBeanForList.parseCompList(infos));
//		} else {
//			List<String> comp_id_list = moTagsDaoService.queryIdsByTags(tag_list);
//			if (Assert.isEmpty(comp_id_list)) {
//				output.setAll_recd(0);
//				output.setComp_list(new ArrayList<CompBeanForList>());
//			} else {
//				List<MoComponentInfo> infos = moComponentDaoService.pageComponent(component_purpose, impl_types,
//						crt_user_id, publish_state, start_modify_date, end_modify_date, cn_name, comp_id_list,
//						order_type, start_recd, limit_recd);
//				output.setAll_recd(moComponentDaoService.countComponent(component_purpose, impl_types, crt_user_id,
//						publish_state, start_modify_date, end_modify_date, cn_name));
//				output.setComp_list(CompBeanForList.parseCompList(infos));
//			}
//		}
		logger.info("-----------------------PageAllOrderOutputBean end-----------------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageComponentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("key_word=" + input.getComponent_cn_name());
		lst_val.add("start_recd=" + input.getStart_recd());
		lst_val.add("limit_recd=" + input.getLimit_recd());
		return lgsvc.getLogTxt("根据关键字模分页查询组件列表", lst_val);
	}

}
