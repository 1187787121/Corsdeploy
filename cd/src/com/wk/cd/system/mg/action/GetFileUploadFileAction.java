/**
 * Title: GetFileUploadFileAction.java
 * File Description: ��ȡ��Ϣ�ļ��ϴ�·��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��11��7��
 */
package com.wk.cd.system.mg.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.mg.bean.GetFileUploadFileInputBean;
import com.wk.cd.system.mg.bean.GetFileUploadFileOutputBean;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description: ��ȡ��Ϣ�ļ��ϴ�·��
 * @author HT
 */
public class GetFileUploadFileAction extends ActionBasic<GetFileUploadFileInputBean, GetFileUploadFileOutputBean>{

	
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: ��ȡ��Ϣ�ļ��ϴ�·��
	 * @param input
	 * @return 
	 */
	@Override
	protected GetFileUploadFileOutputBean doAction(GetFileUploadFileInputBean input) {
		logger.info("------GetFileUploadFileAction begin------");
		logger.debug("------user_id=[{}]",input.getOrg_user_id());
		
		GetFileUploadFileOutputBean output = new GetFileUploadFileOutputBean();
		
		JaDate date=input.getDtbs_bk_date();
		String user_id=input.getOrg_user_id();
		
		Assert.assertNotEmpty(date,GetFileUploadFileInputBean.DTBS_BK_DATECN);
		Assert.assertNotEmpty(user_id,GetFileUploadFileInputBean.ORG_USER_IDCN);
		
		//�ļ��ϴ�·��
		String upload_base_path =CfgTool.getProperties("./system.properties").getProperty("system.app.upload_base_path");
		String msg_file_path=CfgTool.getProjectPropterty("cms.msg.fileupload.path");
		
		String fileupload_path=upload_base_path+msg_file_path+user_id+File.separator+date+File.separator;
		File file =new File(fileupload_path);    
		//����ļ��в������򴴽�    
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		output.setFileupload_path(msg_file_path+user_id+File.separator+date+File.separator);
		logger.info("------GetFileUploadFileAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(GetFileUploadFileInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		return lgsvc.getLogTxt("��ȡ��Ϣ�ļ��ϴ�·��", log_lst);
	}

}
