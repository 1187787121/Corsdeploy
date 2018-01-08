/**
 * Title: ChChannelSrvgPrivInfo.java
 * File Description: ����������Ȩ�����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */

package com.wk.cd.system.ch.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:����������Ȩ�����ñ�
 * @author AutoGen
 */
@Table("CH_CHANNEL_SRVG_PRIV")
@PrimaryKey({"channel_code","srvg_code"})
public class ChChannelSrvgPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "����������Ȩ�����ñ�";

	/**
	 *��������
	 */
	private String channel_code;

	public static final String CHANNEL_CODECN = "��������";

	/**
	 *���������
	 */
	private String srvg_code;

	public static final String SRVG_CODECN = "���������";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return channel_code ��������
	 */
	public String getChannel_code() {
		return this.channel_code;
	}

	/**
	 *@param channel_code ��������
	 */
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	/**
	 *@return srvg_code ���������
	 */
	public String getSrvg_code() {
		return this.srvg_code;
	}

	/**
	 *@param srvg_code ���������
	 */
	public void setSrvg_code(String srvg_code) {
		this.srvg_code = srvg_code;
	}

	/**
	 *@return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
