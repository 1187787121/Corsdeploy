/**
 * Title: InstanceManager.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.module1.service;

import java.util.Arrays;
import java.util.List;

import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.impl.DefaultEnv;
import com.wk.cd.module1.info.InstanceInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;

/**
 * Class Description:
 * @author zhangj
 */
public class InstanceManager {
	private static final Log logger = LogFactory.getLog();

	@Inject
	DtSocService dtSocService;

	/**
	 * Description: 这个生成实例的方法在cd项目里面被调用
	 * @param template 模板，里面主要是要存上参数的关系
	 * @param phase_list 主要是阶段是否生成 以及阶段的实现类别
	 * @param params
	 * @return
	 */
	public InstanceInfo prepare(TemplateInfo template, List<PhaseTestBean> phase_list, ParamInfo[] params) {
		DefaultEnv env = getEnvByParam(params);
		TemplateInfo template_info = new TemplateInfo();
		List<ModuleInfo> infos = template.getSub_ModuleInfos();
		template_info.setAll_params(template.getAll_params());
		template_info.setParams(template.getParams());
		int index = 0;
		for (PhaseTestBean bean : phase_list) {
			Assert.assertNotEmpty(infos.get(index), "模板中阶段" + index);
			ModuleInfo mi = infos.get(index);
			mi.setGen_yn_flag(bean.getGen_flag());
			template_info.addSub_moduleInfo(mi);
			if (YN_FLAG.NO.equals(bean.getGen_flag())) {
				index++;
				continue;
			}

			IMPL_TYPE type = bean.getImpl_type();
			StageSourceBean[] ssb = bean.getSrv_soc();
			Assert.assertNotEmpty(ssb, PhaseTestBean.SRV_SOCCN);
			for (StageSourceBean s_bean : ssb) {
				logger.debug("当前生成env数据源[{}],步骤号[{}].(步骤号从1开始)", s_bean.getExe_soc_name(), index + 1);
				Assert.assertNotEmpty(s_bean.getExe_soc_name(), StageSourceBean.EXE_SOC_NAMECN);
				PROTOCOL_TYPE protocol_type = s_bean.getExe_protocol_type();
				DtSourceInfo info = dtSocService.getInfoByKey(s_bean.getExe_soc_name());
				if (PROTOCOL_TYPE.AGENT.equals(protocol_type)) {
					info.setSoc_ip(s_bean.getExe_ip());

				}

				if (IMPL_TYPE.SVN.equals(type)) {
					ModuleSourceInfo msi = new ModuleSourceInfo(PROTOCOL_TYPE.SVN, info);
					msi.setProtocol_type(PROTOCOL_TYPE.SVN);
					Assert.assertNotEmpty(s_bean.getVer_soc_name(), StageSourceBean.VER_SOC_NAMECN);
					DtSourceInfo ver_info = dtSocService.getInfoByKey(s_bean.getVer_soc_name());
					msi.setData(getDataFromSourceInfo(ver_info, type));
					env.addStageNode(index, msi);
				} else if (IMPL_TYPE.WAS.equals(type)) {
					ModuleSourceInfo msi = new ModuleSourceInfo(PROTOCOL_TYPE.WAS, info);
					msi.setProtocol_type(PROTOCOL_TYPE.WAS);
					Assert.assertNotEmpty(s_bean.getVer_soc_name(), StageSourceBean.VER_SOC_NAMECN);
					DtSourceInfo ver_info = dtSocService.getInfoByKey(s_bean.getVer_soc_name());
					msi.setData(getDataFromSourceInfo(ver_info, type));
					env.addStageNode(index, msi);
				} else if (IMPL_TYPE.WEBLOGIC.equals(type)) {
					ModuleSourceInfo msi = new ModuleSourceInfo(PROTOCOL_TYPE.WEBLOGIC, info);
					msi.setProtocol_type(PROTOCOL_TYPE.WEBLOGIC);
					Assert.assertNotEmpty(s_bean.getVer_soc_name(), StageSourceBean.VER_SOC_NAMECN);
					DtSourceInfo ver_info = dtSocService.getInfoByKey(s_bean.getVer_soc_name());
					msi.setData(getDataFromSourceInfo(ver_info, type));
					env.addStageNode(index, msi);
				} else {
					ModuleSourceInfo msi = new ModuleSourceInfo(info.getProtocol_type(), info);
					env.addStageNode(index, msi);
				}
			}
			index++;
		}

		// InstanceInfo inst = ProcessManager.instance.prepare(template_info,
		// env);

		return null;

	}

	public DefaultEnv getEnvByParam(ParamInfo[] param_infos) {
		DefaultEnv env = new DefaultEnv();
		if (Assert.isEmpty(param_infos)) {
			return env;
		}
		for (ParamInfo info : param_infos) {
			logger.debug("当前处理的参数的名[{}],参数值[{}]", info.getParam_name(), info.getParam_value());
			Integer phase_no = info.getPhase_no();
			String value = info.getParam_value();
			// 如果是自定义参数，即带阶段号的参数，不放在参数表中，因为生成实例的实现是以参数名为map的key，加入进去会重复
			if ((phase_no != null && phase_no > 0) || Assert.isEmpty(value)) {
				continue;
			}
			String[] values = null;
			if (value.contains("-,-")) {
				values = value.split("-,-");
			} else {
				values = new String[] { value };
			}
			logger.debug("当前处理参数的值[{}]", Arrays.toString(values));
			ParamInfo pi = ParamInfo.copy(info);
			if (!Assert.isEmpty(pi.getParam_group())) {
				pi.setParam_name(info.getParam_group() + "." + info.getParam_name());
			}
			Param sub_p = new Param(pi, values);

			if (Assert.isEmpty(info.getParam_group())) {
				env.setParam(info.getParam_name(), sub_p);
			} else {
				String name = info.getParam_group() + "." + info.getParam_name();
				env.setParam(name, sub_p);
			}
		}
		return env;

	}

	public ServiceData getDataFromSourceInfo(DtSourceInfo info, IMPL_TYPE impl_type) {
		ServiceData data = new ServiceData();
		if (IMPL_TYPE.SVN.equals(impl_type)) {
			StringBuilder url = new StringBuilder();
			url.append(info.getBackup_fld()).append("://").append(info.getSoc_ip()).append(':')
					.append(info.getSoc_port()).append(info.getUser_root_path());
			data.putString("url", url.toString());
			data.putString("user", info.getRemote_uname());
			data.putString("password", info.getRemote_passwd());
			logger.debug("svn dtsource data[{}]", data.toString());
		} else if (IMPL_TYPE.WAS.equals(impl_type)) {
			// TODO
		} else if (IMPL_TYPE.WEBLOGIC.equals(impl_type)) {
			data.putString("url", "t3://" + info.getSoc_ip() + ":" + info.getSoc_port());
			data.putString("user", info.getRemote_uname());
			data.putString("password", info.getRemote_passwd());
			logger.debug("weblogic dtsource data[{}]", data.toString());
		}
		return data;
	}
}
