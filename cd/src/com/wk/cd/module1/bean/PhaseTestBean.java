/**
 * Title: PhaseTestBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��12��22��
 */
package com.wk.cd.module1.bean;

import java.util.Arrays;
import java.util.List;

import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.bean.NodeBean;
import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.bean.StageSourceBean;

/**
 * Class Description:
 * @author zhangj
 */
public class PhaseTestBean {
	/**
	 * �׶κ�
	 */
	private int phase_no;

	public static final String PHASE_NOCN = "�׶κ�";

	/**
	 * �׶���
	 */
	private String cn_name;

	public static final String CN_NAMECN = "�׶���";
	
	
	/**
	 * �׶α���
	 */
	private String alias_name;
	
	public static final String ALIAS_NAME = "�׶α���";

	/**
	 * �Ƿ����ɱ�־
	 */
	private YN_FLAG gen_flag;

	public static final String GEN_FLAGCN = "�Ƿ����ɱ�ʾ";
	
	/**
	 * �Ƿ��ѡ��ʾ
	 */
	private YN_FLAG selectable_flag;

	public static final String SELECTABLE_FLAGCN = "�Ƿ��ѡ��ʾ";

	/**
	 * ִ�����
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPLE_TYPECN = "ִ�����";

	/**
	 * �����б�
	 */
	private String[] cmds;

	public static final String CMDSCN = "�����б�";
	
	/**
	 * ����ʽ��־ true��ʾ�ǽ���ʽ��
	 */
	private boolean interactor_flag;
	
	/**
	 * �ɲ��б�־ true��ʾ���Բ���
	 */
	private boolean parallel_flag;

	/**
	 * ������ip������Դ
	 */
	private StageSourceBean[] srv_soc;

	public static final String SRV_SOCCN = "������ip������Դ";

	/**
	 * �׶�ʹ��Ͷ�����б����������Ҫ������cl��Ŀ��ÿ���׶���Ҫ����Ͷ����������Ͷ��������ȡͶ���������ã�
	 */
	private String[] package_names;

	public static final String PACKAGE_NAMESCN = "�׶�ʹ��Ͷ�����б�";

	/**
	 * �����ļ��ڵ��б�
	 */
	private NodeBean[] config_nodes;

	public static final String CONFIG_NODESCN = "�����ļ��ڵ��б�";
	
	/**
	 * �׶�����
	 */
	private String bk_desc;
	
	public static final String BK_DESC = "�׶�����";
	/**
	 * �������
	 */
	
	private MODULE_TYPE type;
	
	public static final String TYPE = "�������";
	
	/**
	 * �����
	 */
	private List<PhaseTestBean> modules;
	
	public static final String MODULES = "�����";
	
	
	/**
	 * �������
	 */
	private List<ParamInfo> ref_params;
	
	public static final String REF_PARAMS = "�������";
	
	/**
	 * ����������б�
	 */
	private List<ParamInfo> param_list;
	
	public static final String PARAM_LIST = "����������б�";
	
	
	/**
	 * @return phase_no
	 */
	public int getPhase_no() {
		return phase_no;
	}

	/**
	 * @param phase_no
	 */
	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}

	/**
	 * @return cn_name
	 */
	public String getCn_name() {
		return cn_name;
	}

	/**
	 * @param cn_name
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	/**
	 * @return gen_flag
	 */
	public YN_FLAG getGen_flag() {
		return this.gen_flag;
	}

	/**
	 * @param gen_flag
	 */
	public void setGen_flag(YN_FLAG gen_flag) {
		this.gen_flag = gen_flag;
	}

	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return cmds
	 */
	public String[] getCmds() {
		return cmds;
	}

	/**
	 * @param cmds
	 */
	public void setCmds(String[] cmds) {
		this.cmds = cmds;
	}

	/**
	 * @return srv_soc
	 */
	public StageSourceBean[] getSrv_soc() {
		return srv_soc;
	}

	/**
	 * @param srv_soc
	 */
	public void setSrv_soc(StageSourceBean[] srv_soc) {
		this.srv_soc = srv_soc;
	}

	public String[] getPackage_names() {
		return package_names;
	}

	public void setPackage_names(String[] package_names) {
		this.package_names = package_names;
	}

	public NodeBean[] getConfig_nodes() {
		return config_nodes;
	}

	public void setConfig_nodes(NodeBean[] config_nodes) {
		this.config_nodes = config_nodes;
	}

	/**
	 * @return interactor_flag
	 */
	public boolean isInteractor_flag() {
		return interactor_flag;
	}

	/**
	 * @param interactor_flag
	 */
	public void setInteractor_flag(boolean interactor_flag) {
		this.interactor_flag = interactor_flag;
	}

	/**
	 * @return parallel_flag
	 */
	public boolean isParallel_flag() {
		return parallel_flag;
	}

	/**
	 * @param parallel_flag
	 */
	public void setParallel_flag(boolean parallel_flag) {
		this.parallel_flag = parallel_flag;
	}

	@Override
	public String toString() {
		return "PhaseTestBean [phase_no=" + phase_no + ", cn_name=" + cn_name + ", gen_flag=" + gen_flag
				+ ", impl_type=" + impl_type + ", cmds=" + Arrays.toString(cmds) + ", srv_soc="
				+ Arrays.toString(srv_soc) + ", package_names=" + Arrays.toString(package_names) + ", config_ip="
				+ Arrays.toString(config_nodes) + "]";
	}

	/**
	 * @return selectable_flag
	 */
	public YN_FLAG getSelectable_flag() {
		return selectable_flag;
	}

	/**
	 * @param show_flag
	 */
	public void setSelectable_flag(YN_FLAG selectable_flag) {
		this.selectable_flag = selectable_flag;
	}

	/**
	 * @return type
	 */
	public MODULE_TYPE getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(MODULE_TYPE type) {
		this.type = type;
	}

	/**
	 * @return modules
	 */
	public List<PhaseTestBean> getModules() {
		return modules;
	}

	/**
	 * @param modules
	 */
	public void setModules(List<PhaseTestBean> modules) {
		this.modules = modules;
	}

	/**
	 * @return bk_desc
	 */
	public String getBk_desc() {
		return bk_desc;
	}

	/**
	 * @param bk_desc
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
	}

	/**
	 * @return ref_params
	 */
	public List<ParamInfo> getRef_params() {
		return ref_params;
	}

	/**
	 * @param ref_params
	 */
	public void setRef_params(List<ParamInfo> ref_params) {
		this.ref_params = ref_params;
	}


	/**
	 * @return param_list
	 */
	public List<ParamInfo> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<ParamInfo> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return alias_name
	 */
	public String getAlias_name() {
		return alias_name;
	}

	/**
	 * @param alias_name
	 */
	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name;
	}
	
	
}
