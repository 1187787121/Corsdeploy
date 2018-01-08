/**
 * Title: CeSystemDaoService.java
 * File Description: Ӧ��ϵͳ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
import com.wk.util.StringUtil;

/**
 * Class description:Ӧ��ϵͳ��
 * @author AutoGen
 */
public class CeSystemDaoService {
	@Inject private CeSystemDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeSystemInfo info
	 * @return CeSystemInfo
	 */
	public CeSystemInfo getInfoByKey(CeSystemInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeSystemInfo info
	 * @return CeSystemInfo
	 */
	public CeSystemInfo getInfoByKeyForUpdate(CeSystemInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeSystemInfo info
	 * @return int
	 */
	public int insertInfo(CeSystemInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeSystemInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeSystemInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: ����ϵͳ����ѯϵͳ������
	 * @param sys_name
	 * @return
	 */
	public int countBySystemName(String sys_name){
		return dao.countBySystemName(sys_name);
	}
	
	/** 
	 * Description: ����ϵͳ��Ϣ
	 * @param tpinfo 
	 */
	public int updateSystemInfo(CeSystemInfo info) {
		return dao.update(info);
		
	}
	/** 
	 * Description: ɾ��һ��ϵͳ��Ϣ
	 * @param info
	 * @return
	 */
	public int deleteSystem(CeSystemInfo info){
		return dao.delete(info);
		
	}
	/**
	 * Description: ��ҳ��ѯ����Ӧ��ϵͳ
	 * @return
	 */
	public List<PageSystemListBean> pageAllSystem(String sys_name, String sys_cn_name, String order_col_name, ORDER_TYPE order_type, int start_recd,int limit_recd){
		String order_type_str = order_type.getName();
		if("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)){
			return dao.pageSystemListDefault(sys_name, sys_cn_name, start_recd, limit_recd);
		}
		if("create_bk_date".equals(order_col_name)) {
			System.out.println("---------------");
			return dao.pageSystemList(sys_name,sys_cn_name,order_col_name+" "+order_type.getName()+",create_bk_time",order_type_str,start_recd, limit_recd);
		}
		return dao.pageSystemList(sys_name,sys_cn_name,order_col_name,order_type_str,start_recd, limit_recd);
	}
	
	/**
	 * Description: ��ѯ����Ӧ��ϵͳ
	 * @return
	 */
	public List<PageSystemListBean> getAllSystemList(String sys_name, String sys_cn_name){
		return dao.getSystemList(sys_name,sys_cn_name);
	}

	/** 
	 * Description: �õ�ϵͳ����
	 * @param sys_name
	 * @return 
	 */
	public int countSystemBySysName(String sys_name) {
		 
		return dao.countAllSystem(sys_name);
	}

	/** 
	 * Description: �������Ӧ��ϵͳ
	 * @return 
	 */
	public List<CeSystemInfo> getAllSystemInfo() {
		return dao.getSystemInfo();
	}

	/** 
	 * Description: ����Ӧ��ϵͳ�����Ϣ
	 * @param sys_name
	 * @param sys_cn_name
	 * @param sys_bk_desc
	 * @param sys_type
	 * @param dtbs_bk_date
	 * @param dtbs_bk_time
	 * @param org_user_id 
	 */
	public int updateSystemMsgByKey(String sys_cn_name, String sys_bk_desc, SYS_TYPE sys_type, JaDate dtbs_bk_date, JaTime dtbs_bk_time,String org_user_id,String sys_name) {
		return dao.updateSystemMsgByKey(sys_cn_name,sys_bk_desc,sys_type,dtbs_bk_date,dtbs_bk_time,org_user_id,sys_name);
	}
	
	/**
	 * Description: 
	 * @param sys_name
	 * @return
	 */
	public CeSystemInfo getInfoBySysName(String sys_name){
		return dao.get(sys_name);
	}

}