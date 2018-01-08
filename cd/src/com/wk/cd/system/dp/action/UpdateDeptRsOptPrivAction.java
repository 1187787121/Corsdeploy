/**
 * Title: UpdateDeptRsOptPrivAction.java
 * File Description: �޸Ĳ�����Դ������Ȩ����Ϣ
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��27��
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.UpdateDeptRsOptPrivInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptRsOptPrivOutputBean;
import com.wk.cd.system.dp.dao.DpDeptOptPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptRsPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;
import com.wk.cd.system.dp.info.DpDeptRsPrivInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.dao.RsOptDaoService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsOptInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸Ĳ�����Դ������Ȩ����Ϣ
 * @author HT
 */
public class UpdateDeptRsOptPrivAction extends ActionBasic<UpdateDeptRsOptPrivInputBean, UpdateDeptRsOptPrivOutputBean>{
	
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private DpDeptRsPrivDaoService dpDeptRsPrivDaoService;
	@Inject
	private UsRoleRsPrivDaoService usRoleRsPrivDaoService;
	@Inject
	private UsUserRsPrivDaoService usUserRsPrivDaoService;
	@Inject
	private DpDeptOptPrivDaoService dpDeptOptPrivDaoService;
	@Inject
	private RsOptDaoService rsOptDaoService;

	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸Ĳ�����Դ������Ȩ����Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptRsOptPrivOutputBean doAction(
			UpdateDeptRsOptPrivInputBean input) {
		logger.info("------UpdateDeptRsOptPrivAction begin------");
		logger.debug("------dept_id[{}]",input.getDept_id());
		
		UpdateDeptRsOptPrivOutputBean output = new UpdateDeptRsOptPrivOutputBean();
		
		String dept_id = input.getDept_id();
		String[] rs_ary = input.getRs_ary();
		
		Assert.assertNotEmpty(dept_id, UpdateDeptRsOptPrivInputBean.DEPT_IDCN);
		
		// ���ݲ��ű�����Ҳ��Ž�ɫ
		List<String> dprl_list = usDeptRoleDaoService.queryRoleByDept(dept_id);
		
		// ���Ҳ��������н�ɫ����Դ
		List<String> dprl_rs_list = usRoleRsPrivDaoService.queryRsPrivByDprls(dprl_list);
		
		// Ҫ���ĵ���Դ�б���û�����еĸò����½�ɫ��Դ �򱨴�
		for (String rs_code:dprl_rs_list) {
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",���ڲ��Ž�ɫ�������ԴȨ��");
			}else{
				List<String> tempList = Arrays.asList(rs_ary);
				if (!tempList.contains(rs_code.trim())) {
					RsResInfo rs_info = new RsResInfo();
					rs_info.setRs_code(rs_code);
					rs_info = rsResDaoService.getInfoByKey(rs_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",���ڲ��Ž�ɫ�������ԴȨ��");
				}
			}
		}
		
		
		//��ѯ�¼������б�
		List<String> sub_dept_ids = dpPublicService.querySubDeptIdsByDeptId(dept_id);

		// *��ѯ�¼����ŵ���ԴȨ�ޣ���Ҫ���ĵ���ԴȨ���б�ʹ���򱨴�
		List<String> dept_rs_list = dpDeptRsPrivDaoService.queryRsPrivByDepts(sub_dept_ids);// *��Դ����
		for (String rs_code : dept_rs_list) {
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",�����¼����ŷ������ԴȨ��");
			}
			List<String> tempList = Arrays.asList(rs_ary);
			if (!tempList.contains(rs_code.trim())) {
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",�����¼����ŷ������ԴȨ��");
			}
		}
		
		// ���ݲ��ű�����Ҳ������û�
		List<String> user_list = usUserDaoService.queryUserByBlDeptId(dept_id);

		// ��ѯ�����û�����ԴȨ�ޣ���Ҫ���ĵ���ԴȨ���б�ʹ���򱨴�
		List<String> user_rs_list = usUserRsPrivDaoService.queryRsPrivByUserId(user_list);
		for(String rs_code:user_rs_list){
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",�����û��������ԴȨ��");
			}
			List<String> tempList = Arrays.asList(rs_ary);
			if (!tempList.contains(rs_code.trim())) {
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"������ԴȨ��[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",�����û��������ԴȨ��");
			}
		}
		
		dpDeptRsPrivDaoService.deleteDeptRsPrivInfo(dept_id);
		// �޸�
		if (!Assert.isEmpty(rs_ary) && rs_ary.length != 0) {
			for (String rs_code : rs_ary) {
				DpDeptRsPrivInfo rsPrivInfo = new DpDeptRsPrivInfo();
				rsPrivInfo.setDept_id(dept_id);
				rsPrivInfo.setRs_code(rs_code);
				dpDeptRsPrivDaoService.insertInfo(rsPrivInfo);
			}
		} 
		
		/********************************* ����Ȩ����֤���޸�  *******************************************/
		
		List<DpDeptOptPrivInfo> dp_opt_priv=input.getOpt_priv();
		
		List<String> new_forbid_priv=new ArrayList<String>();
		List<String> new_allow_priv=new ArrayList<String>();
		if(!Assert.isEmpty(dp_opt_priv)){
			for(DpDeptOptPrivInfo opt_priv:dp_opt_priv){
				if(opt_priv.getPriv_flag()==PRIV_FLAG.YES){//���ڽ�ֹ��Դ�µĲ���Ȩ��
					new_allow_priv.add(opt_priv.getOpt_code());
				}else{//���ڿ�����Դ�µĲ���Ȩ�� 
					new_forbid_priv.add(opt_priv.getOpt_code());
				}
			}
		}
		
		// ��ѯ�¼����Ų���Ȩ��
		List<DpDeptOptPrivInfo> subdp_allow_priv = dpPublicService.queryOptAllowPrivByDepts(sub_dept_ids);
		List<DpDeptOptPrivInfo> subdp_forbid_priv = dpPublicService.queryOptForbidPrivByDepts(sub_dept_ids);

		// ���Ž�ֹȨ��תList<String>
		List<String> subdp_forbid_strs = new ArrayList<String>();
		for (DpDeptOptPrivInfo privInfo : subdp_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			subdp_forbid_strs.add(opt_code);
		}
		
		for(DpDeptOptPrivInfo privInfo:subdp_allow_priv){//�µ�����Ȩ�ޱ�������¼���������Ȩ��		���¼�����Ȩ��Ϊ�գ�������֤
			String opt_code=privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)){
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����ŷ���ò���Ȩ��");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����ŷ���ò���Ȩ��");
			}
		}
		
		for(String opt_code:new_forbid_priv){//�¼����Ž�ֹȨ�ޱ�������µĲ��Ž�ֹȨ��		���½�ֹΪ�գ���ȫ������������֤
			if(!Assert.isEmpty(sub_dept_ids)){
				if (Assert.isEmpty(subdp_forbid_strs)){
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����ŷ���ò���Ȩ��");
				}
				if (!subdp_forbid_strs.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����ŷ���ò���Ȩ��");
				}
			}
		}
		
		
		// ��ѯ���Ž�ɫ����Ȩ��
		List<UsRoleOptPrivInfo> dr_forbid_priv = dpPublicService.queryOptForbidPrivByDprls(dprl_list);
		List<UsRoleOptPrivInfo> dr_allow_priv = dpPublicService.queryOptAllowPrivByDprls(dprl_list);
		// ���Ž�ɫ��ֹȨ��תList<String>
		List<String> dr_forbid_strs = new ArrayList<String>();
		for (UsRoleOptPrivInfo privInfo : dr_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			dr_forbid_strs.add(opt_code);
		}
		
		for(UsRoleOptPrivInfo privInfo:dr_allow_priv){//�µ�����Ȩ�ޱ�������¼����Ž�ɫ����Ȩ��		���¼�����Ȩ��Ϊ�գ�������֤
			String opt_code=privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)){
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����Ž�ɫ����ò���Ȩ��");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����Ž�ɫ����ò���Ȩ��");
			}
		}
		
		for(String opt_code:new_forbid_priv){//�¼����Ž�ɫ��ֹȨ�ޱ�������µĲ��Ž�ֹȨ��		���½�ֹΪ�գ���ȫ������������֤
			if(!Assert.isEmpty(dprl_list)){
				if (Assert.isEmpty(dr_forbid_strs)){
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����Ž�ɫ����ò���Ȩ��");
				}
				if (!dr_forbid_priv.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",�����¼����Ž�ɫ����ò���Ȩ��");
				}
			}
		}
		
		
		// ��ѯ�û�����Ȩ��
		List<UsUserOptPrivInfo> us_forbid_priv = dpPublicService.queryOptForbidPrivByUsers(user_list);
		List<UsUserOptPrivInfo> us_allow_priv = dpPublicService.queryOptAllowPrivByUsers(user_list);
		// ���Ž�ɫ��ֹȨ��תList<String>
		List<String> us_forbid_strs = new ArrayList<String>();
		for (UsUserOptPrivInfo privInfo : us_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			us_forbid_strs.add(opt_code);
		}

		for (UsUserOptPrivInfo privInfo : us_allow_priv) {// �µ�����Ȩ�ޱ�������¼��û�����Ȩ��               ���¼�����Ȩ��Ϊ�գ�������֤
			String opt_code = privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",�����û�����ò���Ȩ��");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",�����û�����ò���Ȩ��");
			}
		}

		for (String opt_code : new_forbid_priv) {// �¼��û���ֹȨ�ޱ�������µĲ��Ž�ֹȨ��     ���½�ֹΪ�գ���ȫ������������֤
			if(!Assert.isEmpty(user_list)){
				if (Assert.isEmpty(us_forbid_strs)) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",�����û�����ò���Ȩ��");
				}
				if (!us_forbid_strs.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"���Ų���Ȩ��[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",�����û�����ò���Ȩ��");
				}
			}
		}
		
		dpDeptOptPrivDaoService.deleteDeptOptPrivByDpetId(dept_id);
		for (DpDeptOptPrivInfo opt_priv : dp_opt_priv) {
			opt_priv.setDept_id(dept_id);
			dpDeptOptPrivDaoService.insertInfo(opt_priv);
		}
		
		logger.info("------UpdateDeptRsOptPrivAction end------");
		return output;
	}
	

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptRsOptPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		String[] rs_ary = input.getRs_ary();
		if (!Assert.isEmpty(rs_ary)) {
			log_lst.add(rs_ary.toString());
		}
		return lgsvc.getLogTxt("�޸Ĳ�����Դ������Ȩ����Ϣ", log_lst);
	}

}
