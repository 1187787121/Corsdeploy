/**
 * Title: CeServerDsDaoService.java
 * File Description: ����������Դ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.lang.Inject;

/**
 * Class description:����������Դ��
 * @author AutoGen
 */
public class CeServerDsDaoService {
	@Inject private CeServerDsDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeServerDsInfo info
	 * @return CeServerDsInfo
	 */
	public CeServerDsInfo getInfoByKey(CeServerDsInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeServerDsInfo info
	 * @return CeServerDsInfo
	 */
	public CeServerDsInfo getInfoByKeyForUpdate(CeServerDsInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeServerDsInfo info
	 * @return int
	 */
	public int insertInfo(CeServerDsInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeServerDsInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeServerDsInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description: ���ݷ�������ɾ����¼
	 * @param server_name ��������
	 * @return int
	 */
	public int deleteInfoByServerName(String server_name) {
		return dao.deleteInfoByServerName(server_name);
	}
	
	/**
	 * Description: ���ݷ��������ƻ�ȡFTP��������Դ
	 * @param server_name
	 * @return
	 */
	public String getFtpConfigSocByServerName(String server_name){
		return dao.getFtpConfigSocByServerName(server_name);
	}
	
	/**
	 * Description: ���ݷ��������ƻ�ȡSHELL��������Դ
	 * @param server_name
	 * @return
	 */
	public String getShellConfigSocByServerName(String server_name){
		return dao.getShellConfigSocByServerName(server_name);
	}

	/** 
	 * Description: ���ݷ��������Ʋ�ѯ����Դ
	 * @param server_name
	 * @return 
	 */
	public List<String> querySocNameByServer(String server_name) {
		return dao.querySocNameByServer(server_name);
	}

	/** 
	 * Description: �õ���Ӧ���������ݸ���
	 * @param server_name
	 * @param soc_name
	 * @return 
	 */
	public int countServerDtsource(String server_name, String soc_name) {
		
		return dao.countServerDtsourceByKey(server_name,soc_name);
	}

	/** 
	 * Description: ���ݷ���������ѯ����������Դ��
	 * @param server_name
	 * @return 
	 */
	public List<CeServerDsInfo> querySourceByServer(String server_name) {
		return dao.querySourceByServer(server_name);
	}
}