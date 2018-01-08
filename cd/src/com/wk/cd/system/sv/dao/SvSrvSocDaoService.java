/**
 * Title: SvSrvSocDaoService.java
 * File Description: ��������Դ���ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.sv.info.*;
import com.wk.lang.Inject;

/**
 * Class description:��������Դ���ñ�
 * @author AutoGen
 */
public class SvSrvSocDaoService {
	@Inject private SvSrvSocDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param SvSrvSocInfo info
	 * @return SvSrvSocInfo
	 */
	public SvSrvSocInfo getInfoByKey(SvSrvSocInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param SvSrvSocInfo info
	 * @return SvSrvSocInfo
	 */
	public SvSrvSocInfo getInfoByKeyForUpdate(SvSrvSocInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param SvSrvSocInfo info
	 * @return int
	 */
	public int insertInfo(SvSrvSocInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<SvSrvSocInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvSrvSocInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * ���ݷ������Ʋ�ѯ����Դ����������¼
	 * @param String srv_name
	 * @return List<SvSrvSocInfo>
	 */
	public List<SvSrvSocInfo> getListInfoByName(String srv_name) {
		List<SvSrvSocInfo> srv_soc_lst = dao.getListInfoByName(srv_name);
		if (Assert.isEmpty(srv_soc_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvSrvSocInfo.TABLECN).addScene("FIELD", srv_name);
		}
		return srv_soc_lst;
	}
	
	/**
	 * ���ݷ�������ɾ��һ���������Դ��Ϣ
	 * @param srv_name ��������
	 * @return int ɾ������
	 */
	public int deleteInfo(String srv_name){
		return dao.deleteInfo(srv_name);
	}
	
	/**
	 * ���ݷ������Ʋ�ѯ��¼����
	 * @param srv_name ��������
	 * @return int ��¼����
	 */
	public int countInfo(String srv_name){
		return dao.countInfo(srv_name);
	}
}