/**
 * Title: RsResInfo.java
 * File Description: ��Դ���ñ�
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
 * Class description:��Դ���ñ�
 * @author AutoGen
 */
@Table("RS_RES")
@PrimaryKey({"rs_code"})
public class RsResInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "��Դ���ñ�";

	/**
	 *��Դ����
	 */
	private String rs_code;

	public static final String RS_CODECN = "��Դ����";

	/**
	 *�ϼ���Դ����
	 */
	private String super_rs_code;

	public static final String SUPER_RS_CODECN = "�ϼ���Դ����";
	
	/**
	 *����һ����Դ����
	 */
	private String bl_rs_code;

	public static final String BL_RS_CODECN = "����һ����Դ����";

	/**
	 *��Դ����
	 */
	private FUN_TYPE rs_fun_type;

	public static final String RS_FUN_TYPECN = "��Դ����";

	/**
	 *��Դ����
	 */
	private String rs_cn_name;

	public static final String RS_CN_NAMECN = "��Դ����";

	/**
	 *��Դ����
	 */
	private String rs_bk_desc;

	public static final String RS_BK_DESCCN = "��Դ����";

	/**
	 *��Դ��ַ
	 */
	private String rs_url;

	public static final String RS_URLCN = "��Դ��ַ";

	/**
	 *��Դͼ���ַ
	 */
	private String rsim_url;

	public static final String RSIM_URLCN = "��Դͼ���ַ";

	/**
	 *��Դ����
	 */
	private int rs_level;

	public static final String RS_LEVELCN = "��Դ����";

	/**
	 *�Ƿ��б���
	 */
	private TITLE_ABLE title_able;

	public static final String TITLE_ABLECN = "�Ƿ��б���";
	
	/**
	 *�Ƿ񹫿�
	 */
	private YN_FLAG public_yn_flag;

	public static final String PUBLIC_YN_FLAGCN = "�Ƿ񹫿�";
	
	/**
	 *��������
	 */
	private OPEN_TYPE open_type;

	public static final String OPEN_TYPECN = "��������";

	/**
	 *�����
	 */
	private int sort_key;

	public static final String SORT_KEYCN = "�����";

	/**
	 *��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 *
	 */
	private String gheight_bk_pixel;

	public static final String GHEIGHT_BK_PIXELCN = "����ҳ��߶�";

	/**
	 *
	 */
	private String pwidth_bk_pixel;

	public static final String PWIDTH_BK_PIXELCN = "����ҳ����";

	/**
	 *@return rs_code ��Դ����
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 *@param rs_code ��Դ����
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	/**
	 *@return super_rs_code �ϼ���Դ����
	 */
	public String getSuper_rs_code() {
		return this.super_rs_code;
	}

	/**
	 *@param super_rs_code �ϼ���Դ����
	 */
	public void setSuper_rs_code(String super_rs_code) {
		this.super_rs_code = super_rs_code;
	}

	/**
	 * @return bl_rs_code ����һ����Դ����
	 */
	public String getBl_rs_code() {
		return this.bl_rs_code;
	}

	/**
	 * @param bl_rs_code ����һ����Դ����
	 */
	public void setBl_rs_code(String bl_rs_code) {
		this.bl_rs_code = bl_rs_code;
	}

	/**
	 *@return rs_fun_type ��Դ����
	 */
	public FUN_TYPE getRs_fun_type() {
		return this.rs_fun_type;
	}

	/**
	 *@param rs_fun_type ��Դ����
	 */
	public void setRs_fun_type(FUN_TYPE rs_fun_type) {
		this.rs_fun_type = rs_fun_type;
	}

	/**
	 *@return rs_cn_name ��Դ����
	 */
	public String getRs_cn_name() {
		return this.rs_cn_name;
	}

	/**
	 *@param rs_cn_name ��Դ����
	 */
	public void setRs_cn_name(String rs_cn_name) {
		this.rs_cn_name = rs_cn_name;
	}

	/**
	 *@return rs_bk_desc ��Դ����
	 */
	public String getRs_bk_desc() {
		return this.rs_bk_desc;
	}

	/**
	 *@param rs_bk_desc ��Դ����
	 */
	public void setRs_bk_desc(String rs_bk_desc) {
		this.rs_bk_desc = rs_bk_desc;
	}

	/**
	 *@return rs_url ��Դ��ַ
	 */
	public String getRs_url() {
		return this.rs_url;
	}

	/**
	 *@param rs_url ��Դ��ַ
	 */
	public void setRs_url(String rs_url) {
		this.rs_url = rs_url;
	}

	/**
	 *@return rsim_url ��Դͼ���ַ
	 */
	public String getRsim_url() {
		return this.rsim_url;
	}

	/**
	 *@param rsim_url ��Դͼ���ַ
	 */
	public void setRsim_url(String rsim_url) {
		this.rsim_url = rsim_url;
	}

	/**
	 *@return rs_level ��Դ����
	 */
	public int getRs_level() {
		return this.rs_level;
	}

	/**
	 *@param rs_level ��Դ����
	 */
	public void setRs_level(int rs_level) {
		this.rs_level = rs_level;
	}

	/**
	 *@return title_able �Ƿ��б���
	 */
	public TITLE_ABLE getTitle_able() {
		return this.title_able;
	}

	/**
	 *@param title_able �Ƿ��б���
	 */
	public void setTitle_able(TITLE_ABLE title_able) {
		this.title_able = title_able;
	}
	
	/**
	 * @return public_yn_flag �Ƿ񹫿�
	 */
	public YN_FLAG getPublic_yn_flag() {
		return public_yn_flag;
	}

	/**
	 * @param public_yn_flag �Ƿ񹫿�
	 */
	public void setPublic_yn_flag(YN_FLAG public_yn_flag) {
		this.public_yn_flag = public_yn_flag;
	}

	/**
	 *@return sort_key �����
	 */
	public int getSort_key() {
		return this.sort_key;
	}

	/**
	 *@param sort_key �����
	 */
	public void setSort_key(int sort_key) {
		this.sort_key = sort_key;
	}

	/**
	 *@return rcd_state ��¼״̬
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state ��¼״̬
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

	/**
	 *@return gheight_bk_pixel ����ҳ��߶�
	 */
	public String getGheight_bk_pixel() {
		return this.gheight_bk_pixel;
	}

	/**
	 *@param gheight_bk_pixel ����ҳ��߶�
	 */
	public void setGheight_bk_pixel(String gheight_bk_pixel) {
		this.gheight_bk_pixel = gheight_bk_pixel;
	}

	/**
	 *@return pwidth_bk_pixel ����ҳ����
	 */
	public String getPwidth_bk_pixel() {
		return this.pwidth_bk_pixel;
	}

	/**
	 *@param pwidth_bk_pixel ����ҳ����
	 */
	public void setPwidth_bk_pixel(String pwidth_bk_pixel) {
		this.pwidth_bk_pixel = pwidth_bk_pixel;
	}

	/**
	 * @return open_type ��������
	 */
	public OPEN_TYPE getOpen_type() {
		return this.open_type;
	}

	/**
	 * @param open_type ��������
	 */
	public void setOpen_type(OPEN_TYPE open_type) {
		this.open_type = open_type;
	}
	
}
