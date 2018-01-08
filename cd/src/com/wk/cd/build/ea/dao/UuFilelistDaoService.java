/**
 * Title: UuFilelistDaoService.java
 * File Description: �ļ��嵥��
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.lang.Inject;

/**
 * Class description:�ļ��嵥��
 * @author AutoGen
 */
public class UuFilelistDaoService {
	@Inject private UuFilelistDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param UuFilelistInfo info
	 * @return UuFilelistInfo
	 */
	public UuFilelistInfo getInfoByKey(UuFilelistInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UuFilelistInfo info
	 * @return UuFilelistInfo
	 */
	public UuFilelistInfo getInfoByKeyForUpdate(UuFilelistInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UuFilelistInfo info
	 * @return int
	 */
	public int insertInfo(UuFilelistInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UuFilelistInfo>
	 * @return int
	 */
	public int insertListInfo(List<UuFilelistInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ɾ���ļ��嵥
	 * @param list_uuid 
	 */
	public int deleteUuFileByListId(String list_uuid) {
		return dao.deleteUuFileByListId(list_uuid);
	}

	/** 
	 * Description: ����ļ��嵥�б�
	 * @param file_uuid
	 * @return 
	 */
	public List<UuFilelistInfo> getInfoByFileUuId(String list_uuid) {
		return dao.queryInfoByFileUuId(list_uuid);
	}
	
	/**
	 * Description: ����UUID��ȡ���а���
	 * @param list_uuid
	 * @return
	 */
	public List<String> queryPacList(String list_uuid){
		return dao.queryPacList(list_uuid);
	}
	
	/**
	 * Description: ���ݰ�����ѯ�ļ��б�
	 * @param list_uuid
	 * @param package_name
	 * @return
	 */
	public List<UuFilelistInfo> queryFileByPac(String list_uuid, String package_name){
		return dao.queryFileByPac(list_uuid, package_name);
	}

}