/**
 * Title: ServerMsgBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.enu.MID_WARE;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��������Ϣ
 * @author xuph
 */
public class ServerMsgBean extends CeServerInfo{

	/** 
	 * @Fields serialVersionUID : 6824839887672343637L
	 */ 
	private static final long serialVersionUID = 6824839887672343637L;
	
	/**
	 * ���ݿ��б�
	 */
	private DBBean[] server_db_list;
	
	public static final String SERVER_DB_LISTCN = "���ݿ��б�";

	/**
	 * �м���б�
	 */
	private List<MID_WARE> mid_ware_list;
	
	public static final String MID_WARE_LISTCN = "�м���б�";

	/**
	 * ����Դ�б�
	 */
	private List<CeServerDsInfo> soc_list;
	
	public static final String SOC_LISTCN = "�м���б�";

	
	/**
	 *  ����ʱ��
	 */
	private JaDateTime create_date_time;
	
	public static final String CREATE_DATE_TIMECN = "����ʱ��";
	
	/**
	 *  �޸�ʱ��
	 */
	private JaDateTime modify_date_time;
	
	public static final String MODIFY_DATE_TIMECN = "�޸�ʱ��";
	
	

	
	/**
	 * @return server_db_list
	 */
	public DBBean[] getServer_db_list() {
		return server_db_list;
	}

	/**
	 * @param server_db_list
	 */
	public void setServer_db_list(DBBean[] server_db_list) {
		this.server_db_list = server_db_list;
	}

	/**
	 * @return mid_ware_list
	 */
	public List<MID_WARE> getMid_ware_list() {
		return mid_ware_list;
	}

	/**
	 * @param mid_ware_list
	 */
	public void setMid_ware_list(List<MID_WARE> mid_ware_list) {
		this.mid_ware_list = mid_ware_list;
	}

	/**
	 * @return soc_list
	 */
	public List<CeServerDsInfo> getSoc_list() {
		return soc_list;
	}

	/**
	 * @param soc_list
	 */
	public void setSoc_list(List<CeServerDsInfo> soc_list) {
		this.soc_list = soc_list;
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

}
