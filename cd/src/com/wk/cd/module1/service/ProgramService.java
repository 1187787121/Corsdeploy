/**
 * Title: ProgramService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年3月27日
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;
import com.wk.cd.module1.xml.XmlUtils;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.YN_FLAG;

/**
 * Class Description:
 * @author yangl
 */
public class ProgramService {
	
//	/**
//	 * Description: 根据阶段列表查询有效参数列表
//	 * @param phase_list 要求至少包含所有gen_flag为YES的阶段
//	 * @param params
//	 * @param ref_params
//	 * @return
//	 */
//	public List<ParamInfo> queryEffectiveParamByPhaseList(List<PhaseTestBean> phase_list, List<ParamInfo> params,
//			List<ParamInfo> ref_params) {
//		List<String> effective_params = new ArrayList<String>();
//		Map<Integer, List<ParamInfo>> map = XmlUtils.getRefParamsMap(ref_params);
//		if (!Assert.isEmpty(phase_list)) {
//			for (PhaseTestBean phase : phase_list) {
//				if (phase.getGen_flag() == YN_FLAG.YES) {
//					List<ParamInfo> phase_params = XmlUtils.matchParams(phase.getCmds());
//					phase_params = XmlUtils.uniqueParams(phase_params);
//					List<ParamInfo> phase_ref_params = map.get(phase.getPhase_no());
//					if (!Assert.isEmpty(phase_ref_params)) {
//						phase_params = XmlUtils.removeParams(phase_params, phase_ref_params);
//						phase_params.addAll(phase_ref_params);
//					}
//					for (ParamInfo phase_param : phase_params) {
//						String param_name = Assert.isEmpty(phase_param.getRef()) ? phase_param.getParam_name()
//								: phase_param.getRef();
//						if (!effective_params.contains(param_name)) {
//							effective_params.add(param_name);
//						}
//					}
//				}
//			}
//		}
//		if (!Assert.isEmpty(params)) {
//			for (ParamInfo param : params) {
//				if (effective_params.contains(param.getParam_name())) {
//					param.setGen_flag(YN_FLAG.YES);
//				} else {
//					param.setGen_flag(YN_FLAG.NO);
//				}
//			}
//		}
//		return params;
//	}


	/**
	 * Description: 根据模板信息查询包类型列表
	 * @param template
	 * @return
	 */
	public List<PackageTypeInfo> getPackageTypesFromTemplate(TemplateInfo template) {
		return template.getPackage_types();
	}

	/**
	 * Description: 根据模板信息查询阶段列表
	 * @param template
	 * @return
	 */
	public List<PhaseTestBean> getPhaseListFromTemplate(TemplateInfo template) {
		List<PhaseTestBean> phase_list = new ArrayList<PhaseTestBean>();
		List<ModuleBasicInfo> modules = template.getModules();
		if (!Assert.isEmpty(modules)) {
			int phase_no = 1;
			for (ModuleBasicInfo module : modules) {
				if (module.getType() == MODULE_TYPE.COMPONENT || module.getType() == MODULE_TYPE.PHASE) {
					phase_list.add(parsePhase(module, phase_no++));
				} else if (module.getType() == MODULE_TYPE.GROUP) {
					for (ModuleBasicInfo module1 : module.getModules()) {
						phase_list.add(parsePhase(module1, phase_no++));
					}
				}
			}
		}
		return phase_list;
	}

	private PhaseTestBean parsePhase(ModuleBasicInfo module, int phase_no) {
		PhaseTestBean phase = new PhaseTestBean();
		phase.setCn_name(Assert.isEmpty(module.getAlias_name()) ? module.getCn_name() : module.getAlias_name());
		phase.setCmds(XmlUtils.replaceRefParams(module.getCmds(), module.getRef_params()));
		phase.setGen_flag(YN_FLAG.YES);
		phase.setSelectable_flag(YN_FLAG.YES);
		phase.setPhase_no(phase_no);
		phase.setImpl_type(module.getImpl_type());
		return phase;
	}

	/**
	 * Description: 根据模板信息查询参数列表
	 * @param template
	 * @return
	 */
	public List<ParamInfo> getParamsFromTemplate(TemplateInfo template) {
		List<ParamInfo> param_list = new ArrayList<ParamInfo>();
		ParamInfo[] params = template.getParams();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo param : params) {
				param.setGen_flag(YN_FLAG.YES);
				param_list.add(param);
			}
		}
		return param_list;
	}

	/**
	 * Description: 根据模板信息查询引用参数
	 * @param template
	 * @return
	 */
	public List<ParamInfo> getRefParamsFromTemplate(TemplateInfo template) {
		List<ParamInfo> ref_params = new ArrayList<ParamInfo>();
		ParamInfo[] params = template.getAll_params();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo param : params) {
				if (!Assert.isEmpty(param.getRef())) {
					ref_params.add(param);
				}
			}
		}
		return ref_params;
	}






}
