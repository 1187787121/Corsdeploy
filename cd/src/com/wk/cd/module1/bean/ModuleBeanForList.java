/**
 * Title: ModuleListBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��12��28��
 */
package com.wk.cd.module1.bean;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.module1.dao.MoTagsDaoService;
import com.wk.cd.module1.info.MoModuleInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.module1.bean.ModuleBeanForList;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:
 * @author yangl
 */
public class ModuleBeanForList {

	private String id;

	private String cn_name;

	private IMPL_TYPE impl_type;

	private PUBLISH_STATE publish_state;

	private JaDate crt_bk_date;

	private JaTime crt_bk_time;

	private JaDate modify_bk_date;

	private JaTime modify_bk_time;

	private String crt_user_name;

	private String modify_user_name;

	private int[] module_purposes;

	private String[] tag_list;

	/**
	 * Description: ��������飩��Ϣת��Ϊǰ�˱�׼�ֶ�
	 * @param info_list
	 * @return
	 */
	public static List<ModuleBeanForList> parseModuleList(List<MoModuleInfo> info_list) {
		List<ModuleBeanForList> bean_list = new ArrayList<ModuleBeanForList>();
		if (!Assert.isEmpty(info_list)) {
			for (MoModuleInfo info : info_list) {
				bean_list.add(new ModuleBeanForList(info));
			}
		}
		return bean_list;
	}

	public ModuleBeanForList(MoModuleInfo module_info) {
		super();
		this.id = module_info.getModule_id();
		this.cn_name = module_info.getModule_cn_name();
		this.impl_type = module_info.getImpl_type();
		this.publish_state = module_info.getPublish_state();
		this.crt_user_name = HolderClass.usGetUserInfoService.getUserCnNameByUserId(module_info.getCrt_user_id());
		this.crt_bk_date = module_info.getCrt_bk_date();
		this.crt_bk_time = module_info.getCrt_bk_time();
		this.modify_bk_date = module_info.getModify_bk_date();
		this.modify_bk_time = module_info.getModify_bk_time();
		if (!Assert.isEmpty(module_info.getModule_purposes())) {
			this.module_purposes = StringUtil.str2intary(module_info.getModule_purposes());
		}
		if (!Assert.isEmpty(module_info.getModify_user_id())) {
			this.modify_user_name = HolderClass.usGetUserInfoService.getUserCnNameByUserId(module_info.getModify_user_id());
		}
//		List<String> tags = HolderClass.moTagsDaoService.queryTagsById(id);
//		this.setTag_list(tags.toArray(new String[tags.size()]));

	}

	// IoDH��ʽʵ���ӳټ���
	private static class HolderClass {
		private static Injector injector = Controller.getInstance().getInjector();
		private static UsGetUserInfoService usGetUserInfoService = injector.getBean(UsGetUserInfoService.class);
		private static MoTagsDaoService moTagsDaoService = injector.getBean(MoTagsDaoService.class);
	}
	
	public ModuleBeanForList() {
		super();
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return cn_name
	 */
	public String getCn_name() {
		return cn_name;
	}

	/**
	 * @param cn_name
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
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
	 * @return crt_bk_date
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 * @return crt_bk_time
	 */
	public JaTime getCrt_bk_time() {
		return crt_bk_time;
	}

	/**
	 * @param crt_bk_time
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 * @return modify_bk_date
	 */
	public JaDate getModify_bk_date() {
		return modify_bk_date;
	}

	/**
	 * @param modify_bk_date
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 * @return modify_bk_time
	 */
	public JaTime getModify_bk_time() {
		return modify_bk_time;
	}

	/**
	 * @param modify_bk_time
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 * @return crt_user_name
	 */
	public String getCrt_user_name() {
		return crt_user_name;
	}

	/**
	 * @param crt_user_name
	 */
	public void setCrt_user_name(String crt_user_name) {
		this.crt_user_name = crt_user_name;
	}

	/**
	 * @return modify_user_name
	 */
	public String getModify_user_name() {
		return modify_user_name;
	}

	/**
	 * @param modify_user_name
	 */
	public void setModify_user_name(String modify_user_name) {
		this.modify_user_name = modify_user_name;
	}

	/**
	 * @return publish_state
	 */
	public PUBLISH_STATE getPublish_state() {
		return publish_state;
	}

	/**
	 * @param publish_state
	 */
	public void setPublish_state(PUBLISH_STATE publish_state) {
		this.publish_state = publish_state;
	}

	/**
	 * @return module_purposes
	 */
	public int[] getModule_purposes() {
		return module_purposes;
	}

	/**
	 * @param module_purposes
	 */
	public void setModule_purposes(int[] module_purposes) {
		this.module_purposes = module_purposes;
	}

	public String[] getTag_list() {
		return tag_list;
	}

	public void setTag_list(String[] tag_list) {
		this.tag_list = tag_list;
	}

}