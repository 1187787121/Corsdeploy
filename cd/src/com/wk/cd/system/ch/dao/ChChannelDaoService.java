/**
 * Title: ChChannelDaoService.java
 * File Description: ���������
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-10-10
 */
package com.wk.cd.system.ch.dao;

import java.util.List;

import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.lang.Inject;

/**
 * Class description:���������
 * @author AutoGen
 */
public class ChChannelDaoService {
	@Inject private ChChannelDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param ChChannelInfo info
	 * @return ChChannelInfo
	 */
	public ChChannelInfo getInfoByKey(ChChannelInfo info) {
		return dao.get(info);
	}
	
	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param ChChannelInfo info
	 * @return ChChannelInfo
	 */
	public ChChannelInfo getInfoByKeyForUpdate(ChChannelInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param ChChannelInfo info
	 * @return int
	 */
	public int insertInfo(ChChannelInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<ChChannelInfo>
	 * @return int
	 */
	public int insertListInfo(List<ChChannelInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ��ҳ��ѯ������Ϣ�б�
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<ChChannelInfo> pageChannel(int start_recd, int limit_recd) {
		return dao.pageChannel(start_recd,limit_recd);
	}

	/** 
	 * Description: ��ѯ�����б�����
	 * @return 
	 */
	public int countChannel() {
		return dao.countChannel();
	}

	/** 
	 * Description: ����������벻����
	 * @param channel_code 
	 */
	public void checkChannelCodeNoExist(String channel_code) {
		int count=dao.countByChannelCode(channel_code);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_code);
		}
	}

	/** 
	 * Description: ����������Ʋ�����
	 * @param channel_cn_name 
	 */
	public void checkChannelCnNameNoExist(String channel_cn_name) {
		int count=dao.countByChannelCnName(channel_cn_name);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_cn_name);
		}
	}

	/** 
	 * Description: ��������������
	 * @param channel_code 
	 */
	public void checkChannelCodeExist(String channel_code) {
		int count=dao.countByChannelCode(channel_code);
		if(count==0){
			throw new RecordNotFoundException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_code);
		}
	}

	/** 
	 * Description: ɾ��������Ϣ��rcd_state=2��
	 * @param channel_code 
	 */
	public void deleteChannel(String channel_code) {
		dao.deleteChannelCode(channel_code);
//		dao.updateRcdState(RCD_STATE.ABNORMAL,channel_code);
	}

	/** 
	 * Description: �޸�������Ϣ
	 * @param info 
	 */
	public void updateChannel(ChChannelInfo info) {
		dao.update(info);
	}

	/** 
	 * Description: ��ѯ����������Ϣ�б�
	 * @return 
	 */
	public List<ChChannelInfo> listAllChannel() {
		return dao.listAll();
	}
}