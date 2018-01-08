/**
 * Title: EnvInfoBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.util.JaDateTime;

/**
 * Class Description: ������ϢFor App
 * @author xuph
 */
public class EnvInfoBean {
	/**
	 * ��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 * �������
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "�������";

	/**
	 * ��������
	 */
	private String env_bk_desc;

	public static final String ENV_BK_DESCCN = "��������";

	/**
	 * ��������
	 */
	private ENV_TYPE env_type;

	public static final String ENV_TYPECN = "��������";

	/**
	 * Ӧ��ϵͳ
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ";
	
	/**
	 * ϵͳ������
	 */
	private String sys_cn_name;
	
	public static final String SYS_CN_NAMENC = "ϵͳ������";
	
	/**
	 *Ӧ��ϵͳ����
	 */
	private SYS_TYPE sys_type;

	public static final String SYS_TYPECN = "Ӧ��ϵͳ����";

	/**
	 * ����Ҫ��
	 */
	private ELE_TYPE[] ele_type;

	public static final String ELE_TYPECN = "����Ҫ��";

	/**
	 * ���ݷ�Χ
	 */
	private DT_RANGE dt_range;

	public static final String DT_RANGECN = "���ݷ�Χ";

	/**
	 * ����ʱ��
	 */
	private JaDateTime create_date_time;

	public static final String CREATE_DATE_TIMECN = "����ʱ��";

	/**
	 * �����û�
	 */
	private String create_user_id;

	public static final String CREATE_USER_IDCN = "�����û�";

	/**
	 * �޸�ʱ��
	 */
	private JaDateTime modify_date_time;

	public static final String MODIFY_DATE_TIMECN = "�޸�ʱ��";

	/**
	 * �޸��û�
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "�޸��û�";

	/**
	 * Ӧ�÷�����
	 */
	private List<String> sys_server_list;

	public static final String SYS_SERVER_LISTCN = "Ӧ�÷�����";

	/**
	 * ���ݿ������
	 */
	private List<String> db_server_list;

	public static final String DB_SERVER_LISTCN = "���ݿ������";
	
	/**
	 * �汾������
	 */
	private List<String> ver_server_list;

	public static final String VER_SERVER_LISTCN = "�汾������";
	
	/**
	 * �Ƿ��ղ�
	 */
	private int follow_enable;
	
	public static final String FOLLOW_ENABLECN = "�Ƿ��ղ�";

	/**
	 * @return env_name
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return env_cn_name
	 */
	public String getEnv_cn_name() {
		return env_cn_name;
	}

	/**
	 * @param env_cn_name
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return env_bk_desc
	 */
	public String getEnv_bk_desc() {
		return env_bk_desc;
	}

	/**
	 * @param env_bk_desc
	 */
	public void setEnv_bk_desc(String env_bk_desc) {
		this.env_bk_desc = env_bk_desc;
	}

	/**
	 * @return env_type
	 */
	public ENV_TYPE getEnv_type() {
		return env_type;
	}

	/**
	 * @param env_type
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 * @return sys_name
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return ele_type
	 */
	public ELE_TYPE[] getEle_type() {
		return ele_type;
	}

	/**
	 * @param ele_type
	 */
	public void setEle_type(ELE_TYPE[] ele_type) {
		this.ele_type = ele_type;
	}

	/**
	 * @return dt_range
	 */
	public DT_RANGE getDt_range() {
		return dt_range;
	}

	/**
	 * @param dt_range
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

	/**
	 * @return create_date_time
	 */
	public JaDateTime getCreate_date_time() {
		return create_date_time;
	}

	/**
	 * @param create_date_time
	 */
	public void setCreate_date_time(JaDateTime create_date_time) {
		this.create_date_time = create_date_time;
	}

	/**
	 * @return create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
	}

	/**
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 * @return modify_date_time
	 */
	public JaDateTime getModify_date_time() {
		return modify_date_time;
	}

	/**
	 * @param modify_date_time
	 */
	public void setModify_date_time(JaDateTime modify_date_time) {
		this.modify_date_time = modify_date_time;
	}

	/**
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}

	/**
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 * @return sys_server_list
	 */
	public List<String> getSys_server_list() {
		return sys_server_list;
	}

	/**
	 * @param sys_server_list
	 */
	public void setSys_server_list(List<String> sys_server_list) {
		this.sys_server_list = sys_server_list;
	}

	/**
	 * @return db_server_list
	 */
	public List<String> getDb_server_list() {
		return db_server_list;
	}

	/**
	 * @param db_server_list
	 */
	public void setDb_server_list(List<String> db_server_list) {
		this.db_server_list = db_server_list;
	}

	/**
	 * @return ver_server_list
	 */
	public List<String> getVer_server_list() {
		return ver_server_list;
	}

	/**
	 * @param ver_server_list
	 */
	public void setVer_server_list(List<String> ver_server_list) {
		this.ver_server_list = ver_server_list;
	}

	/**
	 * @return follow_enable
	 */
	public int getFollow_enable() {
		return follow_enable;
	}

	/**
	 * @param follow_enable
	 */
	public void setFollow_enable(int follow_enable) {
		this.follow_enable = follow_enable;
	}

	/**
	 * @return sys_type
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return sys_cn_name
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}
	
	
	
}
