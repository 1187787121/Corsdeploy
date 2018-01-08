/**
 * Title: BuildStepInfo.java
 * File Description: 构建阶段表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:构建阶段表
 * @author AutoGen
 */
@Table("BUILD_STEP")
@PrimaryKey({"work_id","template_name","phase_id"})
public class BuildStepInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "构建阶段表";

	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *模板名
	 */
	private String template_name;

	public static final String TEMPLATE_NAMECN = "模板名";

	/**
	 *阶段编号
	 */
	private int phase_id;

	public static final String PHASE_IDCN = "阶段编号";

	/**
	 *阶段描述
	 */
	private String phase_bk_desc;

	public static final String PHASE_BK_DESCCN = "阶段描述";

	/**
	 *数据源UUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "数据源UUID";

	/**
	 *是否生成实例
	 */
	private YN_FLAG gen_yn_flag;

	public static final String GEN_YN_FLAGCN = "是否生成实例";

	/**
	 *组件执行类型
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPL_TYPECN = "组件执行类型";

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
	 *@return template_name 模板名
	 */
	public String getTemplate_name() {
		return this.template_name;
	}

	/**
	 *@param template_name 模板名
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 *@return phase_id 阶段编号
	 */
	public int getPhase_id() {
		return this.phase_id;
	}

	/**
	 *@param phase_id 阶段编号
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 *@return phase_bk_desc 阶段描述
	 */
	public String getPhase_bk_desc() {
		return this.phase_bk_desc;
	}

	/**
	 *@param phase_bk_desc 阶段描述
	 */
	public void setPhase_bk_desc(String phase_bk_desc) {
		this.phase_bk_desc = phase_bk_desc;
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
	 *@return gen_yn_flag 是否生成实例
	 */
	public YN_FLAG getGen_yn_flag() {
		return this.gen_yn_flag;
	}

	/**
	 *@param gen_yn_flag 是否生成实例
	 */
	public void setGen_yn_flag(YN_FLAG gen_yn_flag) {
		this.gen_yn_flag = gen_yn_flag;
	}

	/**
	 *@return impl_type 组件执行类型
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 *@param impl_type 组件执行类型
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

}
