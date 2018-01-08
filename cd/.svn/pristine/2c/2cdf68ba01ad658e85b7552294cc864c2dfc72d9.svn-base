/**
 * Title: SvRemoteSrvInfo.java
 * File Description: 远程服务调用配置表
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
 * Class description:远程服务调用配置表
 * @author AutoGen
 */
@Table("SV_REMOTE_SRV")
@PrimaryKey({"srv_name","deal_seq"})
public class SvRemoteSrvInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "远程服务调用配置表";

	/**
	 *服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 *处理序号
	 */
	private int deal_seq;

	public static final String DEAL_SEQCN = "处理序号";

	/**
	 *目标主机名称
	 */
	private String remote_srv_name;

	public static final String REMOTE_SRV_NAMECN = "目标主机名称";

	/**
	 *目标主机服务类型
	 */
	private String srv_type;

	public static final String SRV_TYPECN = "目标主机服务类型";

	/**
	 *服务路径
	 */
	private String srv_root_path;

	public static final String SRV_ROOT_PATHCN = "服务路径";

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
	 *@return deal_seq 处理序号
	 */
	public int getDeal_seq() {
		return this.deal_seq;
	}

	/**
	 *@param deal_seq 处理序号
	 */
	public void setDeal_seq(int deal_seq) {
		this.deal_seq = deal_seq;
	}

	/**
	 *@return remote_srv_name 目标主机名称
	 */
	public String getRemote_srv_name() {
		return this.remote_srv_name;
	}

	/**
	 *@param remote_srv_name 目标主机名称
	 */
	public void setRemote_srv_name(String remote_srv_name) {
		this.remote_srv_name = remote_srv_name;
	}

	/**
	 *@return srv_type 目标主机服务类型
	 */
	public String getSrv_type() {
		return this.srv_type;
	}

	/**
	 *@param srv_type 目标主机服务类型
	 */
	public void setSrv_type(String srv_type) {
		this.srv_type = srv_type;
	}

	/**
	 *@return srv_root_path 服务路径
	 */
	public String getSrv_root_path() {
		return this.srv_root_path;
	}

	/**
	 *@param srv_root_path 服务路径
	 */
	public void setSrv_root_path(String srv_root_path) {
		this.srv_root_path = srv_root_path;
	}

}
