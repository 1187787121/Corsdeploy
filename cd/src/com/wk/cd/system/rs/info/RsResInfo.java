/**
 * Title: RsResInfo.java
 * File Description: 资源配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.rs.info;

import java.io.Serializable;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.OPEN_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.TITLE_ABLE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:资源配置表
 * @author AutoGen
 */
@Table("RS_RES")
@PrimaryKey({"rs_code"})
public class RsResInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "资源配置表";

	/**
	 *资源编码
	 */
	private String rs_code;

	public static final String RS_CODECN = "资源编码";

	/**
	 *上级资源编码
	 */
	private String super_rs_code;

	public static final String SUPER_RS_CODECN = "上级资源编码";
	
	/**
	 *所属一级资源编码
	 */
	private String bl_rs_code;

	public static final String BL_RS_CODECN = "所属一级资源编码";

	/**
	 *资源类型
	 */
	private FUN_TYPE rs_fun_type;

	public static final String RS_FUN_TYPECN = "资源类型";

	/**
	 *资源名称
	 */
	private String rs_cn_name;

	public static final String RS_CN_NAMECN = "资源名称";

	/**
	 *资源描述
	 */
	private String rs_bk_desc;

	public static final String RS_BK_DESCCN = "资源描述";

	/**
	 *资源地址
	 */
	private String rs_url;

	public static final String RS_URLCN = "资源地址";

	/**
	 *资源图标地址
	 */
	private String rsim_url;

	public static final String RSIM_URLCN = "资源图标地址";

	/**
	 *资源级别
	 */
	private int rs_level;

	public static final String RS_LEVELCN = "资源级别";

	/**
	 *是否有标题
	 */
	private TITLE_ABLE title_able;

	public static final String TITLE_ABLECN = "是否有标题";
	
	/**
	 *是否公开
	 */
	private YN_FLAG public_yn_flag;

	public static final String PUBLIC_YN_FLAGCN = "是否公开";
	
	/**
	 *开放类型
	 */
	private OPEN_TYPE open_type;

	public static final String OPEN_TYPECN = "开放类型";

	/**
	 *排序键
	 */
	private int sort_key;

	public static final String SORT_KEYCN = "排序键";

	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

	/**
	 *
	 */
	private String gheight_bk_pixel;

	public static final String GHEIGHT_BK_PIXELCN = "弹窗页面高度";

	/**
	 *
	 */
	private String pwidth_bk_pixel;

	public static final String PWIDTH_BK_PIXELCN = "弹窗页面宽度";

	/**
	 *@return rs_code 资源编码
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 *@param rs_code 资源编码
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	/**
	 *@return super_rs_code 上级资源编码
	 */
	public String getSuper_rs_code() {
		return this.super_rs_code;
	}

	/**
	 *@param super_rs_code 上级资源编码
	 */
	public void setSuper_rs_code(String super_rs_code) {
		this.super_rs_code = super_rs_code;
	}

	/**
	 * @return bl_rs_code 所属一级资源编码
	 */
	public String getBl_rs_code() {
		return this.bl_rs_code;
	}

	/**
	 * @param bl_rs_code 所属一级资源编码
	 */
	public void setBl_rs_code(String bl_rs_code) {
		this.bl_rs_code = bl_rs_code;
	}

	/**
	 *@return rs_fun_type 资源类型
	 */
	public FUN_TYPE getRs_fun_type() {
		return this.rs_fun_type;
	}

	/**
	 *@param rs_fun_type 资源类型
	 */
	public void setRs_fun_type(FUN_TYPE rs_fun_type) {
		this.rs_fun_type = rs_fun_type;
	}

	/**
	 *@return rs_cn_name 资源名称
	 */
	public String getRs_cn_name() {
		return this.rs_cn_name;
	}

	/**
	 *@param rs_cn_name 资源名称
	 */
	public void setRs_cn_name(String rs_cn_name) {
		this.rs_cn_name = rs_cn_name;
	}

	/**
	 *@return rs_bk_desc 资源描述
	 */
	public String getRs_bk_desc() {
		return this.rs_bk_desc;
	}

	/**
	 *@param rs_bk_desc 资源描述
	 */
	public void setRs_bk_desc(String rs_bk_desc) {
		this.rs_bk_desc = rs_bk_desc;
	}

	/**
	 *@return rs_url 资源地址
	 */
	public String getRs_url() {
		return this.rs_url;
	}

	/**
	 *@param rs_url 资源地址
	 */
	public void setRs_url(String rs_url) {
		this.rs_url = rs_url;
	}

	/**
	 *@return rsim_url 资源图标地址
	 */
	public String getRsim_url() {
		return this.rsim_url;
	}

	/**
	 *@param rsim_url 资源图标地址
	 */
	public void setRsim_url(String rsim_url) {
		this.rsim_url = rsim_url;
	}

	/**
	 *@return rs_level 资源级别
	 */
	public int getRs_level() {
		return this.rs_level;
	}

	/**
	 *@param rs_level 资源级别
	 */
	public void setRs_level(int rs_level) {
		this.rs_level = rs_level;
	}

	/**
	 *@return title_able 是否有标题
	 */
	public TITLE_ABLE getTitle_able() {
		return this.title_able;
	}

	/**
	 *@param title_able 是否有标题
	 */
	public void setTitle_able(TITLE_ABLE title_able) {
		this.title_able = title_able;
	}
	
	/**
	 * @return public_yn_flag 是否公开
	 */
	public YN_FLAG getPublic_yn_flag() {
		return public_yn_flag;
	}

	/**
	 * @param public_yn_flag 是否公开
	 */
	public void setPublic_yn_flag(YN_FLAG public_yn_flag) {
		this.public_yn_flag = public_yn_flag;
	}

	/**
	 *@return sort_key 排序键
	 */
	public int getSort_key() {
		return this.sort_key;
	}

	/**
	 *@param sort_key 排序键
	 */
	public void setSort_key(int sort_key) {
		this.sort_key = sort_key;
	}

	/**
	 *@return rcd_state 记录状态
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state 记录状态
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

	/**
	 *@return gheight_bk_pixel 弹窗页面高度
	 */
	public String getGheight_bk_pixel() {
		return this.gheight_bk_pixel;
	}

	/**
	 *@param gheight_bk_pixel 弹窗页面高度
	 */
	public void setGheight_bk_pixel(String gheight_bk_pixel) {
		this.gheight_bk_pixel = gheight_bk_pixel;
	}

	/**
	 *@return pwidth_bk_pixel 弹窗页面宽度
	 */
	public String getPwidth_bk_pixel() {
		return this.pwidth_bk_pixel;
	}

	/**
	 *@param pwidth_bk_pixel 弹窗页面宽度
	 */
	public void setPwidth_bk_pixel(String pwidth_bk_pixel) {
		this.pwidth_bk_pixel = pwidth_bk_pixel;
	}

	/**
	 * @return open_type 开放类型
	 */
	public OPEN_TYPE getOpen_type() {
		return this.open_type;
	}

	/**
	 * @param open_type 开放类型
	 */
	public void setOpen_type(OPEN_TYPE open_type) {
		this.open_type = open_type;
	}
	
}
