/**
 * Title: QuerySrvByNameOutputBean.java
 * File Description: 按照服务名查询服务信息输出接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.SALLOW_FLAG;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.cd.system.sv.info.SvSrvSocInfo;

/**
 * Class Description: 按照服务名查询服务信息输出接口
 * @author tlw
 */
public class QuerySrvByNameOutputBean
		extends ActionRootOutputBean {
	private static final long serialVersionUID = 2368165847747714242L;

	/**
	 * 服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 * 服务描述
	 */
	private String srv_bk_desc;

	public static final String SRV_BK_DESCCN = "服务描述";

	/**
	 * 服务类型
	 */
	private FUN_TYPE srv_fun_type;

	public static final String SRV_FUN_TYPECN = "服务类型";

	/**
	 * 服务类名
	 */
	private String srv_class_name;

	public static final String SRV_CLASS_NAMECN = "服务类名";

	/**
	 * 服务方法名
	 */
	private String srv_method_name;

	public static final String SRV_METHOD_NAMECN = "服务方法名";

	/**
	 * 服务允许标志
	 */
	private SALLOW_FLAG sallow_flag;

	public static final String SALLOW_FLAGCN = "服务允许标志";

	/**
	 * 是否定义数据源
	 */
	private SOC_FLAG soc_flag;

	public static final String SOC_FLAGCN = "是否定义数据源";

	/**
	 * 数据源定义信息
	 */
	private List<SvSrvSocInfo> srv_soc_list;

	public static final String SRV_SOC_LISTCN = "数据源定义信息";

	/**
	 * 目标主机名称
	 */
	private String remote_srv_name;

	public static final String REMOTE_SRV_NAMECN = "目标主机名称";

	/**
	 * 目标主机服务类型
	 */
	private String srv_type;

	public static final String SRV_TYPECN = "目标主机服务类型";

	/**
	 * 服务路径
	 */
	private String srv_root_path;

	public static final String SRV_ROOT_PATHCN = "服务路径";

	/**
	 * @return srv_name 服务名称
	 */
	public String getSrv_name() {
		return srv_name;
	}

	/**
	 * @param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 * @return srv_bk_desc 服务描述
	 */
	public String getSrv_bk_desc() {
		return srv_bk_desc;
	}

	/**
	 * @param srv_bk_desc 服务描述
	 */
	public void setSrv_bk_desc(String srv_bk_desc) {
		this.srv_bk_desc = srv_bk_desc;
	}

	/**
	 * @return srv_fun_type 服务类型
	 */
	public FUN_TYPE getSrv_fun_type() {
		return srv_fun_type;
	}

	/**
	 * @param srv_fun_type 服务类型
	 */
	public void setSrv_fun_type(FUN_TYPE srv_fun_type) {
		this.srv_fun_type = srv_fun_type;
	}

	/**
	 * @return srv_class_name 服务类名
	 */
	public String getSrv_class_name() {
		return srv_class_name;
	}

	/**
	 * @param srv_class_name 服务类名
	 */
	public void setSrv_class_name(String srv_class_name) {
		this.srv_class_name = srv_class_name;
	}

	/**
	 * @return srv_method_name 服务方法名
	 */
	public String getSrv_method_name() {
		return srv_method_name;
	}

	/**
	 * @param srv_method_name 服务方法名
	 */
	public void setSrv_method_name(String srv_method_name) {
		this.srv_method_name = srv_method_name;
	}

	/**
	 * @return sallow_flag 服务允许标志
	 */
	public SALLOW_FLAG getSallow_flag() {
		return sallow_flag;
	}

	/**
	 * @param sallow_flag 服务允许标志
	 */
	public void setSallow_flag(SALLOW_FLAG sallow_flag) {
		this.sallow_flag = sallow_flag;
	}

	/**
	 * @return soc_flag 是否定义数据源
	 */
	public SOC_FLAG getSoc_flag() {
		return soc_flag;
	}

	/**
	 * @param soc_flag 是否定义数据源
	 */
	public void setSoc_flag(SOC_FLAG soc_flag) {
		this.soc_flag = soc_flag;
	}

	/**
	 * @return srv_soc_list 数据源定义信息
	 */
	public List<SvSrvSocInfo> getSrv_soc_list() {
		return srv_soc_list;
	}

	/**
	 * @param srv_soc_list 数据源定义信息
	 */
	public void setSrv_soc_list(List<SvSrvSocInfo> srv_soc_list) {
		this.srv_soc_list = srv_soc_list;
	}

	/**
	 * @return remote_srv_name 目标主机名称
	 */
	public String getRemote_srv_name() {
		return remote_srv_name;
	}

	/**
	 * @param remote_srv_name 目标主机名称
	 */
	public void setRemote_srv_name(String remote_srv_name) {
		this.remote_srv_name = remote_srv_name;
	}

	/**
	 * @return srv_type 目标主机服务类型
	 */
	public String getSrv_type() {
		return srv_type;
	}

	/**
	 * @param srv_type 目标主机服务类型
	 */
	public void setSrv_type(String srv_type) {
		this.srv_type = srv_type;
	}

	/**
	 * @return srv_root_path 目标主机名称
	 */
	public String getSrv_root_path() {
		return srv_root_path;
	}

	/**
	 * @param srv_root_path 目标主机名称
	 */
	public void setSrv_root_path(String srv_root_path) {
		this.srv_root_path = srv_root_path;
	}

}
