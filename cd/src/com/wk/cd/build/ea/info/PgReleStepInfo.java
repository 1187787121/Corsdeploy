/**
 * Title: PgReleStepInfo.java
 * File Description: ���������׶α�
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-3
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.build.ea.bean.PhasePublishBean;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���������׶α�
 * @author AutoGen
 */
@Table("PG_RELE_STEP")
@PrimaryKey({"prog_id","template_name","phase_id"})
public class PgReleStepInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���������׶α�";

	/**
	 *�������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";

	/**
	 *ģ����
	 */
	private String template_name;

	public static final String TEMPLATE_NAMECN = "ģ����";

	/**
	 *�׶α��
	 */
	private int phase_id;

	public static final String PHASE_IDCN = "�׶α��";

	/**
	 *�׶�����
	 */
	private String phase_bk_desc;

	public static final String PHASE_BK_DESCCN = "�׶�����";

	/**
	 *����ԴUUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "����ԴUUID";

	/**
	 *�Ƿ�����ʵ��
	 */
	private YN_FLAG gen_yn_flag;

	public static final String GEN_YN_FLAGCN = "�Ƿ�����ʵ��";

	/**
	 *ʵ������
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPL_TYPECN = "ʵ������";

	/**
	 *@return prog_id �������
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 *@return template_name ģ����
	 */
	public String getTemplate_name() {
		return this.template_name;
	}

	/**
	 *@param template_name ģ����
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 *@return phase_id �׶α��
	 */
	public int getPhase_id() {
		return this.phase_id;
	}

	/**
	 *@param phase_id �׶α��
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 *@return phase_bk_desc �׶�����
	 */
	public String getPhase_bk_desc() {
		return this.phase_bk_desc;
	}

	/**
	 *@param phase_bk_desc �׶�����
	 */
	public void setPhase_bk_desc(String phase_bk_desc) {
		this.phase_bk_desc = phase_bk_desc;
	}

	/**
	 *@return soc_uuid ����ԴUUID
	 */
	public String getSoc_uuid() {
		return this.soc_uuid;
	}

	/**
	 *@param soc_uuid ����ԴUUID
	 */
	public void setSoc_uuid(String soc_uuid) {
		this.soc_uuid = soc_uuid;
	}

	/**
	 *@return gen_yn_flag �Ƿ�����ʵ��
	 */
	public YN_FLAG getGen_yn_flag() {
		return this.gen_yn_flag;
	}

	/**
	 *@param gen_yn_flag �Ƿ�����ʵ��
	 */
	public void setGen_yn_flag(YN_FLAG gen_yn_flag) {
		this.gen_yn_flag = gen_yn_flag;
	}

	/**
	 *@return impl_type ʵ������
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 *@param impl_type ʵ������
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}
	
	public static PhasePublishBean copyToPhasePublicBean(PgReleStepInfo info){
		PhasePublishBean bean = new PhasePublishBean();
		bean.setImpl_type(info.getImpl_type());
		bean.setGen_flag(info.getGen_yn_flag());
		bean.setCn_name(info.getPhase_bk_desc());
		return bean;
		
	}

}