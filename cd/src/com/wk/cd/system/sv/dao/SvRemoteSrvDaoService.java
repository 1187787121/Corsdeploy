/**
 * Title: SvRemoteSrvDaoService.java
 * File Description: Զ�̷���������ñ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-16
 */
package com.wk.cd.system.sv.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.sv.info.*;
import com.wk.lang.Inject;

/**
 * Class description:Զ�̷���������ñ�
 * @author AutoGen
 */
public class SvRemoteSrvDaoService {
	@Inject
	private SvRemoteSrvDao dao;
	@Inject
	private SvSrvDao info_dao;

	/**
	 * ����������ѯһ����¼
	 * @param SvRemoteSrvInfo info
	 * @return SvRemoteSrvInfo
	 */
	public SvRemoteSrvInfo getInfoByKey(SvRemoteSrvInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param SvRemoteSrvInfo info
	 * @return SvRemoteSrvInfo
	 */
	public SvRemoteSrvInfo getInfoByKeyForUpdate(SvRemoteSrvInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ���ݷ������Ʋ�ѯ�����Ӧ��һ���¼(�Ҳ�����¼����)
	 * @param srv_name ��������
	 * @return һ��״̬������Զ�����ü�¼
	 */
	public SvRemoteSrvInfo queryInfoBySrvName(String srv_name) {
		SvRemoteSrvInfo infos = dao.queryInfoBySrvName(srv_name);
		SvSrvInfo info = info_dao.getInfoByName(srv_name);
		if (Assert.isEmpty(infos)) {
			throw new RecordNotFoundException().addScene("TABLE",
					SvRemoteSrvInfo.TABLECN).addScene("FIELD", info.getSrv_bk_desc()+"("+srv_name+")");
		}
		return infos;
	}

	/**
	 * ���ݷ������Ʋ�ѯ�����Ӧ��һ���¼(������)
	 * @param srv_name ��������
	 * @return һ��״̬������Զ�����ü�¼
	 */
	public SvRemoteSrvInfo queryInfoByNameNoError(String srv_name) {
		return dao.queryInfoBySrvName(srv_name);
	}

	/**
	 * ���ݷ������Ʋ�ѯ�����Ӧ��һ��״̬�����ļ�¼����
	 * @param srv_name ��������
	 * @return ��¼����
	 */
	public int countInfo(String srv_name) {
		return dao.countInfoBySrvName(srv_name);
	}

	/**
	 * ���ݷ�������ɾ�������Ӧ��һ���¼
	 * @param srv_name ��������
	 * @return ɾ������
	 */
	public int deleteInfo(String srv_name) {
		return dao.deleteInfoBySrvName(srv_name);
	}

	/**
	 * ����һ����¼
	 * @param SvRemoteSrvInfo info
	 * @return int
	 */
	public int insertInfo(SvRemoteSrvInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<SvRemoteSrvInfo>
	 * @return int
	 */
	public int insertListInfo(List<SvRemoteSrvInfo> infos) {
		return dao.insert(infos);
	}

}