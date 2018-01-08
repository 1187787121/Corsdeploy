/**
 * Title: FTPBean.java
 * File Description: FTP服务接口
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
 * Class Description:FTP服务接口
 * @author lixl
 */
public class FTPBean extends RBean{
	//转码方式
	private CVT_TYPE cvt_type;
	//上下传脚本
	private String ftp_local_script;
	//转码脚本
	private String cvt_local_script;
	//转码field文件名称
	private String fld_fname;
	//分隔符
	private String sep;

	//是否开启上传监控
	private boolean is_monitor = true;

	/**
	 * ftp服务器文件名称编码格式(GBK/UTF-8)
	 *   注意默认ftp服务器编码格式为GBK
	 *   如果服务器中上传下传的文件名称都是UTF-8格式，
	 *     调用文件上下传必须要制定编码格式为UTF-8,
	 *     否则文件上传下载不能成功
	 */
	private String ftp_encoding;

	/**
	 * @return cvt_type 转码方式
	 */
	public CVT_TYPE getCvt_type() {
		return this.cvt_type;
	}

	/**
	 * @param cvtType 转码方式
	 */
	public void setCvt_type(CVT_TYPE cvt_type) {
		this.cvt_type = cvt_type;
	}

	/**
	 * @return ftp_local_script 上下传脚本
	 */
	public String getFtp_local_script() {
		return this.ftp_local_script;
	}

	/**
	 * @param ftpLocalScript 上下传脚本
	 */
	public void setFtp_local_script(String ftp_local_script) {
		this.ftp_local_script = ftp_local_script;
	}

	/**
	 * @return cvt_local_script 转码脚本
	 */
	public String getCvt_local_script() {
		return this.cvt_local_script;
	}

	/**
	 * @param cvtLocalScript 转码脚本
	 */
	public void setCvt_local_script(String cvt_local_script) {
		this.cvt_local_script = cvt_local_script;
	}

	/**
	 * @return String 转码field文件名称
	 */
	public String getFld_fname(){
		return this.fld_fname;
	}

	/**
	 * @param fld_fname 转码field文件名称
	 */
	public void setFld_fname(String fld_fname){
		this.fld_fname = fld_fname;
	}

	/**
	 * @return char 分隔符
	 */
	public String getSep(){
		return this.sep;
	}

	/**
	 * @param sep 分隔符
	 */
	public void setSep(String sep){
		this.sep = sep;
	}

	/**
	 * @return String ftp服务器文件编码各式
	 */
	public String getFtp_encoding(){
		return this.ftp_encoding;
	}

	/**
	 * @param ftp_encoding ftp服务文件编码各式
	 */
	public void setFtp_encoding(String ftp_encoding){
		this.ftp_encoding = ftp_encoding;
	}

	/**
	 * @param is_monitor 是否开启监控
	 */
	public void setIs_monitor(boolean is_monitor){
	    this.is_monitor = is_monitor;
	}

	/**
	 * @return boolean 是否开启监控
	 */
	public boolean getIs_monitor(){
	    return this.is_monitor;
	}
}

