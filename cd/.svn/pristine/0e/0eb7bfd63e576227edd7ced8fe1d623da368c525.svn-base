/**
 * Title: UpdateDeptRsOptPrivAction.java
 * File Description: 修改部门资源及操作权限信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月27日
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
 * Class Description: 修改部门资源及操作权限信息
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
	 * Description: 修改部门资源及操作权限信息
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
		
		// 根据部门编码查找部门角色
		List<String> dprl_list = usDeptRoleDaoService.queryRoleByDept(dept_id);
		
		// 查找部门下所有角色的资源
		List<String> dprl_rs_list = usRoleRsPrivDaoService.queryRsPrivByDprls(dprl_list);
		
		// 要更改的资源列表里没有所有的该部门下角色资源 则报错
		for (String rs_code:dprl_rs_list) {
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在部门角色分配该资源权限");
			}else{
				List<String> tempList = Arrays.asList(rs_ary);
				if (!tempList.contains(rs_code.trim())) {
					RsResInfo rs_info = new RsResInfo();
					rs_info.setRs_code(rs_code);
					rs_info = rsResDaoService.getInfoByKey(rs_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在部门角色分配该资源权限");
				}
			}
		}
		
		
		//查询下级部门列表
		List<String> sub_dept_ids = dpPublicService.querySubDeptIdsByDeptId(dept_id);

		// *查询下级部门的资源权限，若要更改的资源权限有被使用则报错
		List<String> dept_rs_list = dpDeptRsPrivDaoService.queryRsPrivByDepts(sub_dept_ids);// *资源编码
		for (String rs_code : dept_rs_list) {
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在下级部门分配该资源权限");
			}
			List<String> tempList = Arrays.asList(rs_ary);
			if (!tempList.contains(rs_code.trim())) {
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在下级部门分配该资源权限");
			}
		}
		
		// 根据部门编码查找部门下用户
		List<String> user_list = usUserDaoService.queryUserByBlDeptId(dept_id);

		// 查询部门用户的资源权限，若要更改的资源权限有被使用则报错
		List<String> user_rs_list = usUserRsPrivDaoService.queryRsPrivByUserId(user_list);
		for(String rs_code:user_rs_list){
			if (Assert.isEmpty(rs_ary)){
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在用户分配该资源权限");
			}
			List<String> tempList = Arrays.asList(rs_ary);
			if (!tempList.contains(rs_code.trim())) {
				RsResInfo rs_info = new RsResInfo();
				rs_info.setRs_code(rs_code);
				rs_info = rsResDaoService.getInfoByKey(rs_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门资源权限[" + rs_info.getRs_cn_name() + "][" + rs_code + "]").addScene("PARM2", ",存在用户分配该资源权限");
			}
		}
		
		dpDeptRsPrivDaoService.deleteDeptRsPrivInfo(dept_id);
		// 修改
		if (!Assert.isEmpty(rs_ary) && rs_ary.length != 0) {
			for (String rs_code : rs_ary) {
				DpDeptRsPrivInfo rsPrivInfo = new DpDeptRsPrivInfo();
				rsPrivInfo.setDept_id(dept_id);
				rsPrivInfo.setRs_code(rs_code);
				dpDeptRsPrivDaoService.insertInfo(rsPrivInfo);
			}
		} 
		
		/********************************* 操作权限验证及修改  *******************************************/
		
		List<DpDeptOptPrivInfo> dp_opt_priv=input.getOpt_priv();
		
		List<String> new_forbid_priv=new ArrayList<String>();
		List<String> new_allow_priv=new ArrayList<String>();
		if(!Assert.isEmpty(dp_opt_priv)){
			for(DpDeptOptPrivInfo opt_priv:dp_opt_priv){
				if(opt_priv.getPriv_flag()==PRIV_FLAG.YES){//属于禁止资源下的操作权限
					new_allow_priv.add(opt_priv.getOpt_code());
				}else{//属于开放资源下的操作权限 
					new_forbid_priv.add(opt_priv.getOpt_code());
				}
			}
		}
		
		// 查询下级部门操作权限
		List<DpDeptOptPrivInfo> subdp_allow_priv = dpPublicService.queryOptAllowPrivByDepts(sub_dept_ids);
		List<DpDeptOptPrivInfo> subdp_forbid_priv = dpPublicService.queryOptForbidPrivByDepts(sub_dept_ids);

		// 部门禁止权限转List<String>
		List<String> subdp_forbid_strs = new ArrayList<String>();
		for (DpDeptOptPrivInfo privInfo : subdp_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			subdp_forbid_strs.add(opt_code);
		}
		
		for(DpDeptOptPrivInfo privInfo:subdp_allow_priv){//新的允许权限必须包含下级部门允许权限		若下级允许权限为空，不需验证
			String opt_code=privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)){
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门分配该操作权限");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门分配该操作权限");
			}
		}
		
		for(String opt_code:new_forbid_priv){//下级部门禁止权限必须包含新的部门禁止权限		若新禁止为空，则全部允许，不需验证
			if(!Assert.isEmpty(sub_dept_ids)){
				if (Assert.isEmpty(subdp_forbid_strs)){
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门分配该操作权限");
				}
				if (!subdp_forbid_strs.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门分配该操作权限");
				}
			}
		}
		
		
		// 查询部门角色操作权限
		List<UsRoleOptPrivInfo> dr_forbid_priv = dpPublicService.queryOptForbidPrivByDprls(dprl_list);
		List<UsRoleOptPrivInfo> dr_allow_priv = dpPublicService.queryOptAllowPrivByDprls(dprl_list);
		// 部门角色禁止权限转List<String>
		List<String> dr_forbid_strs = new ArrayList<String>();
		for (UsRoleOptPrivInfo privInfo : dr_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			dr_forbid_strs.add(opt_code);
		}
		
		for(UsRoleOptPrivInfo privInfo:dr_allow_priv){//新的允许权限必须包含下级部门角色允许权限		若下级允许权限为空，不需验证
			String opt_code=privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)){
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门角色分配该操作权限");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门角色分配该操作权限");
			}
		}
		
		for(String opt_code:new_forbid_priv){//下级部门角色禁止权限必须包含新的部门禁止权限		若新禁止为空，则全部允许，不需验证
			if(!Assert.isEmpty(dprl_list)){
				if (Assert.isEmpty(dr_forbid_strs)){
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门角色分配该操作权限");
				}
				if (!dr_forbid_priv.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code + "]").addScene("PARM2", ",存在下级部门角色分配该操作权限");
				}
			}
		}
		
		
		// 查询用户操作权限
		List<UsUserOptPrivInfo> us_forbid_priv = dpPublicService.queryOptForbidPrivByUsers(user_list);
		List<UsUserOptPrivInfo> us_allow_priv = dpPublicService.queryOptAllowPrivByUsers(user_list);
		// 部门角色禁止权限转List<String>
		List<String> us_forbid_strs = new ArrayList<String>();
		for (UsUserOptPrivInfo privInfo : us_forbid_priv) {
			String opt_code = privInfo.getOpt_code();
			us_forbid_strs.add(opt_code);
		}

		for (UsUserOptPrivInfo privInfo : us_allow_priv) {// 新的允许权限必须包含下级用户允许权限               若下级允许权限为空，不需验证
			String opt_code = privInfo.getOpt_code();
			if (Assert.isEmpty(new_allow_priv)) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",存在用户分配该操作权限");
			}
			if (!new_allow_priv.contains(opt_code.trim())) {
				RsOptInfo opt_info = new RsOptInfo();
				opt_info.setOpt_code(opt_code);
				opt_info = rsOptDaoService.getInfoByKey(opt_info);
				throw new IllegalOperaterException().addScene("PARM1",
						"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",存在用户分配该操作权限");
			}
		}

		for (String opt_code : new_forbid_priv) {// 下级用户禁止权限必须包含新的部门禁止权限     若新禁止为空，则全部允许，不需验证
			if(!Assert.isEmpty(user_list)){
				if (Assert.isEmpty(us_forbid_strs)) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",存在用户分配该操作权限");
				}
				if (!us_forbid_strs.contains(opt_code.trim())) {
					RsOptInfo opt_info = new RsOptInfo();
					opt_info.setOpt_code(opt_code);
					opt_info = rsOptDaoService.getInfoByKey(opt_info);
					throw new IllegalOperaterException().addScene("PARM1",
							"部门操作权限[" + opt_info.getOpt_name() + "][" + opt_code+ "]").addScene("PARM2", ",存在用户分配该操作权限");
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
	 * Description: 写日志信息
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
		return lgsvc.getLogTxt("修改部门资源及操作权限信息", log_lst);
	}

}
