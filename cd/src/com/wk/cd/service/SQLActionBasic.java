/**
 * Title: SQLActionBasic.java
 * File Description: SQL服务基类
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-8
 */
package com.wk.cd.service;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.bean.SQLActionRootInputBean;
import com.wk.cd.system.us.service.UsCheckUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: SQL服务基类，所有需要使用SQL操作的业务都需要继承此类
 * @author lixl
 */
public abstract class SQLActionBasic<SQLIN extends SQLActionRootInputBean, SQLOUT extends ActionRootOutputBean>
		extends ActionBasic<SQLIN, SQLOUT> {
	/*
	@Inject
	private JSqlParseService jsqlpsvc;
	*/
	@Inject
	private UsCheckUserPrivService chkprivsvc;

	private static final Log logger = LogFactory.getLog();
	
	/**
	 * SQL权限检查
	 */
	@Override
	protected void chkSQLPriv(SQLIN input) {
		/*
		Assert.assertNotEmpty(input.getSql_text(), input.SQL_TEXTCN);
		Assert.assertNotEmpty(input.getOrglst_soc_name(), input.ORGLST_SOC_NAMECN);
		Assert.assertNotEmpty(input.getOpt_type(), input.OPT_TYPECN);
		
		String sql = input.getSql_text();
		String user_id = input.getOrg_user_id();
		String soc_name = input.getOrglst_soc_name()[0];
		int type = jsqlpsvc.getType(new StringReader(sql));
		logger.info("sql操作类型校验：sql_text=[{}]",sql);
		// 验证操作类型
		if (input.getOpt_type() == OPT_TYPE.QUERY) {
			if (!(type == CCJSqlParserConstants.K_SELECT)) {
				throw new SqlOptTypeMismatchException();
			}
		} else if (input.getOpt_type() == OPT_TYPE.MODIFY) {
			if (!(type == CCJSqlParserConstants.K_UPDATE
					|| type == CCJSqlParserConstants.K_DELETE || type == CCJSqlParserConstants.K_INSERT)) {
				throw new SqlOptTypeMismatchException();
			}
		}
		String tablename = "";
		logger.info("sql权限校验：sql_text=[{}]",sql);
		// 权限操作验证
		switch (type) {
		case CCJSqlParserConstants.K_SELECT:
			List<String> tables = jsqlpsvc.getSelectTables(sql);
			if (tables != null) {
				for (String table : tables) {
					// 表select权限验证
					chkprivsvc.checkTabSelPriv(user_id, soc_name, table);
				}
			}
			break;
		case CCJSqlParserConstants.K_UPDATE:
			tablename = jsqlpsvc.getUpdateTables(sql);
			// 表update权限验证
			chkprivsvc.checkTabUpdPriv(user_id, soc_name, tablename);
			// 获取update列名
			List<String> cols = jsqlpsvc.getUpdateCols(sql);
			if (cols != null) {
				for (String col : cols) {
					// update操作列权限验证
					chkprivsvc.checkColUpdPriv(user_id, soc_name, tablename,col);
				}
			}
			break;
		case CCJSqlParserConstants.K_DELETE:
			tablename = jsqlpsvc.getDeleteTables(sql);
			// 表delete权限验证
			chkprivsvc.checkTabDelPriv(user_id, soc_name, tablename);
			break;
		case CCJSqlParserConstants.K_INSERT:
			tablename = jsqlpsvc.getInsertTables(sql);
			// 表insert权限验证
			chkprivsvc.checkTabInsPriv(user_id, soc_name, tablename);
			break;
		default:
			break;
		}
		*/
	}
}
