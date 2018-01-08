/**
 * Title: PhaseParam.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年8月17日
 */
package com.wk.cd.module1.entity;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.xml1.XmlAdapter;
import com.wk.cd.module1.xml1.XmlTags;

/**
 * Class Description:
 * 
 * @author yangl
 */
public class PhaseParam extends Param {

	public static final String PARAM_SLIP = "&&";
	
	public static final String PARAM_CIPHERTEXT = "******";

	public static final String CONFIG_REMOTE = "node_config_file";

	public static final String CONFIG_LOCAL = "node_config_local";

	protected PARAM_TYPE param_type; // 参数类型

	protected String ref;

	protected MODIFY_FLAG modify_flag;

	protected boolean hand_param;
	
	protected boolean delete_flag;
	// 参数多个值得时候 在替换命令时作为多个值得序号，目前就在命令替换的时候用
	private Integer index;

	// 如果是配置文件参数 则这个字段不会为空 且不能为空，如果阶段当中有用到这个参数则认为是配置文件参数
	// 如果阶段中的srv_soc 没有用到这个节点则不生成该阶段，就是选择这两个节点的交集生成阶段
	private String ip;

	/**
	 * Description:
	 * 
	 * @param element
	 */
	@Override
	public void fromElement(Element element) {
		super.fromElement(element);
		param_type = XmlAdapter.getAttribute(element, XmlTags.PARAMTYPE, PARAM_TYPE.class, false);
		ref = element.getAttribute(XmlTags.REF);
		modify_flag = XmlAdapter.getAttribute(element, XmlTags.MODIFY, MODIFY_FLAG.class, false);
		hand_param = Boolean.parseBoolean(element.getAttribute(XmlTags.HAND));
		delete_flag = Boolean.parseBoolean(element.getAttribute(XmlTags.DELETE));
	}

	/**
	 * Description:
	 * 
	 * @param document
	 * @return
	 */
	@Override
	public Element toElement(Document document, String tag) {
		Element param_ele = super.toElement(document, tag);
		XmlAdapter.setAttribute(param_ele, XmlTags.PARAMTYPE, param_type, false);
		param_ele.setAttribute(XmlTags.REF, ref);
		XmlAdapter.setAttribute(param_ele, XmlTags.MODIFY, modify_flag, false);
		param_ele.setAttribute(XmlTags.HAND, Boolean.toString(hand_param));
		param_ele.setAttribute(XmlTags.DELETE, Boolean.toString(delete_flag));
		return param_ele;
	}

	/**
	 * 构造函数
	 */
	public PhaseParam() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param param_name
	 * @param param_cn_name
	 * @param param_bk_desc
	 * @param param_value
	 * @param param_type
	 * @param ref
	 * @param modify_flag
	 */
	public PhaseParam(String param_name, String param_cn_name, String param_bk_desc, String param_value,
			boolean sensitive_flag, PARAM_TYPE param_type, String ref, MODIFY_FLAG modify_flag) {
		super(param_name, param_cn_name, param_bk_desc, param_value, sensitive_flag);
		this.param_type = param_type;
		this.ref = ref;
		this.modify_flag = modify_flag;
	}

	/**
	 * 构造函数
	 * @param param
	 */
	public PhaseParam(Param param) {
		super(param.getParam_name(), param.getParam_cn_name(), param.getParam_bk_desc(), param.getParam_value(), param.getSensitive_flag());
		this.param_type = PARAM_TYPE.PJPARAM;
		this.ref = null;
		this.modify_flag = MODIFY_FLAG.YES;
	}

    /**
     * 构造函数
     * @param phaseParam
     */
    public PhaseParam(PhaseParam phaseParam) {
        super(phaseParam);
        this.param_type=phaseParam.param_type;
        this.ref = phaseParam.ref;
        this.modify_flag = phaseParam.modify_flag;
        this.hand_param = phaseParam.hand_param;
        this.delete_flag = phaseParam.delete_flag;
        this.index = phaseParam.index;
        this.ip = phaseParam.ip;
    }

    /**
	 * @return param_type
	 */
	public PARAM_TYPE getParam_type() {
		return param_type;
	}

	/**
	 * @return ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @return modify_flag
	 */
	public MODIFY_FLAG getModify_flag() {
		return modify_flag;
	}

	/**
	 * @param param_type
	 */
	public void setParam_type(PARAM_TYPE param_type) {
		this.param_type = param_type;
	}

	/**
	 * @param ref
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @param modify_flag
	 */
	public void setModify_flag(MODIFY_FLAG modify_flag) {
		this.modify_flag = modify_flag;
	}

	/**
	 * @return index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Description:
	 *
	 * @return
	 */
    public static List<PhaseParam> copy(List<PhaseParam> list) {
        List<PhaseParam> newList = new ArrayList<PhaseParam>();
        if (Assert.notEmpty(list)) {
            for (PhaseParam phaseParam : list) {
                if (phaseParam != null) {
                    newList.add(new PhaseParam(phaseParam));
                }
            }
        }
        return newList;
    }

	/**
	 * Description:
	 * 
	 * @return
	 */

	public static PhaseParam copy(PhaseParam info) {
		if (info == null) {
			return null;
		}
		PhaseParam bean = new PhaseParam();
		bean.setParam_value(info.getParam_value());
		bean.setParam_cn_name(info.getParam_cn_name());
		bean.setParam_bk_desc(info.getParam_bk_desc());
		bean.setParam_name(info.getParam_name());
		bean.setParam_type(info.getParam_type());
		bean.setRef(info.getRef());
		bean.setModify_flag(info.getModify_flag());
		bean.setIndex(info.getIndex());
		bean.setIp(info.getIp());
		bean.setSensitive_flag(info.getSensitive_flag());
		return bean;
	}
	
	
	@Override
	public String toString() {
		return "PhaseParam [param_type=" + param_type + ", ref=" + ref + ", modify_flag=" + modify_flag
				+ ", hand_param=" + hand_param + ", delete_flag=" + delete_flag + ", index=" + index + ", ip=" + ip
				+ ", param_name=" + param_name + ", param_cn_name=" + param_cn_name + ", param_bk_desc=" + param_bk_desc
				+ ", param_value=" + param_value + ", sensitive_flag=" + sensitive_flag + "]";
	}

	/**
	 * @return hand_param
	 */
	public boolean getHand_param() {
		return hand_param;
	}

	/**
	 * @param hand_param
	 */
	public void setHand_param(boolean hand_param) {
		this.hand_param = hand_param;
	}

	public boolean getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

}
