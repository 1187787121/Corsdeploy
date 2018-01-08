/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.system.dt.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.bean.ProtocolSocBean;
import com.wk.cd.system.dt.bean.ViewDtInputBean;
import com.wk.cd.system.dt.bean.ViewDtOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.lang.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Description:
 * @author Administrator
 */
public class ViewDtAction extends
		IViewActionBasic<ViewDtInputBean, ViewDtOutputBean> {

	@Inject
	private DtCheckSocExistService dtCheckSocExistService;

	@Inject
	private DtSourceDaoService dtsourcedaoservice;

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	public ViewDtOutputBean queryListSocName(ViewDtInputBean input) {
		ViewDtOutputBean output = new ViewDtOutputBean();
		output.setLst_soc_name(this.dtsourcedaoservice.queryAllSocName());
		return output;
	}

	/**
	 * Description: 根据ip地址查询所有相关数据源的名称
	 * @param input
	 * @return
	 */
	public ViewDtOutputBean queryListSocNameByIp(ViewDtInputBean input) {
		ViewDtOutputBean output = new ViewDtOutputBean();
		List<DtSourceInfo> source_info_list = dtsourcedaoservice.querySocByIp(input
				.getSoc_ip());
		List<String> lst_soc_name = new ArrayList<String>();
		if (!Assert.isEmpty(source_info_list) && source_info_list.size() != 0) {
			for (DtSourceInfo info : source_info_list) {
				lst_soc_name.add(info.getSoc_name());
			}
		}
		output.setLst_soc_name(lst_soc_name);
		return output;
	}

	/**
	 * Description: 查询连接方式为PLT_FTP的数据源
	 * @param input
	 * @return
	 */
	public ViewDtOutputBean querySocByIpByProType(ViewDtInputBean input) {
		ViewDtOutputBean output = new ViewDtOutputBean();
		List<DtSourceInfo> source_info_list = dtsourcedaoservice.querySocByProType(PROTOCOL_TYPE.PLT_FTP);
		source_info_list.addAll(this.dtsourcedaoservice
				.querySocByProType(PROTOCOL_TYPE.SFTP));
		List<String> lst_soc_name = new ArrayList<String>();
		if (!(Assert.isEmpty(source_info_list))) {
			for (DtSourceInfo info : source_info_list) {
				lst_soc_name.add(info.getSoc_name());
			}
		}
		output.setLst_soc_name(lst_soc_name);
		return output;
	}

	/**
	 * Description: 根据IP获取所有的协议类型及相应数据源列表
	 * @param input
	 * @return
	 */
	public ViewDtOutputBean queryProtocolAndSocNameByIP(ViewDtInputBean input) {
		ViewDtOutputBean output = new ViewDtOutputBean();
		String soc_ip = input.getSoc_ip();
		Assert.assertNotEmpty(soc_ip, "IP地址");
		this.dtCheckSocExistService.checkSocIPExist(soc_ip);
		List<DtSourceInfo> soc_info_list = dtsourcedaoservice.querySocByIp(soc_ip);
		HashMap<PROTOCOL_TYPE, List<String>> soc_map = new HashMap<PROTOCOL_TYPE, List<String>>();
		if (!(Assert.isEmpty(soc_info_list))) {
			for (DtSourceInfo info : soc_info_list) {
				PROTOCOL_TYPE protocol_type = info.getProtocol_type();
				if (soc_map.containsKey(protocol_type)) {
					soc_map.get(protocol_type).add(info.getSoc_name());
				} else {
					List<String> soc_name_list = new ArrayList<String>();
					soc_name_list.add(info.getSoc_name());
					soc_map.put(protocol_type, soc_name_list);
				}
			}
		}
		List<ProtocolSocBean> protocol_soc_list = new ArrayList<ProtocolSocBean>();
		if (!Assert.isEmpty(soc_map.keySet())) {
			for (PROTOCOL_TYPE protocol_type : soc_map.keySet()) {
				ProtocolSocBean protocol_soc_bean = new ProtocolSocBean();
				protocol_soc_bean.setProtocol_type(protocol_type);
				protocol_soc_bean.setSoc_name_list(soc_map.get(protocol_type));
				protocol_soc_list.add(protocol_soc_bean);
			}
		}
		output.setProtocol_soc_list(protocol_soc_list);
		return output;
	}
	
	/**
	 * Description: 查看所有存在的IP并且去重
	 * @param input
	 * @return
	 */
	public ViewDtOutputBean queryDistinctIp(ViewDtInputBean input){
		ViewDtOutputBean output = new ViewDtOutputBean();
		List<String> ip_list = dtsourcedaoservice.queryDistinctIp();
		output.setIp_list(ip_list);
		return output;
	}
	
	
}