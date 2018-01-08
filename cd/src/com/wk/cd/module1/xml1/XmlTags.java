/**
 * Title: XmlTags.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月14日
 */
package com.wk.cd.module1.xml1;

/**
 * Class Description:
 * 
 * @author "Zhangj"
 */
public class XmlTags {
	/**
	 * 用于实例的最外面的标签
	 */
	public static final String INSTANCE = "instance";

	/**
	 * 是否敏感信息
	 */
	public static final String SENSITIVE = "sensitive";

	/**
	 * 分类标签
	 */
	public static final String TAGS = "tags";
	
	/**
	 * 校验组件标签
	 */
	public static final String CHECK_COMP = "check_comp";
	
	/**
	 * 用于方案最外层的标签
	 */
	public static final String PROGRAM = "program";

	/**
	 * 用于方案第二层的标签
	 */
	public static final String PROGRAM_TYPE = "program_type";

	/**
	 * 用于流程模板最外面的标签
	 */
	public static final String TEMPLATE = "template";

	/**
	 * 用于组件最外面的标签
	 */
	public static final String COMPONENT = "component";

	/**
	 * 用于组件组最外面的标签
	 */
	public static final String GROUP = "group";

	/**
	 * 用于组件组、流程模板中引用的标签
	 */
	public static final String REFERENCES = "references";

	/**
	 * 用于组件中的文件
	 */
	public static final String FILE = "file";

	/**
	 * 用于组件中的文件的参数
	 */
	public static final String FILE_PARAM = "file_param";

	/**
	 * 定义参数里面具体每个子参数的标签
	 */
	public static final String PARAM = "param";

	/**
	 * 定义组件里的输出参数
	 */
	public static final String OUT_PARAM = "out_param";

	/**
	 * 方案参数
	 */
	public static final String PHASE_PARAM = "phase_param";

	/**
	 * 定义参数最外层标签，其内部可以定义多个参数标签（TAG_PARAM）
	 */
	public static final String PARAMS = "params";

	/**
	 * 定义引用参数最外层标签，其内部可以定义多个参数标签（TAG_PARAM）
	 */
	public static final String REFPARAMS = "ref_params";

	/**
	 * 定义参数名、阶段的中文名
	 */
	public static final String NAME = "name";

	/**
	 * 定义参数中文名、组件、组件组的中文名
	 */
	public static final String CNNAME = "cnname";

	/**
	 * 定义参数中文名、组件、组件组的中文描述
	 */
	public static final String DESC = "desc";

	/**
	 * 定义组件、组件组的组件用途
	 */
	public static final String PURPOSES = "purposes";

	/**
	 * 定义参数默认值的标签
	 */
	public static final String DEFAULT = "defult";

	/**
	 * 定义自定义参数的阶段号
	 */
	public static final String PHASENO = "phaseno";

	/**
	 * 方案里的阶段
	 */
	public static final String PHASE = "phase";

	/**
	 * 定义组件组、流程模板中自定义阶段；实例中每个阶段的外层标签
	 */
	public static final String STAGE = "stage";

	/**
	 * 定义组件组、流程模板中自定义阶段；实例中每个阶段的外层标签
	 */
	public static final String GROUP_STAGE = "group_stage";

	/**
	 * 组件参数
	 */
	public static final String REFPARAM = "ref_param";
	public static final String PARAMBKDESC = "param_bk_desc";
	public static final String PARAMCNNAME = "param_cn_name";
	public static final String PARAMGROUP = "param_group";
	public static final String PARAMISUSED = "param_is_used";
	public static final String PARAMNAME = "param_name";
	public static final String PARAMTYPE = "param_type";
	public static final String PARAMRESULT = "param_result";
	public static final String PARAMINDEX = "param_index";

	/**
	 * 组件参数名
	 */
	public static final String REFPARAMNAMES = "ref_param_names";
	public static final String REFPARAMNAME = "ref_param_name";
	public static final String PARAMVALUE = "param_value";

	/**
	 * 脚本的标签
	 */
	public static final String SCRIPT = "script";

	/**
	 * 脚本列表的标签
	 */
	public static final String SCRIPTS = "scripts";

	/**
	 * 定义类型：执行类别
	 */
	public static final String IMPL_TYPE = "impl_type";

	/**
	 * 定义类型：组件来源
	 */
	public static final String COMPONENT_SOURCE = "component_source";

	/**
	 * 定义类型：组件类型
	 */
	public static final String MODULE_TYPE = "module_type";

	/**
	 * 实例中每个阶段对应数据源的标签
	 */
	public static final String NODESOC = "nodesoc";

	/**
	 * 执行实例中每个阶段数据源的IP地址
	 */
	public static final String IP = "ip";

	/**
	 * 执行实例中每个阶段数据源的数据源名
	 */
	public static final String SOURCE = "source";

	/**
	 * 定义ID标签
	 */
	public static final String ID = "id";

	/**
	 * 定义参数是否可修改
	 */
	public static final String MODIFY = "modify";

	/**
	 * 自定义参数标志（true表示为自定义参数）
	 */
	public static final String HAND = "hand";

	/**
	 * 自定义阶段参数的应用参数名
	 */
	public static final String REF = "ref";

	/**
	 * 组件组或流程模板中 给组件或组件组加的别名
	 */
	public static final String ALIAS = "alias";

	/**
	 * 投产包类型 （外层的标签） 暂时用在CL模板中
	 */
	public static final String PKTYPES = "package_types";

	/**
	 * 投产包类型 （内层的标签） 暂时用在CL模板中
	 */
	public static final String PKTYPE = "package_type";

	/**
	 * 投产包类型名
	 */
	public static final String PKNAME = "type_name";

	/**
	 * 投产包类型中文名
	 */
	public static final String PKCNNAME = "type_cn_name";

	/**
	 * 是否生成标志
	 */
	public static final String GENFLAG = "generate";

	/**
	 * 配置文件数据源
	 */
	public static final String CONFIGS = "config_nodes";

	/**
	 * 投产包组合（外层的标签）
	 */
	public static final String PKCOMBINES = "package_combines";

	/**
	 * 投产包组合（内层的标签）
	 */
	public static final String PKCOMBINE = "package_combine";

	public static final String SPLIT = "#@#";

	/**
	 * 发布流程和回退流程
	 */
	public static final String PUB_FLOE = "pub_flow";

	public static final String ROL_FLOW = "rol_flow";

	public static final String MODULE_SOURCE = "module_source";
	// public static final String PROTOCOL_TYPE = "protocol_type";
	public static final String DT_SOURCE = "dt_source";

	public static final String DATA = "data";

	public static final String INSTANCE_PHASE = "instance_phase";

	public static final String PHASES = "phases";

	public static final String PHASE_TYPE = "phase_type";
	/**
	 * dtSource
	 */
	public static final String ROOT_PATH = "root_path";
	public static final String PORT = "port";
	public static final String SOC_NAME = "soc_name";
	public static final String SOC_PARAMS = "soc_params";
	public static final String SOC_IP = "soc_ip";
	public static final String DOMAIN = "domain";
	public static final String BK_DESC = "bk_desc";
	public static final String REMOTE_UNAME = "remote_uname";
	public static final String REMOTE_PASSWD = "remote_passwd";
	public static final String RCD_STATE = "rcd_state";
	public static final String PROTOCOL_TYPE = "protocol_type";
	public static final String KEY_REMOTE_PASSWD = "keyremote_passwd";
	public static final String JDBC_URL = "jdbc_url";
	public static final String JDBC_SCHEMA = "jdbc_schema";
	public static final String JDBC_DRV = "jdbc_drv";
	public static final String FTP_LOCAL_SCRIPT = "local_script";
	public static final String ENV_VAR = "env_var";
	public static final String ENCONDING_TYPE = "enconding_type";
	public static final String CVT_TYPE = "cvt_type";
	public static final String CVT_LOCAL_SCRIPT = "cvt_local_script";
	public static final String BK_TIMEOUT = "bk_timeout";
	public static final String BACKUP_FLD = "backup_fld";
	
	
	
	/*
	 * Template
	 */
	public static final String TEMPLATE_TYPE = "template_type";
	public static final String OPERATING_SYSTEM = "operating_system";
	
	/**
	 * cd方案
	 */
	public static final String PUB_TEMPLATE_NAME = "pub_template_name";
	public static final String ROL_TEMPLATE_NAME = "rol_template_name";
	public static final String VER_SERVER_NAME = "ver_server_name";
	public static final String EXE_SERVER_NAME = "exe_server_name";
	public static final String VER_SOC_NAME = "ver_soc_name";
	public static final String EXE_SOC_NAME = "exe_soc_name";
	public static final String CODE_BK_DIR = "code_bk_dir";

	/**
	 * 交互式标志 true表示是交互式的
	 */
	public static final String INTERACTOR_FLAG = "interactor_flag";

	/**
	 * 可并行标志 true表示可以并行
	 */
	public static final String PARALLEL_FLAG = "parallel_flag";
	
	/**
	 * 引用参数删除标志
	 */
	public static final String DELETE = "delete";

	/**
	 * 原方案阶段号
	 */
	public static final String ORIGINAL_PHASE = "original_phase_id";

}
