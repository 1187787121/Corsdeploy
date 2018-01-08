/**
 * Title: PgProgramInfo.java
 * File Description: 环境方案表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:环境方案表
 * @author AutoGen
 */
@Table("PG_PROGRAM")
@PrimaryKey({"prog_id"})
public class PgProgramInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "环境方案表";

	/**
	 *方案编号
	 */
	private String prog_id;

	public static final String PROG_IDCN = "方案编号";

	/**
	 *方案名称
	 */
	private String prog_name;

	public static final String PROG_NAMECN = "方案名称";

	/**
	 *所属环境
	 */
	private String env_name;

	public static final String ENV_NAMECN = "所属环境";

	/**
	 *方案描述
	 */
	private String prog_bk_desc;

	public static final String PROG_BK_DESCCN = "方案描述";

	/**
	 *方案类型
	 */
	private PROG_TYPE prog_type;

	public static final String PROG_TYPECN = "方案类型";

	/**
	 *是否发布
	 */
	private IS_PUBLISH is_publish;

	public static final String IS_PUBLISHCN = "是否发布";

	/**
	 *创建人
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建人";

	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";

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
	 *@return prog_name 方案名称
	 */
	public String getProg_name() {
		return this.prog_name;
	}

	/**
	 *@param prog_name 方案名称
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	/**
	 *@return env_name 所属环境
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name 所属环境
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return prog_bk_desc 方案描述
	 */
	public String getProg_bk_desc() {
		return this.prog_bk_desc;
	}

	/**
	 *@param prog_bk_desc 方案描述
	 */
	public void setProg_bk_desc(String prog_bk_desc) {
		this.prog_bk_desc = prog_bk_desc;
	}

	/**
	 *@return prog_type 方案类型
	 */
	public PROG_TYPE getProg_type() {
		return this.prog_type;
	}

	/**
	 *@param prog_type 方案类型
	 */
	public void setProg_type(PROG_TYPE prog_type) {
		this.prog_type = prog_type;
	}

	/**
	 *@return is_publish 是否发布
	 */
	public IS_PUBLISH getIs_publish() {
		return this.is_publish;
	}

	/**
	 *@param is_publish 是否发布
	 */
	public void setIs_publish(IS_PUBLISH is_publish) {
		this.is_publish = is_publish;
	}

	/**
	 *@return crt_user_id 创建人
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建人
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}
}
