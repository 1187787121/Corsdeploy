/**
 * Title: CeServerDao.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-1
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.PageServerBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:��������
 * @author AutoGen
 */
abstract class CeServerDao
		extends EntityDao<CeServerInfo> {

	/**
	 * Description: �������ƺ͵�ַ��ҳ��ѯ������
	 * @param server_name ����������
	 * @param server_ip ��������ַ
	 * @param start_recd ��ʼ��¼
	 * @param limit_recd ��ѯ����
	 * @return
	 */
	@SqlParam(sql = "select ser.SERVER_NAME,ser.SERVER_CN_NAME,ser.SERVER_IP,count(es.ENV_NAME) ENVIRONMENT_NUM,count(DISTINCT en.SYS_NAME) SYSTEM_NUM,SER.CREATE_BK_DATE,SER.CREATE_BK_TIME from ce_server ser LEFT JOIN (SELECT SERVER_NAME,ENV_NAME FROM ce_environment_server GROUP BY SERVER_NAME,ENV_NAME) es on ser.SERVER_NAME=es.SERVER_NAME LEFT JOIN ce_environment en on es.ENV_NAME=en.ENV_NAME where (ser.SERVER_NAME LIKE '%${server_name}%') and (ser.SERVER_IP LIKE '%${server_ip}%') GROUP BY ser.SERVER_NAME,ser.SERVER_CN_NAME,ser.SERVER_IP,SER.CREATE_BK_DATE,SER.CREATE_BK_TIME ORDER BY ${order_col_name} ${order_type}", dynamic = true)
	abstract List<PageServerBean> pageServerByNameAndIP(String server_name,
			String server_ip, String order_col_name, String order_type,
			int start_recd, int limit_recd);

	/**
	 * Description: �������ƺ͵�ַ��ҳͳ�Ʒ���������
	 * @param server_name ����������
	 * @param server_ip ��������ַ
	 * @return
	 */
	@SqlParam(sql = "select count(1) from ce_server where (SERVER_NAME LIKE '%${server_name}%') and (SERVER_IP LIKE '%${server_ip}%')", dynamic = true)
	abstract int countServerByNameAndIP(String server_name, String server_ip);

	/**
	 * Description: ��ѯ���з���������
	 * @param
	 * @return
	 */
	@SqlParam(querySet = { "SERVER_NAME", "SERVER_CN_NAME",
	"SERVER_IP" }, condition = "1=1")
	abstract DBIterator<ServerBean> queryAllServerName();

	/**
	 * ���ݷ���������ѯһ����¼
	 * @param String server_name
	 * @return CeServerInfo
	 */
	@SqlParam(condition = "PK")
	abstract CeServerInfo getInfoByServerName(String server_name);

	/**
	 * Description: ����IPģ����ѯ��������Ϣ
	 * @param server_ip
	 * @return
	 */
	@SqlParam(sql = "select * from ce_server where (SERVER_IP LIKE '%${server_ip}%')", dynamic = true)
	abstract DBIterator<CeServerInfo> queryInfoByLikeIP(String server_ip);
	
	/**
	 * Description: ���ݵ�ַͳ�Ʒ���������
	 * @param server_ip ��������ַ
	 * @return
	 */
	@SqlParam(condition = "server_ip=:server_ip")
	abstract int countServerByIP(String server_ip);

	/** 
	 * Description: ���ݼ��ͳ�Ʒ���������
	 * @param server_cn_name
	 * @return 
	 */
	@SqlParam(condition = "SERVER_CN_NAME=:SERVER_CN_NAME")
	abstract int countByServerCnName(String server_cn_name);

	/** 
	 * Description: ��ѯ���м�¼
	 * @return 
	 */
	@SqlParam(orderBy = "SERVER_IP")
	abstract DBIterator<CeServerInfo> queryAllInfo();

}