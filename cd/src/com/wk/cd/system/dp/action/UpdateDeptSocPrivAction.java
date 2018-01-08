/**
 * Title: UpdateDeptSocPrivAction.java
 * File Description: �޸Ĳ�������ԴȨ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��28��
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.UpdateDeptSocPrivInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptSocPrivOutputBean;
import com.wk.cd.system.dp.dao.DpDeptSocPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptSocPrivInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸Ĳ�������ԴȨ��
 * @author HT
 */
public class UpdateDeptSocPrivAction extends ActionBasic<UpdateDeptSocPrivInputBean, UpdateDeptSocPrivOutputBean>{

	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsRoleSocPrivDaoService usRoleSocPrivDaoService;
	@Inject 
	private UsUserSocPrivDaoService usUserSocPrivDaoService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private DpDeptSocPrivDaoService dpDeptSocPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸Ĳ�������ԴȨ��
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptSocPrivOutputBean doAction(UpdateDeptSocPrivInputBean input) {
		logger.info("------UpdateDeptSocPrivAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		
		UpdateDeptSocPrivOutputBean output=new UpdateDeptSocPrivOutputBean();
		String dept_id =input.getDept_id();
		String[] soc_ary = input.getSoc_ary();
		
		Assert.assertNotEmpty(dept_id, UpdateDeptSocPrivInputBean.DEPT_IDCN);
		
		// ���ݲ��ű�����Ҳ��Ž�ɫ
		List<String> dprl_list = usDeptRoleDaoService.queryRoleByDept(dept_id);

		// ���Ҳ��������н�ɫ������ԴȨ��
		List<String> dprl_soc_list = usRoleSocPrivDaoService.querySocPrivByDprls(dprl_list);
		
		// Ҫ���ĵ�����Դ�б���û�����еĸò����½�ɫ����Դ �򱨴�
		for (String soc_name:dprl_soc_list) {
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"��������ԴȨ��[" + soc_name+ "]").addScene("PARM2", ",���ڲ��Ž�ɫ���������ԴȨ��");
			}else{
				List<String> tempList = Arrays.asList(soc_ary);
				if (!tempList.contains(soc_name.trim())) {
					throw new IllegalOperaterException().addScene("PARM1",
							"��������ԴȨ��[" + soc_name + "]").addScene("PARM2", "���ڲ��Ž�ɫ���������ԴȨ��");
				}
			}
		}
		
		
		//��ѯ�¼������б�
		List<String> sub_dept_ids = dpPublicService.querySubDeptIdsByDeptId(dept_id);

		// *��ѯ�¼����ŵ�����ԴȨ�ޣ���Ҫ���ĵ�����ԴȨ���б�ʹ���򱨴�
		List<String> dept_soc_list = dpDeptSocPrivDaoService.querySocPrivByDepts(sub_dept_ids);// *����Դ����
		for (String soc_name : dept_soc_list) {
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"��������ԴȨ��[" + soc_name + "]").addScene("PARM2", ",�����¼����ŷ��������ԴȨ��");
			}
			List<String> tempList = Arrays.asList(soc_ary);
			if (!tempList.contains(soc_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1",
						"��������ԴȨ��[" + soc_name + "]").addScene("PARM2", ",�����¼����ŷ��������ԴȨ��");
			}
		}
		
		// ���ݲ��ű�����Ҳ������û�
		List<String> user_list = usUserDaoService.queryUserByBlDeptId(dept_id);

		// ��ѯ�����û�������ԴȨ�ޣ���Ҫ���ĵ�����ԴȨ���б�ʹ���򱨴�
		List<String> user_soc_list = usUserSocPrivDaoService.querySocPrivByUserId(user_list);
		for(String soc_name:user_soc_list){
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"��������ԴȨ��[" +soc_name + "]").addScene("PARM2", ",�����û����������ԴȨ��");
			}
			List<String> tempList = Arrays.asList(soc_ary);
			if (!tempList.contains(soc_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1",
						"��������ԴȨ��[" + soc_name + "]").addScene("PARM2", ",�����û����������ԴȨ��");
			}
		}
		
		dpDeptSocPrivDaoService.deleteDeptSocPrivInfo(dept_id);
		// �޸�
		if (!Assert.isEmpty(soc_ary) && soc_ary.length != 0) {
			for (String soc_name : soc_ary) {
				DpDeptSocPrivInfo socPrivInfo = new DpDeptSocPrivInfo();
				socPrivInfo.setDept_id(dept_id);
				socPrivInfo.setSoc_name(soc_name);
				dpDeptSocPrivDaoService.insertInfo(socPrivInfo);
			}
		} 
		
		logger.info("------UpdateDeptSocPrivAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptSocPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		String[] soc_ary = input.getSoc_ary();
		if (!Assert.isEmpty(soc_ary)) {
			log_lst.add(soc_ary.toString());
		}
		return lgsvc.getLogTxt("�޸Ĳ�������ԴȨ����Ϣ", log_lst);
	}

}
