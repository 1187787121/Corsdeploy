/**
 * Title: SystemAppbean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.common.util.Assert;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author Xul
 */
public class SystemAppbean extends CeSystemInfo{

	private static final long serialVersionUID = -7432251184846829965L;
	
	/**
	 * 创建时间
	 */
	private JaDateTime createDateTime;
	
	public static final String CREATEDATETIMECN = "创建时间";
	
	/**
	 * 修改时间
	 */
	private JaDateTime modifyDateTime;
	
	public static final String MODIFYDATETIMECN = "修改时间";
	
	/**
	 * 创建人中文名
	 */
	private String crt_user_cname;
	
	public static final String CRT_USER_CNAMECN = "创建人中文名";
	
	/**
	 * 修改人中文名
	 */
	private String mod_user_cname;
	
	public static final String MOD_USER_CNAMECN = "修改人中文名";
	
	/**
	 * 配置文件列表
	 */
	private String[] config_list;
	
	public static final String CONFIG_LISTCN = "配置文件列表";
	
	/**
	 * 模板列表
	 */
	private List<TpAppBean> tp_list;
	
	public static final String TP_LISTCN = "模板列表";
	
	/**
	 * 服务器列表
	 */
	private List<CeServerInfo> server_list;
	
	public static final String SERVER_LISTCN = "服务器列表";

	/**
	 * @return createDateTime 创建时间
	 */
	public JaDateTime getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime 创建时间
	 */
	public void setCreateDateTime(JaDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return modifyDateTime 修改时间
	 */
	public JaDateTime getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime 修改时间
	 */
	public void setModifyDateTime(JaDateTime modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	
	/**
	 * @return crt_user_cname 创建人中文名
	 */
	public String getCrt_user_cname() {
		return crt_user_cname;
	}

	/**
	 * @param crt_user_cname 创建人中文名
	 */
	public void setCrt_user_cname(String crt_user_cname) {
		this.crt_user_cname = crt_user_cname;
	}

	/**
	 * @return mod_user_cname 修改人中文名
	 */
	public String getMod_user_cname() {
		return mod_user_cname;
	}

	/**
	 * @param mod_user_cname 修改人中文名
	 */
	public void setMod_user_cname(String mod_user_cname) {
		this.mod_user_cname = mod_user_cname;
	}

	/**
	 * @return config_list 配置文件列表
	 */
	public String[] getConfig_list() {
		return config_list;
	}

	/**
	 * @param config_list 配置文件列表
	 */
	public void setConfig_list(String[] config_list) {
		this.config_list = config_list;
	}

	/**
	 * @return tp_list 模板列表
	 */
	public List<TpAppBean> getTp_list() {
		return tp_list;
	}

	/**
	 * @param tp_list 模板列表
	 */
	public void setTp_list(List<TpAppBean> tp_list) {
		this.tp_list = tp_list;
	}
	
	/**
	 * @return server_list 服务器列表
	 */
	public List<CeServerInfo> getServer_list() {
		return server_list;
	}

	/**
	 * @param server_list 服务器列表
	 */
	public void setServer_list(List<CeServerInfo> server_list) {
		this.server_list = server_list;
	}

	/** 
	 * Description: 
	 * @param info
	 * @return 
	 */
	public static SystemAppbean copy(CeSystemInfo info) {
		SystemAppbean bean = new SystemAppbean();
		bean.setSys_name(info.getSys_name());
		bean.setSys_cn_name(info.getSys_cn_name());
		bean.setSys_type(info.getSys_type());
		bean.setSys_bk_desc(info.getSys_bk_desc());
		bean.setCreateDateTime(getJaDateTime(info.getCreate_bk_date(), info.getCreate_bk_time()));
		bean.setCreate_user_id(info.getCreate_user_id());
		bean.setModifyDateTime(getJaDateTime(info.getModify_bk_date(), info.getModify_bk_time()));
		bean.setModify_user_id(info.getModify_user_id());
		return bean;
	}
	
	//获取JaDateTime
	private static JaDateTime getJaDateTime(JaDate d, JaTime t){
		if(Assert.isEmpty(d) || Assert.isEmpty(t)){
			return null;
		}
		JaDateTime dt = JaDateTime.valueOf(d.getYear(),d.getMonth(),d.getDay(),t.getHour(),t.getMinute(),t.getSecond(),t.getMillisecond());
		return dt;
	}
}
