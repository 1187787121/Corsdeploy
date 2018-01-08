package com.wk.cd.system.dt.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.dp.bean.SocPrivBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

public class DtSourceDaoService {

	@Inject
	private DtSourceDao dao;

	@Inject
	private DtCheckSocExistService daoCheckService;

	public DtSourceInfo getInfoByKey(DtSourceInfo info) {
		return dao.get(info);
	}

	public DtSourceInfo getInfoByKeyForUpdate(DtSourceInfo info) {
		DtSourceInfo bean = dao.getForUpdate(info);
		if ((bean == null) || (bean.getRcd_state() == RCD_STATE.ABNORMAL)) {
			throw new RecordNotFoundException().addScene("TABLE", "数据源信息表")
					.addScene("FIELD", info.getSoc_name());
		}
		return bean;
	}

	public int insertInfo(DtSourceInfo info) {
		return this.dao.insert(info);
	}

	public int insertListInfo(List<DtSourceInfo> infos) {
		return this.dao.insert(infos);
	}

	public int countBySocName(String soc_name) {
		return this.dao.countBySocName(soc_name);
	}

	public int countBySocIp(String soc_ip) {
		return this.dao.countBySocIp(soc_ip);
	}

	public DtSourceInfo querySocDetailBySocName(String soc_name) {
		this.daoCheckService.checkSocExist(soc_name);
		return this.dao.querySocDetailBySocName(soc_name);
	}

	public void updateBySocName(DtSourceInfo info) {
		this.dao.update(info);
	}

	public void deleteBySocName(String soc_name) {
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(soc_name);
		this.dao.delete(info);
	}

	public List<DtSourceInfo> pageAllSocName(String soc_name, int start_recd,
			int limited_recd) {
		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();
		soc_list = this.dao.pageAllSocName(soc_name, start_recd, limited_recd);
		return soc_list;
	}

	public int countAllSocName(String soc_name) {
		return this.dao.countAllSocName(soc_name);
	}

	public int countAllSocName() {
		return this.dao.countAllSocName();
	}

	public List<DtSourceInfo> pageBySocName(String soc_name, int start_rcd,
			int limited_rcd) {
		List<DtSourceInfo> soc_list = new ArrayList<DtSourceInfo>();
		soc_list = this.dao.pageBySocName(soc_name, start_rcd, limited_rcd);
		return soc_list;
	}

	public int countByLikeSocName(String soc_name) {
		return this.dao.countByLikeSocName(soc_name);
	}

	public List<DtSourceInfo> querySocInfoList(List<String> soc_name_list) {
		String soc_arr = "";
		if (!(Assert.isEmpty(soc_name_list))) {
			soc_arr = "('";
			for (String soc_name : soc_name_list) {
				soc_arr = soc_arr + soc_name + "','";
			}
			soc_arr = soc_arr.substring(0, soc_arr.length() - 2);
			soc_arr = soc_arr + ")";
		} else {
			soc_arr = "('')";
		}
		return this.dao.querySocInfoList(soc_arr);
	}

	public List<DtSourceInfo> queryAllSoc() {
		return this.dao.queryAllSoc();
	}

	public DBIterator<DtSourceInfo> iteratorAllSoc() {
		return this.dao.iteratorAllSoc();
	}

	public List<SocPrivBean> queryAllSocPriv() {
		List<SocPrivBean> soc_list = new ArrayList<SocPrivBean>();
		DBIterator<SocPrivBean> soc_iterator = dao.iteratorAllSocPriv();
		try {
			while (soc_iterator.hasNext())
				soc_list.add(soc_iterator.next());
		} finally {
			soc_iterator.close();
		}
		return soc_list;
	}

	public List<String> queryAllSocName() {
		return this.dao.queryAllSocName();
	}

	public List<DtSourceInfo> querySocByIp(String ip) {
		return this.dao.querySocByIp(ip);
	}

	public List<DtSourceInfo> querySocByProType(PROTOCOL_TYPE protocol_type) {
		return this.dao.querySocByProType(protocol_type);
	}

	public List<DtSourceInfo> querySocByIpAndProType(
			PROTOCOL_TYPE protocol_type, String soc_ip) {
		return this.dao.querySocByIpAndProType(protocol_type, soc_ip);
	}

	public PROTOCOL_TYPE getProtocolTypeByName(String soc_name) {
		return (PROTOCOL_TYPE.valueOf(PROTOCOL_TYPE.class,dao.getProtocolTypeByName(soc_name)));
	}

	public String getUserRootPath(String soc_name) {
		return this.dao.getUserRootPath(soc_name);
	}
	
	/**
	 * Description: 查看所有存在的IP并且去重
	 * @return
	 */
	public List<String> queryDistinctIp(){
		return dao.queryDistinctIp();
	}
}