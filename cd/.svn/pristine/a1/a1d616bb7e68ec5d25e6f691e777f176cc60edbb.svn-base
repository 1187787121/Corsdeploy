/**
 * Title: DcDictInfo.java
 * File Description: 数据字典信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.dc.info;

import java.io.Serializable;

import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:数据字典信息表
 * @author AutoGen
 */
@Table("DC_DICT")
@PrimaryKey({"domain_name"})
public class DcDictInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "数据字典信息表";

	/**
	 *域民称
	 */
	private String domain_name;

	public static final String DOMAIN_NAMECN = "域民称";

	/**
	 *域中文名称
	 */
	private String domain_cn_name;

	public static final String DOMAIN_CN_NAMECN = "域中文名称";

	/**
	 *类型
	 */
	private String fld_type;

	public static final String FLD_TYPECN = "类型";

	/**
	 *总长度
	 */
	private int fld_length;

	public static final String FLD_LENGTHCN = "总长度";

	/**
	 *小数位
	 */
	private int fld_scale;

	public static final String FLD_SCALECN = "小数位";

	/**
	 *是否枚举
	 */
	private YN_FLAG enu_yn_flag;

	public static final String ENU_YN_FLAGCN = "是否枚举";

	/**
	 *@return domain_name 域民称
	 */
	public String getDomain_name() {
		return this.domain_name;
	}

	/**
	 *@param domain_name 域民称
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	/**
	 *@return domain_cn_name 域中文名称
	 */
	public String getDomain_cn_name() {
		return this.domain_cn_name;
	}

	/**
	 *@param domain_cn_name 域中文名称
	 */
	public void setDomain_cn_name(String domain_cn_name) {
		this.domain_cn_name = domain_cn_name;
	}

	/**
	 *@return fld_type 类型
	 */
	public String getFld_type() {
		return this.fld_type;
	}

	/**
	 *@param fld_type 类型
	 */
	public void setFld_type(String fld_type) {
		this.fld_type = fld_type;
	}

	/**
	 *@return fld_length 总长度
	 */
	public int getFld_length() {
		return this.fld_length;
	}

	/**
	 *@param fld_length 总长度
	 */
	public void setFld_length(int fld_length) {
		this.fld_length = fld_length;
	}

	/**
	 *@return fld_scale 小数位
	 */
	public int getFld_scale() {
		return this.fld_scale;
	}

	/**
	 *@param fld_scale 小数位
	 */
	public void setFld_scale(int fld_scale) {
		this.fld_scale = fld_scale;
	}

	/**
	 *@return enu_yn_flag 是否枚举
	 */
	public YN_FLAG getEnu_yn_flag() {
		return this.enu_yn_flag;
	}

	/**
	 *@param enu_yn_flag 是否枚举
	 */
	public void setEnu_yn_flag(YN_FLAG enu_yn_flag) {
		this.enu_yn_flag = enu_yn_flag;
	}

}
