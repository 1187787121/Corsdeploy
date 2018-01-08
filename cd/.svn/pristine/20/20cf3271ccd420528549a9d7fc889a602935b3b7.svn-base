/**
 * Title: SvSrvService.java
 * File Description: 服务模块公用服务类
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.ap.service;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AUTH_FLAG;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.CHECK_FLAG;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.dao.SvSrvAuthDaoService;
import com.wk.cd.system.ap.dao.SvSrvCheckDaoService;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.lang.Inject;

/**
 * Class Description: 服务模块公用服务类
 * @author tlw
 */
public class ApPublicService {
	@Inject
	private SvSrvCheckDaoService ssCheckDaos;
	@Inject
	private SvSrvAuthDaoService ssAuthDaos;
	@Inject
	private SvSrvDaoService ssDaos;
	@Inject
	private DpPublicService dpsrv;
	
	/**
	 * 按照主键获取复核信息
	 * @param info 输入主键信息
	 * @return 复核信息
	 */
	public SvSrvCheckInfo queryNoDelSrvCheckByKey(SvSrvCheckInfo info) {
		return ssCheckDaos.getInfoByKey(info);
	}

	/**
	 * 按照主键获取授权信息
	 * @param info 输入主键信息
	 * @return 授权信息
	 */
	public SvSrvAuthInfo queryNoDelSrvAuthByKey(SvSrvAuthInfo info) {
		return ssAuthDaos.getInfoByKey(info);
	}
	
	/**
	 * 按照服务名称删除服务复核配置表一组信息
	 * @param srv_name 服务名称
	 * @return 删除条数
	 */
	public int deleteSvSrvCheck(String srv_name) {
		return ssCheckDaos.deleteSvSrvCheck(srv_name);
	}

	/**
	 * 按照服务名称删除服务授权配置表一组信息
	 * @param srv_name 服务名称
	 * @return 删除条数
	 */
	public int deleteSvSrvAuth(String srv_name) {
		return ssAuthDaos.deleteSvSrvAuth(srv_name);
	}
	
	/**
	 * 按照服务名称查询服务复核表中对应记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countSvSrvCheck(String srv_name) {
		return ssCheckDaos.countSvSrvCheck(srv_name);
	}

	/**
	 * 按照服务名称查询服务授权表中对应记录条数
	 * @param srv_name 服务名称
	 * @return 记录条数
	 */
	public int countSvSrvAuth(String srv_name) {
		return ssAuthDaos.countSvSrvAuth(srv_name);
	}
	
	/**
	 * Description: 按照部门编码和服务名称更新服务复核表相关信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @param infos 更新复核信息列表
	 * @return 记录条数
	 */
	public int updateSvSrvCheckByName(String dept_id, String srv_name, 
			List<SvSrvCheckInfo> infos) {
		//如果本部门和下级部门有配置审批流程，删除所有的审批配置流程
		String[] dept_arr = {dept_id};
		List<String> dept_list = dpsrv.queryDeptIdByKey(dept_arr);
		String dept_str = listToString(dept_list);
		int update_item = ssCheckDaos.countInfos(dept_str, srv_name);
		if (update_item > 0) {
			for(String d : dept_list){
				ssCheckDaos.deleteInfo(d, srv_name);
			}
		}
		// 需要复核写入新的记录
		ssCheckDaos.insertListInfo(infos);
		return update_item;
	}

	/**
	 * Description: 按照部门编码和服务名称更新服务授权表相关信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @param infos 更新授权信息列表
	 * @return 记录条数
	 */
	public int updateSvSrvAuthByName(String dept_id, String srv_name, 
			List<SvSrvAuthInfo> infos) {
		//如果本部门和下级部门有配置审批流程，删除所有的审批配置流程
		String[] dept_arr = {dept_id};
		List<String> dept_list = dpsrv.queryDeptIdByKey(dept_arr);
		String dept_str = listToString(dept_list);
		int update_item = ssAuthDaos.countInfos(dept_str, srv_name);
		if (update_item > 0) {
			for(String d : dept_list){
				ssAuthDaos.deleteInfo(d, srv_name);
			}
		}
		// 需要授权写入新的记录
		ssAuthDaos.insertListInfo(infos);
		return update_item;
	}
	
	/**
	 * 根据服务名称和授权复核类型查询最小的授权复核序号
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @param check_auth_seq 授权复核序号
	 * @param deal_type 处理类型
	 * @return
	 */
	public int queryMinSeq(String dept_id, String srv_name, int check_auth_seq, DEAL_TYPE deal_type){
		int seq = 0;
		if(deal_type == DEAL_TYPE.RECHECK){
			seq = ssCheckDaos.queryMinCheckSeq(dept_id, srv_name, check_auth_seq);
		}else if(deal_type == DEAL_TYPE.AUTH){
			seq = ssAuthDaos.queryMinAuthSeq(dept_id, srv_name, check_auth_seq);
		}else{
			throw new DataErrorException().addScene("INPUT", "查询服务[" + srv_name + "]最小复核授权序号时" + DEAL_TYPE.ENUMCN);
		}
		if(seq < 0){
			throw new DataErrorException().addScene("INPUT", "服务[" + srv_name + "]复核或者授权序号");
		}
		return seq;
	}
	
	/** 
	 * 检查服务是否需要本地授权
	 * @param org_srv_name
	 * @return 
	 */
	public boolean queryLocalAuthFlag(String dept_id, String org_srv_name) {
		int lauth_count=ssAuthDaos.countBySrvAndType(dept_id, org_srv_name,AUTH_TYPE.LOCAL);
		if(lauth_count>0){
			return true;
		}
		return false;
	}

	/** 
	 * 检查服务是否需要远程授权
	 * @param org_srv_name
	 * @return 
	 */
	public boolean queryRemoteAuthFlag(String dept_id, String org_srv_name) {
		int rauth_count=ssAuthDaos.countBySrvAndType(dept_id, org_srv_name,AUTH_TYPE.REMOTE);
		if(rauth_count>0){
			return true;
		}
		return false;
	}

	/** 
	 * 查询第一个本地授权角色
	 * @param dept_id 部门编码
	 * @param org_srv_name 发起服务名称
	 * @return 
	 */
	public SvSrvAuthInfo queryLocalAuthRole(String dept_id, String org_srv_name,int auth_seq) {
		return ssAuthDaos.queryLocalAuthRole(dept_id, org_srv_name,auth_seq);
	}
	
	/**
	 * Description: 根据机构编码和服务名称查询待复核部门角色编码信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return
	 */
	public List<ChkDprlCodeBean> queryCheckBySrvName (String dept_id, String srv_name){
		return ssCheckDaos.queryCheckBySrvName(dept_id, srv_name);
	}
	
	/**
	 * Description: 根据部门编码和服务名称组合查询待授权部门角色信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return
	 */
	public List<AuthDprlCodeBean> queryAuthBySrvName(String dept_id, String srv_name) {
		return ssAuthDaos.queryAuthBySrvName(dept_id, srv_name);
	}
	
	/**
	 * 判断机构中的服务是否需要复核
	 * @param dept_id 机构代码
	 * @param srv_name 服务名称
	 * @return 是否需要复核标志
	 */
	public boolean queryCheckFlag(String dept_id, String srv_name) {
		boolean flag = false;
		SvSrvInfo info = new SvSrvInfo();
		info.setSrv_name(srv_name);
		info = ssDaos.getInfoByName(info);
		if (info.getCheck_flag() == CHECK_FLAG.ALLOW) {
			if(!Assert.isEmpty(ssCheckDaos.queryCheckBySrvName(dept_id, srv_name))){
				flag = true;
			}
		} 
		return flag;
	}

	/**
	 * 判断机构中的服务是否需要授权
	 * @param dept_id 机构代码
	 * @param srv_name 服务名称
	 * @return 是否需要授权标志
	 */
	public boolean queryAuthFlag(String dept_id, String srv_name) {
		boolean flag = false;
		SvSrvInfo info = new SvSrvInfo();
		info.setSrv_name(srv_name);
		info = ssDaos.getInfoByName(info);
		if (info.getAuth_flag() == AUTH_FLAG.ALLOW) {
			if(!Assert.isEmpty(ssAuthDaos.queryAuthBySrvName(dept_id, srv_name))){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 将输入的列表转换为字符串，例如列表中为a b c，转换后则为('a','b','c')
	 * @param list 输入列表
	 * @return 字符串
	 */
	private String listToString(List<String> list) {
		String str = "";
		// 输入的部门角色列表信息为空报错
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "输入信息");
		}
		for (String s : list) {
			str = str + s + "','";
		}
		str = "('" + str.substring(0, str.length() - 2) + ")";
		return str;
	}
}
