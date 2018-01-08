/**
 * Title: Instance.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2017年8月19日
 */
package com.wk.cd.module1.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;

/**
 * Class Description: 
 * @author chiss
 */
public class InstancePhase implements XmlEntity{

	/**
	 *原方案阶段号 为了并行 按钮的显示
	 */
	private int original_phase_id;

	private int phase_no;

	private String phase_name;

	private IMPL_TYPE impl_type;

	private Script script;

	
	private List<StageSourceBean> srv_soc;
	
	private ModuleSourceInfo module_source_info;
	
	/**
	 * 交互式标志 true表示是交互式的
	 */
	private boolean interactor_flag;
	
	/**
	 * 可并行标志 true表示可以并行
	 */
	private boolean parallel_flag;

	/** 
	 * Description: 
	 * @param element 
	 */
	@Override
	public void fromElement(Element element) {
		String opi = element.getAttribute(XmlTags.ORIGINAL_PHASE);
		if(!Assert.isEmpty(opi) && StringUtils.isNumeric(opi)){
			original_phase_id = Integer.parseInt(opi);
		}
		
		String phase_no1 = element.getAttribute(XmlTags.PHASENO);
		if(!Assert.isEmpty(phase_no1) && StringUtils.isNumeric(phase_no1)){
			phase_no = Integer.parseInt(phase_no1);
		}
		phase_name =element.getAttribute(XmlTags.CNNAME);
		impl_type = XmlAdapter.getAttribute(element, XmlTags.IMPL_TYPE, IMPL_TYPE.class, true);
		script = XmlAdapter.getElementEntity(element, XmlTags.SCRIPT, Script.class, true);
		srv_soc = XmlAdapter.getElementEntityList(element, XmlTags.SOURCE, StageSourceBean.class, false);
		module_source_info = XmlAdapter.getElementEntity(element, XmlTags.MODULE_SOURCE, ModuleSourceInfo.class, true);
		interactor_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.INTERACTOR_FLAG));
		parallel_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.PARALLEL_FLAG));
		
	}

	/** 
	 * Description: 
	 * @param document
	 * @param tag
	 * @return 
	 */
	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.INSTANCE_PHASE;
		}
		Element instance_phase_ele = document.createElement(tag);
		instance_phase_ele.setAttribute(XmlTags.ORIGINAL_PHASE, String.valueOf(original_phase_id));
		instance_phase_ele.setAttribute(XmlTags.PHASENO, String.valueOf(phase_no));
		instance_phase_ele.setAttribute(XmlTags.CNNAME, phase_name);
		XmlAdapter.setAttribute(instance_phase_ele, XmlTags.IMPL_TYPE, impl_type, true);
		instance_phase_ele.appendChild(script.toElement(document, XmlTags.SCRIPT));
		instance_phase_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.SOURCE, srv_soc));
		instance_phase_ele.appendChild(module_source_info.toElement(document, XmlTags.MODULE_SOURCE));
		instance_phase_ele.setAttribute(XmlTags.INTERACTOR_FLAG, Boolean.toString(interactor_flag));
		instance_phase_ele.setAttribute(XmlTags.PARALLEL_FLAG, Boolean.toString(parallel_flag));
		return instance_phase_ele;
	}

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
	 * @return phase_name
	 */
	public String getPhase_name() {
		return phase_name;
	}

	/**
	 * @param phase_name
	 */
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}

	/**
	 * @return impl_type
	 */
	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	/**
	 * @param impl_type
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return script
	 */
	public Script getScript() {
		return script;
	}

	/**
	 * @param script
	 */
	public void setScript(Script script) {
		this.script = script;
	}

	/**
	 * @return srv_soc
	 */
	public List<StageSourceBean> getSrv_soc() {
		return srv_soc;
	}

	/**
	 * @param srv_soc
	 */
	public void setSrv_soc(List<StageSourceBean> srv_soc) {
		this.srv_soc = srv_soc;
	}

	/**
	 * @return module_source_info
	 */
	public ModuleSourceInfo getModule_source_info() {
		return module_source_info;
	}

	/**
	 * @param module_source_info
	 */
	public void setModule_source_info(ModuleSourceInfo module_source_info) {
		this.module_source_info = module_source_info;
	}
	
	

	/**
	 * @return original_phase_id
	 */
	public int getOriginal_phase_id() {
		return original_phase_id;
	}

	/**
	 * @param original_phase_id
	 */
	public void setOriginal_phase_id(int original_phase_id) {
		this.original_phase_id = original_phase_id;
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

	public void addCmds(String[] cmds){
		Script script1 = new Script();
		script1.setCmds(cmds);
		this.script = script1;
	}
}
