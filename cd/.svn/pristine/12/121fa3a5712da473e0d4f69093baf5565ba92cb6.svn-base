/**
 * Title: ChChannelService.java
 * File Description: 渠道模块公共服务
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月10日
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
 * Class Description: 渠道模块公共服务
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
	 * Description: 检查渠道编码存在
	 * @param channel_code 
	 */
	public void checkChannelCodeExist(String channel_code) {
		chChannelDaoService.checkChannelCodeExist(channel_code);
	}

	/** 
	 * Description: 检查渠道是否有服务权限，没有抛异常
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
					"渠道[" + chInfo.getChannel_cn_name() + "]{" + channel_code+ "}").addScene("PARM2",
					"服务[" + srvInfo.getSrv_bk_desc() + "]{" + srv_name + "}");
		}
	}

	/** 
	 * Description: 判断渠道是否有该服务的权限
	 * @param channel_code
	 * @param srv_name
	 * @return 
	 */
	private boolean channelHasSrvPriv(String channel_code, String srv_name) {
		ChChannelSrvPrivInfo srvPirvInfo=new ChChannelSrvPrivInfo();
		srvPirvInfo.setChannel_code(channel_code);
		srvPirvInfo.setSrv_name(srv_name);
		srvPirvInfo=srvPrivDaoService.getInfoByKey(srvPirvInfo);
		if(!Assert.isEmpty(srvPirvInfo)){//渠道服务权限不为空,则根据允许禁止标志判断渠道是否有服务权限
			if(srvPirvInfo.getAf_flag()==AF_FLAG.ALLOW){
				return true;
			}else{
				return false;
			}
		}else{//否则校验服务组中是否有该服务权限
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
	 * Description: 根据主键查询一条记录
	 * @param org_channel_code
	 * @return 
	 */
	public ChChannelInfo getInfoByKey(String channel_code) {
		ChChannelInfo chInfo =new ChChannelInfo();
		chInfo.setChannel_code(channel_code);
		return chChannelDaoService.getInfoByKey(chInfo);
	}

}
