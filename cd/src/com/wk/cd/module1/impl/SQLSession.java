package com.wk.cd.module1.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Types;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import com.wk.Controller;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.exc.CallExecuteSqlErrorException;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.db.DBSource;
import com.wk.db.pool.ConnectionPool;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 远程JDBC会话连接 Created by 姜志刚 on 2016/11/25.
 */
public class SQLSession
		extends ModuleSessionBase {

	private static final String JDBC_ENCODING = "GBK";
	private static final String DRIVER = "db.jdbc.driver";
	private static final String URL = "db.jdbc.url";
	private static final String USER = "db.jdbc.user";
	private static final String PASSWD = "db.jdbc.password";
	private static final String SCHEMA = "db.jdbc.schema";
	private static final String POOL = "db.jdbc.pool";
	private static final String ENCODING = "db.jdbc.encoding";
	protected static final int DEFAULT_TIMEOUT = 3600;

	private static final Log logger = LogFactory.getLog();

	private DBSource db;
	private ConnectionPool pool;
	private Connection conn;
	private DtSourceInfo dt_info;
	private RSession sess;
	public SQLSession(ModuleSourceInfo module_source_info, int step_count) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
	}

	@Override
	protected void implConnect() {
		dt_info = module_source_info.getDt_source_info();

		try {
			PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
			if (protocol_type == PROTOCOL_TYPE.JDBC) {
				Properties pro = new Properties();
				pro.put(DRIVER, dt_info.getJdbc_drv());
				pro.put(URL, dt_info.getJdbc_url());
				pro.put(USER, dt_info.getRemote_uname());
				String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd());
				String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
				pro.put(PASSWD, remote_passwd.trim());
				if (!Assert.isEmpty(dt_info.getJdbc_schema())) {
					pro.put(SCHEMA, dt_info.getJdbc_schema());
				}
				pro.put(ENCODING, JDBC_ENCODING);
				pro.put(POOL, "nopool");
				System.out.println(pro);
				DBSource db = DBSource.createDBSource(dt_info.getSoc_name(), pro);
				logger.debug("create new DBSource=[{}]", db);
				pool = db.getConnectionPool();
				conn = pool.getConnection();

				logger.debug("open session ok");
			} else if(protocol_type == PROTOCOL_TYPE.AGENT){
				sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),
						IMPL_TYPE.SQL, step_count, AgentRSession.SYNCHRO_TYPE,false,null,dt_info,0,0);
			}else {
				throw new RuntimeException(protocol_type + " not supported");
			}
			logger.info("{}'s session {} connected", dt_info.getSoc_name(), conn);
		} catch (RuntimeException t) {
			logger.error("连接数据源[{}]异常", dt_info.getSoc_name(), t);
			throw t;
		}
	}

	public String execute(String cmd)
		throws IOException {
		if (cmd.endsWith(";")) {
			cmd = cmd.substring(0, cmd.length() - 1);
		}
		String pre = cmd.substring(0, 4);
		if ("CALL".equalsIgnoreCase(pre)) {
			return executeProcedure(cmd);
		} else if ("EXEC".equalsIgnoreCase(pre)) {
			return executeFile(cmd);
		} else {
			return executeSql(cmd);
		}
	}

	public String executeSql(String sql) {
		PreparedStatement st = null;
		try {
			if(!Assert.isEmpty(sess)){
				ShExecRsBean result = sess.sendCmd(sql);
				if(!result.getIs_succ()){
					return "SQLWARN: "+result.getErr_msg();
				}else{
					return result.getRs_msg();
				}
			}
			StringBuilder msg = new StringBuilder();
			st = conn.prepareStatement(sql);
			boolean result_flag = st.execute();
			SQLWarning warn = st.getWarnings();
			if (warn != null) {
				msg.append("SQLWARN: ").append(warn.getMessage());
			}
			int row = st.getUpdateCount();
			if (result_flag) {
				ResultSet rs = st.getResultSet();
				ResultSetMetaData mate = rs.getMetaData();
				for (int i = 1; i <= mate.getColumnCount(); i++) {
					msg.append(mate.getColumnLabel(i)).append("   ");
				}
				msg.append('\n');
				while (rs.next()) {
					for (int i = 1; i <= mate.getColumnCount(); i++) {
						Object obj = rs.getObject(i);
						if (obj == null) {
							msg.append("null ");
						} else {
							msg.append('\'').append(obj.toString()).append("' ");
						}
					}
					msg.append('\n');
					row = rs.getRow();
				}
			} else if (row >= 0) {
				msg.append(row).append(" rows affected").append('\n');
			} else {
				msg.append("completed").append('\n');
			}
			return msg.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public String executeProcedure(String sql) {
		CallableStatement st = null;
		try {
			StringBuilder msg = new StringBuilder();
			int out_param = 0;
			if ("CALL".equalsIgnoreCase(sql.substring(0, 4)) && sql.indexOf('?', 4) > 0) {
				String[] strings = sql.split("\\(|,|\\)");
				for (String string : strings) {
					if ("?".equals(string.trim())) {
						out_param++;
					}
				}
			}
			st = conn.prepareCall(sql);
			if (out_param > 0) {
				for (int i = 1; i <= out_param; i++) {
					st.registerOutParameter(i, Types.CHAR);
				}
			}
			boolean result_flag = st.execute();
			SQLWarning warn = st.getWarnings();
			if (warn != null) {
				msg.append("SQLWARN: ").append(warn.getMessage());
			}
			if (out_param > 0) {
				for (int i = 1; i <= out_param; i++) {
					Object obj = st.getObject(i);
					if (obj == null) {
						msg.append("out").append(i).append(" = null");
					} else {
						msg.append("out").append(i).append(" = '").append(obj).append('\'');
					}
				}
			}
			int row = st.getUpdateCount();
			if (result_flag) {
				ResultSet rs = st.getResultSet();
				ResultSetMetaData mate = rs.getMetaData();
				for (int i = 1; i <= mate.getColumnCount(); i++) {
					msg.append(mate.getColumnLabel(i)).append("  ");
				}
				msg.append('\n');
				while (rs.next()) {
					for (int i = 1; i <= mate.getColumnCount(); i++) {
						Object obj = rs.getObject(i);
						if (obj == null) {
							msg.append("null ");
						} else {
							msg.append('\'').append(obj.toString()).append("' ");
						}
					}
					msg.append('\n');
					row = rs.getRow();
				}
				msg.append("rows count: " + row);
			} else if (row >= 0) {
				msg.append(row).append(" rows affected");
			} else {
				msg.append("completed");
			}
			return sql;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public String executeFile(String sql)
		throws IOException {
		implDisconnect();
		String file_path = sql.substring(4).trim();
		File file = new File(file_path);
		File log_file = new File(file_path + ".log");
		SQLExec sqlExec = new SQLExec();
		// 设置数据库参数
		String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
		sqlExec.setDriver(dt_info.getJdbc_drv());
		sqlExec.setUrl(dt_info.getJdbc_url());
		sqlExec.setUserid(dt_info.getRemote_uname());
		sqlExec.setPassword(remote_passwd.trim());
		// 要执行的脚本
		sqlExec.setSrc(file);
		// 有出错的语句该如何处理
		sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
		sqlExec.setPrint(true); // 设置是否输出
		// 输出到文件 sql.out 中；不设置该属性，默认输出到控制台
		sqlExec.setOutput(log_file);
		sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
		sqlExec.execute();
		StringBuilder msg = new StringBuilder();
		BufferedReader bf = null;
		FileReader rd = null;
		try {
			rd = new FileReader(log_file);
			bf = new BufferedReader(rd);
			String line = null;
			while ((line = bf.readLine()) != null) {
				msg.append(line).append('\n');
			}
		} finally {
			if (bf != null) {
				bf.close();
			}
			if (rd != null) {
				rd.close();
			}
		}
		msg.append("execute all success !");
		return msg.toString();
	}

	@Override
	protected void implDisconnect() {
		if (conn != null) {
			pool.closeConnection(conn);
		}
		if (db != null) {
			db.destoryDBSource();
		}
		if(sess != null){
			sess.disconnect();
		}
	}

}
