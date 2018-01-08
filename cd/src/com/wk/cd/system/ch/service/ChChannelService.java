/**
 * Title: ChChannelService.java
 * File Description: ����ģ�鹫������
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��10��
 */
package com.wk.cd.system.ch.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvPrivDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvgPrivDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.ch.info.ChChannelSrvPrivInfo;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.lang.Inject;

/**
 * Class Description: ����ģ�鹫������
 * @author HT
 */
public class ChChannelService {
	
	@Inject
	private SvSrvDaoService srvDaoService;
	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ChChannelSrvgPrivDaoService srvgPrivDaoService;
	@Inject
	private ChChannelSrvPrivDaoService srvPrivDaoService;
	
	/** 
	 * Description: ��������������
	 * @param channel_code 
	 */
	public void checkChannelCodeExist(String channel_code) {
		chChannelDaoService.checkChannelCodeExist(channel_code);
	}

	/** 
	 * Description: ��������Ƿ��з���Ȩ�ޣ�û�����쳣
	 * @param channel_code
	 * @param srv_name 
	 */
	public void checkChannelHasSrvPriv(String channel_code,String srv_name) {
		if(!channelHasSrvPriv(channel_code,srv_name)){
			ChChannelInfo chInfo=new ChChannelInfo();
			chInfo.setChannel_code(channel_code);
			chInfo=chChannelDaoService.getInfoByKey(chInfo);
			
			SvSrvInfo srvInfo=new SvSrvInfo();
			srvInfo.setSrv_name(srv_name);
			srvInfo=srvDaoService.getInfoByKey(srvInfo);

			throw new IllegalOperaterException().addScene("PARM1",
					"����[" + chInfo.getChannel_cn_name() + "]{" + channel_code+ "}").addScene("PARM2",
					"����[" + srvInfo.getSrv_bk_desc() + "]{" + srv_name + "}");
		}
	}

	/** 
	 * Description: �ж������Ƿ��и÷����Ȩ��
	 * @param channel_code
	 * @param srv_name
	 * @return 
	 */
	private boolean channelHasSrvPriv(String channel_code, String srv_name) {
		ChChannelSrvPrivInfo srvPirvInfo=new ChChannelSrvPrivInfo();
		srvPirvInfo.setChannel_code(channel_code);
		srvPirvInfo.setSrv_name(srv_name);
		srvPirvInfo=srvPrivDaoService.getInfoByKey(srvPirvInfo);
		if(!Assert.isEmpty(srvPirvInfo)){//��������Ȩ�޲�Ϊ��,����������ֹ��־�ж������Ƿ��з���Ȩ��
			if(srvPirvInfo.getAf_flag()==AF_FLAG.ALLOW){
				return true;
			}else{
				return false;
			}
		}else{//����У����������Ƿ��и÷���Ȩ��
			SvSrvInfo srvInfo =new SvSrvInfo();
			srvInfo.setSrv_name(srv_name);
			srvInfo =srvDaoService.getInfoByKey(srvInfo);
			
			int count=srvgPrivDaoService.countByChannelAndSrvg(channel_code,srvInfo.getSup_srvg_code());
			if(count>0){
				return true;
			}else{
				return false;	
			}
		}
	}

	/** 
	 * Description: ����������ѯһ����¼
	 * @param org_channel_code
	 * @return 
	 */
	public ChChannelInfo getInfoByKey(String channel_code) {
		ChChannelInfo chInfo =new ChChannelInfo();
		chInfo.setChannel_code(channel_code);
		return chChannelDaoService.getInfoByKey(chInfo);
	}

}
