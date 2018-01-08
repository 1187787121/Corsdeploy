/**
 * Title: PgInteStepInfo.java
 * File Description: 集成方案步骤表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-25
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.COMPILE_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:集成方案步骤表
 * @author AutoGen
 */
@Table("PG_INTE_STEP")
@PrimaryKey({"prog_id","step_id"})
public class PgInteStepInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "集成方案步骤表";

	/**
	 *方案编号
	 */
	private String prog_id;

	public static final String PROG_IDCN = "方案编号";

	/**
	 *步骤编号
	 */
	private int step_id;

	public static final String STEP_IDCN = "步骤编号";

	/**
	 *步骤说明
	 */
	private String step_expl;

	public static final String STEP_EXPLCN = "步骤说明";

	/**
	 *步骤类型
	 */
	private STEP_TYPE step_type;

	public static final String STEP_TYPECN = "步骤类型";

	/**
	 *数据源UUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "数据源UUID";

	/**
	 *脚本
	 */
	private String step_bk_script;

	public static final String STEP_BK_SCRIPTCN = "脚本";

	/**
	 *编译类型
	 */
	private COMPILE_TYPE compile_type;

	public static final String COMPILE_TYPECN = "编译类型";

	/**
	 *编译路径
	 */
	private String complie_bk_path;

	public static final String COMPLIE_BK_PATHCN = "编译路径";

	/**
	 *环境变量
	 */
	private String env_variable;

	public static final String ENV_VARIABLECN = "环境变量";

	/**
	 *编译参数
	 */
	private String compile_param;

	public static final String COMPILE_PARAMCN = "编译参数";

	/**
	 *入库清单UUID
	 */
	private String storage_list_uuid;

	public static final String STORAGE_LIST_UUIDCN = "入库清单UUID";
	
	/**
	 *打包根路径
	 */
	private String storage_bk_path;
	
	public static final String STORAGE_BK_PATHCN = "打包根路径";

	/**
	 *@return prog_id 方案编号
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id 方案编号
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 *@return step_id 步骤编号
	 */
	public int getStep_id() {
		return this.step_id;
	}

	/**
	 *@param step_id 步骤编号
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	/**
	 *@return step_expl 步骤说明
	 */
	public String getStep_expl() {
		return this.step_expl;
	}

	/**
	 *@param step_expl 步骤说明
	 */
	public void setStep_expl(String step_expl) {
		this.step_expl = step_expl;
	}

	/**
	 *@return step_type 步骤类型
	 */
	public STEP_TYPE getStep_type() {
		return this.step_type;
	}

	/**
	 *@param step_type 步骤类型
	 */
	public void setStep_type(STEP_TYPE step_type) {
		this.step_type = step_type;
	}

	/**
	 *@return soc_uuid 数据源UUID
	 */
	public String getSoc_uuid() {
		return this.soc_uuid;
	}

	/**
	 *@param soc_uuid 数据源UUID
	 */
	public void setSoc_uuid(String soc_uuid) {
		this.soc_uuid = soc_uuid;
	}

	/**
	 *@return step_bk_script 脚本
	 */
	public String getStep_bk_script() {
		return this.step_bk_script;
	}

	/**
	 *@param step_bk_script 脚本
	 */
	public void setStep_bk_script(String step_bk_script) {
		this.step_bk_script = step_bk_script;
	}

	/**
	 *@return compile_type 编译类型
	 */
	public COMPILE_TYPE getCompile_type() {
		return this.compile_type;
	}

	/**
	 *@param compile_type 编译类型
	 */
	public void setCompile_type(COMPILE_TYPE compile_type) {
		this.compile_type = compile_type;
	}

	/**
	 *@return complie_bk_path 编译路径
	 */
	public String getComplie_bk_path() {
		return this.complie_bk_path;
	}

	/**
	 *@param complie_bk_path 编译路径
	 */
	public void setComplie_bk_path(String complie_bk_path) {
		this.complie_bk_path = complie_bk_path;
	}

	/**
	 *@return env_variable 环境变量
	 */
	public String getEnv_variable() {
		return this.env_variable;
	}

	/**
	 *@param env_variable 环境变量
	 */
	public void setEnv_variable(String env_variable) {
		this.env_variable = env_variable;
	}

	/**
	 *@return compile_param 编译参数
	 */
	public String getCompile_param() {
		return this.compile_param;
	}

	/**
	 *@param compile_param 编译参数
	 */
	public void setCompile_param(String compile_param) {
		this.compile_param = compile_param;
	}

	/**
	 *@return storage_list_uuid 入库清单UUID
	 */
	public String getStorage_list_uuid() {
		return this.storage_list_uuid;
	}

	/**
	 *@param storage_list_uuid 入库清单UUID
	 */
	public void setStorage_list_uuid(String storage_list_uuid) {
		this.storage_list_uuid = storage_list_uuid;
	}

	/**
	 * @return storage_bk_path 打包根路径
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_bk_path 打包根路径
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "PgInteStepInfo [prog_id=" + prog_id + ", step_id=" + step_id + ", step_expl=" + step_expl + ", step_type=" + step_type + ", soc_uuid=" + soc_uuid
				+ ", step_bk_script=" + step_bk_script + ", compile_type=" + compile_type + ", complie_bk_path=" + complie_bk_path + ", env_variable=" + env_variable
				+ ", compile_param=" + compile_param + ", storage_list_uuid=" + storage_list_uuid + ", storage_bk_path=" + storage_bk_path + "]";
	}
	
	
}
