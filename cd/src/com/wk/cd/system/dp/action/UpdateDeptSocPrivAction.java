/**
 * Title: UpdateDeptSocPrivAction.java
 * File Description: 修改部门数据源权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月28日
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
 * Class Description: 修改部门数据源权限
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
	 * Description: 修改部门数据源权限
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
		
		// 根据部门编码查找部门角色
		List<String> dprl_list = usDeptRoleDaoService.queryRoleByDept(dept_id);

		// 查找部门下所有角色的数据源权限
		List<String> dprl_soc_list = usRoleSocPrivDaoService.querySocPrivByDprls(dprl_list);
		
		// 要更改的数据源列表里没有所有的该部门下角色数据源 则报错
		for (String soc_name:dprl_soc_list) {
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"部门数据源权限[" + soc_name+ "]").addScene("PARM2", ",存在部门角色分配该数据源权限");
			}else{
				List<String> tempList = Arrays.asList(soc_ary);
				if (!tempList.contains(soc_name.trim())) {
					throw new IllegalOperaterException().addScene("PARM1",
							"部门数据源权限[" + soc_name + "]").addScene("PARM2", "存在部门角色分配该数据源权限");
				}
			}
		}
		
		
		//查询下级部门列表
		List<String> sub_dept_ids = dpPublicService.querySubDeptIdsByDeptId(dept_id);

		// *查询下级部门的数据源权限，若要更改的数据源权限有被使用则报错
		List<String> dept_soc_list = dpDeptSocPrivDaoService.querySocPrivByDepts(sub_dept_ids);// *数据源编码
		for (String soc_name : dept_soc_list) {
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"部门数据源权限[" + soc_name + "]").addScene("PARM2", ",存在下级部门分配该数据源权限");
			}
			List<String> tempList = Arrays.asList(soc_ary);
			if (!tempList.contains(soc_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1",
						"部门数据源权限[" + soc_name + "]").addScene("PARM2", ",存在下级部门分配该数据源权限");
			}
		}
		
		// 根据部门编码查找部门下用户
		List<String> user_list = usUserDaoService.queryUserByBlDeptId(dept_id);

		// 查询部门用户的数据源权限，若要更改的数据源权限有被使用则报错
		List<String> user_soc_list = usUserSocPrivDaoService.querySocPrivByUserId(user_list);
		for(String soc_name:user_soc_list){
			if (Assert.isEmpty(soc_ary)){
				throw new IllegalOperaterException().addScene("PARM1",
						"部门数据源权限[" +soc_name + "]").addScene("PARM2", ",存在用户分配该数据源权限");
			}
			List<String> tempList = Arrays.asList(soc_ary);
			if (!tempList.contains(soc_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1",
						"部门数据源权限[" + soc_name + "]").addScene("PARM2", ",存在用户分配该数据源权限");
			}
		}
		
		dpDeptSocPrivDaoService.deleteDeptSocPrivInfo(dept_id);
		// 修改
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
	 * Description: 写日志信息
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
		return lgsvc.getLogTxt("修改部门数据源权限信息", log_lst);
	}

}
