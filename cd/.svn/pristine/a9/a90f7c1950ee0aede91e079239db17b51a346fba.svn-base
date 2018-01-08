/**
 * Title: Phase.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月17日
 */
package com.wk.cd.module1.entity;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.xml1.ElementEntity;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.enu.PHASE_TYPE;

/**
 * Class Description:
 * @author yangl
 */
public class Phase
		implements ElementEntity {

	private int phase_no;

	private String phase_name;

	private String bk_desc;
	
	private PHASE_TYPE phase_type;

	private IMPL_TYPE impl_type;

	private Script script;

	private List<StageSourceBean> srv_soc;

	private List<Param> param_list;

	private List<PhaseParam> ref_param_list;//引用参数

	private String component_id;
	
	
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
		phase_no = Integer.parseInt(element.getAttribute(XmlTags.PHASENO));
		phase_name =element.getAttribute(XmlTags.CNNAME);
		impl_type = XmlAdapter.getAttribute(element, XmlTags.IMPL_TYPE, IMPL_TYPE.class, true);
		bk_desc = XmlAdapter.getElementContent(element, XmlTags.DESC, false);
        phase_type = XmlAdapter.getAttribute(element, XmlTags.PHASE_TYPE, PHASE_TYPE.class, true);
		
		component_id =element.getAttribute(XmlTags.COMPONENT);
		interactor_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.INTERACTOR_FLAG));
		parallel_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.PARALLEL_FLAG));
		
		script = XmlAdapter.getElementEntity(element, XmlTags.SCRIPT, Script.class, true);
		srv_soc = XmlAdapter.getElementEntityList(element, XmlTags.SOURCE, StageSourceBean.class, false);
		param_list = XmlAdapter.getElementEntityList(element, XmlTags.PARAM, Param.class, false);
		ref_param_list = XmlAdapter.getElementEntityList(element, XmlTags.REFPARAM, PhaseParam.class, false);
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
			tag = XmlTags.PHASE;
		}
		Element phase_ele = document.createElement(tag);
		phase_ele.setAttribute(XmlTags.PHASENO, String.valueOf(phase_no));
		phase_ele.setAttribute(XmlTags.CNNAME, phase_name);
		XmlAdapter.setAttribute(phase_ele, XmlTags.IMPL_TYPE, impl_type, true);
		phase_ele.setAttribute(XmlTags.COMPONENT, component_id);
		phase_ele.setAttribute(XmlTags.INTERACTOR_FLAG, Boolean.toString(interactor_flag));
		phase_ele.setAttribute(XmlTags.PARALLEL_FLAG, Boolean.toString(parallel_flag));
		if (!Assert.isEmpty(bk_desc)) {
			Element desc_ele = document.createElement(XmlTags.DESC);
			desc_ele.setTextContent(bk_desc.trim());
			phase_ele.appendChild(desc_ele);		
		}
        XmlAdapter.setAttribute(phase_ele, XmlTags.PHASE_TYPE, phase_type, true);
		phase_ele.appendChild(script.toElement(document, XmlTags.SCRIPT));
		phase_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.SOURCE, srv_soc));
		phase_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PARAM, param_list));
		phase_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.REFPARAM, ref_param_list));
		return phase_ele;
	}

	/**
	 * @return phase_no
	 */
	public int getPhase_no() {
		return phase_no;
	}

	/**
	 * @return phase_name
	 */
	public String getPhase_name() {
		return phase_name;
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
	 * @return srv_soc
	 */
	public List<StageSourceBean> getSrv_soc() {
		return srv_soc;
	}

	/**
	 * @return param_list
	 */
	public List<Param> getParam_list() {
		return param_list;
	}

	/**
	 * @return ref_param_list
	 */
	public List<PhaseParam> getRef_param_list() {
		return ref_param_list;
	}

	/**
	 * @return component_id
	 */
	public String getComponent_id() {
		return component_id;
	}

	/**
	 * @param phase_no
	 */
	public void setPhase_no(int phase_no) {
		this.phase_no = phase_no;
	}

	/**
	 * @param phase_name
	 */
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}

	/**
	 * @param script
	 */
	public void setScript(Script script) {
		this.script = script;
	}

	/**
	 * @param srv_soc
	 */
	public void setSrv_soc(List<StageSourceBean> srv_soc) {
		this.srv_soc = srv_soc;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<Param> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @param ref_param_list
	 */
	public void setRef_param_list(List<PhaseParam> ref_param_list) {
		this.ref_param_list = ref_param_list;
	}

	/**
	 * @param component_id
	 */
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	/**
	 * @return bk_desc
	 */
	public String getBk_desc() {
		return bk_desc;
	}

	/**
	 * @param bk_desc
	 */
	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc;
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
	
	/**
	 * @return phase_type
	 */
	public PHASE_TYPE getPhase_type() {
		return phase_type;
	}

	/**
	 * @param phase_type
	 */
	public void setPhase_type(PHASE_TYPE phase_type) {
		this.phase_type = phase_type;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "Phase [phase_no=" + phase_no + ", phase_name=" + phase_name + ", bk_desc=" + bk_desc + ", phase_type=" + phase_type + ", impl_type="
				+ impl_type + ", script=" + script + ", srv_soc=" + srv_soc + ", param_list=" + param_list + ", ref_param_list=" + ref_param_list
				+ ", component_id=" + component_id + ", interactor_flag=" + interactor_flag + ", parallel_flag=" + parallel_flag + "]";
	}

	public Phase copy(Phase phase){
		Phase new_phase = new Phase();
		new_phase.setBk_desc(bk_desc);
		new_phase.setComponent_id(component_id);
		new_phase.setImpl_type(impl_type);
		new_phase.setInteractor_flag(interactor_flag);
		new_phase.setParallel_flag(parallel_flag);
		new_phase.setParam_list(param_list);
		new_phase.setPhase_name(phase_name);
		new_phase.setPhase_no(phase_no);
		new_phase.setPhase_type(phase_type);
		new_phase.setRef_param_list(ref_param_list);
		new_phase.setScript(script);
//		new_phase.setSrv_soc(srv_soc);
		return new_phase;
	}
}
