/**
 * Title: XmlReader.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.module1.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wk.cd.module1.bean.NodeBean;
import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.exc.CheckElementNodeIsEmptyException;
import com.wk.cd.module1.exc.GetFileDocumentException;
import com.wk.cd.module1.info.GroupModuleInfo;
import com.wk.cd.module1.info.InstanceInfo;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.PackageCombine;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.Script;
//import com.wk.cl.business.entity.ProgramBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.PARAM_TYPE;
//import com.wk.cd.enu.PROG_SOURCE;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.xml.XmlPathUtil;
import com.wk.cd.module1.xml.XmlReader;
import com.wk.cd.module1.xml.XmlTags;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.lang.AppException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.JSON;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class XmlReader {

	private final Document document;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 根据路径读取组件
	 * @param file_path
	 * @return
	 */
	public static ModuleInfo readerModuleByPath(String file_path) {
		XmlReader reader = XmlReader.getInstanceByPath(file_path, MODULE_TYPE.COMPONENT);
		return readerModule(reader, "default");
	}

	/**
	 * Description: 根据ID读取组件
	 * @return
	 */
	public static ModuleInfo readerModule(String id) {
		return readerModule(id, "default");
	}

	/**
	 * Description: 根据路径读取组件
	 * @param file_path
	 * @return
	 */
	public static ModuleInfo readerModuleByPath(String file_path, String script_type) {
		XmlReader reader = XmlReader.getInstanceByPath(file_path, MODULE_TYPE.COMPONENT);
		return readerModule(reader, script_type);
	}

	/**
	 * Description: 根据ID读取组件
	 * @return
	 */
	public static ModuleInfo readerModule(String id, String script_type) {
		XmlReader reader = XmlReader.xmlReaderInstanceFactory(id, MODULE_TYPE.COMPONENT);
		return readerModule(reader, script_type);
	}

	/**
	 * Description: 根据路径读取组件组
	 * @param file_path
	 * @return
	 */
	public static GroupModuleInfo readerGroupByPath(String file_path) {
		XmlReader reader = XmlReader.getInstanceByPath(file_path, MODULE_TYPE.GROUP);
		return readerGroup(reader);
	}

	/**
	 * Description: 根据ID读取组件组
	 * @param id
	 * @return
	 */
	public static GroupModuleInfo readerGroup(String id) {
		XmlReader reader = XmlReader.xmlReaderInstanceFactory(id, MODULE_TYPE.GROUP);
		return readerGroup(reader);
	}

	// /**
	// * Description: 根据路径读取模板
	// * @param file_path
	// * @return
	// */
	// public static TemplateInfo readTemplateByPath(String file_path) {
	// XmlReader reader = XmlReader.getInstanceByPath(file_path,
	// MODULE_TYPE.TEMPLATE);
	// return readTemplate(reader);
	// }
	//
	// /**
	// * Description: 根据ID读取模板
	// * @param id
	// * @return
	// */
	// public static TemplateInfo readTemplate(String id) {
	// XmlReader reader = XmlReader.xmlReaderInstanceFactory(id,
	// MODULE_TYPE.TEMPLATE);
	// return readTemplate(reader);
	// }

	/**
	 * Description: 根据ID读取实例
	 * @param id
	 * @return
	 */
	public static InstanceInfo readInstance(String id) {
		XmlReader reader = XmlReader.xmlReaderInstanceFactory(id, MODULE_TYPE.INSTANCE);
		return readInstance(reader);
	}

	public static InstanceInfo readInstanceByPath(String file_path) {
		XmlReader reader = XmlReader.getInstanceByPath(file_path, MODULE_TYPE.INSTANCE);
		return readInstance(reader);
	}




/*
	public static ProgramBean readFromXmlById(String id) {
		XmlReader reader = XmlReader.xmlReaderInstanceFactory(id, MODULE_TYPE.PROGRAM);
		return readFromXml(reader);
	}
	
	public static ProgramBean readFromXmlByPath(String file_path) {
		XmlReader reader = XmlReader.getInstanceByPath(file_path, MODULE_TYPE.PROGRAM);
		return readFromXml(reader);
	}
	
	public static ProgramBean readFromXml(XmlReader reader) {
		ProgramBean bean = new ProgramBean();
		Element program = reader.getDocumentSubElement(XmlTags.PROGRAM);
		//获取ProgramBean父类的信息
		bean.setProg_id(program.getAttribute(XmlTags.PROGID));
		bean.setBusiness_sys_name(program.getAttribute(XmlTags.BUSINESSSYSNAME));
		String publish_state = program.getAttribute(XmlTags.PUBLISHSTATE);
		bean.setPublish_state(PUBLISH_STATE.valueOf(PUBLISH_STATE.class, publish_state));
		bean.setProg_cn_name(program.getAttribute(XmlTags.PROGCNNAME));
		bean.setProg_bk_desc(program.getAttribute(XmlTags.PROGBKDESC));
		
		String prog_source = program.getAttribute(XmlTags.PROGSOURCE);
		bean.setProg_source(PROG_SOURCE.valueOf(PROG_SOURCE.class, prog_source));
		
		String prog_type = program.getAttribute(XmlTags.PROGTYPE);
		bean.setProg_type(PROG_TYPE.valueOf(PROG_TYPE.class, prog_type));
		bean.setPac_type(program.getAttribute(XmlTags.PAC_TYPE));
		
		NodeList stage_list = program.getElementsByTagName(XmlTags.STAGE);
		if(!Assert.isEmpty(stage_list)) {
			//获取方案阶段信息
			List<PhaseTestBean> phase_list = new ArrayList<PhaseTestBean>();
			for (int i = 0; i < stage_list.getLength(); i++) {
				PhaseTestBean phaseBean = new PhaseTestBean();
				Element stage = (Element) stage_list.item(i);
				phaseBean.setPhase_no(Integer.parseInt(stage.getAttribute(XmlTags.PHASENO)));
				phaseBean.setCn_name(stage.getAttribute(XmlTags.CNNAME));
				phaseBean.setAlias_name(stage.getAttribute(XmlTags.ALIAS));
				phaseBean.setGen_flag(YN_FLAG.valueOf(YN_FLAG.class, stage.getAttribute(XmlTags.GENFLAG)));
				phaseBean.setType(MODULE_TYPE.valueOf(MODULE_TYPE.class, stage.getAttribute(XmlTags.MODULETYPE)));
				phaseBean.setBk_desc(stage.getAttribute(XmlTags.BKDESC));
				phaseBean.setInteractor_flag(Boolean.valueOf(stage.getAttribute(XmlTags.INTERACTOR)));
				phaseBean.setParallel_flag(Boolean.valueOf(stage.getAttribute(XmlTags.PARALLEL)));
				//获取命令和执行类别
				Element script = reader.getSubElement(stage, XmlTags.SCRIPT, true);
				String type = script.getAttribute(XmlTags.TYPE);
				phaseBean.setImpl_type(reader.judgeImplType(type));
				String[] cmds = getCmd(script.getTextContent());
				phaseBean.setCmds(cmds);
				//获取配置文件节点列表
				Element configs = reader.getSubElement(stage, XmlTags.CONFIGS, false);
				if (!Assert.isEmpty(configs)) {
					phaseBean.setConfig_nodes(reader.getConfigs(configs));
				}
				//服务器ip和数据源
				Element node = reader.getSubElement(stage, XmlTags.NODESOC, false);
				if (!Assert.isEmpty(node)) {
					phaseBean.setSrv_soc(reader.getStageSource(node));
				}
				//获取阶段使用投产包列表
				Element packName = reader.getSubElement(stage, XmlTags.PKCNAME, false);
				if(!Assert.isEmpty(packName)) {
					String[] package_names = getpackageNames(packName.getTextContent());
					phaseBean.setPackage_names(package_names);
				}

				//获取组件参数
				Element params_ele = reader.getSubElement(stage, XmlTags.PARAMS, false);
				ParamInfo[] params = reader.getParamBeans(params_ele);
				if(!Assert.isEmpty(params)){
					phaseBean.setParam_list(Arrays.asList(params));
				}
				//组件参数名列表
				Element ref_params_ele = reader.getSubElement(stage, XmlTags.REFPARAMS, false);
				List<ParamInfo> ref_params = reader.getRefParams(ref_params_ele);
				if(!Assert.isEmpty(ref_params)) {
					phaseBean.setRef_params(ref_params);
				}
//				List<ParamInfo> ref_params = reader.getRefParams2(stage, XmlTags.REFPARAMS, false);
//				if(!Assert.isEmpty(ref_params)) {
//					phaseBean.setRef_params(ref_params);
//				}
				phase_list.add(phaseBean);
			}
			
			bean.setPhase_list(phase_list);
		}
		//方案参数列表
		Element params_ele = reader.getSubElement(program, XmlTags.ALLPARAMS, false);
		ParamInfo[] params = reader.getParamBeans(params_ele);
		if(!Assert.isEmpty(params)){
			bean.setParam_list(Arrays.asList(params));
		}
		//投产包类型列表
		Element package_ele = reader.getSubElement(program, XmlTags.PKTYPES, false);
		if (!Assert.isEmpty(package_ele)) {
			List<PackageTypeInfo> package_types = reader.getPkTypes(package_ele);
			bean.setPackage_types(package_types);
		}
		
		return bean;
	}*/
	


	@SuppressWarnings("unchecked")
	private Map<String, PackageCombine> getPkCombines(Element combines_ele) {
		Map<String, PackageCombine> combines = new HashMap<String, PackageCombine>();
		if (!Assert.isEmpty(combines_ele)) {
			NodeList combines_node = combines_ele.getElementsByTagName(XmlTags.PKCOMBINE);
			if (!Assert.isEmpty(combines_node)) {
				for (int i = 0; i < combines_node.getLength(); i++) {
					Element combine = (Element) combines_node.item(i);
					String id = combine.getAttribute(XmlTags.ID);
					String gen_phase_json = getSubElement(combine, "gen" + XmlTags.PHASENO, false).getTextContent();
					ServiceData gen_sd = JSON.toServiceData("{phase:" + gen_phase_json + "}");
					int[] gen_phase_nos = gen_sd.getIntArray("phase");
					List<Integer> gen_phase_no_list = new ArrayList<Integer>();
					if (!Assert.isEmpty(gen_phase_nos)) {
						for (Integer no : gen_phase_nos) {
							gen_phase_no_list.add(no);
						}
					}
					String sel_phase_json = getSubElement(combine, "sel" + XmlTags.PHASENO, false).getTextContent();
					ServiceData sel_sd = JSON.toServiceData("{phase:" + sel_phase_json + "}");
					int[] sel_phase_nos = sel_sd.getIntArray("phase");
					List<Integer> sel_phase_no_list = new ArrayList<Integer>();
					if (!Assert.isEmpty(sel_phase_nos)) {
						for (Integer no : sel_phase_nos) {
							sel_phase_no_list.add(no);
						}
					}

					String param_json = getSubElement(combine, XmlTags.PARAM, false).getTextContent();
					ServiceData param_sd = JSON.toServiceData("{param:" + param_json + "}");
					List<String> param_name_list = param_sd.getList("param");
					PackageCombine pk_combine = new PackageCombine();
					pk_combine.setGen_phase_list(gen_phase_no_list);
					pk_combine.setSelectable_phase_list(sel_phase_no_list);
					pk_combine.setParam_name_list(param_name_list);
					combines.put(id, pk_combine);
				}
			}
		}
		return combines;
	}

	private static InstanceInfo readInstance(XmlReader reader) {

		Element instance = reader.getDocumentSubElement(XmlTags.INSTANCE);
		String instance_id = instance.getAttribute(XmlTags.ID);
		InstanceInfo ins = new InstanceInfo(instance_id, null, null);
		NodeList stage_list = instance.getElementsByTagName(XmlTags.STAGE);
		if (!Assert.isEmpty(stage_list)) {
			for (int i = 0; i < stage_list.getLength(); i++) {
				ModuleInfo mi = new ModuleInfo();
				Element stage = (Element) stage_list.item(i);
				String desc = stage.getAttribute(XmlTags.DESC);
				String id = stage.getAttribute(XmlTags.ID);
				Element script = reader.getSubElement(stage, XmlTags.SCRIPT, true);
				String type = script.getAttribute(XmlTags.TYPE);
				String[] cmds = getCmd(script.getTextContent());
				mi.setCmds(cmds);
				mi.setImpl_type(reader.judgeImplType(type));
				mi.setCn_name(desc);
				mi.setId(id);
				Element node = reader.getSubElement(stage, XmlTags.NODESOC, true);
				mi.setSource_info(reader.getSource(node));
				ins.addModuleInfo(mi);
			}
		}
		Element params_ele = reader.getSubElement(instance, XmlTags.PARAMS, false);
		ParamInfo[] params = reader.getParamBeans(params_ele);
		ins.setParams(params);
		return ins;
	}

	// /**
	// * Description: 模板读取方法
	// * @return
	// */
	// private static TemplateInfo readTemplate(XmlReader reader) {
	// TemplateInfo template_info = new TemplateInfo();
	// Element template = reader.getDocumentSubElement(XmlTags.TEMPLATE);
	// template_info.setId(template.getAttribute(XmlTags.ID));
	// String cn_name = template.getAttribute(XmlTags.CNNAME);
	// Element desc = reader.getSubElement(template, XmlTags.DESC, false);
	// template_info.setCn_name(cn_name);
	// if (!Assert.isEmpty(desc) && !Assert.isEmpty(desc.getTextContent())) {
	// template_info.setBk_desc(desc.getTextContent().trim());
	// }
	// Element params_ele = reader.getSubElement(template, XmlTags.PARAMS,
	// false);
	// ParamInfo[] params = reader.getParamBeans(params_ele);
	//
	// Element package_type = reader.getSubElement(template, XmlTags.PKTYPES,
	// false);
	// List<PackageTypeInfo> package_types = reader.getPkTypes(package_type);
	// NodeList children = template.getChildNodes();
	// if (!Assert.isEmpty(children)) {
	// int index = 1;
	// for (int i = 0; i < children.getLength(); i++) {
	// Node node = children.item(i);
	// String tag = node.getNodeName();
	// if (tag.equalsIgnoreCase(XmlTags.REFERENCES)) {
	// Element child = (Element) children.item(i);
	//
	// ModuleBasicInfo module = reader.paserReference(child);
	// if (MODULE_TYPE.GROUP.equals(module.getType())) {
	// GroupModuleInfo group = GroupModuleInfo.copyByBasic(module);
	// GroupModuleInfo group1 = GroupModuleInfo.copyByBasic(module);
	// group1.cleanModules();
	// List<ModuleBasicInfo> list = group.getModules();
	// for (ModuleBasicInfo info : list) {
	// info.setRef_params(ParamInfo.getParamByPhaseNo(params, index++));
	// group1.addModule(info);
	// template_info.addSub_moduleInfo(ModuleInfo.copyToChild(info));
	// }
	// template_info.addModule(group1);
	// } else if (MODULE_TYPE.COMPONENT.equals(module.getType())) {
	// module.addAllRef_params(ParamInfo.getParamByPhaseNo(params, index++));
	// template_info.addModule(module);
	// template_info.addSub_moduleInfo(ModuleInfo.copyToChild(module));
	// }
	//
	// } else if (tag.equalsIgnoreCase(XmlTags.STAGE)) {
	// Element child = (Element) children.item(i);
	// template_info.addModule(reader.paserStage(child));
	// template_info.addSub_moduleInfo(ModuleInfo.copyToChild(reader.paserStage(child)));
	// index++;
	// }
	// }
	// }
	// List<ParamInfo> pl = ParamInfo.PhaseNoMinus(
	// params == null || params.length == 0 ? new ArrayList<ParamInfo>() :
	// Arrays.asList(params));
	// template_info.setAll_params(pl.toArray(new ParamInfo[pl.size()]));
	// template_info.setParams(ParamInfo.getGeneralParams(params));
	// template_info.setPackage_types(package_types);
	// return template_info;
	// }

	private static ModuleInfo readerOldModule(XmlReader reader) {
		ModuleInfo module_info = new ModuleInfo();
		Element component = reader.getDocumentSubElement(XmlTags.COMPONENT);
		String inner_id = component.getAttribute(XmlTags.ID);
		String cn_name = component.getAttribute(XmlTags.CNNAME);
		Element desc = reader.getSubElement(component, XmlTags.DESC, false);
		module_info.setId(inner_id);
		module_info.setCn_name(cn_name);
		module_info.setBk_desc(desc.getTextContent());
		module_info.setType(MODULE_TYPE.COMPONENT);
		Element script = reader.getSubElement(component, XmlTags.SCRIPT, true);
		String type = script.getAttribute(XmlTags.TYPE);
		module_info.setImpl_type(reader.judgeImplType(type));
		String cmd = script.getTextContent();
		if (!Assert.isEmpty(cmd)) {
			String[] cmds = reader.getCmd(cmd);
			module_info.setCmds(cmds);
		}
		Element params_ele = reader.getSubElement(component, XmlTags.PARAMS, false);
		ParamInfo[] params = reader.getParamBeans(params_ele);
		module_info.setParams(params);
		return module_info;
	}

	private static ModuleInfo readerModule(XmlReader reader, String script_type) {
		ModuleInfo module_info = new ModuleInfo();
		Element component = reader.getDocumentSubElement(XmlTags.COMPONENT);
		String id = component.getAttribute(XmlTags.ID);
		String cn_name = component.getAttribute(XmlTags.CNNAME);
		String type = component.getAttribute(XmlTags.TYPE);
		Element desc = reader.getSubElement(component, XmlTags.DESC, false);
		module_info.setId(id);
		module_info.setCn_name(cn_name);
		module_info.setImpl_type(reader.judgeImplType(type));
		module_info.setBk_desc(desc.getTextContent());
		module_info.setType(MODULE_TYPE.COMPONENT);
		Element scripts = reader.getSubElement(component, XmlTags.SCRIPTS, true);
		NodeList children = scripts.getElementsByTagName(XmlTags.SCRIPT);
		if (!Assert.isEmpty(children)) {
			for (int i = 0; i < children.getLength(); i++) {
				Script script = new Script();
				Node child = children.item(i);
				Element ele = (Element) child;
				script.setScript_type(ele.getAttribute(XmlTags.TYPE));
				script.setCmds(getCmd(ele.getTextContent()));
				module_info.addScript_list(script);
				if (script_type.equalsIgnoreCase(script.getScript_type())) {
					module_info.setCmds(script.getCmds());
				}
			}
			if (Assert.isEmpty(module_info.getCmds())) {
				throw new AppException("组件[" + id + "]不包含类型为[" + script_type + "]的脚本！");
			}
		}
		Element params_ele = reader.getSubElement(component, XmlTags.PARAMS, false);
		ParamInfo[] params = reader.getParamBeans(params_ele);
		module_info.setParams(params);
		return module_info;
	}

	private static GroupModuleInfo readerGroup(XmlReader reader) {
		GroupModuleInfo group_info = new GroupModuleInfo();
		Element group = reader.getDocumentSubElement(XmlTags.GROUP);
		String inner_id = group.getAttribute(XmlTags.ID);
		String cn_name = group.getAttribute(XmlTags.CNNAME);
		Element desc = reader.getSubElement(group, XmlTags.DESC, false);
		group_info.setId(inner_id);
		group_info.setCn_name(cn_name);
		group_info.setBk_desc(desc.getTextContent());
		group_info.setType(MODULE_TYPE.GROUP);
		Element params_ele = reader.getSubElement(group, XmlTags.PARAMS, false);
		ParamInfo[] params = reader.getParamBeans(params_ele);
		NodeList children = group.getChildNodes();
		int index = 1;
		if (!Assert.isEmpty(children)) {
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				String tag = child.getNodeName();
				if (XmlTags.REFERENCES.equalsIgnoreCase(tag)) {
					Element ele = (Element) child;
					List<ParamInfo> private_params = ParamInfo.getParamByPhaseNo(params, index++);
					ModuleBasicInfo module = reader.paserReference(ele);
					module.addAllRef_params(private_params);
					group_info.addModule(module);
				} else if (XmlTags.STAGE.equalsIgnoreCase(tag)) {
					Element ele = (Element) child;
					group_info.addModule(reader.paserStage(ele));
					index++;
				}

			}
		}
		group_info.setParams(ParamInfo.getGeneralParams(params));
		return group_info;

	}

	private DocumentBuilder getDocumentBuilder() {
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new GetFileDocumentException();
		}
		return db;
	}

	private File judgeFileExist(String path) {
		if (Assert.isEmpty(path)) {
			throw new FileNotExistException().addScene("FILE", path);
		}
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotExistException().addScene("FILE", path);
		}
		return file;
	}

	private Element getDocumentSubElement(String tag) {
		NodeList node_list = document.getElementsByTagName(tag);
		if (node_list == null || node_list.getLength() == 0) {
			throw new CheckElementNodeIsEmptyException().addScene("TAG", tag);
		}
		Element element = (Element) node_list.item(0);
		return element;
	}

	private Element getSubElement(Element element, String tag, boolean check_null) {
		NodeList node_list = element.getElementsByTagName(tag);
		if (check_null && (node_list == null || node_list.getLength() == 0)) {
			throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG", tag);
		}
		return (Element) node_list.item(0);
	}

	private static String[] getCmd(String cmd) {
		List<String> cmds = new ArrayList<String>();
		String[] cmd_arr = cmd.split("\n");
		for (String str : cmd_arr) {
			if (!Assert.isEmpty(str)) {
				cmds.add(str.trim());
			}
		}
		return cmds.toArray(new String[cmds.size()]);

	}
	
	public static String[] getpackageNames(String cmd) {
		List<String> cmds = new ArrayList<String>();
		String[] cmd_arr = cmd.split(",");
		for (String str : cmd_arr) {
			if (!Assert.isEmpty(str)) {
				cmds.add(str.trim());
			}
		}
		return cmds.toArray(new String[cmds.size()]);

	}
	
	private MODULE_TYPE judgeCompType(String type) {
		if ("component".equalsIgnoreCase(type)) {
			return MODULE_TYPE.COMPONENT;
		} else if ("group".equalsIgnoreCase(type)) {
			return MODULE_TYPE.GROUP;
		} else if ("template".equalsIgnoreCase(type)) {
			return MODULE_TYPE.TEMPLATE;
		} else if ("phase".equalsIgnoreCase(type)) {
			return MODULE_TYPE.PHASE;
		} else {
			return null;
		}
	}

	private IMPL_TYPE judgeImplType(String type) {
		if ("shell".equalsIgnoreCase(type)) {
			return IMPL_TYPE.SHELL;
		} else if ("ftp".equalsIgnoreCase(type)) {
			return IMPL_TYPE.FTP;
		} else if ("was".equalsIgnoreCase(type)) {
			return IMPL_TYPE.WAS;
		} else if ("svn".equalsIgnoreCase(type)) {
			return IMPL_TYPE.SVN;
		} else if ("weblogic".equalsIgnoreCase(type)) {
			return IMPL_TYPE.WEBLOGIC;
		} else if ("jdbc".equalsIgnoreCase(type)) {
			return IMPL_TYPE.SQL;
		} else {
			return null;
		}
	}

	/**
	 * Description: 解析引用
	 * @param reference
	 * @return
	 */
	private ModuleBasicInfo paserReference(Element reference) {
		ModuleBasicInfo bean = null;
		String type = reference.getAttribute(XmlTags.TYPE).trim();
		MODULE_TYPE comp_type = judgeCompType(type);
		String id = reference.getAttribute(XmlTags.ID).trim();
		String alias = reference.getAttribute(XmlTags.ALIAS);
		Assert.assertNotEmpty(id, "引用的ID不能为空");
		if (MODULE_TYPE.COMPONENT.equals(comp_type)) {
			bean = readerModule(id);
			bean.setType(MODULE_TYPE.COMPONENT);
		} else if (MODULE_TYPE.GROUP.equals(comp_type)) {
			bean = XmlReader.readerGroup(id);
			bean.setType(MODULE_TYPE.GROUP);
		}
		if (!Assert.isEmpty(bean)) {
			bean.setId(id);
			if (!Assert.isEmpty(alias)) {
				bean.setAlias_name(alias);
			}
		}
		return bean;
	}

	private ModuleBasicInfo paserStage(Element stage) {
		ModuleBasicInfo bean = new ModuleInfo();
		bean.setCn_name(stage.getAttribute(XmlTags.NAME));
		Element script = getSubElement(stage, XmlTags.SCRIPT, true);
		String type = script.getAttribute(XmlTags.TYPE);
		String cmd = script.getTextContent();
		bean.setCmds(getCmd(cmd));
		bean.setImpl_type(judgeImplType(type));
		bean.setType(MODULE_TYPE.PHASE);
		return bean;
	}

	private ParamInfo[] getParamBeans(Element params) {
		List<ParamInfo> params_list = new ArrayList<ParamInfo>();
		if (params == null) {
			return null;
		}
		NodeList sub_params = params.getElementsByTagName(XmlTags.PARAM);
		if (!Assert.isEmpty(sub_params)) {
			for (int i = 0; i < sub_params.getLength(); i++) {
				Element param_ele = (Element) sub_params.item(i);
				String param_name = param_ele.getAttribute(XmlTags.NAME);
				String param_cn_name = param_ele.getAttribute(XmlTags.CNNAME);
				String group = param_ele.getAttribute(XmlTags.GROUP);
				String param_desc = param_ele.getAttribute(XmlTags.DESC);
				String default_value = param_ele.getAttribute(XmlTags.DEFAULT);
				String modify_flag = param_ele.getAttribute(XmlTags.MODIFY);
				ParamInfo info = new ParamInfo();
				String hand = param_ele.getAttribute(XmlTags.HAND);
				if (!Assert.isEmpty(hand)) {
					info.setHand_param("true".equalsIgnoreCase(hand) ? true : false);
				}
				info.setParam_type(judgeParamType(param_ele.getAttribute(XmlTags.TYPE)));
				String phaseno = param_ele.getAttribute(XmlTags.PHASENO);
				if (!Assert.isEmpty(phaseno)) {
					info.setPhase_no(stringToInt(phaseno));
					info.setRef(param_ele.getAttribute(XmlTags.REF));
				}
				info.setParam_group(group);
				info.setParam_name(param_name);
				info.setParam_cn_name(param_cn_name);
				info.setParam_bk_desc(param_desc);
				info.setParam_value(default_value);
				info.setParam_modify_flag(getModifyFlag(modify_flag));
				info.setGen_flag(YN_FLAG.YES);
				params_list.add(info);
			}
		}
		return params_list.toArray(new ParamInfo[params_list.size()]);
	}

	private List<ParamInfo> getRefParams(Element ref_params_ele) {
		List<ParamInfo> ref_params = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(ref_params_ele)) {
			NodeList params = ref_params_ele.getElementsByTagName(XmlTags.PARAM);
			if (!Assert.isEmpty(params)) {
				for (int i = 0; i < params.getLength(); i++) {
					Element param_ele = (Element) params.item(i);
					String param_name = param_ele.getAttribute(XmlTags.NAME);
					String param_cn_name = param_ele.getAttribute(XmlTags.CNNAME);
					String param_desc = param_ele.getAttribute(XmlTags.DESC);
					String phaseno = param_ele.getAttribute(XmlTags.PHASENO);
					ParamInfo info = new ParamInfo();
					Integer phase_no = stringToInt(phaseno);
					info.setPhase_no(phase_no);
					info.setRef(param_ele.getAttribute(XmlTags.REF));
					info.setParam_name(param_name);
					info.setParam_cn_name(param_cn_name);
					info.setParam_bk_desc(param_desc);
					info.setParam_type(judgeParamType(param_ele.getAttribute(XmlTags.TYPE)));
					ref_params.add(info);
				}
			}
		}
		return ref_params;
	}

	/**
	 * Description: 获得引用参数
	 * @param ref_params_ele
	 * @param b
	 * @param refparams
	 * @return
	 */
	private List<ParamInfo> getRefParams2(Element element, String tag, boolean check_null) {
		NodeList node_list = element.getElementsByTagName(tag);
		if (check_null && (node_list == null || node_list.getLength() == 0)) {
			throw new CheckElementNodeIsEmptyException().addScene("ELEMENT", element.getTagName()).addScene("TAG", tag);
		}
		List<ParamInfo> ref_params = new ArrayList<ParamInfo>();
		int ref_size = node_list.getLength();
		for (int i = 0; i < ref_size; i++) {
			Element ref_params_ele = (Element) node_list.item(i);
			NodeList ref_list = ref_params_ele.getElementsByTagName(XmlTags.REFPARAM);
			for (int k = 0; k < ref_list.getLength(); k++) {
				ParamInfo paramInfo = new ParamInfo();
				Element ref_param = (Element) ref_list.item(k);

				String param_bk_desc = ref_param.getAttribute(XmlTags.PARAMBKDESC);
				paramInfo.setParam_bk_desc(param_bk_desc);

				String param_cn_name = ref_param.getAttribute(XmlTags.PARAMCNNAME);
				paramInfo.setParam_cn_name(param_cn_name);

				String param_group = ref_param.getAttribute(XmlTags.PARAMGROUP);
				paramInfo.setParam_group(param_group);

				String param_name = ref_param.getAttribute(XmlTags.PARAMNAME);
				paramInfo.setParam_name(param_name);

				String param_type = ref_param.getAttribute(XmlTags.PARAMTYPE);
				paramInfo.setParam_type(PARAM_TYPE.valueOf(PARAM_TYPE.class, param_type));

				String param_result = ref_param.getAttribute(XmlTags.PARAMRESULT);
				paramInfo.setRef(param_result);

				String param_index = ref_param.getAttribute(XmlTags.PARAMINDEX);
				paramInfo.setIndex(Integer.valueOf(param_index));

				String hand_param = ref_param.getAttribute(XmlTags.PARAMISUSED);
				paramInfo.setHand_param(Boolean.valueOf(hand_param));

				ref_params.add(paramInfo);
			}
		}
		return ref_params;
	}

	private List<PackageTypeInfo> getPkTypes(Element params) {
		List<PackageTypeInfo> package_list = new ArrayList<PackageTypeInfo>();
		if (params == null) {
			return null;
		}
		NodeList sub_params = params.getElementsByTagName(XmlTags.PKTYPE);
		if (!Assert.isEmpty(sub_params)) {
			for (int i = 0; i < sub_params.getLength(); i++) {
				Element pk_ele = (Element) sub_params.item(i);
				String name = pk_ele.getAttribute(XmlTags.PKNAME);
				String cn_name = pk_ele.getAttribute(XmlTags.PKCNNAME);
				PackageTypeInfo info = new PackageTypeInfo(name, cn_name);
				package_list.add(info);
			}
		}
		return package_list;
	}

	private ModuleSourceInfo getSource(Element ele) {
		String json = ele.getTextContent();
		ModuleSourceInfo msi = ModuleSourceInfo.getInfoFromJson(json);
		return msi;
	}

	/**
	 * Description: 方案中阶段配置的数据源
	 * @param json
	 * @return
	 */
	private StageSourceBean[] getStageSource(Element ele) {
		String jsones = ele.getTextContent();
		String[] json = jsones.split(XmlTags.SPLIT);
		StageSourceBean[] ssb = new StageSourceBean[json.length];
		int index = 0;
		for (String j : json) {
			StageSourceBean bean = StageSourceBean.getInfoFromJson(j);
			ssb[index++] = bean;
		}
		return ssb;

	}

	/**
	 * Description: 方案阶段中的ip 如果是配置文件的阶段 这里就是ip
	 * @param ele
	 * @return
	 */
	private NodeBean[] getConfigs(Element ele) {
		String ips = ele.getTextContent();
		String[] nodes = ips.split(XmlTags.SPLIT);
		NodeBean[] node_beans = new NodeBean[nodes.length];
		for (int i = 0; i < node_beans.length; i++) {
			NodeBean node_baen = new NodeBean();
			String[] node = nodes[i].split("\\|");
			if (!Assert.isEmpty(node) && node.length > 1) {
				node_baen.setIp(node[1]);
			}
			node_baen.setName(node[0]);

			node_beans[i] = node_baen;
		}
		return node_beans;
	}

	private PARAM_TYPE judgeParamType(String type) {
		if ("pjparam".equalsIgnoreCase(type)) {
			return PARAM_TYPE.PJPARAM;
		} else if ("pddparam".equalsIgnoreCase(type)) {
			return PARAM_TYPE.PDDPARAM;
		} else {
			return null;
		}
	}

	private Integer stringToInt(String order) {
		if (StringUtils.isNumeric(order)) {
			return Integer.parseInt(order);
		} else {
			return 0;
			// TODO:
		}
	}

	private MODIFY_FLAG getModifyFlag(String str) {
		if ("yes".equalsIgnoreCase(str)) {
			return MODIFY_FLAG.YES;
		} else if ("no".equalsIgnoreCase(str)) {
			return MODIFY_FLAG.NO;
		}
		return null;
	}

	private XmlReader(String id, MODULE_TYPE comp_type, boolean flag) {
		String file_path = "";
		if (flag) {
			file_path = XmlPathUtil.getPathByCompId(id, comp_type);

		} else {
			file_path = id;
		}
		DocumentBuilder db = getDocumentBuilder();
		try {
			judgeFileExist(file_path);
			Document document1 = db.parse(file_path);
			this.document = document1;
		} catch (SAXException e) {
			e.printStackTrace();
			throw new GetFileDocumentException().addScene("PATH", file_path);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GetFileDocumentException().addScene("PATH", file_path);
		}
	}

	/**
	 * Description: 根据ID获取获取读取实例
	 * @param id
	 * @param comp_type
	 * @return
	 */
	private static XmlReader xmlReaderInstanceFactory(String id, MODULE_TYPE comp_type) {

		logger.debug("根据ID[{}]获取[{}]的读取实例", id, comp_type.getCname());
		XmlReader reader = new XmlReader(id, comp_type, true);
		return reader;
	}

	/**
	 * Description: 根据路径获取读取实例
	 * @param file_path
	 * @param comp_type
	 * @return
	 */
	private static XmlReader getInstanceByPath(String file_path, MODULE_TYPE comp_type) {
		logger.debug("根据路径[{}]获取[{}]的读取实例", file_path, comp_type.getCname());
		XmlReader reader = new XmlReader(file_path, comp_type, false);
		return reader;
	}

}
