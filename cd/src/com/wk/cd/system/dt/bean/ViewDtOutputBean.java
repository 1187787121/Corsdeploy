/**
 * Title: AddSocOutputBean.java
 * File Description: �������Դ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.system.dt.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.dt.info.DtSourceInfo;

/**
 * Class Description:�������Դ����ӿ�
 * @author link
 */
public class ViewDtOutputBean
		extends ActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8520115819242189912L;

	private List<String> lst_soc_name;

	public static final String LST_SOC_NAMECN = "����Դ�����б�";

	private List<String> ip_list;

	public static final String IP_LISTCN = "IP��ַ�б�";

	private List<DtSourceInfo> soc_list;

	public static final String SOC_LISTCN = "����Դ�б�";

	/**
	 * Э������Դ�б�
	 */
	private List<ProtocolSocBean> protocol_soc_list;
	public static final String PROTOCOL_SOC_LISTCN = "Э������Դ�б�";
	
	/**
	 * �Ƿ����Agent
	 */
	private YN_FLAG exist_agent_yn_falg;
	
	public static final String EXIST_AGENT_YN_FALGCN = "�Ƿ����Agent";
	
	/**
	 * �Ƿ�ѡ��AgentУ������Դ
	 */
	private YN_FLAG agent_config_yn_falg;

	public static final String AGENT_CONFIG_YN_FALGCN = "�Ƿ�ѡ��AgentУ������Դ";

	/**
	 * @param lst_soc_name ����Դ�����б�
	 */
	public void setLst_soc_name(List<String> lst_soc_name) {
		this.lst_soc_name = lst_soc_name;
	}

	/**
	 * @return lst_soc_name ����Դ�����б�
	 */
	public List<String> getLst_soc_name() {
		return lst_soc_name;
	}

	/**
	 * @return Э������Դ�б�
	 */
	public List<ProtocolSocBean> getProtocol_soc_list() {
		return protocol_soc_list;
	}

	/**
	 * @param Э������Դ�б�
	 */
	public void setProtocol_soc_list(List<ProtocolSocBean> protocol_soc_list) {
		this.protocol_soc_list = protocol_soc_list;
	}

	/**
	 * @return ip_list
	 */
	public List<String> getIp_list() {
		return ip_list;
	}

	/**
	 * @param ip_list
	 */
	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}

	/**
	 * @return soc_list
	 */
	public List<DtSourceInfo> getSoc_list() {
		return soc_list;
	}

	/**
	 * @param soc_list
	 */
	public void setSoc_list(List<DtSourceInfo> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return exist_agent_yn_falg
	 */
	public YN_FLAG getExist_agent_yn_falg() {
		return exist_agent_yn_falg;
	}

	/**
	 * @param exist_agent_yn_falg
	 */
	public void setExist_agent_yn_falg(YN_FLAG exist_agent_yn_falg) {
		this.exist_agent_yn_falg = exist_agent_yn_falg;
	}

	/**
	 * @return agent_config_yn_falg
	 */
	public YN_FLAG getAgent_config_yn_falg() {
		return agent_config_yn_falg;
	}

	/**
	 * @param agent_config_yn_falg
	 */
	public void setAgent_config_yn_falg(YN_FLAG agent_config_yn_falg) {
		this.agent_config_yn_falg = agent_config_yn_falg;
	}

}
