/**
 * Title: FTPBean.java
 * File Description: FTP����ӿ�
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/25/2014
 */

package com.wk.cd.remote.fp.bean;


import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.remote.bean.RBean;

/**
 * Class Description:FTP����ӿ�
 * @author lixl
 */
public class FTPBean extends RBean{
	//ת�뷽ʽ
	private CVT_TYPE cvt_type;
	//���´��ű�
	private String ftp_local_script;
	//ת��ű�
	private String cvt_local_script;
	//ת��field�ļ�����
	private String fld_fname;
	//�ָ���
	private String sep;

	//�Ƿ����ϴ����
	private boolean is_monitor = true;

	/**
	 * ftp�������ļ����Ʊ����ʽ(GBK/UTF-8)
	 *   ע��Ĭ��ftp�����������ʽΪGBK
	 *   ������������ϴ��´����ļ����ƶ���UTF-8��ʽ��
	 *     �����ļ����´�����Ҫ�ƶ������ʽΪUTF-8,
	 *     �����ļ��ϴ����ز��ܳɹ�
	 */
	private String ftp_encoding;

	/**
	 * @return cvt_type ת�뷽ʽ
	 */
	public CVT_TYPE getCvt_type() {
		return this.cvt_type;
	}

	/**
	 * @param cvtType ת�뷽ʽ
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
	 * @param ftpLocalScript ���´��ű�
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
	 * @param cvtLocalScript ת��ű�
	 */
	public void setCvt_local_script(String cvt_local_script) {
		this.cvt_local_script = cvt_local_script;
	}

	/**
	 * @return String ת��field�ļ�����
	 */
	public String getFld_fname(){
		return this.fld_fname;
	}

	/**
	 * @param fld_fname ת��field�ļ�����
	 */
	public void setFld_fname(String fld_fname){
		this.fld_fname = fld_fname;
	}

	/**
	 * @return char �ָ���
	 */
	public String getSep(){
		return this.sep;
	}

	/**
	 * @param sep �ָ���
	 */
	public void setSep(String sep){
		this.sep = sep;
	}

	/**
	 * @return String ftp�������ļ������ʽ
	 */
	public String getFtp_encoding(){
		return this.ftp_encoding;
	}

	/**
	 * @param ftp_encoding ftp�����ļ������ʽ
	 */
	public void setFtp_encoding(String ftp_encoding){
		this.ftp_encoding = ftp_encoding;
	}

	/**
	 * @param is_monitor �Ƿ������
	 */
	public void setIs_monitor(boolean is_monitor){
	    this.is_monitor = is_monitor;
	}

	/**
	 * @return boolean �Ƿ������
	 */
	public boolean getIs_monitor(){
	    return this.is_monitor;
	}
}

