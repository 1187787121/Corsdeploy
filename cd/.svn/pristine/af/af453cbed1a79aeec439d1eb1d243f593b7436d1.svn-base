/**
 * Title: ChChannelDaoService.java
 * File Description: 渠道定义表
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
 * Class description:渠道定义表
 * @author AutoGen
 */
public class ChChannelDaoService {
	@Inject private ChChannelDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param ChChannelInfo info
	 * @return ChChannelInfo
	 */
	public ChChannelInfo getInfoByKey(ChChannelInfo info) {
		return dao.get(info);
	}
	
	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param ChChannelInfo info
	 * @return ChChannelInfo
	 */
	public ChChannelInfo getInfoByKeyForUpdate(ChChannelInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param ChChannelInfo info
	 * @return int
	 */
	public int insertInfo(ChChannelInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<ChChannelInfo>
	 * @return int
	 */
	public int insertListInfo(List<ChChannelInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 分页查询渠道信息列表
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<ChChannelInfo> pageChannel(int start_recd, int limit_recd) {
		return dao.pageChannel(start_recd,limit_recd);
	}

	/** 
	 * Description: 查询渠道列表总数
	 * @return 
	 */
	public int countChannel() {
		return dao.countChannel();
	}

	/** 
	 * Description: 检查渠道编码不存在
	 * @param channel_code 
	 */
	public void checkChannelCodeNoExist(String channel_code) {
		int count=dao.countByChannelCode(channel_code);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_code);
		}
	}

	/** 
	 * Description: 检查渠道名称不存在
	 * @param channel_cn_name 
	 */
	public void checkChannelCnNameNoExist(String channel_cn_name) {
		int count=dao.countByChannelCnName(channel_cn_name);
		if(count>0){
			throw new RecordAlreadyExistException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_cn_name);
		}
	}

	/** 
	 * Description: 检查渠道编码存在
	 * @param channel_code 
	 */
	public void checkChannelCodeExist(String channel_code) {
		int count=dao.countByChannelCode(channel_code);
		if(count==0){
			throw new RecordNotFoundException().addScene("TABLE", ChChannelInfo.TABLECN).addScene("FIELD", channel_code);
		}
	}

	/** 
	 * Description: 删除渠道信息（rcd_state=2）
	 * @param channel_code 
	 */
	public void deleteChannel(String channel_code) {
		dao.deleteChannelCode(channel_code);
//		dao.updateRcdState(RCD_STATE.ABNORMAL,channel_code);
	}

	/** 
	 * Description: 修改渠道信息
	 * @param info 
	 */
	public void updateChannel(ChChannelInfo info) {
		dao.update(info);
	}

	/** 
	 * Description: 查询所有渠道信息列表
	 * @return 
	 */
	public List<ChChannelInfo> listAllChannel() {
		return dao.listAll();
	}
}