package com.wk.cd.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.Union;

/**
 * Class Description: jsql解析表工具类
 * @author HT
 */
public class TablesFinder implements SelectVisitor, FromItemVisitor, ExpressionVisitor,ItemsListVisitor{
	private List<String> tables;

	/** 
	 * Description: 获取select 的操作列名
	 * @param select
	 * @return 
	 */
	public List<String> getTableList(Select select) {
		tables = new ArrayList<String>();
		select.getSelectBody().accept(this);
		return removeDuplicate(tables);
	}
	
	
	/**
	 * Description: 去重复
	 * @param tables
	 * @return
	 */
	private List<String> removeDuplicate(List<String> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	/** 
	 * Description: 
	 * @param binaryExpression 
	 */
	public void visitBinaryExpression(BinaryExpression binaryExpression) {
		binaryExpression.getLeftExpression().accept(this);
		binaryExpression.getRightExpression().accept(this);
	}

	/** 
	 * Description: SelectVisitor
	 * @param plainselect 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void visit(PlainSelect plainselect) {
		plainselect.getFromItem().accept(this);
		if (plainselect.getJoins() != null) {
			for (Iterator joinsIt = plainselect.getJoins().iterator(); joinsIt.hasNext();) {
				Join join = (Join) joinsIt.next();
				join.getRightItem().accept(this);
			}
		}
		if (plainselect.getWhere() != null)
			plainselect.getWhere().accept(this);
		
	}

	/** 
	 * Description: SelectVisitor
	 * @param union 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void visit(Union union) {
		for (Iterator iter = union.getPlainSelects().iterator(); iter.hasNext();) {
			PlainSelect plainSelect = (PlainSelect) iter.next();
			visit(plainSelect);
		}
	}

	/** 
	 * Description: FromItemVisitor
	 * @param table 
	 */
	@Override
	public void visit(Table table) {
		String tableWholeName = table.getWholeTableName();
		tables.add(tableWholeName);
	}

	/** 
	 * Description: FromItemVisitor
	 * @param subselect 
	 */
	@Override
	public void visit(SubSelect subselect) {
		subselect.getSelectBody().accept(this);
	}

	/** 
	 * Description: FromItemVisitor
	 * @param subjoin 
	 */
	@Override
	public void visit(SubJoin subjoin) {
		subjoin.getLeft().accept(this);
		subjoin.getJoin().getRightItem().accept(this);
	}


	/** 
	 * Description: ExpressionVisitor
	 * @param nullvalue 
	 */
	@Override
	public void visit(NullValue nullvalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param function 
	 */
	@Override
	public void visit(Function function) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param inverseexpression 
	 */
	@Override
	public void visit(InverseExpression inverseexpression) {
		inverseexpression.getExpression().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param jdbcparameter 
	 */
	@Override
	public void visit(JdbcParameter jdbcparameter) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param doublevalue 
	 */
	@Override
	public void visit(DoubleValue doublevalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param longvalue 
	 */
	@Override
	public void visit(LongValue longvalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param datevalue 
	 */
	@Override
	public void visit(DateValue datevalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param timevalue 
	 */
	@Override
	public void visit(TimeValue timevalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param timestampvalue 
	 */
	@Override
	public void visit(TimestampValue timestampvalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param parenthesis 
	 */
	@Override
	public void visit(Parenthesis parenthesis) {
		parenthesis.getExpression().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param stringvalue 
	 */
	@Override
	public void visit(StringValue stringvalue) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param addition 
	 */
	@Override
	public void visit(Addition addition) {
		visitBinaryExpression(addition);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param division 
	 */
	@Override
	public void visit(Division division) {
		visitBinaryExpression(division);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param multiplication 
	 */
	@Override
	public void visit(Multiplication multiplication) {
		visitBinaryExpression(multiplication);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param subtraction 
	 */
	@Override
	public void visit(Subtraction subtraction) {
		visitBinaryExpression(subtraction);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param andexpression 
	 */
	@Override
	public void visit(AndExpression andexpression) {
		visitBinaryExpression(andexpression);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param orexpression 
	 */
	@Override
	public void visit(OrExpression orexpression) {
		visitBinaryExpression(orexpression);	
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param between 
	 */
	@Override
	public void visit(Between between) {
		between.getLeftExpression().accept(this);
		between.getBetweenExpressionStart().accept(this);
		between.getBetweenExpressionEnd().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param equalsto 
	 */
	@Override
	public void visit(EqualsTo equalsto) {
		visitBinaryExpression(equalsto);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param greaterthan 
	 */
	@Override
	public void visit(GreaterThan greaterthan) {
		visitBinaryExpression(greaterthan);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param greaterthanequals 
	 */
	@Override
	public void visit(GreaterThanEquals greaterthanequals) {
		visitBinaryExpression(greaterthanequals);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param inexpression 
	 */
	@Override
	public void visit(InExpression inexpression) {
		inexpression.getLeftExpression().accept(this);
		inexpression.getItemsList().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param isnullexpression 
	 */
	@Override
	public void visit(IsNullExpression isnullexpression) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param likeexpression 
	 */
	@Override
	public void visit(LikeExpression likeexpression) {
		visitBinaryExpression(likeexpression);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param minorthan 
	 */
	@Override
	public void visit(MinorThan minorthan) {
		visitBinaryExpression(minorthan);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param minorthanequals 
	 */
	@Override
	public void visit(MinorThanEquals minorthanequals) {
		visitBinaryExpression(minorthanequals);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param notequalsto 
	 */
	@Override
	public void visit(NotEqualsTo notequalsto) {
		visitBinaryExpression(notequalsto);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param column 
	 */
	@Override
	public void visit(Column column) {
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param caseexpression 
	 */
	@Override
	public void visit(CaseExpression caseexpression) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param whenclause 
	 */
	@Override
	public void visit(WhenClause whenclause) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param existsexpression 
	 */
	@Override
	public void visit(ExistsExpression existsexpression) {
		existsexpression.getRightExpression().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param allcomparisonexpression 
	 */
	@Override
	public void visit(AllComparisonExpression allcomparisonexpression) {
		allcomparisonexpression.GetSubSelect().getSelectBody().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param anycomparisonexpression 
	 */
	@Override
	public void visit(AnyComparisonExpression anycomparisonexpression) {
		anycomparisonexpression.GetSubSelect().getSelectBody().accept(this);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param concat 
	 */
	@Override
	public void visit(Concat concat) {
		visitBinaryExpression(concat);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param matches 
	 */
	@Override
	public void visit(Matches matches) {
		visitBinaryExpression(matches);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param bitwiseand 
	 */
	@Override
	public void visit(BitwiseAnd bitwiseand) {
		visitBinaryExpression(bitwiseand);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param bitwiseor 
	 */
	@Override
	public void visit(BitwiseOr bitwiseor) {
		visitBinaryExpression(bitwiseor);
	}

	/** 
	 * Description: ExpressionVisitor
	 * @param bitwisexor 
	 */
	@Override
	public void visit(BitwiseXor bitwisexor) {
		visitBinaryExpression(bitwisexor);
	}

	/** 
	 * Description: ItemsListVisitor
	 * @param expressionlist 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void visit(ExpressionList expressionlist) {
		for (Iterator iter = expressionlist.getExpressions().iterator(); iter.hasNext();) {
			Expression expression = (Expression) iter.next();
			expression.accept(this);
		}
	}	

}
