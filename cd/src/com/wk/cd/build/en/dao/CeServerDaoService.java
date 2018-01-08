/**
 * Title: CeServerDaoService.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-1
 */
package com.wk.cd.build.en.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.PageServerBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.util.StringUtil;

/**
 * Class description:��������
 * @author AutoGen
 */
public class CeServerDaoService {
	@Inject
	private CeServerDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeServerInfo info
	 * @return CeServerInfo
	 */
	public CeServerInfo getInfoByKey(CeServerInfo info) {
		return dao.get(info);
	}

	/**
	 * ���ݷ���������ѯһ����¼
	 * @param String server_name
	 * @return CeServerInfo
	 */
	public CeServerInfo getInfoByServerName(String server_name) {
		return dao.getInfoByServerName(server_name);
	}
	
	/**
	 * ���ݷ���������ѯһ����¼
	 * @param String server_name
	 * @return CeServerInfo
	 */
	public int countByServerCnName(String server_cn_name) {
		return dao.countByServerCnName(server_cn_name);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeServerInfo info
	 * @return CeServerInfo
	 */
	public CeServerInfo getInfoByKeyForUpdate(CeServerInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeServerInfo info
	 * @return int
	 */
	public int insertInfo(CeServerInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeServerInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeServerInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ������������һ����¼
	 * @param CeServerInfo info
	 * @return CeServerInfo
	 */
	public int updateInfoByKey(CeServerInfo info) {
		return dao.update(info);
	}

	/**
	 * ���ݷ�������ɾ��һ����¼
	 * @param String server_name
	 * @return int
	 */
	public int deleteInfoByServerName(String server_name) {
		return dao.delete(server_name);
	}

	/**
	 * Description: �������ƺ͵�ַ��ҳ��ѯ������
	 * @param server_name ����������
	 * @param server_ip ��������ַ
	 * @param start_recd ��ʼ��¼
	 * @param limit_recd ��ѯ����
	 * @return
	 */
	public List<PageServerBean> pageServerByNameAndIP(String server_name,
			String server_ip, String order_col_name, ORDER_TYPE order_type,
			int start_recd, int limit_recd) {
		if ("default".equals(order_col_name)|| StringUtil.isEmpty(order_col_name)) {
			return dao.pageServerByNameAndIP(server_name, server_ip,"CREATE_BK_DATE,CREATE_BK_TIME", ORDER_TYPE.DESC.getName(), start_recd, limit_recd);
		}
		if("create_bk_date".equals(order_col_name)) {
			return dao.pageServerByNameAndIP(server_name, server_ip, order_col_name+" "+order_type.getName()+",create_bk_time",order_type.getName(), start_recd, limit_recd);
		}
		return dao.pageServerByNameAndIP(server_name, server_ip, order_col_name,order_type.getName(), start_recd, limit_recd);
	}

	/**
	 * Description: �������ƺ͵�ַ��ҳͳ�Ʒ���������
	 * @param server_name ����������
	 * @param server_ip ��������ַ
	 * @return
	 */
	public int countServerByNameAndIP(String server_name, String server_ip) {
		return dao.countServerByNameAndIP(server_name, server_ip);
	}
	
	/**
	 * Description: ���ݵ�ַͳ�Ʒ���������
	 * @param server_ip ��������ַ
	 * @return
	 */
	public int countServerByIP(String server_ip) {
		return dao.countServerByIP(server_ip);
	}

	/**
	 * Description: ��ѯ���з���������
	 * @param
	 * @return
	 */
	public List<ServerBean> queryAllServerName() {
		DBIterator<ServerBean> iterator = dao.queryAllServerName();
		List<ServerBean> list = new ArrayList<ServerBean>();
		try {
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return list;
	}

	/**
	 * Description: ����IPģ����ѯ��������Ϣ
	 * @param server_ip
	 * @return
	 */
	public List<CeServerInfo> queryInfoByLikeIP(String server_ip) {
		DBIterator<CeServerInfo> iterator = dao.queryInfoByLikeIP(server_ip);
		List<CeServerInfo> list = new ArrayList<CeServerInfo>();
		try {
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return list;
	}
	
	/**
	 * Description: ��ѯ���м�¼
	 * @return
	 */
	public List<CeServerInfo> queryAllInfo() {
		DBIterator<CeServerInfo> iterator = dao.queryAllInfo();
		List<CeServerInfo> list = new ArrayList<CeServerInfo>();
		try {
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		} finally {
			iterator.close();
		}
		return list;
	}
}