/**
 * Title: BuildConfigfileInfo.java
 * File Description: 构建任务配置文件变更表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.OPT_STATUS;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:构建任务配置文件变更表
 * @author AutoGen
 */
@Table("BUILD_CONFIGFILE")
@PrimaryKey({"file_work_seq"})
public class BuildConfigfileInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "构建任务配置文件变更表";

	/**
	 *文件变更流水号
	 */
	private String file_work_seq;

	public static final String FILE_WORK_SEQCN = "文件变更流水号";

	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *配置类型
	 */
	private CFG_TYPE cfg_type;

	public static final String CFG_TYPECN = "配置类型";

	/**
	 *服务器名
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名";

	/**
	 *服务器IP
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "服务器IP";

	/**
	 *操作类型
	 */
	private FOPT_TYPE fopt_type;

	public static final String FOPT_TYPECN = "操作类型";

	/**
	 *文件路径
	 */
	private String file_bk_path;

	public static final String FILE_BK_PATHCN = "文件路径";

	/**
	 *文件名
	 */
	private String file_bk_fname;

	public static final String FILE_BK_FNAMECN = "文件名";

	/**
	 *文件CSUM
	 */
	private String file_bk_csum;

	public static final String FILE_BK_CSUMCN = "文件CSUM";

	/**
	 *是否目录标志
	 */
	private YN_FLAG dir_yn_flag;

	public static final String DIR_YN_FLAGCN = "是否目录标志";

	/**
	 *操作状态
	 */
	private OPT_STATUS opt_status;

	public static final String OPT_STATUSCN = "操作状态";

	/**
	 *修改人
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "修改人";

	/**
	 *修改日期
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 *修改时间
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "修改时间";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return file_work_seq 文件变更流水号
	 */
	public String getFile_work_seq() {
		return this.file_work_seq;
	}

	/**
	 *@param file_work_seq 文件变更流水号
	 */
	public void setFile_work_seq(String file_work_seq) {
		this.file_work_seq = file_work_seq;
	}

	/**
	 *@return work_id 任务编号
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 *@param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 *@return cfg_type 配置类型
	 */
	public CFG_TYPE getCfg_type() {
		return this.cfg_type;
	}

	/**
	 *@param cfg_type 配置类型
	 */
	public void setCfg_type(CFG_TYPE cfg_type) {
		this.cfg_type = cfg_type;
	}

	/**
	 *@return server_name 服务器名
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name 服务器名
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 *@return server_ip 服务器IP
	 */
	public String getServer_ip() {
		return this.server_ip;
	}

	/**
	 *@param server_ip 服务器IP
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 *@return fopt_type 操作类型
	 */
	public FOPT_TYPE getFopt_type() {
		return this.fopt_type;
	}

	/**
	 *@param fopt_type 操作类型
	 */
	public void setFopt_type(FOPT_TYPE fopt_type) {
		this.fopt_type = fopt_type;
	}

	/**
	 *@return file_bk_path 文件路径
	 */
	public String getFile_bk_path() {
		return this.file_bk_path;
	}

	/**
	 *@param file_bk_path 文件路径
	 */
	public void setFile_bk_path(String file_bk_path) {
		this.file_bk_path = file_bk_path;
	}

	/**
	 *@return file_bk_fname 文件名
	 */
	public String getFile_bk_fname() {
		return this.file_bk_fname;
	}

	/**
	 *@param file_bk_fname 文件名
	 */
	public void setFile_bk_fname(String file_bk_fname) {
		this.file_bk_fname = file_bk_fname;
	}

	/**
	 *@return file_bk_csum 文件CSUM
	 */
	public String getFile_bk_csum() {
		return this.file_bk_csum;
	}

	/**
	 *@param file_bk_csum 文件CSUM
	 */
	public void setFile_bk_csum(String file_bk_csum) {
		this.file_bk_csum = file_bk_csum;
	}

	/**
	 *@return dir_yn_flag 是否目录标志
	 */
	public YN_FLAG getDir_yn_flag() {
		return this.dir_yn_flag;
	}

	/**
	 *@param dir_yn_flag 是否目录标志
	 */
	public void setDir_yn_flag(YN_FLAG dir_yn_flag) {
		this.dir_yn_flag = dir_yn_flag;
	}

	/**
	 *@return opt_status 操作状态
	 */
	public OPT_STATUS getOpt_status() {
		return this.opt_status;
	}

	/**
	 *@param opt_status 操作状态
	 */
	public void setOpt_status(OPT_STATUS opt_status) {
		this.opt_status = opt_status;
	}

	/**
	 *@return modify_user_id 修改人
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id 修改人
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 *@return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
