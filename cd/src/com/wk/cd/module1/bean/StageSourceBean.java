/**
 * Title: StageSourceBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.module1.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.xml1.ElementEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.sdo.ServiceData;
import com.wk.util.JSON;
import com.wk.util.JSONCaseType;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class StageSourceBean
		implements ElementEntity {

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
	 * 版本数据源
	 */
	private String ver_soc_name;

	public static final String VER_SOC_NAMECN = "版本数据源";

	/**
	 * 执行IP
	 */
	private String exe_ip;

	public static final String EXE_IPCN = "执行IP";

	/**
	 * 版本及IP
	 */
	private String ver_ip;

	public static final String VER_IPCN = "版本机ip";

	/**
	 * 当协议类型是angent的时候 需要传入这个协议类型，用来判断，因为当为angent的时候只传过来IP和协议类型，并没有soc_name.
	 * 需要用exe_ip和这个协议类型、以及配置文件当中的端口号来生成对应的数据源
	 */
	private PROTOCOL_TYPE exe_protocol_type;

	public static final String EXE_PROTOCOL_TYPECN = "执行协议类型";


	private PROTOCOL_TYPE ver_protocol_type;

	public static final String VER_PROTOCOL_TYPECN = "版本协议类型";

	/**
	 * 执行备选数据源列表
	 */
	private String[] exe_soc_list;
//	private List<SocListBean> exe_soc_list;

	/**
	 * 版本备选数据源列表
	 */
	private String[] ver_soc_list;
//	private List<SocListBean> ver_soc_list;

	/**
	 * 构造函数
	 */
	public StageSourceBean() {
		super();
	}

	/**
	 * @return exe_server_name
	 */
	public String getExe_server_name() {
		return exe_server_name;
	}

	/**
	 * @param exe_server_name
	 */
	public void setExe_server_name(String exe_server_name) {
		this.exe_server_name = exe_server_name;
	}

	/**
	 * @return ver_server_name
	 */
	public String getVer_server_name() {
		return ver_server_name;
	}

	/**
	 * @param ver_server_name
	 */
	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	/**
	 * @return
	 */
	public String getExe_soc_name() {
		return this.exe_soc_name;
	}

	/**
	 * @param exe_soc_name
	 */
	public void setExe_soc_name(String exe_soc_name) {
		this.exe_soc_name = exe_soc_name;
	}

	public String getVer_soc_name() {
		return ver_soc_name;
	}

	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	public String getExe_ip() {
		return exe_ip;
	}

	public void setExe_ip(String exe_ip) {
		this.exe_ip = exe_ip;
	}

	public String getVer_ip() {
		return ver_ip;
	}

	public void setVer_ip(String ver_ip) {
		this.ver_ip = ver_ip;
	}

	public PROTOCOL_TYPE getExe_protocol_type() {
		return exe_protocol_type;
	}

	public void setExe_protocol_type(PROTOCOL_TYPE exe_protocol_type) {
		this.exe_protocol_type = exe_protocol_type;
	}

	public PROTOCOL_TYPE getVer_protocol_type() {
		return ver_protocol_type;
	}

	public void setVer_protocol_type(PROTOCOL_TYPE ver_protocol_type) {
		this.ver_protocol_type = ver_protocol_type;
	}

	/**
	 * @return exe_soc_list
	 */
	public String[] getExe_soc_list() {
		return exe_soc_list;
	}

	/**
	 * @param exe_soc_list
	 */
	public void setExe_soc_list(String[] exe_soc_list) {
		this.exe_soc_list = exe_soc_list;
	}

	/**
	 * @return ver_soc_list
	 */
	public String[] getVer_soc_list() {
		return ver_soc_list;
	}

	/**
	 * @param ver_soc_list
	 */
	public void setVer_soc_list(String[] ver_soc_list) {
		this.ver_soc_list = ver_soc_list;
	}

	public static String toJSOCString(StageSourceBean bean) {
		String json = JSON.fromBean(bean, JSONCaseType.DEFAULT);
		return json;
	}

	@SuppressWarnings("unchecked")
	public static StageSourceBean getInfoFromJson(String json) {
		ServiceData sd = JSON.toServiceData(json);
		String exe_server_name = sd.getString("exe_server_name");
		String ver_server_name = sd.getString("ver_server_name");
		String exe_soc_name = sd.getString("exe_soc_name");
		String ver_soc_name = sd.getString("ver_soc_name");
		String exe_ip = sd.getString("exe_ip");
		String ver_ip = sd.getString("ver_ip");
		
//		List<SocListBean> exe_soc_list = sd.getList("exe_soc_list");
//		List<SocListBean> ver_soc_list = sd.getList("ver_soc_list");
		String[] exe_soc_list = sd.getStringArray("exe_soc_list");
		String[] ver_soc_list = sd.getStringArray("ver_soc_list");
		StageSourceBean bean = new StageSourceBean();
		bean.setExe_server_name(exe_server_name);
		bean.setVer_server_name(ver_server_name);
		bean.setExe_ip(exe_ip);
		bean.setExe_soc_name(exe_soc_name);
		bean.setVer_ip(ver_ip);
		bean.setVer_soc_name(ver_soc_name);
		bean.setExe_soc_list(exe_soc_list);
		bean.setVer_soc_list(ver_soc_list);
		return bean;
	}

	public static StageSourceBean[] getArray(String exe_soc, String ver_soc) {
		String[] es = null;
		if (exe_soc.contains(",")) {
			es = exe_soc.split(",");
		} else {
			es = new String[] { exe_soc };
		}
		String[] vs = null;
		if (!Assert.isEmpty(ver_soc)) {
			if (ver_soc.contains(",")) {
				vs = ver_soc.split(",");
			} else {
				vs = new String[] { ver_soc };
			}
		}
		List<StageSourceBean> list = new ArrayList<StageSourceBean>();
		for (int i = 0; i < es.length; i++) {
			StageSourceBean bean = new StageSourceBean();
			bean.setExe_soc_name(es[i]);
			if (vs != null && vs.length == es.length) {
				bean.setVer_soc_name(vs[i]);
			}
			list.add(bean);
		}
		return list.toArray(new StageSourceBean[list.size()]);

	}
	
	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "StageSourceBean [exe_server_name=" + exe_server_name + ", ver_server_name=" + ver_server_name + ", exe_soc_name=" + exe_soc_name
				+ ", ver_soc_name=" + ver_soc_name + ", exe_ip=" + exe_ip + ", ver_ip=" + ver_ip + ", exe_protocol_type=" + exe_protocol_type
				+ ", ver_protocol_type=" + ver_protocol_type + ", exe_soc_list=" + Arrays.toString(exe_soc_list) + ", ver_soc_list="
				+ Arrays.toString(ver_soc_list) + "]";
	}

	/**
	 * Description:
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		StageSourceBean bean = getInfoFromJson(element.getTextContent());
		this.setExe_server_name(bean.getExe_server_name());
		this.setVer_server_name(bean.getVer_server_name());
		this.setExe_ip(bean.getExe_ip());
		this.setExe_soc_name(bean.getExe_soc_name());
		this.setVer_ip(bean.getVer_ip());
		this.setVer_soc_name(bean.getVer_soc_name());
		this.setExe_soc_list(bean.getExe_soc_list());
		this.setVer_soc_list(bean.getVer_soc_list());
	}

	/**
	 * Description:
	 * @param document
	 * @param tag
	 * @return
	 */
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.SOURCE;
		}
		Element soc_ele = document.createElement(tag);
		soc_ele.setTextContent(toJSOCString(this));
		return soc_ele;
	}

}
