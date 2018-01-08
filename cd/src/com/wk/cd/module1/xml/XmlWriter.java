/**
 * Title: XmlWriter.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��12��
 */
package com.wk.cd.module1.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.module1.bean.NodeBean;
import com.wk.cd.module1.bean.PhaseTestBean;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.exc.GetFileDocumentException;
import com.wk.cd.module1.exc.WriterXmlException;
import com.wk.cd.module1.info.GroupModuleInfo;
import com.wk.cd.module1.info.InstanceInfo;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.PackageCombine;
import com.wk.cd.module1.info.PackageTypeInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.Script;
import com.wk.cd.module1.info.TemplateInfo;
//import com.wk.cl.business.entity.ProgramBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.module1.xml.XmlPathUtil;
import com.wk.cd.module1.xml.XmlTags;
import com.wk.cd.module1.xml.XmlWriter;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JSON;
import com.wk.util.JSONCaseType;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class XmlWriter {

	private static final Log logger = LogFactory.getLog();
	private Document document;

	private final String file_path;

	private final String id;

	private XmlWriter(String id, MODULE_TYPE comp_type) {
		DocumentBuilder db = getDocumentBuilder();
		Document document1 = db.newDocument();
		String file_path1 = XmlPathUtil.getPathByCompId(id, comp_type);
		this.document = document1;
		this.file_path = file_path1;
		this.id = id;
		document1.setXmlStandalone(true);

	}

	private static XmlWriter xmlWriterInstanceFactory(String id, MODULE_TYPE comp_type) {
		XmlWriter writer = new XmlWriter(id, comp_type);
		return writer;
	}
	
	
/*	public static void writerToXml(ProgramBean bean) {
		logger.debug("------writerProgram begin id" + bean.getProg_id() + "------");
		XmlWriter writer = xmlWriterInstanceFactory(bean.getProg_id(), MODULE_TYPE.PROGRAM);
		Element program = writer.document.createElement(XmlTags.PROGRAM);
		program.setAttribute(XmlTags.BUSINESSSYSNAME, bean.getBusiness_sys_name());
		program.setAttribute(XmlTags.PROGID, bean.getProg_id());
		program.setAttribute(XmlTags.PUBLISHSTATE,Assert.isEmpty(bean.getPublish_state()) ? null :  bean.getPublish_state().getCname());
		program.setAttribute(XmlTags.PROGCNNAME, bean.getProg_cn_name());
		program.setAttribute(XmlTags.PROGBKDESC, bean.getProg_bk_desc());
		program.setAttribute(XmlTags.PROGSOURCE, Assert.isEmpty(bean.getProg_source()) ? null : bean.getProg_source().getCname());
		program.setAttribute(XmlTags.PROGTYPE, Assert.isEmpty(bean.getProg_type()) ? null : bean.getProg_type().getCname());
		program.setAttribute(XmlTags.PAC_TYPE, bean.getPac_type());
		
		//�����׶���Ϣ
		List<PhaseTestBean> phase_list = bean.getPhase_list();
		if (!Assert.isEmpty(phase_list)) {
			for (PhaseTestBean phase : phase_list) {
				Element stage = getPhaseTestBean(writer, phase);
				program.appendChild(stage);
			}
		}
		//���������б�
		List<ParamInfo> param_list = bean.getParam_list();
		if (!Assert.isEmpty(param_list)) {
			Element params_ele = writer.getAllParamElement(param_list);
			program.appendChild(params_ele);
		}
		//Ͷ���������б�
		List<PackageTypeInfo> pakcage_types = bean.getPackage_types();
		if (!Assert.isEmpty(pakcage_types)) {
			Element pk_ele = writer.getPackageElement(pakcage_types);
			program.appendChild(pk_ele);
		}
		
		writer.document.appendChild(program);
		writer.writer();
	}*/

	/**
	 * Description: ��ȡPhaseTestBean
	 * @param program_info
	 */
	public static Element getPhaseTestBean(XmlWriter writer,PhaseTestBean phase){
		Element stage = writer.document.createElement(XmlTags.STAGE);

		stage.setAttribute(XmlTags.PHASENO, phase.getPhase_no() + "");
		stage.setAttribute(XmlTags.CNNAME, phase.getCn_name());
		stage.setAttribute(XmlTags.ALIAS, phase.getAlias_name());
		stage.setAttribute(XmlTags.GENFLAG, phase.getGen_flag().getCname());
		stage.setAttribute(XmlTags.MODULETYPE, phase.getType().getCname());
		stage.setAttribute(XmlTags.BKDESC, phase.getBk_desc());
		stage.setAttribute(XmlTags.INTERACTOR, String.valueOf(phase.isInteractor_flag()));
		stage.setAttribute(XmlTags.PARALLEL, String.valueOf(phase.isParallel_flag()));
		IMPL_TYPE type = phase.getImpl_type();
		String[] cmds = phase.getCmds();
		Element script = writer.script(cmds, type);
		stage.appendChild(script);
		//������ip������Դ
		StageSourceBean[] ssb = phase.getSrv_soc();
		if (!Assert.isEmpty(ssb)) {
			Element source = writer.source(ssb);
			stage.appendChild(source);
		}
		//�����ļ��ڵ��б�
		NodeBean[] config_nodes = phase.getConfig_nodes();
		if (!Assert.isEmpty(config_nodes)) {
			Element config = writer.configs(config_nodes);
			stage.appendChild(config);
		}
		// �׶�ʹ��Ͷ�����б����������Ҫ������cl��Ŀ��ÿ���׶���Ҫ����Ͷ����������Ͷ��������ȡͶ���������ã�
		String[] package_names = phase.getPackage_names();
		if (!Assert.isEmpty(package_names)) {
			Element pk_ele = writer.getPackName(package_names, XmlTags.PKCNAME);
			stage.appendChild(pk_ele);
		}
		//�������
		List<ParamInfo> params = phase.getParam_list();
		if (!Assert.isEmpty(params)) {
			Element params_ele = writer.getParamElement(params);
			stage.appendChild(params_ele);
		}
		//����������б�
		List<ParamInfo> ref_params = phase.getRef_params();
		if (!Assert.isEmpty(ref_params)) {
			Element ref_params_ele = writer.getRefParamElement(ref_params);
			stage.appendChild(ref_params_ele);
		}
		

		return stage;
	}

	public static void writerInstance(InstanceInfo instance_info) {
		XmlWriter writer = xmlWriterInstanceFactory(instance_info.getInstanceId(), MODULE_TYPE.INSTANCE);
		Element instance = writer.document.createElement(XmlTags.INSTANCE);
		instance.setAttribute(XmlTags.ID, instance_info.getInstanceId());
		List<ModuleInfo> modules = instance_info.getModuleInfos();
		int index = 0;
		for (ModuleInfo s : modules) {
			// д��׶ε����� ִ����� ˳��
			String desc = s.getCn_name();
			Element stage = writer.document.createElement(XmlTags.STAGE);
			stage.setAttribute("order", index++ + "");
			stage.setAttribute(XmlTags.DESC, desc);
			// �����µ�scriptԪ�� ���м�¼����ű�
			IMPL_TYPE type = s.getImpl_type();
			String[] cmds = s.getCmds();
			Element script = writer.script(cmds, type);
			stage.appendChild(script);

			ModuleSourceInfo msi = s.getSource_info();
			Element node = writer.getNodeSocElementList(msi);
			stage.appendChild(node);
			instance.appendChild(stage);
		}

		ParamInfo[] params = instance_info.getParams();
		if (!Assert.isEmpty(params)) {
			Element params_ele = writer.getParamElement(Arrays.asList(params));
			instance.appendChild(params_ele);
		}
		writer.document.appendChild(instance);
		writer.writer();
	}

	/**
	 * Description: д��ģ��
	 * @param template_info
	 */
	public static void writerTemplate(TemplateInfo template_info, String id) {
		XmlWriter writer = xmlWriterInstanceFactory(id, MODULE_TYPE.TEMPLATE);
		Element template = writer.document.createElement(XmlTags.TEMPLATE);
		template.setAttribute(XmlTags.ID, writer.id);
		String cn_name = template_info.getCn_name();
		String desc = template_info.getBk_desc();
		// д������������������
		writer.addDescAndCnname(cn_name, desc, template);
		List<ModuleBasicInfo> temp_list = template_info.getModules();
		// ����������������е����� �������׶Ρ����������飩����������뵽groupԪ����
		List<ParamInfo> private_params = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(temp_list)) {
			int index = 1;
			for (ModuleBasicInfo info : temp_list) {
				MODULE_TYPE module_type = info.getType();
				if (MODULE_TYPE.PHASE.equals(module_type)) {
					Element stage = writer.document.createElement(XmlTags.STAGE);
					stage.setAttribute("order", index++ + "");
					stage.setAttribute(XmlTags.NAME, info.getCn_name());
					stage.setAttribute(XmlTags.TYPE, info.getImpl_type().getName());
					Element script = writer.script(info.getCmds(), info.getImpl_type());
					stage.appendChild(script);
					template.appendChild(stage);
				} else if (MODULE_TYPE.COMPONENT.equals(module_type)) {
					Element references = writer.getReferenceElement(info, index);
					template.appendChild(references);

					info.getRef_params();
					private_params.addAll(ParamInfo.setPhaseNo(info.getRef_params(), index++));
				} else if (MODULE_TYPE.GROUP.equals(module_type)) {
					Element references = writer.getReferenceElement(info, index);
					template.appendChild(references);
					GroupModuleInfo group = GroupModuleInfo.copyByBasic(info);
					List<ModuleBasicInfo> child_modules = group.getModules();
					for (ModuleBasicInfo mi : child_modules) {
						private_params.addAll(ParamInfo.setPhaseNo(mi.getRef_params(), index++));
					}
				}
			}
		}
		List<ParamInfo> all_params = mergerParam(template_info.getParams(), private_params);
		if (!Assert.isEmpty(all_params)) {
			Element params_ele = writer.getParamElement(all_params);
			template.appendChild(params_ele);
		}

		List<PackageTypeInfo> pakcage_types = template_info.getPackage_types();
		if (!Assert.isEmpty(pakcage_types)) {
			Element pk_ele = writer.getPackageElement(pakcage_types);
			template.appendChild(pk_ele);
		}
		writer.document.appendChild(template);
		writer.writer();
	}

	/**
	 * Description: д�������
	 * @param group_info
	 * @param id
	 */
	public static void writerGroup(GroupModuleInfo group_info, String id) {
		XmlWriter writer = xmlWriterInstanceFactory(id, MODULE_TYPE.GROUP);
		String cn_name = group_info.getCn_name();
		String desc = group_info.getBk_desc();
		Element group = writer.document.createElement(XmlTags.GROUP);
		group.setAttribute(XmlTags.ID, writer.id);
		// д������������������
		writer.addDescAndCnname(cn_name, desc, group);
		List<ModuleBasicInfo> list = group_info.getModules();
		// ����������������е����� �������׶Ρ����������������뵽groupԪ����
		List<ParamInfo> private_params = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(list)) {
			int index = 1;
			for (ModuleBasicInfo info : list) {
				MODULE_TYPE module_type = info.getType();
				// д����� �Ĳο�
				if (MODULE_TYPE.COMPONENT.equals(module_type)) {
					private_params.addAll(ParamInfo.setPhaseNo(info.getRef_params(), index));
					Element references = writer.getReferenceElement(info, index++);
					group.appendChild(references);
					// д��׶�
				} else if (MODULE_TYPE.PHASE.equals(module_type)) {
					Element stage = writer.document.createElement(XmlTags.STAGE);
					stage.setAttribute("order", index++ + "");
					stage.setAttribute(XmlTags.NAME, info.getCn_name());
					stage.setAttribute(XmlTags.TYPE, info.getImpl_type().getName());
					Element script = writer.script(info.getCmds(), info.getImpl_type());
					stage.appendChild(script);
					group.appendChild(stage);
				}
			}
		}
		List<ParamInfo> all_params = mergerParam(group_info.getParams(), private_params);
		if (!Assert.isEmpty(all_params)) {
			Element params_ele = writer.getParamElement(all_params);
			group.appendChild(params_ele);
		}
		// �������ݼ���dom ��д��xml�ļ�
		writer.document.appendChild(group);
		writer.writer();

	}

	// public static void writModule(ModuleInfo module_info, String id) {
	// XmlWriter writer = xmlWriterInstanceFactory(id, MODULE_TYPE.COMPONENT);
	// String cn_name = module_info.getCn_name();
	// String desc = module_info.getBk_desc();
	// String[] cmds = module_info.getCmds();
	// IMPL_TYPE type = module_info.getImpl_type();
	// ParamInfo[] params = module_info.getParams();
	// // �������Ԫ��
	// Element component = writer.document.createElement(XmlTags.COMPONENT);
	// component.setAttribute(XmlTags.ID, id);
	// // д��������������
	// writer.addDescAndCnname(cn_name, desc, component);
	// // ��ȡ�ű�Ԫ��
	// Element script = writer.script(cmds, type);
	// component.appendChild(script);
	// if (!Assert.isEmpty(params)) {
	// Element params_ele = writer.getParamElement(Arrays.asList(params));
	// component.appendChild(params_ele);
	// }
	// // �����е������Ԫ�� ���뵽DOM��
	// writer.document.appendChild(component);
	// // д��
	// writer.writer();
	// }

	public static void writModule(ModuleInfo module_info) {
		String id = module_info.getId();
		String cn_name = module_info.getCn_name();
		String desc = module_info.getBk_desc();
		IMPL_TYPE type = module_info.getImpl_type();
		List<Script> script_list = module_info.getScript_list();
		ParamInfo[] params = module_info.getParams();
		// �������Ԫ��
		XmlWriter writer = xmlWriterInstanceFactory(id, MODULE_TYPE.COMPONENT);
		Element module_ele = writer.document.createElement(XmlTags.COMPONENT);
		// ��������������
		writer.addModoleAttribute(module_ele, id, cn_name, type, desc);
		// �������ű��ڵ�
		writer.addModoleScript(module_ele, script_list);
		// �����������׶�
		writer.addModoleParam(module_ele, params);
		// �����е������Ԫ�� ���뵽DOM��
		writer.document.appendChild(module_ele);
		// д��
		writer.writer();
	}
	
	/**
	 * Description: ��������������
	 * @param element
	 * @param id
	 * @param cn_name
	 * @param impl_type
	 * @param desc
	 */
	private void addModoleAttribute(Element element, String id, String cn_name, IMPL_TYPE impl_type, String desc) {
		element.setAttribute(XmlTags.ID, id);
		if (!Assert.isEmpty(impl_type)) {
			element.setAttribute(XmlTags.TYPE, impl_type.getCname());
		}
		if (!Assert.isEmpty(cn_name)) {
			element.setAttribute(XmlTags.CNNAME, cn_name);
		}
		if (!Assert.isEmpty(desc)) {
			Element desc_ele = document.createElement(XmlTags.DESC);
			desc_ele.setTextContent(desc.trim());
			element.appendChild(desc_ele);
		}
	}
	
	/**
	 * Description: �������ű��ڵ�
	 * @param element
	 * @param script_list
	 */
	private void addModoleScript(Element element, List<Script> script_list) {
		if (!Assert.isEmpty(script_list)) {
			Element scripts_ele = document.createElement(XmlTags.SCRIPTS);
			for (Script script : script_list) {
				Element script_ele = document.createElement(XmlTags.SCRIPT);
				script_ele.setAttribute(XmlTags.TYPE, script.getScript_type());
				StringBuffer sb = new StringBuffer();
				sb.append("\n");
				if (!Assert.isEmpty(script.getCmds())) {
					for (String cmd : script.getCmds()) {
						sb.append(cmd);
						sb.append("\n");
					}
				}
				script_ele.setTextContent(sb.toString());
				scripts_ele.appendChild(script_ele);
			}
			element.appendChild(scripts_ele);
		}
	}
	
	/**
	 * Description: �����������ڵ�
	 * @param element
	 * @param params
	 */
	private void addModoleParam(Element element, ParamInfo[] params) {
		if (!Assert.isEmpty(params)) {
			Element params_ele = getParamElement(Arrays.asList(params));
			element.appendChild(params_ele);
		}
	}

	private DocumentBuilder getDocumentBuilder() {
		// ����һ��DocumentBuilderFactory�Ķ���
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// ����DocumentBuilder����
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new GetFileDocumentException();
		}
		return db;
	}

	/**
	 * Description: д�������� �� ����
	 * @param cn_name
	 * @param desc
	 * @param element
	 */
	public void addDescAndCnname(String cn_name, String desc, Element element) {
		if (!Assert.isEmpty(cn_name)) {
			element.setAttribute(XmlTags.CNNAME, cn_name);
		}
		if (!Assert.isEmpty(desc)) {
			Element desc_ele = document.createElement(XmlTags.DESC);
			desc_ele.setTextContent(desc.trim());
			element.appendChild(desc_ele);
		}
	}

	/**
	 * Description: д��ű�����
	 * @param cmds
	 * @param type
	 * @return
	 */
	private Element script(String[] cmds, IMPL_TYPE type) {
		Element script = document.createElement(XmlTags.SCRIPT);
		if (!Assert.isEmpty(type)) {
			script.setAttribute(XmlTags.TYPE, type.getName());
		}
		StringBuffer sb = new StringBuffer();
		// sb.append("<![CDATA[\n");
		sb.append("\n");
		if (!Assert.isEmpty(cmds)) {
			for (String cmd : cmds) {
				sb.append(cmd);
				sb.append("\n");
			}
		}
		// sb.append("]]>\n");
		script.setTextContent(sb.toString());
		return script;
	}

	private Element source(StageSourceBean[] ssb) {
		Element source = document.createElement(XmlTags.NODESOC);
		StringBuffer sb = new StringBuffer();
		for (StageSourceBean bean : ssb) {
			String json = StageSourceBean.toJSOCString(bean);
			sb.append(json);
			sb.append(XmlTags.SPLIT);
		}
		source.setTextContent(sb.toString());
		return source;
	}

	public Element getPackName(String[] pakcage_names,String tags) {
		Element package_names = document.createElement(tags);
		StringBuffer sb = new StringBuffer();
		if(!Assert.isEmpty(pakcage_names)) {
			for (int i = 0; i < pakcage_names.length; i++) {
				sb.append(pakcage_names[i]);
				if(i!=pakcage_names.length-1) {
					sb.append(",");
				}
			}
		}
		package_names.setTextContent(sb.toString());
		return package_names;
	}

	/**
	 * Description: ��ȡ������element
	 * @param params
	 * @return
	 */
	private Element getParamElement(List<ParamInfo> params) {
		Element params_ele = this.document.createElement(XmlTags.PARAMS);
		if (!Assert.isEmpty(params)) {
			// paramsȥ�����еĲ�����Ϊ�յĲ���
			List<ParamInfo> list = deleteRepeate(params);
			for (ParamInfo param : list) {
				Element param_ele = document.createElement(XmlTags.PARAM);
				if (!Assert.isEmpty(param.getParam_group())) {
					param_ele.setAttribute(XmlTags.GROUP, param.getParam_group());
				}
				param_ele.setAttribute(XmlTags.NAME, param.getParam_name());
				param_ele.setAttribute(XmlTags.CNNAME, param.getParam_cn_name());
				param_ele.setAttribute(XmlTags.DESC, param.getParam_bk_desc());
				param_ele.setAttribute(XmlTags.DEFAULT, param.getParam_value());
				if (!Assert.isEmpty(param.getParam_modify_flag())) {
					param_ele.setAttribute(XmlTags.MODIFY, param.getParam_modify_flag().getName());
				}

				if (!Assert.isEmpty(param.getParam_type())) {
					param_ele.setAttribute(XmlTags.TYPE, param.getParam_type().getName());
				}
				param_ele.setAttribute(XmlTags.HAND, String.valueOf(param.isHand_param()));
				if (param.getPhase_no() != null && param.getPhase_no() != 0) {
					param_ele.setAttribute(XmlTags.PHASENO, param.getPhase_no() + "");
					Assert.assertNotEmpty(param.getRef(), "�׶��Զ����������ֵ");
					param_ele.setAttribute(XmlTags.REF, param.getRef());
				}
				params_ele.appendChild(param_ele);
			}
		}
		return params_ele;
	}
	
	/**
	 * Description: ��ȡ������element
	 * @param params
	 * @return
	 */
	public Element getAllParamElement(List<ParamInfo> params) {
		Element params_ele = this.document.createElement(XmlTags.ALLPARAMS);
		if (!Assert.isEmpty(params)) {
			// paramsȥ�����еĲ�����Ϊ�յĲ���
			List<ParamInfo> list = deleteRepeate(params);
			for (ParamInfo param : list) {
				Element param_ele = document.createElement(XmlTags.PARAM);
				if (!Assert.isEmpty(param.getParam_group())) {
					param_ele.setAttribute(XmlTags.GROUP, param.getParam_group());
				}
				param_ele.setAttribute(XmlTags.NAME, param.getParam_name());
				param_ele.setAttribute(XmlTags.CNNAME, param.getParam_cn_name());
				param_ele.setAttribute(XmlTags.DESC, param.getParam_bk_desc());
				param_ele.setAttribute(XmlTags.DEFAULT, param.getParam_value());
				if (!Assert.isEmpty(param.getParam_modify_flag())) {
					param_ele.setAttribute(XmlTags.MODIFY, param.getParam_modify_flag().getName());
				}

				if (!Assert.isEmpty(param.getParam_type())) {
					param_ele.setAttribute(XmlTags.TYPE, param.getParam_type().getName());
				}
				param_ele.setAttribute(XmlTags.HAND, String.valueOf(param.isHand_param()));
				if (param.getPhase_no() != null && param.getPhase_no() != 0) {
					param_ele.setAttribute(XmlTags.PHASENO, param.getPhase_no() + "");
					Assert.assertNotEmpty(param.getRef(), "�׶��Զ����������ֵ");
					param_ele.setAttribute(XmlTags.REF, param.getRef());
				}
				params_ele.appendChild(param_ele);
			}
		}
		return params_ele;
	}

	/**
	 * Description: ��ȡ���ò�����element
	 * @param params
	 * @return
	 */
	private Element getRefParamElement(List<ParamInfo> ref_params) {
		Element ref_params_ele = this.document.createElement(XmlTags.REFPARAMS);
		if (!Assert.isEmpty(ref_params)) {

			// paramsȥ�����еĲ�����Ϊ�յĲ���
			ref_params = deleteRepeate(ref_params);
			for (ParamInfo param : ref_params) {
				Element param_ele = document.createElement(XmlTags.PARAM);
				param_ele.setAttribute(XmlTags.NAME, param.getParam_name());
				param_ele.setAttribute(XmlTags.CNNAME, param.getParam_cn_name());
				param_ele.setAttribute(XmlTags.DESC, param.getParam_bk_desc());
				param_ele.setAttribute(XmlTags.TYPE, param.getParam_type().getName());
				param_ele.setAttribute(XmlTags.PHASENO, param.getPhase_no() + "");
				Assert.assertNotEmpty(param.getRef(), "�׶��Զ����������ֵ");
				param_ele.setAttribute(XmlTags.REF, param.getRef());
				ref_params_ele.appendChild(param_ele);
			}
		}
		return ref_params_ele;
	}

	private Element getPackageElement(List<PackageTypeInfo> pacakges) {
		Element package_ele = this.document.createElement(XmlTags.PKTYPES);
		if (!Assert.isEmpty(pacakges)) {
			// paramsȥ�����еĲ�����Ϊ�յĲ���
			for (PackageTypeInfo info : pacakges) {
				Element ele = document.createElement(XmlTags.PKTYPE);

				ele.setAttribute(XmlTags.PKCNNAME, info.getType_cn_name());
				ele.setAttribute(XmlTags.PKNAME, info.getType_name());

				package_ele.appendChild(ele);
			}
		}
		return package_ele;
	}
	
	private Element configs(NodeBean[] configs) {
		Element config = document.createElement(XmlTags.CONFIGS);
		StringBuffer sb = new StringBuffer();
		for (NodeBean con : configs) {
			sb.append(con.getName());
			sb.append('|');
			sb.append(con.getIp());
			sb.append(XmlTags.SPLIT);
		}
		config.setTextContent(sb.toString());
		return config;
	}

	private Element getCombineElement(Map<String, PackageCombine> combines) {
		Element package_ele = this.document.createElement(XmlTags.PKCOMBINES);
		if (!Assert.isEmpty(combines)) {
			for (Entry<String, PackageCombine> entry : combines.entrySet()) {
				String id = entry.getKey();
				PackageCombine combine = entry.getValue();
				Element ele = document.createElement(XmlTags.PKCOMBINE);
				ele.setAttribute(XmlTags.ID, id);
				Element gen_ph_ele = document.createElement("gen" + XmlTags.PHASENO);
				gen_ph_ele.setTextContent(JSON.fromObject(combine.getGen_phase_list(), JSONCaseType.DEFAULT));
				ele.appendChild(gen_ph_ele);
				Element sel_ph_ele = document.createElement("sel" + XmlTags.PHASENO);
				sel_ph_ele.setTextContent(JSON.fromObject(combine.getSelectable_phase_list(), JSONCaseType.DEFAULT));
				ele.appendChild(sel_ph_ele);
				Element p_ele = document.createElement(XmlTags.PARAM);
				p_ele.setTextContent(JSON.fromObject(combine.getParam_name_list(), JSONCaseType.DEFAULT));
				ele.appendChild(p_ele);
				package_ele.appendChild(ele);
			}
		}
		return package_ele;
	}

	/**
	 * Description: ��ȫ�ֲ�����˽�У��Զ��壩�����ϲ�������ȫ�ֲ���û�н׶κţ�˽�в����н׶κš�
	 * @param general_param
	 * @param private_param
	 * @return
	 */
	private static List<ParamInfo> mergerParam(ParamInfo[] general_param, List<ParamInfo> private_param) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(general_param)) {
			params.addAll(Arrays.asList(general_param));
		}
		if (!Assert.isEmpty(private_param)) {
			params.addAll(private_param);
		}
		return params;
	}

	/**
	 * Description: ��ȡ���õ�Ԫ��
	 * @param info
	 * @param index
	 * @return
	 */
	private Element getReferenceElement(ModuleBasicInfo info, int index) {
		Element references = this.document.createElement(XmlTags.REFERENCES);
		references.setAttribute("order", index + "");
		references.setAttribute(XmlTags.ID, info.getId());
		if (!Assert.isEmpty(info.getAlias_name())) {
			references.setAttribute(XmlTags.ALIAS, info.getAlias_name());
		}
		references.setAttribute(XmlTags.TYPE, info.getType().getName());

		return references;
	}

	/**
	 * Description: ��ȡ����Դ��Element
	 * @param di
	 * @return
	 */
	private Element getNodeSocElementList(ModuleSourceInfo msi) {
		Element ele = document.createElement(XmlTags.NODESOC);
		if (!Assert.isEmpty(msi)) {
			String json = ModuleSourceInfo.toJsonString(msi);
			ele.setTextContent(json);
		}
		return ele;
	}

	private List<ParamInfo> deleteRepeate(List<ParamInfo> list) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		for (ParamInfo info : list) {
			String param_name = info.getParam_name();
			if (Assert.isEmpty(param_name)) {
				continue;
			}
			params.add(info);
		}
		return params;

	}

	/**
	 * Description:д�����
	 */
	private void writer() {
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			// ����Transformer����
			Transformer tf = tff.newTransformer();
			// ���û���
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// ���� script��ǩ�е������ַ�������ת�壬ԭ���������Ϊʵ�ʽű����кܶ� > < ���Ƶ������ַ�
			tf.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, XmlTags.SCRIPT);
			tf.transform(new DOMSource(document), new StreamResult(new File(file_path)));
		} catch (TransformerConfigurationException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new WriterXmlException().addScene("PATH", file_path);
		} catch (TransformerException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
			throw new WriterXmlException().addScene("PATH", file_path);
		}
	}
}
