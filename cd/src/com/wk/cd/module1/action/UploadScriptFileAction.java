package com.wk.cd.module1.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.UploadScriptFileInputBean;
import com.wk.cd.module1.bean.UploadScriptFileOutputBean;
import com.wk.cd.module1.xml.XmlPathUtil;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.EIterable;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;
/**
 * Class Description: �ű��ļ��ϴ�
 * @author Administrator
 */
public class UploadScriptFileAction extends ActionBasic<UploadScriptFileInputBean, UploadScriptFileOutputBean>{
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: ����ű��ļ�
	 * @param input
	 * @return
	 */
	@Override
	protected UploadScriptFileOutputBean doAction(UploadScriptFileInputBean input) {
		logger.info("-------------------------UploadScriptFileAction Begin-----------------");
		UploadScriptFileOutputBean output = new UploadScriptFileOutputBean();
		String script_file_path = input.getScript_file_path();
		Assert.assertNotEmpty(script_file_path, UploadScriptFileInputBean.SCRIPT_FILE_PATHCN);
		// ����ļ���Ϣ
		script_file_path = XmlPathUtil.getImportFileFullPath(script_file_path);
		logger.debug("--------����ɼ��ű��ļ�ȫ·�� [{}]----", script_file_path);
		EIterable<String> file_content = null;
		try {
			file_content = FileUtil.lineReader(new File(script_file_path));
		} catch (Exception e) {
			
			throw new FileNotExistException();
		}
		List<String> script_content = new ArrayList<String>();
		if(!Assert.isEmpty(file_content)){
			for (String line : file_content) {
				script_content.add(line);
			}
		}
		if(!Assert.isEmpty(script_content)){
			output.setScript_content(script_content.toArray(new String[script_content.size()]));
		}
		logger.info("-------------------------UploadScriptFileAction END-----------------");
		return output;
	}

	@Override
	protected String getLogTxt(UploadScriptFileInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getScript_file_path());
		return lgsvc.getLogTxt("����ɼ��ű��ļ�", log_lst);
	}

}
