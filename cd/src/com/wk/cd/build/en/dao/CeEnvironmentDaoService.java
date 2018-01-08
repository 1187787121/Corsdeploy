/**
 * Title: CeEnvironmentDaoService.java
 * File Description: ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
import com.wk.util.StringUtil;

/**
 * Class description:������
 * @author AutoGen
 */
public class CeEnvironmentDaoService {
	@Inject
	private CeEnvironmentDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeEnvironmentInfo info
	 * @return CeEnvironmentInfo
	 */
	public CeEnvironmentInfo getInfoByKey(String env_name) {
		return dao.get(env_name);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeEnvironmentInfo info
	 * @return CeEnvironmentInfo
	 */
	public CeEnvironmentInfo getInfoByKeyForUpdate(CeEnvironmentInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeEnvironmentInfo info
	 * @return int
	 */
	public int insertInfo(CeEnvironmentInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeEnvironmentInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeEnvironmentInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: �õ�Ӧ�û����ĸ���
	 * @param env_name
	 * @return
	 */
	public int countEnvNameNum(String env_name) {
		return dao.countEnvNameNum(env_name);
	}

	/**
	 * Description: ��ҳ��ѯӦ�û�����Ϣ
	 * @param sys_name
	 * @return
	 */
	public List<CeEnvironmentInfo> pageEnvironmentBySysName(String sys_name, String sys_cn_name, String order_col_name, ORDER_TYPE order_type, int start_recd, int limit_recd) {
//		if ("default".equals(order_col_name) || StringUtil.isEmpty(order_col_name)) {
//			return dao.pageEnvironmentDefault(sys_name, sys_cn_name, start_recd, limit_recd);
//		}
		if ("create_bk_date".equals(order_col_name)) {
			return dao.pageEnvironmentDefault(sys_name, sys_cn_name, order_type.getName(), order_type.getName(), start_recd, limit_recd);
		}
		return dao.pageEnvironmentBySysName(sys_name, sys_cn_name, order_col_name, order_type.getName(), start_recd, limit_recd);
	}

	/**
	 * Description: ��ѯӦ�û�������
	 * @param sys_name
	 * @return
	 */
	public int countEnvironmentBySysCnName(String sys_name, String sys_cn_name) {
		return dao.countEnvironmentBySysCnName(sys_name, sys_cn_name);
	}

	/**
	 * Description: ��ѯӦ�û�������
	 * @param sys_name
	 * @return
	 */
	public int countEnvironmentBySysName(String sys_name) {
		return dao.countEnvironmentBySysName(sys_name);
	}

	/**
	 * Description: ɾ��Ӧ�û���
	 * @param env_name
	 */
	public int deleteEnvirByEnvName(String env_name) {
		CeEnvironmentInfo bean = new CeEnvironmentInfo();
		bean.setEnv_name(env_name);
		return dao.delete(bean);

	}

	/**
	 * Description: ���ݻ������õ�������Ϣ
	 * @param env_name
	 * @return
	 */
	public CeEnvironmentInfo getInfoByEnvName(String env_name) {
		CeEnvironmentInfo envir_info = new CeEnvironmentInfo();
		envir_info.setEnv_name(env_name);
		return dao.get(envir_info);
	}

	/**
	 * Description: ���»�����Ϣ
	 * @param envir_update
	 */
	public int updateEnvirInfo(CeEnvironmentInfo envir_update) {
		return dao.update(envir_update);
	}

	/**
	 * Description:
	 * @param sys_name
	 * @return
	 */
	public CeEnvironmentInfo getEnvirNameBySysName(String sys_name) {
		return dao.getEnviNameBySysName(sys_name);
	}

	/**
	 * Description: ���ϵͳ��������
	 * @param sys_name
	 * @return
	 */
	public List<CeEnvironmentInfo> getEnvirListBySysName(String sys_name) {
		return dao.getEnvirListBySysName(sys_name);
	}

	/**
	 * Description: ���ϵͳ��������
	 * @param sys_name
	 * @return
	 */
	public List<CeEnvironmentInfo> getEnvirList(String sys_name) {
		return dao.getEnvirList(sys_name);
	}

	/**
	 * Description: ����Ӧ��ϵͳ����ѯ�����б�
	 * @param sys_name
	 * @return
	 */
	public List<CeEnvironmentInfo> queryEnvInfosBySys(String sys_name) {
		return dao.queryEnvInfosBySys(sys_name);
	}

	/**
	 * Description: �õ�����������
	 * @param env_name
	 * @return
	 */
	public String getInfoByEnvCnName(String env_name) {
		return dao.getInfoByEnvCnName(env_name);
	}

	/**
	 * Description: �޸�Ӧ�û�����Ϣ
	 * @param env_cn_name
	 * @param env_bk_desc
	 * @param ele_type
	 * @param dt_range
	 * @param env_type
	 * @param sys_name
	 * @param dtbs_bk_date
	 * @param dtbs_bk_time
	 * @param org_user_id
	 * @param env_name
	 */
	public int updateEnvirMsgByKey(String env_cn_name, String env_bk_desc, String ele_type, DT_RANGE dt_range, ENV_TYPE env_type, String sys_name, JaDate dtbs_bk_date, JaTime dtbs_bk_time,
			String org_user_id, String env_name) {
		return dao.updateEnvirMsgByKey(env_cn_name, env_bk_desc, ele_type, dt_range, env_type, sys_name, dtbs_bk_date, dtbs_bk_time, org_user_id, env_name);

	}

	/**
	 * Description: ��ѯ����Ӧ�û���Ȩ��
	 * @return
	 */
	public List<EnvPrivBean> queryAllEnvPriv() {
		List<EnvPrivBean> env_list = new ArrayList<EnvPrivBean>();
		DBIterator<EnvPrivBean> env_iterator = dao.queryAllEnvPriv();
		try {
			while (env_iterator.hasNext()) {
				env_list.add(env_iterator.next());
			}
		} finally {
			env_iterator.close();
		}
		return env_list;
	}
	
	/**
	 * Description: ��ȡ���������������ļ�
	 * @param env_name
	 * @return
	 */
	public List<String> queryAllConfigFile(String env_name) {
		return dao.queryAllConfigFile(env_name);
	}
	
	/**
	 * Description: ������л�����Ϣ
	 * @return
	 */
	public List<CeEnvironmentInfo> queryAllEnvMsg(){
		List<CeEnvironmentInfo> env_list = new ArrayList<CeEnvironmentInfo>();
		DBIterator<CeEnvironmentInfo> env_iteror = dao.queryAllEnvMsg();
		try {
			while(env_iteror.hasNext()){
				env_list.add(env_iteror.next());
			}
		} finally{
			env_iteror.close();
		}
		return env_list;
	}
}