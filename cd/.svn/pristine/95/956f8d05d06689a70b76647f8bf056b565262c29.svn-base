/**
 * Title: SvSrvAuthDaoService.java
 * File Description: 服务授权定义表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.ap.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.lang.Inject;

/**
 * Class description:服务授权定义表
 * @author AutoGen
 */
public class SvSrvAuthDaoService {
	@Inject
	private SvSrvAuthDao dao;
	@Inject
	private DpPublicService dpsrv;
	
	/**
	 * 根据主键查询一条记录
	 * @param SvSrvAuthInfo info
	 * @return SvSrvAuthInfo
	 */
	public SvSrvAuthInfo getInfoByKey(SvSrvAuthInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvSrvAuthInfo info
	 * @return SvSrvAuthInfo
	 */
	public SvSrvAuthInfo getInfoByKeyForUpdate(SvSrvAuthInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param SvSrvAuthInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvAuthInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvSrvAuthInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvAuthInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据服务名称查询授权表多条记录
	 * @param String srv_name
	 * @return List<SvSrvAuthInfo>
	 */
	public List<SvSrvAuthInfo> getListInfoByName(String srv_name) {
		List<SvSrvAuthInfo> srv_auth_lst = dao.getListInfoByName(srv_name);
		if (Assert.isEmpty(srv_auth_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvAuthInfo.TABLECN).addScene("FIELD", srv_name);
		}
		return srv_auth_lst;
	}

	/**
	 * 根据部门编码和服务名称删除一组服务授权信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int deleteInfo(String dept_id, String srv_name) {
		return dao.deleteInfo(dept_id, srv_name);
	}
	
	/**
	 * 根据服务名称删除一组服务授权信息
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int deleteSvSrvAuth(String srv_name) {
		return dao.deleteSvSrvAuth(srv_name);
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
	public int countSvSrvAuth(String srv_name) {
		return dao.countSvSrvAuth(srv_name);
	}

	/**
	 * 根据部门角色编码查询角色授权服务
	 * @param auth_dprl_code_list 部门角色编码列表
	 * @return 服务列表
	 */
	public List<SvSrvAuthInfo> querySrvAuthByDprlCode(
			List<String> auth_dprl_code_list) {
		String auth_dprl_code_str = "";
		int list_size = 0;
		if (!Assert.isEmpty(auth_dprl_code_list)) {
			list_size = auth_dprl_code_list.size();
			if (list_size > 0) {
				for (int i = 0; i < list_size; i++) {
					auth_dprl_code_str += auth_dprl_code_list.get(i) + "','";
				}
				auth_dprl_code_str = "('"
						+ auth_dprl_code_str.substring(0, auth_dprl_code_str
								.length() - 2) + ")";
			}
		}
		return dao.querySrvAuthByDprlCode(auth_dprl_code_str);
	}

	/**
	 * 检查服务名、序号对应的部门角色是否存在
	 * @param srv_name 服务名
	 * @param auth_seq 序号
	 * @param dprl_code_list 复核部门角色
	 * @return
	 */
	public int countSrvByNameAndSeq(String srv_name, int auth_seq,
			List<String> dprl_code_list, APROV_CATEGORY role) {
		if (Assert.isEmpty(dprl_code_list)) {
			throw new DataErrorException().addScene("INPUT", "部门角色列表"
					+ dprl_code_list.toString());
		}
		srv_name = "'" + srv_name + "'";
		String dprl_code_str = listToString(dprl_code_list);
		return dao.countSrvByNameAndSeq(srv_name, auth_seq, dprl_code_str, role);
	}

	/**
	 * 部门编码和服务名称组合查询授权角色信息
	 * @param dept_id 部门编码
	 * @param srv_name 服务名称
	 * @return
	 */
	public List<AuthDprlCodeBean> queryAuthBySrvName(String dept_id, String srv_name) {
		List<AuthDprlCodeBean> auth_list = dao.queryAuthDprlBySrvName(dept_id, srv_name);
		if(Assert.isEmpty(auth_list)){
			DpDeptInfo dept_info = dpsrv.getInfoByKey(dept_id);
			if(dept_info.getDept_level() >= 1 && !Assert.isEmpty(dept_info.getSuper_dept_id())){
				auth_list =  queryAuthBySrvName(dept_info.getSuper_dept_id(), srv_name);
				if(!Assert.isEmpty(auth_list)){
					for(AuthDprlCodeBean a : auth_list){
						a.setSuper_flag(SUPER_FLAG.YES);
					}
				}
			}
		}
		return auth_list;
	}
	
	/**
	 * 查找指定的服务名称和大于指定授权序号的最小的授权序号
	 * @param srv_name 服务名称
	 * @param auth_seq 授权序号
	 * @return 最小授权序号
	 */
	public int queryMinAuthSeq(String dept_id, String srv_name, int auth_seq){
		return dao.queryMinAuthSeq(dept_id, srv_name, auth_seq,AUTH_TYPE.REMOTE);
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
			throw new DataErrorException().addScene("INPUT", "输入列表信息");
		}
		for (String s : list) {
			str += "'" + s + "',";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return str;
	}
	
	/** 根据服务和授权类型查询总数
	 * @param org_srv_name
	 * @param local
	 * @return 
	 */
	public int countBySrvAndType(String dept_id, String org_srv_name, AUTH_TYPE type) {
		return dao.countBySrvAndType(dept_id, org_srv_name,type);
	}

	/** 
	 * 查询第一个本地授权角色
	 * @param org_srv_name
	 * @return 
	 */
	public SvSrvAuthInfo queryLocalAuthRole(String dept_id, String org_srv_name,int auth_seq) {
		int min_seq=dao.queryMinAuthSeq(dept_id, org_srv_name, auth_seq,AUTH_TYPE.LOCAL);
		SvSrvAuthInfo authInfo=new SvSrvAuthInfo();
		authInfo.setAuth_dept_id(dept_id);
		authInfo.setSrv_name(org_srv_name);
		authInfo.setAuth_seq(min_seq);
		authInfo=getInfoByKey(authInfo);		
		return authInfo;
	}
}