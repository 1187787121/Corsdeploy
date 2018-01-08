/**
 * Title: GenNoService.java
 * File Description: 生成号码服务
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-19
 */
package com.wk.cd.common.cm.service;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.cm.info.CmSeqInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.SEQ_TYPE;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description:生成号码服务
 * @author lixl
 */
public class GenNoService {
	@Inject
	private CmSeqDaoService csDaos;
	@Inject
	private CommonService cmsvc;
	@Inject
	private BlockSeqService seqsvc;
	private static final String WORKSEQ = "WORKSQ";
	private static final String PIPWKSQ = "WKSI0";
	private static final String PPTWKSQ = "WKSP0";
	private static final String DEPTID = "DEPTID";
	private static final String ROLEID = "ROLEID";
	private static final String WORKID = "WORKID";
	private static final String COMPID = "COMPID";
	private static final String CHCODE = "CHCODE";
	private static final String SRVGSQ = "SRVGSQ";
	private static final String STISSQ = "STISSQ";
	private static final String PLANSQ = "PLANSQ";
	private static final String ODERSQ = "ODERSQ";
	private static final String PROGSQ = "PROGSQ";
	private static final String GOUPSQ = "GOUPSQ";
	private static final String STEPSQ = "STEPSQ";
	private static final String PARMSQ = "PARMSQ";
	private static final String WOSTIS = "WOSTIS";
	private static final String PROGID = "PROGID";
	private static final String STORID = "STORID";
	private static final String INTEID = "INTEID";
	private static final String BACHID = "BACHID";
	private static final String BSPGID = "BSPGID";
	private static final String COPGID = "COPGID";
	private static final String TASKID = "TASKID";
	private static final String SDJOBID = "SDJBID";
	private static final String INTASKID = "INTKID";
	private static final String IMPORTSQ = "IMPTSQ";
	private static final String REPORTID = "REPTID";
	private static final String CHARTID = "CHATID";
	private static final String TARGETID = "TARGID";
	private static final String OPFLOWID = "FLOWID";
	private static final String OPTASKID = "OPTKID";
	private static final String SDTASKID = "SDTKID";
	private static final String SDMEDDLESEQ = "SDMDSQ";
	private static final Log logger = LogFactory.getLog();

	/**
	 * 生成流水号 如果部署模式为单击或单应用集群方式，则生成连续流水号 如果是多机或多应用集群方式，流水号只能是时间戳（毫秒级)
	 * @param cur_date
	 * @return String 流水号(17位)
	 */
	public String getWorkSeq(JaDate cur_date, String ip, int port) {
		String val = CfgTool.getProjectPropterty("cms.sys.depl.mode");
		Assert.assertNotEmpty(cur_date, "cur_date");
		if ("mutli".equalsIgnoreCase(val)) {
			return getTmWorkSeq();
		}
		if ("mutli_ip".equalsIgnoreCase(val)) {
			Assert.assertNotEmpty(ip, "ip");
			return getIPWorkSeq(cur_date, ip);
		}
		if ("mutli_port".equalsIgnoreCase(val)) {
			Assert.assertNotEmpty(port, "port");
			return getPortWorkSeq(cur_date, port);
		}
		return getSeriesWorkSeq(cur_date);
	}

	/**
	 * @return String 流水号(17位)
	 */
	public String getWorkSeq() {
		return getTmWorkSeq();
	}

	/**
	 * 生成当前工作流水号 规则：日期(8)＋序号(len),流水号类型采用日序号方式，即换日后系统内流水号从“1”开始。
	 * @param JaDate 日期类型
	 * @param cur_date 当前日期 return 当前工作流水号
	 */
	private String getSeriesWorkSeq(JaDate cur_date) {
		String cur_seq;
		String str0 = "";
		long seq = 0L;
		int len = 0;
		String rs_seq;

		CmSeqInfo info = new CmSeqInfo();
		info.setSeq_name(WORKSEQ);
		info = csDaos.getInfoByKey(info);
		seq = seqsvc.getNextSeqForBlock(info.getSeq_name(), cur_date);
		cur_seq = String.valueOf(seq);
		len = cur_seq.length();
		for (int i = len; i < info.getSeq_fld_length(); i++) {
			str0 += "0";
		}
		rs_seq = cur_date.toString().replaceAll("\\-", "") + str0 + cur_seq;
		logger.debug("work seq =[{}]", rs_seq);
		return rs_seq;
	}

	/**
	 * 以毫秒时间戳做为流水号(多机集群时生成流水号方式)
	 * @return String 流水号
	 */
	private synchronized String getTmWorkSeq() {
		// 保证1 millis只能获取一个流水号
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			logger.warn("Thread fail!\n{}", e);
		}
		String seq = cmsvc.getDBCurTimestamp();
		logger.debug("tmwork seq = [{}]", seq);
		return seq;
	}

	/**
	 * 流水号格式：8(日期) + 1(标识号,IP) + 8(序号)
	 * @param cur_date
	 * @param ip
	 * @return
	 */
	private String getIPWorkSeq(JaDate cur_date, String ip) {
		String rs_seq;
		String ipr = CfgTool.getProjectPropterty("cms.sys.workseq.ip.role");
		String seq_name = getSeqNameForIP(ip, ipr);
		String prtg = getSubIPBit(ip, ipr);
		rs_seq = getSeqBySeqName(seq_name, cur_date, prtg);
		logger.debug("ip seq name = [{}]ip work seq =[{}]", seq_name, rs_seq);

		return rs_seq;
	}

	/**
	 * 流水号格式 8(日期)+1(标识号，端口)+8位序号
	 * @param cur_date
	 * @param port
	 * @return
	 */
	private String getPortWorkSeq(JaDate cur_date, int port) {
		String rs_seq;
		logger.plog("getPortWorkSeq begin");
		String ipr = CfgTool.getProjectPropterty("cms.sys.workseq.ip.role");
		logger.plog("getProjectPropterty end");
		String seq_name = getSeqNameForPort(port, ipr);
		String prtg = getSubPortBit(String.valueOf(port), ipr);
		logger.plog("getSubBit end");
		rs_seq = getSeqBySeqName(seq_name, cur_date, prtg);
		logger.debug("port seq name = [{}]port work seq =[{}]", seq_name, rs_seq);

		return rs_seq;
	}

	private String getSeqBySeqName(String seq_name, JaDate cur_date, String prtg) {
		long seq = 0L;
		String str_seq, str0 = "", rs_seq;
		logger.plog("getInfoByKey begin");
		CmSeqInfo info = csDaos.getInfoByKey(seq_name);
		if (info == null) {
			info = initCmSeq(seq_name, cur_date);
		}
		logger.plog("getInfoByKey end");
		seq = seqsvc.getNextSeqForBlock(seq_name, cur_date);
		str_seq = String.valueOf(seq);
		int len = str_seq.length();
		// ip段+补“0”+序号
		str0 += prtg;
		for (int i = len; i < info.getSeq_fld_length(); i++) {
			str0 += "0";
		}
		rs_seq = cur_date.toString().replaceAll("\\-", "") + str0 + str_seq;
		return rs_seq;
	}

	private CmSeqInfo initCmSeq(String seq_name, JaDate cur_date) {
		try {
			Thread.sleep(2000);// 防止未提交
		} catch (InterruptedException e) {
			logger.warn("sleep error[{}]", e.toString());
		}
		CmSeqInfo info = csDaos.getInfoByKeyNoCache(seq_name);
		logger.plog("getInfoByKeyNoCache end");
		if (info == null) {
			info = new CmSeqInfo();
			info.setCur_bk_seq(0);
			info.setLmod_bk_date(cur_date);
			info.setLs_bk_seq(0);
			info.setSeq_name(seq_name);
			info.setSeq_type(SEQ_TYPE.DAILY);
			info.setSeq_fld_length(8);// 固定8位，
			csDaos.insertInfoForNewTran(info);
			logger.plog("initCmSeq end");
		}
		return info;
	}

	private String getSeqNameForPort(int port, String ipr) {
		String seq_name = PPTWKSQ;
		String pb = getSubPortBit(String.valueOf(port), ipr);
		return seq_name + pb;

	}

	private String getSubPortBit(String sport, String ipr) {
		String role = ipr.substring(1, 3);
		return getSubBit(sport, role);
	}

	private String getSeqNameForIP(String ip, String ipr) {
		String seq_name = PIPWKSQ;
		String ipb = getSubIPBit(ip, ipr);
		return seq_name + ipb;
	}

	private String getSubIPBit(String ip, String ipr) {
		String[] ipa = ip.split("\\.");
		int sidx = Integer.valueOf(ipr.substring(0, 1));
		String ips = ipa[sidx - 1];// 获取ip段
		String role = ipr.substring(1, 3);
		return getSubBit(ips, role);
	}

	private String getSubBit(String src, String role) {
		int s = Integer.valueOf(role.substring(0, 1));
		int start = s - 1;
		int num = Integer.valueOf(role.substring(1, 2));
		int len = src.length();
		if (num > len)
			num = len;
		int end = start + num;
		if (s <= 0 || end > len) {
			start = src.length() - num;
			end = src.length();
		}
		String itm = src.substring(start, end);// 截取第几位串
		return itm;

	}

	/**
	 * 生成部门编码 规则：部门类型(1)+部门级别(1)+序号(4)，序号从序号表中获取。
	 * @param DEPT_TYPE 枚举类型
	 * @param int 整型
	 * @param JaDate 日期类型
	 * @param dept_type 部门类型
	 * @param dept_level 部门级别
	 * @param cur_date 当前日期 return 部门编码
	 */
	public String getDeptId(DEPT_TYPE dept_type, int dept_level, JaDate cur_date) {
		return String.valueOf(dept_type) + String.valueOf(dept_level) + getNextSeq(DEPTID, cur_date);
	}

	/**
	 * Description: 生成方案编号
	 * @param cur_date
	 * @return
	 */
	public String getProgId(JaDate cur_date) {
		return "PG" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(PROGID, cur_date);
	}

	/**
	 * 生成角色编码 规则：直接从序号表获取编号不足部分补“0”。
	 * @param JaDate 日期类型
	 * @param cur_date 当前日期 return 角色编码
	 */
	public String getRoleCode(JaDate cur_date) {
		return getNextSeq(ROLEID, cur_date);
	}

	/**
	 * 生成渠道编码 规则：直接从序号表获取编号不足部分补“0”。
	 * @param JaDate 日期类型
	 * @param cur_date 当前日期 return 角色编码
	 */
	public String getChannelCode(JaDate cur_date) {
		return getNextSeq(CHCODE, cur_date);
	}

	/**
	 * 生成服务组编码 规则：直接从序号表获取编号不足部分补“0”。
	 * @param JaDate 日期类型
	 * @param cur_date 当前日期 return 服务组编码
	 */
	public String getSrvgCode(JaDate cur_date) {
		return getNextSeq(SRVGSQ, cur_date);
	}

	/**
	 * Description: 生成统计编号
	 * @param cur_date
	 * @return
	 */
	public String getStisCode(JaDate cur_date) {
		return getNextSeq(STISSQ, cur_date);
	}

	/**
	 * Description: 生成工单编号
	 * @param cur_date
	 * @return
	 */
	public String getOderCode(JaDate cur_date) {
		return "WO" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(ODERSQ, cur_date);
	}

	/**
	 * Description: 生成集成编号
	 * @param cur_date
	 * @return
	 */
	public String getInteNum(JaDate cur_date) {
		return "Inte" + getNextSeq(INTEID, cur_date);
	}

	/**
	 * Description: 生成组件或组件组ID
	 * @param cur_date
	 * @return
	 */
	public String getCompCode(JaDate cur_date) {
		return "COMP" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(COMPID, cur_date);
	}

	public String getTaskCode(JaDate cur_date) {
		return "TA" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(TASKID, cur_date);
	}

	public String getJobCode(JaDate cur_date) {
		return "JOB" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(SDJOBID, cur_date);
	}

	/**
	 * Description: 生成故障方案编号
	 * @param cur_date
	 * @return
	 */
	public String getProgCode(JaDate cur_date) {
		return cur_date.toString().replaceAll("\\-", "") + getNextSeq(PROGSQ, cur_date);
	}

	/**
	 * Description: 生成入库编号
	 * @param cur_date
	 * @return
	 */
	public String getStorageId(JaDate cur_date) {
		return "ST" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(STORID, cur_date);
	}

	/**
	 * Description: 生成系统方案编号
	 * @param cur_date
	 * @return
	 */
	public String getBsProgId(JaDate cur_date) {
		return "BP" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(BSPGID, cur_date);
	}

	/**
	 * Description: 生成采集方案编号
	 * @param cur_date
	 * @return
	 */
	public String getCollectProgId(JaDate cur_date) {
		return "CO" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(COPGID, cur_date);
	}

	/**
	 * Description: 生成巡检编号
	 * @param cur_date
	 * @return
	 */
	public String getInTaskId(JaDate cur_date) {
		return "IN" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(INTASKID, cur_date);
	}

	/**
	 * Description: 生成报告编号
	 * @param cur_date
	 * @return
	 */
	public String getReportId(JaDate cur_date) {
		return "RP" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(REPORTID, cur_date);
	}

	/**
	 * Description: 生成报表编号
	 * @param cur_date
	 * @return
	 */
	public String getChartId(JaDate cur_date) {
		return "CH" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(CHARTID, cur_date);
	}

	/**
	 * Description: 生成指标编号
	 * @param cur_date
	 * @return
	 */
	public String getTargetId(JaDate cur_date) {
		return "TG" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(TARGETID, cur_date);
	}

	/**
	 * Description: 生成自动运维流程ID
	 * @param cur_date
	 * @return
	 */
	public String getOpFlowId(JaDate cur_date) {
		return "FL" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(OPFLOWID, cur_date);
	}

	/**
	 * Description: 生成调度流程版本号
	 * @param version_id
	 * @return
	 */
	public String genarateSdVersionId(String version_id) {
		float versionNum = Float.parseFloat(version_id);
		versionNum = versionNum + 1.0f;
		return versionNum + "";
	}

	/**
	 * Description: 生成自动运维任务ID
	 * @param cur_date
	 * @return
	 */
	public String getOpTaskId(JaDate cur_date) {
		return "OT" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(OPTASKID, cur_date);
	}

	/**
	 * Description: 生成自动运维任务ID
	 * @param cur_date
	 * @return
	 */
	public String getSdTaskId(JaDate cur_date) {
		return "TK" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(SDTASKID, cur_date);
	}

	public String getSMeddleSeq(JaDate cur_date) {
		return "SD" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(SDMEDDLESEQ, cur_date);
	}

	/**
	 * Description: 生成步骤ID
	 * @param cur_date
	 * @return
	 */
	public String getStepCode(JaDate cur_date) {
		return getNextSeq(STEPSQ, cur_date);
	}

	/**
	 * Description: 生成前置关联号
	 * @param cur_date
	 * @return
	 */
	public String getPlanCode(JaDate cur_date) {
		return "L" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(PLANSQ, cur_date);
	}

	/**
	 * Description: 生成分组编号
	 * @param cur_date
	 * @return
	 */
	public String getGroupCode(JaDate cur_date) {
		return "T" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(GOUPSQ, cur_date);
	}

	/**
	 * Description: 生成参数序号
	 * @param cur_date
	 * @return
	 */
	public String getParamSeq(JaDate cur_date) {
		return getNextSeq(PARMSQ, cur_date);
	}

	/**
	 * Description: 生成工单统计编号
	 * @param cur_date
	 * @return
	 */
	public String getWoStatisSeq(JaDate cur_date) {
		return getNextSeq(WOSTIS, cur_date);
	}

	/**
	 * Description: 生成导入流水号
	 * @param cur_date
	 * @return
	 */
	public String getInImportSeq(JaDate cur_date) {
		return cur_date.toString().replaceAll("\\-", "") + getNextSeq(IMPORTSQ, cur_date);
	}

	/**
	 * Description: 生成环境配置文件批次号
	 * @param cur_date
	 * @return
	 */
	public String getBatchNo(JaDate cur_date) {
		return cur_date.toString().replaceAll("\\-", "") + getNextSeq(BACHID, cur_date);
	}

	/**
	 * 生成部门角色编码 规则：部门编码＋角色编码
	 * @param String 字符串类型
	 * @param JaDate 日期类型
	 * @param dept_id 部门编码
	 * @param role_code 角色编码
	 * @param cur_date 当前日期 return 部门角色编码
	 */
	public String getDeptRoleCode(String dept_id, String role_code, JaDate cur_date) {
		return dept_id + role_code;
	}

	/**
	 * 生成任务编码 规则：直接从序号表获取编号不足部分补“0”。
	 * @param JaDate 日期类型
	 * @param cur_date 当前日期 return 任务编码
	 */
	public String getWorkCode(JaDate cur_date) {
		return "TK" + cur_date.toString().replaceAll("\\-", "") + getNextSeq(WORKID, cur_date);
	}

	public String getImportSeq() {
		return getSeriesWorkSeq(JaDate.today());
	}

	public String getISPTQuerySessionId() {
		return getSeriesWorkSeq(JaDate.today());
	}

	/**
	 * 根据序号名称和序号种类，获取对应的序号，并且根据序号长度， 将获取的序号转换为字符型，长度不够时前补"0"。
	 * @param String 输入参数类型
	 * @param JaDate 日期类型
	 * @param seq_name 序号名称
	 * @param cur_date 当前日期 return 字符串类型的序号
	 */
	private String getNextSeq(String seq_name, JaDate cur_date) {
		String cur_seq = "";
		String str0 = "";
		CmSeqInfo info = new CmSeqInfo();
		info.setSeq_name(seq_name);
		info = csDaos.getSeqByKey(seq_name, cur_date, 1);
		if (info.getSeq_type() == SEQ_TYPE.PERMANENT) {
			cur_seq = String.valueOf(info.getCur_bk_seq() + 1);

		} else if (info.getSeq_type() == SEQ_TYPE.DAILY) {
			if (cur_date.isBefor(info.getLmod_bk_date())) {
				cur_seq = String.valueOf(info.getLs_bk_seq());
			} else if (cur_date.isAfter(info.getLmod_bk_date())) {
				cur_seq = String.valueOf(info.getCur_bk_seq() + 1);
			} else {
				cur_seq = String.valueOf(info.getCur_bk_seq() + 1);
			}
		}
		for (int i = cur_seq.length(); i < info.getSeq_fld_length(); i++) {
			str0 += "0";
		}
		return str0 + cur_seq;
	}

	public static void main(String[] args) {
		Injector inject = Controller.getInstance().getInjector();
		GenNoService sc = inject.getBean(GenNoService.class);
		// System.out.println(svc.getSeqNameForIP("192.168.0.121", "401"));
		// System.out.println(svc.getSeqNameForIP("192.168.0.12", "401"));
		// System.out.println(svc.getSeqNameForIP("192.168.0.1", "401"));
		// System.out.println(svc.getSeqNameForIP("192.168.0.1", "411"));
		// System.out.println(svc.getSeqNameForIP("192.168.0.12", "421"));
		// System.out.println(svc.getSeqNameForIP("192.168.0.123", "431"));
		// System.out.println(svc.getSeqNameForPort(8080, "041"));
		// System.out.println(svc.getSeqNameForPort(8081, "041"));
		// System.out.println(svc.getSeqNameForPort(8081, "031"));
		// System.out.println(svc.getSeqNameForPort(8071, "021"));
		// System.out.println(svc.getSeqNameForPort(7081, "011"));
		// System.out.println(svc.getSeqNameForPort(7081, "001"));
		System.out.println(sc.getInImportSeq(JaDate.today()));
	}
}
