/**
 * Title: UpdateSrvgInputBean.java
 * File Description: 修改服务组信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.FUN_TYPE;

/**
 * Class Description: 修改服务组信息输入接口
 * @author HT
 */
public class UpdateSrvgInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -8602507067913202936L;
	
	/**
	 *服务组编码
	 */
	private String srvg_code;

	public static final String SRVG_CODECN = "服务组编码";

	/**
	 *服务组名称
	 */
	private String srvg_cn_name;

	public static final String SRVG_CN_NAMECN = "服务组名称";

	/**
	 *服务组描述
	 */
	private String srvg_bk_desc;

	public static final String SRVG_BK_DESCCN = "服务组描述";

	/**
	 *服务组类型
	 */
	private FUN_TYPE srvg_fun_type;

	public static final String SRVG_FUN_TYPECN = "服务组类型";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return srvg_code 服务组编码
	 */
	public String getSrvg_code() {
		return this.srvg_code;
	}

	/**
	 *@param srvg_code 服务组编码
	 */
	public void setSrvg_code(String srvg_code) {
		this.srvg_code = srvg_code;
	}

	/**
	 *@return srvg_cn_name 服务组名称
	 */
	public String getSrvg_cn_name() {
		return this.srvg_cn_name;
	}

	/**
	 *@param srvg_cn_name 服务组名称
	 */
	public void setSrvg_cn_name(String srvg_cn_name) {
		this.srvg_cn_name = srvg_cn_name;
	}

	/**
	 *@return srvg_bk_desc 服务组描述
	 */
	public String getSrvg_bk_desc() {
		return this.srvg_bk_desc;
	}

	/**
	 *@param srvg_bk_desc 服务组描述
	 */
	public void setSrvg_bk_desc(String srvg_bk_desc) {
		this.srvg_bk_desc = srvg_bk_desc;
	}

	/**
	 *@return srv_fun_type 服务组类型
	 */
	public FUN_TYPE getSrvg_fun_type() {
		return this.srvg_fun_type;
	}

	/**
	 *@param srvg_fun_type 服务组类型
	 */
	public void setSrvg_fun_type(FUN_TYPE srvg_fun_type) {
		this.srvg_fun_type = srvg_fun_type;
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
