/**
 * Title: RsResDaoService.java
 * File Description: 资源配置表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.rs.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.exc.RecordStateAbnormalException;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:资源配置表
 * @author AutoGen
 */
public class RsResDaoService {
	@Inject
	private RsResDao dao;

	/**
	 * Description: 根据主键查询一条记录
	 * @param RsResInfo info
	 * @return RsResInfo
	 */
	public RsResInfo getInfoByKey(RsResInfo info) {
		RsResInfo dao_info = dao.get(info);
		if (Assert.isEmpty(dao_info)) {// 数据不存在，抛异常
			throw new RecordNotFoundException().addScene("TABLE",
					RsResInfo.TABLECN).addScene("FIELD", info.getRs_code());
		}
		return dao_info;
	}

	/**
	 * Description: 根据部门名称模糊分页查询部门信息
	 * @param rs_cn_name
	 * @return
	 */
	public List<RsResInfo> queryInfosByName(String rs_cn_name) {
		return dao.queryInfosByName(rs_cn_name);
	}

	/**
	 * Description: 根据部门名称模糊分页查询部门信息之总条数
	 * @param rs_cn_name
	 * @return
	 */
	public int countInfosByNameAllRecd(String rs_cn_name) {
		return dao.countInfosByNameAllRecd(rs_cn_name);
	}

	/**
	 * 
	 * Description: 根据资源编码查询资源信息
	 * @param rs_code
	 * @return
	 */
	public RsResInfo getInfoByCode(String rs_code) {
		RsResInfo info_dao = dao.get(rs_code);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					RsResInfo.TABLECN).addScene("FIELD", rs_code);
		}
		RCD_STATE state = info_dao.getRcd_state();
		if (state == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException().addScene("TABLE",
					RsResInfo.TABLECN).addScene("FIELD", rs_code);
		}
		return info_dao;
	}

	/**
	 * Description: 根据资源编码字符串查询资源信息
	 * @param resInfo
	 */
	public DBIterator<RsResInfo> queryInfoByCodeString(
			DBIterator<String> rs_iterator) {
		String rs_str = getStringByDBIterator(rs_iterator);
		return dao.iteratorInfoByCodeString(rs_str);
	}

	/**
	 * Description: 根据资源编码字符串查询资源信息
	 * @param resInfo
	 */
	public List<RsResInfo> queryInfoByCodeString(List<String> rs_list) {
		String rs_str = getStringByList(rs_list);
		return dao.queryInfoByCodeString(rs_str);
	}

	/**
	 * Description: 根据主键查询一条记录并对记录加锁
	 * @param RsResInfo info
	 * @return RsResInfo
	 */
	public RsResInfo getInfoByKeyForUpdate(RsResInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 
	 * Description: 查询所有资源信息
	 * @return List<RsResInfo>
	 */
	public List<RsResInfo> pageInfosByNull(int start_rcd, int limit_rcd) {
		return dao.pageResInfosByNull(start_rcd, limit_rcd);
	}

	/**
	 * 
	 * Description: 查询所有资源信息之总条数
	 * @return int
	 */
	public int countInfosByNullAllRecd() {
		return dao.countResInfosByNullAllRecd();
	}

	/**
	 * Description: 插入一条记录
	 * @param RsResInfo info
	 * @return int
	 */
	public int insertInfo(RsResInfo info) {
		return dao.insert(info);
	}

	/**
	 * Description: 插入多条记录
	 * @param List<RsResInfo>
	 * @return int
	 */
	public int insertListInfo(List<RsResInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 
	 * Description: 根据资源编码查询资源状态信息
	 * @param rs_code
	 * @return
	 */
	public void getInfoStateByCode(String rs_code) {
		RsResInfo info_dao = dao.get(rs_code);
		if (Assert.isEmpty(info_dao)) {
			throw new RecordNotFoundException().addScene("TABLE",
					RsResInfo.TABLECN).addScene("FIELD", rs_code);
		}
		RCD_STATE state = info_dao.getRcd_state();
		if (state == RCD_STATE.ABNORMAL) {
			throw new RecordStateAbnormalException().addScene("TABLE",
					RsResInfo.TABLECN).addScene("FIELD", rs_code);
		}
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
	 * 将输入的List转换为字符串
	 * @param list
	 * @return
	 */
	private String getStringByList(List<String> list) {
		String str = "";
		if (!list.isEmpty()) {
			for (String s : list) {
				str = str + s + "','";
			}
			if (!str.isEmpty()) {
				str = "('" + str.substring(0, str.length() - 2) + ")";
			} else {
				str = "('')";
			}
		}
		return str;
	}

	/**
	 * 无条件查询所有的资源
	 * @return 服务信息
	 */
	public List<RsResInfo> listRsInfos() {
		List<RsResInfo> rs_infos=new ArrayList<RsResInfo>();
		DBIterator<RsResInfo> iterator =dao.iteratorAllRes();
		try {
			while (iterator.hasNext()) {
				rs_infos.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return rs_infos;
	}
	
	/**
	 * 查询所有资源权限信息列表
	 * @return 
	 */
	public List<RsPrivBean> queryAllUnPublicRsPriv() {
		List<RsPrivBean> rs_list = new ArrayList<RsPrivBean>();
		DBIterator<RsPrivBean> rs_iterator=dao.iteratorAllUnPublicRsPriv();
		try {
			while (rs_iterator.hasNext()) {
				rs_list.add(rs_iterator.next());
			}
		} finally {
			rs_iterator.close();
		}
		return rs_list;
	}

	/** 
	 * Description: 获取一级导航
	 * @return 
	 */
	public List<RsResInfo> getFirstNavigate() {
		return dao.getFirstNavigate();
	}
	
	/** 
	 * Description: 获取一级导航下所有资源
	 * @return 
	 */
	public List<RsResInfo> getRsListByBlRsCode(String bl_rs_code) {
		return dao.getRsListByBlRsCode(bl_rs_code);
	}

	/** 
	 * Description: 获取资源URL对照列表
	 * @return 
	 */
	public List<RsUrlBean> getRsUrlList(String bl_rs_code) {
		return dao.getRsUrlList(bl_rs_code);
	}

	/** 
	 * Description: 检查rs_res表中是否存在rs_code记录，若不存在抛“不存在记录”异常。
	 * @param rs_code 
	 */
	public void checkRsExist(String rs_code) {
		if (dao.countByRsCode(rs_code) == 0) {
			throw new RecordNotFoundException().addScene("TABLE", RsResInfo.TABLECN)
					.addScene("FIELD", rs_code);
		}
	}

	/** 
	 * Description: 修改INfo
	 * @param rsInfo 
	 */
	public void updateInfo(RsResInfo rsInfo) {
		dao.update(rsInfo);
	}

	/** 
	 * Description: 查询bl_rs_code下所有公共资源
	 * @param bl_rs_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorPublicRs(String bl_rs_code) {
		return dao.iteratorPublicRs(bl_rs_code);
	}

	/** 
	 * Description: 增加模块描述（公开资源：方便页面显示）
	 * @param sup_rs_list
	 * @return 
	 */
	public List<RsPrivBean> addModularRs(List<RsPrivBean> sup_rs_list) {
		List<RsResInfo> rs_list= dao.getModular();
		if(!Assert.isEmpty(rs_list)){
			for(RsResInfo rsInfo:rs_list){
				RsPrivBean privBean=new RsPrivBean();
				privBean.setRs_code(rsInfo.getRs_code());
				privBean.setSuper_rs_code(rsInfo.getSuper_rs_code());
				privBean.setRs_cn_name(rsInfo.getRs_cn_name());
				privBean.setRs_fun_type(rsInfo.getRs_fun_type());
				privBean.setRs_level(rsInfo.getRs_level());
				privBean.setOpen_type(rsInfo.getOpen_type());
				sup_rs_list.add(privBean);
			}
		}
		return sup_rs_list;
	}
}