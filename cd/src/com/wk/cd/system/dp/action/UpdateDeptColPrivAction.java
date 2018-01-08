/**
 * Title: UpdateDeptColPrivAction.java
 * File Description: �޸Ĳ���colȨ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��28��
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.UpdateDeptColPrivInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptColPrivOutputBean;
import com.wk.cd.system.dp.dao.DpDeptColPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸Ĳ���colȨ��
 * @author HT
 */
public class UpdateDeptColPrivAction extends ActionBasic<UpdateDeptColPrivInputBean, UpdateDeptColPrivOutputBean>{

	@Inject
	private DpDeptColPrivDaoService dpDeptColPrivDaoService;
	@Inject
	private DtCheckSocExistService dtCheckSocExistService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸Ĳ���colȨ��
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptColPrivOutputBean doAction(UpdateDeptColPrivInputBean input) {
		logger.info("------UpdateDeptColPrivAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		UpdateDeptColPrivOutputBean output=new UpdateDeptColPrivOutputBean();
		String dept_id =input.getDept_id();
		List<DpDeptColPrivInfo> col_list=input.getCol_list();
		
		Assert.assertNotEmpty(dept_id, UpdateDeptColPrivInputBean.DEPT_IDCN);
		// �޸�Ȩ��
		dpDeptColPrivDaoService.deleteDeptColPrivInfo(dept_id);
		
		if(!Assert.isEmpty(col_list)){
			for(DpDeptColPrivInfo col_info:col_list){
				col_info.setDept_id(dept_id);
				// *��֤����Դ�����Ƿ����
				dtCheckSocExistService.checkSocExist(col_info.getSoc_name());
			}
			dpDeptColPrivDaoService.insertListInfo(col_list);
		}
		
		logger.info("------UpdateDeptColPrivAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptColPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		return lgsvc.getLogTxt("�޸Ĳ���ColȨ����Ϣ", log_lst);
	}

}
