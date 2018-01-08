/**
 * Title: LgLogMfDaoService.java
 * File Description: 任务日志流水表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.lg.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.async.da.service.DaoServiceInf;
import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.lg.bean.LogBean;
import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.db.DBIterator;
import com.wk.db.DBService;
import com.wk.db.NewTransaction;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class description:任务日志流水表
 * @author AutoGen
 */
@DBService
public class LgLogMfDaoService extends DaoServiceInf {
	@Inject
	private LgLogMfDao dao;
	@Inject
	private LgLogQuery query;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 实现异步方法
	 * @param infos
	 */
	@NewTransaction
	public void daoRun(List<Object> infos){
		List<LgLogMfInfo> lst = new ArrayList<LgLogMfInfo>();
		for(Object obj :infos){
			lst.add((LgLogMfInfo) obj);
		}
		insertListInfo(lst);
	}

	public void daoRun(Object info){
		LgLogMfInfo _info = (LgLogMfInfo) info;
		insertInfo(_info);
	}

	/**
	 * 根据主键查询一条记录
	 * @param LgLogMfInfo info
	 * @return LgLogMfInfo
	 */
	public LgLogMfInfo getInfoByKey(LgLogMfInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param LgLogMfInfo info
	 * @return LgLogMfInfo
	 */
	public LgLogMfInfo getInfoByKeyForUpdate(LgLogMfInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param LgLogMfInfo info
	 * @return int
	 */
	public int insertInfo(LgLogMfInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<LgLogMfInfo>
	 * @return int
	 */
	public int insertListInfo(List<LgLogMfInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 独立事务插入一条记录
	 * @param LgLogMfInfo info
	 * @return int
	 */
	@NewTransaction
	public int insertInfoForNewTransaction(LgLogMfInfo info) {
		return dao.insert(info);
	}

	/**
	 * 独立事务更新日志内容
	 * @author lixl (2014-11-14)
	 * @param info
	 * @return int
	 */
	@NewTransaction
	public int updateLogTxtByKey(LgLogMfInfo info) {
		return dao.updateLogTxtByKey(info);
	}

	/**
	 * 独立事务更新日志状态
	 * @author lixl (2014-11-14)
	 * @param info
	 * @return int
	 */
	@NewTransaction
	public int updateLogStateByKey(LgLogMfInfo info) {
		return dao.updateLogStateByKey(info);
	}

	/**
	 * 根据起止日期分页查询日志信息
	 * @param start_date 起始日期
	 * @param end_date 截止日期
	 * @param work_seq_iterator 流水列表
	 * @return 日志信息
	 */
	public List<LgLogMfInfo> pageLogByDate(JaDate start_date, JaDate end_date,
			List<String> dept_list, int start_recd, int limit_recd) {
		if (start_date.isAfter(end_date)) {
			throw new DataErrorException().addScene("INPUT", "输入查询的起止日期");
		}
		String dept_str = getStringByList(dept_list);
		return dao.pageLogMfByDate(start_date, end_date, dept_str, start_recd,
				limit_recd);
	}

	/**
	 * Description: 按照条件查询日志信息
	 * @param start_date 起始日期
	 * @param end_date 截止日期
	 * @param dept_list 机构列表
	 * @param log_label 日志级别
	 * @param user_id 用户名称
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return 日志信息
	 */
	public List<LogBean> pageLog(JaDate start_date, JaDate end_date,
			int log_label, String user_id, List<Integer> role_tyles,int log_level,
			int start_recd, int limit_recd) {
		if (start_date.isAfter(end_date)) {
			throw new DataErrorException().addScene("INPUT", "输入查询的起止日期");
		}
		if (log_label < 0) {
			throw new DataErrorException().addScene("INPUT", "日志标记级别");
		}
		// 管理员查找所有日志信息
		for (int role_type : role_tyles) {
			if (role_type <= 2) {
				if(log_label > 0 && log_label == 1){
					return query.pageAllLogByDateAndLabel(log_level,start_date, end_date,start_recd, limit_recd);
				}else{
					return query.pageAllLogByDate(user_id,log_level, start_date,end_date, start_recd, limit_recd);
				}
				
			}
		}

		// 输入日志标记级别时处理
		if (log_label > 0 && log_label == 1) {
			// 日志标记小于0报错
			return query.pageLogByDateAndLabel(log_level,start_date, end_date, user_id, start_recd, limit_recd);
		} else {
			return query.pageLogByDate(user_id,log_level, start_date,end_date, user_id, start_recd, limit_recd);
		}
	}
	
	/** 
	 * Description: 按照条件查询日志信息总数
	 * @param start_date
	 * @param end_date
	 * @param log_label
	 * @param user_id
	 * @param role_types
	 * @param log_level
	 * @return 
	 */
	public int countLog(JaDate start_date, JaDate end_date, int log_label,String user_id, List<Integer> role_types, int log_level) {
		if (start_date.isAfter(end_date)) {
			throw new DataErrorException().addScene("INPUT", "输入查询的起止日期");
		}
		if (log_label < 0) {
			throw new DataErrorException().addScene("INPUT", "日志标记级别");
		}
		// 管理员查找所有日志信息
		for (int role_type : role_types) {
			if (role_type <= 2) {
				if(log_label > 0 && log_label == 1){
					return query.countAllLogByDateAndLabel(log_level,start_date, end_date);
				}else{
					return query.countAllLogByDate(log_level, start_date,end_date);
				}
				
			}
		}

		// 输入日志标记级别时处理
		if (log_label > 0 && log_label == 1) {
			// 日志标记小于0报错
			return query.countLogByDateAndLabel(log_level,start_date, end_date, user_id);
		} else {
			return query.countLogByDate(log_level, start_date,end_date, user_id);
		}
	}

	/**
	 * 精确查询一条日志信息
	 * @param info 主键信息
	 * @return 精确信息
	 */
	public LgLogMfInfo queryLogInfoByKey(LgLogMfInfo info) {
		info = dao.get(info);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					LgLogMfInfo.TABLECN).addScene("FIELD", info.getWork_seq());
		}
		return info;
	}

	/**
	 * 获取日志信息供生成日志文件使用
	 * @param start_date 起始日期
	 * @param end_date 截止日期
	 * @return 日志信息
	 */
	public DBIterator<LgLogMfInfo> getLogInfoForFile(JaDate start_date,
			JaDate end_date,String user_id,List<Integer> role_tyles) {
		if (start_date.isAfter(end_date)) {
			throw new DataErrorException().addScene("INPUT", "输入查询的起止日期");
		}
		// 管理员查找所有日志信息
		for (int role_type : role_tyles) {
			if (role_type <= 2) {
				return dao.getAllLogInfoForFile(start_date, end_date);
			}
		}
		return dao.getSelfLogInfoForFile(start_date, end_date,user_id);
	}

	/**
	 * 根据传入的流水列表查询日志信息
	 * @param work_seq_list 流水列表
	 * @return 日志信息
	 */
	public List<LgLogMfInfo> getLogByWorkSeqList(
			DBIterator<String> work_seq_iterator, int start_recd, int limit_recd) {
		String work_seq_str = getStringByDBIterator(work_seq_iterator);
		return dao.pageLogByWorkSeqList(work_seq_str, start_recd, limit_recd);
	}

	/**
	 * 将输入的List转换为字符串
	 * @param list
	 * @return
	 */
	private String getStringByList(List<String> list) {
		String str = "";
		if (!list.isEmpty()) {
			for (String s : list) {
				str = str + s + "','";
			}
			if (!str.isEmpty()) {
				str = "('" + str.substring(0, str.length() - 2) + ")";
			} else {
				str = "('')";
			}
		}
		return str;
	}

	/**
	 * 将DBIterator转换为字符串
	 * @param dbiterator
	 * @return 字符串
	 */
	private String getStringByDBIterator(DBIterator<String> dbiterator) {
		String str = "";
		try {
			while (dbiterator.hasNext()) {
				str = str + dbiterator.next() + "','";
			}
		} finally {
			dbiterator.close();
		}
		if (!str.isEmpty()) {
			str = "('" + str.substring(0, str.length() - 2) + ")";
		} else {
			str = "('')";
		}
		logger.info("查询日志流水号包括" + str);
		return str;
	}
}