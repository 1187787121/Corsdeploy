/**
 * Title: UpdateRsResInputBean.java
 * File Description: 输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年12月31日
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.OPEN_TYPE;
import com.wk.cd.enu.TITLE_ABLE;
import com.wk.cd.enu.YN_FLAG;

/**
 * Class Description: 修改资源基础信息输入接口
 * @author HT
 */
public class UpdateRsResInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 5388117102475653241L;

	/**
	 *资源编码
	 */
	private String rs_code;

	public static final String RS_CODECN = "资源编码";

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
	 *资源类型
	 */
	private FUN_TYPE rs_fun_type;

	public static final String RS_FUN_TYPECN = "资源类型";

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
	 *弹窗页面高度
	 */
	private String gheight_bk_pixel;

	public static final String GHEIGHT_BK_PIXELCN = "弹窗页面高度";

	/**
	 *弹窗页面宽度
	 */
	private String pwidth_bk_pixel;

	public static final String PWIDTH_BK_PIXELCN = "弹窗页面宽度";

	/**
	 * @return rs_code
	 */
	public String getRs_code() {
		return rs_code;
	}

	/**
	 * @param rs_code
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	/**
	 * @return rs_cn_name
	 */
	public String getRs_cn_name() {
		return rs_cn_name;
	}

	/**
	 * @param rs_cn_name
	 */
	public void setRs_cn_name(String rs_cn_name) {
		this.rs_cn_name = rs_cn_name;
	}

	/**
	 * @return rs_bk_desc
	 */
	public String getRs_bk_desc() {
		return rs_bk_desc;
	}

	/**
	 * @param rs_bk_desc
	 */
	public void setRs_bk_desc(String rs_bk_desc) {
		this.rs_bk_desc = rs_bk_desc;
	}

	
	/**
	 * @return rs_fun_type
	 */
	public FUN_TYPE getRs_fun_type() {
		return rs_fun_type;
	}

	/**
	 * @param rs_fun_type
	 */
	public void setRs_fun_type(FUN_TYPE rs_fun_type) {
		this.rs_fun_type = rs_fun_type;
	}

	/**
	 * @return rs_url
	 */
	public String getRs_url() {
		return rs_url;
	}

	/**
	 * @param rs_url
	 */
	public void setRs_url(String rs_url) {
		this.rs_url = rs_url;
	}

	/**
	 * @return rsim_url
	 */
	public String getRsim_url() {
		return rsim_url;
	}

	/**
	 * @param rsim_url
	 */
	public void setRsim_url(String rsim_url) {
		this.rsim_url = rsim_url;
	}

	/**
	 * @return rs_level
	 */
	public int getRs_level() {
		return rs_level;
	}

	/**
	 * @param rs_level
	 */
	public void setRs_level(int rs_level) {
		this.rs_level = rs_level;
	}

	/**
	 * @return title_able
	 */
	public TITLE_ABLE getTitle_able() {
		return title_able;
	}

	/**
	 * @param title_able
	 */
	public void setTitle_able(TITLE_ABLE title_able) {
		this.title_able = title_able;
	}

	/**
	 * @return public_yn_flag
	 */
	public YN_FLAG getPublic_yn_flag() {
		return public_yn_flag;
	}

	/**
	 * @param public_yn_flag
	 */
	public void setPublic_yn_flag(YN_FLAG public_yn_flag) {
		this.public_yn_flag = public_yn_flag;
	}

	/**
	 * @return open_type
	 */
	public OPEN_TYPE getOpen_type() {
		return open_type;
	}

	/**
	 * @param open_type
	 */
	public void setOpen_type(OPEN_TYPE open_type) {
		this.open_type = open_type;
	}

	/**
	 * @return sort_key
	 */
	public int getSort_key() {
		return sort_key;
	}

	/**
	 * @param sort_key
	 */
	public void setSort_key(int sort_key) {
		this.sort_key = sort_key;
	}

	/**
	 * @return gheight_bk_pixel
	 */
	public String getGheight_bk_pixel() {
		return gheight_bk_pixel;
	}

	/**
	 * @param gheight_bk_pixel
	 */
	public void setGheight_bk_pixel(String gheight_bk_pixel) {
		this.gheight_bk_pixel = gheight_bk_pixel;
	}

	/**
	 * @return pwidth_bk_pixel
	 */
	public String getPwidth_bk_pixel() {
		return pwidth_bk_pixel;
	}

	/**
	 * @param pwidth_bk_pixel
	 */
	public void setPwidth_bk_pixel(String pwidth_bk_pixel) {
		this.pwidth_bk_pixel = pwidth_bk_pixel;
	}
}
