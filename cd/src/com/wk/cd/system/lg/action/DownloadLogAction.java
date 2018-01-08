/**
 * Title: DownloadLogAction.java
 * File Description: 日志下载
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-24
 */
package com.wk.cd.system.lg.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.CreateDownloadFileErrorException;
import com.wk.cd.system.lg.bean.DownLoadLogOutputBean;
import com.wk.cd.system.lg.bean.DownloadLogInputBean;
import com.wk.cd.system.lg.dao.LgLogDownDaoService;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.system.lg.info.LgLogDownInfo;
import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description: 日志下载
 * 
 * @author tlw
 */
public class DownloadLogAction extends
		ActionBasic<DownloadLogInputBean, DownLoadLogOutputBean> {

	@Inject
	private LgLogMfDaoService lgmfsrv;
	@Inject
	private LgLogDownDaoService lgdownsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsGetUserInfoService usinfosrv;
	private static final Log logger = LogFactory.getLog();

	private static final int BUFFER = 1024;

	/**
	 * 日志下载
	 * 
	 * @param input
	 *            输入信息
	 * @return 日志信息
	 */
	@Override
	protected DownLoadLogOutputBean doAction(DownloadLogInputBean input) {
		DownLoadLogOutputBean output = new DownLoadLogOutputBean();
		logger.info("**********DownLoadLogAction Begin**********");
		String user_id = input.getOrg_user_id();
		JaDate start_date = input.getStart_bk_date();
		JaDate end_date = input.getEnd_bk_date();
		Assert.assertNotEmpty(start_date, DownloadLogInputBean.START_BK_DATECN);
		Assert.assertNotEmpty(end_date, DownloadLogInputBean.END_BK_DATECN);
		// 获取日志信息
		List<Integer> role_types = usinfosrv.queryRoleTypeByUserId(user_id);
		DBIterator<LgLogMfInfo> iter_info = lgmfsrv.getLogInfoForFile(
				start_date, end_date, user_id, role_types);
		// 获取文件名和文件路径
		String path = CfgTool.getProjectPropterty("cms.lglogmf.download.home");
		String rt_path = path;
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		String file_name = input.getWork_seq();
		String zip_name = file_name + ".zip";
		File file_dir = new File(path);
		if (!file_dir.exists()) {
			file_dir.mkdirs();
		}
		BufferedInputStream bis = null;
		ZipOutputStream out = null;
		// 将日期信息放入StringBuffer中
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					path + zip_name));
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
					new CRC32());
			out = new ZipOutputStream(cos);
			ZipEntry entry = new ZipEntry(file_name + ".log");
			out.putNextEntry(entry);
			int i = 0, count;
			StringBuffer sb = new StringBuffer("");
			while (iter_info.hasNext()) {
				if (++i <= 6) { //一条日志信息预计在150byte左右，所以此处循环6次
					sb.append(getLogString(iter_info.next()));
					continue;
				}
				bis = new BufferedInputStream(new ByteArrayInputStream(sb
						.toString().getBytes()));
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				i = 0;
				sb = new StringBuffer("");
			}
			bis = new BufferedInputStream(new ByteArrayInputStream(sb
					.toString().getBytes()));
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
		} catch (FileNotFoundException e) {
			throw new CreateDownloadFileErrorException().addScene("FILE",
					zip_name);
		} catch (IOException e) {
			throw new CreateDownloadFileErrorException().addScene("FILE",
					zip_name);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				throw new CreateDownloadFileErrorException().addScene("FILE",
						zip_name);
			}
		}
		// 登记日志下载信息文件
		LgLogDownInfo down_info = new LgLogDownInfo();
		down_info.setLog_file_name(zip_name);
		down_info.setLog_root_path(rt_path);
		down_info.setStart_bk_date(start_date);
		down_info.setEnd_bk_date(end_date);
		down_info.setUser_id(user_id);
		down_info.setCrt_bk_date(input.getDtbs_bk_date());
		down_info.setCrt_bk_time(input.getDtbs_bk_time());
		lgdownsrv.insertInfo(down_info);
		output.setPath_name(rt_path);
		output.setFile_name(zip_name);
		return output;
	}

	/**
	 * 生成日志信息
	 * 
	 * @param input
	 *            输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(DownloadLogInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("起始日期" + input.getStart_bk_date());
		lst_val.add("截止日期" + input.getEnd_bk_date());
		return lgsrv.getLogTxt("下载日志信息", lst_val);
	}

	/**
	 * 将日志信息转换为字符串
	 * 
	 * @param info
	 * @return
	 */
	private String getLogString(LgLogMfInfo info) {
		String org_channel_code = info.getOrg_channel_code();
		if (Assert.isEmpty(org_channel_code)) {
			org_channel_code = "";
		}
		String org_term_no = info.getOrg_term_no();
		if (Assert.isEmpty(org_term_no)) {
			org_term_no = "";
		}
		String org_work_code = info.getOrg_work_code();
		if (Assert.isEmpty(org_work_code)) {
			org_work_code = "";
		}
		String org_srv_name = info.getOrg_srv_name();
		if (Assert.isEmpty(org_srv_name)) {
			org_srv_name = "";
		}
		String org_rs_code = info.getOrg_rs_code();
		if (Assert.isEmpty(org_rs_code)) {
			org_rs_code = "";
		}
		String org_soc_name = info.getOrg_ary_socname();
		if (Assert.isEmpty(org_soc_name)) {
			org_soc_name = "";
		}
		String pend_work_seq = info.getPend_work_seq();
		if (Assert.isEmpty(pend_work_seq)) {
			pend_work_seq = "";
		}
		String pend_work_code = info.getPend_work_code();
		if (Assert.isEmpty(pend_work_code)) {
			pend_work_code = "";
		}
		String pend_srv_name = info.getPend_srv_name();
		if (Assert.isEmpty(pend_srv_name)) {
			pend_srv_name = "";
		}
		String pend_rs_code = info.getPend_rs_code();
		if (Assert.isEmpty(pend_rs_code)) {
			pend_rs_code = "";
		}
		String pend_soc_name = info.getPend_ary_socname();
		if (Assert.isEmpty(pend_soc_name)) {
			pend_soc_name = "";
		}
		String pendwk_bk_expl = info.getPendwk_bk_expl();
		if (Assert.isEmpty(pendwk_bk_expl)) {
			pendwk_bk_expl = "";
		}
		String pbl_code = info.getPbl_code();
		if (Assert.isEmpty(pbl_code)) {
			pbl_code = "";
		}
		String log_txt = info.getLog_txt();
		if (Assert.isEmpty(log_txt)) {
			log_txt = "";
		}
		String crt_user_id = info.getCrt_user_id();
		if (Assert.isEmpty(crt_user_id)) {
			crt_user_id = "";
		}
		String crt_dept_id = info.getCrt_dept_id();
		if (Assert.isEmpty(crt_dept_id)) {
			crt_dept_id = "";
		}
		return info.getWork_seq() + "|" + org_channel_code + "|" + org_term_no
				+ "|" + org_work_code + "|" + org_srv_name + "|" + org_rs_code
				+ "|" + org_soc_name + "|" + pend_work_seq + "|"
				+ pend_work_code + "|" + pend_srv_name + "|" + pend_rs_code
				+ "|" + pend_soc_name + "|" + pendwk_bk_expl + "|" + pbl_code
				+ "|" + info.getSr_yn_flag() + "|" + crt_user_id + "|"
				+ crt_dept_id + "|"
				+ info.getCrt_bk_date().toSimpleDateString() + "|"
				+ info.getCrt_bk_time().toSimpleTimeString() + "|" + log_txt
				+ "\n";
	}
}
