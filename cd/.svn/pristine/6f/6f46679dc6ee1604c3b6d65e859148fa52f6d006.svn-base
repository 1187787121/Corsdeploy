/**
 * Title: BuildScriptExeInfo.java
 * File Description: 构建脚本执行表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDateTime;
/**
 * Class description:构建脚本执行表
 * @author AutoGen
 */
@Table("BUILD_SCRIPT_EXE")
@PrimaryKey({"instance_id","exe_bk_no"})
public class BuildScriptExeInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "构建脚本执行表";

	/**
	 *执行实例ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "执行实例ID";

	/**
	 *执行编号
	 */
	private int exe_bk_no;

	public static final String EXE_BK_NOCN = "执行编号";

	/**
	 *服务器名
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名";

	/**
	 *数据源名
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源名";

	/**
	 *执行状态
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "执行状态";

	/**
	 *执行结果
	 */
	private EXE_RESULT exe_result;

	public static final String EXE_RESULTCN = "执行结果";

	/**
	 *执行信息
	 */
	private String exec_text;

	public static final String EXEC_TEXTCN = "执行信息";

	/**
	 *执行开始时间
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "执行开始时间";

	/**
	 *执行结束时间
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "执行结束时间";

	/**
	 *耗时
	 */
	private int time_used;

	public static final String TIME_USEDCN = "耗时";

	/**
	 *@return instance_id 执行实例ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id 执行实例ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 *@return exe_bk_no 执行编号
	 */
	public int getExe_bk_no() {
		return this.exe_bk_no;
	}

	/**
	 *@param exe_bk_no 执行编号
	 */
	public void setExe_bk_no(int exe_bk_no) {
		this.exe_bk_no = exe_bk_no;
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
	 *@return soc_name 数据源名
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 *@param soc_name 数据源名
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 *@return exe_status 执行状态
	 */
	public EXE_STATUS getExe_status() {
		return this.exe_status;
	}

	/**
	 *@param exe_status 执行状态
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 *@return exec_result 执行结果
	 */
	public EXE_RESULT getExe_result() {
		return this.exe_result;
	}

	/**
	 *@param exec_result 执行结果
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 *@return exec_text 执行信息
	 */
	public String getExec_text() {
		return this.exec_text;
	}

	/**
	 *@param exec_text 执行信息
	 */
	public void setExec_text(String exec_text) {
		this.exec_text = exec_text;
	}

	/**
	 *@return start_bk_tm 执行开始时间
	 */
	public JaDateTime getStart_bk_tm() {
		return this.start_bk_tm;
	}

	/**
	 *@param start_bk_tm 执行开始时间
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 *@return end_bk_tm 执行结束时间
	 */
	public JaDateTime getEnd_bk_tm() {
		return this.end_bk_tm;
	}

	/**
	 *@param end_bk_tm 执行结束时间
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 *@return time_used 耗时
	 */
	public int getTime_used() {
		return this.time_used;
	}

	/**
	 *@param time_used 耗时
	 */
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}

}
