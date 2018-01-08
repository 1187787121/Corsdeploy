/**
 * Title: BuildConfigfileDaoService.java
 * File Description: �������������ļ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:�������������ļ������
 * @author AutoGen
 */
public class BuildConfigfileDaoService {
	@Inject private BuildConfigfileDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param BuildConfigfileInfo info
	 * @return BuildConfigfileInfo
	 */
	public BuildConfigfileInfo getInfoByKey(BuildConfigfileInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param BuildConfigfileInfo info
	 * @return BuildConfigfileInfo
	 */
	public BuildConfigfileInfo getInfoByKeyForUpdate(BuildConfigfileInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param BuildConfigfileInfo info
	 * @return int
	 */
	public int insertInfo(BuildConfigfileInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<BuildConfigfileInfo>
	 * @return int
	 */
	public int insertListInfo(List<BuildConfigfileInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ���������źͲ������Ͳ�ѯ���޸���Ϣ�б�
	 * @param server_ip
	 * @param work_id
	 * @param modify
	 * @return 
	 */
	public List<BuildConfigfileInfo> getInfoByWorkAndFopt(String server_name, String work_id, FOPT_TYPE fopt_type,CFG_TYPE cfg_type) {
		List<BuildConfigfileInfo> list = new ArrayList<BuildConfigfileInfo>();
		DBIterator<BuildConfigfileInfo> iterator = dao.getInfoByWorkAndFopt(server_name, work_id, fopt_type, cfg_type);
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
	 * Description: ���������źͷ�����IPɾ��һ����¼
	 * @param work_id
	 * @param server_ip
	 * @param modify 
	 */
	public int deleteInfoByWorkAndIp(String work_id, String server_ip, FOPT_TYPE fopt_type,String file_bk_fname) {
		return dao.deleteInfoByWorkAndIp(work_id,server_ip,fopt_type,file_bk_fname);
	}
	
	/** 
	 * Description: ���������Ų�ѯ�������ļ��б�
	 * @param server_ip
	 * @param work_id
	 * @param modify
	 * @return 
	 */
	public List<BuildConfigfileInfo> getInfoByWork(String server_ip, String work_id, CFG_TYPE cfg_type) {
		List<BuildConfigfileInfo> list = new ArrayList<BuildConfigfileInfo>();
		DBIterator<BuildConfigfileInfo> iterator = dao.getInfoByWork(server_ip, work_id, cfg_type);
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