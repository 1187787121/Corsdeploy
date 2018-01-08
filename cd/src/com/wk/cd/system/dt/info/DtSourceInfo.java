/**
 * Title: DtSourceInfo.java
 * File Description: ����Դ��Ϣ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-13
 */

package com.wk.cd.system.dt.info;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;

/**
 * Class description:����Դ��Ϣ��
 * @author AutoGen
 */
@Table("DT_SOURCE")
@PrimaryKey({ "soc_name" })
public class DtSourceInfo
		implements Serializable, XmlEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	public static final String TABLECN = "����Դ��Ϣ��";
	/**
	 * AGENT����Դ������Դ����ֻ��shell���͵ķ����б��в��ܷ������
	 */
	public static final String AGENT_SOC_NAME = "AGENT";

	/**
	 * ����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";

	/**
	 * IP��ַ
	 */
	private String soc_ip;

	public static final String SOC_IPCN = "IP��ַ";

	/**
	 * �˿ں�
	 */
	private int soc_port;

	public static final String SOC_PORTCN = "�˿ں�";

	/**
	 * Э������
	 */
	private PROTOCOL_TYPE protocol_type;

	public static final String PROTOCOL_TYPECN = "Э������";

	/**
	 * ��������½�û���
	 */
	private String remote_uname;

	public static final String REMOTE_UNAMECN = "��������½�û���";

	/**
	 * ��������½����
	 */
	private String remote_passwd;

	public static final String REMOTE_PASSWDCN = "��������½����";

	/**
	 * ��Կ
	 */
	private String key_remote_passwd;

	public static final String KEY_REMOTE_PASSWDCN = "��Կ";

	/**
	 * ��ʱʱ��
	 */
	private long bk_timeout;

	public static final String BK_TIMEOUTCN = "��ʱʱ��";

	/**
	 * Jdbc_driver
	 */
	private String jdbc_drv;

	public static final String JDBC_DRVCN = "Jdbc_driver";

	/**
	 * Jdbc_url
	 */
	private String jdbc_url;

	public static final String JDBC_URLCN = "Jdbc_url";

	/**
	 * Jdbc_schema
	 */
	private String jdbc_schema;

	public static final String JDBC_SCHEMACN = "Jdbc_schema";

	/**
	 * ת�뷽ʽ
	 */
	private CVT_TYPE cvt_type;

	public static final String CVT_TYPECN = "ת�뷽ʽ";

	/**
	 * ���´��ű�
	 */
	private String ftp_local_script;

	public static final String FTP_LOCAL_SCRIPTCN = "���´��ű�";

	/**
	 * ת��ű�
	 */
	private String cvt_local_script;

	public static final String CVT_LOCAL_SCRIPTCN = "ת��ű�";

	/**
	 * ����Դ����
	 */
	private String soc_domain;

	public static final String SOC_DOMAINCN = "����Դ����";

	/**
	 * ����Դ����
	 */
	private String soc_bk_desc;

	public static final String SOC_BK_DESCCN = "����Դ����";

	/**
	 * ����Դ����
	 */
	private String soc_params;

	public static final String SOC_PARAMSCN = "����Դ����";

	/**
	 * �û���·��
	 */
	private String user_root_path;

	public static final String USER_ROOT_PATHCN = "�û���·��";

	/**
	 * �����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 * ��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 * ��������
	 */
	private String environment_variables;

	public static final String ENVIRONMENT_VARIABLESCN = "��������";

	/**
	 * �����ʽ
	 */
	private String encoding_type;

	public static final String ENCODING_TYPECN = "�����ʽ";

	/**
	 * @return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return soc_ip IP��ַ
	 */
	public String getSoc_ip() {
		return this.soc_ip;
	}

	/**
	 * @param soc_ip IP��ַ
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}

	/**
	 * @return soc_port �˿ں�
	 */
	public int getSoc_port() {
		return this.soc_port;
	}

	/**
	 * @param soc_port �˿ں�
	 */
	public void setSoc_port(int soc_port) {
		this.soc_port = soc_port;
	}

	/**
	 * @return protocol_type Э������
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return this.protocol_type;
	}

	/**
	 * @param protocol_type Э������
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return remote_uname ��������½�û���
	 */
	public String getRemote_uname() {
		return this.remote_uname;
	}

	/**
	 * @param remote_uname ��������½�û���
	 */
	public void setRemote_uname(String remote_uname) {
		this.remote_uname = remote_uname;
	}

	/**
	 * @return remote_passwd ��������½����
	 */
	public String getRemote_passwd() {
		return this.remote_passwd;
	}

	/**
	 * @param remote_passwd ��������½����
	 */
	public void setRemote_passwd(String remote_passwd) {
		this.remote_passwd = remote_passwd;
	}

	/**
	 * @return key_remote_passwd ��Կ
	 */
	public String getKey_remote_passwd() {
		return this.key_remote_passwd;
	}

	/**
	 * @param key_remote_passwd ��Կ
	 */
	public void setKey_remote_passwd(String key_remote_passwd) {
		this.key_remote_passwd = key_remote_passwd;
	}

	/**
	 * @return bk_timeout ��ʱʱ��
	 */
	public long getBk_timeout() {
		return this.bk_timeout;
	}

	/**
	 * @param bk_timeout ��ʱʱ��
	 */
	public void setBk_timeout(long bk_timeout) {
		this.bk_timeout = bk_timeout;
	}

	/**
	 * @return jdbc_drv Jdbc_driver
	 */
	public String getJdbc_drv() {
		return this.jdbc_drv;
	}

	/**
	 * @param jdbc_drv Jdbc_driver
	 */
	public void setJdbc_drv(String jdbc_drv) {
		this.jdbc_drv = jdbc_drv;
	}

	/**
	 * @return jdbc_url Jdbc_url
	 */
	public String getJdbc_url() {
		return this.jdbc_url;
	}

	/**
	 * @param jdbc_url Jdbc_url
	 */
	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	/**
	 * @return jdbc_schema Jdbc_schema
	 */
	public String getJdbc_schema() {
		return this.jdbc_schema;
	}

	/**
	 * @param jdbc_schema Jdbc_schema
	 */
	public void setJdbc_schema(String jdbc_schema) {
		this.jdbc_schema = jdbc_schema;
	}

	/**
	 * @return cvt_type ת�뷽ʽ
	 */
	public CVT_TYPE getCvt_type() {
		return this.cvt_type;
	}

	/**
	 * @param cvt_type ת�뷽ʽ
	 */
	public void setCvt_type(CVT_TYPE cvt_type) {
		this.cvt_type = cvt_type;
	}

	/**
	 * @return ftp_local_script ���´��ű�
	 */
	public String getFtp_local_script() {
		return this.ftp_local_script;
	}

	/**
	 * @param ftp_local_script ���´��ű�
	 */
	public void setFtp_local_script(String ftp_local_script) {
		this.ftp_local_script = ftp_local_script;
	}

	/**
	 * @return cvt_local_script ת��ű�
	 */
	public String getCvt_local_script() {
		return this.cvt_local_script;
	}

	/**
	 * @param cvt_local_script ת��ű�
	 */
	public void setCvt_local_script(String cvt_local_script) {
		this.cvt_local_script = cvt_local_script;
	}

	/**
	 * @return soc_domain ����Դ����
	 */
	public String getSoc_domain() {
		return this.soc_domain;
	}

	/**
	 * @param soc_domain ����Դ����
	 */
	public void setSoc_domain(String soc_domain) {
		this.soc_domain = soc_domain;
	}

	/**
	 * @return soc_bk_desc ����Դ����
	 */
	public String getSoc_bk_desc() {
		return this.soc_bk_desc;
	}

	/**
	 * @param soc_bk_desc ����Դ����
	 */
	public void setSoc_bk_desc(String soc_bk_desc) {
		this.soc_bk_desc = soc_bk_desc;
	}

	/**
	 * @return soc_params ����Դ����
	 */
	public String getSoc_params() {
		return this.soc_params;
	}

	/**
	 * @param soc_params ����Դ����
	 */
	public void setSoc_params(String soc_params) {
		this.soc_params = soc_params;
	}

	/**
	 * @return user_root_path �û���·��
	 */
	public String getUser_root_path() {
		return this.user_root_path;
	}

	/**
	 * @param user_root_path �û���·��
	 */
	public void setUser_root_path(String user_root_path) {
		this.user_root_path = user_root_path;
	}

	/**
	 * @return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 * @param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

	/**
	 * @return rcd_state ��¼״̬
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 * @param rcd_state ��¼״̬
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

	/**
	 * @return environment_variables ��������
	 */
	public String getEnvironment_variables() {
		return this.environment_variables;
	}

	/**
	 * @param environment_variables ��������
	 */
	public void setEnvironment_variables(String environment_variables) {
		this.environment_variables = environment_variables;
	}

	/**
	 * @return encoding_type �����ʽ
	 */
	public String getEncoding_type() {
		return this.encoding_type;
	}

	/**
	 * @param encoding_type �����ʽ
	 */
	public void setEncoding_type(String encoding_type) {
		this.encoding_type = encoding_type;
	}

	@Override
	public void fromElement(Element element) {
		user_root_path = element.getAttribute(XmlTags.ROOT_PATH);
		String port = element.getAttribute(XmlTags.PORT);
		if (!Assert.isEmpty(port) && StringUtils.isNumeric(port)) {
			soc_port = Integer.parseInt(port);
		}
		protocol_type = XmlAdapter.getAttribute(element, XmlTags.PROTOCOL_TYPE, PROTOCOL_TYPE.class,
				true);
		soc_name = element.getAttribute(XmlTags.SOC_NAME);
		soc_ip = element.getAttribute(XmlTags.SOC_IP);
		if (protocol_type != PROTOCOL_TYPE.AGENT) {
			soc_params = element.getAttribute(XmlTags.SOC_PARAMS);

			soc_domain = element.getAttribute(XmlTags.DOMAIN);
			soc_bk_desc = element.getAttribute(XmlTags.BK_DESC);
			remote_uname = element.getAttribute(XmlTags.REMOTE_UNAME);
			remote_passwd = element.getAttribute(XmlTags.REMOTE_PASSWD);
			rcd_state = XmlAdapter.getAttribute(element, XmlTags.RCD_STATE, RCD_STATE.class, true);

			key_remote_passwd = element.getAttribute(XmlTags.KEY_REMOTE_PASSWD);
			jdbc_url = element.getAttribute(XmlTags.JDBC_URL);
			jdbc_schema = element.getAttribute(XmlTags.JDBC_SCHEMA);
			jdbc_drv = element.getAttribute(XmlTags.JDBC_DRV);
			ftp_local_script = element.getAttribute(XmlTags.FTP_LOCAL_SCRIPT);
			environment_variables = element.getAttribute(XmlTags.ENV_VAR);
			encoding_type = element.getAttribute(XmlTags.ENCONDING_TYPE);
			cvt_type = XmlAdapter.getAttribute(element, XmlTags.CVT_TYPE, CVT_TYPE.class, true);
			cvt_local_script = element.getAttribute(XmlTags.CVT_LOCAL_SCRIPT);
			bk_timeout = Long.parseLong(element.getAttribute(XmlTags.BK_TIMEOUT));
			backup_fld = element.getAttribute(XmlTags.BACKUP_FLD);
		}

	}

	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.DT_SOURCE;
		}
		Element dtSource_ele = document.createElement(tag);
		XmlAdapter.setAttribute(dtSource_ele, XmlTags.PROTOCOL_TYPE, protocol_type, true);
		if (protocol_type != PROTOCOL_TYPE.AGENT) {
			dtSource_ele.setAttribute(XmlTags.ROOT_PATH, user_root_path);
			dtSource_ele.setAttribute(XmlTags.SOC_PARAMS, soc_params);
			dtSource_ele.setAttribute(XmlTags.DOMAIN, soc_domain);
			dtSource_ele.setAttribute(XmlTags.BK_DESC, soc_bk_desc);
			dtSource_ele.setAttribute(XmlTags.REMOTE_UNAME, remote_uname);
			dtSource_ele.setAttribute(XmlTags.REMOTE_PASSWD, remote_passwd);
			XmlAdapter.setAttribute(dtSource_ele, XmlTags.RCD_STATE, rcd_state, true);

			dtSource_ele.setAttribute(XmlTags.KEY_REMOTE_PASSWD, key_remote_passwd);
			dtSource_ele.setAttribute(XmlTags.JDBC_URL, jdbc_url);
			dtSource_ele.setAttribute(XmlTags.JDBC_SCHEMA, jdbc_schema);
			dtSource_ele.setAttribute(XmlTags.JDBC_DRV, jdbc_drv);
			dtSource_ele.setAttribute(XmlTags.FTP_LOCAL_SCRIPT, ftp_local_script);
			dtSource_ele.setAttribute(XmlTags.ENV_VAR, environment_variables);
			dtSource_ele.setAttribute(XmlTags.ENCONDING_TYPE, encoding_type);
			XmlAdapter.setAttribute(dtSource_ele, XmlTags.CVT_TYPE, cvt_type, true);
			dtSource_ele.setAttribute(XmlTags.CVT_LOCAL_SCRIPT, cvt_local_script);
			dtSource_ele.setAttribute(XmlTags.BK_TIMEOUT, String.valueOf(bk_timeout));
			dtSource_ele.setAttribute(XmlTags.BACKUP_FLD, backup_fld);
		}
		dtSource_ele.setAttribute(XmlTags.PORT, String.valueOf(soc_port));
		dtSource_ele.setAttribute(XmlTags.SOC_NAME, soc_name);
		dtSource_ele.setAttribute(XmlTags.SOC_IP, soc_ip);
		return dtSource_ele;
	}

	public static DtSourceInfo copy(DtSourceInfo dt) {
		DtSourceInfo info = new DtSourceInfo();
		info.setBackup_fld(Assert.isEmpty(dt.getBackup_fld()) ? dt.getBackup_fld()
				: dt.getBackup_fld().trim());
		info.setBk_timeout(dt.getBk_timeout());
		info.setCvt_local_script(Assert.isEmpty(dt.getCvt_local_script()) ? dt.getCvt_local_script()
				: dt.getCvt_local_script().trim());
		info.setCvt_type(dt.getCvt_type());
		info.setEncoding_type(Assert.isEmpty(dt.getEncoding_type()) ? dt.getEncoding_type()
				: dt.getEncoding_type().trim());
		info.setEnvironment_variables(Assert.isEmpty(dt.getEnvironment_variables())
				? dt.getEnvironment_variables() : dt.getEnvironment_variables().trim());
		info.setFtp_local_script(Assert.isEmpty(dt.getFtp_local_script()) ? dt.getFtp_local_script()
				: dt.getFtp_local_script().trim());
		info.setJdbc_drv(
				Assert.isEmpty(dt.getJdbc_drv()) ? dt.getJdbc_drv() : dt.getJdbc_drv().trim());
		info.setJdbc_schema(Assert.isEmpty(dt.getJdbc_schema()) ? dt.getJdbc_schema()
				: dt.getJdbc_schema().trim());
		info.setJdbc_url(
				Assert.isEmpty(dt.getJdbc_url()) ? dt.getJdbc_url() : dt.getJdbc_url().trim());
		info.setKey_remote_passwd(Assert.isEmpty(dt.getKey_remote_passwd())
				? dt.getKey_remote_passwd() : dt.getKey_remote_passwd().trim());
		info.setProtocol_type(dt.getProtocol_type());
		info.setRcd_state(dt.getRcd_state());
		info.setRemote_passwd(Assert.isEmpty(dt.getRemote_passwd()) ? dt.getRemote_passwd()
				: dt.getRemote_passwd().trim());
		info.setRemote_uname(Assert.isEmpty(dt.getRemote_uname()) ? dt.getRemote_uname()
				: dt.getRemote_uname().trim());
		info.setSoc_bk_desc(Assert.isEmpty(dt.getSoc_bk_desc()) ? dt.getSoc_bk_desc()
				: dt.getSoc_bk_desc().trim());
		info.setSoc_domain(Assert.isEmpty(dt.getSoc_domain()) ? dt.getSoc_domain()
				: dt.getSoc_domain().trim());
		info.setSoc_ip(Assert.isEmpty(dt.getSoc_ip()) ? dt.getSoc_ip() : dt.getSoc_ip().trim());
		info.setSoc_name(
				Assert.isEmpty(dt.getSoc_name()) ? dt.getSoc_name() : dt.getSoc_name().trim());
		info.setSoc_params(Assert.isEmpty(dt.getSoc_params()) ? dt.getSoc_params()
				: dt.getSoc_params().trim());
		info.setSoc_port(dt.getSoc_port());
		info.setUser_root_path(Assert.isEmpty(dt.getUser_root_path()) ? dt.getUser_root_path()
				: dt.getUser_root_path().trim());

		return info;
	}

	/**
	 * ���캯��
	 */
	public DtSourceInfo() {
		super();
	}

	/**
	 * Description:
	 * @return
	 */
	@Override
	public String toString() {
		return "DtSourceInfo [soc_name=" + soc_name + ", soc_ip=" + soc_ip + ", soc_port="
				+ soc_port + ", protocol_type=" + protocol_type + ", remote_uname=" + remote_uname
				+ ", remote_passwd=" + remote_passwd + ", key_remote_passwd=" + key_remote_passwd
				+ ", bk_timeout=" + bk_timeout + ", jdbc_drv=" + jdbc_drv + ", jdbc_url=" + jdbc_url
				+ ", jdbc_schema=" + jdbc_schema + ", cvt_type=" + cvt_type + ", ftp_local_script="
				+ ftp_local_script + ", cvt_local_script=" + cvt_local_script + ", soc_domain="
				+ soc_domain + ", soc_bk_desc=" + soc_bk_desc + ", soc_params=" + soc_params
				+ ", user_root_path=" + user_root_path + ", backup_fld=" + backup_fld
				+ ", rcd_state=" + rcd_state + ", environment_variables=" + environment_variables
				+ ", encoding_type=" + encoding_type + "]";
	}

}
