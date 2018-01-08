/**
 * Title: SvSrvCheckDaoService.java
 * File Description: 服务复核定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.lang.Inject;

/**
 * Class description:服务复核定义表
 * @author AutoGen
 */
public class SvSrvCheckDaoService {
	@Inject
	private SvSrvCheckDao dao;
	@Inject
	private SvSrvCheckDaoService daosrv;
	@Inject
	private DpPublicService dpsrv;
	
	/**
	 * 根据主键查询一条记录
	 * @param SvSrvCheckInfo info
	 * @return SvSrvCheckInfo
	 */
	public SvSrvCheckInfo getInfoByKey(SvSrvCheckInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvSrvCheckInfo info
	 * @return SvSrvCheckInfo
	 */
	public SvSrvCheckInfo getInfoByKeyForUpdate(SvSrvCheckInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param SvSrvCheckInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvCheckInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvSrvCheckInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvCheckInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据服务名称查询复核表多条记录
	 * @param String srv_name
	 * @return List<SvSrvCheckInfo>
	 */
	public List<SvSrvCheckInfo> getListInfoByName(String srv_name) {
		List<SvSrvCheckInfo> srv_chk_lst = dao.getListInfoByName(srv_name);
		if (Assert.isEmpty(srv_chk_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvCheckInfo.TABLECN).addScene("FIELD", srv_name);
		}
		return srv_chk_lst;
	}

	/**
	 * 根据部门编码和服务名称删除一组服务复核信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int deleteInfo(String dept_id, String srv_name) {
		return dao.deleteInfo(dept_id, srv_name);
	}
	
	/**
	 * 根据服务名称删除一组服务复核信息
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int deleteSvSrvCheck(String srv_name) {
		return dao.deleteSvSrvCheck(srv_name);
	}

	/**
	 * 根据部门编码和服务名称查询记录条数
	 * @param dept_str 部门编码
	 * @param srv_name 服务名称
	 * @return int 记录条数
	 */
	public int countInfos(String dept_str, String srv_name) {
		return dao.countInfos(dept_str, srv_name);
	}
	
	/**
	 * 根据服务名称查询记录条数
	 * @param srv_name 服务名称
	 * @return int 记录条数
	 */
	public int countSvSrvCheck(String srv_name) {
		return dao.countSvSrvCheck(srv_name);
	}

	/**
	 * 根据部门角色编码查询角色复核服务
	 * @param check_dprl_code_list 复核部门角色列表
	 * @return 服务列表
	 */
	public List<SvSrvCheckInfo> querySrvCheckByDprlCode(
			List<String> check_dprl_code_list) {
		String check_dprl_code_str = "";
		int list_size = 0;
		if (!Assert.isEmpty(check_dprl_code_list)) {
			list_size = check_dprl_code_list.size();
			if (list_size > 0) {
				for (int i = 0; i < list_size; i++) {
					check_dprl_code_str += check_dprl_code_list.get(i) + "','";
				}
				check_dprl_code_str = "('"
						+ check_dprl_code_str.substring(0, check_dprl_code_str
								.length() - 2) + ")";
			}
		}
		return dao.querySrvCheckByDprlCode(check_dprl_code_str);
	}

	/**
	 * Description: 根据机构编码和服务名称查询带复核部门角色编码信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return
	 */
	public List<ChkDprlCodeBean> queryCheckBySrvName (String dept_id, String srv_name){
		List<ChkDprlCodeBean> check_list =  dao.queryCheckDprlBySrvName(dept_id, srv_name);
		if(Assert.isEmpty(check_list)){
			DpDeptInfo dept_info = dpsrv.getInfoByKey(dept_id);
			if(dept_info.getDept_level() >= 1 && !Assert.isEmpty(dept_info.getSuper_dept_id())){
				check_list =  daosrv.queryCheckBySrvName(dept_info.getSuper_dept_id(), srv_name);
				if(!Assert.isEmpty(check_list)){
					for(ChkDprlCodeBean c : check_list){
						c.setSuper_flag(SUPER_FLAG.YES);
					}
				}
			}
		}
		return check_list;
	}
	
	/**
	 * 查找指定的服务名称和大于指定复核序号的最小的复核序号
	 * @param srv_name 服务名称
	 * @param check_seq 复核序号
	 * @return 最小复核序号
	 */
	public int queryMinCheckSeq(String dept_id, String srv_name, int check_seq){
		return dao.queryMinCheckSeq(dept_id, srv_name, check_seq);
	}
}
