/**
 * Title: QueryRsPrivAction.java
 * File Description: 根据用户名查询用户拥有的权限类
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.SuperResouceNotAssignedPermissionsException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.us.bean.ItemBean;
import com.wk.cd.system.us.bean.LevelMenuBean;
import com.wk.cd.system.us.bean.MenuBean;
import com.wk.cd.system.us.bean.QueryRsPrivInputBean;
import com.wk.cd.system.us.bean.QueryRsPrivOutputBean;
import com.wk.cd.system.us.bean.RsUrlBean;
import com.wk.cd.system.us.bean.TabBean;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 根据用户名查询用户拥有的权限类, 此方法只是为了生成权限页面使用
 * @author lixl
 */
public class QueryRsPrivAction
		extends ActionBasic<QueryRsPrivInputBean, QueryRsPrivOutputBean> {
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private WorkPublicService wksvc;
	@Inject
	private UsGetUserPrivService ussvc;
	@Inject
	private RsPublicService rsPublicService;
	@Inject
	private UsCheckUserService usCheckUserService;
	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 1.资源级别范围-n<x<n,"-"表示不在页面显示，n表示级别
	 * 2.资源配置1级Menu如果不存在下级Tablepane(TableBean)对象前端根据Menu中配置的rs_url显示页面
	 * 　如果存在Tablepane那么显示默认的Tablepane页面，例如首页配置（通讯录等页面）
	 * @param input
	 * @return QueryRsPrivOutputBean
	 */
	@Override
	protected QueryRsPrivOutputBean doAction(QueryRsPrivInputBean input) {
		logger.info("************QueryRsPrivAction**************");
		QueryRsPrivOutputBean output = new QueryRsPrivOutputBean();
		
		if(CfgTool.isIntegrationMode()){
			return doIntegrationReturn(input);
		}
		
		String user_id=input.getOrg_user_id();
		String bl_rs_code=input.getBl_rs_code();
		JaDate dt_date=input.getDtbs_bk_date();
		JaTime dt_time=input.getDtbs_bk_time();
		JaDateTime dt_datetime=JaDateTime.valueOf(dt_date.getYear(), dt_date.getMonth(), dt_date.getDay(), dt_time.getHour(), dt_time.getMinute(), dt_time.getSecond(), dt_time.getMillisecond());
		
		Assert.assertNotEmpty(user_id, QueryRsPrivInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(bl_rs_code, QueryRsPrivInputBean.BL_RS_CODECN);
	
		List<ItemBean> items = new ArrayList<ItemBean>();
		Map<String, MenuBean> menus = new HashMap<String, MenuBean>();
		Map<String, LevelMenuBean> level_menus = new HashMap<String, LevelMenuBean>();
		
		List<RsResInfo> lst_bean = ussvc.getUserRsPriv(user_id,bl_rs_code,dt_datetime);
		
		int size = lst_bean.size() > 0 ? lst_bean.size() : 0;
		
		logger.plog("getUserRsPriv end");
		//TODO 返回的导航目录信息加入一级目录,二级目录加入资源图标地址 (Xul)
		for (int i = 0; i < size; i++) {
			RsResInfo b = lst_bean.get(i);
			if (b.getRs_level() == -1) {
				continue;
			}
			if (b.getRs_level() == -2){
				LevelMenuBean l = new LevelMenuBean();
				l.setMenu_name(b.getRs_cn_name());
				l.setSort_key(b.getSort_key());
				l.setRsim_url(b.getRsim_url());
				level_menus.put(b.getRs_code(), l);
			} else if (b.getRs_level() == 1) {
				LevelMenuBean l = level_menus.get(b.getSuper_rs_code());
				if (Assert.isEmpty(l)) {
					logger.debug("rs_code=[{}], super_rs_code=[{}]",
							b.getRs_code(), b.getSuper_rs_code());
					throw new SuperResouceNotAssignedPermissionsException()
							.addScene("SP_RS_CODE", b.getSuper_rs_code())
							.addScene("RS_CODE", b.getRs_code());
				}
				List<MenuBean> levels = l.getMeun_bean_list();
				if (Assert.isEmpty(levels)) {
					levels = new ArrayList<MenuBean>();
				}
				MenuBean m = new MenuBean();
				m.setDefaulttab(0);
				m.setName(b.getRs_cn_name());
				m.setSort_key(b.getSort_key());
				m.setFrame(b.getRs_url());
				m.setRsim_url(b.getRsim_url());
				menus.put(b.getRs_code(), m);
				levels.add(m);
				l.setMeun_bean_list(levels);
			} else if (b.getRs_level() == 2) {
				MenuBean m = menus.get(b.getSuper_rs_code());
				if (Assert.isEmpty(m)) {
					logger.debug("rs_code=[{}], super_rs_code=[{}]",
							b.getRs_code(), b.getSuper_rs_code());
					throw new SuperResouceNotAssignedPermissionsException()
							.addScene("SP_RS_CODE", b.getSuper_rs_code())
							.addScene("RS_CODE", b.getRs_code());
				}
				List<TabBean> tabs = m.getTabs();
				if (Assert.isEmpty(tabs)) {
					tabs = new ArrayList<TabBean>();
				}
				TabBean tab = new TabBean();
				tab.setText(b.getRs_cn_name());
				tab.setFrame(b.getRs_url());
				tab.setSort_key(b.getSort_key());
				tabs.add(tab);
				m.setTabs(tabs);
				
			} else if (b.getRs_level() == 0) {
				ItemBean ib = new ItemBean();
				ib.setIcoindex(Integer.valueOf(b.getRsim_url()));
				ib.setName(b.getRs_cn_name());
				ib.setPage(b.getRs_url());
				ib.setGheight(b.getGheight_bk_pixel());
				ib.setPwidth(b.getPwidth_bk_pixel());
				items.add(ib);
			}
		}
		output.setDefaultmenu(0);
		output.setItem(items);
		List<LevelMenuBean> lst_men = new ArrayList<LevelMenuBean>();
		for (Map.Entry<String, LevelMenuBean> entry : level_menus.entrySet()) {
			LevelMenuBean mb = entry.getValue();
			//TODO 若一级目录下没有二级目录，则不显示该一级目录（Xul）
			if(!Assert.isEmpty(mb.getMeun_bean_list())){
				lst_men.add(mb);
			}
		}

		logger.plog("addItem end");
		Collections.sort(lst_men, new Comparator<LevelMenuBean>() {
			public int compare(LevelMenuBean arg0, LevelMenuBean arg1) {
				return arg0.getSort_key() - arg1.getSort_key();
			}
		});

		logger.plog("sort end");

		output.setLevel_menu_list(lst_men);
		output.setPwork_bk_num(wksvc.countInfoNumByUser(input.getOrg_user_id()));
		logger.plog("countInfoNumByUser end");
		
		if(usCheckUserService.isUserManager(input.getOrg_user_id())){
			int pterm_bk_num=usUserTermDaoService.countAllUnUseUserTerm();
			output.setPterm_bk_num(pterm_bk_num);
		}
		
		List<RsUrlBean> rsurl=rsPublicService.getRsUrlList(bl_rs_code);
		output.setRsurl(rsurl);
		
		List<RsUrlBean> rs_config=rsPublicService.getRsConfig();
		output.setRs_config(rs_config);
		return output;
	}

	/** 
	 * Description: 集成模式返回数据
	 * @param input
	 * @return 
	 */
	private QueryRsPrivOutputBean doIntegrationReturn(QueryRsPrivInputBean input) {
		QueryRsPrivOutputBean output = new QueryRsPrivOutputBean();
		String user_id=input.getOrg_user_id();
		String bl_rs_code=input.getBl_rs_code();
		Assert.assertNotEmpty(user_id, QueryRsPrivInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(bl_rs_code, QueryRsPrivInputBean.BL_RS_CODECN);
		
		List<ItemBean> items = new ArrayList<ItemBean>();
		Map<String, MenuBean> menus = new HashMap<String, MenuBean>();
		Map<String, LevelMenuBean> level_menus = new HashMap<String, LevelMenuBean>();
		
		List<RsResInfo> lst_bean = ussvc.getAllSubRsPriv(user_id,bl_rs_code);
		
		int size = lst_bean.size() > 0 ? lst_bean.size() : 0;
		
		logger.plog("getUserRsPriv end");
		//TODO 返回的导航目录信息加入一级目录,二级目录加入资源图标地址 (Xul)
		for (int i = 0; i < size; i++) {
			RsResInfo b = lst_bean.get(i);
			if (b.getRs_level() == -1) {
				continue;
			}
			if (b.getRs_level() == -2){
				LevelMenuBean l = new LevelMenuBean();
				l.setMenu_name(b.getRs_cn_name());
				l.setSort_key(b.getSort_key());
				level_menus.put(b.getRs_code(), l);
			} else if (b.getRs_level() == 1) {
				LevelMenuBean l = level_menus.get(b.getSuper_rs_code());
				if (Assert.isEmpty(l)) {
					logger.debug("rs_code=[{}], super_rs_code=[{}]",
							b.getRs_code(), b.getSuper_rs_code());
					throw new SuperResouceNotAssignedPermissionsException()
							.addScene("SP_RS_CODE", b.getSuper_rs_code())
							.addScene("RS_CODE", b.getRs_code());
				}
				List<MenuBean> levels = l.getMeun_bean_list();
				if (Assert.isEmpty(levels)) {
					levels = new ArrayList<MenuBean>();
				}
				MenuBean m = new MenuBean();
				m.setDefaulttab(0);
				m.setName(b.getRs_cn_name());
				m.setSort_key(b.getSort_key());
				m.setFrame(b.getRs_url());
				m.setRsim_url(b.getRsim_url());
				menus.put(b.getRs_code(), m);
				levels.add(m);
				l.setMeun_bean_list(levels);
			} else if (b.getRs_level() == 2) {
				MenuBean m = menus.get(b.getSuper_rs_code());
				if (Assert.isEmpty(m)) {
					logger.debug("rs_code=[{}], super_rs_code=[{}]",
							b.getRs_code(), b.getSuper_rs_code());
					throw new SuperResouceNotAssignedPermissionsException()
							.addScene("SP_RS_CODE", b.getSuper_rs_code())
							.addScene("RS_CODE", b.getRs_code());
				}
				List<TabBean> tabs = m.getTabs();
				if (Assert.isEmpty(tabs)) {
					tabs = new ArrayList<TabBean>();
				}
				TabBean tab = new TabBean();
				tab.setText(b.getRs_cn_name());
				tab.setFrame(b.getRs_url());
				tab.setSort_key(b.getSort_key());
				tabs.add(tab);
				m.setTabs(tabs);
				
			} else if (b.getRs_level() == 0) {
				ItemBean ib = new ItemBean();
				ib.setIcoindex(Integer.valueOf(b.getRsim_url()));
				ib.setName(b.getRs_cn_name());
				ib.setPage(b.getRs_url());
				ib.setGheight(b.getGheight_bk_pixel());
				ib.setPwidth(b.getPwidth_bk_pixel());
				items.add(ib);
			}
		}
		output.setDefaultmenu(0);
		output.setItem(items);
		List<LevelMenuBean> lst_men = new ArrayList<LevelMenuBean>();
		for (Map.Entry<String, LevelMenuBean> entry : level_menus.entrySet()) {
			LevelMenuBean mb = entry.getValue();
			//TODO 若一级目录下没有二级目录，则不显示该一级目录（Xul）
			if(!Assert.isEmpty(mb.getMeun_bean_list())){
				lst_men.add(mb);
			}
		}

		logger.plog("addItem end");
		Collections.sort(lst_men, new Comparator<LevelMenuBean>() {
			public int compare(LevelMenuBean arg0, LevelMenuBean arg1) {
				return arg0.getSort_key() - arg1.getSort_key();
			}
		});

		logger.plog("sort end");

		output.setLevel_menu_list(lst_men);
		output.setPwork_bk_num(wksvc.countInfoNumByUser(input.getOrg_user_id()));
		logger.plog("countInfoNumByUser end");
		
		if(usCheckUserService.isUserManager(input.getOrg_user_id())){
			int pterm_bk_num=usUserTermDaoService.countAllUnUseUserTerm();
			output.setPterm_bk_num(pterm_bk_num);
		}
		
		List<RsUrlBean> rsurl=rsPublicService.getRsUrlList(bl_rs_code);
		output.setRsurl(rsurl);
		
		List<RsUrlBean> rs_config=rsPublicService.getRsConfig();
		output.setRs_config(rs_config);
		return output;
	}

	/**
	 * 查询用户待处理任务数
	 * @param input
	 * @return QueryRsPrivOutputBean
	 */
	public QueryRsPrivOutputBean queryPendWorkNum(QueryRsPrivInputBean input) {
		QueryRsPrivOutputBean output = new QueryRsPrivOutputBean();
		output.setPwork_bk_num(wksvc.countInfoNumByUser(input.getOrg_user_id()));
		return output;
	}
	
	/**
	 * 查询用户待处理终端申请数
	 * @param input
	 * @return QueryRsPrivOutputBean
	 */
	public QueryRsPrivOutputBean queryPendTermNum(QueryRsPrivInputBean input) {
		QueryRsPrivOutputBean output = new QueryRsPrivOutputBean();
		int pterm_bk_num=usUserTermDaoService.countAllUnUseUserTerm();
		output.setPterm_bk_num(pterm_bk_num);
		return output;
	}

	@Override
	protected String getLogTxt(QueryRsPrivInputBean input) {
		return lgsvc.getLogTxt("获取用户资源列表", null);
	}

}
