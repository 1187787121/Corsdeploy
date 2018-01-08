package com.wk.cd.module1.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TEMPLATE_FORMATE;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.exc.InputStringIllegalException;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;
import com.wk.cd.module1.xml.XmlReader;
import com.wk.cd.module1.xml.XmlUtils;
import com.wk.lang.BaseException;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateService {

	@Inject
	MoTemplateDaoService moTemplateDaoService;
	private static final Log logger = LogFactory.getLog();
	private static final Pattern TEMPLATE_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{1,50}$");

	public void checkTemplateNameIsNotExist(String template_name) {
		logger.info("检测新增模板[{}]是否不存在，存在就抛异常", template_name);
		if (this.moTemplateDaoService.countByTemplateName(template_name) > 0)
			throw new RecordAlreadyExistException().addScene("TABLE", "投产模版表").addScene("FIELD", template_name);
	}

	public void checkTemplateNameIsExist(String template_name) {
		logger.info("检测模板名[{}]是否存在，不存在就抛异常", template_name);
		if (this.moTemplateDaoService.countByTemplateName(template_name) == 0)
			throw new RecordNotFoundException().addScene("TABLE", "投产模版表").addScene("FIELD", template_name);
	}

	public void checkTemplateNameIsRight(String template_name) {
		logger.info("检测模板名[{}]是否合法", template_name);
		Matcher matcher = TEMPLATE_NAME_PATTERN.matcher(template_name);
		if (!matcher.matches())
			throw new InputStringIllegalException().addScene("FIELD", "模版名称").addScene("STRING", template_name);
	}

	public MoTemplateInfo getTemplateInfo(String template_name) {
		return this.moTemplateDaoService.getInfoByKey(template_name);
	}

//	public TemplateInfo getInfoByTemplateName(String template_name) {
//		checkTemplateNameIsExist(template_name);
//		String id = this.moTemplateDaoService.getRefModuleIdByName(template_name);
//		Assert.assertNotEmpty(id, "流程模板" + template_name + "引用模板的ID");
//		return XmlReader.readTemplate(id);
//	}

	public TEMPLATE_FORMATE getTemplateFormate(String template_name) {
		return this.moTemplateDaoService.getInfoByKey(template_name).getTemplate_formate();
	}

	public List<String> queryAllTemplateName() {
		return this.moTemplateDaoService.queryAllTemplateName();
	}

	public List<String> queryTemplateNameByType(TEMPLATE_TYPE template_type) {
		return this.moTemplateDaoService.queryTemplateNameByType(template_type);
	}

//	public List<ParamInfo> queryEffectiveParamByPhaseNoList(String template_name, int[] phase_no_list) {
//		List<ParamInfo> param_list = null;
//		TemplateInfo template = getInfoByTemplateName(template_name);
//		List<ModuleBasicInfo> all_mudeles = template.getModules();
//		List<ModuleBasicInfo> mudeles = new ArrayList<ModuleBasicInfo>();
//		for (int phase_no : phase_no_list)
//			if (phase_no != 0) {
//				mudeles.add(all_mudeles.get(phase_no - 1));
//			}
//		param_list = XmlUtils.combineParams(mudeles, null);
//		return param_list;
//	}

//	public List<ParamInfo> queryEffectiveParamByPhaseList(String template_name, List<PhaseTestBean> phase_list) {
//		int length = phase_list.size();
//		int[] phase_no_list = new int[length];
//		for (int i = 0; i < length; i++) {
//			PhaseTestBean phase = phase_list.get(i);
//			if (phase.getGen_flag() == YN_FLAG.YES) {
//				phase_no_list[i] = phase.getPhase_no();
//			}
//		}
//		return queryEffectiveParamByPhaseNoList(template_name, phase_no_list);
//	}

//	public List<ParamInfo> queryEffectiveParamByPhaseList(String template_name, List<PhaseTestBean> phase_list, List<ParamInfo> param_list) {
//		int length = phase_list.size();
//		int[] phase_no_list = new int[length];
//		for (int i = 0; i < length; i++) {
//			PhaseTestBean phase = phase_list.get(i);
//			if (phase.getGen_flag() == YN_FLAG.YES) {
//				phase_no_list[i] = phase.getPhase_no();
//			}
//		}
//		return queryEffectiveParamByPhaseNoList(template_name, phase_no_list);
//	}
}