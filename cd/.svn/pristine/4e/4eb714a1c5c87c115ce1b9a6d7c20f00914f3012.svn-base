/**
 * Title: SvSrvSocInfo.java
 * File Description: 服务数据源配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.sv.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:服务数据源配置表
 * @author AutoGen
 */
@Table("SV_SRV_SOC")
@PrimaryKey({"srv_name","soc_name"})
public class SvSrvSocInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "服务数据源配置表";

	/**
	 *服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 *数据源名称
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源名称";

	/**
	 *数据源顺序号
	 */
	private int soc_seq;

	public static final String SOC_SEQCN = "数据源顺序号";

	/**
	 *@return srv_name 服务名称
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 *@return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 *@param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 *@return soc_seq 数据源顺序号
	 */
	public int getSoc_seq() {
		return this.soc_seq;
	}

	/**
	 *@param soc_seq 数据源顺序号
	 */
	public void setSoc_seq(int soc_seq) {
		this.soc_seq = soc_seq;
	}

}
