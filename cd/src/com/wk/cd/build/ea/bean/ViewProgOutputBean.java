/**
 * Title: ViewProgActionOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月14日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class ViewProgOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -865419499129222489L;
	
	/**
	 * 方案名称
	 */
	private String prog_name;
	
	public static final String PROG_NAMECN = "方案名称";
	
	/**
	 * 方案描述
	 */
	private String prog_desc;
	
	public static final String PROG_DESCCN = "方案描述";
	
	/**
	 * 方案类型
	 */
	private PROG_TYPE prog_type;
	
	public static final String PROG_TYPECN = "方案类型";
	
	/**
	 * 编译路径
	 */
	private String complie_bk_path;

	public static final String COMPLIE_BK_PATHCN = "编译路径";
	
	/**
	 * 打包根目录
	 */
	private String target_root_path;
	
	public static final String TARGET_ROOT_PATHCN = "打包根目录";
	
	/**
	 * 集成步骤详细信息列表
	 */
	private List<EnvProgStepBean> prog_step_list;
	
	public static final String PROG_STEP_LISTCN = "集成步骤详细信息列表";
	
	/**
	 * 下一步骤编号
	 */
	private int next_step_id;
	
	public static final String NEXT_STEP_IDCN = "下一步骤编号";
	
	/**
	 * 集成步骤详细信息
	 */
	private EnvProgStepBean prog_step_bean;
	
	public static final String PROG_STEP_BEANCN = "集成步骤详细信息";
	
	/**
	 * 阶段名列表
	 */
	private List<String> phase_list;
	
	public static final String PHASE_LISTCN = "阶段名列表";
	
	/**
	 * 模板参数列表
	 */
	private List<TpParamBean> tp_param_list;
	
	public static final String TP_PARAM_LISTCN = "模板参数列表";
	
	/**
	 * 发布列表
	 */
	private List<PgProgramInfo> prog_list;
	
	public static final String PROG_LISTCN = "发布列表";
	
	/**
	 * 版本机服务器
	 */
	private String ver_server_name ;
	
	public static final String VER_SERVER_NAMECN = "版本机服务器";
	
	/**
	 * 版本机数据源
	 */
	private String ver_soc_name;
	
	public static final String VER_SOC_NAMECN = "版本机数据源";
	/**
	 * 发布模板
	 */
	private String pub_template_name ;
	
	public static final String PUB_TEMPLATE_NAMECN = "发布模板";
	
	/**
	 * 回退模版
	 */
	private String rol_template_name ;
	
	public static final String ROL_TEMPLAE_NAMECN = "回退模版";
	
	/**
	 * 发布阶段列表
	 */
	private List<PhasePublishBean> pub_phase_list ;
	
	public static final String PUB_PHASE_LISTCN = "发布阶段列表";
	
	/**
	 * 目录
	 */
	private String ver_bk_dir;
	
	public static final String VER_BK_DIRCN = "目录";
	

	/**
	 * 回退阶段列表
	 */
	List<PhasePublishBean> rol_phase_list;
	
	public static final String ROL_PHASE_LISTCN = "回退阶段列表";

	/**
	 * 发布模板参数列表
	 */
	List<UuParamInfo> pub_param_list;
	
	public static final String PUB_PARAM_LISTCN = "发布模板参数列表";
	
	/**
	 * 回退模板参数列表
	 */
	List<UuParamInfo> rol_param_list;
	
	public static final String ROL_PARAM_LISTCN = "回退模板参数列表";
	
	
	/**
	 * 阶段列表
	 */
	List<PhasePublishBean> phases;
	
	public static final String PHASESCN = "阶段列表";

	/**
	 * 模板参数列表
	 */
	List<UuParamInfo> param_list;
	
	public static final String PARAM_LISTCN = "模板参数列表";
	
	/**
	 * 模板名列表
	 */
	String [] template_names;
	
	public static final String TEMPLATE_NAMESCN = "模板名列表";
	

	/**
	 *源码版本目录
	 */
	private String code_bk_dir;

	public static final String CODE_BK_DIRCN = "源码版本目录";
	
	/**
	 * 服务器简称
	 */
	private String server_cn_name;
	
	public static final String SERVER_CN_NAMECN = "服务器简称";
	
	/**
	 * 模板信息
	 */
	private Template template;
	
	public static final String TEMPLATECN = "模板信息";
	
	/**
	 * 发布方案信息
	 */
	private Program pub_program;

	public static final String PUB_PROGRAMCN = "发布方案信息";
	
	/**
	 * 回退方案信息
	 */
	private Program rol_program;
	
	public static final String ROL_PROGRAMCN = "回退方案信息";
	
	/**
	 * 集成步骤信息
	 */
	private List<InteStepBean> step_list;
	
	public static final String  STEP_LISTCN= "集成步骤信息";
	
	/**
	 * @return prog_name 方案名称
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name 方案名称
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	/**
	 * @return prog_desc 方案描述
	 */
	public String getProg_desc() {
		return prog_desc;
	}

	/**
	 * @param prog_desc 方案描述
	 */
	public void setProg_desc(String prog_desc) {
		this.prog_desc = prog_desc;
	}

	/**
	 * @return prog_type 方案类型"
	 */
	public PROG_TYPE getProg_type() {
		return prog_type;
	}

	/**
	 * @param prog_type 方案类型"
	 */
	public void setProg_type(PROG_TYPE prog_type) {
		this.prog_type = prog_type;
	}
	
	/**
	 * @return complie_bk_path 编译路径
	 */
	public String getComplie_bk_path() {
		return complie_bk_path;
	}

	/**
	 * @param complie_bk_path 编译路径
	 */
	public void setComplie_bk_path(String complie_bk_path) {
		this.complie_bk_path = complie_bk_path;
	}

	/**
	 * @return target_root_path 打包根目录
	 */
	public String getTarget_root_path() {
		return target_root_path;
	}

	/**
	 * @param target_root_path 打包根目录
	 */
	public void setTarget_root_path(String target_root_path) {
		this.target_root_path = target_root_path;
	}
	
	/**
	 * @return prog_step_list 集成步骤详细信息列表
	 */
	public List<EnvProgStepBean> getProg_step_list() {
		return prog_step_list;
	}

	/**
	 * @param prog_step_list 集成步骤详细信息列表
	 */
	public void setProg_step_list(List<EnvProgStepBean> prog_step_list) {
		this.prog_step_list = prog_step_list;
	}

	/**
	 * @return next_step_id 下一步骤编号
	 */
	public int getNext_step_id() {
		return next_step_id;
	}

	/**
	 * @param next_step_id 下一步骤编号
	 */
	public void setNext_step_id(int next_step_id) {
		this.next_step_id = next_step_id;
	}

	/**
	 * @return prog_step_bean 集成步骤详细信息
	 */
	public EnvProgStepBean getProg_step_bean() {
		return prog_step_bean;
	}

	/**
	 * @param prog_step_bean 集成步骤详细信息
	 */
	public void setProg_step_bean(EnvProgStepBean prog_step_bean) {
		this.prog_step_bean = prog_step_bean;
	}

	/**
	 * @return phase_list 阶段名列表
	 */
	public List<String> getPhase_list() {
		return phase_list;
	}

	/**
	 * @param phase_list 阶段名列表
	 */
	public void setPhase_list(List<String> phase_list) {
		this.phase_list = phase_list;
	}

	/**
	 * @return tp_param_list 模板参数列表
	 */
	public List<TpParamBean> getTp_param_list() {
		return tp_param_list;
	}

	/**
	 * @param tp_param_list 模板参数列表
	 */
	public void setTp_param_list(List<TpParamBean> tp_param_list) {
		this.tp_param_list = tp_param_list;
	}

	/**
	 * @return prog_list 发布列表
	 */
	public List<PgProgramInfo> getProg_list() {
		return prog_list;
	}

	/**
	 * @param prog_list 发布列表
	 */
	public void setProg_list(List<PgProgramInfo> prog_list) {
		this.prog_list = prog_list;
	}

	/**
	 * @return ver_server_name 版本机服务器
	 */
	public String getVer_server_name() {
		return this.ver_server_name;
	}

	/**
	 * @param ver_server_name 版本机服务器
	 */
	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	/**
	 * @return ver_soc_name 版本机数据源
	 */
	public String getVer_soc_name() {
		return this.ver_soc_name;
	}

	/**
	 * @param ver_soc_name 版本机数据源
	 */
	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	/**
	 * @return pub_template_name 发布模板
	 */
	public String getPub_template_name() {
		return this.pub_template_name;
	}

	/**
	 * @param pub_template_name 发布模板
	 */
	public void setPub_template_name(String pub_template_name) {
		this.pub_template_name = pub_template_name;
	}

	/**
	 * @return rol_template_name 回退模版
	 */
	public String getRol_template_name() {
		return this.rol_template_name;
	}

	/**
	 * @param rol_template_name 回退模版
	 */
	public void setRol_template_name(String rol_template_name) {
		this.rol_template_name = rol_template_name;
	}

	/**
	 * @return pub_phase_list
	 */
	public List<PhasePublishBean> getPub_phase_list() {
		return pub_phase_list;
	}

	/**
	 * @param pub_phase_list
	 */
	public void setPub_phase_list(List<PhasePublishBean> pub_phase_list) {
		this.pub_phase_list = pub_phase_list;
	}

	/**
	 * @return rol_phase_list
	 */
	public List<PhasePublishBean> getRol_phase_list() {
		return rol_phase_list;
	}

	/**
	 * @param rol_phase_list
	 */
	public void setRol_phase_list(List<PhasePublishBean> rol_phase_list) {
		this.rol_phase_list = rol_phase_list;
	}

	/**
	 * @return pub_param_list
	 */
	public List<UuParamInfo> getPub_param_list() {
		return pub_param_list;
	}

	/**
	 * @param pub_param_list
	 */
	public void setPub_param_list(List<UuParamInfo> pub_param_list) {
		this.pub_param_list = pub_param_list;
	}

	/**
	 * @return rol_param_list
	 */
	public List<UuParamInfo> getRol_param_list() {
		return rol_param_list;
	}

	/**
	 * @param rol_param_list
	 */
	public void setRol_param_list(List<UuParamInfo> rol_param_list) {
		this.rol_param_list = rol_param_list;
	}

	/**
	 * @return ver_bk_dir
	 */
	public String getVer_bk_dir() {
		return ver_bk_dir;
	}

	/**
	 * @param ver_bk_dir
	 */
	public void setVer_bk_dir(String ver_bk_dir) {
		this.ver_bk_dir = ver_bk_dir;
	}

	/**
	 * @return phases
	 */
	public List<PhasePublishBean> getPhases() {
		return phases;
	}

	/**
	 * @param phases
	 */
	public void setPhases(List<PhasePublishBean> phases) {
		this.phases = phases;
	}

	/**
	 * @return param_list
	 */
	public List<UuParamInfo> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<UuParamInfo> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return template_names
	 */
	public String[] getTemplate_names() {
		return template_names;
	}

	/**
	 * @param template_names
	 */
	public void setTemplate_names(String[] template_names) {
		this.template_names = template_names;
	}

	public String getCode_bk_dir() {
		return code_bk_dir;
	}

	public void setCode_bk_dir(String code_bk_dir) {
		this.code_bk_dir = code_bk_dir;
	}

	public String getServer_cn_name() {
		return server_cn_name;
	}

	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Program getPub_program() {
		return pub_program;
	}

	public void setPub_program(Program pub_program) {
		this.pub_program = pub_program;
	}

	public Program getRol_program() {
		return rol_program;
	}

	public void setRol_program(Program rol_program) {
		this.rol_program = rol_program;
	}

	public List<InteStepBean> getStep_list() {
		return step_list;
	}

	public void setStep_list(List<InteStepBean> step_list) {
		this.step_list = step_list;
	}
}
