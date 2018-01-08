package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.info.ParamInfo;

/**
 * Class Description: 组件
 * 
 * @author "Zhangj"
 */
public class ModuleInfo extends ModuleBasicInfo{

	private ParamInfo[] outputs;

	private Map<String, Object> extra = new HashMap<String, Object>();

	/**
	 * 组件阶段执行使用的数据源
	 */
	private ModuleSourceInfo source_info;
	
	/**
	 * 阶段是否生成实例标志
	 */
	private YN_FLAG gen_yn_flag;
	
	private List<PackageTypeInfo> pakcage_types;
		
	/**
	 * 生成实例后 原本实例在模板里面的阶段号 从1开始
	 */
	private Integer template_phase_no;
	
	public static final String MODULE_IDCN = "组件ID";
	public static final String MODULE_ALIAS_NAMECN = "组件别名";
	public static final String MODULE_CN_NAMECN = "组件中文名";
	public static final String MODULE_BK_DESCCN = "组件描述";
	public static final String IMPL_TYPECN = "实现类型";
	public static final String CMDSCN = "执行脚本列表";
	public static final String PARAMSCN = "组件参数列表";
	public static final String REF_PARAMSCN = "引用参数列表";
	
	public ParamInfo[] getOutputs() {
		return outputs;
	}

	public void setOutputs(ParamInfo[] outputs) {
		this.outputs = outputs;
	}

	public int getCount() {
		if (Assert.isEmpty(cmds)) {
			return 0;
		}
		return cmds.length;
	}

	public Set<String> getExtraNames() {
		return extra.keySet();
	}

	public void setExtraInfo(String name, Object value) {
		extra.put(name, value);
	}

	public Object getExtraInfo(String name) {
		return extra.get(name);
	}

	
	public Integer getTemplate_phase_no() {
		return template_phase_no;
	}

	public void setTemplate_phase_no(Integer template_phase_no) {
		this.template_phase_no = template_phase_no;
	}

	
	public ModuleSourceInfo getSource_info() {
		return source_info;
	}

	public void setSource_info(ModuleSourceInfo source_info) {
		this.source_info = source_info;
	}

	public YN_FLAG getGen_yn_flag() {
		return gen_yn_flag;
	}

	public void setGen_yn_flag(YN_FLAG gen_yn_flag) {
		this.gen_yn_flag = gen_yn_flag;
	}

	public static ModuleInfo copy( ModuleInfo info) {
		ModuleInfo copy = new ModuleInfo();
		copy.setId(info.id);
		copy.setCn_name(info.cn_name);
		copy.setImpl_type(info.impl_type);
		copy.setBk_desc(info.bk_desc);
		copy.setParams(info.params);
		copy.setOutputs(info.outputs);
		copy.extra = info.extra;
		copy.setCmds(info.cmds);
		copy.setAlias_name(info.alias_name);
		copy.addAllRef_params(info.getRef_params());
		copy.setSource_info(info.getSource_info());
		copy.setTemplate_phase_no(info.getTemplate_phase_no());
		return copy;
	}

	public static ModuleInfo copyToChild(ModuleBasicInfo info){
		ModuleInfo mi = (ModuleInfo) info;
		return copy(mi);
	}

	/**
	 * 构造函数
	 */
	public ModuleInfo() {
		super();
	}

	public static List<ModuleInfo> copyListToChildern(List<ModuleBasicInfo> infos){
		List<ModuleInfo> list = new ArrayList<ModuleInfo>();
		for(ModuleBasicInfo info : infos){
			list.add(copyToChild(info));
		}
		return list;
				
	}

	/**
	 * @return pakcage_types
	 */
	public List<PackageTypeInfo> getPakcage_types() {
		return pakcage_types;
	}

	/**
	 * @param pakcage_types
	 */
	public void setPakcage_types(List<PackageTypeInfo> pakcage_types) {
		this.pakcage_types = pakcage_types;
	}
	
}
