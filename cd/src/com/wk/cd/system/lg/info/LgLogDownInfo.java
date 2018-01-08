/**
 * Title: LgLogDownInfo.java
 * File Description: 日期下载信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.lg.info;

import java.io.Serializable;
import com.wk.util.*;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:日期下载信息表
 * @author AutoGen
 */
@Table("LG_LOG_DOWN")
@PrimaryKey({"log_root_path","log_file_name"})
public class LgLogDownInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "日期下载信息表";

	/**
	 *日志文件路径
	 */
	private String log_root_path;

	public static final String LOG_ROOT_PATHCN = "日志文件路径";

	/**
	 *日志文件名称
	 */
	private String log_file_name;

	public static final String LOG_FILE_NAMECN = "日志文件名称";

	/**
	 *日志起始日期
	 */
	private JaDate start_bk_date;

	public static final String START_BK_DATECN = "日志起始日期";

	/**
	 *日志截止日期
	 */
	private JaDate end_bk_date;

	public static final String END_BK_DATECN = "日志截止日期";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *生成日志日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "生成日志日期";

	/**
	 *生成日志时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "生成日志时间";

	/**
	 *备用
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用";

	/**
	 *@return log_root_path 日志文件路径
	 */
	public String getLog_root_path() {
		return this.log_root_path;
	}

	/**
	 *@param log_root_path 日志文件路径
	 */
	public void setLog_root_path(String log_root_path) {
		this.log_root_path = log_root_path;
	}

	/**
	 *@return log_file_name 日志文件名称
	 */
	public String getLog_file_name() {
		return this.log_file_name;
	}

	/**
	 *@param log_file_name 日志文件名称
	 */
	public void setLog_file_name(String log_file_name) {
		this.log_file_name = log_file_name;
	}

	/**
	 *@return start_bk_date 日志起始日期
	 */
	public JaDate getStart_bk_date() {
		return this.start_bk_date;
	}

	/**
	 *@param start_bk_date 日志起始日期
	 */
	public void setStart_bk_date(JaDate start_bk_date) {
		this.start_bk_date = start_bk_date;
	}

	/**
	 *@return end_bk_date 日志截止日期
	 */
	public JaDate getEnd_bk_date() {
		return this.end_bk_date;
	}

	/**
	 *@param end_bk_date 日志截止日期
	 */
	public void setEnd_bk_date(JaDate end_bk_date) {
		this.end_bk_date = end_bk_date;
	}

	/**
	 *@return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return crt_bk_date 生成日志日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 生成日志日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 生成日志时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 生成日志时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return backup_fld 备用
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
