/**
 * Title: ModuleSourceInfo.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017年1月6日
 */
package com.wk.cd.module1.info;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.sdo.ServiceData;
import com.wk.util.JSON;
import com.wk.util.JSONCaseType;

/**
 * Class Description:
 * @author zhangj
 */
public class ModuleSourceInfo
		implements XmlEntity {
	/**
	 * 执行协议类型
	 */
	private PROTOCOL_TYPE protocol_type;

	public static final String PROTOCOL_TYPECN = "执行协议类型";
	
	/**
	 * 执行服务器名
	 */
	private String exe_server_name;
	
	public static final String EXE_SERVER_NAMECN = "执行服务器名";
	
	/**
	 * 版本服务器名
	 */
	private String ver_server_name;
	
	public static final String VER_SERVER_NAMECN = "版本服务器名";
	
	/**
	 * 执行数据源名
	 */
	private String exe_soc_name;
	
	public static final String EXE_SOC_NAMECN = "执行数据源名";
	
	/**
	 * 版本数据源名
	 */
	private String ver_soc_name;
	
	public static final String VER_SOC_NAMECN = "版本数据源名";
	
	/**
	 * 参数（现在主要是存放svn的参数）
	 */
	ServiceData data;

	public static final String DATACN = "参数";

	/**
	 * 具体执行使用数据源
	 */
	private DtSourceInfo dt_source_info;

	public static final String DT_SOURCE_INFOCN = "具体执行使用数据源";

	@Override
	public void fromElement(Element element) {
		protocol_type = XmlAdapter.getAttribute(element, XmlTags.PROTOCOL_TYPE, PROTOCOL_TYPE.class, true);
		exe_soc_name = element.getAttribute(XmlTags.EXE_SOC_NAME);
		ver_soc_name = element.getAttribute(XmlTags.VER_SOC_NAME);
		exe_server_name = element.getAttribute(XmlTags.EXE_SERVER_NAME);
		ver_server_name = element.getAttribute(XmlTags.VER_SERVER_NAME);
		data = JSON.toServiceData(XmlAdapter.getElementContent(element, XmlTags.DATA, false));
		dt_source_info = XmlAdapter.getElementEntity(element, XmlTags.DT_SOURCE, DtSourceInfo.class, true);
	}

	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.MODULE_SOURCE;
		}
		Element module_source_ele = document.createElement(tag);
		Element service_data = document.createElement(XmlTags.DATA);
		service_data.setTextContent(JSON.fromServiceData(data, JSONCaseType.DEFAULT));
		module_source_ele.appendChild(service_data);
		XmlAdapter.setAttribute(module_source_ele, XmlTags.PROTOCOL_TYPE, protocol_type, true);
		module_source_ele.setAttribute(XmlTags.EXE_SOC_NAME, exe_soc_name);
		module_source_ele.setAttribute(XmlTags.VER_SOC_NAME, ver_soc_name);
		module_source_ele.setAttribute(XmlTags.EXE_SERVER_NAME, exe_server_name);
		module_source_ele.setAttribute(XmlTags.VER_SERVER_NAME, ver_server_name);
		module_source_ele.appendChild(dt_source_info.toElement(document, XmlTags.DT_SOURCE));
		return module_source_ele;
	}

	public ModuleSourceInfo() {

	}

	/**
	 * 构造函数
	 * @param protocol_type
	 * @param dt_source_info
	 */
	public ModuleSourceInfo(PROTOCOL_TYPE protocol_type, DtSourceInfo dt_source_info) {
		super();
		this.protocol_type = protocol_type;
		this.dt_source_info = DtSourceInfo.copy(dt_source_info);
	}

	public ModuleSourceInfo(DtSourceInfo dt_source_info, DtSourceInfo ver_source_info) {
		super();
		this.dt_source_info = dt_source_info;
		PROTOCOL_TYPE type = ver_source_info.getProtocol_type();
		if (PROTOCOL_TYPE.SVN.equals(type)) {
			this.protocol_type = PROTOCOL_TYPE.SVN;
			ServiceData data = new ServiceData();
			StringBuilder url = new StringBuilder();
			url.append(ver_source_info.getBackup_fld()).append("://").append(ver_source_info.getSoc_ip()).append(':').append(ver_source_info.getSoc_port());
			//url.append(ver_source_info.getBackup_fld()).append("://").append(ver_source_info.getSoc_ip()).append(ver_source_info.getUser_root_path());
			data.putString("url", url.toString());
			data.putString("user", ver_source_info.getRemote_uname());
			data.putString("password", ver_source_info.getRemote_passwd());
			data.putString("passed_key", ver_source_info.getKey_remote_passwd());
			data.putString("ecode", ver_source_info.getEncoding_type());
			this.data = data;
		} else if (PROTOCOL_TYPE.WAS.equals(type)) {
			this.protocol_type = PROTOCOL_TYPE.WAS;
			ServiceData data = new ServiceData();
			data.putString("url", ver_source_info.getSoc_ip());
			data.putString("user", ver_source_info.getRemote_uname());
			data.putString("password", ver_source_info.getRemote_passwd());
			data.putInt("port", ver_source_info.getSoc_port());
//			System.out.println("@@@" + ver_source_info.getEnvironment_variables());
			data.putString("env", ver_source_info.getEnvironment_variables());
			data.putString("key_remote_passwd", ver_source_info.getKey_remote_passwd());
			data.putString("ecode", ver_source_info.getEncoding_type());
			// data.putString("env",
			// ver_source_info.getEnvironment_variables());
			this.data = data;
		} else if (PROTOCOL_TYPE.WEBLOGIC.equals(type)) {
			this.protocol_type = PROTOCOL_TYPE.WEBLOGIC;
			ServiceData data = new ServiceData();
			data.putString("url", "t3://" + ver_source_info.getSoc_ip() + ":" + ver_source_info.getSoc_port());
			data.putString("user", ver_source_info.getRemote_uname());
			data.putString("password", ver_source_info.getRemote_passwd());
			data.putInt("port", ver_source_info.getSoc_port());
			data.putString("ecode", ver_source_info.getEncoding_type());
			// data.putString("env",
			// ver_source_info.getEnvironment_variables());
			this.data = data;
		} else {
			// throw new GenerationFailedException().addScene("REASON",
			// "协议类型"+type+"不支持作为VER数据源");
		}

	}

	public ServiceData getData() {
		return data;
	}

	public void setData(ServiceData data) {
		this.data = data;
	}

	public PROTOCOL_TYPE getProtocol_type() {
		return protocol_type;
	}

	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}
	
	public String getExe_server_name() {
		return exe_server_name;
	}

	public void setExe_server_name(String exe_server_name) {
		this.exe_server_name = exe_server_name;
	}

	public String getVer_server_name() {
		return ver_server_name;
	}

	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	public String getExe_soc_name() {
		return exe_soc_name;
	}

	public void setExe_soc_name(String exe_soc_name) {
		this.exe_soc_name = exe_soc_name;
	}

	public String getVer_soc_name() {
		return ver_soc_name;
	}

	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	public DtSourceInfo getDt_source_info() {
		return dt_source_info;
	}

	public void setDt_source_info(DtSourceInfo dt_source_info) {
		this.dt_source_info = dt_source_info;
	}

	public static String toJsonString(ModuleSourceInfo info) {
		String json = JSON.fromBean(info, JSONCaseType.DEFAULT);
		return json;
	}

	public static ModuleSourceInfo getInfoFromJson(String json) {

		ServiceData sd = JSON.toServiceData(json);
		ServiceData sd_source = sd.getServiceData("dt_source_info");
		int pro = sd.getInt("protocol_type");
		ServiceData data = sd.getServiceData("data");
		PROTOCOL_TYPE type = PROTOCOL_TYPE.valueOf(com.wk.cd.enu.PROTOCOL_TYPE.class, pro);
		ModuleSourceInfo info = new ModuleSourceInfo(type, getDtInfoFromData(sd_source));
		info.setData(data);
		return info;

	}

	private static DtSourceInfo getDtInfoFromData(ServiceData data) {
		DtSourceInfo info = new DtSourceInfo();
		String soc_name = data.getString("soc_name");
		info.setSoc_name(soc_name);

		info.setRemote_uname(data.getString("remote_uname"));
		info.setKey_remote_passwd(data.getString("key_remote_passwd"));
		info.setRemote_passwd(data.getString("remote_passwd"));
		info.setSoc_ip(data.getString("soc_ip"));
		info.setSoc_port(data.getInt("soc_port"));
		info.setUser_root_path(data.getString("user_root_path"));
		info.setBk_timeout(data.getLong("bk_timeout"));
		info.setJdbc_drv(data.getString("jdbc_drv"));
		info.setJdbc_url(data.getString("jdbc_url"));
		info.setJdbc_schema(data.getString("jdbc_schema"));
		info.setFtp_local_script(data.getString("ftp_local_script"));
		info.setCvt_local_script(data.getString("cvt_local_script"));
		info.setSoc_domain(data.getString("soc_domain"));
		info.setSoc_bk_desc(data.getString("soc_bk_desc"));

		info.setEnvironment_variables(data.getString("environment_variables"));
		if (!DtSourceInfo.AGENT_SOC_NAME.equals(soc_name)) {
			int pro = data.getInt("protocol_type");
			PROTOCOL_TYPE protocol_type = PROTOCOL_TYPE.valueOf(com.wk.cd.enu.PROTOCOL_TYPE.class, pro);
			info.setProtocol_type(protocol_type);

			int rcd = data.getInt("rcd_state");
			RCD_STATE rcd_state = RCD_STATE.valueOf(com.wk.cd.enu.RCD_STATE.class, rcd);
			info.setRcd_state(rcd_state);

			int cvt = data.getInt("cvt_type");
			CVT_TYPE cvt_type = CVT_TYPE.valueOf(com.wk.cd.enu.CVT_TYPE.class, cvt);
			info.setCvt_type(cvt_type);
		} else {
			info.setProtocol_type(PROTOCOL_TYPE.AGENT);
		}

		return info;
	}

	private Element serviceData(ServiceData data, Document document) {
		Element script = document.createElement(XmlTags.SCRIPT);
		// if (!Assert.isEmpty(type)) {
		// script.setAttribute(XmlTags.TYPE, type.getName());
		// }
		StringBuffer sb = new StringBuffer();
		sb.append(JSON.fromServiceData(data, JSONCaseType.DEFAULT));
		script.setTextContent(sb.toString());
		return script;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "ModuleSourceInfo [protocol_type=" + protocol_type + ", data=" + data + ", dt_source_info=" + dt_source_info + "]";
	}
	
	
}
