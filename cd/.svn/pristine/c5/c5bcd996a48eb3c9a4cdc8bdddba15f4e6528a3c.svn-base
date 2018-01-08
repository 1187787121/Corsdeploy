package com.wk.cd.remote.agent.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import com.wk.cd.build.exc.SqlParserException;
import com.wk.cd.build.exc.SqlTypeGetException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.SQL_STATE;
import com.wk.cd.enu.SQL_TYPE;
import com.wk.cd.remote.agent.bean.ExecuteMessageBean;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentHelperUtil;
import com.wk.cd.remote.jc.bean.JDBCBean;
import com.wk.cd.remote.jc.bean.JDBCResultBean;
import com.wk.cd.remote.jc.service.JDBCRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.sdo.ServiceData;
import com.wk.util.JSON;
import com.wk.util.JSONCaseType;

/**
 * SQL执行数据库连接,传连接信息模式
 * Class Description: 
 * @author 12049
 */
public class SqlExecTransferModeService {

	private static final Log logger = LogFactory.getLog();
	
	@Inject JDBCRCallService jdbcRCallService;
	
	public ShellBean execSQLCmd(ShellBean isbean, ShellBean osbean) {
		String sql = DESUtil.decrypt(isbean.getShell());
		logger.debug("agent[{}] connect database", isbean.getId());
		JDBCBean jdbcBean = new JDBCBean();
		
		DtSourceInfo dt_source_info = isbean.getDt_source_info();
		Assert.assertNotEmpty(dt_source_info, "dt_source_info");
		
		jdbcBean = getJdbcBeanByDtSource(dt_source_info);
		
		String[] sql_arry = null;
		if(!Assert.isEmpty(sql)){
			 sql_arry = sql.trim().split("\n");
		}
		
		//保存sql执行结果
		List<ExecuteMessageBean> sql_rs_list = new ArrayList<ExecuteMessageBean>();
		ServiceData service_data = new ServiceData();
		List<ServiceData> service_list = new ArrayList<ServiceData>();
		
		if(!Assert.isEmpty(sql_arry)){
			for(String sql_txt : sql_arry){
				ExecuteMessageBean execMsgBean = new ExecuteMessageBean();
				List<String> sql_list = new ArrayList<String>();
				sql_list.add(sql_txt);
				jdbcBean.setSql_lst(sql_list);
				SQL_TYPE sql_type = getSqlType(sql_txt);
				
				if(sql_type == SQL_TYPE.SELECT){
					
					int start_num = isbean.getStart_num();
					int end_num = isbean.getOffset() + isbean.getStart_num();
					
					if(end_num != 0){
						Map<Integer, List<ServiceData>> result_map = jdbcRCallService.executeQuerySQLPage(jdbcBean, start_num, end_num);
						ServiceData json_data = new ServiceData();
						for (Integer key : result_map.keySet()) {
							execMsgBean.setTotal_page(key);
							json_data.putInt("total_page", key);
						}
						for (Entry<Integer, List<ServiceData>> mapdt : result_map.entrySet()) {
							json_data.putBean("tbodys", mapdt.getValue());
							json_data.putInt("act_bk_num", mapdt.getValue().size());
							execMsgBean.setTbodys(mapdt.getValue());
							execMsgBean.setAct_bk_num(mapdt.getValue().size());
						}
						// 处理执行返回数据
						execMsgBean.setSql_state(SQL_STATE.SUCCEED);
						execMsgBean.setSql_type(SQL_TYPE.SELECT);
						execMsgBean.setSuccess_flag(true);
						json_data.putBean("sql_type", SQL_TYPE.SELECT);
						json_data.putBean("sql_state", SQL_STATE.SUCCEED);
						json_data.putBoolean("success_flag", true);
						service_list.add(json_data);
					}else{
						List<ServiceData> result = jdbcRCallService.executeQuerySQL(jdbcBean);
						ServiceData json_data = new ServiceData();
						json_data.putBean("tbodys", result);
						json_data.putInt("act_bk_num", result.size());
						json_data.putString("sql_text", sql_txt);
						json_data.putBean("sql_type", SQL_TYPE.SELECT);
						json_data.putBean("sql_state", SQL_STATE.SUCCEED);
						json_data.putBoolean("success_flag", true);
						service_list.add(json_data);
					}
					
				}else{
					List<JDBCBean> input_list = new ArrayList<JDBCBean>();
					input_list.add(jdbcBean);
					long start = System.currentTimeMillis();
					List<JDBCResultBean> result = jdbcRCallService.executeModifySQL(input_list);
					long end = System.currentTimeMillis();
					execMsgBean.setAct_exec_time((int) (end - start));
					execMsgBean.setModify_list(result);
					boolean success_flag = true;
					execMsgBean.setSuccess_flag(success_flag);
					execMsgBean.setSql_state(SQL_STATE.SUCCEED);
					execMsgBean.setSql_type(sql_type);
					if (success_flag && result.size() >= 0) {
						List<Integer> rs_no = result.get(0).getRs_no();
						execMsgBean.setAct_bk_num(rs_no.get(0));
					} else {
						execMsgBean.setAct_bk_num(0);
					}
					
					ServiceData json_data = new ServiceData();
					json_data.putInt("act_exec_time", (int) (end - start));
					json_data.putBean("modify_list", result);
					json_data.putBoolean("success_flag", success_flag);
					json_data.putBean("sql_state", SQL_STATE.SUCCEED);
					json_data.putBean("sql_type", sql_type);
					if (success_flag && result.size() >= 0) {
						List<Integer> rs_no = result.get(0).getRs_no();
						json_data.putInt("act_bk_num", rs_no.get(0));
					} else {
						json_data.putInt("act_bk_num", 0);
					}
					service_list.add(json_data);
				}
				sql_rs_list.add(execMsgBean);
			}
		}
		
		service_data.putBean("result", service_list);
		String json_result =JSON.fromBean(service_data, JSONCaseType.DEFAULT);
		osbean.setResult(json_result);
		
		osbean.setImpl_type(isbean.getImpl_type());
		osbean.setId(isbean.getId());
		osbean.setShell(sql);
		osbean.setExitStatus(0);
		osbean.setRs_flag("ok");
		osbean.setShell(DESUtil.encrypt(sql));
		osbean = AgentHelperUtil.getMD5Code(osbean); // 进行摘要验证
		logger.debug("jdbc task end");
		return osbean;
	}
	
	/**
	 * Description: 根据DtSourceInfo获取JDBCBean
	 * @param dt_source_info
	 * @return
	 */
	private static JDBCBean getJdbcBeanByDtSource(DtSourceInfo dt_source_info) {
		JDBCBean jdbc_bean = new JDBCBean();
		jdbc_bean.setSoc_name(dt_source_info.getSoc_name());
		jdbc_bean.setJdbc_url(dt_source_info.getJdbc_url());
		jdbc_bean.setJdbc_driver(dt_source_info.getJdbc_drv());
		jdbc_bean.setJdbc_schema(dt_source_info.getJdbc_schema());
		jdbc_bean.setProtocol_type(dt_source_info.getProtocol_type());
		// 对加密的密码 进行解密
		String remote_passwd = decryptPassword(dt_source_info.getRemote_passwd(), dt_source_info.getKey_remote_passwd());
		jdbc_bean.setRemote_passwd(remote_passwd);
		jdbc_bean.setRemote_uname(dt_source_info.getRemote_uname());
		jdbc_bean.setSoc_ip(dt_source_info.getSoc_ip());
		jdbc_bean.setSoc_port(dt_source_info.getSoc_port());
		return jdbc_bean;
	}
	
	/**
	 * Description: 对数据源的远程访问密码 进行解密
	 * @param password
	 * @param key
	 * @return
	 */
	private static String decryptPassword(String password, String key) {
		String en_key = DESUtil.docryptAllowReverse(false, null, key);
		String en_passwd = DESUtil.docryptAllowReverse(false, en_key, password).trim();
		return en_passwd;
	}
	
	/**
	 * Description: 判断SQL语句是否合法
	 * @param sql
	 */
	public static Statement checkSql(String sql) {
		try {
			CCJSqlParserManager parserManager = new CCJSqlParserManager();
			parserManager.parse(new StringReader(sql));
			Statement stmt = parserManager.parse(new StringReader(sql));
			return stmt;
		} catch (JSQLParserException e) {
			logger.error(e.toString(), e);
			throw new SqlParserException().addScene("SQL", "SQL语句").addScene(
					"REASON", "语法错误");
		}
	}

	/**
	 * Description:根据sql语句判断sql类别 ,只能支持select，update，insert，delete
	 * @param sql
	 * @return
	 */
	public static SQL_TYPE getSqlType(String sql) {
		logger.debug("获取sql[{}]的sql类型", sql);
		Statement stmt = checkSql(sql);
		if (stmt instanceof Delete) {
			return SQL_TYPE.DELETE;
		} else if (stmt instanceof Select) {
			return SQL_TYPE.SELECT;
		} else if (stmt instanceof Update) {
			return SQL_TYPE.UPDATE;
		} else if (stmt instanceof Insert) {
			return SQL_TYPE.INSERT;
		} else {
			throw new SqlTypeGetException().addScene("SQL", sql);
		}

	}
}
