package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class  ParamInfo {
	protected static final Log logger = LogFactory.getLog();

	private String param_name;

	private String param_cn_name;

	private PARAM_TYPE param_type;

	private String param_bk_desc;

	private String param_value;

	private String param_group;

	private String ref;

	private Integer phase_no;

	private YN_FLAG gen_flag;

	private MODIFY_FLAG param_modify_flag;

	private boolean hand_param;

	private String ftp_soc_name;

	private String shell_soc_name;

	private String node;
	// cl项目中多个值的情况下使用
	private int index;

	public static ParamInfo copy(ParamInfo info) {
		if (info == null) {
			return null;
		}
		ParamInfo bean = new ParamInfo();
		bean.setParam_value(info.getParam_value());
		bean.setParam_cn_name(info.getParam_cn_name());
		bean.setParam_bk_desc(info.getParam_bk_desc());
		bean.setParam_group(info.getParam_group());
		bean.setParam_name(info.getParam_name());
		bean.setParam_type(info.getParam_type());
		bean.setPhase_no(info.getPhase_no());
		bean.setRef(info.getRef());
		bean.setParam_modify_flag(info.getParam_modify_flag());
		bean.setHand_param(info.isHand_param());
		bean.setNode(info.getNode());
		bean.setFtp_soc_name(info.getFtp_soc_name());
		bean.setShell_soc_name(info.getShell_soc_name());
		return bean;
	}

	public static List<ParamInfo> copy(List<ParamInfo> infos) {
		if (infos == null) {
			return null;
		}
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		if (Assert.isEmpty(infos)) {
			return list;
		}
		for (ParamInfo info : infos) {
			list.add(copy(info));
		}
		return list;
	}

	public static ParamInfo[] copy(ParamInfo[] infos) {
		if (infos == null) {
			return null;
		}
		ParamInfo[] array = new ParamInfo[infos.length];
		if (Assert.isEmpty(infos)) {
			return array;
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = ParamInfo.copy(infos[i]);
		}
		return array;
	}

	/**
	 * Description: 设置阶段号
	 * @param list
	 * @param index
	 * @return
	 */
	public static List<ParamInfo> setPhaseNo(List<ParamInfo> list, int index) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if (Assert.isEmpty(list)) {
			return params;
		}
		if (index <= 0) {
			return list;
		}
		for (ParamInfo info : list) {
			ParamInfo param = copy(info);
			param.setPhase_no(index);
			params.add(param);
		}
		return params;
	}

	/**
	 * Description: 根据阶段号获取参数
	 * @param list
	 * @param index
	 * @return
	 */
	public static List<ParamInfo> getParamByPhaseNo(ParamInfo[] list, int index) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if (Assert.isEmpty(list)) {
			return params;
		}
		for (ParamInfo info : list) {
			if (info.getPhase_no() != null && info.getPhase_no() == index) {
				params.add(copy(info));
			}
		}
		return params;
	}

	/**
	 * Description: 获取全局参数 就是不带阶段号的参数
	 * @param params
	 * @return
	 */
	public static ParamInfo[] getGeneralParams(ParamInfo[] params) {
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		if (Assert.isEmpty(params)) {
			return list.toArray(new ParamInfo[list.size()]);
		}
		for (ParamInfo info : params) {
			if (info.getPhase_no() == null || info.getPhase_no() == 0) {
				logger.debug("getGeneralParams _param_name[{}],param_value[{}]", info.getParam_name(),
						info.getParam_value());
				list.add(copy(info));
			}
		}
		return list.toArray(new ParamInfo[list.size()]);
	}

	/**
	 * Description:用于自定义参数 阶段号批量减1，用于生成实例的时候用，因为和前端交替所有阶段号从1开始，后台生成实例和执行都是从0开始
	 * @return
	 */
	public static List<ParamInfo> PhaseNoMinus(List<ParamInfo> list) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		for (ParamInfo info : list) {
			if (info.getPhase_no() == null || info.getPhase_no() <= 0) {
				continue;
			}
			ParamInfo new_info = copy(info);
			new_info.setPhase_no(info.getPhase_no());
			params.add(new_info);
		}
		return params;
	}

	@Override
	public String toString() {
		return "ParamInfo [param_name=" + param_name + ", param_cn_name=" + param_cn_name + ", param_value="
				+ param_value + ", param_group=" + param_group + ", ip=" + node + ", index=" + index + "]";
	}

	/**
	 * 构造函数
	 */
	public ParamInfo() {
		super();
	}

	/**
	 * 构造函数
	 * @param param_name
	 * @param param_value
	 */
	public ParamInfo(String param_name, String param_value) {
		super();
		setParam_name(param_name);
		setParam_value(param_value);
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref == null ? ref : new String(ref);
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name == null ? param_name : new String(param_name);
	}

	public String getParam_cn_name() {
		return param_cn_name;
	}

	public void setParam_cn_name(String param_cn_name) {
		this.param_cn_name = param_cn_name == null ? param_cn_name : new String(param_cn_name);
	}

	public PARAM_TYPE getParam_type() {
		return param_type;
	}

	public void setParam_type(PARAM_TYPE param_type) {
		this.param_type = param_type;
	}

	public String getParam_bk_desc() {
		return param_bk_desc;
	}

	public void setParam_bk_desc(String param_desc) {
		this.param_bk_desc = param_desc == null ? param_desc : new String(param_desc);
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value == null ? param_value : new String(param_value);
	}

	public String getParam_group() {
		return param_group;
	}

	public void setParam_group(String param_group) {
		this.param_group = param_group == null ? param_group : new String(param_group);
	}

	public Integer getPhase_no() {
		return this.phase_no;
	}

	public void setPhase_no(Integer phase_no) {
		this.phase_no = phase_no == null ? phase_no : new Integer(phase_no);
	}

	/**
	 * @return param_modify_flag
	 */
	public MODIFY_FLAG getParam_modify_flag() {
		return this.param_modify_flag;
	}

	/**
	 * @param param_modify_flag
	 */
	public void setParam_modify_flag(MODIFY_FLAG param_modify_flag) {
		this.param_modify_flag = param_modify_flag;
	}

	public boolean isHand_param() {
		return hand_param;
	}

	public void setHand_param(boolean hand_param) {
		this.hand_param = hand_param;
	}

	public String getFtp_soc_name() {
		return ftp_soc_name;
	}

	public void setFtp_soc_name(String ftp_soc_name) {
		this.ftp_soc_name = ftp_soc_name;
	}

	public String getShell_soc_name() {
		return shell_soc_name;
	}

	public void setShell_soc_name(String shell_soc_name) {
		this.shell_soc_name = shell_soc_name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return gen_flag
	 */
	public YN_FLAG getGen_flag() {
		return gen_flag;
	}

	/**
	 * @param gen_flag
	 */
	public void setGen_flag(YN_FLAG gen_flag) {
		this.gen_flag = gen_flag;
	}
	
	
}
