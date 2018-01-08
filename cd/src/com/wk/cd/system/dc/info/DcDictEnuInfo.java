/**
 * Title: DcDictEnuInfo.java
 * File Description: 数据字典枚举表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.dc.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:数据字典枚举表
 * @author AutoGen
 */
@Table("DC_DICT_ENU")
@PrimaryKey({"domain_name","enu_value"})
public class DcDictEnuInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "数据字典枚举表";

	/**
	 *域名称
	 */
	private String domain_name;

	public static final String DOMAIN_NAMECN = "域名称";

	/**
	 *选项值
	 */
	private int enu_value;

	public static final String ENU_VALUECN = "选项值";

	/**
	 *选项代码
	 */
	private String enu_code;

	public static final String ENU_CODECN = "选项代码";

	/**
	 *选项说明
	 */
	private String enu_bk_expl;

	public static final String ENU_BK_EXPLCN = "选项说明";

	/**
	 *@return domain_name 域名称
	 */
	public String getDomain_name() {
		return this.domain_name;
	}

	/**
	 *@param domain_name 域名称
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	/**
	 *@return enu_value 选项值
	 */
	public int getEnu_value() {
		return this.enu_value;
	}

	/**
	 *@param enu_value 选项值
	 */
	public void setEnu_value(int enu_value) {
		this.enu_value = enu_value;
	}

	/**
	 *@return enu_code 选项代码
	 */
	public String getEnu_code() {
		return this.enu_code;
	}

	/**
	 *@param enu_code 选项代码
	 */
	public void setEnu_code(String enu_code) {
		this.enu_code = enu_code;
	}

	/**
	 *@return enu_bk_expl 选项说明
	 */
	public String getEnu_bk_expl() {
		return this.enu_bk_expl;
	}

	/**
	 *@param enu_bk_expl 选项说明
	 */
	public void setEnu_bk_expl(String enu_bk_expl) {
		this.enu_bk_expl = enu_bk_expl;
	}

}
