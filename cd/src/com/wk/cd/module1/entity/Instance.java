/**
 * Title: Instance.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年8月19日
 */
package com.wk.cd.module1.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlEntity;
import com.wk.cd.module1.xml1.XmlTags;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.common.util.Assert;

/**
 * Class Description: 
 * @author zhangj
 */
public class Instance implements XmlEntity{
	
	private String instance_id;
	
	/**
	 * 这个参数是生成实例的时候用到的全局参数 
	 * 在发布的地方就是项目参数 
	 * 这东西加上去就是为了给前端查看用 后台执行什么的都用不上 对你没有看错 后台用不上
	 */
	private List<PhaseParam> param_list;
	
	private final List<InstancePhase> phase = new ArrayList<InstancePhase>();
	
	@Override
	public void fromElement(Element element) {
		instance_id = element.getAttribute(XmlTags.ID);
		param_list = XmlAdapter.getElementEntityList(element, XmlTags.PARAM, PhaseParam.class, false);
		List<InstancePhase> phase1 = XmlAdapter.getElementEntityList(element, XmlTags.PHASES, InstancePhase.class, false);
		addPhases(phase1);
	}

	@Override
	public Element toElement(Document document, String tag) {
		if (Assert.isEmpty(tag)) {
			tag = XmlTags.INSTANCE;
		}
		Element instance_ele = document.createElement(tag);
		instance_ele.setAttribute(XmlTags.ID, String.valueOf(instance_id));
		instance_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PARAM, param_list));
		instance_ele.appendChild(XmlAdapter.toDocumentFragment(document, XmlTags.PHASES, phase));
		return instance_ele;
	}

	/**
	 * @return instance_id
	 */
	public String getInstance_id() {
		return instance_id;
	}

	/**
	 * @param instance_id
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	
	/**
	 * @return phase
	 */
	public List<InstancePhase> getPhase() {
		return phase;
	}


	public Iterator<InstancePhase> phaseIterator() {
		return phase.iterator();
	}
	
	
	public void addPhase(InstancePhase phase){
		if(!Assert.isEmpty(phase)){
			this.phase.add(phase);
		}
		
	}
	
	public void addPhases(List<InstancePhase> phases){
		if(!Assert.isEmpty(phases)){
			this.phase.addAll(phases);
		}
		
	}
	
	public int getPhaseCount(){
		return phase.size();
	}

	/**
	 * @return param_list
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}

	/**
	 * 构造函数
	 * @param instance_id
	 */
	public Instance(String instance_id) {
		super();
		this.instance_id = instance_id;
	}
	

}
