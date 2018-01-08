/**
 * Title: JDBCRCallService.java
 * File Description: JDBC远程调用服务类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/25/2014
 */

package com.wk.cd.remote.jc.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DATABASE_TYPE;
import com.wk.cd.enu.SQL_STATE;
import com.wk.cd.exc.SqlTextIllegalException;
import com.wk.cd.remote.bean.SqlExecuteBean;
import com.wk.cd.remote.exc.CallExecuteSqlErrorException;
import com.wk.cd.remote.jc.bean.JDBCBean;
import com.wk.cd.remote.jc.bean.JDBCResultBean;
import com.wk.db.DBIterator;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;

/**
 * Class Description:JDBC远程调用服务类
 * @author lixl
 */
public class JDBCRCallService {

	private static final String JDBC_ENCODING = "gbk";
	private static final String DRIVER = "db.jdbc.driver";
	private static final String URL = "db.jdbc.url";
	private static final String USER = "db.jdbc.user";
	private static final String PASSWD = "db.jdbc.password";
	private static final String SCHEMA = "db.jdbc.schema";
	private static final String POOL = "db.jdbc.pool";
	private static final String ENCODING = "db.jdbc.encoding";
	private static final Log logger = LogFactory.getLog();

	/**
	 * 执行select语句 返回ServiceData即Key是字段名称val是值
	 * @author lixl (2014-11-25)
	 * @param input
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executeQuerySQL(JDBCBean input) {
		DBSource db;
		Session s;
		String sql = "";
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0).replaceAll("\\s*;|；\\s*", "");
			logger.debug("execute sql=[{}]", sql);
			DBIterator<ServiceData>lst_sdts = s.queryForServiceDataIterator(sql);
			if (!Assert.isEmpty(lst_sdts)) {
				while(lst_sdts.hasNext()){
					lst_sdt.add(lst_sdts.next());
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			endSession(succ, ref, tran);
			close(s, db);
		}
		return lst_sdt;
	}

	/**
	 * 执行select语句 分页查询(只支持有参数的SQL)
	 * @author xuph (2017-7-21)
	 * @param input
	 * @param end_num
	 * @param start_num
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executeQueryPageSQL(JDBCBean input, int start_num, int end_num) {
		DBSource db;
		Session s;
		String sql = "";
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0);
			logger.debug("execute 分页 sql=[{}],start_num=[{}],end_num=[{}]", sql, start_num, end_num);
			// lst_sdt = s.pageForServiceDataList(sql,start_num, end_num);
			DBIterator<ServiceData> lst_sdts = s.queryForServiceDataIterator(sql);
			if (!Assert.isEmpty(lst_sdts)) {
				while (lst_sdts.hasNext()) {
					lst_sdt.add(lst_sdts.next());
				}
				if (start_num > end_num) {
					return lst_sdt;
				}
				if (end_num > lst_sdt.size()) {
					end_num = lst_sdt.size();
				}
				lst_sdt = lst_sdt.subList(start_num, end_num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			endSession(succ, ref, tran);
			close(s, db);
		}
		return lst_sdt;
	}

	/**
	 * 执行select语句 分页查询(只支持有参数的SQL)
	 * @author xuph (2017-7-21)
	 * @param input
	 * @param end_num
	 * @param start_num
	 * @return List<ServiceData> 查询结果集
	 */
	public Map<Integer, List<ServiceData>> executeQuerySQLPage(JDBCBean input, int start_num, int end_num) {
		DBSource db;
		Session s;
		String sql = "";
		Map<Integer, List<ServiceData>> list_map = new HashMap<Integer, List<ServiceData>>();
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0);
			String total_sql = getTargetPageDataNum(sql).replaceAll("\\s*;|；\\s*", "");
			logger.debug("page 分页长度 sql=[{}]", total_sql);
			List<ServiceData> lst_num = s.queryForServiceDataList(total_sql);
			int size = 0;
			DATABASE_TYPE db_type = input.getDb_type();
			String page_sql = "";
			if(db_type==DATABASE_TYPE.DB2){
				page_sql = getTargetPageDb2Sql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			if(db_type==DATABASE_TYPE.MYSQL){
				page_sql = getTargetPageMySql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			if(db_type==DATABASE_TYPE.ORACLE){
				page_sql = getTargetPageOrcSql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			logger.debug("page 分页数据 sql=[{}]", page_sql);
			List<ServiceData> lst_sdts = s.queryForServiceDataList(page_sql);
			if (!Assert.isEmpty(lst_sdts)) {
				size = lst_num.get(0).getInt("ct_num");
				list_map.put(size, lst_sdts);
			} else {
				list_map.put(0, lst_sdt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			endSession(succ, ref, tran);
			close(s, db);
		}
		return list_map;
	}

	/**
	 * Description: 数据超过1000条
	 * @param input
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executeIteratorSql(JDBCBean input) {
		DBSource db;
		Session s;
		String sql = "";
		DBIterator<ServiceData> lst_sdt = null;

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		List<ServiceData> list = null;
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0);
			logger.debug("execute sql=[{}]", sql);
			lst_sdt = s.queryForServiceDataIterator(sql);
			list = new ArrayList<ServiceData>();
			if (!Assert.isEmpty(lst_sdt)) {
				for (ServiceData data : lst_sdt) {
					list.add(data);
				}
			}
		} catch (Exception e) {
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			lst_sdt.close();
			endSession(succ, ref, tran);
			close(s, db);
		}

		return list;
	}

	/**
	 * insert/update/delete操作执行 数据源集保持整体事务
	 * @author lixl (2014-11-25)
	 * @param input_lst
	 * @return 输出接口 更新条数
	 */
	public List<JDBCResultBean> executeModifySQL(List<JDBCBean> input_lst) {
		Assert.assertNotEmpty(input_lst, "input_lst");

		List<Session> lst_s = new ArrayList<Session>();
		List<JDBCResultBean> output_lst = new ArrayList<JDBCResultBean>();
		DBSource db;
		Session s;
		String sql = "";

		// 接口检查
		for (JDBCBean input : input_lst) {
			Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
			Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
			Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
			Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
			Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");
		}

		for (JDBCBean input : input_lst) {
			logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
			// 创建数据源
			db = createDBSource(input);

			boolean succ = true;
			com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
			com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
			s = db.openSession(ref);
			lst_s.add(s);
			logger.debug("open session ok");

			try {
				// 执行SQL
				List<Integer> lst_int = new ArrayList<Integer>();
				for (int i = 0; i < input.getSql_lst().size(); i++) {
					// logger.debug("execute sql=[{}]", sql);
					sql = input.getSql_lst().get(i);
					logger.debug("execute sql=[{}]", sql);
					lst_int.add(s.execute(sql));
				}
				// 设置输出
				JDBCResultBean b = new JDBCResultBean();
				b.setSoc_name(input.getSoc_name());
				b.setRs_no(lst_int);
				output_lst.add(b);
			} catch (Exception e) {
				succ = false;
				logger.error(e.toString(), e);
				throw new CallExecuteSqlErrorException().addScene("SQL", e.getCause().getMessage());
			} finally {
				endSession(succ, ref, tran);
				close(s, db);
			}
		}

		return output_lst;
	}

	/**
	 * insert/update/delete操作执行 数据源集保持整体事务
	 * @author xuph (2017-5-20)
	 * @param input_lst
	 * @return 输出接口 更新条数
	 */
	public SqlExecuteBean executePgSQL(List<JDBCBean> input_lst) {
		Assert.assertNotEmpty(input_lst, "input_lst");
		SqlExecuteBean pgmsg = new SqlExecuteBean();
		List<com.wk.db.SessionHandle> ref_list = new ArrayList<com.wk.db.SessionHandle>();
		List<com.wk.db.DBTransaction> tran_list = new ArrayList<com.wk.db.DBTransaction>();
		List<DBSource> db_list = new ArrayList<DBSource>();
		List<Session> sessions = new ArrayList<Session>();
		DBSource db;
		// 成功标识
		boolean succ = true;
		Session s = null;
		String sql = "";
		// 接口检查
		for (JDBCBean input : input_lst) {
			Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
			Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
			Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
			Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
			Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");
		}
		com.wk.db.SessionHandle ref = null;
		com.wk.db.DBTransaction tran = null;
		int exe_step = 1;
		String error_msg = "";
		SQL_STATE state = SQL_STATE.SUCCEED;
		long start_time = System.currentTimeMillis();
		String soc_name = "";
		try {
			List<String> soc_list = new ArrayList<String>();
			for (JDBCBean input : input_lst) {
				logger.debug("执行数据源名称=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
				soc_name = input.getSoc_name();
				if (!soc_list.contains(soc_name)) {
					soc_list.add(soc_name);
					ref = new com.wk.db.SessionHandle();
					tran = com.wk.db.DBTransaction.suspend(ref);
					ref_list.add(ref);
					tran_list.add(tran);
					// 创建数据源
					db = createDBSource(input);
					db_list.add(db);
					s = db.openSession(ref);
					sessions.add(s);
				}
				logger.debug("open session ok");
				// 执行SQL
				for (int i = 0; i < input.getSql_lst().size(); i++) {
					sql = input.getSql_lst().get(i);
					logger.debug("execute sql=[{}]", sql);
					s.execute(sql);
				}
				exe_step++;
			}
		} catch (Exception e) {
			error_msg = e.getCause().getMessage();
			state = SQL_STATE.FAILED;
			succ = false;
		} finally {
			long end_time = System.currentTimeMillis();
			pgmsg.setUsed_time(end_time - start_time);
			pgmsg.setSoc_name(soc_name);
			pgmsg.setSql_text(sql);
			if (succ) {
				exe_step--;
			}
			pgmsg.setSql_seq(exe_step);
			pgmsg.setSql_state(state);
			pgmsg.setSuccess_flag(succ);
			pgmsg.setError_msg(error_msg);
			// 关闭任务执行
			endSessions(succ, ref_list, tran_list);
			closeAllSess(sessions, db_list);
		}
		return pgmsg;
	}

	/**
	 * insert/update/delete操作执行 数据源集保持整体事务不提交执行
	 * @author xuph (2017-5-22)
	 * @param input_lst
	 * @return 输出接口 更新条数
	 */
	public SqlExecuteBean executePgSQLNoCommmit(List<JDBCBean> input_lst) {
		Assert.assertNotEmpty(input_lst, "input_lst");
		SqlExecuteBean pgmsg = new SqlExecuteBean();
		List<com.wk.db.SessionHandle> ref_list = new ArrayList<com.wk.db.SessionHandle>();
		List<com.wk.db.DBTransaction> tran_list = new ArrayList<com.wk.db.DBTransaction>();
		List<DBSource> db_list = new ArrayList<DBSource>();
		List<Session> sessions = new ArrayList<Session>();
		DBSource db;
		// 成功标识
		boolean succ = true;
		Session s = null;
		String sql = "";
		// 接口检查
		for (JDBCBean input : input_lst) {
			Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
			Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
			Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
			Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
			Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");
		}
		com.wk.db.SessionHandle ref = null;
		com.wk.db.DBTransaction tran = null;
		int exe_step = 1;
		String error_msg = "";
		SQL_STATE state = SQL_STATE.SUCCEED;
		long start_time = System.currentTimeMillis();
		String soc_name = "";
		try {
			List<String> soc_list = new ArrayList<String>();
			for (JDBCBean input : input_lst) {
				logger.debug("执行数据源名称=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
				soc_name = input.getSoc_name();
				if (!soc_list.contains(soc_name)) {
					soc_list.add(soc_name);
					ref = new com.wk.db.SessionHandle();
					tran = com.wk.db.DBTransaction.suspend(ref);
					ref_list.add(ref);
					tran_list.add(tran);
					// 创建数据源
					db = createDBSource(input);
					db_list.add(db);
					s = db.openSession(ref);
					sessions.add(s);
				}
				logger.debug("open session ok");
				// 执行SQL
				for (int i = 0; i < input.getSql_lst().size(); i++) {
					sql = input.getSql_lst().get(i);
					logger.debug("execute sql=[{}]", sql);
					s.execute(sql);
				}
				exe_step++;
			}
		} catch (Exception e) {
			error_msg = e.getCause().getMessage();
			state = SQL_STATE.FAILED;
			succ = false;
		} finally {
			long end_time = System.currentTimeMillis();
			pgmsg.setUsed_time(end_time - start_time);
			pgmsg.setSoc_name(soc_name);
			pgmsg.setSql_text(sql);
			if (succ) {
				exe_step--;
			}
			pgmsg.setSql_seq(exe_step);
			pgmsg.setSql_state(state);
			pgmsg.setSuccess_flag(succ);
			pgmsg.setError_msg(error_msg);
			// 关闭任务执行
			rollbackSessions(succ, ref_list, tran_list);
			closeAllSess(sessions, db_list);
		}
		return pgmsg;
	}

	private void rollbackSessions(boolean succ, List<com.wk.db.SessionHandle> ref_list, List<com.wk.db.DBTransaction> tran_list) {
		int num = tran_list.size();
		try {
			for (int i = 0; i < num; i++) {
				tran_list.get(i).rollback(ref_list.get(i));
			}
		} finally {
			com.wk.db.DBTransaction.resume();
		}
	}

	private void endSessions(boolean succ, List<com.wk.db.SessionHandle> ref_list, List<com.wk.db.DBTransaction> tran_list) {
		int num = tran_list.size();
		try {
			if (!succ) {
				for (int i = 0; i < num; i++) {
					tran_list.get(i).rollback(ref_list.get(i));
				}

			} else {
				try {
					for (int i = 0; i < num; i++) {
						tran_list.get(i).commit(ref_list.get(i));
					}
				} catch (RuntimeException e) {
					for (int i = 0; i < num; i++) {
						tran_list.get(i).rollback(ref_list.get(i));
					}
				}
			}
		} finally {
			com.wk.db.DBTransaction.resume();
		}

	}

	private void closeAllSess(List<Session> sessions, List<DBSource> db_list) {
		if (!Assert.isEmpty(sessions)) {
			for (Session session : sessions) {
				session.close();
			}
		}
		for (DBSource dbSource : db_list) {
			Connection conn = dbSource.getConnectionPool().getConnection();
			try {
				conn.close();
				dbSource.destoryDBSource();
			} catch (SQLException e) {
				logger.warn("Connection colse fail{}", e);
			}
		}
	}

	private void endSession(boolean issucc, com.wk.db.SessionHandle ref, com.wk.db.DBTransaction tran) {
		try {
			if (!issucc) {
				tran.rollback(ref);
			} else {
				try {
					tran.commit(ref);
				} catch (RuntimeException e) {
					tran.rollback(ref);
					throw e;
				}
			}
		} finally {
			com.wk.db.DBTransaction.resume();
		}

	}

	public DBSource createDBSource(JDBCBean input) {
		Properties pro = new Properties();
		pro.put(DRIVER, input.getJdbc_driver());
		pro.put(URL, input.getJdbc_url());
		pro.put(USER, input.getRemote_uname());
		pro.put(PASSWD, input.getRemote_passwd());
		if (!Assert.isEmpty(input.getJdbc_schema())) {
			pro.put(SCHEMA, input.getJdbc_schema());
		}
		pro.put(ENCODING, JDBC_ENCODING);
		pro.put(POOL, "nopool");
		DBSource db = DBSource.createDBSource(input.getSoc_name(), pro);
		logger.debug("create new DBSource=[{}]", db);
		return db;
	}

	private void close(Session s, DBSource db) {
		s.close();
		Connection conn = db.getConnectionPool().getConnection();
		try {
			conn.close();
			db.destoryDBSource();
		} catch (SQLException e) {
			logger.warn("Connection colse fail{}", e);
		}
	}

	/**
	 * Description: 从数据库导出数据(目前仅支持单表查询)
	 * @param input
	 */
	public List<String> exportDbData(JDBCBean input) {
		List<String> sql_list = new ArrayList<String>();
		DBSource db;

		// 非空校验
		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		// 校验传入的都为select语句
		for (String str : input.getSql_lst()) {
			String sql_type = str.substring(0, 6).toLowerCase();
			if (!"select".equals(sql_type)) {
				throw new SqlTextIllegalException().addScene("SQL", "不是查询语句，");
			}
		}

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);

		// 获取数据库版本
		String dbProduct = db.getDatabaseProductName();
		if (!Assert.isEmpty(dbProduct)) {
			dbProduct = dbProduct.toUpperCase();
		}

		try {
			// 获得连接
			Connection connection = db.getConnectionPool().getConnection();
			Statement stmt = connection.createStatement();
			for (String sql : input.getSql_lst()) {
				logger.debug("导出方案SQL:[{}]",sql);
				ResultSet rs = stmt.executeQuery(sql);

				// 获取返回结果集结构
				ResultSetMetaData eprsmd = rs.getMetaData();
				int colcount = eprsmd.getColumnCount();
				
				// 获得表名
				String table_name = "";
				if (colcount >= 1) {
					table_name = eprsmd.getTableName(1);
				}
				logger.debug("表[{}],列数：[{}]",table_name,colcount);
				String rsql = "\n";
				// 获取返回结果
				while (rs.next()) {
					rsql = "INSERT INTO " + table_name + " (";
					String rsql2 = ") VALUES (";
					for (int i = 1; i <= colcount; i++) {
						Object colvalue = rs.getObject(i);
						String col_name = eprsmd.getColumnName(i);
						String col_type = eprsmd.getColumnTypeName(i);
						// 如果类型为时间或日期，且值为空，则跳过
						if (("DATE".equals(col_type) || "TIME".equals(col_type)) && Assert.isEmpty(colvalue)) {
							continue;
						}
						rsql += col_name + " ,";
						rsql2 += getInputColValue(colvalue == null ? "" : colvalue.toString().trim(), col_type, dbProduct) + ",";
					}
					rsql = rsql.substring(0, rsql.length() - 1) + rsql2.substring(0, rsql2.length() - 1) + ");\r\n";
					logger.debug("生成的导出方案SQL:[{}]",rsql);
					sql_list.add(rsql);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		}
		return sql_list;
	}

	// 获得insert语句插入值
	public String getInputColValue(String value, String col_type, String dbProduct) {
		col_type = col_type.toUpperCase();
		if (value != null) {
			// 替换sql值内的'为''(针对DB2环境转意)
			value = value.replaceAll("'", "''");
			if (col_type.indexOf("CHAR") >= 0) {
				value = "'" + value + "'";
			} else if (col_type.indexOf("DATE") >= 0 || col_type.indexOf("TIME") >= 0) {
				if (dbProduct.contains("ORACLE")) {
					if (value.length() > 19) {
						value = value.substring(0, 19);
					}
					if (value.length() == 10) {
						value = "to_date('" + value + "','yyyy-mm-dd')";
					} else if (value.length() == 0) {
						value = "null";
					} else {
						value = "to_date('" + value + "','yyyy-mm-dd hh24:mi:ss')";
					}
				} else {
					value = "'" + value + "'";
				}
			} else if (isNumber(col_type)) {
				if (value.equals("")) {
					value = "0";
				} else {
					value = value.trim();
				}
			}
		} else {
			value = "null";
		}
		return value;
	}

	// 判断是否为数值
	private boolean isNumber(String type) {
		type = type.toUpperCase();
		if (type.indexOf("NUMBER") >= 0 || type.indexOf("INT") >= 0 || type.indexOf("FLOAT") >= 0 || type.indexOf("DECIMAL") >= 0 || type.indexOf("DOUBLE") >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 执行select语句 返回ServiceData即Key是字段名称val是值
	 * @author lixl (2014-11-25)
	 * @param input
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executeQuerySQL2(JDBCBean input, int offset, int limit) {
		DBSource db;
		Session s;
		String sql = "";
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0);
			logger.debug("execute sql=[{}]", sql);
			sql = "SELECT count(1) FROM (SELECT work_seq,org_term_no,org_srv_name from LG_LOG_MF WHERE ORG_SRV_NAME = 'pj_PageNotExecutePjProjectAction')";
			List<ServiceData> lst_sdts = s.queryForServiceDataList(sql);
			if (!Assert.isEmpty(lst_sdts)) {
				lst_sdt = lst_sdts;
			}
		} catch (Exception e) {
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			endSession(succ, ref, tran);
			close(s, db);
		}
		return lst_sdt;
	}

	/**
	 * Description: 获得可以执行的分页DB2语句
	 * @param sql
	 * @return
	 */
	private String getTargetPageDb2Sql(String sql, int offset, int limit) {
		String sel_part1 = "SELECT * FROM (SELECT ROW_NUMBER() OVER() AS ROWNUM,t.* FROM (";
		String sel_part2 = ") t ) WHERE ROWNUM > " + offset + " AND ROWNUM <=" + limit + "";
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}
	/**
	 * Description: 获得可以执行的分页MYSQL语句
	 * @param sql
	 * @return
	 */
	private String getTargetPageMySql(String sql, int offset, int limit){
		String sel_part1 = "SELECT t.* FROM (";
		String sel_part2 = ") t LIMIT " + offset + "," + (limit-offset) + "";
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}
	/**
	 * Description: 获得可以执行的分页ORACL语句
	 * @param sql
	 * @return
	 */
	private String getTargetPageOrcSql(String sql, int offset, int limit){
		String sel_part1 = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM(";
		String sel_part2 = ") A WHERE ROWNUM <= " + limit + ") WHERE RN >="+offset;
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}

	/**
	 * Description: 获得分页个数
	 * @param sql
	 * @return
	 */
	private String getTargetPageDataNum(String sql) {
		String sel_part1 = "SELECT COUNT(1) ct_num FROM (";
		String sel_part2 = ") ct_tb";
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}

	/**
	 * Description: 获得查询结果总数
	 * @param input
	 * @return int
	 */
	public int getSelectCountNum(JDBCBean input) {
		int count = 0;
		DBSource db;
		Session s;
		String sql = "";

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());
		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0).replaceAll("\\s*;|；\\s*", "");
			sql = getTargetPageDataNum(sql);
			logger.debug("execute sql=[{}]", sql);
			List<ServiceData> lst_sdts = s.queryForServiceDataList(sql);
			if (!Assert.isEmpty(lst_sdts)) {
				count = lst_sdts.get(0).getInt("ct_num");
			}
			lst_sdts = null;
		} catch (Exception e) {
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			endSession(succ, ref, tran);
			close(s, db);
		}
		return count;
	}

	/**
	 * 执行select语句 分页查询
	 * @author xuph (2017-7-21)
	 * @param input
	 * @param end_num
	 * @param start_num
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executePageSQLPage(JDBCBean input, int start_num, int end_num) {
		DBSource db;
		Session s;
		String sql = "";
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();

		Assert.assertNotEmpty(input.getJdbc_driver(), "jdbc_driver");
		Assert.assertNotEmpty(input.getJdbc_url(), "jdbc_url");
		Assert.assertNotEmpty(input.getRemote_uname(), "jdbc_uname");
		Assert.assertNotEmpty(input.getSoc_name(), "soc_name");
		Assert.assertNotEmpty(input.getSql_lst(), "sql_lst");

		logger.debug("begin create DBSource=[{}]--URL=[{}]--Driver=[{}]", input.getSoc_name(), input.getJdbc_url(), input.getJdbc_driver());

		db = createDBSource(input);
		boolean succ = true;
		com.wk.db.SessionHandle ref = new com.wk.db.SessionHandle();
		com.wk.db.DBTransaction tran = com.wk.db.DBTransaction.suspend(ref);
		s = db.openSession(ref);
		logger.debug("open session ok");
		try {
			sql = input.getSql_lst().get(0);
			DATABASE_TYPE db_type = input.getDb_type();
			String page_sql = "";
			if(db_type==DATABASE_TYPE.DB2){
				page_sql = getTargetPageDb2Sql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			if(db_type==DATABASE_TYPE.MYSQL){
				page_sql = getTargetPageMySql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			if(db_type==DATABASE_TYPE.ORACLE){
				page_sql = getTargetPageOrcSql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			}
			logger.debug("####生成执行的SQL###:[{}]"+page_sql);
			lst_sdt = s.queryForServiceDataList(page_sql);
			return lst_sdt;
		} catch (Exception e) {
			succ = false;
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			lst_sdt = null;
			endSession(succ, ref, tran);
			close(s, db);
		}
	}

}
