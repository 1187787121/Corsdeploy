package com.wk.cd.remote.agent.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
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
 * SQL执行
 * Class Description: 
 * @author 12049
 */
public class AgentSQLDaoService {
	
	private static final Log logger = LogFactory.getLog();
	
	
	/**
	 *  执行select语句 返回ServiceData即Key是字段名称val是值
	 * @author hulc 
	 * @param sql
	 * @return List<ServiceData> 查询结果集
	 */
	public List<ServiceData> executeQuerySQL(String sql) {
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();
		String sql_str = "";
		Assert.assertNotEmpty(sql, "sql");
		
		DBSource db = DBSource.get("agentdb");
		Session session = db.openSession();
		session.beginTransaction();
		
		logger.debug("open session ok");
		try {
			sql_str = sql.replaceAll("\\s*;|；\\s*", "");
			logger.debug("execute sql=[{}]", sql_str);
			DBIterator<ServiceData> lst_sdts = session.queryForServiceDataIterator(sql_str);
			
			if (!Assert.isEmpty(lst_sdts)) {
				while(lst_sdts.hasNext()){
					lst_sdt.add(lst_sdts.next());
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			session.commit();
			session.close();
			Connection conn = db.getConnectionPool().getConnection();
			try {
				conn.close();
				db.destoryDBSource();
			} catch (SQLException e) {
				logger.warn("Connection colse fail{}", e);
			}
		}
		
		logger.debug("lst_sdt.size = {}", lst_sdt.size());
		return lst_sdt;
	}
	
	/**
	 * 执行select语句 分页查询(只支持有参数的SQL)
	 * Description: 
	 * @param sql
	 * @param start_num
	 * @param end_num
	 * @return
	 */
	public Map<Integer, List<ServiceData>> executeQuerySQLPage(String sql, int start_num, int end_num) {
		
		Map<Integer, List<ServiceData>> list_map = new HashMap<Integer, List<ServiceData>>();
		List<ServiceData> lst_sdt = new ArrayList<ServiceData>();
		
		Assert.assertNotEmpty(sql, "sql");
		DBSource db = DBSource.get("agentdb");
		Session session = db.openSession();
		session.beginTransaction();
		logger.debug("open session ok");
		
		try {
			String total_sql = getTargetPageDataNum(sql).replaceAll("\\s*;|；\\s*", "");
			logger.debug("page 分页长度 sql=[{}]", total_sql);
			List<ServiceData> lst_num = session.queryForServiceDataList(total_sql);
			int size = 0;
			String page_sql = getTargetPageSql(sql, start_num, end_num).replaceAll("\\s*;|；\\s*", "");
			logger.debug("page 分页数据 sql=[{}]", page_sql);
			List<ServiceData> lst_sdts = session.queryForServiceDataList(page_sql);
			if (!Assert.isEmpty(lst_sdts)) {
				size = lst_num.get(0).getInt("1");
				list_map.put(size, lst_sdts);
			} else {
				list_map.put(0, lst_sdt);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new CallExecuteSqlErrorException().addScene("SQL", e.getMessage());
		} finally {
			session.commit();
			session.close();
			Connection conn = db.getConnectionPool().getConnection();
			try {
				conn.close();
				db.destoryDBSource();
			} catch (SQLException e) {
				logger.warn("Connection colse fail{}", e);
			}
		}
		
		return list_map;
	}
	
	/**
	 * nsert/update/delete操作执行 数据源集保持整体事务
	 * Description: 
	 * @param input_lst
	 * @return 输出接口 更新条数
	 */
	public List<JDBCResultBean> executeModifySQL(List<JDBCBean> input_lst) {
		Assert.assertNotEmpty(input_lst, "input_lst");
		
		List<JDBCResultBean> output_lst = new ArrayList<JDBCResultBean>();
		String sql = "";
		
		DBSource db = DBSource.get("agentdb");
		Session session = db.openSession();
		session.beginTransaction();
		logger.debug("open session ok");
		
		for (JDBCBean input : input_lst) {
			
			try {
				// 执行SQL
				List<Integer> lst_int = new ArrayList<Integer>();
				for (int i = 0; i < input.getSql_lst().size(); i++) {
					// logger.debug("execute sql=[{}]", sql);
					sql = input.getSql_lst().get(i);
					logger.debug("execute sql=[{}]", sql);
					lst_int.add(session.execute(sql));
				}
				// 设置输出
				JDBCResultBean b = new JDBCResultBean();
				b.setSoc_name(input.getSoc_name());
				b.setRs_no(lst_int);
				output_lst.add(b);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new CallExecuteSqlErrorException().addScene("SQL", e.getCause().getMessage());
			} finally {
				session.commit();
				session.close();
				Connection conn = db.getConnectionPool().getConnection();
				try {
					conn.close();
					db.destoryDBSource();
				} catch (SQLException e) {
					logger.warn("Connection colse fail{}", e);
				}
			}
		}

		return output_lst;
	}
	
	/**
	 * Description: 获得分页个数
	 * @param sql
	 * @return
	 */
	private String getTargetPageDataNum(String sql) {
		String sel_part1 = "SELECT COUNT(1) FROM (";
		String sel_part2 = ")";
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}
	
	/**
	 * Description: 获得可以执行的分页语句
	 * @param sql
	 * @return
	 */
	private String getTargetPageSql(String sql, int offset, int limit) {
		String sel_part1 = "SELECT * FROM (SELECT ROW_NUMBER() OVER() AS ROWNUM,t.* FROM (";
		String sel_part2 = ") t ) WHERE ROWNUM > " + offset + " AND ROWNUM <=" + limit + "";
		StringBuffer sql_sBuffer = new StringBuffer();
		sql_sBuffer.append(sel_part1).append(sql).append(sel_part2);
		return sql_sBuffer.toString();
	}
}
