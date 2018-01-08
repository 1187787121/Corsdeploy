/**
 * Title: SvSrvDaoService.java
 * File Description: 服务配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.SALLOW_FLAG;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.system.ap.bean.ApproveServiceBean;
import com.wk.cd.system.exc.ServiceMustLocalExecuteException;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.db.DBIterator;
import com.wk.db.NewTransaction;
import com.wk.lang.Inject;

/**
 * Class description:服务配置表
 * @author AutoGen
 */
public class SvSrvDaoService {
	@Inject
	private SvSrvDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param SvSrvInfo info
	 * @return SvSrvInfo
	 */
	public SvSrvInfo getInfoByKey(SvSrvInfo info) {
		SvSrvInfo bean = dao.get(info);
		if(bean == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvInfo.TABLECN).addScene("FIELD", info.getSrv_name());
		}else if(bean.getRcd_state() == RCD_STATE.ABNORMAL){
			throw new RecordStateAbnormalException()
				.addScene("TABLE",SvSrvInfo.TABLECN).addScene("FIELD",info.getSrv_name());
		}
		return bean;
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param SvSrvInfo info
	 * @return SvSrvInfo
	 */
	public SvSrvInfo getInfoByKeyForUpdate(SvSrvInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param SvSrvInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<SvSrvInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据主键查询一条状态为正常的记录
	 * @param SvSrvInfo info
	 * @return SvSrvInfo
	 */
	public SvSrvInfo getInfoByName(SvSrvInfo info) {
		SvSrvInfo ninfo = dao.getInfoByName(info.getSrv_name());
		if (Assert.isEmpty(ninfo)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvInfo.TABLECN).addScene("FIELD", info.getSrv_name());
		}
		return ninfo;
	}

	/**
	 * 分页查询服务定义表中所有状态为正常的记录
	 * @param user_srv_list 用户服务列表
	 * @param srv_fun_type 服务类型
	 * @param offset 起始条数
	 * @param limit 查询条数
	 * @return List<SvSrvInfo>
	 */
	public List<SvSrvInfo> pageAllSvSrv(List<String> user_srv_list,
			List<FUN_TYPE> srv_type_list, int offset, int limit) {
		String user_srv_str = "";
		String srv_type_str = "";
		if (Assert.isEmpty(user_srv_list)) {
			throw new DataErrorException().addScene("INPUT", "用户服务列表");
		}
		// 生成用户服务字符串
		for (String user_srv : user_srv_list) {
			user_srv_str += "'" + user_srv + "',";
		}
		user_srv_str = "("
				+ user_srv_str.substring(0, user_srv_str.length() - 1) + ")";
		// 生成服务类型字符串
		if (srv_type_list != null && srv_type_list.size() > 0) {
			for (FUN_TYPE type : srv_type_list) {
				srv_type_str += "'" + type + "',";
			}
			srv_type_str = "("
					+ srv_type_str.substring(0, srv_type_str.length() - 1)
					+ ")";
		}
		return dao.pageAllSvSrv(user_srv_str, srv_type_str, offset, limit);
	}

	/**
	 * 分页查询服务定义表中所有状态为正常的记录
	 * @param user_srv_list 用户服务列表
	 * @param srv_fun_type 服务类型
	 * @return 总条数
	 */
	public int countAllSvSrv(List<String> user_srv_list,
			List<FUN_TYPE> srv_type_list) {
		String user_srv_str = "";
		String srv_type_str = "";
		if (Assert.isEmpty(user_srv_list)) {
			throw new DataErrorException().addScene("INPUT", "用户服务列表");
		}
		// 生成用户服务字符串
		for (String user_srv : user_srv_list) {
			user_srv_str += "'" + user_srv + "',";
		}
		user_srv_str = "("
				+ user_srv_str.substring(0, user_srv_str.length() - 1) + ")";
		// 生成服务类型字符串
		if (srv_type_list != null && srv_type_list.size() > 0) {
			for (FUN_TYPE type : srv_type_list) {
				srv_type_str += "'" + type + "',";
			}
			srv_type_str = "("
					+ srv_type_str.substring(0, srv_type_str.length() - 1)
					+ ")";
		}
		return dao.countAllSvSrv(user_srv_str, srv_type_str);
	}

	/**
	 * 根据服务名称删除一组服务信息（修改记录状态为2）
	 * @param srv_name 服务名称
	 * @return int 删除条数
	 */
	public int lgDeleteInfo(String srv_name) {
		return dao.updateRcdStateInfo(RCD_STATE.ABNORMAL, srv_name);
	}

	/**
	 * 根据服务名称查询记录条数
	 * @param srv_name 服务名称
	 * @return int 记录条数
	 */
	public int countInfo(String srv_name) {
		return dao.countInfo(srv_name);
	}

	/**
	 * 检查服务是否存在
	 * @param srv_name 服务名称
	 * @return int 记录条数
	 */
	public void checkServiceExist(String srv_name) {
		if (dao.countInfo(srv_name) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvInfo.TABLECN).addScene("FIELD", srv_name);
		}
	}

	/**
	 * 根据服务名称修改服务相关信息
	 * @param SvSrvInfo info
	 * @return int 记录条数
	 */
	public int updateInfo(SvSrvInfo info) {
		return dao.updateInfo(info);
	}

	/**
	 * @param srvName
	 * @param sallowFlag
	 */
	public void checkSallowFlag(String srv_name, SALLOW_FLAG sallow_flag) {
		SvSrvInfo info = new SvSrvInfo();
		info.setSrv_name(srv_name);
		info = getInfoByName(info);
		// 服务允许标志是时不控制，直接退出
		if (sallow_flag != SALLOW_FLAG.ALL) {
			return;
		}
		// 服务允许标志是本地执行但是作为远程执行使用报错
		if (sallow_flag == SALLOW_FLAG.ALWREMOTE
				&& info.getSallow_flag() == SALLOW_FLAG.ALWLOCAL) {
			throw new ServiceMustLocalExecuteException();
		}
		// 服务允许标志是远程执行但是作为本地执行使用报错
		if (sallow_flag == SALLOW_FLAG.ALWLOCAL
				&& info.getSallow_flag() == SALLOW_FLAG.ALWREMOTE) {
			throw new ServiceMustLocalExecuteException();
		}
	}

	/**
	 * 根据传入的服务名称信息dbiterator查询服务信息
	 * @param srv_name_iterator 传入的服务名称信息
	 * @return
	 */
	public DBIterator<SvSrvInfo> iteratorSrvBySrvNames(
			DBIterator<String> srv_name_iterator) {
		String srv_name_str = getStringByDBIterator(srv_name_iterator);
		return dao.iteratorSrvBySrvNames(srv_name_str);
	}

	/**
	 * 无条件查询所有的服务
	 * @return 服务信息
	 */
	public DBIterator<SvSrvInfo> iteratorAllSrv() {
		return dao.iteratorAllSrv();
	}

	/**
	 * 将DBIterator转换为字符串
	 * @param dbiterator
	 * @return 字符串
	 */
	private String getStringByDBIterator(DBIterator<String> dbiterator) {
		String str = "";
		try {
			while (dbiterator.hasNext()) {
				str = str + dbiterator.next() + "','";
			}
		} finally {
			dbiterator.close();
		}
		if (!str.isEmpty()) {
			str = "('" + str.substring(0, str.length() - 2) + ")";
		} else {
			str = "('')";
		}
		return str;
	}

	/** 
	 * 查询所有本地授权服务数组
	 * @return 
	 */
	public List<String> queryLocalAuthSrv() {
		List<String> srv_list=new ArrayList<String>(); 
		DBIterator<String> srv_iterator=dao.queryLocalAuthSrv();
		try {
			while (srv_iterator.hasNext()) {
				String srv_name = srv_iterator.next();
				srv_list.add(srv_name);
			}
		} finally {
			srv_iterator.close();
		}
		return srv_list;
	}
	
	/**
	 * Description: 根据用户名、服务类型分页查询用户拥有的服务权限
	 * @param srv_fun_type1 服务类型
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return
	 */
	public List<SvSrvInfo> pageSrvByFunType(FUN_TYPE srv_fun_type, int start_recd, int limit_recd) {
		return dao.pageSrvByFunType(srv_fun_type,start_recd, limit_recd);
	}

	/**
	 * Description: 根据服务类型分页查询服务
	 * @param srv_fun_type 服务类型
	 * @return
	 */
	public int countSrvByFunType(FUN_TYPE srv_fun_type) {
		return dao.countSrvByFunType(srv_fun_type);
	}

	/** 
	 * Description: 查询服务组包含服务列表
	 * @param sup_srvg_code
	 * @return 
	 */
	public List<SvSrvInfo> listSubSrvBySrvgCode(String sup_srvg_code) {
		List<SvSrvInfo> srv_list=new ArrayList<SvSrvInfo>();
		DBIterator<SvSrvInfo> iterator= dao.listSubSrvBySrvgCode(sup_srvg_code);
		try {
			while (iterator.hasNext()) {
				srv_list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		
		return srv_list;
	}

	/** 
	 * Description: 无条件查询所有的服务
	 * @return 
	 */
	public List<SvSrvInfo> listAllSrv() {
		List<SvSrvInfo> srv_list=new ArrayList<SvSrvInfo>();
		DBIterator<SvSrvInfo> iterator= dao.iteratorAllSrv();
		try {
			while (iterator.hasNext()) {
				srv_list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		
		return srv_list;
	}
	
	/**
	 * Description: 查询所有可复核和可授权的服务
	 * @return
	 */
	public List<ApproveServiceBean> queryCanApproveSrv(){
		List<ApproveServiceBean> app_list=new ArrayList<ApproveServiceBean>();
		DBIterator<ApproveServiceBean> iterator =dao.queryCanApproveSrv();
		try {
			while (iterator.hasNext()) {
				app_list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return app_list;
	}

	@NewTransaction
	public int updateClassNameByKey(String class_name, String srv_name){
		return dao.updateClassNameByKey(class_name,srv_name);
	}

	/** 
	 * Description: 
	 * @param key
	 * @return 
	 */
	public List<ApproveServiceBean> querySrvNameList(String key) {
		List<ApproveServiceBean> app_list=new ArrayList<ApproveServiceBean>();
		DBIterator<ApproveServiceBean> iterator =dao.queryCanApproveSrvByKey(key);
		try {
			while (iterator.hasNext()) {
				app_list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return app_list;
	}
}