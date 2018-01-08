/**
 * Title: DeleteDeptAction.java
 * File Description: 删除部门
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.DeleteDeptInputBean;
import com.wk.cd.system.dp.bean.DeleteDeptOutputBean;
import com.wk.cd.system.dp.dao.DpDeptColPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.dao.DpDeptOptPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptRsPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptSocPrivDaoService;
import com.wk.cd.system.dp.dao.DpDeptSqlPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.exc.DeptHasDprlException;
import com.wk.cd.system.exc.DeptHasSubDeptException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 删除部门
 * 
 * @author xuy
 */
public class DeleteDeptAction extends
		ActionBasic<DeleteDeptInputBean, DeleteDeptOutputBean> {

	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private DpDeptRsPrivDaoService deptRsPrivDaoService;
	@Inject
	private DpDeptSocPrivDaoService deptSocPrivDaoService;
	@Inject
	private DpDeptSqlPrivDaoService deptsqlprivDaoService;
	@Inject
	private DpDeptColPrivDaoService deptcolprivDaoService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private DpDeptOptPrivDaoService dpDeptOptPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	/**
	 * Description: 删除部门
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteDeptOutputBean doAction(DeleteDeptInputBean input) {
		DeleteDeptOutputBean output = new DeleteDeptOutputBean();
		// 得到要删除的全部部门编码
		String dept_id = input.getDept_id();
		// 非空检查
		Assert.assertNotEmpty(dept_id, DeleteDeptInputBean.DEPT_IDCN);
		// 循环删除多个部门表及相关表数据，部门资源表、部门数据源表、部门服务表、部门SQL权限表、部门SQL字段权限表
		// 检查部门状态
		dpPublicService.checkDeptState(dept_id);
		DpDeptInfo Info = new DpDeptInfo();
		Info.setDept_id(dept_id);
		String dept_name = deptDaoService.getInfoByKey(Info).getDept_cn_name();
		// *该部门若有下级部门，抛出异常
		List<DpDeptInfo> lower_deptInfos = deptDaoService.pageSubInfosByKey(
				dept_id, 0, 2);
		if (lower_deptInfos.size() >= 1) {
			throw new DeptHasSubDeptException().addScene("PARM", dept_name + "[" + dept_id + "]");
		}
		// *该部门下有角色，抛出异常
		int count_dept_role = usDeptRoleDaoService.countRoleByDept(dept_id);
		if (count_dept_role > 0) {
			throw new DeptHasDprlException().addScene("PARM1", dept_name + "[" + dept_id + "]")
					.addScene("PARM2", "角色");
		}
		// *该部门下有用户，抛出异常
		int count_user = usUserDaoService.countUserByBlDeptId(dept_id);
		if (count_user > 0) {
			throw new DeptHasDprlException().addScene("PARM1", dept_name + "[" + dept_id + "]")
					.addScene("PARM2", "用户");
		}
		// 删除该部门ID下所有表数据
		JaDate del_bk_date = input.getDtbs_bk_date();
		JaTime del_bk_time = input.getDtbs_bk_time();
		String del_user_id = input.getOrg_user_id();
		deptDaoService.deleteDeptInfo(del_bk_date, del_bk_time, del_user_id,
				dept_id);
		deptRsPrivDaoService.deleteDeptRsPrivInfo(dept_id);
		deptSocPrivDaoService.deleteDeptSocPrivInfo(dept_id);
		deptsqlprivDaoService.deleteDeptSqlPrivInfo(dept_id);
		deptcolprivDaoService.deleteDeptColPrivInfo(dept_id);
		dpDeptOptPrivDaoService.deleteDeptOptPrivByDpetId(dept_id);
		return output;
	}

	/**
	 * Description:新增部门日志信息
	 * 
	 * @param input
	 *            输入接口
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteDeptInputBean input) {
		List<String> dept_val = new ArrayList<String>();
		dept_val.add("部门编号列表：" + input.getDept_id());
		return lgsvc.getLogTxt("删除部门信息", dept_val);
	}

}
